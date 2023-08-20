# [2828. 判别首字母缩略词](https://leetcode.cn/problems/check-if-a-string-is-an-acronym-of-words)

[English Version](/solution/2800-2899/2828.Check%20if%20a%20String%20Is%20an%20Acronym%20of%20Words/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个字符串数组&nbsp;<code>words</code> 和一个字符串 <code>s</code> ，请你判断 <code>s</code> 是不是 <code>words</code> 的 <strong>首字母缩略词</strong> 。</p>

<p>如果可以按顺序串联 <code>words</code> 中每个字符串的第一个字符形成字符串 <code>s</code> ，则认为 <code>s</code> 是 <code>words</code> 的首字母缩略词。例如，<code>"ab"</code> 可以由 <code>["apple", "banana"]</code> 形成，但是无法从 <code>["bear", "aardvark"]</code> 形成。</p>

<p>如果 <code>s</code> 是 <code>words</code> 的首字母缩略词，返回 <code>true</code><em> </em>；否则，返回<em> </em><code>false</code> 。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<strong>输入：</strong>words = ["alice","bob","charlie"], s = "abc"
<strong>输出：</strong>true
<strong>解释：</strong>words 中 "alice"、"bob" 和 "charlie" 的第一个字符分别是 'a'、'b' 和 'c'。因此，s = "abc" 是首字母缩略词。 
</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<strong>输入：</strong>words = ["an","apple"], s = "a"
<strong>输出：</strong>false
<strong>解释：</strong>words 中 "an" 和 "apple" 的第一个字符分别是 'a' 和 'a'。
串联这些字符形成的首字母缩略词是 "aa" 。
因此，s = "a" 不是首字母缩略词。
</pre>

<p><strong class="example">示例 3：</strong></p>

<pre>
<strong>输入：</strong>words = ["never","gonna","give","up","on","you"], s = "ngguoy"
<strong>输出：</strong>true
<strong>解释：</strong>串联数组 words 中每个字符串的第一个字符，得到字符串 "ngguoy" 。
因此，s = "ngguoy" 是首字母缩略词。 
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= words.length &lt;= 100</code></li>
	<li><code>1 &lt;= words[i].length &lt;= 10</code></li>
	<li><code>1 &lt;= s.length &lt;= 100</code></li>
	<li><code>words[i]</code> 和 <code>s</code> 由小写英文字母组成</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：模拟**

我们可以遍历数组 $words$ 中的每个字符串，将其首字母拼接起来，得到一个新的字符串 $t$，然后判断 $t$ 是否等于 $s$ 即可。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 是数组 $words$ 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def isAcronym(self, words: List[str], s: str) -> bool:
        return "".join(w[0] for w in words) == s
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public boolean isAcronym(List<String> words, String s) {
        StringBuilder t = new StringBuilder();
        for (var w : words) {
            t.append(w.charAt(0));
        }
        return t.toString().equals(s);
    }
}
```

### **C++**

```cpp
class Solution {
public:
    bool isAcronym(vector<string>& words, string s) {
        string t;
        for (auto& w : words) {
            t += w[0];
        }
        return t == s;
    }
};
```

### **Go**

```go
func isAcronym(words []string, s string) bool {
	t := []byte{}
	for _, w := range words {
		t = append(t, w[0])
	}
	return string(t) == s
}
```

### **TypeScript**

```ts
function isAcronym(words: string[], s: string): boolean {
    return words.map(w => w[0]).join('') === s;
}
```

### **...**

```

```

<!-- tabs:end -->
