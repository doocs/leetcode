# [1150. 检查一个数是否在数组中占绝大多数](https://leetcode.cn/problems/check-if-a-number-is-majority-element-in-a-sorted-array)

[English Version](/solution/1100-1199/1150.Check%20If%20a%20Number%20Is%20Majority%20Element%20in%20a%20Sorted%20Array/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给出一个按 <strong>非递减</strong> 顺序排列的数组 <code>nums</code>，和一个目标数值 <code>target</code>。假如数组 <code>nums</code> 中绝大多数元素的数值都等于 <code>target</code>，则返回 <code>True</code>，否则请返回 <code>False</code>。</p>

<p>所谓占绝大多数，是指在长度为 <code>N</code> 的数组中出现必须<strong> 超过 <code>N/2</code></strong> <strong>次</strong>。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [2,4,5,5,5,5,5,6,6], target = 5
<strong>输出：</strong>true
<strong>解释：</strong>
数字 5 出现了 5 次，而数组的长度为 9。
所以，5 在数组中占绝大多数，因为 5 次 > 9/2。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [10,100,101,101], target = 101
<strong>输出：</strong>false
<strong>解释：</strong>
数字 101 出现了 2 次，而数组的长度是 4。
所以，101 <strong>不是 </strong>数组占绝大多数的元素，因为 2 次 = 4/2。
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= nums.length <= 1000</code></li>
	<li><code>1 <= nums[i] <= 10^9</code></li>
	<li><code>1 <= target <= 10^9</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

“二分查找”求 `target` 在数组 `nums` 中的左右边界。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

自己实现二分查找：

```python
class Solution:
    def isMajorityElement(self, nums: List[int], target: int) -> bool:
        def bsearch_left(nums, target, left, right):
            while left < right:
                mid = (left + right) >> 1
                if nums[mid] >= target:
                    right = mid
                else:
                    left = mid + 1
            return left if nums[left] == target else -1

        def bsearch_right(nums, target, left, right):
            while left < right:
                mid = (left + right + 1) >> 1
                if nums[mid] <= target:
                    left = mid
                else:
                    right = mid - 1
            return left if nums[left] == target else -1

        n = len(nums)
        left = bsearch_left(nums, target, 0, n - 1)
        if left == -1:
            return False
        right = bsearch_right(nums, target, left, n - 1)
        if right == -1:
            return False
        return right - left + 1 > n >> 1
```

使用 `bisect` 实现二分查找：

```python
class Solution:
    def isMajorityElement(self, nums: List[int], target: int) -> bool:
        left, right = bisect_left(nums, target), bisect_right(nums, target)
        return right - left > (len(nums) >> 1)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public boolean isMajorityElement(int[] nums, int target) {
        int n = nums.length;
        int left = bsearchLeft(nums, target, 0, n - 1);
        if (left == -1) {
            return false;
        }
        int right = bsearchRight(nums, target, left, n - 1);
        if (right == -1) {
            return false;
        }
        return right - left + 1 > (n >> 1);
    }

    private int bsearchLeft(int[] nums, int target, int left, int right) {
        while (left < right) {
            int mid = (left + right) >> 1;
            if (nums[mid] >= target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return nums[left] == target ? left : -1;
    }

    private int bsearchRight(int[] nums, int target, int left, int right) {
        while (left < right) {
            int mid = (left + right + 1) >> 1;
            if (nums[mid] <= target) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        return nums[left] == target ? left : -1;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
