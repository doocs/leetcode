# [69. x 的平方根](https://leetcode-cn.com/problems/sqrtx)

[English Version](/solution/0000-0099/0069.Sqrt%28x%29/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>实现&nbsp;<code>int sqrt(int x)</code>&nbsp;函数。</p>

<p>计算并返回&nbsp;<em>x</em>&nbsp;的平方根，其中&nbsp;<em>x </em>是非负整数。</p>

<p>由于返回类型是整数，结果只保留整数的部分，小数部分将被舍去。</p>

<p><strong>示例 1:</strong></p>

<pre><strong>输入:</strong> 4
<strong>输出:</strong> 2
</pre>

<p><strong>示例 2:</strong></p>

<pre><strong>输入:</strong> 8
<strong>输出:</strong> 2
<strong>说明:</strong> 8 的平方根是 2.82842..., 
&nbsp;    由于返回类型是整数，小数部分将被舍去。
</pre>


## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def mySqrt(self, x: int) -> int:
        if x == 0:
            return 0
        left, right = 1, x
        while left < right:
            mid = (left + right + 1) >> 1
            # mid*mid <= x
            if x // mid >= mid:
                left = mid
            else:
                right = mid - 1
        return left
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int mySqrt(int x) {
        if (x == 0) {
            return 0;
        }
        int left = 1, right = x;
        while (left < right) {
            int mid = (left + right + 1) >>> 1;
            if (x / mid >= mid) {
                // mid*mid <= x
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int mySqrt(int x) {
        if (x == 0) {
            return 0;
        }
        int left = 1, right = x;
        while (left < right) {
            int mid = left + ((right - left + 1) >> 1);
            if (x / mid >= mid) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }
};
```

### **JavaScript**

```js
/**
 * @param {number} x
 * @return {number}
 */
var mySqrt = function(x) {
    if (x == 0) {
        return 0;
    }
    let left = 1;
    let right = x;
    while (left < right) {
        const mid = (left + right + 1) >>> 1;
        if (x / mid >= mid) {
            left = mid;
        } else {
            right = mid - 1;
        }
    }
    return left;
};
```

### **Go**

```go
func mySqrt(x int) int {
	if x == 0 {
		return 0
	}
	left, right := 1, x
	for left < right {
		mid := left + (right-left+1)>>1
		if x/mid >= mid {
			left = mid
		} else {
			right = mid - 1
		}
	}
	return left
}
```

### **C#**

```cs
public class Solution {
    public int MySqrt(int x) {
        if (x == 0) {
            return 0;
        }
        int left = 1, right = x;
        while (left < right)
        {
            int mid = left + right + 1 >> 1;
            if (x / mid >= mid)
            {
                left = mid;
            } 
            else 
            {
                right = mid - 1;
            }
        }
        return left;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
