# [945. 使数组唯一的最小增量](https://leetcode.cn/problems/minimum-increment-to-make-array-unique)

[English Version](/solution/0900-0999/0945.Minimum%20Increment%20to%20Make%20Array%20Unique/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个整数数组 <code>nums</code> 。每次 move 操作将会选择任意一个满足 <code>0 &lt;= i &lt; nums.length</code> 的下标 <code>i</code>，并将&nbsp;<code>nums[i]</code> 递增&nbsp;<code>1</code>。</p>

<p>返回使 <code>nums</code> 中的每个值都变成唯一的所需要的最少操作次数。</p>

<div class="original__bRMd">
<div>
<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,2,2]
<strong>输出：</strong>1
<strong>解释：</strong>经过一次 <em>move</em> 操作，数组将变为 [1, 2, 3]。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [3,2,1,2,1,7]
<strong>输出：</strong>6
<strong>解释：</strong>经过 6 次 <em>move</em> 操作，数组将变为 [3, 4, 1, 2, 5, 7]。
可以看出 5 次或 5 次以下的 <em>move</em> 操作是不能让数组的每个值唯一的。</pre>
</div>
</div>

<p>&nbsp;</p>
<strong>提示：</strong>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：排序 + 贪心**

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def minIncrementForUnique(self, nums: List[int]) -> int:
        nums.sort()
        ans = 0
        for i in range(1, len(nums)):
            if nums[i] <= nums[i - 1]:
                d = nums[i - 1] - nums[i] + 1
                nums[i] += d
                ans += d
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int minIncrementForUnique(int[] nums) {
        Arrays.sort(nums);
        int ans = 0;
        for (int i = 1; i < nums.length; ++i) {
            if (nums[i] <= nums[i - 1]) {
                int d = nums[i - 1] - nums[i] + 1;
                nums[i] += d;
                ans += d;
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
    int minIncrementForUnique(vector<int>& nums) {
        sort(nums.begin(), nums.end());
        int ans = 0;
        for (int i = 1; i < nums.size(); ++i) {
            if (nums[i] <= nums[i - 1]) {
                int d = nums[i - 1] - nums[i] + 1;
                nums[i] += d;
                ans += d;
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func minIncrementForUnique(nums []int) int {
	sort.Ints(nums)
	ans := 0
	for i := 1; i < len(nums); i++ {
		if nums[i] <= nums[i-1] {
			d := nums[i-1] - nums[i] + 1
			nums[i] += d
			ans += d
		}
	}
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
