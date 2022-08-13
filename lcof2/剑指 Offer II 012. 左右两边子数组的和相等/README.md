# [剑指 Offer II 012. 左右两边子数组的和相等](https://leetcode.cn/problems/tvdfij)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个整数数组&nbsp;<code>nums</code> ，请计算数组的 <strong>中心下标 </strong>。</p>

<p>数组<strong> 中心下标</strong><strong> </strong>是数组的一个下标，其左侧所有元素相加的和等于右侧所有元素相加的和。</p>

<p>如果中心下标位于数组最左端，那么左侧数之和视为 <code>0</code> ，因为在下标的左侧不存在元素。这一点对于中心下标位于数组最右端同样适用。</p>

<p>如果数组有多个中心下标，应该返回 <strong>最靠近左边</strong> 的那一个。如果数组不存在中心下标，返回 <code>-1</code> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,7,3,6,5,6]
<strong>输出：</strong>3
<strong>解释：</strong>
中心下标是 3 。
左侧数之和 sum = nums[0] + nums[1] + nums[2] = 1 + 7 + 3 = 11 ，
右侧数之和 sum = nums[4] + nums[5] = 5 + 6 = 11 ，二者相等。
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
左侧数之和 sum = 0 ，（下标 0 左侧不存在元素），
右侧数之和 sum = nums[1] + nums[2] = 1 + -1 = 0 。</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>4</sup></code></li>
	<li><code>-1000 &lt;= nums[i] &lt;= 1000</code></li>
</ul>

<p>&nbsp;</p>

<p><meta charset="UTF-8" />注意：本题与主站 724&nbsp;题相同：&nbsp;<a href="https://leetcode.cn/problems/find-pivot-index/">https://leetcode.cn/problems/find-pivot-index/</a></p>

## 解法

<!-- 这里可写通用的实现逻辑 -->

用前缀和进行预处理，避免重复计算

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def pivotIndex(self, nums: List[int]) -> int:
        n = len(nums)
        sum = [0 for _ in range(n + 1)]
        for i in range(1, n + 1):
            sum[i] = sum[i - 1] + nums[i - 1]
        for i in range(0, n):
            if sum[i] == sum[n] - sum[i + 1]:
                return i
        return -1
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int pivotIndex(int[] nums) {
        int n = nums.length;
        int[] sum = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            sum[i] = sum[i - 1] + nums[i - 1];
        }
        for (int i = 0; i < n; i++) {
            if (sum[i] == sum[n] - sum[i + 1]) {
                return i;
            }
        }
        return -1;
    }
}
```

### **Go**

```go
func pivotIndex(nums []int) int {
	n := len(nums)
	sum := make([]int, n+1)
	for i := 1; i <= n; i++ {
		sum[i] = sum[i-1] + nums[i-1]
	}
	for i := 0; i < n; i++ {
		if sum[i] == sum[n]-sum[i+1] {
			return i
		}
	}
	return -1
}
```

### **C++**

```cpp
class Solution {
public:
    int pivotIndex(vector<int>& nums) {
        int sum = 0;
        int total = 0;
        for (int num : nums)
            sum += num;

        for (int i = 0; i < nums.size(); i++) {
            total += nums[i];
            if (total - nums[i] == sum - total)
                return i;
        }

        return -1;
    }
};
```

### **...**

```

```

<!-- tabs:end -->
