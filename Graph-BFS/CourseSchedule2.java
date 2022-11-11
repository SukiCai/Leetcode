/**
210. Course Schedule II

There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1. You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that you must take course bi first if you want to take course ai.

For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
Return the ordering of courses you should take to finish all courses. If there are many valid answers, return any of them. If it is impossible to finish all courses, return an empty array.

Example 1:

Input: numCourses = 2, prerequisites = [[1,0]]
Output: [0,1]
Explanation: There are a total of 2 courses to take. To take course 1 you should have finished course 0. So the correct course order is [0,1].
Example 2:

Input: numCourses = 4, prerequisites = [[1,0],[2,0],[3,1],[3,2]]
Output: [0,2,1,3]
Explanation: There are a total of 4 courses to take. To take course 3 you should have finished both courses 1 and 2. Both courses 1 and 2 should be taken after you finished course 0.
So one correct course order is [0,1,2,3]. Another correct ordering is [0,2,1,3].
Example 3:

Input: numCourses = 1, prerequisites = []
Output: [0]

Algorithom:
Same as CourseSchedule.java
But add an array to store the pop up number 
Notice! if there is circle inside the graph, return new int[0] instead!

Complexity:
- Time: O(N^2)
- Space: O(N) (Hashmap)

*/
class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] result = new int[numCourses];
        int count = 0;
        int[] inDegree = new int[numCourses];
        HashMap<Integer, List<Integer>> pre_to_take_map = new HashMap<>();

        for (int[] pre: prerequisites) {
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

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < inDegree.length; i++) {
            if (inDegree[i] == 0) {
                queue.add(i);
            } 
        }

        while(!queue.isEmpty()) {
            int cur = queue.poll();
            result[count] = cur;
            count++;
            List<Integer> list_to_take = pre_to_take_map.get(cur);
            if (list_to_take == null) {
                continue;
            }
            for (int i = 0; i < list_to_take.size(); i++ ){
                int to_take = list_to_take.get(i);
                inDegree[to_take]--;
                if (inDegree[to_take] == 0) {
                    queue.add(to_take);
                }
            }
        }
        if (count != numCourses) {
            return new int[0];
        }
        return result;
    }
}