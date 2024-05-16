---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3100-3199/3131.Find%20the%20Integer%20Added%20to%20Array%20I/README.md
rating: 1160
source: 第 395 场周赛 Q1
tags:
    - 数组
---

# [3131. 找出与数组相加的整数 I](https://leetcode.cn/problems/find-the-integer-added-to-array-i)

[English Version](/solution/3100-3199/3131.Find%20the%20Integer%20Added%20to%20Array%20I/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你两个长度相等的数组 <code>nums1</code> 和 <code>nums2</code>。</p>

<p>数组 <code>nums1</code> 中的每个元素都与变量 <code>x</code> 所表示的整数相加。如果 <code>x</code> 为负数，则表现为元素值的减少。</p>

<p>在与 <code>x</code> 相加后，<code>nums1</code> 和 <code>nums2</code> <strong>相等</strong> 。当两个数组中包含相同的整数，并且这些整数出现的频次相同时，两个数组 <strong>相等</strong> 。</p>

<p>返回整数 <code>x</code> 。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1:</strong></p>

<div class="example-block">
<p><strong>输入：</strong><span class="example-io" style="
    font-family: Menlo,sans-serif;
    font-size: 0.85rem;
">nums1 = [2,6,4], nums2 = [9,7,5]</span></p>

<p><strong>输出：</strong><span class="example-io" style="
    font-family: Menlo,sans-serif;
    font-size: 0.85rem;
">3</span></p>

<p><strong>解释：</strong></p>

<p>与 3 相加后，<code>nums1</code> 和 <code>nums2</code> 相等。</p>
</div>

<p><strong class="example">示例 2:</strong></p>

<div class="example-block">
<p><strong>输入：</strong><span class="example-io" style="
    font-family: Menlo,sans-serif;
    font-size: 0.85rem;
">nums1 = [10], nums2 = [5]</span></p>

<p><strong>输出：</strong><span class="example-io" style="
    font-family: Menlo,sans-serif;
    font-size: 0.85rem;
">-5</span></p>

<p><strong>解释：</strong></p>

<p>与 <code>-5</code> 相加后，<code>nums1</code> 和 <code>nums2</code> 相等。</p>
</div>

<p><strong class="example">示例 3:</strong></p>

<div class="example-block">
<p><strong>输入：</strong><span class="example-io" style="
    font-family: Menlo,sans-serif;
    font-size: 0.85rem;
">nums1 = [1,1,1,1], nums2 = [1,1,1,1]</span></p>

<p><strong>输出：</strong><span class="example-io" style="
    font-family: Menlo,sans-serif;
    font-size: 0.85rem;
">0</span></p>

<p><strong>解释：</strong></p>

<p>与 0 相加后，<code>nums1</code> 和 <code>nums2</code> 相等。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums1.length == nums2.length &lt;= 100</code></li>
	<li><code>0 &lt;= nums1[i], nums2[i] &lt;= 1000</code></li>
	<li>测试用例以这样的方式生成：存在一个整数 <code>x</code>，使得 <code>nums1</code> 中的每个元素都与 <code>x</code> 相加后，<code>nums1</code> 与 <code>nums2</code> 相等。</li>
</ul>

## 解法

### 方法一：求最小值差

我们可以分别求出两个数组的最小值，然后返回两个最小值的差值即可。

时间复杂度 $O(n)$，其中 $n$ 为数组的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

```python
class Solution:
    def addedInteger(self, nums1: List[int], nums2: List[int]) -> int:
        return min(nums2) - min(nums1)
```

```java
class Solution {
    public int addedInteger(int[] nums1, int[] nums2) {
        return Arrays.stream(nums2).min().getAsInt() - Arrays.stream(nums1).min().getAsInt();
    }
}
```

```cpp
class Solution {
public:
    int addedInteger(vector<int>& nums1, vector<int>& nums2) {
        return *min_element(nums2.begin(), nums2.end()) - *min_element(nums1.begin(), nums1.end());
    }
};
```

```go
func addedInteger(nums1 []int, nums2 []int) int {
	return slices.Min(nums2) - slices.Min(nums1)
}
```

```ts
function addedInteger(nums1: number[], nums2: number[]): number {
    return Math.min(...nums2) - Math.min(...nums1);
}
```

<!-- tabs:end -->

<!-- end -->
