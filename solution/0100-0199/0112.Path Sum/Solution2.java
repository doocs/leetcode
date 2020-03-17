/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
import java.util.*;

class Pair {
    TreeNode node;
    Integer sum;
    public Pair(TreeNode node, Integer sum) {
        this.node = node;
        this.sum = sum;
    }
}

class Solution {
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) return false;
        LinkedList<Pair> queue = new LinkedList<>();
        queue.offer(new Pair(root, root.val));

        while (!queue.isEmpty()) {
            Pair pair = queue.poll();
            if (pair.node.left == null && pair.node.right == null && pair.sum == sum) {
                return true;
            }
            if (pair.node.left != null) {
                queue.offer(new Pair(pair.node.left, pair.node.left.val + pair.sum));
            }
            if (pair.node.right != null) {
                queue.offer(new Pair(pair.node.right, pair.node.right.val + pair.sum));
            }
        }
        return false;
    }
}