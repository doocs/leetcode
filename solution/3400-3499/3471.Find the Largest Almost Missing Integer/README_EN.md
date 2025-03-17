---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3400-3499/3471.Find%20the%20Largest%20Almost%20Missing%20Integer/README_EN.md
rating: 1308
source: Weekly Contest 439 Q1
tags:
    - Array
    - Hash Table
---

<!-- problem:start -->

# [3471. Find the Largest Almost Missing Integer](https://leetcode.com/problems/find-the-largest-almost-missing-integer)

[中文文档](/solution/3400-3499/3471.Find%20the%20Largest%20Almost%20Missing%20Integer/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>nums</code> and an integer <code>k</code>.</p>

<p>An integer <code>x</code> is <strong>almost missing</strong> from <code>nums</code> if <code>x</code> appears in <em>exactly</em> one subarray of size <code>k</code> within <code>nums</code>.</p>

<p>Return the <b>largest</b> <strong>almost missing</strong> integer from <code>nums</code>. If no such integer exists, return <code>-1</code>.</p>
A <strong>subarray</strong> is a contiguous sequence of elements within an array.
<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [3,9,2,1,7], k = 3</span></p>

<p><strong>Output:</strong> <span class="example-io">7</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>1 appears in 2 subarrays of size 3: <code>[9, 2, 1]</code> and <code>[2, 1, 7]</code>.</li>
	<li>2 appears in 3 subarrays of size 3: <code>[3, 9, 2]</code>, <code>[9, 2, 1]</code>, <code>[2, 1, 7]</code>.</li>
	<li index="2">3 appears in 1 subarray of size 3: <code>[3, 9, 2]</code>.</li>
	<li index="3">7 appears in 1 subarray of size 3: <code>[2, 1, 7]</code>.</li>
	<li index="4">9 appears in 2 subarrays of size 3: <code>[3, 9, 2]</code>, and <code>[9, 2, 1]</code>.</li>
</ul>

<p>We return 7 since it is the largest integer that appears in exactly one subarray of size <code>k</code>.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [3,9,7,2,1,7], k = 4</span></p>

<p><strong>Output:</strong> <span class="example-io">3</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>1 appears in 2 subarrays of size 4: <code>[9, 7, 2, 1]</code>, <code>[7, 2, 1, 7]</code>.</li>
	<li>2 appears in 3 subarrays of size 4: <code>[3, 9, 7, 2]</code>, <code>[9, 7, 2, 1]</code>, <code>[7, 2, 1, 7]</code>.</li>
	<li>3 appears in 1 subarray of size 4: <code>[3, 9, 7, 2]</code>.</li>
	<li>7 appears in 3 subarrays of size 4: <code>[3, 9, 7, 2]</code>, <code>[9, 7, 2, 1]</code>, <code>[7, 2, 1, 7]</code>.</li>
	<li>9 appears in 2 subarrays of size 4: <code>[3, 9, 7, 2]</code>, <code>[9, 7, 2, 1]</code>.</li>
</ul>

<p>We return 3 since it is the largest and only integer that appears in exactly one subarray of size <code>k</code>.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [0,0], k = 1</span></p>

<p><strong>Output:</strong> <span class="example-io">-1</span></p>

<p><strong>Explanation:</strong></p>

<p>There is no integer that appears in only one subarray of size 1.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 50</code></li>
	<li><code>0 &lt;= nums[i] &lt;= 50</code></li>
	<li><code>1 &lt;= k &lt;= nums.length</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Case Analysis

If $k = 1$, then each element in the array forms a subarray of size $1$. In this case, we only need to find the maximum value among the elements that appear exactly once in the array.

If $k = n$, then the entire array forms a subarray of size $n$. In this case, we only need to return the maximum value in the array.

If $1 < k < n$, only $\textit{nums}[0]$ and $\textit{nums}[n-1]$ can be the almost missing integers. If they appear elsewhere in the array, they are not almost missing integers. Therefore, we only need to check if $\textit{nums}[0]$ and $\textit{nums}[n-1]$ appear elsewhere in the array and return the maximum value among them.

If no almost missing integer exists, return $-1$.

The time complexity is $O(n)$, and the space complexity is $O(n)$. Here, $n$ is the length of the array $\textit{nums}$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def largestInteger(self, nums: List[int], k: int) -> int:
        def f(k: int) -> int:
            for i, x in enumerate(nums):
                if i != k and x == nums[k]:
                    return -1
            return nums[k]

        if k == 1:
            cnt = Counter(nums)
            return max((x for x, v in cnt.items() if v == 1), default=-1)
        if k == len(nums):
            return max(nums)
        return max(f(0), f(len(nums) - 1))
```

#### Java

```java
class Solution {
    private int[] nums;

    public int largestInteger(int[] nums, int k) {
        this.nums = nums;
        if (k == 1) {
            Map<Integer, Integer> cnt = new HashMap<>();
            for (int x : nums) {
                cnt.merge(x, 1, Integer::sum);
            }
            int ans = -1;
            for (var e : cnt.entrySet()) {
                if (e.getValue() == 1) {
                    ans = Math.max(ans, e.getKey());
                }
            }
            return ans;
        }
        if (k == nums.length) {
            return Arrays.stream(nums).max().getAsInt();
        }
        return Math.max(f(0), f(nums.length - 1));
    }

    private int f(int k) {
        for (int i = 0; i < nums.length; ++i) {
            if (i != k && nums[i] == nums[k]) {
                return -1;
            }
        }
        return nums[k];
    }
}
```

#### C++

```cpp
class Solution {
public:
    int largestInteger(vector<int>& nums, int k) {
        if (k == 1) {
            unordered_map<int, int> cnt;
            for (int x : nums) {
                ++cnt[x];
            }
            int ans = -1;
            for (auto& [x, v] : cnt) {
                if (v == 1) {
                    ans = max(ans, x);
                }
            }
            return ans;
        }
        int n = nums.size();
        if (k == n) {
            return ranges::max(nums);
        }
        auto f = [&](int k) -> int {
            for (int i = 0; i < n; ++i) {
                if (i != k && nums[i] == nums[k]) {
                    return -1;
                }
            }
            return nums[k];
        };
        return max(f(0), f(n - 1));
    }
};
```

#### Go

```go
func largestInteger(nums []int, k int) int {
    if k == 1 {
        cnt := make(map[int]int)
        for _, x := range nums {
            cnt[x]++
        }
        ans := -1
        for x, v := range cnt {
            if v == 1 {
                ans = max(ans, x)
            }
        }
        return ans
    }

    n := len(nums)
    if k == n {
        return slices.Max(nums)
    }

    f := func(k int) int {
        for i, x := range nums {
            if i != k && x == nums[k] {
                return -1
            }
        }
        return nums[k]
    }

    return max(f(0), f(n-1))
}
```

#### TypeScript

```ts
function largestInteger(nums: number[], k: number): number {
    if (k === 1) {
        const cnt = new Map<number, number>();
        for (const x of nums) {
            cnt.set(x, (cnt.get(x) || 0) + 1);
        }
        let ans = -1;
        for (const [x, v] of cnt.entries()) {
            if (v === 1 && x > ans) {
                ans = x;
            }
        }
        return ans;
    }

    const n = nums.length;
    if (k === n) {
        return Math.max(...nums);
    }

    const f = (k: number): number => {
        for (let i = 0; i < n; i++) {
            if (i !== k && nums[i] === nums[k]) {
                return -1;
            }
        }
        return nums[k];
    };

    return Math.max(f(0), f(n - 1));
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
