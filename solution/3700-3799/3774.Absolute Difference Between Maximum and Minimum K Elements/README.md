---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3700-3799/3774.Absolute%20Difference%20Between%20Maximum%20and%20Minimum%20K%20Elements/README.md
---

<!-- problem:start -->

# [3774. 最大和最小 K 个元素的绝对差](https://leetcode.cn/problems/absolute-difference-between-maximum-and-minimum-k-elements)

[English Version](/solution/3700-3799/3774.Absolute%20Difference%20Between%20Maximum%20and%20Minimum%20K%20Elements/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个整数数组 <code>nums</code> 和一个整数 <code>k</code>。</p>

<p>请计算以下两者的绝对差值：</p>

<ul>
	<li>数组中 <code>k</code> 个 <strong>最大</strong> 元素的<strong>总和</strong>；</li>
	<li>数组中 <code>k</code> 个 <strong>最小</strong> 元素的<strong>总和</strong>。</li>
</ul>

<p>返回表示此差值的整数。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [5,2,2,4], k = 2</span></p>

<p><strong>输出：</strong> <span class="example-io">5</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li><code>k = 2</code> 个最大的元素是 4 和 5。它们的总和是 <code>4 + 5 = 9</code>。</li>
	<li><code>k = 2</code> 个最小的元素是 2 和 2。它们的总和是 <code>2 + 2 = 4</code>。</li>
	<li>绝对差值是 <code>abs(9 - 4) = 5</code>。</li>
</ul>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [100], k = 1</span></p>

<p><strong>输出：</strong> <span class="example-io">0</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li>最大的元素是 100。</li>
	<li>最小的元素是 100。</li>
	<li>绝对差值是 <code>abs(100 - 100) = 0</code>。</li>
</ul>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n == nums.length &lt;= 100</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 100</code></li>
	<li><code>1 &lt;= k &lt;= n</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：排序

我们首先对数组 $\textit{nums}$ 进行排序。然后计算数组中前 $k$ 个元素的和以及后 $k$ 个元素的和，最后返回两者的差值。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(\log n)$，其中 $n$ 是数组 $\textit{nums}$ 的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def absDifference(self, nums: List[int], k: int) -> int:
        nums.sort()
        return sum(nums[-k:]) - sum(nums[:k])
```

#### Java

```java
class Solution {
    public int absDifference(int[] nums, int k) {
        Arrays.sort(nums);
        int ans = 0;
        int n = nums.length;
        for (int i = 0; i < k; ++i) {
            ans += nums[n - i - 1] - nums[i];
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int absDifference(vector<int>& nums, int k) {
        ranges::sort(nums);
        int n = nums.size();
        int ans = 0;
        for (int i = 0; i < k; ++i) {
            ans += nums[n - i - 1] - nums[i];
        }
        return ans;
    }
};
```

#### Go

```go
func absDifference(nums []int, k int) (ans int) {
	slices.Sort(nums)
	for i := 0; i < k; i++ {
		ans += nums[len(nums)-i-1] - nums[i]
	}
	return
}
```

#### TypeScript

```ts
function absDifference(nums: number[], k: number): number {
    nums.sort((a, b) => a - b);
    let ans = 0;
    for (let i = 0; i < k; ++i) {
        ans += nums.at(-i - 1)! - nums[i];
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
