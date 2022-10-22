# [1751. Maximum Number of Events That Can Be Attended II](https://leetcode.com/problems/maximum-number-of-events-that-can-be-attended-ii)

[中文文档](/solution/1700-1799/1751.Maximum%20Number%20of%20Events%20That%20Can%20Be%20Attended%20II/README.md)

## Description

<p>You are given an array of <code>events</code> where <code>events[i] = [startDay<sub>i</sub>, endDay<sub>i</sub>, value<sub>i</sub>]</code>. The <code>i<sup>th</sup></code> event starts at <code>startDay<sub>i</sub></code><sub> </sub>and ends at <code>endDay<sub>i</sub></code>, and if you attend this event, you will receive a value of <code>value<sub>i</sub></code>. You are also given an integer <code>k</code> which represents the maximum number of events you can attend.</p>

<p>You can only attend one event at a time. If you choose to attend an event, you must attend the <strong>entire</strong> event. Note that the end day is <strong>inclusive</strong>: that is, you cannot attend two events where one of them starts and the other ends on the same day.</p>

<p>Return <em>the <strong>maximum sum</strong> of values that you can receive by attending events.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1700-1799/1751.Maximum%20Number%20of%20Events%20That%20Can%20Be%20Attended%20II/images/screenshot-2021-01-11-at-60048-pm.png" style="width: 400px; height: 103px;" /></p>

<pre>
<strong>Input:</strong> events = [[1,2,4],[3,4,3],[2,3,1]], k = 2
<strong>Output:</strong> 7
<strong>Explanation: </strong>Choose the green events, 0 and 1 (0-indexed) for a total value of 4 + 3 = 7.</pre>

<p><strong class="example">Example 2:</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1700-1799/1751.Maximum%20Number%20of%20Events%20That%20Can%20Be%20Attended%20II/images/screenshot-2021-01-11-at-60150-pm.png" style="width: 400px; height: 103px;" /></p>

<pre>
<strong>Input:</strong> events = [[1,2,4],[3,4,3],[2,3,10]], k = 2
<strong>Output:</strong> 10
<strong>Explanation:</strong> Choose event 2 for a total value of 10.
Notice that you cannot attend any other event as they overlap, and that you do <strong>not</strong> have to attend k events.</pre>

<p><strong class="example">Example 3:</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1700-1799/1751.Maximum%20Number%20of%20Events%20That%20Can%20Be%20Attended%20II/images/screenshot-2021-01-11-at-60703-pm.png" style="width: 400px; height: 126px;" /></strong></p>

<pre>
<strong>Input:</strong> events = [[1,1,1],[2,2,2],[3,3,3],[4,4,4]], k = 3
<strong>Output:</strong> 9
<strong>Explanation:</strong> Although the events do not overlap, you can only attend 3 events. Pick the highest valued three.</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= k &lt;= events.length</code></li>
	<li><code>1 &lt;= k * events.length &lt;= 10<sup>6</sup></code></li>
	<li><code>1 &lt;= startDay<sub>i</sub> &lt;= endDay<sub>i</sub> &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= value<sub>i</sub> &lt;= 10<sup>6</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

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
