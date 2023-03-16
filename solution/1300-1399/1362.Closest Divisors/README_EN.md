# [1362. Closest Divisors](https://leetcode.com/problems/closest-divisors)

[中文文档](/solution/1300-1399/1362.Closest%20Divisors/README.md)

## Description

<p>Given an integer <code>num</code>, find the closest two integers in absolute difference whose product equals&nbsp;<code>num + 1</code>&nbsp;or <code>num + 2</code>.</p>

<p>Return the two integers in any order.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> num = 8
<strong>Output:</strong> [3,3]
<strong>Explanation:</strong> For num + 1 = 9, the closest divisors are 3 &amp; 3, for num + 2 = 10, the closest divisors are 2 &amp; 5, hence 3 &amp; 3 is chosen.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> num = 123
<strong>Output:</strong> [5,25]
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> num = 999
<strong>Output:</strong> [40,25]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= num &lt;= 10^9</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def closestDivisors(self, num: int) -> List[int]:
        def f(x):
            for i in range(int(sqrt(x)), 0, -1):
                if x % i == 0:
                    return [i, x // i]

        a = f(num + 1)
        b = f(num + 2)
        return a if abs(a[0] - a[1]) < abs(b[0] - b[1]) else b
```

### **Java**

```java
class Solution {
    public int[] closestDivisors(int num) {
        int[] a = f(num + 1);
        int[] b = f(num + 2);
        return Math.abs(a[0] - a[1]) < Math.abs(b[0] - b[1]) ? a : b;
    }

    private int[] f(int x) {
        for (int i = (int) Math.sqrt(x);; --i) {
            if (x % i == 0) {
                return new int[] {i, x / i};
            }
        }
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<int> closestDivisors(int num) {
        auto f = [](int x) {
            for (int i = sqrt(x);; --i) {
                if (x % i == 0) {
                    return vector<int>{i, x / i};
                }
            }
        };
        vector<int> a = f(num + 1);
        vector<int> b = f(num + 2);
        return abs(a[0] - a[1]) < abs(b[0] - b[1]) ? a : b;
    }
};
```

### **Go**

```go
func closestDivisors(num int) []int {
	f := func(x int) []int {
		for i := int(math.Sqrt(float64(x))); ; i-- {
			if x%i == 0 {
				return []int{i, x / i}
			}
		}
	}
	a, b := f(num+1), f(num+2)
	if abs(a[0]-a[1]) < abs(b[0]-b[1]) {
		return a
	}
	return b
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}
```

### **...**

```

```

<!-- tabs:end -->
