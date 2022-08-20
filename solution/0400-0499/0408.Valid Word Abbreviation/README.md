# [408. 有效单词缩写](https://leetcode.cn/problems/valid-word-abbreviation)

[English Version](/solution/0400-0499/0408.Valid%20Word%20Abbreviation/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>字符串可以用 <strong>缩写</strong> 进行表示，<strong>缩写</strong> 的方法是将任意数量的 <strong>不相邻</strong> 的子字符串替换为相应子串的长度。例如，字符串 <code>"substitution"</code> 可以缩写为（不止这几种方法）：</p>

<ul>
	<li><code>"s10n"</code> (<code>"s <em><strong>ubstitutio</strong></em> n"</code>)</li>
	<li><code>"sub4u4"</code> (<code>"sub <em><strong>stit</strong></em> u <em><strong>tion</strong></em>"</code>)</li>
	<li><code>"12"</code> (<code>"<em><strong>substitution</strong></em>"</code>)</li>
	<li><code>"su3i1u2on"</code> (<code>"su <em><strong>bst</strong></em> i <em><strong>t</strong></em> u <em><strong>ti</strong></em> on"</code>)</li>
	<li><code>"substitution"</code> (没有替换子字符串)</li>
</ul>

<p>下列是不合法的缩写：</p>

<ul>
	<li><code>"s55n"</code>&nbsp;(<code>"s&nbsp;<u>ubsti</u>&nbsp;<u>tutio</u>&nbsp;n"</code>，两处缩写相邻)</li>
	<li><code>"s010n"</code>&nbsp;(缩写存在前导零)</li>
	<li><code>"s0ubstitution"</code>&nbsp;(缩写是一个空字符串)</li>
</ul>

<p>给你一个字符串单词 <code>word</code> 和一个缩写&nbsp;<code>abbr</code>&nbsp;，判断这个缩写是否可以是给定单词的缩写。</p>

<p><strong>子字符串</strong>是字符串中连续的<strong>非空</strong>字符序列。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>word = "internationalization", abbr = "i12iz4n"
<strong>输出：</strong>true
<strong>解释：</strong>单词 "internationalization" 可以缩写为 "i12iz4n" ("i <em><strong>nternational</strong></em> iz <em><strong>atio</strong></em> n") 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>word = "apple", abbr = "a2e"
<strong>输出：</strong>false
<strong>解释：</strong>单词 "apple" 无法缩写为 "a2e" 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= word.length &lt;= 20</code></li>
	<li><code>word</code> 仅由小写英文字母组成</li>
	<li><code>1 &lt;= abbr.length &lt;= 10</code></li>
	<li><code>abbr</code> 由小写英文字母和数字组成</li>
	<li><code>abbr</code> 中的所有数字均符合 32-bit 整数范围</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：模拟**

模拟字符匹配替换。

同时遍历 $word$ 和 $abbr$，若 $abbr$ 遇到数字，则 $word$ 跳过对应数字长度的字符数。若数字为空，或者有前导零，则提前返回 false。

时间复杂度 $O(m+n)$，空间复杂度 $O(1)$。其中 $m$ 是 $word$ 的长度，而 $n$ 是 $abbr$ 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def validWordAbbreviation(self, word: str, abbr: str) -> bool:
        i = j = 0
        m, n = len(word), len(abbr)
        while i < m:
            if j >= n:
                return False
            if word[i] == abbr[j]:
                i, j = i + 1, j + 1
                continue
            k = j
            while k < n and abbr[k].isdigit():
                k += 1
            t = abbr[j: k]
            if not t.isdigit() or t[0] == '0' or int(t) == 0:
                return False
            i += int(t)
            j = k
        return i == m and j == n
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public boolean validWordAbbreviation(String word, String abbr) {
        int m = word.length(), n = abbr.length();
        int i = 0, j = 0;
        while (i < m) {
            if (j >= n) {
                return false;
            }
            if (word.charAt(i) == abbr.charAt(j)) {
                ++i;
                ++j;
                continue;
            }
            int k = j;
            while (k < n && Character.isDigit(abbr.charAt(k))) {
                ++k;
            }
            String t = abbr.substring(j, k);
            if (j == k || t.charAt(0) == '0' || Integer.parseInt(t) == 0) {
                return false;
            }
            i += Integer.parseInt(t);
            j = k;
        }
        return i == m && j == n;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    bool validWordAbbreviation(string word, string abbr) {
        int i = 0, j = 0;
        int m = word.size(), n = abbr.size();
        while (i < m) {
            if (j >= n) {
                return false;
            }
            if (word[i] == abbr[j]) {
                ++i;
                ++j;
                continue;
            }
            int k = j;
            while (k < n && isdigit(abbr[k])) {
                ++k;
            }
            string t = abbr.substr(j, k - j);
            if (k == j || t[0] == '0') {
                return false;
            }
            int x = stoi(t);
            if (x == 0) {
                return false;
            }
            i += x;
            j = k;
        }
        return i == m && j == n;
    }
};
```

### **Go**

```go
func validWordAbbreviation(word string, abbr string) bool {
	i, j := 0, 0
	m, n := len(word), len(abbr)
	for i < m {
		if j >= n {
			return false
		}
		if word[i] == abbr[j] {
			i++
			j++
			continue
		}
		k := j
		for k < n && abbr[k] >= '0' && abbr[k] <= '9' {
			k++
		}
		if k == j || abbr[j] == '0' {
			return false
		}
		x, _ := strconv.Atoi(abbr[j:k])
		if x == 0 {
			return false
		}
		i += x
		j = k
	}
	return i == m && j == n
}
```

### **...**

```

```

<!-- tabs:end -->
