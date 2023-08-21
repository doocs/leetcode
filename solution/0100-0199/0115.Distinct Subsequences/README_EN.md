# [115. Distinct Subsequences](https://leetcode.com/problems/distinct-subsequences)

[中文文档](/solution/0100-0199/0115.Distinct%20Subsequences/README.md)

## Description

<p>Given two strings <code>s</code> and <code>t</code>, return <em>the number of distinct</em> <span data-keyword="subsequence-string"><strong><em>subsequences</em></strong></span><em> of </em><code>s</code><em> which equals </em><code>t</code>.</p>

<p>The test cases are generated so that the answer fits on a 32-bit signed integer.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;rabbbit&quot;, t = &quot;rabbit&quot;
<strong>Output:</strong> 3
<strong>Explanation:</strong>
As shown below, there are 3 ways you can generate &quot;rabbit&quot; from s.
<code><strong><u>rabb</u></strong>b<strong><u>it</u></strong></code>
<code><strong><u>ra</u></strong>b<strong><u>bbit</u></strong></code>
<code><strong><u>rab</u></strong>b<strong><u>bit</u></strong></code>
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;babgbag&quot;, t = &quot;bag&quot;
<strong>Output:</strong> 5
<strong>Explanation:</strong>
As shown below, there are 5 ways you can generate &quot;bag&quot; from s.
<code><strong><u>ba</u></strong>b<u><strong>g</strong></u>bag</code>
<code><strong><u>ba</u></strong>bgba<strong><u>g</u></strong></code>
<code><u><strong>b</strong></u>abgb<strong><u>ag</u></strong></code>
<code>ba<u><strong>b</strong></u>gb<u><strong>ag</strong></u></code>
<code>babg<strong><u>bag</u></strong></code></pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length, t.length &lt;= 1000</code></li>
	<li><code>s</code> and <code>t</code> consist of English letters.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def numDistinct(self, s: str, t: str) -> int:
        m, n = len(s), len(t)
        f = [[0] * (n + 1) for _ in range(m + 1)]
        for i in range(m + 1):
            f[i][0] = 1
        for i, a in enumerate(s, 1):
            for j, b in enumerate(t, 1):
                f[i][j] = f[i - 1][j]
                if a == b:
                    f[i][j] += f[i - 1][j - 1]
        return f[m][n]
```

```python
class Solution:
    def numDistinct(self, s: str, t: str) -> int:
        n = len(t)
        f = [1] + [0] * n
        for a in s:
            for j in range(n, 0, -1):
                if a == t[j - 1]:
                    f[j] += f[j - 1]
        return f[n]
