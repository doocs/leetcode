---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0900-0999/0946.Validate%20Stack%20Sequences/README_EN.md
tags:
    - Stack
    - Array
    - Simulation
---

<!-- problem:start -->

# [946. Validate Stack Sequences](https://leetcode.com/problems/validate-stack-sequences)

[中文文档](/solution/0900-0999/0946.Validate%20Stack%20Sequences/README.md)

## Description

<!-- description:start -->

<p>Given two integer arrays <code>pushed</code> and <code>popped</code> each with distinct values, return <code>true</code><em> if this could have been the result of a sequence of push and pop operations on an initially empty stack, or </em><code>false</code><em> otherwise.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> pushed = [1,2,3,4,5], popped = [4,5,3,2,1]
<strong>Output:</strong> true
<strong>Explanation:</strong> We might do the following sequence:
push(1), push(2), push(3), push(4),
pop() -&gt; 4,
push(5),
pop() -&gt; 5, pop() -&gt; 3, pop() -&gt; 2, pop() -&gt; 1
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> pushed = [1,2,3,4,5], popped = [4,3,5,1,2]
<strong>Output:</strong> false
<strong>Explanation:</strong> 1 cannot be popped before 2.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= pushed.length &lt;= 1000</code></li>
	<li><code>0 &lt;= pushed[i] &lt;= 1000</code></li>
	<li>All the elements of <code>pushed</code> are <strong>unique</strong>.</li>
	<li><code>popped.length == pushed.length</code></li>
	<li><code>popped</code> is a permutation of <code>pushed</code>.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Stack Simulation

We iterate through the $\textit{pushed}$ array. For the current element $x$ being iterated, we push it into the stack $\textit{stk}$. Then, we check if the top element of the stack is equal to the next element to be popped in the $\textit{popped}$ array. If they are equal, we pop the top element from the stack and increment the index $i$ of the next element to be popped in the $\textit{popped}$ array. Finally, if all elements can be popped in the order specified by the $\textit{popped}$ array, return $\textit{true}$; otherwise, return $\textit{false}$.

The time complexity is $O(n)$, and the space complexity is $O(n)$. Here, $n$ is the length of the $\textit{pushed}$ array.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def validateStackSequences(self, pushed: List[int], popped: List[int]) -> bool:
        stk = []
        i = 0
        for x in pushed:
            stk.append(x)
            while stk and stk[-1] == popped[i]:
                stk.pop()
                i += 1
        return i == len(popped)
```

#### Java

```java
class Solution {
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        Deque<Integer> stk = new ArrayDeque<>();
        int i = 0;
        for (int x : pushed) {
            stk.push(x);
            while (!stk.isEmpty() && stk.peek() == popped[i]) {
                stk.pop();
                ++i;
            }
        }
        return i == popped.length;
    }
}
```

#### C++

```cpp
class Solution {
public:
    bool validateStackSequences(vector<int>& pushed, vector<int>& popped) {
        stack<int> stk;
        int i = 0;
        for (int x : pushed) {
            stk.push(x);
            while (stk.size() && stk.top() == popped[i]) {
                stk.pop();
                ++i;
            }
        }
        return i == popped.size();
    }
};
```

#### Go

```go
func validateStackSequences(pushed []int, popped []int) bool {
	stk := []int{}
	i := 0
	for _, x := range pushed {
		stk = append(stk, x)
		for len(stk) > 0 && stk[len(stk)-1] == popped[i] {
			stk = stk[:len(stk)-1]
			i++
		}
	}
	return i == len(popped)
}
```

#### TypeScript

```ts
function validateStackSequences(pushed: number[], popped: number[]): boolean {
    const stk: number[] = [];
    let i = 0;
    for (const x of pushed) {
        stk.push(x);
        while (stk.length && stk.at(-1)! === popped[i]) {
            stk.pop();
            i++;
        }
    }
    return i === popped.length;
}
```

#### Rust

```rust
impl Solution {
    pub fn validate_stack_sequences(pushed: Vec<i32>, popped: Vec<i32>) -> bool {
        let mut stk: Vec<i32> = Vec::new();
        let mut i = 0;
        for &x in &pushed {
            stk.push(x);
            while !stk.is_empty() && *stk.last().unwrap() == popped[i] {
                stk.pop();
                i += 1;
            }
        }
        i == popped.len()
    }
}
```

#### JavaScript

```js
/**
 * @param {number[]} pushed
 * @param {number[]} popped
 * @return {boolean}
 */
var validateStackSequences = function (pushed, popped) {
    const stk = [];
    let i = 0;
    for (const x of pushed) {
        stk.push(x);
        while (stk.length && stk.at(-1) === popped[i]) {
            stk.pop();
            i++;
        }
    }
    return i === popped.length;
};
```

#### C#

```cs
public class Solution {
    public bool ValidateStackSequences(int[] pushed, int[] popped) {
        Stack<int> stk = new Stack<int>();
        int i = 0;

        foreach (int x in pushed) {
            stk.Push(x);
            while (stk.Count > 0 && stk.Peek() == popped[i]) {
                stk.Pop();
                i++;
            }
        }

        return i == popped.Length;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
