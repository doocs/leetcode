---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2900-2999/2956.Find%20Common%20Elements%20Between%20Two%20Arrays/README.md
rating: 1214
source: 第 119 场双周赛 Q1
tags:
    - 数组
    - 哈希表
---

<!-- problem:start -->

# [2956. 找到两个数组中的公共元素](https://leetcode.cn/problems/find-common-elements-between-two-arrays)

[English Version](/solution/2900-2999/2956.Find%20Common%20Elements%20Between%20Two%20Arrays/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你两个下标从 <strong>0</strong>&nbsp;开始的整数数组&nbsp;<code>nums1</code>&nbsp;和&nbsp;<code>nums2</code>&nbsp;，它们分别含有 <code>n</code>&nbsp;和 <code>m</code>&nbsp;个元素。请你计算以下两个数值：</p>

<ul>
	<li><code>answer1</code>：使得&nbsp;<code>nums1[i]</code>&nbsp;在&nbsp;<code>nums2</code>&nbsp;中出现的下标&nbsp;<code>i</code>&nbsp;的数量。</li>
	<li><code>answer2</code>：使得&nbsp;<code>nums2[i]</code>&nbsp;在&nbsp;<code>nums1</code>&nbsp;中出现的下标&nbsp;<code>i</code>&nbsp;的数量。</li>
</ul>

<p>返回 <code>[answer1, answer2]</code>。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong><span class="example-io">nums1 = [2,3,2], nums2 = [1,2]</span></p>

<p><strong>输出：</strong><span class="example-io">[2,1]</span></p>

<p><strong>解释：</strong></p>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2900-2999/2956.Find%20Common%20Elements%20Between%20Two%20Arrays/images/3488_find_common_elements_between_two_arrays-t1.gif" style="width: 225px; height: 150px;" /></p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong><span class="example-io">nums1 = [4,3,2,3,1], nums2 = [2,2,5,2,3,6]</span></p>

<p><strong>输出：</strong><span class="example-io">[3,4]</span></p>

<p><strong>解释：</strong></p>

<p><code>nums1</code>&nbsp;中下标在 1，2，3 的元素在&nbsp;<code>nums2</code>&nbsp;中也存在。所以&nbsp;<code>answer1</code>&nbsp;为&nbsp;3。</p>

<p><code>nums2</code>&nbsp;中下标在 0，1，3，4 的元素在&nbsp;<code>nums1</code>&nbsp;中也存在。所以&nbsp;<code>answer2</code>&nbsp;为 4。</p>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><strong>输入：</strong><span class="example-io">nums1 = [3,4,2,3], nums2 = [1,5]</span></p>

<p><strong>输出：</strong><span class="example-io">[0,0]</span></p>

<p><strong>解释：</strong></p>

<p><code>nums1</code>&nbsp;和&nbsp;<code>nums2</code>&nbsp;中没有相同的数字，所以答案是 [0,0]。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == nums1.length</code></li>
	<li><code>m == nums2.length</code></li>
	<li><code>1 &lt;= n, m &lt;= 100</code></li>
	<li><code>1 &lt;= nums1[i], nums2[i] &lt;= 100</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：哈希表或数组

我们可以用两个哈希表或数组 $s1$ 和 $s2$ 分别记录两个数组中出现的元素。

接下来，我们创建一个长度为 $2$ 的数组 $ans$，其中 $ans[0]$ 表示 $nums1$ 中出现在 $s2$ 中的元素个数，$ans[1]$ 表示 $nums2$ 中出现在 $s1$ 中的元素个数。

然后，我们遍历数组 $nums1$ 中的每个元素 $x$，如果 $x$ 在 $s2$ 中出现过，则将 $ans[0]$ 加一。接着，我们遍历数组 $nums2$ 中的每个元素 $x$，如果 $x$ 在 $s1$ 中出现过，则将 $ans[1]$ 加一。

最后，我们返回数组 $ans$ 即可。

时间复杂度 $O(n + m)$，空间复杂度 $O(n + m)$。其中 $n$ 和 $m$ 分别是数组 $nums1$ 和 $nums2$ 的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def findIntersectionValues(self, nums1: List[int], nums2: List[int]) -> List[int]:
        s1, s2 = set(nums1), set(nums2)
        return [sum(x in s2 for x in nums1), sum(x in s1 for x in nums2)]
```

#### Java

```java
class Solution {
    public int[] findIntersectionValues(int[] nums1, int[] nums2) {
        int[] s1 = new int[101];
        int[] s2 = new int[101];
        for (int x : nums1) {
            s1[x] = 1;
        }
        for (int x : nums2) {
            s2[x] = 1;
        }
        int[] ans = new int[2];
        for (int x : nums1) {
            ans[0] += s2[x];
        }
        for (int x : nums2) {
            ans[1] += s1[x];
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<int> findIntersectionValues(vector<int>& nums1, vector<int>& nums2) {
        int s1[101]{};
        int s2[101]{};
        for (int& x : nums1) {
            s1[x] = 1;
        }
        for (int& x : nums2) {
            s2[x] = 1;
        }
        vector<int> ans(2);
        for (int& x : nums1) {
            ans[0] += s2[x];
        }
        for (int& x : nums2) {
            ans[1] += s1[x];
        }
        return ans;
    }
};
```

#### Go

```go
func findIntersectionValues(nums1 []int, nums2 []int) []int {
	s1 := [101]int{}
	s2 := [101]int{}
	for _, x := range nums1 {
		s1[x] = 1
	}
	for _, x := range nums2 {
		s2[x] = 1
	}
	ans := make([]int, 2)
	for _, x := range nums1 {
		ans[0] += s2[x]
	}
	for _, x := range nums2 {
		ans[1] += s1[x]
	}
	return ans
}
```

#### TypeScript

```ts
function findIntersectionValues(nums1: number[], nums2: number[]): number[] {
    const s1: number[] = Array(101).fill(0);
    const s2: number[] = Array(101).fill(0);
    for (const x of nums1) {
        s1[x] = 1;
    }
    for (const x of nums2) {
        s2[x] = 1;
    }
    const ans: number[] = Array(2).fill(0);
    for (const x of nums1) {
        ans[0] += s2[x];
    }
    for (const x of nums2) {
        ans[1] += s1[x];
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
