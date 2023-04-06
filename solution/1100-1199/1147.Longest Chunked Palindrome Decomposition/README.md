# [1147. 段式回文](https://leetcode.cn/problems/longest-chunked-palindrome-decomposition)

[English Version](/solution/1100-1199/1147.Longest%20Chunked%20Palindrome%20Decomposition/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>你会得到一个字符串&nbsp;<code>text</code>&nbsp;。你应该把它分成 <code>k</code>&nbsp;个子字符串&nbsp;<code>(subtext1, subtext2，…， subtextk)</code>&nbsp;，要求满足:</p>

<ul>
	<li><code>subtext<sub>i</sub></code><sub>&nbsp;</sub>是 <strong>非空&nbsp;</strong>字符串</li>
	<li>所有子字符串的连接等于 <code>text</code> ( 即<code>subtext<sub>1</sub>&nbsp;+ subtext<sub>2</sub>&nbsp;+ ... + subtext<sub>k</sub>&nbsp;== text</code>&nbsp;)</li>
	<li>对于所有 <font color="#c7254e"><font face="Menlo, Monaco, Consolas, Courier New, monospace"><span style="font-size:12.6px"><span style="background-color:#f9f2f4">i</span></span></font></font>&nbsp;的有效值( 即&nbsp;<code>1 &lt;= i&nbsp;&lt;= k</code> ) ，<code>subtext<sub>i</sub>&nbsp;== subtext<sub>k - i + 1</sub></code> 均成立</li>
</ul>

<p>返回<code>k</code>可能最大值。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>text = "ghiabcdefhelloadamhelloabcdefghi"
<strong>输出：</strong>7
<strong>解释：</strong>我们可以把字符串拆分成 "(ghi)(abcdef)(hello)(adam)(hello)(abcdef)(ghi)"。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>text = "merchant"
<strong>输出：</strong>1
<strong>解释：</strong>我们可以把字符串拆分成 "(merchant)"。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>text = "antaprezatepzapreanta"
<strong>输出：</strong>11
<strong>解释：</strong>我们可以把字符串拆分成 "(a)(nt)(a)(pre)(za)(tpe)(za)(pre)(a)(nt)(a)"。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= text.length &lt;= 1000</code></li>
	<li><code>text</code>&nbsp;仅由小写英文字符组成</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：贪心 + 递归**

从字符串的两端开始，如果两端的字符相同，则可以贪心地将这两端的字符作为一段回文串，然后递归处理中间的字符串。

时间复杂度 $O(n^2)$，其中 $n$ 为字符串的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def longestDecomposition(self, text: str) -> int:
        n = len(text)
        if n < 2:
            return n
        for i in range(n // 2 + 1):
            if text[:i] == text[-i:]:
                return 2 + self.longestDecomposition(text[i: -i])
        return 1
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int longestDecomposition(String text) {
        int n = text.length();
        if (n < 2) {
            return n;
        }
        for (int i = 1; i <= n >> 1; ++i) {
            if (text.substring(0, i).equals(text.substring(n - i))) {
                return 2 + longestDecomposition(text.substring(i, n - i));
            }
        }
        return 1;
    }
}
```

```java
class Solution {
    public int longestDecomposition(String text) {
        char[] cs = text.toCharArray();
        int res = 0;
        for (int i = 0, j = cs.length - 1; i <= j;) {
            boolean flag = true;
            for (int k = 1; i + k - 1 < j - k + 1; ++k) {
                if (check(cs, i, j - k + 1, k)) {
                    res += 2;
                    i += k;
                    j -= k;
                    flag = false;
                    break;
                }
            }
            if (flag) {
                ++res;
                break;
            }
        }
        return res;
    }

    private boolean check(char[] cs, int i, int j, int k) {
        while (k-- > 0) {
            if (cs[i++] != cs[j++]) {
                return false;
            }
        }
        return true;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int longestDecomposition(string text) {
        int n = text.size();
        if (n < 2) return n;
        for (int i = 1; i <= n >> 1; ++i) {
            if (text.substr(0, i) == text.substr(n - i)) {
                return 2 + longestDecomposition(text.substr(i, n - i - i));
            }
        }
        return 1;
    }
};
```

### **Go**

```go
func longestDecomposition(text string) int {
	n := len(text)
	if n < 2 {
		return n
	}
	for i := 1; i <= n>>1; i++ {
		if text[:i] == text[n-i:] {
			return 2 + longestDecomposition(text[i:n-i])
		}
	}
	return 1
}
```

### **...**

```

```

<!-- tabs:end -->
