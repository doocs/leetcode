# [8. String to Integer (atoi)](https://leetcode.com/problems/string-to-integer-atoi)

[中文文档](</solution/0000-0099/0008.String%20to%20Integer%20(atoi)/README.md>)

## Description

<p>Implement <code><span>atoi</span></code> which&nbsp;converts a string to an integer.</p>

<p>The function first discards as many whitespace characters as necessary until the first non-whitespace character is found. Then, starting from this character, takes an optional initial plus or minus sign followed by as many numerical digits as possible, and interprets them as a numerical value.</p>

<p>The string can contain additional characters after those that form the integral number, which are ignored and have no effect on the behavior of this function.</p>

<p>If the first sequence of non-whitespace characters in str is not a valid integral number, or if no such sequence exists because either str is empty or it contains only whitespace characters, no conversion is performed.</p>

<p>If no valid conversion could be performed, a zero value is returned.</p>

<p><strong>Note:</strong></p>

<ul>
    <li>Only the space character <code>&#39; &#39;</code> is considered as whitespace character.</li>
    <li>Assume we are dealing with an environment which could only store integers within the 32-bit signed integer range: [&minus;2<sup>31</sup>,&nbsp; 2<sup>31&nbsp;</sup>&minus; 1]. If the numerical value is out of the range of representable values, INT_MAX (2<sup>31&nbsp;</sup>&minus; 1) or INT_MIN (&minus;2<sup>31</sup>) is returned.</li>
</ul>

<p><strong>Example 1:</strong></p>

<pre>

<strong>Input:</strong> &quot;42&quot;

<strong>Output:</strong> 42

</pre>

<p><strong>Example 2:</strong></p>

<pre>

<strong>Input:</strong> &quot;   -42&quot;

<strong>Output:</strong> -42

<strong>Explanation:</strong> The first non-whitespace character is &#39;-&#39;, which is the minus sign.

&nbsp;            Then take as many numerical digits as possible, which gets 42.

</pre>

<p><strong>Example 3:</strong></p>

<pre>

<strong>Input:</strong> &quot;4193 with words&quot;

<strong>Output:</strong> 4193

<strong>Explanation:</strong> Conversion stops at digit &#39;3&#39; as the next character is not a numerical digit.

</pre>

<p><strong>Example 4:</strong></p>

<pre>

<strong>Input:</strong> &quot;words and 987&quot;

<strong>Output:</strong> 0

<strong>Explanation:</strong> The first non-whitespace character is &#39;w&#39;, which is not a numerical 

&nbsp;            digit or a +/- sign. Therefore no valid conversion could be performed.</pre>

<p><strong>Example 5:</strong></p>

<pre>

<strong>Input:</strong> &quot;-91283472332&quot;

<strong>Output:</strong> -2147483648

<strong>Explanation:</strong> The number &quot;-91283472332&quot; is out of the range of a 32-bit signed integer.

&nbsp;            Thefore INT_MIN (&minus;2<sup>31</sup>) is returned.</pre>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def myAtoi(self, s: str) -> int:
        if not s:
            return 0
        n = len(s)
        if n == 0:
            return 0
        i = 0
        while s[i] == ' ':
            i += 1
            # only contains blank space
            if i == n:
                return 0
        sign = -1 if s[i] == '-' else 1
        if s[i] in ['-', '+']:
            i += 1
        res, flag = 0, (2 ** 31 - 1) // 10
        while i < n:
            # not a number, exit the loop
            if not s[i].isdigit():
                break
            c = int(s[i])
            # if overflows
            if res > flag or (res == flag and c > 7):
                return 2 ** 31 - 1 if sign > 0 else -2 ** 31
            res = res * 10 + c
            i += 1
        return sign * res
```

### **Java**

```java
class Solution {
    public int myAtoi(String s) {
        if (s == null) return 0;
        int n = s.length();
        if (n == 0) return 0;
        int i = 0;
        while (s.charAt(i) == ' ') {
            // only contains blank space
            if (++i == n) return 0;
        }
        int sign = 1;
        if (s.charAt(i) == '-') sign = -1;
        if (s.charAt(i) == '-' || s.charAt(i) == '+') ++i;
        int res = 0, flag = Integer.MAX_VALUE / 10;
        for (; i < n; ++i) {
            // not a number, exit the loop
            if (s.charAt(i) < '0' || s.charAt(i) > '9') break;
            // if overflows
            if (res > flag || (res == flag && s.charAt(i) > '7')) return sign > 0 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            res = res * 10 + (s.charAt(i) - '0');
        }
        return sign * res;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
