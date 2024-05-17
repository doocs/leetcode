---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3000-3099/3002.Maximum%20Size%20of%20a%20Set%20After%20Removals/README.md
rating: 1917
source: 第 379 场周赛 Q3
tags:
    - 贪心
    - 数组
    - 哈希表
---

<!-- problem:start -->

# [3002. 移除后集合的最多元素数](https://leetcode.cn/problems/maximum-size-of-a-set-after-removals)

[English Version](/solution/3000-3099/3002.Maximum%20Size%20of%20a%20Set%20After%20Removals/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你两个下标从 <code>0</code> 开始的整数数组 <code>nums1</code> 和 <code>nums2</code> ，它们的长度都是偶数<code> n</code> 。</p>

<p>你必须从 <code>nums1</code> 中移除 <code>n / 2</code> 个元素，同时从 <code>nums2</code> 中也移除 <code>n / 2</code> 个元素。移除之后，你将 <code>nums1</code> 和 <code>nums2</code> 中剩下的元素插入到集合 <code>s</code> 中。</p>

<p>返回集合 <code>s</code>可能的<strong> 最多 </strong>包含多少元素。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums1 = [1,2,1,2], nums2 = [1,1,1,1]
<strong>输出：</strong>2
<strong>解释：</strong>从 nums1 和 nums2 中移除两个 1 。移除后，数组变为 nums1 = [2,2] 和 nums2 = [1,1] 。因此，s = {1,2} 。
可以证明，在移除之后，集合 s 最多可以包含 2 个元素。
</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums1 = [1,2,3,4,5,6], nums2 = [2,3,2,3,2,3]
<strong>输出：</strong>5
<strong>解释：</strong>从 nums1 中移除 2、3 和 6 ，同时从 nums2 中移除两个 3 和一个 2 。移除后，数组变为 nums1 = [1,4,5] 和 nums2 = [2,3,2] 。因此，s = {1,2,3,4,5} 。
可以证明，在移除之后，集合 s 最多可以包含 5 个元素。 
</pre>

<p><strong class="example">示例 3：</strong></p>

<pre>
<strong>输入：</strong>nums1 = [1,1,2,2,3,3], nums2 = [4,4,5,5,6,6]
<strong>输出：</strong>6
<strong>解释：</strong>从 nums1 中移除 1、2 和 3 ，同时从 nums2 中移除 4、5 和 6 。移除后，数组变为 nums1 = [1,2,3] 和 nums2 = [4,5,6] 。因此，s = {1,2,3,4,5,6} 。
可以证明，在移除之后，集合 s 最多可以包含 6 个元素。 </pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == nums1.length == nums2.length</code></li>
	<li><code>1 &lt;= n &lt;= 2 * 10<sup>4</sup></code></li>
	<li><code>n</code>是偶数。</li>
	<li><code>1 &lt;= nums1[i], nums2[i] &lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一

<!-- tabs:start -->

```python
class Solution:
    def maximumSetSize(self, nums1: List[int], nums2: List[int]) -> int:
        s1 = set(nums1)
        s2 = set(nums2)
        n = len(nums1)
        a = min(len(s1 - s2), n // 2)
        b = min(len(s2 - s1), n // 2)
        return min(a + b + len(s1 & s2), n)
```

```java
class Solution {
    public int maximumSetSize(int[] nums1, int[] nums2) {
        Set<Integer> s1 = new HashSet<>();
        Set<Integer> s2 = new HashSet<>();
        for (int x : nums1) {
            s1.add(x);
        }
        for (int x : nums2) {
            s2.add(x);
        }
        int n = nums1.length;
        int a = 0, b = 0, c = 0;
        for (int x : s1) {
            if (!s2.contains(x)) {
                ++a;
            }
        }
        for (int x : s2) {
            if (!s1.contains(x)) {
                ++b;
            } else {
                ++c;
            }
        }
        a = Math.min(a, n / 2);
        b = Math.min(b, n / 2);
        return Math.min(a + b + c, n);
    }
}
```

```cpp
class Solution {
public:
    int maximumSetSize(vector<int>& nums1, vector<int>& nums2) {
        unordered_set<int> s1(nums1.begin(), nums1.end());
        unordered_set<int> s2(nums2.begin(), nums2.end());
        int n = nums1.size();
        int a = 0, b = 0, c = 0;
        for (int x : s1) {
            if (!s2.count(x)) {
                ++a;
            }
        }
        for (int x : s2) {
            if (!s1.count(x)) {
                ++b;
            } else {
                ++c;
            }
        }
        a = min(a, n / 2);
        b = min(b, n / 2);
        return min(a + b + c, n);
    }
};
```

```go
func maximumSetSize(nums1 []int, nums2 []int) int {
	s1 := map[int]bool{}
	s2 := map[int]bool{}
	for _, x := range nums1 {
		s1[x] = true
	}
	for _, x := range nums2 {
		s2[x] = true
	}
	a, b, c := 0, 0, 0
	for x := range s1 {
		if !s2[x] {
			a++
		}
	}
	for x := range s2 {
		if !s1[x] {
			b++
		} else {
			c++
		}
	}
	n := len(nums1)
	a = min(a, n/2)
	b = min(b, n/2)
	return min(a+b+c, n)
}
```

```ts
function maximumSetSize(nums1: number[], nums2: number[]): number {
    const s1: Set<number> = new Set(nums1);
    const s2: Set<number> = new Set(nums2);
    const n = nums1.length;
    let [a, b, c] = [0, 0, 0];
    for (const x of s1) {
        if (!s2.has(x)) {
            ++a;
        }
    }
    for (const x of s2) {
        if (!s1.has(x)) {
            ++b;
        } else {
            ++c;
        }
    }
    a = Math.min(a, n >> 1);
    b = Math.min(b, n >> 1);
    return Math.min(a + b + c, n);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
