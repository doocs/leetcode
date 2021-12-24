# [151. 翻转字符串里的单词](https://leetcode-cn.com/problems/reverse-words-in-a-string)

[English Version](/solution/0100-0199/0151.Reverse%20Words%20in%20a%20String/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个字符串，逐个翻转字符串中的每个单词。</p>

<p><strong>说明：</strong></p>

<ul>
	<li>无空格字符构成一个 <strong>单词</strong> 。</li>
	<li>输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。</li>
	<li>如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。</li>
</ul>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>&quot;<code>the sky is blue</code>&quot;
<strong>输出：</strong>&quot;<code>blue is sky the</code>&quot;
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>&quot; &nbsp;hello world! &nbsp;&quot;
<strong>输出：</strong>&quot;world! hello&quot;
<strong>解释：</strong>输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>&quot;a good &nbsp; example&quot;
<strong>输出：</strong>&quot;example good a&quot;
<strong>解释：</strong>如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。
</pre>

<p><strong>示例 4：</strong></p>

<pre><strong>输入：</strong>s = &quot;  Bob    Loves  Alice   &quot;
<strong>输出：</strong>&quot;Alice Loves Bob&quot;
</pre>

<p><strong>示例 5：</strong></p>

<pre><strong>输入：</strong>s = &quot;Alice does not even like bob&quot;
<strong>输出：</strong>&quot;bob like even not does Alice&quot;
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>4</sup></code></li>
	<li><code>s</code> 包含英文大小写字母、数字和空格 <code>&#39; &#39;</code></li>
	<li><code>s</code> 中 <strong>至少存在一个</strong> 单词</li>
</ul>

<ul>
</ul>

<p>&nbsp;</p>

<p><strong>进阶：</strong></p>

<ul>
	<li>请尝试使用&nbsp;<em>O</em>(1) 额外空间复杂度的原地解法。</li>
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
    return words.join(" ");
}
```

### **...**

```

```

<!-- tabs:end -->
