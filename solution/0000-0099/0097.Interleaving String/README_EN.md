---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0000-0099/0097.Interleaving%20String/README_EN.md
tags:
    - String
    - Dynamic Programming
---

<!-- problem:start -->

# [97. Interleaving String](https://leetcode.com/problems/interleaving-string)

[中文文档](/solution/0000-0099/0097.Interleaving%20String/README.md)

## Description

<p>Given strings <code>s1</code>, <code>s2</code>, and <code>s3</code>, find whether <code>s3</code> is formed by an <strong>interleaving</strong> of <code>s1</code> and <code>s2</code>.</p>

<p>An <strong>interleaving</strong> of two strings <code>s</code> and <code>t</code> is a configuration where <code>s</code> and <code>t</code> are divided into <code>n</code> and <code>m</code> <span data-keyword="substring-nonempty">substrings</span> respectively, such that:</p>

<ul>
	<li><code>s = s<sub>1</sub> + s<sub>2</sub> + ... + s<sub>n</sub></code></li>
	<li><code>t = t<sub>1</sub> + t<sub>2</sub> + ... + t<sub>m</sub></code></li>
	<li><code>|n - m| &lt;= 1</code></li>
	<li>The <strong>interleaving</strong> is <code>s<sub>1</sub> + t<sub>1</sub> + s<sub>2</sub> + t<sub>2</sub> + s<sub>3</sub> + t<sub>3</sub> + ...</code> or <code>t<sub>1</sub> + s<sub>1</sub> + t<sub>2</sub> + s<sub>2</sub> + t<sub>3</sub> + s<sub>3</sub> + ...</code></li>
</ul>

<p><strong>Note:</strong> <code>a + b</code> is the concatenation of strings <code>a</code> and <code>b</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0000-0099/0097.Interleaving%20String/images/interleave.jpg" style="width: 561px; height: 203px;" />
<pre>
<strong>Input:</strong> s1 = &quot;aabcc&quot;, s2 = &quot;dbbca&quot;, s3 = &quot;aadbbcbcac&quot;
<strong>Output:</strong> true
<strong>Explanation:</strong> One way to obtain s3 is:
Split s1 into s1 = &quot;aa&quot; + &quot;bc&quot; + &quot;c&quot;, and s2 into s2 = &quot;dbbc&quot; + &quot;a&quot;.
Interleaving the two splits, we get &quot;aa&quot; + &quot;dbbc&quot; + &quot;bc&quot; + &quot;a&quot; + &quot;c&quot; = &quot;aadbbcbcac&quot;.
Since s3 can be obtained by interleaving s1 and s2, we return true.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s1 = &quot;aabcc&quot;, s2 = &quot;dbbca&quot;, s3 = &quot;aadbbbaccc&quot;
<strong>Output:</strong> false
<strong>Explanation:</strong> Notice how it is impossible to interleave s2 with any other string to obtain s3.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> s1 = &quot;&quot;, s2 = &quot;&quot;, s3 = &quot;&quot;
<strong>Output:</strong> true
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>0 &lt;= s1.length, s2.length &lt;= 100</code></li>
	<li><code>0 &lt;= s3.length &lt;= 200</code></li>
	<li><code>s1</code>, <code>s2</code>, and <code>s3</code> consist of lowercase English letters.</li>
</ul>

<p>&nbsp;</p>
<p><strong>Follow up:</strong> Could you solve it using only <code>O(s2.length)</code> additional memory space?</p>

## Solutions

<!-- solution:start -->

### Solution 1: Memoization Search

Let's denote the length of string $s_1$ as $m$ and the length of string $s_2$ as $n$. If $m + n \neq |s_3|$, then $s_3$ is definitely not an interleaving string of $s_1$ and $s_2$, so we return `false`.

Next, we design a function $dfs(i, j)$, which represents whether the remaining part of $s_3$ can be interleaved from the $i$th character of $s_1$ and the $j$th character of $s_2$. The answer is $dfs(0, 0)$.

The calculation process of function $dfs(i, j)$ is as follows:

If $i \geq m$ and $j \geq n$, it means that both $s_1$ and $s_2$ have been traversed, so we return `true`.

