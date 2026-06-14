---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3900-3999/3925.Concatenate%20Array%20With%20Reverse/README.md
rating: 1176
source: 第 501 场周赛 Q1
tags:
    - 数组
    - 模拟
---

<!-- problem:start -->

# [3925. 连接逆序数组](https://leetcode.cn/problems/concatenate-array-with-reverse)

[English Version](/solution/3900-3999/3925.Concatenate%20Array%20With%20Reverse/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个长度为 <code>n</code> 的整数数组 <code>nums</code>。</p>

<p>构造一个新的长度为 <code>2 * n</code> 的数组 <code>ans</code>，其中前 <code>n</code> 个元素与 <code>nums</code> 相同，后 <code>n</code> 个元素为 <code>nums</code> 的逆序。</p>

<p>具体而言，对于 <code>0 &lt;= i &lt;= n - 1</code>：</p>

<ul>
	<li><code>ans[i] = nums[i]</code></li>
	<li><code>ans[i + n] = nums[n - i - 1]</code></li>
</ul>

<p>返回整数数组 <code>ans</code>。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [1,2,3]</span></p>

<p><strong>输出：</strong> <span class="example-io">[1,2,3,3,2,1]</span></p>

<p><strong>解释：</strong></p>

<p><code>ans</code> 的前 <code>n</code> 个元素与 <code>nums</code> 相同。</p>

<p>接下来的 <code>n = 3</code> 个元素按照 <code>nums</code> 的逆序填入：</p>

<ul>
	<li><code>ans[3] = nums[2] = 3</code></li>
	<li><code>ans[4] = nums[1] = 2</code></li>
	<li><code>ans[5] = nums[0] = 1</code></li>
</ul>

<p>因此，<code>ans = [1, 2, 3, 3, 2, 1]</code>。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [1]</span></p>

<p><strong>输出：</strong> <span class="example-io">[1,1]</span></p>

<p><strong>解释：</strong></p>

<p>数组逆序后保持不变。因此，<code>ans = [1, 1]</code>。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 100</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 100</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：模拟

我们创建一个长度为 $2 \times n$ 的数组 $\textit{ans}$，前 $n$ 个元素与 $\textit{nums}$ 相同，后 $n$ 个元素为 $\textit{nums}$ 的逆序。

具体而言，对于 $0 \leq i \leq n - 1$，我们将 $\textit{ans}[i]$ 赋值为 $\textit{nums}[i]$，将 $\textit{ans}[i + n]$ 赋值为 $\textit{nums}[n - i - 1]$。

最后返回数组 $\textit{ans}$ 即可。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 是数组 $\textit{nums}$ 的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def concatWithReverse(self, nums: list[int]) -> list[int]:
        n = len(nums)
        ans = [0] * (2 * n)
        for i, x in enumerate(nums):
            ans[i] = x
            ans[i + n] = nums[n - i - 1]
        return ans
```

#### Java

```java
class Solution {
    public int[] concatWithReverse(int[] nums) {
        int n = nums.length;
        int[] ans = new int[2 * n];
        for (int i = 0; i < n; ++i) {
            ans[i] = nums[i];
            ans[i + n] = nums[n - i - 1];
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<int> concatWithReverse(vector<int>& nums) {
        int n = nums.size();
        vector<int> ans(2 * n);
        for (int i = 0; i < n; ++i) {
            ans[i] = nums[i];
            ans[i + n] = nums[n - i - 1];
        }
        return ans;
    }
};
```

#### Go

```go
func concatWithReverse(nums []int) []int {
	n := len(nums)
	ans := make([]int, 2*n)
	for i, x := range nums {
		ans[i] = x
		ans[i+n] = nums[n-i-1]
	}
	return ans
}
```

#### TypeScript

```ts
function concatWithReverse(nums: number[]): number[] {
    const n = nums.length;
    const ans: number[] = new Array(2 * n);
    for (let i = 0; i < n; ++i) {
        ans[i] = nums[i];
        ans[i + n] = nums[n - i - 1];
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
