---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3100-3199/3145.Find%20Products%20of%20Elements%20of%20Big%20Array/README.md
rating: 2859
source: 第 130 场双周赛 Q4
tags:
    - 位运算
    - 数组
    - 二分查找
---

<!-- problem:start -->

# [3145. 大数组元素的乘积](https://leetcode.cn/problems/find-products-of-elements-of-big-array)

[English Version](/solution/3100-3199/3145.Find%20Products%20of%20Elements%20of%20Big%20Array/README_EN.md)

## 题目描述

<!-- description:start -->

<p>一个非负整数 <code>x</code>&nbsp;的 <strong>强数组</strong>&nbsp;指的是满足元素为 2 的幂且元素总和为 <code>x</code> 的最短有序数组。下表说明了如何确定 <strong>强数组</strong> 的示例。可以证明，<code>x</code>&nbsp;对应的强数组是独一无二的。</p>

<table border="1">
	<tbody>
		<tr>
			<th>数字</th>
			<th>二进制表示</th>
			<th>强数组</th>
		</tr>
		<tr>
			<td>1</td>
			<td>0000<u>1</u></td>
			<td>[1]</td>
		</tr>
		<tr>
			<td>8</td>
			<td>0<u>1</u>000</td>
			<td>[8]</td>
		</tr>
		<tr>
			<td>10</td>
			<td>0<u>1</u>0<u>1</u>0</td>
			<td>[2, 8]</td>
		</tr>
		<tr>
			<td>13</td>
			<td>0<u>11</u>0<u>1</u></td>
			<td>[1, 4, 8]</td>
		</tr>
		<tr>
			<td>23</td>
			<td><u>1</u>0<u>111</u></td>
			<td>[1, 2, 4, 16]</td>
		</tr>
	</tbody>
</table>

<p>&nbsp;</p>

<p>我们将每一个升序的正整数 <code>i</code>&nbsp;（即1，2，3等等）的 <strong>强数组</strong>&nbsp;连接得到数组&nbsp;<code>big_nums</code>&nbsp;，<code>big_nums</code>&nbsp;开始部分为&nbsp;<code>[<u>1</u>, <u>2</u>, <u>1, 2</u>, <u>4</u>, <u>1, 4</u>, <u>2, 4</u>, <u>1, 2, 4</u>, <u>8</u>, ...]</code>&nbsp;。</p>

<p>给你一个二维整数数组&nbsp;<code>queries</code>&nbsp;，其中&nbsp;<code>queries[i] = [from<sub>i</sub>, to<sub>i</sub>, mod<sub>i</sub>]</code>&nbsp;，你需要计算&nbsp;<code>(big_nums[from<sub>i</sub>] * big_nums[from<sub>i</sub> + 1] * ... * big_nums[to<sub>i</sub>]) % mod<sub>i</sub></code>&nbsp;。</p>

<p>请你返回一个整数数组&nbsp;<code>answer</code>&nbsp;，其中&nbsp;<code>answer[i]</code>&nbsp;是第 <code>i</code>&nbsp;个查询的答案。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><b>输入：</b>queries = [[1,3,7]]</p>

<p><b>输出：</b>[4]</p>

<p><strong>解释：</strong></p>

<p>只有一个查询。</p>

<p><code>big_nums[1..3] = [2,1,2]</code>&nbsp;。它们的乘积为 4。结果为&nbsp;<code>4 % 7 = 4</code>。</p>

<p><strong>示例 2：</strong></p>

<p><b>输入：</b>queries = [[2,5,3],[7,7,4]]</p>

<p><b>输出：</b>[2,2]</p>

<p><strong>解释：</strong></p>

<p>有两个查询。</p>

<p>第一个查询：<code>big_nums[2..5] = [1,2,4,1]</code>&nbsp;。它们的乘积为 8 。结果为&nbsp; <code>8 % 3 = 2</code>。</p>

<p>第二个查询：<code>big_nums[7] = 2</code>&nbsp;。结果为 <code>2 % 4 = 2</code>。</p>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= queries.length &lt;= 500</code></li>
	<li><code>queries[i].length == 3</code></li>
	<li><code>0 &lt;= queries[i][0] &lt;= queries[i][1] &lt;= 10<sup>15</sup></code></li>
	<li><code>1 &lt;= queries[i][2] &lt;= 10<sup>5</sup></code></li>
</ul>

<p>&nbsp;</p>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：二分查找 + 位运算

