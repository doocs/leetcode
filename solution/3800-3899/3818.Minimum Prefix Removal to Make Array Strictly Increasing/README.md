---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3800-3899/3818.Minimum%20Prefix%20Removal%20to%20Make%20Array%20Strictly%20Increasing/README.md
---

<!-- problem:start -->

# [3818. 移除前缀使数组严格递增](https://leetcode.cn/problems/minimum-prefix-removal-to-make-array-strictly-increasing)

[English Version](/solution/3800-3899/3818.Minimum%20Prefix%20Removal%20to%20Make%20Array%20Strictly%20Increasing/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个整数数组 <code>nums</code>。</p>

<p>你需要从 <code>nums</code> 中<strong>&nbsp;恰好&nbsp;</strong>移除一个前缀（可以为空）。</p>

<p>返回一个整数，表示被移除的前缀的<strong>&nbsp;最小</strong>&nbsp;长度，使得剩余的数组 <strong>严格递增</strong>&nbsp;。</p>

<p>数组的<strong>&nbsp;前缀&nbsp;</strong>是从数组的起始位置开始，一直延伸到任意位置的子数组。</p>

<p>如果一个数组的每个元素都严格大于它的前一个元素（若存在），则称该数组<strong>&nbsp;严格递增</strong>&nbsp;。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [1,-1,2,3,3,4,5]</span></p>

<p><strong>输出：</strong> <span class="example-io">4</span></p>

<p><strong>解释：</strong></p>

<p>移除前缀 <code>prefix = [1, -1, 2, 3]</code> 后，剩余数组为 <code>[3, 4, 5]</code>，严格递增。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [4,3,-2,-5]</span></p>

<p><strong>输出：</strong> <span class="example-io">3</span></p>

<p><strong>解释：</strong></p>

<p>移除前缀 <code>prefix = [4, 3, -2]</code> 后，剩余数组为 <code>[-5]</code>，严格递增。</p>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [1,2,3,4]</span></p>

<p><strong>输出：</strong> <span class="example-io">0</span></p>

<p><strong>解释：</strong></p>

<p>数组 <code>nums = [1, 2, 3, 4]</code> 已经是严格递增的，因此移除空前缀即可。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>-10<sup>9</sup> &lt;= nums[i] &lt;= 10<sup>9</sup>​​​​​​​</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：倒序遍历

我们可以从数组的末尾开始往前遍历，找到第一个不满足严格递增条件的位置 $i$，即满足 $nums[i-1] \geq nums[i]$ 的位置。此时，移除前缀的最小长度即为 $i$。

如果整个数组都是严格递增的，那么我们不需要移除任何前缀，返回 $0$ 即可。

时间复杂度 $O(n)$，其中 $n$ 为数组的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minimumPrefixLength(self, nums: List[int]) -> int:
        for i in range(len(nums) - 1, 0, -1):
            if nums[i - 1] >= nums[i]:
                return i
        return 0
```

#### Java

```java
class Solution {
    public int minimumPrefixLength(int[] nums) {
        for (int i = nums.length - 1; i > 0; --i) {
            if (nums[i - 1] >= nums[i]) {
                return i;
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
    int minimumPrefixLength(vector<int>& nums) {
        for (int i = nums.size() - 1; i; --i) {
            if (nums[i - 1] >= nums[i]) {
                return i;
            }
        }
        return 0;
    }
};
```

#### Go

```go
func minimumPrefixLength(nums []int) int {
	for i := len(nums) - 1; i > 0; i-- {
		if nums[i-1] >= nums[i] {
			return i
		}
	}
	return 0
}
```

#### TypeScript

```ts
function minimumPrefixLength(nums: number[]): number {
    for (let i = nums.length - 1; i; --i) {
        if (nums[i - 1] >= nums[i]) {
            return i;
        }
    }
    return 0;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
