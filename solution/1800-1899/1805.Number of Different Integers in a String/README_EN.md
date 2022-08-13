# [1805. Number of Different Integers in a String](https://leetcode.com/problems/number-of-different-integers-in-a-string)

[中文文档](/solution/1800-1899/1805.Number%20of%20Different%20Integers%20in%20a%20String/README.md)

## Description

<p>You are given a string <code>word</code> that consists of digits and lowercase English letters.</p>

<p>You will replace every non-digit character with a space. For example, <code>&quot;a123bc34d8ef34&quot;</code> will become <code>&quot; 123&nbsp; 34 8&nbsp; 34&quot;</code>. Notice that you are left with some integers that are separated by at least one space: <code>&quot;123&quot;</code>, <code>&quot;34&quot;</code>, <code>&quot;8&quot;</code>, and <code>&quot;34&quot;</code>.</p>

<p>Return <em>the number of <strong>different</strong> integers after performing the replacement operations on </em><code>word</code>.</p>

<p>Two integers are considered different if their decimal representations <strong>without any leading zeros</strong> are different.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> word = &quot;a<u>123</u>bc<u>34</u>d<u>8</u>ef<u>34</u>&quot;
<strong>Output:</strong> 3
<strong>Explanation: </strong>The three different integers are &quot;123&quot;, &quot;34&quot;, and &quot;8&quot;. Notice that &quot;34&quot; is only counted once.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> word = &quot;leet<u>1234</u>code<u>234</u>&quot;
<strong>Output:</strong> 2
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> word = &quot;a<u>1</u>b<u>01</u>c<u>001</u>&quot;
<strong>Output:</strong> 1
<strong>Explanation: </strong>The three integers &quot;1&quot;, &quot;01&quot;, and &quot;001&quot; all represent the same integer because
the leading zeros are ignored when comparing their decimal values.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= word.length &lt;= 1000</code></li>
	<li><code>word</code> consists of digits and lowercase English letters.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
import re


class Solution:
    def numDifferentIntegers(self, word: str) -> int:
        nums = re.split(r'[a-z]+', word)
        return len({int(num) for num in nums if num != ''})
```

### **Java**

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
