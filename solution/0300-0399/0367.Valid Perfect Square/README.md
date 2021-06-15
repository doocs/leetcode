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

二分法。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def isPerfectSquare(self, num: int) -> bool:
        left, right = 1, num
        while left < right:
            mid = left + ((right - left) >> 1)
            if num // mid <= mid:
                right = mid
            else:
                left = mid + 1
        return left * left == num
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public boolean isPerfectSquare(int num) {
        int left = 1, right = num;
        while (left < right) {
            int mid = (left + right) >>> 1;
            if (num / mid <= mid) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left * left == num;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    bool isPerfectSquare(int num) {
        long left = 1, right = num;
        while (left < right) {
            int mid = left + ((right - left) >> 1);
            if (num / mid <= mid) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left * left == num;
    }
};
```

### **Go**

```go
func isPerfectSquare(num int) bool {
	left, right := 1, num
	for left < right {
		mid := left + (right-left)>>1
		if num/mid <= mid {
			right = mid
		} else {
			left = mid + 1
		}
	}
	return left*left == num
}
```

### **...**

```

```

<!-- tabs:end -->
