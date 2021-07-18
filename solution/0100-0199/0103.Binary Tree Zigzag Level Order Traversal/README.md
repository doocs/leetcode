# [103. 二叉树的锯齿形层序遍历](https://leetcode-cn.com/problems/binary-tree-zigzag-level-order-traversal)

[English Version](/solution/0100-0199/0103.Binary%20Tree%20Zigzag%20Level%20Order%20Traversal/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个二叉树，返回其节点值的锯齿形层序遍历。（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。</p>

<p>例如：<br />
给定二叉树 <code>[3,9,20,null,null,15,7]</code>,</p>

<pre>
    3
   / \
  9  20
    /  \
   15   7
</pre>

<p>返回锯齿形层序遍历如下：</p>

<pre>
[
  [3],
  [20,9],
  [15,7]
]
</pre>


## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def zigzagLevelOrder(self, root: TreeNode) -> List[List[int]]:
        if root is None:
            return []
        res = []
        q = collections.deque([root])
        left = False
        while q:
            size = len(q)
            t = []
            for _ in range(size):
                node = q.popleft()
                t.append(node.val)
                if node.left:
                    q.append(node.left)
                if node.right:
                    q.append(node.right)
            if left:
                t.reverse()
            res.append(t)
            left = not left
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
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        if (root == null) {
            return Collections.emptyList();
        }
        Deque<TreeNode> q = new ArrayDeque<>();
        q.offer(root);
        List<List<Integer>> res = new ArrayList<>();
        boolean left = false;
        while (!q.isEmpty()) {
            int size = q.size();
            List<Integer> t = new ArrayList<>();
            while (size-- > 0) {
                TreeNode node = q.pollFirst();
                t.add(node.val);
                if (node.left != null) {
                    q.offer(node.left);
                }
                if (node.right != null) {
                    q.offer(node.right);
                }
            }
            if (left) {
                Collections.reverse(t);
            }
            res.add(t);
            left = !left;
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
var zigzagLevelOrder = function (root) {
    if (!root) {
        return [];
    }
    let res = [], q = [];
    q.push(root);
    let leftToRight = true;
    while (q.length) {
        let levelSize = q.length, levelOutput = [];
        for (let i = 0; i < levelSize; i++) {
            let cur = q.shift();
            if (cur.left) {
                q.push(cur.left);
            }
            if (cur.right) {
                q.push(cur.right);
            }
            if (leftToRight) {
                levelOutput.push(cur.val);
            } else {
                levelOutput.unshift(cur.val);
            }
        }
        res.push(levelOutput);
        leftToRight = !leftToRight;
    }
    return res;
};
```

### **...**

```

```

<!-- tabs:end -->
