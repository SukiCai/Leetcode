/**
863. All Nodes Distance K in Binary Tree
给定node，和长度，找离这个node有给定长度距离的所有node

Example 1:
Input: root = [3,5,1,6,2,0,8,null,null,7,4], target = 5, k = 2
Output: [7,4,1]
Explanation: The nodes that are a distance 2 from the target node (with value 5) have values 7, 4, and 1.

Example 2:
Input: root = [1], target = 1, k = 3
Output: []

Algorithm： BFS
1. Transfer graph to map （从而可以进行双向遍历）
2. BFS
    queue：放所有该层的node （ex: k = 1）; pop 第一个qulified的进行process
    hashset 用来看该node是否visit过

Complexcity:
Running: O(N)
Space: O(N)

*/

class Solution {
    Map<TreeNode, List<TreeNode>> map = new HashMap<>();
    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        List<Integer> result = new ArrayList<>();
        
        if (k == 0) {
            result.add(target.val);
            return result;
        }

        // Transfer tree to map
        buildmap(map, root);

        // BFS
        Queue<TreeNode> queue = new LinkedList<>();
        HashSet<TreeNode> visited = new HashSet<>();

        queue.add(target);

        while(!queue.isEmpty()) {
            k--;

            // k ！= 0 时， 将每一个相邻节点都加到queue里（如果visited）
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode current = queue.poll();
                visited.add(current);
                List<TreeNode> adj_list = map.get(current);
                if (adj_list == null) {
                    continue;
                }
                for (TreeNode adj: adj_list) {
                    if (!visited.contains(adj)) {
                        queue.add(adj);
                    }
                }         
                
            }
            // 当 k = 0时结束
            if (k == 0) {
                for (TreeNode node: queue) {
                    result.add(node.val);
                }
            }
        }
        return result;
    }

    public void buildmap(Map<TreeNode, List<TreeNode>> map, TreeNode node) {
        if (node == null) {
            return;
        }

        if (node.left != null) {
            map.putIfAbsent(node, new ArrayList<>());
            map.putIfAbsent(node.left, new ArrayList<>());

            map.get(node).add(node.left);
            map.get(node.left).add(node);
        }

        if (node.right != null) {
            map.putIfAbsent(node, new ArrayList<>());
            map.putIfAbsent(node.right, new ArrayList<>());

            map.get(node).add(node.right);
            map.get(node.right).add(node);
        }

        buildmap(map, node.right);
        buildmap(map, node.left);
    }
}

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */