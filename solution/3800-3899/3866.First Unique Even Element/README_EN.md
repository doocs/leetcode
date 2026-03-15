---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3800-3899/3866.First%20Unique%20Even%20Element/README_EN.md
---

<!-- problem:start -->

# [3866. First Unique Even Element](https://leetcode.com/problems/first-unique-even-element)

[中文文档](/solution/3800-3899/3866.First%20Unique%20Even%20Element/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>nums</code>.</p>

<p>Return an integer denoting the first <strong>even</strong> integer (earliest by array index) that appears <strong>exactly</strong> once in <code>nums</code>. If no such integer exists, return -1.</p>

<p>An integer <code>x</code> is considered <strong>even</strong> if it is divisible by 2.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [3,4,2,5,4,6]</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong></p>

<p>Both 2 and 6 are even and they appear exactly once. Since 2 occurs first in the array, the answer is 2.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [4,4]</span></p>

<p><strong>Output:</strong> <span class="example-io">-1</span></p>

<p><strong>Explanation:</strong></p>

<p>No even integer appears exactly once, so return -1.</p>
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

We can use a hash table or array $\textit{cnt}$ to count the number of occurrences of each integer in the array. Then we traverse the array again to find and return the first even number that satisfies the condition. If no such even number exists, we return -1.

The time complexity is $O(n)$, where $n$ is the length of the array. The space complexity is $O(M)$, where $M$ is the range of integers in the array (100 in this problem).

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def firstUniqueEven(self, nums: list[int]) -> int:
        cnt = Counter(nums)
        for x in nums:
            if x % 2 == 0 and cnt[x] == 1:
                return x
        return -1
```

#### Java

```java
class Solution {
    public int firstUniqueEven(int[] nums) {
        int[] cnt = new int[101];
        for (int x : nums) {
            ++cnt[x];
        }
        for (int x : nums) {
            if (x % 2 == 0 && cnt[x] == 1) {
                return x;
            }
        }
        return -1;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int firstUniqueEven(vector<int>& nums) {
        int cnt[101]{};
        for (int x : nums) {
            ++cnt[x];
        }
        for (int x : nums) {
            if (x % 2 == 0 && cnt[x] == 1) {
                return x;
            }
        }
        return -1;
    }
};
```

#### Go

```go
func firstUniqueEven(nums []int) int {
    cnt := make([]int, 101)
    for _, x := range nums {
        cnt[x]++
    }
    for _, x := range nums {
        if x%2 == 0 && cnt[x] == 1 {
            return x
        }
    }
    return -1
}
```

#### TypeScript

```ts
function firstUniqueEven(nums: number[]): number {
    const cnt: number[] = new Array(101).fill(0);

    for (const x of nums) {
        cnt[x]++;
    }

    for (const x of nums) {
        if (x % 2 === 0 && cnt[x] === 1) {
            return x;
        }
    }

    return -1;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
