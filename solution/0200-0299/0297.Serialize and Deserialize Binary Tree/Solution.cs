/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     public int val;
 *     public TreeNode left;
 *     public TreeNode right;
 *     public TreeNode(int x) { val = x; }
 * }
 */
public class Codec {

    // Encodes a tree to a single string.
    public string serialize(TreeNode root) {
        if (root == null) {
            return null;
        }
        List<string> ans = new List<string>();
        Queue<TreeNode> q = new Queue<TreeNode>();
        q.Enqueue(root);
        while (q.Count > 0) {
            TreeNode node = q.Dequeue();
            if (node != null) {
                ans.Add(node.val.ToString());
                q.Enqueue(node.left);
                q.Enqueue(node.right);
            } else {
                ans.Add("#");
            }
        }
        return string.Join(",", ans);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(string data) {
        if (data == null) {
            return null;
        }
        string[] vals = data.Split(',');
        int i = 0;
        TreeNode root = new TreeNode(int.Parse(vals[i++]));
        Queue<TreeNode> q = new Queue<TreeNode>();
        q.Enqueue(root);
        while (q.Count > 0) {
            TreeNode node = q.Dequeue();
            if (vals[i] != "#") {
                node.left = new TreeNode(int.Parse(vals[i]));
                q.Enqueue(node.left);
            }
            i++;
            if (vals[i] != "#") {
                node.right = new TreeNode(int.Parse(vals[i]));
                q.Enqueue(node.right);
            }
            i++;
        }
        return root;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));
