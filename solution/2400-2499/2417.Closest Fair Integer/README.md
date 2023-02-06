# [2417. 最近的公平整数](https://leetcode.cn/problems/closest-fair-integer)

[English Version](/solution/2400-2499/2417.Closest%20Fair%20Integer/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个 <strong>正整数</strong>&nbsp;<code>n</code>。</p>

<p>如果一个整数 <code>k</code> 中的&nbsp;<strong>偶数&nbsp;</strong>位数与<strong> 奇数</strong> 位数相等，那么我们称&nbsp;<code>k</code> 为公平整数。</p>

<p>返回&nbsp;<em><strong>大于或等于&nbsp;</strong></em><code>n</code><em> 的&nbsp;<strong>最小&nbsp;</strong>的公平整数。</em></p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入:</strong> n = 2
<strong>输出:</strong> 10
<strong>解释:</strong> 大于等于 2 的最小的公平整数是 10。
10是公平整数，因为它的偶数和奇数个数相等 (一个奇数和一个偶数)。</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入:</strong> n = 403
<strong>输出:</strong> 1001
<strong>解释:</strong> 大于或等于 403 的最小的公平整数是 1001。
1001 是公平整数，因为它有相等数量的偶数和奇数 (两个奇数和两个偶数)。
</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10<sup>9</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：分类讨论**

我们记 $n$ 的位数为 $k$，奇数位数、偶数位数分别为 $a$ 和 $b$。

-   若 $a=b$，则 $n$ 本身就是 `fair` 的，直接返回 $n$ 即可；
-   否则，若 $k$ 为奇数，那么我们找到 $k+1$ 位的最小 `fair` 数即可，形如 `10000111`；若 $k$ 为偶数，我们直接暴力递归 `closestFair(n+1)` 即可。

时间复杂度 $O(\sqrt{n} \times \log_{10} n)$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def closestFair(self, n: int) -> int:
        a = b = k = 0
        t = n
        while t:
            if (t % 10) & 1:
                a += 1
            else:
                b += 1
            t //= 10
            k += 1
        if k & 1:
            x = 10**k
            y = int('1' * (k >> 1) or '0')
            return x + y
        if a == b:
            return n
        return self.closestFair(n + 1)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int closestFair(int n) {
        int a = 0, b = 0;
        int k = 0, t = n;
        while (t > 0) {
            if ((t % 10) % 2 == 1) {
                ++a;
            } else {
                ++b;
            }
            t /= 10;
            ++k;
        }
        if (k % 2 == 1) {
            int x = (int) Math.pow(10, k);
            int y = 0;
            for (int i = 0; i<k> > 1; ++i) {
                y = y * 10 + 1;
            }
            return x + y;
        }
        if (a == b) {
            return n;
        }
        return closestFair(n + 1);
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int closestFair(int n) {
        int a = 0, b = 0;
        int t = n, k = 0;
        while (t) {
            if ((t % 10) & 1) {
                ++a;
            } else {
                ++b;
            }
            ++k;
            t /= 10;
        }
        if (a == b) {
            return n;
        }
        if (k % 2 == 1) {
            int x = pow(10, k);
            int y = 0;
            for (int i = 0; i < k >> 1; ++i) {
                y = y * 10 + 1;
            }
            return x + y;
        }
        return closestFair(n + 1);
    }
};
```

### **Go**

```go
func closestFair(n int) int {
	a, b := 0, 0
	t, k := n, 0
	for t > 0 {
		if (t%10)%2 == 1 {
			a++
		} else {
			b++
		}
		k++
		t /= 10
	}
	if a == b {
		return n
	}
	if k%2 == 1 {
		x := int(math.Pow(10, float64(k)))
		y := 0
		for i := 0; i < k>>1; i++ {
			y = y*10 + 1
		}
		return x + y
	}
	return closestFair(n + 1)
}
```

### **TypeScript**

```ts

```

### **...**

```

```

<!-- tabs:end -->
