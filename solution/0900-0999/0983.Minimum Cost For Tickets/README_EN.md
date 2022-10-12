# [983. Minimum Cost For Tickets](https://leetcode.com/problems/minimum-cost-for-tickets)

[中文文档](/solution/0900-0999/0983.Minimum%20Cost%20For%20Tickets/README.md)

## Description

<p>You have planned some train traveling one year in advance. The days of the year in which you will travel are given as an integer array <code>days</code>. Each day is an integer from <code>1</code> to <code>365</code>.</p>

<p>Train tickets are sold in <strong>three different ways</strong>:</p>

<ul>
	<li>a <strong>1-day</strong> pass is sold for <code>costs[0]</code> dollars,</li>
	<li>a <strong>7-day</strong> pass is sold for <code>costs[1]</code> dollars, and</li>
	<li>a <strong>30-day</strong> pass is sold for <code>costs[2]</code> dollars.</li>
</ul>

<p>The passes allow that many days of consecutive travel.</p>

<ul>
	<li>For example, if we get a <strong>7-day</strong> pass on day <code>2</code>, then we can travel for <code>7</code> days: <code>2</code>, <code>3</code>, <code>4</code>, <code>5</code>, <code>6</code>, <code>7</code>, and <code>8</code>.</li>
</ul>

<p>Return <em>the minimum number of dollars you need to travel every day in the given list of days</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> days = [1,4,6,7,8,20], costs = [2,7,15]
<strong>Output:</strong> 11
<strong>Explanation:</strong> For example, here is one way to buy passes that lets you travel your travel plan:
On day 1, you bought a 1-day pass for costs[0] = $2, which covered day 1.
On day 3, you bought a 7-day pass for costs[1] = $7, which covered days 3, 4, ..., 9.
On day 20, you bought a 1-day pass for costs[0] = $2, which covered day 20.
In total, you spent $11 and covered all the days of your travel.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> days = [1,2,3,4,5,6,7,8,9,10,30,31], costs = [2,7,15]
<strong>Output:</strong> 17
<strong>Explanation:</strong> For example, here is one way to buy passes that lets you travel your travel plan:
On day 1, you bought a 30-day pass for costs[2] = $15 which covered days 1, 2, ..., 30.
On day 31, you bought a 1-day pass for costs[0] = $2 which covered day 31.
In total, you spent $17 and covered all the days of your travel.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= days.length &lt;= 365</code></li>
	<li><code>1 &lt;= days[i] &lt;= 365</code></li>
	<li><code>days</code> is in strictly increasing order.</li>
	<li><code>costs.length == 3</code></li>
	<li><code>1 &lt;= costs[i] &lt;= 1000</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def mincostTickets(self, days: List[int], costs: List[int]) -> int:
        @cache
        def dfs(i):
            if i >= len(days):
                return 0
            res = inf
            for c, d in zip(costs, [1, 7, 30]):
                j = bisect_left(days, days[i] + d)
                res = min(res, c + dfs(j))
            return res

        return dfs(0)
```

### **Java**

```java
class Solution {
    private static final int[] T = new int[] {1, 7, 30};
    private int[] costs;
    private int[] days;
    private int[] f;
    private int n;

    public int mincostTickets(int[] days, int[] costs) {
        n = days.length;
        f = new int[n];
        this.costs = costs;
        this.days = days;
        Arrays.fill(f, -1);
        return dfs(0);
    }

    private int dfs(int i) {
        if (i >= n) {
            return 0;
        }
        if (f[i] != -1) {
            return f[i];
        }
        int res = Integer.MAX_VALUE;

        for (int k = 0; k < 3; ++k) {
            int j = lowerBound(days, days[i] + T[k]);
            res = Math.min(res, costs[k] + dfs(j));
        }
        f[i] = res;
        return res;
    }

    private int lowerBound(int[] days, int x) {
        int left = 0, right = days.length;
        while (left < right) {
            int mid = (left + right) >> 1;
            if (days[mid] >= x) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<int> t = {1, 7, 30};
    vector<int> days;
    vector<int> costs;
    vector<int> f;
    int n;

    int mincostTickets(vector<int>& days, vector<int>& costs) {
        n = days.size();
        this->days = days;
        this->costs = costs;
        f.assign(n, -1);
        return dfs(0);
    }

    int dfs(int i) {
        if (i >= n) return 0;
        if (f[i] != -1) return f[i];
        int res = INT_MAX;
        for (int k = 0; k < 3; ++k) {
            int j = lower_bound(days.begin(), days.end(), days[i] + t[k]) - days.begin();
            res = min(res, costs[k] + dfs(j));
        }
        f[i] = res;
        return res;
    }
};
```

### **Go**

```go
func mincostTickets(days []int, costs []int) int {
	t := []int{1, 7, 30}
	n := len(days)
	f := make([]int, n)
	for i := range f {
		f[i] = -1
	}
	var dfs func(i int) int
	dfs = func(i int) int {
		if i >= n {
			return 0
		}
		if f[i] != -1 {
			return f[i]
		}
		res := 0x3f3f3f3f
		for k, c := range costs {
			j := lowerBound(days, days[i]+t[k])
			res = min(res, c+dfs(j))
		}
		f[i] = res
		return res
	}
	return dfs(0)
}

func lowerBound(arr []int, x int) int {
	left, right := 0, len(arr)
	for left < right {
		mid := (left + right) >> 1
		if arr[mid] >= x {
			right = mid
		} else {
			left = mid + 1
		}
	}
	return left
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}
```

### **TypeScript**

```ts
function mincostTickets(days: number[], costs: number[]): number {
    const n = days.length,
        m = days[n - 1] + 1;
    const [a, b, c] = costs;
    let dp = new Array(m).fill(0);
    for (let i = 1; i < m; i++) {
        let x = days.includes(i) ? dp[i - 1] + a : dp[i - 1];
        let y = (i > 7 ? dp[i - 7] : dp[0]) + b;
        let z = (i > 30 ? dp[i - 30] : dp[0]) + c;
        dp[i] = Math.min(x, y, z);
    }
    return dp[m - 1];
}
```

### **...**

```

```

<!-- tabs:end -->
