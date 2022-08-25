# [654. 最大二叉树](https://leetcode.cn/problems/maximum-binary-tree)

[English Version](/solution/0600-0699/0654.Maximum%20Binary%20Tree/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个不重复的整数数组&nbsp;<code>nums</code> 。&nbsp;<strong>最大二叉树</strong>&nbsp;可以用下面的算法从&nbsp;<code>nums</code> 递归地构建:</p>

<ol>
	<li>创建一个根节点，其值为&nbsp;<code>nums</code> 中的最大值。</li>
	<li>递归地在最大值&nbsp;<strong>左边</strong>&nbsp;的&nbsp;<strong>子数组前缀上</strong>&nbsp;构建左子树。</li>
	<li>递归地在最大值 <strong>右边</strong> 的&nbsp;<strong>子数组后缀上</strong>&nbsp;构建右子树。</li>
</ol>

<p>返回&nbsp;<em><code>nums</code> 构建的 </em><strong><em>最大二叉树</em> </strong>。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0600-0699/0654.Maximum%20Binary%20Tree/images/tree1.jpg" />
<pre>
<strong>输入：</strong>nums = [3,2,1,6,0,5]
<strong>输出：</strong>[6,3,5,null,2,0,null,null,1]
<strong>解释：</strong>递归调用如下所示：
- [3,2,1,6,0,5] 中的最大值是 6 ，左边部分是 [3,2,1] ，右边部分是 [0,5] 。
    - [3,2,1] 中的最大值是 3 ，左边部分是 [] ，右边部分是 [2,1] 。
        - 空数组，无子节点。
        - [2,1] 中的最大值是 2 ，左边部分是 [] ，右边部分是 [1] 。
            - 空数组，无子节点。
            - 只有一个元素，所以子节点是一个值为 1 的节点。
    - [0,5] 中的最大值是 5 ，左边部分是 [0] ，右边部分是 [] 。
        - 只有一个元素，所以子节点是一个值为 0 的节点。
        - 空数组，无子节点。
</pre>

<p><strong>示例 2：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0600-0699/0654.Maximum%20Binary%20Tree/images/tree2.jpg" />
<pre>
<strong>输入：</strong>nums = [3,2,1]
<strong>输出：</strong>[3,null,2,null,1]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 1000</code></li>
	<li><code>0 &lt;= nums[i] &lt;= 1000</code></li>
	<li><code>nums</code> 中的所有整数 <strong>互不相同</strong></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：递归**

先找到数组 $nums$ 的最大元素所在的位置 $i$，将 $nums[i]$ 作为根节点，然后递归左右两侧的子数组，构建左右子树。

时间复杂度 $O(n^2)$，空间复杂度 $O(n)$，其中 $n$ 是数组的长度。

**方法二：线段树**

方法一中，每次查找区间最大值，需要 $O(n)$ 的时间，我们可以借助线段树，将每次查询区间最大值的时间降至 $O(logn)$。

最多需要查询 $n$ 次，因此，总的时间复杂度为 $O(nlogn)$，空间复杂度 $O(n)$，其中 $n$ 是数组的长度。

**方法三：单调栈**

题目表达了一个意思：如果 $nums$ 中间有一个数字 $v$，找出它左右两侧最大的数，这两个最大的数应该比 $v$ 小。

了解单调栈的朋友，或许会注意到：

当我们尝试向栈中压入一个数字 $v$ 时，如果栈顶元素比 $v$ 小，则循环弹出栈顶元素，并记录最后一个弹出的元素 $last$。那么循环结束，$last$ 必须位于 $v$ 的左侧，因为 $last$ 是 $v$ 的左侧最大的数。令 $node(val=v).left$ 指向 $last$。

如果此时存在栈顶元素，栈顶元素一定大于 $v$。$v$ 成为栈顶元素的候选右子树节点。令 $stk.top().right$ 指向 $v$。然后 $v$ 入栈。

遍历结束，栈底元素成为树的根节点。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def constructMaximumBinaryTree(self, nums: List[int]) -> Optional[TreeNode]:
        def dfs(nums):
            if not nums:
                return None
            val = max(nums)
            i = nums.index(val)
            root = TreeNode(val)
            root.left = dfs(nums[:i])
            root.right = dfs(nums[i + 1:])
            return root

        return dfs(nums)
```

```python
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def constructMaximumBinaryTree(self, nums: List[int]) -> Optional[TreeNode]:
        def dfs(l, r):
            if l > r:
                return None
            val = tree.query(1, l, r)
            root = TreeNode(val)
            root.left = dfs(l, d[val] - 1)
            root.right = dfs(d[val] + 1, r)
            return root

        d = {v: i for i, v in enumerate(nums, 1)}
        tree = SegmentTree(nums)
        return dfs(1, len(nums))


class Node:
    def __init__(self):
        self.l = 0
        self.r = 0
        self.v = 0


class SegmentTree:
    def __init__(self, nums):
        self.nums = nums
        n = len(nums)
        self.tr = [Node() for _ in range(n << 2)]
        self.build(1, 1, n)

    def build(self, u, l, r):
        self.tr[u].l, self.tr[u].r = l, r
        if l == r:
            self.tr[u].v = self.nums[l - 1]
            return
        mid = (l + r) >> 1
        self.build(u << 1, l, mid)
        self.build(u << 1 | 1, mid + 1, r)
        self.pushup(u)

    def query(self, u, l, r):
        if self.tr[u].l >= l and self.tr[u].r <= r:
            return self.tr[u].v
        mid = (self.tr[u].l + self.tr[u].r) >> 1
        v = 0
        if l <= mid:
            v = max(v, self.query(u << 1, l, r))
        if r > mid:
            v = max(v, self.query(u << 1 | 1, l, r))
        return v

    def pushup(self, u):
        self.tr[u].v = max(self.tr[u << 1].v, self.tr[u << 1 | 1].v)
```

```python
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def constructMaximumBinaryTree(self, nums: List[int]) -> Optional[TreeNode]:
        stk = []
        for v in nums:
            node = TreeNode(v)
            last = None
            while stk and stk[-1].val < v:
                last = stk.pop()
            node.left = last
            if stk:
                stk[-1].right = node
            stk.append(node)
        return stk[0]
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
    private int[] nums;

    public TreeNode constructMaximumBinaryTree(int[] nums) {
        this.nums = nums;
        return dfs(0, nums.length - 1);
    }

    private TreeNode dfs(int l, int r) {
        if (l > r) {
            return null;
        }
        int i = l;
        for (int j = l; j <= r; ++j) {
            if (nums[i] < nums[j]) {
                i = j;
            }
        }
        TreeNode root = new TreeNode(nums[i]);
        root.left = dfs(l, i - 1);
        root.right = dfs(i + 1, r);
        return root;
    }
}
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
class Solution {
    private SegmentTree tree;
    private int[] nums;
    private static int[] d = new int[1010];

