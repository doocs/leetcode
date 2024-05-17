---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1400-1499/1403.Minimum%20Subsequence%20in%20Non-Increasing%20Order/README_EN.md
rating: 1288
source: Weekly Contest 183 Q1
tags:
    - Greedy
    - Array
    - Sorting
---

<!-- problem:start -->

# [1403. Minimum Subsequence in Non-Increasing Order](https://leetcode.com/problems/minimum-subsequence-in-non-increasing-order)

[中文文档](/solution/1400-1499/1403.Minimum%20Subsequence%20in%20Non-Increasing%20Order/README.md)

## Description

<!-- description:start -->

<p>Given the array <code>nums</code>, obtain a subsequence of the array whose sum of elements is <strong>strictly greater</strong> than the sum of the non&nbsp;included elements in such subsequence.&nbsp;</p>

<p>If there are multiple solutions, return the subsequence with <strong>minimum size</strong> and if there still exist multiple solutions, return the subsequence with the <strong>maximum total sum</strong> of all its elements. A subsequence of an array can be obtained by erasing some (possibly zero) elements from the array.&nbsp;</p>

<p>Note that the solution with the given constraints is guaranteed to be&nbsp;<strong>unique</strong>. Also return the answer sorted in <strong>non-increasing</strong> order.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [4,3,10,9,8]
<strong>Output:</strong> [10,9] 
<strong>Explanation:</strong> The subsequences [10,9] and [10,8] are minimal such that the sum of their elements is strictly greater than the sum of elements not included. However, the subsequence [10,9] has the maximum total sum of its elements.&nbsp;
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [4,4,7,6,7]
<strong>Output:</strong> [7,7,6] 
<strong>Explanation:</strong> The subsequence [7,7] has the sum of its elements equal to 14 which is not strictly greater than the sum of elements not included (14 = 4 + 4 + 6). Therefore, the subsequence [7,6,7] is the minimal satisfying the conditions. Note the subsequence has to be returned in non-increasing order.  
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 500</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 100</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Sorting

We can first sort the array $nums$ in descending order, then add the elements to the array from largest to smallest. After each addition, we check whether the sum of the current elements is greater than the sum of the remaining elements. If it is, we return the current array.

The time complexity is $O(n \times \log n)$, and the space complexity is $O(\log n)$. Where $n$ is the length of the array $nums$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minSubsequence(self, nums: List[int]) -> List[int]:
        ans = []
        s, t = sum(nums), 0
        for x in sorted(nums, reverse=True):
            t += x
            ans.append(x)
            if t > s - t:
                break
        return ans
```

#### Java

```java
class Solution {
    public List<Integer> minSubsequence(int[] nums) {
        Arrays.sort(nums);
        List<Integer> ans = new ArrayList<>();
        int s = Arrays.stream(nums).sum();
        int t = 0;
        for (int i = nums.length - 1; i >= 0; i--) {
            t += nums[i];
            ans.add(nums[i]);
            if (t > s - t) {
                break;
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
    vector<int> minSubsequence(vector<int>& nums) {
        sort(nums.rbegin(), nums.rend());
        int s = accumulate(nums.begin(), nums.end(), 0);
        int t = 0;
        vector<int> ans;
        for (int x : nums) {
            t += x;
            ans.push_back(x);
            if (t > s - t) {
                break;
            }
        }
        return ans;
    }
};
```

#### Go

```go
func minSubsequence(nums []int) (ans []int) {
	sort.Ints(nums)
	s, t := 0, 0
	for _, x := range nums {
		s += x
	}
	for i := len(nums) - 1; ; i-- {
		t += nums[i]
		ans = append(ans, nums[i])
		if t > s-t {
			return
		}
	}
}
```

#### TypeScript

```ts
function minSubsequence(nums: number[]): number[] {
    nums.sort((a, b) => b - a);
    const s = nums.reduce((r, c) => r + c);
    let t = 0;
    for (let i = 0; ; ++i) {
        t += nums[i];
        if (t > s - t) {
            return nums.slice(0, i + 1);
        }
    }
}
```

#### Rust

```rust
impl Solution {
    pub fn min_subsequence(mut nums: Vec<i32>) -> Vec<i32> {
        nums.sort_by(|a, b| b.cmp(a));
        let sum = nums.iter().sum::<i32>();
        let mut res = vec![];
        let mut t = 0;
        for num in nums.into_iter() {
            t += num;
            res.push(num);
            if t > sum - t {
                break;
            }
        }
        res
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
