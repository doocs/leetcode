---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3400-3499/3454.Separate%20Squares%20II/README_EN.md
rating: 2671
source: Biweekly Contest 150 Q3
tags:
    - Segment Tree
    - Array
    - Binary Search
    - Line Sweep
---

<!-- problem:start -->

# [3454. Separate Squares II](https://leetcode.com/problems/separate-squares-ii)

[中文文档](/solution/3400-3499/3454.Separate%20Squares%20II/README.md)

## Description

<!-- description:start -->

<p>You are given a 2D integer array <code>squares</code>. Each <code>squares[i] = [x<sub>i</sub>, y<sub>i</sub>, l<sub>i</sub>]</code> represents the coordinates of the bottom-left point and the side length of a square parallel to the x-axis.</p>

<p>Find the <strong>minimum</strong> y-coordinate value of a horizontal line such that the total area covered by squares above the line <em>equals</em> the total area covered by squares below the line.</p>

<p>Answers within <code>10<sup>-5</sup></code> of the actual answer will be accepted.</p>

<p><strong>Note</strong>: Squares <strong>may</strong> overlap. Overlapping areas should be counted <strong>only once</strong> in this version.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">squares = [[0,0,1],[2,2,1]]</span></p>

<p><strong>Output:</strong> <span class="example-io">1.00000</span></p>

<p><strong>Explanation:</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3400-3499/3454.Separate%20Squares%20II/images/4065example1drawio.png" style="width: 269px; height: 203px;" /></p>

<p>Any horizontal line between <code>y = 1</code> and <code>y = 2</code> results in an equal split, with 1 square unit above and 1 square unit below. The minimum y-value is 1.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">squares = [[0,0,2],[1,1,1]]</span></p>

<p><strong>Output:</strong> <span class="example-io">1.00000</span></p>

<p><strong>Explanation:</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3400-3499/3454.Separate%20Squares%20II/images/4065example2drawio.png" style="width: 269px; height: 203px;" /></p>

<p>Since the blue square overlaps with the red square, it will not be counted again. Thus, the line <code>y = 1</code> splits the squares into two equal parts.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= squares.length &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>squares[i] = [x<sub>i</sub>, y<sub>i</sub>, l<sub>i</sub>]</code></li>
	<li><code>squares[i].length == 3</code></li>
	<li><code>0 &lt;= x<sub>i</sub>, y<sub>i</sub> &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= l<sub>i</sub> &lt;= 10<sup>9</sup></code></li>
	<li>The total area of all the squares will not exceed <code>10<sup>15</sup></code>.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Sweep Line

This problem can be solved using the sweep line algorithm to calculate the total area of all squares.

We treat the top and bottom boundaries of each square as event points for the sweep line, sorted by $y$ coordinate in ascending order. For each event point, we use a segment tree to maintain the length of the covered $x$-axis interval below the current sweep line, allowing us to calculate the area increment between the current sweep line and the previous one.

The specific steps are as follows:

1. **Preprocess Event Points**: For each square, calculate the $y$ coordinates of its top and bottom boundaries and add them as event points to the event list. Each event point contains the $y$ coordinate, left boundary $x_1$, right boundary $x_2$, and a flag (indicating whether it's the top or bottom boundary).
2. **Sort Event Points**: Sort all event points by $y$ coordinate in ascending order.
3. **Build Segment Tree**: Build a segment tree using the discretized $x$ coordinates to maintain the length of the currently covered $x$-axis intervals.
4. **Scan Event Points**: Traverse the sorted event point list. For each event point:
    - Calculate the area increment between the current event point and the previous one, and add it to the total area.
    - Based on the type of the current event point (top or bottom boundary), update the segment tree by increasing or decreasing the coverage count of the corresponding $x$-axis interval.
5. **Calculate Target Area**: The target area is half of the total area.
6. **Scan Event Points Again**: Traverse the event point list again, calculating the cumulative area. When the cumulative area reaches the target area, calculate and return the corresponding $y$ coordinate.

The time complexity is $O(n \times \log n)$ and the space complexity is $O(n)$, where $n$ is the number of squares.

<!-- tabs:start -->

#### Python3

```python
class Node:
    __slots__ = ("l", "r", "cnt", "length")

    def __init__(self):
        self.l = self.r = 0
        self.cnt = self.length = 0


class SegmentTree:
    def __init__(self, nums):
        n = len(nums) - 1
        self.nums = nums
        self.tr = [Node() for _ in range(n << 2)]
        self.build(1, 0, n - 1)

    def build(self, u, l, r):
        self.tr[u].l, self.tr[u].r = l, r
        if l != r:
            mid = (l + r) >> 1
            self.build(u << 1, l, mid)
            self.build(u << 1 | 1, mid + 1, r)

    def modify(self, u, l, r, k):
        if self.tr[u].l >= l and self.tr[u].r <= r:
            self.tr[u].cnt += k
        else:
            mid = (self.tr[u].l + self.tr[u].r) >> 1
            if l <= mid:
                self.modify(u << 1, l, r, k)
            if r > mid:
                self.modify(u << 1 | 1, l, r, k)
        self.pushup(u)

    def pushup(self, u):
        if self.tr[u].cnt:
            self.tr[u].length = self.nums[self.tr[u].r + 1] - self.nums[self.tr[u].l]
        elif self.tr[u].l == self.tr[u].r:
            self.tr[u].length = 0
        else:
            self.tr[u].length = self.tr[u << 1].length + self.tr[u << 1 | 1].length

    @property
    def length(self):
        return self.tr[1].length


class Solution:
    def separateSquares(self, squares: List[List[int]]) -> float:
        xs = set()
        segs = []
        for x1, y1, l in squares:
            x2, y2 = x1 + l, y1 + l
            xs.update([x1, x2])
            segs.append((y1, x1, x2, 1))
            segs.append((y2, x1, x2, -1))
        segs.sort()
        st = sorted(xs)
        tree = SegmentTree(st)
        d = {x: i for i, x in enumerate(st)}
        area = 0
        y0 = 0
        for y, x1, x2, k in segs:
            area += (y - y0) * tree.length
            tree.modify(1, d[x1], d[x2] - 1, k)
            y0 = y

        target = area / 2
        area = 0
        y0 = 0
        for y, x1, x2, k in segs:
            t = (y - y0) * tree.length
            if area + t >= target:
                return y0 + (target - area) / tree.length
            area += t
            tree.modify(1, d[x1], d[x2] - 1, k)
            y0 = y
        return 0
```

#### Java

```java
class Node {
    int l, r, cnt, length;
}

class SegmentTree {
    private Node[] tr;
    private int[] nums;

    public SegmentTree(int[] nums) {
        this.nums = nums;
        int n = nums.length - 1;
        tr = new Node[n << 2];
        for (int i = 0; i < tr.length; ++i) {
            tr[i] = new Node();
        }
        build(1, 0, n - 1);
    }

    private void build(int u, int l, int r) {
        tr[u].l = l;
        tr[u].r = r;
        if (l != r) {
            int mid = (l + r) >> 1;
            build(u << 1, l, mid);
            build(u << 1 | 1, mid + 1, r);
        }
    }

    public void modify(int u, int l, int r, int k) {
        if (tr[u].l >= l && tr[u].r <= r) {
            tr[u].cnt += k;
        } else {
            int mid = (tr[u].l + tr[u].r) >> 1;
            if (l <= mid) {
                modify(u << 1, l, r, k);
            }
            if (r > mid) {
                modify(u << 1 | 1, l, r, k);
            }
        }
        pushup(u);
    }

    private void pushup(int u) {
        if (tr[u].cnt > 0) {
            tr[u].length = nums[tr[u].r + 1] - nums[tr[u].l];
        } else if (tr[u].l == tr[u].r) {
            tr[u].length = 0;
        } else {
            tr[u].length = tr[u << 1].length + tr[u << 1 | 1].length;
        }
    }

    public int query() {
        return tr[1].length;
    }
}

class Solution {
    public double separateSquares(int[][] squares) {
        Set<Integer> xs = new HashSet<>();
        List<int[]> segs = new ArrayList<>();
        for (int[] sq : squares) {
            int x1 = sq[0], y1 = sq[1], l = sq[2];
            int x2 = x1 + l, y2 = y1 + l;
            xs.add(x1);
            xs.add(x2);
            segs.add(new int[] {y1, x1, x2, 1});
            segs.add(new int[] {y2, x1, x2, -1});
        }
        segs.sort(Comparator.comparingInt(a -> a[0]));
        int[] st = new int[xs.size()];
        int i = 0;
        for (int x : xs) {
            st[i++] = x;
        }
        Arrays.sort(st);
        SegmentTree tree = new SegmentTree(st);
        Map<Integer, Integer> d = new HashMap<>(st.length);
        for (i = 0; i < st.length; i++) {
            d.put(st[i], i);
        }
        double area = 0.0;
        int y0 = 0;
        for (int[] s : segs) {
            int y = s[0], x1 = s[1], x2 = s[2], k = s[3];
            area += (double) (y - y0) * tree.query();
            tree.modify(1, d.get(x1), d.get(x2) - 1, k);
            y0 = y;
        }
        double target = area / 2.0;
        area = 0.0;
        y0 = 0;
        for (int[] s : segs) {
            int y = s[0], x1 = s[1], x2 = s[2], k = s[3];
            double t = (double) (y - y0) * tree.query();
            if (area + t >= target) {
                return y0 + (target - area) / tree.query();
            }
            area += t;
            tree.modify(1, d.get(x1), d.get(x2) - 1, k);
            y0 = y;
        }
        return 0.0;
    }
}
```

#### C++

```cpp
struct Node {
    int l = 0, r = 0, cnt = 0;
    int length = 0;
};

class SegmentTree {
private:
    vector<Node> tr;
    vector<int> nums;

    void build(int u, int l, int r) {
        tr[u].l = l;
        tr[u].r = r;
        if (l != r) {
            int mid = (l + r) >> 1;
            build(u << 1, l, mid);
            build(u << 1 | 1, mid + 1, r);
        }
    }

    void pushup(int u) {
        if (tr[u].cnt > 0) {
            tr[u].length = nums[tr[u].r + 1] - nums[tr[u].l];
        } else if (tr[u].l == tr[u].r) {
            tr[u].length = 0;
        } else {
            tr[u].length = tr[u << 1].length + tr[u << 1 | 1].length;
        }
    }

public:
    SegmentTree(const vector<int>& nums)
        : nums(nums) {
        int n = (int) nums.size() - 1;
        tr.assign(n << 2, Node());
        build(1, 0, n - 1);
    }

    void modify(int u, int l, int r, int k) {
        if (tr[u].l >= l && tr[u].r <= r) {
            tr[u].cnt += k;
        } else {
            int mid = (tr[u].l + tr[u].r) >> 1;
            if (l <= mid) modify(u << 1, l, r, k);
            if (r > mid) modify(u << 1 | 1, l, r, k);
        }
        pushup(u);
    }

    int query() const {
        return tr[1].length;
    }
};

class Solution {
public:
    double separateSquares(vector<vector<int>>& squares) {
        set<int> xs;
        vector<array<int, 4>> segs;

        for (auto& sq : squares) {
            int x1 = sq[0], y1 = sq[1], l = sq[2];
            int x2 = x1 + l, y2 = y1 + l;
            xs.insert(x1);
            xs.insert(x2);
            segs.push_back({y1, x1, x2, 1});
            segs.push_back({y2, x1, x2, -1});
        }

        sort(segs.begin(), segs.end(), [](const auto& a, const auto& b) {
            return a[0] < b[0];
        });

        vector<int> st;
        st.reserve(xs.size());
        for (int x : xs) st.push_back(x);

        SegmentTree tree(st);

        unordered_map<int, int> d;
        d.reserve(st.size() * 2);
        for (int i = 0; i < (int) st.size(); i++) d[st[i]] = i;

        double area = 0.0;
        int y0 = 0;
        for (auto& s : segs) {
            int y = s[0], x1 = s[1], x2 = s[2], k = s[3];
            area += (double) (y - y0) * tree.query();
            tree.modify(1, d[x1], d[x2] - 1, k);
            y0 = y;
        }

        double target = area / 2.0;
        area = 0.0;
        y0 = 0;
        for (auto& s : segs) {
            int y = s[0], x1 = s[1], x2 = s[2], k = s[3];
            double t = (double) (y - y0) * tree.query();
            if (area + t >= target) {
                return y0 + (target - area) / tree.query();
            }
            area += t;
            tree.modify(1, d[x1], d[x2] - 1, k);
            y0 = y;
        }

        return 0.0;
    }
};
```

#### Go

```go
type Node struct {
	l, r   int
	cnt    int
	length int
}

type SegmentTree struct {
	tr   []Node
	nums []int
}

func NewSegmentTree(nums []int) *SegmentTree {
	n := len(nums) - 1
	tr := make([]Node, n<<2)
	t := &SegmentTree{tr: tr, nums: nums}
	t.build(1, 0, n-1)
	return t
}

func (t *SegmentTree) build(u, l, r int) {
	t.tr[u].l = l
	t.tr[u].r = r
	if l != r {
		mid := (l + r) >> 1
		t.build(u<<1, l, mid)
		t.build(u<<1|1, mid+1, r)
	}
}

func (t *SegmentTree) modify(u, l, r, k int) {
	if l > r {
		return
	}
	if t.tr[u].l >= l && t.tr[u].r <= r {
		t.tr[u].cnt += k
	} else {
		mid := (t.tr[u].l + t.tr[u].r) >> 1
		if l <= mid {
			t.modify(u<<1, l, r, k)
		}
		if r > mid {
			t.modify(u<<1|1, l, r, k)
		}
	}
	t.pushup(u)
}

func (t *SegmentTree) pushup(u int) {
	if t.tr[u].cnt > 0 {
		t.tr[u].length = t.nums[t.tr[u].r+1] - t.nums[t.tr[u].l]
	} else if t.tr[u].l == t.tr[u].r {
		t.tr[u].length = 0
	} else {
		t.tr[u].length = t.tr[u<<1].length + t.tr[u<<1|1].length
	}
}

func (t *SegmentTree) query() int {
	return t.tr[1].length
}

func separateSquares(squares [][]int) float64 {
	pos := make(map[int]bool)
	xs := make([]int, 0)
	segs := make([][]int, 0, len(squares)*2)
	for _, sq := range squares {
		x1, y1, l := sq[0], sq[1], sq[2]
		x2, y2 := x1+l, y1+l
		if !pos[x1] {
			pos[x1] = true
			xs = append(xs, x1)
		}
		if !pos[x2] {
			pos[x2] = true
			xs = append(xs, x2)
		}
		segs = append(segs, []int{y1, x1, x2, 1})
		segs = append(segs, []int{y2, x1, x2, -1})
	}
	sort.Slice(segs, func(i, j int) bool { return segs[i][0] < segs[j][0] })
	sort.Ints(xs)
	tree := NewSegmentTree(xs)
	d := make(map[int]int, len(xs))
	for i, x := range xs {
		d[x] = i
	}
	area := 0.0
	y0 := 0
	for _, s := range segs {
		y, x1, x2, k := s[0], s[1], s[2], s[3]
		area += float64(y-y0) * float64(tree.query())
		tree.modify(1, d[x1], d[x2]-1, k)
		y0 = y
	}
	target := area / 2.0
	area = 0.0
	y0 = 0
	for _, s := range segs {
		y, x1, x2, k := s[0], s[1], s[2], s[3]
		curLen := tree.query()
		t := float64(y-y0) * float64(curLen)
		if area+t >= target {
			return float64(y0) + (target-area)/float64(curLen)
		}
		area += t
		tree.modify(1, d[x1], d[x2]-1, k)
		y0 = y
	}
	return 0.0
}
```

#### TypeScript

```ts
class Node {
    l = 0;
    r = 0;
    cnt = 0;
    length = 0;
}

class SegmentTree {
    private tr: Node[];
    private nums: number[];
    constructor(nums: number[]) {
        this.nums = nums;
        const n = nums.length - 1;
        this.tr = Array.from({ length: n << 2 }, () => new Node());
        this.build(1, 0, n - 1);
    }

    private build(u: number, l: number, r: number): void {
        this.tr[u].l = l;
        this.tr[u].r = r;
        if (l !== r) {
            const mid = (l + r) >> 1;
            this.build(u << 1, l, mid);
            this.build((u << 1) | 1, mid + 1, r);
        }
    }

    modify(u: number, l: number, r: number, k: number): void {
        if (l > r) return;
        if (this.tr[u].l >= l && this.tr[u].r <= r) {
            this.tr[u].cnt += k;
        } else {
            const mid = (this.tr[u].l + this.tr[u].r) >> 1;
            if (l <= mid) this.modify(u << 1, l, r, k);
            if (r > mid) this.modify((u << 1) | 1, l, r, k);
        }
        this.pushup(u);
    }

    private pushup(u: number): void {
        if (this.tr[u].cnt > 0) {
            this.tr[u].length = this.nums[this.tr[u].r + 1] - this.nums[this.tr[u].l];
        } else if (this.tr[u].l === this.tr[u].r) {
            this.tr[u].length = 0;
        } else {
            this.tr[u].length = this.tr[u << 1].length + this.tr[(u << 1) | 1].length;
        }
    }

    query(): number {
        return this.tr[1].length;
    }
}

function separateSquares(squares: number[][]): number {
    const xsSet = new Set<number>();
    const segs: number[][] = [];
    for (const [x1, y1, l] of squares) {
        const [x2, y2] = [x1 + l, y1 + l];
        xsSet.add(x1);
        xsSet.add(x2);
        segs.push([y1, x1, x2, 1]);
        segs.push([y2, x1, x2, -1]);
    }
    segs.sort((a, b) => a[0] - b[0]);
    const xs = Array.from(xsSet);
    xs.sort((a, b) => a - b);
    const tree = new SegmentTree(xs);
    const d = new Map<number, number>();
    for (let i = 0; i < xs.length; i++) {
        d.set(xs[i], i);
    }
    let area = 0.0;
    let y0 = 0;
    for (const [y, x1, x2, k] of segs) {
        area += (y - y0) * tree.query();
        tree.modify(1, d.get(x1)!, d.get(x2)! - 1, k);
        y0 = y;
    }
    const target = area / 2.0;
    area = 0.0;
    y0 = 0;
    for (const [y, x1, x2, k] of segs) {
        const curLen = tree.query();
        const t = (y - y0) * curLen;
        if (area + t >= target) {
            return y0 + (target - area) / curLen;
        }
        area += t;
        tree.modify(1, d.get(x1)!, d.get(x2)! - 1, k);
        y0 = y;
    }
    return 0.0;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
