# [408. 有效单词缩写 🔒](https://leetcode.cn/problems/valid-word-abbreviation)

[English Version](/solution/0400-0499/0408.Valid%20Word%20Abbreviation/README_EN.md)

<!-- tags:双指针,字符串 -->

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

### 方法一：模拟

我们可以直接模拟字符匹配替换。

假设字符串 $word$ 和字符串 $abbr$ 的长度分别为 $m$ 和 $n$，我们使用两个指针 $i$ 和 $j$ 分别指向字符串 $word$ 和字符串 $abbr$ 的初始位置，用一个整型变量 $x$ 记录当前匹配到的 $abbr$ 的数字。

循环匹配字符串 $word$ 和字符串 $abbr$ 的每个字符：

如果指针 $j$ 指向的字符 $abbr[j]$ 是数字，如果 $abbr[j]$ 是 `'0'`，并且 $x$ 为 $0$，说明 $abbr$ 中的数字含有前导零，因此不是合法的缩写，返回 `false`；否则将 $x$ 更新为 $x \times 10 + abbr[j] - '0'$。

如果指针 $j$ 指向的字符 $abbr[j]$ 不是数字，那么我们此时将指针 $i$ 往前移动 $x$ 个位置，然后将 $x$ 重置为 $0$。如果此时 $i \geq m$ 或者 $word[i] \neq abbr[j]$，说明两个字符串无法匹配，返回 `false`；否则将指针 $i$ 往前移动 $1$ 个位置。

然后我们将指针 $j$ 往前移动 $1$ 个位置，重复上述过程，直到 $i$ 超出字符串 $word$ 的长度或者 $j$ 超出字符串 $abbr$ 的长度。

最后，如果 $i + x$ 等于 $m$ 且 $j$ 等于 $n$，说明字符串 $word$ 可以缩写成字符串 $abbr$，返回 `true`；否则返回 `false`。

时间复杂度 $O(m + n)$，其中 $m$ 和 $n$ 分别是字符串 $word$ 和字符串 $abbr$ 的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

```python
class Solution:
    def validWordAbbreviation(self, word: str, abbr: str) -> bool:
        m, n = len(word), len(abbr)
        i = j = x = 0
        while i < m and j < n:
            if abbr[j].isdigit():
                if abbr[j] == "0" and x == 0:
                    return False
                x = x * 10 + int(abbr[j])
            else:
                i += x
                x = 0
                if i >= m or word[i] != abbr[j]:
                    return False
                i += 1
            j += 1
        return i + x == m and j == n
```

```java
class Solution {
    public boolean validWordAbbreviation(String word, String abbr) {
        int m = word.length(), n = abbr.length();
        int i = 0, j = 0, x = 0;
        for (; i < m && j < n; ++j) {
            char c = abbr.charAt(j);
            if (Character.isDigit(c)) {
                if (c == '0' && x == 0) {
                    return false;
                }
                x = x * 10 + (c - '0');
            } else {
                i += x;
                x = 0;
                if (i >= m || word.charAt(i) != c) {
                    return false;
                }
                ++i;
            }
        }
        return i + x == m && j == n;
    }
}
```

```cpp
class Solution {
public:
    bool validWordAbbreviation(string word, string abbr) {
        int m = word.size(), n = abbr.size();
        int i = 0, j = 0, x = 0;
        for (; i < m && j < n; ++j) {
            if (isdigit(abbr[j])) {
                if (abbr[j] == '0' && x == 0) {
                    return false;
                }
                x = x * 10 + (abbr[j] - '0');
            } else {
                i += x;
                x = 0;
                if (i >= m || word[i] != abbr[j]) {
                    return false;
                }
                ++i;
            }
        }
        return i + x == m && j == n;
    }
};
```

```go
func validWordAbbreviation(word string, abbr string) bool {
	m, n := len(word), len(abbr)
	i, j, x := 0, 0, 0
	for ; i < m && j < n; j++ {
		if abbr[j] >= '0' && abbr[j] <= '9' {
			if x == 0 && abbr[j] == '0' {
				return false
			}
			x = x*10 + int(abbr[j]-'0')
		} else {
			i += x
			x = 0
			if i >= m || word[i] != abbr[j] {
				return false
			}
			i++
		}
	}
	return i+x == m && j == n
}
```

```ts
function validWordAbbreviation(word: string, abbr: string): boolean {
    const [m, n] = [word.length, abbr.length];
    let [i, j, x] = [0, 0, 0];
    for (; i < m && j < n; ++j) {
        if (abbr[j] >= '0' && abbr[j] <= '9') {
            if (abbr[j] === '0' && x === 0) {
                return false;
            }
            x = x * 10 + Number(abbr[j]);
        } else {
            i += x;
            x = 0;
            if (i >= m || word[i++] !== abbr[j]) {
                return false;
            }
        }
    }
    return i + x === m && j === n;
}
```

<!-- tabs:end -->

<!-- end -->
