# [2008. Maximum Earnings From Taxi](https://leetcode.com/problems/maximum-earnings-from-taxi)

[中文文档](/solution/2000-2099/2008.Maximum%20Earnings%20From%20Taxi/README.md)

## Description

<p>There are <code>n</code> points on a road you are driving your taxi on. The <code>n</code> points on the road are labeled from <code>1</code> to <code>n</code> in the direction you are going, and you want to drive from point <code>1</code> to point <code>n</code> to make money by picking up passengers. You cannot change the direction of the taxi.</p>

<p>The passengers are represented by a <strong>0-indexed</strong> 2D integer array <code>rides</code>, where <code>rides[i] = [start<sub>i</sub>, end<sub>i</sub>, tip<sub>i</sub>]</code> denotes the <code>i<sup>th</sup></code> passenger requesting a ride from point <code>start<sub>i</sub></code> to point <code>end<sub>i</sub></code> who is willing to give a <code>tip<sub>i</sub></code> dollar tip.</p>

<p>For<strong> each </strong>passenger <code>i</code> you pick up, you <strong>earn</strong> <code>end<sub>i</sub> - start<sub>i</sub> + tip<sub>i</sub></code> dollars. You may only drive <b>at most one </b>passenger at a time.</p>

<p>Given <code>n</code> and <code>rides</code>, return <em>the <strong>maximum</strong> number of dollars you can earn by picking up the passengers optimally.</em></p>

<p><strong>Note:</strong> You may drop off a passenger and pick up a different passenger at the same point.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> n = 5, rides = [<u>[2,5,4]</u>,[1,5,1]]
<strong>Output:</strong> 7
<strong>Explanation:</strong> We can pick up passenger 0 to earn 5 - 2 + 4 = 7 dollars.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 20, rides = [[1,6,1],<u>[3,10,2]</u>,<u>[10,12,3]</u>,[11,12,2],[12,15,2],<u>[13,18,1]</u>]
<strong>Output:</strong> 20
<strong>Explanation:</strong> We will pick up the following passengers:
- Drive passenger 1 from point 3 to point 10 for a profit of 10 - 3 + 2 = 9 dollars.
- Drive passenger 2 from point 10 to point 12 for a profit of 12 - 10 + 3 = 5 dollars.
- Drive passenger 5 from point 13 to point 18 for a profit of 18 - 13 + 1 = 6 dollars.
We earn 9 + 5 + 6 = 20 dollars in total.</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= rides.length &lt;= 3 * 10<sup>4</sup></code></li>
	<li><code>rides[i].length == 3</code></li>
	<li><code>1 &lt;= start<sub>i</sub> &lt; end<sub>i</sub> &lt;= n</code></li>
	<li><code>1 &lt;= tip<sub>i</sub> &lt;= 10<sup>5</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def maxTaxiEarnings(self, n: int, rides: List[List[int]]) -> int:
        @cache
        def dfs(i):
            if i >= len(rides):
                return 0
            s, e, t = rides[i]
            j = bisect_left(rides, e, lo=i + 1, key=lambda x: x[0])
            return max(dfs(i + 1), dfs(j) + e - s + t)

        rides.sort()
        return dfs(0)
```

```python
class Solution:
    def maxTaxiEarnings(self, n: int, rides: List[List[int]]) -> int:
        rides.sort(key=lambda x: x[1])
        m = len(rides)
        dp = [0] * (m + 1)
        for i, (s, e, t) in enumerate(rides):
            j = bisect_right(rides, s, hi=i, key=lambda x: x[1])
            dp[i + 1] = max(dp[i], dp[j] + e - s + t)
        return dp[m]
```

### **Java**

```java
class Solution {
    private int[][] rides;
    private long[] f;
    private int m;

    public long maxTaxiEarnings(int n, int[][] rides) {
        m = rides.length;
        f = new long[m];
        Arrays.sort(rides, (a, b) -> a[0] - b[0]);
        this.rides = rides;
        return dfs(0);
    }

