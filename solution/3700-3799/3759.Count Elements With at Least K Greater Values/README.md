---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3700-3799/3759.Count%20Elements%20With%20at%20Least%20K%20Greater%20Values/README.md
rating: 1372
source: 第 478 场周赛 Q1
tags:
    - 数组
    - 二分查找
    - 分治
    - 快速选择
    - 排序
---

<!-- problem:start -->

# [3759. 统计合格元素的数目](https://leetcode.cn/problems/count-elements-with-at-least-k-greater-values)

[English Version](/solution/3700-3799/3759.Count%20Elements%20With%20at%20Least%20K%20Greater%20Values/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个长度为 <code>n</code> 的整数数组 <code>nums</code> 和一个整数 <code>k</code>。</p>

<p>如果数组 <code>nums</code> 中的某个元素满足以下条件，则称其为 <strong>合格元素</strong>：存在&nbsp;<strong>至少</strong> <code>k</code> 个元素&nbsp;<strong>严格大于&nbsp;</strong>它。</p>

<p>返回一个整数，表示数组 <code>nums</code> 中合格元素的总数。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [3,1,2], k = 1</span></p>

<p><strong>输出：</strong> <span class="example-io">2</span></p>

<p><strong>解释：</strong></p>

<p>元素 1 和 2 均有至少 <code>k = 1</code> 个元素大于它们。<br />
没有元素比 3 更大。因此答案是 2。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [5,5,5], k = 2</span></p>

<p><strong>输出：</strong> <span class="example-io">0</span></p>

<p><strong>解释：</strong></p>

<p>由于所有元素都等于 5，没有任何元素比其他元素大。因此答案是 0。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n == nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>0 &lt;= k &lt; n</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：排序

如果 $k = 0$，那么数组中所有元素均为合格元素，直接返回数组长度即可。

否则，我们对数组进行排序，记排序后数组长度为 $n$。对于每个下标 $i$ 满足 $0 \\leq i < n - k$ 的元素，如果它严格小于下标为 $n - k$ 的元素，则它是一个合格元素。我们只需统计这样的元素个数并返回即可。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(\log n)$。其中 $n$ 是数组 $\textit{nums}$ 的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def countElements(self, nums: List[int], k: int) -> int:
        n = len(nums)
        if k == 0:
            return n
        nums.sort()
        return sum(nums[n - k] > nums[i] for i in range(n - k))
```

#### Java

```java
class Solution {
    public int countElements(int[] nums, int k) {
        int n = nums.length;
        if (k == 0) {
            return n;
        }
        Arrays.sort(nums);
        int ans = 0;
        for (int i = 0; i < n - k; ++i) {
            if (nums[n - k] > nums[i]) {
                ++ans;
            }
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int countElements(vector<int>& nums, int k) {
        int n = nums.size();
        if (k == 0) {
            return n;
        }
        ranges::sort(nums);
        int ans = 0;
        for (int i = 0; i < n - k; ++i) {
            if (nums[n - k] > nums[i]) {
                ++ans;
            }
        }
        return ans;
    }
};
```

#### Go

```go
func countElements(nums []int, k int) int {
	n := len(nums)
	if k == 0 {
		return n
	}
	sort.Ints(nums)
	ans := 0
	for i := 0; i < n-k; i++ {
		if nums[n-k] > nums[i] {
			ans++
		}
	}
	return ans
}
```

#### TypeScript

```ts
function countElements(nums: number[], k: number): number {
    const n = nums.length;
    if (k === 0) {
        return n;
    }
    nums.sort((a, b) => a - b);
    let ans = 0;
    for (let i = 0; i < n - k; ++i) {
        if (nums[n - k] > nums[i]) {
            ++ans;
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
