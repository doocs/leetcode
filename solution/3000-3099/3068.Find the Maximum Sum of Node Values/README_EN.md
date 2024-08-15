---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3000-3099/3068.Find%20the%20Maximum%20Sum%20of%20Node%20Values/README_EN.md
rating: 2267
source: Biweekly Contest 125 Q4
tags:
    - Greedy
    - Bit Manipulation
    - Tree
    - Array
    - Dynamic Programming
    - Sorting
---

<!-- problem:start -->

# [3068. Find the Maximum Sum of Node Values](https://leetcode.com/problems/find-the-maximum-sum-of-node-values)

[中文文档](/solution/3000-3099/3068.Find%20the%20Maximum%20Sum%20of%20Node%20Values/README.md)

## Description

<!-- description:start -->

<p>There exists an <strong>undirected</strong> tree with <code>n</code> nodes numbered <code>0</code> to <code>n - 1</code>. You are given a <strong>0-indexed</strong> 2D integer array <code>edges</code> of length <code>n - 1</code>, where <code>edges[i] = [u<sub>i</sub>, v<sub>i</sub>]</code> indicates that there is an edge between nodes <code>u<sub>i</sub></code> and <code>v<sub>i</sub></code> in the tree. You are also given a <strong>positive</strong> integer <code>k</code>, and a <strong>0-indexed</strong> array of <strong>non-negative</strong> integers <code>nums</code> of length <code>n</code>, where <code>nums[i]</code> represents the <strong>value</strong> of the node numbered <code>i</code>.</p>

<p>Alice wants the sum of values of tree nodes to be <strong>maximum</strong>, for which Alice can perform the following operation <strong>any</strong> number of times (<strong>including zero</strong>) on the tree:</p>

<ul>
	<li>Choose any edge <code>[u, v]</code> connecting the nodes <code>u</code> and <code>v</code>, and update their values as follows:

    <ul>
    	<li><code>nums[u] = nums[u] XOR k</code></li>
    	<li><code>nums[v] = nums[v] XOR k</code></li>
    </ul>
    </li>

</ul>

<p>Return <em>the <strong>maximum</strong> possible <strong>sum</strong> of the <strong>values</strong> Alice can achieve by performing the operation <strong>any</strong> number of times</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3000-3099/3068.Find%20the%20Maximum%20Sum%20of%20Node%20Values/images/screenshot-2023-11-10-012513.png" style="width: 300px; height: 277px;padding: 10px; background: #fff; border-radius: .5rem;" />
<pre>
<strong>Input:</strong> nums = [1,2,1], k = 3, edges = [[0,1],[0,2]]
<strong>Output:</strong> 6
<strong>Explanation:</strong> Alice can achieve the maximum sum of 6 using a single operation:
- Choose the edge [0,2]. nums[0] and nums[2] become: 1 XOR 3 = 2, and the array nums becomes: [1,2,1] -&gt; [2,2,2].
The total sum of values is 2 + 2 + 2 = 6.
It can be shown that 6 is the maximum achievable sum of values.
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3000-3099/3068.Find%20the%20Maximum%20Sum%20of%20Node%20Values/images/screenshot-2024-01-09-220017.png" style="padding: 10px; background: rgb(255, 255, 255); border-radius: 0.5rem; width: 300px; height: 239px;" />
<pre>
<strong>Input:</strong> nums = [2,3], k = 7, edges = [[0,1]]
<strong>Output:</strong> 9
<strong>Explanation:</strong> Alice can achieve the maximum sum of 9 using a single operation:
- Choose the edge [0,1]. nums[0] becomes: 2 XOR 7 = 5 and nums[1] become: 3 XOR 7 = 4, and the array nums becomes: [2,3] -&gt; [5,4].
The total sum of values is 5 + 4 = 9.
It can be shown that 9 is the maximum achievable sum of values.
</pre>

<p><strong class="example">Example 3:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3000-3099/3068.Find%20the%20Maximum%20Sum%20of%20Node%20Values/images/screenshot-2023-11-10-012641.png" style="width: 600px; height: 233px;padding: 10px; background: #fff; border-radius: .5rem;" />
<pre>
<strong>Input:</strong> nums = [7,7,7,7,7,7], k = 3, edges = [[0,1],[0,2],[0,3],[0,4],[0,5]]
<strong>Output:</strong> 42
<strong>Explanation:</strong> The maximum achievable sum is 42 which can be achieved by Alice performing no operations.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= n == nums.length &lt;= 2 * 10<sup>4</sup></code></li>
	<li><code>1 &lt;= k &lt;= 10<sup>9</sup></code></li>
	<li><code>0 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>edges.length == n - 1</code></li>
	<li><code>edges[i].length == 2</code></li>
	<li><code>0 &lt;= edges[i][0], edges[i][1] &lt;= n - 1</code></li>
	<li>The input is generated such that <code>edges</code> represent&nbsp;a valid tree.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Dynamic Programming

