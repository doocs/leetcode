---
comments: true
difficulty: Medium
tags:
    - Tree
    - Depth-First Search
    - Hash Table
    - Binary Tree
    - Tree DP
---

<!-- problem:start -->

# [508. Most Frequent Subtree Sum](https://leetcode.com/problems/most-frequent-subtree-sum)

[中文文档](/solution/0500-0599/0508.Most%20Frequent%20Subtree%20Sum/README.md)

## Description

<!-- description:start -->

<p>Given the <code>root</code> of a binary tree, return the most frequent <strong>subtree sum</strong>. If there is a tie, return all the values with the highest frequency in any order.</p>

<p>The <strong>subtree sum</strong> of a node is defined as the sum of all the node values formed by the subtree rooted at that node (including the node itself).</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0500-0599/0508.Most%20Frequent%20Subtree%20Sum/images/freq1-tree.jpg" style="width: 207px; height: 183px;" />
<pre>
<strong>Input:</strong> root = [5,2,-3]
<strong>Output:</strong> [2,-3,4]
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0500-0599/0508.Most%20Frequent%20Subtree%20Sum/images/freq2-tree.jpg" style="width: 207px; height: 183px;" />
<pre>
<strong>Input:</strong> root = [5,2,-5]
<strong>Output:</strong> [2]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li>The number of nodes in the tree is in the range <code>[1, 10<sup>4</sup>]</code>.</li>
	<li><code>-10<sup>5</sup> &lt;= Node.val &lt;= 10<sup>5</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Hash Table + DFS

<!-- thinking:start -->

> **Thinking**
>
> A subtree sum is left + right + root, so both children must be known first. Rescanning each subtree repeats work.
>
> A post-order DFS returns the current sum and a hash map counts frequencies. After the walk, keep the sums whose frequency is maximal. One traversal both sums and counts.

<!-- thinking:end -->

We can use a hash table $\textit{cnt}$ to record the frequency of each subtree sum. Then, we use depth-first search (DFS) to traverse the entire tree, calculate the sum of elements for each subtree, and update $\textit{cnt}$.

Finally, we traverse $\textit{cnt}$ to find all subtree sums that appear most frequently.

The time complexity is $O(n)$, and the space complexity is $O(n)$. Here, $n$ is the number of nodes in the binary tree.

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

<!-- solution:start -->

### Solution 2: Hash Table + Explicit Stack

<!-- thinking:start -->

> **Thinking**
>
> A subtree sum is the left sum plus the right sum plus the node value, so both children must be known first. Rescanning each subtree repeats work. Returning the sum from a postorder recursion is correct on a short tree.
>
> The tree can contain $10^4$ nodes. A left chain makes this postorder walk recurse once per node and overflow the call stack.
>
> Each sum depends only on the two children, so children must be finished before their parent. Frequencies can be counted in a hash table during the walk.
>
> An explicit stack performs that postorder walk. On entry it pushes an exit marker and the two children; on exit it writes the subtree sum into the hash table. After the walk, the sums with the highest frequency are the answer.

<!-- thinking:end -->

A hash table $\textit{cnt}$ records how often each subtree sum appears. An explicit stack walks the binary tree in postorder. On entry we push an exit marker, then the right child and the left child. On exit the children's sums are already stored, the current sum is those two plus the node value, and $\textit{cnt}$ is updated.

Finally we keep every subtree sum whose frequency is maximal.

