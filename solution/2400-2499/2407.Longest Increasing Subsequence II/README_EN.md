# [2407. Longest Increasing Subsequence II](https://leetcode.com/problems/longest-increasing-subsequence-ii)

[中文文档](/solution/2400-2499/2407.Longest%20Increasing%20Subsequence%20II/README.md)

## Description

<p>You are given an integer array <code>nums</code> and an integer <code>k</code>.</p>

<p>Find the longest subsequence of <code>nums</code> that meets the following requirements:</p>

<ul>
	<li>The subsequence is <strong>strictly increasing</strong> and</li>
	<li>The difference between adjacent elements in the subsequence is <strong>at most</strong> <code>k</code>.</li>
</ul>

<p>Return<em> the length of the <strong>longest</strong> <strong>subsequence</strong> that meets the requirements.</em></p>

<p>A <strong>subsequence</strong> is an array that can be derived from another array by deleting some or no elements without changing the order of the remaining elements.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [4,2,1,4,3,4,5,8,15], k = 3
<strong>Output:</strong> 5
<strong>Explanation:</strong>
The longest subsequence that meets the requirements is [1,3,4,5,8].
The subsequence has a length of 5, so we return 5.
Note that the subsequence [1,3,4,5,8,15] does not meet the requirements because 15 - 8 = 7 is larger than 3.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [7,4,5,1,8,12,4,7], k = 5
<strong>Output:</strong> 4
<strong>Explanation:</strong>
The longest subsequence that meets the requirements is [4,5,8,12].
The subsequence has a length of 4, so we return 4.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,5], k = 1
<strong>Output:</strong> 1
<strong>Explanation:</strong>
The longest subsequence that meets the requirements is [1].
The subsequence has a length of 1, so we return 1.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i], k &lt;= 10<sup>5</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Node:
    def __init__(self):
        self.l = 0
        self.r = 0
        self.v = 0


class SegmentTree:
    def __init__(self, n):
        self.tr = [Node() for _ in range(4 * n)]
        self.build(1, 1, n)

    def build(self, u, l, r):
        self.tr[u].l = l
        self.tr[u].r = r
        if l == r:
            return
        mid = (l + r) >> 1
        self.build(u << 1, l, mid)
        self.build(u << 1 | 1, mid + 1, r)

    def modify(self, u, x, v):
        if self.tr[u].l == x and self.tr[u].r == x:
            self.tr[u].v = v
            return
        mid = (self.tr[u].l + self.tr[u].r) >> 1
        if x <= mid:
            self.modify(u << 1, x, v)
        else:
            self.modify(u << 1 | 1, x, v)
        self.pushup(u)

    def pushup(self, u):
        self.tr[u].v = max(self.tr[u << 1].v, self.tr[u << 1 | 1].v)

    def query(self, u, l, r):
        if self.tr[u].l >= l and self.tr[u].r <= r:
            return self.tr[u].v
        mid = (self.tr[u].l + self.tr[u].r) >> 1
        v = 0
        if l <= mid:
            v = self.query(u << 1, l, r)
        if r > mid:
            v = max(v, self.query(u << 1 | 1, l, r))
        return v


class Solution:
    def lengthOfLIS(self, nums: List[int], k: int) -> int:
        tree = SegmentTree(max(nums))
        ans = 1
        for v in nums:
            t = tree.query(1, v - k, v - 1) + 1
            ans = max(ans, t)
            tree.modify(1, v, t)
        return ans
```

### **Java**

```java
class Solution {
    public int lengthOfLIS(int[] nums, int k) {
        int mx = nums[0];
        for (int v : nums) {
            mx = Math.max(mx, v);
        }
        SegmentTree tree = new SegmentTree(mx);
        int ans = 0;
        for (int v : nums) {
            int t = tree.query(1, v - k, v - 1) + 1;
            ans = Math.max(ans, t);
            tree.modify(1, v, t);
        }
        return ans;
    }
}

class Node {
    int l;
    int r;
    int v;
}

class SegmentTree {
    private Node[] tr;

    public SegmentTree(int n) {
        tr = new Node[4 * n];
        for (int i = 0; i < tr.length; ++i) {
            tr[i] = new Node();
        }
        build(1, 1, n);
    }

