# [1802. 有界数组中指定下标处的最大值](https://leetcode.cn/problems/maximum-value-at-a-given-index-in-a-bounded-array)

[English Version](/solution/1800-1899/1802.Maximum%20Value%20at%20a%20Given%20Index%20in%20a%20Bounded%20Array/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你三个正整数 <code>n</code>、<code>index</code> 和 <code>maxSum</code> 。你需要构造一个同时满足下述所有条件的数组 <code>nums</code>（下标 <strong>从 0 开始</strong> 计数）：</p>

<ul>
	<li><code>nums.length == n</code></li>
	<li><code>nums[i]</code> 是 <strong>正整数</strong> ，其中 <code>0 &lt;= i &lt; n</code></li>
	<li><code>abs(nums[i] - nums[i+1]) &lt;= 1</code> ，其中 <code>0 &lt;= i &lt; n-1</code></li>
	<li><code>nums</code> 中所有元素之和不超过 <code>maxSum</code></li>
	<li><code>nums[index]</code> 的值被 <strong>最大化</strong></li>
</ul>

<p>返回你所构造的数组中的 <code>nums[index]</code> 。</p>

<p>注意：<code>abs(x)</code> 等于 <code>x</code> 的前提是 <code>x &gt;= 0</code> ；否则，<code>abs(x)</code> 等于 <code>-x</code> 。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>n = 4, index = 2,  maxSum = 6
<strong>输出：</strong>2
<strong>解释：</strong>数组 [1,1,<strong>2</strong>,1] 和 [1,2,<strong>2</strong>,1] 满足所有条件。不存在其他在指定下标处具有更大值的有效数组。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>n = 6, index = 1,  maxSum = 10
<strong>输出：</strong>3
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= maxSum &lt;= 10<sup>9</sup></code></li>
	<li><code>0 &lt;= index &lt; n</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：二分查找**

根据题目描述，如果我们确定了 $nums[index]$ 的值为 $x$，此时我们可以找到一个最小的数组总和。也就是说，在 $index$ 左侧的数组元素从 $x-1$ 一直递减到 $1$，如果还有剩余的元素，那么剩余的元素都为 $1$；同理，在 $index$ 及右侧的数组元素从 $x$ 一直递减到 $1$，如果还有剩余的元素，那么剩余的元素都为 $1$。

这样我们就可以计算出数组的总和，如果总和小于等于 $maxSum$，那么此时的 $x$ 是合法的。随着 $x$ 的增大，数组的总和也会增大，因此我们可以使用二分查找的方法，找到一个最大的且符合条件的 $x$。

为了方便计算数组左侧、右侧的元素之和，我们定义一个函数 $sum(x, cnt)$，表示一共有 $cnt$ 个元素，且最大值为 $x$ 的数组的总和。函数 $sum(x, cnt)$ 可以分为两种情况：

-   如果 $x \geq cnt$，那么数组的总和为 $\frac{(x + x - cnt + 1) \times cnt}{2}$
-   如果 $x \lt cnt$，那么数组的总和为 $\frac{(x + 1) \times x}{2} + cnt - x$

接下来，定义二分的左边界 $left = 1$，右边界 $right = maxSum$，然后二分查找 $nums[index]$ 的值 $mid$，如果 $sum(mid - 1, index) + sum(mid, n - index) \leq maxSum$，那么此时的 $mid$ 是合法的，我们可以将 $left$ 更新为 $mid$，否则我们将 $right$ 更新为 $mid - 1$。

最后将 $left$ 作为答案返回即可。

时间复杂度 $O(\log M)$，空间复杂度 $O(1)$。其中 $M=maxSum$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def maxValue(self, n: int, index: int, maxSum: int) -> int:
        def sum(x, cnt):
            return (x + x - cnt + 1) * cnt // 2 if x >= cnt else (x + 1) * x // 2 + cnt - x

        left, right = 1, maxSum
        while left < right:
            mid = (left + right + 1) >> 1
            if sum(mid - 1, index) + sum(mid, n - index) <= maxSum:
                left = mid
            else:
                right = mid - 1
        return left
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int maxValue(int n, int index, int maxSum) {
        int left = 1, right = maxSum;
        while (left < right) {
            int mid = (left + right + 1) >>> 1;
            if (sum(mid - 1, index) + sum(mid, n - index) <= maxSum) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }

    private long sum(long x, int cnt) {
        return x >= cnt ? (x + x - cnt + 1) * cnt / 2 : (x + 1) * x / 2 + cnt - x;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int maxValue(int n, int index, int maxSum) {
        auto sum = [](long x, int cnt) {
            return x >= cnt ? (x + x - cnt + 1) * cnt / 2 : (x + 1) * x / 2 + cnt - x;
        };
        int left = 1, right = maxSum;
        while (left < right) {
            int mid = (left + right + 1) >> 1;
            if (sum(mid - 1, index) + sum(mid, n - index) <= maxSum) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }
};
```

### **Go**

```go
func maxValue(n int, index int, maxSum int) int {
	sum := func(x, cnt int) int {
		if x >= cnt {
			return (x + x - cnt + 1) * cnt / 2
		}
		return (x+1)*x/2 + cnt - x
	}
	return sort.Search(maxSum, func(x int) bool {
		x++
		return sum(x-1, index)+sum(x, n-index) > maxSum
	})
}
```

### **...**

```

```

<!-- tabs:end -->
