# [983. 最低票价](https://leetcode.cn/problems/minimum-cost-for-tickets)

[English Version](/solution/0900-0999/0983.Minimum%20Cost%20For%20Tickets/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>在一个火车旅行很受欢迎的国度，你提前一年计划了一些火车旅行。在接下来的一年里，你要旅行的日子将以一个名为&nbsp;<code>days</code>&nbsp;的数组给出。每一项是一个从&nbsp;<code>1</code>&nbsp;到&nbsp;<code>365</code>&nbsp;的整数。</p>

<p>火车票有 <strong>三种不同的销售方式</strong> ：</p>

<ul>
	<li>一张 <strong>为期一天</strong> 的通行证售价为&nbsp;<code>costs[0]</code> 美元；</li>
	<li>一张 <strong>为期七天</strong> 的通行证售价为&nbsp;<code>costs[1]</code> 美元；</li>
	<li>一张 <strong>为期三十天</strong> 的通行证售价为&nbsp;<code>costs[2]</code> 美元。</li>
</ul>

<p>通行证允许数天无限制的旅行。 例如，如果我们在第 <code>2</code> 天获得一张 <strong>为期 7 天</strong> 的通行证，那么我们可以连着旅行 7 天：第 <code>2</code> 天、第 <code>3</code> 天、第 <code>4</code> 天、第 <code>5</code> 天、第 <code>6</code> 天、第 <code>7</code> 天和第 <code>8</code> 天。</p>

<p>返回 <em>你想要完成在给定的列表&nbsp;<code>days</code>&nbsp;中列出的每一天的旅行所需要的最低消费&nbsp;</em>。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>days = [1,4,6,7,8,20], costs = [2,7,15]
<strong>输出：</strong>11
<strong>解释： </strong>
例如，这里有一种购买通行证的方法，可以让你完成你的旅行计划：
在第 1 天，你花了 costs[0] = $2 买了一张为期 1 天的通行证，它将在第 1 天生效。
在第 3 天，你花了 costs[1] = $7 买了一张为期 7 天的通行证，它将在第 3, 4, ..., 9 天生效。
在第 20 天，你花了 costs[0] = $2 买了一张为期 1 天的通行证，它将在第 20 天生效。
你总共花了 $11，并完成了你计划的每一天旅行。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>days = [1,2,3,4,5,6,7,8,9,10,30,31], costs = [2,7,15]
<strong>输出：</strong>17
<strong>解释：
</strong>例如，这里有一种购买通行证的方法，可以让你完成你的旅行计划： 
在第 1 天，你花了 costs[2] = $15 买了一张为期 30 天的通行证，它将在第 1, 2, ..., 30 天生效。
在第 31 天，你花了 costs[0] = $2 买了一张为期 1 天的通行证，它将在第 31 天生效。 
你总共花了 $17，并完成了你计划的每一天旅行。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= days.length &lt;= 365</code></li>
	<li><code>1 &lt;= days[i] &lt;= 365</code></li>
	<li><code>days</code>&nbsp;按顺序严格递增</li>
	<li><code>costs.length == 3</code></li>
	<li><code>1 &lt;= costs[i] &lt;= 1000</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：记忆化搜索 + 二分查找**

定义 `dfs(i)` 表示从第 `i` 次出行开始的最低消费。答案为 `dfs(0)`。

采用记忆化搜索的方法，记录已经计算过的结果，避免重复计算。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为 `days` 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
