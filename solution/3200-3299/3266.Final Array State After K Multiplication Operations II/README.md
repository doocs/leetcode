---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3200-3299/3266.Final%20Array%20State%20After%20K%20Multiplication%20Operations%20II/README.md
tags:
    - 数组
    - 模拟
    - 堆（优先队列）
---

<!-- problem:start -->

# [3266. K 次乘运算后的最终数组 II](https://leetcode.cn/problems/final-array-state-after-k-multiplication-operations-ii)

[English Version](/solution/3200-3299/3266.Final%20Array%20State%20After%20K%20Multiplication%20Operations%20II/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个整数数组&nbsp;<code>nums</code>&nbsp;，一个整数&nbsp;<code>k</code>&nbsp;&nbsp;和一个整数&nbsp;<code>multiplier</code>&nbsp;。</p>

<p>你需要对 <code>nums</code>&nbsp;执行 <code>k</code>&nbsp;次操作，每次操作中：</p>

<ul>
	<li>找到 <code>nums</code>&nbsp;中的 <strong>最小</strong>&nbsp;值&nbsp;<code>x</code>&nbsp;，如果存在多个最小值，选择最 <strong>前面</strong>&nbsp;的一个。</li>
	<li>将 <code>x</code>&nbsp;替换为&nbsp;<code>x * multiplier</code>&nbsp;。</li>
</ul>

<p><code>k</code>&nbsp;次操作以后，你需要将 <code>nums</code>&nbsp;中每一个数值对&nbsp;<code>10<sup>9</sup> + 7</code>&nbsp;取余。</p>

<p>请你返回执行完 <code>k</code>&nbsp;次乘运算以及取余运算之后，最终的 <code>nums</code>&nbsp;数组。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>nums = [2,1,3,5,6], k = 5, multiplier = 2</span></p>

<p><span class="example-io"><b>输出：</b>[8,4,6,5,6]</span></p>

<p><strong>解释：</strong></p>

<table>
	<tbody>
		<tr>
			<th>操作</th>
			<th>结果</th>
		</tr>
		<tr>
			<td>1 次操作后</td>
			<td>[2, 2, 3, 5, 6]</td>
		</tr>
		<tr>
			<td>2 次操作后</td>
			<td>[4, 2, 3, 5, 6]</td>
		</tr>
		<tr>
			<td>3 次操作后</td>
			<td>[4, 4, 3, 5, 6]</td>
		</tr>
		<tr>
			<td>4 次操作后</td>
			<td>[4, 4, 6, 5, 6]</td>
		</tr>
		<tr>
			<td>5 次操作后</td>
			<td>[8, 4, 6, 5, 6]</td>
		</tr>
		<tr>
			<td>取余操作后</td>
			<td>[8, 4, 6, 5, 6]</td>
		</tr>
	</tbody>
</table>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>nums = [100000,2000], k = 2, multiplier = 1000000</span></p>

<p><span class="example-io"><b>输出：</b>[999999307,999999993]</span></p>

<p><strong>解释：</strong></p>

<table>
	<tbody>
		<tr>
			<th>操作</th>
			<th>结果</th>
		</tr>
		<tr>
			<td>1 次操作后</td>
			<td>[100000, 2000000000]</td>
		</tr>
		<tr>
			<td>2 次操作后</td>
			<td>[100000000000, 2000000000]</td>
		</tr>
		<tr>
			<td>取余操作后</td>
			<td>[999999307, 999999993]</td>
		</tr>
	</tbody>
</table>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>4</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= k &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= multiplier &lt;= 10<sup>6</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：优先队列（小根堆）+ 模拟

我们记数组 $\textit{nums}$ 的长度为 $n$，最大值为 $m$。

我们首先通过利用优先队列（小根堆），模拟操作，直到完成 $k$ 次操作或者堆中所有元素都大于等于 $m$。

此时，数组所有元素值都小于 $m \times \textit{multiplier}$，由于 $1 \leq m \leq 10^9$ 且 $1 \leq \textit{multiplier} \leq 10^6$，所以 $m \times \textit{multiplier} \leq 10^{15}$，是在 $64$ 位整数范围内的。

接下来，我们的每一次操作，都会将数组中的最小元素变成最大元素，因此在每 $n$ 次连续操作后，数组中的每个元素都恰好执行了一次乘法操作。

因此，我们在模拟过后，剩余 $k$ 次操作，那么数组中最小的 $k \bmod n$ 个元素将会执行 $\lfloor k / n \rfloor + 1$ 次乘法操作，其余的元素将会执行 $\lfloor k / n \rfloor$ 次乘法操作。

最后，我们将数组中的每个元素乘上对应的乘法次数，再取模 $10^9 + 7$ 即可，可以通过快速幂来计算。

时间复杂度 $O(n \times \log n \times \log M + n \times \log k)$，空间复杂度 $O(n)$。其中 $n$ 为数组 $\textit{nums}$ 的长度，而 $M$ 为数组 $\textit{nums}$ 中的最大值。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def getFinalState(self, nums: List[int], k: int, multiplier: int) -> List[int]:
        if multiplier == 1:
            return nums
        pq = [(x, i) for i, x in enumerate(nums)]
        heapify(pq)
        m = max(nums)
        while k and pq[0][0] < m:
            x, i = heappop(pq)
            heappush(pq, (x * multiplier, i))
            k -= 1
        n = len(nums)
        mod = 10**9 + 7
        pq.sort()
        for i, (x, j) in enumerate(pq):
            nums[j] = x * pow(multiplier, k // n + int(i < k % n), mod) % mod
        return nums
```

#### Java

```java
class Solution {
    public int[] getFinalState(int[] nums, int k, int multiplier) {
        if (multiplier == 1) {
            return nums;
        }
        PriorityQueue<long[]> pq = new PriorityQueue<>(
            (a, b) -> a[0] == b[0] ? Long.compare(a[1], b[1]) : Long.compare(a[0], b[0]));
        int n = nums.length;
        int m = Arrays.stream(nums).max().getAsInt();
        for (int i = 0; i < n; ++i) {
            pq.offer(new long[] {nums[i], i});
        }
        for (; k > 0 && pq.peek()[0] < m; --k) {
            long[] p = pq.poll();
            p[0] *= multiplier;
            pq.offer(p);
        }
        final int mod = (int) 1e9 + 7;
        for (int i = 0; i < n; ++i) {
            long[] p = pq.poll();
            long x = p[0];
            int j = (int) p[1];
            nums[j] = (int) ((x % mod) * qpow(multiplier, k / n + (i < k % n ? 1 : 0), mod) % mod);
        }
        return nums;
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
class Solution {
public:
    vector<int> getFinalState(vector<int>& nums, int k, int multiplier) {
        if (multiplier == 1) {
            return nums;
        }

        using ll = long long;
        using pli = pair<ll, int>;
        auto cmp = [](const pli& a, const pli& b) {
            if (a.first == b.first) {
                return a.second > b.second;
            }
            return a.first > b.first;
        };
        priority_queue<pli, vector<pli>, decltype(cmp)> pq(cmp);

        int n = nums.size();
        int m = *max_element(nums.begin(), nums.end());

        for (int i = 0; i < n; ++i) {
            pq.emplace(nums[i], i);
        }

        while (k > 0 && pq.top().first < m) {
            auto p = pq.top();
            pq.pop();
            p.first *= multiplier;
            pq.emplace(p);
            --k;
        }

        auto qpow = [&](ll a, ll n, ll mod) {
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
        };

        const int mod = 1e9 + 7;
        for (int i = 0; i < n; ++i) {
            auto p = pq.top();
            pq.pop();
            long long x = p.first;
            int j = p.second;
            nums[j] = static_cast<int>((x % mod) * qpow(multiplier, k / n + (i < k % n ? 1 : 0), mod) % mod);
        }

        return nums;
    }
};
```

#### Go

```go
func getFinalState(nums []int, k int, multiplier int) []int {
	if multiplier == 1 {
		return nums
	}
	n := len(nums)
	pq := make(hp, n)
	for i, x := range nums {
		pq[i] = pair{x, i}
	}
	heap.Init(&pq)
	m := slices.Max(nums)
	for ; k > 0 && pq[0].x < m; k-- {
		x := pq[0]
		heap.Pop(&pq)
		x.x *= multiplier
		heap.Push(&pq, x)
	}
	const mod int = 1e9 + 7

	for i := range nums {
		p := heap.Pop(&pq).(pair)
		x, j := p.x, p.i
		power := k / n
		if i < k%n {
			power++
		}
		nums[j] = (x % mod) * qpow(multiplier, power, mod) % mod
	}
	return nums
}

func qpow(a, n, mod int) int {
	ans := 1 % mod
	a = a % mod
	for n > 0 {
		if n&1 == 1 {
			ans = (ans * a) % mod
		}
		a = (a * a) % mod
		n >>= 1
	}
	return int(ans)
}

type pair struct{ x, i int }
type hp []pair

func (h hp) Len() int           { return len(h) }
func (h hp) Less(i, j int) bool { return h[i].x < h[j].x || h[i].x == h[j].x && h[i].i < h[j].i }
func (h hp) Swap(i, j int)      { h[i], h[j] = h[j], h[i] }
func (h *hp) Push(x any)        { *h = append(*h, x.(pair)) }
func (h *hp) Pop() any          { a := *h; x := a[len(a)-1]; *h = a[:len(a)-1]; return x }
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
