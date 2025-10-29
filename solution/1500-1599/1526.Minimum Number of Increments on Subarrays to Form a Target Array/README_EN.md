---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1500-1599/1526.Minimum%20Number%20of%20Increments%20on%20Subarrays%20to%20Form%20a%20Target%20Array/README_EN.md
rating: 1872
source: Biweekly Contest 31 Q4
tags:
    - Stack
    - Greedy
    - Array
    - Dynamic Programming
    - Monotonic Stack
---

<!-- problem:start -->

# [1526. Minimum Number of Increments on Subarrays to Form a Target Array](https://leetcode.com/problems/minimum-number-of-increments-on-subarrays-to-form-a-target-array)

[中文文档](/solution/1500-1599/1526.Minimum%20Number%20of%20Increments%20on%20Subarrays%20to%20Form%20a%20Target%20Array/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>target</code>. You have an integer array <code>initial</code> of the same size as <code>target</code> with all elements initially zeros.</p>

<p>In one operation you can choose <strong>any</strong> subarray from <code>initial</code> and increment each value by one.</p>

<p>Return <em>the minimum number of operations to form a </em><code>target</code><em> array from </em><code>initial</code>.</p>

<p>The test cases are generated so that the answer fits in a 32-bit integer.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> target = [1,2,3,2,1]
<strong>Output:</strong> 3
<strong>Explanation:</strong> We need at least 3 operations to form the target array from the initial array.
[<strong><u>0,0,0,0,0</u></strong>] increment 1 from index 0 to 4 (inclusive).
[1,<strong><u>1,1,1</u></strong>,1] increment 1 from index 1 to 3 (inclusive).
[1,2,<strong><u>2</u></strong>,2,1] increment 1 at index 2.
[1,2,3,2,1] target array is formed.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> target = [3,1,1,2]
<strong>Output:</strong> 4
<strong>Explanation:</strong> [<strong><u>0,0,0,0</u></strong>] -&gt; [1,1,1,<strong><u>1</u></strong>] -&gt; [<strong><u>1</u></strong>,1,1,2] -&gt; [<strong><u>2</u></strong>,1,1,2] -&gt; [3,1,1,2]
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> target = [3,1,5,4,2]
<strong>Output:</strong> 7
<strong>Explanation:</strong> [<strong><u>0,0,0,0,0</u></strong>] -&gt; [<strong><u>1</u></strong>,1,1,1,1] -&gt; [<strong><u>2</u></strong>,1,1,1,1] -&gt; [3,1,<strong><u>1,1,1</u></strong>] -&gt; [3,1,<strong><u>2,2</u></strong>,2] -&gt; [3,1,<strong><u>3,3</u></strong>,2] -&gt; [3,1,<strong><u>4</u></strong>,4,2] -&gt; [3,1,5,4,2].
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= target.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= target[i] &lt;= 10<sup>5</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Dynamic Programming

We define $f[i]$ as the minimum number of operations required to obtain $target[0,..i]$, initially setting $f[0] = target[0]$.

For $target[i]$, if $target[i] \leq target[i-1]$, then $f[i] = f[i-1]$; otherwise, $f[i] = f[i-1] + target[i] - target[i-1]$.

The final answer is $f[n-1]$.

We notice that $f[i]$ only depends on $f[i-1]$, so we can maintain the operation count using just one variable.

The time complexity is $O(n)$, where $n$ is the length of the array $target$. The space complexity is $O(1)$.

Similar problems:

-   [3229. Minimum Operations to Make Array Equal to Target](https://github.com/doocs/leetcode/blob/main/solution/3200-3299/3229.Minimum%20Operations%20to%20Make%20Array%20Equal%20to%20Target/README_EN.md)

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minNumberOperations(self, target: List[int]) -> int:
        return target[0] + sum(max(0, b - a) for a, b in pairwise(target))
```

#### Java

```java
class Solution {
    public int minNumberOperations(int[] target) {
        int f = target[0];
        for (int i = 1; i < target.length; ++i) {
            if (target[i] > target[i - 1]) {
                f += target[i] - target[i - 1];
            }
        }
        return f;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int minNumberOperations(vector<int>& target) {
        int f = target[0];
        for (int i = 1; i < target.size(); ++i) {
            if (target[i] > target[i - 1]) {
                f += target[i] - target[i - 1];
            }
        }
        return f;
    }
};
```

#### Go

```go
func minNumberOperations(target []int) int {
	f := target[0]
	for i, x := range target[1:] {
		if x > target[i] {
			f += x - target[i]
		}
	}
	return f
}
```

#### TypeScript

```ts
function minNumberOperations(target: number[]): number {
    let f = target[0];
    for (let i = 1; i < target.length; ++i) {
        if (target[i] > target[i - 1]) {
            f += target[i] - target[i - 1];
        }
    }
    return f;
}
```

#### Rust

```rust
impl Solution {
    pub fn min_number_operations(target: Vec<i32>) -> i32 {
        let mut f = target[0];
        for i in 1..target.len() {
            if target[i] > target[i - 1] {
                f += target[i] - target[i - 1];
            }
        }
        f
    }
}
```

#### C#

```cs
public class Solution {
    public int MinNumberOperations(int[] target) {
        int f = target[0];
        for (int i = 1; i < target.Length; ++i) {
            if (target[i] > target[i - 1]) {
                f += target[i] - target[i - 1];
            }
        }
        return f;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
