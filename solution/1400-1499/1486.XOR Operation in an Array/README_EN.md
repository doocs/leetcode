# [1486. XOR Operation in an Array](https://leetcode.com/problems/xor-operation-in-an-array)

[中文文档](/solution/1400-1499/1486.XOR%20Operation%20in%20an%20Array/README.md)

## Description

<p>You are given an integer <code>n</code> and an integer <code>start</code>.</p>

<p>Define an array <code>nums</code> where <code>nums[i] = start + 2 * i</code> (<strong>0-indexed</strong>) and <code>n == nums.length</code>.</p>

<p>Return <em>the bitwise XOR of all elements of</em> <code>nums</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> n = 5, start = 0
<strong>Output:</strong> 8
<strong>Explanation:</strong> Array nums is equal to [0, 2, 4, 6, 8] where (0 ^ 2 ^ 4 ^ 6 ^ 8) = 8.
Where &quot;^&quot; corresponds to bitwise XOR operator.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 4, start = 3
<strong>Output:</strong> 8
<strong>Explanation:</strong> Array nums is equal to [3, 5, 7, 9] where (3 ^ 5 ^ 7 ^ 9) = 8.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 1000</code></li>
	<li><code>0 &lt;= start &lt;= 1000</code></li>
	<li><code>n == nums.length</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def xorOperation(self, n: int, start: int) -> int:
        ans = 0
        for i in range(n):
            ans ^= start + 2 * i
        return ans
```

```python
class Solution:
    def xorOperation(self, n: int, start: int) -> int:
        return reduce(xor, ((start + 2 * i) for i in range(n)))
```

### **Java**

```java
class Solution {
    public int xorOperation(int n, int start) {
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            ans ^= start + 2 * i;
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int xorOperation(int n, int start) {
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            ans ^= start + 2 * i;
        }
        return ans;
    }
};
```

### **Go**

```go
func xorOperation(n int, start int) (ans int) {
	for i := 0; i < n; i++ {
		ans ^= start + 2*i
	}
	return
}
```

### **...**

```

```

<!-- tabs:end -->
