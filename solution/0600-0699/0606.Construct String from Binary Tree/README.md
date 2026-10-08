---
comments: true
difficulty: 中等
tags:
    - 树
    - 深度优先搜索
    - 字符串
    - 二叉树
---

<!-- problem:start -->

# [606. 根据二叉树创建字符串](https://leetcode.cn/problems/construct-string-from-binary-tree)

[English Version](/solution/0600-0699/0606.Construct%20String%20from%20Binary%20Tree/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定二叉树的根节点 <code>root</code>，你的任务是按照一组特定的格式规则创建该树的字符串表示。该表示应基于二叉树的前序遍历，并且必须遵循以下规则：</p>

<ul>
	<li>
	<p><strong>节点表示</strong>：树中的每个节点都应使用其整数值表示。</p>
	</li>
	<li>
	<p><strong>子节点的括号表示</strong>：如果一个节点至少有一个子节点（左子节点或右子节点），则其子节点应使用括号表示。具体来说：</p>

    <ul>
    	<li>如果一个节点存在左子节点，则应将左子节点的表示放在括号中，并紧跟在当前节点的值之后。</li>
    	<li>如果一个节点存在右子节点，则也应将右子节点的表示放在括号中。右子节点对应的括号应位于左子节点对应括号之后。</li>
    </ul>
    </li>
    <li>
    <p><strong>省略空括号</strong>：最终的树字符串表示中，应省略所有空括号对（即 <code>()</code>），但有一种特殊情况除外：当一个节点存在右子节点但不存在左子节点时，必须保留一对空括号，以表示左子节点缺失。这样可以保证字符串表示与原二叉树结构之间的一一对应关系。</p>

    <p>总而言之，当一个节点只有左子节点或者没有任何子节点时，应省略空括号对。但是，当一个节点只有右子节点而没有左子节点时，必须在右子节点的表示之前添加一对空括号，以准确表示树的结构。</p>
    </li>

</ul>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0600-0699/0606.Construct%20String%20from%20Binary%20Tree/images/cons1-tree.jpg" style="padding: 10px; background: #fff; border-radius: .5rem;" />
<pre>
<strong>输入：</strong> root = [1,2,3,4]
<strong>输出：</strong> "1(2(4))(3)"
<strong>解释：</strong> 原本需要表示为 "1(2(4)())(3()())"，但需要省略所有空括号对。因此最终得到 "1(2(4))(3)"。
</pre>

<p><strong class="example">示例 2：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0600-0699/0606.Construct%20String%20from%20Binary%20Tree/images/cons2-tree.jpg" style="padding: 10px; background: #fff; border-radius: .5rem;" />
<pre>
<strong>输入：</strong> root = [1,2,3,null,4]
<strong>输出：</strong> "1(2()(4))(3)"
<strong>解释：</strong> 与第一个示例基本相同，不同之处在于 <code>2</code> 后面的 <code>()</code> 是必须保留的，因为它表示节点 <code>2</code> 不存在左子节点，但存在右子节点。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li>树中节点的数量范围为 <code>[1, 10<sup>4</sup>]</code>。</li>
	<li><code>-1000 &lt;= Node.val &lt;= 1000</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一

<!-- thinking:start -->

> **思考**
>
> 前序遍历加括号可以还原结构，但空括号规则不对称：仅有左子树时要写一对括号，仅有右子树时左侧的空括号不能省。
>
> 因此递归按三种情形输出：叶子只写值；无右子树只包左；否则左右都包。这样省略规则与题意一致。

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

### 方法二：显式栈前序遍历

<!-- thinking:start -->

> **思考**
>
> 把二叉树写成带括号的前序字符串，递归按三种情形输出即可：叶子只写值，没有右孩子时只包左子树，否则左右都包。较短的树上这样是对的。
>
> 结点个数可达 $10^4$。左链使这次前序按结点个数递归，调用栈会溢出。先拼出子树字符串再接回父结点，还会把同一段字符复制多次。
>
> 写出顺序已经由前序和空括号规则决定，并不需要子树先返回一整段字符串。
>
> 因此用一个缓冲区和显式栈。进入结点时写入当前值；不是叶子就写下左括号，压入结束标记和左孩子。左子树结束后补右括号，有右孩子时再包一层。叶子只留下值，只有右孩子时左侧是一对空括号。

<!-- thinking:end -->

我们用显式栈按前序把结点写入同一个字符串。进入结点时先写值。若左右都空，该结点结束。否则先写左括号，压入“左子树已结束”的标记，再压入左孩子。标记弹出时补上右括号；若右孩子存在，再写左括号并压入右孩子和对应的结束标记。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为二叉树的结点个数。

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
