# [545. 二叉树的边界 🔒](https://leetcode.cn/problems/boundary-of-binary-tree)

[English Version](/solution/0500-0599/0545.Boundary%20of%20Binary%20Tree/README_EN.md)

<!-- tags:树,深度优先搜索,二叉树 -->

## 题目描述

<!-- 这里写题目描述 -->

<p>二叉树的 <strong>边界</strong> 是由 <strong>根节点 </strong>、<strong>左边界</strong> 、按从左到右顺序的<strong> 叶节点</strong> 和 <strong>逆序的右边界</strong> ，按顺序依次连接组成。</p>

<p><strong>左边界 </strong>是满足下述定义的节点集合：</p>

<ul>
	<li>根节点的左子节点在左边界中。如果根节点不含左子节点，那么左边界就为 <strong>空</strong> 。</li>
	<li>如果一个节点在左边界中，并且该节点有左子节点，那么它的左子节点也在左边界中。</li>
	<li>如果一个节点在左边界中，并且该节点 <strong>不含</strong> 左子节点，那么它的右子节点就在左边界中。</li>
	<li>最左侧的叶节点 <strong>不在</strong> 左边界中。</li>
</ul>

<p><strong>右边界</strong> 定义方式与 <strong>左边界</strong> 相同，只是将左替换成右。即，右边界是根节点右子树的右侧部分；叶节点 <strong>不是</strong> 右边界的组成部分；如果根节点不含右子节点，那么右边界为 <strong>空</strong> 。</p>

<p><strong>叶节点</strong> 是没有任何子节点的节点。对于此问题，根节点 <strong>不是</strong> 叶节点。</p>

<p>给你一棵二叉树的根节点 <code>root</code> ，按顺序返回组成二叉树 <strong>边界</strong> 的这些值。</p>

<p> </p>

<p><strong>示例 1：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0500-0599/0545.Boundary%20of%20Binary%20Tree/images/boundary1.jpg" style="width: 299px; height: 290px;" />
<pre>
<strong>输入：</strong>root = [1,null,2,3,4]
<strong>输出：</strong>[1,3,4,2]
<b>解释：</b>
- 左边界为空，因为二叉树不含左子节点。
- 右边界是 [2] 。从根节点的右子节点开始的路径为 2 -> 4 ，但 4 是叶节点，所以右边界只有 2 。
- 叶节点从左到右是 [3,4] 。
按题目要求依序连接得到结果 [1] + [] + [3,4] + [2] = [1,3,4,2] 。</pre>

<p><strong>示例 2：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0500-0599/0545.Boundary%20of%20Binary%20Tree/images/boundary2.jpg" style="width: 599px; height: 411px;" />
<pre>
<strong>输入：</strong>root = [1,2,3,4,5,6,null,null,null,7,8,9,10]
<strong>输出：</strong>[1,2,4,7,8,9,10,6,3]
<b>解释：</b>
- 左边界为 [2] 。从根节点的左子节点开始的路径为 2 -> 4 ，但 4 是叶节点，所以左边界只有 2 。
- 右边界是 [3,6] ，逆序为 [6,3] 。从根节点的右子节点开始的路径为 3 -> 6 -> 10 ，但 10 是叶节点。
- 叶节点从左到右是 [4,7,8,9,10]
按题目要求依序连接得到结果 [1] + [2] + [4,7,8,9,10] + [6,3] = [1,2,4,7,8,9,10,6,3] 。</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li>树中节点的数目在范围 <code>[1, 10<sup>4</sup>]</code> 内</li>
	<li><code>-1000 <= Node.val <= 1000</code></li>
</ul>

## 解法

### 方法一

<!-- tabs:start -->

```python
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def boundaryOfBinaryTree(self, root: TreeNode) -> List[int]:
        self.res = []
        if not root:
            return self.res
        # root
        if not self.is_leaf(root):
            self.res.append(root.val)

        # left boundary
        t = root.left
        while t:
            if not self.is_leaf(t):
                self.res.append(t.val)
            t = t.left if t.left else t.right

        # leaves
        self.add_leaves(root)

        # right boundary(reverse order)
        s = []
        t = root.right
        while t:
            if not self.is_leaf(t):
                s.append(t.val)
            t = t.right if t.right else t.left
        while s:
            self.res.append(s.pop())

        # output
        return self.res

    def add_leaves(self, root):
        if self.is_leaf(root):
            self.res.append(root.val)
            return
        if root.left:
            self.add_leaves(root.left)
        if root.right:
            self.add_leaves(root.right)

    def is_leaf(self, node) -> bool:
        return node and node.left is None and node.right is None
```

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
    private List<Integer> res;

    public List<Integer> boundaryOfBinaryTree(TreeNode root) {
        if (root == null) {
            return Collections.emptyList();
        }
        res = new ArrayList<>();

        // root
        if (!isLeaf(root)) {
            res.add(root.val);
        }

        // left boundary
        TreeNode t = root.left;
        while (t != null) {
            if (!isLeaf(t)) {
                res.add(t.val);
            }
            t = t.left == null ? t.right : t.left;
        }

        // leaves
        addLeaves(root);

        // right boundary(reverse order)
        Deque<Integer> s = new ArrayDeque<>();
        t = root.right;
        while (t != null) {
            if (!isLeaf(t)) {
                s.offer(t.val);
            }
            t = t.right == null ? t.left : t.right;
        }
        while (!s.isEmpty()) {
            res.add(s.pollLast());
        }

        // output
        return res;
    }

    private void addLeaves(TreeNode root) {
        if (isLeaf(root)) {
            res.add(root.val);
            return;
        }
        if (root.left != null) {
            addLeaves(root.left);
        }
        if (root.right != null) {
            addLeaves(root.right);
        }
    }

    private boolean isLeaf(TreeNode node) {
        return node != null && node.left == null && node.right == null;
    }
}
```

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
 * @return {number[]}
 */
var boundaryOfBinaryTree = function (root) {
    let leftBoundary = function (root, res) {
        while (root) {
            let curVal = root.val;
            if (root.left) {
                root = root.left;
            } else if (root.right) {
                root = root.right;
            } else {
                break;
            }
            res.push(curVal);
        }
    };
    let rightBoundary = function (root, res) {
        let stk = [];
        while (root) {
            let curVal = root.val;
            if (root.right) {
                root = root.right;
            } else if (root.left) {
                root = root.left;
            } else {
                break;
            }
            stk.push(curVal);
        }
        let len = stk.length;
        for (let i = 0; i < len; i++) {
            res.push(stk.pop());
        }
    };
    let levelBoundary = function (root, res) {
        if (root) {
            levelBoundary(root.left, res);
            if (!root.left && !root.right) {
                res.push(root.val);
            }
            levelBoundary(root.right, res);
        }
    };
    let res = [];
    if (root) {
        res.push(root.val);
        leftBoundary(root.left, res);
        if (root.left || root.right) {
            levelBoundary(root, res);
        }
        rightBoundary(root.right, res);
    }
    return res;
};
```

<!-- tabs:end -->

<!-- end -->
