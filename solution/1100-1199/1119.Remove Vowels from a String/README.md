# [1119. 删去字符串中的元音 🔒](https://leetcode.cn/problems/remove-vowels-from-a-string)

[English Version](/solution/1100-1199/1119.Remove%20Vowels%20from%20a%20String/README_EN.md)

<!-- tags:字符串 -->

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个字符串 <code>s</code>&nbsp;，请你删去其中的所有元音字母&nbsp;<code>'a'</code>，<code>'e'</code>，<code>'i'</code>，<code>'o'</code>，<code>'u'</code>，并返回这个新字符串。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>s = "leetcodeisacommunityforcoders"
<strong>输出：</strong>"ltcdscmmntyfrcdrs"
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>s = "aeiou"
<strong>输出：</strong>""
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= S.length &lt;= 1000</code></li>
	<li><code>s</code>&nbsp;仅由小写英文字母组成</li>
</ul>

## 解法

### 方法一：模拟

我们直接按照题目要求，遍历字符串，将不是元音字母的字符拼接到结果字符串中即可。

时间复杂度 $O(n)$，其中 $n$ 为字符串的长度。忽略答案字符串的空间消耗，空间复杂度 $O(1)$。

<!-- tabs:start -->

```python
class Solution:
    def removeVowels(self, s: str) -> str:
        return "".join(c for c in s if c not in "aeiou")
```

```java
class Solution {
    public String removeVowels(String s) {
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < s.length(); ++i) {
            char c = s.charAt(i);
            if (!(c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u')) {
                ans.append(c);
            }
        }
        return ans.toString();
    }
}
```

```cpp
class Solution {
public:
    string removeVowels(string s) {
        string ans;
        for (char& c : s) {
            if (!(c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u')) {
                ans += c;
            }
        }
        return ans;
    }
};
```

```go
func removeVowels(s string) string {
	ans := []rune{}
	for _, c := range s {
		if !(c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
			ans = append(ans, c)
		}
	}
	return string(ans)
}
```

```ts
function removeVowels(s: string): string {
    return s.replace(/[aeiou]/g, '');
}
```

<!-- tabs:end -->

<!-- end -->
