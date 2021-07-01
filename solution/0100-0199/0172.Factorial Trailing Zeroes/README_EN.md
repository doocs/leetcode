# [172. Factorial Trailing Zeroes](https://leetcode.com/problems/factorial-trailing-zeroes)

[中文文档](/solution/0100-0199/0172.Factorial%20Trailing%20Zeroes/README.md)

## Description

<p>Given an integer <code>n</code>, return <em>the number of trailing zeroes in <code>n!</code></em>.</p>

<p><b>Follow up: </b>Could you write a&nbsp;solution that works in logarithmic time complexity?</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> n = 3
<strong>Output:</strong> 0
<strong>Explanation:</strong>&nbsp;3! = 6, no trailing zero.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 5
<strong>Output:</strong> 1
<strong>Explanation:</strong>&nbsp;5! = 120, one trailing zero.
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> n = 0
<strong>Output:</strong> 0
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>0 &lt;= n &lt;= 10<sup>4</sup></code></li>
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
function trailingZeroes(n: number): number {
    let count = 0;
    while (n > 0) {
        n = Math.floor(n / 5);
        count += n;
    }
    return count;
};
```

### **...**

```

```

<!-- tabs:end -->
