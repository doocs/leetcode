# [2147. Number of Ways to Divide a Long Corridor](https://leetcode.com/problems/number-of-ways-to-divide-a-long-corridor)

[中文文档](/solution/2100-2199/2147.Number%20of%20Ways%20to%20Divide%20a%20Long%20Corridor/README.md)

## Description

<p>Along a long library corridor, there is a line of seats and decorative plants. You are given a <strong>0-indexed</strong> string <code>corridor</code> of length <code>n</code> consisting of letters <code>&#39;S&#39;</code> and <code>&#39;P&#39;</code> where each <code>&#39;S&#39;</code> represents a seat and each <code>&#39;P&#39;</code> represents a plant.</p>

<p>One room divider has <strong>already</strong> been installed to the left of index <code>0</code>, and <strong>another</strong> to the right of index <code>n - 1</code>. Additional room dividers can be installed. For each position between indices <code>i - 1</code> and <code>i</code> (<code>1 &lt;= i &lt;= n - 1</code>), at most one divider can be installed.</p>

<p>Divide the corridor into non-overlapping sections, where each section has <strong>exactly two seats</strong> with any number of plants. There may be multiple ways to perform the division. Two ways are <strong>different</strong> if there is a position with a room divider installed in the first way but not in the second way.</p>

<p>Return <em>the number of ways to divide the corridor</em>. Since the answer may be very large, return it <strong>modulo</strong> <code>10<sup>9</sup> + 7</code>. If there is no way, return <code>0</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2100-2199/2147.Number%20of%20Ways%20to%20Divide%20a%20Long%20Corridor/images/1.png" style="width: 410px; height: 199px;" />
<pre>
<strong>Input:</strong> corridor = &quot;SSPPSPS&quot;
<strong>Output:</strong> 3
<strong>Explanation:</strong> There are 3 different ways to divide the corridor.
The black bars in the above image indicate the two room dividers already installed.
Note that in each of the ways, <strong>each</strong> section has exactly <strong>two</strong> seats.
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2100-2199/2147.Number%20of%20Ways%20to%20Divide%20a%20Long%20Corridor/images/2.png" style="width: 357px; height: 68px;" />
<pre>
<strong>Input:</strong> corridor = &quot;PPSPSP&quot;
<strong>Output:</strong> 1
<strong>Explanation:</strong> There is only 1 way to divide the corridor, by not installing any additional dividers.
Installing any would create some section that does not have exactly two seats.
</pre>

<p><strong class="example">Example 3:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2100-2199/2147.Number%20of%20Ways%20to%20Divide%20a%20Long%20Corridor/images/3.png" style="width: 115px; height: 68px;" />
<pre>
<strong>Input:</strong> corridor = &quot;S&quot;
<strong>Output:</strong> 0
<strong>Explanation:</strong> There is no way to divide the corridor because there will always be a section that does not have exactly two seats.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == corridor.length</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>corridor[i]</code> is either <code>&#39;S&#39;</code> or <code>&#39;P&#39;</code>.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def numberOfWays(self, corridor: str) -> int:
        @cache
        def dfs(i, cnt):
            if i == n:
                return int(cnt == 2)
            cnt += corridor[i] == 'S'
            if cnt > 2:
                return 0
            ans = dfs(i + 1, cnt)
            if cnt == 2:
                ans += dfs(i + 1, 0)
                ans %= mod
            return ans

        n = len(corridor)
        mod = 10**9 + 7
        ans = dfs(0, 0)
        dfs.cache_clear()
        return ans
```

### **Java**

```java
class Solution {
    private String s;
    private int n;
    private int[][] f;
    private static final int MOD = (int) 1e9 + 7;

    public int numberOfWays(String corridor) {
        s = corridor;
        n = s.length();
        f = new int[n][3];
        for (var e : f) {
            Arrays.fill(e, -1);
        }
        return dfs(0, 0);
    }

    private int dfs(int i, int cnt) {
        if (i == n) {
            return cnt == 2 ? 1 : 0;
        }
        cnt += s.charAt(i) == 'S' ? 1 : 0;
        if (cnt > 2) {
            return 0;
        }
        if (f[i][cnt] != -1) {
            return f[i][cnt];
        }
        int ans = dfs(i + 1, cnt);
        if (cnt == 2) {
            ans += dfs(i + 1, 0);
            ans %= MOD;
        }
        f[i][cnt] = ans;
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    const int mod = 1e9 + 7;

    int numberOfWays(string corridor) {
        int n = corridor.size();
        vector<vector<int>> f(n, vector<int>(3, -1));
        function<int(int, int)> dfs;
        dfs = [&](int i, int cnt) {
            if (i == n) return cnt == 2 ? 1 : 0;
            cnt += corridor[i] == 'S';
            if (cnt > 2) return 0;
            if (f[i][cnt] != -1) return f[i][cnt];
            int ans = dfs(i + 1, cnt);
            if (cnt == 2) {
                ans += dfs(i + 1, 0);
                ans %= mod;
            }
            f[i][cnt] = ans;
            return ans;
        };
        return dfs(0, 0);
    }
};
```

### **Go**

```go
func numberOfWays(corridor string) int {
	n := len(corridor)
	var mod int = 1e9 + 7
	f := make([][]int, n)
	for i := range f {
		f[i] = make([]int, 3)
		for j := range f[i] {
			f[i][j] = -1
		}
	}
	var dfs func(i, cnt int) int
	dfs = func(i, cnt int) int {
		if i == n {
			if cnt == 2 {
				return 1
			}
			return 0
		}
		if corridor[i] == 'S' {
			cnt++
		}
		if cnt > 2 {
			return 0
		}
		if f[i][cnt] != -1 {
			return f[i][cnt]
		}
		ans := dfs(i+1, cnt)
		if cnt == 2 {
			ans += dfs(i+1, 0)
			ans %= mod
		}
		f[i][cnt] = ans
		return ans
	}
	return dfs(0, 0)
}
```

### **TypeScript**

```ts

```

### **...**

```

```

<!-- tabs:end -->
