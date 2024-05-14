# [1551. 使数组中所有元素相等的最小操作数](https://leetcode.cn/problems/minimum-operations-to-make-array-equal)

[English Version](/solution/1500-1599/1551.Minimum%20Operations%20to%20Make%20Array%20Equal/README_EN.md)

<!-- tags:数学 -->

<!-- difficulty:中等 -->

## 题目描述

<!-- 这里写题目描述 -->

<p>存在一个长度为 <code>n</code> 的数组 <code>arr</code> ，其中 <code>arr[i] = (2 * i) + 1</code> （ <code>0 &lt;= i &lt; n</code> ）。</p>

<p>一次操作中，你可以选出两个下标，记作 <code>x</code> 和 <code>y</code> （ <code>0 &lt;= x, y &lt; n</code> ）并使 <code>arr[x]</code> 减去 <code>1</code> 、<code>arr[y]</code> 加上 <code>1</code> （即 <code>arr[x] -=1 </code>且 <code>arr[y] += 1</code> ）。最终的目标是使数组中的所有元素都 <strong>相等</strong> 。题目测试用例将会 <strong>保证</strong> ：在执行若干步操作后，数组中的所有元素最终可以全部相等。</p>

<p>给你一个整数 <code>n</code>，即数组的长度。请你返回使数组 <code>arr</code> 中所有元素相等所需的 <strong>最小操作数</strong> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>n = 3
<strong>输出：</strong>2
<strong>解释：</strong>arr = [1, 3, 5]
第一次操作选出 x = 2 和 y = 0，使数组变为 [2, 3, 4]
第二次操作继续选出 x = 2 和 y = 0，数组将会变成 [3, 3, 3]
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>n = 6
<strong>输出：</strong>9
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10^4</code></li>
</ul>

## 解法

### 方法一：数学

根据题目描述，数组 $arr$ 是一个首项为 $1$，公差为 $2$ 的等差数列。那么数组前 $n$ 项的和为：

$$
\begin{aligned}
S_n &= \frac{n}{2} \times (a_1 + a_n) \\
&= \frac{n}{2} \times (1 + (2n - 1)) \\
&= n^2
\end{aligned}
$$

由于一次操作中，一个数减一，另一个数加一，数组中所有元素的和不变。因此，数组中所有元素相等时，每个元素的值为 $S_n / n = n$。那么，数组中所有元素相等所需的最小操作数为：

$$
\sum_{i=0}{\frac{n}{2}} (n - (2i + 1))
$$

时间复杂度 $O(n)$，其中 $n$ 为数组长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

```python
class Solution:
    def minOperations(self, n: int) -> int:
        return sum(n - (i << 1 | 1) for i in range(n >> 1))
```

```java
class Solution {
    public int minOperations(int n) {
        int ans = 0;
        for (int i = 0; i < n >> 1; ++i) {
            ans += n - (i << 1 | 1);
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int minOperations(int n) {
        int ans = 0;
        for (int i = 0; i < n >> 1; ++i) {
            ans += n - (i << 1 | 1);
        }
        return ans;
    }
};
```

```go
func minOperations(n int) (ans int) {
	for i := 0; i < n>>1; i++ {
		ans += n - (i<<1 | 1)
	}
	return
}
```

```ts
function minOperations(n: number): number {
    let ans = 0;
    for (let i = 0; i < n >> 1; ++i) {
        ans += n - ((i << 1) | 1);
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- end -->
