---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2700-2799/2740.Find%20the%20Value%20of%20the%20Partition/README.md
rating: 1301
source: 第 350 场周赛 Q2
tags:
    - 数组
    - 排序
---

<!-- problem:start -->

# [2740. 找出分区值](https://leetcode.cn/problems/find-the-value-of-the-partition)

[English Version](/solution/2700-2799/2740.Find%20the%20Value%20of%20the%20Partition/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个 <strong>正</strong> 整数数组 <code>nums</code> 。</p>

<p>将 <code>nums</code> 分成两个数组：<code>nums1</code> 和 <code>nums2</code> ，并满足下述条件：</p>

<ul>
	<li>数组 <code>nums</code> 中的每个元素都属于数组 <code>nums1</code> 或数组 <code>nums2</code> 。</li>
	<li>两个数组都 <strong>非空</strong> 。</li>
	<li>分区值 <strong>最小</strong> 。</li>
</ul>

<p>分区值的计算方法是 <code>|max(nums1) - min(nums2)|</code> 。</p>

<p>其中，<code>max(nums1)</code> 表示数组 <code>nums1</code> 中的最大元素，<code>min(nums2)</code> 表示数组 <code>nums2</code> 中的最小元素。</p>

<p>返回表示分区值的整数。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>nums = [1,3,2,4]
<strong>输出：</strong>1
<strong>解释：</strong>可以将数组 nums 分成 nums1 = [1,2] 和 nums2 = [3,4] 。
- 数组 nums1 的最大值等于 2 。
- 数组 nums2 的最小值等于 3 。
分区值等于 |2 - 3| = 1 。
可以证明 1 是所有分区方案的最小值。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>nums = [100,1,10]
<strong>输出：</strong>9
<strong>解释：</strong>可以将数组 nums 分成 nums1 = [10] 和 nums2 = [100,1] 。 
- 数组 nums1 的最大值等于 10 。 
- 数组 nums2 的最小值等于 1 。 
分区值等于 |10 - 1| = 9 。 
可以证明 9 是所有分区方案的最小值。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：排序

题目要求分区值最小，那么我们可以将数组排序，然后取相邻两个数的差值的最小值即可。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(\log n)$。其中 $n$ 是数组的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def findValueOfPartition(self, nums: List[int]) -> int:
        nums.sort()
        return min(b - a for a, b in pairwise(nums))
```

#### Java

```java
class Solution {
    public int findValueOfPartition(int[] nums) {
        Arrays.sort(nums);
        int ans = 1 << 30;
        for (int i = 1; i < nums.length; ++i) {
            ans = Math.min(ans, nums[i] - nums[i - 1]);
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int findValueOfPartition(vector<int>& nums) {
        sort(nums.begin(), nums.end());
        int ans = 1 << 30;
        for (int i = 1; i < nums.size(); ++i) {
            ans = min(ans, nums[i] - nums[i - 1]);
        }
        return ans;
    }
};
```

#### Go

```go
func findValueOfPartition(nums []int) int {
	sort.Ints(nums)
	ans := 1 << 30
	for i, x := range nums[1:] {
		ans = min(ans, x-nums[i])
	}
	return ans
}
```

#### TypeScript

```ts
function findValueOfPartition(nums: number[]): number {
    nums.sort((a, b) => a - b);
    let ans = Infinity;
    for (let i = 1; i < nums.length; ++i) {
        ans = Math.min(ans, Math.abs(nums[i] - nums[i - 1]));
    }
    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn find_value_of_partition(mut nums: Vec<i32>) -> i32 {
        nums.sort();
        let mut ans = i32::MAX;
        for i in 1..nums.len() {
            ans = ans.min(nums[i] - nums[i - 1]);
        }
        ans
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