连续的正整数数字对应的强整数数组连接得到数组 $\textit{bignums}$，题目需要我们求出对于每个查询 $[\textit{left}, \textit{right}, \textit{mod}]$，子数组 $\textit{bignums}[\textit{left}..\textit{right}]$ 的乘积对 $\textit{mod}$ 取模的结果。由于子数组每个元素都是 $2$ 的幂，这等价于求子数组的幂次之和 $\textit{power}$，然后计算 $2^{\textit{power}} \bmod \textit{mod}$。例如，对于子数组 $[1, 4, 8]$，即 $[2^0, 2^2, 2^3]$，其幂次之和为 $0 + 2 + 3 = 5$，所以 $2^5 \bmod \textit{mod}$ 就是我们要求的结果。

因此，我们不妨将 $\textit{bignums}$ 转换为幂次数组，即对于子数组 $[1, 4, 8]$，我们将其转换为 $[0, 2, 3]$。这样，问题转换为求幂次数组的子数组之和，即 $\textit{power} = \textit{f}(\textit{right} + 1) - \textit{f}(\textit{left})$，其中 $\textit{f}(i)$ 表示 $\textit{bignums}[0..i)$ 的幂次之和，也即是前缀和。

接下来，就是根据下标 $i$ 计算 $\textit{f}(i)$ 的值。我们可以使用二分查找的方法，先找到强数组长度和小于 $i$ 的最大数字，然后再计算剩下的数字的幂次之和。

我们根据题目描述，列出数字 $0..14$ 的强整数：

| $\textit{nums}$ | 8($2^3$)                              | 4($2^2$)                              | 2($2^1$)                              | ($2^0$)                               |
| --------------- | ------------------------------------- | ------------------------------------- | ------------------------------------- | ------------------------------------- |
| 0               | 0                                     | 0                                     | 0                                     | 0                                     |
| 1               | <span style="color: red;">0</span>    | <span style="color: red;">0</span>    | <span style="color: red;">0</span>    | <span style="color: red;">1</span>    |
| 2               | <span style="color: blue;">0</span>   | <span style="color: blue;">0</span>   | <span style="color: blue;">1</span>   | <span style="color: blue;">0</span>   |
| 3               | <span style="color: blue;">0</span>   | <span style="color: blue;">0</span>   | <span style="color: blue;">1</span>   | <span style="color: blue;">1</span>   |
| 4               | <span style="color: green;">0</span>  | <span style="color: green;">1</span>  | <span style="color: green;">0</span>  | <span style="color: green;">0</span>  |
| 5               | <span style="color: green;">0</span>  | <span style="color: green;">1</span>  | <span style="color: green;">0</span>  | <span style="color: green;">1</span>  |
| 6               | <span style="color: green;">0</span>  | <span style="color: green;">1</span>  | <span style="color: green;">1</span>  | <span style="color: green;">0</span>  |
| 7               | <span style="color: green;">0</span>  | <span style="color: green;">1</span>  | <span style="color: green;">1</span>  | <span style="color: green;">1</span>  |
| 8               | <span style="color: yellow;">1</span> | <span style="color: yellow;">0</span> | <span style="color: yellow;">0</span> | <span style="color: yellow;">0</span> |
| 9               | <span style="color: yellow;">1</span> | <span style="color: yellow;">0</span> | <span style="color: yellow;">0</span> | <span style="color: yellow;">1</span> |
| 10              | <span style="color: yellow;">1</span> | <span style="color: yellow;">0</span> | <span style="color: yellow;">1</span> | <span style="color: yellow;">0</span> |
| 11              | <span style="color: yellow;">1</span> | <span style="color: yellow;">0</span> | <span style="color: yellow;">1</span> | <span style="color: yellow;">1</span> |
| 12              | <span style="color: yellow;">1</span> | <span style="color: yellow;">1</span> | <span style="color: yellow;">0</span> | <span style="color: yellow;">0</span> |
| 13              | <span style="color: yellow;">1</span> | <span style="color: yellow;">1</span> | <span style="color: yellow;">0</span> | <span style="color: yellow;">1</span> |
| 14              | <span style="color: yellow;">1</span> | <span style="color: yellow;">1</span> | <span style="color: yellow;">1</span> | <span style="color: yellow;">0</span> |

