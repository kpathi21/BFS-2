package bfs;

import java.util.LinkedList;
import java.util.Queue;

public class CousinsInBinaryTree {
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
    public boolean isCousins(TreeNode root, int x, int y) {
        if (root == null)
            return true;
        Queue<TreeNode> q = new LinkedList<>();
        Queue<TreeNode> pq = new LinkedList<>(); //parent Q
        q.add(root);
        pq.add(null);

        boolean x_found = false, y_found = false;
        TreeNode x_parent = null, y_parent = null;

        while (!q.isEmpty()) {
            int size = q.size();

            for (int i = 0; i < size; i++) {
                TreeNode curr = q.poll();
                TreeNode parent = pq.poll();

                if (curr.val == x) {
                    x_found = true;
                    x_parent = parent;
                }

                if (curr.val == y) {
                    y_found = true;
                    y_parent = parent;
                }

                if (curr.left != null) {
                    q.add(curr.left);
                    pq.add(curr);
                }

                if (curr.right != null) {
                    q.add(curr.right);
                    pq.add(curr);
                }
            }

            if (x_found && y_found) {
                return x_parent != y_parent;
            }

            if (x_found || y_found)
                return false;
        }

        return true;
    }
}

//TC: O(n), SC: O(n)

//Approach -2

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
class Solution1 {
    int x_level, y_level;
    TreeNode x_parent, y_parent;

    public boolean isCousins(TreeNode root, int x, int y) {
        dfs(root, x, y, 0, null);
        return x_level == y_level && x_parent != y_parent;
    }

    private void dfs(TreeNode root, int x, int y, int level, TreeNode parent) {
        if (root == null || x_parent != null && y_parent != null)
            return;

        if (root.val == x) {
            x_level = level;
            x_parent = parent;
        }

        if (root.val == y) {
            y_level = level;
            y_parent = parent;
        }

        dfs(root.left, x, y, level + 1, root);
        dfs(root.right, x, y, level + 1, root);
    }
}

//TC: O(N), SC: O(h)