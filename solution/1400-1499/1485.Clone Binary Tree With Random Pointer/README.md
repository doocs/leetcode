---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1400-1499/1485.Clone%20Binary%20Tree%20With%20Random%20Pointer/README.md
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

### 方法一

<!-- tabs:start -->

```python
# Definition for Node.
# class Node:
#     def __init__(self, val=0, left=None, right=None, random=None):
#         self.val = val
#         self.left = left
#         self.right = right
#         self.random = random


class Solution:
    def copyRandomBinaryTree(self, root: 'Optional[Node]') -> 'Optional[NodeCopy]':
        def dfs(root):
            if root is None:
                return None
            if root in mp:
                return mp[root]
            copy = NodeCopy(root.val)
            mp[root] = copy
            copy.left = dfs(root.left)
            copy.right = dfs(root.right)
            copy.random = dfs(root.random)
            return copy

        mp = {}
        return dfs(root)
```

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
    private Map<Node, NodeCopy> mp;

    public NodeCopy copyRandomBinaryTree(Node root) {
        mp = new HashMap<>();
        return dfs(root);
    }

    private NodeCopy dfs(Node root) {
        if (root == null) {
            return null;
        }
        if (mp.containsKey(root)) {
            return mp.get(root);
        }
        NodeCopy copy = new NodeCopy(root.val);
        mp.put(root, copy);
        copy.left = dfs(root.left);
        copy.right = dfs(root.right);
        copy.random = dfs(root.random);
        return copy;
    }
}
```

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
        unordered_map<Node*, NodeCopy*> mp;
        return dfs(root, mp);
    }

    NodeCopy* dfs(Node* root, unordered_map<Node*, NodeCopy*>& mp) {
        if (!root) return nullptr;
        if (mp.count(root)) return mp[root];
        NodeCopy* copy = new NodeCopy(root->val);
        mp[root] = copy;
        copy->left = dfs(root->left, mp);
        copy->right = dfs(root->right, mp);
        copy->random = dfs(root->random, mp);
        return copy;
    }
};
```

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
	mp := make(map[*Node]*NodeCopy)
	var dfs func(root *Node) *NodeCopy
	dfs = func(root *Node) *NodeCopy {
		if root == nil {
			return nil
		}
		if v, ok := mp[root]; ok {
			return v
		}
		copy := &NodeCopy{Val: root.Val}
		mp[root] = copy
		copy.Left = dfs(root.Left)
		copy.Right = dfs(root.Right)
		copy.Random = dfs(root.Random)
		return copy
	}
	return dfs(root)
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
