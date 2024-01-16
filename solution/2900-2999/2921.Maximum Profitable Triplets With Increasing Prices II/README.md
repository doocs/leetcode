# [2921. 价格递增的最大利润三元组 II](https://leetcode.cn/problems/maximum-profitable-triplets-with-increasing-prices-ii)

[English Version](/solution/2900-2999/2921.Maximum%20Profitable%20Triplets%20With%20Increasing%20Prices%20II/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定长度为 <code>n</code>&nbsp; 的数组&nbsp;<code>prices</code>&nbsp;和&nbsp;<code>profits</code>&nbsp;（<strong>下标从 0 开始</strong>）。一个商店有&nbsp;<code>n</code>&nbsp;个商品，第&nbsp;<code>i</code>&nbsp;个商品的价格为&nbsp;<code>prices[i]</code>，利润为&nbsp;<code>profits[i]</code>。</p>

<p>需要选择三个商品，满足以下条件：</p>

<ul>
	<li><code>prices[i] &lt; prices[j] &lt; prices[k]</code>，其中&nbsp;<code>i &lt; j &lt; k</code>。</li>
</ul>

<p>如果选择的商品&nbsp;<code>i</code>, <code>j</code>&nbsp;和&nbsp;<code>k</code>&nbsp;满足以下条件，那么利润将等于&nbsp;<code>profits[i] + profits[j] + profits[k]</code>。</p>

<p>返回能够获得的<em>&nbsp;<strong>最大利润</strong>，如果不可能满足给定条件，</em>返回<em>&nbsp;</em><code>-1</code><em>。</em></p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<b>输入：</b>prices = [10,2,3,4], profits = [100,2,7,10]
<b>输出：</b>19
<b>解释：</b>不能选择下标 i=0 的商品，因为没有下标 j 和 k 的商品满足条件。
只能选择下标为 1、2、3 的三个商品，这是有效的选择，因为 prices[1] &lt; prices[2] &lt; prices[3]。
答案是它们的利润之和，即 2 + 7 + 10 = 19。</pre>

<p><strong>示例 2：</strong></p>

<pre>
<b>输入：</b>prices = [1,2,3,4,5], profits = [1,5,3,4,6]
<b>输出：</b>15
<b>解释：</b>可以选择任意三个商品，因为对于每组满足下标为 i &lt; j &lt; k 的三个商品，条件都成立。
因此，能得到的最大利润就是利润和最大的三个商品，即下标为 1，3 和 4 的商品。
答案就是它们的利润之和，即 5 + 4 + 6 = 15。</pre>

<p><strong>示例 3：</strong></p>

<pre>
<b>输入：</b>prices = [4,3,2,1], profits = [33,20,19,87]
<b>输出：</b>-1
<b>解释：</b>找不到任何可以满足条件的三个商品，所以返回 -1.
</pre>

<p>&nbsp;</p>

<p><b>提示：</b></p>

<ul>
	<li><code>3 &lt;= prices.length == profits.length &lt;= 50000</code></li>
	<li><code>1 &lt;= prices[i] &lt;= 5000</code></li>
	<li><code>1 &lt;= profits[i] &lt;= 10<sup>6</sup></code></li>
</ul>

## 解法

### 方法一：树状数组

我们可以用两个树状数组分别维护每个价格左边以及右边的最大利润，然后枚举中间的价格，通过树状数组查询左右两边的最大利润，最后取最大值即可。

时间复杂度 $O(n \times \log M)$，空间复杂度 $O(M)$。其中 $n$ 是数组 $prices$ 的长度，而 $M$ 是数组 $prices$ 中的最大值，本题中 $M \le 5000$。

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

<!-- end -->