If $i < m$ and $s_1[i] = s_3[i + j]$, it means that the character $s_1[i]$ is part of $s_3[i + j]$. Therefore, we recursively call $dfs(i + 1, j)$ to check whether the next character of $s_1$ can match the current character of $s_2$. If it can match, we return `true`.

Similarly, if $j < n$ and $s_2[j] = s_3[i + j]$, it means that the character $s_2[j]$ is part of $s_3[i + j]$. Therefore, we recursively call $dfs(i, j + 1)$ to check whether the next character of $s_2$ can match the current character of $s_1$. If it can match, we return `true`.

Otherwise, we return `false`.

To avoid repeated calculations, we can use memoization search.

The time complexity is $O(m \times n)$, and the space complexity is $O(m \times n)$. Here, $m$ and $n$ are the lengths of strings $s_1$ and $s_2$ respectively.

<!-- tabs:start -->

```python
class Solution:
    def isInterleave(self, s1: str, s2: str, s3: str) -> bool:
        @cache
        def dfs(i: int, j: int) -> bool:
            if i >= m and j >= n:
                return True
            k = i + j
            if i < m and s1[i] == s3[k] and dfs(i + 1, j):
                return True
            if j < n and s2[j] == s3[k] and dfs(i, j + 1):
                return True
            return False

        m, n = len(s1), len(s2)
        if m + n != len(s3):
            return False
        return dfs(0, 0)
```

```java
class Solution {
    private Map<List<Integer>, Boolean> f = new HashMap<>();
    private String s1;
    private String s2;
    private String s3;
    private int m;
    private int n;

    public boolean isInterleave(String s1, String s2, String s3) {
        m = s1.length();
        n = s2.length();
        if (m + n != s3.length()) {
            return false;
        }
        this.s1 = s1;
        this.s2 = s2;
        this.s3 = s3;
        return dfs(0, 0);
    }

    private boolean dfs(int i, int j) {
        if (i >= m && j >= n) {
            return true;
        }
        var key = List.of(i, j);
        if (f.containsKey(key)) {
            return f.get(key);
        }
        int k = i + j;
        boolean ans = false;
        if (i < m && s1.charAt(i) == s3.charAt(k) && dfs(i + 1, j)) {
            ans = true;
        }
        if (!ans && j < n && s2.charAt(j) == s3.charAt(k) && dfs(i, j + 1)) {
            ans = true;
        }
        f.put(key, ans);
        return ans;
    }
}
```

```cpp
class Solution {
public:
    bool isInterleave(string s1, string s2, string s3) {
        int m = s1.size(), n = s2.size();
        if (m + n != s3.size()) {
            return false;
        }
        vector<vector<int>> f(m + 1, vector<int>(n + 1, -1));
        function<bool(int, int)> dfs = [&](int i, int j) {
            if (i >= m && j >= n) {
                return true;
            }
            if (f[i][j] != -1) {
                return f[i][j] == 1;
            }
            f[i][j] = 0;
            int k = i + j;
            if (i < m && s1[i] == s3[k] && dfs(i + 1, j)) {
                f[i][j] = 1;
            }
            if (!f[i][j] && j < n && s2[j] == s3[k] && dfs(i, j + 1)) {
                f[i][j] = 1;
            }
            return f[i][j] == 1;
        };
        return dfs(0, 0);
    }
};
```

```go
func isInterleave(s1 string, s2 string, s3 string) bool {
	m, n := len(s1), len(s2)
	if m+n != len(s3) {
		return false
	}

	f := map[int]bool{}
	var dfs func(int, int) bool
	dfs = func(i, j int) bool {
		if i >= m && j >= n {
			return true
		}
		if v, ok := f[i*200+j]; ok {
			return v
		}
		k := i + j
		f[i*200+j] = (i < m && s1[i] == s3[k] && dfs(i+1, j)) || (j < n && s2[j] == s3[k] && dfs(i, j+1))
		return f[i*200+j]
	}
	return dfs(0, 0)
}
```

