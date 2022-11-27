# [2484. Count Palindromic Subsequences](https://leetcode.com/problems/count-palindromic-subsequences)

[中文文档](/solution/2400-2499/2484.Count%20Palindromic%20Subsequences/README.md)

## Description

<p>Given a string of digits <code>s</code>, return <em>the number of <strong>palindromic subsequences</strong> of</em> <code>s</code><em> having length </em><code>5</code>. Since the answer may be very large, return it <strong>modulo</strong> <code>10<sup>9</sup> + 7</code>.</p>

<p><strong>Note:</strong></p>

<ul>
	<li>A string is <strong>palindromic</strong> if it reads the same forward and backward.</li>
	<li>A <strong>subsequence</strong> is a string that can be derived from another string by deleting some or no characters without changing the order of the remaining characters.</li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;103301&quot;
<strong>Output:</strong> 2
<strong>Explanation:</strong> 
There are 6 possible subsequences of length 5: &quot;10330&quot;,&quot;10331&quot;,&quot;10301&quot;,&quot;10301&quot;,&quot;13301&quot;,&quot;03301&quot;. 
Two of them (both equal to &quot;10301&quot;) are palindromic.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;0000000&quot;
<strong>Output:</strong> 21
<strong>Explanation:</strong> All 21 subsequences are &quot;00000&quot;, which is palindromic.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;9999900000&quot;
<strong>Output:</strong> 2
<strong>Explanation:</strong> The only two palindromic subsequences are &quot;99999&quot; and &quot;00000&quot;.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>4</sup></code></li>
	<li><code>s</code> consists of digits.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

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
