---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1500-1599/1586.Binary%20Search%20Tree%20Iterator%20II/README.md
tags:
    - 栈
    - 树
    - 设计
    - 二叉搜索树
    - 二叉树
    - 迭代器
---

<!-- problem:start -->

# [1586. 二叉搜索树迭代器 II 🔒](https://leetcode.cn/problems/binary-search-tree-iterator-ii)

[English Version](/solution/1500-1599/1586.Binary%20Search%20Tree%20Iterator%20II/README_EN.md)

## 题目描述

<!-- description:start -->

<p>实现二叉搜索树（BST）的<a href="https://baike.baidu.com/item/中序遍历/757281?fr=aladdin">中序遍历</a>迭代器&nbsp;<code>BSTIterator</code>&nbsp;类：</p>

<ul>
	<li><code>BSTIterator(TreeNode root)</code>&nbsp;初始化&nbsp;<code>BSTIterator</code>&nbsp;类的实例。二叉搜索树的根节点&nbsp;<code>root</code>&nbsp;作为构造函数的参数传入。内部指针使用一个不存在于树中且小于树中任意值的数值来初始化。</li>
	<li><code>boolean hasNext()</code>&nbsp;如果当前指针在中序遍历序列中，存在右侧数值，返回&nbsp;<code>true</code> ，否则返回&nbsp;<code>false</code>&nbsp;。</li>
	<li><code>int next()</code>&nbsp;将指针在中序遍历序列中向右移动，然后返回移动后指针所指数值。</li>
	<li><code>boolean hasPrev()</code>&nbsp;如果当前指针在中序遍历序列中，存在左侧数值，返回&nbsp;<code>true</code>&nbsp;，否则返回&nbsp;<code>false</code>&nbsp;。</li>
	<li><code>int prev()</code>&nbsp;将指针在中序遍历序列中向左移动，然后返回移动后指针所指数值。</li>
</ul>

<p>注意，虽然我们使用树中不存在的最小值来初始化内部指针，第一次调用&nbsp;<code>next()</code>&nbsp;需要返回二叉搜索树中最小的元素。</p>

<p>你可以假设&nbsp;<code>next()</code>&nbsp;和&nbsp;<code>prev()</code>&nbsp;的调用总是有效的。即，当&nbsp;<code>next()</code>/<code>prev()</code>&nbsp;被调用的时候，在中序遍历序列中一定存在下一个/上一个元素。</p>

<p><strong>进阶：</strong>你可以不提前遍历树中的值来解决问题吗？</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1500-1599/1586.Binary%20Search%20Tree%20Iterator%20II/images/untitled-diagram-1.png" style="height: 201px; width: 201px;"></strong></p>

<pre><strong>输入</strong>
[&quot;BSTIterator&quot;, &quot;next&quot;, &quot;next&quot;, &quot;prev&quot;, &quot;next&quot;, &quot;hasNext&quot;, &quot;next&quot;, &quot;next&quot;, &quot;next&quot;, &quot;hasNext&quot;, &quot;hasPrev&quot;, &quot;prev&quot;, &quot;prev&quot;]
[[[7, 3, 15, null, null, 9, 20]], [null], [null], [null], [null], [null], [null], [null], [null], [null], [null], [null], [null]]
<strong>输出</strong>
[null, 3, 7, 3, 7, true, 9, 15, 20, false, true, 15, 9]

<strong>解释</strong>
// 划线的元素表示指针当前的位置。
BSTIterator bSTIterator = new BSTIterator([7, 3, 15, null, null, 9, 20]); // 当前状态为 &lt;u&gt; &lt;/u&gt; [3, 7, 9, 15, 20]
bSTIterator.next(); // 状态变为 [&lt;u&gt;3&lt;/u&gt;, 7, 9, 15, 20], 返回 3
bSTIterator.next(); // 状态变为 [3, &lt;u&gt;7&lt;/u&gt;, 9, 15, 20], 返回 7
bSTIterator.prev(); // 状态变为 [&lt;u&gt;3&lt;/u&gt;, 7, 9, 15, 20], 返回 3
bSTIterator.next(); // 状态变为 [3, &lt;u&gt;7&lt;/u&gt;, 9, 15, 20], 返回 7
bSTIterator.hasNext(); // 返回 true
bSTIterator.next(); // 状态变为 [3, 7, &lt;u&gt;9&lt;/u&gt;, 15, 20], 返回 9
bSTIterator.next(); // 状态变为 [3, 7, 9, &lt;u&gt;15&lt;/u&gt;, 20], 返回 15
bSTIterator.next(); // 状态变为 [3, 7, 9, 15, &lt;u&gt;20&lt;/u&gt;], 返回 20
bSTIterator.hasNext(); // 返回 false
bSTIterator.hasPrev(); // 返回 true
bSTIterator.prev(); // 状态变为 [3, 7, 9, &lt;u&gt;15&lt;/u&gt;, 20], 返回 15
bSTIterator.prev(); // 状态变为 [3, 7, &lt;u&gt;9&lt;/u&gt;, 15, 20], 返回 9
</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li>树中节点个数的范围是&nbsp;<code>[1, 10<sup>5</sup>]</code>&nbsp;。</li>
	<li><code>0 &lt;= Node.val &lt;= 10<sup>6</sup></code></li>
	<li>最多调用&nbsp;10<sup>5</sup>&nbsp;次&nbsp;<code>hasNext</code>、&nbsp;<code>next</code>、&nbsp;<code>hasPrev</code>&nbsp;和&nbsp;<code>prev</code>&nbsp;。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：中序遍历 + 数组

