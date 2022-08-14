# [402. Remove K Digits](https://leetcode.com/problems/remove-k-digits)

[中文文档](/solution/0400-0499/0402.Remove%20K%20Digits/README.md)

## Description

<p>Given string num representing a non-negative integer <code>num</code>, and an integer <code>k</code>, return <em>the smallest possible integer after removing</em> <code>k</code> <em>digits from</em> <code>num</code>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> num = &quot;1432219&quot;, k = 3
<strong>Output:</strong> &quot;1219&quot;
<strong>Explanation:</strong> Remove the three digits 4, 3, and 2 to form the new number 1219 which is the smallest.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> num = &quot;10200&quot;, k = 1
<strong>Output:</strong> &quot;200&quot;
<strong>Explanation:</strong> Remove the leading 1 and the number is 200. Note that the output must not contain leading zeroes.
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> num = &quot;10&quot;, k = 2
<strong>Output:</strong> &quot;0&quot;
<strong>Explanation:</strong> Remove all the digits from the number and it is left with nothing which is 0.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= k &lt;= num.length &lt;= 10<sup>5</sup></code></li>
	<li><code>num</code> consists of only digits.</li>
	<li><code>num</code> does not have any leading zeros except for the zero itself.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python

```

### **Java**

```java

```

### **TypeScript**

```ts
function removeKdigits(num: string, k: number): string {
    let nums = [...num];
    while (k > 0) {
        let idx = 0;
        while (idx < nums.length - 1 && nums[idx + 1] >= nums[idx]) {
            idx++;
        }
        nums.splice(idx, 1);
        k--;
    }
    return nums.join('').replace(/^0*/g, '') || '0';
}
```

### **...**

```

```

<!-- tabs:end -->
