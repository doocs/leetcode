---
comment: true
edit_url: https://github.com/doocs/leetcode/edit/main/lcof2/%E5%89%91%E6%8C%87%20Offer%20II%20072.%20%E6%B1%82%E5%B9%B3%E6%96%B9%E6%A0%B9/README.md
---

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

### 方法一

<!-- tabs:start -->

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

```java
class Solution {
    public int mySqrt(int x) {
        int left = 0, right = x;
        while (left < right) {
            int mid = (left + right + 1) >>> 1;
            if (mid <= x / mid) {
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
        return (int) left;
    }
};
```

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

<!-- tabs:end -->

<!-- end -->
