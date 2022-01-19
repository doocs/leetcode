/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        dfs(root, sb);
        return sb.substring(0, sb.length() - 1);
    }

    private void dfs(TreeNode root, StringBuilder sb) {
        if (root == null) {
            return;
        }
        sb.append(root.val).append(",");
        dfs(root.left, sb);
        dfs(root.right, sb);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data == null || "".equals(data)) {
            return null;
        }
        String[] s = data.split(",");
        return build(s, 0, s.length - 1);
    }

    private TreeNode build(String[] s, int l, int r) {
        if (l > r) {
            return null;
        }
        int idx = r + 1;
        TreeNode root = new TreeNode(Integer.valueOf(s[l]));
        for (int i = l + 1; i <= r; ++i) {
            if (Integer.valueOf(s[i]) > root.val) {
                idx = i;
                break;
            }
        }
        root.left = build(s, l + 1, idx - 1);
        root.right = build(s, idx, r);
        return root;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec ser = new Codec();
// Codec deser = new Codec();
// String tree = ser.serialize(root);
// TreeNode ans = deser.deserialize(tree);
// return ans;