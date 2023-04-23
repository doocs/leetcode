# [1163. 按字典序排在最后的子串](https://leetcode.cn/problems/last-substring-in-lexicographical-order)

[English Version](/solution/1100-1199/1163.Last%20Substring%20in%20Lexicographical%20Order/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个字符串&nbsp;<code>s</code>&nbsp;，找出它的所有子串并按字典序排列，返回排在最后的那个子串。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>s = "abab"
<strong>输出：</strong>"bab"
<strong>解释：</strong>我们可以找出 7 个子串 ["a", "ab", "aba", "abab", "b", "ba", "bab"]。按字典序排在最后的子串是 "bab"。
</pre>

<p><strong>示例&nbsp;2：</strong></p>

<pre>
<strong>输入：</strong>s = "leetcode"
<strong>输出：</strong>"tcode"
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 4 * 10<sup>5</sup></code></li>
	<li><code>s</code> 仅含有小写英文字符。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：双指针**

我们注意到，如果一个子串从位置 $i$ 开始，那么字典序最大的子串一定是 $s[i,..n-1]$，即从位置 $i$ 开始的最长后缀。因此，我们只需要找出字典序最大的后缀子串即可。

我们使用双指针 $i$ 和 $j$，其中指针 $i$ 指向当前字典序最大的子串的起始位置，指针 $j$ 指向当前考虑的子串的起始位置。另外，用一个变量 $k$ 记录当前比较到的位置。初始时 $i = 0$, $j=1$, $k=0$。

每一次，我们比较 $s[i+k]$ 和 $s[j+k]$：

如果 $s[i + k] = s[j + k]$，说明 $s[i,..i+k]$ 和 $s[j,..j+k]$ 相同，我们将 $k$ 加 $1$，继续比较 $s[i+k]$ 和 $s[j+k]$；

如果 $s[i + k] \lt s[j + k]$，说明 $s[j,..j+k]$ 的字典序更大。此时，我们更新 $i = i + k + 1$，并将 $k$ 重置为 $0$。如果此时 $i \geq j$，那么我们将指针 $j$ 更新为 $i + 1$，即 $j = i + 1$。这里我们跳过了以 $s[i,..,i+k]$ 为起始位置的所有后缀子串，因为它们的字典序一定小于对应的 $s[j,..,j+k]$ 为起始位置的后缀子串。

同理，如果 $s[i + k] \gt s[j + k]$，说明 $s[i,..,i+k]$ 的字典序更大。此时，我们更新 $j = j + k + 1$，并将 $k$ 重置为 $0$。这里我们跳过了以 $s[j,..,j+k]$ 为起始位置的所有后缀子串，因为它们的字典序一定小于对应的 $s[i,..,i+k]$ 为起始位置的后缀子串。

最后，我们返回以 $i$ 为起始位置的后缀子串即可，即 $s[i,..,n-1]$。

时间复杂度 $O(n)$，其中 $n$ 为字符串 $s$ 的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def lastSubstring(self, s: str) -> str:
        i, j, k = 0, 1, 0
        while j + k < len(s):
            if s[i + k] == s[j + k]:
                k += 1
            elif s[i + k] < s[j + k]:
                i += k + 1
                k = 0
                if i >= j:
                    j = i + 1
            else:
                j += k + 1
                k = 0
        return s[i:]
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public String lastSubstring(String s) {
        int n = s.length();
        int i = 0;
        for (int j = 1, k = 0; j + k < n;) {
            int d = s.charAt(i + k) - s.charAt(j + k);
            if (d == 0) {
                ++k;
            } else if (d < 0) {
                i += k + 1;
                k = 0;
                if (i >= j) {
                    j = i + 1;
                }
            } else {
                j += k + 1;
                k = 0;
            }
        }
        return s.substring(i);
    }
}
```

### **C++**

```cpp
class Solution {
public:
    string lastSubstring(string s) {
        int n = s.size();
        int i = 0;
        for (int j = 1, k = 0; j + k < n;) {
            if (s[i + k] == s[j + k]) {
                ++k;
            } else if (s[i + k] < s[j + k]) {
                i += k + 1;
                k = 0;
                if (i >= j) {
                    j = i + 1;
                }
            } else {
                j += k + 1;
                k = 0;
            }
        }
        return s.substr(i);
    }
};
```

### **Go**

```go
func lastSubstring(s string) string {
	i, n := 0, len(s)
	for j, k := 1, 0; j+k < n; {
		if s[i+k] == s[j+k] {
			k++
		} else if s[i+k] < s[j+k] {
			i += k + 1
			k = 0
			if i >= j {
				j = i + 1
			}
		} else {
			j += k + 1
			k = 0
		}
	}
	return s[i:]
}
```

### **TypeScript**

```ts
function lastSubstring(s: string): string {
    const n = s.length;
    let i = 0;
    for (let j = 1, k = 0; j + k < n; ) {
        if (s[i + k] === s[j + k]) {
            ++k;
        } else if (s[i + k] < s[j + k]) {
            i += k + 1;
            k = 0;
            if (i >= j) {
                j = i + 1;
            }
        } else {
            j += k + 1;
            k = 0;
        }
    }
    return s.slice(i);
}
```

### **...**

```

```

<!-- tabs:end -->
