---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1500-1599/1563.Stone%20Game%20V/README_EN.md
rating: 2087
source: Weekly Contest 203 Q4
tags:
    - Array
    - Math
    - Dynamic Programming
    - Game Theory
---

<!-- problem:start -->

# [1563. Stone Game V](https://leetcode.com/problems/stone-game-v)

[中文文档](/solution/1500-1599/1563.Stone%20Game%20V/README.md)

## Description

<!-- description:start -->

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

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Memoization + Pruning

First, we preprocess the prefix sum array $\textit{s}$, where $\textit{s}[i]$ represents the sum of the first $i$ elements of the array $\textit{stoneValue}$.

Next, we design a function $\textit{dfs}(i, j)$, which represents the maximum score Alice can get from the stones in the subarray $\textit{stoneValue}$ within the index range $[i, j]$. The answer is $\textit{dfs}(0, n - 1)$.

The calculation process of the function $\textit{dfs}(i, j)$ is as follows:

-   If $i \geq j$, it means there is only one stone left, and Alice cannot split it, so return $0$.
-   Otherwise, we enumerate the split point $k$, i.e., $i \leq k < j$, splitting the stones in the subarray $\textit{stoneValue}$ within the index range $[i, j]$ into two parts: $[i, k]$ and $[k + 1, j]$. We calculate $a$ and $b$, which represent the total sum of the stones in the two parts, respectively. Then we calculate $\textit{dfs}(i, k)$ and $\textit{dfs}(k + 1, j)$, and update the answer.

Note that if $a < b$ and $\textit{ans} \geq a \times 2$, this enumeration can be skipped; if $a > b$ and $\textit{ans} \geq b \times 2$, all subsequent enumerations can be skipped, and the loop can be exited directly.

Finally, we return the answer.

To avoid repeated calculations, we can use memoization and pruning to optimize the enumeration efficiency.

The time complexity is $O(n^3)$, and the space complexity is $O(n^2)$. Here, $n$ is the length of the array $\textit{stoneValue}$.

<!-- tabs:start -->

#### Python3

```python
def max(a: int, b: int) -> int:
    return a if a > b else b


class Solution:
    def stoneGameV(self, stoneValue: List[int]) -> int:
        @cache
        def dfs(i: int, j: int) -> int:
            if i >= j:
                return 0
            ans = l = 0
            r = s[j + 1] - s[i]
            for k in range(i, j):
                l += stoneValue[k]
                r -= stoneValue[k]
                if l < r:
                    if ans >= l * 2:
                        continue
                    ans = max(ans, l + dfs(i, k))
                elif l > r:
                    if ans >= r * 2:
                        break
                    ans = max(ans, r + dfs(k + 1, j))
                else:
                    ans = max(ans, max(l + dfs(i, k), r + dfs(k + 1, j)))
            return ans

        s = list(accumulate(stoneValue, initial=0))
        return dfs(0, len(stoneValue) - 1)
```

#### Java

```java
class Solution {
    private int n;
    private int[] s;
    private int[] nums;
    private Integer[][] f;

    public int stoneGameV(int[] stoneValue) {
        n = stoneValue.length;
        s = new int[n + 1];
        nums = stoneValue;
        f = new Integer[n][n];
        for (int i = 1; i <= n; ++i) {
            s[i] = s[i - 1] + nums[i - 1];
        }
        return dfs(0, n - 1);
    }

    private int dfs(int i, int j) {
        if (i >= j) {
            return 0;
        }
        if (f[i][j] != null) {
            return f[i][j];
        }
        int ans = 0, l = 0, r = s[j + 1] - s[i];
        for (int k = i; k < j; ++k) {
            l += nums[k];
            r -= nums[k];
            if (l < r) {
                if (ans > l * 2) {
                    continue;
                }
                ans = Math.max(ans, l + dfs(i, k));
            } else if (l > r) {
                if (ans > r * 2) {
                    break;
                }
                ans = Math.max(ans, r + dfs(k + 1, j));
            } else {
                ans = Math.max(ans, Math.max(l + dfs(i, k), r + dfs(k + 1, j)));
            }
        }
        return f[i][j] = ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int stoneGameV(vector<int>& stoneValue) {
        int n = stoneValue.size();
        int s[n + 1];
        s[0] = 0;
        for (int i = 0; i < n; i++) {
            s[i + 1] = s[i] + stoneValue[i];
        }
        int f[n][n];
        memset(f, -1, sizeof(f));
        auto dfs = [&](this auto&& dfs, int i, int j) -> int {
            if (i >= j) {
                return 0;
            }
            if (f[i][j] != -1) {
                return f[i][j];
            }
            int ans = 0, l = 0, r = s[j + 1] - s[i];
            for (int k = i; k < j; ++k) {
                l += stoneValue[k];
                r -= stoneValue[k];
                if (l < r) {
                    if (ans > l * 2) {
                        continue;
                    }
                    ans = max(ans, l + dfs(i, k));
                } else if (l > r) {
                    if (ans > r * 2) {
                        break;
                    }
                    ans = max(ans, r + dfs(k + 1, j));
                } else {
                    ans = max({ans, l + dfs(i, k), r + dfs(k + 1, j)});
                }
            }
            return f[i][j] = ans;
        };
        return dfs(0, n - 1);
    }
};
```

#### Go

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
		for j := range f[i] {
			f[i][j] = -1
		}
	}
	var dfs func(int, int) int
	dfs = func(i, j int) int {
		if i >= j {
			return 0
		}
		if f[i][j] != -1 {
			return f[i][j]
		}
		ans, l, r := 0, 0, s[j+1]-s[i]
		for k := i; k < j; k++ {
			l += stoneValue[k]
			r -= stoneValue[k]
			if l < r {
				if ans > l*2 {
					continue
				}
				ans = max(ans, dfs(i, k)+l)
			} else if l > r {
				if ans > r*2 {
					break
				}
				ans = max(ans, dfs(k+1, j)+r)
			} else {
				ans = max(ans, max(dfs(i, k), dfs(k+1, j))+l)
			}
		}
		f[i][j] = ans
		return ans
	}
	return dfs(0, n-1)
}
```

#### TypeScript

```ts
function stoneGameV(stoneValue: number[]): number {
    const n = stoneValue.length;
    const s: number[] = Array(n + 1).fill(0);
    for (let i = 0; i < n; i++) {
        s[i + 1] = s[i] + stoneValue[i];
    }
    const f: number[][] = Array.from({ length: n }, () => Array(n).fill(-1));

    const dfs = (i: number, j: number): number => {
        if (i >= j) {
            return 0;
        }
        if (f[i][j] !== -1) {
            return f[i][j];
        }
        let [ans, l, r] = [0, 0, s[j + 1] - s[i]];
        for (let k = i; k < j; ++k) {
            l += stoneValue[k];
            r -= stoneValue[k];
            if (l < r) {
                if (ans > l * 2) {
                    continue;
                }
                ans = Math.max(ans, l + dfs(i, k));
            } else if (l > r) {
                if (ans > r * 2) {
                    break;
                }
                ans = Math.max(ans, r + dfs(k + 1, j));
            } else {
                ans = Math.max(ans, l + dfs(i, k), r + dfs(k + 1, j));
            }
        }
        return (f[i][j] = ans);
    };

    return dfs(0, n - 1);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
