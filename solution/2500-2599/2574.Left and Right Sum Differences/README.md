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

我们定义变量 $left$ 表示数组 `nums` 中下标 $i$ 左侧元素之和，变量 $right$ 表示数组 `nums` 中下标 $i$ 右侧元素之和。初始时 $left = 0$, $right = \sum_{i = 0}^{n - 1} nums[i]$。

遍历数组 `nums`，对于当前遍历到的数字 $x$，我们更新 $right = right - x$，此时 $left$ 和 $right$ 分别表示数组 `nums` 中下标 $i$ 左侧元素之和和右侧元素之和。我们将 $left$ 和 $right$ 的差的绝对值加入答案数组 `ans` 中，然后更新 $left = left + x$。

遍历完成后，返回答案数组 `ans` 即可。

时间复杂度 $O(n)$，空间复杂度 $O(1)$。其中 $n$ 为数组 `nums` 的长度。

相似题目：

-   [0724. 寻找数组的中心下标](https://github.com/doocs/leetcode/blob/main/solution/0700-0799/0724.Find%20Pivot%20Index/README.md)
-   [1991. 找到数组的中间位置](https://github.com/doocs/leetcode/blob/main/solution/1900-1999/1991.Find%20the%20Middle%20Index%20in%20Array/README.md)

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def leftRigthDifference(self, nums: List[int]) -> List[int]:
        left, right = 0, sum(nums)
        ans = []
        for x in nums:
            right -= x
            ans.append(abs(left - right))
            left += x
        return ans
```

#### Java

```java
class Solution {
    public int[] leftRigthDifference(int[] nums) {
        int left = 0, right = Arrays.stream(nums).sum();
        int n = nums.length;
        int[] ans = new int[n];
        for (int i = 0; i < n; ++i) {
            right -= nums[i];
            ans[i] = Math.abs(left - right);
            left += nums[i];
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<int> leftRigthDifference(vector<int>& nums) {
        int left = 0, right = accumulate(nums.begin(), nums.end(), 0);
        vector<int> ans;
        for (int& x : nums) {
            right -= x;
            ans.push_back(abs(left - right));
            left += x;
        }
        return ans;
    }
};
```

#### Go

```go
func leftRigthDifference(nums []int) (ans []int) {
	var left, right int
	for _, x := range nums {
		right += x
	}
	for _, x := range nums {
		right -= x
		ans = append(ans, abs(left-right))
		left += x
	}
	return
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
function leftRigthDifference(nums: number[]): number[] {
    let left = 0,
        right = nums.reduce((a, b) => a + b);
    const ans: number[] = [];
    for (const x of nums) {
        right -= x;
        ans.push(Math.abs(left - right));
        left += x;
    }
    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn left_rigth_difference(nums: Vec<i32>) -> Vec<i32> {
        let mut left = 0;
        let mut right = nums.iter().sum::<i32>();
        nums.iter()
            .map(|v| {
                right -= v;
                let res = (left - right).abs();
                left += v;
                res
            })
            .collect()
    }
}
```

#### C

```c
/**
 * Note: The returned array must be malloced, assume caller calls free().
 */
int* leftRigthDifference(int* nums, int numsSize, int* returnSize) {
    int left = 0;
    int right = 0;
    for (int i = 0; i < numsSize; i++) {
        right += nums[i];
    }
    int* ans = malloc(sizeof(int) * numsSize);
    for (int i = 0; i < numsSize; i++) {
        right -= nums[i];
        ans[i] = abs(left - right);
        left += nums[i];
    }
    *returnSize = numsSize;
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法二

<!-- tabs:start -->

#### TypeScript

```ts
function leftRigthDifference(nums: number[]): number[] {
    let left = 0;
    let right = nums.reduce((r, v) => r + v);
    return nums.map(v => {
        right -= v;
        const res = Math.abs(left - right);
        left += v;
        return res;
    });
}
```

#### Rust

```rust
impl Solution {
    pub fn left_right_difference(nums: Vec<i32>) -> Vec<i32> {
        let mut ans = vec![];

        for i in 0..nums.len() {
            let mut left = 0;
            for j in 0..i {
                left += nums[j];
            }

            let mut right = 0;
            for k in i + 1..nums.len() {
                right += nums[k];
            }

            ans.push((left - right).abs());
        }

        ans
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法三

<!-- tabs:start -->

#### Rust

```rust
impl Solution {
    pub fn left_right_difference(nums: Vec<i32>) -> Vec<i32> {
        let mut left = 0;
        let mut right: i32 = nums.iter().sum();
        let mut ans = vec![];

        for &x in &nums {
            right -= x;
            ans.push((left - right).abs());
            left += x;
        }

        ans
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