For any number $x$, its value remains unchanged after being XORed with $k$ an even number of times. Therefore, for any path in a tree, if we perform the operation on all edges in the path, the values of all nodes on the path except the start and end nodes will not change.

Additionally, no matter how many operations are performed, there will always be an even number of elements XORed with $k$, and the remaining elements will remain unchanged.

Thus, the problem is transformed into: for the array $\textit{nums}$, select an even number of elements to XOR with $k$ to maximize the sum.

We can use dynamic programming to solve this problem. Let $f_0$ represent the maximum sum when an even number of elements have been XORed with $k$, and $f_1$ represent the maximum sum when an odd number of elements have been XORed with $k$. The state transition equations are:

$$
\begin{aligned}
f_0 &= \max(f_0 + x, f_1 + (x \oplus k)) \\
f_1 &= \max(f_1 + x, f_0 + (x \oplus k))
\end{aligned}
$$

where $x$ represents the current element's value.

We traverse the array $\textit{nums}$ and update $f_0$ and $f_1$ according to the above state transition equations. Finally, we return $f_0$.

The time complexity is $O(n)$, where $n$ is the length of the array $\textit{nums}$. The space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maximumValueSum(self, nums: List[int], k: int, edges: List[List[int]]) -> int:
        f0, f1 = 0, -inf
        for x in nums:
            f0, f1 = max(f0 + x, f1 + (x ^ k)), max(f1 + x, f0 + (x ^ k))
        return f0
```

#### Java

```java
class Solution {
    public long maximumValueSum(int[] nums, int k, int[][] edges) {
        long f0 = 0, f1 = -0x3f3f3f3f;
        for (int x : nums) {
            long tmp = f0;
            f0 = Math.max(f0 + x, f1 + (x ^ k));
            f1 = Math.max(f1 + x, tmp + (x ^ k));
        }
        return f0;
    }
}
```

#### C++

```cpp
class Solution {
public:
    long long maximumValueSum(vector<int>& nums, int k, vector<vector<int>>& edges) {
        long long f0 = 0, f1 = -0x3f3f3f3f;
        for (int x : nums) {
            long long tmp = f0;
            f0 = max(f0 + x, f1 + (x ^ k));
            f1 = max(f1 + x, tmp + (x ^ k));
        }
        return f0;
    }
};
```

#### Go

```go
func maximumValueSum(nums []int, k int, edges [][]int) int64 {
	f0, f1 := 0, -0x3f3f3f3f
	for _, x := range nums {
		f0, f1 = max(f0+x, f1+(x^k)), max(f1+x, f0+(x^k))
	}
	return int64(f0)
}
```

#### TypeScript

```ts
function maximumValueSum(nums: number[], k: number, edges: number[][]): number {
    let [f0, f1] = [0, -Infinity];
    for (const x of nums) {
        [f0, f1] = [Math.max(f0 + x, f1 + (x ^ k)), Math.max(f1 + x, f0 + (x ^ k))];
    }
    return f0;
}
```

#### Rust

```rust
impl Solution {
    pub fn maximum_value_sum(nums: Vec<i32>, k: i32, edges: Vec<Vec<i32>>) -> i64 {
        let mut f0: i64 = 0;
        let mut f1: i64 = i64::MIN;

        for &x in &nums {
            let tmp = f0;
            f0 = std::cmp::max(f0 + x as i64, f1 + (x ^ k) as i64);
            f1 = std::cmp::max(f1 + x as i64, tmp + (x ^ k) as i64);
        }

        f0
    }
}
```

#### C#

```cs
public class Solution {
    public long MaximumValueSum(int[] nums, int k, int[][] edges) {
        long f0 = 0, f1 = -0x3f3f3f3f;
        foreach (int x in nums) {
            long tmp = f0;
            f0 = Math.Max(f0 + x, f1 + (x ^ k));
            f1 = Math.Max(f1 + x, tmp + (x ^ k));
        }
        return f0;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
