# [872. 叶子相似的树](https://leetcode-cn.com/problems/leaf-similar-trees)

[English Version](/solution/0800-0899/0872.Leaf-Similar%20Trees/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>请考虑一棵二叉树上所有的叶子，这些叶子的值按从左到右的顺序排列形成一个&nbsp;<em>叶值序列</em> 。</p>

<p><img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/0800-0899/0872.Leaf-Similar%20Trees/images/tree.png" style="height: 240px; width: 300px;"></p>

<p>举个例子，如上图所示，给定一棵叶值序列为&nbsp;<code>(6, 7, 4, 9, 8)</code>&nbsp;的树。</p>

<p>如果有两棵二叉树的叶值序列是相同，那么我们就认为它们是&nbsp;<em>叶相似&nbsp;</em>的。</p>

<p>如果给定的两个头结点分别为&nbsp;<code>root1</code> 和&nbsp;<code>root2</code>&nbsp;的树是叶相似的，则返回&nbsp;<code>true</code>；否则返回 <code>false</code> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/0800-0899/0872.Leaf-Similar%20Trees/images/leaf-similar-1.jpg" style="height: 297px; width: 750px;"></p>

<pre><strong>输入：</strong>root1 = [3,5,1,6,2,9,8,null,null,7,4], root2 = [3,5,1,6,7,4,2,null,null,null,null,null,null,9,8]
<strong>输出：</strong>true
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>root1 = [1], root2 = [1]
<strong>输出：</strong>true
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>root1 = [1], root2 = [2]
<strong>输出：</strong>false
</pre>

<p><strong>示例 4：</strong></p>

<pre><strong>输入：</strong>root1 = [1,2], root2 = [2,2]
<strong>输出：</strong>true
</pre>

<p><strong>示例 5：</strong></p>

<p><img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/0800-0899/0872.Leaf-Similar%20Trees/images/leaf-similar-2.jpg" style="height: 165px; width: 450px;"></p>

<pre><strong>输入：</strong>root1 = [1,2,3], root2 = [1,3,2]
<strong>输出：</strong>false
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li>给定的两棵树可能会有&nbsp;<code>1</code>&nbsp;到 <code>200</code>&nbsp;个结点。</li>
	<li>给定的两棵树上的值介于 <code>0</code> 到 <code>200</code> 之间。</li>
</ul>


## 解法

<!-- 这里可写通用的实现逻辑 -->

深度优先搜索。

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
    def leafSimilar(self, root1: TreeNode, root2: TreeNode) -> bool:
        def dfs(root, leaves):
            if root is None:
                return
            if root.left is None and root.right is None:
                leaves.append(root.val)
                return
            dfs(root.left, leaves)
            dfs(root.right, leaves)
        l1, l2 = [], []
        dfs(root1, l1)
        dfs(root2, l2)
        return l1 == l2
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
    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        List<Integer> l1 = new ArrayList<>();
        List<Integer> l2 = new ArrayList<>();
        dfs(root1, l1);
        dfs(root2, l2);
        return l1.equals(l2);
    }

    private void dfs(TreeNode root, List<Integer> leaves) {
        if (root == null) return;
        if (root.left == null && root.right == null) {
            leaves.add(root.val);
            return;
        }
        dfs(root.left, leaves);
        dfs(root.right, leaves);
    }
}
```

### **Go**

```go
func leafSimilar(root1 *TreeNode, root2 *TreeNode) bool {
	var l1, l2 []int
	if root1 != nil {
		dfs(root1, &l1)
	}
	if root2 != nil {
		dfs(root2, &l2)
	}
	return reflect.DeepEqual(l1, l2)
}

func dfs(root *TreeNode, leaves *[]int) {
	if root.Left == nil && root.Right == nil {
		*leaves = append(*leaves, root.Val)
	} else {
		if root.Left != nil {
			dfs(root.Left, leaves)
		}
		if root.Right != nil {
			dfs(root.Right, leaves)
		}
	}
}
```

### **...**

```

```

<!-- tabs:end -->
