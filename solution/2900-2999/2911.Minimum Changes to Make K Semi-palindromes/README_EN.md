# [2911. Minimum Changes to Make K Semi-palindromes](https://leetcode.com/problems/minimum-changes-to-make-k-semi-palindromes)

[中文文档](/solution/2900-2999/2911.Minimum%20Changes%20to%20Make%20K%20Semi-palindromes/README.md)

## Description

<p>Given a string <code>s</code> and an integer <code>k</code>, partition <code>s</code> into <code>k</code> <strong>substrings</strong> such that the sum of the number of letter changes required to turn each <strong>substring</strong> into a <strong>semi-palindrome</strong> is minimized.</p>

<p>Return <em>an integer denoting the <strong>minimum</strong> number of letter changes required.</em></p>

<p><strong>Notes</strong></p>

<ul>
	<li>A string is a <strong>palindrome</strong> if it can be read the same way from left to right and right to left.</li>
	<li>A string with a length of <code>len</code> is considered a <strong>semi-palindrome</strong> if there exists a positive integer <code>d</code> such that <code>1 &lt;= d &lt; len</code> and <code>len % d == 0</code>, and if we take indices that have the same modulo by <code>d</code>, they form a <strong>palindrome</strong>. For example, <code>&quot;aa&quot;</code>, <code>&quot;aba&quot;</code>, <code>&quot;adbgad&quot;</code>, and, <code>&quot;abab&quot;</code> are <strong>semi-palindrome</strong> and <code>&quot;a&quot;</code>, <code>&quot;ab&quot;</code>, and, <code>&quot;abca&quot;</code> are not.</li>
	<li>A <strong>substring</strong> is a contiguous sequence of characters within a string.</li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;abcac&quot;, k = 2
<strong>Output:</strong> 1
<strong>Explanation:</strong> We can divide s into substrings &quot;ab&quot; and &quot;cac&quot;. The string &quot;cac&quot; is already a semi-palindrome. If we change &quot;ab&quot; to &quot;aa&quot;, it becomes a semi-palindrome with d = 1.
It can be shown that there is no way to divide the string &quot;abcac&quot; into two semi-palindrome substrings. Therefore, the answer would be at least 1.</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;abcdef&quot;, k = 2
<strong>Output:</strong> 2
<strong>Explanation:</strong> We can divide it into substrings &quot;abc&quot; and &quot;def&quot;. Each of the substrings &quot;abc&quot; and &quot;def&quot; requires one change to become a semi-palindrome, so we need 2 changes in total to make all substrings semi-palindrome.
It can be shown that we cannot divide the given string into two substrings in a way that it would require less than 2 changes.</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;aabbaa&quot;, k = 3
<strong>Output:</strong> 0
<strong>Explanation:</strong> We can divide it into substrings &quot;aa&quot;, &quot;bb&quot; and &quot;aa&quot;.
The strings &quot;aa&quot; and &quot;bb&quot; are already semi-palindromes. Thus, the answer is zero.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= s.length &lt;= 200</code></li>
	<li><code>1 &lt;= k &lt;= s.length / 2</code></li>
	<li><code>s</code> consists only of lowercase English letters.</li>
</ul>

## Solutions

**Optimization: Preprocessing Divisors List**

