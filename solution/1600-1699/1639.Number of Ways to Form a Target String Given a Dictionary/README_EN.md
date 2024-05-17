---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1600-1699/1639.Number%20of%20Ways%20to%20Form%20a%20Target%20String%20Given%20a%20Dictionary/README_EN.md
rating: 2081
source: Biweekly Contest 38 Q4
tags:
    - Array
    - String
    - Dynamic Programming
---

<!-- problem:start -->

# [1639. Number of Ways to Form a Target String Given a Dictionary](https://leetcode.com/problems/number-of-ways-to-form-a-target-string-given-a-dictionary)

[中文文档](/solution/1600-1699/1639.Number%20of%20Ways%20to%20Form%20a%20Target%20String%20Given%20a%20Dictionary/README.md)

## Description

<!-- description:start -->

<p>You are given a list of strings of the <strong>same length</strong> <code>words</code> and a string <code>target</code>.</p>

<p>Your task is to form <code>target</code> using the given <code>words</code> under the following rules:</p>

<ul>
	<li><code>target</code> should be formed from left to right.</li>
	<li>To form the <code>i<sup>th</sup></code> character (<strong>0-indexed</strong>) of <code>target</code>, you can choose the <code>k<sup>th</sup></code> character of the <code>j<sup>th</sup></code> string in <code>words</code> if <code>target[i] = words[j][k]</code>.</li>
	<li>Once you use the <code>k<sup>th</sup></code> character of the <code>j<sup>th</sup></code> string of <code>words</code>, you <strong>can no longer</strong> use the <code>x<sup>th</sup></code> character of any string in <code>words</code> where <code>x &lt;= k</code>. In other words, all characters to the left of or at index <code>k</code> become unusuable for every string.</li>
	<li>Repeat the process until you form the string <code>target</code>.</li>
</ul>

<p><strong>Notice</strong> that you can use <strong>multiple characters</strong> from the <strong>same string</strong> in <code>words</code> provided the conditions above are met.</p>

<p>Return <em>the number of ways to form <code>target</code> from <code>words</code></em>. Since the answer may be too large, return it <strong>modulo</strong> <code>10<sup>9</sup> + 7</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> words = [&quot;acca&quot;,&quot;bbbb&quot;,&quot;caca&quot;], target = &quot;aba&quot;
<strong>Output:</strong> 6
<strong>Explanation:</strong> There are 6 ways to form target.
&quot;aba&quot; -&gt; index 0 (&quot;<u>a</u>cca&quot;), index 1 (&quot;b<u>b</u>bb&quot;), index 3 (&quot;cac<u>a</u>&quot;)
&quot;aba&quot; -&gt; index 0 (&quot;<u>a</u>cca&quot;), index 2 (&quot;bb<u>b</u>b&quot;), index 3 (&quot;cac<u>a</u>&quot;)
&quot;aba&quot; -&gt; index 0 (&quot;<u>a</u>cca&quot;), index 1 (&quot;b<u>b</u>bb&quot;), index 3 (&quot;acc<u>a</u>&quot;)
&quot;aba&quot; -&gt; index 0 (&quot;<u>a</u>cca&quot;), index 2 (&quot;bb<u>b</u>b&quot;), index 3 (&quot;acc<u>a</u>&quot;)
&quot;aba&quot; -&gt; index 1 (&quot;c<u>a</u>ca&quot;), index 2 (&quot;bb<u>b</u>b&quot;), index 3 (&quot;acc<u>a</u>&quot;)
&quot;aba&quot; -&gt; index 1 (&quot;c<u>a</u>ca&quot;), index 2 (&quot;bb<u>b</u>b&quot;), index 3 (&quot;cac<u>a</u>&quot;)
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> words = [&quot;abba&quot;,&quot;baab&quot;], target = &quot;bab&quot;
<strong>Output:</strong> 4
<strong>Explanation:</strong> There are 4 ways to form target.
&quot;bab&quot; -&gt; index 0 (&quot;<u>b</u>aab&quot;), index 1 (&quot;b<u>a</u>ab&quot;), index 2 (&quot;ab<u>b</u>a&quot;)
&quot;bab&quot; -&gt; index 0 (&quot;<u>b</u>aab&quot;), index 1 (&quot;b<u>a</u>ab&quot;), index 3 (&quot;baa<u>b</u>&quot;)
&quot;bab&quot; -&gt; index 0 (&quot;<u>b</u>aab&quot;), index 2 (&quot;ba<u>a</u>b&quot;), index 3 (&quot;baa<u>b</u>&quot;)
&quot;bab&quot; -&gt; index 1 (&quot;a<u>b</u>ba&quot;), index 2 (&quot;ba<u>a</u>b&quot;), index 3 (&quot;baa<u>b</u>&quot;)
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= words.length &lt;= 1000</code></li>
	<li><code>1 &lt;= words[i].length &lt;= 1000</code></li>
	<li>All strings in <code>words</code> have the same length.</li>
	<li><code>1 &lt;= target.length &lt;= 1000</code></li>
	<li><code>words[i]</code> and <code>target</code> contain only lowercase English letters.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Preprocessing + Memory Search

