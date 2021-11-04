# [367. Valid Perfect Square](https://leetcode.com/problems/valid-perfect-square)

[中文文档](/solution/0300-0399/0367.Valid%20Perfect%20Square/README.md)

## Description

<p>Given a <strong>positive</strong> integer <i>num</i>, write a function which returns True if <i>num</i> is a perfect square else False.</p>

<p><b>Follow up:</b> <b>Do not</b> use any built-in library function such as <code>sqrt</code>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<pre><strong>Input:</strong> num = 16
<strong>Output:</strong> true
</pre><p><strong>Example 2:</strong></p>
<pre><strong>Input:</strong> num = 14
<strong>Output:</strong> false
</pre>
<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= num &lt;= 2^31 - 1</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def isPerfectSquare(self, num: int) -> bool:
        left, right = 1, num
        while left < right:
            mid = (left + right) >> 1
            if mid * mid >= num:
                right = mid
            else:
                left = mid + 1
        return left * left == num
```

```python
class Solution:
    def isPerfectSquare(self, num: int) -> bool:
        i = 1
        while num > 0:
            num -= i
            i += 2
        return num == 0
```

### **Java**

```java
class Solution {
    public boolean isPerfectSquare(int num) {
        long left = 1, right = num;
        while (left < right) {
            long mid = (left + right) >>> 1;
            if (mid * mid >= num) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left * left == num;
    }
}
```

```java
class Solution {
    public boolean isPerfectSquare(int num) {
        for (int i = 1; num > 0; i += 2) {
            num -= i;
        }
        return num == 0;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    bool isPerfectSquare(int num) {
        long left = 1, right = num;
        while (left < right)
        {
            long mid = left + right >> 1;
            if (mid * mid >= num) right = mid;
            else left = mid + 1;
        }
        return left * left == num;
    }
};
```

```cpp
class Solution {
public:
    bool isPerfectSquare(int num) {
        for (int i = 1; num > 0; i += 2) num -= i;
        return num == 0;
    }
};
```

### **Go**

```go
func isPerfectSquare(num int) bool {
	left, right := 1, num
	for left < right {
		mid := (left + right) >> 1
		if mid*mid >= num {
			right = mid
		} else {
			left = mid + 1
		}
	}
	return left*left == num
}
```

```go
func isPerfectSquare(num int) bool {
	for i := 1; num > 0; i += 2 {
		num -= i
	}
	return num == 0
}
```

### **...**

```

```

<!-- tabs:end -->
