---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1800-1899/1874.Minimize%20Product%20Sum%20of%20Two%20Arrays/README.md
tags:
    - 贪心
    - 数组
    - 排序
---

<!-- problem:start -->

# [1874. 两个数组的最小乘积和 🔒](https://leetcode.cn/problems/minimize-product-sum-of-two-arrays)

[English Version](/solution/1800-1899/1874.Minimize%20Product%20Sum%20of%20Two%20Arrays/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定两个<strong>长度相等</strong>的数组<code>a</code>和<code>b</code>，它们的<strong>乘积和</strong>为数组中所有的<code>a[i] * b[i]</code>之和，其中<code>0 &lt;= i &lt; a.length</code>。</p>

<ul>
	<li>比如<code>a = [1,2,3,4]</code>，<code>b = [5,2,3,1]</code>时，它们的<strong>乘积和</strong>为<code>1*5 + 2*2 + 3*3 + 4*1 = 22</code></li>
</ul>

<p>现有两个长度都为<code>n</code>的数组<code>nums1</code>和<code>nums2</code>，你可以以<strong>任意顺序排序</strong><code>nums1</code>，请返回它们的<strong>最小乘积和</strong>。</p>

<p><strong>示例 1:</strong></p>

<pre><strong>输入:</strong> nums1 = [5,3,4,2], nums2 = [4,2,2,5]
<strong>输出:</strong> 40
<strong>解释: </strong>将 num1 重新排列为 [3,5,4,2] 后，可由<b> </b>[3,5,4,2] 和 [4,2,2,5] 得到最小乘积和 3*4 + 5*2 + 4*2 + 2*5 = 40。
</pre>

<p><strong>示例 2:</strong></p>

<pre><strong>输入:</strong> nums1 = [2,1,4,5,7], nums2 = [3,2,4,8,6]
<strong>输出:</strong> 65
<strong>解释:</strong> 将 num1 重新排列为 [5,7,4,1,2] 后，可由<b> </b>[5,7,4,1,2] 和 [3,2,4,8,6] 得到最小乘积和 5*3 + 7*2 + 4*4 + 1*8 + 2*6 = 65。
</pre>

<p> </p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>n == nums1.length == nums2.length</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums1[i], nums2[i] &lt;= 100</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：贪心 + 排序

<!-- thinking:start -->

> **思考**
>
> 两数组对应相乘再求和，其中一个可任意重排，求最小乘积和。正数情形下，大数应配小数。
>
> 将 $nums1$ 升序、$nums2$ 降序后对应相乘累加，即重新排列中的最小点积。

<!-- thinking:end -->

由于两个数组都是正整数，要使得乘积和最小，我们可以将两个数组中的最大值和最小值相乘，次大值和次小值相乘，以此类推。

因此，我们将数组 $\textit{nums1}$ 按照升序排序，将数组 $\textit{nums2}$ 按照降序排序，然后将两个数组对应位置的元素相乘，累加即可。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(\log n)$。其中 $n$ 是数组 $\textit{nums1}$ 的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minProductSum(self, nums1: List[int], nums2: List[int]) -> int:
        nums1.sort()
        nums2.sort(reverse=True)
        return sum(x * y for x, y in zip(nums1, nums2))
```

#### Java

```java
class Solution {
    public int minProductSum(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int n = nums1.length;
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            ans += nums1[i] * nums2[n - i - 1];
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int minProductSum(vector<int>& nums1, vector<int>& nums2) {
        ranges::sort(nums1);
        ranges::sort(nums2, greater<int>());
        int n = nums1.size();
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            ans += nums1[i] * nums2[i];
        }
        return ans;
    }
};
```

#### Go

```go
func minProductSum(nums1 []int, nums2 []int) (ans int) {
	sort.Ints(nums1)
	sort.Ints(nums2)
	for i, x := range nums1 {
		ans += x * nums2[len(nums2)-1-i]
	}
	return
}
```

#### TypeScript

```ts
function minProductSum(nums1: number[], nums2: number[]): number {
    nums1.sort((a, b) => a - b);
    nums2.sort((a, b) => b - a);
    let ans = 0;
    for (let i = 0; i < nums1.length; ++i) {
        ans += nums1[i] * nums2[i];
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
