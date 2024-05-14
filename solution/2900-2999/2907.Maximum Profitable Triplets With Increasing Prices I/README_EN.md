# [2907. Maximum Profitable Triplets With Increasing Prices I 🔒](https://leetcode.com/problems/maximum-profitable-triplets-with-increasing-prices-i)

[中文文档](/solution/2900-2999/2907.Maximum%20Profitable%20Triplets%20With%20Increasing%20Prices%20I/README.md)

<!-- tags:Binary Indexed Tree,Segment Tree,Array -->

<!-- difficulty:Medium -->

## Description

<p>Given the <strong>0-indexed</strong> arrays <code>prices</code> and <code>profits</code> of length <code>n</code>. There are <code>n</code> items in an store where the <code>i<sup>th</sup></code> item has a price of <code>prices[i]</code> and a profit of <code>profits[i]</code>.</p>

<p>We have to pick three items with the following condition:</p>

<ul>
	<li><code>prices[i] &lt; prices[j] &lt; prices[k]</code> where <code>i &lt; j &lt; k</code>.</li>
</ul>

<p>If we pick items with indices <code>i</code>, <code>j</code> and <code>k</code> satisfying the above condition, the profit would be <code>profits[i] + profits[j] + profits[k]</code>.</p>

<p>Return<em> the <strong>maximum profit</strong> we can get, and </em><code>-1</code><em> if it&#39;s not possible to pick three items with the given condition.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> prices = [10,2,3,4], profits = [100,2,7,10]
<strong>Output:</strong> 19
<strong>Explanation:</strong> We can&#39;t pick the item with index i=0 since there are no indices j and k such that the condition holds.
So the only triplet we can pick, are the items with indices 1, 2 and 3 and it&#39;s a valid pick since prices[1] &lt; prices[2] &lt; prices[3].
The answer would be sum of their profits which is 2 + 7 + 10 = 19.</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> prices = [1,2,3,4,5], profits = [1,5,3,4,6]
<strong>Output:</strong> 15
<strong>Explanation:</strong> We can select any triplet of items since for each triplet of indices i, j and k such that i &lt; j &lt; k, the condition holds.
Therefore the maximum profit we can get would be the 3 most profitable items which are indices 1, 3 and 4.
The answer would be sum of their profits which is 5 + 4 + 6 = 15.</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> prices = [4,3,2,1], profits = [33,20,19,87]
<strong>Output:</strong> -1
<strong>Explanation:</strong> We can&#39;t select any triplet of indices such that the condition holds, so we return -1.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>3 &lt;= prices.length == profits.length &lt;= 2000</code></li>
	<li><code>1 &lt;= prices[i] &lt;= 10<sup>6</sup></code></li>
	<li><code>1 &lt;= profits[i] &lt;= 10<sup>6</sup></code></li>
</ul>

## Solutions

### Solution 1: Enumerate the Middle Element

We can enumerate the middle element $profits[j]$, and then enumerate the left element $profits[i]$ and the right element $profits[k]$. For each $profits[j]$, we need to find the maximum $profits[i]$ and the maximum $profits[k]$ such that $prices[i] < prices[j] < prices[k]$. We define $left$ as the maximum value on the left of $profits[j]$, and $right$ as the maximum value on the right of $profits[j]$. If they exist, we update the answer as $ans = \max(ans, left + profits[j] + right)$.

The time complexity is $O(n^2)$, where $n$ is the length of the array. The space complexity is $O(1)$.

<!-- tabs:start -->

```python
class Solution:
    def maxProfit(self, prices: List[int], profits: List[int]) -> int:
        n = len(prices)
        ans = -1
        for j, x in enumerate(profits):
            left = right = 0
            for i in range(j):
                if prices[i] < prices[j] and left < profits[i]:
                    left = profits[i]
            for k in range(j + 1, n):
                if prices[j] < prices[k] and right < profits[k]:
                    right = profits[k]
            if left and right:
                ans = max(ans, left + x + right)
        return ans
```

