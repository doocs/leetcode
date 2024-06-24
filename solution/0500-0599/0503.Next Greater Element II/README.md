---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0500-0599/0503.Next%20Greater%20Element%20II/README.md
tags:
    - 栈
    - 数组
    - 单调栈
---

<!-- problem:start -->

# [503. 下一个更大元素 II](https://leetcode.cn/problems/next-greater-element-ii)

[English Version](/solution/0500-0599/0503.Next%20Greater%20Element%20II/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定一个循环数组&nbsp;<code>nums</code>&nbsp;（&nbsp;<code>nums[nums.length - 1]</code>&nbsp;的下一个元素是&nbsp;<code>nums[0]</code>&nbsp;），返回&nbsp;<em><code>nums</code>&nbsp;中每个元素的 <strong>下一个更大元素</strong></em> 。</p>

<p>数字 <code>x</code>&nbsp;的 <strong>下一个更大的元素</strong> 是按数组遍历顺序，这个数字之后的第一个比它更大的数，这意味着你应该循环地搜索它的下一个更大的数。如果不存在，则输出 <code>-1</code>&nbsp;。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入:</strong> nums = [1,2,1]
<strong>输出:</strong> [2,-1,2]
<strong>解释:</strong> 第一个 1 的下一个更大的数是 2；
数字 2 找不到下一个更大的数； 
第二个 1 的下一个最大的数需要循环搜索，结果也是 2。
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入:</strong> nums = [1,2,3,4,3]
<strong>输出:</strong> [2,3,4,-1,4]
</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>4</sup></code></li>
	<li><code>-10<sup>9</sup>&nbsp;&lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：单调栈

题目需要我们找到每个元素的下一个更大元素，那么我们可以从后往前遍历数组，这样可以将问题为求上一个更大元素。另外，由于数组是循环的，我们可以将数组遍历两次。

具体地，我们从下标 $n \times 2 - 1$ 开始向前遍历数组，其中 $n$ 是数组的长度。然后，我们记 $j = i \bmod n$，其中 $\bmod$ 表示取模运算。如果栈不为空且栈顶元素小于等于 $nums[j]$，那么我们就不断地弹出栈顶元素，直到栈为空或者栈顶元素大于 $nums[j]$。此时，栈顶元素就是 $nums[j]$ 的上一个更大元素，我们将其赋给 $ans[j]$。最后，我们将 $nums[j]$ 入栈。继续遍历下一个元素。

遍历结束后，我们就可以得到数组 $ans$，它是数组 $nums$ 中每个元素的下一个更大元素。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 是数组 $nums$ 的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def nextGreaterElements(self, nums: List[int]) -> List[int]:
        n = len(nums)
        ans = [-1] * n
        stk = []
        for i in range(n * 2 - 1, -1, -1):
            i %= n
            while stk and stk[-1] <= nums[i]:
                stk.pop()
            if stk:
                ans[i] = stk[-1]
            stk.append(nums[i])
        return ans
```

#### Java

```java
class Solution {
    public int[] nextGreaterElements(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n];
        Arrays.fill(ans, -1);
        Deque<Integer> stk = new ArrayDeque<>();
        for (int i = n * 2 - 1; i >= 0; --i) {
            int j = i % n;
            while (!stk.isEmpty() && stk.peek() <= nums[j]) {
                stk.pop();
            }
            if (!stk.isEmpty()) {
                ans[j] = stk.peek();
            }
            stk.push(nums[j]);
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<int> nextGreaterElements(vector<int>& nums) {
        int n = nums.size();
        vector<int> ans(n, -1);
        stack<int> stk;
        for (int i = n * 2 - 1; ~i; --i) {
            int j = i % n;
            while (stk.size() && stk.top() <= nums[j]) {
                stk.pop();
            }
            if (stk.size()) {
                ans[j] = stk.top();
            }
            stk.push(nums[j]);
        }
        return ans;
    }
};
```

#### Go

```go
func nextGreaterElements(nums []int) []int {
	n := len(nums)
	ans := make([]int, n)
	for i := range ans {
		ans[i] = -1
	}
	stk := []int{}
	for i := n*2 - 1; i >= 0; i-- {
		j := i % n
		for len(stk) > 0 && stk[len(stk)-1] <= nums[j] {
			stk = stk[:len(stk)-1]
		}
		if len(stk) > 0 {
			ans[j] = stk[len(stk)-1]
		}
		stk = append(stk, nums[j])
	}
	return ans
}
```

#### TypeScript

```ts
function nextGreaterElements(nums: number[]): number[] {
    const n = nums.length;
    const stk: number[] = [];
    const ans: number[] = Array(n).fill(-1);
    for (let i = n * 2 - 1; ~i; --i) {
        const j = i % n;
        while (stk.length && stk.at(-1)! <= nums[j]) {
            stk.pop();
        }
        if (stk.length) {
            ans[j] = stk.at(-1)!;
        }
        stk.push(nums[j]);
    }
    return ans;
}
```

#### JavaScript

```js
/**
 * @param {number[]} nums
 * @return {number[]}
 */
var nextGreaterElements = function (nums) {
    const n = nums.length;
    const stk = [];
    const ans = Array(n).fill(-1);
    for (let i = n * 2 - 1; ~i; --i) {
        const j = i % n;
        while (stk.length && stk.at(-1) <= nums[j]) {
            stk.pop();
        }
        if (stk.length) {
            ans[j] = stk.at(-1);
        }
        stk.push(nums[j]);
    }
    return ans;
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