    public TreeNode constructMaximumBinaryTree(int[] nums) {
        int n = nums.length;
        this.nums = nums;
        tree = new SegmentTree(nums);
        for (int i = 0; i < n; ++i) {
            d[nums[i]] = i + 1;
        }
        return dfs(1, n);
    }

    private TreeNode dfs(int l, int r) {
        if (l > r) {
            return null;
        }
        int val = tree.query(1, l, r);
        TreeNode root = new TreeNode(val);
        root.left = dfs(l, d[val] - 1);
        root.right = dfs(d[val] + 1, r);
        return root;
    }
}

class Node {
    int l;
    int r;
    int v;
}

class SegmentTree {
    Node[] tr;
    int[] nums;

    public SegmentTree(int[] nums) {
        int n = nums.length;
        this.nums = nums;
        tr = new Node[n << 2];
        for (int i = 0; i < tr.length; ++i) {
            tr[i] = new Node();
        }
        build(1, 1, n);
    }

    private void build(int u, int l, int r) {
        tr[u].l = l;
        tr[u].r = r;
        if (l == r) {
            tr[u].v = nums[l - 1];
            return;
        }
        int mid = (l + r) >> 1;
        build(u << 1, l, mid);
        build(u << 1 | 1, mid + 1, r);
        pushup(u);
    }

    public int query(int u, int l, int r) {
        if (tr[u].l >= l && tr[u].r <= r) {
            return tr[u].v;
        }
        int mid = (tr[u].l + tr[u].r) >> 1;
        int v = 0;
        if (l <= mid) {
            v = query(u << 1, l, r);
        }
        if (r > mid) {
            v = Math.max(v, query(u << 1 | 1, l, r));
        }
        return v;
    }

