# [1157. Online Majority Element In Subarray](https://leetcode.com/problems/online-majority-element-in-subarray)

[中文文档](/solution/1100-1199/1157.Online%20Majority%20Element%20In%20Subarray/README.md)

## Description

<p>Design a data structure that efficiently finds the <strong>majority element</strong> of a given subarray.</p>

<p>The <strong>majority element</strong> of a subarray is an element that occurs <code>threshold</code> times or more in the subarray.</p>

<p>Implementing the <code>MajorityChecker</code> class:</p>

<ul>
	<li><code>MajorityChecker(int[] arr)</code> Initializes the instance of the class with the given array <code>arr</code>.</li>
	<li><code>int query(int left, int right, int threshold)</code> returns the element in the subarray <code>arr[left...right]</code> that occurs at least <code>threshold</code> times, or <code>-1</code> if no such element exists.</li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input</strong>
[&quot;MajorityChecker&quot;, &quot;query&quot;, &quot;query&quot;, &quot;query&quot;]
[[[1, 1, 2, 2, 1, 1]], [0, 5, 4], [0, 3, 3], [2, 3, 2]]
<strong>Output</strong>
[null, 1, -1, 2]

<strong>Explanation</strong>
MajorityChecker majorityChecker = new MajorityChecker([1, 1, 2, 2, 1, 1]);
majorityChecker.query(0, 5, 4); // return 1
majorityChecker.query(0, 3, 3); // return -1
majorityChecker.query(2, 3, 2); // return 2
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= arr.length &lt;= 2 * 10<sup>4</sup></code></li>
	<li><code>1 &lt;= arr[i] &lt;= 2 * 10<sup>4</sup></code></li>
	<li><code>0 &lt;= left &lt;= right &lt; arr.length</code></li>
	<li><code>threshold &lt;= right - left + 1</code></li>
	<li><code>2 * threshold &gt; right - left + 1</code></li>
	<li>At most <code>10<sup>4</sup></code> calls will be made to <code>query</code>.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Node:
    def __init__(self):
        self.l = self.r = 0
        self.x = self.cnt = 0


class SegmentTree:
    def __init__(self, nums):
        self.nums = nums
        n = len(nums)
        self.tr = [Node() for _ in range(n << 2)]
        self.build(1, 1, n)

    def build(self, u, l, r):
        self.tr[u].l, self.tr[u].r = l, r
        if l == r:
            self.tr[u].x = self.nums[l - 1]
            self.tr[u].cnt = 1
            return
        mid = (l + r) >> 1
        self.build(u << 1, l, mid)
        self.build(u << 1 | 1, mid + 1, r)
        self.pushup(u)

    def query(self, u, l, r):
        if self.tr[u].l >= l and self.tr[u].r <= r:
            return self.tr[u].x, self.tr[u].cnt
        mid = (self.tr[u].l + self.tr[u].r) >> 1
        if r <= mid:
            return self.query(u << 1, l, r)
        if l > mid:
            return self.query(u << 1 | 1, l, r)
        x1, cnt1 = self.query(u << 1, l, r)
        x2, cnt2 = self.query(u << 1 | 1, l, r)
        if x1 == x2:
            return x1, cnt1 + cnt2
        if cnt1 >= cnt2:
            return x1, cnt1 - cnt2
        else:
            return x2, cnt2 - cnt1

    def pushup(self, u):
        if self.tr[u << 1].x == self.tr[u << 1 | 1].x:
            self.tr[u].x = self.tr[u << 1].x
            self.tr[u].cnt = self.tr[u << 1].cnt + self.tr[u << 1 | 1].cnt
        elif self.tr[u << 1].cnt >= self.tr[u << 1 | 1].cnt:
            self.tr[u].x = self.tr[u << 1].x
            self.tr[u].cnt = self.tr[u << 1].cnt - self.tr[u << 1 | 1].cnt
        else:
            self.tr[u].x = self.tr[u << 1 | 1].x
            self.tr[u].cnt = self.tr[u << 1 | 1].cnt - self.tr[u << 1].cnt


class MajorityChecker:

    def __init__(self, arr: List[int]):
        self.tree = SegmentTree(arr)
        self.d = defaultdict(list)
        for i, x in enumerate(arr):
            self.d[x].append(i)

    def query(self, left: int, right: int, threshold: int) -> int:
        x, _ = self.tree.query(1, left + 1, right + 1)
        l = bisect_left(self.d[x], left)
        r = bisect_left(self.d[x], right + 1)
        return x if r - l >= threshold else -1