    public void build(int u, int l, int r) {
        tr[u].l = l;
        tr[u].r = r;
        if (l == r) {
            return;
        }
        int mid = (l + r) >> 1;
        build(u << 1, l, mid);
        build(u << 1 | 1, mid + 1, r);
    }

    public void modify(int u, int x, int v) {
        if (tr[u].l == x && tr[u].r == x) {
            tr[u].v = v;
            return;
        }
        int mid = (tr[u].l + tr[u].r) >> 1;
        if (x <= mid) {
            modify(u << 1, x, v);
        } else {
            modify(u << 1 | 1, x, v);
        }
        pushup(u);
    }

    public void pushup(int u) {
        tr[u].v = Math.max(tr[u << 1].v, tr[u << 1 | 1].v);
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
}
```

### **C++**

```cpp
class Node {
public:
    int l;
    int r;
    int v;
};

class SegmentTree {
public:
    vector<Node*> tr;

    SegmentTree(int n) {
        tr.resize(4 * n);
        for (int i = 0; i < tr.size(); ++i) tr[i] = new Node();
        build(1, 1, n);
    }

    void build(int u, int l, int r) {
        tr[u]->l = l;
        tr[u]->r = r;
        if (l == r) return;
        int mid = (l + r) >> 1;
        build(u << 1, l, mid);
        build(u << 1 | 1, mid + 1, r);
    }

    void modify(int u, int x, int v) {
        if (tr[u]->l == x && tr[u]->r == x)
        {
            tr[u]->v = v;
            return;
        }
        int mid = (tr[u]->l + tr[u]->r) >> 1;
        if (x <= mid) modify(u << 1, x, v);
        else modify(u << 1 | 1, x, v);
        pushup(u);
    }

    void pushup(int u) {
        tr[u]->v = max(tr[u << 1]->v, tr[u << 1 | 1]->v);
    }

    int query(int u, int l, int r) {
        if (tr[u]->l >= l && tr[u]->r <= r) return tr[u]->v;
        int mid = (tr[u]->l + tr[u]->r) >> 1;
        int v = 0;
        if (l <= mid) v = query(u << 1, l, r);
        if (r > mid) v = max(v, query(u << 1 | 1, l, r));
        return v;
    }
};

class Solution {
public:
    int lengthOfLIS(vector<int>& nums, int k) {
        SegmentTree* tree = new SegmentTree(*max_element(nums.begin(), nums.end()));
        int ans = 1;
        for (int v : nums) {
            int t = tree->query(1, v - k, v - 1) + 1;
            ans = max(ans, t);
            tree->modify(1, v, t);
        }
        return ans;
    }
};
```

### **Go**

```go
func lengthOfLIS(nums []int, k int) int {
	mx := nums[0]
	for _, v := range nums {
		mx = max(mx, v)
	}
	tree := newSegmentTree(mx)
	ans := 1
	for _, v := range nums {
		t := tree.query(1, v-k, v-1) + 1
		ans = max(ans, t)
		tree.modify(1, v, t)
	}
	return ans
}

type node struct {
	l int
	r int
	v int
}

type segmentTree struct {
	tr []*node
}

func newSegmentTree(n int) *segmentTree {
	tr := make([]*node, n<<2)
	for i := range tr {
		tr[i] = &node{}
	}
	t := &segmentTree{tr}
	t.build(1, 1, n)
	return t
}

func (t *segmentTree) build(u, l, r int) {
	t.tr[u].l, t.tr[u].r = l, r
	if l == r {
		return
	}
	mid := (l + r) >> 1
	t.build(u<<1, l, mid)
	t.build(u<<1|1, mid+1, r)
	t.pushup(u)
}

func (t *segmentTree) modify(u, x, v int) {
	if t.tr[u].l == x && t.tr[u].r == x {
		t.tr[u].v = v
		return
	}
	mid := (t.tr[u].l + t.tr[u].r) >> 1
	if x <= mid {
		t.modify(u<<1, x, v)
	} else {
		t.modify(u<<1|1, x, v)
	}
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

### **TypeScript**

```ts

```

### **...**

```


```

<!-- tabs:end -->
