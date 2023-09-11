# [87. Scramble String](https://leetcode.com/problems/scramble-string)

[中文文档](/solution/0000-0099/0087.Scramble%20String/README.md)

## Description

<p>We can scramble a string s to get a string t using the following algorithm:</p>

<ol>
	<li>If the length of the string is 1, stop.</li>
	<li>If the length of the string is &gt; 1, do the following:
	<ul>
		<li>Split the string into two non-empty substrings at a random index, i.e., if the string is <code>s</code>, divide it to <code>x</code> and <code>y</code> where <code>s = x + y</code>.</li>
		<li><strong>Randomly</strong>&nbsp;decide to swap the two substrings or to keep them in the same order. i.e., after this step, <code>s</code> may become <code>s = x + y</code> or <code>s = y + x</code>.</li>
		<li>Apply step 1 recursively on each of the two substrings <code>x</code> and <code>y</code>.</li>
	</ul>
	</li>
</ol>

<p>Given two strings <code>s1</code> and <code>s2</code> of <strong>the same length</strong>, return <code>true</code> if <code>s2</code> is a scrambled string of <code>s1</code>, otherwise, return <code>false</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s1 = &quot;great&quot;, s2 = &quot;rgeat&quot;
<strong>Output:</strong> true
<strong>Explanation:</strong> One possible scenario applied on s1 is:
&quot;great&quot; --&gt; &quot;gr/eat&quot; // divide at random index.
&quot;gr/eat&quot; --&gt; &quot;gr/eat&quot; // random decision is not to swap the two substrings and keep them in order.
&quot;gr/eat&quot; --&gt; &quot;g/r / e/at&quot; // apply the same algorithm recursively on both substrings. divide at random index each of them.
&quot;g/r / e/at&quot; --&gt; &quot;r/g / e/at&quot; // random decision was to swap the first substring and to keep the second substring in the same order.
&quot;r/g / e/at&quot; --&gt; &quot;r/g / e/ a/t&quot; // again apply the algorithm recursively, divide &quot;at&quot; to &quot;a/t&quot;.
&quot;r/g / e/ a/t&quot; --&gt; &quot;r/g / e/ a/t&quot; // random decision is to keep both substrings in the same order.
The algorithm stops now, and the result string is &quot;rgeat&quot; which is s2.
As one possible scenario led s1 to be scrambled to s2, we return true.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s1 = &quot;abcde&quot;, s2 = &quot;caebd&quot;
<strong>Output:</strong> false
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> s1 = &quot;a&quot;, s2 = &quot;a&quot;
<strong>Output:</strong> true
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>s1.length == s2.length</code></li>
	<li><code>1 &lt;= s1.length &lt;= 30</code></li>
	<li><code>s1</code> and <code>s2</code> consist of lowercase English letters.</li>
</ul>

## Solutions

**Solution 1: Memorized Search**

We design a function $dfs(i, j, k)$, which means whether the substring starting from $i$ with length $k$ in $s_1$ can be converted into the substring starting from $j$ with length $k$ in $s_2$. If it can be converted, return `true`, otherwise return `false`. The answer is $dfs(0, 0, n)$, where $n$ is the length of the string.

The calculation method of function $dfs(i, j, k)$ is as follows:

-   If $k=1$, then we only need to judge whether $s_1[i]$ and $s_2[j]$ are equal. If they are equal, return `true`, otherwise return `false`;
-   If $k \gt 1$, we enumerate the length of the split part $h$, then there are two cases: if the two substrings of the split are not swapped, then it is $dfs(i, j, h) \land dfs(i+h, j+h, k-h)$; if the two substrings of the split are swapped, then it is $dfs(i, j+k-h, h) \land dfs(i+h, j, k-h)$. If one of the two cases is true, then $dfs(i, j, k)$ is true, return `true`, otherwise return `false`.

Finally, we return $dfs(0, 0, n)$.

In order to avoid repeated calculation, we can use memory search.

The time complexity is $O(n^4)$, and the space complexity is $O(n^3)$. Where $n$ is the length of the string.

**Solution 2: Dynamic Programming (Interval DP)**

We define $f[i][j][k]$ as whether the substring of length $k$ starting from $i$ of string $s_1$ can be transformed into the substring of length $k$ starting from $j$ of string $s_2$. Then the answer is $f[0][0][n]$, where $n$ is the length of the string.

For substring of length $1$, if $s_1[i] = s_2[j]$, then $f[i][j][1] = true$, otherwise $f[i][j][1] = false$.

Next, we enumerate the length $k$ of the substring from small to large, and enumerate $i$ from $0$, and enumerate $j$ from $0$. If $f[i][j][h] \land f[i + h][j + h][k - h]$ or $f[i][j + k - h][h] \land f[i + h][j][k - h]$ is true, then $f[i][j][k]$ is also true.

Finally, we return $f[0][0][n]$.

