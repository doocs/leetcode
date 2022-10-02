# [2429. Minimize XOR](https://leetcode.com/problems/minimize-xor)

[中文文档](/solution/2400-2499/2429.Minimize%20XOR/README.md)

## Description

<p>Given two positive integers <code>num1</code> and <code>num2</code>, find the integer <code>x</code> such that:</p>

<ul>
	<li><code>x</code> has the same number of set bits as <code>num2</code>, and</li>
	<li>The value <code>x XOR num1</code> is <strong>minimal</strong>.</li>
</ul>

<p>Note that <code>XOR</code> is the bitwise XOR operation.</p>

<p>Return <em>the integer </em><code>x</code>. The test cases are generated such that <code>x</code> is <strong>uniquely determined</strong>.</p>

<p>The number of <strong>set bits</strong> of an integer is the number of <code>1</code>&#39;s in its binary representation.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> num1 = 3, num2 = 5
<strong>Output:</strong> 3
<strong>Explanation:</strong>
The binary representations of num1 and num2 are 0011 and 0101, respectively.
The integer <strong>3</strong> has the same number of set bits as num2, and the value <code>3 XOR 3 = 0</code> is minimal.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> num1 = 1, num2 = 12
<strong>Output:</strong> 3
<strong>Explanation:</strong>
The binary representations of num1 and num2 are 0001 and 1100, respectively.
The integer <strong>3</strong> has the same number of set bits as num2, and the value <code>3 XOR 1 = 2</code> is minimal.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= num1, num2 &lt;= 10<sup>9</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def minimizeXor(self, num1: int, num2: int) -> int:
        cnt = num2.bit_count()
        ans = 0
        for i in range(30, -1, -1):
            if (num1 >> i) & 1:
                ans |= 1 << i
                cnt -= 1
                if cnt == 0:
                    return ans
        for i in range(31):
            if (num1 >> i) & 1 == 0:
                ans |= 1 << i
                cnt -= 1
                if cnt == 0:
                    return ans
        return 0
```

### **Java**

```java
class Solution {
    public int minimizeXor(int num1, int num2) {
        int cnt = Integer.bitCount(num2);
        int ans = 0;
        for (int i = 30; i >= 0; --i) {
            if (((num1 >> i) & 1) == 1) {
                ans |= 1 << i;
                if (--cnt == 0) {
                    return ans;
                }
            }
        }
        for (int i = 0; i < 31; ++i) {
            if (((num1 >> i) & 1) == 0) {
                ans |= 1 << i;
                if (--cnt == 0) {
                    return ans;
                }
            }
        }
        return 0;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int minimizeXor(int num1, int num2) {
        int cnt = __builtin_popcount(num2);
        int ans = 0;
        for (int i = 30; i >= 0; --i) {
            if ((num1 >> i) & 1) {
                ans |= 1 << i;
                if (--cnt == 0) {
                    return ans;
                }
            }
        }
        for (int i = 0; i < 31; ++i) {
            if (((num1 >> i) & 1) == 0) {
                ans |= 1 << i;
                if (--cnt == 0) {
                    return ans;
                }
            }
        }
        return 0;
    }
};
```

### **Go**

```go
func minimizeXor(num1 int, num2 int) int {
	cnt := bits.OnesCount(uint(num2))
	ans := 0
	for i := 30; i >= 0; i-- {
		if num1>>i&1 == 1 {
			ans |= 1 << i
			cnt--
			if cnt == 0 {
				return ans
			}
		}
	}
	for i := 0; i < 31; i++ {
		if num1>>i&1 == 0 {
			ans |= 1 << i
			cnt--
			if cnt == 0 {
				return ans
			}
		}
	}
	return 0
}
```

### **TypeScript**

```ts

```

### **...**

```

```

<!-- tabs:end -->
