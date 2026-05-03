---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3900-3999/3917.Count%20Indices%20With%20Opposite%20Parity/README_EN.md
---

<!-- problem:start -->

# [3917. Count Indices With Opposite Parity](https://leetcode.com/problems/count-indices-with-opposite-parity)

[中文文档](/solution/3900-3999/3917.Count%20Indices%20With%20Opposite%20Parity/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>nums</code> of length <code>n</code>.</p>

<p>The <strong>score</strong> of an index <code>i</code> is defined as the number of indices <code>j</code> such that:</p>

<ul>
	<li><code>i &lt; j &lt; n</code>, and</li>
	<li><code>nums[i]</code> and <code>nums[j]</code> have different parity (one is even and the other is odd).</li>
</ul>

<p>Return an integer array <code>answer</code> of length <code>n</code>, where <code>answer[i]</code> is the score of index <code>i</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,2,3,4]</span></p>

<p><strong>Output:</strong> <span class="example-io">[2,1,1,0]</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li><code>nums[0] = 1</code>, which is odd. Thus, the indices <code>j = 1</code> and <code>j = 3</code> satisfy the conditions, so the score of index 0 is 2.</li>
	<li><code>nums[1] = 2</code>, which is even. Thus, the index <code>j = 2</code> satisfies the conditions, so the score of index 1 is 1.</li>
	<li><code>nums[2] = 3</code>, which is odd. Thus, the index <code>j = 3</code> satisfies the conditions, so the score of index 2 is 1.</li>
	<li><code>nums[3] = 4</code>, which is even. Thus, no index satisfies the conditions, so the score of index 3 is 0.</li>
</ul>

<p>Thus, the <code>answer = [2, 1, 1, 0]</code>.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1]</span></p>

<p><strong>Output:</strong> <span class="example-io">[0]</span></p>

<p><strong>Explanation:</strong></p>

<p>There is only one element in <code>nums</code>. Thus, the score of index 0 is 0.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 100</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 100</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Counting

We first count the number of even and odd elements in the array $\textit{nums}$, denoted as $cnt[0]$ and $cnt[1]$ respectively.

Then, we traverse the array $\textit{nums}$ from left to right. For index $i$, we first decrement $cnt[\textit{nums}[i] \bmod 2]$ by 1, then assign $cnt[\textit{nums}[i] \bmod 2 \oplus 1]$ to $ans[i]$.

After the traversal, return the answer array $ans$.

The time complexity is $O(n)$, where $n$ is the length of the array $\textit{nums}$. Ignoring the space complexity of the answer array, the space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def countOppositeParity(self, nums: list[int]) -> list[int]:
        cnt = [0, 0]
        for x in nums:
            cnt[x & 1] += 1
        ans = [0] * len(nums)
        for i, x in enumerate(nums):
            cnt[x & 1] -= 1
            ans[i] = cnt[x & 1 ^ 1]
        return ans
```

#### Java

```java
class Solution {
    public int[] countOppositeParity(int[] nums) {
        int[] cnt = new int[2];
        for (int x : nums) {
            cnt[x & 1]++;
        }
        int n = nums.length;
        int[] ans = new int[n];
        for (int i = 0; i < n; ++i) {
            cnt[nums[i] & 1]--;
            ans[i] = cnt[nums[i] & 1 ^ 1];
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<int> countOppositeParity(vector<int>& nums) {
        int cnt[2] = {0, 0};
        for (int x : nums) {
            cnt[x & 1]++;
        }
        int n = nums.size();
        vector<int> ans(n);
        for (int i = 0; i < n; ++i) {
            cnt[nums[i] & 1]--;
            ans[i] = cnt[(nums[i] & 1) ^ 1];
        }
        return ans;
    }
};
```

#### Go

```go
func countOppositeParity(nums []int) []int {
    cnt := [2]int{}
    for _, x := range nums {
        cnt[x&1]++
    }
    n := len(nums)
    ans := make([]int, n)
    for i, x := range nums {
        cnt[x&1]--
        ans[i] = cnt[x&1^1]
    }
    return ans
}
```

#### TypeScript

```ts
function countOppositeParity(nums: number[]): number[] {
    const cnt = Array<number>(2).fill(0);
    for (const x of nums) {
        ++cnt[x & 1];
    }
    const n = nums.length;
    const ans = Array<number>(n).fill(0);
    for (let i = 0; i < n; ++i) {
        --cnt[nums[i] & 1];
        ans[i] = cnt[(nums[i] & 1) ^ 1];
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
