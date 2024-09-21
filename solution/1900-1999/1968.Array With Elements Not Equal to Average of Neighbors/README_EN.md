---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1900-1999/1968.Array%20With%20Elements%20Not%20Equal%20to%20Average%20of%20Neighbors/README_EN.md
rating: 1499
source: Weekly Contest 254 Q2
tags:
    - Greedy
    - Array
    - Sorting
---

<!-- problem:start -->

# [1968. Array With Elements Not Equal to Average of Neighbors](https://leetcode.com/problems/array-with-elements-not-equal-to-average-of-neighbors)

[中文文档](/solution/1900-1999/1968.Array%20With%20Elements%20Not%20Equal%20to%20Average%20of%20Neighbors/README.md)

## Description

<!-- description:start -->

<p>You are given a <strong>0-indexed</strong> array <code>nums</code> of <strong>distinct</strong> integers. You want to rearrange the elements in the array such that every element in the rearranged array is <strong>not</strong> equal to the <strong>average</strong> of its neighbors.</p>

<p>More formally, the rearranged array should have the property such that for every <code>i</code> in the range <code>1 &lt;= i &lt; nums.length - 1</code>, <code>(nums[i-1] + nums[i+1]) / 2</code> is <strong>not</strong> equal to <code>nums[i]</code>.</p>

<p>Return <em><strong>any</strong> rearrangement of </em><code>nums</code><em> that meets the requirements</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,3,4,5]
<strong>Output:</strong> [1,2,4,5,3]
<strong>Explanation:</strong>
When i=1, nums[i] = 2, and the average of its neighbors is (1+4) / 2 = 2.5.
When i=2, nums[i] = 4, and the average of its neighbors is (2+5) / 2 = 3.5.
When i=3, nums[i] = 5, and the average of its neighbors is (4+3) / 2 = 3.5.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [6,2,0,9,7]
<strong>Output:</strong> [9,7,6,2,0]
<strong>Explanation:</strong>
When i=1, nums[i] = 7, and the average of its neighbors is (9+6) / 2 = 7.5.
When i=2, nums[i] = 6, and the average of its neighbors is (7+2) / 2 = 4.5.
When i=3, nums[i] = 2, and the average of its neighbors is (6+0) / 2 = 3.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>3 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Sorting

Since the elements in the array are distinct, we can first sort the array, then divide the array into two parts. Place the first half of the elements in the even positions of the answer array, and the second half of the elements in the odd positions of the answer array. In this way, for each element, its two adjacent elements will not be equal to its average value.

The time complexity is $O(n \times \log n)$, and the space complexity is $O(n)$. Here, $n$ is the length of the array $\textit{nums}$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def rearrangeArray(self, nums: List[int]) -> List[int]:
        nums.sort()
        n = len(nums)
        m = (n + 1) // 2
        ans = []
        for i in range(m):
            ans.append(nums[i])
            if i + m < n:
                ans.append(nums[i + m])
        return ans
```

#### Java

```java
class Solution {
    public int[] rearrangeArray(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        int m = (n + 1) >> 1;
        int[] ans = new int[n];
        for (int i = 0, j = 0; i < n; i += 2, j++) {
            ans[i] = nums[j];
            if (j + m < n) {
                ans[i + 1] = nums[j + m];
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
    vector<int> rearrangeArray(vector<int>& nums) {
        ranges::sort(nums);
        vector<int> ans;
        int n = nums.size();
        int m = (n + 1) >> 1;
        for (int i = 0; i < m; ++i) {
            ans.push_back(nums[i]);
            if (i + m < n) {
                ans.push_back(nums[i + m]);
            }
        }
        return ans;
    }
};
```

#### Go

```go
func rearrangeArray(nums []int) (ans []int) {
	sort.Ints(nums)
	n := len(nums)
	m := (n + 1) >> 1
	for i := 0; i < m; i++ {
		ans = append(ans, nums[i])
		if i+m < n {
			ans = append(ans, nums[i+m])
		}
	}
	return
}
```

#### TypeScript

```ts
function rearrangeArray(nums: number[]): number[] {
    nums.sort((a, b) => a - b);
    const n = nums.length;
    const m = (n + 1) >> 1;
    const ans: number[] = [];
    for (let i = 0; i < m; i++) {
        ans.push(nums[i]);
        if (i + m < n) {
            ans.push(nums[i + m]);
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
