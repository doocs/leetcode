# [461. Hamming Distance](https://leetcode.com/problems/hamming-distance)

[中文文档](/solution/0400-0499/0461.Hamming%20Distance/README.md)

## Description

<p>The <a href="https://en.wikipedia.org/wiki/Hamming_distance" target="_blank">Hamming distance</a> between two integers is the number of positions at which the corresponding bits are different.</p>

<p>Given two integers <code>x</code> and <code>y</code>, return <em>the <strong>Hamming distance</strong> between them</em>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> x = 1, y = 4
<strong>Output:</strong> 2
<strong>Explanation:</strong>
1   (0 0 0 1)
4   (0 1 0 0)
       &uarr;   &uarr;
The above arrows point to positions where the corresponding bits are different.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> x = 3, y = 1
<strong>Output:</strong> 1
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>0 &lt;=&nbsp;x, y &lt;= 2<sup>31</sup> - 1</code></li>
</ul>

## Solutions

Use xor operation to find different bits.

-   0 ^ 0 = 0
-   1 ^ 1 = 0
-   0 ^ 1 = 1
-   1 ^ 0 = 1

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def hammingDistance(self, x: int, y: int) -> int:
        num, count = x ^ y, 0
        while num != 0:
            num &= num - 1
            count += 1
        return count
```

### **Java**

```java
class Solution {
    public int hammingDistance(int x, int y) {
        int num = x ^ y;
        int count = 0;
        while (num != 0) {
            num &= num - 1;
            count++;
        }
        return count;
    }
}
```

Or use the library function `Integer.bitCount()`

```java
class Solution {
    public int hammingDistance(int x, int y) {
        return Integer.bitCount(x ^ y);
    }
}
```

### **JavaScript**

```js
/**
 * @param {number} x
 * @param {number} y
 * @return {number}
 */
var hammingDistance = function (x, y) {
    let distance = x ^ y;
    let count = 0;
    while (distance != 0) {
        count++;
        distance &= distance - 1;
    }
    return count;
};
```

### **C++**

```cpp
class Solution {
public:
    int hammingDistance(int x, int y) {
        x ^= y;
        int count = 0;
        while (x) {
            ++count;
            x &= (x - 1);
        }
        return count;
    }
};
```

### **Go**

```go
func hammingDistance(x int, y int) int {
	x ^= y
	count := 0
	for x != 0 {
		count++
		x &= (x - 1)
	}
	return count
}
```

### **...**

```

```

<!-- tabs:end -->
