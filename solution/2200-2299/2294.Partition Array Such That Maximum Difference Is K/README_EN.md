---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2200-2299/2294.Partition%20Array%20Such%20That%20Maximum%20Difference%20Is%20K/README_EN.md
rating: 1416
source: Weekly Contest 296 Q2
tags:
    - Greedy
    - Array
    - Sorting
---

<!-- problem:start -->

# [2294. Partition Array Such That Maximum Difference Is K](https://leetcode.com/problems/partition-array-such-that-maximum-difference-is-k)

[中文文档](/solution/2200-2299/2294.Partition%20Array%20Such%20That%20Maximum%20Difference%20Is%20K/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>nums</code> and an integer <code>k</code>. You may partition <code>nums</code> into one or more <strong>subsequences</strong> such that each element in <code>nums</code> appears in <strong>exactly</strong> one of the subsequences.</p>

<p>Return <em>the <strong>minimum </strong>number of subsequences needed such that the difference between the maximum and minimum values in each subsequence is <strong>at most</strong> </em><code>k</code><em>.</em></p>

<p>A <strong>subsequence</strong> is a sequence that can be derived from another sequence by deleting some or no elements without changing the order of the remaining elements.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [3,6,1,2,5], k = 2
<strong>Output:</strong> 2
<strong>Explanation:</strong>
We can partition nums into the two subsequences [3,1,2] and [6,5].
The difference between the maximum and minimum value in the first subsequence is 3 - 1 = 2.
The difference between the maximum and minimum value in the second subsequence is 6 - 5 = 1.
Since two subsequences were created, we return 2. It can be shown that 2 is the minimum number of subsequences needed.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,3], k = 1
<strong>Output:</strong> 2
<strong>Explanation:</strong>
We can partition nums into the two subsequences [1,2] and [3].
The difference between the maximum and minimum value in the first subsequence is 2 - 1 = 1.
The difference between the maximum and minimum value in the second subsequence is 3 - 3 = 0.
Since two subsequences were created, we return 2. Note that another optimal solution is to partition nums into the two subsequences [1] and [2,3].
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [2,2,4,5], k = 0
<strong>Output:</strong> 3
<strong>Explanation:</strong>
We can partition nums into the three subsequences [2,2], [4], and [5].
The difference between the maximum and minimum value in the first subsequences is 2 - 2 = 0.
The difference between the maximum and minimum value in the second subsequences is 4 - 4 = 0.
The difference between the maximum and minimum value in the third subsequences is 5 - 5 = 0.
Since three subsequences were created, we return 3. It can be shown that 3 is the minimum number of subsequences needed.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= k &lt;= 10<sup>5</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Greedy + Sorting

The problem requires dividing into subsequences, not subarrays, so the elements in a subsequence can be non-continuous. We can sort the array $\textit{nums}$. Assuming the first element of the current subsequence is $a$, the difference between the maximum and minimum values in the subsequence will not exceed $k$. Therefore, we can iterate through the array $\textit{nums}$. If the difference between the current element $b$ and $a$ is greater than $k$, then update $a$ to $b$ and increase the number of subsequences by 1. After the iteration, we can obtain the minimum number of subsequences, noting that the initial number of subsequences is $1$.

The time complexity is $O(n \times \log n)$, and the space complexity is $O(\log n)$. Here, $n$ is the length of the array $\textit{nums}$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def partitionArray(self, nums: List[int], k: int) -> int:
        nums.sort()
        ans, a = 1, nums[0]
        for b in nums:
            if b - a > k:
                a = b
                ans += 1
        return ans
```

#### Java

```java
class Solution {
    public int partitionArray(int[] nums, int k) {
        Arrays.sort(nums);
        int ans = 1, a = nums[0];
        for (int b : nums) {
            if (b - a > k) {
                a = b;
                ++ans;
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
    int partitionArray(vector<int>& nums, int k) {
        sort(nums.begin(), nums.end());
        int ans = 1, a = nums[0];
        for (int& b : nums) {
            if (b - a > k) {
                a = b;
                ++ans;
            }
        }
        return ans;
    }
};
```

#### Go

```go
func partitionArray(nums []int, k int) int {
	sort.Ints(nums)
	ans, a := 1, nums[0]
	for _, b := range nums {
		if b-a > k {
			a = b
			ans++
		}
	}
	return ans
}
```

#### TypeScript

```ts
function partitionArray(nums: number[], k: number): number {
    nums.sort((a, b) => a - b);
    let ans = 1;
    let a = nums[0];
    for (const b of nums) {
        if (b - a > k) {
            a = b;
            ++ans;
        }
    }
    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn partition_array(mut nums: Vec<i32>, k: i32) -> i32 {
        nums.sort();
        let mut ans = 1;
        let mut a = nums[0];

        for &b in nums.iter() {
            if b - a > k {
                a = b;
                ans += 1;
            }
        }

        ans
    }
}
```

#### Rust

```rust
public class Solution {
    public int PartitionArray(int[] nums, int k) {
        Array.Sort(nums);
        int ans = 1;
        int a = nums[0];

        foreach (int b in nums) {
            if (b - a > k) {
                a = b;
                ans++;
            }
        }

        return ans;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
