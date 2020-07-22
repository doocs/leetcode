# [05.07. Exchange](https://leetcode-cn.com/problems/exchange-lcci)

[中文文档](/lcci/05.07.Exchange/README.md)

## Description
<p>Write a program to swap odd and even bits in an integer with as few instructions as possible (e.g., bit 0 and bit 1 are swapped, bit 2 and bit 3 are swapped, and so on).</p>



<p><strong>Example1:</strong></p>



<pre>

<strong> Input</strong>: num = 2（0b10）

<strong> Output</strong> 1 (0b01)

</pre>



<p><strong>Example2:</strong></p>



<pre>

<strong> Input</strong>: num = 3

<strong> Output</strong>: 3

</pre>



<p><strong>Note:</strong></p>



<ol>
	<li><code>0 &lt;= num &lt;=</code>&nbsp;2^30 - 1</li>
	<li>The result integer fits into 32-bit integer.</li>
</ol>




## Solutions


<!-- tabs:start -->

### **Python3**

```python

```

### **Java**

```java
class Solution {
    public int exchangeBits(int num) {
        int t1 = num >> 1;
    	int t2 = num << 1;
    	return t1 & 0x55555555 | t2 & 0xaaaaaaaa;
    }
}
```

### **...**
```

```

<!-- tabs:end -->