The time complexity is $O(n^4)$, and the space complexity is $O(n^3)$. Where $n$ is the length of the string.

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def isScramble(self, s1: str, s2: str) -> bool:
        @cache
        def dfs(i: int, j: int, k: int) -> bool:
            if k == 1:
                return s1[i] == s2[j]
            for h in range(1, k):
                if dfs(i, j, h) and dfs(i + h, j + h, k - h):
                    return True
                if dfs(i + h, j, k - h) and dfs(i, j + k - h, h):
                    return True
            return False

        return dfs(0, 0, len(s1))
```

```python
class Solution:
    def isScramble(self, s1: str, s2: str) -> bool:
        n = len(s1)
        f = [[[False] * (n + 1) for _ in range(n)] for _ in range(n)]
        for i in range(n):
            for j in range(n):
                f[i][j][1] = s1[i] == s2[j]
        for k in range(2, n + 1):
            for i in range(n - k + 1):
                for j in range(n - k + 1):
                    for h in range(1, k):
                        if f[i][j][h] and f[i + h][j + h][k - h]:
                            f[i][j][k] = True
                            break
                        if f[i + h][j][k - h] and f[i][j + k - h][h]:
                            f[i][j][k] = True
                            break
        return f[0][0][n]
```

### **Java**

```java
class Solution {
    private Boolean[][][] f;
    private String s1;
    private String s2;

    public boolean isScramble(String s1, String s2) {
        int n = s1.length();
        this.s1 = s1;
        this.s2 = s2;
        f = new Boolean[n][n][n + 1];
        return dfs(0, 0, n);
    }

    private boolean dfs(int i, int j, int k) {
        if (f[i][j][k] != null) {
            return f[i][j][k];
        }
        if (k == 1) {
            return s1.charAt(i) == s2.charAt(j);
        }
        for (int h = 1; h < k; ++h) {
            if (dfs(i, j, h) && dfs(i + h, j + h, k - h)) {
                return f[i][j][k] = true;
            }
            if (dfs(i + h, j, k - h) && dfs(i, j + k - h, h)) {
                return f[i][j][k] = true;
            }
        }
        return f[i][j][k] = false;
    }
}
```

```java
class Solution {
    public boolean isScramble(String s1, String s2) {
        int n = s1.length();
        boolean[][][] f = new boolean[n][n][n + 1];
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                f[i][j][1] = s1.charAt(i) == s2.charAt(j);
            }
        }
        for (int k = 2; k <= n; ++k) {
            for (int i = 0; i <= n - k; ++i) {
                for (int j = 0; j <= n - k; ++j) {
                    for (int h = 1; h < k; ++h) {
                        if (f[i][j][h] && f[i + h][j + h][k - h]) {
                            f[i][j][k] = true;
                            break;
                        }
                        if (f[i + h][j][k - h] && f[i][j + k - h][h]) {
                            f[i][j][k] = true;
                            break;
                        }
                    }
                }
            }
        }
        return f[0][0][n];
    }
}
```

### **C++**

```cpp
class Solution {
public:
    bool isScramble(string s1, string s2) {
        int n = s1.size();
        int f[n][n][n + 1];
        memset(f, -1, sizeof(f));
        function<bool(int, int, int)> dfs = [&](int i, int j, int k) -> int {
            if (f[i][j][k] != -1) {
                return f[i][j][k] == 1;
            }
            if (k == 1) {
                return s1[i] == s2[j];
            }
            for (int h = 1; h < k; ++h) {
                if (dfs(i, j, h) && dfs(i + h, j + h, k - h)) {
                    return f[i][j][k] = true;
                }
                if (dfs(i + h, j, k - h) && dfs(i, j + k - h, h)) {
                    return f[i][j][k] = true;
                }
            }
            return f[i][j][k] = false;
        };
        return dfs(0, 0, n);
    }
};
```

```cpp
class Solution {
public:
    bool isScramble(string s1, string s2) {
        int n = s1.length();
        bool f[n][n][n + 1];
        memset(f, false, sizeof(f));
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                f[i][j][1] = s1[i] == s2[j];
            }
        }
        for (int k = 2; k <= n; ++k) {
            for (int i = 0; i <= n - k; ++i) {
                for (int j = 0; j <= n - k; ++j) {
                    for (int h = 1; h < k; ++h) {
                        if (f[i][j][h] && f[i + h][j + h][k - h]) {
                            f[i][j][k] = true;
                            break;
                        }
                        if (f[i + h][j][k - h] && f[i][j + k - h][h]) {
                            f[i][j][k] = true;
                            break;
                        }
                    }
                }
            }
        }
        return f[0][0][n];
    }
};
```

### **Go**

```go
func isScramble(s1 string, s2 string) bool {
	n := len(s1)
	f := make([][][]int, n)
	for i := range f {
		f[i] = make([][]int, n)
		for j := range f[i] {
			f[i][j] = make([]int, n+1)
		}
	}
	var dfs func(i, j, k int) bool
	dfs = func(i, j, k int) bool {
		if k == 1 {
			return s1[i] == s2[j]
		}
		if f[i][j][k] != 0 {
			return f[i][j][k] == 1
		}
		f[i][j][k] = 2
		for h := 1; h < k; h++ {
			if (dfs(i, j, h) && dfs(i+h, j+h, k-h)) || (dfs(i+h, j, k-h) && dfs(i, j+k-h, h)) {
				f[i][j][k] = 1
				return true
			}
		}
		return false
	}
	return dfs(0, 0, n)
}
```

```go
func isScramble(s1 string, s2 string) bool {
	n := len(s1)
	f := make([][][]bool, n)
	for i := range f {
		f[i] = make([][]bool, n)
		for j := 0; j < n; j++ {
			f[i][j] = make([]bool, n+1)
			f[i][j][1] = s1[i] == s2[j]
		}
	}
	for k := 2; k <= n; k++ {
		for i := 0; i <= n-k; i++ {
			for j := 0; j <= n-k; j++ {
				for h := 1; h < k; h++ {
					if (f[i][j][h] && f[i+h][j+h][k-h]) || (f[i+h][j][k-h] && f[i][j+k-h][h]) {
						f[i][j][k] = true
						break
					}
				}
			}
		}
	}
	return f[0][0][n]
}
```

### **TypeScript**

```ts
function isScramble(s1: string, s2: string): boolean {
    const n = s1.length;
    const f = new Array(n)
        .fill(0)
        .map(() => new Array(n).fill(0).map(() => new Array(n + 1).fill(-1)));
    const dfs = (i: number, j: number, k: number): boolean => {
        if (f[i][j][k] !== -1) {
            return f[i][j][k] === 1;
        }
        if (k === 1) {
            return s1[i] === s2[j];
        }
        for (let h = 1; h < k; ++h) {
            if (dfs(i, j, h) && dfs(i + h, j + h, k - h)) {
                return Boolean((f[i][j][k] = 1));
            }
            if (dfs(i + h, j, k - h) && dfs(i, j + k - h, h)) {
                return Boolean((f[i][j][k] = 1));
            }
        }
        return Boolean((f[i][j][k] = 0));
    };
    return dfs(0, 0, n);
}
```

```ts
function isScramble(s1: string, s2: string): boolean {
    const n = s1.length;
    const f = new Array(n)
        .fill(0)
        .map(() => new Array(n).fill(0).map(() => new Array(n + 1).fill(false)));
    for (let i = 0; i < n; ++i) {
        for (let j = 0; j < n; ++j) {
            f[i][j][1] = s1[i] === s2[j];
        }
    }
    for (let k = 2; k <= n; ++k) {
        for (let i = 0; i <= n - k; ++i) {
            for (let j = 0; j <= n - k; ++j) {
                for (let h = 1; h < k; ++h) {
                    if (f[i][j][h] && f[i + h][j + h][k - h]) {
                        f[i][j][k] = true;
                        break;
                    }
                    if (f[i + h][j][k - h] && f[i][j + k - h][h]) {
                        f[i][j][k] = true;
                        break;
                    }
                }
            }
        }
    }
    return f[0][0][n];
}
```

### **C#**

```cs
public class Solution {
    private string s1;
    private string s2;
    private int[,,] f;

