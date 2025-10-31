---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2100-2199/2149.Rearrange%20Array%20Elements%20by%20Sign/README_EN.md
rating: 1235
source: Weekly Contest 277 Q2
tags:
    - Array
    - Two Pointers
    - Simulation
---

<!-- problem:start -->

# [2149. Rearrange Array Elements by Sign](https://leetcode.com/problems/rearrange-array-elements-by-sign)

[中文文档](/solution/2100-2199/2149.Rearrange%20Array%20Elements%20by%20Sign/README.md)

## Description

<!-- description:start -->

<p>You are given a <strong>0-indexed</strong> integer array <code>nums</code> of <strong>even</strong> length consisting of an <strong>equal</strong> number of positive and negative integers.</p>

<p>You should return the array of nums such that the array follows the given conditions:</p>

<ol>
	<li>Every <strong>consecutive pair</strong> of integers have <strong>opposite signs</strong>.</li>
	<li>For all integers with the same sign, the <strong>order</strong> in which they were present in <code>nums</code> is <strong>preserved</strong>.</li>
	<li>The rearranged array begins with a positive integer.</li>
</ol>

<p>Return <em>the modified array after rearranging the elements to satisfy the aforementioned conditions</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [3,1,-2,-5,2,-4]
<strong>Output:</strong> [3,-2,1,-5,2,-4]
<strong>Explanation:</strong>
The positive integers in nums are [3,1,2]. The negative integers are [-2,-5,-4].
The only possible way to rearrange them such that they satisfy all conditions is [3,-2,1,-5,2,-4].
Other ways such as [1,-2,2,-5,3,-4], [3,1,2,-2,-5,-4], [-2,3,-5,1,-4,2] are incorrect because they do not satisfy one or more conditions.  
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [-1,1]
<strong>Output:</strong> [1,-1]
<strong>Explanation:</strong>
1 is the only positive integer and -1 the only negative integer in nums.
So nums is rearranged to [1,-1].
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= nums.length &lt;= 2 * 10<sup>5</sup></code></li>
	<li><code>nums.length</code> is <strong>even</strong></li>
	<li><code>1 &lt;= |nums[i]| &lt;= 10<sup>5</sup></code></li>
	<li><code>nums</code> consists of <strong>equal</strong> number of positive and negative integers.</li>
</ul>

<p>&nbsp;</p>
It is not required to do the modifications in-place.

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Two Pointers

First, we create an array $\textit{ans}$ of length $n$. Then, we use two pointers $i$ and $j$ to point to the even and odd indices of $\textit{ans}$, respectively, with initial values $i = 0$, $j = 1$.

We iterate through the array $\textit{nums}$. If the current element $x$ is a positive integer, then we place $x$ into $\textit{ans}[i]$ and increase $i$ by $2$; otherwise, we place $x$ into $\textit{ans}[j]$ and increase $j$ by $2$.

Finally, we return $\textit{ans}$.

The time complexity is $O(n)$, and the space complexity is $O(n)$. Here, $n$ is the length of the array $\textit{nums}$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def rearrangeArray(self, nums: List[int]) -> List[int]:
        ans = [0] * len(nums)
        i, j = 0, 1
        for x in nums:
            if x > 0:
                ans[i] = x
                i += 2
            else:
                ans[j] = x
                j += 2
        return ans
```

#### Java

```java
class Solution {
    public int[] rearrangeArray(int[] nums) {
        int[] ans = new int[nums.length];
        int i = 0, j = 1;
        for (int x : nums) {
            if (x > 0) {
                ans[i] = x;
                i += 2;
            } else {
                ans[j] = x;
                j += 2;
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
        vector<int> ans(nums.size());
        int i = 0, j = 1;
        for (int x : nums) {
            if (x > 0) {
                ans[i] = x;
                i += 2;
            } else {
                ans[j] = x;
                j += 2;
            }
        }
        return ans;
    }
};
```

#### Go

```go
func rearrangeArray(nums []int) []int {
	ans := make([]int, len(nums))
	i, j := 0, 1
	for _, x := range nums {
		if x > 0 {
			ans[i] = x
			i += 2
		} else {
			ans[j] = x
			j += 2
		}
	}
	return ans
}
```

#### TypeScript

```ts
function rearrangeArray(nums: number[]): number[] {
    const ans: number[] = Array(nums.length);
    let [i, j] = [0, 1];
    for (const x of nums) {
        if (x > 0) {
            ans[i] = x;
            i += 2;
        } else {
            ans[j] = x;
            j += 2;
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
