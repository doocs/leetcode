// https://leetcode.com/problems/count-complete-tree-nodes/

using System;

public class Solution
{
    public int CountNodes(TreeNode root)
    {
        if (root == null) return 0;
        var lastNodeOffset = CountNodes_GetLastNodeOffset(root, 0);
        var totalDepth = CountNodes_GetDeepth(root);
        return (int)Math.Pow(2, totalDepth - 1) - 1 + lastNodeOffset;
    }

    private int CountNodes_GetLastNodeOffset(TreeNode node, int offset)
    {
        if (node == null) return offset;
        if (node.left == null) return offset + 1;
        var leftDepth = CountNodes_GetDeepth(node.left);
        var rightDepth = CountNodes_GetDeepth(node.right);
        if (leftDepth > rightDepth)
        {
            return CountNodes_GetLastNodeOffset(node.left, offset);
        }
        return CountNodes_GetLastNodeOffset(node.right, offset + (int)Math.Pow(2, leftDepth - 1));
    }

    private int CountNodes_GetDeepth(TreeNode node)
    {
        var depth = 0;
        while (node != null)
        {
            ++depth;
            node = node.left;
        }
        return depth;
    }
}
