---
comments: true
difficulty: Medium
tags:
    - Tree
    - Depth-First Search
    - String
    - Binary Tree
---

<!-- problem:start -->

# [606. Construct String from Binary Tree](https://leetcode.com/problems/construct-string-from-binary-tree)

[中文文档](/solution/0600-0699/0606.Construct%20String%20from%20Binary%20Tree/README.md)

## Description

<!-- description:start -->

<p>Given the <code>root</code> node of a binary tree, your task is to create a string representation of the tree following a specific set of formatting rules. The representation should be based on a preorder traversal of the binary tree and must adhere to the following guidelines:</p>

<ul>
	<li>
	<p><strong>Node Representation</strong>: Each node in the tree should be represented by its integer value.</p>
	</li>
	<li>
	<p><strong>Parentheses for Children</strong>: If a node has at least one child (either left or right), its children should be represented inside parentheses. Specifically:</p>

    <ul>
    	<li>If a node has a left child, the value of the left child should be enclosed in parentheses immediately following the node&#39;s value.</li>
    	<li>If a node has a right child, the value of the right child should also be enclosed in parentheses. The parentheses for the right child should follow those of the left child.</li>
    </ul>
    </li>
    <li>
    <p><strong>Omitting Empty Parentheses</strong>: Any empty parentheses pairs (i.e., <code>()</code>) should be omitted from the final string representation of the tree, with one specific exception: when a node has a right child but no left child. In such cases, you must include an empty pair of parentheses to indicate the absence of the left child. This ensures that the one-to-one mapping between the string representation and the original binary tree structure is maintained.</p>

    <p>In summary, empty parentheses pairs should be omitted when a node has only a left child or no children. However, when a node has a right child but no left child, an empty pair of parentheses must precede the representation of the right child to reflect the tree&#39;s structure accurately.</p>
    </li>

</ul>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0600-0699/0606.Construct%20String%20from%20Binary%20Tree/images/cons1-tree.jpg" style="padding: 10px; background: #fff; border-radius: .5rem;" />
<pre>
<strong>Input:</strong> root = [1,2,3,4]
<strong>Output:</strong> &quot;1(2(4))(3)&quot;
<strong>Explanation:</strong> Originally, it needs to be &quot;1(2(4)())(3()())&quot;, but you need to omit all the empty parenthesis pairs. And it will be &quot;1(2(4))(3)&quot;.
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0600-0699/0606.Construct%20String%20from%20Binary%20Tree/images/cons2-tree.jpg" style="padding: 10px; background: #fff; border-radius: .5rem;" />
<pre>
<strong>Input:</strong> root = [1,2,3,null,4]
<strong>Output:</strong> &quot;1(2()(4))(3)&quot;
<strong>Explanation:</strong> Almost the same as the first example, except the <code>()</code> after <code>2</code> is necessary to indicate the absence of a left child for <code>2</code> and the presence of a right child.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li>The number of nodes in the tree is in the range <code>[1, 10<sup>4</sup>]</code>.</li>
	<li><code>-1000 &lt;= Node.val &lt;= 1000</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1

<!-- thinking:start -->

> **Thinking**
>
> A preorder walk with parentheses can recover the tree, but the empty-parenthesis rule is asymmetric: a missing right child may omit `()`, a missing left child may not.
>
> Recurse in three cases: a leaf is just the value; no right child wraps only the left; otherwise wrap both. That matches the required omission rule.

