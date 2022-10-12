# [2209. Minimum White Tiles After Covering With Carpets](https://leetcode.com/problems/minimum-white-tiles-after-covering-with-carpets)

[中文文档](/solution/2200-2299/2209.Minimum%20White%20Tiles%20After%20Covering%20With%20Carpets/README.md)

## Description

<p>You are given a <strong>0-indexed binary</strong> string <code>floor</code>, which represents the colors of tiles on a floor:</p>

<ul>
	<li><code>floor[i] = &#39;0&#39;</code> denotes that the <code>i<sup>th</sup></code> tile of the floor is colored <strong>black</strong>.</li>
	<li>On the other hand, <code>floor[i] = &#39;1&#39;</code> denotes that the <code>i<sup>th</sup></code> tile of the floor is colored <strong>white</strong>.</li>
</ul>

<p>You are also given <code>numCarpets</code> and <code>carpetLen</code>. You have <code>numCarpets</code> <strong>black</strong> carpets, each of length <code>carpetLen</code> tiles. Cover the tiles with the given carpets such that the number of <strong>white</strong> tiles still visible is <strong>minimum</strong>. Carpets may overlap one another.</p>

<p>Return <em>the <strong>minimum</strong> number of white tiles still visible.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2200-2299/2209.Minimum%20White%20Tiles%20After%20Covering%20With%20Carpets/images/ex1-1.png" style="width: 400px; height: 73px;" />
<pre>
<strong>Input:</strong> floor = &quot;10110101&quot;, numCarpets = 2, carpetLen = 2
<strong>Output:</strong> 2
<strong>Explanation:</strong> 
The figure above shows one way of covering the tiles with the carpets such that only 2 white tiles are visible.
No other way of covering the tiles with the carpets can leave less than 2 white tiles visible.
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2200-2299/2209.Minimum%20White%20Tiles%20After%20Covering%20With%20Carpets/images/ex2.png" style="width: 353px; height: 123px;" />
<pre>
<strong>Input:</strong> floor = &quot;11111&quot;, numCarpets = 2, carpetLen = 3
<strong>Output:</strong> 0
<strong>Explanation:</strong> 
The figure above shows one way of covering the tiles with the carpets such that no white tiles are visible.
Note that the carpets are able to overlap one another.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= carpetLen &lt;= floor.length &lt;= 1000</code></li>
	<li><code>floor[i]</code> is either <code>&#39;0&#39;</code> or <code>&#39;1&#39;</code>.</li>
	<li><code>1 &lt;= numCarpets &lt;= 1000</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def minimumWhiteTiles(self, floor: str, numCarpets: int, carpetLen: int) -> int:
        @cache
        def dfs(i, j):
            if i >= n:
                return 0
            if floor[i] == '0':
                return dfs(i + 1, j)
            if j == 0:
                return s[-1] - s[i]
            return min(1 + dfs(i + 1, j), dfs(i + carpetLen, j - 1))

        n = len(floor)
        s = [0] * (n + 1)
        for i, c in enumerate(floor):
            s[i + 1] = s[i] + int(c == '1')
        ans = dfs(0, numCarpets)
        dfs.cache_clear()
        return ans
```

### **Java**

```java
class Solution {
    private int[][] f;
    private int[] s;
    private int n;
    private int k;

    public int minimumWhiteTiles(String floor, int numCarpets, int carpetLen) {
        n = floor.length();
        f = new int[n][numCarpets + 1];
        for (var e : f) {
            Arrays.fill(e, -1);
        }
        s = new int[n + 1];
        for (int i = 0; i < n; ++i) {
            s[i + 1] = s[i] + (floor.charAt(i) == '1' ? 1 : 0);
        }
        k = carpetLen;
        return dfs(0, numCarpets);
    }

    private int dfs(int i, int j) {
        if (i >= n) {
            return 0;
        }
        if (j == 0) {
            return s[n] - s[i];
        }
        if (f[i][j] != -1) {
            return f[i][j];
        }
        if (s[i + 1] == s[i]) {
            return dfs(i + 1, j);
        }
        int ans = Math.min(1 + dfs(i + 1, j), dfs(i + k, j - 1));
        f[i][j] = ans;
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int minimumWhiteTiles(string floor, int numCarpets, int carpetLen) {
        int n = floor.size();
        vector<vector<int>> f(n, vector<int>(numCarpets + 1, -1));
        vector<int> s(n + 1);
        for (int i = 0; i < n; ++i) {
            s[i + 1] = s[i] + (floor[i] == '1');
        }
        function<int(int, int)> dfs;
        dfs = [&](int i, int j) {
            if (i >= n) return 0;
            if (j == 0) return s[n] - s[i];
            if (f[i][j] != -1) return f[i][j];
            if (s[i + 1] == s[i]) return dfs(i + 1, j);
            int ans = min(1 + dfs(i + 1, j), dfs(i + carpetLen, j - 1));
            f[i][j] = ans;
            return ans;
        };
        return dfs(0, numCarpets);
    }
};
```

### **Go**

```go
func minimumWhiteTiles(floor string, numCarpets int, carpetLen int) int {
	n := len(floor)
	f := make([][]int, n)
	for i := range f {
		f[i] = make([]int, numCarpets+1)
		for j := range f[i] {
			f[i][j] = -1
		}
	}
	s := make([]int, n+1)
	for i, c := range floor {
		s[i+1] = s[i] + int(c-'0')
	}
	var dfs func(i, j int) int
	dfs = func(i, j int) int {
		if i >= n {
			return 0
		}
		if j == 0 {
			return s[n] - s[i]
		}
		if f[i][j] != -1 {
			return f[i][j]
		}
		if s[i+1] == s[i] {
			return dfs(i+1, j)
		}
		ans := min(1+dfs(i+1, j), dfs(i+carpetLen, j-1))
		f[i][j] = ans
		return ans
	}
	return dfs(0, numCarpets)
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

```

### **...**

```

```

<!-- tabs:end -->
