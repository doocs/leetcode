# [894. All Possible Full Binary Trees](https://leetcode.com/problems/all-possible-full-binary-trees)

[中文文档](/solution/0800-0899/0894.All%20Possible%20Full%20Binary%20Trees/README.md)

## Description

<p>Given an integer <code>n</code>, return <em>a list of all possible <strong>full binary trees</strong> with</em> <code>n</code> <em>nodes</em>. Each node of each tree in the answer must have <code>Node.val == 0</code>.</p>

<p>Each element of the answer is the root node of one possible tree. You may return the final list of trees in <strong>any order</strong>.</p>

<p>A <strong>full binary tree</strong> is a binary tree where each node has exactly <code>0</code> or <code>2</code> children.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0800-0899/0894.All%20Possible%20Full%20Binary%20Trees/images/fivetrees.png" style="width: 700px; height: 400px;" />
<pre>
<strong>Input:</strong> n = 7
<strong>Output:</strong> [[0,0,0,null,null,0,0,null,null,0,0],[0,0,0,null,null,0,0,0,0],[0,0,0,0,0,0,0],[0,0,0,0,0,null,null,null,null,0,0],[0,0,0,0,0,null,null,0,0]]
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 3
<strong>Output:</strong> [[0,0,0]]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 20</code></li>
</ul>

## Solutions

### Solution 1

<!-- tabs:start -->

```python
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def allPossibleFBT(self, n: int) -> List[Optional[TreeNode]]:
        @cache
        def dfs(n: int) -> List[Optional[TreeNode]]:
            if n == 1:
                return [TreeNode()]
            ans = []
            for i in range(n - 1):
                j = n - 1 - i
                for left in dfs(i):
                    for right in dfs(j):
                        ans.append(TreeNode(0, left, right))
            return ans

        return dfs(n)
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
    private List<TreeNode>[] f;

    public List<TreeNode> allPossibleFBT(int n) {
        f = new List[n + 1];
        return dfs(n);
    }

    private List<TreeNode> dfs(int n) {
        if (f[n] != null) {
            return f[n];
        }
        if (n == 1) {
            return List.of(new TreeNode());
        }
        List<TreeNode> ans = new ArrayList<>();
        for (int i = 0; i < n - 1; ++i) {
            int j = n - 1 - i;
            for (var left : dfs(i)) {
                for (var right : dfs(j)) {
                    ans.add(new TreeNode(0, left, right));
                }
            }
        }
        return f[n] = ans;
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
 *     TreeNode() : val(0), left(nullptr), right(nullptr) {}
 *     TreeNode(int x) : val(x), left(nullptr), right(nullptr) {}
 *     TreeNode(int x, TreeNode *left, TreeNode *right) : val(x), left(left), right(right) {}
 * };
 */
class Solution {
public:
    vector<TreeNode*> allPossibleFBT(int n) {
        vector<vector<TreeNode*>> f(n + 1);
        function<vector<TreeNode*>(int)> dfs = [&](int n) -> vector<TreeNode*> {
            if (f[n].size()) {
                return f[n];
            }
            if (n == 1) {
                return vector<TreeNode*>{new TreeNode()};
            }
            vector<TreeNode*> ans;
            for (int i = 0; i < n - 1; ++i) {
                int j = n - 1 - i;
                for (auto left : dfs(i)) {
                    for (auto right : dfs(j)) {
                        ans.push_back(new TreeNode(0, left, right));
                    }
                }
            }
            return f[n] = ans;
        };
        return dfs(n);
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
func allPossibleFBT(n int) []*TreeNode {
	f := make([][]*TreeNode, n+1)
	var dfs func(int) []*TreeNode
	dfs = func(n int) []*TreeNode {
		if len(f[n]) > 0 {
			return f[n]
		}
		if n == 1 {
			return []*TreeNode{&TreeNode{Val: 0}}
		}
		ans := []*TreeNode{}
		for i := 0; i < n-1; i++ {
			j := n - 1 - i
			for _, left := range dfs(i) {
				for _, right := range dfs(j) {
					ans = append(ans, &TreeNode{0, left, right})
				}
			}
		}
		f[n] = ans
		return ans
	}
	return dfs(n)
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

function allPossibleFBT(n: number): Array<TreeNode | null> {
    const f: Array<Array<TreeNode | null>> = new Array(n + 1).fill(0).map(() => []);
    const dfs = (n: number): Array<TreeNode | null> => {
        if (f[n].length) {
            return f[n];
        }
        if (n === 1) {
            f[n].push(new TreeNode(0));
            return f[n];
        }
        const ans: Array<TreeNode | null> = [];
        for (let i = 0; i < n - 1; ++i) {
            const j = n - 1 - i;
            for (const left of dfs(i)) {
                for (const right of dfs(j)) {
                    ans.push(new TreeNode(0, left, right));
                }
            }
        }
        return (f[n] = ans);
    };
    return dfs(n);
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

impl TreeNode {
    pub fn new_with_node(
        left: Option<Rc<RefCell<TreeNode>>>,
        right: Option<Rc<RefCell<TreeNode>>>
    ) -> Self {
        Self {
            val: 0,
            left,
            right,
        }
    }
}

use std::rc::Rc;
use std::cell::RefCell;
impl Solution {
    #[allow(dead_code)]
    pub fn all_possible_fbt(n: i32) -> Vec<Option<Rc<RefCell<TreeNode>>>> {
        let mut record_vec = vec![vec![]; n as usize + 1];
        Self::dfs(n, &mut record_vec)
    }

    #[allow(dead_code)]
    fn dfs(
        n: i32,
        record_vec: &mut Vec<Vec<Option<Rc<RefCell<TreeNode>>>>>
    ) -> Vec<Option<Rc<RefCell<TreeNode>>>> {
        if record_vec[n as usize].len() != 0 {
            return record_vec[n as usize].clone();
        }
        if n == 1 {
            // Just directly return a single node
            return vec![Some(Rc::new(RefCell::new(TreeNode::new(0))))];
        }
        // Otherwise, need to construct return vector
        let mut ret_vec = Vec::new();

        // Enumerate the node number for left subtree from 0 -> n - 1
        for i in 0..n - 1 {
            // The number of right subtree node
            let j = n - i - 1;
            for left in Self::dfs(i, record_vec) {
                for right in Self::dfs(j, record_vec) {
                    // Construct the ret vector
                    ret_vec.push(
                        Some(
                            Rc::new(
                                RefCell::new(TreeNode::new_with_node(left.clone(), right.clone()))
                            )
                        )
                    );
                }
            }
        }

        record_vec[n as usize] = ret_vec;

        record_vec[n as usize].clone()
    }
}
```

<!-- tabs:end -->

<!-- end -->
