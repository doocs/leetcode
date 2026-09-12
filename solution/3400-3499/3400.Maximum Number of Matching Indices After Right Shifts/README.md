---
comments: true
difficulty: 中等
tags:
    - 数组
    - 双指针
    - 模拟
---

<!-- problem:start -->

# [3400. 右移后的最大匹配索引数 🔒](https://leetcode.cn/problems/maximum-number-of-matching-indices-after-right-shifts)

[English Version](/solution/3400-3499/3400.Maximum%20Number%20of%20Matching%20Indices%20After%20Right%20Shifts/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定两个长度相同的整数数组&nbsp;<code>nums1</code> 和&nbsp;<code>nums2</code>。</p>

<p>如果&nbsp;<code>nums1[i] == nums2[i]</code>&nbsp;则认为下标&nbsp;<code>i</code> 是 <strong>匹配</strong> 的。</p>

<p>返回在&nbsp;<code>nums1</code>&nbsp;上进行任意次数 <strong>右移</strong>&nbsp;后 <strong>最大</strong>&nbsp;的 <strong>匹配&nbsp;</strong>下标数量。</p>

<p><strong>右移&nbsp;</strong>是对于所有下标，将位于下标&nbsp;<code>i</code>&nbsp;的元素移动到&nbsp;<code>(i + 1) % n</code>。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong><span class="example-io">nums1 = [3,1,2,3,1,2], nums2 = [1,2,3,1,2,3]</span></p>

<p><span class="example-io"><b>输出：</b>6</span></p>

<p><strong>解释：</strong></p>

<p>如果我们右移&nbsp;<code>nums1</code> 2 次，它变为&nbsp;<code>[1, 2, 3, 1, 2, 3]</code>。每个下标都匹配，所以输出为 6。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>nums1 = [1,4,2,5,3,1], nums2 = [2,3,1,2,4,6]</span></p>

<p><span class="example-io"><b>输出：</b>3</span></p>

<p><strong>解释：</strong></p>

<p>如果我们右移&nbsp;<code>nums1</code> 3 次，它变为&nbsp;<code>[5, 3, 1, 1, 4, 2]</code>。下标 1，2，4 匹配，所以输出为 3。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>nums1.length == nums2.length</code></li>
	<li><code>1 &lt;= nums1.length, nums2.length &lt;= 3000</code></li>
	<li><code>1 &lt;= nums1[i], nums2[i] &lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：枚举

<!-- thinking:start -->

> **思考**
>
> 题目要求对 $\textit{nums1}$ 做若干次循环右移后，统计与 $\textit{nums2}$ 同下标相等的位置数，并取最大值。若对每个偏移都物化一份新数组，额外的写入并不能改变比较次数。
>
> $n \le 3000$，枚举全部 $n$ 种偏移并逐位比较的时间约为 $O(n^2)$，可以接受。匹配只取决于相对错位，因此不必真正旋转。
>
> 注意到右移 $k$ 次后，原下标 $(i+k)\bmod n$ 的元素落到位置 $i$。为此枚举 $k$，用 $\textit{nums1}[(i+k)\bmod n]$ 与 $\textit{nums2}[i]$ 比较并累计，再取最大值即可。

<!-- thinking:end -->

我们可以枚举右移的次数 $k$，其中 $0 \leq k \lt n$。对于每一个 $k$，我们可以计算出右移 $k$ 次后的数组 $\textit{nums1}$ 和 $\textit{nums2}$ 的匹配下标数量，取最大值作为答案即可。

时间复杂度 $O(n^2)$，其中 $n$ 为数组 $\textit{nums1}$ 的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maximumMatchingIndices(self, nums1: List[int], nums2: List[int]) -> int:
        n = len(nums1)
        ans = 0
        for k in range(n):
            t = sum(nums1[(i + k) % n] == x for i, x in enumerate(nums2))
            ans = max(ans, t)
        return ans
```

#### Java

```java
class Solution {
    public int maximumMatchingIndices(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int ans = 0;
        for (int k = 0; k < n; ++k) {
            int t = 0;
            for (int i = 0; i < n; ++i) {
                if (nums1[(i + k) % n] == nums2[i]) {
                    ++t;
                }
            }
            ans = Math.max(ans, t);
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int maximumMatchingIndices(vector<int>& nums1, vector<int>& nums2) {
        int n = nums1.size();
        int ans = 0;
        for (int k = 0; k < n; ++k) {
            int t = 0;
            for (int i = 0; i < n; ++i) {
                if (nums1[(i + k) % n] == nums2[i]) {
                    ++t;
                }
            }
            ans = max(ans, t);
        }
        return ans;
    }
};
```

#### Go

```go
func maximumMatchingIndices(nums1 []int, nums2 []int) (ans int) {
	n := len(nums1)
	for k := range nums1 {
		t := 0
		for i, x := range nums2 {
			if nums1[(i+k)%n] == x {
				t++
			}
		}
		ans = max(ans, t)
	}
	return
}
```

#### TypeScript

```ts
function maximumMatchingIndices(nums1: number[], nums2: number[]): number {
    const n = nums1.length;
    let ans: number = 0;
    for (let k = 0; k < n; ++k) {
        let t: number = 0;
        for (let i = 0; i < n; ++i) {
            if (nums1[(i + k) % n] === nums2[i]) {
                ++t;
            }
        }
        ans = Math.max(ans, t);
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
