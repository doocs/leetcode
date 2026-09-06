---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/4000-4099/4031.Find%20All%20Numbers%20Disappeared%20in%20an%20Array%20II/README_EN.md
rating: 1416
source: Weekly Contest 516 Q2
---

<!-- problem:start -->

# [4031. Find All Numbers Disappeared in an Array II](https://leetcode.com/problems/find-all-numbers-disappeared-in-an-array-ii)

[中文文档](/solution/4000-4099/4031.Find%20All%20Numbers%20Disappeared%20in%20an%20Array%20II/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>nums</code> and two integers <code>lower</code> and <code>upper</code>.</p>

<p>A <strong>missing integer</strong> is an integer in the inclusive range <code>[lower, upper]</code> that does not appear in <code>nums</code>.</p>

<p>Return a 2D integer array where each element is of the form <code>[start, end]</code>, representing a <strong>contiguous</strong> range of missing integers. Return the ranges in <strong>increasing</strong> order. If there are no missing integers, return an empty array.</p>

<p><strong>Note:</strong> Consecutive missing integers should be grouped into a single range.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [3,9,7], lower = 1, upper = 12</span></p>

<p><strong>Output:</strong> <span class="example-io">[[1,2],[4,6],[8,8],[10,12]]</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>The missing integers are <code>[1, 2, 4, 5, 6, 8, 10, 11, 12]</code>.</li>
	<li>Grouping the missing integers into the minimum number of contiguous ranges, we get <code>[1, 2]</code>, <code>[4, 6]</code>, <code>[8, 8]</code>, and <code>[10, 12]</code>.</li>
	<li>Therefore, the answer is <code>[[1, 2], [4, 6], [8, 8], [10, 12]]</code>.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,1], lower = 5, upper = 7</span></p>

<p><strong>Output:</strong> <span class="example-io">[[5,7]]</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>The missing integers are <code>[5, 6, 7]</code>.</li>
	<li>Grouping the missing integers into the minimum number of contiguous ranges, we get <code>[5, 7]</code>.</li>
	<li>Therefore, the answer is <code>[[5, 7]]</code>.</li>
</ul>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [2,3,5], lower = 2, upper = 3</span></p>

<p><strong>Output:</strong> <span class="example-io">[]</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>There are no missing integers.</li>
	<li>Therefore, the answer is <code>[]</code>.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= lower &lt;= upper &lt;= 10<sup>5</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Sorting

We sort $\textit{nums}$ and then scan it. Let $\textit{prev}$ be the previous number that appears in $[\textit{lower}, \textit{upper}]$, initially $\textit{lower} - 1$.

Iterate over the sorted array and skip values outside $[\textit{lower}, \textit{upper}]$. If there is a gap between the current number $x$ and $\textit{prev}$, i.e. $x - \textit{prev} > 1$, append the missing range $[\textit{prev} + 1, x - 1]$ to the answer, then set $\textit{prev}$ to $x$.

After the scan, if $\textit{prev} < \textit{upper}$, append the trailing range $[\textit{prev} + 1, \textit{upper}]$.

The time complexity is $O(n \times \log n)$ and the space complexity is $O(\log n)$, where $n$ is the length of $\textit{nums}$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def findDisappearedNumbers(
        self, nums: List[int], lower: int, upper: int
    ) -> List[List[int]]:
        ans = []
        prev = lower - 1
        for x in sorted(set(nums)):
            if x < lower:
                continue
            if x > upper:
                break
            if x - prev > 1:
                ans.append([prev + 1, x - 1])
            prev = x
        if prev < upper:
            ans.append([prev + 1, upper])
        return ans
```

#### Java

```java
class Solution {
    public List<List<Integer>> findDisappearedNumbers(int[] nums, int lower, int upper) {
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<>();
        int prev = lower - 1;
        for (int x : nums) {
            if (x < lower || x > upper) {
                continue;
            }
            if (x - prev > 1) {
                ans.add(List.of(prev + 1, x - 1));
            }
            prev = x;
        }
        if (prev < upper) {
            ans.add(List.of(prev + 1, upper));
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<vector<int>> findDisappearedNumbers(vector<int>& nums, int lower, int upper) {
        sort(nums.begin(), nums.end());
        vector<vector<int>> ans;
        int prev = lower - 1;
        for (int x : nums) {
            if (x < lower || x > upper) {
                continue;
            }
            if (x - prev > 1) {
                ans.push_back({prev + 1, x - 1});
            }
            prev = x;
        }
        if (prev < upper) {
            ans.push_back({prev + 1, upper});
        }
        return ans;
    }
};
```

#### Go

```go
func findDisappearedNumbers(nums []int, lower int, upper int) (ans [][]int) {
	sort.Ints(nums)
	prev := lower - 1
	for _, x := range nums {
		if x < lower || x > upper {
			continue
		}
		if x-prev > 1 {
			ans = append(ans, []int{prev + 1, x - 1})
		}
		prev = x
	}
	if prev < upper {
		ans = append(ans, []int{prev + 1, upper})
	}
	return
}
```

#### TypeScript

```ts
function findDisappearedNumbers(nums: number[], lower: number, upper: number): number[][] {
    nums.sort((a, b) => a - b);
    const ans: number[][] = [];
    let prev = lower - 1;
    for (const x of nums) {
        if (x < lower || x > upper) {
            continue;
        }
        if (x - prev > 1) {
            ans.push([prev + 1, x - 1]);
        }
        prev = x;
    }
    if (prev < upper) {
        ans.push([prev + 1, upper]);
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
