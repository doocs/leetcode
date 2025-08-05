---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3400-3499/3479.Fruits%20Into%20Baskets%20III/README_EN.md
rating: 2178
source: Weekly Contest 440 Q3
tags:
    - Segment Tree
    - Array
    - Binary Search
    - Ordered Set
---

<!-- problem:start -->

# [3479. Fruits Into Baskets III](https://leetcode.com/problems/fruits-into-baskets-iii)

[中文文档](/solution/3400-3499/3479.Fruits%20Into%20Baskets%20III/README.md)

## Description

<!-- description:start -->

<p>You are given two arrays of integers, <code>fruits</code> and <code>baskets</code>, each of length <code>n</code>, where <code>fruits[i]</code> represents the <strong>quantity</strong> of the <code>i<sup>th</sup></code> type of fruit, and <code>baskets[j]</code> represents the <strong>capacity</strong> of the <code>j<sup>th</sup></code> basket.</p>

<p>From left to right, place the fruits according to these rules:</p>

<ul>
	<li>Each fruit type must be placed in the <strong>leftmost available basket</strong> with a capacity <strong>greater than or equal</strong> to the quantity of that fruit type.</li>
	<li>Each basket can hold <b>only one</b> type of fruit.</li>
	<li>If a fruit type <b>cannot be placed</b> in any basket, it remains <b>unplaced</b>.</li>
</ul>

<p>Return the number of fruit types that remain unplaced after all possible allocations are made.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">fruits = [4,2,5], baskets = [3,5,4]</span></p>

<p><strong>Output:</strong> <span class="example-io">1</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li><code>fruits[0] = 4</code> is placed in <code>baskets[1] = 5</code>.</li>
	<li><code>fruits[1] = 2</code> is placed in <code>baskets[0] = 3</code>.</li>
	<li><code>fruits[2] = 5</code> cannot be placed in <code>baskets[2] = 4</code>.</li>
</ul>

<p>Since one fruit type remains unplaced, we return 1.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">fruits = [3,6,1], baskets = [6,4,7]</span></p>

<p><strong>Output:</strong> <span class="example-io">0</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li><code>fruits[0] = 3</code> is placed in <code>baskets[0] = 6</code>.</li>
	<li><code>fruits[1] = 6</code> cannot be placed in <code>baskets[1] = 4</code> (insufficient capacity) but can be placed in the next available basket, <code>baskets[2] = 7</code>.</li>
	<li><code>fruits[2] = 1</code> is placed in <code>baskets[1] = 4</code>.</li>
</ul>

<p>Since all fruits are successfully placed, we return 0.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == fruits.length == baskets.length</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= fruits[i], baskets[i] &lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Segment Tree Binary Search

We can use a segment tree to maintain the maximum basket capacity in an interval, which allows us to quickly find the first basket with capacity greater than or equal to the fruit quantity through binary search. If no such basket is found, we increment the answer by one; if found, we set that basket's capacity to zero, indicating that the basket has been used.

The time complexity is $O(n \times \log n)$, and the space complexity is $O(n)$, where $n$ is the length of $\textit{baskets}$.

<!-- tabs:start -->

#### Python3

```python
class SegmentTree:
    __slots__ = ["nums", "tr"]

    def __init__(self, nums):
        self.nums = nums
        n = len(nums)
        self.tr = [0] * (n << 2)
        self.build(1, 1, n)

    def build(self, u, l, r):
        if l == r:
            self.tr[u] = self.nums[l - 1]
            return
        mid = (l + r) >> 1
        self.build(u << 1, l, mid)
        self.build(u << 1 | 1, mid + 1, r)
        self.pushup(u)

    def modify(self, u, l, r, i, v):
        if l == r:
            self.tr[u] = v
            return
        mid = (l + r) >> 1
        if i <= mid:
            self.modify(u << 1, l, mid, i, v)
        else:
            self.modify(u << 1 | 1, mid + 1, r, i, v)
        self.pushup(u)

    def query(self, u, l, r, v):
        if self.tr[u] < v:
            return -1
        if l == r:
            return l
        mid = (l + r) >> 1
        if self.tr[u << 1] >= v:
            return self.query(u << 1, l, mid, v)
        return self.query(u << 1 | 1, mid + 1, r, v)

    def pushup(self, u):
        self.tr[u] = max(self.tr[u << 1], self.tr[u << 1 | 1])


class Solution:
    def numOfUnplacedFruits(self, fruits: List[int], baskets: List[int]) -> int:
        tree = SegmentTree(baskets)
        n = len(baskets)
        ans = 0
        for x in fruits:
            i = tree.query(1, 1, n, x)
            if i < 0:
                ans += 1
            else:
                tree.modify(1, 1, n, i, 0)
        return ans
```

