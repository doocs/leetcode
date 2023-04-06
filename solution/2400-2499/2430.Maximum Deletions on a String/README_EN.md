# [2430. Maximum Deletions on a String](https://leetcode.com/problems/maximum-deletions-on-a-string)

[中文文档](/solution/2400-2499/2430.Maximum%20Deletions%20on%20a%20String/README.md)

## Description

<p>You are given a string <code>s</code> consisting of only lowercase English letters. In one operation, you can:</p>

<ul>
	<li>Delete <strong>the entire string</strong> <code>s</code>, or</li>
	<li>Delete the <strong>first</strong> <code>i</code> letters of <code>s</code> if the first <code>i</code> letters of <code>s</code> are <strong>equal</strong> to the following <code>i</code> letters in <code>s</code>, for any <code>i</code> in the range <code>1 &lt;= i &lt;= s.length / 2</code>.</li>
</ul>

<p>For example, if <code>s = &quot;ababc&quot;</code>, then in one operation, you could delete the first two letters of <code>s</code> to get <code>&quot;abc&quot;</code>, since the first two letters of <code>s</code> and the following two letters of <code>s</code> are both equal to <code>&quot;ab&quot;</code>.</p>

<p>Return <em>the <strong>maximum</strong> number of operations needed to delete all of </em><code>s</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;abcabcdabc&quot;
<strong>Output:</strong> 2
<strong>Explanation:</strong>
- Delete the first 3 letters (&quot;abc&quot;) since the next 3 letters are equal. Now, s = &quot;abcdabc&quot;.
- Delete all the letters.
We used 2 operations so return 2. It can be proven that 2 is the maximum number of operations needed.
Note that in the second operation we cannot delete &quot;abc&quot; again because the next occurrence of &quot;abc&quot; does not happen in the next 3 letters.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;aaabaab&quot;
<strong>Output:</strong> 4
<strong>Explanation:</strong>
- Delete the first letter (&quot;a&quot;) since the next letter is equal. Now, s = &quot;aabaab&quot;.
- Delete the first 3 letters (&quot;aab&quot;) since the next 3 letters are equal. Now, s = &quot;aab&quot;.
- Delete the first letter (&quot;a&quot;) since the next letter is equal. Now, s = &quot;ab&quot;.
- Delete all the letters.
We used 4 operations so return 4. It can be proven that 4 is the maximum number of operations needed.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;aaaaa&quot;
<strong>Output:</strong> 5
<strong>Explanation:</strong> In each operation, we can delete the first letter of s.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 4000</code></li>
	<li><code>s</code> consists only of lowercase English letters.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def deleteString(self, s: str) -> int:
        @cache
        def dfs(i: int) -> int:
            if i == n:
                return 0
            ans = 1
            for j in range(1, (n - i) // 2 + 1):
                if s[i: i + j] == s[i + j: i + j + j]:
                    ans = max(ans, 1 + dfs(i + j))
            return ans

        n = len(s)
        return dfs(0)
```

```python
class Solution:
    def deleteString(self, s: str) -> int:
        n = len(s)
        g = [[0] * (n + 1) for _ in range(n + 1)]
        for i in range(n - 1, -1, -1):
            for j in range(i + 1, n):
                if s[i] == s[j]:
                    g[i][j] = g[i + 1][j + 1] + 1

        f = [1] * n
        for i in range(n - 1, -1, -1):
            for j in range(1, (n - i) // 2 + 1):
                if g[i][i + j] >= j:
                    f[i] = max(f[i], f[i + j] + 1)
        return f[0]
```

### **Java**

```java
class Solution {
    private int n;
    private Integer[] f;
    private int[][] g;

    public int deleteString(String s) {
        n = s.length();
        f = new Integer[n];
        g = new int[n + 1][n + 1];
        for (int i = n - 1; i >= 0; --i) {
            for (int j = i + 1; j < n; ++j) {
                if (s.charAt(i) == s.charAt(j)) {
                    g[i][j] = g[i + 1][j + 1] + 1;
                }
            }
        }
        return dfs(0);
    }

    private int dfs(int i) {
        if (i == n) {
            return 0;
        }
        if (f[i] != null) {
            return f[i];
        }
        f[i] = 1;
        for (int j = 1; j <= (n - i) / 2; ++j) {
            if (g[i][i + j] >= j) {
                f[i] = Math.max(f[i], 1 + dfs(i + j));
            }
        }
        return f[i];
    }
}
```

```java
class Solution {
    public int deleteString(String s) {
        int n = s.length();
        int[][] g = new int[n + 1][n + 1];
        for (int i = n - 1; i >= 0; --i) {
            for (int j = i + 1; j < n; ++j) {
                if (s.charAt(i) == s.charAt(j)) {
                    g[i][j] = g[i + 1][j + 1] + 1;
                }
            }
        }
        int[] f = new int[n];
        for (int i = n - 1; i >= 0; --i) {
            f[i] = 1;
            for (int j = 1; j <= (n - i) / 2; ++j) {
                if (g[i][i + j] >= j) {
                    f[i] = Math.max(f[i], f[i + j] + 1);
                }
            }
        }
        return f[0];
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int deleteString(string s) {
        int n = s.size();
        int g[n + 1][n + 1];
        memset(g, 0, sizeof(g));
        for (int i = n - 1; ~i; --i) {
            for (int j = i + 1; j < n; ++j) {
                if (s[i] == s[j]) {
                    g[i][j] = g[i + 1][j + 1] + 1;
                }
            }
        }
        int f[n];
        memset(f, 0, sizeof(f));
        function<int(int)> dfs = [&](int i) -> int {
            if (i == n) {
                return 0;
            }
            if (f[i]) {
                return f[i];
            }
            f[i] = 1;
            for (int j = 1; j <= (n - i) / 2; ++j) {
                if (g[i][i + j] >= j) {
                    f[i] = max(f[i], 1 + dfs(i + j));
                }
            }
            return f[i];
        };
        return dfs(0);
    }
};
```

```cpp
class Solution {
public:
    int deleteString(string s) {
        int n = s.size();
        int g[n + 1][n + 1];
        memset(g, 0, sizeof(g));
        for (int i = n - 1; ~i; --i) {
            for (int j = i + 1; j < n; ++j) {
                if (s[i] == s[j]) {
                    g[i][j] = g[i + 1][j + 1] + 1;
                }
            }
        }
        int f[n];
        for (int i = n - 1; ~i; --i) {
            f[i] = 1;
            for (int j = 1; j <= (n - i) / 2; ++j) {
                if (g[i][i + j] >= j) {
                    f[i] = max(f[i], f[i + j] + 1);
                }
            }
        }
        return f[0];
    }
};
```

### **Go**

```go
func deleteString(s string) int {
	n := len(s)
	g := make([][]int, n+1)
	for i := range g {
		g[i] = make([]int, n+1)
	}
	for i := n - 1; i >= 0; i-- {
		for j := i + 1; j < n; j++ {
			if s[i] == s[j] {
				g[i][j] = g[i+1][j+1] + 1
			}
		}
	}
	f := make([]int, n)
	var dfs func(int) int
	dfs = func(i int) int {
		if i == n {
			return 0
		}
		if f[i] > 0 {
			return f[i]
		}
		f[i] = 1
		for j := 1; j <= (n-i)/2; j++ {
			if g[i][i+j] >= j {
				f[i] = max(f[i], dfs(i+j)+1)
			}
		}
		return f[i]
	}
	return dfs(0)
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
```

```go
func deleteString(s string) int {
	n := len(s)
	g := make([][]int, n+1)
	for i := range g {
		g[i] = make([]int, n+1)
	}
	for i := n - 1; i >= 0; i-- {
		for j := i + 1; j < n; j++ {
			if s[i] == s[j] {
				g[i][j] = g[i+1][j+1] + 1
			}
		}
	}
	f := make([]int, n)
	for i := n - 1; i >= 0; i-- {
		f[i] = 1
		for j := 1; j <= (n-i)/2; j++ {
			if g[i][i+j] >= j {
				f[i] = max(f[i], f[i+j]+1)
			}
		}
	}
	return f[0]
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
```

### **TypeScript**

```ts
function deleteString(s: string): number {
    const n = s.length;
    const f: number[] = new Array(n).fill(0);
    const dfs = (i: number): number => {
        if (i == n) {
            return 0;
        }
        if (f[i] > 0) {
            return f[i];
        }
        f[i] = 1;
        for (let j = 1; j <= (n - i) >> 1; ++j) {
            if (s.slice(i, i + j) == s.slice(i + j, i + j + j)) {
                f[i] = Math.max(f[i], dfs(i + j) + 1);
            }
        }
        return f[i];
    };
    return dfs(0);
}
```

```ts
function deleteString(s: string): number {
    const n = s.length;
    const f: number[] = new Array(n).fill(1);
    for (let i = n - 1; i >= 0; --i) {
        for (let j = 1; j <= (n - i) >> 1; ++j) {
            if (s.slice(i, i + j) === s.slice(i + j, i + j + j)) {
                f[i] = Math.max(f[i], f[i + j] + 1);
            }
        }
    }
    return f[0];
}
```

### **...**

```

```

<!-- tabs:end -->
