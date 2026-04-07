---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3800-3899/3875.Construct%20Uniform%20Parity%20Array%20I/README.md
rating: 1199
source: 第 494 场周赛 Q1
tags:
    - 数组
    - 数学
---

<!-- problem:start -->

# [3875. 构造奇偶一致的数组 I](https://leetcode.cn/problems/construct-uniform-parity-array-i)

[English Version](/solution/3800-3899/3875.Construct%20Uniform%20Parity%20Array%20I/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个长度为 <code>n</code> 的数组 <code>nums1</code>，其中包含 <strong>互不相同</strong> 的整数。</p>

<p>你需要构造另一个长度为 <code>n</code> 的数组 <code>nums2</code>，使得 <code>nums2</code> 中的元素要么全部为&nbsp;<strong>奇数</strong>，要么全部为<strong>&nbsp;偶数</strong>。</p>

<p>对于每个下标 <code>i</code>，你必须从以下两种选择中<strong>&nbsp;任选其一</strong>（顺序不限）：</p>

<ul>
	<li><code>nums2[i] = nums1[i]</code></li>
	<li><code>nums2[i] = nums1[i] - nums1[j]</code>，其中 <code>j != i</code></li>
</ul>

<p>如果能够构造出满足条件的数组，则返回 <code>true</code>；否则，返回 <code>false</code>。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums1 = [2,3]</span></p>

<p><strong>输出：</strong> <span class="example-io">true</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li>选择 <code>nums2[0] = nums1[0] - nums1[1] = 2 - 3 = -1</code>。</li>
	<li>选择 <code>nums2[1] = nums1[1] = 3</code>。</li>
	<li><code>nums2 = [-1, 3]</code>，两个元素均为奇数。因此答案为 <code>true</code>。</li>
</ul>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums1 = [4,6]</span></p>

<p><strong>输出：</strong> <span class="example-io">true</span></p>

<p><strong>解释：</strong>​​​​​​​</p>

<ul>
	<li>选择 <code>nums2[0] = nums1[0] = 4</code>。</li>
	<li>选择 <code>nums2[1] = nums1[1] = 6</code>。</li>
	<li><code>nums2 = [4, 6]</code>，两个元素均为偶数。因此答案为 <code>true</code>。</li>
</ul>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n == nums1.length &lt;= 100</code></li>
	<li><code>1 &lt;= nums1[i] &lt;= 100</code></li>
	<li><code>nums1</code> 中的所有整数互不相同。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：脑筋急转弯

如果 $\textit{nums1}$ 中的所有元素全是奇数或者全是偶数，那么我们可以直接将 $\textit{nums2}$ 设为 $\textit{nums1}$，满足条件。

如果 $\textit{nums1}$ 中既有奇数又有偶数，那么我们可以将 $\textit{nums2}$ 中的每个元素都设为 $\textit{nums1}$ 的当前元素减去 $\textit{nums1}$ 中的一个与当前元素奇偶性不同的元素。由于奇数减偶数和偶数减奇数的结果都是奇数，因此 $\textit{nums2}$ 中的所有元素都是奇数，满足条件。

因此，无论 $\textit{nums1}$ 中的元素是全奇数、全偶数，还是既有奇数又有偶数，我们都可以构造出满足条件的 $\textit{nums2}$。所以答案始终是 $\text{true}$。

时间复杂度 $O(1)$，空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def uniformArray(self, nums1: list[int]) -> bool:
        return True
```

#### Java

```java
class Solution {
    public boolean uniformArray(int[] nums1) {
        return true;
    }
}
```

#### C++

```cpp
class Solution {
public:
    bool uniformArray(vector<int>& nums1) {
        return true;
    }
};
```

#### Go

```go
func uniformArray(nums1 []int) bool {
	return true
}
```

#### TypeScript

```ts
function uniformArray(nums1: number[]): boolean {
    return true;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
