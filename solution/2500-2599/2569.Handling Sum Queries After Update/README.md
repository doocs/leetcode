# [2569. 更新数组后处理求和查询](https://leetcode.cn/problems/handling-sum-queries-after-update)

[English Version](/solution/2500-2599/2569.Handling%20Sum%20Queries%20After%20Update/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你两个下标从 <strong>0</strong>&nbsp;开始的数组&nbsp;<code>nums1</code> 和&nbsp;<code>nums2</code>&nbsp;，和一个二维数组&nbsp;<code>queries</code>&nbsp;表示一些操作。总共有 3 种类型的操作：</p>

<ol>
	<li>操作类型 1 为&nbsp;<code>queries[i]&nbsp;= [1, l, r]</code>&nbsp;。你需要将 <code>nums1</code>&nbsp;从下标&nbsp;<code>l</code>&nbsp;到下标 <code>r</code>&nbsp;的所有 <code>0</code>&nbsp;反转成 <code>1</code>&nbsp;或将 <code>1</code>&nbsp;反转成 <code>0</code>&nbsp;。<code>l</code>&nbsp;和 <code>r</code>&nbsp;下标都从 <strong>0</strong>&nbsp;开始。</li>
	<li>操作类型 2 为&nbsp;<code>queries[i]&nbsp;= [2, p, 0]</code>&nbsp;。对于&nbsp;<code>0 &lt;= i &lt; n</code>&nbsp;中的所有下标，令&nbsp;<code>nums2[i] =&nbsp;nums2[i]&nbsp;+ nums1[i]&nbsp;* p</code>&nbsp;。</li>
	<li>操作类型 3 为&nbsp;<code>queries[i]&nbsp;= [3, 0, 0]</code>&nbsp;。求&nbsp;<code>nums2</code>&nbsp;中所有元素的和。</li>
</ol>

<p>请你返回一个数组，包含所有第三种操作类型的答案。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<b>输入：</b>nums1 = [1,0,1], nums2 = [0,0,0], queries = [[1,1,1],[2,1,0],[3,0,0]]
<b>输出：</b>[3]
<strong>解释：</strong>第一个操作后 nums1 变为 [1,1,1] 。第二个操作后，nums2 变成 [1,1,1] ，所以第三个操作的答案为 3 。所以返回 [3] 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<b>输入：</b>nums1 = [1], nums2 = [5], queries = [[2,0,0],[3,0,0]]
<b>输出：</b>[5]
<b>解释：</b>第一个操作后，nums2 保持不变为 [5] ，所以第二个操作的答案是 5 。所以返回 [5] 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums1.length,nums2.length &lt;= 10<sup>5</sup></code></li>
	<li><code>nums1.length = nums2.length</code></li>
	<li><code>1 &lt;= queries.length &lt;= 10<sup>5</sup></code></li>
	<li><code>queries[i].length = 3</code></li>
	<li><code>0 &lt;= l &lt;= r &lt;= nums1.length - 1</code></li>
	<li><code>0 &lt;= p &lt;= 10<sup>6</sup></code></li>
	<li><code>0 &lt;= nums1[i] &lt;= 1</code></li>
	<li><code>0 &lt;= nums2[i] &lt;= 10<sup>9</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：线段树**

根据题目描述：

-   操作 $1$ 是把数组 `nums1` 的下标区间 $[l,..r]$ 的所有数反转，即把 $0$ 变成 $1$，把 $1$ 变成 $0$。
-   操作 $3$ 是求数组 `nums2` 的所有数之和。
-   操作 $2$ 是把数组 `nums2` 的所有数之和加上 $p$ 乘以数组 `nums1` 所有数之和，即 $sum(nums2) = sum(nums2) + p * sum(nums1)$。

因此，我们实际上只需要维护数组 `nums1` 的区间和即可，我们可以通过线段树来实现。

我们定义线段树的每个节点为 `Node`，每个节点包含如下属性：

-   `l`：节点的左端点，下标从 $1$ 开始。
-   `r`：节点的右端点，下标从 $1$ 开始。
-   `s`：节点的区间和。
-   `lazy`：节点的懒标记。

线段树主要有以下几个操作：

-   `build(u, l, r)`：建立线段树。
-   `pushdown(u)`：下传懒标记。
-   `pushup(u)`：用子节点的信息更新父节点的信息。
-   `modify(u, l, r)`：修改区间和，本题中是反转区间中的每个数，那么区间和 $s = r - l + 1 - s$。
-   `query(u, l, r)`：查询区间和。

我们先算出数组 `nums2` 的所有数之和，记为 $s$。

执行操作 $1$ 时，我们只需要调用 `modify(1, l + 1, r + 1)` 即可。

执行操作 $2$ 时，我们更新 $s = s + p \times query(1, 1, n)$ 即可。

执行操作 $3$ 时，我们只需要将 $s$ 加入答案数组即可。

时间复杂度 $O(n + m \times \log n)$，空间复杂度 $O(n)$。其中 $n$ 和 $m$ 分别为数组 `nums1` 和 `queries` 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Node:
    def __init__(self):
        self.l = self.r = 0
        self.s = self.lazy = 0


class SegmentTree:
    def __init__(self, nums):
        self.nums = nums
        n = len(nums)
        self.tr = [Node() for _ in range(n << 2)]
        self.build(1, 1, n)

    def build(self, u, l, r):
        self.tr[u].l, self.tr[u].r = l, r
        if l == r:
            self.tr[u].s = self.nums[l - 1]
            return
        mid = (l + r) >> 1
        self.build(u << 1, l, mid)
        self.build(u << 1 | 1, mid + 1, r)
        self.pushup(u)

    def modify(self, u, l, r):
        if self.tr[u].l >= l and self.tr[u].r <= r:
            self.tr[u].lazy ^= 1
            self.tr[u].s = self.tr[u].r - self.tr[u].l + 1 - self.tr[u].s
            return
        self.pushdown(u)
        mid = (self.tr[u].l + self.tr[u].r) >> 1
        if l <= mid:
            self.modify(u << 1, l, r)
        if r > mid:
            self.modify(u << 1 | 1, l, r)
        self.pushup(u)

    def query(self, u, l, r):
        if self.tr[u].l >= l and self.tr[u].r <= r:
            return self.tr[u].s
        self.pushdown(u)
        mid = (self.tr[u].l + self.tr[u].r) >> 1
        res = 0
        if l <= mid:
            res += self.query(u << 1, l, r)
        if r > mid:
            res += self.query(u << 1 | 1, l, r)
        return res

    def pushup(self, u):
        self.tr[u].s = self.tr[u << 1].s + self.tr[u << 1 | 1].s

    def pushdown(self, u):
        if self.tr[u].lazy:
            mid = (self.tr[u].l + self.tr[u].r) >> 1
            self.tr[u << 1].s = mid - self.tr[u].l + 1 - self.tr[u << 1].s
            self.tr[u << 1].lazy ^= 1
            self.tr[u << 1 | 1].s = self.tr[u].r - mid - self.tr[u << 1 | 1].s
            self.tr[u << 1 | 1].lazy ^= 1
            self.tr[u].lazy ^= 1


class Solution:
    def handleQuery(
        self, nums1: List[int], nums2: List[int], queries: List[List[int]]
    ) -> List[int]:
        tree = SegmentTree(nums1)
        s = sum(nums2)
        ans = []
        for op, a, b in queries:
            if op == 1:
                tree.modify(1, a + 1, b + 1)
            elif op == 2:
                s += a * tree.query(1, 1, len(nums1))
            else:
                ans.append(s)
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Node {
    int l, r;
    int s, lazy;
}

class SegmentTree {
    private Node[] tr;
    private int[] nums;

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
            tr[u].s = nums[l - 1];
            return;
        }
        int mid = (l + r) >> 1;
        build(u << 1, l, mid);
        build(u << 1 | 1, mid + 1, r);
        pushup(u);
    }

    public void modify(int u, int l, int r) {
        if (tr[u].l >= l && tr[u].r <= r) {
            tr[u].lazy ^= 1;
            tr[u].s = tr[u].r - tr[u].l + 1 - tr[u].s;
            return;
        }
        pushdown(u);
        int mid = (tr[u].l + tr[u].r) >> 1;
        if (l <= mid) {
            modify(u << 1, l, r);
        }
        if (r > mid) {
            modify(u << 1 | 1, l, r);
        }
        pushup(u);
    }

    public int query(int u, int l, int r) {
        if (tr[u].l >= l && tr[u].r <= r) {
            return tr[u].s;
        }
        pushdown(u);
        int mid = (tr[u].l + tr[u].r) >> 1;
        int res = 0;
        if (l <= mid) {
            res += query(u << 1, l, r);
        }
        if (r > mid) {
            res += query(u << 1 | 1, l, r);
        }
        return res;
    }

    private void pushup(int u) {
        tr[u].s = tr[u << 1].s + tr[u << 1 | 1].s;
    }

    private void pushdown(int u) {
        if (tr[u].lazy == 1) {
            int mid = (tr[u].l + tr[u].r) >> 1;
            tr[u << 1].s = mid - tr[u].l + 1 - tr[u << 1].s;
            tr[u << 1].lazy ^= 1;
            tr[u << 1 | 1].s = tr[u].r - mid - tr[u << 1 | 1].s;
            tr[u << 1 | 1].lazy ^= 1;
            tr[u].lazy ^= 1;
        }
    }
}

