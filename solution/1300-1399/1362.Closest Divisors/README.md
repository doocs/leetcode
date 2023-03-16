# [1362. 最接近的因数](https://leetcode.cn/problems/closest-divisors)

[English Version](/solution/1300-1399/1362.Closest%20Divisors/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个整数&nbsp;<code>num</code>，请你找出同时满足下面全部要求的两个整数：</p>

<ul>
	<li>两数乘积等于 &nbsp;<code>num + 1</code>&nbsp;或&nbsp;<code>num + 2</code></li>
	<li>以绝对差进行度量，两数大小最接近</li>
</ul>

<p>你可以按任意顺序返回这两个整数。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>num = 8
<strong>输出：</strong>[3,3]
<strong>解释：</strong>对于 num + 1 = 9，最接近的两个因数是 3 &amp; 3；对于 num + 2 = 10, 最接近的两个因数是 2 &amp; 5，因此返回 3 &amp; 3 。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>num = 123
<strong>输出：</strong>[5,25]
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>num = 999
<strong>输出：</strong>[40,25]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= num &lt;= 10^9</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：枚举**

我们设计一个函数 $f(x)$，该函数返回乘积等于 $x$ 的两个数，且这两个数的差的绝对值最小。我们可以从 $\sqrt{x}$ 开始枚举 $i$，如果 $x$ 能被 $i$ 整除，那么 $\frac{x}{i}$ 就是另一个因数，此时我们就找到了一个乘积等于 $x$ 的两个因数，我们将其返回即可。否则我们减小 $i$ 的值，继续枚举。

接下来，我们只需要分别计算 $f(num + 1)$ 和 $f(num + 2)$，然后比较两个函数的返回值，返回差的绝对值更小的那个即可。

时间复杂度 $O(\sqrt{num})$，空间复杂度 $O(1)$。其中 $num$ 是给定的整数。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def closestDivisors(self, num: int) -> List[int]:
        def f(x):
            for i in range(int(sqrt(x)), 0, -1):
                if x % i == 0:
                    return [i, x // i]

        a = f(num + 1)
        b = f(num + 2)
        return a if abs(a[0] - a[1]) < abs(b[0] - b[1]) else b
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int[] closestDivisors(int num) {
        int[] a = f(num + 1);
        int[] b = f(num + 2);
        return Math.abs(a[0] - a[1]) < Math.abs(b[0] - b[1]) ? a : b;
    }

    private int[] f(int x) {
        for (int i = (int) Math.sqrt(x);; --i) {
            if (x % i == 0) {
                return new int[] {i, x / i};
            }
        }
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<int> closestDivisors(int num) {
        auto f = [](int x) {
            for (int i = sqrt(x);; --i) {
                if (x % i == 0) {
                    return vector<int>{i, x / i};
                }
            }
        };
        vector<int> a = f(num + 1);
        vector<int> b = f(num + 2);
        return abs(a[0] - a[1]) < abs(b[0] - b[1]) ? a : b;
    }
};
```

### **Go**

```go
func closestDivisors(num int) []int {
	f := func(x int) []int {
		for i := int(math.Sqrt(float64(x))); ; i-- {
			if x%i == 0 {
				return []int{i, x / i}
			}
		}
	}
	a, b := f(num+1), f(num+2)
	if abs(a[0]-a[1]) < abs(b[0]-b[1]) {
		return a
	}
	return b
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}
```

### **...**

```

```

<!-- tabs:end -->
