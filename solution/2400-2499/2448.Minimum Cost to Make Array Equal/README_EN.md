# [2448. Minimum Cost to Make Array Equal](https://leetcode.com/problems/minimum-cost-to-make-array-equal)

[中文文档](/solution/2400-2499/2448.Minimum%20Cost%20to%20Make%20Array%20Equal/README.md)

## Description

<p>You are given two <strong>0-indexed</strong> arrays <code>nums</code> and <code>cost</code> consisting each of <code>n</code> <strong>positive</strong> integers.</p>

<p>You can do the following operation <strong>any</strong> number of times:</p>

<ul>
	<li>Increase or decrease <strong>any</strong> element of the array <code>nums</code> by <code>1</code>.</li>
</ul>

<p>The cost of doing one operation on the <code>i<sup>th</sup></code> element is <code>cost[i]</code>.</p>

<p>Return <em>the <strong>minimum</strong> total cost such that all the elements of the array </em><code>nums</code><em> become <strong>equal</strong></em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,3,5,2], cost = [2,3,1,14]
<strong>Output:</strong> 8
<strong>Explanation:</strong> We can make all the elements equal to 2 in the following way:
- Increase the 0<sup>th</sup> element one time. The cost is 2.
- Decrease the 1<sup><span style="font-size: 10.8333px;">st</span></sup> element one time. The cost is 3.
- Decrease the 2<sup>nd</sup> element three times. The cost is 1 + 1 + 1 = 3.
The total cost is 2 + 3 + 3 = 8.
It can be shown that we cannot make the array equal with a smaller cost.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [2,2,2,2,2], cost = [4,2,8,1,3]
<strong>Output:</strong> 0
<strong>Explanation:</strong> All the elements are already equal, so no operations are needed.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == nums.length == cost.length</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i], cost[i] &lt;= 10<sup>6</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

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
