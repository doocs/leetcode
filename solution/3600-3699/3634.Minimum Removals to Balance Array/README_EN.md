---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3600-3699/3634.Minimum%20Removals%20to%20Balance%20Array/README_EN.md
rating: 1453
source: Biweekly Contest 162 Q2
tags:
    - Array
    - Sorting
    - Sliding Window
---

<!-- problem:start -->

# [3634. Minimum Removals to Balance Array](https://leetcode.com/problems/minimum-removals-to-balance-array)

[中文文档](/solution/3600-3699/3634.Minimum%20Removals%20to%20Balance%20Array/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>nums</code> and an integer <code>k</code>.</p>

<p>An array is considered <strong>balanced</strong> if the value of its <strong>maximum</strong> element is <strong>at most</strong> <code>k</code> times the <strong>minimum</strong> element.</p>

<p>You may remove <strong>any</strong> number of elements from <code>nums</code>​​​​​​​ without making it <strong>empty</strong>.</p>

<p>Return the <strong>minimum</strong> number of elements to remove so that the remaining array is balanced.</p>

<p><strong>Note:</strong> An array of size 1 is considered balanced as its maximum and minimum are equal, and the condition always holds true.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [2,1,5], k = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">1</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Remove <code>nums[2] = 5</code> to get <code>nums = [2, 1]</code>.</li>
	<li>Now <code>max = 2</code>, <code>min = 1</code> and <code>max &lt;= min * k</code> as <code>2 &lt;= 1 * 2</code>. Thus, the answer is 1.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,6,2,9], k = 3</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Remove <code>nums[0] = 1</code> and <code>nums[3] = 9</code> to get <code>nums = [6, 2]</code>.</li>
	<li>Now <code>max = 6</code>, <code>min = 2</code> and <code>max &lt;= min * k</code> as <code>6 &lt;= 2 * 3</code>. Thus, the answer is 2.</li>
</ul>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [4,6], k = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">0</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Since <code>nums</code> is already balanced as <code>6 &lt;= 4 * 2</code>, no elements need to be removed.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= k &lt;= 10<sup>5</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Sorting + Binary Search

We first sort the array, then enumerate each element $\textit{nums}[i]$ from small to large as the minimum value of the balanced array. The maximum value $\textit{max}$ of the balanced array must satisfy $\textit{max} \leq \textit{nums}[i] \times k$. Therefore, we can use binary search to find the index $j$ of the first element greater than $\textit{nums}[i] \times k$. At this point, the length of the balanced array is $j - i$. We record the maximum length $\textit{cnt}$, and the final answer is the array length minus $\textit{cnt}$.

The time complexity is $O(n \times \log n)$, and the space complexity is $O(\log n)$, where $n$ is the length of the array $\textit{nums}$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minRemoval(self, nums: List[int], k: int) -> int:
        nums.sort()
        cnt = 0
        for i, x in enumerate(nums):
            j = bisect_right(nums, k * x)
            cnt = max(cnt, j - i)
        return len(nums) - cnt
