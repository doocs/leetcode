---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3800-3899/3874.Valid%20Subarrays%20With%20Exactly%20One%20Peak/README.md
tags:
    - 数组
    - 数学
---

<!-- problem:start -->

# [3874. 具有恰好一个峰值的有效子数组 🔒](https://leetcode.cn/problems/valid-subarrays-with-exactly-one-peak)

[English Version](/solution/3800-3899/3874.Valid%20Subarrays%20With%20Exactly%20One%20Peak/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定一个长度为&nbsp;<code>n</code>&nbsp;的整数数组&nbsp;<code>nums</code>&nbsp;和一个整数&nbsp;<code>k</code>。</p>

<p>下标&nbsp;<code>i</code> 是 <strong>峰值</strong> 的条件为：</p>

<ul>
	<li><code>0 &lt; i &lt; n - 1</code></li>
	<li><code>nums[i] &gt; nums[i - 1]</code> 且&nbsp;<code>nums[i] &gt; nums[i + 1]</code></li>
</ul>

<p>一个子数组&nbsp;<code>[l, r]</code>&nbsp;是 <strong>有效 </strong>的条件是：</p>

<ul>
	<li>它 <strong>恰好有一个</strong>&nbsp;<code>nums</code>&nbsp;中下标&nbsp;<code>i</code> 处的峰值</li>
	<li><code>i - l &lt;= k</code> 且&nbsp;<code>r - i &lt;= k</code></li>
</ul>

<p>返回一个整数，表示 <code>nums</code> 中 <strong>有效子数组</strong> 的数量。</p>
<strong>子数组</strong> 是数组中的连续 <strong>非空</strong> 元素序列。

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>nums = [1,3,2], k = 1</span></p>

<p><span class="example-io"><b>输出：</b>4</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li>下标 <code>i = 1</code>&nbsp;是一个峰值，因为 <code>nums[1] = 3</code> 大于 <code>nums[0] = 1</code> 和 <code>nums[2] = 2</code>。</li>
	<li>任何有效的子数组必须包含下标&nbsp;1，且子数组两端到峰值的距离不得超过 <code>k = 1</code>。</li>
	<li>有效的子数组是&nbsp;<code>[3]</code>，<code>[1, 3]</code>，<code>[3, 2]</code>&nbsp;和&nbsp;<code>[1, 3, 2]</code>，所以答案是 4。</li>
</ul>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>nums = [7,8,9], k = 2</span></p>

<p><span class="example-io"><b>输出：</b>0</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li>没有下标&nbsp;<code>i</code> 使得&nbsp;<code>nums[i]</code>&nbsp;同时比&nbsp;<code>nums[i - 1]</code> 和&nbsp;<code>nums[i + 1]</code>&nbsp;更大。</li>
	<li>因此，该数组中没有峰值。所以，有效子数组的数量为 0。</li>
</ul>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>nums = [4,3,5,1], k = 2</span></p>

<p><span class="example-io"><b>输出：</b>6</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li>下标 <code>i = 2</code>&nbsp;是一个峰值，因为 <code>nums[2] = 5</code> 大于 <code>nums[1] = 3</code>&nbsp;和 <code>nums[3] = 1</code>。</li>
	<li>任何有效的子数组都必须包含这个峰值，并且峰值到子数组两端的距离不得超过 <code>k = 2</code>。</li>
	<li>合法子数组是&nbsp;<code>[5]</code>，<code>[3, 5]</code>，<code>[5, 1]</code>，<code>[3, 5, 1]</code>，<code>[4, 3, 5]</code>&nbsp;和&nbsp;<code>[4, 3, 5, 1]</code>，所以答案是 6。</li>
</ul>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n == nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>-10<sup>5</sup> &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= k &lt;= n</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：模拟

我们首先遍历数组，找到所有的峰值位置，并将它们存储在一个列表 $\textit{peaks}$ 中。

对于每个峰值位置，我们计算以该峰值为中心，距离不超过 $k$ 的左右边界。需要注意的是，如果存在多个峰值，我们需要确保计算的子数组不包含其他峰值。然后根据左右边界计算以该峰值为中心的有效子数组数量，并将其累加到答案中。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 是数组的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def validSubarrays(self, nums: list[int], k: int) -> int:
        n = len(nums)
        peaks = []
        for i in range(1, n - 1):
            if nums[i] > nums[i - 1] and nums[i] > nums[i + 1]:
                peaks.append(i)

        ans = 0
        for j, p in enumerate(peaks):
            left_min = max(p - k, 0)
            if j > 0:
                left_min = max(left_min, peaks[j - 1] + 1)

            right_max = min(p + k, n - 1)
            if j < len(peaks) - 1:
                right_max = min(right_max, peaks[j + 1] - 1)

            ans += (p - left_min + 1) * (right_max - p + 1)
        return ans
```

#### Java

```java
class Solution {
    public long validSubarrays(int[] nums, int k) {
        int n = nums.length;
        List<Integer> peaks = new ArrayList<>();

        for (int i = 1; i < n - 1; i++) {
            if (nums[i] > nums[i - 1] && nums[i] > nums[i + 1]) {
                peaks.add(i);
            }
        }

        long ans = 0;
        for (int j = 0; j < peaks.size(); j++) {
            int p = peaks.get(j);

            int leftMin = Math.max(p - k, 0);
            if (j > 0) {
                leftMin = Math.max(leftMin, peaks.get(j - 1) + 1);
            }

            int rightMax = Math.min(p + k, n - 1);
            if (j < peaks.size() - 1) {
                rightMax = Math.min(rightMax, peaks.get(j + 1) - 1);
            }

            ans += (long) (p - leftMin + 1) * (rightMax - p + 1);
        }

        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    long long validSubarrays(vector<int>& nums, int k) {
        int n = nums.size();
        vector<int> peaks;

        for (int i = 1; i < n - 1; ++i) {
            if (nums[i] > nums[i - 1] && nums[i] > nums[i + 1]) {
                peaks.push_back(i);
            }
        }

        long long ans = 0;
        for (int j = 0; j < peaks.size(); ++j) {
            int p = peaks[j];

            int leftMin = max(p - k, 0);
            if (j > 0) {
                leftMin = max(leftMin, peaks[j - 1] + 1);
            }

            int rightMax = min(p + k, n - 1);
            if (j < peaks.size() - 1) {
                rightMax = min(rightMax, peaks[j + 1] - 1);
            }

            ans += 1LL * (p - leftMin + 1) * (rightMax - p + 1);
        }

        return ans;
    }
};
```

#### Go

```go
func validSubarrays(nums []int, k int) int64 {
	n := len(nums)
	peaks := []int{}

	for i := 1; i < n-1; i++ {
		if nums[i] > nums[i-1] && nums[i] > nums[i+1] {
			peaks = append(peaks, i)
		}
	}

	var ans int64
	for j, p := range peaks {
		leftMin := max(p-k, 0)
		if j > 0 {
			leftMin = max(leftMin, peaks[j-1]+1)
		}

		rightMax := min(p+k, n-1)
		if j < len(peaks)-1 {
			rightMax = min(rightMax, peaks[j+1]-1)
		}

		ans += int64(p-leftMin+1) * int64(rightMax-p+1)
	}

	return ans
}
```

#### TypeScript

```ts
function validSubarrays(nums: number[], k: number): number {
    const n = nums.length;
    const peaks: number[] = [];

    for (let i = 1; i < n - 1; i++) {
        if (nums[i] > nums[i - 1] && nums[i] > nums[i + 1]) {
            peaks.push(i);
        }
    }

    let ans = 0;
    for (let j = 0; j < peaks.length; j++) {
        const p = peaks[j];

        let leftMin = Math.max(p - k, 0);
        if (j > 0) {
            leftMin = Math.max(leftMin, peaks[j - 1] + 1);
        }

        let rightMax = Math.min(p + k, n - 1);
        if (j < peaks.length - 1) {
            rightMax = Math.min(rightMax, peaks[j + 1] - 1);
        }

        ans += (p - leftMin + 1) * (rightMax - p + 1);
    }

    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
