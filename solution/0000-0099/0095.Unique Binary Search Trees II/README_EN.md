# [95. Unique Binary Search Trees II](https://leetcode.com/problems/unique-binary-search-trees-ii)

[中文文档](/solution/0000-0099/0095.Unique%20Binary%20Search%20Trees%20II/README.md)

## Description

<p>Given an integer <code>n</code>, return <em>all the structurally unique <strong>BST&#39;</strong>s (binary search trees), which has exactly </em><code>n</code><em> nodes of unique values from</em> <code>1</code> <em>to</em> <code>n</code>. Return the answer in <strong>any order</strong>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/0000-0099/0095.Unique%20Binary%20Search%20Trees%20II/images/uniquebstn3.jpg" style="width: 600px; height: 148px;" />
<pre>
<strong>Input:</strong> n = 3
<strong>Output:</strong> [[1,null,2,null,3],[1,null,3,2],[2,1,3],[3,1,null,null,2],[3,2,null,1]]
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 1
<strong>Output:</strong> [[1]]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 8</code></li>
</ul>


## Solutions

<!-- tabs:start -->

### **Python3**

```python

```

### **Java**

```java

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

function generateTrees(n: number): Array<TreeNode | null> {
    if (n == 0) return [];
    return helper(1, n);
};

function helper (start: number, end: number): Array<TreeNode | null> {
    let ans = [];
    if (start > end) {
        ans.push(null);
        return ans;
    }
    for (let i = start; i <= end; i++) {
        let lefts = helper(start, i - 1);
        let rights = helper(i + 1, end);
        for (let left of lefts) {
            for (let right of rights) {
                let root = new TreeNode(i);
                root.left = left;
                root.right = right;
                ans.push(root);
            }
        }
    }
    return ans;
}
```

### **...**

```

```

<!-- tabs:end -->
