# [2448. 使数组相等的最小开销](https://leetcode.cn/problems/minimum-cost-to-make-array-equal)

[English Version](/solution/2400-2499/2448.Minimum%20Cost%20to%20Make%20Array%20Equal/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你两个下标从 <strong>0</strong>&nbsp;开始的数组&nbsp;<code>nums</code> 和&nbsp;<code>cost</code>&nbsp;，分别包含&nbsp;<code>n</code>&nbsp;个&nbsp;<strong>正</strong>&nbsp;整数。</p>

<p>你可以执行下面操作 <strong>任意</strong>&nbsp;次：</p>

<ul>
	<li>将&nbsp;<code>nums</code>&nbsp;中 <strong>任意</strong>&nbsp;元素增加或者减小 <code>1</code>&nbsp;。</li>
</ul>

<p>对第 <code>i</code>&nbsp;个元素执行一次操作的开销是&nbsp;<code>cost[i]</code>&nbsp;。</p>

<p>请你返回使 <code>nums</code>&nbsp;中所有元素 <strong>相等</strong>&nbsp;的 <strong>最少</strong>&nbsp;总开销。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><b>输入：</b>nums = [1,3,5,2], cost = [2,3,1,14]
<b>输出：</b>8
<b>解释：</b>我们可以执行以下操作使所有元素变为 2 ：
- 增加第 0 个元素 1 次，开销为 2 。
- 减小第 1 个元素 1 次，开销为 3 。
- 减小第 2 个元素 3 次，开销为 1 + 1 + 1 = 3 。
总开销为 2 + 3 + 3 = 8 。
这是最小开销。
</pre>

<p><strong>示例 2：</strong></p>

<pre><b>输入：</b>nums = [2,2,2,2,2], cost = [4,2,8,1,3]
<b>输出：</b>0
<b>解释：</b>数组中所有元素已经全部相等，不需要执行额外的操作。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == nums.length == cost.length</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i], cost[i] &lt;= 10<sup>6</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：前缀和 + 排序 + 枚举**

我们记数组 `nums` 所有元素为 $a_1, a_2, \cdots, a_n$，数组 `cost` 所有元素为 $b_1, b_2, \cdots, b_n$。我们不妨令 $a_1 \leq a_2 \leq \cdots \leq a_n$，即数组 `nums` 升序排列。

假设我们将数组 `nums` 中的元素全部变为 $x$，那么我们需要的总开销为：

$$
\begin{aligned}
\sum_{i=1}^{n} \left | a_i-x \right | b_i  &= \sum_{i=1}^{k} (x-a_i)b_i + \sum_{i=k+1}^{n} (a_i-x)b_i \\
&= x\sum_{i=1}^{k} b_i - \sum_{i=1}^{k} a_ib_i + \sum_{i=k+1}^{n}a_ib_i - x\sum_{i=k+1}^{n}b_i
\end{aligned}
$$

其中 $k$ 为 $a_1, a_2, \cdots, a_n$ 中小于等于 $x$ 的元素个数。

我们可以使用前缀和的方法，计算 $\sum_{i=1}^{k} b_i$ 和 $\sum_{i=1}^{k} a_ib_i$，以及 $\sum_{i=k+1}^{n}a_ib_i$ 和 $\sum_{i=k+1}^{n}b_i$。

然后我们枚举 $x$，计算上述四个前缀和，得到上述的总开销，取其中的最小值即可。

时间复杂度 $O(n\times \log n)$。其中 $n$ 为数组 `nums` 的长度。主要是排序的时间复杂度。

**方法二：排序 + 中位数**

我们还可以把 $b_i$ 看作是 $a_i$ 的出现次数，那么中位数下标是 $\frac{\sum_{i=1}^{n} b_i}{2}$。把所有数变成中位数，一定是最优的。

时间复杂度 $O(n\times \log n)$。其中 $n$ 为数组 `nums` 的长度。主要是排序的时间复杂度。

相似题目：

-   [296. 最佳的碰头地点](/solution/0200-0299/0296.Best%20Meeting%20Point/README.md)
-   [462. 最少移动次数使数组元素相等 II](/solution/0400-0499/0462.Minimum%20Moves%20to%20Equal%20Array%20Elements%20II/README.md)

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def minCost(self, nums: List[int], cost: List[int]) -> int:
        arr = sorted(zip(nums, cost))
        n = len(arr)
        f = [0] * (n + 1)
        g = [0] * (n + 1)
        for i in range(1, n + 1):
            a, b = arr[i - 1]
            f[i] = f[i - 1] + a * b
            g[i] = g[i - 1] + b
        ans = inf
        for i in range(1, n + 1):
            a = arr[i - 1][0]
            l = a * g[i - 1] - f[i - 1]
            r = f[n] - f[i] - a * (g[n] - g[i])
            ans = min(ans, l + r)
        return ans
