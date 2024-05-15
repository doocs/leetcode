---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/lcci/04.12.Paths%20with%20Sum/README_EN.md
---

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

### Solution 1: Hash Table + Prefix Sum + Recursion

We can use the idea of prefix sum to recursively traverse the binary tree, and use a hash table $cnt$ to count the occurrence of each prefix sum on the path from the root node to the current node.

We design a recursive function $dfs(node, s)$, where the current node being traversed is $node$, and the prefix sum on the path from the root node to the current node is $s$. The return value of the function is the number of paths with the path sum equal to $sum$ and the path ends at the $node$ node or its subtree nodes. Therefore, the answer is $dfs(root, 0)$.

The recursive process of the function $dfs(node, s)$ is as follows:

-   If the current node $node$ is null, return $0$.
-   Calculate the prefix sum $s$ on the path from the root node to the current node.
-   Use $cnt[s - sum]$ to represent the number of paths with the path sum equal to $sum$ and the path ends at the current node, where $cnt[s - sum]$ is the count of the prefix sum equal to $s - sum$ in $cnt$.
-   Add the count of the prefix sum $s$ by $1$, i.e., $cnt[s] = cnt[s] + 1$.
-   Recursively traverse the left and right child nodes of the current node, i.e., call the functions $dfs(node.left, s)$ and $dfs(node.right, s)$, and add their return values.
-   After the return value is calculated, subtract the count of the prefix sum $s$ of the current node by $1$, i.e., execute $cnt[s] = cnt[s] - 1$.
-   Finally, return the answer.

The time complexity is $O(n)$, and the space complexity is $O(n)$. Here, $n$ is the number of nodes in the binary tree.

<!-- tabs:start -->

```python
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None


class Solution:
    def pathSum(self, root: TreeNode, sum: int) -> int:
        def dfs(root: TreeNode, s: int):
            if root is None:
                return 0
            s += root.val
            ans = cnt[s - sum]
            cnt[s] += 1
            ans += dfs(root.left, s)
            ans += dfs(root.right, s)
            cnt[s] -= 1
            return ans

        cnt = Counter({0: 1})
        return dfs(root, 0)
```

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
    private Map<Long, Integer> cnt = new HashMap<>();
    private int target;

    public int pathSum(TreeNode root, int sum) {
        cnt.put(0L, 1);
        target = sum;
        return dfs(root, 0);
    }

    private int dfs(TreeNode root, long s) {
        if (root == null) {
            return 0;
        }
        s += root.val;
        int ans = cnt.getOrDefault(s - target, 0);
        cnt.merge(s, 1, Integer::sum);
        ans += dfs(root.left, s);
        ans += dfs(root.right, s);
        cnt.merge(s, -1, Integer::sum);
        return ans;
    }
}
```

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
    int pathSum(TreeNode* root, int sum) {
        unordered_map<long long, int> cnt;
        cnt[0] = 1;
        function<int(TreeNode*, long long)> dfs = [&](TreeNode* root, long long s) {
            if (!root) {
                return 0;
            }
            s += root->val;
            int ans = cnt[s - sum];
            ++cnt[s];
            ans += dfs(root->left, s);
            ans += dfs(root->right, s);
            --cnt[s];
            return ans;
        };
        return dfs(root, 0);
    }
};
```

```go
/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func pathSum(root *TreeNode, sum int) int {
	cnt := map[int]int{0: 1}
	var dfs func(*TreeNode, int) int
	dfs = func(root *TreeNode, s int) int {
		if root == nil {
			return 0
		}
		s += root.Val
		ans := cnt[s-sum]
		cnt[s]++
		ans += dfs(root.Left, s)
		ans += dfs(root.Right, s)
		cnt[s]--
		return ans
	}
	return dfs(root, 0)
}
```

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

function pathSum(root: TreeNode | null, sum: number): number {
    const cnt: Map<number, number> = new Map();
    cnt.set(0, 1);
    const dfs = (root: TreeNode | null, s: number): number => {
        if (!root) {
            return 0;
        }
        s += root.val;
        let ans = cnt.get(s - sum) ?? 0;
        cnt.set(s, (cnt.get(s) ?? 0) + 1);
        ans += dfs(root.left, s);
        ans += dfs(root.right, s);
        cnt.set(s, (cnt.get(s) ?? 0) - 1);
        return ans;
    };
    return dfs(root, 0);
}
```

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
use std::collections::HashMap;
impl Solution {
    pub fn path_sum(root: Option<Rc<RefCell<TreeNode>>>, sum: i32) -> i32 {
        let mut cnt = HashMap::new();
        cnt.insert(0, 1);
        return Self::dfs(root, sum, 0, &mut cnt);
    }

    fn dfs(
        root: Option<Rc<RefCell<TreeNode>>>,
        sum: i32,
        s: i32,
        cnt: &mut HashMap<i32, i32>
    ) -> i32 {
        if let Some(node) = root {
            let node = node.borrow();
            let s = s + node.val;
            let mut ans = *cnt.get(&(s - sum)).unwrap_or(&0);
            *cnt.entry(s).or_insert(0) += 1;
            ans += Self::dfs(node.left.clone(), sum, s, cnt);
            ans += Self::dfs(node.right.clone(), sum, s, cnt);
            *cnt.entry(s).or_insert(0) -= 1;
            return ans;
        }
        return 0;
    }
}
```

```swift
/* class TreeNode {
*    var val: Int
*    var left: TreeNode?
*    var right: TreeNode?
*
*    init(_ val: Int, _ left: TreeNode? = nil, _ right: TreeNode? = nil) {
*        self.val = val
*        self.left = left
*        self.right = right
*    }
* }
*/

class Solution {
    private var cnt: [Int: Int] = [:]
    private var target: Int = 0

    func pathSum(_ root: TreeNode?, _ sum: Int) -> Int {
        cnt[0] = 1
        target = sum
        return dfs(root, 0)
    }

    private func dfs(_ root: TreeNode?, _ s: Int) -> Int {
        guard let root = root else {
            return 0
        }
        let newSum = s + root.val
        let ans = cnt[newSum - target, default: 0]

        cnt[newSum, default: 0] += 1
        let leftPaths = dfs(root.left, newSum)
        let rightPaths = dfs(root.right, newSum)
        cnt[newSum, default: 0] -= 1

        return ans + leftPaths + rightPaths
    }
}
```

<!-- tabs:end -->

<!-- end -->