We noticed that the length of each string in the string array $words$ is the same, so let's remember $n$, then we can preprocess a two-dimensional array $cnt$, where $cnt[j][c]$ represents the string array $words$ The number of characters $c$ in the $j$-th position of.

Next, we design a function $dfs(i, j)$, which represents the number of schemes that construct $target[i,..]$ and the currently selected character position from $words$ is $j$. Then the answer is $dfs(0, 0)$.

The calculation logic of function $dfs(i, j)$ is as follows:

-   If $i \geq m$, it means that all characters in $target$ have been selected, then the number of schemes is $1$.
-   If $j \geq n$, it means that all characters in $words$ have been selected, then the number of schemes is $0$.
-   Otherwise, we can choose not to select the character in the $j$-th position of $words$, then the number of schemes is $dfs(i, j + 1)$; or we choose the character in the $j$-th position of $words$, then the number of schemes is $dfs(i + 1, j + 1) \times cnt[j][target[i] - 'a']$.

Finally, we return $dfs(0, 0)$. Note that the answer is taken in modulo operation.

The time complexity is $O(m \times n)$, and the space complexity is $O(m \times n)$. Where $m$ is the length of the string $target$, and $n$ is the length of each string in the string array $words$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def numWays(self, words: List[str], target: str) -> int:
        @cache
        def dfs(i: int, j: int) -> int:
            if i >= m:
                return 1
            if j >= n:
                return 0
            ans = dfs(i + 1, j + 1) * cnt[j][ord(target[i]) - ord('a')]
            ans = (ans + dfs(i, j + 1)) % mod
            return ans

        m, n = len(target), len(words[0])
        cnt = [[0] * 26 for _ in range(n)]
        for w in words:
            for j, c in enumerate(w):
                cnt[j][ord(c) - ord('a')] += 1
        mod = 10**9 + 7
        return dfs(0, 0)
```

#### Java

```java
class Solution {
    private int m;
    private int n;
    private String target;
    private Integer[][] f;
    private int[][] cnt;
    private final int mod = (int) 1e9 + 7;

    public int numWays(String[] words, String target) {
        m = target.length();
        n = words[0].length();
        f = new Integer[m][n];
        this.target = target;
        cnt = new int[n][26];
        for (var w : words) {
            for (int j = 0; j < n; ++j) {
                cnt[j][w.charAt(j) - 'a']++;
            }
        }
        return dfs(0, 0);
    }

