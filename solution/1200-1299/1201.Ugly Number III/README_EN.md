# [1201. Ugly Number III](https://leetcode.com/problems/ugly-number-iii)

[中文文档](/solution/1200-1299/1201.Ugly%20Number%20III/README.md)

## Description

<p>An <strong>ugly number</strong> is a positive integer that is divisible by <code>a</code>, <code>b</code>, or <code>c</code>.</p>

<p>Given four integers <code>n</code>, <code>a</code>, <code>b</code>, and <code>c</code>, return the <code>n<sup>th</sup></code> <strong>ugly number</strong>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> n = 3, a = 2, b = 3, c = 5
<strong>Output:</strong> 4
<strong>Explanation:</strong> The ugly numbers are 2, 3, 4, 5, 6, 8, 9, 10... The 3<sup>rd</sup> is 4.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 4, a = 2, b = 3, c = 4
<strong>Output:</strong> 6
<strong>Explanation:</strong> The ugly numbers are 2, 3, 4, 6, 8, 9, 10, 12... The 4<sup>th</sup> is 6.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> n = 5, a = 2, b = 11, c = 13
<strong>Output:</strong> 10
<strong>Explanation:</strong> The ugly numbers are 2, 4, 6, 8, 10, 11, 12, 13... The 5<sup>th</sup> is 10.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n, a, b, c &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= a * b * c &lt;= 10<sup>18</sup></code></li>
	<li>It is guaranteed that the result will be in range <code>[1, 2 * 10<sup>9</sup>]</code>.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

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
