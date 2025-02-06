---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1900-1999/1920.Build%20Array%20from%20Permutation/README.md
rating: 1160
source: 第 248 场周赛 Q1
tags:
    - 数组
    - 模拟
---

<!-- problem:start -->

# [1920. 基于排列构建数组](https://leetcode.cn/problems/build-array-from-permutation)

[English Version](/solution/1900-1999/1920.Build%20Array%20from%20Permutation/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个 <strong>从 0 开始的排列</strong> <code>nums</code>（<strong>下标也从 0 开始</strong>）。请你构建一个 <strong>同样长度</strong> 的数组 <code>ans</code> ，其中，对于每个 <code>i</code>（<code>0 &lt;= i &lt; nums.length</code>），都满足 <code>ans[i] = nums[nums[i]]</code> 。返回构建好的数组 <code>ans</code> 。</p>

<p><strong>从 0 开始的排列</strong> <code>nums</code> 是一个由 <code>0</code> 到&nbsp;<code>nums.length - 1</code>（<code>0</code> 和 <code>nums.length - 1</code> 也包含在内）的不同整数组成的数组。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [0,2,1,5,3,4]
<strong>输出：</strong>[0,1,2,4,5,3]<strong>
解释：</strong>数组 ans 构建如下：
ans = [nums[nums[0]], nums[nums[1]], nums[nums[2]], nums[nums[3]], nums[nums[4]], nums[nums[5]]]
    = [nums[0], nums[2], nums[1], nums[5], nums[3], nums[4]]
    = [0,1,2,4,5,3]</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [5,0,1,2,3,4]
<strong>输出：</strong>[4,5,0,1,2,3]
<strong>解释：</strong>数组 ans 构建如下：
ans = [nums[nums[0]], nums[nums[1]], nums[nums[2]], nums[nums[3]], nums[nums[4]], nums[nums[5]]]
    = [nums[5], nums[0], nums[1], nums[2], nums[3], nums[4]]
    = [4,5,0,1,2,3]</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 1000</code></li>
	<li><code>0 &lt;= nums[i] &lt; nums.length</code></li>
	<li><code>nums</code> 中的元素 <strong>互不相同</strong></li>
</ul>

<p>&nbsp;</p>

<p><strong>进阶：</strong>你能在不使用额外空间的情况下解决此问题吗（即 <code>O(1)</code> 内存）？</p>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：模拟

我们可以直接模拟题目描述的过程，构建一个新的数组 $\textit{ans}$，对于每个 $i$，令 $\textit{ans}[i] = \textit{nums}[\textit{nums}[i]]$。

时间复杂度 $O(n)$，其中 $n$ 是数组 $\textit{nums}$ 的长度。忽略答案数组的空间消耗，空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def buildArray(self, nums: List[int]) -> List[int]:
        return [nums[num] for num in nums]
```

#### Java

```java
class Solution {
    public int[] buildArray(int[] nums) {
        int[] ans = new int[nums.length];
        for (int i = 0; i < nums.length; ++i) {
            ans[i] = nums[nums[i]];
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<int> buildArray(vector<int>& nums) {
        vector<int> ans;
        for (int& num : nums) {
            ans.push_back(nums[num]);
        }
        return ans;
    }
};
```

#### Go

```go
func buildArray(nums []int) []int {
	ans := make([]int, len(nums))
	for i, num := range nums {
		ans[i] = nums[num]
	}
	return ans
}
```

#### TypeScript

```ts
function buildArray(nums: number[]): number[] {
    return nums.map(x => nums[x]);
}
```

#### Rust

```rust
impl Solution {
    pub fn build_array(nums: Vec<i32>) -> Vec<i32> {
        nums.iter().map(|&v| nums[v as usize]).collect()
    }
}
```

#### JavaScript

```js
/**
 * @param {number[]} nums
 * @return {number[]}
 */
var buildArray = function (nums) {
    return nums.map(x => nums[x]);
};
```

#### C

```c
/**
 * Note: The returned array must be malloced, assume caller calls free().
 */
int* buildArray(int* nums, int numsSize, int* returnSize) {
    int* ans = malloc(sizeof(int) * numsSize);
    for (int i = 0; i < numsSize; i++) {
        ans[i] = nums[nums[i]];
    }
    *returnSize = numsSize;
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
