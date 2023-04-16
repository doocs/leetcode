# [1157. 子数组中占绝大多数的元素](https://leetcode.cn/problems/online-majority-element-in-subarray)

[English Version](/solution/1100-1199/1157.Online%20Majority%20Element%20In%20Subarray/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>设计一个数据结构，有效地找到给定子数组的 <strong>多数元素</strong> 。</p>

<p>子数组的 <strong>多数元素</strong> 是在子数组中出现&nbsp;<code>threshold</code>&nbsp;次数或次数以上的元素。</p>

<p>实现 <code>MajorityChecker</code> 类:</p>

<ul>
	<li><code>MajorityChecker(int[] arr)</code>&nbsp;会用给定的数组 <code>arr</code>&nbsp;对&nbsp;<code>MajorityChecker</code> 初始化。</li>
	<li><code>int query(int left, int right, int threshold)</code>&nbsp;返回子数组中的元素 &nbsp;<code>arr[left...right]</code>&nbsp;至少出现&nbsp;<code>threshold</code>&nbsp;次数，如果不存在这样的元素则返回 <code>-1</code>。</li>
</ul>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入:</strong>
["MajorityChecker", "query", "query", "query"]
[[[1, 1, 2, 2, 1, 1]], [0, 5, 4], [0, 3, 3], [2, 3, 2]]
<strong>输出：</strong>
[null, 1, -1, 2]

<b>解释：</b>
MajorityChecker majorityChecker = new MajorityChecker([1,1,2,2,1,1]);
majorityChecker.query(0,5,4); // 返回 1
majorityChecker.query(0,3,3); // 返回 -1
majorityChecker.query(2,3,2); // 返回 2
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= arr.length &lt;= 2 * 10<sup>4</sup></code></li>
	<li><code>1 &lt;= arr[i] &lt;= 2 * 10<sup>4</sup></code></li>
	<li><code>0 &lt;= left &lt;= right &lt; arr.length</code></li>
	<li><code>threshold &lt;= right - left + 1</code></li>
	<li><code>2 * threshold &gt; right - left + 1</code></li>
	<li>调用&nbsp;<code>query</code>&nbsp;的次数最多为&nbsp;<code>10<sup>4</sup></code>&nbsp;</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：线段树 + 摩尔投票 + 二分查找**

我们注意到，题目需要我们找出特定区间内可能的众数，考虑使用线段树来维护每个区间内的候选众数以及其出现的次数。

我们定义线段树的每个节点为 `Node`，每个节点包含如下属性：

-   `l`：节点的左端点，下标从 $1$ 开始。
-   `r`：节点的右端点，下标从 $1$ 开始。
-   `x`：节点的候选众数。
-   `cnt`：节点的候选众数出现的次数。

线段树主要有以下几个操作：

-   `build(u, l, r)`：建立线段树。
-   `pushup(u)`：用子节点的信息更新父节点的信息。
-   `query(u, l, r)`：查询区间和。

在主函数的初始化方法中，我们先创建一个线段树，并且用哈希表 $d$ 记录每个元素在数组中的所有下标。

在 `query(left, right, threshold)` 方法中，我们直接调用线段树的 `query` 方法，得到候选众数 $x$。然后使用二分查找，找到 $x$ 在数组中第一个大于等于 $left$ 的下标 $l$，以及第一个大于 $right$ 的下标 $r$。如果 $r - l \ge threshold$，则返回 $x$，否则返回 $-1$。

时间复杂度方面，初始化方法的时间复杂度为 $O(n)$，查询方法的时间复杂度为 $O(\log n)$。空间复杂度为 $O(n)$。其中 $n$ 为数组的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