<!-- thinking:end -->

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
    def tree2str(self, root: Optional[TreeNode]) -> str:
        def dfs(root):
            if root is None:
                return ''
            if root.left is None and root.right is None:
                return str(root.val)
            if root.right is None:
                return f'{root.val}({dfs(root.left)})'
            return f'{root.val}({dfs(root.left)})({dfs(root.right)})'

        return dfs(root)
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
    public String tree2str(TreeNode root) {
        if (root == null) {
            return "";
        }
        if (root.left == null && root.right == null) {
            return root.val + "";
        }
        if (root.right == null) {
            return root.val + "(" + tree2str(root.left) + ")";
        }
        return root.val + "(" + tree2str(root.left) + ")(" + tree2str(root.right) + ")";
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
    string tree2str(TreeNode* root) {
        if (!root) return "";
        if (!root->left && !root->right) return to_string(root->val);
        if (!root->right) return to_string(root->val) + "(" + tree2str(root->left) + ")";
        return to_string(root->val) + "(" + tree2str(root->left) + ")(" + tree2str(root->right) + ")";
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
func tree2str(root *TreeNode) string {
	if root == nil {
		return ""
	}
	if root.Left == nil && root.Right == nil {
		return strconv.Itoa(root.Val)
	}
	if root.Right == nil {
		return strconv.Itoa(root.Val) + "(" + tree2str(root.Left) + ")"
	}
	return strconv.Itoa(root.Val) + "(" + tree2str(root.Left) + ")(" + tree2str(root.Right) + ")"
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

function tree2str(root: TreeNode | null): string {
    if (root == null) {
        return '';
    }
    if (root.left == null && root.right == null) {
        return `${root.val}`;
    }
    return `${root.val}(${root.left ? tree2str(root.left) : ''})${
        root.right ? `(${tree2str(root.right)})` : ''
    }`;
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
use std::rc::Rc;
impl Solution {
    fn dfs(root: &Option<Rc<RefCell<TreeNode>>>, res: &mut String) {
        if let Some(node) = root {
            let node = node.borrow();
            res.push_str(node.val.to_string().as_str());

            if node.left.is_none() && node.right.is_none() {
                return;
            }
            res.push('(');
            if node.left.is_some() {
                Self::dfs(&node.left, res);
            }
            res.push(')');
            if node.right.is_some() {
                res.push('(');
                Self::dfs(&node.right, res);
                res.push(')');
            }
        }
    }

    pub fn tree2str(root: Option<Rc<RefCell<TreeNode>>>) -> String {
        let mut res = String::new();
        Self::dfs(&root, &mut res);
        res
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Solution 2: Explicit-Stack Preorder

<!-- thinking:start -->

> **Thinking**
>
> A preorder string with parentheses can be built by recursion: a leaf is just the value, a missing right child wraps only the left, and otherwise both children are wrapped. That is correct on a short tree.
>
> The tree can contain $10^4$ nodes. A left chain makes this walk recurse once per node and overflow the call stack. Returning each subtree as a new string also copies the same characters many times.
>
> The characters are already ordered by the preorder walk and the empty-parenthesis rule, so a subtree does not need to return a finished string.
>
> One buffer and an explicit stack follow that order. On entry the walk writes the current value; if the node is not a leaf it writes `(`, then pushes an exit marker and the left child. After the left subtree it writes `)`, and wraps the right child the same way when one exists. A leaf leaves only its value, and a missing left child leaves an empty pair.

<!-- thinking:end -->

An explicit stack writes the preorder string into one buffer. On entry we write the node value. A leaf stops there. Otherwise we write `(`, push a marker meaning the left subtree is finished, and push the left child. When that marker pops we write `)`. If a right child exists, we write `(` and push that child with its own closing marker.

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
    def tree2str(self, root: Optional[TreeNode]) -> str:
        parts = []
        stk = [(root, 0)]
        while stk:
            node, state = stk.pop()
            if state == 0:
                if node is None:
                    continue
                parts.append(str(node.val))
                if node.left is None and node.right is None:
                    continue
                parts.append('(')
                stk.append((node, 1))
                stk.append((node.left, 0))
                continue
            if state == 1:
                parts.append(')')
                if node.right is not None:
                    parts.append('(')
                    stk.append((node, 2))
                    stk.append((node.right, 0))
                continue
            parts.append(')')
        return ''.join(parts)
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

    public String tree2str(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        Deque<Frame> stk = new ArrayDeque<>();
        stk.push(new Frame(root, 0));
        while (!stk.isEmpty()) {
            Frame cur = stk.pop();
            TreeNode node = cur.node;
            if (cur.state == 0) {
                if (node == null) {
                    continue;
                }
                sb.append(node.val);
                if (node.left == null && node.right == null) {
                    continue;
                }
                sb.append('(');
                stk.push(new Frame(node, 1));
                if (node.left != null) {
                    stk.push(new Frame(node.left, 0));
                }
                continue;
            }
            if (cur.state == 1) {
                sb.append(')');
                if (node.right != null) {
                    sb.append('(');
                    stk.push(new Frame(node, 2));
                    stk.push(new Frame(node.right, 0));
                }
                continue;
            }
            sb.append(')');
        }
        return sb.toString();
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
    string tree2str(TreeNode* root) {
        string res;
        vector<pair<TreeNode*, int>> stk{{root, 0}};
        while (!stk.empty()) {
            auto [node, state] = stk.back();
            stk.pop_back();
            if (state == 0) {
                if (!node) {
                    continue;
                }
                res += to_string(node->val);
                if (!node->left && !node->right) {
                    continue;
                }
                res.push_back('(');
                stk.emplace_back(node, 1);
                stk.emplace_back(node->left, 0);
                continue;
            }
            if (state == 1) {
                res.push_back(')');
                if (node->right) {
                    res.push_back('(');
                    stk.emplace_back(node, 2);
                    stk.emplace_back(node->right, 0);
                }
                continue;
            }
            res.push_back(')');
        }
        return res;
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
func tree2str(root *TreeNode) string {
	var b strings.Builder
	type frame struct {
		node  *TreeNode
		state int
	}
	stk := []frame{{root, 0}}
	for len(stk) > 0 {
		cur := stk[len(stk)-1]
		stk = stk[:len(stk)-1]
		node, state := cur.node, cur.state
		if state == 0 {
			if node == nil {
				continue
			}
			b.WriteString(strconv.Itoa(node.Val))
			if node.Left == nil && node.Right == nil {
				continue
			}
			b.WriteByte('(')
			stk = append(stk, frame{node, 1}, frame{node.Left, 0})
			continue
		}
		if state == 1 {
			b.WriteByte(')')
			if node.Right != nil {
				b.WriteByte('(')
				stk = append(stk, frame{node, 2}, frame{node.Right, 0})
			}
			continue
		}
		b.WriteByte(')')
	}
	return b.String()
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

function tree2str(root: TreeNode | null): string {
    const parts: string[] = [];
    const stk: [TreeNode | null, number][] = [[root, 0]];
    while (stk.length) {
        const [node, state] = stk.pop()!;
        if (state === 0) {
            if (!node) {
                continue;
            }
            parts.push(`${node.val}`);
            if (!node.left && !node.right) {
                continue;
            }
            parts.push('(');
            stk.push([node, 1]);
            stk.push([node.left, 0]);
            continue;
        }
        if (state === 1) {
            parts.push(')');
            if (node && node.right) {
                parts.push('(');
                stk.push([node, 2]);
                stk.push([node.right, 0]);
            }
            continue;
        }
        parts.push(')');
    }
    return parts.join('');
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
use std::rc::Rc;

impl Solution {
    pub fn tree2str(root: Option<Rc<RefCell<TreeNode>>>) -> String {
        let mut res = String::new();
        let mut stk = vec![(root, 0)];
        while let Some((node, state)) = stk.pop() {
            if state == 0 {
                if let Some(node) = node {
                    let (val, left, leaf) = {
                        let b = node.borrow();
                        (b.val, b.left.clone(), b.left.is_none() && b.right.is_none())
                    };
                    res.push_str(&val.to_string());
                    if leaf {
                        continue;
                    }
                    res.push('(');
                    stk.push((Some(node), 1));
                    if left.is_some() {
                        stk.push((left, 0));
                    }
                }
            } else if state == 1 {
                if let Some(node) = node {
                    res.push(')');
                    let right = node.borrow().right.clone();
                    if right.is_some() {
                        res.push('(');
                        stk.push((Some(node), 2));
                        stk.push((right, 0));
                    }
                }
            } else {
                res.push(')');
            }
        }
        res
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
