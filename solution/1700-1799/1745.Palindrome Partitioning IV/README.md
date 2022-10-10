# [1745. 回文串分割 IV](https://leetcode.cn/problems/palindrome-partitioning-iv)

[English Version](/solution/1700-1799/1745.Palindrome%20Partitioning%20IV/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个字符串 <code>s</code> ，如果可以将它分割成三个 <strong>非空</strong> 回文子字符串，那么返回 <code>true</code> ，否则返回 <code>false</code> 。</p>

<p>当一个字符串正着读和反着读是一模一样的，就称其为 <strong>回文字符串</strong> 。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<b>输入：</b>s = "abcbdd"
<b>输出：</b>true
<strong>解释：</strong>"abcbdd" = "a" + "bcb" + "dd"，三个子字符串都是回文的。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<b>输入：</b>s = "bcbddxy"
<b>输出：</b>false
<strong>解释：</strong>s 没办法被分割成 3 个回文子字符串。
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>3 <= s.length <= 2000</code></li>
	<li><code>s</code>​​​​​​ 只包含小写英文字母。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：预处理 + 枚举**

预处理出字符串 `s` 的所有子串是否为回文串，然后枚举 `s` 的所有分割点，判断是否满足条件。

时间复杂度 $O(n^2)$，空间复杂度 $O(n^2)$。其中 $n$ 为字符串 `s` 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def checkPartitioning(self, s: str) -> bool:
        n = len(s)
        g = [[True] * n for _ in range(n)]
        for i in range(n - 1, -1, -1):
            for j in range(i + 1, n):
                g[i][j] = s[i] == s[j] and (i + 1 == j or g[i + 1][j - 1])
        for i in range(n - 2):
            for j in range(i + 1, n - 1):
                if g[0][i] and g[i + 1][j] and g[j + 1][-1]:
                    return True
        return False
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public boolean checkPartitioning(String s) {
        int n = s.length();
        boolean[][] g = new boolean[n][n];
        for (var e : g) {
            Arrays.fill(e, true);
        }
        for (int i = n - 1; i >= 0; --i) {
            for (int j = i + 1; j < n; ++j) {
                g[i][j] = s.charAt(i) == s.charAt(j) && (i + 1 == j || g[i + 1][j - 1]);
            }
        }
        for (int i = 0; i < n - 2; ++i) {
            for (int j = i + 1; j < n - 1; ++j) {
                if (g[0][i] && g[i + 1][j] && g[j + 1][n - 1]) {
                    return true;
                }
            }
        }
        return false;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    bool checkPartitioning(string s) {
        int n = s.size();
        vector<vector<bool>> g(n, vector<bool>(n, true));
        for (int i = n - 1; i >= 0; --i) {
            for (int j = i + 1; j < n; ++j) {
                g[i][j] = s[i] == s[j] && (i + 1 == j || g[i + 1][j - 1]);
            }
        }
        for (int i = 0; i < n - 2; ++i) {
            for (int j = i + 1; j < n - 1; ++j) {
                if (g[0][i] && g[i + 1][j] && g[j + 1][n - 1]) {
                    return true;
                }
            }
        }
        return false;
    }
};
```

### **Go**

```go
func checkPartitioning(s string) bool {
	n := len(s)
	g := make([][]bool, n)
	for i := range g {
		g[i] = make([]bool, n)
		for j := range g[i] {
			g[i][j] = true
		}
	}
	for i := n - 1; i >= 0; i-- {
		for j := i + 1; j < n; j++ {
			g[i][j] = s[i] == s[j] && (i+1 == j || g[i+1][j-1])
		}
	}
	for i := 0; i < n-2; i++ {
		for j := i + 1; j < n-1; j++ {
			if g[0][i] && g[i+1][j] && g[j+1][n-1] {
				return true
			}
		}
	}
	return false
}
```

### **...**

```

```

<!-- tabs:end -->
