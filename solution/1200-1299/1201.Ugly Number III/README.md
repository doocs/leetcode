# [1201. 丑数 III](https://leetcode.cn/problems/ugly-number-iii)

[English Version](/solution/1200-1299/1201.Ugly%20Number%20III/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你四个整数：<code>n</code> 、<code>a</code> 、<code>b</code> 、<code>c</code> ，请你设计一个算法来找出第 <code>n</code> 个丑数。</p>

<p>丑数是可以被 <code>a</code> <strong>或</strong> <code>b</code> <strong>或</strong> <code>c</code> 整除的 <strong>正整数</strong> 。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>n = 3, a = 2, b = 3, c = 5
<strong>输出：</strong>4
<strong>解释：</strong>丑数序列为 2, 3, 4, 5, 6, 8, 9, 10... 其中第 3 个是 4。</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>n = 4, a = 2, b = 3, c = 4
<strong>输出：</strong>6
<strong>解释：</strong>丑数序列为 2, 3, 4, 6, 8, 9, 10, 12... 其中第 4 个是 6。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>n = 5, a = 2, b = 11, c = 13
<strong>输出：</strong>10
<strong>解释：</strong>丑数序列为 2, 4, 6, 8, 10, 11, 12, 13... 其中第 5 个是 10。
</pre>

<p><strong>示例 4：</strong></p>

<pre>
<strong>输入：</strong>n = 1000000000, a = 2, b = 217983653, c = 336916467
<strong>输出：</strong>1999999984
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= n, a, b, c <= 10^9</code></li>
	<li><code>1 <= a * b * c <= 10^18</code></li>
	<li>本题结果在 <code>[1, 2 * 10^9]</code> 的范围内</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：二分搜索**

根据题目提示结果在 [1, 2 * 10<sup>9</sup>] 的闭区间上，所以定义二分搜索的左边界 left=1，右边界 right=2e9。此时我们只需要在 [left,right] 的闭区间内找到一个最小的数 num，使其满足 [1,num] 内的丑数总数等于 n，则 num 就是第 n 个丑数。计算在 [1,num] 的范围内丑数的数目，即可以被 a、b 或 c 任意一个数整除的数的总数，其方法如下：

`f(num, a, b, c) = num/a + num/b + num/c - a⋂b - a⋂c - b⋂c + a⋂b⋂c`

-   num/a 表示在 [1,num] 内可以整除 a 的数目，num/b 表示在 [1,num] 内可以整除 b 的数目，num/c 表示在 [1,num] 内可以整除 c 的数目。
-   a⋂b 表示在 [1,num] 内可以同时整除 a 和 b 的数目，a⋂c 表示在 [1,num] 内可以同时整除 a 和 c 的数，b⋂c 表示在 [1,num] 内可以同时整除 b 和 c 的数。
-   a⋂b⋂c 表示在 [1,num] 内可以同时整除 a、b 和 c 的数。
-   a⋂b = num/least_common_multiple(a, b)，其他情况依次类推。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def f(self, num: int, a: int, b: int, c: int) -> int:
        return num // a + num // b + num // c - num // math.lcm(a, b) - num // math.lcm(a, c) - num // math.lcm(b, c) \
            + num // math.lcm(a, b, c)

    def nthUglyNumber(self, n: int, a: int, b: int, c: int) -> int:
        left, right = 1, int(2e9)
        while left <= right:
            mid = left + (right - left) // 2
            if self.f(mid, a, b, c) < n:
                left = mid + 1
            else:
                right = mid - 1
        return left
```

### **Go**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```go
func nthUglyNumber(n int, a int, b int, c int) int {
	left, right := 1, int(2e9)
	for left <= right {
		mid := left + (right-left)/2
		if f(mid, a, b, c) < n {
			left = mid + 1
		} else {
			right = mid - 1
		}
	}
	return left
}

func f(num int, a int, b int, c int) int {
	return num/a + num/b + num/c - num/lcm(a, b) - num/lcm(a, c) - num/lcm(b, c) + num/lcm(lcm(a, b), c)
}

// Least common multiple
func lcm(a, b int) int {
	// Greatest common divisor
	gcd := func(x, y int) int {
		for y != 0 {
			if x < y {
				x, y = y, x
			}
			x, y = y, x%y
		}
		return x
	}
	return a * b / gcd(a, b)
}
```

### **C++**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```cpp
class Solution {
public:
    long gcd(long x, long y) {
        while (y != 0) {
            if (x < y)
                swap(x, y);
            long tmp = x % y;
            x = y;
            y = tmp;
        }
        return x;
    }

    long lcm(long x, long y) { return x * y / gcd(x, y); }

    long f(int num, int a, int b, int c) {
        long sumabc = long(num / a) + num / b + num / c;
        long intersections = long(num / lcm(a, b)) + num / lcm(a, c) + num / lcm(b, c) - num / lcm(lcm(a, b), c);
        return sumabc - intersections;
    }

    int nthUglyNumber(int n, int a, int b, int c) {
        int left = 1, right = int(2e9);
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (f(mid, a, b, c) < n) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }
};
```

### **...**

```

```

<!-- tabs:end -->
