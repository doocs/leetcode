# [449. Serialize and Deserialize BST](https://leetcode.com/problems/serialize-and-deserialize-bst)

[中文文档](/solution/0400-0499/0449.Serialize%20and%20Deserialize%20BST/README.md)

## Description

<p>Serialization is converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the same or another computer environment.</p>

<p>Design an algorithm to serialize and deserialize a <b>binary search tree</b>. There is no restriction on how your serialization/deserialization algorithm should work. You need to ensure that a binary search tree can be serialized to a string, and this string can be deserialized to the original tree structure.</p>

<p><b>The encoded string should be as compact as possible.</b></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<pre><strong>Input:</strong> root = [2,1,3]
<strong>Output:</strong> [2,1,3]
</pre><p><strong class="example">Example 2:</strong></p>
<pre><strong>Input:</strong> root = []
<strong>Output:</strong> []
</pre>
<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li>The number of nodes in the tree is in the range <code>[0, 10<sup>4</sup>]</code>.</li>
	<li><code>0 &lt;= Node.val &lt;= 10<sup>4</sup></code></li>
	<li>The input tree is <strong>guaranteed</strong> to be a binary search tree.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None


class Codec:
    def serialize(self, root: TreeNode) -> str:
        """Encodes a tree to a single string."""

        def dfs(root):
            if root is None:
                return
            nonlocal t
            t.append(str(root.val))
            t.append(',')
            dfs(root.left)
            dfs(root.right)

        if root is None:
            return ''
        t = []
        dfs(root)
        return ''.join(t[:-1])

    def deserialize(self, data: str) -> TreeNode:
        """Decodes your encoded data to tree."""

        def build(s, l, r):
            if l > r:
                return None
            root = TreeNode(int(s[l]))
            idx = r + 1
            for i in range(l + 1, r + 1):
                if int(s[i]) > root.val:
                    idx = i
                    break
            root.left = build(s, l + 1, idx - 1)
            root.right = build(s, idx, r)
            return root

        if not data:
            return None
        s = data.split(',')
        return build(s, 0, len(s) - 1)


# Your Codec object will be instantiated and called as such:
# Your Codec object will be instantiated and called as such:
# ser = Codec()
# deser = Codec()
# tree = ser.serialize(root)
# ans = deser.deserialize(tree)
# return ans
```

### **Java**

```java
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
```

### **...**

```

```

<!-- tabs:end -->