# Your MajorityChecker object will be instantiated and called as such:
# obj = MajorityChecker(arr)
# param_1 = obj.query(left,right,threshold)
```

### **Java**

```java
class Node {
    int l, r;
    int x, cnt;
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
            tr[u].x = nums[l - 1];
            tr[u].cnt = 1;
            return;
        }
        int mid = (l + r) >> 1;
        build(u << 1, l, mid);
        build(u << 1 | 1, mid + 1, r);
        pushup(u);
    }

    public int[] query(int u, int l, int r) {
        if (tr[u].l >= l && tr[u].r <= r) {
            return new int[] {tr[u].x, tr[u].cnt};
        }
        int mid = (tr[u].l + tr[u].r) >> 1;
        if (r <= mid) {
            return query(u << 1, l, r);
        }
        if (l > mid) {
            return query(u << 1 | 1, l, r);
        }
        var left = query(u << 1, l, r);
        var right = query(u << 1 | 1, l, r);
        if (left[0] == right[0]) {
            left[1] += right[1];
        } else if (left[1] >= right[1]) {
            left[1] -= right[1];
        } else {
            right[1] -= left[1];
            left = right;
        }
        return left;
    }

    private void pushup(int u) {
        if (tr[u << 1].x == tr[u << 1 | 1].x) {
            tr[u].x = tr[u << 1].x;
            tr[u].cnt = tr[u << 1].cnt + tr[u << 1 | 1].cnt;
        } else if (tr[u << 1].cnt >= tr[u << 1 | 1].cnt) {
            tr[u].x = tr[u << 1].x;
            tr[u].cnt = tr[u << 1].cnt - tr[u << 1 | 1].cnt;
        } else {
            tr[u].x = tr[u << 1 | 1].x;
            tr[u].cnt = tr[u << 1 | 1].cnt - tr[u << 1].cnt;
        }
    }
}

class MajorityChecker {
    private SegmentTree tree;
    private Map<Integer, List<Integer>> d = new HashMap<>();

    public MajorityChecker(int[] arr) {
        tree = new SegmentTree(arr);
        for (int i = 0; i < arr.length; ++i) {
            d.computeIfAbsent(arr[i], k -> new ArrayList<>()).add(i);
        }
    }

    public int query(int left, int right, int threshold) {
        int x = tree.query(1, left + 1, right + 1)[0];
        int l = search(d.get(x), left);
        int r = search(d.get(x), right + 1);
        return r - l >= threshold ? x : -1;
    }

    private int search(List<Integer> arr, int x) {
        int left = 0, right = arr.size();
        while (left < right) {
            int mid = (left + right) >> 1;
            if (arr.get(mid) >= x) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
}

/**
 * Your MajorityChecker object will be instantiated and called as such:
 * MajorityChecker obj = new MajorityChecker(arr);
 * int param_1 = obj.query(left,right,threshold);
 */
```

### **C++**

```cpp
class Node {
public:
    int l = 0, r = 0;
    int x = 0, cnt = 0;
};

using pii = pair<int, int>;

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

    pii query(int u, int l, int r) {
        if (tr[u]->l >= l && tr[u]->r <= r) {
            return {tr[u]->x, tr[u]->cnt};
        }
        int mid = (tr[u]->l + tr[u]->r) >> 1;
        if (r <= mid) {
            return query(u << 1, l, r);
        }
        if (l > mid) {
            return query(u << 1 | 1, l, r);
        }
        auto left = query(u << 1, l, r);
        auto right = query(u << 1 | 1, l, r);
        if (left.first == right.first) {
            left.second += right.second;
        } else if (left.second >= right.second) {
            left.second -= right.second;
        } else {
            right.second -= left.second;
            left = right;
        }
        return left;
    }

private:
    vector<Node*> tr;
    vector<int> nums;

    void build(int u, int l, int r) {
        tr[u]->l = l;
        tr[u]->r = r;
        if (l == r) {
            tr[u]->x = nums[l - 1];
            tr[u]->cnt = 1;
            return;
        }
        int mid = (l + r) >> 1;
        build(u << 1, l, mid);
        build(u << 1 | 1, mid + 1, r);
        pushup(u);
    }

    void pushup(int u) {
        if (tr[u << 1]->x == tr[u << 1 | 1]->x) {
            tr[u]->x = tr[u << 1]->x;
            tr[u]->cnt = tr[u << 1]->cnt + tr[u << 1 | 1]->cnt;
        } else if (tr[u << 1]->cnt >= tr[u << 1 | 1]->cnt) {
            tr[u]->x = tr[u << 1]->x;
            tr[u]->cnt = tr[u << 1]->cnt - tr[u << 1 | 1]->cnt;
        } else {
            tr[u]->x = tr[u << 1 | 1]->x;
            tr[u]->cnt = tr[u << 1 | 1]->cnt - tr[u << 1]->cnt;
        }
    }
};

class MajorityChecker {
public:
    MajorityChecker(vector<int>& arr) {
        tree = new SegmentTree(arr);
        for (int i = 0; i < arr.size(); ++i) {
            d[arr[i]].push_back(i);
        }
    }

