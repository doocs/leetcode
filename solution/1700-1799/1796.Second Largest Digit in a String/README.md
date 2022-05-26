# [1796. 字符串中第二大的数字](https://leetcode.cn/problems/second-largest-digit-in-a-string)

[English Version](/solution/1700-1799/1796.Second%20Largest%20Digit%20in%20a%20String/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个混合字符串 <code>s</code> ，请你返回 <code>s</code> 中 <strong>第二大 </strong>的数字，如果不存在第二大的数字，请你返回 <code>-1</code> 。</p>

<p><strong>混合字符串 </strong>由小写英文字母和数字组成。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<b>输入：</b>s = "dfa12321afd"
<b>输出：</b>2
<b>解释：</b>出现在 s 中的数字包括 [1, 2, 3] 。第二大的数字是 2 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<b>输入：</b>s = "abc1111"
<b>输出：</b>-1
<b>解释：</b>出现在 s 中的数字只包含 [1] 。没有第二大的数字。
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= s.length <= 500</code></li>
	<li><code>s</code> 只包含小写英文字母和（或）数字。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

假设字符串最大的数为 `largestDigit`，第二大的数为 `secondLargestDigit`，初始化均为 -1。

遍历字符串，判断当前字符是否为数字型字符。若是，先转为数字 `num`。然后判断数字与 `largestDigit`、`secondLargestDigit` 的大小关系：

-   若 `num > largestDigit`，将 `secondLargestDigit` 更新为 `largestDigit`，而 `largestDigit` 更新为 num；
-   若 `num > secondLargestDigit`，并且 `num < largestDigit`，将 `secondLargestDigit` 更新为 num；
-   其他情况不做处理。

最后返回 `secondLargestDigit` 即可。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