    private long dfs(int i) {
        if (i >= m) {
            return 0;
        }
        if (f[i] != 0) {
            return f[i];
        }
        int s = rides[i][0], e = rides[i][1], t = rides[i][2];
        int j = search(rides, e, i + 1);
        long ans = Math.max(dfs(i + 1), dfs(j) + e - s + t);
        f[i] = ans;
        return ans;
    }

    private int search(int[][] rides, int x, int i) {
        int left = i, right = m;
        while (left < right) {
            int mid = (left + right) >> 1;
            if (rides[mid][0] >= x) {
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
    public long maxTaxiEarnings(int n, int[][] rides) {
        Arrays.sort(rides, (a, b) -> a[1] - b[1]);
        int m = rides.length;
        long[] dp = new long[m + 1];
        for (int i = 0; i < m; ++i) {
            int s = rides[i][0], e = rides[i][1], t = rides[i][2];
            int j = search(rides, s, i);
            dp[i + 1] = Math.max(dp[i], dp[j] + e - s + t);
        }
        return dp[m];
    }

    private int search(int[][] rides, int x, int n) {
        int left = 0, right = n;
        while (left < right) {
            int mid = (left + right) >> 1;
            if (rides[mid][1] > x) {
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
using ll = long long;

class Solution {
public:
    long long maxTaxiEarnings(int n, vector<vector<int>>& rides) {
        sort(rides.begin(), rides.end());
        int m = rides.size();
        vector<ll> f(m);
        vector<int> x(3);
        function<ll(int)> dfs = [&](int i) -> ll {
            if (i >= m) return 0;
            if (f[i]) return f[i];
            int s = rides[i][0], e = rides[i][1], t = rides[i][2];
            x[0] = e;
            int j = lower_bound(rides.begin() + i + 1, rides.end(), x, [&](auto& l, auto& r) -> bool { return l[0] < r[0];}) - rides.begin();
            ll ans = max(dfs(i + 1), dfs(j) + e - s + t);
            f[i] = ans;
            return ans;
        };
        return dfs(0);
    }
};
```

```cpp
using ll = long long;

class Solution {
public:
    long long maxTaxiEarnings(int n, vector<vector<int>>& rides) {
        sort(rides.begin(), rides.end(), [&](auto& l, auto& r) -> bool { return l[1] < r[1]; });
        int m = rides.size();
        vector<ll> dp(m + 1);
        vector<int> x(3);
        for (int i = 0; i < m; ++i) {
            int s = rides[i][0], e = rides[i][1], t = rides[i][2];
            x[1] = s;
            int j = upper_bound(rides.begin(), rides.begin() + i, x, [&](auto& l, auto& r) -> bool { return l[1] < r[1]; }) - rides.begin();
            dp[i + 1] = max(dp[i], dp[j] + e - s + t);
        }
        return dp[m];
    }
};
```

### **Go**

```go
func maxTaxiEarnings(n int, rides [][]int) int64 {
	sort.Slice(rides, func(i, j int) bool { return rides[i][0] < rides[j][0] })
	m := len(rides)
	f := make([]int64, m)
	var dfs func(int) int64
	dfs = func(i int) int64 {
		if i >= m {
			return 0
		}
		if f[i] != 0 {
			return f[i]
		}
		s, e, t := rides[i][0], rides[i][1], rides[i][2]
		j := sort.Search(m, func(k int) bool { return rides[k][0] >= e })
		ans := max(dfs(i+1), dfs(j)+int64(e-s+t))
		f[i] = ans
		return ans
	}
	return dfs(0)
}

func max(a, b int64) int64 {
	if a > b {
		return a
	}
	return b
}
```

```go
func maxTaxiEarnings(n int, rides [][]int) int64 {
	sort.Slice(rides, func(i, j int) bool { return rides[i][1] < rides[j][1] })
	m := len(rides)
	dp := make([]int64, m+1)
	for i, ride := range rides {
		s, e, t := ride[0], ride[1], ride[2]
		j := sort.Search(m, func(k int) bool { return rides[k][1] > s })
		dp[i+1] = max(dp[i], dp[j]+int64(e-s+t))
	}
	return dp[m]
}

func max(a, b int64) int64 {
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
