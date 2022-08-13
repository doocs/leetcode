# [剑指 Offer II 009. 乘积小于 K 的子数组](https://leetcode.cn/problems/ZVAVXX)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个正整数数组&nbsp;<code>nums</code>和整数 <code>k</code>&nbsp;，请找出该数组内乘积小于&nbsp;<code>k</code>&nbsp;的连续的子数组的个数。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入:</strong> nums = [10,5,2,6], k = 100
<strong>输出:</strong> 8
<strong>解释:</strong> 8 个乘积小于 100 的子数组分别为: [10], [5], [2], [6], [10,5], [5,2], [2,6], [5,2,6]。
需要注意的是 [10,5,2] 并不是乘积小于100的子数组。
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入:</strong> nums = [1,2,3], k = 0
<strong>输出:</strong> 0</pre>

<p>&nbsp;</p>

<p><strong>提示:&nbsp;</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 3 * 10<sup>4</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 1000</code></li>
	<li><code>0 &lt;= k &lt;= 10<sup>6</sup></code></li>
</ul>

<p>&nbsp;</p>

<p><meta charset="UTF-8" />注意：本题与主站 713&nbsp;题相同：<a href="https://leetcode.cn/problems/subarray-product-less-than-k/">https://leetcode.cn/problems/subarray-product-less-than-k/</a>&nbsp;</p>

## 解法

<!-- 这里可写通用的实现逻辑 -->

利用滑动窗口，我们能求出每个不同 `right` 结尾的合法子数组的个数

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def numSubarrayProductLessThanK(self, nums: List[int], k: int) -> int:
        n = len(nums)
        ans = 0
        sum = 1
        left, right = 0, 0
        while right < n:
            sum *= nums[right]
            right += 1
            while sum >= k and left < right:
                sum /= nums[left]
                left += 1
            ans += right - left
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        int n = nums.length;
        int ans = 0;
        int sum = 1;
        int left = 0, right = 0;
        while (right < n) {
            sum *= nums[right++];
            while (sum >= k && left < right) {
                sum /= nums[left++];
            }
            ans += right - left;
        }
        return ans;
    }
}
```

### **Go**

```go
func numSubarrayProductLessThanK(nums []int, k int) int {
	n := len(nums)
	ans := 0
	sum := 1
	left, right := 0, 0
	for right < n {
		sum *= nums[right]
		right++
		for sum >= k && left < right {
			sum /= nums[left]
			left++
		}
		ans += right - left
	}
	return ans
}
```

### **C++**

```cpp
class Solution {
public:
    int numSubarrayProductLessThanK(vector<int>& nums, int k) {
        int left = 0, right;
        long mul = 1;
        int count = 0;

        for (right = 0; right < nums.size(); right++) {
            mul *= nums[right];

            while (left <= right && mul >= k) {
                mul /= nums[left++];
            }

            count += right >= left ? right - left + 1 : 0;
        }

        return count;
    }
};
```

### **...**

```

```

<!-- tabs:end -->
