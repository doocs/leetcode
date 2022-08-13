# [剑指 Offer II 072. 求平方根](https://leetcode.cn/problems/jJ0w9p)

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
<strong>说明:</strong> 根号 8 是 2.82842..., 
&nbsp;    由于返回类型是整数，小数部分将被舍去。
</pre>

<p><meta charset="UTF-8" />注意：本题与主站 69&nbsp;题相同：&nbsp;<a href="https://leetcode.cn/problems/sqrtx/">https://leetcode.cn/problems/sqrtx/</a></p>

## 解法

<!-- 这里可写通用的实现逻辑 -->

二分查找。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def mySqrt(self, x: int) -> int:
        left, right = 0, x
        while left < right:
            mid = (left + right + 1) >> 1
            # mid*mid <= x
            if mid <= x // mid:
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
        int left = 0, right = x;
        while (left < right) {
            int mid = (left + right + 1) >>> 1;
            if (mid <= x /mid) {
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
        long long left = 0, right = x;
        while (left < right) {
            long long mid = left + ((right - left + 1) >> 1);
            if (mid <= x / mid)
                left = mid;
            else
                right = mid - 1;
        }
        return (int)left;
    }
};
```

### **Go**

```go
func mySqrt(x int) int {
	left, right := 0, x
	for left < right {
		mid := left + (right-left+1)>>1
		if mid <= x/mid {
			left = mid
		} else {
			right = mid - 1
		}
	}
	return left
}
```

### **JavaScript**

```js
/**
 * @param {number} x
 * @return {number}
 */
var mySqrt = function (x) {
    let left = 0;
    let right = x;
    while (left < right) {
        const mid = (left + right + 1) >>> 1;
        if (mid <= x / mid) {
            left = mid;
        } else {
            right = mid - 1;
        }
    }
    return left;
};
```

### **C#**

```cs
public class Solution {
    public int MySqrt(int x) {
        int left = 0, right = x;
        while (left < right)
        {
            int mid = left + right + 1 >> 1;
            if (mid <= x / mid)
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
