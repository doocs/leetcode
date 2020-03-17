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

class Solution {
    public boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        LinkedList<TreeNode> queueA = new LinkedList<>();
        LinkedList<TreeNode> queueB = new LinkedList<>();
        queueA.addLast(root);
        queueB.addLast(root);
        while (!queueA.isEmpty() && !queueB.isEmpty()) {
            TreeNode popA = queueA.pollFirst();
            TreeNode popB = queueB.pollFirst();
            if (popA == null && popB == null) continue;
            if (popA == null || popB == null) return false;

            if (popA.val != popB.val) return false;
            queueA.addLast(popA.left);
            queueA.addLast(popA.right);

            queueB.addLast(popB.right);
            queueB.addLast(popB.left);
        }
        return queueA.isEmpty() && queueB.isEmpty();
    }
}