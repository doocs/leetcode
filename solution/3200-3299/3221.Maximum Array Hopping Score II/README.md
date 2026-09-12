---
comments: true
difficulty: 中等
tags:
    - 栈
    - 贪心
    - 数组
    - 单调栈
---

<!-- problem:start -->

# [3221. 最大数组跳跃得分 II 🔒](https://leetcode.cn/problems/maximum-array-hopping-score-ii)

[English Version](/solution/3200-3299/3221.Maximum%20Array%20Hopping%20Score%20II/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定一个数组&nbsp;<code>nums</code>，你必须从索引 0 开始跳跃，直到到达数组的最后一个元素，使得获取 <strong>最大</strong> 分数。</p>

<p>每一次 <strong>跳跃</strong> 中，你可以从下标&nbsp;<code>i</code>&nbsp;跳到一个&nbsp;<code>j &gt; i</code>&nbsp;的下标，并且可以得到&nbsp;<code>(j - i) * nums[j]</code>&nbsp;的分数。</p>

<p>返回你能够取得的最大分数。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><b>输入：</b>nums = [1,5,8]</p>

<p><b>输出：</b>16</p>

<p><strong>解释：</strong></p>

<p>有两种可能的方法可以到达最后一个元素：</p>

<ul>
	<li><code>0 -&gt; 1 -&gt; 2</code> 得分为&nbsp;<code>(1 - 0) * 5 + (2 - 1) * 8 = 13</code>。</li>
	<li><code>0 -&gt; 2</code> 得分为&nbsp;<code>(2 - 0) * 8 =&nbsp;16</code>。</li>
</ul>

<p><strong>示例 2：</strong></p>

<p><b>输入：</b>nums = [4,5,2,8,9,1,3]</p>

<p><b>输出：</b>42</p>

<p><strong>解释：</strong></p>

<p>我们可以按&nbsp;<code>0 -&gt; 4 -&gt; 6</code>&nbsp;进行跳跃，得分为&nbsp;<code>(4 - 0) * 9 + (6 - 4) * 3 = 42</code>。</p>

<p>&nbsp;</p>

<p><b>提示：</b></p>

<ul>
	<li><code>2 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：单调栈

<!-- thinking:start -->

> **思考**
>
> 跳跃得分规则与「数组跳跃得分 I」相同，但本题规模更大，平方 DP 无法使用。最优仍是每次跳到后方下一个不小于当前值的位置。
>
> 单调递减栈扫出这条下标链，再从 $0$ 沿栈中下标累加 $\textit{nums}[j]\times(j-i)$。线性时间，无需记忆化。

<!-- thinking:end -->

我们观察发现，对于当前位置 $i$，我们应该跳到下一个值最大的位置 $j$，这样才能获得最大的分数。

因此，我们遍历数组 $\textit{nums}$，维护一个从栈底到栈顶单调递减的栈 $\textit{stk}$。对于当前遍历到的位置 $i$，如果栈顶元素对应的值小于等于 $\textit{nums}[i]$，我们就不断地弹出栈顶元素，直到栈为空或者栈顶元素对应的值大于 $\textit{nums}[i]$，然后将 $i$ 入栈。

然后，我们初始化答案 $\textit{ans}$ 和当前位置 $i = 0$，遍历栈中的元素，每次取出栈顶元素 $j$，更新答案 $\textit{ans} += \textit{nums}[j] \times (j - i)$，然后更新 $i = j$。

最后返回答案 $\textit{ans}$。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 是数组的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxScore(self, nums: List[int]) -> int:
        stk = []
        for i, x in enumerate(nums):
            while stk and nums[stk[-1]] <= x:
                stk.pop()
            stk.append(i)
        ans = i = 0
        for j in stk:
            ans += nums[j] * (j - i)
            i = j
        return ans
```

#### Java

```java
class Solution {
    public long maxScore(int[] nums) {
        Deque<Integer> stk = new ArrayDeque<>();
        for (int i = 0; i < nums.length; ++i) {
            while (!stk.isEmpty() && nums[stk.peek()] <= nums[i]) {
                stk.pop();
            }
            stk.push(i);
        }
        long ans = 0, i = 0;
        while (!stk.isEmpty()) {
            int j = stk.pollLast();
            ans += (j - i) * nums[j];
            i = j;
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    long long maxScore(vector<int>& nums) {
        vector<int> stk;
        for (int i = 0; i < nums.size(); ++i) {
            while (stk.size() && nums[stk.back()] <= nums[i]) {
                stk.pop_back();
            }
            stk.push_back(i);
        }
        long long ans = 0, i = 0;
        for (int j : stk) {
            ans += (j - i) * nums[j];
            i = j;
        }
        return ans;
    }
};
```

#### Go

```go
func maxScore(nums []int) (ans int64) {
	stk := []int{}
	for i, x := range nums {
		for len(stk) > 0 && nums[stk[len(stk)-1]] <= x {
			stk = stk[:len(stk)-1]
		}
		stk = append(stk, i)
	}
	i := 0
	for _, j := range stk {
		ans += int64((j - i) * nums[j])
		i = j
	}
	return
}
```

#### TypeScript

```ts
function maxScore(nums: number[]): number {
    const stk: number[] = [];
    for (let i = 0; i < nums.length; ++i) {
        while (stk.length && nums[stk.at(-1)!] <= nums[i]) {
            stk.pop();
        }
        stk.push(i);
    }
    let ans = 0;
    let i = 0;
    for (const j of stk) {
        ans += (j - i) * nums[j];
        i = j;
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
