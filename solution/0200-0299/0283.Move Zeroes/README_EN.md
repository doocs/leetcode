# [283. Move Zeroes](https://leetcode.com/problems/move-zeroes)

[中文文档](/solution/0200-0299/0283.Move%20Zeroes/README.md)

## Description

<p>Given an integer array <code>nums</code>, move all <code>0</code>&#39;s to the end of it while maintaining the relative order of the non-zero elements.</p>

<p><strong>Note</strong> that you must do this in-place without making a copy of the array.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<pre><strong>Input:</strong> nums = [0,1,0,3,12]
<strong>Output:</strong> [1,3,12,0,0]
</pre><p><strong>Example 2:</strong></p>
<pre><strong>Input:</strong> nums = [0]
<strong>Output:</strong> [0]
</pre>
<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>4</sup></code></li>
	<li><code>-2<sup>31</sup> &lt;= nums[i] &lt;= 2<sup>31</sup> - 1</code></li>
</ul>

<p>&nbsp;</p>
<strong>Follow up:</strong> Could you minimize the total number of operations done?

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def moveZeroes(self, nums: List[int]) -> None:
        """
        Do not return anything, modify nums in-place instead.
        """
        if not nums:
            return
        n = len(nums)
        zero_count = 0
        for i in range(n):
            if nums[i] == 0:
                zero_count += 1
            else:
                nums[i - zero_count] = nums[i]
        while zero_count > 0:
            nums[n - zero_count] = 0
            zero_count -= 1
```

### **Java**

```java
class Solution {
    public void moveZeroes(int[] nums) {
        int n;
        if (nums == null || (n = nums.length) < 1) {
            return;
        }
        int zeroCount = 0;
        for (int i = 0; i < n; ++i) {
            if (nums[i] == 0) {
                ++zeroCount;
            } else {
                nums[i - zeroCount] = nums[i];
            }
        }
        while (zeroCount > 0) {
            nums[n - zeroCount--] = 0;
        }
    }
}
```
### **JavaScript**

```js
/**
 * @param {number[]} nums
 * @return {void} Do not return anything, modify nums in-place instead.
 */
var moveZeroes = function (nums) {
    if (!nums.length) return nums;
    let j = 0;
    for (i = 0; i < nums.length; i++) {
        if (nums[i]) {
            if (i > j) [nums[i], nums[j]] = [nums[j], nums[i]];
            j++;
        }
    }
    return nums;
};
```

### **Go**

```go
func moveZeroes(nums []int) {
	n := len(nums)
	left := 0
	for right := 0; right < n; right++ {
		if nums[right] != 0 {
			nums[left], nums[right] = nums[right], nums[left]
			left++
		}
	}
}
```

### **...**

```

```

<!-- tabs:end -->