#### Java

```java
class SegmentTree {
    int[] nums;
    int[] tr;

    public SegmentTree(int[] nums) {
        this.nums = nums;
        int n = nums.length;
        this.tr = new int[n << 2];
        build(1, 1, n);
    }

    public void build(int u, int l, int r) {
        if (l == r) {
            tr[u] = nums[l - 1];
            return;
        }
        int mid = (l + r) >> 1;
        build(u << 1, l, mid);
        build(u << 1 | 1, mid + 1, r);
        pushup(u);
    }

    public void modify(int u, int l, int r, int i, int v) {
        if (l == r) {
            tr[u] = v;
            return;
        }
        int mid = (l + r) >> 1;
        if (i <= mid) {
            modify(u << 1, l, mid, i, v);
        } else {
            modify(u << 1 | 1, mid + 1, r, i, v);
        }
        pushup(u);
    }

    public int query(int u, int l, int r, int v) {
        if (tr[u] < v) {
            return -1;
        }
        if (l == r) {
            return l;
        }
        int mid = (l + r) >> 1;
        if (tr[u << 1] >= v) {
            return query(u << 1, l, mid, v);
        }
        return query(u << 1 | 1, mid + 1, r, v);
    }

    public void pushup(int u) {
        tr[u] = Math.max(tr[u << 1], tr[u << 1 | 1]);
    }
}

class Solution {
    public int numOfUnplacedFruits(int[] fruits, int[] baskets) {
        SegmentTree tree = new SegmentTree(baskets);
        int n = baskets.length;
        int ans = 0;
        for (int x : fruits) {
            int i = tree.query(1, 1, n, x);
            if (i < 0) {
                ans++;
            } else {
                tree.modify(1, 1, n, i, 0);
            }
        }
        return ans;
    }
}
```

#### C++

```cpp
class SegmentTree {
public:
    vector<int> nums, tr;

    SegmentTree(vector<int>& nums) {
        this->nums = nums;
        int n = nums.size();
        tr.resize(n * 4);
        build(1, 1, n);
    }

    void build(int u, int l, int r) {
        if (l == r) {
            tr[u] = nums[l - 1];
            return;
        }
        int mid = (l + r) >> 1;
        build(u * 2, l, mid);
        build(u * 2 + 1, mid + 1, r);
        pushup(u);
    }

    void modify(int u, int l, int r, int i, int v) {
        if (l == r) {
            tr[u] = v;
            return;
        }
        int mid = (l + r) >> 1;
        if (i <= mid) {
            modify(u * 2, l, mid, i, v);
        } else {
            modify(u * 2 + 1, mid + 1, r, i, v);
        }
        pushup(u);
    }

    int query(int u, int l, int r, int v) {
        if (tr[u] < v) {
            return -1;
        }
        if (l == r) {
            return l;
        }
        int mid = (l + r) >> 1;
        if (tr[u * 2] >= v) {
            return query(u * 2, l, mid, v);
        }
        return query(u * 2 + 1, mid + 1, r, v);
    }

    void pushup(int u) {
        tr[u] = max(tr[u * 2], tr[u * 2 + 1]);
    }
};

class Solution {
public:
    int numOfUnplacedFruits(vector<int>& fruits, vector<int>& baskets) {
        SegmentTree tree(baskets);
        int n = baskets.size();
        int ans = 0;
        for (int x : fruits) {
            int i = tree.query(1, 1, n, x);
            if (i < 0) {
                ans++;
            } else {
                tree.modify(1, 1, n, i, 0);
            }
        }
        return ans;
    }
};
```

#### Go

