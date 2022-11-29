# [910. 最小差值 II](https://leetcode.cn/problems/smallest-range-ii)

[English Version](/solution/0900-0999/0910.Smallest%20Range%20II/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个整数数组 <code>nums</code>，和一个整数&nbsp;<code>k</code> 。</p>

<p>对于每个下标 <code>i</code>（<code>0 &lt;= i &lt; nums.length</code>），将 <code>nums[i]</code> 变成<strong> </strong> <code>nums[i] + k</code> 或 <code>nums[i] - k</code> 。</p>

<p><code>nums</code> 的 <strong>分数</strong> 是 <code>nums</code> 中最大元素和最小元素的差值。</p>

<p>在更改每个下标对应的值之后，返回 <code>nums</code> 的最小 <strong>分数</strong> 。</p>

<p>&nbsp;</p>

<ol>
</ol>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [1], k = 0
<strong>输出：</strong>0
<strong>解释：</strong>分数 = max(nums) - min(nums) = 1 - 1 = 0 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [0,10], k = 2
<strong>输出：</strong>6
<strong>解释：</strong>将数组变为 [2, 8] 。分数 = max(nums) - min(nums) = 8 - 2 = 6 。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,3,6], k = 3
<strong>输出：</strong>3
<strong>解释：</strong>将数组变为 [4, 6, 3] 。分数 = max(nums) - min(nums) = 6 - 3 = 3 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>4</sup></code></li>
	<li><code>0 &lt;= nums[i] &lt;= 10<sup>4</sup></code></li>
	<li><code>0 &lt;= k &lt;= 10<sup>4</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：贪心 + 枚举**

根据题目要求，我们需要求数组中的元素最大值与最小值差值的最小值。每个元素可以加上或者减去 $k$，因此我们可以将数组中的元素分为两部分，一部分加上 $k$，一部分减去 $k$。那么，我们应该将数组中的较大值减去 $k$，较小值加上 $k$，这样才能保证最大值与最小值的差值最小。

因此，我们可以先将数组排序，然后枚举数组中的每个元素，将其分为两部分，一部分加上 $k$，一部分减去 $k$，并计算最大值与最小值的差值。最后，取所有差值中的最小值即可。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(\log n)$。其中 $n$ 为数组的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def smallestRangeII(self, nums: List[int], k: int) -> int:
        nums.sort()
        ans = nums[-1] - nums[0]
        for i in range(1, len(nums)):
            mi = min(nums[0] + k, nums[i] - k)
            mx = max(nums[i - 1] + k, nums[-1] - k)
            ans = min(ans, mx - mi)
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int smallestRangeII(int[] nums, int k) {
        Arrays.sort(nums);
        int n = nums.length;
        int ans = nums[n - 1] - nums[0];
        for (int i = 1; i < n; ++i) {
            int mi = Math.min(nums[0] + k, nums[i] - k);
            int mx = Math.max(nums[i - 1] + k, nums[n - 1] - k);
            ans = Math.min(ans, mx - mi);
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int smallestRangeII(vector<int>& nums, int k) {
        sort(nums.begin(), nums.end());
        int n = nums.size();
        int ans = nums[n - 1] - nums[0];
        for (int i = 1; i < n; ++i) {
            int mi = min(nums[0] + k, nums[i] - k);
            int mx = max(nums[i - 1] + k, nums[n - 1] - k);
            ans = min(ans, mx - mi);
        }
        return ans;
    }
};
```

### **Go**

```go
func smallestRangeII(nums []int, k int) int {
	sort.Ints(nums)
	n := len(nums)
	ans := nums[n-1] - nums[0]
	for i := 1; i < n; i++ {
		mi := min(nums[0]+k, nums[i]-k)
		mx := max(nums[i-1]+k, nums[n-1]-k)
		ans = min(ans, mx-mi)
	}
	return ans
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}
```

### **...**

```

```

<!-- tabs:end -->