We can calculate the divisors list for each length, which can save a lot of division operations in the inner loop.

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def minimumChanges(self, s: str, k: int) -> int:
        n = len(s)
        g = [[inf] * (n + 1) for _ in range(n + 1)]
        for i in range(1, n + 1):
            for j in range(i, n + 1):
                m = j - i + 1
                for d in range(1, m):
                    if m % d == 0:
                        cnt = 0
                        for l in range(m):
                            r = (m // d - 1 - l // d) * d + l % d
                            if l >= r:
                                break
                            if s[i - 1 + l] != s[i - 1 + r]:
                                cnt += 1
                        g[i][j] = min(g[i][j], cnt)

        f = [[inf] * (k + 1) for _ in range(n + 1)]
        f[0][0] = 0
        for i in range(1, n + 1):
            for j in range(1, k + 1):
                for h in range(i - 1):
                    f[i][j] = min(f[i][j], f[h][j - 1] + g[h + 1][i])
        return f[n][k]
```

### **Java**

```java
class Solution {
    public int minimumChanges(String s, int k) {
        int n = s.length();
        int[][] g = new int[n + 1][n + 1];
        int[][] f = new int[n + 1][k + 1];
        final int inf = 1 << 30;
        for (int i = 0; i <= n; ++i) {
            Arrays.fill(g[i], inf);
            Arrays.fill(f[i], inf);
        }
        for (int i = 1; i <= n; ++i) {
            for (int j = i; j <= n; ++j) {
                int m = j - i + 1;
                for (int d = 1; d < m; ++d) {
                    if (m % d == 0) {
                        int cnt = 0;
                        for (int l = 0; l < m; ++l) {
                            int r = (m / d - 1 - l / d) * d + l % d;
                            if (l >= r) {
                                break;
                            }
                            if (s.charAt(i - 1 + l) != s.charAt(i - 1 + r)) {
                                ++cnt;
                            }
                        }
                        g[i][j] = Math.min(g[i][j], cnt);
                    }
                }
            }
        }
        f[0][0] = 0;
        for (int i = 1; i <= n; ++i) {
            for (int j = 1; j <= k; ++j) {
                for (int h = 0; h < i - 1; ++h) {
                    f[i][j] = Math.min(f[i][j], f[h][j - 1] + g[h + 1][i]);
                }
            }
        }
        return f[n][k];
    }
}
```

```java
class Solution {
    static int inf = 200;
    List<Integer>[] factorLists;
    int n;
    int k;
    char[] ch;
    Integer[][] cost;
    public int minimumChanges(String s, int k) {
        this.k = k;
        n = s.length();
        ch = s.toCharArray();

        factorLists = getFactorLists(n);
        cost = new Integer[n + 1][n + 1];
        return calcDP();
    }
    static List<Integer>[] getFactorLists(int n) {
        List<Integer>[] l = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            l[i] = new ArrayList<>();
            l[i].add(1);
        }
        for (int factor = 2; factor < n; factor++) {
            for (int num = factor + factor; num <= n; num += factor) {
                l[num].add(factor);
            }
        }
        return l;
    }
    int calcDP() {
        int[] dp = new int[n];
        for (int i = n - k * 2 + 1; i >= 1; i--) {
            dp[i] = getCost(0, i);
        }
        int bound = 0;
        for (int subs = 2; subs <= k; subs++) {
            bound = subs * 2;
            for (int i = n - 1 - k * 2 + subs * 2; i >= bound - 1; i--) {
                dp[i] = inf;
                for (int prev = bound - 3; prev < i - 1; prev++) {
                    dp[i] = Math.min(dp[i], dp[prev] + getCost(prev + 1, i));
                }
            }
        }
        return dp[n - 1];
    }
    int getCost(int l, int r) {
        if (l >= r) {
            return inf;
        }
        if (cost[l][r] != null) {
            return cost[l][r];
        }
        cost[l][r] = inf;
        for (int factor : factorLists[r - l + 1]) {
            cost[l][r] = Math.min(cost[l][r], getStepwiseCost(l, r, factor));
        }
        return cost[l][r];
    }
    int getStepwiseCost(int l, int r, int stepsize) {
        if (l >= r) {
            return 0;
        }
        int left = 0;
        int right = 0;
        int count = 0;
        for (int i = 0; i < stepsize; i++) {
            left = l + i;
            right = r - stepsize + 1 + i;
            while (left + stepsize <= right) {
                if (ch[left] != ch[right]) {
                    count++;
                }
                left += stepsize;
                right -= stepsize;
            }
        }
        return count;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int minimumChanges(string s, int k) {
        int n = s.size();
        int g[n + 1][n + 1];
        int f[n + 1][k + 1];
        memset(g, 0x3f, sizeof(g));
        memset(f, 0x3f, sizeof(f));
        f[0][0] = 0;
        for (int i = 1; i <= n; ++i) {
            for (int j = i; j <= n; ++j) {
                int m = j - i + 1;
                for (int d = 1; d < m; ++d) {
                    if (m % d == 0) {
                        int cnt = 0;
                        for (int l = 0; l < m; ++l) {
                            int r = (m / d - 1 - l / d) * d + l % d;
                            if (l >= r) {
                                break;
                            }
                            if (s[i - 1 + l] != s[i - 1 + r]) {
                                ++cnt;
                            }
                        }
                        g[i][j] = min(g[i][j], cnt);
                    }
                }
            }
        }
        for (int i = 1; i <= n; ++i) {
            for (int j = 1; j <= k; ++j) {
                for (int h = 0; h < i - 1; ++h) {
                    f[i][j] = min(f[i][j], f[h][j - 1] + g[h + 1][i]);
                }
            }
        }
        return f[n][k];
    }
};
```

### **Go**

```go
func minimumChanges(s string, k int) int {
	n := len(s)
	g := make([][]int, n+1)
	f := make([][]int, n+1)
	const inf int = 1 << 30
	for i := range g {
		g[i] = make([]int, n+1)
		f[i] = make([]int, k+1)
		for j := range g[i] {
			g[i][j] = inf
		}
		for j := range f[i] {
			f[i][j] = inf
		}
	}
	f[0][0] = 0
	for i := 1; i <= n; i++ {
		for j := i; j <= n; j++ {
			m := j - i + 1
			for d := 1; d < m; d++ {
				if m%d == 0 {
					cnt := 0
					for l := 0; l < m; l++ {
						r := (m/d-1-l/d)*d + l%d
						if l >= r {
							break
						}
						if s[i-1+l] != s[i-1+r] {
							cnt++
						}
					}
					g[i][j] = min(g[i][j], cnt)
				}
			}
		}
	}
	for i := 1; i <= n; i++ {
		for j := 1; j <= k; j++ {
			for h := 0; h < i-1; h++ {
				f[i][j] = min(f[i][j], f[h][j-1]+g[h+1][i])
			}
		}
	}
	return f[n][k]
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
function minimumChanges(s: string, k: number): number {
    const n = s.length;
    const g = Array.from({ length: n + 1 }, () => Array.from({ length: n + 1 }, () => Infinity));
    const f = Array.from({ length: n + 1 }, () => Array.from({ length: k + 1 }, () => Infinity));
    f[0][0] = 0;
    for (let i = 1; i <= n; ++i) {
        for (let j = 1; j <= n; ++j) {
            const m = j - i + 1;
            for (let d = 1; d < m; ++d) {
                if (m % d === 0) {
                    let cnt = 0;
                    for (let l = 0; l < m; ++l) {
                        const r = (((m / d) | 0) - 1 - ((l / d) | 0)) * d + (l % d);
                        if (l >= r) {
                            break;
                        }
                        if (s[i - 1 + l] !== s[i - 1 + r]) {
                            ++cnt;
                        }
                    }
                    g[i][j] = Math.min(g[i][j], cnt);
                }
            }
        }
    }
    for (let i = 1; i <= n; ++i) {
        for (let j = 1; j <= k; ++j) {
            for (let h = 0; h < i - 1; ++h) {
                f[i][j] = Math.min(f[i][j], f[h][j - 1] + g[h + 1][i]);
            }
        }
    }
    return f[n][k];
}
```

### **...**

```

```

<!-- tabs:end -->
