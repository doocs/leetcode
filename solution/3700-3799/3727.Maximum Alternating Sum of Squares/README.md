---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3700-3799/3727.Maximum%20Alternating%20Sum%20of%20Squares/README.md
rating: 1454
source: 第 473 场周赛 Q2
tags:
    - 贪心
    - 数组
    - 排序
---

<!-- problem:start -->

# [3727. 最大交替平方和](https://leetcode.cn/problems/maximum-alternating-sum-of-squares)

[English Version](/solution/3700-3799/3727.Maximum%20Alternating%20Sum%20of%20Squares/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个整数数组 <code>nums</code>。你可以以任意顺序&nbsp;<strong>重新排列元素&nbsp;</strong>。</p>

<p>数组 <code>arr</code> 的&nbsp;<strong>交替得分&nbsp;</strong>定义为：</p>

<ul>
	<li><code>score = arr[0]<sup>2</sup> - arr[1]<sup>2</sup> + arr[2]<sup>2</sup> - arr[3]<sup>2</sup> + ...</code></li>
</ul>

<p>在对 <code>nums</code> 重新排列后，返回其&nbsp;<strong>最大可能的交替得分</strong>。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [1,2,3]</span></p>

<p><strong>输出：</strong> <span class="example-io">12</span></p>

<p><strong>解释：</strong></p>

<p><code>nums</code> 的一种可行重排为 <code>[2,1,3]</code>，该排列在所有可能重排中给出了最大交替得分。</p>

<p>交替得分计算如下：</p>

<p><code>score = 2<sup>2</sup> - 1<sup>2</sup> + 3<sup>2</sup> = 4 - 1 + 9 = 12</code></p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [1,-1,2,-2,3,-3]</span></p>

<p><strong>输出：</strong> <span class="example-io">16</span></p>

<p><strong>解释：</strong></p>

<p><code>nums</code> 的一种可行重排为 <code>[-3,-1,-2,1,3,2]</code>，该排列在所有可能重排中给出了最大交替得分。</p>

<p>交替得分计算如下：</p>

<p><code>score = (-3)<sup>2</sup> - (-1)<sup>2</sup> + (-2)<sup>2</sup> - (1)<sup>2</sup> + (3)<sup>2</sup> - (2)<sup>2</sup> = 9 - 1 + 4 - 1 + 9 - 4 = 16</code></p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>-4 * 10<sup>4</sup> &lt;= nums[i] &lt;= 4 * 10<sup>4</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：排序

我们可以将数组中的元素按其平方值进行排序，然后将平方值较大的元素放在偶数下标位置，平方值较小的元素放在奇数下标位置。

最终的交替得分即为平方值较大的元素之和减去平方值较小的元素之和，即数组 $\text{nums}$ 排序后的后半部分元素平方和减去前半部分元素平方和。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(\log n)$。其中 $n$ 是数组的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxAlternatingSum(self, nums: List[int]) -> int:
        nums.sort(key=lambda x: x * x)
        n = len(nums)
        s1 = sum(x * x for x in nums[: n // 2])
        s2 = sum(x * x for x in nums[n // 2 :])
        return s2 - s1
```

#### Java

```java
class Solution {
    public long maxAlternatingSum(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; ++i) {
            nums[i] *= nums[i];
        }
        Arrays.sort(nums);
        long ans = 0;
        int m = n / 2;
        for (int i = 0; i < m; ++i) {
            ans -= nums[i];
        }
        for (int i = m; i < n; ++i) {
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
    long long maxAlternatingSum(vector<int>& nums) {
        for (int& x : nums) {
            x = x * x;
        }
        ranges::sort(nums);
        long long ans = 0, m = nums.size() / 2;
        for (int i = 0; i < m; ++i) {
            ans -= nums[i];
        }
        for (int i = m; i < nums.size(); ++i) {
            ans += nums[i];
        }
        return ans;
    }
};
```

#### Go

```go
func maxAlternatingSum(nums []int) (ans int64) {
	for i, x := range nums {
		nums[i] *= x
	}
	slices.Sort(nums)
	m := len(nums) / 2
	for _, x := range nums[:m] {
		ans -= int64(x)
	}
	for _, x := range nums[m:] {
		ans += int64(x)
	}
	return
}
```

#### TypeScript

```ts
function maxAlternatingSum(nums: number[]): number {
    const n = nums.length;
    for (let i = 0; i < n; i++) {
        nums[i] = nums[i] ** 2;
    }
    nums.sort((a, b) => a - b);
    const m = Math.floor(n / 2);
    let ans = 0;
    for (let i = 0; i < m; i++) {
        ans -= nums[i];
    }
    for (let i = m; i < n; i++) {
        ans += nums[i];
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