```ts
function isInterleave(s1: string, s2: string, s3: string): boolean {
    const m = s1.length;
    const n = s2.length;
    if (m + n !== s3.length) {
        return false;
    }
    const f: number[][] = new Array(m + 1).fill(0).map(() => new Array(n + 1).fill(0));
    const dfs = (i: number, j: number): boolean => {
        if (i >= m && j >= n) {
            return true;
        }
        if (f[i][j]) {
            return f[i][j] === 1;
        }
        f[i][j] = -1;
        if (i < m && s1[i] === s3[i + j] && dfs(i + 1, j)) {
            f[i][j] = 1;
        }
        if (f[i][j] === -1 && j < n && s2[j] === s3[i + j] && dfs(i, j + 1)) {
            f[i][j] = 1;
        }
        return f[i][j] === 1;
    };
    return dfs(0, 0);
}
```

```rust
impl Solution {
    #[allow(dead_code)]
    pub fn is_interleave(s1: String, s2: String, s3: String) -> bool {
        let n = s1.len();
        let m = s2.len();

        if s1.len() + s2.len() != s3.len() {
            return false;
        }

        let mut record = vec![vec![-1; m + 1]; n + 1];

        Self::dfs(
            &mut record,
            n,
            m,
            0,
            0,
            &s1.chars().collect(),
            &s2.chars().collect(),
            &s3.chars().collect()
        )
    }

    #[allow(dead_code)]
    fn dfs(
        record: &mut Vec<Vec<i32>>,
        n: usize,
        m: usize,
        i: usize,
        j: usize,
        s1: &Vec<char>,
        s2: &Vec<char>,
        s3: &Vec<char>
    ) -> bool {
        if i >= n && j >= m {
            return true;
        }

        if record[i][j] != -1 {
            return record[i][j] == 1;
        }

        // Set the initial value
        record[i][j] = 0;
        let k = i + j;

        // Let's try `s1` first
        if i < n && s1[i] == s3[k] && Self::dfs(record, n, m, i + 1, j, s1, s2, s3) {
            record[i][j] = 1;
        }

        // If the first approach does not succeed, let's then try `s2`
        if
            record[i][j] == 0 &&
            j < m &&
            s2[j] == s3[k] &&
            Self::dfs(record, n, m, i, j + 1, s1, s2, s3)
        {
            record[i][j] = 1;
        }

        record[i][j] == 1
    }
}
```

```cs
public class Solution {
    private int m;
    private int n;
    private string s1;
    private string s2;
    private string s3;
    private int[,] f;

    public bool IsInterleave(string s1, string s2, string s3) {
        m = s1.Length;
        n = s2.Length;
        if (m + n != s3.Length) {
            return false;
        }
        this.s1 = s1;
        this.s2 = s2;
        this.s3 = s3;
        f = new int[m + 1, n + 1];
        return dfs(0, 0);
    }

    private bool dfs(int i, int j) {
        if (i >= m && j >= n) {
            return true;
        }
        if (f[i, j] != 0) {
            return f[i, j] == 1;
        }
        f[i, j] = -1;
        if (i < m && s1[i] == s3[i + j] && dfs(i + 1, j)) {
            f[i, j] = 1;
        }
        if (f[i, j] == -1 && j < n && s2[j] == s3[i + j] && dfs(i, j + 1)) {
            f[i, j] = 1;
        }
        return f[i, j] == 1;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Solution 2: Dynamic Programming

We can convert the memoization search in Solution 1 into dynamic programming.

We define $f[i][j]$ to represent whether the first $i$ characters of string $s_1$ and the first $j$ characters of string $s_2$ can interleave to form the first $i + j$ characters of string $s_3$. When transitioning states, we can consider whether the current character is obtained from the last character of $s_1$ or the last character of $s_2$. Therefore, we have the state transition equation:

$$
f[i][j] = \begin{cases}
f[i - 1][j] & \text{if } s_1[i - 1] = s_3[i + j - 1] \\
\text{or } f[i][j - 1] & \text{if } s_2[j - 1] = s_3[i + j - 1] \\
\text{false} & \text{otherwise}
\end{cases}
$$

where $f[0][0] = \text{true}$ indicates that an empty string is an interleaving string of two empty strings.

The answer is $f[m][n]$.

The time complexity is $O(m \times n)$, and the space complexity is $O(m \times n)$. Here, $m$ and $n$ are the lengths of strings $s_1$ and $s_2$ respectively.

<!-- tabs:start -->

```python
class Solution:
    def isInterleave(self, s1: str, s2: str, s3: str) -> bool:
        m, n = len(s1), len(s2)
        if m + n != len(s3):
            return False
        f = [[False] * (n + 1) for _ in range(m + 1)]
        f[0][0] = True
        for i in range(m + 1):
            for j in range(n + 1):
                k = i + j - 1
                if i and s1[i - 1] == s3[k]:
                    f[i][j] = f[i - 1][j]
                if j and s2[j - 1] == s3[k]:
                    f[i][j] |= f[i][j - 1]
        return f[m][n]
