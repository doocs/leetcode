---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2800-2899/2819.Minimum%20Relative%20Loss%20After%20Buying%20Chocolates/README_EN.md
tags:
    - Array
    - Binary Search
    - Prefix Sum
    - Sorting
---

<!-- problem:start -->

# [2819. Minimum Relative Loss After Buying Chocolates 🔒](https://leetcode.com/problems/minimum-relative-loss-after-buying-chocolates)

[中文文档](/solution/2800-2899/2819.Minimum%20Relative%20Loss%20After%20Buying%20Chocolates/README.md)

## Description

<p>You are given an integer array <code>prices</code>, which shows the chocolate prices and a 2D integer array <code>queries</code>, where <code>queries[i] = [k<sub>i</sub>, m<sub>i</sub>]</code>.</p>

<p>Alice and Bob went to buy some chocolates, and Alice suggested a way to pay for them, and Bob agreed.</p>

<p>The terms for each query are as follows:</p>

<ul>
	<li>If the price of a chocolate is <strong>less than or equal to</strong> <code>k<sub>i</sub></code>, Bob pays for it.</li>
	<li>Otherwise, Bob pays <code>k<sub>i</sub></code> of it, and Alice pays the <strong>rest</strong>.</li>
</ul>

<p>Bob wants to select <strong>exactly</strong> <code>m<sub>i</sub></code> chocolates such that his <strong>relative loss</strong> is <strong>minimized</strong>, more formally, if, in total, Alice has paid <code>a<sub>i</sub></code> and Bob has paid <code>b<sub>i</sub></code>, Bob wants to minimize <code>b<sub>i</sub> - a<sub>i</sub></code>.</p>

