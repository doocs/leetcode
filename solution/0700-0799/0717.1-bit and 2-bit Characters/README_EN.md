# [717. 1-bit and 2-bit Characters](https://leetcode.com/problems/1-bit-and-2-bit-characters)

[中文文档](/solution/0700-0799/0717.1-bit%20and%202-bit%20Characters/README.md)

## Description

<p>We have two special characters:</p>

<ul>
	<li>The first character can be represented by one bit <code>0</code>.</li>
	<li>The second character can be represented by two bits (<code>10</code> or <code>11</code>).</li>
</ul>

<p>Given a binary array <code>bits</code> that ends with <code>0</code>, return <code>true</code> if the last character must be a one-bit character.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> bits = [1,0,0]
<strong>Output:</strong> true
<strong>Explanation:</strong> The only way to decode it is two-bit character and one-bit character.
So the last character is one-bit character.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> bits = [1,1,1,0]
<strong>Output:</strong> false
<strong>Explanation:</strong> The only way to decode it is two-bit character and two-bit character.
So the last character is not one-bit character.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= bits.length &lt;= 1000</code></li>
	<li><code>bits[i]</code> is either <code>0</code> or <code>1</code>.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def isOneBitCharacter(self, bits: List[int]) -> bool:
        i, n = 0, len(bits)
        while i < n - 1:
            i += bits[i] + 1
        return i == n - 1
```

### **Java**

```java
class Solution {
    public boolean isOneBitCharacter(int[] bits) {
        int i = 0, n = bits.length;
        while (i < n - 1) {
            i += bits[i] + 1;
        }
        return i == n - 1;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    bool isOneBitCharacter(vector<int>& bits) {
        int i = 0, n = bits.size();
        while (i < n - 1) i += bits[i] + 1;
        return i == n - 1;
    }
};
```

### **Go**

```go
func isOneBitCharacter(bits []int) bool {
	i, n := 0, len(bits)
	for i < n-1 {
		i += bits[i] + 1
	}
	return i == n-1
}
```

### **JavaScript**

```js
/**
 * @param {number[]} bits
 * @return {boolean}
 */
var isOneBitCharacter = function (bits) {
    let i = 0;
    const n = bits.length;
    while (i < n - 1) {
        i += bits[i] + 1;
    }
    return i == n - 1;
};
```

### **...**

```

```

<!-- tabs:end -->