```

```java
class Solution {
    public boolean isInterleave(String s1, String s2, String s3) {
        int m = s1.length(), n = s2.length();
        if (m + n != s3.length()) {
            return false;
        }
        boolean[][] f = new boolean[m + 1][n + 1];
        f[0][0] = true;
        for (int i = 0; i <= m; ++i) {
            for (int j = 0; j <= n; ++j) {
                int k = i + j - 1;
                if (i > 0 && s1.charAt(i - 1) == s3.charAt(k)) {
                    f[i][j] = f[i - 1][j];
                }
                if (j > 0 && s2.charAt(j - 1) == s3.charAt(k)) {
                    f[i][j] |= f[i][j - 1];
                }
            }
        }
        return f[m][n];
    }
}
```

```cpp
class Solution {
public:
    bool isInterleave(string s1, string s2, string s3) {
        int m = s1.size(), n = s2.size();
        if (m + n != s3.size()) {
            return false;
        }
        bool f[m + 1][n + 1];
        memset(f, false, sizeof(f));
        f[0][0] = true;
        for (int i = 0; i <= m; ++i) {
            for (int j = 0; j <= n; ++j) {
                int k = i + j - 1;
                if (i > 0 && s1[i - 1] == s3[k]) {
                    f[i][j] = f[i - 1][j];
                }
                if (j > 0 && s2[j - 1] == s3[k]) {
                    f[i][j] |= f[i][j - 1];
                }
            }
        }
        return f[m][n];
    }
};
```

```go
func isInterleave(s1 string, s2 string, s3 string) bool {
	m, n := len(s1), len(s2)
	if m+n != len(s3) {
		return false
	}
	f := make([][]bool, m+1)
	for i := range f {
		f[i] = make([]bool, n+1)
	}
	f[0][0] = true
	for i := 0; i <= m; i++ {
		for j := 0; j <= n; j++ {
			k := i + j - 1
			if i > 0 && s1[i-1] == s3[k] {
				f[i][j] = f[i-1][j]
			}
			if j > 0 && s2[j-1] == s3[k] {
				f[i][j] = (f[i][j] || f[i][j-1])
			}
		}
	}
	return f[m][n]
}
```

```ts
function isInterleave(s1: string, s2: string, s3: string): boolean {
    const m = s1.length;
    const n = s2.length;
    if (m + n !== s3.length) {
        return false;
    }
    const f: boolean[][] = new Array(m + 1).fill(0).map(() => new Array(n + 1).fill(false));
    f[0][0] = true;
    for (let i = 0; i <= m; ++i) {
        for (let j = 0; j <= n; ++j) {
            const k = i + j - 1;
            if (i > 0 && s1[i - 1] === s3[k]) {
                f[i][j] = f[i - 1][j];
            }
            if (j > 0 && s2[j - 1] === s3[k]) {
                f[i][j] = f[i][j] || f[i][j - 1];
            }
        }
    }
    return f[m][n];
}
```

```cs
public class Solution {
    public bool IsInterleave(string s1, string s2, string s3) {
        int m = s1.Length, n = s2.Length;
        if (m + n != s3.Length) {
            return false;
        }
        bool[,] f = new bool[m + 1, n + 1];
        f[0, 0] = true;
        for (int i = 0; i <= m; ++i) {
            for (int j = 0; j <= n; ++j) {
                int k = i + j - 1;
                if (i > 0 && s1[i - 1] == s3[k]) {
                    f[i, j] = f[i - 1, j];
                }
                if (j > 0 && s2[j - 1] == s3[k]) {
                    f[i, j] |= f[i, j - 1];
                }
            }
        }
        return f[m, n];
    }
}
```

<!-- tabs:end -->

We notice that the state $f[i][j]$ is only related to the states $f[i - 1][j]$, $f[i][j - 1]$, and $f[i - 1][j - 1]$. Therefore, we can use a rolling array to optimize the space complexity, reducing the original space complexity from $O(m \times n)$ to $O(n)$.

<!-- tabs:start -->

```python
class Solution:
    def isInterleave(self, s1: str, s2: str, s3: str) -> bool:
        m, n = len(s1), len(s2)
        if m + n != len(s3):
            return False
        f = [True] + [False] * n
        for i in range(m + 1):
            for j in range(n + 1):
                k = i + j - 1
                if i:
                    f[j] &= s1[i - 1] == s3[k]
                if j:
                    f[j] |= f[j - 1] and s2[j - 1] == s3[k]
        return f[n]
