# [1922. Count Good Numbers](https://leetcode.com/problems/count-good-numbers)

[中文文档](/solution/1900-1999/1922.Count%20Good%20Numbers/README.md)

## Description

<p>A digit string is <strong>good</strong> if the digits <strong>(0-indexed)</strong> at <strong>even</strong> indices are <strong>even</strong> and the digits at <strong>odd</strong> indices are <strong>prime</strong> (<code>2</code>, <code>3</code>, <code>5</code>, or <code>7</code>).</p>

<ul>
	<li>For example, <code>&quot;2582&quot;</code> is good because the digits (<code>2</code> and <code>8</code>) at even positions are even and the digits (<code>5</code> and <code>2</code>) at odd positions are prime. However, <code>&quot;3245&quot;</code> is <strong>not</strong> good because <code>3</code> is at an even index but is not even.</li>
</ul>

<p>Given an integer <code>n</code>, return <em>the <strong>total</strong> number of good digit strings of length </em><code>n</code>. Since the answer may be large, <strong>return it modulo </strong><code>10<sup>9</sup> + 7</code>.</p>

<p>A <strong>digit string</strong> is a string consisting of digits <code>0</code> through <code>9</code> that may contain leading zeros.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> n = 1
<strong>Output:</strong> 5
<strong>Explanation:</strong> The good numbers of length 1 are &quot;0&quot;, &quot;2&quot;, &quot;4&quot;, &quot;6&quot;, &quot;8&quot;.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 4
<strong>Output:</strong> 400
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> n = 50
<strong>Output:</strong> 564908303
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10<sup>15</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def countGoodNumbers(self, n: int) -> int:
        mod = 10**9 + 7

        def myPow(x, n):
            res = 1
            while n:
                if (n & 1) == 1:
                    res = res * x % mod
                x = x * x % mod
                n >>= 1
            return res

        return myPow(5, (n + 1) >> 1) * myPow(4, n >> 1) % mod
```

### **Java**

```java
class Solution {
    private int mod = 1000000007;

    public int countGoodNumbers(long n) {
        return (int) (myPow(5, (n + 1) >> 1) * myPow(4, n >> 1) % mod);
    }

    private long myPow(long x, long n) {
        long res = 1;
        while (n != 0) {
            if ((n & 1) == 1) {
                res = res * x % mod;
            }
            x = x * x % mod;
            n >>= 1;
        }
        return res;
    }
}
```

### **C++**

```cpp
int MOD = 1000000007;

class Solution {
public:
    int countGoodNumbers(long long n) {
        return (int)(myPow(5, (n + 1) >> 1) * myPow(4, n >> 1) % MOD);
    }

private:
    long long myPow(long long x, long long n) {
        long long res = 1;
        while (n) {
            if ((n & 1) == 1) {
                res = res * x % MOD;
            }
            x = x * x % MOD;
            n >>= 1;
        }
        return res;
    }
};
```

### **Go**

```go
const mod int64 = 1e9 + 7

func countGoodNumbers(n int64) int {
	return int(myPow(5, (n+1)>>1) * myPow(4, n>>1) % mod)
}

func myPow(x, n int64) int64 {
	var res int64 = 1
	for n != 0 {
		if (n & 1) == 1 {
			res = res * x % mod
		}
		x = x * x % mod
		n >>= 1
	}
	return res
}
```

### **...**

```

```

<!-- tabs:end -->
