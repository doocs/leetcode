# [280. 摆动排序](https://leetcode.cn/problems/wiggle-sort)

[English Version](/solution/0200-0299/0280.Wiggle%20Sort/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个的整数数组&nbsp;<code>nums</code>, 将该数组重新排序后使&nbsp;<code>nums[0] &lt;= nums[1] &gt;= nums[2] &lt;= nums[3]...</code>&nbsp;</p>

<p>输入数组总是有一个有效的答案。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入：</strong><code>nums = [3,5,2,1,6,4]</code>
<strong>输出：</strong>[3,5,1,6,2,4]
<strong>解释：</strong>[1,6,2,5,3,4]也是有效的答案</pre>

<p><strong>示例 2:</strong></p>

<pre>
<b>输入：</b>nums = [6,6,5,6,3,8]
<b>输出：</b>[6,6,5,6,3,8]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<p><meta charset="UTF-8" /></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>0 &lt;= nums[i] &lt;= 10<sup>4</sup></code></li>
	<li>
	<p>输入的&nbsp;<code>nums</code> 保证至少有一个答案。</p>
	</li>
</ul>

<p>&nbsp;</p>

<p><b>进阶：</b>你能在&nbsp;<code>O(n)</code>&nbsp;时间复杂度下解决这个问题吗？</p>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def wiggleSort(self, nums: List[int]) -> None:
        """
        Do not return anything, modify nums in-place instead.
        """
        for i in range(1, len(nums)):
            if (i % 2 == 1 and nums[i] < nums[i - 1]) or (
                i % 2 == 0 and nums[i] > nums[i - 1]
            ):
                nums[i], nums[i - 1] = nums[i - 1], nums[i]
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public void wiggleSort(int[] nums) {
        for (int i = 1; i < nums.length; ++i) {
            if ((i % 2 == 1 && nums[i] < nums[i - 1]) || (i % 2 == 0 && nums[i] > nums[i - 1])) {
                swap(nums, i, i - 1);
            }
        }
    }

    private void swap(int[] nums, int i, int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    void wiggleSort(vector<int>& nums) {
        for (int i = 1; i < nums.size(); ++i) {
            if ((i % 2 == 1 && nums[i] < nums[i - 1]) || (i % 2 == 0 && nums[i] > nums[i - 1])) {
                swap(nums[i], nums[i - 1]);
            }
        }
    }
};
```

### **Go**

```go
func wiggleSort(nums []int) {
	for i := 1; i < len(nums); i++ {
		if (i%2 == 1 && nums[i] < nums[i-1]) || (i%2 == 0 && nums[i] > nums[i-1]) {
			nums[i], nums[i-1] = nums[i-1], nums[i]
		}
	}
}
```

### **...**

```

```

<!-- tabs:end -->