将数字按照 $[2^i, 2^{i+1}-1]$ 的区间划分为不同的颜色，可以发现，区间 $[2^i, 2^{i+1}-1]$ 的数字，相当于在区间 $[0, 2^i-1]$ 的数字基础上，每个数字加上 $2^i$。我们可以根据这个规律，计算出 $\textit{bignums}$ 的前 $i$ 组的所有数字的强数组个数之和 $\textit{cnt}[i]$ 和幂次之和 $\textit{s}[i]$。

接下来，对于任何数字，我们考虑如何计算其强数组的个数和幂次之和。我们可以通过二进制的方式，从最高位开始，诸位计算。例如，对于数字 $13 = 2^3 + 2^2 + 2^0$，前 $2^3$ 个数字的结果可以由 $textit{cnt}[3]$ 和 $\textit{s}[3]$ 计算得到，而剩下的 $[2^3, 13]$ 的结果，相当于给 $[0, 13-2^3]$ 的所有数字，即 $[0, 5]$ 的所有数字的强数组增加 $3$，问题转换为计算 $[0, 5]$ 的所有数字的强数组的个数和幂次之和。这样，我们可以计算出任意数字的强数组的个数和幂次之和。

最后，我们可以根据 $\textit{power}$ 的值，利用快速幂的方法，计算出 $2^{\textit{power}} \bmod \textit{mod}$ 的结果。

时间复杂度 $O(q \times \log M)$，空间复杂度 $(\log M)$。其中 $q$ 为查询的个数，而 $M$ 为数字的上界，本题中 $M \le 10^{15}$。

<!-- tabs:start -->

#### Python3

```python
m = 50
cnt = [0] * (m + 1)
s = [0] * (m + 1)
p = 1
for i in range(1, m + 1):
    cnt[i] = cnt[i - 1] * 2 + p
    s[i] = s[i - 1] * 2 + p * (i - 1)
    p *= 2


def num_idx_and_sum(x: int) -> tuple:
    idx = 0
    total_sum = 0
    while x:
        i = x.bit_length() - 1
        idx += cnt[i]
        total_sum += s[i]
        x -= 1 << i
        total_sum += (x + 1) * i
        idx += x + 1
    return (idx, total_sum)


def f(i: int) -> int:
    l, r = 0, 1 << m
    while l < r:
        mid = (l + r + 1) >> 1
        idx, _ = num_idx_and_sum(mid)
        if idx < i:
            l = mid
        else:
            r = mid - 1

    total_sum = 0
    idx, total_sum = num_idx_and_sum(l)
    i -= idx
    x = l + 1
    for _ in range(i):
        y = x & -x
        total_sum += y.bit_length() - 1
        x -= y
    return total_sum


class Solution:
    def findProductsOfElements(self, queries: List[List[int]]) -> List[int]:
        return [pow(2, f(right + 1) - f(left), mod) for left, right, mod in queries]
```

#### Java

```java
class Solution {
    private static final int M = 50;
    private static final long[] cnt = new long[M + 1];
    private static final long[] s = new long[M + 1];

    static {
        long p = 1;
        for (int i = 1; i <= M; i++) {
            cnt[i] = cnt[i - 1] * 2 + p;
            s[i] = s[i - 1] * 2 + p * (i - 1);
            p *= 2;
        }
    }

    private static long[] numIdxAndSum(long x) {
        long idx = 0;
        long totalSum = 0;
        while (x > 0) {
            int i = Long.SIZE - Long.numberOfLeadingZeros(x) - 1;
            idx += cnt[i];
            totalSum += s[i];
            x -= 1L << i;
            totalSum += (x + 1) * i;
            idx += x + 1;
        }
        return new long[] {idx, totalSum};
    }

    private static long f(long i) {
        long l = 0;
        long r = 1L << M;
        while (l < r) {
            long mid = (l + r + 1) >> 1;
            long[] idxAndSum = numIdxAndSum(mid);
            long idx = idxAndSum[0];
            if (idx < i) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }

        long[] idxAndSum = numIdxAndSum(l);
        long totalSum = idxAndSum[1];
        long idx = idxAndSum[0];
        i -= idx;
        long x = l + 1;
        for (int j = 0; j < i; j++) {
            long y = x & -x;
            totalSum += Long.numberOfTrailingZeros(y);
            x -= y;
        }
        return totalSum;
    }

    public int[] findProductsOfElements(long[][] queries) {
        int n = queries.length;
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            long left = queries[i][0];
            long right = queries[i][1];
            long mod = queries[i][2];
            long power = f(right + 1) - f(left);
            ans[i] = qpow(2, power, mod);
        }
        return ans;
    }

    private int qpow(long a, long n, long mod) {
        long ans = 1 % mod;
        for (; n > 0; n >>= 1) {
            if ((n & 1) == 1) {
                ans = ans * a % mod;
            }
            a = a * a % mod;
        }
        return (int) ans;
    }
}
```

