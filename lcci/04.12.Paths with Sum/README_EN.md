# [04.12. Paths with Sum](https://leetcode.cn/problems/paths-with-sum-lcci)

[中文文档](/lcci/04.12.Paths%20with%20Sum/README.md)

## Description

<p>You are given a binary tree in which each node contains an integer value (which might be positive or negative). Design an algorithm to count the number of paths that sum to a given value. The path does not need to start or end at the root or a leaf, but it must go downwards (traveling only from parent nodes to child nodes).</p>

<p><strong>Example:</strong><br />

Given the following tree and &nbsp;<code>sum = 22,</code></p>

<pre>

              5

             / \

            4   8

           /   / \

          11  13  4

         /  \    / \

        7    2  5   1

</pre>

<p>Output:</p>

<pre>

3

<strong>Explanation: </strong>Paths that have sum 22 are: [5,4,11,2], [5,8,4,5], [4,11,7]</pre>

<p>Note:</p>

<ul>
	<li><code>node number &lt;= 10000</code></li>
</ul>

## Solutions

Depth-First-Search

<!-- tabs:start -->

### **Python3**

Using the idea of recursion, at each recursion to a node.

-   If root.val-sum == 0, add 1 to the result
-   Consider two scenarios for inclusion or exclusion of this node from the pathway

Special case: if the parent node of this node is in the path, this node must be included in the path (the path cannot be broken)

```python
class Solution:
    def pathSum(self, root: TreeNode, sum: int) -> int:
        def dfs(root, sum, flag):
            nonlocal ans
            if not root:
                return 0
            if sum - root.val == 0:
                ans += 1
            if flag == 0:
                dfs(root.left, sum, 0)
                dfs(root.right, sum, 0)
            dfs(root.left, sum - root.val, 1)
            dfs(root.right, sum - root.val, 1)

        if not root:
            return 0
        ans = 0
        dfs(root, sum, 0)
        return ans
```

### **Java**

Use to 2 recursive processes.

-   BFS: (traverse) traverses each tree node.
-   DFS: Starting from each tree node, the nodes sum to see if sum can be satisfied.

Note that node values can be positive or negative, and all possible paths need to be exhausted.

```java
class Solution {
    int ans = 0;
    public int pathSum(TreeNode root, int sum) {
        traverse(root, sum);
        return ans;
    }

    void traverse(TreeNode root, int sum) {
        if (root == null) return;
        ans += dfs(root, sum, 0);
        traverse(root.left, sum);
        traverse(root.right, sum);
    }

    // check if sum of path is sum.
    int dfs(TreeNode root, int sum, int cur) {
        if (root == null) return 0;
        cur += root.val;
        int res = 0;
        if (cur == sum) res++;
        res += dfs(root.left, sum, cur);
        res += dfs(root.right, sum, cur);
        return res;
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

function dfs(root: TreeNode | null, sum: number): number {
    let res = 0;
    if (root == null) {
        return res;
    }
    sum -= root.val;
    if (sum === 0) {
        res++;
    }
    return res + dfs(root.left, sum) + dfs(root.right, sum);
}

function pathSum(root: TreeNode | null, sum: number): number {
    if (root == null) {
        return 0;
    }
    return dfs(root, sum) + pathSum(root.left, sum) + pathSum(root.right, sum);
}
```

### **Rust**

```rust
// Definition for a binary tree node.
// #[derive(Debug, PartialEq, Eq)]
// pub struct TreeNode {
//   pub val: i32,
//   pub left: Option<Rc<RefCell<TreeNode>>>,
//   pub right: Option<Rc<RefCell<TreeNode>>>,
// }
//
// impl TreeNode {
//   #[inline]
//   pub fn new(val: i32) -> Self {
//     TreeNode {
//       val,
//       left: None,
//       right: None
//     }
//   }
// }
use std::rc::Rc;
use std::cell::RefCell;
use std::collections::VecDeque;
impl Solution {
    fn dfs(root: &Option<Rc<RefCell<TreeNode>>>, mut sum: i32) -> i32 {
        let mut res = 0;
        if root.is_none() {
            return res;
        }
        let root = root.as_ref().unwrap().borrow();
        sum -= root.val;
        if sum == 0 {
            res += 1;
        }
        res + Self::dfs(&root.left, sum) + Self::dfs(&root.right, sum)
    }

    pub fn path_sum(root: Option<Rc<RefCell<TreeNode>>>, sum: i32) -> i32 {
        let mut queue = VecDeque::new();
        if root.is_some() {
            queue.push_back(root);
        }
        let mut res = 0;
        while let Some(mut root) = queue.pop_front() {
            res += Self::dfs(&root, sum);
            let mut root = root.as_mut().unwrap().borrow_mut();
            if root.left.is_some() {
                queue.push_back(root.left.take());
            }
            if root.right.is_some() {
                queue.push_back(root.right.take());
            }
        }
        res
    }
}
```

### **...**

```

```

<!-- tabs:end -->
