/**
545. Boundary of Binary Tree
逆时针的走一圈tree，最左边的那一列 + 所有leave + 最右边的那一列

The boundary of a binary tree is the concatenation of the root, the left boundary, the leaves ordered from left-to-right, and the reverse order of the right boundary.

The left boundary is the set of nodes defined by the following:

The root node's left child is in the left boundary. If the root does not have a left child, then the left boundary is empty.
If a node in the left boundary and has a left child, then the left child is in the left boundary.
If a node is in the left boundary, has no left child, but has a right child, then the right child is in the left boundary.
The leftmost leaf is not in the left boundary.
The right boundary is similar to the left boundary, except it is the right side of the root's right subtree. Again, the leaf is not part of the right boundary, and the right boundary is empty if the root does not have a right child.

The leaves are nodes that do not have any children. For this problem, the root is not a leaf.

Given the root of a binary tree, return the values of its boundary.

Example 1:
Input: root = [1,null,2,3,4]
Output: [1,3,4,2]
Explanation:
- The left boundary is empty because the root does not have a left child.
- The right boundary follows the path starting from the root's right child 2 -> 4.
  4 is a leaf, so the right boundary is [2].
- The leaves from left to right are [3,4].
Concatenating everything results in [1] + [] + [3,4] + [2] = [1,3,4,2].

Example 2:
Input: root = [1,2,3,4,5,6,null,null,null,7,8,9,10]
Output: [1,2,4,7,8,9,10,6,3]
Explanation:
- The left boundary follows the path starting from the root's left child 2 -> 4.
  4 is a leaf, so the left boundary is [2].
- The right boundary follows the path starting from the root's right child 3 -> 6 -> 10.
  10 is a leaf, so the right boundary is [3,6], and in reverse order is [6,3].
- The leaves from left to right are [4,7,8,9,10].
Concatenating everything results in [1] + [2] + [4,7,8,9,10] + [6,3] = [1,2,4,7,8,9,10,6,3].

Algorithom:
1. Triverse the left child by recursion (basecase: node == null, 如果左边没有了如果右边有就先走右边再走左边)
2. Triverse the leave child by recursion (先走root左边的所有leave，再走root右边的所有leave)
    不能先走node.right： 因为必须先add左边的再add右边的
    不放node.right在node.left后面，因为node.left得等right全部走完才能存，会导致runningtime变大
 */
class Solution {
    List<Integer> result = new ArrayList<>();
    
    public List<Integer> boundaryOfBinaryTree(TreeNode root) {
        if (root == null) {
            return result;
        }

        result.add(root.val);

        this.go_left(root.left);
        this.get_leave(root.left);
        this.get_leave(root.right);
        this.go_right(root.right);
        
        return result;
    }

    private void go_left(TreeNode node) {
        if (node == null) {
            return;
        }
        if (node.left != null) {
            result.add(node.val);
            go_left(node.left);
        } else if (node.right != null) {
            result.add(node.val);
            go_left(node.right);
        }
    }

    private void go_right(TreeNode node) {
        if (node == null) {
            return;
        }
        if (node.right != null) {
            go_right(node.right);
            result.add(node.val);
        } else if (node.left != null) {
            go_right(node.left);
            result.add(node.val);
        }

    }

    private void get_leave(TreeNode node) {
        if (node == null) {
            return;
        }
        get_leave(node.left);
        if (node.left == null && node.right == null) {
            result.add(node.val);
        }
        get_leave(node.right);
    }
}
