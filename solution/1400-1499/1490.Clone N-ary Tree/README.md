---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1400-1499/1490.Clone%20N-ary%20Tree/README.md
tags:
    - 树
    - 深度优先搜索
    - 广度优先搜索
    - 哈希表
---

<!-- problem:start -->

# [1490. 克隆 N 叉树 🔒](https://leetcode.cn/problems/clone-n-ary-tree)

[English Version](/solution/1400-1499/1490.Clone%20N-ary%20Tree/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定一棵 N 叉树的根节点&nbsp;<code>root</code>&nbsp;，返回该树的<a href="https://baike.baidu.com/item/深拷贝/22785317?fr=aladdin"><strong>深拷贝</strong></a>（克隆）。</p>

<p>N 叉树的每个节点都包含一个值（ <code>int</code>&nbsp;）和子节点的列表（ <code>List[Node]</code>&nbsp;）。</p>

<pre>
class Node {
    public int val;
    public List&lt;Node&gt; children;
}
</pre>

<p><em>N 叉树的输入序列用层序遍历表示，每组子节点用 null 分隔（见示例）。</em></p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1400-1499/1490.Clone%20N-ary%20Tree/images/narytreeexample.png" style="width:330px" /></p>

<pre>
<strong>输入：</strong>root = [1,null,3,2,4,null,5,6]
<strong>输出：</strong>[1,null,3,2,4,null,5,6]
</pre>

<p><strong>示例 2：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1400-1499/1490.Clone%20N-ary%20Tree/images/sample_4_964.png" style="height:241px; width:296px" /></p>

<pre>
<strong>输入：</strong>root = [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]
<strong>输出：</strong>[1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li>给定的 N 叉树的深度小于或等于&nbsp;<code>1000</code>。</li>
	<li>节点的总个数在&nbsp;<code>[0,&nbsp;10^4]</code>&nbsp;之间</li>
</ul>

<p>&nbsp;</p>

<p><strong>进阶：</strong>你的解决方案可以适用于<a href="https://leetcode.cn/problems/clone-graph/">克隆图</a>问题吗？</p>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：递归

我们可以用递归的方法来实现 N 叉树的深拷贝。

对于当前节点，如果为空，则返回空；否则，创建一个新节点，其值为当前节点的值，然后对当前节点的每个子节点递归调用该函数，将返回值作为新节点的子节点。最后返回新节点即可。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为 N 叉树的节点个数。

<!-- tabs:start -->

#### Python3

```python
"""
# Definition for a Node.
class Node:
    def __init__(self, val=None, children=None):
        self.val = val
        self.children = children if children is not None else []
"""


class Solution:
    def cloneTree(self, root: 'Node') -> 'Node':
        if root is None:
            return None
        children = [self.cloneTree(child) for child in root.children]
        return Node(root.val, children)
```

#### Java

```java
/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> children;


    public Node() {
        children = new ArrayList<Node>();
    }

    public Node(int _val) {
        val = _val;
        children = new ArrayList<Node>();
    }

    public Node(int _val,ArrayList<Node> _children) {
        val = _val;
        children = _children;
    }
};
*/

class Solution {
    public Node cloneTree(Node root) {
        if (root == null) {
            return null;
        }
        ArrayList<Node> children = new ArrayList<>();
        for (Node child : root.children) {
            children.add(cloneTree(child));
        }
        return new Node(root.val, children);
    }
}
```

#### C++

```cpp
/*
// Definition for a Node.
class Node {
public:
    int val;
    vector<Node*> children;

    Node() {}

    Node(int _val) {
        val = _val;
    }

    Node(int _val, vector<Node*> _children) {
        val = _val;
        children = _children;
    }
};
*/

class Solution {
public:
    Node* cloneTree(Node* root) {
        if (!root) {
            return root;
        }
        vector<Node*> children;
        for (Node* child : root->children) {
            children.emplace_back(cloneTree(child));
        }
        return new Node(root->val, children);
    }
};
```

#### Go

```go
/**
 * Definition for a Node.
 * type Node struct {
 *     Val int
 *     Children []*Node
 * }
 */

func cloneTree(root *Node) *Node {
	if root == nil {
		return nil
	}
	children := []*Node{}
	for _, child := range root.Children {
		children = append(children, cloneTree(child))
	}
	return &Node{root.Val, children}
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