    private void pushup(int u) {
        tr[u].v = Math.max(tr[u << 1].v, tr[u << 1 | 1].v);
    }
}
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
class Solution {
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        Deque<TreeNode> stk = new ArrayDeque<>();
        for (int v : nums) {
            TreeNode node = new TreeNode(v);
            TreeNode last = null;
            while (!stk.isEmpty() && stk.peek().val < v) {
                last = stk.pop();
            }
            node.left = last;
            if (!stk.isEmpty()) {
                stk.peek().right = node;
            }
            stk.push(node);
        }
        return stk.getLast();
    }
}
```

### **C++**

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
    TreeNode* constructMaximumBinaryTree(vector<int>& nums) {
        return dfs(nums, 0, nums.size() - 1);
    }

    TreeNode* dfs(vector<int>& nums, int l, int r) {
        if (l > r) return nullptr;
        int i = l;
        for (int j = l; j <= r; ++j) {
            if (nums[i] < nums[j]) {
                i = j;
            }
        }
        TreeNode* root = new TreeNode(nums[i]);
        root->left = dfs(nums, l, i - 1);
        root->right = dfs(nums, i + 1, r);
        return root;
    }
};
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
class Node {
public:
    int l, r, v;
};

class SegmentTree {
public:
    vector<Node*> tr;
    vector<int> nums;

    SegmentTree(vector<int>& nums) {
        this->nums = nums;
        int n = nums.size();
        tr.resize(n << 2);
        for (int i = 0; i < tr.size(); ++i) tr[i] = new Node();
        build(1, 1, n);
    }

    void build(int u, int l, int r) {
        tr[u]->l = l;
        tr[u]->r = r;
        if (l == r) {
            tr[u]->v = nums[l - 1];
            return;
        }
        int mid = (l + r) >> 1;
        build(u << 1, l, mid);
        build(u << 1 | 1, mid + 1, r);
        pushup(u);
    }

    int query(int u, int l, int r) {
        if (tr[u]->l >= l && tr[u]->r <= r) return tr[u]->v;
        int mid = (tr[u]->l + tr[u]->r) >> 1;
        int v = 0;
        if (l <= mid) v = query(u << 1, l, r);
        if (r > mid) v = max(v, query(u << 1 | 1, l, r));
        return v;
    }

    void pushup(int u) {
        tr[u]->v = max(tr[u << 1]->v, tr[u << 1 | 1]->v);
    }
};

class Solution {
public:
    SegmentTree* tree;
    vector<int> nums;
    vector<int> d;

    TreeNode* constructMaximumBinaryTree(vector<int>& nums) {
        tree = new SegmentTree(nums);
        this->nums = nums;
        d.assign(1010, 0);
        int n = nums.size();
        for (int i = 0; i < n; ++i) d[nums[i]] = i + 1;
        return dfs(1, nums.size());
    }

    TreeNode* dfs(int l, int r) {
        if (l > r) {
            return nullptr;
        }
        int val = tree->query(1, l, r);
        TreeNode* root = new TreeNode(val);
        root->left = dfs(l, d[val] - 1);
        root->right = dfs(d[val] + 1, r);
        return root;
    }
};
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
class Solution {
public:
    TreeNode* constructMaximumBinaryTree(vector<int>& nums) {
        stack<TreeNode*> stk;
        for (int v : nums) {
            TreeNode* node = new TreeNode(v);
            TreeNode* last = nullptr;
            while (!stk.empty() && stk.top()->val < v) {
                last = stk.top();
                stk.pop();
            }
            node->left = last;
            if (!stk.empty()) {
                stk.top()->right = node;
            }
            stk.push(node);
        }
        while (stk.size() > 1) {
            stk.pop();
        }
        return stk.top();
    }
};
```

### **Go**

