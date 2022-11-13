/**
207. Course Schedule (Detecting circle in directed graph)

Example 1:
Input: numCourses = 2, prerequisites = [[1,0]]
Output: true
Explanation: There are a total of 2 courses to take. 
To take course 1 you should have finished course 0. So it is possible.

Example 2:
Input: numCourses = 2, prerequisites = [[1,0],[0,1]]
Output: false
Explanation: There are a total of 2 courses to take. 
To take course 1 you should have finished course 0, and to take course 0 you should also have finished course 1. So it is impossible.

Algorithom: BFS
Data Structure:
1. 用一个list去存每一个course对应的indegree（有多少个course指向他）是多少
2. 用一个map去存每一个course和他对应的pre-course是啥
3. 用一个queue去记录所有indegree=0（ready to delete) 的course是哪些

Transfer Graph:
1. 对于每一个[course，pre_courses] 的 pair, 放进hashmap里（key = pre_course，value = list of course_to_take <- 一个pair add一次)
2. 相应的course对应的indegree + 1 （用indegree list存， coursenumber = index）

3. 对于所有在indegree list里为0的index（course number）， 放进queue里
4. 每一个在queue里的数，pop第一个数， pop的同时从graph里找到该数对应的list of course_to_take, 对list of course_to_take的每一个数在queue上找到相应的位置减一 （减indegree）
5. 如果新的indegree list里面有0，加进queue里，直到所有的list of course_to_take 走完，queue是empty的时候停止

6. 最后check一次indegree里的每个数是不是都为0，如果为有非0则代表有circle, 也可以用count， 没pop一次就count++，查最后的count == numCourses



Complexity:
- Time: O(N^2)
- Space: O(N) (Hashmap)
*/


class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        int[] inDegree = new int[numCourses];
        HashMap<Integer, List<Integer>> graph = new HashMap<>();

        for (int[] pre: prerequisites) {
            int course = pre[0];
            int pre_courses = pre[1];
            inDegree[course]++;
            if (graph.containsKey(pre_courses)) {
                graph.get(pre_courses).add(course);
            } else {
                List<Integer> to_take_course = new ArrayList<>();
                to_take_course.add(course);
                graph.put(pre_courses, to_take_course);
            }
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (inDegree[i] == 0) {
                queue.add(i);
            }
        }

        while(!queue.isEmpty()) {
            int cur = queue.poll();
            List<Integer> to_take_courses = graph.get(cur);
            for (int i = 0; to_take_courses != null && i < to_take_courses.size(); i++) {
                inDegree[to_take_courses.get(i)]--;
                if (inDegree[to_take_courses.get(i)] == 0) {
                    queue.add(to_take_courses.get(i));
                }
            }
        }

        return true;

    }
}



// 自己写哒
class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        int count = 0;
        int[] inDegree = new int[numCourses];
        HashMap<Integer, List<Integer>> pre_to_take_map = new HashMap<>();

        // Convert to map
        for (int[] pre: prerequisites){
            int course = pre[0];
            int pre_course = pre[1];
            inDegree[course]++;
            if (pre_to_take_map.containsKey(pre_course)) {
                pre_to_take_map.get(pre_course).add(course);
            } else {
                List<Integer> list_to_take = new ArrayList<>();
                list_to_take.add(course);
                pre_to_take_map.put(pre_course, list_to_take);
            }
        }
        
        // Queue
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < inDegree.length; i++) {
            if (inDegree[i] == 0) {
                queue.add(i);
            }
        }

        while(!queue.isEmpty()) {
            List<Integer> list_to_take = pre_to_take_map.get(queue.poll());
            count++;
            if(list_to_take == null) {
                continue;
            }
            for (int to_take: list_to_take) {
                inDegree[to_take]--;
                if (inDegree[to_take] == 0) {
                    queue.add(to_take);
                }
            }
        }

        return count == numCourses;
    }
}