<p>Return <em>an integer array</em> <code>ans</code> <em>where</em> <code>ans[i]</code> <em>is Bob&#39;s <strong>minimum relative loss </strong>possible for</em> <code>queries[i]</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> prices = [1,9,22,10,19], queries = [[18,4],[5,2]]
<strong>Output:</strong> [34,-21]
<strong>Explanation:</strong> For the 1<sup>st</sup> query Bob selects the chocolates with prices [1,9,10,22]. He pays 1 + 9 + 10 + 18 = 38 and Alice pays 0 + 0 + 0 + 4 = 4. So Bob&#39;s relative loss is 38 - 4 = 34.
For the 2<sup>nd</sup> query Bob selects the chocolates with prices [19,22]. He pays 5 + 5 = 10 and Alice pays 14 + 17 = 31. So Bob&#39;s relative loss is 10 - 31 = -21.
It can be shown that these are the minimum possible relative losses.</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> prices = [1,5,4,3,7,11,9], queries = [[5,4],[5,7],[7,3],[4,5]]
<strong>Output:</strong> [4,16,7,1]
<strong>Explanation:</strong> For the 1<sup>st</sup> query Bob selects the chocolates with prices [1,3,9,11]. He pays 1 + 3 + 5 + 5 = 14 and Alice pays 0 + 0 + 4 + 6 = 10. So Bob&#39;s relative loss is 14 - 10 = 4.
For the 2<sup>nd</sup> query Bob has to select all the chocolates. He pays 1 + 5 + 4 + 3 + 5 + 5 + 5 = 28 and Alice pays 0 + 0 + 0 + 0 + 2 + 6 + 4 = 12. So Bob&#39;s relative loss is 28 - 12 = 16.
For the 3<sup>rd</sup> query Bob selects the chocolates with prices [1,3,11] and he pays 1 + 3 + 7 = 11 and Alice pays 0 + 0 + 4 = 4. So Bob&#39;s relative loss is 11 - 4 = 7.
For the 4<sup>th</sup> query Bob selects the chocolates with prices [1,3,7,9,11] and he pays 1 + 3 + 4 + 4 + 4 = 16 and Alice pays 0 + 0 + 3 + 5 + 7 = 15. So Bob&#39;s relative loss is 16 - 15 = 1.
It can be shown that these are the minimum possible relative losses.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> prices = [5,6,7], queries = [[10,1],[5,3],[3,3]]
<strong>Output:</strong> [5,12,0]
<strong>Explanation:</strong> For the 1<sup>st</sup> query Bob selects the chocolate with price 5 and he pays 5 and Alice pays 0. So Bob&#39;s relative loss is 5 - 0 = 5.
For the 2<sup>nd</sup> query Bob has to select all the chocolates. He pays 5 + 5 + 5 = 15 and Alice pays 0 + 1 + 2 = 3. So Bob&#39;s relative loss is 15 - 3 = 12.
For the 3<sup>rd</sup> query Bob has to select all the chocolates. He pays 3 + 3 + 3 = 9 and Alice pays 2 + 3 + 4 = 9. So Bob&#39;s relative loss is 9 - 9 = 0.
It can be shown that these are the minimum possible relative losses.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= prices.length == n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= prices[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= queries.length &lt;= 10<sup>5</sup></code></li>
	<li><code>queries[i].length == 2</code></li>
	<li><code>1 &lt;= k<sub>i</sub> &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= m<sub>i</sub> &lt;= n</code></li>
</ul>

## Solutions

<!-- solution:start -->

### Solution 1: Sorting + Binary Search + Prefix Sum

Based on the problem description, we know:

If $prices[i] \leq k$, then Bob needs to pay $prices[i]$, and Alice doesn't need to pay. Therefore, Bob's relative loss is $prices[i]$. In this case, Bob should choose the chocolate with a lower price to minimize the relative loss.

If $prices[i] > k$, then Bob needs to pay $k$, and Alice needs to pay $prices[i] - k$. Therefore, Bob's relative loss is $k - (prices[i] - k) = 2k - prices[i]$. In this case, Bob should choose the chocolate with a higher price to minimize the relative loss.

Therefore, we first sort the price array $prices$, and then preprocess the prefix sum array $s$, where $s[i]$ represents the sum of the prices of the first $i$ chocolates.

Next, for each query $[k, m]$, we first use binary search to find the index $r$ of the first chocolate with a price greater than $k$. Then, we use binary search again to find the number of chocolates $l$ that should be chosen on the left, so the number of chocolates that should be chosen on the right is $m - l$. At this point, Bob's relative loss is $s[l] + 2k(m - l) - (s[n] - s[n - (m - l)])$.

In the second binary search process mentioned above, we need to judge whether $prices[mid] < 2k - prices[n - (m - mid)]$, where $right$ represents the number of chocolates that should be chosen on the right. If this inequality holds, it means that choosing the chocolate at position $mid$ has a lower relative loss, so we update $l = mid + 1$. Otherwise, it means that the chocolate at position $mid$ has a higher relative loss, so we update $r = mid$.

The time complexity is $O((n + m) \times \log n)$, and the space complexity is $O(n)$. Where $n$ and $m$ are the lengths of the arrays $prices$ and $queries$, respectively.

<!-- tabs:start -->

```python
class Solution:
    def minimumRelativeLosses(
        self, prices: List[int], queries: List[List[int]]
    ) -> List[int]:
        def f(k: int, m: int) -> int:
            l, r = 0, min(m, bisect_right(prices, k))
            while l < r:
                mid = (l + r) >> 1
                right = m - mid
                if prices[mid] < 2 * k - prices[n - right]:
                    l = mid + 1
                else:
                    r = mid
            return l

        prices.sort()
        s = list(accumulate(prices, initial=0))
        ans = []
        n = len(prices)
        for k, m in queries:
            l = f(k, m)
            r = m - l
            loss = s[l] + 2 * k * r - (s[n] - s[n - r])
            ans.append(loss)
        return ans
```

```java
class Solution {
    private int n;
    private int[] prices;

    public long[] minimumRelativeLosses(int[] prices, int[][] queries) {
        n = prices.length;
        Arrays.sort(prices);
        this.prices = prices;
        long[] s = new long[n + 1];
        for (int i = 0; i < n; ++i) {
            s[i + 1] = s[i] + prices[i];
        }
        int q = queries.length;
        long[] ans = new long[q];
        for (int i = 0; i < q; ++i) {
            int k = queries[i][0], m = queries[i][1];
            int l = f(k, m);
            int r = m - l;
            ans[i] = s[l] + 2L * k * r - (s[n] - s[n - r]);
        }
        return ans;
    }

    private int f(int k, int m) {
        int l = 0, r = Arrays.binarySearch(prices, k);
        if (r < 0) {
            r = -(r + 1);
        }
        r = Math.min(m, r);
        while (l < r) {
            int mid = (l + r) >> 1;
            int right = m - mid;
            if (prices[mid] < 2L * k - prices[n - right]) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        return l;
    }
}
```

```cpp
class Solution {
public:
    vector<long long> minimumRelativeLosses(vector<int>& prices, vector<vector<int>>& queries) {
        int n = prices.size();
        sort(prices.begin(), prices.end());
        long long s[n + 1];
        s[0] = 0;
        for (int i = 1; i <= n; ++i) {
            s[i] = s[i - 1] + prices[i - 1];
        }
        auto f = [&](int k, int m) {
            int l = 0, r = upper_bound(prices.begin(), prices.end(), k) - prices.begin();
            r = min(r, m);
            while (l < r) {
                int mid = (l + r) >> 1;
                int right = m - mid;
                if (prices[mid] < 2LL * k - prices[n - right]) {
                    l = mid + 1;
                } else {
                    r = mid;
                }
            }
            return l;
        };
        vector<long long> ans;
        for (auto& q : queries) {
            int k = q[0], m = q[1];
            int l = f(k, m);
            int r = m - l;
            ans.push_back(s[l] + 2LL * k * r - (s[n] - s[n - r]));
        }
        return ans;
    }
};
```

```go
func minimumRelativeLosses(prices []int, queries [][]int) []int64 {
	n := len(prices)
	sort.Ints(prices)
	s := make([]int, n+1)
	for i, x := range prices {
		s[i+1] = s[i] + x
	}
	f := func(k, m int) int {
		l, r := 0, sort.Search(n, func(i int) bool { return prices[i] > k })
		if r > m {
			r = m
		}
		for l < r {
			mid := (l + r) >> 1
			right := m - mid
			if prices[mid] < 2*k-prices[n-right] {
				l = mid + 1
			} else {
				r = mid
			}
		}
		return l
	}
	ans := make([]int64, len(queries))
	for i, q := range queries {
		k, m := q[0], q[1]
		l := f(k, m)
		r := m - l
		ans[i] = int64(s[l] + 2*k*r - (s[n] - s[n-r]))
	}
	return ans
}
```

```ts
function minimumRelativeLosses(prices: number[], queries: number[][]): number[] {
    const n = prices.length;
    prices.sort((a, b) => a - b);
    const s: number[] = Array(n).fill(0);
    for (let i = 0; i < n; ++i) {
        s[i + 1] = s[i] + prices[i];
    }

    const search = (x: number): number => {
        let l = 0;
        let r = n;
        while (l < r) {
            const mid = (l + r) >> 1;
            if (prices[mid] > x) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    };

    const f = (k: number, m: number): number => {
        let l = 0;
        let r = Math.min(search(k), m);
        while (l < r) {
            const mid = (l + r) >> 1;
            const right = m - mid;
            if (prices[mid] < 2 * k - prices[n - right]) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        return l;
    };
    const ans: number[] = [];
    for (const [k, m] of queries) {
        const l = f(k, m);
        const r = m - l;
        ans.push(s[l] + 2 * k * r - (s[n] - s[n - r]));
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
