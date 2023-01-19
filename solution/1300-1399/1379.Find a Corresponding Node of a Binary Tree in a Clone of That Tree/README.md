# [1379. 找出克隆二叉树中的相同节点](https://leetcode.cn/problems/find-a-corresponding-node-of-a-binary-tree-in-a-clone-of-that-tree)

[English Version](/solution/1300-1399/1379.Find%20a%20Corresponding%20Node%20of%20a%20Binary%20Tree%20in%20a%20Clone%20of%20That%20Tree/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你两棵二叉树，原始树 <code>original</code> 和克隆树 <code>cloned</code>，以及一个位于原始树 <code>original</code>&nbsp;中的目标节点&nbsp;<code>target</code>。</p>

<p>其中，克隆树 <code>cloned</code>&nbsp;是原始树 <code>original</code>&nbsp;的一个<strong> 副本 </strong>。</p>

<p>请找出在树&nbsp;<code>cloned</code>&nbsp;中，与&nbsp;<code>target</code>&nbsp;<strong>相同&nbsp;</strong>的节点，并返回对该节点的引用（在 C/C++ 等有指针的语言中返回 节点指针，其他语言返回节点本身）。</p>

<p>&nbsp;</p>

<p><strong>注意：</strong>你 <strong>不能</strong> 对两棵二叉树，以及 <code>target</code>&nbsp;节点进行更改。<strong>只能</strong> 返回对克隆树&nbsp;<code>cloned</code>&nbsp;中已有的节点的引用。</p>

<ul>
</ul>

<p>&nbsp;</p>

<ul>
</ul>

<p><strong>示例 1:</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1300-1399/1379.Find%20a%20Corresponding%20Node%20of%20a%20Binary%20Tree%20in%20a%20Clone%20of%20That%20Tree/images/e1.png" /></p>

<pre>
<strong>输入:</strong> tree = [7,4,3,null,null,6,19], target = 3
<strong>输出:</strong> 3
<strong>解释:</strong> 上图画出了树 original 和 cloned。target 节点在树 original 中，用绿色标记。答案是树 cloned 中的黄颜色的节点（其他示例类似）。</pre>

<p><strong>示例 2:</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1300-1399/1379.Find%20a%20Corresponding%20Node%20of%20a%20Binary%20Tree%20in%20a%20Clone%20of%20That%20Tree/images/e2.png" /></p>

<pre>
<strong>输入:</strong> tree = [7], target =  7
<strong>输出:</strong> 7
</pre>

<p><strong>示例 3:</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1300-1399/1379.Find%20a%20Corresponding%20Node%20of%20a%20Binary%20Tree%20in%20a%20Clone%20of%20That%20Tree/images/e3.png" /></p>

<pre>
<strong>输入:</strong> tree = [8,null,6,null,5,null,4,null,3,null,2,null,1], target = 4
<strong>输出:</strong> 4
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li>树中节点的数量范围为<meta charset="UTF-8" />&nbsp;<code>[1, 10<sup>4</sup>]</code>&nbsp;。</li>
	<li>同一棵树中，没有值相同的节点。</li>
	<li><code>target</code>&nbsp;节点是树&nbsp;<code>original</code>&nbsp;中的一个节点，并且不会是&nbsp;<code>null</code>&nbsp;。</li>
</ul>

<p>&nbsp;</p>

<p><strong>进阶：</strong>如果树中允许出现值相同的节点，将如何解答？</p>

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


class Solution:
    def getTargetCopy(
        self, original: TreeNode, cloned: TreeNode, target: TreeNode
    ) -> TreeNode:
        res = None

        def dfs(original, cloned):
            nonlocal res
            if cloned is None:
                return
            if original == target:
                res = cloned
                return
            dfs(original.left, cloned.left)
            dfs(original.right, cloned.right)

        dfs(original, cloned)
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
    private TreeNode res;

    public final TreeNode getTargetCopy(
        final TreeNode original, final TreeNode cloned, final TreeNode target) {
        dfs(original, cloned, target);
        return res;
    }

    private void dfs(TreeNode original, TreeNode cloned, TreeNode target) {
        if (cloned == null) {
            return;
        }
        if (original == target) {
            res = cloned;
            return;
        }
        dfs(original.left, cloned.left, target);
        dfs(original.right, cloned.right, target);
    }
}
```

### **C++**

```cpp
/**
 * Definition for a binary tree node.
 * struct TreeNode {
 *     int val;
 *     TreeNode *left;
 *     TreeNode *right;
 *     TreeNode(int x) : val(x), left(NULL), right(NULL) {}
 * };
 */

class Solution {
public:
    TreeNode* res;

    TreeNode* getTargetCopy(TreeNode* original, TreeNode* cloned, TreeNode* target) {
        dfs(original, cloned, target);
        return res;
    }

    void dfs(TreeNode* original, TreeNode* cloned, TreeNode* target) {
        if (!cloned) return;
        if (original == target) {
            res = cloned;
            return;
        }
        dfs(original->left, cloned->left, target);
        dfs(original->right, cloned->right, target);
    }
};
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

function getTargetCopy(
    original: TreeNode | null,
    cloned: TreeNode | null,
    target: TreeNode | null,
): TreeNode | null {
    if (cloned === null) {
        return null;
    }
    if (cloned.val === target.val) {
        return cloned;
    }
    return (
        getTargetCopy(original, cloned.left, target) ||
        getTargetCopy(original, cloned.right, target)
    );
}
```

### **...**

```

```

<!-- tabs:end -->
