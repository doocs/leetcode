# [2533. 好二进制字符串的数量](https://leetcode.cn/problems/number-of-good-binary-strings)

[English Version](/solution/2500-2599/2533.Number%20of%20Good%20Binary%20Strings/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p><span style="">给你四个整数 </span><code>minLenght</code>、<code>maxLength</code>、<code>oneGroup</code><span style=""> 和 </span><code>zeroGroup</code><span style=""> 。</span></p>

<p><strong>好 </strong>二进制字符串满足下述条件：</p>

<ul>
	<li>字符串的长度在 <code>[minLength, maxLength]</code> 之间。</li>
	<li>每块连续 <code>1</code> 的个数是 <code>oneGroup</code> 的整数倍
	<ul>
		<li>例如在二进制字符串 <code>00<em><strong>11</strong></em>0<em><strong>1111</strong></em>00</code> 中，每块连续 <code>1</code> 的个数分别是<code>[2,4]</code> 。</li>
	</ul>
	</li>
	<li>每块连续 <code>0</code> 的个数是 <code>zeroGroup</code> 的整数倍
	<ul>
		<li>例如在二进制字符串 <code><em><strong>00</strong></em>11<em><strong>0</strong></em>1111<em><strong>00</strong></em></code> 中，每块连续 <code>0</code> 的个数分别是 <code>[2,1,2]</code> 。</li>
	</ul>
	</li>
</ul>

<p>请返回好二进制字符串的个数。答案可能很大<strong>，</strong>请返回对 <code>10<sup>9</sup> + 7</code> 取余后的结果。</p>

<p><strong>注意：</strong><code>0</code> 可以被认为是所有数字的倍数。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>minLength = 2, maxLength = 3, oneGroup = 1, zeroGroup = 2
<strong>输出：</strong>5
<strong>解释：</strong>在本示例中有 5 个好二进制字符串: "00", "11", "001", "100", 和 "111" 。
可以证明只有 5 个好二进制字符串满足所有的条件。</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>minLength = 4, maxLength = 4, oneGroup = 4, zeroGroup = 3
<strong>输出：</strong>1
<strong>解释：</strong>在本示例中有 1 个好二进制字符串: "1111" 。
可以证明只有 1 个好字符串满足所有的条件。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= minLength &lt;= maxLength &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= oneGroup, zeroGroup &lt;= maxLength</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：动态规划**

我们定义 $f[i]$ 表示长度为 $i$ 的字符串中满足条件的个数。状态转移方程为：

$$
f[i] = \begin{cases}
1 & i = 0 \\
f[i - oneGroup] + f[i - zeroGroup] & i \geq 1
\end{cases}
$$

最终答案为 $f[minLength] + f[minLength + 1] + \cdots + f[maxLength]$。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n=maxLength$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def goodBinaryStrings(self, minLength: int, maxLength: int, oneGroup: int, zeroGroup: int) -> int:
        mod = 10**9 + 7
        f = [1] + [0] * maxLength
        for i in range(1, len(f)):
            if i - oneGroup >= 0:
                f[i] += f[i - oneGroup]
            if i - zeroGroup >= 0:
                f[i] += f[i - zeroGroup]
            f[i] %= mod
        return sum(f[minLength:]) % mod
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int goodBinaryStrings(int minLength, int maxLength, int oneGroup, int zeroGroup) {
        final int mod = (int) 1e9 + 7;
        int[] f = new int[maxLength + 1];
        f[0] = 1;
        for (int i = 1; i <= maxLength; ++i) {
            if (i - oneGroup >= 0) {
                f[i] = (f[i] + f[i - oneGroup]) % mod;
            }
            if (i - zeroGroup >= 0) {
                f[i] = (f[i] + f[i - zeroGroup]) % mod;
            }
        }
        int ans = 0;
        for (int i = minLength; i <= maxLength; ++i) {
            ans = (ans + f[i]) % mod;
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int goodBinaryStrings(int minLength, int maxLength, int oneGroup, int zeroGroup) {
        const int mod = 1e9 + 7;
        int f[maxLength + 1];
        memset(f, 0, sizeof f);
        f[0] = 1;
        for (int i = 1; i <= maxLength; ++i) {
            if (i - oneGroup >= 0) {
                f[i] = (f[i] + f[i - oneGroup]) % mod;
            }
            if (i - zeroGroup >= 0) {
                f[i] = (f[i] + f[i - zeroGroup]) % mod;
            }
        }
        int ans = 0;
        for (int i = minLength; i <= maxLength; ++i) {
            ans = (ans + f[i]) % mod;
        }
        return ans;
    }
};
```

### **Go**

```go
func goodBinaryStrings(minLength int, maxLength int, oneGroup int, zeroGroup int) (ans int) {
	const mod int = 1e9 + 7
	f := make([]int, maxLength+1)
	f[0] = 1
	for i := 1; i <= maxLength; i++ {
		if i-oneGroup >= 0 {
			f[i] += f[i-oneGroup]
		}
		if i-zeroGroup >= 0 {
			f[i] += f[i-zeroGroup]
		}
		f[i] %= mod
	}
	for _, v := range f[minLength:] {
		ans = (ans + v) % mod
	}
	return
}
```

### **...**

```

```

<!-- tabs:end -->
