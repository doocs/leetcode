# [151. 翻转字符串里的单词](https://leetcode-cn.com/problems/reverse-words-in-a-string)

[English Version](/solution/0100-0199/0151.Reverse%20Words%20in%20a%20String/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个字符串 <code>s</code> ，逐个翻转字符串中的所有 <strong>单词</strong> 。</p>

<p><strong>单词</strong> 是由非空格字符组成的字符串。<code>s</code> 中使用至少一个空格将字符串中的 <strong>单词</strong> 分隔开。</p>

<p>请你返回一个翻转 <code>s</code> 中单词顺序并用单个空格相连的字符串。</p>

<p><strong>说明：</strong></p>

<ul>
	<li>输入字符串 <code>s</code> 可以在前面、后面或者单词间包含多余的空格。</li>
	<li>翻转后单词间应当仅用一个空格分隔。</li>
	<li>翻转后的字符串中不应包含额外的空格。</li>
</ul>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>s = "<code>the sky is blue</code>"
<strong>输出：</strong>"<code>blue is sky the</code>"
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>s = "  hello world  "
<strong>输出：</strong>"world hello"
<strong>解释：</strong>输入字符串可以在前面或者后面包含多余的空格，但是翻转后的字符不能包括。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>s = "a good   example"
<strong>输出：</strong>"example good a"
<strong>解释：</strong>如果两个单词间有多余的空格，将翻转后单词间的空格减少到只含一个。
</pre>

<p><strong>示例 4：</strong></p>

<pre>
<strong>输入：</strong>s = "  Bob    Loves  Alice   "
<strong>输出：</strong>"Alice Loves Bob"
</pre>

<p><strong>示例 5：</strong></p>

<pre>
<strong>输入：</strong>s = "Alice does not even like bob"
<strong>输出：</strong>"bob like even not does Alice"
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= s.length <= 10<sup>4</sup></code></li>
	<li><code>s</code> 包含英文大小写字母、数字和空格 <code>' '</code></li>
	<li><code>s</code> 中 <strong>至少存在一个</strong> 单词</li>
</ul>

<ul>
</ul>

<p> </p>

<p><strong>进阶：</strong></p>

<ul>
	<li>请尝试使用 <code><em>O</em>(1)</code> 额外空间复杂度的原地解法。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def reverseWords(self, s: str) -> str:
        words = s.strip().split()
        return ' '.join(words[::-1])
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public String reverseWords(String s) {
        List<String> words = Arrays.asList(s.trim().split("\\s+"));
        Collections.reverse(words);
        return String.join(" ", words);
    }
}
```

### **C#**

```cs
public class Solution {
    public string ReverseWords(string s) {
         return string.Join(" ", s.Trim().Split(" ").Where(word => !string.IsNullOrEmpty(word) && !string.IsNullOrEmpty(word.Trim())).Reverse());
    }
}
```

### **TypeScript**

```ts
function reverseWords(s: string): string {
    let words: string[] = s.trim().split(/\s+/g);
    words.reverse();
    return words.join(' ');
}
```

### **...**

```

```

<!-- tabs:end -->
