---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3500-3599/3522.Calculate%20Score%20After%20Performing%20Instructions/README_EN.md
---

<!-- problem:start -->

# [3522. Calculate Score After Performing Instructions](https://leetcode.com/problems/calculate-score-after-performing-instructions)

[中文文档](/solution/3500-3599/3522.Calculate%20Score%20After%20Performing%20Instructions/README.md)

## Description

<!-- description:start -->

<p>You are given two arrays, <code>instructions</code> and <code>values</code>, both of size <code>n</code>.</p>

<p>You need to simulate a process based on the following rules:</p>

<ul>
	<li>You start at the first instruction at index <code>i = 0</code> with an initial score of 0.</li>
	<li>If <code>instructions[i]</code> is <code>&quot;add&quot;</code>:
	<ul>
		<li>Add <code>values[i]</code> to your score.</li>
		<li>Move to the next instruction <code>(i + 1)</code>.</li>
	</ul>
	</li>
	<li>If <code>instructions[i]</code> is <code>&quot;jump&quot;</code>:
	<ul>
		<li>Move to the instruction at index <code>(i + values[i])</code> without modifying your score.</li>
	</ul>
	</li>
</ul>

<p>The process ends when you either:</p>

<ul>
	<li>Go out of bounds (i.e., <code>i &lt; 0 or i &gt;= n</code>), or</li>
	<li>Attempt to revisit an instruction that has been previously executed. The revisited instruction is not executed.</li>
</ul>

<p>Return your score at the end of the process.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">instructions = [&quot;jump&quot;,&quot;add&quot;,&quot;add&quot;,&quot;jump&quot;,&quot;add&quot;,&quot;jump&quot;], values = [2,1,3,1,-2,-3]</span></p>

<p><strong>Output:</strong> <span class="example-io">1</span></p>

<p><strong>Explanation:</strong></p>

<p>Simulate the process starting at instruction 0:</p>

<ul>
	<li>At index 0: Instruction is <code>&quot;jump&quot;</code>, move to index <code>0 + 2 = 2</code>.</li>
	<li>At index 2: Instruction is <code>&quot;add&quot;</code>, add <code>values[2] = 3</code> to your score and move to index 3. Your score becomes 3.</li>
	<li>At index 3: Instruction is <code>&quot;jump&quot;</code>, move to index <code>3 + 1 = 4</code>.</li>
	<li>At index 4: Instruction is <code>&quot;add&quot;</code>, add <code>values[4] = -2</code> to your score and move to index 5. Your score becomes 1.</li>
	<li>At index 5: Instruction is <code>&quot;jump&quot;</code>, move to index <code>5 + (-3) = 2</code>.</li>
	<li>At index 2: Already visited. The process ends.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">instructions = [&quot;jump&quot;,&quot;add&quot;,&quot;add&quot;], values = [3,1,1]</span></p>

<p><strong>Output:</strong> <span class="example-io">0</span></p>

<p><strong>Explanation:</strong></p>

<p>Simulate the process starting at instruction 0:</p>

<ul>
	<li>At index 0: Instruction is <code>&quot;jump&quot;</code>, move to index <code>0 + 3 = 3</code>.</li>
	<li>At index 3: Out of bounds. The process ends.</li>
</ul>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">instructions = [&quot;jump&quot;], values = [0]</span></p>

<p><strong>Output:</strong> <span class="example-io">0</span></p>

<p><strong>Explanation:</strong></p>

<p>Simulate the process starting at instruction 0:</p>

<ul>
	<li>At index 0: Instruction is <code>&quot;jump&quot;</code>, move to index <code>0 + 0 = 0</code>.</li>
	<li>At index 0: Already visited. The process ends.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == instructions.length == values.length</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>instructions[i]</code> is either <code>&quot;add&quot;</code> or <code>&quot;jump&quot;</code>.</li>
	<li><code>-10<sup>5</sup> &lt;= values[i] &lt;= 10<sup>5</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Simulation

We can simulate the process based on the problem description.

Define a boolean array $\textit{vis}$ of length $n$ to record whether each instruction has been executed. Initially, all elements are set to $\text{false}$.

Then, starting from index $i = 0$, perform the following steps in a loop:

1. Set $\textit{vis}[i]$ to $\text{true}$.
2. If the first character of $\textit{instructions}[i]$ is 'a', add $\textit{value}[i]$ to the answer and increment $i$ by $1$. Otherwise, increment $i$ by $\textit{value}[i]$.

The loop continues until $i \lt 0$, $i \ge n$, or $\textit{vis}[i]$ is $\text{true}$.

Finally, return the answer.

The time complexity is $O(n)$, and the space complexity is $O(n)$, where $n$ is the length of the array $\textit{value}$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def calculateScore(self, instructions: List[str], values: List[int]) -> int:
        n = len(values)
        vis = [False] * n
        ans = i = 0
        while 0 <= i < n and not vis[i]:
            vis[i] = True
            if instructions[i][0] == "a":
                ans += values[i]
                i += 1
            else:
                i = i + values[i]
        return ans
```

#### Java

```java
class Solution {
    public long calculateScore(String[] instructions, int[] values) {
        int n = values.length;
        boolean[] vis = new boolean[n];
        long ans = 0;
        int i = 0;

        while (i >= 0 && i < n && !vis[i]) {
            vis[i] = true;
            if (instructions[i].charAt(0) == 'a') {
                ans += values[i];
                i += 1;
            } else {
                i = i + values[i];
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
    long long calculateScore(vector<string>& instructions, vector<int>& values) {
        int n = values.size();
        vector<bool> vis(n, false);
        long long ans = 0;
        int i = 0;

        while (i >= 0 && i < n && !vis[i]) {
            vis[i] = true;
            if (instructions[i][0] == 'a') {
                ans += values[i];
                i += 1;
            } else {
                i += values[i];
            }
        }

        return ans;
    }
};
```

#### Go

```go
func calculateScore(instructions []string, values []int) (ans int64) {
	n := len(values)
	vis := make([]bool, n)
	i := 0
	for i >= 0 && i < n && !vis[i] {
		vis[i] = true
		if instructions[i][0] == 'a' {
			ans += int64(values[i])
			i += 1
		} else {
			i += values[i]
		}
	}
	return
}
```

#### TypeScript

```ts
function calculateScore(instructions: string[], values: number[]): number {
    const n = values.length;
    const vis: boolean[] = Array(n).fill(false);
    let ans = 0;
    let i = 0;

    while (i >= 0 && i < n && !vis[i]) {
        vis[i] = true;
        if (instructions[i][0] === 'a') {
            ans += values[i];
            i += 1;
        } else {
            i += values[i];
        }
    }

    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