```

### **Java**

```java
class Solution {
    public int numDistinct(String s, String t) {
        int m = s.length(), n = t.length();
        int[][] f = new int[m + 1][n + 1];
        for (int i = 0; i < m + 1; ++i) {
            f[i][0] = 1;
        }
        for (int i = 1; i < m + 1; ++i) {
            for (int j = 1; j < n + 1; ++j) {
                f[i][j] = f[i - 1][j];
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    f[i][j] += f[i - 1][j - 1];
                }
            }
        }
        return f[m][n];
    }
}
```

```java
class Solution {
    public int numDistinct(String s, String t) {
        int n = t.length();
        int[] f = new int[n + 1];
        f[0] = 1;
        for (char a : s.toCharArray()) {
            for (int j = n; j > 0; --j) {
                char b = t.charAt(j - 1);
                if (a == b) {
                    f[j] += f[j - 1];
                }
            }
        }
        return f[n];
    }
}
```

### **Go**

```go
func numDistinct(s string, t string) int {
	m, n := len(s), len(t)
	dp := make([][]int, m+1)
	for i := 0; i <= m; i++ {
		dp[i] = make([]int, n+1)
		dp[i][0] = 1
	}
	for i := 1; i <= m; i++ {
		for j := 1; j <= n; j++ {
			dp[i][j] = dp[i-1][j]
			if s[i-1] == t[j-1] {
				dp[i][j] += dp[i-1][j-1]
			}
		}
	}
	return dp[m][n]
}
```

### **C++**

```cpp
class Solution {
public:
    int numDistinct(string s, string t) {
        int m = s.size(), n = t.size();
        unsigned long long f[m + 1][n + 1];
        memset(f, 0, sizeof(f));
        for (int i = 0; i < m + 1; ++i) {
            f[i][0] = 1;
        }
        for (int i = 1; i < m + 1; ++i) {
            for (int j = 1; j < n + 1; ++j) {
                f[i][j] = f[i - 1][j];
                if (s[i - 1] == t[j - 1]) {
                    f[i][j] += f[i - 1][j - 1];
                }
            }
        }
        return f[m][n];
    }
};
```

```cpp
class Solution {
public:
    int numDistinct(string s, string t) {
        int n = t.size();
        unsigned long long f[n + 1];
        memset(f, 0, sizeof(f));
        f[0] = 1;
        for (char& a : s) {
            for (int j = n; j; --j) {
                char b = t[j - 1];
                if (a == b) {
                    f[j] += f[j - 1];
                }
            }
        }
        return f[n];
    }
};
```

### **Go**

```go
func numDistinct(s string, t string) int {
	m, n := len(s), len(t)
	f := make([][]int, m+1)
	for i := range f {
		f[i] = make([]int, n+1)
	}
	for i := 0; i <= m; i++ {
		f[i][0] = 1
	}
	for i := 1; i <= m; i++ {
		for j := 1; j <= n; j++ {
			f[i][j] = f[i-1][j]
			if s[i-1] == t[j-1] {
				f[i][j] += f[i-1][j-1]
			}
		}
	}
	return f[m][n]
}
```

```go
func numDistinct(s string, t string) int {
	n := len(t)
	f := make([]int, n+1)
	f[0] = 1
	for _, a := range s {
		for j := n; j > 0; j-- {
			if b := t[j-1]; byte(a) == b {
				f[j] += f[j-1]
			}
		}
	}
	return f[n]
}
```

### **TypeScript**

```ts
function numDistinct(s: string, t: string): number {
    const m = s.length;
    const n = t.length;
    const f: number[][] = new Array(m + 1)
        .fill(0)
        .map(() => new Array(n + 1).fill(0));
    for (let i = 0; i <= m; ++i) {
        f[i][0] = 1;
    }
    for (let i = 1; i <= m; ++i) {
        for (let j = 1; j <= n; ++j) {
            f[i][j] = f[i - 1][j];
            if (s[i - 1] === t[j - 1]) {
                f[i][j] += f[i - 1][j - 1];
            }
        }
    }
    return f[m][n];
}
```

```ts
function numDistinct(s: string, t: string): number {
    const n = t.length;
    const f: number[] = new Array(n + 1).fill(0);
    f[0] = 1;
    for (const a of s) {
        for (let j = n; j; --j) {
            const b = t[j - 1];
            if (a === b) {
                f[j] += f[j - 1];
            }
        }
    }
    return f[n];
}
```

### **Rust**

```rust
impl Solution {
    #[allow(dead_code)]
    pub fn num_distinct(s: String, t: String) -> i32 {
        let n = s.len();
        let m = t.len();
        let mut dp: Vec<Vec<u64>> = vec![vec![0; m + 1]; n + 1];

        // Initialize the dp vector
        for i in 0..=n {
            dp[i][0] = 1;
        }

        // Begin the actual dp process
        for i in 1..=n {
            for j in 1..=m {
                dp[i][j] = if s.as_bytes()[i - 1] == t.as_bytes()[j - 1] {
                    dp[i - 1][j] + dp[i - 1][j - 1]
                } else {
                    dp[i - 1][j]
                }
            }
        }

        dp[n][m] as i32
    }
}
```

### **...**

```

```

<!-- tabs:end -->
