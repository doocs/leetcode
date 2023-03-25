# [1575. Count All Possible Routes](https://leetcode.com/problems/count-all-possible-routes)

[中文文档](/solution/1500-1599/1575.Count%20All%20Possible%20Routes/README.md)

## Description

<p>You are given an array of <strong>distinct</strong> positive integers locations where <code>locations[i]</code> represents the position of city <code>i</code>. You are also given integers <code>start</code>, <code>finish</code> and <code>fuel</code> representing the starting city, ending city, and the initial amount of fuel you have, respectively.</p>

<p>At each step, if you are at city <code>i</code>, you can pick any city <code>j</code> such that <code>j != i</code> and <code>0 &lt;= j &lt; locations.length</code> and move to city <code>j</code>. Moving from city <code>i</code> to city <code>j</code> reduces the amount of fuel you have by <code>|locations[i] - locations[j]|</code>. Please notice that <code>|x|</code> denotes the absolute value of <code>x</code>.</p>

<p>Notice that <code>fuel</code> <strong>cannot</strong> become negative at any point in time, and that you are <strong>allowed</strong> to visit any city more than once (including <code>start</code> and <code>finish</code>).</p>

<p>Return <em>the count of all possible routes from </em><code>start</code> <em>to</em> <code>finish</code>. Since the answer may be too large, return it modulo <code>10<sup>9</sup> + 7</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> locations = [2,3,6,8,4], start = 1, finish = 3, fuel = 5
<strong>Output:</strong> 4
<strong>Explanation:</strong> The following are all possible routes, each uses 5 units of fuel:
1 -&gt; 3
1 -&gt; 2 -&gt; 3
1 -&gt; 4 -&gt; 3
1 -&gt; 4 -&gt; 2 -&gt; 3
</pre>

<p><strong class="example">Example 2:</strong></p>

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

<p><strong class="example">Example 3:</strong></p>

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
        def dfs(i: int, k: int) -> int:
            if k < 0 or abs(locations[i] - locations[finish] > k):
                return 0
            ans = int(i == finish)
            ans += sum(
                dfs(j, k - abs(locations[i] - x))
                for j, x in enumerate(locations)
                if j != i
            )
            return ans % mod

        mod = 10**9 + 7
        return dfs(start, fuel)
```

### **Java**

```java
class Solution {
    private int[] locations;
    private int finish;
    private int n;
    private Integer[][] f;
    private final int mod = (int) 1e9 + 7;

    public int countRoutes(int[] locations, int start, int finish, int fuel) {
        n = locations.length;
        this.locations = locations;
        this.finish = finish;
        f = new Integer[n][fuel + 1];
        return dfs(start, fuel);
    }

    private int dfs(int i, int k) {
        if (k < 0 || Math.abs(locations[i] - locations[finish]) > k) {
            return 0;
        }
        if (f[i][k] != null) {
            return f[i][k];
        }
        int ans = i == finish ? 1 : 0;
        for (int j = 0; j < n; ++j) {
            if (j != i) {
                ans = (ans + dfs(j, k - Math.abs(locations[i] - locations[j]))) % mod;
            }
        }
        return f[i][k] = ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int countRoutes(vector<int>& locations, int start, int finish, int fuel) {
        int n = locations.size();
        int f[n][fuel + 1];
        memset(f, -1, sizeof(f));
        const int mod = 1e9 + 7;
        function<int(int, int)> dfs = [&](int i, int k) -> int {
            if (k < 0 || abs(locations[i] - locations[finish]) > k) {
                return 0;
            }
            if (f[i][k] != -1) {
                return f[i][k];
            }
            int ans = i == finish;
            for (int j = 0; j < n; ++j) {
                if (j != i) {
                    ans = (ans + dfs(j, k - abs(locations[i] - locations[j]))) % mod;
                }
            }
            return f[i][k] = ans;
        };
        return dfs(start, fuel);
    }
};
```

### **Go**

```go
func countRoutes(locations []int, start int, finish int, fuel int) int {
	n := len(locations)
	f := make([][]int, n)
	for i := range f {
		f[i] = make([]int, fuel+1)
		for j := range f[i] {
			f[i][j] = -1
		}
	}
	const mod = 1e9 + 7
	var dfs func(int, int) int
	dfs = func(i, k int) (ans int) {
		if k < 0 || abs(locations[i]-locations[finish]) > k {
			return 0
		}
		if f[i][k] != -1 {
			return f[i][k]
		}
		if i == finish {
			ans = 1
		}
		for j, x := range locations {
			if j != i {
				ans = (ans + dfs(j, k-abs(locations[i]-x))) % mod
			}
		}
		f[i][k] = ans
		return
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

### **TypeScript**

```ts
function countRoutes(
    locations: number[],
    start: number,
    finish: number,
    fuel: number,
): number {
    const n = locations.length;
    const f = Array.from({ length: n }, () => Array(fuel + 1).fill(-1));
    const mod = 1e9 + 7;
    const dfs = (i: number, k: number): number => {
        if (k < 0 || Math.abs(locations[i] - locations[finish]) > k) {
            return 0;
        }
        if (f[i][k] !== -1) {
            return f[i][k];
        }
        let ans = i === finish ? 1 : 0;
        for (let j = 0; j < n; ++j) {
            if (j != i) {
                const x = Math.abs(locations[i] - locations[j]);
                ans = (ans + dfs(j, k - x)) % mod;
            }
        }
        return (f[i][k] = ans);
    };
    return dfs(start, fuel);
}
```

### **...**

```

```

<!-- tabs:end -->
