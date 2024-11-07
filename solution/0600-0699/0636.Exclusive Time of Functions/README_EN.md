---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0600-0699/0636.Exclusive%20Time%20of%20Functions/README_EN.md
tags:
    - Stack
    - Array
---

<!-- problem:start -->

# [636. Exclusive Time of Functions](https://leetcode.com/problems/exclusive-time-of-functions)

[中文文档](/solution/0600-0699/0636.Exclusive%20Time%20of%20Functions/README.md)

## Description

<!-- description:start -->

<p>On a <strong>single-threaded</strong> CPU, we execute a program containing <code>n</code> functions. Each function has a unique ID between <code>0</code> and <code>n-1</code>.</p>

<p>Function calls are <strong>stored in a <a href="https://en.wikipedia.org/wiki/Call_stack">call stack</a></strong>: when a function call starts, its ID is pushed onto the stack, and when a function call ends, its ID is popped off the stack. The function whose ID is at the top of the stack is <strong>the current function being executed</strong>. Each time a function starts or ends, we write a log with the ID, whether it started or ended, and the timestamp.</p>

<p>You are given a list <code>logs</code>, where <code>logs[i]</code> represents the <code>i<sup>th</sup></code> log message formatted as a string <code>&quot;{function_id}:{&quot;start&quot; | &quot;end&quot;}:{timestamp}&quot;</code>. For example, <code>&quot;0:start:3&quot;</code> means a function call with function ID <code>0</code> <strong>started at the beginning</strong> of timestamp <code>3</code>, and <code>&quot;1:end:2&quot;</code> means a function call with function ID <code>1</code> <strong>ended at the end</strong> of timestamp <code>2</code>. Note that a function can be called <b>multiple times, possibly recursively</b>.</p>

<p>A function&#39;s <strong>exclusive time</strong> is the sum of execution times for all function calls in the program. For example, if a function is called twice, one call executing for <code>2</code> time units and another call executing for <code>1</code> time unit, the <strong>exclusive time</strong> is <code>2 + 1 = 3</code>.</p>

<p>Return <em>the <strong>exclusive time</strong> of each function in an array, where the value at the </em><code>i<sup>th</sup></code><em> index represents the exclusive time for the function with ID </em><code>i</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0600-0699/0636.Exclusive%20Time%20of%20Functions/images/diag1b.png" style="width: 550px; height: 239px;" />
<pre>
<strong>Input:</strong> n = 2, logs = [&quot;0:start:0&quot;,&quot;1:start:2&quot;,&quot;1:end:5&quot;,&quot;0:end:6&quot;]
<strong>Output:</strong> [3,4]
<strong>Explanation:</strong>
Function 0 starts at the beginning of time 0, then it executes 2 for units of time and reaches the end of time 1.
Function 1 starts at the beginning of time 2, executes for 4 units of time, and ends at the end of time 5.
Function 0 resumes execution at the beginning of time 6 and executes for 1 unit of time.
So function 0 spends 2 + 1 = 3 units of total time executing, and function 1 spends 4 units of total time executing.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 1, logs = [&quot;0:start:0&quot;,&quot;0:start:2&quot;,&quot;0:end:5&quot;,&quot;0:start:6&quot;,&quot;0:end:6&quot;,&quot;0:end:7&quot;]
<strong>Output:</strong> [8]
<strong>Explanation:</strong>
Function 0 starts at the beginning of time 0, executes for 2 units of time, and recursively calls itself.
Function 0 (recursive call) starts at the beginning of time 2 and executes for 4 units of time.
Function 0 (initial call) resumes execution then immediately calls itself again.
Function 0 (2nd recursive call) starts at the beginning of time 6 and executes for 1 unit of time.
Function 0 (initial call) resumes execution at the beginning of time 7 and executes for 1 unit of time.
So function 0 spends 2 + 4 + 1 + 1 = 8 units of total time executing.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> n = 2, logs = [&quot;0:start:0&quot;,&quot;0:start:2&quot;,&quot;0:end:5&quot;,&quot;1:start:6&quot;,&quot;1:end:6&quot;,&quot;0:end:7&quot;]
<strong>Output:</strong> [7,1]
<strong>Explanation:</strong>
Function 0 starts at the beginning of time 0, executes for 2 units of time, and recursively calls itself.
Function 0 (recursive call) starts at the beginning of time 2 and executes for 4 units of time.
Function 0 (initial call) resumes execution then immediately calls function 1.
Function 1 starts at the beginning of time 6, executes 1 unit of time, and ends at the end of time 6.
Function 0 resumes execution at the beginning of time 6 and executes for 2 units of time.
So function 0 spends 2 + 4 + 1 = 7 units of total time executing, and function 1 spends 1 unit of total time executing.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 100</code></li>
	<li><code>1 &lt;= logs.length &lt;= 500</code></li>
	<li><code>0 &lt;= function_id &lt; n</code></li>
	<li><code>0 &lt;= timestamp &lt;= 10<sup>9</sup></code></li>
	<li>No two start events will happen at the same timestamp.</li>
	<li>No two end events will happen at the same timestamp.</li>
	<li>Each function has an <code>&quot;end&quot;</code> log for each <code>&quot;start&quot;</code> log.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Stack + Simulation

