# [1999. 最小的仅由两个数组成的倍数](https://leetcode.cn/problems/smallest-greater-multiple-made-of-two-digits)

[English Version](/solution/1900-1999/1999.Smallest%20Greater%20Multiple%20Made%20of%20Two%20Digits/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你三个整数, <code>k</code>, <code>digit1</code>和&nbsp;<code>digit2</code>, 你想要找到满足以下条件的 <strong>最小 </strong>整数：</p>

<ul>
	<li><span style=""><b>大于</b></span><code>k</code> 且是 <code>k</code> 的<strong>倍数</strong></li>
	<li><strong>仅由</strong><code>digit1</code> <span style="">和 </span><code>digit2</code> 组成，即 <strong>每一位数 </strong>均是 <code>digit1</code> 或 <code>digit2</code></li>
</ul>

<p>请你返回<strong> </strong><strong>最小的满足这两个条件的整数</strong>，如果不存在这样的整数，或者最小的满足这两个条件的整数不在32位整数范围（0~<code>2<sup>31</sup>-1</code>），就返回 <code>-1</code> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>k = 2, digit1 = 0, digit2 = 2
<strong>输出：</strong>20
<strong>解释：</strong>
20 是第一个仅有数字0和2组成的，比2大且是2的倍数的整数。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>k = 3, digit1 = 4, digit2 = 2
<strong>输出：</strong>24
<strong>解释：</strong>
24 是第一个仅有数字 2 和 4 组成的，比 3 大且是 3 的倍数的整数。</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>k = 2, digit1 = 0, digit2 = 0
<strong>输出：</strong>-1
<strong>解释：
</strong>不存在仅由 0 组成的比 2 大且是 2 的倍数的整数，因此返回 -1 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= k &lt;= 1000</code></li>
	<li><code>0 &lt;= digit1 &lt;= 9</code></li>
	<li><code>0 &lt;= digit2 &lt;= 9</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：BFS**

我们观察 $k$ 的范围，发现 $1 \leq k \leq 1000$，因此，如果 $digit1$ 和 $digit2$ 都为 $0$，那么一定不存在满足条件的整数，直接返回 $-1$ 即可。

否则，我们不妨设 $digit1 \leq digit2$，接下来我们可以使用 BFS 的方法，初始时将整数 $0$ 入队，然后不断地从队首取出一个整数 $x$，如果 $x \gt 2^{31} - 1$，那么说明不存在满足条件的整数，直接返回 $-1$ 即可。如果 $x \gt k$ 且 $x \bmod k = 0$，那么说明找到了满足条件的整数，直接返回 $x$ 即可。否则，我们将其乘以 $10$ 后加上 $digit1$ 和 $digit2$，并将这两个整数入队，继续进行搜索。

时间复杂度 $(\log_{10} M)$，空间复杂度 $O(\log_{10} M)$，其中 $M$ 为 $2^{31} - 1$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def findInteger(self, k: int, digit1: int, digit2: int) -> int:
        if digit1 == 0 and digit2 == 0:
            return -1
        if digit1 > digit2:
            return self.findInteger(k, digit2, digit1)
        q = deque([0])
        while 1:
            x = q.popleft()
            if x > 2**31 - 1:
                return -1
            if x > k and x % k == 0:
                return x
            q.append(x * 10 + digit1)
            if digit1 != digit2:
                q.append(x * 10 + digit2)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int findInteger(int k, int digit1, int digit2) {
        if (digit1 == 0 && digit2 == 0) {
            return -1;
        }
        if (digit1 > digit2) {
            return findInteger(k, digit2, digit1);
        }
        Deque<Long> q = new ArrayDeque<>();
        q.offer(0L);
        while (true) {
            long x = q.poll();
            if (x > Integer.MAX_VALUE) {
                return -1;
            }
            if (x > k && x % k == 0) {
                return (int) x;
            }
            q.offer(x * 10 + digit1);
            if (digit1 != digit2) {
                q.offer(x * 10 + digit2);
            }
        }
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int findInteger(int k, int digit1, int digit2) {
        if (digit1 == 0 && digit2 == 0) {
            return -1;
        }
        if (digit1 > digit2) {
            swap(digit1, digit2);
        }
        queue<long long> q{{0}};
        while (1) {
            long long x = q.front();
            q.pop();
            if (x > INT_MAX) {
                return -1;
            }
            if (x > k && x % k == 0) {
                return x;
            }
            q.emplace(x * 10 + digit1);
            if (digit1 != digit2) {
                q.emplace(x * 10 + digit2);
            }
        }
    }
};
```

### **Go**

```go
func findInteger(k int, digit1 int, digit2 int) int {
	if digit1 == 0 && digit2 == 0 {
		return -1
	}
	if digit1 > digit2 {
		digit1, digit2 = digit2, digit1
	}
	q := []int{0}
	for {
		x := q[0]
		q = q[1:]
		if x > math.MaxInt32 {
			return -1
		}
		if x > k && x%k == 0 {
			return x
		}
		q = append(q, x*10+digit1)
		if digit1 != digit2 {
			q = append(q, x*10+digit2)
		}
	}
}
```

### **...**

```

```

<!-- tabs:end -->
