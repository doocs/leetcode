# [793. 阶乘函数后 K 个零](https://leetcode.cn/problems/preimage-size-of-factorial-zeroes-function)

[English Version](/solution/0700-0799/0793.Preimage%20Size%20of%20Factorial%20Zeroes%20Function/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>&nbsp;<code>f(x)</code>&nbsp;是&nbsp;<code>x!</code>&nbsp;末尾是 0 的数量。回想一下&nbsp;<code>x! = 1 * 2 * 3 * ... * x</code>，且 <code>0! = 1</code>&nbsp;。</p>

<ul>
	<li>例如，&nbsp;<code>f(3) = 0</code>&nbsp;，因为 <code>3! = 6</code> 的末尾没有 0 ；而 <code>f(11) = 2</code>&nbsp;，因为 <code>11!= 39916800</code> 末端有 2 个 0 。</li>
</ul>

<p>给定&nbsp;<code>k</code>，找出返回能满足 <code>f(x) = k</code>&nbsp;的非负整数 <code>x</code>&nbsp;的数量。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong><strong> </strong></p>

<pre>
<strong>输入：</strong>k = 0<strong>
输出：</strong>5<strong>
解释：</strong>0!, 1!, 2!, 3!, 和 4!&nbsp;均符合 k = 0 的条件。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>k = 5
<strong>输出：</strong>0
<strong>解释：</strong>没有匹配到这样的 x!，符合 k = 5 的条件。</pre>

<p><strong>示例 3:</strong></p>

<pre>
<strong>输入:</strong> k = 3
<strong>输出:</strong> 5
</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>0 &lt;= k &lt;= 10<sup>9</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：二分查找**

定义 $f(x)$ 为 $x!$ 末尾零的个数，那么

$$
f(x)=
\begin{cases}
0, x=0\\
x/5+f(x/5), x>0
\end{cases}
$$

定义 $g(k)$ 表示 $x!$ 末尾为零的个数为 $k$ 的最小的 $x$ 值，那么题目等价于求解 $g(k+1)-g(k)$。

由于 $g(k)$ 是单调递增的，因此可以使用二分查找求解 $g(k)$。

同时，由于 $f(x)=x/5+f(x/5) \ge x/5$，因此 $f(5k)\ge k$。所以，求解 $g(k)$ 时，二分的右边界可以取 $5k$。

时间复杂度 $O(log^2k)$，其中 $k$ 为题目给定的整数。二分查找 $g(k)$ 的时间复杂度为 $O(logk)$，计算 $f(x)$ 的时间复杂度为 $O(logx)$，因此总时间复杂度为 $O(log^2k)$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
