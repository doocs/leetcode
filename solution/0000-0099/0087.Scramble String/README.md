# [87. 扰乱字符串](https://leetcode.cn/problems/scramble-string)

[English Version](/solution/0000-0099/0087.Scramble%20String/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

使用下面描述的算法可以扰乱字符串 <code>s</code> 得到字符串 <code>t</code> ：

<ol>
	<li>如果字符串的长度为 1 ，算法停止</li>
	<li>如果字符串的长度 > 1 ，执行下述步骤：
	<ul>
		<li>在一个随机下标处将字符串分割成两个非空的子字符串。即，如果已知字符串 <code>s</code> ，则可以将其分成两个子字符串 <code>x</code> 和 <code>y</code> ，且满足 <code>s = x + y</code> 。</li>
		<li><strong>随机</strong> 决定是要「交换两个子字符串」还是要「保持这两个子字符串的顺序不变」。即，在执行这一步骤之后，<code>s</code> 可能是 <code>s = x + y</code> 或者 <code>s = y + x</code> 。</li>
		<li>在 <code>x</code> 和 <code>y</code> 这两个子字符串上继续从步骤 1 开始递归执行此算法。</li>
	</ul>
	</li>
</ol>

<p>给你两个 <strong>长度相等</strong> 的字符串 <code>s1</code><em> </em>和 <code>s2</code>，判断 <code>s2</code><em> </em>是否是 <code>s1</code><em> </em>的扰乱字符串。如果是，返回 <code>true</code> ；否则，返回 <code>false</code> 。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>s1 = "great", s2 = "rgeat"
<strong>输出：</strong>true
<strong>解释：</strong>s1 上可能发生的一种情形是：
"great" --> "gr/eat" // 在一个随机下标处分割得到两个子字符串
"gr/eat" --> "gr/eat" // 随机决定：「保持这两个子字符串的顺序不变」
"gr/eat" --> "g/r / e/at" // 在子字符串上递归执行此算法。两个子字符串分别在随机下标处进行一轮分割
"g/r / e/at" --> "r/g / e/at" // 随机决定：第一组「交换两个子字符串」，第二组「保持这两个子字符串的顺序不变」
"r/g / e/at" --> "r/g / e/ a/t" // 继续递归执行此算法，将 "at" 分割得到 "a/t"
"r/g / e/ a/t" --> "r/g / e/ a/t" // 随机决定：「保持这两个子字符串的顺序不变」
算法终止，结果字符串和 s2 相同，都是 "rgeat"
这是一种能够扰乱 s1 得到 s2 的情形，可以认为 s2 是 s1 的扰乱字符串，返回 true
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>s1 = "abcde", s2 = "caebd"
<strong>输出：</strong>false
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>s1 = "a", s2 = "a"
<strong>输出：</strong>true
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>s1.length == s2.length</code></li>
	<li><code>1 <= s1.length <= 30</code></li>
	<li><code>s1</code> 和 <code>s2</code> 由小写英文字母组成</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：记忆化搜索**

我们设计一个函数 $dfs(i, j, k)$，表示字符串 $s_1$ 从 $i$ 开始长度为 $k$ 的子串是否能变换为字符串 $s_2$ 从 $j$ 开始长度为 $k$ 的子串。如果能变换，返回 `true`，否则返回 `false`。那么答案就是 $dfs(0, 0, n)$，其中 $n$ 是字符串的长度。

函数 $dfs(i, j, k)$ 的计算方式如下：

-   如果 $k=1$，那么只需要判断 $s_1[i]$ 和 $s_2[j]$ 是否相等，如果相等返回 `true`，否则返回 `false`；
-   如果 $k \gt 1$，我们枚举分割部分的长度 $h$，那么有两种情况：如果不交换分割的两个子字符串，那么就是 $dfs(i, j, h) \land dfs(i+h, j+h, k-h)$；如果交换了分割的两个子字符串，那么就是 $dfs(i, j+k-h, h) \land dfs(i+h, j, k-h)$。如果两种情况中有一种情况成立，那么就说明 $dfs(i, j, k)$ 成立，返回 `true`，否则返回 `false`。

最后，我们返回 $dfs(0, 0, n)$。

为了避免重复计算，我们可以使用记忆化搜索的方式。

时间复杂度 $O(n^4)$，空间复杂度 $O(n^3)$。其中 $n$ 是字符串的长度。

**方法二：动态规划（区间 DP）**

我们定义 $f[i][j][k]$ 表示字符串 $s_1$ 从 $i$ 开始长度为 $k$ 的子串是否能变换为字符串 $s_2$ 从 $j$ 开始长度为 $k$ 的子串。那么答案就是 $f[0][0][n]$，其中 $n$ 是字符串的长度。

对于长度为 $1$ 的子串，如果 $s_1[i] = s_2[j]$，那么 $f[i][j][1] = true$，否则 $f[i][j][1] = false$。

接下来，我们从小到大枚举子串的长度 $k$，从 $0$ 开始枚举 $i$，从 $0$ 开始枚举 $j$，如果 $f[i][j][h] \land f[i + h][j + h][k - h]$ 或者 $f[i][j + k - h][h] \land f[i + h][j][k - h]$ 成立，那么 $f[i][j][k]$ 也成立。

最后，我们返回 $f[0][0][n]$。

时间复杂度 $O(n^4)$，空间复杂度 $O(n^3)$。其中 $n$ 是字符串的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
                        if () {
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
