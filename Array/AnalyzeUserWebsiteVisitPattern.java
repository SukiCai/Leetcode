/**
1152. Analyze User Website Visit Pattern
You are given two string arrays username and website and an integer array timestamp. All the given arrays are of the same length and the tuple [username[i], website[i], timestamp[i]] indicates that the user username[i] visited the website website[i] at time timestamp[i].

A pattern is a list of three websites (not necessarily distinct).

For example, ["home", "away", "love"], ["leetcode", "love", "leetcode"], and ["luffy", "luffy", "luffy"] are all patterns.
The score of a pattern is the number of users that visited all the websites in the pattern in the same order they appeared in the pattern.

For example, if the pattern is ["home", "away", "love"], the score is the number of users x such that x visited "home" then visited "away" and visited "love" after that.
Similarly, if the pattern is ["leetcode", "love", "leetcode"], the score is the number of users x such that x visited "leetcode" then visited "love" and visited "leetcode" one more time after that.
Also, if the pattern is ["luffy", "luffy", "luffy"], the score is the number of users x such that x visited "luffy" three different times at different timestamps.
Return the pattern with the largest score. If there is more than one pattern with the same largest score, return the lexicographically smallest such pattern.

 

Example 1:
Input: username = ["joe","joe","joe","james","james","james","james","mary","mary","mary"], timestamp = [1,2,3,4,5,6,7,8,9,10], website = ["home","about","career","home","cart","maps","home","home","about","career"]
Output: ["home","about","career"]
Explanation: The tuples in this example are:
["joe","home",1],["joe","about",2],["joe","career",3],["james","home",4],["james","cart",5],["james","maps",6],["james","home",7],["mary","home",8],["mary","about",9], and ["mary","career",10].
The pattern ("home", "about", "career") has score 2 (joe and mary).
The pattern ("home", "cart", "maps") has score 1 (james).
The pattern ("home", "cart", "home") has score 1 (james).
The pattern ("home", "maps", "home") has score 1 (james).
The pattern ("cart", "maps", "home") has score 1 (james).
The pattern ("home", "home", "home") has score 0 (no user visited home 3 times).

Example 2:
Input: username = ["ua","ua","ua","ub","ub","ub"], timestamp = [1,2,3,4,5,6], website = ["a","b","a","a","b","c"]
Output: ["a","b","a"]

Algorithom:
1. 先用一个map来记录对应每一个user， 在哪一个时间点，visit了哪个website。 Map<String, List<Pair<int, String>> -> Map<Username, List<Pair<Timestamp, website>>
2. 用一个for loop把map造出来
3. 对于每一个map entry -> Map.Entry<String, List<Pair<int, String> entry: map.entrySet()， 也就是对应每一个user
4. sort当前user的timestamp，得到有序的访问website的list
5. 对于每一个有序的 website list，我们开始造pattern，通过一个3层的for loop造出大小为三个website的pattern，由于pattern和pattern之间要进行对比，所以我们在这里造一个String，用“/”来连接三个website作为一个pattern
6. 再用一个map来存下当前pattern visit过的user有哪些 Map<String, Set<String>> -> Map<Pattern, Set<User>>在这里由于user访问多次一样的pattern只算一个user，所以我们要找的是unique user， 用set
7. 比较当前user set的大小和max， 如果大于max则更新result. 如果等于max我们需要sort in alphabet order，选首字母比较小的数
    Note: 
        String s1 = "A"
        String s2 = "B"
        s1.compareTo(s2) = -1 （最后结果 s1 放在 s2 前面）lambda也是！
8.最后返回时需要split之后转换成list: Arrays.asList(result.split("/"))


*/

class Solution {
    public List<String> mostVisitedPattern(String[] username, int[] timestamp, String[] website) {
        int max = 0;
        String result_pattern = "";
        Map<String, List<Pair<Integer, String>>> map = new HashMap<>();
        for (int i = 0; i < timestamp.length; i++) {
            if (!map.containsKey(username[i])) {
                List<Pair<Integer, String>> visit = new ArrayList<>();
                visit.add(new Pair(timestamp[i], website[i]));
                map.put(username[i], visit);
            } else {
                map.get(username[i]).add(new Pair(timestamp[i], website[i]));
            }
        }

        Map<String, Set<String>> pattern_map = new HashMap<>();
        for (Map.Entry<String, List<Pair<Integer, String>>> entry: map.entrySet()) {
            String user = entry.getKey();
            List<Pair<Integer, String>> visit = entry.getValue();
            Collections.sort(visit, (a, b) -> (a.getKey() - b.getKey()));

            for (int i = 0; i < visit.size() - 2; i++) {
                for (int j = i + 1; j < visit.size() - 1; j++) {
                    for (int m = j + 1; m < visit.size(); m++) {
                        String pattern = visit.get(i).getValue() + "/"
                                    + visit.get(j).getValue() + "/"
                                    + visit.get(m).getValue();
                        
                        if (!pattern_map.containsKey(pattern)) {
                            Set<String> user_set = new HashSet<>();
                            user_set.add(user);
                            pattern_map.put(pattern, user_set);
                        } else {   
                            pattern_map.get(pattern).add(user);
                        }

                        if (pattern_map.get(pattern).size() > max) {
                            max = pattern_map.get(pattern).size();
                            result_pattern = pattern;
                        } else if (pattern_map.get(pattern).size() == max) {
                            result_pattern = pattern.compareTo(result_pattern) <= 0 ? pattern : result_pattern;
                        }
                    }
                }
            }
        }
        return Arrays.asList(result_pattern.split("/"));



    }
}