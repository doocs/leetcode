---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0700-0799/0739.Daily%20Temperatures/README_EN.md
tags:
    - Stack
    - Array
    - Monotonic Stack
---

<!-- problem:start -->

# [739. Daily Temperatures](https://leetcode.com/problems/daily-temperatures)

[中文文档](/solution/0700-0799/0739.Daily%20Temperatures/README.md)

## Description

<!-- description:start -->

<p>Given an array of integers <code>temperatures</code> represents the daily temperatures, return <em>an array</em> <code>answer</code> <em>such that</em> <code>answer[i]</code> <em>is the number of days you have to wait after the</em> <code>i<sup>th</sup></code> <em>day to get a warmer temperature</em>. If there is no future day for which this is possible, keep <code>answer[i] == 0</code> instead.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<pre><strong>Input:</strong> temperatures = [73,74,75,71,69,72,76,73]
<strong>Output:</strong> [1,1,4,2,1,1,0,0]
</pre><p><strong class="example">Example 2:</strong></p>
<pre><strong>Input:</strong> temperatures = [30,40,50,60]
<strong>Output:</strong> [1,1,1,0]
</pre><p><strong class="example">Example 3:</strong></p>
<pre><strong>Input:</strong> temperatures = [30,60,90]
<strong>Output:</strong> [1,1,0]
</pre>
<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;=&nbsp;temperatures.length &lt;= 10<sup>5</sup></code></li>
	<li><code>30 &lt;=&nbsp;temperatures[i] &lt;= 100</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Monotonic Stack

This problem requires us to find the position of the first element greater than each element to its right, which is a typical application scenario for a monotonic stack.

We traverse the array $\textit{temperatures}$ from right to left, maintaining a stack $\textit{stk}$ that is monotonically increasing from top to bottom in terms of temperature. The stack stores the indices of the array elements. For each element $\textit{temperatures}[i]$, we continuously compare it with the top element of the stack. If the temperature corresponding to the top element of the stack is less than or equal to $\textit{temperatures}[i]$, we pop the top element of the stack in a loop until the stack is empty or the temperature corresponding to the top element of the stack is greater than $\textit{temperatures}[i]$. At this point, the top element of the stack is the first element greater than $\textit{temperatures}[i]$ to its right, and the distance is $\textit{stk.top()} - i$. We update the answer array accordingly. Then we push $\textit{temperatures}[i]$ onto the stack and continue traversing.

After the traversal, we return the answer array.

The time complexity is $O(n)$, and the space complexity is $O(n)$. Here, $n$ is the length of the array $\textit{temperatures}$.

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
