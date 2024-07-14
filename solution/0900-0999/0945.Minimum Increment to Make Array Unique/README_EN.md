---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0900-0999/0945.Minimum%20Increment%20to%20Make%20Array%20Unique/README_EN.md
tags:
    - Greedy
    - Array
    - Counting
    - Sorting
---

<!-- problem:start -->

# [945. Minimum Increment to Make Array Unique](https://leetcode.com/problems/minimum-increment-to-make-array-unique)

[中文文档](/solution/0900-0999/0945.Minimum%20Increment%20to%20Make%20Array%20Unique/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>nums</code>. In one move, you can pick an index <code>i</code> where <code>0 &lt;= i &lt; nums.length</code> and increment <code>nums[i]</code> by <code>1</code>.</p>

<p>Return <em>the minimum number of moves to make every value in </em><code>nums</code><em> <strong>unique</strong></em>.</p>

<p>The test cases are generated so that the answer fits in a 32-bit integer.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,2]
<strong>Output:</strong> 1
<strong>Explanation:</strong> After 1 move, the array could be [1, 2, 3].
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [3,2,1,2,1,7]
<strong>Output:</strong> 6
<strong>Explanation:</strong> After 6 moves, the array could be [3, 4, 1, 2, 5, 7].
It can be shown with 5 or less moves that it is impossible for the array to have all unique values.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minIncrementForUnique(self, nums: List[int]) -> int:
        nums.sort()
        ans = 0
        for i in range(1, len(nums)):
            if nums[i] <= nums[i - 1]:
                d = nums[i - 1] - nums[i] + 1
                nums[i] += d
                ans += d
        return ans
```

#### Java

```java
class Solution {
    public int minIncrementForUnique(int[] nums) {
        Arrays.sort(nums);
        int ans = 0;
        for (int i = 1; i < nums.length; ++i) {
            if (nums[i] <= nums[i - 1]) {
                int d = nums[i - 1] - nums[i] + 1;
                nums[i] += d;
                ans += d;
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
    int minIncrementForUnique(vector<int>& nums) {
        sort(nums.begin(), nums.end());
        int ans = 0;
        for (int i = 1; i < nums.size(); ++i) {
            if (nums[i] <= nums[i - 1]) {
                int d = nums[i - 1] - nums[i] + 1;
                nums[i] += d;
                ans += d;
            }
        }
        return ans;
    }
};
```

#### Go

```go
func minIncrementForUnique(nums []int) (ans int) {
	sort.Ints(nums)
	for i := 1; i < len(nums); i++ {
		if nums[i] <= nums[i-1] {
			d := nums[i-1] - nums[i] + 1
			nums[i] += d
			ans += d
		}
	}
	return
}
```

#### TypeScript

```ts
function minIncrementForUnique(nums: number[]): number {
    nums.sort((a, b) => a - b);
    let ans = 0;
    for (let i = 1; i < nums.length; ++i) {
        if (nums[i] <= nums[i - 1]) {
            ans += nums[i - 1] - nums[i] + 1;
            nums[i] = nums[i - 1] + 1;
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- source:start -->

### Solution 2: Counting + Greedy

According to the problem description, the maximum value of the result array $m = \max(\text{nums}) + \text{len}(\text{nums})$. We can use a counting array `cnt` to record the occurrence times of each element.

Then, we iterate from $0$ to $m - 1$. For each element $i$, if its occurrence times $\text{cnt}[i]$ is greater than $1$, then we add $\text{cnt}[i] - 1$ elements to $i + 1$ and accumulate the operation times to the result.

After the iteration, we return the result.

The time complexity is $O(m)$, and the space complexity is $O(m)$. Here, $m$ is the length of the array `nums` plus the maximum value in the array `nums`.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minIncrementForUnique(self, nums: List[int]) -> int:
        m = max(nums) + len(nums)
        cnt = Counter(nums)
        ans = 0
        for i in range(m - 1):
            if (diff := cnt[i] - 1) > 0:
                cnt[i + 1] += diff
                ans += diff
        return ans
```

#### Java

```java
class Solution {
    public int minIncrementForUnique(int[] nums) {
        int m = Arrays.stream(nums).max().getAsInt() + nums.length;
        int[] cnt = new int[m];
        for (int x : nums) {
            ++cnt[x];
        }
        int ans = 0;
        for (int i = 0; i < m - 1; ++i) {
            int diff = cnt[i] - 1;
            if (diff > 0) {
                cnt[i + 1] += diff;
                ans += diff;
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
    int minIncrementForUnique(vector<int>& nums) {
        int m = *max_element(nums.begin(), nums.end()) + nums.size();
        int cnt[m];
        memset(cnt, 0, sizeof(cnt));
        for (int x : nums) {
            ++cnt[x];
        }
        int ans = 0;
        for (int i = 0; i < m - 1; ++i) {
            int diff = cnt[i] - 1;
            if (diff > 0) {
                cnt[i + 1] += diff;
                ans += diff;
            }
        }
        return ans;
    }
};
```

#### Go

```go
func minIncrementForUnique(nums []int) (ans int) {
	m := slices.Max(nums) + len(nums)
	cnt := make([]int, m)
	for _, x := range nums {
		cnt[x]++
	}
	for i := 0; i < m-1; i++ {
		if diff := cnt[i] - 1; diff > 0 {
			cnt[i+1] += diff
			ans += diff
		}
	}
	return ans
}
```

#### TypeScript

```ts
function minIncrementForUnique(nums: number[]): number {
    const m = Math.max(...nums) + nums.length;
    const cnt: number[] = Array(m).fill(0);
    for (const x of nums) {
        cnt[x]++;
    }
    let ans = 0;
    for (let i = 0; i < m - 1; ++i) {
        const diff = cnt[i] - 1;
        if (diff > 0) {
            cnt[i + 1] += diff;
            ans += diff;
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
