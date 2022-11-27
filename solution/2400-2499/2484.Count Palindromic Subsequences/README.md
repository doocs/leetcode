# [2484. 统计回文子序列数目](https://leetcode.cn/problems/count-palindromic-subsequences)

[English Version](/solution/2400-2499/2484.Count%20Palindromic%20Subsequences/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你数字字符串&nbsp;<code>s</code>&nbsp;，请你返回&nbsp;<code>s</code>&nbsp;中长度为&nbsp;<code>5</code>&nbsp;的 <b>回文子序列</b>&nbsp;数目。由于答案可能很大，请你将答案对&nbsp;<code>10<sup>9</sup> + 7</code>&nbsp;<strong>取余</strong>&nbsp;后返回。</p>

<p><strong>提示：</strong></p>

<ul>
	<li>如果一个字符串从前往后和从后往前读相同，那么它是 <strong>回文字符串</strong>&nbsp;。</li>
	<li>子序列是一个字符串中删除若干个字符后，不改变字符顺序，剩余字符构成的字符串。</li>
</ul>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>s = "103301"
<b>输出：</b>2
<b>解释：</b>
总共有 6 长度为 5 的子序列："10330" ，"10331" ，"10301" ，"10301" ，"13301" ，"03301" 。
它们中有两个（都是 "10301"）是回文的。
</pre>

<p><strong>示例 2：</strong></p>

<pre><b>输入：</b>s = "0000000"
<b>输出：</b>21
<b>解释：</b>所有 21 个长度为 5 的子序列都是 "00000" ，都是回文的。
</pre>

<p><strong>示例 3：</strong></p>

<pre><b>输入：</b>s = "9999900000"
<b>输出：</b>2
<b>解释：</b>仅有的两个回文子序列是 "99999" 和 "00000" 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>4</sup></code></li>
	<li><code>s</code>&nbsp;只包含数字字符。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：枚举 + 计数**

时间复杂度 $O(100 \times n)$，空间复杂度 $O(100 \times n)$。其中 $n$ 为字符串 $s$ 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def countPalindromes(self, s: str) -> int:
        mod = 10**9 + 7
        n = len(s)
        pre = [[[0] * 10 for _ in range(10)] for _ in range(n + 2)]
        suf = [[[0] * 10 for _ in range(10)] for _ in range(n + 2)]
        t = list(map(int, s))
        c = [0] * 10
        for i, v in enumerate(t, 1):
            for j in range(10):
                for k in range(10):
                    pre[i][j][k] = pre[i - 1][j][k]
            for j in range(10):
                pre[i][j][v] += c[j]
            c[v] += 1
        c = [0] * 10
        for i in range(n, 0, -1):
            v = t[i - 1]
            for j in range(10):
                for k in range(10):
                    suf[i][j][k] = suf[i + 1][j][k]
            for j in range(10):
                suf[i][j][v] += c[j]
            c[v] += 1
        ans = 0
        for i in range(1, n + 1):
            for j in range(10):
                for k in range(10):
                    ans += pre[i - 1][j][k] * suf[i + 1][j][k]
                    ans %= mod
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    private static final int MOD = (int) 1e9 + 7;

    public int countPalindromes(String s) {
        int n = s.length();
        int[][][] pre = new int[n + 2][10][10];
        int[][][] suf = new int[n + 2][10][10];
        int[] t = new int[n];
        for (int i = 0; i < n; ++i) {
            t[i] = s.charAt(i) - '0';
        }
        int[] c = new int[10];
        for (int i = 1; i <= n; ++i) {
            int v = t[i - 1];
            for (int j = 0; j < 10; ++j) {
                for (int k = 0; k < 10; ++k) {
                    pre[i][j][k] = pre[i - 1][j][k];
                }
            }
            for (int j = 0; j < 10; ++j) {
                pre[i][j][v] += c[j];
            }
            c[v]++;
        }
        c = new int[10];
        for (int i = n; i > 0; --i) {
            int v = t[i - 1];
            for (int j = 0; j < 10; ++j) {
                for (int k = 0; k < 10; ++k) {
                    suf[i][j][k] = suf[i + 1][j][k];
                }
            }
            for (int j = 0; j < 10; ++j) {
                suf[i][j][v] += c[j];
            }
            c[v]++;
        }
        long ans = 0;
        for (int i = 1; i <= n; ++i) {
            for (int j = 0; j < 10; ++j) {
                for (int k = 0; k < 10; ++k) {
                    ans += (long) pre[i - 1][j][k] * suf[i + 1][j][k];
                    ans %= MOD;
                }
            }
        }
        return (int) ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    const int mod = 1e9 + 7;

    int countPalindromes(string s) {
        int n = s.size();
        int pre[n + 2][10][10];
        int suf[n + 2][10][10];
        memset(pre, 0, sizeof pre);
        memset(suf, 0, sizeof suf);
        int t[n];
        for (int i = 0; i < n; ++i) t[i] = s[i] - '0';
        int c[10] = {0};
        for (int i = 1; i <= n; ++i) {
            int v = t[i - 1];
            for (int j = 0; j < 10; ++j) {
                for (int k = 0; k < 10; ++k) {
                    pre[i][j][k] = pre[i - 1][j][k];
                }
            }
            for (int j = 0; j < 10; ++j) {
                pre[i][j][v] += c[j];
            }
            c[v]++;
        }
        memset(c, 0, sizeof c);
        for (int i = n; i > 0; --i) {
            int v = t[i - 1];
            for (int j = 0; j < 10; ++j) {
                for (int k = 0; k < 10; ++k) {
                    suf[i][j][k] = suf[i + 1][j][k];
                }
            }
            for (int j = 0; j < 10; ++j) {
                suf[i][j][v] += c[j];
            }
            c[v]++;
        }
        long ans = 0;
        for (int i = 1; i <= n; ++i) {
            for (int j = 0; j < 10; ++j) {
                for (int k = 0; k < 10; ++k) {
                    ans += 1ll * pre[i - 1][j][k] * suf[i + 1][j][k];
                    ans %= mod;
                }
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func countPalindromes(s string) int {
	n := len(s)
	pre := [10010][10][10]int{}
	suf := [10010][10][10]int{}
	t := make([]int, n)
	for i, c := range s {
		t[i] = int(c - '0')
	}
	c := [10]int{}
	for i := 1; i <= n; i++ {
		v := t[i-1]
		for j := 0; j < 10; j++ {
			for k := 0; k < 10; k++ {
				pre[i][j][k] = pre[i-1][j][k]
			}
		}
		for j := 0; j < 10; j++ {
			pre[i][j][v] += c[j]
		}
		c[v]++
	}
	c = [10]int{}
	for i := n; i > 0; i-- {
		v := t[i-1]
		for j := 0; j < 10; j++ {
			for k := 0; k < 10; k++ {
				suf[i][j][k] = suf[i+1][j][k]
			}
		}
		for j := 0; j < 10; j++ {
			suf[i][j][v] += c[j]
		}
		c[v]++
	}
	ans := 0
	const mod int = 1e9 + 7
	for i := 1; i <= n; i++ {
		for j := 0; j < 10; j++ {
			for k := 0; k < 10; k++ {
				ans += pre[i-1][j][k] * suf[i+1][j][k]
				ans %= mod
			}
		}
	}
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