我们可以使用中序遍历将二叉搜索树的所有节点的值存储到数组 $nums$ 中，然后使用数组实现迭代器。我们定义一个指针 $i$，初始时 $i = -1$，表示指向数组 $nums$ 中的一个元素。每次调用 $next()$ 时，我们将 $i$ 的值加 $1$，并返回 $nums[i]$；每次调用 $prev()$ 时，我们将 $i$ 的值减 $1$，并返回 $nums[i]$。

时间复杂度方面，初始化迭代器需要 $O(n)$ 的时间，其中 $n$ 是二叉搜索树的节点数。每次调用 $next()$ 和 $prev()$ 都需要 $O(1)$ 的时间。空间复杂度方面，我们需要 $O(n)$ 的空间存储二叉搜索树的所有节点的值。

<!-- tabs:start -->

```python
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class BSTIterator:
    def __init__(self, root: Optional[TreeNode]):
        self.nums = []

        def dfs(root):
            if root is None:
                return
            dfs(root.left)
            self.nums.append(root.val)
            dfs(root.right)

        dfs(root)
        self.i = -1

    def hasNext(self) -> bool:
        return self.i < len(self.nums) - 1

    def next(self) -> int:
        self.i += 1
        return self.nums[self.i]

    def hasPrev(self) -> bool:
        return self.i > 0

    def prev(self) -> int:
        self.i -= 1
        return self.nums[self.i]


# Your BSTIterator object will be instantiated and called as such:
# obj = BSTIterator(root)
# param_1 = obj.hasNext()
# param_2 = obj.next()
# param_3 = obj.hasPrev()
# param_4 = obj.prev()
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
class BSTIterator {
    private List<Integer> nums = new ArrayList<>();
    private int i = -1;

    public BSTIterator(TreeNode root) {
        dfs(root);
    }

    public boolean hasNext() {
        return i < nums.size() - 1;
    }

    public int next() {
        return nums.get(++i);
    }

    public boolean hasPrev() {
        return i > 0;
    }

    public int prev() {
        return nums.get(--i);
    }

    private void dfs(TreeNode root) {
        if (root == null) {
            return;
        }
        dfs(root.left);
        nums.add(root.val);
        dfs(root.right);
    }
}

/**
 * Your BSTIterator object will be instantiated and called as such:
 * BSTIterator obj = new BSTIterator(root);
 * boolean param_1 = obj.hasNext();
 * int param_2 = obj.next();
 * boolean param_3 = obj.hasPrev();
 * int param_4 = obj.prev();
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
class BSTIterator {
public:
    BSTIterator(TreeNode* root) {
        dfs(root);
        n = nums.size();
    }

    bool hasNext() {
        return i < n - 1;
    }

    int next() {
        return nums[++i];
    }

    bool hasPrev() {
        return i > 0;
    }

    int prev() {
        return nums[--i];
    }

private:
    vector<int> nums;
    int i = -1;
    int n;

    void dfs(TreeNode* root) {
        if (!root) {
            return;
        }
        dfs(root->left);
        nums.push_back(root->val);
        dfs(root->right);
    }
};

/**
 * Your BSTIterator object will be instantiated and called as such:
 * BSTIterator* obj = new BSTIterator(root);
 * bool param_1 = obj->hasNext();
 * int param_2 = obj->next();
 * bool param_3 = obj->hasPrev();
 * int param_4 = obj->prev();
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
type BSTIterator struct {
	nums []int
	i, n int
}

func Constructor(root *TreeNode) BSTIterator {
	nums := []int{}
	var dfs func(*TreeNode)
	dfs = func(root *TreeNode) {
		if root == nil {
			return
		}
		dfs(root.Left)
		nums = append(nums, root.Val)
		dfs(root.Right)
	}
	dfs(root)
	return BSTIterator{nums, -1, len(nums)}
}

func (this *BSTIterator) HasNext() bool {
	return this.i < this.n-1
}

func (this *BSTIterator) Next() int {
	this.i++
	return this.nums[this.i]
}

func (this *BSTIterator) HasPrev() bool {
	return this.i > 0
}

func (this *BSTIterator) Prev() int {
	this.i--
	return this.nums[this.i]
}

/**
 * Your BSTIterator object will be instantiated and called as such:
 * obj := Constructor(root);
 * param_1 := obj.HasNext();
 * param_2 := obj.Next();
 * param_3 := obj.HasPrev();
 * param_4 := obj.Prev();
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

class BSTIterator {
    private nums: number[];
    private n: number;
    private i: number;

    constructor(root: TreeNode | null) {
        this.nums = [];
        const dfs = (root: TreeNode | null) => {
            if (!root) {
                return;
            }
            dfs(root.left);
            this.nums.push(root.val);
            dfs(root.right);
        };
        dfs(root);
        this.n = this.nums.length;
        this.i = -1;
    }

    hasNext(): boolean {
        return this.i < this.n - 1;
    }

    next(): number {
        return this.nums[++this.i];
    }

    hasPrev(): boolean {
        return this.i > 0;
    }

    prev(): number {
        return this.nums[--this.i];
    }
}

/**
 * Your BSTIterator object will be instantiated and called as such:
 * var obj = new BSTIterator(root)
 * var param_1 = obj.hasNext()
 * var param_2 = obj.next()
 * var param_3 = obj.hasPrev()
 * var param_4 = obj.prev()
 */
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
