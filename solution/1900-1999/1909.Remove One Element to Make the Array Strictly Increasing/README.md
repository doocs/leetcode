# [1909. 删除一个元素使数组严格递增](https://leetcode.cn/problems/remove-one-element-to-make-the-array-strictly-increasing)

[English Version](/solution/1900-1999/1909.Remove%20One%20Element%20to%20Make%20the%20Array%20Strictly%20Increasing/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个下标从 <strong>0</strong> 开始的整数数组 <code>nums</code> ，如果 <strong>恰好</strong> 删除 <strong>一个</strong> 元素后，数组 <strong>严格递增</strong> ，那么请你返回 <code>true</code> ，否则返回 <code>false</code> 。如果数组本身已经是严格递增的，请你也返回 <code>true</code> 。</p>

<p>数组 <code>nums</code> 是 <strong>严格递增</strong> 的定义为：对于任意下标的 <code>1 &lt;= i &lt; nums.length</code> 都满足 <code>nums[i - 1] &lt; nums[i]</code> 。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre><b>输入：</b>nums = [1,2,<strong>10</strong>,5,7]
<b>输出：</b>true
<b>解释：</b>从 nums 中删除下标 2 处的 10 ，得到 [1,2,5,7] 。
[1,2,5,7] 是严格递增的，所以返回 true 。
</pre>

<p><strong>示例 2：</strong></p>

<pre><b>输入：</b>nums = [2,3,1,2]
<b>输出：</b>false
<b>解释：</b>
[3,1,2] 是删除下标 0 处元素后得到的结果。
[2,1,2] 是删除下标 1 处元素后得到的结果。
[2,3,2] 是删除下标 2 处元素后得到的结果。
[2,3,1] 是删除下标 3 处元素后得到的结果。
没有任何结果数组是严格递增的，所以返回 false 。</pre>

<p><strong>示例 3：</strong></p>

<pre><b>输入：</b>nums = [1,1,1]
<b>输出：</b>false
<b>解释：</b>删除任意元素后的结果都是 [1,1] 。
[1,1] 不是严格递增的，所以返回 false 。
</pre>

<p><strong>示例 4：</strong></p>

<pre><b>输入：</b>nums = [1,2,3]
<b>输出：</b>true
<b>解释：</b>[1,2,3] 已经是严格递增的，所以返回 true 。
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= nums.length &lt;= 1000</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 1000</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def canBeIncreasing(self, nums: List[int]) -> bool:
        def check(nums, i):
            prev = -inf
            for j, num in enumerate(nums):
                if i == j:
                    continue
                if prev >= nums[j]:
                    return False
                prev = nums[j]
            return True

        i, n = 1, len(nums)
        while i < n and nums[i - 1] < nums[i]:
            i += 1
        return check(nums, i - 1) or check(nums, i)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public boolean canBeIncreasing(int[] nums) {
        int i = 1, n = nums.length;
        for (; i < n && nums[i - 1] < nums[i]; ++i)
            ;
        return check(nums, i - 1) || check(nums, i);
    }

    private boolean check(int[] nums, int i) {
        int prev = Integer.MIN_VALUE;
        for (int j = 0; j < nums.length; ++j) {
            if (i == j) {
                continue;
            }
            if (prev >= nums[j]) {
                return false;
            }
            prev = nums[j];
        }
        return true;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    bool canBeIncreasing(vector<int>& nums) {
        int i = 1, n = nums.size();
        for (; i < n && nums[i - 1] < nums[i]; ++i)
            ;
        return check(nums, i - 1) || check(nums, i);
    }

    bool check(vector<int>& nums, int i) {
        int prev = 0;
        for (int j = 0; j < nums.size(); ++j) {
            if (i == j) continue;
            if (prev >= nums[j]) return false;
            prev = nums[j];
        }
        return true;
    }
};
```

### **Go**

```go
func canBeIncreasing(nums []int) bool {
	i, n := 1, len(nums)
	for ; i < n && nums[i-1] < nums[i]; i++ {

	}
	return check(nums, i-1) || check(nums, i)
}

func check(nums []int, i int) bool {
	prev := 0
	for j := 0; j < len(nums); j++ {
		if i == j {
			continue
		}
		if prev >= nums[j] {
			return false
		}
		prev = nums[j]
	}
	return true
}
```

### **...**

```

```

<!-- tabs:end -->