    public bool IsScramble(string s1, string s2) {
        int n = s1.Length;
        this.s1 = s1;
        this.s2 = s2;
        f = new int[n, n, n + 1];
        return dfs(0, 0, n);
    }

    private bool dfs(int i, int j, int k) {
        if (f[i, j, k] != 0) {
            return f[i, j, k] == 1;
        }
        if (k == 1) {
            return s1[i] == s2[j];
        }
        for (int h = 1; h < k; ++h) {
            if (dfs(i, j, h) && dfs(i + h, j + h, k - h)) {
                f[i, j, k] = 1;
                return true;
            }
            if (dfs(i, j + k - h, h) && dfs(i + h, j, k - h)) {
                f[i, j, k] = 1;
                return true;
            }
        }
        f[i, j, k] = -1;
        return false;
    }
}
```

```cs
public class Solution {
    public bool IsScramble(string s1, string s2) {
        int n = s1.Length;
        bool[,,] f = new bool[n, n, n + 1];
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++ j) {
                f[i, j, 1] = s1[i] == s2[j];
            }
        }
        for (int k = 2; k <= n; ++k) {
            for (int i = 0; i <= n - k; ++i) {
                for (int j = 0; j <= n - k; ++j) {
                    for (int h = 1; h < k; ++h) {
                        if (f[i, j, h] && f[i + h, j + h, k - h]) {
                            f[i, j, k] = true;
                            break;
                        }
                        if (f[i, j + k - h, h] && f[i + h, j, k - h]) {
                            f[i, j, k] = true;
                            break;
                        }
                    }
                }
            }
        }
        return f[0, 0, n];
    }
}
```

### **...**

```

```

<!-- tabs:end -->
