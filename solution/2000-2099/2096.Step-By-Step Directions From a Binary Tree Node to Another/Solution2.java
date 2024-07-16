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
    static byte[] path = new byte[200_001];
    int strtLevel = -1;
    int destLevel = -1;
    int comnLevel = -1;

    public String getDirections(TreeNode root, int startValue, int destValue) {
        findPaths(root, startValue, destValue, 100_000);
        int answerIdx = comnLevel;
        for (int i = strtLevel; i > comnLevel; i--) {
            path[--answerIdx] = 'U';
        }
        return new String(path, answerIdx, destLevel - answerIdx);
    }

    private int findPaths(TreeNode node, int strtVal, int destVal, int level) {
        if (node == null) {
            return 0;
        }
        int result = 0;
        if (node.val == strtVal) {
            strtLevel = level;
            result = 1;
        } else if (node.val == destVal) {
            destLevel = level;
            result = 1;
        }
        int leftFound = 0;
        int rightFound = 0;
        if (comnLevel < 0) {
            if (destLevel < 0) {
                path[level] = 'L';
            }
            leftFound = findPaths(node.left, strtVal, destVal, level + 1);
            rightFound = 0;
            if (comnLevel < 0) {
                if (destLevel < 0) {
                    path[level] = 'R';
                }
                rightFound = findPaths(node.right, strtVal, destVal, level + 1);
            }
        }
        if (comnLevel < 0 && leftFound + rightFound + result == 2) {
            comnLevel = level;
        }
        return result | leftFound | rightFound;
    }
}
