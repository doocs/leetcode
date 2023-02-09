# [1223. Dice Roll Simulation](https://leetcode.com/problems/dice-roll-simulation)

[中文文档](/solution/1200-1299/1223.Dice%20Roll%20Simulation/README.md)

## Description

<p>A die simulator generates a random number from <code>1</code> to <code>6</code> for each roll. You introduced a constraint to the generator such that it cannot roll the number <code>i</code> more than <code>rollMax[i]</code> (<strong>1-indexed</strong>) consecutive times.</p>

<p>Given an array of integers <code>rollMax</code> and an integer <code>n</code>, return <em>the number of distinct sequences that can be obtained with exact </em><code>n</code><em> rolls</em>. Since the answer may be too large, return it <strong>modulo</strong> <code>10<sup>9</sup> + 7</code>.</p>

<p>Two sequences are considered different if at least one element differs from each other.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> n = 2, rollMax = [1,1,2,2,2,3]
<strong>Output:</strong> 34
<strong>Explanation:</strong> There will be 2 rolls of die, if there are no constraints on the die, there are 6 * 6 = 36 possible combinations. In this case, looking at rollMax array, the numbers 1 and 2 appear at most once consecutively, therefore sequences (1,1) and (2,2) cannot occur, so the final answer is 36-2 = 34.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 2, rollMax = [1,1,1,1,1,1]
<strong>Output:</strong> 30
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> n = 3, rollMax = [1,1,1,2,2,3]
<strong>Output:</strong> 181
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 5000</code></li>
	<li><code>rollMax.length == 6</code></li>
	<li><code>1 &lt;= rollMax[i] &lt;= 15</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def dieSimulator(self, n: int, rollMax: List[int]) -> int:
        @cache
        def dfs(i, j, x):
            if i >= n:
                return 1
            ans = 0
            for k in range(1, 7):
                if k != j:
                    ans += dfs(i + 1, k, 1)
                elif x < rollMax[j - 1]:
                    ans += dfs(i + 1, j, x + 1)
            return ans % (10 ** 9 + 7)

        return dfs(0, 0, 0)
```

```python
class Solution:
    def dieSimulator(self, n: int, rollMax: List[int]) -> int:
        f = [[[0] * 16 for _ in range(7)] for _ in range(n + 1)]
        for j in range(1, 7):
            f[1][j][1] = 1
        for i in range(2, n + 1):
            for j in range(1, 7):
                for x in range(1, rollMax[j - 1] + 1):
                    for k in range(1, 7):
                        if k != j:
                            f[i][k][1] += f[i - 1][j][x]
                        elif x + 1 <= rollMax[j - 1]:
                            f[i][j][x + 1] += f[i - 1][j][x]
        mod = 10**9 + 7
        ans = 0
        for j in range(1, 7):
            for x in range(1, rollMax[j - 1] + 1):
                ans = (ans + f[n][j][x]) % mod
        return ans
```

### **Java**

```java
class Solution {
    private Integer[][][] f;
    private int[] rollMax;

    public int dieSimulator(int n, int[] rollMax) {
        f = new Integer[n][7][16];
        this.rollMax = rollMax;
        return dfs(0, 0, 0);
    }

