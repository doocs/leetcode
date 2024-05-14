# [2495. 乘积为偶数的子数组数 🔒](https://leetcode.cn/problems/number-of-subarrays-having-even-product)

[English Version](/solution/2400-2499/2495.Number%20of%20Subarrays%20Having%20Even%20Product/README_EN.md)

<!-- tags:数组,数学,动态规划 -->

<!-- difficulty:中等 -->

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个整数数组 <code>nums</code>，返回<em>具有偶数乘积的 </em><code>nums</code><em> <span data-keyword="subarray">子数组</span>的数目</em>。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入:</strong> nums = [9,6,7,13]
<strong>输出:</strong> 6
<strong>解释:</strong> 有6个子数组的乘积是偶数:
- nums[0..1] = 9 * 6 = 54.
- nums[0..2] = 9 * 6 * 7 = 378.
- nums[0..3] = 9 * 6 * 7 * 13 = 4914.
- nums[1..1] = 6.
- nums[1..2] = 6 * 7 = 42.
- nums[1..3] = 6 * 7 * 13 = 546.
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入:</strong> nums = [7,3,5]
<strong>输出:</strong> 0
<strong>解释:</strong> 没有乘积是偶数的子数组
</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
</ul>

## 解法

### 方法一：一次遍历

我们知道，一个子数组的乘积为偶数，当且仅当该子数组中至少有一个偶数。

因此，我们可以遍历数组，记录最近一个偶数的下标 `last`，则以当前元素结尾的子数组中，乘积为偶数的子数组个数为 `last + 1`，累加到结果中即可。

时间复杂度 $O(n)$，其中 $n$ 为数组 `nums` 的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

```python
class Solution:
    def evenProduct(self, nums: List[int]) -> int:
        ans, last = 0, -1
        for i, v in enumerate(nums):
            if v % 2 == 0:
                last = i
            ans += last + 1
        return ans
```

```java
class Solution {
    public long evenProduct(int[] nums) {
        long ans = 0;
        int last = -1;
        for (int i = 0; i < nums.length; ++i) {
            if (nums[i] % 2 == 0) {
                last = i;
            }
            ans += last + 1;
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    long long evenProduct(vector<int>& nums) {
        long long ans = 0;
        int last = -1;
        for (int i = 0; i < nums.size(); ++i) {
            if (nums[i] % 2 == 0) {
                last = i;
            }
            ans += last + 1;
        }
        return ans;
    }
};
```

```go
func evenProduct(nums []int) int64 {
	ans, last := 0, -1
	for i, v := range nums {
		if v%2 == 0 {
			last = i
		}
		ans += last + 1
	}
	return int64(ans)
}
```

<!-- tabs:end -->

<!-- end -->