```java
class Solution {
    public int maxProfit(int[] prices, int[] profits) {
        int n = prices.length;
        int ans = -1;
        for (int j = 0; j < n; ++j) {
            int left = 0, right = 0;
            for (int i = 0; i < j; ++i) {
                if (prices[i] < prices[j]) {
                    left = Math.max(left, profits[i]);
                }
            }
            for (int k = j + 1; k < n; ++k) {
                if (prices[j] < prices[k]) {
                    right = Math.max(right, profits[k]);
                }
            }
            if (left > 0 && right > 0) {
                ans = Math.max(ans, left + profits[j] + right);
            }
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int maxProfit(vector<int>& prices, vector<int>& profits) {
        int n = prices.size();
        int ans = -1;
        for (int j = 0; j < n; ++j) {
            int left = 0, right = 0;
            for (int i = 0; i < j; ++i) {
                if (prices[i] < prices[j]) {
                    left = max(left, profits[i]);
                }
            }
            for (int k = j + 1; k < n; ++k) {
                if (prices[j] < prices[k]) {
                    right = max(right, profits[k]);
                }
            }
            if (left && right) {
                ans = max(ans, left + profits[j] + right);
            }
        }
        return ans;
    }
};
```

```go
func maxProfit(prices []int, profits []int) int {
	n := len(prices)
	ans := -1
	for j, x := range profits {
		left, right := 0, 0
		for i := 0; i < j; i++ {
			if prices[i] < prices[j] {
				left = max(left, profits[i])
			}
		}
		for k := j + 1; k < n; k++ {
			if prices[j] < prices[k] {
				right = max(right, profits[k])
			}
		}
		if left > 0 && right > 0 {
			ans = max(ans, left+x+right)
		}
	}
	return ans
}
```

```ts
function maxProfit(prices: number[], profits: number[]): number {
    const n = prices.length;
    let ans = -1;
    for (let j = 0; j < n; ++j) {
        let [left, right] = [0, 0];
        for (let i = 0; i < j; ++i) {
            if (prices[i] < prices[j]) {
                left = Math.max(left, profits[i]);
            }
        }
        for (let k = j + 1; k < n; ++k) {
            if (prices[j] < prices[k]) {
                right = Math.max(right, profits[k]);
            }
        }
        if (left > 0 && right > 0) {
            ans = Math.max(ans, left + profits[j] + right);
        }
    }
    return ans;
}
```

```rust
impl Solution {
    pub fn max_profit(prices: Vec<i32>, profits: Vec<i32>) -> i32 {
        let n = prices.len();
        let mut ans = -1;

        for j in 0..n {
            let mut left = 0;
            let mut right = 0;

            for i in 0..j {
                if prices[i] < prices[j] {
                    left = left.max(profits[i]);
                }
            }

            for k in j + 1..n {
                if prices[j] < prices[k] {
                    right = right.max(profits[k]);
                }
            }

            if left > 0 && right > 0 {
                ans = ans.max(left + profits[j] + right);
            }
        }

        ans
    }
}
```

<!-- tabs:end -->

### Solution 2: Binary Indexed Tree

We can use two Binary Indexed Trees (BITs) to maintain the maximum profit on the left and right of each price, respectively. Then, we enumerate the middle price, query the maximum profit on both sides through the BIT, and finally take the maximum value.

The time complexity is $O(n \times \log M)$, and the space complexity is $O(M)$. Here, $n$ is the length of the array $prices$, and $M$ is the maximum value in the array $prices$. In this problem, $M \le 10^6$.

Since the length of $prices$ does not exceed $2000$, and the value of $prices[i]$ reaches $10^6$, we can discretize $prices$, which can reduce the space complexity to $O(n)$.

<!-- tabs:start -->

```python
class BinaryIndexedTree:
    def __init__(self, n: int):
        self.n = n
        self.c = [0] * (n + 1)

    def update(self, x: int, v: int):
        while x <= self.n:
            self.c[x] = max(self.c[x], v)
            x += x & -x

    def query(self, x: int) -> int:
        mx = 0
        while x:
            mx = max(mx, self.c[x])
            x -= x & -x
        return mx


class Solution:
    def maxProfit(self, prices: List[int], profits: List[int]) -> int:
        n = len(prices)
        left = [0] * n
        right = [0] * n

        m = max(prices)
        tree1 = BinaryIndexedTree(m + 1)
        tree2 = BinaryIndexedTree(m + 1)

        for i, x in enumerate(prices):
            left[i] = tree1.query(x - 1)
            tree1.update(x, profits[i])
        for i in range(n - 1, -1, -1):
            x = m + 1 - prices[i]
            right[i] = tree2.query(x - 1)
            tree2.update(x, profits[i])

        return max(
            (l + x + r for l, x, r in zip(left, profits, right) if l and r), default=-1
        )
```

