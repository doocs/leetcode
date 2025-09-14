---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3600-3699/3684.Maximize%20Sum%20of%20At%20Most%20K%20Distinct%20Elements/README_EN.md
---

<!-- problem:start -->

# [3684. Maximize Sum of At Most K Distinct Elements](https://leetcode.com/problems/maximize-sum-of-at-most-k-distinct-elements)

[中文文档](/solution/3600-3699/3684.Maximize%20Sum%20of%20At%20Most%20K%20Distinct%20Elements/README.md)

## Description

<!-- description:start -->

<p>You are given a <strong>positive</strong> integer array <code>nums</code> and an integer <code>k</code>.</p>

<p>Choose at most <code>k</code> elements from <code>nums</code> so that their sum is maximized. However, the chosen numbers must be <strong>distinct</strong>.</p>

<p>Return an array containing the chosen numbers in <strong>strictly descending</strong> order.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [84,93,100,77,90], k = 3</span></p>

<p><strong>Output:</strong> <span class="example-io">[100,93,90]</span></p>

<p><strong>Explanation:</strong></p>

<p>The maximum sum is 283, which is attained by choosing 93, 100 and 90. We rearrange them in strictly descending order as <code>[100, 93, 90]</code>.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [84,93,100,77,93], k = 3</span></p>

<p><strong>Output:</strong> <span class="example-io">[100,93,84]</span></p>

<p><strong>Explanation:</strong></p>

<p>The maximum sum is 277, which is attained by choosing 84, 93 and 100. We rearrange them in strictly descending order as <code>[100, 93, <span class="example-io">84</span>]</code>. We cannot choose 93, 100 and 93 because the chosen numbers must be distinct.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,1,1,2,2,2], k = 6</span></p>

<p><strong>Output:</strong> <span class="example-io">[2,1]</span></p>

<p><strong>Explanation:</strong></p>

<p>The maximum sum is 3, which is attained by choosing 1 and 2. We rearrange them in strictly descending order as <code>[2, 1]</code>.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 100</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= k &lt;= nums.length</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Sorting

We first sort the array $\textit{nums}$, then iterate from the end to the beginning, selecting the largest $k$ distinct elements. Since we require a strictly decreasing order, we skip duplicate elements during selection.

The time complexity is $O(n \times \log n)$, where $n$ is the length of the $\textit{nums}$ array. Ignoring the space used for the answer, the space complexity is $O(\log n)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxKDistinct(self, nums: List[int], k: int) -> List[int]:
        nums.sort()
        n = len(nums)
        ans = []
        for i in range(n - 1, -1, -1):
            if i + 1 < n and nums[i] == nums[i + 1]:
                continue
            ans.append(nums[i])
            k -= 1
            if k == 0:
                break
        return ans
```

#### Java

```java
class Solution {
    public int[] maxKDistinct(int[] nums, int k) {
        Arrays.sort(nums);
        int n = nums.length;
        List<Integer> ans = new ArrayList<>();
        for (int i = n - 1; i >= 0; --i) {
            if (i + 1 < n && nums[i] == nums[i + 1]) {
                continue;
            }
            ans.add(nums[i]);
            if (--k == 0) {
                break;
            }
        }
        return ans.stream().mapToInt(x -> x).toArray();
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<int> maxKDistinct(vector<int>& nums, int k) {
        ranges::sort(nums);
        int n = nums.size();
        vector<int> ans;
        for (int i = n - 1; ~i; --i) {
            if (i + 1 < n && nums[i] == nums[i + 1]) {
                continue;
            }
            ans.push_back(nums[i]);
            if (--k == 0) {
                break;
            }
        }
        return ans;
    }
};
```

#### Go

```go
func maxKDistinct(nums []int, k int) (ans []int) {
	slices.Sort(nums)
	n := len(nums)
	for i := n - 1; i >= 0; i-- {
		if i+1 < n && nums[i] == nums[i+1] {
			continue
		}
		ans = append(ans, nums[i])
		if k--; k == 0 {
			break
		}
	}
	return
}
```

#### TypeScript

```ts
function maxKDistinct(nums: number[], k: number): number[] {
    nums.sort((a, b) => a - b);
    const ans: number[] = [];
    const n = nums.length;
    for (let i = n - 1; ~i; --i) {
        if (i + 1 < n && nums[i] === nums[i + 1]) {
            continue;
        }
        ans.push(nums[i]);
        if (--k === 0) {
            break;
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
