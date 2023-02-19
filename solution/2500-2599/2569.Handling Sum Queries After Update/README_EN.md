# [2569. Handling Sum Queries After Update](https://leetcode.com/problems/handling-sum-queries-after-update)

[中文文档](/solution/2500-2599/2569.Handling%20Sum%20Queries%20After%20Update/README.md)

## Description

<p>You are given two <strong>0-indexed</strong> arrays <code>nums1</code> and <code>nums2</code> and a 2D array <code>queries</code> of queries. There are three types of queries:</p>

<ol>
	<li>For a query of type 1, <code>queries[i]&nbsp;= [1, l, r]</code>. Flip the values from <code>0</code> to <code>1</code> and from <code>1</code> to <code>0</code> in <code>nums1 </code>from index <code>l</code> to index <code>r</code>. Both <code>l</code> and <code>r</code> are <strong>0-indexed</strong>.</li>
	<li>For a query of type 2, <code>queries[i]&nbsp;= [2, p, 0]</code>. For every index <code>0 &lt;= i &lt; n</code>, set&nbsp;<code>nums2[i] =&nbsp;nums2[i]&nbsp;+ nums1[i]&nbsp;* p</code>.</li>
	<li>For a query of type 3, <code>queries[i]&nbsp;= [3, 0, 0]</code>. Find the sum of the elements in <code>nums2</code>.</li>
</ol>

<p>Return <em>an array containing all the answers to the third type&nbsp;queries.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums1 = [1,0,1], nums2 = [0,0,0], queries = [[1,1,1],[2,1,0],[3,0,0]]
<strong>Output:</strong> [3]
<strong>Explanation:</strong> After the first query nums1 becomes [1,1,1]. After the second query, nums2 becomes [1,1,1], so the answer to the third query is 3. Thus, [3] is returned.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums1 = [1], nums2 = [5], queries = [[2,0,0],[3,0,0]]
<strong>Output:</strong> [5]
<strong>Explanation:</strong> After the first query, nums2 remains [5], so the answer to the second query is 5. Thus, [5] is returned.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums1.length,nums2.length &lt;= 10<sup>5</sup></code></li>
	<li><code>nums1.length = nums2.length</code></li>
	<li><code>1 &lt;= queries.length &lt;= 10<sup>5</sup></code></li>
	<li><code><font face="monospace">queries[i].length = 3</font></code></li>
	<li><code><font face="monospace">0 &lt;= l &lt;= r &lt;= nums1.length - 1</font></code></li>
	<li><code><font face="monospace">0 &lt;= p &lt;= 10<sup>6</sup></font></code></li>
	<li><code>0 &lt;= nums1[i] &lt;= 1</code></li>
	<li><code>0 &lt;= nums2[i] &lt;= 10<sup>9</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

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