```java
class BinaryIndexedTree {
    private int n;
    private int[] c;

    public BinaryIndexedTree(int n) {
        this.n = n;
        c = new int[n + 1];
    }

    public void update(int x, int v) {
        while (x <= n) {
            c[x] = Math.max(c[x], v);
            x += x & -x;
        }
    }

    public int query(int x) {
        int mx = 0;
        while (x > 0) {
            mx = Math.max(mx, c[x]);
            x -= x & -x;
        }
        return mx;
    }
}

class Solution {
    public int maxProfit(int[] prices, int[] profits) {
        int n = prices.length;
        int[] left = new int[n];
        int[] right = new int[n];
        int m = 0;
        for (int x : prices) {
            m = Math.max(m, x);
        }
        BinaryIndexedTree tree1 = new BinaryIndexedTree(m + 1);
        BinaryIndexedTree tree2 = new BinaryIndexedTree(m + 1);
        for (int i = 0; i < n; ++i) {
            int x = prices[i];
            left[i] = tree1.query(x - 1);
            tree1.update(x, profits[i]);
        }
        for (int i = n - 1; i >= 0; --i) {
            int x = m + 1 - prices[i];
            right[i] = tree2.query(x - 1);
            tree2.update(x, profits[i]);
        }
        int ans = -1;
        for (int i = 0; i < n; ++i) {
            if (left[i] > 0 && right[i] > 0) {
                ans = Math.max(ans, left[i] + profits[i] + right[i]);
            }
        }
        return ans;
    }
}
```

```cpp
class BinaryIndexedTree {
private:
    int n;
    vector<int> c;

public:
    BinaryIndexedTree(int n) {
        this->n = n;
        c.resize(n + 1, 0);
    }

    void update(int x, int v) {
        while (x <= n) {
            c[x] = max(c[x], v);
            x += x & -x;
        }
    }

    int query(int x) {
        int mx = 0;
        while (x > 0) {
            mx = max(mx, c[x]);
            x -= x & -x;
        }
        return mx;
    }
};

class Solution {
public:
    int maxProfit(vector<int>& prices, vector<int>& profits) {
        int n = prices.size();
        vector<int> left(n, 0);
        vector<int> right(n, 0);
        int m = *max_element(prices.begin(), prices.end());
        BinaryIndexedTree tree1(m + 1);
        BinaryIndexedTree tree2(m + 1);
        for (int i = 0; i < n; ++i) {
            int x = prices[i];
            left[i] = tree1.query(x - 1);
            tree1.update(x, profits[i]);
        }
        for (int i = n - 1; i >= 0; --i) {
            int x = m + 1 - prices[i];
            right[i] = tree2.query(x - 1);
            tree2.update(x, profits[i]);
        }
        int ans = -1;
        for (int i = 0; i < n; ++i) {
            if (left[i] > 0 && right[i] > 0) {
                ans = max(ans, left[i] + profits[i] + right[i]);
            }
        }
        return ans;
    }
};
```

```go
type BinaryIndexedTree struct {
	n int
	c []int
}

func NewBinaryIndexedTree(n int) BinaryIndexedTree {
	c := make([]int, n+1)
	return BinaryIndexedTree{n: n, c: c}
}

func (bit *BinaryIndexedTree) update(x, v int) {
	for x <= bit.n {
		bit.c[x] = max(bit.c[x], v)
		x += x & -x
	}
}

func (bit *BinaryIndexedTree) query(x int) int {
	mx := 0
	for x > 0 {
		mx = max(mx, bit.c[x])
		x -= x & -x
	}
	return mx
}

func maxProfit(prices []int, profits []int) int {
	n := len(prices)
	left := make([]int, n)
	right := make([]int, n)
	m := slices.Max(prices)

	tree1 := NewBinaryIndexedTree(m + 1)
	tree2 := NewBinaryIndexedTree(m + 1)

	for i, x := range prices {
		left[i] = tree1.query(x - 1)
		tree1.update(x, profits[i])
	}

	for i := n - 1; i >= 0; i-- {
		x := m + 1 - prices[i]
		right[i] = tree2.query(x - 1)
		tree2.update(x, profits[i])
	}

	ans := -1

	for i := 0; i < n; i++ {
		if left[i] > 0 && right[i] > 0 {
			ans = max(ans, left[i]+profits[i]+right[i])
		}
	}

	return ans
}
```

