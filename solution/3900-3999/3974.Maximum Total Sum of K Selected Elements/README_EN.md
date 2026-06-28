---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3900-3999/3974.Maximum%20Total%20Sum%20of%20K%20Selected%20Elements/README_EN.md
---

<!-- problem:start -->

# [3974. Maximum Total Sum of K Selected Elements](https://leetcode.com/problems/maximum-total-sum-of-k-selected-elements)

[中文文档](/solution/3900-3999/3974.Maximum%20Total%20Sum%20of%20K%20Selected%20Elements/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>nums</code> and two integers <code>k</code> and <code>mul</code>.</p>

<p>Select <strong>exactly</strong> <code>k</code> elements from <code>nums</code>. Process these elements one by one in any order you choose.</p>

<p>For each selected element, <strong>independently</strong> choose one of the following:</p>

<ul>
	<li><strong>Add</strong> the element&#39;s value to the total sum, or</li>
	<li><strong>Multiply</strong> the element by the <strong>current</strong> value of <code>mul</code> and <strong>add</strong> the result to the total sum.</li>
</ul>

<p>After processing each selected element, <code>mul</code> <strong>decreases</strong> by 1, regardless of which option was chosen. The current value of <code>mul</code> may become 0 or negative.</p>

<p>Return an integer denoting the <strong>maximum</strong> possible total sum.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [6,1,2,9], k = 3, mul = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">26</span></p>

<p><strong>Explanation:</strong></p>

<p>One optimal way:</p>

<ul>
	<li>One optimal selection is <code>nums[3] = 9</code>, <code>nums[0] = 6</code>, and <code>nums[2] = 2</code>.</li>
	<li>Process <code>nums[3] = 9</code> first: choose multiplication, so it contributes <code>9 * 2 = 18</code>. Now, <code>mul</code> becomes 1.</li>
	<li>Process <code>nums[0] = 6</code> next: choose multiplication, so it contributes <code>6 * 1 = 6</code>. Now, <code>mul</code> becomes 0.</li>
	<li>Process <code>nums[2] = 2</code> last: choose addition, so it contributes 2.</li>
	<li>The total sum is <code>18 + 6 + 2 = 26</code>.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [3,7,5,2], k = 2, mul = 4</span></p>

<p><strong>Output:</strong> <span class="example-io">43</span></p>

<p><strong>Explanation:</strong></p>

<p>One optimal way:</p>

<ul>
	<li>One optimal selection is <code>nums[1] = 7</code> and <code>nums[2] = 5</code>.</li>
	<li>Process <code>nums[1] = 7</code> first: choose multiplication, so it contributes <code>7 * 4 = 28</code>. Now, <code>mul</code> becomes 3.</li>
	<li>Process <code>nums[2] = 5</code> next: choose multiplication, so it contributes <code>5 * 3 = 15</code>.</li>
	<li>The total sum is <code>28 + 15 = 43</code>.</li>
</ul>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [4,4], k = 1, mul = 1</span></p>

<p><strong>Output:</strong> <span class="example-io">4</span></p>

<p><strong>Explanation:</strong></p>

<p>One optimal way:</p>

<ul>
	<li>One optimal selection is <code>nums[0] = 4</code>.</li>
	<li>Process <code>nums[0] = 4</code>: choose multiplication, so it contributes <code>4 * 1 = 4</code>.</li>
	<li>The total sum is 4.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= k &lt;= nums.length</code></li>
	<li><code>1 &lt;= mul &lt;= 10<sup>5</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Greedy + Sorting

We can sort the array $\textit{nums}$ and then select the $k$ largest elements from the sorted array. For the $i$-th element, we can choose to multiply it by $\max(1, \textit{mul})$ and add it to the total sum, and then $\textit{mul}$ decreases by $1$. Finally, we return the total sum.

The time complexity is $O(n \log n)$, and the space complexity is $O(\log n)$. Where $n$ is the length of the array $\textit{nums}$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxSum(self, nums: list[int], k: int, mul: int) -> int:
        nums.sort()
        n = len(nums)
        ans = 0
        for i in range(n - 1, n - 1 - k, -1):
            ans += nums[i] * max(1, mul)
            mul -= 1
        return ans
```

#### Java

```java
class Solution {
    public long maxSum(int[] nums, int k, int mul) {
        Arrays.sort(nums);
        int n = nums.length;
        long ans = 0;

        for (int i = n - 1; i >= n - k; i--) {
            int m = Math.max(1, mul);
            ans += (long) nums[i] * m;
            mul--;
        }

        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    long long maxSum(vector<int>& nums, int k, int mul) {
        sort(nums.begin(), nums.end());
        int n = nums.size();
        long long ans = 0;

        for (int i = n - 1; i >= n - k; --i) {
            int m = max(1, mul);
            ans += 1LL * nums[i] * m;
            mul--;
        }

        return ans;
    }
};
```

#### Go

```go
func maxSum(nums []int, k int, mul int) int64 {
    sort.Ints(nums)
    n := len(nums)
    var ans int64 = 0

    for i := n - 1; i >= n-k; i-- {
        m := max(1, mul)
        ans += int64(nums[i]) * int64(m)
        mul--
    }

    return ans
}
```

#### TypeScript

```ts
function maxSum(nums: number[], k: number, mul: number): number {
    nums.sort((a, b) => a - b);
    const n = nums.length;
    let ans = 0;

    for (let i = n - 1; i >= n - k; i--) {
        const m = Math.max(1, mul);
        ans += nums[i] * m;
        mul--;
    }

    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
