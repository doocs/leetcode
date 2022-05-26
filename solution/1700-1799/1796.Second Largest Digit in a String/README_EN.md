# [1796. Second Largest Digit in a String](https://leetcode.com/problems/second-largest-digit-in-a-string)

[中文文档](/solution/1700-1799/1796.Second%20Largest%20Digit%20in%20a%20String/README.md)

## Description

<p>Given an alphanumeric string <code>s</code>, return <em>the <strong>second largest</strong> numerical digit that appears in </em><code>s</code><em>, or </em><code>-1</code><em> if it does not exist</em>.</p>

<p>An <strong>alphanumeric</strong><strong> </strong>string is a string consisting of lowercase English letters and digits.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;dfa12321afd&quot;
<strong>Output:</strong> 2
<strong>Explanation:</strong> The digits that appear in s are [1, 2, 3]. The second largest digit is 2.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;abc1111&quot;
<strong>Output:</strong> -1
<strong>Explanation:</strong> The digits that appear in s are [1]. There is no second largest digit. 
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 500</code></li>
	<li><code>s</code> consists of only lowercase English letters and/or digits.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def secondHighest(self, s: str) -> int:
        largest_digit = second_largest_digit = -1
        for c in s:
            if c.isdigit():
                num = int(c)
                if num > largest_digit:
                    second_largest_digit, largest_digit = largest_digit, num
                elif num > second_largest_digit and num < largest_digit:
                    second_largest_digit = num
        return second_largest_digit
```

### **Java**

```java
class Solution {
    public int secondHighest(String s) {
        int largestDigit = -1, secondLargestDigit = -1;
        for (int i = 0; i < s.length(); ++i) {
            char c = s.charAt(i);
            if (c >= '0' && c <= '9') {
                int num = c - '0';
                if (num > largestDigit) {
                    secondLargestDigit = largestDigit;
                    largestDigit = num;
                } else if (num > secondLargestDigit && num < largestDigit) {
                    secondLargestDigit = num;
                }
            }
        }
        return secondLargestDigit;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
