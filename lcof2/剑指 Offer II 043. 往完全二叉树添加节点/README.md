# [剑指 Offer II 043. 往完全二叉树添加节点](https://leetcode.cn/problems/NaqhDT)

## 题目描述

<!-- 这里写题目描述 -->

<p>完全二叉树是每一层（除最后一层外）都是完全填充（即，节点数达到最大，第 <code>n</code> 层有 <code>2<sup>n-1</sup></code>&nbsp;个节点）的，并且所有的节点都尽可能地集中在左侧。</p>

<p>设计一个用完全二叉树初始化的数据结构&nbsp;<code>CBTInserter</code>，它支持以下几种操作：</p>

<ul>
	<li><code>CBTInserter(TreeNode root)</code>&nbsp;使用根节点为&nbsp;<code>root</code>&nbsp;的给定树初始化该数据结构；</li>
	<li><code>CBTInserter.insert(int v)</code>&nbsp; 向树中插入一个新节点，节点类型为 <code>TreeNode</code>，值为 <code>v</code> 。使树保持完全二叉树的状态，<strong>并返回插入的新节点的父节点的值</strong>；</li>
	<li><code>CBTInserter.get_root()</code> 将返回树的根节点。</li>
</ul>

<p>&nbsp;</p>

<ol>
</ol>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>inputs = [&quot;CBTInserter&quot;,&quot;insert&quot;,&quot;get_root&quot;], inputs = [[[1]],[2],[]]
<strong>输出：</strong>[null,1,[1,2]]
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>inputs = [&quot;CBTInserter&quot;,&quot;insert&quot;,&quot;insert&quot;,&quot;get_root&quot;], inputs = [[[1,2,3,4,5,6]],[7],[8],[]]
<strong>输出：</strong>[null,3,4,[1,2,3,4,5,6,7,8]]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li>最初给定的树是完全二叉树，且包含&nbsp;<code>1</code>&nbsp;到&nbsp;<code>1000</code>&nbsp;个节点。</li>
	<li>每个测试用例最多调用&nbsp;<code>CBTInserter.insert</code>&nbsp; 操作&nbsp;<code>10000</code>&nbsp;次。</li>
	<li>给定节点或插入节点的每个值都在&nbsp;<code>0</code>&nbsp;到&nbsp;<code>5000</code>&nbsp;之间。</li>
</ul>

<p>&nbsp;</p>

<p><meta charset="UTF-8" />注意：本题与主站 919&nbsp;题相同：&nbsp;<a href="https://leetcode.cn/problems/complete-binary-tree-inserter/">https://leetcode.cn/problems/complete-binary-tree-inserter/</a></p>

## 解法

### 方法一：BFS

我们可以使用一个数组 $tree$ 来存储完全二叉树的所有节点。在初始化时，我们使用一个队列 $q$ 来层序遍历给定的树，并将所有节点存储到数组 $tree$ 中。

在插入节点时，我们可以通过数组 $tree$ 来找到新节点的父节点 $p$。然后我们创建一个新节点 $node$，将其插入到数组 $tree$ 中，并将 $node$ 作为 $p$ 的左子节点或右子节点。最后返回 $p$ 的值。

在获取根节点时，我们直接返回数组 $tree$ 的第一个元素。

时间复杂度方面，初始化时需要 $O(n)$ 的时间，插入节点和获取根节点的时间复杂度均为 $O(1)$。空间复杂度为 $O(n)$。其中 $n$ 为树中节点的数量。

<!-- tabs:start -->

