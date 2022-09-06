# [829. 连续整数求和](https://leetcode.cn/problems/consecutive-numbers-sum)

[English Version](/solution/0800-0899/0829.Consecutive%20Numbers%20Sum/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个正整数 <code>n</code>，返回 <em>连续正整数满足所有数字之和为 <code>n</code>&nbsp;的组数</em> 。&nbsp;</p>

<p>&nbsp;</p>

<p><strong>示</strong><strong>例 1:</strong></p>

<pre>
<strong>输入: </strong>n = 5
<strong>输出: </strong>2
<strong>解释: </strong>5 = 2 + 3，共有两组连续整数([5],[2,3])求和后为 5。</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入: </strong>n = 9
<strong>输出: </strong>3
<strong>解释: </strong>9 = 4 + 5 = 2 + 3 + 4</pre>

<p><strong>示例 3:</strong></p>

<pre>
<strong>输入: </strong>n = 15
<strong>输出: </strong>4
<strong>解释: </strong>15 = 8 + 7 = 4 + 5 + 6 = 1 + 2 + 3 + 4 + 5</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10<sup>9</sup></code>​​​​​​​</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：数学推导**

连续正整数构成一个等差数列($d=1$)。我们假设等差数列的第一项为 $a$，项数为 $k$，则 $n=(a+a+k-1)*k/2$，即 $n*2=(a*2+k-1)*k$ ①。

由于是连续正整数， $a>=1$，所以 ① 可以化为 $n*2>=(k+1)*k$，即 $k*(k+1)<=n*2$ ②。

因此，$k$ 的范围需要满足 $k>=1$ 并且 $k*(k+1)<=n*2$。另外，我们从 ① 式可以发现，$k$ 必须能整除 $n*2$。

综上，我们枚举 $k$，累加满足条件的 $k$ 的个数即可。

时间复杂度 $O(\sqrt{n})$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def consecutiveNumbersSum(self, n: int) -> int:
        n <<= 1
        ans, k = 0, 1
        while k * (k + 1) <= n:
            if n % k == 0 and (n // k + 1 - k) % 2 == 0:
                ans += 1
            k += 1
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {

    public int consecutiveNumbersSum(int n) {
        n <<= 1;
        int ans = 0;
        for (int k = 1; k * (k + 1) <= n; ++k) {
            if (n % k == 0 && (n / k + 1 - k) % 2 == 0) {
                ++ans;
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
    int consecutiveNumbersSum(int n) {
        n <<= 1;
        int ans = 0;
        for (int k = 1; k * (k + 1) <= n; ++k) {
            if (n % k == 0 && (n / k + 1 - k) % 2 == 0) {
                ++ans;
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func consecutiveNumbersSum(n int) int {
	n <<= 1
	ans := 0
	for k := 1; k*(k+1) <= n; k++ {
		if n%k == 0 && (n/k+1-k)%2 == 0 {
			ans++
		}
	}
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
