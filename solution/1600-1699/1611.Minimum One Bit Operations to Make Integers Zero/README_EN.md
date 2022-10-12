# [1611. Minimum One Bit Operations to Make Integers Zero](https://leetcode.com/problems/minimum-one-bit-operations-to-make-integers-zero)

[中文文档](/solution/1600-1699/1611.Minimum%20One%20Bit%20Operations%20to%20Make%20Integers%20Zero/README.md)

## Description

<p>Given an integer <code>n</code>, you must transform it into <code>0</code> using the following operations any number of times:</p>

<ul>
	<li>Change the rightmost (<code>0<sup>th</sup></code>) bit in the binary representation of <code>n</code>.</li>
	<li>Change the <code>i<sup>th</sup></code> bit in the binary representation of <code>n</code> if the <code>(i-1)<sup>th</sup></code> bit is set to <code>1</code> and the <code>(i-2)<sup>th</sup></code> through <code>0<sup>th</sup></code> bits are set to <code>0</code>.</li>
</ul>

<p>Return <em>the minimum number of operations to transform </em><code>n</code><em> into </em><code>0</code><em>.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> n = 3
<strong>Output:</strong> 2
<strong>Explanation:</strong> The binary representation of 3 is &quot;11&quot;.
&quot;<u>1</u>1&quot; -&gt; &quot;<u>0</u>1&quot; with the 2<sup>nd</sup> operation since the 0<sup>th</sup> bit is 1.
&quot;0<u>1</u>&quot; -&gt; &quot;0<u>0</u>&quot; with the 1<sup>st</sup> operation.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 6
<strong>Output:</strong> 4
<strong>Explanation:</strong> The binary representation of 6 is &quot;110&quot;.
&quot;<u>1</u>10&quot; -&gt; &quot;<u>0</u>10&quot; with the 2<sup>nd</sup> operation since the 1<sup>st</sup> bit is 1 and 0<sup>th</sup> through 0<sup>th</sup> bits are 0.
&quot;01<u>0</u>&quot; -&gt; &quot;01<u>1</u>&quot; with the 1<sup>st</sup> operation.
&quot;0<u>1</u>1&quot; -&gt; &quot;0<u>0</u>1&quot; with the 2<sup>nd</sup> operation since the 0<sup>th</sup> bit is 1.
&quot;00<u>1</u>&quot; -&gt; &quot;00<u>0</u>&quot; with the 1<sup>st</sup> operation.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>0 &lt;= n &lt;= 10<sup>9</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def minimumOneBitOperations(self, n: int) -> int:
        if n <= 1:
            return n
        for i in range(64):
            if (n >> i) == 1:
                base = 1 << i
                break
        return 2*base-1 - self.minimumOneBitOperations(n-base)
```

### **Go**

```go
func minimumOneBitOperations(n int) int {
	if n <= 1 {
		return n
	}
	base := 0
	for i := 0; i < 64; i++ {
		if (n >> i) == 1 {
			base = 1 << i
			break
		}
	}
	return (base << 1) - 1 - minimumOneBitOperations(n-base)
}
```

### **...**

```

```

<!-- tabs:end -->
