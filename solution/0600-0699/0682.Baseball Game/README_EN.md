---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0600-0699/0682.Baseball%20Game/README_EN.md
tags:
    - Stack
    - Array
    - Simulation
---

<!-- problem:start -->

# [682. Baseball Game](https://leetcode.com/problems/baseball-game)

[中文文档](/solution/0600-0699/0682.Baseball%20Game/README.md)

## Description

<!-- description:start -->

<p>You are keeping the scores for a baseball game with strange rules. At the beginning of the game, you start with an empty record.</p>

<p>You are given a list of strings <code>operations</code>, where <code>operations[i]</code> is the <code>i<sup>th</sup></code> operation you must apply to the record and is one of the following:</p>

<ul>
	<li>An integer <code>x</code>.

    <ul>
    	<li>Record a new score of <code>x</code>.</li>
    </ul>
    </li>
    <li><code>&#39;+&#39;</code>.
    <ul>
    	<li>Record a new score that is the sum of the previous two scores.</li>
    </ul>
    </li>
    <li><code>&#39;D&#39;</code>.
    <ul>
    	<li>Record a new score that is the double of the previous score.</li>
    </ul>
    </li>
    <li><code>&#39;C&#39;</code>.
    <ul>
    	<li>Invalidate the previous score, removing it from the record.</li>
    </ul>
    </li>

</ul>

<p>Return <em>the sum of all the scores on the record after applying all the operations</em>.</p>

<p>The test cases are generated such that the answer and all intermediate calculations fit in a <strong>32-bit</strong> integer and that all operations are valid.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> ops = [&quot;5&quot;,&quot;2&quot;,&quot;C&quot;,&quot;D&quot;,&quot;+&quot;]
<strong>Output:</strong> 30
<strong>Explanation:</strong>
&quot;5&quot; - Add 5 to the record, record is now [5].
&quot;2&quot; - Add 2 to the record, record is now [5, 2].
&quot;C&quot; - Invalidate and remove the previous score, record is now [5].
&quot;D&quot; - Add 2 * 5 = 10 to the record, record is now [5, 10].
&quot;+&quot; - Add 5 + 10 = 15 to the record, record is now [5, 10, 15].
The total sum is 5 + 10 + 15 = 30.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> ops = [&quot;5&quot;,&quot;-2&quot;,&quot;4&quot;,&quot;C&quot;,&quot;D&quot;,&quot;9&quot;,&quot;+&quot;,&quot;+&quot;]
<strong>Output:</strong> 27
<strong>Explanation:</strong>
&quot;5&quot; - Add 5 to the record, record is now [5].
&quot;-2&quot; - Add -2 to the record, record is now [5, -2].
&quot;4&quot; - Add 4 to the record, record is now [5, -2, 4].
&quot;C&quot; - Invalidate and remove the previous score, record is now [5, -2].
&quot;D&quot; - Add 2 * -2 = -4 to the record, record is now [5, -2, -4].
&quot;9&quot; - Add 9 to the record, record is now [5, -2, -4, 9].
&quot;+&quot; - Add -4 + 9 = 5 to the record, record is now [5, -2, -4, 9, 5].
&quot;+&quot; - Add 9 + 5 = 14 to the record, record is now [5, -2, -4, 9, 5, 14].
The total sum is 5 + -2 + -4 + 9 + 5 + 14 = 27.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> ops = [&quot;1&quot;,&quot;C&quot;]
<strong>Output:</strong> 0
<strong>Explanation:</strong>
&quot;1&quot; - Add 1 to the record, record is now [1].
&quot;C&quot; - Invalidate and remove the previous score, record is now [].
Since the record is empty, the total sum is 0.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= operations.length &lt;= 1000</code></li>
	<li><code>operations[i]</code> is <code>&quot;C&quot;</code>, <code>&quot;D&quot;</code>, <code>&quot;+&quot;</code>, or a string representing an integer in the range <code>[-3 * 10<sup>4</sup>, 3 * 10<sup>4</sup>]</code>.</li>
	<li>For operation <code>&quot;+&quot;</code>, there will always be at least two previous scores on the record.</li>
	<li>For operations <code>&quot;C&quot;</code> and <code>&quot;D&quot;</code>, there will always be at least one previous score on the record.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Stack + Simulation

