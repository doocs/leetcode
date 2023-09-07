# [2847. Smallest Number With Given Digit Product](https://leetcode.cn/problems/smallest-number-with-given-digit-product)

[English Version](/solution/2800-2899/2847.Smallest%20Number%20With%20Given%20Digit%20Product/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>Given a <strong>positive</strong> integer <code>n</code>, return <em>a string representing the <strong>smallest positive</strong> integer such that the product of its digits is equal to</em> <code>n</code><em>, or </em><code>&quot;-1&quot;</code><em> if no such number exists</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> n = 105
<strong>Output:</strong> &quot;357&quot;
<strong>Explanation:</strong> 3 * 5 * 7 = 105. It can be shown that 357 is the smallest number with a product of digits equal to 105. So the answer would be &quot;105&quot;.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 7
<strong>Output:</strong> &quot;7&quot;
<strong>Explanation:</strong> Since 7 has only one digit, its product of digits would be 7. We will show that 7 is the smallest number with a product of digits equal to 7. Since the product of numbers 1 to 6 is 1 to 6 respectively, so &quot;7&quot; would be the answer.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> n = 44
<strong>Output:</strong> &quot;-1&quot;
<strong>Explanation:</strong> It can be shown that there is no number such that its product of digits is equal to 44. So the answer would be &quot;-1&quot;.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10<sup>18</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：质因数分解 + 贪心**

我们考虑对数字 $n$ 进行质因数分解，如果 $n$ 的质因数中存在大于 $9$ 的质数，那么一定无法找到符合条件的数字，因为大于 $9$ 的质数无法通过 $1$ 到 $9$ 的数字相乘得到，例如 $11$ 无法通过 $1$ 到 $9$ 的数字相乘得到，因此我们只需要考虑 $n$ 的质因数中是否存在大于 $9$ 的质数即可，如果存在，直接返回 $-1$。

否则，如果质因数中包含 $7$ 和 $5$，那么数字 $n$ 首先可以拆分为若干个 $7$ 和 $5$，两个数字 $3$ 可以合成一个数字 $9$，三个数字 $2$ 可以合成一个数字 $8$，数字 $2$ 和数字 $3$ 可以合成一个数字 $6$，因此我们只需要将数字拆分为 $[2,..9]$ 的数字即可，我们可以使用贪心的方法，优先拆分出数字 $9$，然后拆分出数字 $8$，依次类推。

时间复杂度 $O(\log n)$，空间复杂度 $O(1)$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def smallestNumber(self, n: int) -> str:
        cnt = [0] * 10
        for i in range(9, 1, -1):
            while n % i == 0:
                n //= i
                cnt[i] += 1
        if n > 1:
            return "-1"
        ans = "".join(str(i) * cnt[i] for i in range(2, 10))
        return ans if ans else "1"
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public String smallestNumber(long n) {
        int[] cnt = new int[10];
        for (int i = 9; i > 1; --i) {
            while (n % i == 0) {
                ++cnt[i];
                n /= i;
            }
        }
        if (n > 1) {
            return "-1";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 2; i < 10; ++i) {
            while (cnt[i] > 0) {
                sb.append(i);
                --cnt[i];
            }
        }
        String ans = sb.toString();
        return ans.isEmpty() ? "1" : ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    string smallestNumber(long long n) {
        int cnt[10]{};
        for (int i = 9; i > 1; --i) {
            while (n % i == 0) {
                n /= i;
                ++cnt[i];
            }
        }
        if (n > 1) {
            return "-1";
        }
        string ans;
        for (int i = 2; i < 10; ++i) {
            ans += string(cnt[i], '0' + i);
        }
        return ans == "" ? "1" : ans;
    }
};
```

### **Go**

```go
func smallestNumber(n int64) string {
	cnt := [10]int{}
	for i := 9; i > 1; i-- {
		for n%int64(i) == 0 {
			cnt[i]++
			n /= int64(i)
		}
	}
	if n != 1 {
		return "-1"
	}
	sb := &strings.Builder{}
	for i := 2; i < 10; i++ {
		for j := 0; j < cnt[i]; j++ {
			sb.WriteByte(byte(i) + '0')
		}
	}
	ans := sb.String()
	if len(ans) > 0 {
		return ans
	}
	return "1"
}
```

### **...**

```

```

<!-- tabs:end -->
