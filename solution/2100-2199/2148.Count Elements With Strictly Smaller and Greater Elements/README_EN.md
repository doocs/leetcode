---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2100-2199/2148.Count%20Elements%20With%20Strictly%20Smaller%20and%20Greater%20Elements/README_EN.md
rating: 1201
source: Weekly Contest 277 Q1
tags:
    - Array
    - Counting
    - Sorting
---

<!-- problem:start -->

# [2148. Count Elements With Strictly Smaller and Greater Elements](https://leetcode.com/problems/count-elements-with-strictly-smaller-and-greater-elements)

[中文文档](/solution/2100-2199/2148.Count%20Elements%20With%20Strictly%20Smaller%20and%20Greater%20Elements/README.md)

## Description

<!-- description:start -->

<p>Given an integer array <code>nums</code>, return <em>the number of elements that have <strong>both</strong> a strictly smaller and a strictly greater element appear in </em><code>nums</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [11,7,2,15]
<strong>Output:</strong> 2
<strong>Explanation:</strong> The element 7 has the element 2 strictly smaller than it and the element 11 strictly greater than it.
Element 11 has element 7 strictly smaller than it and element 15 strictly greater than it.
In total there are 2 elements having both a strictly smaller and a strictly greater element appear in <code>nums</code>.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [-3,3,3,90]
<strong>Output:</strong> 2
<strong>Explanation:</strong> The element 3 has the element -3 strictly smaller than it and the element 90 strictly greater than it.
Since there are two elements with the value 3, in total there are 2 elements having both a strictly smaller and a strictly greater element appear in <code>nums</code>.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 100</code></li>
	<li><code>-10<sup>5</sup> &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Find Minimum and Maximum Values

According to the problem description, we can first find the minimum value $\textit{mi}$ and the maximum value $\textit{mx}$ of the array $\textit{nums}$. Then, traverse the array $\textit{nums}$ and count the number of elements that satisfy $\textit{mi} < x < \textit{mx}$.

The time complexity is $O(n)$, where $n$ is the length of the array $\textit{nums}$. The space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def countElements(self, nums: List[int]) -> int:
        mi, mx = min(nums), max(nums)
        return sum(mi < x < mx for x in nums)
```

#### Java

```java
class Solution {
    public int countElements(int[] nums) {
        int mi = Arrays.stream(nums).min().getAsInt();
        int mx = Arrays.stream(nums).max().getAsInt();
        int ans = 0;
        for (int x : nums) {
            if (mi < x && x < mx) {
                ans++;
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
    int countElements(vector<int>& nums) {
        auto [mi, mx] = ranges::minmax_element(nums);
        return ranges::count_if(nums, [mi, mx](int x) { return *mi < x && x < *mx; });
    }
};
```

#### Go

```go
func countElements(nums []int) (ans int) {
	mi := slices.Min(nums)
	mx := slices.Max(nums)
	for _, x := range nums {
		if mi < x && x < mx {
			ans++
		}
	}
	return
}
```

#### TypeScript

```ts
function countElements(nums: number[]): number {
    const mi = Math.min(...nums);
    const mx = Math.max(...nums);
    return nums.filter(x => mi < x && x < mx).length;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