class Solution {
    public long[] handleQuery(int[] nums1, int[] nums2, int[][] queries) {
        SegmentTree tree = new SegmentTree(nums1);
        long s = 0;
        for (int x : nums2) {
            s += x;
        }
        int m = 0;
        for (var q : queries) {
            if (q[0] == 3) {
                ++m;
            }
        }
        long[] ans = new long[m];
        int i = 0;
        for (var q : queries) {
            if (q[0] == 1) {
                tree.modify(1, q[1] + 1, q[2] + 1);
            } else if (q[0] == 2) {
                s += 1L * q[1] * tree.query(1, 1, nums2.length);
            } else {
                ans[i++] = s;
            }
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Node {
public:
    int l = 0, r = 0;
    int s = 0, lazy = 0;
};

class SegmentTree {
public:
    SegmentTree(vector<int>& nums) {
        this->nums = nums;
        int n = nums.size();
        tr.resize(n << 2);
        for (int i = 0; i < tr.size(); ++i) {
            tr[i] = new Node();
        }
        build(1, 1, n);
    }

    void modify(int u, int l, int r) {
        if (tr[u]->l >= l && tr[u]->r <= r) {
            tr[u]->lazy ^= 1;
            tr[u]->s = tr[u]->r - tr[u]->l + 1 - tr[u]->s;
            return;
        }
        pushdown(u);
        int mid = (tr[u]->l + tr[u]->r) >> 1;
        if (l <= mid) {
            modify(u << 1, l, r);
        }
        if (r > mid) {
            modify(u << 1 | 1, l, r);
        }
        pushup(u);
    }

    int query(int u, int l, int r) {
        if (tr[u]->l >= l && tr[u]->r <= r) {
            return tr[u]->s;
        }
        pushdown(u);
        int mid = (tr[u]->l + tr[u]->r) >> 1;
        int res = 0;
        if (l <= mid) {
            res += query(u << 1, l, r);
        }
        if (r > mid) {
            res += query(u << 1 | 1, l, r);
        }
        return res;
    }

private:
    vector<Node*> tr;
    vector<int> nums;

    void build(int u, int l, int r) {
        tr[u]->l = l;
        tr[u]->r = r;
        if (l == r) {
            tr[u]->s = nums[l - 1];
            return;
        }
        int mid = (l + r) >> 1;
        build(u << 1, l, mid);
        build(u << 1 | 1, mid + 1, r);
        pushup(u);
    }

    void pushup(int u) {
        tr[u]->s = tr[u << 1]->s + tr[u << 1 | 1]->s;
    }

    void pushdown(int u) {
        if (tr[u]->lazy) {
            int mid = (tr[u]->l + tr[u]->r) >> 1;
            tr[u << 1]->s = mid - tr[u]->l + 1 - tr[u << 1]->s;
            tr[u << 1]->lazy ^= 1;
            tr[u << 1 | 1]->s = tr[u]->r - mid - tr[u << 1 | 1]->s;
            tr[u << 1 | 1]->lazy ^= 1;
            tr[u]->lazy ^= 1;
        }
    }
};

class Solution {
public:
    vector<long long> handleQuery(vector<int>& nums1, vector<int>& nums2, vector<vector<int>>& queries) {
        SegmentTree* tree = new SegmentTree(nums1);
        long long s = 0;
        for (int& x : nums2) {
            s += x;
        }
        vector<long long> ans;
        for (auto& q : queries) {
            if (q[0] == 1) {
                tree->modify(1, q[1] + 1, q[2] + 1);
            } else if (q[0] == 2) {
                s += 1LL * q[1] * tree->query(1, 1, nums1.size());
            } else {
                ans.push_back(s);
            }
        }
        return ans;
    }
};
```

### **Go**

```go
type node struct {
	l, r, s, lazy int
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
		t.tr[u].s = t.nums[l-1]
		return
	}
	mid := (l + r) >> 1
	t.build(u<<1, l, mid)
	t.build(u<<1|1, mid+1, r)
	t.pushup(u)
}

func (t *segmentTree) modify(u, l, r int) {
	if t.tr[u].l >= l && t.tr[u].r <= r {
		t.tr[u].lazy ^= 1
		t.tr[u].s = t.tr[u].r - t.tr[u].l + 1 - t.tr[u].s
		return
	}
	t.pushdown(u)
	mid := (t.tr[u].l + t.tr[u].r) >> 1
	if l <= mid {
		t.modify(u<<1, l, r)
	}
	if r > mid {
		t.modify(u<<1|1, l, r)
	}
	t.pushup(u)
}

func (t *segmentTree) query(u, l, r int) int {
	if t.tr[u].l >= l && t.tr[u].r <= r {
		return t.tr[u].s
	}
	t.pushdown(u)
	mid := (t.tr[u].l + t.tr[u].r) >> 1
	res := 0
	if l <= mid {
		res += t.query(u<<1, l, r)
	}
	if r > mid {
		res += t.query(u<<1|1, l, r)
	}
	return res
}

func (t *segmentTree) pushup(u int) {
	t.tr[u].s = t.tr[u<<1].s + t.tr[u<<1|1].s
}

func (t *segmentTree) pushdown(u int) {
	if t.tr[u].lazy == 1 {
		mid := (t.tr[u].l + t.tr[u].r) >> 1
		t.tr[u<<1].s = mid - t.tr[u].l + 1 - t.tr[u<<1].s
		t.tr[u<<1].lazy ^= 1
		t.tr[u<<1|1].s = t.tr[u].r - mid - t.tr[u<<1|1].s
		t.tr[u<<1|1].lazy ^= 1
		t.tr[u].lazy ^= 1
	}
}

func handleQuery(nums1 []int, nums2 []int, queries [][]int) (ans []int64) {
	tree := newSegmentTree(nums1)
	var s int64
	for _, x := range nums2 {
		s += int64(x)
	}
	for _, q := range queries {
		if q[0] == 1 {
			tree.modify(1, q[1]+1, q[2]+1)
		} else if q[0] == 2 {
			s += int64(q[1] * tree.query(1, 1, len(nums1)))
		} else {
			ans = append(ans, s)
		}
	}
	return
}
```

### **...**

```

```

<!-- tabs:end -->
