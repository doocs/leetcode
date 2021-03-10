# [259. 较小的三数之和](https://leetcode-cn.com/problems/3sum-smaller)

[English Version](/solution/0200-0299/0259.3Sum%20Smaller/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->
<p>给定一个长度为 <em>n</em> 的整数数组和一个目标值<em> target</em>，寻找能够使条件 <code>nums[i] + nums[j] + nums[k] < target</code> 成立的三元组  <code>i, j, k</code> 个数（<code>0 <= i < j < k < n</code>）。</p>

<p><strong>示例：</strong></p>

<pre><strong>输入: </strong><em>nums</em> = <code>[-2,0,1,3]</code>, <em>target</em> = 2
<strong>输出: </strong>2 
<strong>解释: </strong>因为一共有两个三元组满足累加和小于 2:
     [-2,0,1]
     [-2,0,3]
</pre>

<p><strong>进阶：</strong>是否能在 <em>O</em>(<em>n</em><sup>2</sup>) 的时间复杂度内解决？</p>

## 解法

<!-- 这里可写通用的实现逻辑 -->

双指针解决。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def threeSumSmaller(self, nums: List[int], target: int) -> int:
        def threeSumSmaller(nums, start, end, target):
            count = 0
            while start < end:
                if nums[start] + nums[end] < target:
                    count += (end - start)
                    start += 1
                else:
                    end -= 1
            return count

        nums.sort()
        n, count = len(nums), 0
        for i in range(n - 2):
            count += threeSumSmaller(nums, i + 1, n - 1, target - nums[i])
        return count
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int threeSumSmaller(int[] nums, int target) {
        Arrays.sort(nums);
        int n = nums.length;
        int count = 0;
        for (int i = 0; i < n - 2; ++i) {
            count += threeSumSmaller(nums, i + 1, n - 1, target - nums[i]);
        }
        return count;
    }

    private int threeSumSmaller(int[] nums, int start, int end, int target) {
        int count = 0;
        while (start < end) {
            if (nums[start] + nums[end] < target) {
                count += (end - start);
                ++start;
            } else {
                --end;
            }
        }
        return count;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
