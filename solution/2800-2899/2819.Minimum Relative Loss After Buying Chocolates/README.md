# [2819. 购买巧克力后的最小相对损失](https://leetcode.cn/problems/minimum-relative-loss-after-buying-chocolates)

[English Version](/solution/2800-2899/2819.Minimum%20Relative%20Loss%20After%20Buying%20Chocolates/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>现给定一个整数数组 <code>prices</code>，表示巧克力的价格；以及一个二维整数数组 <code>queries</code>，其中 <code>queries[i] = [ki, mi]</code>。</p>

<p>Alice 和 Bob 去买巧克力，Alice 提出了一种付款方式，而 Bob 同意了。</p>

<p>对于每个 <code>queries[i]</code> ，它的条件如下：</p>

<ul>
	<li>如果一块巧克力的价格 <strong>小于等于</strong> <code>ki</code>，那么 Bob 为它付款。</li>
	<li>否则，Bob 为其中 <code>ki</code> 部分付款，而 Alice 为 <strong>剩余</strong> 部分付款。</li>
</ul>

<p>Bob 想要选择 <strong>恰好</strong> <code>mi</code> 块巧克力，使得他的 <strong>相对损失最小</strong> 。更具体地说，如果总共 Alice 付款了 <code>ai</code>，Bob 付款了 <code>bi</code>，那么 Bob 希望最小化 <code>bi - ai</code>。</p>

<p>返回一个整数数组 <code>ans</code>，其中 <code>ans[i]</code> 是 Bob 在&nbsp;<code>queries[i]</code> 中可能的 <strong>最小相对损失</strong> 。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<b>输入：</b>prices = [1,9,22,10,19], queries = [[18,4],[5,2]]
<b>输出：</b>[34,-21]
<b>解释：</b>对于第一个 query，Bob 选择价格为 [1,9,10,22] 的巧克力。他付了 1 + 9 + 10 + 18 = 38，Alice 付了 0 + 0 + 0 + 4 = 4。因此，Bob 的相对损失是 38 - 4 = 34。
对于第二个 query，Bob 选择价格为 [19,22] 的巧克力。他付了 5 + 5 = 10，Alice 付了 14 + 17 = 31。因此，Bob 的相对损失是 10 - 31 = -21。
可以证明这些是可能的最小相对损失。</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<b>输入：</b>prices = [1,5,4,3,7,11,9], queries = [[5,4],[5,7],[7,3],[4,5]]
<b>输出：</b>[4,16,7,1]
<b>解释：</b>对于第一个 query，Bob 选择价格为 [1,3,9,11] 的巧克力。他付了 1 + 3 + 5 + 5 = 14，Alice 付了 0 + 0 + 4 + 6 = 10。因此，Bob 的相对损失是 14 - 10 = 4。
对于第二个 query，Bob 必须选择所有的巧克力。他付了 1 + 5 + 4 + 3 + 5 + 5 + 5 = 28，Alice 付了 0 + 0 + 0 + 0 + 2 + 6 + 4 = 12。因此，Bob 的相对损失是 28 - 12 = 16。
对于第三个 query，Bob 选择价格为 [1,3,11] 的巧克力。他付了 1 + 3 + 7 = 11，Alice 付了 0 + 0 + 4 = 4。因此，Bob 的相对损失是 11 - 4 = 7。
对于第四个 query，Bob 选择价格为 [1,3,7,9,11] 的巧克力。他付了 1 + 3 + 4 + 4 + 4 = 16，Alice 付了 0 + 0 + 3 + 5 + 7 = 15。因此，Bob 的相对损失是 16 - 15 = 1。
可以证明这些是可能的最小相对损失。
</pre>

<p><strong class="example">示例 3：</strong></p>

<pre>
<b>输入：</b>prices = [5,6,7], queries = [[10,1],[5,3],[3,3]]
<b>输出：</b>[5,12,0]
<b>解释：</b>对于第一个 query，Bob 选择价格为 5 的巧克力。他付了 5，Alice 付了 0。因此，Bob 的相对损失是 5 - 0 = 5。
对于第二个 query，Bob 必须选择所有的巧克力。他付了 5 + 5 + 5 = 15，Alice 付了 0 + 1 + 2 = 3。因此，Bob 的相对损失是 15 - 3 = 12。
对于第三个 query，Bob 必须选择所有的巧克力。他付了 3 + 3 + 3 = 9，Alice 付了 2 + 3 + 4 = 9。因此，Bob 的相对损失是 9 - 9 = 0。
可以证明这些是可能的最小相对损失。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= prices.length == n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= prices[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= queries.length &lt;= 10<sup>5</sup></code></li>
	<li><code>queries[i].length == 2</code></li>
	<li><code>1 &lt;= k<sub>i</sub> &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= m<sub>i</sub> &lt;= n</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：排序 + 二分查找 + 前缀和**

根据题目描述，我们可以知道：

如果 $prices[i] \leq k$，那么 Bob 需要支付 $prices[i]$，而 Alice 不需要支付。因此 Bob 的相对损失为 $prices[i]$。在这种情况下，Bob 应该选择价格较低的巧克力，才能最小化相对损失。

如果 $prices[i] \gt k$，那么 Bob 需要支付 $k$，而 Alice 需要支付 $prices[i] - k$。因此 Bob 的相对损失为 $k - (prices[i] - k) = 2k - prices[i]$。在这种情况下，Bob 应该选择价格较高的巧克力，才能最小化相对损失。

因此，我们先对价格数组 $prices$ 进行排序，然后预处理出前缀和数组 $s$，其中 $s[i]$ 表示前 $i$ 个巧克力的价格之和。

接下来，对于每个询问 $[k, m]$，我们先使用二分查找，找到第一个价格大于 $k$ 的巧克力的下标 $r$。然后，再利用二分查找，找到左侧应该选择的巧克力的数量 $l$，那么右侧应该选择的巧克力的数量就是 $m - l$。此时，Bob 的相对损失为 $s[l] + 2k(m - l) - (s[n] - s[n - (m - l)])$。

上述第二次二分查找的过程中，我们需要判断 $prices[mid] \lt 2k - prices[n - (m - mid)]$，其中 $right$ 表示右侧应该选择的巧克力的数量。如果该不等式成立，那么说明选择 $mid$ 位置的巧克力的相对损失较低，此时更新 $l = mid + 1$。否则，说明 $mid$ 位置的巧克力的相对损失较高，此时更新 $r = mid$。

时间复杂度 $O((n + m) \times \log n)$，空间复杂度 $O(n)$。其中 $n$ 和 $m$ 分别是数组 $prices$ 和 $queries$ 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

### **C++**

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

### **Go**

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

### **TypeScript**

```ts
function minimumRelativeLosses(
    prices: number[],
    queries: number[][],
): number[] {
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

### **...**

```

```

<!-- tabs:end -->