```ts
class BinaryIndexedTree {
    private n: number;
    private c: number[];

    constructor(n: number) {
        this.n = n;
        this.c = Array(n + 1).fill(0);
    }

    update(x: number, v: number): void {
        while (x <= this.n) {
            this.c[x] = Math.max(this.c[x], v);
            x += x & -x;
        }
    }

    query(x: number): number {
        let mx = 0;
        while (x > 0) {
            mx = Math.max(mx, this.c[x]);
            x -= x & -x;
        }
        return mx;
    }
}

function maxProfit(prices: number[], profits: number[]): number {
    const n: number = prices.length;
    const left: number[] = Array(n).fill(0);
    const right: number[] = Array(n).fill(0);
    const m = Math.max(...prices);

    const tree1: BinaryIndexedTree = new BinaryIndexedTree(m + 1);
    const tree2: BinaryIndexedTree = new BinaryIndexedTree(m + 1);

    for (let i = 0; i < n; i++) {
        const x: number = prices[i];
        left[i] = tree1.query(x - 1);
        tree1.update(x, profits[i]);
    }

    for (let i = n - 1; i >= 0; i--) {
        const x: number = m + 1 - prices[i];
        right[i] = tree2.query(x - 1);
        tree2.update(x, profits[i]);
    }

    let ans: number = -1;

    for (let i = 0; i < n; i++) {
        if (left[i] > 0 && right[i] > 0) {
            ans = Math.max(ans, left[i] + profits[i] + right[i]);
        }
    }

    return ans;
}
```

```rust
struct BinaryIndexedTree {
    n: usize,
    c: Vec<i32>,
}

impl BinaryIndexedTree {
    fn new(n: usize) -> BinaryIndexedTree {
        BinaryIndexedTree {
            n,
            c: vec![0; n + 1],
        }
    }

    fn update(&mut self, x: usize, v: i32) {
        let mut x = x;
        while x <= self.n {
            self.c[x] = self.c[x].max(v);
            x += x & x.wrapping_neg();
        }
    }

    fn query(&self, x: usize) -> i32 {
        let mut x = x;
        let mut mx = 0;
        while x > 0 {
            mx = mx.max(self.c[x]);
            x -= x & x.wrapping_neg();
        }
        mx
    }
}

impl Solution {
    pub fn max_profit(prices: Vec<i32>, profits: Vec<i32>) -> i32 {
        let n = prices.len();
        let mut left = vec![0; n];
        let mut right = vec![0; n];
        let m = prices.iter().cloned().max().unwrap_or(0);

        let mut tree1 = BinaryIndexedTree::new((m as usize) + 1);
        let mut tree2 = BinaryIndexedTree::new((m as usize) + 1);

        for i in 0..n {
            let x = prices[i] as usize;
            left[i] = tree1.query(x - 1);
            tree1.update(x, profits[i]);
        }

        for i in (0..n).rev() {
            let x = (m + 1 - prices[i]) as usize;
            right[i] = tree2.query(x - 1);
            tree2.update(x, profits[i]);
        }

        let mut ans = -1;
        for i in 0..n {
            if left[i] > 0 && right[i] > 0 {
                ans = ans.max(left[i] + profits[i] + right[i]);
            }
        }

        ans
    }
}
```

<!-- tabs:end -->

### Solution 3

<!-- tabs:start -->

