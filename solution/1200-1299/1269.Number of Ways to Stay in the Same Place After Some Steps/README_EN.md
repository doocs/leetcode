# [1269. Number of Ways to Stay in the Same Place After Some Steps](https://leetcode.com/problems/number-of-ways-to-stay-in-the-same-place-after-some-steps)

[中文文档](/solution/1200-1299/1269.Number%20of%20Ways%20to%20Stay%20in%20the%20Same%20Place%20After%20Some%20Steps/README.md)

## Description

<p>You have a pointer at index <code>0</code> in an array of size <code>arrLen</code>. At each step, you can move 1 position to the left, 1 position to the right in the array, or stay in the same place (The pointer should not be placed outside the array at any time).</p>

<p>Given two integers <code>steps</code> and <code>arrLen</code>, return the number of ways such that your pointer is still at index <code>0</code> after <strong>exactly</strong> <code>steps</code> steps. Since the answer may be too large, return it <strong>modulo</strong> <code>10<sup>9</sup> + 7</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> steps = 3, arrLen = 2
<strong>Output:</strong> 4
<strong>Explanation: </strong>There are 4 differents ways to stay at index 0 after 3 steps.
Right, Left, Stay
Stay, Right, Left
Right, Stay, Left
Stay, Stay, Stay
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> steps = 2, arrLen = 4
<strong>Output:</strong> 2
<strong>Explanation:</strong> There are 2 differents ways to stay at index 0 after 2 steps
Right, Left
Stay, Stay
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> steps = 4, arrLen = 2
<strong>Output:</strong> 8
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= steps &lt;= 500</code></li>
	<li><code>1 &lt;= arrLen &lt;= 10<sup>6</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def numWays(self, steps: int, arrLen: int) -> int:
        @cache
        def dfs(i, j):
            if i > j or i >= arrLen or i < 0 or j < 0:
                return 0
            if i == 0 and j == 0:
                return 1
            ans = 0
            for k in range(-1, 2):
                ans += dfs(i + k, j - 1)
                ans %= mod
            return ans

        mod = 10**9 + 7
        return dfs(0, steps)
```

### **Java**

```java
class Solution {
    private Integer[][] f;
    private int n;

    public int numWays(int steps, int arrLen) {
        f = new Integer[steps][steps + 1];
        n = arrLen;
        return dfs(0, steps);
    }

    private int dfs(int i, int j) {
        if (i > j || i >= n || i < 0 || j < 0) {
            return 0;
        }
        if (i == 0 && j == 0) {
            return 1;
        }
        if (f[i][j] != null) {
            return f[i][j];
        }
        int ans = 0;
        final int mod = (int) 1e9 + 7;
        for (int k = -1; k <= 1; ++k) {
            ans = (ans + dfs(i + k, j - 1)) % mod;
        }
        return f[i][j] = ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int numWays(int steps, int arrLen) {
        int f[steps][steps + 1];
        memset(f, -1, sizeof f);
        const int mod = 1e9 + 7;
        function<int(int, int)> dfs = [&](int i, int j) -> int {
            if (i > j || i >= arrLen || i < 0 || j < 0) {
                return 0;
            }
            if (i == 0 && j == 0) {
                return 1;
            }
            if (f[i][j] != -1) {
                return f[i][j];
            }
            int ans = 0;
            for (int k = -1; k <= 1; ++k) {
                ans = (ans + dfs(i + k, j - 1)) % mod;
            }
            return f[i][j] = ans;
        };
        return dfs(0, steps);
    }
};
```

### **Go**

```go
func numWays(steps int, arrLen int) int {
	const mod int = 1e9 + 7
	f := make([][]int, steps)
	for i := range f {
		f[i] = make([]int, steps+1)
		for j := range f[i] {
			f[i][j] = -1
		}
	}
	var dfs func(i, j int) int
	dfs = func(i, j int) (ans int) {
		if i > j || i >= arrLen || i < 0 || j < 0 {
			return 0
		}
		if i == 0 && j == 0 {
			return 1
		}
		if f[i][j] != -1 {
			return f[i][j]
		}
		for k := -1; k <= 1; k++ {
			ans += dfs(i+k, j-1)
			ans %= mod
		}
		f[i][j] = ans
		return
	}
	return dfs(0, steps)
}
```

### **TypeScript**

```ts
function numWays(steps: number, arrLen: number): number {
    const f = Array.from({ length: steps }, () => Array(steps + 1).fill(-1));
    const mod = 10 ** 9 + 7;
    const dfs = (i: number, j: number) => {
        if (i > j || i >= arrLen || i < 0 || j < 0) {
            return 0;
        }
        if (i == 0 && j == 0) {
            return 1;
        }
        if (f[i][j] != -1) {
            return f[i][j];
        }
        let ans = 0;
        for (let k = -1; k <= 1; ++k) {
            ans = (ans + dfs(i + k, j - 1)) % mod;
        }
        return (f[i][j] = ans);
    };
    return dfs(0, steps);
}
```

### **...**

```

```

<!-- tabs:end -->
