# [204. 计数质数](https://leetcode.cn/problems/count-primes)

[English Version](/solution/0200-0299/0204.Count%20Primes/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定整数 <code>n</code> ，返回 <em>所有小于非负整数&nbsp;<code>n</code>&nbsp;的质数的数量</em> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>n = 10
<strong>输出：</strong>4
<strong>解释：</strong>小于 10 的质数一共有 4 个, 它们是 2, 3, 5, 7 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>n = 0
<strong>输出：</strong>0
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>n = 1
<strong>输出</strong>：0
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>0 &lt;= n &lt;= 5 * 10<sup>6</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：埃氏筛**

如果 $x$ 是质数，那么大于 $x$ 的 $x$ 的倍数 $2x$,$3x$,… 一定不是质数，因此我们可以从这里入手。

设 $primes[i]$ 表示数 $i$ 是不是质数，如果是质数则为 $true$，否则为 $false$。

我们在 $[2,n]$ 范围内顺序遍历每个数 $i$，如果这个数为质数（$primes[i]==true$），质数个数增 1，然后将其所有的倍数 $j$ 都标记为合数（除了该质数本身），即 $primes[j]=false$，这样在运行结束的时候我们即能知道质数的个数。

时间复杂度 $O(nloglogn)$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def countPrimes(self, n: int) -> int:
        primes = [True] * n
        ans = 0
        for i in range(2, n):
            if primes[i]:
                ans += 1
                for j in range(i + i, n, i):
                    primes[j] = False
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int countPrimes(int n) {
        boolean[] primes = new boolean[n];
        Arrays.fill(primes, true);
        int ans = 0;
        for (int i = 2; i < n; ++i) {
            if (primes[i]) {
                ++ans;
                for (int j = i + i; j < n; j += i) {
                    primes[j] = false;
                }
            }
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int countPrimes(int n) {
        vector<bool> primes(n, true);
        int ans = 0;
        for (int i = 2; i < n; ++i) {
            if (primes[i]) {
                ++ans;
                for (int j = i; j < n; j += i) primes[j] = false;
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func countPrimes(n int) int {
	primes := make([]bool, n)
	for i := range primes {
		primes[i] = true
	}
	ans := 0
	for i := 2; i < n; i++ {
		if primes[i] {
			ans++
			for j := i + i; j < n; j += i {
				primes[j] = false
			}
		}
	}
	return ans
}
```

### **JavaScript**

```js
/**
 * @param {number} n
 * @return {number}
 */
var countPrimes = function (n) {
    let primes = new Array(n).fill(true);
    let ans = 0;
    for (let i = 2; i < n; ++i) {
        if (primes[i]) {
            ++ans;
            for (let j = i + i; j < n; j += i) {
                primes[j] = false;
            }
        }
    }
    return ans;
};
```

### **C#**

```cs
public class Solution {
    public int CountPrimes(int n) {
        var notPrimes = new bool[n];
        int ans = 0;
        for (int i = 2; i < n; ++i)
        {
            if (!notPrimes[i])
            {
                ++ans;
                for (int j = i + i; j < n; j += i)
                {
                    notPrimes[j] = true;
                }
            }
        }
        return ans;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
