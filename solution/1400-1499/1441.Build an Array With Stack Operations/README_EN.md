---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1400-1499/1441.Build%20an%20Array%20With%20Stack%20Operations/README_EN.md
rating: 1180
source: Weekly Contest 188 Q1
tags:
    - Stack
    - Array
    - Simulation
---

<!-- problem:start -->

# [1441. Build an Array With Stack Operations](https://leetcode.com/problems/build-an-array-with-stack-operations)

[中文文档](/solution/1400-1499/1441.Build%20an%20Array%20With%20Stack%20Operations/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>target</code> and an integer <code>n</code>.</p>

<p>You have an empty stack with the two following operations:</p>

<ul>
	<li><strong><code>&quot;Push&quot;</code></strong>: pushes an integer to the top of the stack.</li>
	<li><strong><code>&quot;Pop&quot;</code></strong>: removes the integer on the top of the stack.</li>
</ul>

<p>You also have a stream of the integers in the range <code>[1, n]</code>.</p>

<p>Use the two stack operations to make the numbers in the stack (from the bottom to the top) equal to <code>target</code>. You should follow the following rules:</p>

<ul>
	<li>If the stream of the integers is not empty, pick the next integer from the stream and push it to the top of the stack.</li>
	<li>If the stack is not empty, pop the integer at the top of the stack.</li>
	<li>If, at any moment, the elements in the stack (from the bottom to the top) are equal to <code>target</code>, do not read new integers from the stream and do not do more operations on the stack.</li>
</ul>

<p>Return <em>the stack operations needed to build </em><code>target</code> following the mentioned rules. If there are multiple valid answers, return <strong>any of them</strong>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> target = [1,3], n = 3
<strong>Output:</strong> [&quot;Push&quot;,&quot;Push&quot;,&quot;Pop&quot;,&quot;Push&quot;]
<strong>Explanation:</strong> Initially the stack s is empty. The last element is the top of the stack.
Read 1 from the stream and push it to the stack. s = [1].
Read 2 from the stream and push it to the stack. s = [1,2].
Pop the integer on the top of the stack. s = [1].
Read 3 from the stream and push it to the stack. s = [1,3].
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> target = [1,2,3], n = 3
<strong>Output:</strong> [&quot;Push&quot;,&quot;Push&quot;,&quot;Push&quot;]
<strong>Explanation:</strong> Initially the stack s is empty. The last element is the top of the stack.
Read 1 from the stream and push it to the stack. s = [1].
Read 2 from the stream and push it to the stack. s = [1,2].
Read 3 from the stream and push it to the stack. s = [1,2,3].
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> target = [1,2], n = 4
<strong>Output:</strong> [&quot;Push&quot;,&quot;Push&quot;]
<strong>Explanation:</strong> Initially the stack s is empty. The last element is the top of the stack.
Read 1 from the stream and push it to the stack. s = [1].
Read 2 from the stream and push it to the stack. s = [1,2].
Since the stack (from the bottom to the top) is equal to target, we stop the stack operations.
The answers that read integer 3 from the stream are not accepted.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= target.length &lt;= 100</code></li>
	<li><code>1 &lt;= n &lt;= 100</code></li>
	<li><code>1 &lt;= target[i] &lt;= n</code></li>
	<li><code>target</code> is strictly increasing.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Simulation

We define a variable $\textit{cur}$ to represent the current number to be read, initially set to $\textit{cur} = 1$, and use an array $\textit{ans}$ to store the answer.

Next, we iterate through each number $x$ in the array $\textit{target}$:

-   If $\textit{cur} < x$, we add $\textit{Push}$ and $\textit{Pop}$ to the answer alternately until $\textit{cur} = x$;
-   Then we add $\textit{Push}$ to the answer, representing reading the number $x$;
-   After that, we increment $\textit{cur}$ and continue to process the next number.

After the iteration, we return the answer array.

The time complexity is $O(n)$, where $n$ is the length of the array $\textit{target}$. Ignoring the space consumption of the answer array, the space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def buildArray(self, target: List[int], n: int) -> List[str]:
        ans = []
        cur = 1
        for x in target:
            while cur < x:
                ans.extend(["Push", "Pop"])
                cur += 1
            ans.append("Push")
            cur += 1
        return ans
```

#### Java

```java
class Solution {
    public List<String> buildArray(int[] target, int n) {
        List<String> ans = new ArrayList<>();
        int cur = 1;
        for (int x : target) {
            while (cur < x) {
                ans.addAll(List.of("Push", "Pop"));
                ++cur;
            }
            ans.add("Push");
            ++cur;
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<string> buildArray(vector<int>& target, int n) {
        vector<string> ans;
        int cur = 1;
        for (int x : target) {
            while (cur < x) {
                ans.push_back("Push");
                ans.push_back("Pop");
                ++cur;
            }
            ans.push_back("Push");
            ++cur;
        }
        return ans;
    }
};
```

#### Go

```go
func buildArray(target []int, n int) (ans []string) {
	cur := 1
	for _, x := range target {
		for ; cur < x; cur++ {
			ans = append(ans, "Push", "Pop")
		}
		ans = append(ans, "Push")
		cur++
	}
	return
}
```

#### TypeScript

```ts
function buildArray(target: number[], n: number): string[] {
    const ans: string[] = [];
    let cur: number = 1;
    for (const x of target) {
        for (; cur < x; ++cur) {
            ans.push('Push', 'Pop');
        }
        ans.push('Push');
        ++cur;
    }
    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn build_array(target: Vec<i32>, n: i32) -> Vec<String> {
        let mut ans = Vec::new();
        let mut cur = 1;
        for &x in &target {
            while cur < x {
                ans.push("Push".to_string());
                ans.push("Pop".to_string());
                cur += 1;
            }
            ans.push("Push".to_string());
            cur += 1;
        }
        ans
    }
}
```

#### C

```c
/**
 * Note: The returned array must be malloced, assume caller calls free().
 */
char** buildArray(int* target, int targetSize, int n, int* returnSize) {
    char** ans = (char**) malloc(sizeof(char*) * (2 * n));
    *returnSize = 0;
    int cur = 1;
    for (int i = 0; i < targetSize; i++) {
        while (cur < target[i]) {
            ans[(*returnSize)++] = "Push";
            ans[(*returnSize)++] = "Pop";
            cur++;
        }
        ans[(*returnSize)++] = "Push";
        cur++;
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
