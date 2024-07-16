---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2100-2199/2196.Create%20Binary%20Tree%20From%20Descriptions/README.md
rating: 1643
source: 第 283 场周赛 Q3
tags:
    - 树
    - 数组
    - 哈希表
    - 二叉树
---

<!-- problem:start -->

# [2196. 根据描述创建二叉树](https://leetcode.cn/problems/create-binary-tree-from-descriptions)

[English Version](/solution/2100-2199/2196.Create%20Binary%20Tree%20From%20Descriptions/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个二维整数数组 <code>descriptions</code> ，其中 <code>descriptions[i] = [parent<sub>i</sub>, child<sub>i</sub>, isLeft<sub>i</sub>]</code> 表示 <code>parent<sub>i</sub></code> 是 <code>child<sub>i</sub></code> 在 <strong>二叉树</strong> 中的 <strong>父节点</strong>，二叉树中各节点的值 <strong>互不相同</strong> 。此外：</p>

<ul>
	<li>如果 <code>isLeft<sub>i</sub> == 1</code> ，那么 <code>child<sub>i</sub></code> 就是 <code>parent<sub>i</sub></code> 的左子节点。</li>
	<li>如果 <code>isLeft<sub>i</sub> == 0</code> ，那么 <code>child<sub>i</sub></code> 就是 <code>parent<sub>i</sub></code> 的右子节点。</li>
</ul>

<p>请你根据 <code>descriptions</code> 的描述来构造二叉树并返回其 <strong>根节点</strong> 。</p>

<p>测试用例会保证可以构造出 <strong>有效</strong> 的二叉树。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2100-2199/2196.Create%20Binary%20Tree%20From%20Descriptions/images/example1drawio.png" style="width: 300px; height: 236px;" /></p>

<pre>
<strong>输入：</strong>descriptions = [[20,15,1],[20,17,0],[50,20,1],[50,80,0],[80,19,1]]
<strong>输出：</strong>[50,20,80,15,17,19]
<strong>解释：</strong>根节点是值为 50 的节点，因为它没有父节点。
结果二叉树如上图所示。
</pre>

<p><strong>示例 2：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2100-2199/2196.Create%20Binary%20Tree%20From%20Descriptions/images/example2drawio.png" style="width: 131px; height: 300px;" /></p>

<pre>
<strong>输入：</strong>descriptions = [[1,2,1],[2,3,0],[3,4,1]]
<strong>输出：</strong>[1,2,null,null,3,4]
<strong>解释：</strong>根节点是值为 1 的节点，因为它没有父节点。 
结果二叉树如上图所示。 </pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= descriptions.length &lt;= 10<sup>4</sup></code></li>
	<li><code>descriptions[i].length == 3</code></li>
	<li><code>1 &lt;= parent<sub>i</sub>, child<sub>i</sub> &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= isLeft<sub>i</sub> &lt;= 1</code></li>
	<li><code>descriptions</code> 所描述的二叉树是一棵有效二叉树</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：哈希表

我们可以用一个哈希表 $\textit{nodes}$ 来存储所有节点，其中键为节点的值，值为节点本身，用一个集合 $\textit{children}$ 来存储所有的子节点。

遍历 $\textit{descriptions}$，对于每个描述 $[\textit{parent}, \textit{child}, \textit{isLeft}]$，如果 $\textit{parent}$ 不在 $\textit{nodes}$ 中，我们就将 $\textit{parent}$ 加入 $\textit{nodes}$，并初始化一个值为 $\textit{parent}$ 的节点。如果 $\textit{child}$ 不在 $\textit{nodes}$ 中，我们就将 $\textit{child}$ 加入 $\textit{nodes}$，并初始化一个值为 $\textit{child}$ 的节点。然后我们将 $\textit{child}$ 加入 $\textit{children}$。

如果 $\textit{isLeft}$ 为真，我们就将 $\textit{child}$ 作为 $\textit{parent}$ 的左子节点，否则我们就将 $\textit{child}$ 作为 $\textit{parent}$ 的右子节点。

最后，我们遍历 $\textit{nodes}$，如果某个节点的值不在 $\textit{children}$ 中，那么这个节点就是根节点，我们返回这个节点。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 是 $\textit{descriptions}$ 的长度。

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
    def createBinaryTree(self, descriptions: List[List[int]]) -> Optional[TreeNode]:
        nodes = defaultdict(TreeNode)
        children = set()
        for parent, child, isLeft in descriptions:
            if parent not in nodes:
                nodes[parent] = TreeNode(parent)
            if child not in nodes:
                nodes[child] = TreeNode(child)
            children.add(child)
            if isLeft:
                nodes[parent].left = nodes[child]
            else:
                nodes[parent].right = nodes[child]
        root = (set(nodes.keys()) - children).pop()
        return nodes[root]
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
    public TreeNode createBinaryTree(int[][] descriptions) {
        Map<Integer, TreeNode> nodes = new HashMap<>();
        Set<Integer> children = new HashSet<>();
        for (var d : descriptions) {
            int parent = d[0], child = d[1], isLeft = d[2];
            if (!nodes.containsKey(parent)) {
                nodes.put(parent, new TreeNode(parent));
            }
            if (!nodes.containsKey(child)) {
                nodes.put(child, new TreeNode(child));
            }
            if (isLeft == 1) {
                nodes.get(parent).left = nodes.get(child);
            } else {
                nodes.get(parent).right = nodes.get(child);
            }
            children.add(child);
        }
        for (var e : nodes.entrySet()) {
            if (!children.contains(e.getKey())) {
                return e.getValue();
            }
        }
        return null;
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
    TreeNode* createBinaryTree(vector<vector<int>>& descriptions) {
        unordered_map<int, TreeNode*> nodes;
        unordered_set<int> children;
        for (const auto& d : descriptions) {
            int parent = d[0], child = d[1], isLeft = d[2];
            if (!nodes.contains(parent)) {
                nodes[parent] = new TreeNode(parent);
            }
            if (!nodes.contains(child)) {
                nodes[child] = new TreeNode(child);
            }
            if (isLeft) {
                nodes[parent]->left = nodes[child];
            } else {
                nodes[parent]->right = nodes[child];
            }
            children.insert(child);
        }
        for (const auto& [k, v] : nodes) {
            if (!children.contains(k)) {
                return v;
            }
        }
        return nullptr;
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
func createBinaryTree(descriptions [][]int) *TreeNode {
	nodes := map[int]*TreeNode{}
	children := map[int]bool{}
	for _, d := range descriptions {
		parent, child, isLeft := d[0], d[1], d[2]
		if _, ok := nodes[parent]; !ok {
			nodes[parent] = &TreeNode{Val: parent}
		}
		if _, ok := nodes[child]; !ok {
			nodes[child] = &TreeNode{Val: child}
		}
		if isLeft == 1 {
			nodes[parent].Left = nodes[child]
		} else {
			nodes[parent].Right = nodes[child]
		}
		children[child] = true
	}
	for k, v := range nodes {
		if _, ok := children[k]; !ok {
			return v
		}
	}
	return nil
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

function createBinaryTree(descriptions: number[][]): TreeNode | null {
    const nodes: Record<number, TreeNode> = {};
    const children = new Set<number>();
    for (const [parent, child, isLeft] of descriptions) {
        if (!nodes[parent]) {
            nodes[parent] = new TreeNode(parent);
        }
        if (!nodes[child]) {
            nodes[child] = new TreeNode(child);
        }
        if (isLeft) {
            nodes[parent].left = nodes[child];
        } else {
            nodes[parent].right = nodes[child];
        }
        children.add(child);
    }
    for (const [k, v] of Object.entries(nodes)) {
        if (!children.has(+k)) {
            return v;
        }
    }
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
use std::collections::{HashMap, HashSet};
use std::rc::Rc;
impl Solution {
    pub fn create_binary_tree(descriptions: Vec<Vec<i32>>) -> Option<Rc<RefCell<TreeNode>>> {
        let mut nodes = HashMap::new();
        let mut children = HashSet::new();

        for d in descriptions {
            let parent = d[0];
            let child = d[1];
            let is_left = d[2];

            nodes
                .entry(parent)
                .or_insert_with(|| Rc::new(RefCell::new(TreeNode::new(parent))));
            nodes
                .entry(child)
                .or_insert_with(|| Rc::new(RefCell::new(TreeNode::new(child))));

            if is_left == 1 {
                nodes.get(&parent).unwrap().borrow_mut().left =
                    Some(Rc::clone(nodes.get(&child).unwrap()));
            } else {
                nodes.get(&parent).unwrap().borrow_mut().right =
                    Some(Rc::clone(nodes.get(&child).unwrap()));
            }

            children.insert(child);
        }

        for (key, node) in &nodes {
            if !children.contains(key) {
                return Some(Rc::clone(node));
            }
        }

        None
    }
}
```

#### JavaScript

```js
/**
 * Definition for a binary tree node.
 * function TreeNode(val, left, right) {
 *     this.val = (val===undefined ? 0 : val)
 *     this.left = (left===undefined ? null : left)
 *     this.right = (right===undefined ? null : right)
 * }
 */
/**
 * @param {number[][]} descriptions
 * @return {TreeNode}
 */
var createBinaryTree = function (descriptions) {
    const nodes = {};
    const children = new Set();
    for (const [parent, child, isLeft] of descriptions) {
        if (!nodes[parent]) {
            nodes[parent] = new TreeNode(parent);
        }
        if (!nodes[child]) {
            nodes[child] = new TreeNode(child);
        }
        if (isLeft) {
            nodes[parent].left = nodes[child];
        } else {
            nodes[parent].right = nodes[child];
        }
        children.add(child);
    }
    for (const [k, v] of Object.entries(nodes)) {
        if (!children.has(+k)) {
            return v;
        }
    }
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
