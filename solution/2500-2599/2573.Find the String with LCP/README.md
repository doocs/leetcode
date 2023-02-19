# [2573. 找出对应 LCP 矩阵的字符串](https://leetcode.cn/problems/find-the-string-with-lcp)

[English Version](/solution/2500-2599/2573.Find%20the%20String%20with%20LCP/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>对任一由 <code>n</code> 个小写英文字母组成的字符串 <code>word</code> ，我们可以定义一个 <code>n x n</code> 的矩阵，并满足：</p>

<ul>
	<li><code>lcp[i][j]</code> 等于子字符串&nbsp;<code>word[i,...,n-1]</code> 和 <code>word[j,...,n-1]</code> 之间的最长公共前缀的长度。</li>
</ul>

<p>给你一个 <code>n x n</code> 的矩阵 <code>lcp</code> 。返回与 <code>lcp</code> 对应的、按字典序最小的字符串&nbsp;<code>word</code> 。如果不存在这样的字符串，则返回空字符串。</p>

<p>对于长度相同的两个字符串 <code>a</code> 和 <code>b</code> ，如果在 <code>a</code> 和 <code>b</code> 不同的第一个位置，字符串 <code>a</code> 的字母在字母表中出现的顺序先于 <code>b</code> 中的对应字母，则认为字符串 <code>a</code> 按字典序比字符串 <code>b</code> 小。例如，<code>"aabd"</code> 在字典上小于 <code>"aaca"</code> ，因为二者不同的第一位置是第三个字母，而&nbsp;<code>'b'</code> 先于 <code>'c'</code> 出现。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>lcp = [[4,0,2,0],[0,3,0,1],[2,0,2,0],[0,1,0,1]]
<strong>输出：</strong>"abab"
<strong>解释：</strong>lcp 对应由两个交替字母组成的任意 4 字母字符串，字典序最小的是 "abab" 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>lcp = [[4,3,2,1],[3,3,2,1],[2,2,2,1],[1,1,1,1]]
<strong>输出：</strong>"aaaa"
<strong>解释：</strong>lcp 对应只有一个不同字母的任意 4 字母字符串，字典序最小的是 "aaaa" 。 
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>lcp = [[4,3,2,1],[3,3,2,1],[2,2,2,1],[1,1,1,3]]
<strong>输出：</strong>""
<strong>解释：</strong>lcp[3][3] 无法等于 3 ，因为 word[3,...,3] 仅由单个字母组成；因此，不存在答案。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n ==&nbsp;</code><code>lcp.length == </code><code>lcp[i].length</code>&nbsp;<code>&lt;= 1000</code></li>
	<li><code><font face="monospace">0 &lt;= lcp[i][j] &lt;= n</font></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：贪心 + 构造**

由于构造的字符串要求字典序最小，因此我们可以从字符 `'a'` 开始，填充到字符串 $s$ 中。

如果当前位置 $i$ 还未填充字符，那么我们可以将字符 `'a'` 填充到 $i$ 位置，然后枚举所有 $j \gt i$ 的位置，如果 $lcp[i][j] \gt 0$，那么位置 $j$ 也应该填充字符 `'a'`。然后我们将字符 `'a'` 的 ASCII 码加一，继续填充剩余未填充的位置。

填充结束后，如果字符串中存在未填充的位置，说明无法构造出对应的字符串，返回空字符串。

接下来，我们可以从大到小枚举字符串中的每个位置 $i$ 和 $j$，然后判断 $s[i]$ 和 $s[j]$ 是否相等：

-   如果 $s[i] = s[j]$，此时我们需要判断 $i$ 和 $j$ 是否为字符串的最后一个位置，如果是，那么 $lcp[i][j]$ 应该等于 $1$，否则 $lcp[i][j]$ 应该等于 $0$。如果不满足上述条件，说明无法构造出对应的字符串，返回空字符串。如果 $i$ 和 $j$ 不是字符串的最后一个位置，那么 $lcp[i][j]$ 应该等于 $lcp[i + 1][j + 1] + 1$，否则说明无法构造出对应的字符串，返回空字符串。
-   否则，如果 $lcp[i][j] \gt 0$，说明无法构造出对应的字符串，返回空字符串。

如果字符串中的每个位置都满足上述条件，那么我们就可以构造出对应的字符串，返回即可。

时间复杂度为 $O(n^2)$，空间复杂度为 $O(n)$。其中 $n$ 为字符串的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def findTheString(self, lcp: List[List[int]]) -> str:
        n = len(lcp)
        s = [""] * n
        i = 0
        for c in ascii_lowercase:
            while i < n and s[i]:
                i += 1
            if i == n:
                break
            for j in range(i, n):
                if lcp[i][j]:
                    s[j] = c
        if "" in s:
            return ""
        for i in range(n - 1, -1, -1):
            for j in range(n - 1, -1, -1):
                if s[i] == s[j]:
                    if i == n - 1 or j == n - 1:
                        if lcp[i][j] != 1:
                            return ""
                    elif lcp[i][j] != lcp[i + 1][j + 1] + 1:
                        return ""
                elif lcp[i][j]:
                    return ""
        return "".join(s)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public String findTheString(int[][] lcp) {
        int n = lcp.length;
        char[] s = new char[n];
        int i = 0;
        for (char c = 'a'; c <= 'z'; ++c) {
            while (i < n && s[i] != '\0') {
                ++i;
            }
            if (i == n) {
                break;
            }
            for (int j = i; j < n; ++j) {
                if (lcp[i][j] > 0) {
                    s[j] = c;
                }
            }
        }
        for (i = 0; i < n; ++i) {
            if (s[i] == '\0') {
                return "";
            }
        }
        for (i = n - 1; i >= 0; --i) {
            for (int j = n - 1; j >= 0; --j) {
                if (s[i] == s[j]) {
                    if (i == n - 1 || j == n - 1) {
                        if (lcp[i][j] != 1) {
                            return "";
                        }
                    } else if (lcp[i][j] != lcp[i + 1][j + 1] + 1) {
                        return "";
                    }
                } else if (lcp[i][j] > 0) {
                    return "";
                }
            }
        }
        return String.valueOf(s);
    }
}
```

### **C++**

```cpp
class Solution {
public:
    string findTheString(vector<vector<int>>& lcp) {
        int i = 0, n = lcp.size();
        string s(n, '\0');
        for (char c = 'a'; c <= 'z'; ++c) {
            while (i < n && s[i]) {
                ++i;
            }
            if (i == n) {
                break;
            }
            for (int j = i; j < n; ++j) {
                if (lcp[i][j]) {
                    s[j] = c;
                }
            }
        }
        if (s.find('\0') != -1) {
            return "";
        }
        for (i = n - 1; ~i; --i) {
            for (int j = n - 1; ~j; --j) {
                if (s[i] == s[j]) {
                    if (i == n - 1 || j == n - 1) {
                        if (lcp[i][j] != 1) {
                            return "";
                        }
                    } else if (lcp[i][j] != lcp[i + 1][j + 1] + 1) {
                        return "";
                    }
                } else if (lcp[i][j]) {
                    return "";
                }
            }
        }
        return s;
    }
};
```

### **Go**

```go
func findTheString(lcp [][]int) string {
	i, n := 0, len(lcp)
	s := make([]byte, n)
	for c := 'a'; c <= 'z'; c++ {
		for i < n && s[i] != 0 {
			i++
		}
		if i == n {
			break
		}
		for j := i; j < n; j++ {
			if lcp[i][j] > 0 {
				s[j] = byte(c)
			}
		}
	}
	if bytes.IndexByte(s, 0) >= 0 {
		return ""
	}
	for i := n - 1; i >= 0; i-- {
		for j := n - 1; j >= 0; j-- {
			if s[i] == s[j] {
				if i == n-1 || j == n-1 {
					if lcp[i][j] != 1 {
						return ""
					}
				} else if lcp[i][j] != lcp[i+1][j+1]+1 {
					return ""
				}
			} else if lcp[i][j] > 0 {
				return ""
			}
		}
	}
	return string(s)
}
```

### **...**

```

```

<!-- tabs:end -->
