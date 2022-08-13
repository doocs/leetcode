# [1575. Count All Possible Routes](https://leetcode.com/problems/count-all-possible-routes)

[中文文档](/solution/1500-1599/1575.Count%20All%20Possible%20Routes/README.md)

## Description

<p>You are given an array of <strong>distinct</strong> positive integers locations where <code>locations[i]</code> represents the position of city <code>i</code>. You are also given integers <code>start</code>, <code>finish</code> and <code>fuel</code> representing the starting city, ending city, and the initial amount of fuel you have, respectively.</p>

<p>At each step, if you are at city <code>i</code>, you can pick any city <code>j</code> such that <code>j != i</code> and <code>0 &lt;= j &lt; locations.length</code> and move to city <code>j</code>. Moving from city <code>i</code> to city <code>j</code> reduces the amount of fuel you have by <code>|locations[i] - locations[j]|</code>. Please notice that <code>|x|</code> denotes the absolute value of <code>x</code>.</p>

<p>Notice that <code>fuel</code> <strong>cannot</strong> become negative at any point in time, and that you are <strong>allowed</strong> to visit any city more than once (including <code>start</code> and <code>finish</code>).</p>

<p>Return <em>the count of all possible routes from </em><code>start</code> <em>to</em> <code>finish</code>. Since the answer may be too large, return it modulo <code>10<sup>9</sup> + 7</code>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> locations = [2,3,6,8,4], start = 1, finish = 3, fuel = 5
<strong>Output:</strong> 4
<strong>Explanation:</strong> The following are all possible routes, each uses 5 units of fuel:
1 -&gt; 3
1 -&gt; 2 -&gt; 3
1 -&gt; 4 -&gt; 3
1 -&gt; 4 -&gt; 2 -&gt; 3
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> locations = [4,3,1], start = 1, finish = 0, fuel = 6
<strong>Output:</strong> 5
<strong>Explanation:</strong> The following are all possible routes:
1 -&gt; 0, used fuel = 1
1 -&gt; 2 -&gt; 0, used fuel = 5
1 -&gt; 2 -&gt; 1 -&gt; 0, used fuel = 5
1 -&gt; 0 -&gt; 1 -&gt; 0, used fuel = 3
1 -&gt; 0 -&gt; 1 -&gt; 0 -&gt; 1 -&gt; 0, used fuel = 5
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> locations = [5,2,1], start = 0, finish = 2, fuel = 3
<strong>Output:</strong> 0
<strong>Explanation:</strong> It is impossible to get from 0 to 2 using only 3 units of fuel since the shortest route needs 4 units of fuel.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= locations.length &lt;= 100</code></li>
	<li><code>1 &lt;= locations[i] &lt;= 10<sup>9</sup></code></li>
	<li>All integers in <code>locations</code> are <strong>distinct</strong>.</li>
	<li><code>0 &lt;= start, finish &lt; locations.length</code></li>
	<li><code>1 &lt;= fuel &lt;= 200</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def countRoutes(
        self, locations: List[int], start: int, finish: int, fuel: int
    ) -> int:
        @cache
        def dfs(i, t):
            if abs(locations[i] - locations[finish]) > t:
                return 0
            res = int(i == finish)
            for j, v in enumerate(locations):
                if j != i:
                    if (cost := abs(locations[i] - v)) <= t:
                        res += dfs(j, t - cost)
            return res % mod

        mod = 10**9 + 7
        return dfs(start, fuel)
```

### **Java**

```java
class Solution {
    private int[][] f;
    private int[] locations;
    private int target;
    private static final int MOD = (int) 1e9 + 7;

    public int countRoutes(int[] locations, int start, int finish, int fuel) {
        int n = locations.length;
        f = new int[n + 1][fuel + 1];
        this.locations = locations;
        target = finish;
        for (int i = 0; i < f.length; ++i) {
            Arrays.fill(f[i], -1);
        }
        return dfs(start, fuel);
    }

    private int dfs(int i, int t) {
        if (f[i][t] != -1) {
            return f[i][t];
        }
        if (Math.abs(locations[i] - locations[target]) > t) {
            return 0;
        }
        int res = i == target ? 1 : 0;
        for (int j = 0; j < locations.length; ++j) {
            if (j != i) {
                int cost = Math.abs(locations[i] - locations[j]);
                if (cost <= t) {
                    res += dfs(j, t - cost);
                    res %= MOD;
                }
            }
        }
        f[i][t] = res;
        return res;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    const int mod = 1e9 + 7;

    int countRoutes(vector<int>& locations, int start, int finish, int fuel) {
        int n = locations.size();
        vector<vector<int>> f(n + 1, vector<int>(fuel + 1, -1));
        return dfs(start, fuel, locations, finish, f);
    }

    int dfs(int i, int t, vector<int>& locations, int target, vector<vector<int>>& f) {
        if (f[i][t] != -1) return f[i][t];
        if (abs(locations[i] - locations[target]) > t) return 0;
        int res = i == target;
        for (int j = 0; j < locations.size(); ++j) {
            if (j == i) continue;
            int cost = abs(locations[i] - locations[j]);
            if (cost <= t) res = (res + dfs(j, t - cost, locations, target, f)) % mod;
        }
        f[i][t] = res;
        return res;
    }
};
```

### **Go**

```go
func countRoutes(locations []int, start int, finish int, fuel int) int {
	n := len(locations)
	f := make([][]int, n+1)
	for i := range f {
		f[i] = make([]int, fuel+1)
		for j := range f[i] {
			f[i][j] = -1
		}
	}
	mod := int(1e9) + 7
	var dfs func(int, int) int
	dfs = func(i, t int) int {
		if f[i][t] != -1 {
			return f[i][t]
		}
		if abs(locations[i]-locations[finish]) > t {
			return 0
		}
		res := 0
		if i == finish {
			res++
		}
		for j, v := range locations {
			if j != i {
				cost := abs(locations[i] - v)
				if cost <= t {
					res = (res + dfs(j, t-cost)) % mod
				}
			}
		}
		f[i][t] = res
		return res
	}
	return dfs(start, fuel)
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}
```

### **...**

```

```

<!-- tabs:end -->
