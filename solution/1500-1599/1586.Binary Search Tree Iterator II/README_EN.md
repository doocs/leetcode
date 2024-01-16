# [1586. Binary Search Tree Iterator II](https://leetcode.com/problems/binary-search-tree-iterator-ii)

[中文文档](/solution/1500-1599/1586.Binary%20Search%20Tree%20Iterator%20II/README.md)

## Description

<p>Implement the <code>BSTIterator</code> class that represents an iterator over the <strong><a href="https://en.wikipedia.org/wiki/Tree_traversal#In-order_(LNR)">in-order traversal</a></strong> of a binary search tree (BST):</p>

<ul>
	<li><code>BSTIterator(TreeNode root)</code> Initializes an object of the <code>BSTIterator</code> class. The <code>root</code> of the BST is given as part of the constructor. The pointer should be initialized to a non-existent number smaller than any element in the BST.</li>
	<li><code>boolean hasNext()</code> Returns <code>true</code> if there exists a number in the traversal to the right of the pointer, otherwise returns <code>false</code>.</li>
	<li><code>int next()</code> Moves the pointer to the right, then returns the number at the pointer.</li>
	<li><code>boolean hasPrev()</code> Returns <code>true</code> if there exists a number in the traversal to the left of the pointer, otherwise returns <code>false</code>.</li>
	<li><code>int prev()</code> Moves the pointer to the left, then returns the number at the pointer.</li>
</ul>

<p>Notice that by initializing the pointer to a non-existent smallest number, the first call to <code>next()</code> will return the smallest element in the BST.</p>

<p>You may assume that <code>next()</code> and <code>prev()</code> calls will always be valid. That is, there will be at least a next/previous number in the in-order traversal when <code>next()</code>/<code>prev()</code> is called.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1500-1599/1586.Binary%20Search%20Tree%20Iterator%20II/images/untitled-diagram-1.png" style="width: 201px; height: 201px;" /></strong></p>

<pre>
<strong>Input</strong>
[&quot;BSTIterator&quot;, &quot;next&quot;, &quot;next&quot;, &quot;prev&quot;, &quot;next&quot;, &quot;hasNext&quot;, &quot;next&quot;, &quot;next&quot;, &quot;next&quot;, &quot;hasNext&quot;, &quot;hasPrev&quot;, &quot;prev&quot;, &quot;prev&quot;]
[[[7, 3, 15, null, null, 9, 20]], [null], [null], [null], [null], [null], [null], [null], [null], [null], [null], [null], [null]]
<strong>Output</strong>
[null, 3, 7, 3, 7, true, 9, 15, 20, false, true, 15, 9]

<strong>Explanation</strong>
// The underlined element is where the pointer currently is.
BSTIterator bSTIterator = new BSTIterator([7, 3, 15, null, null, 9, 20]); // state is <u> </u> [3, 7, 9, 15, 20]
bSTIterator.next(); // state becomes [<u>3</u>, 7, 9, 15, 20], return 3
bSTIterator.next(); // state becomes [3, <u>7</u>, 9, 15, 20], return 7
bSTIterator.prev(); // state becomes [<u>3</u>, 7, 9, 15, 20], return 3
bSTIterator.next(); // state becomes [3, <u>7</u>, 9, 15, 20], return 7
bSTIterator.hasNext(); // return true
bSTIterator.next(); // state becomes [3, 7, <u>9</u>, 15, 20], return 9
bSTIterator.next(); // state becomes [3, 7, 9, <u>15</u>, 20], return 15
bSTIterator.next(); // state becomes [3, 7, 9, 15, <u>20</u>], return 20
bSTIterator.hasNext(); // return false
bSTIterator.hasPrev(); // return true
bSTIterator.prev(); // state becomes [3, 7, 9, <u>15</u>, 20], return 15
bSTIterator.prev(); // state becomes [3, 7, <u>9</u>, 15, 20], return 9
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li>The number of nodes in the tree is in the range <code>[1, 10<sup>5</sup>]</code>.</li>
	<li><code>0 &lt;= Node.val &lt;= 10<sup>6</sup></code></li>
	<li>At most <code>10<sup>5</sup></code> calls will be made to <code>hasNext</code>, <code>next</code>, <code>hasPrev</code>, and <code>prev</code>.</li>
</ul>

<p>&nbsp;</p>
<strong>Follow up:</strong> Could you solve the problem without precalculating the values of the tree?

## Solutions

### Solution 1: In-order Traversal + Array

We can use in-order traversal to store the values of all nodes in the binary search tree into an array $nums$, and then use the array to implement the iterator. We define a pointer $i$, initially $i = -1$, which points to an element in the array $nums$. Each time we call $next()$, we add $1$ to the value of $i$ and return $nums[i]$; each time we call $prev()$, we subtract $1$ from the value of $i$ and return $nums[i]$.

In terms of time complexity, initializing the iterator requires $O(n)$ time, where $n$ is the number of nodes in the binary search tree. Each call to $next()$ and $prev()$ requires $O(1)$ time. In terms of space complexity, we need $O(n)$ space to store the values of all nodes in the binary search tree.

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

<!-- end -->
