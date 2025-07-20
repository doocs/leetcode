---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1400-1499/1498.Number%20of%20Subsequences%20That%20Satisfy%20the%20Given%20Sum%20Condition/README_EN.md
rating: 2276
source: Weekly Contest 195 Q3
tags:
    - Array
    - Two Pointers
    - Binary Search
    - Sorting
---

<!-- problem:start -->

# [1498. Number of Subsequences That Satisfy the Given Sum Condition](https://leetcode.com/problems/number-of-subsequences-that-satisfy-the-given-sum-condition)

[中文文档](/solution/1400-1499/1498.Number%20of%20Subsequences%20That%20Satisfy%20the%20Given%20Sum%20Condition/README.md)

## Description

<!-- description:start -->

<p>You are given an array of integers <code>nums</code> and an integer <code>target</code>.</p>

<p>Return <em>the number of <strong>non-empty</strong> subsequences of </em><code>nums</code><em> such that the sum of the minimum and maximum element on it is less or equal to </em><code>target</code>. Since the answer may be too large, return it <strong>modulo</strong> <code>10<sup>9</sup> + 7</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [3,5,6,7], target = 9
<strong>Output:</strong> 4
<strong>Explanation:</strong> There are 4 subsequences that satisfy the condition.
[3] -&gt; Min value + max value &lt;= target (3 + 3 &lt;= 9)
[3,5] -&gt; (3 + 5 &lt;= 9)
[3,5,6] -&gt; (3 + 6 &lt;= 9)
[3,6] -&gt; (3 + 6 &lt;= 9)
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [3,3,6,8], target = 10
<strong>Output:</strong> 6
<strong>Explanation:</strong> There are 6 subsequences that satisfy the condition. (nums can have repeated numbers).
[3] , [3] , [3,3], [3,6] , [3,6] , [3,3,6]
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [2,3,3,4,6,7], target = 12
<strong>Output:</strong> 61
<strong>Explanation:</strong> There are 63 non-empty subsequences, two of them do not satisfy the condition ([6,7], [7]).
Number of valid subsequences (63 - 2 = 61).
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>6</sup></code></li>
	<li><code>1 &lt;= target &lt;= 10<sup>6</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Sorting + Binary Search

Since the problem is about subsequences and involves the sum of the minimum and maximum elements, we can first sort the array $\textit{nums}$.

Then we enumerate the minimum element $\textit{nums}[i]$. For each $\textit{nums}[i]$, we can find the maximum element $\textit{nums}[j]$ in $\textit{nums}[i + 1]$ to $\textit{nums}[n - 1]$ such that $\textit{nums}[i] + \textit{nums}[j] \leq \textit{target}$. The number of valid subsequences in this case is $2^{j - i}$, where $2^{j - i}$ represents all possible subsequences from $\textit{nums}[i + 1]$ to $\textit{nums}[j]$. We sum up the counts of all such subsequences.

The time complexity is $O(n \times \log n)$, and the space complexity is $O(n)$, where $n$ is the length of the array $\textit{nums}$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def numSubseq(self, nums: List[int], target: int) -> int:
        mod = 10**9 + 7
        nums.sort()
        n = len(nums)
        f = [1] + [0] * n
        for i in range(1, n + 1):
            f[i] = f[i - 1] * 2 % mod
        ans = 0
        for i, x in enumerate(nums):
            if x * 2 > target:
                break
            j = bisect_right(nums, target - x, i + 1) - 1
            ans = (ans + f[j - i]) % mod
        return ans
```

#### Java

```java
class Solution {
    public int numSubseq(int[] nums, int target) {
        Arrays.sort(nums);
        final int mod = (int) 1e9 + 7;
        int n = nums.length;
        int[] f = new int[n + 1];
        f[0] = 1;
        for (int i = 1; i <= n; ++i) {
            f[i] = (f[i - 1] * 2) % mod;
        }
        int ans = 0;
        for (int i = 0; i < n && nums[i] * 2 <= target; ++i) {
            int j = search(nums, target - nums[i], i + 1) - 1;
            ans = (ans + f[j - i]) % mod;
        }
        return ans;
    }

    private int search(int[] nums, int x, int left) {
        int right = nums.length;
        while (left < right) {
            int mid = (left + right) >> 1;
            if (nums[mid] > x) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int numSubseq(vector<int>& nums, int target) {
        sort(nums.begin(), nums.end());
        const int mod = 1e9 + 7;
        int n = nums.size();
        int f[n + 1];
        f[0] = 1;
        for (int i = 1; i <= n; ++i) {
            f[i] = (f[i - 1] * 2) % mod;
        }
        int ans = 0;
        for (int i = 0; i < n && nums[i] * 2 <= target; ++i) {
            int j = upper_bound(nums.begin() + i + 1, nums.end(), target - nums[i]) - nums.begin() - 1;
            ans = (ans + f[j - i]) % mod;
        }
        return ans;
    }
};
```

#### Go

```go
func numSubseq(nums []int, target int) (ans int) {
	sort.Ints(nums)
	n := len(nums)
	f := make([]int, n+1)
	f[0] = 1
	const mod int = 1e9 + 7
	for i := 1; i <= n; i++ {
		f[i] = f[i-1] * 2 % mod
	}
	for i, x := range nums {
		if x*2 > target {
			break
		}
		j := sort.SearchInts(nums[i+1:], target-x+1) + i
		ans = (ans + f[j-i]) % mod
	}
	return
}
```

#### TypeScript

```ts
function numSubseq(nums: number[], target: number): number {
    nums.sort((a, b) => a - b);
    const mod = 1e9 + 7;
    const n = nums.length;
    const f: number[] = Array(n + 1).fill(1);
    for (let i = 1; i <= n; ++i) {
        f[i] = (f[i - 1] * 2) % mod;
    }

    let ans = 0;
    for (let i = 0; i < n && nums[i] * 2 <= target; ++i) {
        const j = search(nums, target - nums[i], i + 1) - 1;
        if (j >= i) {
            ans = (ans + f[j - i]) % mod;
        }
    }
    return ans;
}

function search(nums: number[], x: number, left: number): number {
    let right = nums.length;
    while (left < right) {
        const mid = (left + right) >> 1;
        if (nums[mid] > x) {
            right = mid;
        } else {
            left = mid + 1;
        }
    }
    return left;
}
```

#### Rust

```rust
impl Solution {
    pub fn num_subseq(mut nums: Vec<i32>, target: i32) -> i32 {
        nums.sort();
        const MOD: i32 = 1_000_000_007;
        let n = nums.len();
        let mut f = vec![1; n + 1];
        for i in 1..=n {
            f[i] = (f[i - 1] * 2) % MOD;
        }
        let mut ans = 0;
        for i in 0..n {
            if nums[i] * 2 > target {
                break;
            }
            let mut l = i + 1;
            let mut r = n;
            while l < r {
                let m = (l + r) / 2;
                if nums[m] > target - nums[i] {
                    r = m;
                } else {
                    l = m + 1;
                }
            }
            let j = l - 1;
            ans = (ans + f[j - i]) % MOD;
        }
        ans
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