    private int dfs(int i, int j, int x) {
        if (i >= f.length) {
            return 1;
        }
        if (f[i][j][x] != null) {
            return f[i][j][x];
        }
        long ans = 0;
        for (int k = 1; k <= 6; ++k) {
            if (k != j) {
                ans += dfs(i + 1, k, 1);
            } else if (x < rollMax[j - 1]) {
                ans += dfs(i + 1, j, x + 1);
            }
        }
        ans %= 1000000007;
        return f[i][j][x] = (int) ans;
    }
}
```

```java
class Solution {
    public int dieSimulator(int n, int[] rollMax) {
        int[][][] f = new int[n + 1][7][16];
        for (int j = 1; j <= 6; ++j) {
            f[1][j][1] = 1;
        }
        final int mod = (int) 1e9 + 7;
        for (int i = 2; i <= n; ++i) {
            for (int j = 1; j <= 6; ++j) {
                for (int x = 1; x <= rollMax[j - 1]; ++x) {
                    for (int k = 1; k <= 6; ++k) {
                        if (k != j) {
                            f[i][k][1] = (f[i][k][1] + f[i - 1][j][x]) % mod;
                        } else if (x + 1 <= rollMax[j - 1]) {
                            f[i][j][x + 1] = (f[i][j][x + 1] + f[i - 1][j][x]) % mod;
                        }
                    }
                }
            }
        }
        int ans = 0;
        for (int j = 1; j <= 6; ++j) {
            for (int x = 1; x <= rollMax[j - 1]; ++x) {
                ans = (ans + f[n][j][x]) % mod;
            }
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int dieSimulator(int n, vector<int>& rollMax) {
        int f[n][7][16];
        memset(f, 0, sizeof f);
        const int mod = 1e9 + 7;
        function<int(int, int, int)> dfs = [&](int i, int j, int x) -> int {
            if (i >= n) {
                return 1;
            }
            if (f[i][j][x]) {
                return f[i][j][x];
            }
            long ans = 0;
            for (int k = 1; k <= 6; ++k) {
                if (k != j) {
                    ans += dfs(i + 1, k, 1);
                } else if (x < rollMax[j - 1]) {
                    ans += dfs(i + 1, j, x + 1);
                }
            }
            ans %= mod;
            return f[i][j][x] = ans;
        };
        return dfs(0, 0, 0);
    }
};
```

```cpp
class Solution {
public:
    int dieSimulator(int n, vector<int>& rollMax) {
        int f[n + 1][7][16];
        memset(f, 0, sizeof f);
        for (int j = 1; j <= 6; ++j) {
            f[1][j][1] = 1;
        }
        const int mod = 1e9 + 7;
        for (int i = 2; i <= n; ++i) {
            for (int j = 1; j <= 6; ++j) {
                for (int x = 1; x <= rollMax[j - 1]; ++x) {
                    for (int k = 1; k <= 6; ++k) {
                        if (k != j) {
                            f[i][k][1] = (f[i][k][1] + f[i - 1][j][x]) % mod;
                        } else if (x + 1 <= rollMax[j - 1]) {
                            f[i][j][x + 1] = (f[i][j][x + 1] + f[i - 1][j][x]) % mod;
                        }
                    }
                }
            }
        }
        int ans = 0;
        for (int j = 1; j <= 6; ++j) {
            for (int x = 1; x <= rollMax[j - 1]; ++x) {
                ans = (ans + f[n][j][x]) % mod;
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func dieSimulator(n int, rollMax []int) int {
	f := make([][7][16]int, n)
	const mod = 1e9 + 7
	var dfs func(i, j, x int) int
	dfs = func(i, j, x int) int {
		if i >= n {
			return 1
		}
		if f[i][j][x] != 0 {
			return f[i][j][x]
		}
		ans := 0
		for k := 1; k <= 6; k++ {
			if k != j {
				ans += dfs(i+1, k, 1)
			} else if x < rollMax[j-1] {
				ans += dfs(i+1, j, x+1)
			}
		}
		f[i][j][x] = ans % mod
		return f[i][j][x]
	}
	return dfs(0, 0, 0)
}
```

```go
func dieSimulator(n int, rollMax []int) (ans int) {
	f := make([][7][16]int, n+1)
	for j := 1; j <= 6; j++ {
		f[1][j][1] = 1
	}
	const mod = 1e9 + 7
	for i := 2; i <= n; i++ {
		for j := 1; j <= 6; j++ {
			for x := 1; x <= rollMax[j-1]; x++ {
				for k := 1; k <= 6; k++ {
					if k != j {
						f[i][k][1] = (f[i][k][1] + f[i-1][j][x]) % mod
					} else if x+1 <= rollMax[j-1] {
						f[i][j][x+1] = (f[i][j][x+1] + f[i-1][j][x]) % mod
					}
				}
			}
		}
	}
	for j := 1; j <= 6; j++ {
		for x := 1; x <= rollMax[j-1]; x++ {
			ans = (ans + f[n][j][x]) % mod
		}
	}
	return
}
```

### **...**

```

```

<!-- tabs:end -->
