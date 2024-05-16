---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2900-2999/2911.Minimum%20Changes%20to%20Make%20K%20Semi-palindromes/README_EN.md
rating: 2607
source: Weekly Contest 368 Q4
tags:
    - Two Pointers
    - String
    - Dynamic Programming
---

# [2911. Minimum Changes to Make K Semi-palindromes](https://leetcode.com/problems/minimum-changes-to-make-k-semi-palindromes)

[中文文档](/solution/2900-2999/2911.Minimum%20Changes%20to%20Make%20K%20Semi-palindromes/README.md)

## Description

<p>Given a string <code>s</code> and an integer <code>k</code>, partition <code>s</code> into <code>k</code> <strong><span data-keyword="substring-nonempty">substrings</span></strong> such that the letter changes needed to make each substring a <strong>semi-palindrome</strong>&nbsp;are minimized.</p>

<p>Return the <em><strong>minimum</strong> number of letter changes</em> required<em>.</em></p>

<p>A <strong>semi-palindrome</strong> is a special type of string that can be divided into <strong><span data-keyword="palindrome">palindromes</span></strong> based on a repeating pattern. To check if a string is a semi-palindrome:​</p>

<ol>
	<li>Choose a positive divisor <code>d</code> of the string&#39;s length. <code>d</code> can range from <code>1</code> up to, but not including, the string&#39;s length. For a string of length <code>1</code>, it does not have a valid divisor as per this definition, since the only divisor is its length, which is not allowed.</li>
	<li>For a given divisor <code>d</code>, divide the string into groups where each group contains characters from the string that follow a repeating pattern of length <code>d</code>. Specifically, the first group consists of characters at positions <code>1</code>, <code>1 + d</code>, <code>1 + 2d</code>, and so on; the second group includes characters at positions <code>2</code>, <code>2 + d</code>, <code>2 + 2d</code>, etc.</li>
	<li>The string is considered a semi-palindrome if each of these groups forms a palindrome.</li>
</ol>

<p>Consider the string <code>&quot;abcabc&quot;</code>:</p>

<ul>
	<li>The length of <code>&quot;abcabc&quot;</code> is <code>6</code>. Valid divisors are <code>1</code>, <code>2</code>, and <code>3</code>.</li>
	<li>For <code>d = 1</code>: The entire string <code>&quot;abcabc&quot;</code> forms one group. Not a palindrome.</li>
	<li>For <code>d = 2</code>:
	<ul>
		<li>Group 1 (positions <code>1, 3, 5</code>): <code>&quot;acb&quot;</code></li>
		<li>Group 2 (positions <code>2, 4, 6</code>): <code>&quot;bac&quot;</code></li>
		<li>Neither group forms a palindrome.</li>
	</ul>
	</li>
	<li>For <code>d = 3</code>:
	<ul>
		<li>Group 1 (positions <code>1, 4</code>): <code>&quot;aa&quot;</code></li>
		<li>Group 2 (positions <code>2, 5</code>): <code>&quot;bb&quot;</code></li>
		<li>Group 3 (positions <code>3, 6</code>): <code>&quot;cc&quot;</code></li>
		<li>All groups form palindromes. Therefore, <code>&quot;abcabc&quot;</code> is a semi-palindrome.</li>
	</ul>
	</li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Example 1: </strong></p>

<div class="example-block" style="border-color: var(--border-tertiary); border-left-width: 2px; color: var(--text-secondary); font-size: .875rem; margin-bottom: 1rem; margin-top: 1rem; overflow: visible; padding-left: 1rem;">
<p><strong>Input: </strong> <span class="example-io" style="font-family: Menlo,sans-serif; font-size: 0.85rem;"> s = &quot;abcac&quot;, k = 2 </span></p>

<p><strong>Output: </strong> <span class="example-io" style="font-family: Menlo,sans-serif; font-size: 0.85rem;"> 1 </span></p>

<p><strong>Explanation: </strong> Divide <code>s</code> into <code>&quot;ab&quot;</code> and <code>&quot;cac&quot;</code>. <code>&quot;cac&quot;</code> is already semi-palindrome. Change <code>&quot;ab&quot;</code> to <code>&quot;aa&quot;</code>, it becomes semi-palindrome with <code>d = 1</code>.</p>
</div>

<p><strong class="example">Example 2: </strong></p>

<div class="example-block" style="border-color: var(--border-tertiary); border-left-width: 2px; color: var(--text-secondary); font-size: .875rem; margin-bottom: 1rem; margin-top: 1rem; overflow: visible; padding-left: 1rem;">
<p><strong>Input: </strong> <span class="example-io" style="font-family: Menlo,sans-serif; font-size: 0.85rem;"> s = &quot;abcdef&quot;, k = 2 </span></p>

<p><strong>Output: </strong> <span class="example-io" style="font-family: Menlo,sans-serif; font-size: 0.85rem;"> 2 </span></p>

<p><strong>Explanation: </strong> Divide <code>s</code> into substrings <code>&quot;abc&quot;</code> and <code>&quot;def&quot;</code>. Each&nbsp;needs one change to become semi-palindrome.</p>
</div>

<p><strong class="example">Example 3: </strong></p>

<div class="example-block" style="border-color: var(--border-tertiary); border-left-width: 2px; color: var(--text-secondary); font-size: .875rem; margin-bottom: 1rem; margin-top: 1rem; overflow: visible; padding-left: 1rem;">
<p><strong>Input: </strong> <span class="example-io" style="font-family: Menlo,sans-serif; font-size: 0.85rem;"> s = &quot;aabbaa&quot;, k = 3 </span></p>

<p><strong>Output: </strong> <span class="example-io" style="font-family: Menlo,sans-serif; font-size: 0.85rem;"> 0 </span></p>

<p><strong>Explanation: </strong> Divide <code>s</code> into substrings <code>&quot;aa&quot;</code>, <code>&quot;bb&quot;</code> and <code>&quot;aa&quot;</code>.&nbsp;All are already semi-palindromes.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= s.length &lt;= 200</code></li>
	<li><code>1 &lt;= k &lt;= s.length / 2</code></li>
	<li><code>s</code> contains only lowercase English letters.</li>
</ul>

## Solutions

### Solution 1

<!-- tabs:start -->

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
```

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

<!-- tabs:end -->

### Solution 2

<!-- tabs:start -->

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

<!-- tabs:end -->

<!-- end -->