```python
class BinaryIndexedTree:
    def __init__(self, n: int):
        self.n = n
        self.c = [0] * (n + 1)

    def update(self, x: int, v: int):
        while x <= self.n:
            self.c[x] = max(self.c[x], v)
            x += x & -x

    def query(self, x: int) -> int:
        mx = 0
        while x:
            mx = max(mx, self.c[x])
            x -= x & -x
        return mx


class Solution:
    def maxProfit(self, prices: List[int], profits: List[int]) -> int:
        n = len(prices)
        left = [0] * n
        right = [0] * n

        s = sorted(set(prices))
        m = len(s)
        tree1 = BinaryIndexedTree(m + 1)
        tree2 = BinaryIndexedTree(m + 1)

        for i, x in enumerate(prices):
            x = bisect_left(s, x) + 1
            left[i] = tree1.query(x - 1)
            tree1.update(x, profits[i])
        for i in range(n - 1, -1, -1):
            x = m + 1 - (bisect_left(s, prices[i]) + 1)
            right[i] = tree2.query(x - 1)
            tree2.update(x, profits[i])

        return max(
            (l + x + r for l, x, r in zip(left, profits, right) if l and r), default=-1
        )
```

```java
class BinaryIndexedTree {
    private int n;
    private int[] c;

    public BinaryIndexedTree(int n) {
        this.n = n;
        c = new int[n + 1];
    }

    public void update(int x, int v) {
        while (x <= n) {
            c[x] = Math.max(c[x], v);
            x += x & -x;
        }
    }

    public int query(int x) {
        int mx = 0;
        while (x > 0) {
            mx = Math.max(mx, c[x]);
            x -= x & -x;
        }
        return mx;
    }
}

class Solution {
    public int maxProfit(int[] prices, int[] profits) {
        int n = prices.length;
        int[] left = new int[n];
        int[] right = new int[n];
        int[] s = prices.clone();
        Arrays.sort(s);
        int m = 0;
        for (int i = 0; i < n; ++i) {
            if (i == 0 || s[i] != s[i - 1]) {
                s[m++] = s[i];
            }
        }
        BinaryIndexedTree tree1 = new BinaryIndexedTree(m + 1);
        BinaryIndexedTree tree2 = new BinaryIndexedTree(m + 1);
        for (int i = 0; i < n; ++i) {
            int x = search(s, prices[i], m);
            left[i] = tree1.query(x - 1);
            tree1.update(x, profits[i]);
        }
        for (int i = n - 1; i >= 0; --i) {
            int x = m + 1 - search(s, prices[i], m);
            right[i] = tree2.query(x - 1);
            tree2.update(x, profits[i]);
        }
        int ans = -1;
        for (int i = 0; i < n; ++i) {
            if (left[i] > 0 && right[i] > 0) {
                ans = Math.max(ans, left[i] + profits[i] + right[i]);
            }
        }
        return ans;
    }

    private int search(int[] nums, int x, int r) {
        int l = 0;
        while (l < r) {
            int mid = (l + r) >> 1;
            if (nums[mid] >= x) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l + 1;
    }
}
```

```cpp
class BinaryIndexedTree {
private:
    int n;
    vector<int> c;

public:
    BinaryIndexedTree(int n) {
        this->n = n;
        c.resize(n + 1, 0);
    }

    void update(int x, int v) {
        while (x <= n) {
            c[x] = max(c[x], v);
            x += x & -x;
        }
    }

    int query(int x) {
        int mx = 0;
        while (x > 0) {
            mx = max(mx, c[x]);
            x -= x & -x;
        }
        return mx;
    }
};

class Solution {
public:
    int maxProfit(vector<int>& prices, vector<int>& profits) {
        int n = prices.size();
        vector<int> left(n);
        vector<int> right(n);
        vector<int> s = prices;
        sort(s.begin(), s.end());
        s.erase(unique(s.begin(), s.end()), s.end());
        int m = s.size();
        BinaryIndexedTree tree1(m + 1);
        BinaryIndexedTree tree2(m + 1);
        for (int i = 0; i < n; ++i) {
            int x = lower_bound(s.begin(), s.end(), prices[i]) - s.begin() + 1;
            left[i] = tree1.query(x - 1);
            tree1.update(x, profits[i]);
        }
        for (int i = n - 1; i >= 0; --i) {
            int x = m + 1 - (lower_bound(s.begin(), s.end(), prices[i]) - s.begin() + 1);
            right[i] = tree2.query(x - 1);
            tree2.update(x, profits[i]);
        }
        int ans = -1;
        for (int i = 0; i < n; ++i) {
            if (left[i] > 0 && right[i] > 0) {
                ans = max(ans, left[i] + profits[i] + right[i]);
            }
        }
        return ans;
    }
};
```

