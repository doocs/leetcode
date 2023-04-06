# [1092. 最短公共超序列](https://leetcode.cn/problems/shortest-common-supersequence)

[English Version](/solution/1000-1099/1092.Shortest%20Common%20Supersequence/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你两个字符串&nbsp;<code>str1</code> 和&nbsp;<code>str2</code>，返回同时以&nbsp;<code>str1</code>&nbsp;和&nbsp;<code>str2</code>&nbsp;作为 <strong>子序列</strong> 的最短字符串。如果答案不止一个，则可以返回满足条件的 <strong>任意一个</strong> 答案。</p>

<p>如果从字符串 <code>t</code> 中删除一些字符（也可能不删除），可以得到字符串 <code>s</code> ，那么 <code>s</code> 就是 t 的一个子序列。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>str1 = "abac", str2 = "cab"
<strong>输出：</strong>"cabac"
<strong>解释：</strong>
str1 = "abac" 是 "cabac" 的一个子串，因为我们可以删去 "cabac" 的第一个 "c"得到 "abac"。 
str2 = "cab" 是 "cabac" 的一个子串，因为我们可以删去 "cabac" 末尾的 "ac" 得到 "cab"。
最终我们给出的答案是满足上述属性的最短字符串。
</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<strong>输入：</strong>str1 = "aaaaaaaa", str2 = "aaaaaaaa"
<strong>输出：</strong>"aaaaaaaa"
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= str1.length, str2.length &lt;= 1000</code></li>
	<li><code>str1</code> 和&nbsp;<code>str2</code>&nbsp;都由小写英文字母组成。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：动态规划 + 构造**

我们先用动态规划求出两个字符串的最长公共子序列，然后根据最长公共子序列构造出最短公共超序列。

定义 $f[i][j]$ 表示字符串 $str1$ 的前 $i$ 个字符和字符串 $str2$ 的前 $j$ 个字符的最长公共子序列的长度。状态转移方程如下：

$$
f[i][j] =
\begin{cases}
0 & i = 0 \text{ or } j = 0 \\
f[i - 1][j - 1] + 1 & str1[i - 1] = str2[j - 1] \\
\max(f[i - 1][j], f[i][j - 1]) & str1[i - 1] \neq str2[j - 1]
\end{cases}
$$

接下来我们基于 $f[i][j]$ 构造出最短公共超序列。

```bash
str1:       a   b   a   c

str2:   c   a   b

ans:    c   a   b   a   c
```

不妨对照着上面的示例字符串，来看看如何构造出最短公共超序列。

我们用双指针 $i$ 和 $j$ 分别指向字符串 $str1$ 和 $str2$ 的末尾，然后从后往前遍历，每次比较 $str1[i]$ 和 $str2[j]$ 的值：

-   如果 $str1[i] = str2[j]$，则将 $str1[i]$ 或 $str2[j]$ 中的任意一个字符加入到最答案序列的末尾，然后 $i$ 和 $j$ 同时减 $1$；
-   如果 $str1[i] \neq str2[j]$，则将 $f[i][j]$ 与 $f[i - 1][j]$ 和 $f[i][j - 1]$ 中的最大值进行比较：
    -   如果 $f[i][j] = f[i - 1][j]$，则将 $str1[i]$ 加入到答案序列的末尾，然后 $i$ 减 $1$；
    -   如果 $f[i][j] = f[i][j - 1]$，则将 $str2[j]$ 加入到答案序列的末尾，然后 $j$ 减 $1$。

重复上述操作，直到 $i = 0$ 或 $j = 0$，然后将剩余的字符串加入到答案序列的末尾即可。

最后我们将答案序列反转，即可得到最终的答案。

时间复杂度 $O(m\times n)$，空间复杂度 $O(m\times n)$。其中 $m$ 和 $n$ 分别是字符串 $str1$ 和 $str2$ 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def shortestCommonSupersequence(self, str1: str, str2: str) -> str:
        m, n = len(str1), len(str2)
        f = [[0] * (n + 1) for _ in range(m + 1)]
        for i in range(1, m + 1):
            for j in range(1, n + 1):
                if str1[i - 1] == str2[j - 1]:
                    f[i][j] = f[i - 1][j - 1] + 1
                else:
                    f[i][j] = max(f[i - 1][j], f[i][j - 1])
        ans = []
        i, j = m, n
        while i or j:
            if i == 0:
                j -= 1
                ans.append(str2[j])
            elif j == 0:
                i -= 1
                ans.append(str1[i])
            else:
                if f[i][j] == f[i - 1][j]:
                    i -= 1
                    ans.append(str1[i])
                elif f[i][j] == f[i][j - 1]:
                    j -= 1
                    ans.append(str2[j])
                else:
                    i, j = i - 1, j - 1
                    ans.append(str1[i])
        return ''.join(ans[::-1])
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public String shortestCommonSupersequence(String str1, String str2) {
        int m = str1.length(), n = str2.length();
        int[][] f = new int[m + 1][n + 1];
        for (int i = 1; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    f[i][j] = f[i - 1][j - 1] + 1;
                } else {
                    f[i][j] = Math.max(f[i - 1][j], f[i][j - 1]);
                }
            }
        }
        int i = m, j = n;
        StringBuilder ans = new StringBuilder();
        while (i > 0 || j > 0) {
            if (i == 0) {
                ans.append(str2.charAt(--j));
            } else if (j == 0) {
                ans.append(str1.charAt(--i));
            } else {
                if (f[i][j] == f[i - 1][j]) {
                    ans.append(str1.charAt(--i));
                } else if (f[i][j] == f[i][j - 1]) {
                    ans.append(str2.charAt(--j));
                } else {
                    ans.append(str1.charAt(--i));
                    --j;
                }
            }
        }
        return ans.reverse().toString();
    }
}
```

### **C++**

```cpp
class Solution {
public:
    string shortestCommonSupersequence(string str1, string str2) {
        int m = str1.size(), n = str2.size();
        vector<vector<int>> f(m + 1, vector<int>(n + 1));
        for (int i = 1; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                if (str1[i - 1] == str2[j - 1])
                    f[i][j] = f[i - 1][j - 1] + 1;
                else
                    f[i][j] = max(f[i - 1][j], f[i][j - 1]);
            }
        }
        int i = m, j = n;
        string ans;
        while (i || j) {
            if (i == 0)
                ans += str2[--j];
            else if (j == 0)
                ans += str1[--i];
            else {
                if (f[i][j] == f[i - 1][j])
                    ans += str1[--i];
                else if (f[i][j] == f[i][j - 1])
                    ans += str2[--j];
                else
                    ans += str1[--i], --j;
            }
        }
        reverse(ans.begin(), ans.end());
        return ans;
    }
};
```

### **Go**

```go
func shortestCommonSupersequence(str1 string, str2 string) string {
	m, n := len(str1), len(str2)
	f := make([][]int, m+1)
	for i := range f {
		f[i] = make([]int, n+1)
	}
	for i := 1; i <= m; i++ {
		for j := 1; j <= n; j++ {
			if str1[i-1] == str2[j-1] {
				f[i][j] = f[i-1][j-1] + 1
			} else {
				f[i][j] = max(f[i-1][j], f[i][j-1])
			}
		}
	}
	ans := []byte{}
	i, j := m, n
	for i > 0 || j > 0 {
		if i == 0 {
			j--
			ans = append(ans, str2[j])
		} else if j == 0 {
			i--
			ans = append(ans, str1[i])
		} else {
			if f[i][j] == f[i-1][j] {
				i--
				ans = append(ans, str1[i])
			} else if f[i][j] == f[i][j-1] {
				j--
				ans = append(ans, str2[j])
			} else {
				i, j = i-1, j-1
				ans = append(ans, str1[i])
			}
		}
	}
	for i, j = 0, len(ans)-1; i < j; i, j = i+1, j-1 {
		ans[i], ans[j] = ans[j], ans[i]
	}
	return string(ans)
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
function shortestCommonSupersequence(str1: string, str2: string): string {
    const m = str1.length;
    const n = str2.length;
    const f = new Array(m + 1).fill(0).map(() => new Array(n + 1).fill(0));
    for (let i = 1; i <= m; ++i) {
        for (let j = 1; j <= n; ++j) {
            if (str1[i - 1] == str2[j - 1]) {
                f[i][j] = f[i - 1][j - 1] + 1;
            } else {
                f[i][j] = Math.max(f[i - 1][j], f[i][j - 1]);
            }
        }
    }
    let ans: string[] = [];
    let i = m;
    let j = n;
    while (i > 0 || j > 0) {
        if (i === 0) {
            ans.push(str2[--j]);
        } else if (j === 0) {
            ans.push(str1[--i]);
        } else {
            if (f[i][j] === f[i - 1][j]) {
                ans.push(str1[--i]);
            } else if (f[i][j] === f[i][j - 1]) {
                ans.push(str2[--j]);
            } else {
                ans.push(str1[--i]);
                --j;
            }
        }
    }
    return ans.reverse().join('');
}
```

### **...**

```

```

<!-- tabs:end -->
