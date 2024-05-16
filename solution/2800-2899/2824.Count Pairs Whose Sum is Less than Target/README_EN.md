---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2800-2899/2824.Count%20Pairs%20Whose%20Sum%20is%20Less%20than%20Target/README_EN.md
rating: 1165
source: Biweekly Contest 111 Q1
tags:
    - Array
    - Two Pointers
    - Binary Search
    - Sorting
---

# [2824. Count Pairs Whose Sum is Less than Target](https://leetcode.com/problems/count-pairs-whose-sum-is-less-than-target)

[中文文档](/solution/2800-2899/2824.Count%20Pairs%20Whose%20Sum%20is%20Less%20than%20Target/README.md)

## Description

Given a <strong>0-indexed</strong> integer array <code>nums</code> of length <code>n</code> and an integer <code>target</code>, return <em>the number of pairs</em> <code>(i, j)</code> <em>where</em> <code>0 &lt;= i &lt; j &lt; n</code> <em>and</em> <code>nums[i] + nums[j] &lt; target</code>.

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [-1,1,2,3,1], target = 2
<strong>Output:</strong> 3
<strong>Explanation:</strong> There are 3 pairs of indices that satisfy the conditions in the statement:
- (0, 1) since 0 &lt; 1 and nums[0] + nums[1] = 0 &lt; target
- (0, 2) since 0 &lt; 2 and nums[0] + nums[2] = 1 &lt; target 
- (0, 4) since 0 &lt; 4 and nums[0] + nums[4] = 0 &lt; target
Note that (0, 3) is not counted since nums[0] + nums[3] is not strictly less than the target.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [-6,2,5,-2,-7,-1,3], target = -2
<strong>Output:</strong> 10
<strong>Explanation:</strong> There are 10 pairs of indices that satisfy the conditions in the statement:
- (0, 1) since 0 &lt; 1 and nums[0] + nums[1] = -4 &lt; target
- (0, 3) since 0 &lt; 3 and nums[0] + nums[3] = -8 &lt; target
- (0, 4) since 0 &lt; 4 and nums[0] + nums[4] = -13 &lt; target
- (0, 5) since 0 &lt; 5 and nums[0] + nums[5] = -7 &lt; target
- (0, 6) since 0 &lt; 6 and nums[0] + nums[6] = -3 &lt; target
- (1, 4) since 1 &lt; 4 and nums[1] + nums[4] = -5 &lt; target
- (3, 4) since 3 &lt; 4 and nums[3] + nums[4] = -9 &lt; target
- (3, 5) since 3 &lt; 5 and nums[3] + nums[5] = -3 &lt; target
- (4, 5) since 4 &lt; 5 and nums[4] + nums[5] = -8 &lt; target
- (4, 6) since 4 &lt; 6 and nums[4] + nums[6] = -4 &lt; target
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length == n &lt;= 50</code></li>
	<li><code>-50 &lt;= nums[i], target &lt;= 50</code></li>
</ul>

## Solutions

### Solution 1: Sorting + Binary Search

First, we sort the array $nums$. Then, for each $j$, we use binary search in the range $[0, j)$ to find the first index $i$ that is greater than or equal to $target - nums[j]$. All indices $k$ in the range $[0, i)$ meet the condition, so the answer increases by $i$.

After the traversal, we return the answer.

The time complexity is $O(n \times \log n)$, and the space complexity is $O(\log n)$. Here, $n$ is the length of the array $nums$.

<!-- tabs:start -->

```python
class Solution:
    def countPairs(self, nums: List[int], target: int) -> int:
        nums.sort()
        ans = 0
        for j, x in enumerate(nums):
            i = bisect_left(nums, target - x, hi=j)
            ans += i
        return ans
```

```java
class Solution {
    public int countPairs(List<Integer> nums, int target) {
        Collections.sort(nums);
        int ans = 0;
        for (int j = 0; j < nums.size(); ++j) {
            int x = nums.get(j);
            int i = search(nums, target - x, j);
            ans += i;
        }
        return ans;
    }

    private int search(List<Integer> nums, int x, int r) {
        int l = 0;
        while (l < r) {
            int mid = (l + r) >> 1;
            if (nums.get(mid) >= x) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }
}
```

```cpp
class Solution {
public:
    int countPairs(vector<int>& nums, int target) {
        sort(nums.begin(), nums.end());
        int ans = 0;
        for (int j = 0; j < nums.size(); ++j) {
            int i = lower_bound(nums.begin(), nums.begin() + j, target - nums[j]) - nums.begin();
            ans += i;
        }
        return ans;
    }
};
```

```go
func countPairs(nums []int, target int) (ans int) {
	sort.Ints(nums)
	for j, x := range nums {
		i := sort.SearchInts(nums[:j], target-x)
		ans += i
	}
	return
}
```

```ts
function countPairs(nums: number[], target: number): number {
    nums.sort((a, b) => a - b);
    let ans = 0;
    const search = (x: number, r: number): number => {
        let l = 0;
        while (l < r) {
            const mid = (l + r) >> 1;
            if (nums[mid] >= x) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    };
    for (let j = 0; j < nums.length; ++j) {
        const i = search(target - nums[j], j);
        ans += i;
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- end -->