```python
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class CBTInserter:

    def __init__(self, root: Optional[TreeNode]):
        self.tree = []
        q = deque([root])
        while q:
            for _ in range(len(q)):
                node = q.popleft()
                self.tree.append(node)
                if node.left:
                    q.append(node.left)
                if node.right:
                    q.append(node.right)

    def insert(self, val: int) -> int:
        p = self.tree[(len(self.tree) - 1) // 2]
        node = TreeNode(val)
        self.tree.append(node)
        if p.left is None:
            p.left = node
        else:
            p.right = node
        return p.val

    def get_root(self) -> Optional[TreeNode]:
        return self.tree[0]


# Your CBTInserter object will be instantiated and called as such:
# obj = CBTInserter(root)
# param_1 = obj.insert(val)
# param_2 = obj.get_root()
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
class CBTInserter {
    private List<TreeNode> tree = new ArrayList<>();

    public CBTInserter(TreeNode root) {
        Deque<TreeNode> q = new ArrayDeque<>();
        q.offer(root);
        while (!q.isEmpty()) {
            for (int i = q.size(); i > 0; --i) {
                TreeNode node = q.poll();
                tree.add(node);
                if (node.left != null) {
                    q.offer(node.left);
                }
                if (node.right != null) {
                    q.offer(node.right);
                }
            }
        }
    }

    public int insert(int val) {
        TreeNode p = tree.get((tree.size() - 1) / 2);
        TreeNode node = new TreeNode(val);
        tree.add(node);
        if (p.left == null) {
            p.left = node;
        } else {
            p.right = node;
        }
        return p.val;
    }

    public TreeNode get_root() {
        return tree.get(0);
    }
}

/**
 * Your CBTInserter object will be instantiated and called as such:
 * CBTInserter obj = new CBTInserter(root);
 * int param_1 = obj.insert(val);
 * TreeNode param_2 = obj.get_root();
 */
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
class CBTInserter {
public:
    CBTInserter(TreeNode* root) {
        queue<TreeNode*> q{{root}};
        while (q.size()) {
            for (int i = q.size(); i; --i) {
                auto node = q.front();
                q.pop();
                tree.push_back(node);
                if (node->left) {
                    q.push(node->left);
                }
                if (node->right) {
                    q.push(node->right);
                }
            }
        }
    }

    int insert(int val) {
        auto p = tree[(tree.size() - 1) / 2];
        auto node = new TreeNode(val);
        tree.push_back(node);
        if (!p->left) {
            p->left = node;
        } else {
            p->right = node;
        }
        return p->val;
    }

    TreeNode* get_root() {
        return tree[0];
    }

private:
    vector<TreeNode*> tree;
};

/**
 * Your CBTInserter object will be instantiated and called as such:
 * CBTInserter* obj = new CBTInserter(root);
 * int param_1 = obj->insert(val);
 * TreeNode* param_2 = obj->get_root();
 */
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
type CBTInserter struct {
	tree []*TreeNode
}

func Constructor(root *TreeNode) CBTInserter {
	q := []*TreeNode{root}
	tree := []*TreeNode{}
	for len(q) > 0 {
		for i := len(q); i > 0; i-- {
			node := q[0]
			q = q[1:]
			tree = append(tree, node)
			if node.Left != nil {
				q = append(q, node.Left)
			}
			if node.Right != nil {
				q = append(q, node.Right)
			}
		}
	}
	return CBTInserter{tree}
}

func (this *CBTInserter) Insert(val int) int {
	p := this.tree[(len(this.tree)-1)/2]
	node := &TreeNode{val, nil, nil}
	this.tree = append(this.tree, node)
	if p.Left == nil {
		p.Left = node
	} else {
		p.Right = node
	}
	return p.Val
}

func (this *CBTInserter) Get_root() *TreeNode {
	return this.tree[0]
}

/**
 * Your CBTInserter object will be instantiated and called as such:
 * obj := Constructor(root);
 * param_1 := obj.Insert(val);
 * param_2 := obj.Get_root();
 */
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

class CBTInserter {
    private tree: TreeNode[] = [];

    constructor(root: TreeNode | null) {
        if (root === null) {
            return;
        }
        const q: TreeNode[] = [root];
        while (q.length > 0) {
            const t: TreeNode[] = [];
            for (const node of q) {
                this.tree.push(node);
                node.left !== null && t.push(node.left);
                node.right !== null && t.push(node.right);
            }
            q.splice(0, q.length, ...t);
        }
    }

    insert(val: number): number {
        const p = this.tree[(this.tree.length - 1) >> 1];
        const node = new TreeNode(val);
        this.tree.push(node);
        if (p.left === null) {
            p.left = node;
        } else {
            p.right = node;
        }
        return p.val;
    }

    get_root(): TreeNode | null {
        return this.tree[0];
    }
}

/**
 * Your CBTInserter object will be instantiated and called as such:
 * var obj = new CBTInserter(root)
 * var param_1 = obj.insert(val)
 * var param_2 = obj.get_root()
 */
```

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
 * @param {TreeNode} root
 */
var CBTInserter = function (root) {
    this.tree = [];
    if (root === null) {
        return;
    }
    const q = [root];
    while (q.length > 0) {
        const t = [];
        for (const node of q) {
            this.tree.push(node);
            node.left !== null && t.push(node.left);
            node.right !== null && t.push(node.right);
        }
        q.splice(0, q.length, ...t);
    }
};

/**
 * @param {number} val
 * @return {number}
 */
CBTInserter.prototype.insert = function (val) {
    const p = this.tree[(this.tree.length - 1) >> 1];
    const node = new TreeNode(val);
    this.tree.push(node);
    if (p.left === null) {
        p.left = node;
    } else {
        p.right = node;
    }
    return p.val;
};

/**
 * @return {TreeNode}
 */
CBTInserter.prototype.get_root = function () {
    return this.tree[0];
};

/**
 * Your CBTInserter object will be instantiated and called as such:
 * var obj = new CBTInserter(root)
 * var param_1 = obj.insert(val)
 * var param_2 = obj.get_root()
 */
```

<!-- tabs:end -->

<!-- end -->
