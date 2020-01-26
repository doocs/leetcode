using System;

public class Solution {
    private int _answer = int.MinValue;
    public int MaxPathSum(TreeNode root) {
        GetMaxSumOfOneSide(root);
        return _answer;
    }

    private int GetMaxSumOfOneSide(TreeNode root)
    {
        if (root == null) return 0;
        var leftSum = GetMaxSumOfOneSide(root.left);
        if (leftSum < 0) leftSum = 0;
        var rightSum = GetMaxSumOfOneSide(root.right);
        if (rightSum < 0) rightSum = 0;
        var all = leftSum + rightSum + root.val;
        _answer = Math.Max(_answer, all);
        return Math.Max(root.val, root.val + Math.Max(leftSum, rightSum));
    }
}