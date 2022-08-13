# [1805. 字符串中不同整数的数目](https://leetcode.cn/problems/number-of-different-integers-in-a-string)

[English Version](/solution/1800-1899/1805.Number%20of%20Different%20Integers%20in%20a%20String/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个字符串 <code>word</code> ，该字符串由数字和小写英文字母组成。</p>

<p>请你用空格替换每个不是数字的字符。例如，<code>"a123bc34d8ef34"</code> 将会变成 <code>" 123  34 8  34"</code> 。注意，剩下的这些整数为（相邻彼此至少有一个空格隔开）：<code>"123"</code>、<code>"34"</code>、<code>"8"</code> 和 <code>"34"</code> 。</p>

<p>返回对 <code>word</code> 完成替换后形成的 <strong>不同</strong> 整数的数目。</p>

<p>只有当两个整数的 <strong>不含前导零</strong> 的十进制表示不同， 才认为这两个整数也不同。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>word = "a<strong>123</strong>bc<strong>34</strong>d<strong>8</strong>ef<strong>34</strong>"
<strong>输出：</strong>3
<strong>解释：</strong>不同的整数有 "123"、"34" 和 "8" 。注意，"34" 只计数一次。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>word = "leet<strong>1234</strong>code<strong>234</strong>"
<strong>输出：</strong>2
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>word = "a<strong>1</strong>b<strong>01</strong>c<strong>001</strong>"
<strong>输出：</strong>1
<strong>解释：</strong>"1"、"01" 和 "001" 视为同一个整数的十进制表示，因为在比较十进制值时会忽略前导零的存在。
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= word.length <= 1000</code></li>
	<li><code>word</code> 由数字和小写英文字母组成</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

将 `word` 按照字母切分，得到数字数组 `nums`，然后利用 set 去重，返回 set 的长度即可。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
import re


class Solution:
    def numDifferentIntegers(self, word: str) -> int:
        nums = re.split(r'[a-z]+', word)
        return len({int(num) for num in nums if num != ''})
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int numDifferentIntegers(String word) {
        String[] nums = word.split("[a-z]+");
        Set<String> numSet = new HashSet<>();
        for (String num : nums) {
            if ("".equals(num)) {
                continue;
            }
            int j = 0;
            while (j < num.length() - 1 && num.charAt(j) == '0') {
                ++j;
            }
            numSet.add(num.substring(j));
        }
        return numSet.size();
    }
}
```

### **...**

```

```

<!-- tabs:end -->
