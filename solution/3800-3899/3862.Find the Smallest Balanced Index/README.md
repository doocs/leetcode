---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3800-3899/3862.Find%20the%20Smallest%20Balanced%20Index/README.md
tags:
    - 数组
    - 前缀和
---

<!-- problem:start -->

# [3862. 找出最小平衡下标](https://leetcode.cn/problems/find-the-smallest-balanced-index)

[English Version](/solution/3800-3899/3862.Find%20the%20Smallest%20Balanced%20Index/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个整数数组 <code>nums</code>。</p>

<p>如果某个下标&nbsp;<code>i</code> 满足以下条件，则称其为 <strong>平衡下标</strong>：&nbsp;<code>i</code> 左侧所有元素的总和等于 <code>i</code> 右侧所有元素的乘积。</p>

<p>如果左侧没有元素，则总和视为 0。同样，如果右侧没有元素，则乘积视为 1。</p>

<p>要求返回最小的 <strong>平衡下标</strong>，如果不存在平衡下标，则返回 -1。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [2,1,2]</span></p>

<p><strong>输出：</strong> <span class="example-io">1</span></p>

<p><strong>解释：</strong></p>

<p>对于下标&nbsp;<code>i = 1</code>：</p>

<ul>
	<li>左侧总和 = <code>nums[0] = 2</code></li>
	<li>右侧乘积 = <code>nums[2] = 2</code></li>
	<li>由于左侧总和等于右侧乘积，下标 1 是平衡下标。</li>
</ul>

<p>没有更小的下标满足条件，因此答案是 1。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [2,8,2,2,5]</span></p>

<p><strong>输出：</strong> <span class="example-io">2</span></p>

<p><strong>解释：</strong></p>

<p>对于下标&nbsp;<code>i = 2</code>：</p>

<ul>
	<li>左侧总和 = <code>2 + 8 = 10</code></li>
	<li>右侧乘积 = <code>2 * 5 = 10</code></li>
	<li>由于左侧总和等于右侧乘积，下标&nbsp;2 是平衡下标。</li>
</ul>

<p>没有更小的下标满足条件，因此答案是 2。</p>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [1]</span></p>

<p><strong>输出：</strong> <span class="example-io">-1</span></p>

<p>对于下标&nbsp;<code>i = 0</code>：</p>

<ul>
	<li>左侧为空，因此左侧总和为 0。</li>
	<li>右侧为空，因此右侧乘积为 1。</li>
	<li>由于左侧总和不等于右侧乘积，下标&nbsp;0 不是平衡下标。</li>
</ul>

<p>因此，不存在平衡下标，答案是 -1。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：枚举

我们先计算出数组中所有元素的总和 $s$，然后，我们从右向左枚举每个下标 $i$，在枚举过程中，我们维护一个变量 $p$ 来记录下标 $i$ 右侧所有元素的乘积。当枚举到下标 $i$ 时，我们先将 $s$ 减去 $nums[i]$，然后判断 $s$ 是否等于 $p$，如果相等，则返回下标 $i$。最后，我们将 $p$ 乘以 $nums[i]$，如果 $p$ 大于或等于 $s$，则说明后续的乘积只会越来越大，不可能再找到平衡下标了，因此我们可以提前结束枚举。

枚举结束后，如果没有找到平衡下标，则返回 -1。

时间复杂度 $O(n)$，其中 $n$ 是数组 $nums$ 的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def smallestBalancedIndex(self, nums: list[int]) -> int:
        s = sum(nums)
        p = 1
        for i in range(len(nums) - 1, -1, -1):
            s -= nums[i]
            if s == p:
                return i
            p *= nums[i]
            if p >= s:
                break
        return -1
```

#### Java

```java
class Solution {
    public int smallestBalancedIndex(int[] nums) {
        long s = 0, p = 1;
        for (int x : nums) {
            s += x;
        }
        for (int i = nums.length - 1; i >= 0; --i) {
            s -= nums[i];
            if (s == p) {
                return i;
            }
            p *= nums[i];
            if (p >= s) {
                break;
            }
        }
        return -1;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int smallestBalancedIndex(vector<int>& nums) {
        long long s = 0, p = 1;
        for (int x : nums) {
            s += x;
        }
        for (int i = nums.size() - 1; i >= 0; --i) {
            s -= nums[i];
            if (s == p) {
                return i;
            }
            p *= nums[i];
            if (p >= s) {
                break;
            }
        }
        return -1;
    }
};
```

#### Go

```go
func smallestBalancedIndex(nums []int) int {
	s, p := 0, 1
	for _, x := range nums {
		s += x
	}
	for i := len(nums) - 1; i >= 0; i-- {
		s -= nums[i]
		if s == p {
			return i
		}
		p *= nums[i]
		if p >= s {
			break
		}
	}
	return -1
}
```

#### TypeScript

```ts
function smallestBalancedIndex(nums: number[]): number {
    let s = 0;
    for (const x of nums) {
        s += x;
    }
    for (let i = nums.length - 1, p = 1; i >= 0; --i) {
        s -= nums[i];
        if (s === p) {
            return i;
        }
        p *= nums[i];
        if (p >= s) {
            break;
        }
    }
    return -1;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
