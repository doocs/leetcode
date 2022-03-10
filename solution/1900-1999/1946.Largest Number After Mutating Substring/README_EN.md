# [1946. Largest Number After Mutating Substring](https://leetcode.com/problems/largest-number-after-mutating-substring)

[中文文档](/solution/1900-1999/1946.Largest%20Number%20After%20Mutating%20Substring/README.md)

## Description

<p>You are given a string <code>num</code>, which represents a large integer. You are also given a <strong>0-indexed</strong> integer array <code>change</code> of length <code>10</code> that maps each digit <code>0-9</code> to another digit. More formally, digit <code>d</code> maps to digit <code>change[d]</code>.</p>

<p>You may <strong>choose</strong> to <b>mutate a single substring</b> of <code>num</code>. To mutate a substring, replace each digit <code>num[i]</code> with the digit it maps to in <code>change</code> (i.e. replace <code>num[i]</code> with <code>change[num[i]]</code>).</p>

<p>Return <em>a string representing the <strong>largest</strong> possible integer after <strong>mutating</strong> (or choosing not to) a <strong>single substring</strong> of </em><code>num</code>.</p>

<p>A <strong>substring</strong> is a contiguous sequence of characters within the string.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> num = &quot;<u>1</u>32&quot;, change = [9,8,5,0,3,6,4,2,6,8]
<strong>Output:</strong> &quot;<u>8</u>32&quot;
<strong>Explanation:</strong> Replace the substring &quot;1&quot;:
- 1 maps to change[1] = 8.
Thus, &quot;<u>1</u>32&quot; becomes &quot;<u>8</u>32&quot;.
&quot;832&quot; is the largest number that can be created, so return it.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> num = &quot;<u>021</u>&quot;, change = [9,4,3,5,7,2,1,9,0,6]
<strong>Output:</strong> &quot;<u>934</u>&quot;
<strong>Explanation:</strong> Replace the substring &quot;021&quot;:
- 0 maps to change[0] = 9.
- 2 maps to change[2] = 3.
- 1 maps to change[1] = 4.
Thus, &quot;<u>021</u>&quot; becomes &quot;<u>934</u>&quot;.
&quot;934&quot; is the largest number that can be created, so return it.
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> num = &quot;5&quot;, change = [1,4,7,5,3,2,5,6,9,4]
<strong>Output:</strong> &quot;5&quot;
<strong>Explanation:</strong> &quot;5&quot; is already the largest number that can be created, so return it.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= num.length &lt;= 10<sup>5</sup></code></li>
	<li><code>num</code> consists of only digits <code>0-9</code>.</li>
	<li><code>change.length == 10</code></li>
	<li><code>0 &lt;= change[d] &lt;= 9</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def maximumNumber(self, num: str, change: List[int]) -> str:
        find = False
        nums = list(num)
        for i, c in enumerate(num):
            if int(c) < change[int(c)]:
                nums[i] = str(change[int(c)])
                find = True
            elif find and int(c) == change[int(c)]:
                continue
            elif find:
                break
        return ''.join(nums)
```

### **Java**

```java
class Solution {
    public String maximumNumber(String num, int[] change) {
        boolean find = false;
        char[] nums = num.toCharArray();
        for (int i = 0; i < num.length(); ++i) {
            int c = num.charAt(i) - '0';
            if (c < change[c]) {
                nums[i] = (char) ('0' + change[c]);
                find = true;
            } else if (find && c == change[c]) {
                continue;
            } else if (find) {
                break;
            }
        }
        return new String(nums);
    }
}
```

### **...**

```

```

<!-- tabs:end -->
