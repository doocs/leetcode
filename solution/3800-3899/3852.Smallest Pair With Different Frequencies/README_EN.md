---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3800-3899/3852.Smallest%20Pair%20With%20Different%20Frequencies/README_EN.md
---

<!-- problem:start -->

# [3852. Smallest Pair With Different Frequencies](https://leetcode.com/problems/smallest-pair-with-different-frequencies)

[中文文档](/solution/3800-3899/3852.Smallest%20Pair%20With%20Different%20Frequencies/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>nums</code>.</p>

<p>Consider all pairs of <strong>distinct</strong> values <code>x</code> and <code>y</code> from <code>nums</code> such that:</p>

<ul>
	<li><code>x &lt; y</code></li>
	<li><code>x</code> and <code>y</code> have different frequencies in <code>nums</code>.</li>
</ul>

<p>Among all such pairs:</p>

<ul>
	<li>Choose the pair with the smallest possible value of <code>x</code>.</li>
	<li>If multiple pairs have the same <code>x</code>, choose the one with the smallest possible value of <code>y</code>.</li>
</ul>

<p>Return an integer array <code>[x, y]</code>. If no valid pair exists, return <code>[-1, -1]</code>.</p>

<p>The <strong>frequency</strong> of a value <code>x</code> is the number of times it occurs in the array.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,1,2,2,3,4]</span></p>

<p><strong>Output:</strong> <span class="example-io">[1,3]</span></p>

<p><strong>Explanation:</strong></p>

<p>The smallest value is 1 with a frequency of 2, and the smallest value greater than 1 that has a different frequency from 1 is 3 with a frequency of 1. Thus, the answer is <code>[1, 3]</code>.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,5]</span></p>

<p><strong>Output:</strong> <span class="example-io">[-1,-1]</span></p>

<p><strong>Explanation:</strong></p>

<p>Both values have the same frequency, so no valid pair exists. Return <code>[-1, -1]</code>.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [7]</span></p>

<p><strong>Output:</strong> <span class="example-io">[-1,-1]</span></p>

<p><strong>Explanation:</strong></p>

<p>There is only one value in the array, so no valid pair exists. Return <code>[-1, -1]</code>.</p>
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

### Solution 1: Hash Table

We use a hash table $\textit{cnt}$ to count the frequency of each value in the array. Then we find the smallest value $x$, and the smallest value $y$ that is greater than $x$ and has a different frequency from $x$. If no such $y$ exists, return $[-1, -1]$.

The time complexity is $O(n)$, and the space complexity is $O(n)$, where $n$ is the length of the array $\textit{nums}$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minDistinctFreqPair(self, nums: list[int]) -> list[int]:
        cnt = Counter(nums)
        x = min(cnt.keys())
        min_y = inf
        for y in cnt.keys():
            if y < min_y and cnt[x] != cnt[y]:
                min_y = y
        return [-1, -1] if min_y == inf else [x, min_y]
```

#### Java

```java
class Solution {
    public int[] minDistinctFreqPair(int[] nums) {
        final int inf = Integer.MAX_VALUE;
        Map<Integer, Integer> cnt = new HashMap<>();
        int x = inf;
        for (int v : nums) {
            cnt.merge(v, 1, Integer::sum);
            x = Math.min(x, v);
        }
        int minY = inf;
        for (int y : cnt.keySet()) {
            if (y < minY && cnt.get(x) != cnt.get(y)) {
                minY = y;
            }
        }
        return minY == inf ? new int[] {-1, -1} : new int[] {x, minY};
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<int> minDistinctFreqPair(vector<int>& nums) {
        const int inf = INT_MAX;
        unordered_map<int, int> cnt;
        int x = inf;

        for (int v : nums) {
            cnt[v]++;
            x = min(x, v);
        }

        int minY = inf;
        for (auto& [y, _] : cnt) {
            if (y < minY && cnt[x] != cnt[y]) {
                minY = y;
            }
        }

        if (minY == inf) {
            return {-1, -1};
        }
        return {x, minY};
    }
};
```

#### Go

```go
func minDistinctFreqPair(nums []int) []int {
	const inf = math.MaxInt
	cnt := make(map[int]int)

	for _, v := range nums {
		cnt[v]++
	}

	x := slices.Min(nums)

	minY := inf
	for y := range cnt {
		if y < minY && cnt[x] != cnt[y] {
			minY = y
		}
	}

	if minY == inf {
		return []int{-1, -1}
	}
	return []int{x, minY}
}
```

#### TypeScript

```ts
function minDistinctFreqPair(nums: number[]): number[] {
    const inf = Number.MAX_SAFE_INTEGER;
    const cnt = new Map<number, number>();

    let x = inf;
    for (const v of nums) {
        cnt.set(v, (cnt.get(v) ?? 0) + 1);
        x = Math.min(x, v);
    }

    let minY = inf;
    for (const [y] of cnt) {
        if (y < minY && cnt.get(x)! !== cnt.get(y)!) {
            minY = y;
        }
    }

    return minY === inf ? [-1, -1] : [x, minY];
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
