# [2874. 有序三元组中的最大值 II](https://leetcode.cn/problems/maximum-value-of-an-ordered-triplet-ii)

[English Version](/solution/2800-2899/2874.Maximum%20Value%20of%20an%20Ordered%20Triplet%20II/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个下标从 <strong>0</strong> 开始的整数数组 <code>nums</code> 。</p>

<p>请你从所有满足&nbsp;<code>i &lt; j &lt; k</code> 的下标三元组 <code>(i, j, k)</code> 中，找出并返回下标三元组的最大值。如果所有满足条件的三元组的值都是负数，则返回 <code>0</code> 。</p>

<p><strong>下标三元组</strong> <code>(i, j, k)</code> 的值等于 <code>(nums[i] - nums[j]) * nums[k]</code> 。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [12,6,1,2,7]
<strong>输出：</strong>77
<strong>解释：</strong>下标三元组 (0, 2, 4) 的值是 (nums[0] - nums[2]) * nums[4] = 77 。
可以证明不存在值大于 77 的有序下标三元组。
</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,10,3,4,19]
<strong>输出：</strong>133
<strong>解释：</strong>下标三元组 (1, 2, 4) 的值是 (nums[1] - nums[2]) * nums[4] = 133 。
可以证明不存在值大于 133 的有序下标三元组。 
</pre>

<p><strong class="example">示例 3：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,2,3]
<strong>输出：</strong>0
<strong>解释：</strong>唯一的下标三元组 (0, 1, 2) 的值是一个负数，(nums[0] - nums[1]) * nums[2] = -3 。因此，答案是 0 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>3 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>6</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：维护前缀最大值和最大差值**

我们可以用两个变量 $mx$ 和 $mx\_diff$ 分别维护前缀最大值和最大差值。遍历数组时，更新这两个变量，答案为所有 $mx\_diff \times nums[i]$ 的最大值。

时间复杂度 $O(n)$，其中 $n$ 是数组长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def maximumTripletValue(self, nums: List[int]) -> int:
        ans = mx = mx_diff = 0
        for num in nums:
            ans = max(ans, mx_diff * num)
            mx = max(mx, num)
            mx_diff = max(mx_diff, mx - num)
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public long maximumTripletValue(int[] nums) {
        long max, maxDiff, ans;
        max = 0;
        maxDiff = 0;
        ans = 0;
        for (int num : nums) {
            ans = Math.max(ans, num * maxDiff);
            max = Math.max(max, num);
            maxDiff = Math.max(maxDiff, max - num);
        }
        return ans;
    }
}

```

### **C++**

```cpp
class Solution {
public:
    long long maximumTripletValue(vector<int>& nums) {
        long long ans = 0;
        int mx = 0, mx_diff = 0;
        for (int num : nums) {
            ans = max(ans, 1LL * mx_diff * num);
            mx = max(mx, num);
            mx_diff = max(mx_diff, mx - num);
        }
        return ans;
    }
};
```

### **Go**

```go
func maximumTripletValue(nums []int) int64 {
	ans, mx, mx_diff := 0, 0, 0
	for _, num := range nums {
		ans = max(ans, mx_diff*num)
		mx = max(mx, num)
		mx_diff = max(mx_diff, mx-num)
	}
	return int64(ans)
}
```

### **TypeScript**

```ts
function maximumTripletValue(nums: number[]): number {
    let [ans, mx, mx_diff] = [0, 0, 0];
    for (const num of nums) {
        ans = Math.max(ans, mx_diff * num);
        mx = Math.max(mx, num);
        mx_diff = Math.max(mx_diff, mx - num);
    }
    return ans;
}
```

### **...**

```

```

<!-- tabs:end -->
