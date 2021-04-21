# [724. 寻找数组的中心下标](https://leetcode-cn.com/problems/find-pivot-index)

[English Version](/solution/0700-0799/0724.Find%20Pivot%20Index/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个整数数组 <code>nums</code>，请编写一个能够返回数组 <strong>“中心下标” </strong>的方法。</p>

<p>数组<strong> 中心下标</strong><strong> </strong>是数组的一个下标，其左侧所有元素相加的和等于右侧所有元素相加的和。</p>

<p>如果数组不存在中心下标，返回 <code>-1</code> 。如果数组有多个中心下标，应该返回最靠近左边的那一个。</p>

<p><strong>注意：</strong>中心下标可能出现在数组的两端。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [1, 7, 3, 6, 5, 6]
<strong>输出：</strong>3
<strong>解释：</strong>
中心下标是 3 。
左侧数之和 (1 + 7 + 3 = 11)，
右侧数之和 (5 + 6 = 11) ，二者相等。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [1, 2, 3]
<strong>输出：</strong>-1
<strong>解释：</strong>
数组中不存在满足此条件的中心下标。</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>nums = [2, 1, -1]
<strong>输出：</strong>0
<strong>解释：</strong>
中心下标是 0 。
下标 0 左侧不存在元素，视作和为 0 ；
右侧数之和为 1 + (-1) = 0 ，二者相等。
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>nums</code> 的长度范围为 <code>[0, 10000]</code>。</li>
	<li>任何一个 <code>nums[i]</code> 将会是一个范围在 <code>[-1000, 1000]</code>的整数。</li>
</ul>


## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def pivotIndex(self, nums: List[int]) -> int:
        sums = sum(nums)
        pre_sum = 0
        for i, v in enumerate(nums):
            if (pre_sum << 1) == sums - v:
                return i
            pre_sum += v
        return -1
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int pivotIndex(int[] nums) {
        int sums = 0;
        for (int e : nums) {
            sums += e;
        }
        int preSum = 0;
        for (int i = 0; i < nums.length; ++i) {
            // preSum == sums - nums[i] - preSum
            if (preSum << 1 == sums - nums[i]) {
                return i;
            }
            preSum += nums[i];
        }
        return -1;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
