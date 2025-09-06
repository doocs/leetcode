---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3600-3699/3634.Minimum%20Removals%20to%20Balance%20Array/README.md
rating: 1453
source: 第 162 场双周赛 Q2
tags:
    - 数组
    - 排序
    - 滑动窗口
---

<!-- problem:start -->

# [3634. 使数组平衡的最少移除数目](https://leetcode.cn/problems/minimum-removals-to-balance-array)

[English Version](/solution/3600-3699/3634.Minimum%20Removals%20to%20Balance%20Array/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个整数数组 <code>nums</code> 和一个整数 <code>k</code>。</p>

<p>如果一个数组的&nbsp;<strong>最大&nbsp;</strong>元素的值&nbsp;<strong>至多&nbsp;</strong>是其&nbsp;<strong>最小&nbsp;</strong>元素的 <code>k</code> 倍，则该数组被称为是&nbsp;<strong>平衡&nbsp;</strong>的。</p>

<p>你可以从 <code>nums</code> 中移除&nbsp;<strong>任意&nbsp;</strong>数量的元素，但不能使其变为&nbsp;<strong>空&nbsp;</strong>数组。</p>

<p>返回为了使剩余数组平衡，需要移除的元素的&nbsp;<strong>最小&nbsp;</strong>数量。</p>

<p><strong>注意：</strong>大小为 1 的数组被认为是平衡的，因为其最大值和最小值相等，且条件总是成立。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1:</strong></p>

<div class="example-block">
<p><strong>输入：</strong><span class="example-io">nums = [2,1,5], k = 2</span></p>

<p><strong>输出：</strong><span class="example-io">1</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li>移除 <code>nums[2] = 5</code> 得到 <code>nums = [2, 1]</code>。</li>
	<li>现在 <code>max = 2</code>, <code>min = 1</code>，且 <code>max &lt;= min * k</code>，因为 <code>2 &lt;= 1 * 2</code>。因此，答案是 1。</li>
</ul>
</div>

<p><strong class="example">示例 2:</strong></p>

<div class="example-block">
<p><strong>输入：</strong><span class="example-io">nums = [1,6,2,9], k = 3</span></p>

<p><strong>输出：</strong><span class="example-io">2</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li>移除 <code>nums[0] = 1</code> 和 <code>nums[3] = 9</code> 得到 <code>nums = [6, 2]</code>。</li>
	<li>现在 <code>max = 6</code>, <code>min = 2</code>，且 <code>max &lt;= min * k</code>，因为 <code>6 &lt;= 2 * 3</code>。因此，答案是 2。</li>
</ul>
</div>

<p><strong class="example">示例 3:</strong></p>

<div class="example-block">
<p><strong>输入：</strong><span class="example-io">nums = [4,6], k = 2</span></p>

<p><strong>输出：</strong><span class="example-io">0</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li>由于 <code>nums</code> 已经平衡，因为 <code>6 &lt;= 4 * 2</code>，所以不需要移除任何元素。</li>
</ul>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= k &lt;= 10<sup>5</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：排序 + 二分查找

我们首先对数组进行排序，然后我们从小到大枚举每个元素 $\textit{nums}[i]$ 作为平衡数组的最小值，那么平衡数组的最大值 $\textit{max}$ 必须满足 $\textit{max} \leq \textit{nums}[i] \times k$。因此，我们可以使用二分查找来找到第一个大于 $\textit{nums}[i] \times k$ 的元素的下标 $j$，那么此时平衡数组的长度为 $j - i$，我们记录下最大的长度 $\textit{cnt}$，最后的答案就是数组长度减去 $\textit{cnt}$。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(\log n)$。其中 $n$ 是数组 $\textit{nums}$ 的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minRemoval(self, nums: List[int], k: int) -> int:
        nums.sort()
        cnt = 0
        for i, x in enumerate(nums):
            j = bisect_right(nums, k * x)
            cnt = max(cnt, j - i)
        return len(nums) - cnt
```

#### Java

```java
class Solution {
    public int minRemoval(int[] nums, int k) {
        Arrays.sort(nums);
        int cnt = 0;
        int n = nums.length;
        for (int i = 0; i < n; ++i) {
            int j = n;
            if (1L * nums[i] * k <= nums[n - 1]) {
                j = Arrays.binarySearch(nums, nums[i] * k + 1);
                j = j < 0 ? -j - 1 : j;
            }
            cnt = Math.max(cnt, j - i);
        }
        return n - cnt;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int minRemoval(vector<int>& nums, int k) {
        ranges::sort(nums);
        int cnt = 0;
        int n = nums.size();
        for (int i = 0; i < n; ++i) {
            int j = n;
            if (1LL * nums[i] * k <= nums[n - 1]) {
                j = upper_bound(nums.begin(), nums.end(), 1LL * nums[i] * k) - nums.begin();
            }
            cnt = max(cnt, j - i);
        }
        return n - cnt;
    }
};
```

#### Go

```go
func minRemoval(nums []int, k int) int {
	sort.Ints(nums)
	n := len(nums)
	cnt := 0
	for i := 0; i < n; i++ {
		j := n
		if int64(nums[i])*int64(k) <= int64(nums[n-1]) {
			target := int64(nums[i])*int64(k) + 1
			j = sort.Search(n, func(x int) bool {
				return int64(nums[x]) >= target
			})
		}
		cnt = max(cnt, j-i)
	}
	return n - cnt
}
```

#### TypeScript

```ts
function minRemoval(nums: number[], k: number): number {
    nums.sort((a, b) => a - b);
    const n = nums.length;
    let cnt = 0;
    for (let i = 0; i < n; ++i) {
        let j = n;
        if (nums[i] * k <= nums[n - 1]) {
            const target = nums[i] * k + 1;
            j = _.sortedIndexBy(nums, target, x => x);
        }
        cnt = Math.max(cnt, j - i);
    }
    return n - cnt;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