```

#### Java

```java
class Solution {
    public int minRemoval(int[] nums, int k) {
        Arrays.sort(nums);
        int cnt = 0;
        int n = nums.length;
        for (int i = 0; i < n; ++i) {
            int j = n;
            if (1L * nums[i] * k <= nums[n - 1]) {
                j = Arrays.binarySearch(nums, nums[i] * k + 1);
                j = j < 0 ? -j - 1 : j;
            }
            cnt = Math.max(cnt, j - i);
        }
        return n - cnt;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int minRemoval(vector<int>& nums, int k) {
        ranges::sort(nums);
        int cnt = 0;
        int n = nums.size();
        for (int i = 0; i < n; ++i) {
            int j = n;
            if (1LL * nums[i] * k <= nums[n - 1]) {
                j = upper_bound(nums.begin(), nums.end(), 1LL * nums[i] * k) - nums.begin();
            }
            cnt = max(cnt, j - i);
        }
        return n - cnt;
    }
};
```

#### Go

```go
func minRemoval(nums []int, k int) int {
	sort.Ints(nums)
	n := len(nums)
	cnt := 0
	for i := 0; i < n; i++ {
		j := n
		if int64(nums[i])*int64(k) <= int64(nums[n-1]) {
			target := int64(nums[i])*int64(k) + 1
			j = sort.Search(n, func(x int) bool {
				return int64(nums[x]) >= target
			})
		}
		cnt = max(cnt, j-i)
	}
	return n - cnt
}
```

#### TypeScript

```ts
function minRemoval(nums: number[], k: number): number {
    nums.sort((a, b) => a - b);
    const n = nums.length;
    let cnt = 0;
    for (let i = 0; i < n; ++i) {
        let j = n;
        if (nums[i] * k <= nums[n - 1]) {
            const target = nums[i] * k + 1;
            j = _.sortedIndexBy(nums, target, x => x);
        }
        cnt = Math.max(cnt, j - i);
    }
    return n - cnt;
}
```

#### Rust

```rust
impl Solution {
    pub fn min_removal(mut nums: Vec<i32>, k: i32) -> i32 {
        nums.sort();
        let mut cnt = 0;
        let n = nums.len();
        for i in 0..n {
            let mut j = n;
            let target = nums[i] as i64 * k as i64;
            if target <= nums[n - 1] as i64 {
                j = nums.partition_point(|&x| x as i64 <= target);
            }
            cnt = cnt.max(j - i);
        }
        (n - cnt) as i32
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Solution 2: Sorting + Two Pointers

We first sort the array, then use two pointers to maintain a sliding window. The left pointer $l$ enumerates each element $\textit{nums}[l]$ from left to right as the minimum value of the balanced array. The right pointer $r$ keeps moving right until $\textit{nums}[r]$ is greater than $\textit{nums}[l] \times k$. At this point, the length of the balanced array is $r - l$, and the number of elements to be removed is $n - (r - l)$. We record the minimum number of removals as the answer.

The time complexity is $O(n \times \log n)$ and the space complexity is $O(\log n)$, where $n$ is the length of the array $\textit{nums}$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minRemoval(self, nums: List[int], k: int) -> int:
        nums.sort()
        ans = n = len(nums)
        r = 0
        for l in range(n):
            while r < n and nums[r] <= nums[l] * k:
                r += 1
            ans = min(ans, n - (r - l))
        return ans
```

#### Java

```java
class Solution {
    public int minRemoval(int[] nums, int k) {
        Arrays.sort(nums);
        int n = nums.length;
        int ans = n;
        int r = 0;
        for (int l = 0; l < n; l++) {
            while (r < n && nums[r] <= (long) nums[l] * k) {
                r++;
            }
            ans = Math.min(ans, n - (r - l));
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int minRemoval(vector<int>& nums, int k) {
        ranges::sort(nums);
        int n = nums.size();
        int ans = n;
        int r = 0;
        for (int l = 0; l < n; l++) {
            while (r < n && nums[r] <= (long long) nums[l] * k) {
                r++;
            }
            ans = min(ans, n - (r - l));
        }
        return ans;
    }
};
```

#### Go

```go
func minRemoval(nums []int, k int) int {
	sort.Ints(nums)
	n := len(nums)
	ans := n
	r := 0
	for l := 0; l < n; l++ {
		for r < n && nums[r] <= nums[l]*k {
			r++
		}
		ans = min(ans, n-(r-l))
	}
	return ans
}
```

#### TypeScript

```ts
function minRemoval(nums: number[], k: number): number {
    nums.sort((a, b) => a - b);
    const n = nums.length;
    let ans = n;
    let r = 0;
    for (let l = 0; l < n; l++) {
        while (r < n && nums[r] <= nums[l] * k) {
            r++;
        }
        ans = Math.min(ans, n - (r - l));
    }
    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn min_removal(mut nums: Vec<i32>, k: i32) -> i32 {
        nums.sort();
        let n = nums.len();
        let mut ans = n;
        let mut r = 0;
        let k = k as i64;
        for l in 0..n {
            while r < n && nums[r] as i64 <= nums[l] as i64 * k {
                r += 1;
            }
            ans = ans.min(n - (r - l));
        }
        ans as i32
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
