# [367. 有效的完全平方数](https://leetcode-cn.com/problems/valid-perfect-square)

[English Version](/solution/0300-0399/0367.Valid%20Perfect%20Square/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个 <strong>正整数</strong> <code>num</code> ，编写一个函数，如果 <code>num</code> 是一个完全平方数，则返回 <code>true</code> ，否则返回 <code>false</code> 。</p>

<p><strong>进阶：不要</strong> 使用任何内置的库函数，如  <code>sqrt</code> 。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>num = 16
<strong>输出：</strong>true
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>num = 14
<strong>输出：</strong>false
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= num <= 2^31 - 1</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**1. 二分查找**

**2. 转换为数学问题**

由于 `n² = 1 + 3 + 5 + ... + (2n-1)`，对数字 num 不断减去 i (`i = 1, 3, 5, ...`) 直至 num 不大于 0，如果最终 num 等于 0，说明是一个有效的完全平方数。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
