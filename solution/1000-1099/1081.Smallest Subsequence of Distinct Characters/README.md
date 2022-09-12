# [1081. 不同字符的最小子序列](https://leetcode.cn/problems/smallest-subsequence-of-distinct-characters)

[English Version](/solution/1000-1099/1081.Smallest%20Subsequence%20of%20Distinct%20Characters/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>返回 <code>s</code> 字典序最小的子序列，该子序列包含 <code>s</code> 的所有不同字符，且只包含一次。</p>

<p><strong>注意：</strong>该题与 316 <a href="https://leetcode.com/problems/remove-duplicate-letters/">https://leetcode.com/problems/remove-duplicate-letters/</a> 相同</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong><code>s = "bcabc"</code>
<strong>输出<code>：</code></strong><code>"abc"</code>
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong><code>s = "cbacdcbc"</code>
<strong>输出：</strong><code>"acdb"</code></pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= s.length <= 1000</code></li>
	<li><code>s</code> 由小写英文字母组成</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->
**单调栈**
<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def smallestSubsequence(self, s: str) -> str:
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
func smallestSubsequence(s string) string {
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