    private int dfs(int i, int j) {
        if (i >= m) {
            return 1;
        }
        if (j >= n) {
            return 0;
        }
        if (f[i][j] != null) {
            return f[i][j];
        }
        long ans = dfs(i, j + 1);
        ans += 1L * dfs(i + 1, j + 1) * cnt[j][target.charAt(i) - 'a'];
        ans %= mod;
        return f[i][j] = (int) ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int numWays(vector<string>& words, string target) {
        const int mod = 1e9 + 7;
        int m = target.size(), n = words[0].size();
        vector<vector<int>> cnt(n, vector<int>(26));
        for (auto& w : words) {
            for (int j = 0; j < n; ++j) {
                ++cnt[j][w[j] - 'a'];
            }
        }
        int f[m][n];
        memset(f, -1, sizeof(f));
        function<int(int, int)> dfs = [&](int i, int j) -> int {
            if (i >= m) {
                return 1;
            }
            if (j >= n) {
                return 0;
            }
            if (f[i][j] != -1) {
                return f[i][j];
            }
            int ans = dfs(i, j + 1);
            ans = (ans + 1LL * dfs(i + 1, j + 1) * cnt[j][target[i] - 'a']) % mod;
            return f[i][j] = ans;
        };
        return dfs(0, 0);
    }
};
```

#### Go

```go
func numWays(words []string, target string) int {
	m, n := len(target), len(words[0])
	f := make([][]int, m)
	cnt := make([][26]int, n)
	for _, w := range words {
		for j, c := range w {
			cnt[j][c-'a']++
		}
	}
	for i := range f {
		f[i] = make([]int, n)
		for j := range f[i] {
			f[i][j] = -1
		}
	}
	const mod = 1e9 + 7
	var dfs func(i, j int) int
	dfs = func(i, j int) int {
		if i >= m {
			return 1
		}
		if j >= n {
			return 0
		}
		if f[i][j] != -1 {
			return f[i][j]
		}
		ans := dfs(i, j+1)
		ans = (ans + dfs(i+1, j+1)*cnt[j][target[i]-'a']) % mod
		f[i][j] = ans
		return ans
	}
	return dfs(0, 0)
}
```

#### TypeScript

```ts
function numWays(words: string[], target: string): number {
    const m = target.length;
    const n = words[0].length;
    const f = new Array(m + 1).fill(0).map(() => new Array(n + 1).fill(0));
    const mod = 1e9 + 7;
    for (let j = 0; j <= n; ++j) {
        f[0][j] = 1;
    }
    const cnt = new Array(n).fill(0).map(() => new Array(26).fill(0));
    for (const w of words) {
        for (let j = 0; j < n; ++j) {
            ++cnt[j][w.charCodeAt(j) - 97];
        }
    }
    for (let i = 1; i <= m; ++i) {
        for (let j = 1; j <= n; ++j) {
            f[i][j] = f[i][j - 1] + f[i - 1][j - 1] * cnt[j - 1][target.charCodeAt(i - 1) - 97];
            f[i][j] %= mod;
        }
    }
    return f[m][n];
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Solution 2: Preprocessing + Dynamic Programming

Similar to Solution 1, we can first preprocess a two-dimensional array $cnt$, where $cnt[j][c]$ represents the number of characters $c$ in the $j$-th position of the string array $words$.

Next, we define $f[i][j]$ which represents the number of ways to construct the first $i$ characters of $target$, and currently select characters from the first $j$ characters of each word in $words$. Then the answer is $f[m][n]$. Initially $f[0][j] = 1$, where $0 \leq j \leq n$.

Consider $f[i][j]$, where $i \gt 0$, $j \gt 0$. We can choose not to select the character in the $j$-th position of $words$, in which case the number of ways is $f[i][j - 1]$; or we choose the character in the $j$-th position of $words$, in which case the number of ways is $f[i - 1][j - 1] \times cnt[j - 1][target[i - 1] - 'a']$. Finally, we add the number of ways in these two cases, which is the value of $f[i][j]$.

Finally, we return $f[m][n]$. Note the mod operation of the answer.

The time complexity is $O(m \times n)$, and the space complexity is $O(m \times n)$. Where $m$ is the length of the string $target$, and $n$ is the length of each string in the string array $words$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def numWays(self, words: List[str], target: str) -> int:
        m, n = len(target), len(words[0])
        cnt = [[0] * 26 for _ in range(n)]
        for w in words:
            for j, c in enumerate(w):
                cnt[j][ord(c) - ord('a')] += 1
        mod = 10**9 + 7
        f = [[0] * (n + 1) for _ in range(m + 1)]
        f[0] = [1] * (n + 1)
        for i in range(1, m + 1):
            for j in range(1, n + 1):
                f[i][j] = (
                    f[i][j - 1]
                    + f[i - 1][j - 1] * cnt[j - 1][ord(target[i - 1]) - ord('a')]
                )
                f[i][j] %= mod
        return f[m][n]
```

#### Java

```java
class Solution {
    public int numWays(String[] words, String target) {
        int m = target.length();
        int n = words[0].length();
        final int mod = (int) 1e9 + 7;
        long[][] f = new long[m + 1][n + 1];
        Arrays.fill(f[0], 1);
        int[][] cnt = new int[n][26];
        for (var w : words) {
            for (int j = 0; j < n; ++j) {
                cnt[j][w.charAt(j) - 'a']++;
            }
        }
        for (int i = 1; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                f[i][j] = f[i][j - 1] + f[i - 1][j - 1] * cnt[j - 1][target.charAt(i - 1) - 'a'];
                f[i][j] %= mod;
            }
        }
        return (int) f[m][n];
    }
}
```

#### C++

```cpp
class Solution {
public:
    int numWays(vector<string>& words, string target) {
        int m = target.size(), n = words[0].size();
        const int mod = 1e9 + 7;
        long long f[m + 1][n + 1];
        memset(f, 0, sizeof(f));
        fill(f[0], f[0] + n + 1, 1);
        vector<vector<int>> cnt(n, vector<int>(26));
        for (auto& w : words) {
            for (int j = 0; j < n; ++j) {
                ++cnt[j][w[j] - 'a'];
            }
        }
        for (int i = 1; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                f[i][j] = f[i][j - 1] + f[i - 1][j - 1] * cnt[j - 1][target[i - 1] - 'a'];
                f[i][j] %= mod;
            }
        }
        return f[m][n];
    }
};
```

#### Go

```go
func numWays(words []string, target string) int {
	const mod = 1e9 + 7
	m, n := len(target), len(words[0])
	f := make([][]int, m+1)
	for i := range f {
		f[i] = make([]int, n+1)
	}
	for j := range f[0] {
		f[0][j] = 1
	}
	cnt := make([][26]int, n)
	for _, w := range words {
		for j, c := range w {
			cnt[j][c-'a']++
		}
	}
	for i := 1; i <= m; i++ {
		for j := 1; j <= n; j++ {
			f[i][j] = f[i][j-1] + f[i-1][j-1]*cnt[j-1][target[i-1]-'a']
			f[i][j] %= mod
		}
	}
	return f[m][n]
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
