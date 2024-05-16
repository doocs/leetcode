---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1200-1299/1283.Find%20the%20Smallest%20Divisor%20Given%20a%20Threshold/README_EN.md
rating: 1541
source: Weekly Contest 166 Q3
tags:
    - Array
    - Binary Search
---

# [1283. Find the Smallest Divisor Given a Threshold](https://leetcode.com/problems/find-the-smallest-divisor-given-a-threshold)

[中文文档](/solution/1200-1299/1283.Find%20the%20Smallest%20Divisor%20Given%20a%20Threshold/README.md)

## Description

<p>Given an array of integers <code>nums</code> and an integer <code>threshold</code>, we will choose a positive integer <code>divisor</code>, divide all the array by it, and sum the division&#39;s result. Find the <strong>smallest</strong> <code>divisor</code> such that the result mentioned above is less than or equal to <code>threshold</code>.</p>

<p>Each result of the division is rounded to the nearest integer greater than or equal to that element. (For example: <code>7/3 = 3</code> and <code>10/2 = 5</code>).</p>

<p>The test cases are generated so&nbsp;that there will be an answer.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,5,9], threshold = 6
<strong>Output:</strong> 5
<strong>Explanation:</strong> We can get a sum to 17 (1+2+5+9) if the divisor is 1. 
If the divisor is 4 we can get a sum of 7 (1+1+2+3) and if the divisor is 5 the sum will be 5 (1+1+1+2). 
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [44,22,33,11,1], threshold = 5
<strong>Output:</strong> 44
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>6</sup></code></li>
	<li><code>nums.length &lt;= threshold &lt;= 10<sup>6</sup></code></li>
</ul>

## Solutions

### Solution 1: Binary Search

Notice that for number $v$, if the sum of results of dividing each number in $nums$ by $v$ is less than or equal to $threshold$, then all values greater than $v$ satisfy the condition. There is a monotonicity, so we can use binary search to find the smallest $v$ that satisfies the condition.

We define the left boundary of the binary search $l=1$, $r=\max(nums)$. Each time we take $mid=(l+r)/2$, calculate the sum of the results of dividing each number in $nums$ by $mid$ $s$, if $s$ is less than or equal to $threshold$, then it means that $mid$ satisfies the condition, we will update $r$ to $mid$, otherwise we will update $l$ to $mid+1$.

Finally, return $l$.

The time complexity is $O(n \times \log M)$, where $n$ is the length of the array $nums$ and $M$ is the maximum value in the array $nums$. The space complexity is $O(1)$.

<!-- tabs:start -->

```python
class Solution:
    def smallestDivisor(self, nums: List[int], threshold: int) -> int:
        l, r = 1, max(nums)
        while l < r:
            mid = (l + r) >> 1
            if sum((x + mid - 1) // mid for x in nums) <= threshold:
                r = mid
            else:
                l = mid + 1
        return l
```

```python
class Solution:
    def smallestDivisor(self, nums: List[int], threshold: int) -> int:
        def f(v: int) -> bool:
            v += 1
            return sum((x + v - 1) // v for x in nums) <= threshold

        return bisect_left(range(max(nums)), True, key=f) + 1
```

```java
class Solution {
    public int smallestDivisor(int[] nums, int threshold) {
        int l = 1, r = 1000000;
        while (l < r) {
            int mid = (l + r) >> 1;
            int s = 0;
            for (int x : nums) {
                s += (x + mid - 1) / mid;
            }
            if (s <= threshold) {
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
    int smallestDivisor(vector<int>& nums, int threshold) {
        int l = 1;
        int r = *max_element(nums.begin(), nums.end());
        while (l < r) {
            int mid = (l + r) >> 1;
            int s = 0;
            for (int x : nums) {
                s += (x + mid - 1) / mid;
            }
            if (s <= threshold) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }
};
```

```go
func smallestDivisor(nums []int, threshold int) int {
	return sort.Search(1000000, func(v int) bool {
		v++
		s := 0
		for _, x := range nums {
			s += (x + v - 1) / v
		}
		return s <= threshold
	}) + 1
}
```

```ts
function smallestDivisor(nums: number[], threshold: number): number {
    let l = 1;
    let r = Math.max(...nums);
    while (l < r) {
        const mid = (l + r) >> 1;
        let s = 0;
        for (const x of nums) {
            s += Math.ceil(x / mid);
        }
        if (s <= threshold) {
            r = mid;
        } else {
            l = mid + 1;
        }
    }
    return l;
}
```

```js
/**
 * @param {number[]} nums
 * @param {number} threshold
 * @return {number}
 */
var smallestDivisor = function (nums, threshold) {
    let l = 1;
    let r = Math.max(...nums);
    while (l < r) {
        const mid = (l + r) >> 1;
        let s = 0;
        for (const x of nums) {
            s += Math.ceil(x / mid);
        }
        if (s <= threshold) {
            r = mid;
        } else {
            l = mid + 1;
        }
    }
    return l;
};
```

```cs
public class Solution {
    public int SmallestDivisor(int[] nums, int threshold) {
        int l = 1;
        int r = nums.Max();
        while (l < r) {
            int mid = (l + r) >> 1;
            int s = 0;
            foreach (int x in nums) {
                s += (x + mid - 1) / mid;
            }
            if (s <= threshold) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }
}
```

<!-- tabs:end -->

<!-- end -->