```go
type SegmentTree struct {
	nums, tr []int
}

func NewSegmentTree(nums []int) *SegmentTree {
	n := len(nums)
	tree := &SegmentTree{
		nums: nums,
		tr:   make([]int, n*4),
	}
	tree.build(1, 1, n)
	return tree
}

func (st *SegmentTree) build(u, l, r int) {
	if l == r {
		st.tr[u] = st.nums[l-1]
		return
	}
	mid := (l + r) >> 1
	st.build(u*2, l, mid)
	st.build(u*2+1, mid+1, r)
	st.pushup(u)
}

func (st *SegmentTree) modify(u, l, r, i, v int) {
	if l == r {
		st.tr[u] = v
		return
	}
	mid := (l + r) >> 1
	if i <= mid {
		st.modify(u*2, l, mid, i, v)
	} else {
		st.modify(u*2+1, mid+1, r, i, v)
	}
	st.pushup(u)
}

func (st *SegmentTree) query(u, l, r, v int) int {
	if st.tr[u] < v {
		return -1
	}
	if l == r {
		return l
	}
	mid := (l + r) >> 1
	if st.tr[u*2] >= v {
		return st.query(u*2, l, mid, v)
	}
	return st.query(u*2+1, mid+1, r, v)
}

func (st *SegmentTree) pushup(u int) {
	st.tr[u] = max(st.tr[u*2], st.tr[u*2+1])
}

func numOfUnplacedFruits(fruits []int, baskets []int) (ans int) {
	tree := NewSegmentTree(baskets)
	n := len(baskets)
	for _, x := range fruits {
		i := tree.query(1, 1, n, x)
		if i < 0 {
			ans++
		} else {
			tree.modify(1, 1, n, i, 0)
		}
	}
	return
}
```

#### TypeScript

```ts
class SegmentTree {
    nums: number[];
    tr: number[];

    constructor(nums: number[]) {
        this.nums = nums;
        const n = nums.length;
        this.tr = Array(n * 4).fill(0);
        this.build(1, 1, n);
    }

    build(u: number, l: number, r: number): void {
        if (l === r) {
            this.tr[u] = this.nums[l - 1];
            return;
        }
        const mid = (l + r) >> 1;
        this.build(u * 2, l, mid);
        this.build(u * 2 + 1, mid + 1, r);
        this.pushup(u);
    }

    modify(u: number, l: number, r: number, i: number, v: number): void {
        if (l === r) {
            this.tr[u] = v;
            return;
        }
        const mid = (l + r) >> 1;
        if (i <= mid) {
            this.modify(u * 2, l, mid, i, v);
        } else {
            this.modify(u * 2 + 1, mid + 1, r, i, v);
        }
        this.pushup(u);
    }

    query(u: number, l: number, r: number, v: number): number {
        if (this.tr[u] < v) {
            return -1;
        }
        if (l === r) {
            return l;
        }
        const mid = (l + r) >> 1;
        if (this.tr[u * 2] >= v) {
            return this.query(u * 2, l, mid, v);
        }
        return this.query(u * 2 + 1, mid + 1, r, v);
    }

    pushup(u: number): void {
        this.tr[u] = Math.max(this.tr[u * 2], this.tr[u * 2 + 1]);
    }
}

function numOfUnplacedFruits(fruits: number[], baskets: number[]): number {
    const tree = new SegmentTree(baskets);
    const n = baskets.length;
    let ans = 0;
    for (const x of fruits) {
        const i = tree.query(1, 1, n, x);
        if (i < 0) {
            ans++;
        } else {
            tree.modify(1, 1, n, i, 0);
        }
    }
    return ans;
}
```

#### Rust

```rust
struct SegmentTree<'a> {
    nums: &'a [i32],
    tr: Vec<i32>,
}

impl<'a> SegmentTree<'a> {
    fn new(nums: &'a [i32]) -> Self {
        let n = nums.len();
        let mut tree = SegmentTree {
            nums,
            tr: vec![0; n * 4],
        };
        tree.build(1, 1, n);
        tree
    }

    fn build(&mut self, u: usize, l: usize, r: usize) {
        if l == r {
            self.tr[u] = self.nums[l - 1];
            return;
        }
        let mid = (l + r) >> 1;
        self.build(u * 2, l, mid);
        self.build(u * 2 + 1, mid + 1, r);
        self.pushup(u);
    }

    fn modify(&mut self, u: usize, l: usize, r: usize, i: usize, v: i32) {
        if l == r {
            self.tr[u] = v;
            return;
        }
        let mid = (l + r) >> 1;
        if i <= mid {
            self.modify(u * 2, l, mid, i, v);
        } else {
            self.modify(u * 2 + 1, mid + 1, r, i, v);
        }
        self.pushup(u);
    }

    fn query(&self, u: usize, l: usize, r: usize, v: i32) -> i32 {
        if self.tr[u] < v {
            return -1;
        }
        if l == r {
            return l as i32;
        }
        let mid = (l + r) >> 1;
        if self.tr[u * 2] >= v {
            return self.query(u * 2, l, mid, v);
        }
        self.query(u * 2 + 1, mid + 1, r, v)
    }

    fn pushup(&mut self, u: usize) {
        self.tr[u] = self.tr[u * 2].max(self.tr[u * 2 + 1]);
    }
}

impl Solution {
    pub fn num_of_unplaced_fruits(fruits: Vec<i32>, baskets: Vec<i32>) -> i32 {
        let mut tree = SegmentTree::new(&baskets);
        let n = baskets.len();
        let mut ans = 0;
        for &x in fruits.iter() {
            let i = tree.query(1, 1, n, x);
            if i < 0 {
                ans += 1;
            } else {
                tree.modify(1, 1, n, i as usize, 0);
            }
        }
        ans
    }
}
```