The time complexity is $O(n)$, and the space complexity is $O(n)$. Here, $n$ is the number of nodes in the binary tree.

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
        cnt = Counter()
        sub = {}
        stk = [(root, 0)]
        while stk:
            node, state = stk.pop()
            if node is None:
                continue
            if state == 0:
                stk.append((node, 1))
                stk.append((node.right, 0))
                stk.append((node.left, 0))
                continue
            l = sub[id(node.left)] if node.left is not None else 0
            r = sub[id(node.right)] if node.right is not None else 0
            s = l + r + node.val
            cnt[s] += 1
            sub[id(node)] = s
        if not cnt:
            return []
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
    private static class Frame {
        TreeNode node;
        int state;

        Frame(TreeNode node, int state) {
            this.node = node;
            this.state = state;
        }
    }

    public int[] findFrequentTreeSum(TreeNode root) {
        Map<Integer, Integer> cnt = new HashMap<>();
        Map<TreeNode, Integer> sub = new IdentityHashMap<>();
        int mx = 0;
        if (root != null) {
            Deque<Frame> stk = new ArrayDeque<>();
            stk.push(new Frame(root, 0));
            while (!stk.isEmpty()) {
                Frame cur = stk.pop();
                TreeNode node = cur.node;
                if (cur.state == 0) {
                    stk.push(new Frame(node, 1));
                    if (node.right != null) {
                        stk.push(new Frame(node.right, 0));
                    }
                    if (node.left != null) {
                        stk.push(new Frame(node.left, 0));
                    }
                    continue;
                }
                int l = node.left == null ? 0 : sub.get(node.left);
                int r = node.right == null ? 0 : sub.get(node.right);
                int s = node.val + l + r;
                mx = Math.max(mx, cnt.merge(s, 1, Integer::sum));
                sub.put(node, s);
            }
        }
        List<Integer> ans = new ArrayList<>();
        for (var e : cnt.entrySet()) {
            if (e.getValue() == mx) {
                ans.add(e.getKey());
            }
        }
        return ans.stream().mapToInt(i -> i).toArray();
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
        unordered_map<TreeNode*, int> sub;
        int mx = 0;
        vector<pair<TreeNode*, int>> stk{{root, 0}};
        while (!stk.empty()) {
            auto [node, state] = stk.back();
            stk.pop_back();
            if (!node) {
                continue;
            }
            if (state == 0) {
                stk.emplace_back(node, 1);
                if (node->right) {
                    stk.emplace_back(node->right, 0);
                }
                if (node->left) {
                    stk.emplace_back(node->left, 0);
                }
                continue;
            }
            int l = node->left ? sub[node->left] : 0;
            int r = node->right ? sub[node->right] : 0;
            int s = node->val + l + r;
            mx = max(mx, ++cnt[s]);
            sub[node] = s;
        }
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
	sub := map[*TreeNode]int{}
	mx := 0
	type frame struct {
		node  *TreeNode
		state int
	}
	stk := []frame{{root, 0}}
	for len(stk) > 0 {
		cur := stk[len(stk)-1]
		stk = stk[:len(stk)-1]
		node, state := cur.node, cur.state
		if node == nil {
			continue
		}
		if state == 0 {
			stk = append(stk, frame{node, 1})
			if node.Right != nil {
				stk = append(stk, frame{node.Right, 0})
			}
			if node.Left != nil {
				stk = append(stk, frame{node.Left, 0})
			}
			continue
		}
		l, r := 0, 0
		if node.Left != nil {
			l = sub[node.Left]
		}
		if node.Right != nil {
			r = sub[node.Right]
		}
		s := node.Val + l + r
		cnt[s]++
		mx = max(mx, cnt[s])
		sub[node] = s
	}
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
    const sub = new Map<TreeNode, number>();
    let mx = 0;
    const stk: [TreeNode | null, number][] = [[root, 0]];
    while (stk.length) {
        const [node, state] = stk.pop()!;
        if (!node) {
            continue;
        }
        if (state === 0) {
            stk.push([node, 1]);
            if (node.right) {
                stk.push([node.right, 0]);
            }
            if (node.left) {
                stk.push([node.left, 0]);
            }
            continue;
        }
        const l = node.left ? sub.get(node.left)! : 0;
        const r = node.right ? sub.get(node.right)! : 0;
        const s = node.val + l + r;
        cnt.set(s, (cnt.get(s) ?? 0) + 1);
        mx = Math.max(mx, cnt.get(s)!);
        sub.set(node, s);
    }
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
        let mut cnt: HashMap<i32, i32> = HashMap::new();
        let mut sub: HashMap<usize, i32> = HashMap::new();
        let mut stk = vec![(root, 0)];
        while let Some((node, state)) = stk.pop() {
            if let Some(node) = node {
                if state == 0 {
                    let (left, right) = {
                        let b = node.borrow();
                        (b.left.clone(), b.right.clone())
                    };
                    stk.push((Some(node), 1));
                    if right.is_some() {
                        stk.push((right, 0));
                    }
                    if left.is_some() {
                        stk.push((left, 0));
                    }
                } else {
                    let b = node.borrow();
                    let l = b
                        .left
                        .as_ref()
                        .map(|n| sub[&(Rc::as_ptr(n) as usize)])
                        .unwrap_or(0);
                    let r = b
                        .right
                        .as_ref()
                        .map(|n| sub[&(Rc::as_ptr(n) as usize)])
                        .unwrap_or(0);
                    let s = l + r + b.val;
                    *cnt.entry(s).or_insert(0) += 1;
                    drop(b);
                    sub.insert(Rc::as_ptr(&node) as usize, s);
                }
            }
        }
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
