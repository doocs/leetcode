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

<!-- tabs:start -->

### **Python3**

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

### **Java**

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

### **C++**

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

### **Go**

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

### **TypeScript**

```ts
function isInterleave(s1: string, s2: string, s3: string): boolean {
    const m = s1.length;
    const n = s2.length;
    if (m + n !== s3.length) {
        return false;
    }
    const f: number[][] = new Array(m + 1)
        .fill(0)
        .map(() => new Array(n + 1).fill(0));
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

```ts
function isInterleave(s1: string, s2: string, s3: string): boolean {
    const m = s1.length;
    const n = s2.length;
    if (m + n !== s3.length) {
        return false;
    }
    const f: boolean[][] = new Array(m + 1)
        .fill(0)
        .map(() => new Array(n + 1).fill(false));
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

### **C#**

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

### **...**

```

```

<!-- tabs:end -->
