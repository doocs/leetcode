# [2571. 将整数减少到零需要的最少操作数](https://leetcode.cn/problems/minimum-operations-to-reduce-an-integer-to-0)

[English Version](/solution/2500-2599/2571.Minimum%20Operations%20to%20Reduce%20an%20Integer%20to%200/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个正整数 <code>n</code> ，你可以执行下述操作 <strong>任意</strong> 次：</p>

<ul>
	<li><code>n</code> 加上或减去 <code>2</code> 的某个 <strong>幂</strong></li>
</ul>

<p>返回使 <code>n</code> 等于 <code>0</code> 需要执行的 <strong>最少</strong> 操作数。</p>

<p>如果 <code>x == 2<sup>i</sup></code> 且其中 <code>i &gt;= 0</code> ，则数字 <code>x</code> 是 <code>2</code> 的幂。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>n = 39
<strong>输出：</strong>3
<strong>解释：</strong>我们可以执行下述操作：
- n 加上 2<sup>0</sup> = 1 ，得到 n = 40 。
- n 减去 2<sup>3</sup> = 8 ，得到 n = 32 。
- n 减去 2<sup>5</sup> = 32 ，得到 n = 0 。
可以证明使 n 等于 0 需要执行的最少操作数是 3 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>n = 54
<strong>输出：</strong>3
<strong>解释：</strong>我们可以执行下述操作：
- n 加上 2<sup>1</sup> = 2 ，得到 n = 56 。
- n 加上 2<sup>3</sup> = 8 ，得到 n = 64 。
- n 减去 2<sup>6</sup> = 64 ，得到 n = 0 。
使 n 等于 0 需要执行的最少操作数是 3 。 
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：贪心 + 位运算**

我们将整数 $n$ 转换为二进制，从最低位开始：

-   如果当前位为 $1$，我们就累加当前连续的 $1$ 的个数；
-   如果当前位为 $0$，我们就判断当前连续的 $1$ 的个数是否超过 $0$。如果是，判断当前连续的 $1$ 的个数是否为 $1$，如果是，说明我们通过一次操作可以消除 $1$；如果大于 $1$，说明我们通过一次操作，可以把连续的 $1$ 的个数减少到 $1$。

最后，我们还需要判断当前连续的 $1$ 的个数是否为 $1$，如果是，说明我们通过一次操作可以消除 $1$；如果大于 $1$，我们通过两次操作，可以把连续的 $1$ 的消除。

时间复杂度 $O(\log n)$，空间复杂度 $O(1)$。其中 $n$ 为题目给定的整数。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def minOperations(self, n: int) -> int:
        ans = cnt = 0
        while n:
            if n & 1:
                cnt += 1
            elif cnt:
                ans += 1
                cnt = 0 if cnt == 1 else 1
            n >>= 1
        if cnt == 1:
            ans += 1
        elif cnt > 1:
            ans += 2
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int minOperations(int n) {
        int ans = 0, cnt = 0;
        for (; n > 0; n >>= 1) {
            if ((n & 1) == 1) {
                ++cnt;
            } else if (cnt > 0) {
                ++ans;
                cnt = cnt == 1 ? 0 : 1;
            }
        }
        ans += cnt == 1 ? 1 : 0;
        ans += cnt > 1 ? 2 : 0;
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int minOperations(int n) {
        int ans = 0, cnt = 0;
        for (; n > 0; n >>= 1) {
            if ((n & 1) == 1) {
                ++cnt;
            } else if (cnt > 0) {
                ++ans;
                cnt = cnt == 1 ? 0 : 1;
            }
        }
        ans += cnt == 1 ? 1 : 0;
        ans += cnt > 1 ? 2 : 0;
        return ans;
    }
};
```

### **Go**

```go
func minOperations(n int) (ans int) {
	cnt := 0
	for ; n > 0; n >>= 1 {
		if n&1 == 1 {
			cnt++
		} else if cnt > 0 {
			ans++
			if cnt == 1 {
				cnt = 0
			} else {
				cnt = 1
			}
		}
	}
	if cnt == 1 {
		ans++
	} else if cnt > 1 {
		ans += 2
	}
	return
}
```

### **...**

```

```

<!-- tabs:end -->