#### C++

```cpp
using ll = long long;
const int m = 50;
ll cnt[m + 1];
ll s[m + 1];
ll p = 1;

auto init = [] {
    cnt[0] = 0;
    s[0] = 0;
    for (int i = 1; i <= m; ++i) {
        cnt[i] = cnt[i - 1] * 2 + p;
        s[i] = s[i - 1] * 2 + p * (i - 1);
        p *= 2;
    }
    return 0;
}();

pair<ll, ll> numIdxAndSum(ll x) {
    ll idx = 0;
    ll totalSum = 0;
    while (x > 0) {
        int i = 63 - __builtin_clzll(x);
        idx += cnt[i];
        totalSum += s[i];
        x -= 1LL << i;
        totalSum += (x + 1) * i;
        idx += x + 1;
    }
    return make_pair(idx, totalSum);
}

ll f(ll i) {
    ll l = 0;
    ll r = 1LL << m;
    while (l < r) {
        ll mid = (l + r + 1) >> 1;
        auto idxAndSum = numIdxAndSum(mid);
        ll idx = idxAndSum.first;
        if (idx < i) {
            l = mid;
        } else {
            r = mid - 1;
        }
    }

    auto idxAndSum = numIdxAndSum(l);
    ll totalSum = idxAndSum.second;
    ll idx = idxAndSum.first;
    i -= idx;
    ll x = l + 1;
    for (int j = 0; j < i; ++j) {
        ll y = x & -x;
        totalSum += __builtin_ctzll(y);
        x -= y;
    }
    return totalSum;
}

ll qpow(ll a, ll n, ll mod) {
    ll ans = 1 % mod;
    a = a % mod;
    while (n > 0) {
        if (n & 1) {
            ans = ans * a % mod;
        }
        a = a * a % mod;
        n >>= 1;
    }
    return ans;
}

class Solution {
public:
    vector<int> findProductsOfElements(vector<vector<ll>>& queries) {
        int n = queries.size();
        vector<int> ans(n);
        for (int i = 0; i < n; ++i) {
            ll left = queries[i][0];
            ll right = queries[i][1];
            ll mod = queries[i][2];
            ll power = f(right + 1) - f(left);
            if (power < 0) {
                power += mod;
            }
            ans[i] = static_cast<int>(qpow(2, power, mod));
        }
        return ans;
    }
};
```

#### Go

```go
const m = 50

var cnt [m + 1]int64
var s [m + 1]int64
var p int64 = 1

func init() {
	cnt[0] = 0
	s[0] = 0
	for i := 1; i <= m; i++ {
		cnt[i] = cnt[i-1]*2 + p
		s[i] = s[i-1]*2 + p*(int64(i)-1)
		p *= 2
	}
}

func numIdxAndSum(x int64) (int64, int64) {
	var idx, totalSum int64
	for x > 0 {
		i := 63 - bits.LeadingZeros64(uint64(x))
		idx += cnt[i]
		totalSum += s[i]
		x -= 1 << i
		totalSum += (x + 1) * int64(i)
		idx += x + 1
	}
	return idx, totalSum
}

func f(i int64) int64 {
	l, r := int64(0), int64(1)<<m
	for l < r {
		mid := (l + r + 1) >> 1
		idx, _ := numIdxAndSum(mid)
		if idx < i {
			l = mid
		} else {
			r = mid - 1
		}
	}

	_, totalSum := numIdxAndSum(l)
	idx, _ := numIdxAndSum(l)
	i -= idx
	x := l + 1
	for j := int64(0); j < i; j++ {
		y := x & -x
		totalSum += int64(bits.TrailingZeros64(uint64(y)))
		x -= y
	}
	return totalSum
}

func qpow(a, n, mod int64) int64 {
	ans := int64(1) % mod
	a = a % mod
	for n > 0 {
		if n&1 == 1 {
			ans = (ans * a) % mod
		}
		a = (a * a) % mod
		n >>= 1
	}
	return ans
}

func findProductsOfElements(queries [][]int64) []int {
	ans := make([]int, len(queries))
	for i, q := range queries {
		left, right, mod := q[0], q[1], q[2]
		power := f(right+1) - f(left)
		ans[i] = int(qpow(2, power, mod))
	}
	return ans
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