```

```python
class Solution:
    def minCost(self, nums: List[int], cost: List[int]) -> int:
        arr = sorted(zip(nums, cost))
        mid = sum(cost) // 2
        s = 0
        for x, c in arr:
            s += c
            if s > mid:
                return sum(abs(v - x) * c for v, c in arr)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public long minCost(int[] nums, int[] cost) {
        int n = nums.length;
        int[][] arr = new int[n][2];
        for (int i = 0; i < n; ++i) {
            arr[i] = new int[]{nums[i], cost[i]};
        }
        Arrays.sort(arr, (a, b) -> a[0] - b[0]);
        long[] f = new long[n + 1];
        long[] g = new long[n + 1];
        for (int i = 1; i <= n; ++i) {
            long a = arr[i - 1][0], b = arr[i - 1][1];
            f[i] = f[i - 1] + a * b;
            g[i] = g[i - 1] + b;
        }
        long ans = Long.MAX_VALUE;
        for (int i = 1; i <= n; ++i) {
            long a = arr[i - 1][0];
            long l = a * g[i - 1] - f[i - 1];
            long r = f[n] - f[i] - a * (g[n] - g[i]);
            ans = Math.min(ans, l + r);
        }
        return ans;
    }
}
```

```java
class Solution {
    public long minCost(int[] nums, int[] cost) {
        int n = nums.length;
        int[][] arr = new int[n][2];
        for (int i = 0; i < n; ++i) {
            arr[i] = new int[] {nums[i], cost[i]};
        }
        Arrays.sort(arr, (a, b) -> a[0] - b[0]);
        long mid = sum(cost) / 2;
        long s = 0, ans = 0;
        for (var e : arr) {
            int x = e[0], c = e[1];
            s += c;
            if (s > mid) {
                for (var t : arr) {
                    ans += (long) Math.abs(t[0] - x) * t[1];
                }
                break;
            }
        }
        return ans;
    }

    private long sum(int[] arr) {
        long s = 0;
        for (int v : arr) {
            s += v;
        }
        return s;
    }
}
```

### **C++**

```cpp
using ll = long long;

class Solution {
public:
    long long minCost(vector<int>& nums, vector<int>& cost) {
        int n = nums.size();
        vector<pair<int, int>> arr(n);
        for (int i = 0; i < n; ++i) arr[i] = {nums[i], cost[i]};
        sort(arr.begin(), arr.end());
        vector<ll> f(n + 1), g(n + 1);
        for (int i = 1; i <= n; ++i) {
            auto [a, b] = arr[i - 1];
            f[i] = f[i - 1] + 1ll * a * b;
            g[i] = g[i - 1] + b;
        }
        ll ans = 1e18;
        for (int i = 1; i <= n; ++i) {
            auto [a, _] = arr[i - 1];
            ll l = 1ll * a * g[i - 1] - f[i - 1];
            ll r = f[n] - f[i] - 1ll * a * (g[n] - g[i]);
            ans = min(ans, l + r);
        }
        return ans;
    }
};
```

```cpp
using ll = long long;

class Solution {
public:
    long long minCost(vector<int>& nums, vector<int>& cost) {
        int n = nums.size();
        vector<pair<int, int>> arr(n);
        for (int i = 0; i < n; ++i) arr[i] = {nums[i], cost[i]};
        sort(arr.begin(), arr.end());
        ll mid = accumulate(cost.begin(), cost.end(), 0ll) / 2;
        ll s = 0, ans = 0;
        for (auto [x, c] : arr) {
            s += c;
            if (s > mid) {
                for (auto [v, d] : arr) {
                    ans += 1ll * abs(v - x) * d;
                }
                break;
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func minCost(nums []int, cost []int) int64 {
	n := len(nums)
	type pair struct{ a, b int }
	arr := make([]pair, n)
	for i, a := range nums {
		b := cost[i]
		arr[i] = pair{a, b}
	}
	sort.Slice(arr, func(i, j int) bool { return arr[i].a < arr[j].a })
	f := make([]int, n+1)
	g := make([]int, n+1)
	for i := 1; i <= n; i++ {
		a, b := arr[i-1].a, arr[i-1].b
		f[i] = f[i-1] + a*b
		g[i] = g[i-1] + b
	}
	var ans int64 = 1e18
	for i := 1; i <= n; i++ {
		a := arr[i-1].a
		l := a*g[i-1] - f[i-1]
		r := f[n] - f[i] - a*(g[n]-g[i])
		ans = min(ans, int64(l+r))
	}
	return ans
}

func min(a, b int64) int64 {
	if a < b {
		return a
	}
	return b
}
```

```go
func minCost(nums []int, cost []int) int64 {
	n := len(nums)
	type pair struct{ a, b int }
	arr := make([]pair, n)
	mid := 0
	for i, a := range nums {
		b := cost[i]
		mid += b
		arr[i] = pair{a, b}
	}
	mid /= 2
	sort.Slice(arr, func(i, j int) bool { return arr[i].a < arr[j].a })
	s, ans := 0, 0
	for _, e := range arr {
		x, c := e.a, e.b
		s += c
		if s > mid {
			for _, t := range arr {
				ans += abs(t.a-x) * t.b
			}
			break
		}
	}
	return int64(ans)

}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}
```

### **TypeScript**

```ts

```

### **...**

```

```

<!-- tabs:end -->
