---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3400-3499/3452.Sum%20of%20Good%20Numbers/README.md
rating: 1199
source: 第 150 场双周赛 Q1
tags:
    - 数组
---

<!-- problem:start -->

# [3452. 好数字之和](https://leetcode.cn/problems/sum-of-good-numbers)

[English Version](/solution/3400-3499/3452.Sum%20of%20Good%20Numbers/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定一个整数数组 <code>nums</code> 和一个整数 <code>k</code>，如果元素 <code>nums[i]</code> <strong>严格</strong> 大于下标&nbsp;<code>i - k</code> 和 <code>i + k</code> 处的元素（如果这些元素存在），则该元素 <code>nums[i]</code> 被认为是 <strong>好</strong> 的。如果这两个下标都不存在，那么 <code>nums[i]</code> 仍然被认为是 <strong>好</strong> 的。</p>

<p>返回数组中所有 <strong>好</strong> 元素的 <strong>和</strong>。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [1,3,2,1,5,4], k = 2</span></p>

<p><strong>输出：</strong> <span class="example-io">12</span></p>

<p><strong>解释：</strong></p>

<p>好的数字包括&nbsp;<code>nums[1] = 3</code>，<code>nums[4] = 5</code> 和 <code>nums[5] = 4</code>，因为它们严格大于下标&nbsp;<code>i - k</code> 和 <code>i + k</code> 处的数字。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [2,1], k = 1</span></p>

<p><strong>输出：</strong> <span class="example-io">2</span></p>

<p><strong>解释：</strong></p>

<p>唯一的好数字是 <code>nums[0] = 2</code>，因为它严格大于 <code>nums[1]</code>。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= nums.length &lt;= 100</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 1000</code></li>
	<li><code>1 &lt;= k &lt;= floor(nums.length / 2)</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：遍历

我们可以遍历数组 $\textit{nums}$，对于每个元素 $\textit{nums}[i]$，检查是否满足条件：

- 如果 $i \ge k$ 且 $\textit{nums}[i] \le \textit{nums}[i - k]$，则 $\textit{nums}[i]$ 不是好数字；
- 如果 $i + k < \textit{len}(\textit{nums})$ 且 $\textit{nums}[i] \le \textit{nums}[i + k]$，则 $\textit{nums}[i]$ 不是好数字。
- 否则，$\textit{nums}[i]$ 是好数字，我们将其累加到答案中。

遍历结束后，返回答案即可。

时间复杂度 $O(n)$，其中 $n$ 是数组 $\textit{nums}$ 的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def sumOfGoodNumbers(self, nums: List[int], k: int) -> int:
        ans = 0
        for i, x in enumerate(nums):
            if i >= k and x <= nums[i - k]:
                continue
            if i + k < len(nums) and x <= nums[i + k]:
                continue
            ans += x
        return ans
```

#### Java

```java
class Solution {
    public int sumOfGoodNumbers(int[] nums, int k) {
        int ans = 0;
        int n = nums.length;
        for (int i = 0; i < n; ++i) {
            if (i >= k && nums[i] <= nums[i - k]) {
                continue;
            }
            if (i + k < n && nums[i] <= nums[i + k]) {
                continue;
            }
            ans += nums[i];
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int sumOfGoodNumbers(vector<int>& nums, int k) {
        int ans = 0;
        int n = nums.size();
        for (int i = 0; i < n; ++i) {
            if (i >= k && nums[i] <= nums[i - k]) {
                continue;
            }
            if (i + k < n && nums[i] <= nums[i + k]) {
                continue;
            }
            ans += nums[i];
        }
        return ans;
    }
};
```

#### Go

```go
func sumOfGoodNumbers(nums []int, k int) (ans int) {
	for i, x := range nums {
		if i >= k && x <= nums[i-k] {
			continue
		}
		if i+k < len(nums) && x <= nums[i+k] {
			continue
		}
		ans += x
	}
	return
}
```

#### TypeScript

```ts
function sumOfGoodNumbers(nums: number[], k: number): number {
    const n = nums.length;
    let ans = 0;
    for (let i = 0; i < n; ++i) {
        if (i >= k && nums[i] <= nums[i - k]) {
            continue;
        }
        if (i + k < n && nums[i] <= nums[i + k]) {
            continue;
        }
        ans += nums[i];
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