#### C#

```cs
public class SegmentTree {
    int[] nums;
    int[] tr;

    public SegmentTree(int[] nums) {
        this.nums = nums;
        int n = nums.Length;
        this.tr = new int[n << 2];
        Build(1, 1, n);
    }

    public void Build(int u, int l, int r) {
        if (l == r) {
            tr[u] = nums[l - 1];
            return;
        }
        int mid = (l + r) >> 1;
        Build(u << 1, l, mid);
        Build(u << 1 | 1, mid + 1, r);
        Pushup(u);
    }

    public void Modify(int u, int l, int r, int i, int v) {
        if (l == r) {
            tr[u] = v;
            return;
        }
        int mid = (l + r) >> 1;
        if (i <= mid) {
            Modify(u << 1, l, mid, i, v);
        } else {
            Modify(u << 1 | 1, mid + 1, r, i, v);
        }
        Pushup(u);
    }

    public int Query(int u, int l, int r, int v) {
        if (tr[u] < v) {
            return -1;
        }
        if (l == r) {
            return l;
        }
        int mid = (l + r) >> 1;
        if (tr[u << 1] >= v) {
            return Query(u << 1, l, mid, v);
        }
        return Query(u << 1 | 1, mid + 1, r, v);
    }

    public void Pushup(int u) {
        tr[u] = Math.Max(tr[u << 1], tr[u << 1 | 1]);
    }
}

public class Solution {
    public int NumOfUnplacedFruits(int[] fruits, int[] baskets) {
        SegmentTree tree = new SegmentTree(baskets);
        int n = baskets.Length;
        int ans = 0;
        foreach (var x in fruits) {
            int i = tree.Query(1, 1, n, x);
            if (i < 0) {
                ans++;
            } else {
                tree.Modify(1, 1, n, i, 0);
            }
        }
        return ans;
    }
}
```

#### Swift

```swift
class SegmentTree {
    var nums: [Int]
    var tr: [Int]

    init(_ nums: [Int]) {
        self.nums = nums
        let n = nums.count
        self.tr = [Int](repeating: 0, count: n << 2)
        build(1, 1, n)
    }

    func build(_ u: Int, _ l: Int, _ r: Int) {
        if l == r {
            tr[u] = nums[l - 1]
            return
        }
        let mid = (l + r) >> 1
        build(u << 1, l, mid)
        build(u << 1 | 1, mid + 1, r)
        pushup(u)
    }

    func modify(_ u: Int, _ l: Int, _ r: Int, _ i: Int, _ v: Int) {
        if l == r {
            tr[u] = v
            return
        }
        let mid = (l + r) >> 1
        if i <= mid {
            modify(u << 1, l, mid, i, v)
        } else {
            modify(u << 1 | 1, mid + 1, r, i, v)
        }
        pushup(u)
    }

    func query(_ u: Int, _ l: Int, _ r: Int, _ v: Int) -> Int {
        if tr[u] < v {
            return -1
        }
        if l == r {
            return l
        }
        let mid = (l + r) >> 1
        if tr[u << 1] >= v {
            return query(u << 1, l, mid, v)
        }
        return query(u << 1 | 1, mid + 1, r, v)
    }

    func pushup(_ u: Int) {
        tr[u] = max(tr[u << 1], tr[u << 1 | 1])
    }
}

class Solution {
    func numOfUnplacedFruits(_ fruits: [Int], _ baskets: [Int]) -> Int {
        let tree = SegmentTree(baskets)
        let n = baskets.count
        var ans = 0
        for x in fruits {
            let i = tree.query(1, 1, n, x)
            if i < 0 {
                ans += 1
            } else {
                tree.modify(1, 1, n, i, 0)
            }
        }
        return ans
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
