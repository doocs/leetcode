---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3600-3699/3659.Partition%20Array%20Into%20K-Distinct%20Groups/README_EN.md
---

<!-- problem:start -->

# [3659. Partition Array Into K-Distinct Groups](https://leetcode.com/problems/partition-array-into-k-distinct-groups)

[中文文档](/solution/3600-3699/3659.Partition%20Array%20Into%20K-Distinct%20Groups/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>nums</code> and an integer <code>k</code>.</p>

<p>Your task is to determine whether it is possible to partition all elements of <code>nums</code> into one or more groups such that:</p>

<ul>
	<li>Each group contains <strong>exactly</strong> <code>k</code> <strong>distinct</strong> elements.</li>
	<li>Each element in <code>nums</code> must be assigned to <strong>exactly</strong> one group.</li>
</ul>

<p>Return <code>true</code> if such a partition is possible, otherwise return <code>false</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,2,3,4], k = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">true</span></p>

<p><strong>Explanation:</strong></p>

<p>One possible partition is to have 2 groups:</p>

<ul>
	<li>Group 1: <code>[1, 2]</code></li>
	<li>Group 2: <code>[3, 4]</code></li>
</ul>

<p>Each group contains <code>k = 2</code> distinct elements, and all elements are used exactly once.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [3,5,2,2], k = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">true</span></p>

<p><strong>Explanation:</strong></p>

<p>One possible partition is to have 2 groups:</p>

<ul>
	<li>Group 1: <code>[2, 3]</code></li>
	<li>Group 2: <code>[2, 5]</code></li>
</ul>

<p>Each group contains <code>k = 2</code> distinct elements, and all elements are used exactly once.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,5,2,3], k = 3</span></p>

<p><strong>Output:</strong> <span class="example-io">false</span></p>

<p><strong>Explanation:</strong></p>

<p>We cannot form groups of <code>k = 3</code> distinct elements using all values exactly once.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
	<li><code><sup>​​​​​​​</sup>1 &lt;= k &lt;= nums.length</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Counting

We denote the length of the array as $n$. If $n$ is not divisible by $k$, then we cannot partition the array into groups where each group contains $k$ elements, so we directly return $\text{false}$.

Next, we calculate the size of each group $m = n / k$ and count the occurrence of each element in the array. If the occurrence count of any element exceeds $m$, then it cannot be distributed to any group, so we directly return $\text{false}$.

Finally, if the occurrence count of all elements does not exceed $m$, then we can partition the array into groups where each group contains $k$ elements, and we return $\text{true}$.

Time complexity $O(n)$, space complexity $O(n)$ or $O(M)$. Where $n$ is the length of the array, and $M$ is the maximum value of elements in the array.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def partitionArray(self, nums: List[int], k: int) -> bool:
        m, mod = divmod(len(nums), k)
        if mod:
            return False
        return max(Counter(nums).values()) <= m
```

#### Java

```java
class Solution {
    public boolean partitionArray(int[] nums, int k) {
        int n = nums.length;
        if (n % k != 0) {
            return false;
        }
        int m = n / k;
        int mx = Arrays.stream(nums).max().getAsInt();
        int[] cnt = new int[mx + 1];
        for (int x : nums) {
            if (++cnt[x] > m) {
                return false;
            }
        }
        return true;
    }
}
```

#### C++

```cpp
class Solution {
public:
    bool partitionArray(vector<int>& nums, int k) {
        int n = nums.size();
        if (n % k) {
            return false;
        }
        int m = n / k;
        int mx = *ranges::max_element(nums);
        vector<int> cnt(mx + 1);
        for (int x : nums) {
            if (++cnt[x] > m) {
                return false;
            }
        }
        return true;
    }
};
```

#### Go

```go
func partitionArray(nums []int, k int) bool {
	n := len(nums)
	if n%k != 0 {
		return false
	}
	m := n / k
	mx := slices.Max(nums)
	cnt := make([]int, mx+1)
	for _, x := range nums {
		if cnt[x]++; cnt[x] > m {
			return false
		}
	}
	return true
}
```

#### TypeScript

```ts
function partitionArray(nums: number[], k: number): boolean {
    const n = nums.length;
    if (n % k) {
        return false;
    }
    const m = n / k;
    const mx = Math.max(...nums);
    const cnt: number[] = Array(mx + 1).fill(0);
    for (const x of nums) {
        if (++cnt[x] > m) {
            return false;
        }
    }
    return true;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