```

```java
class Solution {
    public boolean isInterleave(String s1, String s2, String s3) {
        int m = s1.length(), n = s2.length();
        if (m + n != s3.length()) {
            return false;
        }
        boolean[] f = new boolean[n + 1];
        f[0] = true;
        for (int i = 0; i <= m; ++i) {
            for (int j = 0; j <= n; ++j) {
                int k = i + j - 1;
                if (i > 0) {
                    f[j] &= s1.charAt(i - 1) == s3.charAt(k);
                }
                if (j > 0) {
                    f[j] |= (f[j - 1] & s2.charAt(j - 1) == s3.charAt(k));
                }
            }
        }
        return f[n];
    }
}
```

```cpp
class Solution {
public:
    bool isInterleave(string s1, string s2, string s3) {
        int m = s1.size(), n = s2.size();
        if (m + n != s3.size()) {
            return false;
        }
        bool f[n + 1];
        memset(f, false, sizeof(f));
        f[0] = true;
        for (int i = 0; i <= m; ++i) {
            for (int j = 0; j <= n; ++j) {
                int k = i + j - 1;
                if (i) {
                    f[j] &= s1[i - 1] == s3[k];
                }
                if (j) {
                    f[j] |= (s2[j - 1] == s3[k] && f[j - 1]);
                }
            }
        }
        return f[n];
    }
};
```

```go
func isInterleave(s1 string, s2 string, s3 string) bool {
	m, n := len(s1), len(s2)
	if m+n != len(s3) {
		return false
	}
	f := make([]bool, n+1)
	f[0] = true
	for i := 0; i <= m; i++ {
		for j := 0; j <= n; j++ {
			k := i + j - 1
			if i > 0 {
				f[j] = (f[j] && s1[i-1] == s3[k])
			}
			if j > 0 {
				f[j] = (f[j] || (s2[j-1] == s3[k] && f[j-1]))
			}
		}
	}
	return f[n]
}
```

```ts
function isInterleave(s1: string, s2: string, s3: string): boolean {
    const m = s1.length;
    const n = s2.length;
    if (m + n !== s3.length) {
        return false;
    }
    const f: boolean[] = new Array(n + 1).fill(false);
    f[0] = true;
    for (let i = 0; i <= m; ++i) {
        for (let j = 0; j <= n; ++j) {
            const k = i + j - 1;
            if (i) {
                f[j] = f[j] && s1[i - 1] === s3[k];
            }
            if (j) {
                f[j] = f[j] || (f[j - 1] && s2[j - 1] === s3[k]);
            }
        }
    }
    return f[n];
}
```

```cs
public class Solution {
    public bool IsInterleave(string s1, string s2, string s3) {
        int m = s1.Length, n = s2.Length;
        if (m + n != s3.Length) {
            return false;
        }
        bool[] f = new bool[n + 1];
        f[0] = true;
        for (int i = 0; i <= m; ++i) {
            for (int j = 0; j <= n; ++j) {
                int k = i + j - 1;
                if (i > 0) {
                    f[j] &= s1[i - 1] == s3[k];
                }
                if (j > 0) {
                    f[j] |= (f[j - 1] & s2[j - 1] == s3[k]);
                }
            }
        }
        return f[n];
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