We can use a stack to simulate this process.

Traverse $\textit{operations}$, for each operation:

-   If it is `+`, add the top two elements of the stack and push the result onto the stack;
-   If it is `D`, multiply the top element of the stack by 2 and push the result onto the stack;
-   If it is `C`, pop the top element of the stack;
-   If it is a number, push the number onto the stack.

Finally, sum all the elements in the stack to get the answer.

The time complexity is $O(n)$, and the space complexity is $O(n)$. Here, $n$ is the length of $\textit{operations}$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def calPoints(self, operations: List[str]) -> int:
        stk = []
        for op in operations:
            if op == "+":
                stk.append(stk[-1] + stk[-2])
            elif op == "D":
                stk.append(stk[-1] << 1)
            elif op == "C":
                stk.pop()
            else:
                stk.append(int(op))
        return sum(stk)
```

#### Java

```java
class Solution {
    public int calPoints(String[] operations) {
        Deque<Integer> stk = new ArrayDeque<>();
        for (String op : operations) {
            if ("+".equals(op)) {
                int a = stk.pop();
                int b = stk.peek();
                stk.push(a);
                stk.push(a + b);
            } else if ("D".equals(op)) {
                stk.push(stk.peek() << 1);
            } else if ("C".equals(op)) {
                stk.pop();
            } else {
                stk.push(Integer.valueOf(op));
            }
        }
        return stk.stream().mapToInt(Integer::intValue).sum();
    }
}
```

#### C++

```cpp
class Solution {
public:
    int calPoints(vector<string>& operations) {
        vector<int> stk;
        for (auto& op : operations) {
            int n = stk.size();
            if (op == "+") {
                stk.push_back(stk[n - 1] + stk[n - 2]);
            } else if (op == "D") {
                stk.push_back(stk[n - 1] << 1);
            } else if (op == "C") {
                stk.pop_back();
            } else {
                stk.push_back(stoi(op));
            }
        }
        return accumulate(stk.begin(), stk.end(), 0);
    }
};
```

#### Go

```go
func calPoints(operations []string) (ans int) {
	var stk []int
	for _, op := range operations {
		n := len(stk)
		switch op {
		case "+":
			stk = append(stk, stk[n-1]+stk[n-2])
		case "D":
			stk = append(stk, stk[n-1]*2)
		case "C":
			stk = stk[:n-1]
		default:
			num, _ := strconv.Atoi(op)
			stk = append(stk, num)
		}
	}
	for _, x := range stk {
		ans += x
	}
	return
}
```

#### TypeScript

```ts
function calPoints(operations: string[]): number {
    const stk: number[] = [];
    for (const op of operations) {
        if (op === '+') {
            stk.push(stk.at(-1)! + stk.at(-2)!);
        } else if (op === 'D') {
            stk.push(stk.at(-1)! << 1);
        } else if (op === 'C') {
            stk.pop();
        } else {
            stk.push(+op);
        }
    }
    return stk.reduce((a, b) => a + b, 0);
}
```

#### Rust

```rust
impl Solution {
    pub fn cal_points(operations: Vec<String>) -> i32 {
        let mut stk = vec![];
        for op in operations {
            match op.as_str() {
                "+" => {
                    let n = stk.len();
                    stk.push(stk[n - 1] + stk[n - 2]);
                }
                "D" => {
                    stk.push(stk.last().unwrap() * 2);
                }
                "C" => {
                    stk.pop();
                }
                n => {
                    stk.push(n.parse::<i32>().unwrap());
                }
            }
        }
        stk.into_iter().sum()
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
