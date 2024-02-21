# [2911. 得到 K 个半回文串的最少修改次数](https://leetcode.cn/problems/minimum-changes-to-make-k-semi-palindromes)

[English Version](/solution/2900-2999/2911.Minimum%20Changes%20to%20Make%20K%20Semi-palindromes/README_EN.md)

<!-- tags:双指针,字符串,动态规划 -->

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个字符串&nbsp;<code>s</code>&nbsp;和一个整数&nbsp;<code>k</code>&nbsp;，请你将&nbsp;<code>s</code> 分成&nbsp;<code>k</code>&nbsp;个<strong>&nbsp;子字符串</strong>&nbsp;，使得每个 <strong>子字符串</strong>&nbsp;变成&nbsp;<strong>半回文串</strong>&nbsp;需要修改的字符数目最少。</p>

<p>请你返回一个整数，表示需要修改的 <strong>最少</strong>&nbsp;字符数目。</p>

<p><strong>注意：</strong></p>

<ul>
	<li>如果一个字符串从左往右和从右往左读是一样的，那么它是一个 <strong>回文串</strong>&nbsp;。</li>
	<li>如果长度为 <code>len</code>&nbsp;的字符串存在一个满足&nbsp;<code>1 &lt;= d &lt; len</code>&nbsp;的正整数&nbsp;<code>d</code>&nbsp;，<code>len % d == 0</code>&nbsp;成立且所有对 <code>d</code>&nbsp;做除法余数相同的下标对应的字符连起来得到的字符串都是 <strong>回文串</strong>&nbsp;，那么我们说这个字符串是 <strong>半回文串</strong>&nbsp;。比方说&nbsp;<code>"aa"</code>&nbsp;，<code>"aba"</code> ，<code>"adbgad"</code>&nbsp;和&nbsp;<code>"abab"</code>&nbsp;都是 <strong>半回文串</strong>&nbsp;，而&nbsp;<code>"a"</code>&nbsp;，<code>"ab"</code>&nbsp;和&nbsp;<code>"abca"</code>&nbsp;不是。</li>
	<li><strong>子字符串</strong>&nbsp;指的是一个字符串中一段连续的字符序列。</li>
</ul>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<b>输入：</b>s = "abcac", k = 2
<b>输出：</b>1
<b>解释：</b>我们可以将 s 分成子字符串 "ab" 和 "cac" 。子字符串 "cac" 已经是半回文串。如果我们将 "ab" 变成 "aa" ，它也会变成一个 d = 1 的半回文串。
该方案是将 s 分成 2 个子字符串的前提下，得到 2 个半回文子字符串需要的最少修改次数。所以答案为 1 。</pre>

<p><strong class="example">示例 2:</strong></p>

<pre>
<b>输入：</b>s = "abcdef", k = 2
<b>输出：</b>2
<b>解释：</b>我们可以将 s 分成子字符串 "abc" 和 "def" 。子字符串 "abc" 和 "def" 都需要修改一个字符得到半回文串，所以我们总共需要 2 次字符修改使所有子字符串变成半回文串。
该方案是将 s 分成 2 个子字符串的前提下，得到 2 个半回文子字符串需要的最少修改次数。所以答案为 2 。</pre>

<p><strong class="example">示例 3：</strong></p>

<pre>
<b>输入：</b>s = "aabbaa", k = 3
<b>输出：</b>0
<b>解释：</b>我们可以将 s 分成子字符串 "aa" ，"bb" 和 "aa" 。
字符串 "aa" 和 "bb" 都已经是半回文串了。所以答案为 0 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= s.length &lt;= 200</code></li>
	<li><code>1 &lt;= k &lt;= s.length / 2</code></li>
	<li><code>s</code>&nbsp;只包含小写英文字母。</li>
</ul>

## 解法

### 方法一

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

### 方法二

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
