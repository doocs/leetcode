# [2972. 统计移除递增子数组的数目 II](https://leetcode.cn/problems/count-the-number-of-incremovable-subarrays-ii)

[English Version](/solution/2900-2999/2972.Count%20the%20Number%20of%20Incremovable%20Subarrays%20II/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个下标从 <strong>0</strong>&nbsp;开始的 <b>正</b>&nbsp;整数数组&nbsp;<code>nums</code>&nbsp;。</p>

<p>如果 <code>nums</code>&nbsp;的一个子数组满足：移除这个子数组后剩余元素 <strong>严格递增</strong>&nbsp;，那么我们称这个子数组为 <strong>移除递增</strong>&nbsp;子数组。比方说，<code>[5, 3, 4, 6, 7]</code>&nbsp;中的 <code>[3, 4]</code>&nbsp;是一个移除递增子数组，因为移除该子数组后，<code>[5, 3, 4, 6, 7]</code>&nbsp;变为&nbsp;<code>[5, 6, 7]</code>&nbsp;，是严格递增的。</p>

<p>请你返回 <code>nums</code>&nbsp;中 <b>移除递增</b>&nbsp;子数组的总数目。</p>

<p><b>注意</b>&nbsp;，剩余元素为空的数组也视为是递增的。</p>

<p><strong>子数组</strong> 指的是一个数组中一段连续的元素序列。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<b>输入：</b>nums = [1,2,3,4]
<b>输出：</b>10
<b>解释：</b>10 个移除递增子数组分别为：[1], [2], [3], [4], [1,2], [2,3], [3,4], [1,2,3], [2,3,4] 和 [1,2,3,4]。移除任意一个子数组后，剩余元素都是递增的。注意，空数组不是移除递增子数组。
</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<b>输入：</b>nums = [6,5,7,8]
<b>输出：</b>7
<b>解释：</b>7<strong>&nbsp;</strong>个移除递增子数组分别为：[5], [6], [5,7], [6,5], [5,7,8], [6,5,7] 和 [6,5,7,8] 。
nums 中只有这 7 个移除递增子数组。
</pre>

<p><strong class="example">示例 3：</strong></p>

<pre>
<b>输入：</b>nums = [8,7,6,6]
<b>输出：</b>3
<b>解释：</b>3 个移除递增子数组分别为：[8,7,6], [7,6,6] 和 [8,7,6,6] 。注意 [8,7] 不是移除递增子数组因为移除 [8,7] 后 nums 变为 [6,6] ，它不是严格递增的。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：双指针**

根据题目描述，移除一个子数组后，剩余元素严格递增，那么存在以下几种情况：

1. 剩余元素仅包含数组 $nums$ 的前缀（可以为空）；
1. 剩余元素仅包含数组 $nums$ 的后缀；
1. 剩余元素包含数组 $nums$ 的前缀和后缀。

其中第 $2$ 和第 $3$ 种情况可以合并为一种情况，即剩余元素包含数组 $nums$ 的后缀。即一共有以下两种情况：

1. 剩余元素仅包含数组 $nums$ 的前缀（可以为空）；
1. 剩余元素包含数组 $nums$ 的后缀。

我们先考虑第一种情况，即剩余元素仅包含数组 $nums$ 的前缀。我们可以用一个指针 $i$ 指向数组 $nums$ 的最长递增前缀的最后一个元素，即 $nums[0] \lt nums[1] \lt \cdots \lt nums[i]$，那么剩余元素的个数为 $n - i - 1$，其中 $n$ 为数组 $nums$ 的长度。因此，对于这种情况，要使得剩余元素严格递增，我们可以选择移除以下子数组：

1. $nums[i+1,...,n-1]$；
1. $nums[i,...,n-1]$；
1. $nums[i-1,...,n-1]$；
1. $nums[i-2,...,n-1]$；
1. $\cdots$；
1. $nums[0,...,n-1]$。

这一共有 $i + 2$ 种情况，因此对于这种情况，移除递增子数组的数目为 $i + 2$。

再考虑第二种情况，即剩余元素包含数组 $nums$ 的后缀。我们可以用一个指针 $j$ 指向数组 $nums$ 的递增后缀的第一个元素，我们在 $[n - 1,...,1]$ 的范围内枚举 $j$ 作为递增后缀的第一个元素，每一次，我们需要移动指针 $i$ 使得 $nums[i] \lt nums[j]$，那么移除递增子数组的数组增加 $i + 2$。当 $nums[j - 1] \ge nums[j]$ 时，我们停止枚举，因为此时后缀不是严格递增。

时间复杂度 $O(n)$，其中 $n$ 是数组 $nums$ 的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def incremovableSubarrayCount(self, nums: List[int]) -> int:
        i, n = 0, len(nums)
        while i + 1 < n and nums[i] < nums[i + 1]:
            i += 1
        if i == n - 1:
            return n * (n + 1) // 2
        ans = i + 2
        j = n - 1
        while j:
            while i >= 0 and nums[i] >= nums[j]:
                i -= 1
            ans += i + 2
            if nums[j - 1] >= nums[j]:
                break
            j -= 1
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public long incremovableSubarrayCount(int[] nums) {
        int i = 0, n = nums.length;
        while (i + 1 < n && nums[i] < nums[i + 1]) {
            ++i;
        }
        if (i == n - 1) {
            return n * (n + 1L) / 2;
        }
        long ans = i + 2;
        for (int j = n - 1; j > 0; --j) {
            while (i >= 0 && nums[i] >= nums[j]) {
                --i;
            }
            ans += i + 2;
            if (nums[j - 1] >= nums[j]) {
                break;
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
    long long incremovableSubarrayCount(vector<int>& nums) {
        int i = 0, n = nums.size();
        while (i + 1 < n && nums[i] < nums[i + 1]) {
            ++i;
        }
        if (i == n - 1) {
            return n * (n + 1LL) / 2;
        }
        long long ans = i + 2;
        for (int j = n - 1; j > 0; --j) {
            while (i >= 0 && nums[i] >= nums[j]) {
                --i;
            }
            ans += i + 2;
            if (nums[j - 1] >= nums[j]) {
                break;
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func incremovableSubarrayCount(nums []int) int64 {
	i, n := 0, len(nums)
	for i+1 < n && nums[i] < nums[i+1] {
		i++
	}
	if i == n-1 {
		return int64(n * (n + 1) / 2)
	}
	ans := int64(i + 2)
	for j := n - 1; j > 0; j-- {
		for i >= 0 && nums[i] >= nums[j] {
			i--
		}
		ans += int64(i + 2)
		if nums[j-1] >= nums[j] {
			break
		}
	}
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
