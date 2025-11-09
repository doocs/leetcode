---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3500-3599/3542.Minimum%20Operations%20to%20Convert%20All%20Elements%20to%20Zero/README_EN.md
rating: 1889
source: Biweekly Contest 156 Q2
tags:
    - Stack
    - Greedy
    - Array
    - Hash Table
    - Monotonic Stack
---

<!-- problem:start -->

# [3542. Minimum Operations to Convert All Elements to Zero](https://leetcode.com/problems/minimum-operations-to-convert-all-elements-to-zero)

[中文文档](/solution/3500-3599/3542.Minimum%20Operations%20to%20Convert%20All%20Elements%20to%20Zero/README.md)

## Description

<!-- description:start -->

<p>You are given an array <code>nums</code> of size <code>n</code>, consisting of <strong>non-negative</strong> integers. Your task is to apply some (possibly zero) operations on the array so that <strong>all</strong> elements become 0.</p>

<p>In one operation, you can select a <span data-keyword="subarray">subarray</span> <code>[i, j]</code> (where <code>0 &lt;= i &lt;= j &lt; n</code>) and set all occurrences of the <strong>minimum</strong> <strong>non-negative</strong> integer in that subarray to 0.</p>

<p>Return the <strong>minimum</strong> number of operations required to make all elements in the array 0.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [0,2]</span></p>

<p><strong>Output:</strong> <span class="example-io">1</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Select the subarray <code>[1,1]</code> (which is <code>[2]</code>), where the minimum non-negative integer is 2. Setting all occurrences of 2 to 0 results in <code>[0,0]</code>.</li>
	<li>Thus, the minimum number of operations required is 1.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [3,1,2,1]</span></p>

<p><strong>Output:</strong> <span class="example-io">3</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Select subarray <code>[1,3]</code> (which is <code>[1,2,1]</code>), where the minimum non-negative integer is 1. Setting all occurrences of 1 to 0 results in <code>[3,0,2,0]</code>.</li>
	<li>Select subarray <code>[2,2]</code> (which is <code>[2]</code>), where the minimum non-negative integer is 2. Setting all occurrences of 2 to 0 results in <code>[3,0,0,0]</code>.</li>
	<li>Select subarray <code>[0,0]</code> (which is <code>[3]</code>), where the minimum non-negative integer is 3. Setting all occurrences of 3 to 0 results in <code>[0,0,0,0]</code>.</li>
	<li>Thus, the minimum number of operations required is 3.</li>
</ul>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,2,1,2,1,2]</span></p>

<p><strong>Output:</strong> <span class="example-io">4</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Select subarray <code>[0,5]</code> (which is <code>[1,2,1,2,1,2]</code>), where the minimum non-negative integer is 1. Setting all occurrences of 1 to 0 results in <code>[0,2,0,2,0,2]</code>.</li>
	<li>Select subarray <code>[1,1]</code> (which is <code>[2]</code>), where the minimum non-negative integer is 2. Setting all occurrences of 2 to 0 results in <code>[0,0,0,2,0,2]</code>.</li>
	<li>Select subarray <code>[3,3]</code> (which is <code>[2]</code>), where the minimum non-negative integer is 2. Setting all occurrences of 2 to 0 results in <code>[0,0,0,0,0,2]</code>.</li>
	<li>Select subarray <code>[5,5]</code> (which is <code>[2]</code>), where the minimum non-negative integer is 2. Setting all occurrences of 2 to 0 results in <code>[0,0,0,0,0,0]</code>.</li>
	<li>Thus, the minimum number of operations required is 4.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n == nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Monotonic Stack

According to the problem description, we should first convert the smallest numbers to $0$, then the second smallest numbers to $0$, and so on. During this process, if two numbers are separated by smaller numbers, they require an additional operation to become $0$.

We can maintain a monotonically increasing stack $\textit{stk}$ from bottom to top, and traverse each number $\textit{x}$ in the array $\textit{nums}$:

-   When the top element of the stack is greater than $\textit{x}$, it means $\textit{x}$ separates the top element. We need to pop the top element and increment the answer by $1$, continuing until the top element is not greater than $\textit{x}$.
-   If $\textit{x}$ is not $0$, and the stack is empty or the top element is not equal to $\textit{x}$, then push $\textit{x}$ onto the stack.

After traversal, the remaining elements in the stack all require an additional operation to become $0$, so we add the size of the stack to the answer.

The time complexity is $O(n)$, and the space complexity is $O(n)$, where $n$ is the length of the array $\textit{nums}$.

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
