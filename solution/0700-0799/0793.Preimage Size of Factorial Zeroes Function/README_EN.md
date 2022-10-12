# [793. Preimage Size of Factorial Zeroes Function](https://leetcode.com/problems/preimage-size-of-factorial-zeroes-function)

[中文文档](/solution/0700-0799/0793.Preimage%20Size%20of%20Factorial%20Zeroes%20Function/README.md)

## Description

<p>Let <code>f(x)</code> be the number of zeroes at the end of <code>x!</code>. Recall that <code>x! = 1 * 2 * 3 * ... * x</code> and by convention, <code>0! = 1</code>.</p>

<ul>
	<li>For example, <code>f(3) = 0</code> because <code>3! = 6</code> has no zeroes at the end, while <code>f(11) = 2</code> because <code>11! = 39916800</code> has two zeroes at the end.</li>
</ul>

<p>Given an integer <code>k</code>, return the number of non-negative integers <code>x</code> have the property that <code>f(x) = k</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> k = 0
<strong>Output:</strong> 5
<strong>Explanation:</strong> 0!, 1!, 2!, 3!, and 4! end with k = 0 zeroes.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> k = 5
<strong>Output:</strong> 0
<strong>Explanation:</strong> There is no x such that x! ends in k = 5 zeroes.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> k = 3
<strong>Output:</strong> 5
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>0 &lt;= k &lt;= 10<sup>9</sup></code></li>
</ul>

## Solutions

Binary search.

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def preimageSizeFZF(self, k: int) -> int:
        def f(x):
            if x == 0:
                return 0
            return x // 5 + f(x // 5)

        def g(k):
            return bisect_left(range(5 * k), k, key=f)

        return g(k + 1) - g(k)
```

### **Java**

```java
class Solution {
    public int preimageSizeFZF(int k) {
        return g(k + 1) - g(k);
    }

    private int g(int k) {
        long left = 0, right = 5 * k;
        while (left < right) {
            long mid = (left + right) >> 1;
            if (f(mid) >= k) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return (int) left;
    }

    private int f(long x) {
        if (x == 0) {
            return 0;
        }
        return (int) (x / 5) + f(x / 5);
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int preimageSizeFZF(int k) {
        return g(k + 1) - g(k);
    }

    int g(int k) {
        long long left = 0, right = 1ll * 5 * k;
        while (left < right) {
            long long mid = (left + right) >> 1;
            if (f(mid) >= k) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return (int) left;
    }

    int f(long x) {
        int res = 0;
        while (x) {
            x /= 5;
            res += x;
        }
        return res;
    }
};
```

### **Go**

```go
func preimageSizeFZF(k int) int {
	f := func(x int) int {
		res := 0
		for x != 0 {
			x /= 5
			res += x
		}
		return res
	}

	g := func(k int) int {
		left, right := 0, k*5
		for left < right {
			mid := (left + right) >> 1
			if f(mid) >= k {
				right = mid
			} else {
				left = mid + 1
			}
		}
		return left
	}

	return g(k+1) - g(k)
}
```

### **...**

```

```

<!-- tabs:end -->
