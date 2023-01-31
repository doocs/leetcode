# [50. Pow(x, n)](https://leetcode.com/problems/powx-n)

[中文文档](/solution/0000-0099/0050.Pow%28x%2C%20n%29/README.md)

## Description

<p>Implement <a href="http://www.cplusplus.com/reference/valarray/pow/" target="_blank">pow(x, n)</a>, which calculates <code>x</code> raised to the power <code>n</code> (i.e., <code>x<sup>n</sup></code>).</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> x = 2.00000, n = 10
<strong>Output:</strong> 1024.00000
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> x = 2.10000, n = 3
<strong>Output:</strong> 9.26100
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> x = 2.00000, n = -2
<strong>Output:</strong> 0.25000
<strong>Explanation:</strong> 2<sup>-2</sup> = 1/2<sup>2</sup> = 1/4 = 0.25
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>-100.0 &lt; x &lt; 100.0</code></li>
	<li><code>-2<sup>31</sup> &lt;= n &lt;= 2<sup>31</sup>-1</code></li>
	<li><code>n</code> is an integer.</li>
	<li><code>-10<sup>4</sup> &lt;= x<sup>n</sup> &lt;= 10<sup>4</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def myPow(self, x: float, n: int) -> float:
        def qmi(a, k):
            res = 1
            while k:
                if k & 1:
                    res *= a
                a *= a
                k >>= 1
            return res

        return qmi(x, n) if n >= 0 else 1 / qmi(x, -n)
```

### **Java**

```java
class Solution {
    public double myPow(double x, int n) {
        long N = n;
        return n >= 0 ? qmi(x, N) : 1.0 / qmi(x, -N);
    }

    private double qmi(double a, long k) {
        double res = 1;
        while (k != 0) {
            if ((k & 1) != 0) {
                res *= a;
            }
            a *= a;
            k >>= 1;
        }
        return res;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    double myPow(double x, int n) {
        long long N = n;
        return N >= 0 ? qmi(x, N) : 1.0 / qmi(x, -N);
    }

    double qmi(double a, long long k) {
        double res = 1;
        while (k) {
            if (k & 1) {
                res *= a;
            }
            a *= a;
            k >>= 1;
        }
        return res;
    }
};
```

### **Go**

```go
func myPow(x float64, n int) float64 {
	if n >= 0 {
		return qmi(x, n)
	}
	return 1.0 / qmi(x, -n)
}

func qmi(a float64, k int) float64 {
	var res float64 = 1
	for k != 0 {
		if k&1 == 1 {
			res *= a
		}
		a *= a
		k >>= 1
	}
	return res
}
```

### **JavaScript**

```js
/**
 * @param {number} x
 * @param {number} n
 * @return {number}
 */
var myPow = function (x, n) {
    return n >= 0 ? qmi(x, n) : 1 / qmi(x, -n);
};

function qmi(a, k) {
    let res = 1;
    while (k) {
        if (k & 1) {
            res *= a;
        }
        a *= a;
        k >>>= 1;
    }
    return res;
}
```

### **TypeScript**

```ts
function myPow(x: number, n: number): number {
    return n >= 0 ? qmi(x, n) : 1 / qmi(x, -n);
}

function qmi(a: number, k: number): number {
    let res = 1;
    while (k) {
        if (k & 1) {
            res *= a;
        }
        a *= a;
        k >>>= 1;
    }
    return res;
}
```

### **C#**

```cs
public class Solution {
    public double MyPow(double x, int n) {
        long N = n;
        return n >= 0 ? qmi(x, N) : 1.0 / qmi(x, -N);
    }

    private double qmi(double a, long k) {
        double res = 1;
        while (k != 0) {
            if ((k & 1) != 0) {
                res *= a;
            }
            a *= a;
            k >>= 1;
        }
        return res;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
