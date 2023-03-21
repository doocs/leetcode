# [1563. Stone Game V](https://leetcode.com/problems/stone-game-v)

[中文文档](/solution/1500-1599/1563.Stone%20Game%20V/README.md)

## Description

<p>There are several stones <strong>arranged in a row</strong>, and each stone has an associated value which is an integer given in the array <code>stoneValue</code>.</p>

<p>In each round of the game, Alice divides the row into <strong>two non-empty rows</strong> (i.e. left row and right row), then Bob calculates the value of each row which is the sum of the values of all the stones in this row. Bob throws away the row which has the maximum value, and Alice&#39;s score increases by the value of the remaining row. If the value of the two rows are equal, Bob lets Alice decide which row will be thrown away. The next round starts with the remaining row.</p>

<p>The game ends when there is only <strong>one stone remaining</strong>. Alice&#39;s is initially <strong>zero</strong>.</p>

<p>Return <i>the maximum score that Alice can obtain</i>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> stoneValue = [6,2,3,4,5,5]
<strong>Output:</strong> 18
<strong>Explanation:</strong> In the first round, Alice divides the row to [6,2,3], [4,5,5]. The left row has the value 11 and the right row has value 14. Bob throws away the right row and Alice&#39;s score is now 11.
In the second round Alice divides the row to [6], [2,3]. This time Bob throws away the left row and Alice&#39;s score becomes 16 (11 + 5).
The last round Alice has only one choice to divide the row which is [2], [3]. Bob throws away the right row and Alice&#39;s score is now 18 (16 + 2). The game ends because only one stone is remaining in the row.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> stoneValue = [7,7,7,7,7,7,7]
<strong>Output:</strong> 28
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> stoneValue = [4]
<strong>Output:</strong> 0
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= stoneValue.length &lt;= 500</code></li>
	<li><code>1 &lt;= stoneValue[i] &lt;= 10<sup>6</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def stoneGameV(self, stoneValue: List[int]) -> int:
        @cache
        def dfs(i, j):
            if i == j:
                return 0
            ans = a = 0
            for k in range(i, j):
                a += stoneValue[k]
                b = s[j + 1] - s[i] - a
                if a < b:
                    if ans >= a * 2:
                        continue
                    ans = max(ans, a + dfs(i, k))
                elif a > b:
                    if ans >= b * 2:
                        break
                    ans = max(ans, b + dfs(k + 1, j))
                else:
                    ans = max(ans, a + dfs(i, k), b + dfs(k + 1, j))
            return ans

        s = list(accumulate(stoneValue, initial=0))
        return dfs(0, len(stoneValue) - 1)
```

### **Java**

```java
class Solution {
    private int n;
    private int[] s;
    private int[] stoneValue;
    private Integer[][] f;

    public int stoneGameV(int[] stoneValue) {
        n = stoneValue.length;
        this.stoneValue = stoneValue;
        s = new int[n + 1];
        for (int i = 1; i <= n; ++i) {
            s[i] = s[i - 1] + stoneValue[i - 1];
        }
        f = new Integer[n][n];
        return dfs(0, n - 1);
    }

    private int dfs(int i, int j) {
        if (i == j) {
            return 0;
        }
        if (f[i][j] != null) {
            return f[i][j];
        }
        int ans = 0;
        int a = 0;
        for (int k = i; k < j; ++k) {
            a += stoneValue[k];
            int b = s[j + 1] - s[i] - a;
            if (a < b) {
                if (ans >= a * 2) {
                    continue;
                }
                ans = Math.max(ans, a + dfs(i, k));
            } else if (a > b) {
                if (ans >= b * 2) {
                    break;
                }
                ans = Math.max(ans, b + dfs(k + 1, j));
            } else {
                ans = Math.max(ans, Math.max(a + dfs(i, k), b + dfs(k + 1, j)));
            }
        }
        return f[i][j] = ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int stoneGameV(vector<int>& stoneValue) {
        int n = stoneValue.size();
        int s[n + 1];
        s[0] = 0;
        for (int i = 1; i <= n; ++i) {
            s[i] = s[i - 1] + stoneValue[i - 1];
        }
        int f[n][n];
        memset(f, 0, sizeof(f));
        function<int(int, int)> dfs = [&](int i, int j) -> int {
            if (i == j) {
                return 0;
            }
            if (f[i][j]) {
                return f[i][j];
            }
            int ans = 0;
            int a = 0;
            for (int k = i; k < j; ++k) {
                a += stoneValue[k];
                int b = s[j + 1] - s[i] - a;
                if (a < b) {
                    if (ans >= a * 2) {
                        continue;
                    }
                    ans = max(ans, a + dfs(i, k));
                } else if (a > b) {
                    if (ans >= b * 2) {
                        break;
                    }
                    ans = max(ans, b + dfs(k + 1, j));
                } else {
                    ans = max({ans, a + dfs(i, k), b + dfs(k + 1, j)});
                }
            }
            return f[i][j] = ans;
        };
        return dfs(0, n - 1);
    }
};
```

### **Go**

```go
func stoneGameV(stoneValue []int) int {
	n := len(stoneValue)
	s := make([]int, n+1)
	for i, x := range stoneValue {
		s[i+1] = s[i] + x
	}
	f := make([][]int, n)
	for i := range f {
		f[i] = make([]int, n)
	}
	var dfs func(i, j int) int
	dfs = func(i, j int) int {
		if i == j {
			return 0
		}
		if f[i][j] != 0 {
			return f[i][j]
		}
		ans, a := 0, 0
		for k := i; k < j; k++ {
			a += stoneValue[k]
			b := s[j+1] - s[i] - a
			if a < b {
				if ans >= a*2 {
					continue
				}
				ans = max(ans, a+dfs(i, k))
			} else if a > b {
				if ans >= b*2 {
					break
				}
				ans = max(ans, b+dfs(k+1, j))
			} else {
				ans = max(ans, max(a+dfs(i, k), b+dfs(k+1, j)))
			}
		}
		f[i][j] = ans
		return ans
	}
	return dfs(0, n-1)
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
