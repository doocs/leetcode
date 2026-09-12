---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3100-3199/3173.Bitwise%20OR%20of%20Adjacent%20Elements/README.md
tags:
    - 位运算
    - 数组
---

<!-- problem:start -->

# [3173. 相邻元素的按位或 🔒](https://leetcode.cn/problems/bitwise-or-of-adjacent-elements)

[English Version](/solution/3100-3199/3173.Bitwise%20OR%20of%20Adjacent%20Elements/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定一个长度为 <code>n</code>&nbsp;的数组&nbsp;<code>nums</code>，返回一个长度为 <code>n - 1</code>&nbsp;的数组&nbsp;<code>answer</code>&nbsp;使得&nbsp;<code>answer[i] = nums[i] | nums[i + 1]</code>，其中&nbsp;<code>|</code>&nbsp;表示按位&nbsp;<code>OR</code>&nbsp;操作。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong><span class="example-io">nums = [1,3,7,15]</span></p>

<p><strong>输出：</strong><span class="example-io">[3,7,15]</span></p>

<p>&nbsp;</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong><span class="example-io">nums = [8,4,2]</span></p>

<p><strong>输出：</strong><span class="example-io">[12,6]</span></p>

<p>&nbsp;</p>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><strong>输入：</strong><span class="example-io">nums = [5,4,9,11]</span></p>

<p><strong>输出：</strong><span class="example-io">[5,13,11]</span></p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= nums.length &lt;= 100</code></li>
	<li><code>0 &lt;= nums[i]&nbsp;&lt;= 100</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：遍历

<!-- thinking:start -->

> **思考**
>
> 答案第 $i$ 位定义为 $nums[i]\lor nums[i+1]$，与更远元素无关。
>
> 一对一对计算即可，无需前缀或。
>
> 对 `pairwise(nums)` 取或，得到长 $n-1$ 的数组。

<!-- thinking:end -->

我们遍历数组的前 $n - 1$ 个元素，对于每个元素，计算它和它的下一个元素的按位或值，将结果存入答案数组中。

时间复杂度 $O(n)$，其中 $n$ 是数组的长度。忽略答案数组的空间消耗，空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def orArray(self, nums: List[int]) -> List[int]:
        return [a | b for a, b in pairwise(nums)]
```

#### Java

```java
class Solution {
    public int[] orArray(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n - 1];
        for (int i = 0; i < n - 1; ++i) {
            ans[i] = nums[i] | nums[i + 1];
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<int> orArray(vector<int>& nums) {
        int n = nums.size();
        vector<int> ans(n - 1);
        for (int i = 0; i < n - 1; ++i) {
            ans[i] = nums[i] | nums[i + 1];
        }
        return ans;
    }
};
```

#### Go

```go
func orArray(nums []int) (ans []int) {
	for i, x := range nums[1:] {
		ans = append(ans, x|nums[i])
	}
	return
}
```

#### TypeScript

```ts
function orArray(nums: number[]): number[] {
    return nums.slice(0, -1).map((v, i) => v | nums[i + 1]);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
