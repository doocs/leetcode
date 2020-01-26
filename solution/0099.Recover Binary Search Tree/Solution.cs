public class Solution {
    private TreeNode last, first, second;

    public void RecoverTree(TreeNode root) {
        Traverse(root);
        var temp = first.val;
        first.val = second.val;
        second.val = temp;
    }

    private void Traverse(TreeNode root)
    {
        var current = root;
        TreeNode temp;
        while (current != null)
        {
            if (current.left == null)
            {
                Visit(current);
                current = current.right;
            }
            else
            {
                temp = current.left;
                while (temp.right != null && temp.right != current)
                {
                    temp = temp.right;
                }
                if (temp.right == null)
                {
                    temp.right = current;
                    current = current.left;
                }
                else
                {
                    Visit(current);
                    temp.right = null;
                    current = current.right;
                }
            }
        }
    }

    private void Visit(TreeNode node)
    {
        if (last != null)
        {
            if (node.val < last.val)
            {
                if (first == null)
                {
                    first = last;
                }
            }
            if (first != null && node.val < first.val)
            {
                second = node;
            }
        }
        last = node;
    }
}
