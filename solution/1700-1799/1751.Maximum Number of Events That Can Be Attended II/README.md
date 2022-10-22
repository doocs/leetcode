# [1751. 最多可以参加的会议数目 II](https://leetcode.cn/problems/maximum-number-of-events-that-can-be-attended-ii)

[English Version](/solution/1700-1799/1751.Maximum%20Number%20of%20Events%20That%20Can%20Be%20Attended%20II/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个 <code>events</code> 数组，其中 <code>events[i] = [startDay<sub>i</sub>, endDay<sub>i</sub>, value<sub>i</sub>]</code> ，表示第 <code>i</code> 个会议在 <code>startDay<sub>i</sub></code><sub> </sub>天开始，第 <code>endDay<sub>i</sub></code> 天结束，如果你参加这个会议，你能得到价值 <code>value<sub>i</sub></code> 。同时给你一个整数 <code>k</code> 表示你能参加的最多会议数目。</p>

<p>你同一时间只能参加一个会议。如果你选择参加某个会议，那么你必须 <strong>完整</strong> 地参加完这个会议。会议结束日期是包含在会议内的，也就是说你不能同时参加一个开始日期与另一个结束日期相同的两个会议。</p>

<p>请你返回能得到的会议价值 <strong>最大和</strong> 。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1700-1799/1751.Maximum%20Number%20of%20Events%20That%20Can%20Be%20Attended%20II/images/screenshot-2021-01-11-at-60048-pm.png" style="width: 400px; height: 103px;" /></p>

<pre>
<b>输入：</b>events = [[1,2,4],[3,4,3],[2,3,1]], k = 2
<b>输出：</b>7
<strong>解释：</strong>选择绿色的活动会议 0 和 1，得到总价值和为 4 + 3 = 7 。</pre>

<p><strong>示例 2：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1700-1799/1751.Maximum%20Number%20of%20Events%20That%20Can%20Be%20Attended%20II/images/screenshot-2021-01-11-at-60150-pm.png" style="width: 400px; height: 103px;" /></p>

<pre>
<b>输入：</b>events = [[1,2,4],[3,4,3],[2,3,10]], k = 2
<b>输出：</b>10
<b>解释：</b>参加会议 2 ，得到价值和为 10 。
你没法再参加别的会议了，因为跟会议 2 有重叠。你 <strong>不</strong> 需要参加满 k 个会议。</pre>

<p><strong>示例 3：</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1700-1799/1751.Maximum%20Number%20of%20Events%20That%20Can%20Be%20Attended%20II/images/screenshot-2021-01-11-at-60703-pm.png" style="width: 400px; height: 126px;" /></strong></p>

<pre>
<b>输入：</b>events = [[1,1,1],[2,2,2],[3,3,3],[4,4,4]], k = 3
<b>输出：</b>9
<b>解释：</b>尽管会议互不重叠，你只能参加 3 个会议，所以选择价值最大的 3 个会议。</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= k <= events.length</code></li>
	<li><code>1 <= k * events.length <= 10<sup>6</sup></code></li>
	<li><code>1 <= startDay<sub>i</sub> <= endDay<sub>i</sub> <= 10<sup>9</sup></code></li>
	<li><code>1 <= value<sub>i</sub> <= 10<sup>6</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：记忆化搜索 + 二分查找**

我们先将会议按照开始时间从小到大排序，然后设计一个函数 $dfs(i, k)$ 表示从第 $i$ 个会议开始，最多参加 $k$ 个会议的最大价值和。答案即为 $dfs(0, k)$。

函数 $dfs(i, k)$ 的计算过程如下：

对于第 $i$ 个会议，我们可以选择参加或者不参加。如果不参加，那么最大价值和就是 $dfs(i + 1, k)$，如果参加，我们可以通过二分查找，找到第一个开始时间大于第 $i$ 个会议结束时间的会议，记为 $j$，那么最大价值和就是 $dfs(j, k - 1) + value[i]$。取二者的较大值即可。即：

$$
dfs(i, k) = \max(dfs(i + 1, k), dfs(j, k - 1) + value[i])
$$

其中 $j$ 为第一个开始时间大于第 $i$ 个会议结束时间的会议，可以通过二分查找得到。

由于函数 $dfs(i, k)$ 的计算过程中，会调用 $dfs(i + 1, k)$ 和 $dfs(j, k - 1)$，因此我们可以使用记忆化搜索，将计算过的值保存下来，避免重复计算。

时间复杂度 $O(n\times \log n + n\times k)$，其中 $n$ 为会议数量。

**方法二：动态规划 + 二分查找**

我们可以将方法一中的记忆化搜索改为动态规划。

先将会议排序，这次我们按照结束时间从小到大排序。然后定义 $dp[i][j]$ 表示前 $i$ 个会议中，最多参加 $j$ 个会议的最大价值和。答案即为 $dp[n][k]$。

对于第 $i$ 个会议，我们可以选择参加或者不参加。如果不参加，那么最大价值和就是 $dp[i][j]$，如果参加，我们可以通过二分查找，找到最后一个结束时间小于第 $i$ 个会议开始时间的会议，记为 $h$，那么最大价值和就是 $dp[h+1][j - 1] + value[i]$。取二者的较大值即可。即：

$$
dp[i+1][j] = \max(dp[i][j], dp[h+1][j - 1] + value[i])
$$

其中 $h$ 为最后一个结束时间小于第 $i$ 个会议开始时间的会议，可以通过二分查找得到。

时间复杂度 $O(n\times \log n + n\times k)$，其中 $n$ 为会议数量。

相似题目：

-   [1235. 规划兼职工作](/solution/1200-1299/1235.Maximum%20Profit%20in%20Job%20Scheduling/README.md)
-   [2008. 出租车的最大盈利](/solution/2000-2099/2008.Maximum%20Earnings%20From%20Taxi/README.md)

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def maxValue(self, events: List[List[int]], k: int) -> int:
        @cache
        def dfs(i, k):
            if i >= len(events):
                return 0
            _, e, v = events[i]
            ans = dfs(i + 1, k)
            if k:
                j = bisect_right(events, e, lo=i+1, key=lambda x: x[0])
                ans = max(ans, dfs(j, k - 1) + v)
            return ans

        events.sort()
        return dfs(0, k)
```

```python
class Solution:
    def maxValue(self, events: List[List[int]], k: int) -> int:
        events.sort(key=lambda x: x[1])
        n = len(events)
        dp = [[0] * (k + 1) for _ in range(n + 1)]
        for i, (s, _, v) in enumerate(events):
            h = bisect_left(events, s, hi=i, key=lambda x: x[1])
            for j in range(1, k + 1):
                dp[i + 1][j] = max(dp[i][j], dp[h][j - 1] + v)
        return dp[n][k]
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    private int[][] events;
    private int[][] f;
    private int n;

    public int maxValue(int[][] events, int k) {
        Arrays.sort(events, (a, b) -> a[0] - b[0]);
        this.events = events;
        n = events.length;
        f = new int[n][k + 1];
        return dfs(0, k);
    }

    private int dfs(int i, int k) {
        if (i >= n || k <= 0) {
            return 0;
        }
        if (f[i][k] != 0) {
            return f[i][k];
        }
        int j = search(events, events[i][1], i + 1);
        int ans = Math.max(dfs(i + 1, k), dfs(j, k - 1) + events[i][2]);
        f[i][k] = ans;
        return ans;
    }

    private int search(int[][] events, int x, int i) {
        int left = i, right = n;
        while (left < right) {
            int mid = (left + right) >> 1;
            if (events[mid][0] > x) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
}
```

```java
class Solution {
    public int maxValue(int[][] events, int k) {
        Arrays.sort(events, (a, b) -> a[1] - b[1]);
        int n = events.length;
        int[][] dp = new int[n + 1][k + 1];
        for (int i = 0; i < n; ++i) {
            int s = events[i][0], v = events[i][2];
            int h = search(events, s, i);
            for (int j = 1; j <= k; ++j) {
                dp[i + 1][j] = Math.max(dp[i][j], dp[h][j - 1] + v);
            }
        }
        return dp[n][k];
    }

    private int search(int[][] events, int x, int n) {
        int left = 0, right = n;
        while (left < right) {
            int mid = (left + right) >> 1;
            if (events[mid][1] >= x) {
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
    int maxValue(vector<vector<int>>& events, int k) {
        sort(events.begin(), events.end());
        int n = events.size();
        vector<vector<int>> f(n, vector<int>(k + 1));
        function<int(int, int)> dfs = [&](int i, int k) -> int {
            if (i >= n || k <= 0) return 0;
            if (f[i][k]) return f[i][k];
            vector<int> t = {events[i][1]};
            int j = upper_bound(events.begin() + i + 1, events.end(), t, [&](auto& l, auto& r) -> bool { return l[0] < r[0]; }) - events.begin();
            int ans = max(dfs(i + 1, k), dfs(j, k - 1) + events[i][2]);
            f[i][k] = ans;
            return ans;
        };
        return dfs(0, k);
    }
};
```

```cpp
class Solution {
public:
    int maxValue(vector<vector<int>>& events, int k) {
        sort(events.begin(), events.end(), [&](auto& l, auto& r) -> bool { return l[1] < r[1]; });
        int n = events.size();
        vector<vector<int>> dp(n + 1, vector<int>(k + 1));
        for (int i = 0; i < n; ++i) {
            int s = events[i][0], v = events[i][2];
            int h = lower_bound(events.begin(), events.begin() + i, s, [](auto& e, int x) { return e[1] < x; }) - events.begin();
            for (int j = 1; j <= k; ++j) {
                dp[i + 1][j] = max(dp[i][j], dp[h][j - 1] + v);
            }
        }
        return dp[n][k];
    }
};
```

### **Go**

```go
func maxValue(events [][]int, k int) int {
	sort.Slice(events, func(i, j int) bool { return events[i][0] < events[j][0] })
	n := len(events)
	f := make([][]int, n)
	for i := range f {
		f[i] = make([]int, k+1)
	}
	var dfs func(i, k int) int
	dfs = func(i, k int) int {
		if i >= n || k <= 0 {
			return 0
		}
		if f[i][k] > 0 {
			return f[i][k]
		}
		j := sort.Search(n, func(h int) bool { return events[h][0] > events[i][1] })
		ans := max(dfs(i+1, k), dfs(j, k-1)+events[i][2])
		f[i][k] = ans
		return ans
	}
	return dfs(0, k)
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
```

```go
func maxValue(events [][]int, k int) int {
	sort.Slice(events, func(i, j int) bool { return events[i][1] < events[j][1] })
	n := len(events)
	dp := make([][]int, n+1)
	for i := range dp {
		dp[i] = make([]int, k+1)
	}
	for i, event := range events {
		h := sort.Search(i, func(k int) bool { return events[k][1] >= event[0] })
		for j := 1; j <= k; j++ {
			dp[i+1][j] = max(dp[i][j], dp[h][j-1]+event[2])
		}
	}
	return dp[n][k]
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
```

### **...**

```

```

<!-- tabs:end -->
