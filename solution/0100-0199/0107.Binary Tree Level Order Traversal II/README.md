# [107. 二叉树的层序遍历 II](https://leetcode-cn.com/problems/binary-tree-level-order-traversal-ii)

[English Version](/solution/0100-0199/0107.Binary%20Tree%20Level%20Order%20Traversal%20II/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个二叉树，返回其节点值自底向上的层序遍历。 （即按从叶子节点所在层到根节点所在的层，逐层从左向右遍历）</p>

<p>例如：<br />
给定二叉树 <code>[3,9,20,null,null,15,7]</code>,</p>

<pre>
    3
   / \
  9  20
    /  \
   15   7
</pre>

<p>返回其自底向上的层序遍历为：</p>

<pre>
[
  [15,7],
  [9,20],
  [3]
]
</pre>


## 解法

<!-- 这里可写通用的实现逻辑 -->

同 [102](/solution/0100-0199/0102.Binary%20Tree%20Level%20Order%20Traversal/README.md)，最后反转一下结果即可。

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
    def levelOrderBottom(self, root: TreeNode) -> List[List[int]]:
        if root is None:
            return []
        q = [root]
        res = []
        while q:
            size = len(q)
            t = []
            for _ in range(size):
                node = q.pop(0)
                t.append(node.val)
                if node.left is not None:
                    q.append(node.left)
                if node.right is not None:
                    q.append(node.right)
            res.append(t)
        return res[::-1]
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
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        if (root == null) return Collections.emptyList();
        Deque<TreeNode> q = new ArrayDeque<>();
        List<List<Integer>> res = new ArrayList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            int size = q.size();
            List<Integer> t = new ArrayList<>();
            while (size-- > 0) {
                TreeNode node = q.poll();
                t.add(node.val);
                if (node.left != null) q.offer(node.left);
                if (node.right != null) q.offer(node.right);
            }
            res.add(0, t);
        }
        return res;
    }
}
```

### **JavaScript**

```js
/**
 * Definition for a binary tree node.
 * function TreeNode(val, left, right) {
 *     this.val = (val===undefined ? 0 : val)
 *     this.left = (left===undefined ? null : left)
 *     this.right = (right===undefined ? null : right)
 * }
 */
/**
 * @param {TreeNode} root
 * @return {number[][]}
 */
var levelOrderBottom = function (root) {
    if (!root) {
        return [];
    }
    let q = [], output = [], levelOutput = [];
    q.push(root);
    q.push(null);
    while (q.length) {
        let cur = q.shift();
        levelOutput.push(cur.val);
        if (cur.left) {
            q.push(cur.left);
        }
        if (cur.right) {
            q.push(cur.right);
        }
        if (!q[0]) {
            q.shift();
            output.unshift(levelOutput);
            levelOutput = [];
            if (q.length) {
                q.push(null);
            }
        }
    }
    return output;
};
```

### **...**

```

```

<!-- tabs:end -->
