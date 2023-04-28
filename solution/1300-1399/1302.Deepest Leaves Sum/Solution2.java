import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Solution2 {
    public int deepestLeavesSum(TreeNode root) {
        return sumLevel(Collections.singletonList(root));
    }

    private int sumLevel(List<TreeNode> aLevel) {
        if (null == aLevel || 0 == aLevel.size()) {
            return 0;
        }

        int thisLevelSum = 0;
        List<TreeNode> nextLevel = new ArrayList<>(aLevel.size() * 2);

        for (TreeNode node : aLevel) {
            if (null == node) {
                continue;
            }
            thisLevelSum += node.val;

            if (null != node.left) {
                nextLevel.add(node.left);
            }
            if (null != node.right) {
                nextLevel.add(node.right);
            }

        }
        if (0 == nextLevel.size()) {
            return thisLevelSum;
        }
        return sumLevel(nextLevel);
    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

}
