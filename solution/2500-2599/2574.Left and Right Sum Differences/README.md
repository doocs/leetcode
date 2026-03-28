---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2500-2599/2574.Left%20and%20Right%20Sum%20Differences/README.md
rating: 1206
source: 第 334 场周赛 Q1
tags:
    - 数组
    - 前缀和
---

<!-- problem:start -->

# [2574. 左右元素和的差值](https://leetcode.cn/problems/left-and-right-sum-differences)

[English Version](/solution/2500-2599/2574.Left%20and%20Right%20Sum%20Differences/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个下标从 <strong>0</strong> 开始的长度为&nbsp;<code>n</code>&nbsp;的整数数组 <code>nums</code>。</p>

<p>定义两个数组&nbsp;<code>leftSum</code>&nbsp;和&nbsp;<code>rightSum</code>，其中：</p>

<ul>
	<li><code>leftSum[i]</code> 是数组 <code>nums</code> 中下标 <code>i</code> 左侧元素之和。如果不存在对应的元素，<code>leftSum[i] = 0</code> 。</li>
	<li><code>rightSum[i]</code> 是数组 <code>nums</code> 中下标 <code>i</code> 右侧元素之和。如果不存在对应的元素，<code>rightSum[i] = 0</code> 。</li>
</ul>

<p>返回长度为&nbsp;<code>n</code> 数组 <code>answer</code>，其中 <code>answer[i] = |leftSum[i] - rightSum[i]|</code>。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [10,4,8,3]
<strong>输出：</strong>[15,1,11,22]
<strong>解释：</strong>数组 leftSum 为 [0,10,14,22] 且数组 rightSum 为 [15,11,3,0] 。
数组 answer 为 [|0 - 15|,|10 - 11|,|14 - 3|,|22 - 0|] = [15,1,11,22] 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [1]
<strong>输出：</strong>[0]
<strong>解释：</strong>数组 leftSum 为 [0] 且数组 rightSum 为 [0] 。
数组 answer 为 [|0 - 0|] = [0] 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 1000</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：前缀和

我们定义变量 $l$ 表示数组 $\textit{nums}$ 中下标 $i$ 左侧元素之和，变量 $r$ 表示数组 $\textit{nums}$ 中下标 $i$ 右侧元素之和。初始时 $l = 0$, $r = \sum_{i = 0}^{n - 1} \textit{nums}[i]$。

遍历数组 $\textit{nums}$，对于当前遍历到的数字 $x$，我们更新 $r = r - x$，此时 $l$ 和 $r$ 分别表示数组 $\textit{nums}$ 中下标 $i$ 左侧元素之和和右侧元素之和。我们将 $l$ 和 $r$ 的差的绝对值加入答案数组 $\textit{ans}$ 中，然后更新 $l = l + x$。

遍历结束，返回答案数组 $\textit{ans}$ 即可。

时间复杂度 $O(n)$，其中 $n$ 是数组 $\textit{nums}$ 的长度。空间复杂度 $O(1)$，不考虑返回值的空间。

相似题目：

- [0724. 寻找数组的中心下标](https://github.com/doocs/leetcode/blob/main/solution/0700-0799/0724.Find%20Pivot%20Index/README.md)
- [1991. 找到数组的中间位置](https://github.com/doocs/leetcode/blob/main/solution/1900-1999/1991.Find%20the%20Middle%20Index%20in%20Array/README.md)

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def leftRightDifference(self, nums: List[int]) -> List[int]:
        l, r = 0, sum(nums)
        ans = []
        for x in nums:
            r -= x
            ans.append(abs(l - r))
            l += x
        return ans
```

#### Java

```java
class Solution {
    public int[] leftRightDifference(int[] nums) {
        int l = 0, r = 0;
        for (int x : nums) {
            r += x;
        }
        int n = nums.length;
        int[] ans = new int[n];
        for (int i = 0; i < n; ++i) {
            r -= nums[i];
            ans[i] = Math.abs(l - r);
            l += nums[i];
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<int> leftRightDifference(vector<int>& nums) {
        int l = 0, r = 0;
        for (int x : nums) {
            r += x;
        }
        int n = nums.size();
        vector<int> ans(n);
        for (int i = 0; i < n; ++i) {
            r -= nums[i];
            ans[i] = abs(l - r);
            l += nums[i];
        }
        return ans;
    }
};
```

#### Go

```go
func leftRightDifference(nums []int) []int {
	l, r := 0, 0
	for _, x := range nums {
		r += x
	}
	n := len(nums)
	ans := make([]int, n)
	for i, x := range nums {
		r -= x
		ans[i] = abs(l - r)
		l += x
	}
	return ans
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}
```

#### TypeScript

```ts
function leftRightDifference(nums: number[]): number[] {
    let [l, r] = [0, nums.reduce((a, b) => a + b, 0)];
    const ans: number[] = [];
    for (const x of nums) {
        r -= x;
        ans.push(Math.abs(l - r));
        l += x;
    }
    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn left_right_difference(nums: Vec<i32>) -> Vec<i32> {
        let mut l = 0;
        let mut r: i32 = nums.iter().sum();
        let mut ans = Vec::with_capacity(nums.len());
        for x in nums {
            r -= x;
            ans.push((l - r).abs());
            l += x;
        }
        ans
    }
}
```

#### C

```c
/**
 * Note: The returned array must be malloced, assume caller calls free().
 */
int* leftRightDifference(int* nums, int numsSize, int* returnSize) {
    *returnSize = numsSize;
    int* ans = (int*) malloc(sizeof(int) * numsSize);

    int l = 0, r = 0;
    for (int i = 0; i < numsSize; ++i) {
        r += nums[i];
    }

    for (int i = 0; i < numsSize; ++i) {
        r -= nums[i];
        ans[i] = abs(l - r);
        l += nums[i];
    }

    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
