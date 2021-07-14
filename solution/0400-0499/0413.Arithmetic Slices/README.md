# [413. 等差数列划分](https://leetcode-cn.com/problems/arithmetic-slices)

[English Version](/solution/0400-0499/0413.Arithmetic%20Slices/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>如果一个数列至少有三个元素，并且任意两个相邻元素之差相同，则称该数列为等差数列。</p>

<p>例如，以下数列为等差数列:</p>

<pre>
1, 3, 5, 7, 9
7, 7, 7, 7
3, -1, -5, -9</pre>

<p>以下数列不是等差数列。</p>

<pre>
1, 1, 2, 5, 7</pre>

<p>&nbsp;</p>

<p>数组 A 包含 N 个数，且索引从0开始。数组 A 的一个子数组划分为数组 (P, Q)，P 与 Q 是整数且满足 0&lt;=P&lt;Q&lt;N 。</p>

<p>如果满足以下条件，则称子数组(P, Q)为等差数组：</p>

<p>元素 A[P], A[p + 1], ..., A[Q - 1], A[Q] 是等差的。并且&nbsp;P + 1 &lt; Q 。</p>

<p>函数要返回数组 A 中所有为等差数组的子数组个数。</p>

<p>&nbsp;</p>

<p><strong>示例:</strong></p>

<pre>
A = [1, 2, 3, 4]

返回: 3, A 中有三个子等差数组: [1, 2, 3], [2, 3, 4] 以及自身 [1, 2, 3, 4]。
</pre>

## 解法

<!-- 这里可写通用的实现逻辑 -->

动态规划法。

设 `dp[i]` 表示以 i 结尾的数组构成的等差数列的个数。

如果 `nums[i] + nums[i - 2] ≠ nums[i - 1] * 2`，说明以 i 结尾的数组无法构成等差数列，`dp[i] = 0`；否则 `dp[i] = 1 + dp[i - 1]`。

结果返回 dp 数组所有元素之和即可。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def numberOfArithmeticSlices(self, nums: List[int]) -> int:
        n = len(nums)
        dp = [0] * n
        for i in range(2, n):
            if nums[i] + nums[i - 2] == (nums[i - 1] << 1):
                dp[i] = 1 + dp[i - 1]
        return sum(dp)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int numberOfArithmeticSlices(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        for (int i = 2; i < n; ++i) {
            if (nums[i] + nums[i - 2] == (nums[i - 1] << 1)) {
                dp[i] = 1 + dp[i - 1];
            }
        }
        int res = 0;
        for (int e : dp) {
            res += e;
        }
        return res;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int numberOfArithmeticSlices(vector<int>& nums) {
        int n = nums.size();
        vector<int> dp(n, 0);
        for (int i = 2; i < n; ++i) {
            if (nums[i] + nums[i - 2] == (nums[i - 1] * 2)) {
                dp[i] = 1 + dp[i - 1];
            }
        }
        int res = 0;
        for (auto e : dp) {
            res += e;
        }
        return res;
    }
};
```

### **Go**

```go
func numberOfArithmeticSlices(nums []int) int {
	n := len(nums)
	dp := make([]int, n)
	for i := 2; i < n; i++ {
		if nums[i]-nums[i-1] == nums[i-1]-nums[i-2] {
			dp[i] = 1 + dp[i-1]
		}
	}
	res := 0
	for _, e := range dp {
		res += e
	}
	return res
}
```

### **...**

```

```

<!-- tabs:end -->
