# [829. Consecutive Numbers Sum](https://leetcode.com/problems/consecutive-numbers-sum)

[中文文档](/solution/0800-0899/0829.Consecutive%20Numbers%20Sum/README.md)

## Description

<p>Given an integer <code>n</code>, return <em>the number of ways you can write </em><code>n</code><em> as the sum of consecutive positive integers.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> n = 5
<strong>Output:</strong> 2
<strong>Explanation:</strong> 5 = 2 + 3
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 9
<strong>Output:</strong> 3
<strong>Explanation:</strong> 9 = 4 + 5 = 2 + 3 + 4
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> n = 15
<strong>Output:</strong> 4
<strong>Explanation:</strong> 15 = 8 + 7 = 4 + 5 + 6 = 1 + 2 + 3 + 4 + 5
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10<sup>9</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def consecutiveNumbersSum(self, n: int) -> int:
        n <<= 1
        ans, k = 0, 1
        while k * (k + 1) <= n:
            if n % k == 0 and (n // k + 1 - k) % 2 == 0:
                ans += 1
            k += 1
        return ans
```

### **Java**

```java
class Solution {
    public int consecutiveNumbersSum(int n) {
        n <<= 1;
        int ans = 0;
        for (int k = 1; k * (k + 1) <= n; ++k) {
            if (n % k == 0 && (n / k + 1 - k) % 2 == 0) {
                ++ans;
            }
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int consecutiveNumbersSum(int n) {
        n <<= 1;
        int ans = 0;
        for (int k = 1; k * (k + 1) <= n; ++k) {
            if (n % k == 0 && (n / k + 1 - k) % 2 == 0) {
                ++ans;
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func consecutiveNumbersSum(n int) int {
	n <<= 1
	ans := 0
	for k := 1; k*(k+1) <= n; k++ {
		if n%k == 0 && (n/k+1-k)%2 == 0 {
			ans++
		}
	}
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
