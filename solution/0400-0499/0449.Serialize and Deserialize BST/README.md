# [449. 序列化和反序列化二叉搜索树](https://leetcode.cn/problems/serialize-and-deserialize-bst)

[English Version](/solution/0400-0499/0449.Serialize%20and%20Deserialize%20BST/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>序列化是将数据结构或对象转换为一系列位的过程，以便它可以存储在文件或内存缓冲区中，或通过网络连接链路传输，以便稍后在同一个或另一个计算机环境中重建。</p>

<p>设计一个算法来序列化和反序列化<strong> 二叉搜索树</strong> 。 对序列化/反序列化算法的工作方式没有限制。 您只需确保二叉搜索树可以序列化为字符串，并且可以将该字符串反序列化为最初的二叉搜索树。</p>

<p><strong>编码的字符串应尽可能紧凑。</strong></p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>root = [2,1,3]
<strong>输出：</strong>[2,1,3]
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>root = []
<strong>输出：</strong>[]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li>树中节点数范围是 <code>[0, 10<sup>4</sup>]</code></li>
	<li><code>0 &lt;= Node.val &lt;= 10<sup>4</sup></code></li>
	<li>题目数据 <strong>保证</strong> 输入的树是一棵二叉搜索树。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
