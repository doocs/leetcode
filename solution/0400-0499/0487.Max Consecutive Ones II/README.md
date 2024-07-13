---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0400-0499/0487.Max%20Consecutive%20Ones%20II/README.md
tags:
    - 数组
    - 动态规划
    - 滑动窗口
---

<!-- problem:start -->

# [487. 最大连续1的个数 II 🔒](https://leetcode.cn/problems/max-consecutive-ones-ii)

[English Version](/solution/0400-0499/0487.Max%20Consecutive%20Ones%20II/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定一个二进制数组 <code>nums</code> ，如果最多可以翻转一个 <code>0</code> ，则返回数组中连续 <code>1</code> 的最大个数。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,0,1,1,0]
<strong>输出：</strong>4
<strong>解释：</strong>翻转第一个 0 可以得到最长的连续 1。
&nbsp;    当翻转以后，最大连续 1 的个数为 4。
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<b>输入：</b>nums = [1,0,1,1,0,1]
<b>输出：</b>4
</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>nums[i]</code>&nbsp;不是&nbsp;<code>0</code>&nbsp;就是&nbsp;<code>1</code>.</li>
</ul>

<p>&nbsp;</p>

<p><strong>进阶：</strong>如果输入的数字是作为<strong> 无限流 </strong>逐个输入如何处理？换句话说，内存不能存储下所有从流中输入的数字。您可以有效地解决吗？</p>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：滑动窗口

我们可以遍历数组，用一个变量 $\textit{cnt}$ 记录当前窗口中 0 的个数，当 $\textit{cnt} > 1$ 时，我们将窗口的左边界右移一位。

遍历结束后，窗口的长度即为最大连续 1 的个数。

注意，在上述过程中，我们不需要循环将窗口的左边界右移，而是直接将左边界右移一位，这是因为，题目求的是最大连续 1 的个数，因此，窗口的长度只会增加，不会减少，所以我们不需要循环右移左边界。

时间复杂度 $O(n)$，其中 $n$ 为数组的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def findMaxConsecutiveOnes(self, nums: List[int]) -> int:
        l = cnt = 0
        for x in nums:
            cnt += x ^ 1
            if cnt > 1:
                cnt -= nums[l] ^ 1
                l += 1
        return len(nums) - l
```

#### Java

```java
class Solution {
    public int findMaxConsecutiveOnes(int[] nums) {
        int l = 0, cnt = 0;
        for (int x : nums) {
            cnt += x ^ 1;
            if (cnt > 1) {
                cnt -= nums[l++] ^ 1;
            }
        }
        return nums.length - l;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int findMaxConsecutiveOnes(vector<int>& nums) {
        int l = 0, cnt = 0;
        for (int x : nums) {
            cnt += x ^ 1;
            if (cnt > 1) {
                cnt -= nums[l++] ^ 1;
            }
        }
        return nums.size() - l;
    }
};
```

#### Go

```go
func findMaxConsecutiveOnes(nums []int) int {
	l, cnt := 0, 0
	for _, x := range nums {
		cnt += x ^ 1
		if cnt > 1 {
			cnt -= nums[l] ^ 1
			l++
		}
	}
	return len(nums) - l
}
```

#### TypeScript

```ts
function findMaxConsecutiveOnes(nums: number[]): number {
    let [l, cnt] = [0, 0];
    for (const x of nums) {
        cnt += x ^ 1;
        if (cnt > 1) {
            cnt -= nums[l++] ^ 1;
        }
    }
    return nums.length - l;
}
```

#### JavaScript

```js
/**
 * @param {number[]} nums
 * @return {number}
 */
var findMaxConsecutiveOnes = function (nums) {
    let [l, cnt] = [0, 0];
    for (const x of nums) {
        cnt += x ^ 1;
        if (cnt > 1) {
            cnt -= nums[l++] ^ 1;
        }
    }
    return nums.length - l;
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
