---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3100-3199/3194.Minimum%20Average%20of%20Smallest%20and%20Largest%20Elements/README.md
rating: 1194
source: 第 403 场周赛 Q1
tags:
    - 数组
    - 双指针
    - 排序
---

<!-- problem:start -->

# [3194. 最小元素和最大元素的最小平均值](https://leetcode.cn/problems/minimum-average-of-smallest-and-largest-elements)

[English Version](/solution/3100-3199/3194.Minimum%20Average%20of%20Smallest%20and%20Largest%20Elements/README_EN.md)

## 题目描述

<!-- description:start -->

<p>你有一个初始为空的浮点数数组 <code>averages</code>。另给你一个包含 <code>n</code> 个整数的数组 <code>nums</code>，其中 <code>n</code> 为偶数。</p>

<p>你需要重复以下步骤 <code>n / 2</code> 次：</p>

<ul>
	<li>从 <code>nums</code> 中移除<strong> 最小 </strong>的元素 <code>minElement</code> 和<strong> 最大 </strong>的元素 <code>maxElement</code>。</li>
	<li>将 <code>(minElement + maxElement) / 2</code> 加入到 <code>averages</code> 中。</li>
</ul>

<p>返回 <code>averages</code> 中的 <strong>最小 </strong>元素。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [7,8,3,4,15,13,4,1]</span></p>

<p><strong>输出：</strong> <span class="example-io">5.5</span></p>

<p><strong>解释：</strong></p>

<table>
	<tbody>
		<tr>
			<th>步骤</th>
			<th>nums</th>
			<th>averages</th>
		</tr>
		<tr>
			<td>0</td>
			<td>[7,8,3,4,15,13,4,1]</td>
			<td>[]</td>
		</tr>
		<tr>
			<td>1</td>
			<td>[7,8,3,4,13,4]</td>
			<td>[8]</td>
		</tr>
		<tr>
			<td>2</td>
			<td>[7,8,4,4]</td>
			<td>[8,8]</td>
		</tr>
		<tr>
			<td>3</td>
			<td>[7,4]</td>
			<td>[8,8,6]</td>
		</tr>
		<tr>
			<td>4</td>
			<td>[]</td>
			<td>[8,8,6,5.5]</td>
		</tr>
	</tbody>
</table>
返回 averages 中最小的元素，即 5.5。</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [1,9,8,3,10,5]</span></p>

<p><strong>输出：</strong> <span class="example-io">5.5</span></p>

<p><strong>解释：</strong></p>

<table>
	<tbody>
		<tr>
			<th>步骤</th>
			<th>nums</th>
			<th>averages</th>
		</tr>
		<tr>
			<td>0</td>
			<td>[1,9,8,3,10,5]</td>
			<td>[]</td>
		</tr>
		<tr>
			<td>1</td>
			<td>[9,8,3,5]</td>
			<td>[5.5]</td>
		</tr>
		<tr>
			<td>2</td>
			<td>[8,5]</td>
			<td>[5.5,6]</td>
		</tr>
		<tr>
			<td>3</td>
			<td>[]</td>
			<td>[5.5,6,6.5]</td>
		</tr>
	</tbody>
</table>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [1,2,3,7,8,9]</span></p>

<p><strong>输出：</strong> <span class="example-io">5.0</span></p>

<p><strong>解释：</strong></p>

<table>
	<tbody>
		<tr>
			<th>步骤</th>
			<th>nums</th>
			<th>averages</th>
		</tr>
		<tr>
			<td>0</td>
			<td>[1,2,3,7,8,9]</td>
			<td>[]</td>
		</tr>
		<tr>
			<td>1</td>
			<td>[2,3,7,8]</td>
			<td>[5]</td>
		</tr>
		<tr>
			<td>2</td>
			<td>[3,7]</td>
			<td>[5,5]</td>
		</tr>
		<tr>
			<td>3</td>
			<td>[]</td>
			<td>[5,5,5]</td>
		</tr>
	</tbody>
</table>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= n == nums.length &lt;= 50</code></li>
	<li><code>n</code> 为偶数。</li>
	<li><code>1 &lt;= nums[i] &lt;= 50</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：排序

我们首先对数组 $\textit{nums}$ 进行排序，然后从数组的两端开始取元素，分别计算两个元素的和，取最小值。最后将最小值除以 2 作为答案返回即可。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(\log n)$。其中 $n$ 为数组 $\textit{nums}$ 的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minimumAverage(self, nums: List[int]) -> float:
        nums.sort()
        n = len(nums)
        return min(nums[i] + nums[n - i - 1] for i in range(n // 2)) / 2
```

#### Java

```java
class Solution {
    public double minimumAverage(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        int ans = 1 << 30;
        for (int i = 0; i < n / 2; ++i) {
            ans = Math.min(ans, nums[i] + nums[n - i - 1]);
        }
        return ans / 2.0;
    }
}
```

#### C++

```cpp
class Solution {
public:
    double minimumAverage(vector<int>& nums) {
        sort(nums.begin(), nums.end());
        int ans = 1 << 30, n = nums.size();
        for (int i = 0; i < n; ++i) {
            ans = min(ans, nums[i] + nums[n - i - 1]);
        }
        return ans / 2.0;
    }
};
```

#### Go

```go
func minimumAverage(nums []int) float64 {
	sort.Ints(nums)
	n := len(nums)
	ans := 1 << 30
	for i, x := range nums[:n/2] {
		ans = min(ans, x+nums[n-i-1])
	}
	return float64(ans) / 2
}
```

#### TypeScript

```ts
function minimumAverage(nums: number[]): number {
    nums.sort((a, b) => a - b);
    const n = nums.length;
    let ans = Infinity;
    for (let i = 0; i * 2 < n; ++i) {
        ans = Math.min(ans, nums[i] + nums[n - 1 - i]);
    }
    return ans / 2;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
