# [441. 排列硬币](https://leetcode-cn.com/problems/arranging-coins)

[English Version](/solution/0400-0499/0441.Arranging%20Coins/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>你总共有&nbsp;<em>n&nbsp;</em>枚硬币，你需要将它们摆成一个阶梯形状，第&nbsp;<em>k&nbsp;</em>行就必须正好有&nbsp;<em>k&nbsp;</em>枚硬币。</p>

<p>给定一个数字&nbsp;<em>n</em>，找出可形成完整阶梯行的总行数。</p>

<p><em>n&nbsp;</em>是一个非负整数，并且在32位有符号整型的范围内。</p>

<p><strong>示例 1:</strong></p>

<pre>
n = 5

硬币可排列成以下几行:
&curren;
&curren; &curren;
&curren; &curren;

因为第三行不完整，所以返回2.
</pre>

<p><strong>示例 2:</strong></p>

<pre>
n = 8

硬币可排列成以下几行:
&curren;
&curren; &curren;
&curren; &curren; &curren;
&curren; &curren;

因为第四行不完整，所以返回3.
</pre>

## 解法

<!-- 这里可写通用的实现逻辑 -->

1. 数学推导

`(1 + x) * x / 2 <= n`，求解 x。

`(x + 1/2)² <= 2n + 1/4`，即 `x <= sqrt(2n + 1/4) - 1/2`。

由于 2n 可能溢出，故转换为 `x <= sqrt(2) * sqrt(n + 1/8) - 1/2`。

2. 二分查找

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def arrangeCoins(self, n: int) -> int:
        return int(math.sqrt(2) * math.sqrt(n + 0.125) - 0.5)
```

```python
class Solution:
    def arrangeCoins(self, n: int) -> int:
        left, right = 1, n
        while left < right:
            mid = (left + right + 1) >> 1
            s = ((1 + mid) * mid) >> 1
            if n < s:
                right = mid - 1
            else:
                left = mid
        return left
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int arrangeCoins(int n) {
        return (int) (Math.sqrt(2) * Math.sqrt(n + 0.125) - 0.5);
    }
}
```

```java
class Solution {
    public int arrangeCoins(int n) {
        long left = 1, right = n;
        while (left < right) {
            long mid = (left + right + 1) >> 1;
            long s = ((1 + mid) * mid) >> 1;
            if (n < s) {
                right = mid - 1;
            } else {
                left = mid;
            }
        }
        return (int) left;
    }
}
```

### **C++**

```cpp
using LL = long;

class Solution {
public:
    int arrangeCoins(int n) {
        LL left = 1, right = n;
        while (left < right)
        {
            LL mid = left + right + 1 >> 1;
            LL s = (1 + mid) * mid >> 1;
            if (n < s) right = mid - 1;
            else left = mid;
        }
        return left;
    }
};
```

### **Go**

```go
func arrangeCoins(n int) int {
	left, right := 1, n
	for left < right {
		mid := (left + right + 1) >> 1
		s := (1 + mid) * mid >> 1
		if n < s {
			right = mid - 1
		} else {
			left = mid
		}
	}
	return left
}
```

### **...**

```

```

<!-- tabs:end -->
