---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3600-3699/3674.Minimum%20Operations%20to%20Equalize%20Array/README.md
rating: 1369
source: 第 466 场周赛 Q1
---

<!-- problem:start -->

# [3674. 数组元素相等的最小操作次数](https://leetcode.cn/problems/minimum-operations-to-equalize-array)

[English Version](/solution/3600-3699/3674.Minimum%20Operations%20to%20Equalize%20Array/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个长度为 <code>n</code> 的整数数组 <code>nums</code>。</p>

<p>在一次操作中，可以选择任意子数组 <code>nums[l...r]</code> （<code>0 &lt;= l &lt;= r &lt; n</code>），并将该子数组中的每个元素&nbsp;<strong>替换&nbsp;</strong>为所有元素的&nbsp;<strong>按位与（bitwise AND）</strong>结果。</p>

<p>返回使数组 <code>nums</code> 中所有元素相等所需的最小操作次数。</p>

<p><strong>子数组&nbsp;</strong>是数组中连续的、非空的元素序列。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [1,2]</span></p>

<p><strong>输出：</strong> <span class="example-io">1</span></p>

<p><strong>解释：</strong></p>

<p>选择 <code>nums[0...1]</code>：<code>(1 AND 2) = 0</code>，因此数组变为 <code>[0, 0]</code>，所有元素在一次操作后相等。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [5,5,5]</span></p>

<p><strong>输出：</strong> <span class="example-io">0</span></p>

<p><strong>解释：</strong></p>

<p><code>nums</code> 本身是 <code>[5, 5, 5]</code>，所有元素已经相等，因此不需要任何操作。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n == nums.length &lt;= 100</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：一次遍历

如果 $\textit{nums}$ 中所有元素都相等，则不需要任何操作；否则，选择整个数组作为子数组进行一次操作即可。

时间复杂度 $O(n)$，其中 $n$ 是数组 $\textit{nums}$ 的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minOperations(self, nums: List[int]) -> int:
        return int(any(x != nums[0] for x in nums))
```

#### Java

```java
class Solution {
    public int minOperations(int[] nums) {
        for (int x : nums) {
            if (x != nums[0]) {
                return 1;
            }
        }
        return 0;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int minOperations(vector<int>& nums) {
        for (int x : nums) {
            if (x != nums[0]) {
                return 1;
            }
        }
        return 0;
    }
};
```

#### Go

```go
func minOperations(nums []int) int {
	for _, x := range nums {
		if x != nums[0] {
			return 1
		}
	}
	return 0
}
```

#### TypeScript

```ts
function minOperations(nums: number[]): number {
    for (const x of nums) {
        if (x !== nums[0]) {
            return 1;
        }
    }
    return 0;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