```go
type BinaryIndexedTree struct {
	n int
	c []int
}

func NewBinaryIndexedTree(n int) BinaryIndexedTree {
	c := make([]int, n+1)
	return BinaryIndexedTree{n: n, c: c}
}

func (bit *BinaryIndexedTree) update(x, v int) {
	for x <= bit.n {
		bit.c[x] = max(bit.c[x], v)
		x += x & -x
	}
}

func (bit *BinaryIndexedTree) query(x int) int {
	mx := 0
	for x > 0 {
		mx = max(mx, bit.c[x])
		x -= x & -x
	}
	return mx
}

func maxProfit(prices []int, profits []int) int {
	n := len(prices)
	left := make([]int, n)
	right := make([]int, n)
	s := make([]int, n)
	copy(s, prices)
	sort.Ints(s)
	m := 0
	for i, x := range s {
		if i == 0 || x != s[i-1] {
			s[m] = x
			m++
		}
	}

	tree1 := NewBinaryIndexedTree(m + 1)
	tree2 := NewBinaryIndexedTree(m + 1)

	for i, x := range prices {
		x = sort.SearchInts(s[:m], x) + 1
		left[i] = tree1.query(x - 1)
		tree1.update(x, profits[i])
	}

	for i := n - 1; i >= 0; i-- {
		x := m + 1 - (sort.SearchInts(s[:m], prices[i]) + 1)
		right[i] = tree2.query(x - 1)
		tree2.update(x, profits[i])
	}

	ans := -1

	for i := 0; i < n; i++ {
		if left[i] > 0 && right[i] > 0 {
			ans = max(ans, left[i]+profits[i]+right[i])
		}
	}

	return ans
}
```

```ts
class BinaryIndexedTree {
    private n: number;
    private c: number[];

    constructor(n: number) {
        this.n = n;
        this.c = Array(n + 1).fill(0);
    }

    update(x: number, v: number): void {
        while (x <= this.n) {
            this.c[x] = Math.max(this.c[x], v);
            x += x & -x;
        }
    }

    query(x: number): number {
        let mx = 0;
        while (x > 0) {
            mx = Math.max(mx, this.c[x]);
            x -= x & -x;
        }
        return mx;
    }
}

function maxProfit(prices: number[], profits: number[]): number {
    const n: number = prices.length;
    const left: number[] = Array(n).fill(0);
    const right: number[] = Array(n).fill(0);

    const s = [...prices].sort((a, b) => a - b);
    let m = 0;
    for (let i = 0; i < n; ++i) {
        if (i === 0 || s[i] !== s[i - 1]) {
            s[m++] = s[i];
        }
    }
    s.length = m;

    const tree1: BinaryIndexedTree = new BinaryIndexedTree(m + 1);
    const tree2: BinaryIndexedTree = new BinaryIndexedTree(m + 1);

    const search = (nums: number[], x: number): number => {
        let [l, r] = [0, nums.length];
        while (l < r) {
            const mid = (l + r) >> 1;
            if (nums[mid] >= x) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    };

    for (let i = 0; i < n; ++i) {
        const x = search(s, prices[i]) + 1;
        left[i] = tree1.query(x - 1);
        tree1.update(x, profits[i]);
    }

    for (let i = n - 1; i >= 0; i--) {
        const x: number = m + 1 - (search(s, prices[i]) + 1);
        right[i] = tree2.query(x - 1);
        tree2.update(x, profits[i]);
    }

    let ans: number = -1;

    for (let i = 0; i < n; i++) {
        if (left[i] > 0 && right[i] > 0) {
            ans = Math.max(ans, left[i] + profits[i] + right[i]);
        }
    }

    return ans;
}
```

<!-- tabs:end -->

<!-- end -->
