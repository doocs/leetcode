# [316. 去除重复字母](https://leetcode.cn/problems/remove-duplicate-letters)

[English Version](/solution/0300-0399/0316.Remove%20Duplicate%20Letters/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个字符串 <code>s</code> ，请你去除字符串中重复的字母，使得每个字母只出现一次。需保证 <strong>返回结果的字典序最小</strong>（要求不能打乱其他字符的相对位置）。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong><code>s = "bcabc"</code>
<strong>输出<code>：</code></strong><code>"abc"</code>
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong><code>s = "cbacdcbc"</code>
<strong>输出：</strong><code>"acdb"</code></pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>4</sup></code></li>
	<li><code>s</code> 由小写英文字母组成</li>
</ul>

<p>&nbsp;</p>

<p><strong>注意：</strong>该题与 1081 <a href="https://leetcode.cn/problems/smallest-subsequence-of-distinct-characters">https://leetcode.cn/problems/smallest-subsequence-of-distinct-characters</a> 相同</p>

## 解法

<!-- 这里可写通用的实现逻辑 -->
**单调栈**
<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def removeDuplicateLetters(self, s: str) -> str:
        count, in_stack = [0 for _ in range(128)], [False for _ in range(128)]
        stack = []
        for c in s:
            count[ord(c)] += 1
        
        for c in s:
            count[ord(c)] -= 1
            if in_stack[ord(c)]:
                continue
            while len(stack) > 0 and stack[len(stack)-1] > c:
                peek = stack[len(stack)-1]
                if count[ord(peek)] < 1:
                  break
                in_stack[ord(peek)] = False
                stack.pop()
            stack.append(c)
            in_stack[ord(c)] = True
        return ''.join(stack)
```

### **Go**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```go
func removeDuplicateLetters(s string) string {
	count, in_stack, stack := make([]int, 128), make([]bool, 128), make([]rune, 0)
	for _, c := range s {
		count[c] += 1
	}

	for _, c := range s {
		count[c] -= 1
		if in_stack[c] {
			continue
		}
		for len(stack) > 0 && stack[len(stack)-1] > c && count[stack[len(stack)-1]] > 0 {
			peek := stack[len(stack)-1]
			stack = stack[0 : len(stack)-1]
			in_stack[peek] = false
		}
		stack = append(stack, c)
		in_stack[c] = true
	}
	return string(stack)
}
```

### **...**

```

```

<!-- tabs:end -->
