package bfs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
//Approach 1 - BFS
public class RightSideViewOfBinaryTree {
    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     * int val;
     * TreeNode left;
     * TreeNode right;
     * TreeNode() {}
     * TreeNode(int val) { this.val = val; }
     * TreeNode(int val, TreeNode left, TreeNode right) {
     * this.val = val;
     * this.left = left;
     * this.right = right;
     * }
     * }
     */
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new ArrayList<>();

        if (root == null)
            return res;

        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);

        while (!q.isEmpty()) {
            int size = q.size();

            for (int i = 0; i < size; i++) {
                TreeNode curr = q.poll();

                if (curr.left != null) {
                    q.add(curr.left);
                }

                if (curr.right != null) {
                    q.add(curr.right);
                }

                if (i == size - 1) {
                    res.add(curr.val);
                }
            }
        }

        return res;

    }
}

//TC: O(n), SC: O(n/2) - max leaf nodes


//Approach - 2 DFS
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    List<Integer> res;

    public List<Integer> rightSideView(TreeNode root) {
        this.res = new ArrayList<>();
        if (root == null)
            return res;
        helper(root, 0);
        return res;
    }

    private void helper(TreeNode root, int level) {
        if (root == null)
            return;

        if (res.size() == level) {
            res.add(root.val);
        } else {
            res.set(level, root.val);
        }
        helper(root.left, level + 1);
        helper(root.right, level + 1);
    }
}

//TC: O(n), SC: O(h)