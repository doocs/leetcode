# [1312. 让字符串成为回文串的最少插入次数](https://leetcode.cn/problems/minimum-insertion-steps-to-make-a-string-palindrome)

[English Version](/solution/1300-1399/1312.Minimum%20Insertion%20Steps%20to%20Make%20a%20String%20Palindrome/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个字符串&nbsp;<code>s</code>&nbsp;，每一次操作你都可以在字符串的任意位置插入任意字符。</p>

<p>请你返回让&nbsp;<code>s</code>&nbsp;成为回文串的&nbsp;<strong>最少操作次数</strong>&nbsp;。</p>

<p>「回文串」是正读和反读都相同的字符串。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>s = "zzazz"
<strong>输出：</strong>0
<strong>解释：</strong>字符串 "zzazz" 已经是回文串了，所以不需要做任何插入操作。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>s = "mbadm"
<strong>输出：</strong>2
<strong>解释：</strong>字符串可变为 "mbdadbm" 或者 "mdbabdm" 。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>s = "leetcode"
<strong>输出：</strong>5
<strong>解释：</strong>插入 5 个字符后字符串变为 "leetcodocteel" 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 500</code></li>
	<li><code>s</code>&nbsp;中所有字符都是小写字母。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：记忆化搜索**

我们设计一个函数 $dfs(i, j)$，表示将字符串 $s[i..j]$ 变成回文串所需要的最少操作次数。那么答案就是 $dfs(0, n - 1)$。

函数 $dfs(i, j)$ 的计算过程如下：

如果 $i \geq j$，此时无需插入任何字符，我们直接返回 $0$。

否则，我们判断 $s[i]$ 与 $s[j]$ 是否相等，如果 $s[i]=s[j]$，那么我们只需要将 $s[i+1..j-1]$ 变成回文串，那么我们返回 $dfs(i + 1, j - 1)$。否则，我们可以在 $s[i]$ 的左侧或者 $s[j]$ 的右侧插入一个与另一侧相同的字符，那么 $dfs(i, j) = \min(dfs(i + 1, j), dfs(i, j - 1)) + 1$。

为了避免重复计算，我们可以使用记忆化搜索，即使用哈希表或者数组来存储已经计算过的函数值。

最后，我们返回 $dfs(0, n - 1)$ 即可。

时间复杂度 $O(n^2)$，空间复杂度 $O(n^2)$。其中 $n$ 为字符串 $s$ 的长度。

**方法二：动态规划（区间 DP）**

我们定义 $f[i][j]$ 表示将字符串 $s[i..j]$ 变成回文串所需要的最少操作次数。初始时 $f[i][j]=0$，答案即为 $f[0][n-1]$。

对于 $f[i][j]$，如果 $s[i]=s[j]$，那么我们只需要将 $s[i+1..j-1]$ 变成回文串，因此 $f[i][j]=f[i+1][j-1]$。否则，我们可以在 $s[i]$ 的左侧或者 $s[j]$ 的右侧插入一个与另一侧相同的字符，那么 $f[i][j]=\min(f[i+1][j],f[i][j-1])+1$。

综上，我们可以得到状态转移方程：

$$
f[i][j]=\left\{\begin{array}{ll}f[i+1][j-1], & s[i]=s[j]\\ \min(f[i+1][j],f[i][j-1])+1, & s[i]\neq s[j]\end{array}\right.
$$

在枚举时，我们可以有两种枚举的方式：

1. 从大到小枚举 $i$，从小到大枚举 $j$，这样可以保证在计算状态 $f[i][j]$ 时，状态 $f[i+1][j-1]$ 和 $f[i][j-1]$ 都已经计算过了；
1. 从小到大枚举区间长度 $k$，然后枚举区间的左端点 $i$，那么可以得到右端点 $j=i+k-1$，这样也可以保证在计算较大区间 $f[i][j]$ 时，较小区间 $f[i+1][j]$ 和 $f[i][j-1]$ 都已经计算过了。

时间复杂度 $O(n^2)$，空间复杂度 $O(n^2)$。其中 $n$ 为字符串 $s$ 的长度。

相似题目：

-   [1039. 多边形三角剖分的最低得分](/solution/1000-1099/1039.Minimum%20Score%20Triangulation%20of%20Polygon/README.md)

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def minInsertions(self, s: str) -> int:
        @cache
        def dfs(i: int, j: int) -> int:
            if i >= j:
                return 0
            if s[i] == s[j]:
                return dfs(i + 1, j - 1)
            return 1 + min(dfs(i + 1, j), dfs(i, j - 1))

        return dfs(0, len(s) - 1)
```

```python
class Solution:
    def minInsertions(self, s: str) -> int:
        n = len(s)
        f = [[0] * n for _ in range(n)]
        for i in range(n - 2, -1, -1):
            for j in range(i + 1, n):
                if s[i] == s[j]:
                    f[i][j] = f[i + 1][j - 1]
                else:
                    f[i][j] = min(f[i + 1][j], f[i][j - 1]) + 1
        return f[0][-1]
```

```python
class Solution:
    def minInsertions(self, s: str) -> int:
        n = len(s)
        f = [[0] * n for _ in range(n)]
        for k in range(2, n + 1):
            for i in range(n - k + 1):
                j = i + k - 1
                if s[i] == s[j]:
                    f[i][j] = f[i + 1][j - 1]
                else:
                    f[i][j] = min(f[i + 1][j], f[i][j - 1]) + 1
        return f[0][n - 1]
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    private Integer[][] f;
    private String s;

    public int minInsertions(String s) {
        this.s = s;
        int n = s.length();
        f = new Integer[n][n];
        return dfs(0, n - 1);
    }

    private int dfs(int i, int j) {
        if (i >= j) {
            return 0;
        }
        if (f[i][j] != null) {
            return f[i][j];
        }
        int ans = 1 << 30;
        if (s.charAt(i) == s.charAt(j)) {
            ans = dfs(i + 1, j - 1);
        } else {
            ans = Math.min(dfs(i + 1, j), dfs(i, j - 1)) + 1;
        }
        return f[i][j] = ans;
    }
}
```

```java
class Solution {
    public int minInsertions(String s) {
        int n = s.length();
        int[][] f = new int[n][n];
        for (int i = n - 2; i >= 0; --i) {
            for (int j = i + 1; j < n; ++j) {
                if (s.charAt(i) == s.charAt(j)) {
                    f[i][j] = f[i + 1][j - 1];
                } else {
                    f[i][j] = Math.min(f[i + 1][j], f[i][j - 1]) + 1;
                }
            }
        }
        return f[0][n - 1];
    }
}
```

```java
class Solution {
    public int minInsertions(String s) {
        int n = s.length();
        int[][] f = new int[n][n];
        for (int k = 2; k <= n; ++k) {
            for (int i = 0; i + k - 1 < n; ++i) {
                int j = i + k - 1;
                if (s.charAt(i) == s.charAt(j)) {
                    f[i][j] = f[i + 1][j - 1];
                } else {
                    f[i][j] = Math.min(f[i + 1][j], f[i][j - 1]) + 1;
                }
            }
        }
        return f[0][n - 1];
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int minInsertions(string s) {
        int n = s.size();
        int f[n][n];
        memset(f, -1, sizeof(f));
        function<int(int, int)> dfs = [&](int i, int j) -> int {
            if (i >= j) {
                return 0;
            }
            if (f[i][j] != -1) {
                return f[i][j];
            }
            int ans = 1 << 30;
            if (s[i] == s[j]) {
                ans = dfs(i + 1, j - 1);
            } else {
                ans = min(dfs(i + 1, j), dfs(i, j - 1)) + 1;
            }
            return f[i][j] = ans;
        };
        return dfs(0, n - 1);
    }
};
```

```cpp
class Solution {
public:
    int minInsertions(string s) {
        int n = s.size();
        int f[n][n];
        memset(f, 0, sizeof(f));
        for (int i = n - 2; i >= 0; --i) {
            for (int j = i + 1; j < n; ++j) {
                if (s[i] == s[j]) {
                    f[i][j] = f[i + 1][j - 1];
                } else {
                    f[i][j] = min(f[i + 1][j], f[i][j - 1]) + 1;
                }
            }
        }
        return f[0][n - 1];
    }
};
```

```cpp
class Solution {
public:
    int minInsertions(string s) {
        int n = s.size();
        int f[n][n];
        memset(f, 0, sizeof(f));
        for (int k = 2; k <= n; ++k) {
            for (int i = 0; i + k - 1 < n; ++i) {
                int j = i + k - 1;
                if (s[i] == s[j]) {
                    f[i][j] = f[i + 1][j - 1];
                } else {
                    f[i][j] = min(f[i + 1][j], f[i][j - 1]) + 1;
                }
            }
        }
        return f[0][n - 1];
    }
};
```

### **Go**

```go
func minInsertions(s string) int {
	n := len(s)
	f := make([][]int, n)
	for i := range f {
		f[i] = make([]int, n)
		for j := range f[i] {
			f[i][j] = -1
		}
	}
	var dfs func(i, j int) int
	dfs = func(i, j int) int {
		if i >= j {
			return 0
		}
		if f[i][j] != -1 {
			return f[i][j]
		}
		ans := 1 << 30
		if s[i] == s[j] {
			ans = dfs(i+1, j-1)
		} else {
			ans = min(dfs(i+1, j), dfs(i, j-1)) + 1
		}
		f[i][j] = ans
		return ans
	}
	return dfs(0, n-1)
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}
```

```go
func minInsertions(s string) int {
	n := len(s)
	f := make([][]int, n)
	for i := range f {
		f[i] = make([]int, n)
	}
	for i := n - 2; i >= 0; i-- {
		for j := i + 1; j < n; j++ {
			if s[i] == s[j] {
				f[i][j] = f[i+1][j-1]
			} else {
				f[i][j] = min(f[i+1][j], f[i][j-1]) + 1
			}
		}
	}
	return f[0][n-1]
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}
```

```go
func minInsertions(s string) int {
	n := len(s)
	f := make([][]int, n)
	for i := range f {
		f[i] = make([]int, n)
	}
	for k := 2; k <= n; k++ {
		for i := 0; i+k-1 < n; i++ {
			j := i + k - 1
			if s[i] == s[j] {
				f[i][j] = f[i+1][j-1]
			} else {
				f[i][j] = min(f[i+1][j], f[i][j-1]) + 1
			}
		}
	}
	return f[0][n-1]
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}
```

### **...**

```

```

<!-- tabs:end -->
