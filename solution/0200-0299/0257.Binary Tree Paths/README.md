# [257. 二叉树的所有路径](https://leetcode-cn.com/problems/binary-tree-paths)

[English Version](/solution/0200-0299/0257.Binary%20Tree%20Paths/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个二叉树，返回所有从根节点到叶子节点的路径。</p>

<p><strong>说明:</strong>&nbsp;叶子节点是指没有子节点的节点。</p>

<p><strong>示例:</strong></p>

<pre><strong>输入:</strong>

   1
 /   \
2     3
 \
  5

<strong>输出:</strong> [&quot;1-&gt;2-&gt;5&quot;, &quot;1-&gt;3&quot;]

<strong>解释:</strong> 所有根节点到叶子节点的路径为: 1-&gt;2-&gt;5, 1-&gt;3</pre>

## 解法

<!-- 这里可写通用的实现逻辑 -->

深度优先搜索+路径记录。

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

class Solution:
    def binaryTreePaths(self, root: TreeNode) -> List[str]:
        def dfs(root):
            if root is None:
                return
            path.append(str(root.val))
            if root.left is None and root.right is None:
                res.append("->".join(path))
            dfs(root.left)
            dfs(root.right)
            path.pop()
        res = []
        path = []
        dfs(root)
        return res
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
class Solution {
    private List<String> res;
    private List<String> path;

    public List<String> binaryTreePaths(TreeNode root) {
        if (root == null) return Collections.emptyList();
        res = new ArrayList<>();
        path = new ArrayList<>();
        dfs(root);
        return res;
    }

    private void dfs(TreeNode root) {
        if (root == null) return;
        path.add(String.valueOf(root.val));
        if (root.left == null && root.right == null) {
            res.add(String.join("->", path));
        }
        dfs(root.left);
        dfs(root.right);
        path.remove(path.size() - 1);
    }
}
```

### **TypeScript**

```ts
/**
 * Definition for a binary tree node.
 * class TreeNode {
 *     val: number
 *     left: TreeNode | null
 *     right: TreeNode | null
 *     constructor(val?: number, left?: TreeNode | null, right?: TreeNode | null) {
 *         this.val = (val===undefined ? 0 : val)
 *         this.left = (left===undefined ? null : left)
 *         this.right = (right===undefined ? null : right)
 *     }
 * }
 */

function binaryTreePaths(root: TreeNode | null): string[] {
    let ans = [];
    let pre = "";
    dfs(root, pre, ans);
    return ans;
}

function dfs(root: TreeNode | null, pre: string, ans: string[]): void {
    if (root == null) return;
    let val = String(root.val);
    pre = pre.length > 0 ? `${pre}->${val}` : pre + val;
    // 叶子节点
    if (root.left == null && root.right == null) {
        ans.push(pre);
        return;
    }
    dfs(root.left, pre, ans);
    dfs(root.right, pre, ans);
}
```

### **...**

```

```

<!-- tabs:end -->
