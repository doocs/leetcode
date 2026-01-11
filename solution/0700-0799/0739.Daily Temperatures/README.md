---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0700-0799/0739.Daily%20Temperatures/README.md
tags:
    - 栈
    - 数组
    - 单调栈
---

<!-- problem:start -->

# [739. 每日温度](https://leetcode.cn/problems/daily-temperatures)

[English Version](/solution/0700-0799/0739.Daily%20Temperatures/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定一个整数数组&nbsp;<code>temperatures</code>&nbsp;，表示每天的温度，返回一个数组&nbsp;<code>answer</code>&nbsp;，其中&nbsp;<code>answer[i]</code>&nbsp;是指对于第 <code>i</code> 天，下一个更高温度出现在几天后。如果气温在这之后都不会升高，请在该位置用&nbsp;<code>0</code> 来代替。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入:</strong> temperatures = [73,74,75,71,69,72,76,73]
<strong>输出:</strong>&nbsp;[1,1,4,2,1,1,0,0]
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入:</strong> temperatures = [30,40,50,60]
<strong>输出:</strong>&nbsp;[1,1,1,0]
</pre>

<p><strong>示例 3:</strong></p>

<pre>
<strong>输入:</strong> temperatures = [30,60,90]
<strong>输出: </strong>[1,1,0]</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;=&nbsp;temperatures.length &lt;= 10<sup>5</sup></code></li>
	<li><code>30 &lt;=&nbsp;temperatures[i]&nbsp;&lt;= 100</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：单调栈

本题需要我们找出每个元素右边第一个比它大的元素的位置，这是一个典型的单调栈应用场景。

我们从右往左遍历数组 $\textit{temperatures}$，维护一个从栈顶到栈底温度单调递增的栈 $\textit{stk}$，栈中存储的是数组元素的下标。对于每个元素 $\textit{temperatures}[i]$，我们不断将其与栈顶元素进行比较，如果栈顶元素对应的温度小于等于 $\textit{temperatures}[i]$，那么循环将栈顶元素弹出，直到栈为空或者栈顶元素对应的温度大于 $\textit{temperatures}[i]$。此时，栈顶元素就是右边第一个比 $\textit{temperatures}[i]$ 大的元素，距离为 $\textit{stk.top()} - i$，我们更新答案数组。然后将 $\textit{temperatures}[i]$ 入栈，继续遍历。

遍历结束后，返回答案数组即可。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为数组 $\textit{temperatures}$ 的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def dailyTemperatures(self, temperatures: List[int]) -> List[int]:
        stk = []
        n = len(temperatures)
        ans = [0] * n
        for i in range(n - 1, -1, -1):
            while stk and temperatures[stk[-1]] <= temperatures[i]:
                stk.pop()
            if stk:
                ans[i] = stk[-1] - i
            stk.append(i)
        return ans
```

#### Java

```java
class Solution {
    public int[] dailyTemperatures(int[] temperatures) {
        int n = temperatures.length;
        Deque<Integer> stk = new ArrayDeque<>();
        int[] ans = new int[n];
        for (int i = n - 1; i >= 0; --i) {
            while (!stk.isEmpty() && temperatures[stk.peek()] <= temperatures[i]) {
                stk.pop();
            }
            if (!stk.isEmpty()) {
                ans[i] = stk.peek() - i;
            }
            stk.push(i);
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<int> dailyTemperatures(vector<int>& temperatures) {
        int n = temperatures.size();
        stack<int> stk;
        vector<int> ans(n);
        for (int i = n - 1; ~i; --i) {
            while (!stk.empty() && temperatures[stk.top()] <= temperatures[i]) {
                stk.pop();
            }
            if (!stk.empty()) {
                ans[i] = stk.top() - i;
            }
            stk.push(i);
        }
        return ans;
    }
};
```

#### Go

```go
func dailyTemperatures(temperatures []int) []int {
	n := len(temperatures)
	ans := make([]int, n)
	stk := []int{}
	for i := n - 1; i >= 0; i-- {
		for len(stk) > 0 && temperatures[stk[len(stk)-1]] <= temperatures[i] {
			stk = stk[:len(stk)-1]
		}
		if len(stk) > 0 {
			ans[i] = stk[len(stk)-1] - i
		}
		stk = append(stk, i)
	}
	return ans
}
```

#### TypeScript

```ts
function dailyTemperatures(temperatures: number[]): number[] {
    const n = temperatures.length;
    const ans: number[] = Array(n).fill(0);
    const stk: number[] = [];
    for (let i = n - 1; ~i; --i) {
        while (stk.length && temperatures[stk.at(-1)!] <= temperatures[i]) {
            stk.pop();
        }
        if (stk.length) {
            ans[i] = stk.at(-1)! - i;
        }
        stk.push(i);
    }
    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn daily_temperatures(temperatures: Vec<i32>) -> Vec<i32> {
        let n = temperatures.len();
        let mut stack = vec![];
        let mut res = vec![0; n];
        for i in 0..n {
            while !stack.is_empty() && temperatures[*stack.last().unwrap()] < temperatures[i] {
                let j = stack.pop().unwrap();
                res[j] = (i - j) as i32;
            }
            stack.push(i);
        }
        res
    }
}
```

#### Rust

```rust
impl Solution {
    pub fn daily_temperatures(temperatures: Vec<i32>) -> Vec<i32> {
        let n = temperatures.len();
        let mut stk: Vec<usize> = Vec::new();
        let mut ans = vec![0; n];

        for i in (0..n).rev() {
            while let Some(&top) = stk.last() {
                if temperatures[top] <= temperatures[i] {
                    stk.pop();
                } else {
                    break;
                }
            }
            if let Some(&top) = stk.last() {
                ans[i] = (top - i) as i32;
            }
            stk.push(i);
        }

        ans
    }
}
```

#### JavaScript

```js
/**
 * @param {number[]} temperatures
 * @return {number[]}
 */
var dailyTemperatures = function (temperatures) {
    const n = temperatures.length;
    const ans = Array(n).fill(0);
    const stk = [];
    for (let i = n - 1; ~i; --i) {
        while (stk.length && temperatures[stk.at(-1)] <= temperatures[i]) {
            stk.pop();
        }
        if (stk.length) {
            ans[i] = stk.at(-1) - i;
        }
        stk.push(i);
    }
    return ans;
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
