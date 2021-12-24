# [9. 回文数](https://leetcode-cn.com/problems/palindrome-number)

[English Version](/solution/0000-0099/0009.Palindrome%20Number/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个整数 <code>x</code> ，如果 <code>x</code> 是一个回文整数，返回 <code>true</code> ；否则，返回 <code>false</code> 。</p>

<p>回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。例如，<code>121</code> 是回文，而 <code>123</code> 不是。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>x = 121
<strong>输出：</strong>true
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>x = -121
<strong>输出：</strong>false
<strong>解释：</strong>从左向右读, 为 -121 。 从右向左读, 为 121- 。因此它不是一个回文数。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>x = 10
<strong>输出：</strong>false
<strong>解释：</strong>从右向左读, 为 01 。因此它不是一个回文数。
</pre>

<p><strong>示例 4：</strong></p>

<pre>
<strong>输入：</strong>x = -101
<strong>输出：</strong>false
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>-2<sup>31</sup> <= x <= 2<sup>31</sup> - 1</code></li>
</ul>

<p> </p>

<p><strong>进阶：</strong>你能不将整数转为字符串来解决这个问题吗？</p>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def isPalindrome(self, x: int) -> bool:
        if x < 0:
            return False
        y, t = 0, x
        while t:
            y = y * 10 + t % 10
            t //= 10
        return x == y
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public boolean isPalindrome(int x) {
        if (x < 0) return false;
        int y = 0, t = x;
        while (t != 0) {
            y = y * 10 + t % 10;
            t /= 10;
        }
        return x == y;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
