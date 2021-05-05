# [1720. Decode XORed Array](https://leetcode.com/problems/decode-xored-array)

[中文文档](/solution/1700-1799/1720.Decode%20XORed%20Array/README.md)

## Description

<p>There is a <strong>hidden</strong> integer array <code>arr</code> that consists of <code>n</code> non-negative integers.</p>

<p>It was encoded into another integer array <code>encoded</code> of length <code>n - 1</code>, such that <code>encoded[i] = arr[i] XOR arr[i + 1]</code>. For example, if <code>arr = [1,0,2,1]</code>, then <code>encoded = [1,2,3]</code>.</p>

<p>You are given the <code>encoded</code> array. You are also given an integer <code>first</code>, that is the first element of <code>arr</code>, i.e. <code>arr[0]</code>.</p>

<p>Return <em>the original array</em> <code>arr</code>. It can be proved that the answer exists and is unique.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> encoded = [1,2,3], first = 1
<strong>Output:</strong> [1,0,2,1]
<strong>Explanation:</strong> If arr = [1,0,2,1], then first = 1 and encoded = [1 XOR 0, 0 XOR 2, 2 XOR 1] = [1,2,3]
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> encoded = [6,2,7,3], first = 4
<strong>Output:</strong> [4,2,0,7,4]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= n &lt;= 10<sup>4</sup></code></li>
	<li><code>encoded.length == n - 1</code></li>
	<li><code>0 &lt;= encoded[i] &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= first &lt;= 10<sup>5</sup></code></li>
</ul>

## Solutions

XOR.

`a = b ^ c` => `a ^ b = b ^ c ^ b` => `c = a ^ b`.

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def decode(self, encoded: List[int], first: int) -> List[int]:
        res = [first]
        for e in encoded:
            first ^= e
            res.append(first)
        return res
```

### **Java**

```java
class Solution {
    public int[] decode(int[] encoded, int first) {
        int[] res = new int[encoded.length + 1];
        res[0] = first;
        for (int i = 0; i < encoded.length; ++i) {
            res[i + 1] = res[i] ^ encoded[i];
        }
        return res;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
