---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3000-3099/3065.Minimum%20Operations%20to%20Exceed%20Threshold%20Value%20I/README.md
rating: 1149
source: 第 125 场双周赛 Q1
tags:
    - 数组
---

<!-- problem:start -->

# [3065. 超过阈值的最少操作数 I](https://leetcode.cn/problems/minimum-operations-to-exceed-threshold-value-i)

[English Version](/solution/3000-3099/3065.Minimum%20Operations%20to%20Exceed%20Threshold%20Value%20I/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个下标从 <b>0</b>&nbsp;开始的整数数组&nbsp;<code>nums</code>&nbsp;和一个整数&nbsp;<code>k</code>&nbsp;。</p>

<p>一次操作中，你可以删除 <code>nums</code>&nbsp;中的最小元素。</p>

<p>你需要使数组中的所有元素都大于或等于 <code>k</code>&nbsp;，请你返回需要的 <strong>最少</strong>&nbsp;操作次数。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<b>输入：</b>nums = [2,11,10,1,3], k = 10
<b>输出：</b>3
<b>解释：</b>第一次操作后，nums 变为 [2, 11, 10, 3] 。
第二次操作后，nums 变为 [11, 10, 3] 。
第三次操作后，nums 变为 [11, 10] 。
此时，数组中的所有元素都大于等于 10 ，所以我们停止操作。
使数组中所有元素都大于等于 10 需要的最少操作次数为 3 。
</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<b>输入：</b>nums = [1,1,2,4,9], k = 1
<b>输出：</b>0
<b>解释：</b>数组中的所有元素都大于等于 1 ，所以不需要对 nums 做任何操作。</pre>

<p><strong class="example">示例 3：</strong></p>

<pre>
<b>输入：</b>nums = [1,1,2,4,9], k = 9
<b>输出：</b>4
<b>解释：</b>nums 中只有一个元素大于等于 9 ，所以需要执行 4 次操作。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 50</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= k &lt;= 10<sup>9</sup></code></li>
	<li>输入保证至少有一个满足&nbsp;<code>nums[i] &gt;= k</code>&nbsp;的下标&nbsp;<code>i</code>&nbsp;存在。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：遍历计数

我们只需要遍历一遍数组，统计小于 $k$ 的元素个数即可。

时间复杂度 $O(n)$，其中 $n$ 为数组长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

```python
class Solution:
    def minOperations(self, nums: List[int], k: int) -> int:
        return sum(x < k for x in nums)
```

```java
class Solution {
    public int minOperations(int[] nums, int k) {
        int ans = 0;
        for (int x : nums) {
            if (x < k) {
                ++ans;
            }
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int minOperations(vector<int>& nums, int k) {
        int ans = 0;
        for (int x : nums) {
            if (x < k) {
                ++ans;
            }
        }
        return ans;
    }
};
```

```go
func minOperations(nums []int, k int) (ans int) {
	for _, x := range nums {
		if x < k {
			ans++
		}
	}
	return
}
```

```ts
function minOperations(nums: number[], k: number): number {
    return nums.filter(x => x < k).length;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
