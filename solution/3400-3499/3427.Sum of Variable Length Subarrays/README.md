---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3400-3499/3427.Sum%20of%20Variable%20Length%20Subarrays/README.md
rating: 1215
source: 第 433 场周赛 Q1
tags:
    - 数组
    - 前缀和
---

<!-- problem:start -->

# [3427. 变长子数组求和](https://leetcode.cn/problems/sum-of-variable-length-subarrays)

[English Version](/solution/3400-3499/3427.Sum%20of%20Variable%20Length%20Subarrays/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个长度为 <code>n</code>&nbsp;的整数数组&nbsp;<code>nums</code>&nbsp;。对于 <strong>每个</strong> 下标&nbsp;<code>i</code>（<code>0 &lt;= i &lt; n</code>），定义对应的子数组&nbsp;<code>nums[start ... i]</code>（<code>start = max(0, i - nums[i])</code>）。</p>

<p>返回为数组中每个下标定义的子数组中所有元素的总和。</p>
<strong>子数组</strong>&nbsp;是数组中的一个连续、<strong>非空</strong> 的元素序列。

<p>&nbsp;</p>

<p><b>示例 1：</b></p>

<div class="example-block">
<p><b>输入：</b><span class="example-io">nums = [2,3,1]</span></p>

<p><span class="example-io"><b>输出：</b>11</span></p>

<p><b>解释：</b></p>

<table style="border: 1px solid black;">
	<tbody>
		<tr>
			<th style="border: 1px solid black;">下标 i</th>
			<th style="border: 1px solid black;">子数组</th>
			<th style="border: 1px solid black;">和</th>
		</tr>
		<tr>
			<td style="border: 1px solid black;">0</td>
			<td style="border: 1px solid black;"><code>nums[0] = [2]</code></td>
			<td style="border: 1px solid black;">2</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;"><code>nums[0 ... 1] = [2, 3]</code></td>
			<td style="border: 1px solid black;">5</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">2</td>
			<td style="border: 1px solid black;"><code>nums[1 ... 2] = [3, 1]</code></td>
			<td style="border: 1px solid black;">4</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;"><b>总和</b></td>
			<td style="border: 1px solid black;">&nbsp;</td>
			<td style="border: 1px solid black;">11</td>
		</tr>
	</tbody>
</table>

<p>总和为 11 。因此，输出 11 。</p>
</div>

<p><b>示例 2：</b></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>nums = [3,1,1,2]</span></p>

<p><span class="example-io"><b>输出：</b>13</span></p>

<p><b>解释：</b></p>

<table style="border: 1px solid black;">
	<tbody>
		<tr>
			<th style="border: 1px solid black;">下标 i</th>
			<th style="border: 1px solid black;">子数组</th>
			<th style="border: 1px solid black;">和</th>
		</tr>
		<tr>
			<td style="border: 1px solid black;">0</td>
			<td style="border: 1px solid black;"><code>nums[0] = [3]</code></td>
			<td style="border: 1px solid black;">3</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;"><code>nums[0 ... 1] = [3, 1]</code></td>
			<td style="border: 1px solid black;">4</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">2</td>
			<td style="border: 1px solid black;"><code>nums[1 ... 2] = [1, 1]</code></td>
			<td style="border: 1px solid black;">2</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">3</td>
			<td style="border: 1px solid black;"><code>nums[1 ... 3] = [1, 1, 2]</code></td>
			<td style="border: 1px solid black;">4</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;"><b>总和</b></td>
			<td style="border: 1px solid black;">&nbsp;</td>
			<td style="border: 1px solid black;">13</td>
		</tr>
	</tbody>
</table>

<p>总和为 13 。因此，输出为 13 。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n == nums.length &lt;= 100</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 1000</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def subarraySum(self, nums: List[int]) -> int:
        s = list(accumulate(nums, initial=0))
        return sum(s[i + 1] - s[max(0, i - x)] for i, x in enumerate(nums))
```

#### Java

```java
class Solution {
    public int subarraySum(int[] nums) {
        int n = nums.length;
        int[] s = new int[n + 1];
        for (int i = 1; i <= n; ++i) {
            s[i] = s[i - 1] + nums[i - 1];
        }
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            ans += s[i + 1] - s[Math.max(0, i - nums[i])];
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int subarraySum(vector<int>& nums) {
        int n = nums.size();
        vector<int> s(n + 1);
        for (int i = 1; i <= n; ++i) {
            s[i] = s[i - 1] + nums[i - 1];
        }
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            ans += s[i + 1] - s[max(0, i - nums[i])];
        }
        return ans;
    }
};
```

#### Go

```go
func subarraySum(nums []int) (ans int) {
	s := make([]int, len(nums)+1)
	for i, x := range nums {
		s[i+1] = s[i] + x
	}
	for i, x := range nums {
		ans += s[i+1] - s[max(0, i-x)]
	}
	return
}
```

#### TypeScript

```ts
function subarraySum(nums: number[]): number {
    const n = nums.length;
    const s: number[] = Array(n + 1).fill(0);
    for (let i = 0; i < n; ++i) {
        s[i + 1] = s[i] + nums[i];
    }
    let ans = 0;
    for (let i = 0; i < n; ++i) {
        ans += s[i + 1] - s[Math.max(0, i - nums[i])];
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
