---
comments: true
difficulty: 中等
tags:
    - 树
    - 深度优先搜索
    - 广度优先搜索
    - 哈希表
    - 二叉树
---

<!-- problem:start -->

# [1485. 克隆含随机指针的二叉树 🔒](https://leetcode.cn/problems/clone-binary-tree-with-random-pointer)

[English Version](/solution/1400-1499/1485.Clone%20Binary%20Tree%20With%20Random%20Pointer/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个二叉树，树中每个节点都含有一个附加的随机指针，该指针可以指向树中的任何节点或者指向空（<code>null</code>）。</p>

<p>请返回该树的 <strong><a href="https://baike.baidu.com/item/%E6%B7%B1%E6%8B%B7%E8%B4%9D/22785317?fr=aladdin" target="_blank">深拷贝</a></strong> 。</p>

<p>该树的输入/输出形式与普通二叉树相同，每个节点都用 <code>[val, random_index]</code> 表示：</p>

<ul>
	<li><code>val</code>：表示 <code>Node.val</code> 的整数</li>
	<li><code>random_index</code>：随机指针指向的节点（在输入的树数组中）的下标；如果未指向任何节点，则为 <code>null</code> 。</li>
</ul>

<p>该树以 <code>Node</code> 类的形式给出，而你需要以 <code>NodeCopy</code> 类的形式返回克隆得到的树。<code>NodeCopy</code> 类和<code>Node</code> 类定义一致。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1400-1499/1485.Clone%20Binary%20Tree%20With%20Random%20Pointer/images/clone_1.png" style="height: 473px; width: 500px;" /></p>

<pre>
<strong>输入：</strong>root = [[1,null],null,[4,3],[7,0]]
<strong>输出：</strong>[[1,null],null,[4,3],[7,0]]
<strong>解释：</strong>初始二叉树为 [1,null,4,7] 。
节点 1 的随机指针指向 null，所以表示为 [1, null] 。
节点 4 的随机指针指向 7，所以表示为 [4, 3] 其中 3 是树数组中节点 7 对应的下标。
节点 7 的随机指针指向 1，所以表示为 [7, 0] 其中 0 是树数组中节点 1 对应的下标。
</pre>

<p><strong>示例 2：</strong></p>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1400-1499/1485.Clone%20Binary%20Tree%20With%20Random%20Pointer/images/clone_2.png" style="height: 540px; width: 500px;" /></p>

<pre>
<strong>输入：</strong>root = [[1,4],null,[1,0],null,[1,5],[1,5]]
<strong>输出：</strong>[[1,4],null,[1,0],null,[1,5],[1,5]]
<strong>解释：</strong>节点的随机指针可以指向它自身。
</pre>

<p><strong>示例 3：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1400-1499/1485.Clone%20Binary%20Tree%20With%20Random%20Pointer/images/e2.png" style="height: 426px; width: 500px;" /></p>

<pre>
<strong>输入：</strong>root = [[1,6],[2,5],[3,4],[4,3],[5,2],[6,1],[7,0]]
<strong>输出：</strong>[[1,6],[2,5],[3,4],[4,3],[5,2],[6,1],[7,0]]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>tree</code> 中节点数目范围是 <code>[0, 1000]</code></li>
	<li>每个节点的值的范围是 <code>[1, 10^6]</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：哈希表 + DFS

<!-- thinking:start -->

> **思考**
>
> 除左右孩子外还有 $\textit{random}$，可能指向任意已访问或未访问节点，朴素递归会在环上死循环或重复复制。
>
> 用哈希表记下原节点到副本的映射：先建副本并写入表，再递归左、右、$\textit{random}$。已复制的节点直接返回映射，保证同一原节点只生成一份。

<!-- thinking:end -->

我们用哈希表 $\textit{seen}$ 记录原树中每个节点与其拷贝节点的对应关系，然后进行深度优先搜索。

定义函数 $\text{dfs}(root)$，返回节点 $root$ 的拷贝。过程如下：

- 若 $root$ 为空，返回空；
- 若 $root$ 已在 $\textit{seen}$ 中，返回 $\textit{seen}[root]$；
- 否则创建拷贝节点 $\textit{copy}$，令 $\textit{seen}[root] = \textit{copy}$，再分别递归处理 $root$ 的左子节点、右子节点和 $\textit{random}$ 指针；
- 最后返回 $\textit{copy}$。

主函数返回 $\text{dfs}(root)$。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 是节点数量。

<!-- tabs:start -->

#### Python3

```python
# Definition for Node.
# class Node:
#     def __init__(self, val=0, left=None, right=None, random=None):
#         self.val = val
#         self.left = left
#         self.right = right
#         self.random = random


class Solution:
    def copyRandomBinaryTree(self, root: "Optional[Node]") -> "Optional[NodeCopy]":
        def dfs(root: Optional[Node]) -> Optional[NodeCopy]:
            if root is None:
                return None
            if root in seen:
                return seen[root]
            copy = NodeCopy(root.val)
            seen[root] = copy
            copy.left = dfs(root.left)
            copy.right = dfs(root.right)
            copy.random = dfs(root.random)
            return copy

        seen = {}
        return dfs(root)
```

#### Java

```java
/**
 * Definition for Node.
 * public class Node {
 *     int val;
 *     Node left;
 *     Node right;
 *     Node random;
 *     Node() {}
 *     Node(int val) { this.val = val; }
 *     Node(int val, Node left, Node right, Node random) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *         this.random = random;
 *     }
 * }
 */

class Solution {
    private Map<Node, NodeCopy> seen;

    public NodeCopy copyRandomBinaryTree(Node root) {
        seen = new HashMap<>();
        return dfs(root);
    }

    private NodeCopy dfs(Node root) {
        if (root == null) {
            return null;
        }
        if (seen.containsKey(root)) {
            return seen.get(root);
        }
        NodeCopy copy = new NodeCopy(root.val);
        seen.put(root, copy);
        copy.left = dfs(root.left);
        copy.right = dfs(root.right);
        copy.random = dfs(root.random);
        return copy;
    }
}
```

#### C++

```cpp
/**
 * Definition for a Node.
 * struct Node {
 *     int val;
 *     Node *left;
 *     Node *right;
 *     Node *random;
 *     Node() : val(0), left(nullptr), right(nullptr), random(nullptr) {}
 *     Node(int x) : val(x), left(nullptr), right(nullptr), random(nullptr) {}
 *     Node(int x, Node *left, Node *right, Node *random) : val(x), left(left), right(right), random(random) {}
 * };
 */

class Solution {
public:
    NodeCopy* copyRandomBinaryTree(Node* root) {
        unordered_map<Node*, NodeCopy*> seen;
        auto dfs = [&](this auto&& dfs, Node* root) -> NodeCopy* {
            if (!root) {
                return nullptr;
            }
            if (seen.contains(root)) {
                return seen[root];
            }
            NodeCopy* copy = new NodeCopy(root->val);
            seen[root] = copy;
            copy->left = dfs(root->left);
            copy->right = dfs(root->right);
            copy->random = dfs(root->random);
            return copy;
        };
        return dfs(root);
    }
};
```

#### Go

```go
/**
 * Definition for a Node.
 * type Node struct {
 *     Val int
 *     Left *Node
 *     Right *Node
 *     Random *Node
 * }
 */

func copyRandomBinaryTree(root *Node) *NodeCopy {
	seen := make(map[*Node]*NodeCopy)
	var dfs func(root *Node) *NodeCopy
	dfs = func(root *Node) *NodeCopy {
		if root == nil {
			return nil
		}
		if v, ok := seen[root]; ok {
			return v
		}
		copy := &NodeCopy{Val: root.Val}
		seen[root] = copy
		copy.Left = dfs(root.Left)
		copy.Right = dfs(root.Right)
		copy.Random = dfs(root.Random)
		return copy
	}
	return dfs(root)
}
```

#### TypeScript

```ts
/**
 * Definition for Node.
 * class Node {
 *     val: number
 *     left: Node | null
 *     right: Node | null
 *     random: Node | null
 *     constructor(val?: number, left?: Node | null, right?: Node | null, random?: Node | null) {
 *         this.val = (val===undefined ? 0 : val)
 *         this.left = (left===undefined ? null : left)
 *         this.right = (right===undefined ? null : right)
 *         this.random = (random===undefined ? null : random)
 *     }
 * }
 */

function copyRandomBinaryTree(root: Node | null): NodeCopy | null {
    const seen = new Map<Node, NodeCopy>();
    const dfs = (root: Node | null): NodeCopy | null => {
        if (root === null) {
            return null;
        }
        if (seen.has(root)) {
            return seen.get(root)!;
        }
        const copy = new NodeCopy(root.val);
        seen.set(root, copy);
        copy.left = dfs(root.left);
        copy.right = dfs(root.right);
        copy.random = dfs(root.random);
        return copy;
    };
    return dfs(root);
}
```

#### C#

```cs
/*
// Definition for a Node.
public class Node {
    public int val;
    public Node left;
    public Node right;
    public Node random;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _random) {
        val = _val;
        left = _left;
        right = _right;
        random = _random;
    }
}
*/

public class Solution {
    public NodeCopy CopyRandomBinaryTree(Node root) {
        var seen = new Dictionary<Node, NodeCopy>();
        NodeCopy Dfs(Node root) {
            if (root == null) {
                return null;
            }
            if (seen.ContainsKey(root)) {
                return seen[root];
            }
            var copy = new NodeCopy(root.val);
            seen[root] = copy;
            copy.left = Dfs(root.left);
            copy.right = Dfs(root.right);
            copy.random = Dfs(root.random);
            return copy;
        }
        return Dfs(root);
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
