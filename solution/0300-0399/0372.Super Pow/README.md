# [372. 超级次方](https://leetcode.cn/problems/super-pow)

[English Version](/solution/0300-0399/0372.Super%20Pow/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>你的任务是计算 <code>a<sup>b</sup></code> 对 <code>1337</code> 取模，<code>a</code> 是一个正整数，<code>b</code> 是一个非常大的正整数且会以数组形式给出。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>a = 2, b = [3]
<strong>输出：</strong>8
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>a = 2, b = [1,0]
<strong>输出：</strong>1024
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>a = 1, b = [4,3,3,8,5,2]
<strong>输出：</strong>1
</pre>

<p><strong>示例 4：</strong></p>

<pre>
<strong>输入：</strong>a = 2147483647, b = [2,0,0]
<strong>输出：</strong>1198
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= a <= 2<sup>31</sup> - 1</code></li>
	<li><code>1 <= b.length <= 2000</code></li>
	<li><code>0 <= b[i] <= 9</code></li>
	<li><code>b</code> 不含前导 0</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

乘方快速幂。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
