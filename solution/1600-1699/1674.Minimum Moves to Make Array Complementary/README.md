# [1674. 使数组互补的最少操作次数](https://leetcode.cn/problems/minimum-moves-to-make-array-complementary)

[English Version](/solution/1600-1699/1674.Minimum%20Moves%20to%20Make%20Array%20Complementary/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个长度为<strong> 偶数</strong> <code>n</code> 的整数数组 <code>nums</code> 和一个整数 <code>limit</code> 。每一次操作，你可以将 <code>nums</code> 中的任何整数替换为 <code>1</code> 到 <code>limit</code> 之间的另一个整数。</p>

<p>如果对于所有下标 <code>i</code>（<strong>下标从 </strong><code>0</code><strong> 开始</strong>），<code>nums[i] + nums[n - 1 - i]</code> 都等于同一个数，则数组 <code>nums</code> 是 <strong>互补的</strong> 。例如，数组 <code>[1,2,3,4]</code> 是互补的，因为对于所有下标 <code>i</code> ，<code>nums[i] + nums[n - 1 - i] = 5</code> 。</p>

<p>返回使数组 <strong>互补</strong> 的 <strong>最少</strong> 操作次数。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,2,4,3], limit = 4
<strong>输出：</strong>1
<strong>解释：</strong>经过 1 次操作，你可以将数组 nums 变成 [1,2,<strong>2</strong>,3]（加粗元素是变更的数字）：
nums[0] + nums[3] = 1 + 3 = 4.
nums[1] + nums[2] = 2 + 2 = 4.
nums[2] + nums[1] = 2 + 2 = 4.
nums[3] + nums[0] = 3 + 1 = 4.
对于每个 i ，nums[i] + nums[n-1-i] = 4 ，所以 nums 是互补的。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,2,2,1], limit = 2
<strong>输出：</strong>2
<strong>解释：</strong>经过 2 次操作，你可以将数组 nums 变成 [<strong>2</strong>,2,2,<strong>2</strong>] 。你不能将任何数字变更为 3 ，因为 3 > limit 。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,2,1,2], limit = 2
<strong>输出：</strong>0
<strong>解释：</strong>nums 已经是互补的。
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == nums.length</code></li>
	<li><code>2 <= n <= 10<sup>5</sup></code></li>
	<li><code>1 <= nums[i] <= limit <= 10<sup>5</sup></code></li>
	<li><code>n</code> 是偶数。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：差分数组**

我们不妨设 $a$ 为 $nums[i]$ 和 $nums[n-i-1]$ 的较小值，设 $b$ 为 $nums[i]$ 和 $nums[n-i-1]$ 的较大值。

假设经过替换后，两数之和为 $x$。由题意，我们知道 $x$ 最小值为 $2$，即两个数替换为 $1$；最大值为 $2 \times limit$，即两个数都替换为 $limit$。因此 $x$ 的取值范围是 $[2,... 2 \times limit]$。

如何求出对于不同的 $x$，需要替换的最少次数呢？

我们分析发现：

-   如果 $x = a + b$，那么我们需要替换的次数为 $0$，即当前的数对已经满足互补的要求；
-   否则如果 $1 + a \le x \le limit + b $，那么我们需要替换的次数为 $1$，即把其中一个数替换即可；
-   否则如果 $2 \le x \le 2 \times limit$，那么我们需要替换的次数为 $2$，即把两个数都替换。

因此，我们可以遍历每一对数，执行如下操作：

1. 先将 $[2,... 2 \times limit]$ 范围需要的操作次数加 $2$。
1. 再将 $[1 + a,... limit + b]$ 范围需要的操作次数减 $1$。
1. 最后将 $[a + b,... a + b]$ 范围需要的操作次数减 $1$。

可以发现，这实际上是在对一个连续区间内的元素进行加减操作，因此我们可以使用差分数组来实现。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为数组 `nums` 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def minMoves(self, nums: List[int], limit: int) -> int:
        d = [0] * (limit * 2 + 2)
        n = len(nums)

        for i in range(n >> 1):
            a, b = min(nums[i], nums[n - i - 1]), max(nums[i], nums[n - i - 1])

            d[2] += 2
            d[limit * 2 + 1] -= 2

            d[a + 1] -= 1
            d[b + limit + 1] += 1

            d[a + b] -= 1
            d[a + b + 1] += 1

        ans, s = n, 0
        for v in d[2: limit * 2 + 1]:
            s += v
            if ans > s:
                ans = s
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int minMoves(int[] nums, int limit) {
        int n = nums.length;
        int[] d = new int[limit * 2 + 2];
        for (int i = 0; i<n> > 1; ++i) {
            int a = Math.min(nums[i], nums[n - i - 1]);
            int b = Math.max(nums[i], nums[n - i - 1]);

            d[2] += 2;
            d[limit * 2 + 1] -= 2;

            d[a + 1] -= 1;
            d[b + limit + 1] += 1;

            d[a + b] -= 1;
            d[a + b + 1] += 1;
        }
        int ans = n, s = 0;
        for (int i = 2; i <= limit * 2; ++i) {
            s += d[i];
            if (ans > s) {
                ans = s;
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
    int minMoves(vector<int>& nums, int limit) {
        int n = nums.size();
        vector<int> d(limit * 2 + 2);
        for (int i = 0; i < n >> 1; ++i) {
            int a = min(nums[i], nums[n - i - 1]);
            int b = max(nums[i], nums[n - i - 1]);

            d[2] += 2;
            d[limit * 2 + 1] -= 2;

            d[a + 1] -= 1;
            d[b + limit + 1] += 1;

            d[a + b] -= 1;
            d[a + b + 1] += 1;
        }
        int ans = n, s = 0;
        for (int i = 2; i <= limit * 2; ++i) {
            s += d[i];
            if (ans > s) {
                ans = s;
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func minMoves(nums []int, limit int) int {
	d := make([]int, limit*2+2)
	n := len(nums)
	for i := 0; i < n>>1; i++ {
		a, b := min(nums[i], nums[n-i-1]), max(nums[i], nums[n-i-1])
		d[2] += 2
		d[limit*2+1] -= 2

		d[a+1] -= 1
		d[b+limit+1] += 1

		d[a+b] -= 1
		d[a+b+1] += 1
	}
	ans, s := n, 0
	for _, v := range d[2 : limit*2+1] {
		s += v
		if ans > s {
			ans = s
		}
	}
	return ans
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}
```

### **...**

```

```

<!-- tabs:end -->
