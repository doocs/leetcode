# [1869. 哪种连续子字符串更长](https://leetcode-cn.com/problems/longer-contiguous-segments-of-ones-than-zeros)

[English Version](/solution/1800-1899/1869.Longer%20Contiguous%20Segments%20of%20Ones%20than%20Zeros/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个二进制字符串 <code>s</code> 。如果字符串中由 <code>1</code> 组成的 <strong>最长</strong> 连续子字符串 <strong>严格长于</strong> 由 <code>0</code> 组成的 <strong>最长</strong> 连续子字符串，返回 <code>true</code> ；否则，返回 <code>false</code><em> </em>。</p>

<ul>
	<li>例如，<code>s = "<strong>11</strong>01<strong>000</strong>10"</code> 中，由 <code>1</code> 组成的最长连续子字符串的长度是 <code>2</code> ，由 <code>0</code> 组成的最长连续子字符串的长度是 <code>3</code> 。</li>
</ul>

<p>注意，如果字符串中不存在 <code>0</code> ，此时认为由 <code>0</code> 组成的最长连续子字符串的长度是 <code>0</code> 。字符串中不存在 <code>1</code> 的情况也适用此规则。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>s = "1101"
<strong>输出：</strong>true
<strong>解释：</strong>
由 1 组成的最长连续子字符串的长度是 2："<strong>11</strong>01"
由 0 组成的最长连续子字符串的长度是 1："11<strong>0</strong>1"
由 1 组成的子字符串更长，故返回 true 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>s = "111000"
<strong>输出：</strong>false
<strong>解释：</strong>
由 1 组成的最长连续子字符串的长度是 3："<strong>111</strong>000"
由 0 组成的最长连续子字符串的长度是 3："111<strong>000</strong>"
由 1 组成的子字符串不比由 0 组成的子字符串长，故返回 false 。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>s = "110100010"
<strong>输出：</strong>false
<strong>解释：</strong>
由 1 组成的最长连续子字符串的长度是 2："<strong>11</strong>0100010"
由 0 组成的最长连续子字符串的长度是 3："1101<strong>000</strong>10"
由 1 组成的子字符串不比由 0 组成的子字符串长，故返回 false 。
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= s.length <= 100</code></li>
	<li><code>s[i]</code> 不是 <code>'0'</code> 就是 <code>'1'</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

直接遍历字符串，获取“0 子串”和“1 子串”的最大长度 `len0`、`len1`。

遍历结束后，若 `len1 > len0`，返回 true，否则返回 false。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def checkZeroOnes(self, s: str) -> bool:
        len0 = len1 = 0
        t0 = t1 = 0
        for c in s:
            if c == '0':
                t0 += 1
                t1 = 0
            else:
                t0 = 0
                t1 += 1
            len0 = max(len0, t0)
            len1 = max(len1, t1)
        return len1 > len0
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public boolean checkZeroOnes(String s) {
        int len0 = 0, len1 = 0;
        int t0 = 0, t1 = 0;
        for (int i = 0; i < s.length(); ++i) {
            if (s.charAt(i) == '0') {
                t0 += 1;
                t1 = 0;
            } else {
                t0 = 0;
                t1 += 1;
            }
            len0 = Math.max(len0, t0);
            len1 = Math.max(len1, t1);
        }
        return len1 > len0;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
