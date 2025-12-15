---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3500-3599/3542.Minimum%20Operations%20to%20Convert%20All%20Elements%20to%20Zero/README.md
rating: 1889
source: 第 156 场双周赛 Q2
tags:
    - 栈
    - 贪心
    - 数组
    - 哈希表
    - 单调栈
---

<!-- problem:start -->

# [3542. 将所有元素变为 0 的最少操作次数](https://leetcode.cn/problems/minimum-operations-to-convert-all-elements-to-zero)

[English Version](/solution/3500-3599/3542.Minimum%20Operations%20to%20Convert%20All%20Elements%20to%20Zero/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个大小为 <code>n</code> 的 <strong>非负</strong>&nbsp;整数数组 <code>nums</code>&nbsp;。你的任务是对该数组执行若干次（可能为 0 次）操作，使得&nbsp;<strong>所有&nbsp;</strong>元素都变为 0。</p>

<p>在一次操作中，你可以选择一个子数组 <code>[i, j]</code>（其中 <code>0 &lt;= i &lt;= j &lt; n</code>），将该子数组中所有&nbsp;<strong>最小的非负整数&nbsp;</strong>的设为 0。</p>

<p>返回使整个数组变为 0 所需的<strong>最少</strong>操作次数。</p>
一个&nbsp;<strong>子数组&nbsp;</strong>是数组中的一段连续元素。

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入:</strong> <span class="example-io">nums = [0,2]</span></p>

<p><strong>输出:</strong> <span class="example-io">1</span></p>

<p><strong>解释:</strong></p>

<ul>
	<li>选择子数组 <code>[1,1]</code>（即 <code>[2]</code>），其中最小的非负整数是 2。将所有 2 设为 0，结果为 <code>[0,0]</code>。</li>
	<li>因此，所需的最少操作次数为 1。</li>
</ul>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入:</strong> <span class="example-io">nums = [3,1,2,1]</span></p>

<p><strong>输出:</strong> <span class="example-io">3</span></p>

<p><strong>解释:</strong></p>

<ul>
	<li>选择子数组 <code>[1,3]</code>（即 <code>[1,2,1]</code>），最小非负整数是 1。将所有 1 设为 0，结果为 <code>[3,0,2,0]</code>。</li>
	<li>选择子数组 <code>[2,2]</code>（即 <code>[2]</code>），将 2 设为 0，结果为 <code>[3,0,0,0]</code>。</li>
	<li>选择子数组 <code>[0,0]</code>（即 <code>[3]</code>），将 3 设为 0，结果为 <code>[0,0,0,0]</code>。</li>
	<li>因此，最少操作次数为 3。</li>
</ul>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><strong>输入:</strong> <span class="example-io">nums = [1,2,1,2,1,2]</span></p>

<p><strong>输出:</strong> <span class="example-io">4</span></p>

<p><strong>解释:</strong></p>

<ul>
	<li>选择子数组 <code>[0,5]</code>（即 <code>[1,2,1,2,1,2]</code>），最小非负整数是 1。将所有 1 设为 0，结果为 <code>[0,2,0,2,0,2]</code>。</li>
	<li>选择子数组 <code>[1,1]</code>（即 <code>[2]</code>），将 2 设为 0，结果为 <code>[0,0,0,2,0,2]</code>。</li>
	<li>选择子数组 <code>[3,3]</code>（即 <code>[2]</code>），将 2 设为 0，结果为 <code>[0,0,0,0,0,2]</code>。</li>
	<li>选择子数组 <code>[5,5]</code>（即 <code>[2]</code>），将 2 设为 0，结果为 <code>[0,0,0,0,0,0]</code>。</li>
	<li>因此，最少操作次数为 4。</li>
</ul>
</div>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>1 &lt;= n == nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：单调栈

根据题意，我们应该把数字中最小的数先变成 $0$，再把次小的数变成 $0$，依此类推。在这里过程中，如果两个数之间有更小的数隔开，那么它们需要额外的一次操作才能变成 $0$。

我们可以维护一个从栈底到栈顶单调递增的栈 $\textit{stk}$，遍历数组 $\textit{nums}$ 中的每个数 $\textit{x}$：

- 当栈顶元素大于 $\textit{x}$ 时，说明 $\textit{x}$ 将栈顶元素隔开了，我们需要把栈顶元素弹出，并将答案加 $1$，直到栈顶元素不大于 $\textit{x}$ 为止。
- 如果 $\textit{x}$ 不为 $0$，且栈为空或者栈顶元素不等于 $\textit{x}$，则将 $\textit{x}$ 入栈。

遍历结束后，栈中剩余的元素都需要额外的一次操作才能变成 $0$，因此我们将答案加上栈的大小即可。

时间复杂度 $O(n)$，空间复杂度 $O(n)$，其中 $n$ 是数组 $\textit{nums}$ 的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minOperations(self, nums: List[int]) -> int:
        stk = []
        ans = 0
        for x in nums:
            while stk and stk[-1] > x:
                ans += 1
                stk.pop()
            if x and (not stk or stk[-1] != x):
                stk.append(x)
        ans += len(stk)
        return ans
```

#### Java

```java
class Solution {
    public int minOperations(int[] nums) {
        Deque<Integer> stk = new ArrayDeque<>();
        int ans = 0;
        for (int x : nums) {
            while (!stk.isEmpty() && stk.peek() > x) {
                ans++;
                stk.pop();
            }
            if (x != 0 && (stk.isEmpty() || stk.peek() != x)) {
                stk.push(x);
            }
        }
        ans += stk.size();
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int minOperations(vector<int>& nums) {
        vector<int> stk;
        int ans = 0;
        for (int x : nums) {
            while (!stk.empty() && stk.back() > x) {
                ++ans;
                stk.pop_back();
            }
            if (x != 0 && (stk.empty() || stk.back() != x)) {
                stk.push_back(x);
            }
        }
        ans += stk.size();
        return ans;
    }
};
```

#### Go

```go
func minOperations(nums []int) int {
	stk := []int{}
	ans := 0
	for _, x := range nums {
		for len(stk) > 0 && stk[len(stk)-1] > x {
			ans++
			stk = stk[:len(stk)-1]
		}
		if x != 0 && (len(stk) == 0 || stk[len(stk)-1] != x) {
			stk = append(stk, x)
		}
	}
	ans += len(stk)
	return ans
}
```

#### TypeScript

```ts
function minOperations(nums: number[]): number {
    const stk: number[] = [];
    let ans = 0;
    for (const x of nums) {
        while (stk.length > 0 && stk[stk.length - 1] > x) {
            ans++;
            stk.pop();
        }
        if (x !== 0 && (stk.length === 0 || stk[stk.length - 1] !== x)) {
            stk.push(x);
        }
    }
    ans += stk.length;
    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn min_operations(nums: Vec<i32>) -> i32 {
        let mut stk = Vec::new();
        let mut ans = 0;
        for &x in nums.iter() {
            while let Some(&last) = stk.last() {
                if last > x {
                    ans += 1;
                    stk.pop();
                } else {
                    break;
                }
            }
            if x != 0 && (stk.is_empty() || *stk.last().unwrap() != x) {
                stk.push(x);
            }
        }
        ans += stk.len() as i32;
        ans
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
