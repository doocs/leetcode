# [796. 旋转字符串](https://leetcode-cn.com/problems/rotate-string)

[English Version](/solution/0700-0799/0796.Rotate%20String/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定两个字符串, <code>A</code>&nbsp;和&nbsp;<code>B</code>。</p>

<p><code>A</code>&nbsp;的旋转操作就是将&nbsp;<code>A</code> 最左边的字符移动到最右边。&nbsp;例如, 若&nbsp;<code>A = &#39;abcde&#39;</code>，在移动一次之后结果就是<code>&#39;bcdea&#39;</code>&nbsp;。如果在若干次旋转操作之后，<code>A</code>&nbsp;能变成<code>B</code>，那么返回<code>True</code>。</p>

<pre>
<strong>示例 1:</strong>
<strong>输入:</strong> A = &#39;abcde&#39;, B = &#39;cdeab&#39;
<strong>输出:</strong> true

<strong>示例 2:</strong>
<strong>输入:</strong> A = &#39;abcde&#39;, B = &#39;abced&#39;
<strong>输出:</strong> false</pre>

<p><strong>注意：</strong></p>

<ul>
	<li><code>A</code> 和&nbsp;<code>B</code>&nbsp;长度不超过&nbsp;<code>100</code>。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def rotateString(self, s: str, goal: str) -> bool:
        if len(s) != len(goal):
            return False
        return goal in s + s
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public boolean rotateString(String s, String goal) {
        if (s.length() != goal.length()) {
            return false;
        }
        return (s + s).contains(goal);
    }
}
```

### **C++**

```cpp
class Solution {
public:
    bool rotateString(string s, string goal) {
        if (s.size() != goal.size()) {
            return false;
        }
        return !!strstr((s + s).data(), goal.data());
    }
};
```

### **Go**

```go
func rotateString(s string, goal string) bool {
	if len(s) != len(goal) {
		return false
	}
	return strings.Contains(s+s, goal)
}
```

### **...**

```

```

<!-- tabs:end -->
