---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/lcci/04.12.Paths%20with%20Sum/README.md
---

<!-- problem:start -->

# [面试题 04.12. 求和路径](https://leetcode.cn/problems/paths-with-sum-lcci)

[English Version](/lcci/04.12.Paths%20with%20Sum/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定一棵二叉树，其中每个节点都含有一个整数数值(该值或正或负)。设计一个算法，打印节点数值总和等于某个给定值的所有路径的数量。注意，路径不一定非得从二叉树的根节点或叶节点开始或结束，但是其方向必须向下(只能从父节点指向子节点方向)。</p>

<p><strong>示例:</strong><br>
给定如下二叉树，以及目标和&nbsp;<code>sum = 22</code>，</p>

<pre>              5
             / \
            4   8
           /   / \
          11  13  4
         /  \    / \
        7    2  5   1
</pre>

<p>返回:</p>

<pre>3
<strong>解释：</strong>和为 22&nbsp;的路径有：[5,4,11,2], [5,8,4,5], [4,11,7]</pre>

<p>提示：</p>

<ul>
	<li><code>节点总数 &lt;= 10000</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：哈希表 + 前缀和 + 递归

我们可以运用前缀和的思想，对二叉树进行递归遍历，同时用哈希表 $cnt$ 统计从根节点到当前节点的路径上各个前缀和出现的次数。

我们设计一个递归函数 $dfs(node, s)$，表示当前遍历到的节点为 $node$，从根节点到当前节点的路径上的前缀和为 $s$。函数的返回值是统计以 $node$ 节点及其子树节点作为路径终点且路径和为 $sum$ 的路径数目。那么答案就是 $dfs(root, 0)$。

函数 $dfs(node, s)$ 的递归过程如下：

-   如果当前节点 $node$ 为空，则返回 $0$。
-   计算从根节点到当前节点的路径上的前缀和 $s$。
-   用 $cnt[s - sum]$ 表示以当前节点为路径终点且路径和为 $sum$ 的路径数目，其中 $cnt[s - sum]$ 即为 $cnt$ 中前缀和为 $s - sum$ 的个数。
-   将前缀和 $s$ 的计数值加 $1$，即 $cnt[s] = cnt[s] + 1$。
-   递归地遍历当前节点的左右子节点，即调用函数 $dfs(node.left, s)$ 和 $dfs(node.right, s)$，并将它们的返回值相加。
-   在返回值计算完成以后，需要将当前节点的前缀和 $s$ 的计数值减 $1$，即执行 $cnt[s] = cnt[s] - 1$。
-   最后返回答案。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 是二叉树的节点个数。

<!-- tabs:start -->

#### Python3

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

#### Java

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

#### C++

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

#### Go

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

#### TypeScript

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

#### Rust

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
use std::cell::RefCell;
use std::collections::HashMap;
use std::rc::Rc;
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
        cnt: &mut HashMap<i32, i32>,
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

#### Swift

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

<!-- solution:end -->

<!-- problem:end -->