    int query(int left, int right, int threshold) {
        int x = tree->query(1, left + 1, right + 1).first;
        auto l = lower_bound(d[x].begin(), d[x].end(), left);
        auto r = lower_bound(d[x].begin(), d[x].end(), right + 1);
        return r - l >= threshold ? x : -1;
    }

private:
    unordered_map<int, vector<int>> d;
    SegmentTree* tree;
};

/**
 * Your MajorityChecker object will be instantiated and called as such:
 * MajorityChecker* obj = new MajorityChecker(arr);
 * int param_1 = obj->query(left,right,threshold);
 */
```

### **Go**

```go
type node struct {
	l, r, x, cnt int
}

type segmentTree struct {
	nums []int
	tr   []*node
}

type pair struct{ x, cnt int }

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
		t.tr[u].x = t.nums[l-1]
		t.tr[u].cnt = 1
		return
	}
	mid := (l + r) >> 1
	t.build(u<<1, l, mid)
	t.build(u<<1|1, mid+1, r)
	t.pushup(u)
}

func (t *segmentTree) query(u, l, r int) pair {
	if t.tr[u].l >= l && t.tr[u].r <= r {
		return pair{t.tr[u].x, t.tr[u].cnt}
	}
	mid := (t.tr[u].l + t.tr[u].r) >> 1
	if r <= mid {
		return t.query(u<<1, l, r)
	}
	if l > mid {
		return t.query(u<<1|1, l, r)
	}
	left, right := t.query(u<<1, l, r), t.query(u<<1|1, l, r)
	if left.x == right.x {
		left.cnt += right.cnt
	} else if left.cnt >= right.cnt {
		left.cnt -= right.cnt
	} else {
		right.cnt -= left.cnt
		left = right
	}
	return left
}

func (t *segmentTree) pushup(u int) {
	if t.tr[u<<1].x == t.tr[u<<1|1].x {
		t.tr[u].x = t.tr[u<<1].x
		t.tr[u].cnt = t.tr[u<<1].cnt + t.tr[u<<1|1].cnt
	} else if t.tr[u<<1].cnt >= t.tr[u<<1|1].cnt {
		t.tr[u].x = t.tr[u<<1].x
		t.tr[u].cnt = t.tr[u<<1].cnt - t.tr[u<<1|1].cnt
	} else {
		t.tr[u].x = t.tr[u<<1|1].x
		t.tr[u].cnt = t.tr[u<<1|1].cnt - t.tr[u<<1].cnt
	}
}

type MajorityChecker struct {
	tree *segmentTree
	d    map[int][]int
}

func Constructor(arr []int) MajorityChecker {
	tree := newSegmentTree(arr)
	d := map[int][]int{}
	for i, x := range arr {
		d[x] = append(d[x], i)
	}
	return MajorityChecker{tree, d}
}

func (this *MajorityChecker) Query(left int, right int, threshold int) int {
	x := this.tree.query(1, left+1, right+1).x
	l := sort.SearchInts(this.d[x], left)
	r := sort.SearchInts(this.d[x], right+1)
	if r-l >= threshold {
		return x
	}
	return -1
}

/**
 * Your MajorityChecker object will be instantiated and called as such:
 * obj := Constructor(arr);
 * param_1 := obj.Query(left,right,threshold);
 */
```

### **...**

```

```

<!-- tabs:end -->
