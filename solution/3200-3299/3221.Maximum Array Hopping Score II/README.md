---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3200-3299/3221.Maximum%20Array%20Hopping%20Score%20II/README.md
---

<!-- problem:start -->

# [3221. Maximum Array Hopping Score II 🔒](https://leetcode.cn/problems/maximum-array-hopping-score-ii)

[English Version](/solution/3200-3299/3221.Maximum%20Array%20Hopping%20Score%20II/README_EN.md)

## 题目描述

<!-- description:start -->

<p>Given an array <code>nums</code>, you have to get the <strong>maximum</strong> score starting from index 0 and <strong>hopping</strong> until you reach the last element of the array.</p>

<p>In each <strong>hop</strong>, you can jump from index <code>i</code> to an index <code>j &gt; i</code>, and you get a <strong>score</strong> of <code>(j - i) * nums[j]</code>.</p>

<p>Return the <em>maximum score</em> you can get.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,5,8]</span></p>

<p><strong>Output:</strong> <span class="example-io">16</span></p>

<p><strong>Explanation:</strong></p>

<p>There are two possible ways to reach the last element:</p>

<ul>
	<li><code>0 -&gt; 1 -&gt; 2</code> with a score of <code>(1 - 0) * 5 + (2 - 1) * 8 = 13</code>.</li>
	<li><code>0 -&gt; 2</code> with a score of <code>(2 - 0) * 8 = 16</code>.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [4,5,2,8,9,1,3]</span></p>

<p><strong>Output:</strong> <span class="example-io">42</span></p>

<p><strong>Explanation:</strong></p>

<p>We can do the hopping <code>0 -&gt; 4 -&gt; 6</code> with a score of&nbsp;<code>(4 - 0) * 9 + (6 - 4) * 3 = 42</code>.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：单调栈

我们观察发现，对于当前位置 $i$，我们应该跳到下一个值最大的位置 $j$，这样才能获得最大的分数。

因此，我们遍历数组 $\text{nums}$，维护一个从栈底到栈顶单调递减的栈 $\text{stk}$。对于当前遍历到的位置 $i$，如果栈顶元素对应的值小于等于 $\text{nums}[i]$，我们就不断地弹出栈顶元素，直到栈为空或者栈顶元素对应的值大于 $\text{nums}[i]$，然后将 $i$ 入栈。

然后，我们初始化答案 $\text{ans}$ 和当前位置 $i = 0$，遍历栈中的元素，每次取出栈顶元素 $j$，更新答案 $\text{ans} += \text{nums}[j] \times (j - i)$，然后更新 $i = j$。

最后返回答案 $\text{ans}$。

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
