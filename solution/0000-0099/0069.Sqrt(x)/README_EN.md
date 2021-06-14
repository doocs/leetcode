# [69. Sqrt(x)](https://leetcode.com/problems/sqrtx)

[中文文档](/solution/0000-0099/0069.Sqrt%28x%29/README.md)

## Description

<p>Given a non-negative integer <code>x</code>,&nbsp;compute and return <em>the square root of</em> <code>x</code>.</p>

<p>Since the return type&nbsp;is an integer, the decimal digits are <strong>truncated</strong>, and only <strong>the integer part</strong> of the result&nbsp;is returned.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> x = 4
<strong>Output:</strong> 2
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> x = 8
<strong>Output:</strong> 2
<strong>Explanation:</strong> The square root of 8 is 2.82842..., and since the decimal part is truncated, 2 is returned.</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>0 &lt;= x &lt;= 2<sup>31</sup> - 1</code></li>
</ul>


## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def mySqrt(self, x: int) -> int:
        if x == 0:
            return 0
        low, high = 1, x
        while low < high:
            mid = (low + high + 1) >> 1
            # mid * mid <= x
            if x // mid >= mid:
                low = mid
            else:
                high = mid - 1
        return low
```

### **Java**

```java
class Solution {
    public int mySqrt(int x) {
        if (x == 0) {
            return 0;
        }
        int low = 1, high = x;
        while (low < high) {
            int mid = (low + high + 1) >>> 1;
            if (x / mid >= mid) {
                low = mid;
            } else {
                high = mid - 1;
            }
        }
        return low;
    }
}
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
    let low = 1;
    let high = x;
    while (low < high) {
        const mid = low + ((high - low + 1) >> 1);
        if (x / mid >= mid) {
            low = mid;
        } else {
            high = mid - 1;
        }
    }
    return low;
};
```

### **Go**

```go
func mySqrt(x int) int {
	if x == 0 {
		return 0
	}
	low, high := 1, x
	for low < high {
		mid := low + (high-low+1)>>1
		if x/mid >= mid {
			low = mid
		} else {
			high = mid - 1
		}
	}
	return low
}
```

### **C#**

```cs
public class Solution {
    public int MySqrt(int x) {
        if (x == 0) {
            return 0;
        }
        int low = 1, high = x;
        while (low < high)
        {
            int mid = low + ((high - low + 1) >> 1);
            if (x / mid >= mid)
            {
                low = mid;
            } 
            else 
            {
                high = mid - 1;
            }
        }
        return low;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
