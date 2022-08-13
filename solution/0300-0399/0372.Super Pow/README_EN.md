# [372. Super Pow](https://leetcode.com/problems/super-pow)

[中文文档](/solution/0300-0399/0372.Super%20Pow/README.md)

## Description

<p>Your task is to calculate <code>a<sup>b</sup></code> mod <code>1337</code> where <code>a</code> is a positive integer and <code>b</code> is an extremely large positive integer given in the form of an array.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> a = 2, b = [3]
<strong>Output:</strong> 8
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> a = 2, b = [1,0]
<strong>Output:</strong> 1024
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> a = 1, b = [4,3,3,8,5,2]
<strong>Output:</strong> 1
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= a &lt;= 2<sup>31</sup> - 1</code></li>
	<li><code>1 &lt;= b.length &lt;= 2000</code></li>
	<li><code>0 &lt;= b[i] &lt;= 9</code></li>
	<li><code>b</code> does not contain leading zeros.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def superPow(self, a: int, b: List[int]) -> int:
        MOD = 1337
        ans = 1
        for e in b[::-1]:
            ans = ans * pow(a, e, MOD) % MOD
            a = pow(a, 10, MOD)
        return ans
```

### **Java**

```java
class Solution {
    private static final int MOD = 1337;

    public int superPow(int a, int[] b) {
        int ans = 1;
        for (int i = b.length - 1; i >= 0; --i) {
            ans = (int) ((long) ans * quickPowAndMod(a, b[i]) % MOD);
            a = quickPowAndMod(a, 10);
        }
        return ans;
    }

    private int quickPowAndMod(int a, int b) {
        int ans = 1;
        while (b > 0) {
            if ((b & 1) == 1) {
                ans = (ans * (a % MOD)) % MOD;
            }
            b >>= 1;
            a = (a % MOD) * (a % MOD) % MOD;
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
    const int MOD = 1337;

public:
    int superPow(int a, vector<int>& b) {
        int ans = 1;
        for (int i = b.size() - 1; i >= 0; --i) {
            ans = (long)ans * quickPowAndMod(a, b[i]) % MOD;
            a = quickPowAndMod(a, 10);
        }
        return ans;
    }

    int quickPowAndMod(int a, int b) {
        int ans = 1;
        while (b) {
            if (b & 1) {
                ans = (ans * (a % MOD)) % MOD;
            }
            b >>= 1;
            a = ((a % MOD) * (a % MOD)) % MOD;
        }
        return ans;
    }
};
```

### **Go**

```go
const mod = 1337

func superPow(a int, b []int) int {
	ans := 1
	for i := len(b) - 1; i >= 0; i-- {
		ans = ans * quickPowAndMod(a, b[i]) % mod
		a = quickPowAndMod(a, 10)
	}
	return ans
}

func quickPowAndMod(a, b int) int {
	ans := 1
	for b > 0 {
		if b&1 > 0 {
			ans = ans * a % mod
		}
		b >>= 1
		a = ((a % mod) * (a % mod)) % mod
	}
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
