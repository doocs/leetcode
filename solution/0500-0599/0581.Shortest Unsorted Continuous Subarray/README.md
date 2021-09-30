# [581. 最短无序连续子数组](https://leetcode-cn.com/problems/shortest-unsorted-continuous-subarray)

[English Version](/solution/0500-0599/0581.Shortest%20Unsorted%20Continuous%20Subarray/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个整数数组 <code>nums</code> ，你需要找出一个 <strong>连续子数组</strong> ，如果对这个子数组进行升序排序，那么整个数组都会变为升序排序。</p>

<p>请你找出符合题意的 <strong>最短</strong> 子数组，并输出它的长度。</p>

<p> </p>

<div class="original__bRMd">
<div>
<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [2,6,4,8,10,9,15]
<strong>输出：</strong>5
<strong>解释：</strong>你只需要对 [6, 4, 8, 10, 9] 进行升序排序，那么整个表都会变为升序排序。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,2,3,4]
<strong>输出：</strong>0
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>nums = [1]
<strong>输出：</strong>0
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= nums.length <= 10<sup>4</sup></code></li>
	<li><code>-10<sup>5</sup> <= nums[i] <= 10<sup>5</sup></code></li>
</ul>

<p> </p>

<p><strong>进阶：</strong>你可以设计一个时间复杂度为 <code>O(n)</code> 的解决方案吗？</p>
</div>
</div>


## 解法

<!-- 这里可写通用的实现逻辑 -->

将排序后的数组与原数组进行比较，确定左右边界。更进一步优化，可以通过维护最大值和最小值，一次遍历得出结果（见 Golang 解法）

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def findUnsortedSubarray(self, nums: List[int]) -> int:
        n = len(nums)
        numsSorted = sorted(nums)
        left, right = 0, n - 1
        while left < n and nums[left] == numsSorted[left]:
            left += 1
        while right >= 0 and nums[right] == numsSorted[right]:
            right -= 1
        return 0 if right == -1 else right - left + 1
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int findUnsortedSubarray(int[] nums) {
        int n = nums.length;
        int[] numsSorted = new int[n];
        System.arraycopy(nums, 0, numsSorted, 0, n);
        Arrays.sort(numsSorted);
        int left = 0, right = n - 1;
        while (left < n && nums[left] == numsSorted[left]) {
            left++;
        }
        while (right >= 0 && nums[right] == numsSorted[right]) {
            right--;
        }
        return right == -1 ? 0 : right - left + 1;
    }
}
```

### **Go**

```go
func findUnsortedSubarray(nums []int) int {
	n := len(nums)
	maxn, minn := math.MinInt32, math.MaxInt32
	left, right := -1, -1
	for i := 0; i < n; i++ {
		if maxn > nums[i] {
			right = i
		} else {
			maxn = nums[i]
		}
		if minn < nums[n-i-1] {
			left = n - i - 1
		} else {
			minn = nums[n-i-1]
		}
	}
	if right == -1 {
		return 0
	}
	return right - left + 1
}
```

### **...**

```

```

<!-- tabs:end -->
