---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0500-0599/0508.Most%20Frequent%20Subtree%20Sum/README.md
tags:
    - 树
    - 深度优先搜索
    - 哈希表
    - 二叉树
---

<!-- problem:start -->

# [508. 出现次数最多的子树元素和](https://leetcode.cn/problems/most-frequent-subtree-sum)

[English Version](/solution/0500-0599/0508.Most%20Frequent%20Subtree%20Sum/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个二叉树的根结点&nbsp;<code>root</code>&nbsp;，请返回出现次数最多的子树元素和。如果有多个元素出现的次数相同，返回所有出现次数最多的子树元素和（不限顺序）。</p>

<p>一个结点的&nbsp;<strong>「子树元素和」</strong>&nbsp;定义为以该结点为根的二叉树上所有结点的元素之和（包括结点本身）。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0500-0599/0508.Most%20Frequent%20Subtree%20Sum/images/freq1-tree.jpg" /></p>

<pre>
<strong>输入:</strong> root = [5,2,-3]
<strong>输出:</strong> [2,-3,4]
</pre>

<p><strong>示例&nbsp;2：</strong></p>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0500-0599/0508.Most%20Frequent%20Subtree%20Sum/images/freq2-tree.jpg" /></p>

<pre>
<strong>输入:</strong> root = [5,2,-5]
<b>输出:</b> [2]
</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li>节点数在&nbsp;<code>[1, 10<sup>4</sup>]</code>&nbsp;范围内</li>
	<li><code>-10<sup>5</sup>&nbsp;&lt;= Node.val &lt;= 10<sup>5</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：哈希表 + DFS

我们可以使用一个哈希表 $\textit{cnt}$ 记录每个子树元素和出现的次数，然后使用深度优先搜索遍历整棵树，统计每个子树的元素和，并更新 $\textit{cnt}$。

最后，我们遍历 $\textit{cnt}$，找到所有出现次数最多的子树元素和。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为二叉树的节点个数。

<!-- tabs:start -->

#### Python3

```python
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def findFrequentTreeSum(self, root: Optional[TreeNode]) -> List[int]:
        def dfs(root: Optional[TreeNode]) -> int:
            if root is None:
                return 0
            l, r = dfs(root.left), dfs(root.right)
            s = l + r + root.val
            cnt[s] += 1
            return s

        cnt = Counter()
        dfs(root)
        mx = max(cnt.values())
        return [k for k, v in cnt.items() if v == mx]
```

#### Java

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
    private Map<Integer, Integer> cnt = new HashMap<>();
    private int mx;

    public int[] findFrequentTreeSum(TreeNode root) {
        dfs(root);
        List<Integer> ans = new ArrayList<>();
        for (var e : cnt.entrySet()) {
            if (e.getValue() == mx) {
                ans.add(e.getKey());
            }
        }
        return ans.stream().mapToInt(i -> i).toArray();
    }

    private int dfs(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int s = root.val + dfs(root.left) + dfs(root.right);
        mx = Math.max(mx, cnt.merge(s, 1, Integer::sum));
        return s;
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
 *     TreeNode() : val(0), left(nullptr), right(nullptr) {}
 *     TreeNode(int x) : val(x), left(nullptr), right(nullptr) {}
 *     TreeNode(int x, TreeNode *left, TreeNode *right) : val(x), left(left), right(right) {}
 * };
 */
class Solution {
public:
    vector<int> findFrequentTreeSum(TreeNode* root) {
        unordered_map<int, int> cnt;
        int mx = 0;
        function<int(TreeNode*)> dfs = [&](TreeNode* root) -> int {
            if (!root) {
                return 0;
            }
            int s = root->val + dfs(root->left) + dfs(root->right);
            mx = max(mx, ++cnt[s]);
            return s;
        };
        dfs(root);
        vector<int> ans;
        for (const auto& [k, v] : cnt) {
            if (v == mx) {
                ans.push_back(k);
            }
        }
        return ans;
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
func findFrequentTreeSum(root *TreeNode) (ans []int) {
	cnt := map[int]int{}
	var mx int
	var dfs func(*TreeNode) int
	dfs = func(root *TreeNode) int {
		if root == nil {
			return 0
		}
		s := root.Val + dfs(root.Left) + dfs(root.Right)
		cnt[s]++
		mx = max(mx, cnt[s])
		return s
	}
	dfs(root)
	for k, v := range cnt {
		if v == mx {
			ans = append(ans, k)
		}
	}
	return
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

function findFrequentTreeSum(root: TreeNode | null): number[] {
    const cnt = new Map<number, number>();
    let mx = 0;
    const dfs = (root: TreeNode | null): number => {
        if (!root) {
            return 0;
        }
        const { val, left, right } = root;
        const s = val + dfs(left) + dfs(right);
        cnt.set(s, (cnt.get(s) ?? 0) + 1);
        mx = Math.max(mx, cnt.get(s)!);
        return s;
    };
    dfs(root);
    return Array.from(cnt.entries())
        .filter(([_, c]) => c === mx)
        .map(([s, _]) => s);
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
    pub fn find_frequent_tree_sum(root: Option<Rc<RefCell<TreeNode>>>) -> Vec<i32> {
        fn dfs(root: Option<Rc<RefCell<TreeNode>>>, cnt: &mut HashMap<i32, i32>) -> i32 {
            if let Some(node) = root {
                let l = dfs(node.borrow().left.clone(), cnt);
                let r = dfs(node.borrow().right.clone(), cnt);
                let s = l + r + node.borrow().val;
                *cnt.entry(s).or_insert(0) += 1;
                s
            } else {
                0
            }
        }

        let mut cnt = HashMap::new();
        dfs(root, &mut cnt);

        let mx = cnt.values().cloned().max().unwrap_or(0);
        cnt.into_iter()
            .filter(|&(_, v)| v == mx)
            .map(|(k, _)| k)
            .collect()
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
