# [357. Count Numbers with Unique Digits](https://leetcode.com/problems/count-numbers-with-unique-digits)

[中文文档](/solution/0300-0399/0357.Count%20Numbers%20with%20Unique%20Digits/README.md)

## Description

<p>Given an integer <code>n</code>, return the count of all numbers with unique digits, <code>x</code>, where <code>0 &lt;= x &lt; 10<sup>n</sup></code>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> n = 2
<strong>Output:</strong> 91
<strong>Explanation:</strong> The answer should be the total numbers in the range of 0 &le; x &lt; 100, excluding 11,22,33,44,55,66,77,88,99
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 0
<strong>Output:</strong> 1
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>0 &lt;= n &lt;= 8</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def countNumbersWithUniqueDigits(self, n: int) -> int:
        if n == 0:
            return 1
        if n == 1:
            return 10
        ans, cur = 10, 9
        for i in range(n - 1):
            cur *= 9 - i
            ans += cur
        return ans
```

### **Java**

```java
class Solution {
    public int countNumbersWithUniqueDigits(int n) {
        if (n == 0) {
            return 1;
        }
        if (n == 1) {
            return 10;
        }
        int ans = 10;
        for (int i = 0, cur = 9; i < n - 1; ++i) {
            cur *= (9 - i);
            ans += cur;
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int countNumbersWithUniqueDigits(int n) {
        if (n == 0) return 1;
        if (n == 1) return 10;
        int ans = 10;
        for (int i = 0, cur = 9; i < n - 1; ++i) {
            cur *= (9 - i);
            ans += cur;
        }
        return ans;
    }
};
```

### **Go**

```go
func countNumbersWithUniqueDigits(n int) int {
	if n == 0 {
		return 1
	}
	if n == 1 {
		return 10
	}
	ans := 10
	for i, cur := 0, 9; i < n-1; i++ {
		cur *= (9 - i)
		ans += cur
	}
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
