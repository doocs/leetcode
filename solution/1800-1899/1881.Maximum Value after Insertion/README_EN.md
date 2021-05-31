# [1881. Maximum Value after Insertion](https://leetcode.com/problems/maximum-value-after-insertion)

[中文文档](/solution/1800-1899/1881.Maximum%20Value%20after%20Insertion/README.md)

## Description

<p>You are given a very large integer <code>n</code>, represented as a string,​​​​​​ and an integer digit <code>x</code>. The digits in <code>n</code> and the digit <code>x</code> are in the <strong>inclusive</strong> range <code>[1, 9]</code>, and <code>n</code> may represent a <b>negative</b> number.</p>

<p>You want to <strong>maximize </strong><code>n</code><strong>&#39;s numerical value</strong> by inserting <code>x</code> anywhere in the decimal representation of <code>n</code>​​​​​​. You <strong>cannot</strong> insert <code>x</code> to the left of the negative sign.</p>

<ul>
	<li>For example, if <code>n = 73</code> and <code>x = 6</code>, it would be best to insert it between <code>7</code> and <code>3</code>, making <code>n = 763</code>.</li>
	<li>If <code>n = -55</code> and <code>x = 2</code>, it would be best to insert it before the first <code>5</code>, making <code>n = -255</code>.</li>
</ul>

<p>Return <em>a string representing the <strong>maximum</strong> value of </em><code>n</code><em>​​​​​​ after the insertion</em>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> n = &quot;99&quot;, x = 9
<strong>Output:</strong> &quot;999&quot;
<strong>Explanation:</strong> The result is the same regardless of where you insert 9.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = &quot;-13&quot;, x = 2
<strong>Output:</strong> &quot;-123&quot;
<strong>Explanation:</strong> You can make n one of {-213, -123, -132}, and the largest of those three is -123.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= x &lt;= 9</code></li>
	<li>The digits in <code>n</code>​​​ are in the range <code>[1, 9]</code>.</li>
	<li><code>n</code> is a valid representation of an integer.</li>
	<li>In the case of a negative <code>n</code>,​​​​​​ it will begin with <code>&#39;-&#39;</code>.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def maxValue(self, n: str, x: int) -> str:
        negative = n[0] == '-'
        i, res = 0, []
        if negative:
            i += 1
            res.append('-')
        find = False
        while i < len(n):
            num = int(n[i])
            if (negative and x < num) or (not negative and x > num):
                res.append(str(x))
                find = True
                break
            res.append(n[i])
            i += 1
        res.append(n[i:] if find else str(x))
        return ''.join(res)
```

### **Java**

```java
class Solution {
    public String maxValue(String n, int x) {
        boolean negative = n.charAt(0) == '-';
        StringBuilder res = new StringBuilder();
        int i = 0;
        if (negative) {
            ++i;
            res.append("-");
        }
        boolean find = false;
        for (; i < n.length(); ++i) {
            int num = n.charAt(i) - '0';
            if ((negative && x < num) || (!negative && x > num)) {
                res.append(x);
                find = true;
                break;
            }
            res.append(n.charAt(i));
        }
        res.append(find ? n.substring(i) : x);
        return res.toString();
    }
}
```

### **JavaScript**

```js
/**
 * @param {string} n
 * @param {number} x
 * @return {string}
 */
 var maxValue = function(n, x) {
    let nums = [...n];
    let sign = 1, i = 0;
    if (nums[0] == '-') {
        sign = -1;
        i++;
    }
    while (i < n.length && (nums[i] - x) * sign >= 0) {
        i++;
    }
    nums.splice(i, 0, x);
    return nums.join('');
};
```

### **...**

```

```

<!-- tabs:end -->