We define a stack $\textit{stk}$ to store the identifiers of the currently executing functions. We also define an array $\textit{ans}$ to store the exclusive time of each function, initially setting the exclusive time of each function to $0$. We use a variable $\textit{pre}$ to record the previous timestamp.

We traverse the log array. For each log entry, we first split it by colons to get the function identifier $\textit{i}$, the operation type $\textit{op}$, and the timestamp $\textit{t}$.

If $\textit{op}$ is $\text{start}$, it means function $\textit{i}$ starts executing. We need to check if the stack is empty. If it is not empty, we add $\textit{cur} - \textit{pre}$ to the exclusive time of the function at the top of the stack, then push $\textit{i}$ onto the stack and update $\textit{pre}$ to $\textit{cur}$. If $\textit{op}$ is $\text{end}$, it means function $\textit{i}$ finishes executing. We add $\textit{cur} - \textit{pre} + 1$ to the exclusive time of the function at the top of the stack, then pop the top element from the stack and update $\textit{pre}$ to $\textit{cur} + 1$.

Finally, we return the array $\textit{ans}$.

The time complexity is $O(n)$, and the space complexity is $O(n)$. Here, $n$ is the length of the log array.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def exclusiveTime(self, n: int, logs: List[str]) -> List[int]:
        stk = []
        ans = [0] * n
        pre = 0
        for log in logs:
            i, op, t = log.split(":")
            i, cur = int(i), int(t)
            if op[0] == "s":
                if stk:
                    ans[stk[-1]] += cur - pre
                stk.append(i)
                pre = cur
            else:
                ans[stk.pop()] += cur - pre + 1
                pre = cur + 1
        return ans
```

#### Java

```java
class Solution {
    public int[] exclusiveTime(int n, List<String> logs) {
        int[] ans = new int[n];
        Deque<Integer> stk = new ArrayDeque<>();
        int pre = 0;
        for (var log : logs) {
            var parts = log.split(":");
            int i = Integer.parseInt(parts[0]);
            int cur = Integer.parseInt(parts[2]);
            if (parts[1].charAt(0) == 's') {
                if (!stk.isEmpty()) {
                    ans[stk.peek()] += cur - pre;
                }
                stk.push(i);
                pre = cur;
            } else {
                ans[stk.pop()] += cur - pre + 1;
                pre = cur + 1;
            }
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<int> exclusiveTime(int n, vector<string>& logs) {
        vector<int> ans(n);
        stack<int> stk;
        int pre = 0;
        for (const auto& log : logs) {
            int i, cur;
            char c[10];
            sscanf(log.c_str(), "%d:%[^:]:%d", &i, c, &cur);
            if (c[0] == 's') {
                if (stk.size()) {
                    ans[stk.top()] += cur - pre;
                }
                stk.push(i);
                pre = cur;
            } else {
                ans[stk.top()] += cur - pre + 1;
                stk.pop();
                pre = cur + 1;
            }
        }
        return ans;
    }
};
```

#### Go

```go
func exclusiveTime(n int, logs []string) []int {
	ans := make([]int, n)
	stk := []int{}
	pre := 0
	for _, log := range logs {
		parts := strings.Split(log, ":")
		i, _ := strconv.Atoi(parts[0])
		cur, _ := strconv.Atoi(parts[2])
		if parts[1][0] == 's' {
			if len(stk) > 0 {
				ans[stk[len(stk)-1]] += cur - pre
			}
			stk = append(stk, i)
			pre = cur
		} else {
			ans[stk[len(stk)-1]] += cur - pre + 1
			stk = stk[:len(stk)-1]
			pre = cur + 1
		}
	}
	return ans
}
```

#### TypeScript

```ts
function exclusiveTime(n: number, logs: string[]): number[] {
    const ans: number[] = Array(n).fill(0);
    let pre = 0;
    const stk: number[] = [];
    for (const log of logs) {
        const [i, op, cur] = log.split(':');
        if (op[0] === 's') {
            if (stk.length) {
                ans[stk.at(-1)!] += +cur - pre;
            }
            stk.push(+i);
            pre = +cur;
        } else {
            ans[stk.pop()!] += +cur - pre + 1;
            pre = +cur + 1;
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
