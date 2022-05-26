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
            robot(root, sb);
            return sb.substring(0, sb.length() - 1);
        }

        private void robot(TreeNode root, StringBuilder sb) {
            if (root == null) {
                return;
            }
            sb.append(root.val).append(",");
            robot(root.left, sb);
            robot(root.right, sb);
        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            if (data == null || Objects.equals(data, "")) {
                return null;
            }
            String[] pre = data.split(",");
            return build(pre, 0, pre.length - 1);
        }

        private TreeNode build(String[] pre, int start, int end) {
            if (start > end) {
                return null;
            }
            TreeNode root = new TreeNode(Integer.valueOf(pre[start]));

            int index = end + 1;
            for (int i = start + 1; i <= end; i++) {
                if (Integer.valueOf(pre[i]) > root.val) {
                    index = i;
                    break;
                }
            }

            root.left = build(pre, start + 1, index - 1);
            root.right = build(pre, index, end);
            return root;
        }

    }

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));