```go
/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func constructMaximumBinaryTree(nums []int) *TreeNode {
    var dfs func(l, r int) *TreeNode
    dfs = func(l, r int) *TreeNode {
        if l > r {
            return nil
        }
        i := l
        for j := l; j <= r; j++ {
            if nums[i] < nums[j] {
                i = j
            }
        }
        root := &TreeNode{Val: nums[i]}
        root.Left = dfs(l, i - 1)
        root.Right = dfs(i + 1, r)
        return root
    }
    return dfs(0, len(nums)-1)
}
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
func constructMaximumBinaryTree(nums []int) *TreeNode {
	d := make([]int, 1010)
	for i, v := range nums {
		d[v] = i + 1
	}
	tree := newSegmentTree(nums)
	var dfs func(l, r int) *TreeNode
	dfs = func(l, r int) *TreeNode {
		if l > r {
			return nil
		}
		val := tree.query(1, l, r)
		root := &TreeNode{Val: val}
		root.Left = dfs(l, d[val]-1)
		root.Right = dfs(d[val]+1, r)
		return root
	}

	return dfs(1, len(nums))
}

type node struct {
	l int
	r int
	v int
}

type segmentTree struct {
	nums []int
	tr   []*node
}

func newSegmentTree(nums []int) *segmentTree {
	n := len(nums)
	tr := make([]*node, n<<2)
	for i := range tr {
		tr[i] = &node{}
	}
	t := &segmentTree{nums, tr}
	t.build(1, 1, n)
	return t
}

func (t *segmentTree) build(u, l, r int) {
	t.tr[u].l, t.tr[u].r = l, r
	if l == r {
		t.tr[u].v = t.nums[l-1]
		return
	}
	mid := (l + r) >> 1
	t.build(u<<1, l, mid)
	t.build(u<<1|1, mid+1, r)
	t.pushup(u)
}

func (t *segmentTree) query(u, l, r int) int {
	if t.tr[u].l >= l && t.tr[u].r <= r {
		return t.tr[u].v
	}
	mid := (t.tr[u].l + t.tr[u].r) >> 1
	v := 0
	if l <= mid {
		v = t.query(u<<1, l, r)
	}
	if r > mid {
		v = max(v, t.query(u<<1|1, l, r))
	}
	return v
}

func (t *segmentTree) pushup(u int) {
	t.tr[u].v = max(t.tr[u<<1].v, t.tr[u<<1|1].v)
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
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
func constructMaximumBinaryTree(nums []int) *TreeNode {
	stk := []*TreeNode{}
	for _, v := range nums {
		node := &TreeNode{Val: v}
		var last *TreeNode
		for len(stk) > 0 && stk[len(stk)-1].Val < v {
			last = stk[len(stk)-1]
			stk = stk[:len(stk)-1]
		}
		node.Left = last
		if len(stk) > 0 {
			stk[len(stk)-1].Right = node
		}
		stk = append(stk, node)
	}
	return stk[0]
}
```

### **C**

```c
/**
 * Definition for a binary tree node.
 * struct TreeNode {
 *     int val;
 *     struct TreeNode *left;
 *     struct TreeNode *right;
 * };
 */

struct TreeNode* construct(int* nums, int start, int end) {
    if (start >= end) {
        return NULL;
    }
    int idx = 0;
    int maxVal = -1;
    for (int i = start; i < end; i++) {
        if (nums[i] > maxVal) {
            idx = i;
            maxVal = nums[i];
        }
    }
    struct TreeNode* res = (struct TreeNode*)malloc(sizeof(struct TreeNode));
    res->val = maxVal;
    res->left = construct(nums, start, idx);
    res->right = construct(nums, idx + 1, end);
    return res;
}

struct TreeNode* constructMaximumBinaryTree(int* nums, int numsSize) {
    return construct(nums, 0, numsSize);
}
```

### **TypeScript**

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

function constructMaximumBinaryTree(nums: number[]): TreeNode | null {
    const n = nums.length;
    if (n === 0) {
        return null;
    }
    const [val, i] = nums.reduce((r, v, i) => (r[0] < v ? [v, i] : r), [-1, 0]);
    return new TreeNode(
        val,
        constructMaximumBinaryTree(nums.slice(0, i)),
        constructMaximumBinaryTree(nums.slice(i + 1)),
    );
}
```

### **Rust**

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
use std::rc::Rc;
use std::cell::RefCell;
impl Solution {
    fn construct(nums: &Vec<i32>, start: usize, end: usize) -> Option<Rc<RefCell<TreeNode>>> {
        if start >= end {
            return None;
        }
        let mut idx = 0;
        let mut max_val = -1;
        for i in start..end {
            if nums[i] > max_val {
                idx = i;
                max_val = nums[i];
            }
        }
        Some(Rc::new(RefCell::new(TreeNode {
            val: max_val,
            left: Self::construct(nums, start, idx),
            right: Self::construct(nums, idx + 1, end),
        })))
    }

    pub fn construct_maximum_binary_tree(nums: Vec<i32>) -> Option<Rc<RefCell<TreeNode>>> {
        Self::construct(&nums, 0, nums.len())
    }
}
```

### **...**

```

```

<!-- tabs:end -->
