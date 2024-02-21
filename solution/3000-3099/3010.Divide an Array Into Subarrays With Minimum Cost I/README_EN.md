# [3010. Divide an Array Into Subarrays With Minimum Cost I](https://leetcode.com/problems/divide-an-array-into-subarrays-with-minimum-cost-i)

[中文文档](/solution/3000-3099/3010.Divide%20an%20Array%20Into%20Subarrays%20With%20Minimum%20Cost%20I/README.md)

<!-- tags:Array,Enumeration,Sorting -->

## Description

<p>You are given an array of integers <code>nums</code> of length <code>n</code>.</p>

<p>The <strong>cost</strong> of an array is the value of its <strong>first</strong> element. For example, the cost of <code>[1,2,3]</code> is <code>1</code> while the cost of <code>[3,4,1]</code> is <code>3</code>.</p>

<p>You need to divide <code>nums</code> into <code>3</code> <strong>disjoint contiguous </strong><span data-keyword="subarray-nonempty">subarrays</span>.</p>

<p>Return <em>the <strong>minimum</strong> possible <strong>sum</strong> of the cost of these subarrays</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,3,12]
<strong>Output:</strong> 6
<strong>Explanation:</strong> The best possible way to form 3 subarrays is: [1], [2], and [3,12] at a total cost of 1 + 2 + 3 = 6.
The other possible ways to form 3 subarrays are:
- [1], [2,3], and [12] at a total cost of 1 + 2 + 12 = 15.
- [1,2], [3], and [12] at a total cost of 1 + 3 + 12 = 16.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [5,4,3]
<strong>Output:</strong> 12
<strong>Explanation:</strong> The best possible way to form 3 subarrays is: [5], [4], and [3] at a total cost of 5 + 4 + 3 = 12.
It can be shown that 12 is the minimum cost achievable.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [10,3,1,1]
<strong>Output:</strong> 12
<strong>Explanation:</strong> The best possible way to form 3 subarrays is: [10,3], [1], and [1] at a total cost of 10 + 1 + 1 = 12.
It can be shown that 12 is the minimum cost achievable.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>3 &lt;= n &lt;= 50</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 50</code></li>
</ul>

## Solutions

### Solution 1: Traverse to Find the Smallest and Second Smallest Values

We set the first element of the array $nums$ as $a$, the smallest element among the remaining elements as $b$, and the second smallest element as $c$. The answer is $a+b+c$.

The time complexity is $O(n)$, where $n$ is the length of the array $nums$. The space complexity is $O(1)$.

<!-- tabs:start -->

```python
class Solution:
    def minimumCost(self, nums: List[int]) -> int:
        a, b, c = nums[0], inf, inf
        for x in nums[1:]:
            if x < b:
                c, b = b, x
            elif x < c:
                c = x
        return a + b + c
```

```java
class Solution {
    public int minimumCost(int[] nums) {
        int a = nums[0], b = 100, c = 100;
        for (int i = 1; i < nums.length; ++i) {
            if (nums[i] < b) {
                c = b;
                b = nums[i];
            } else if (nums[i] < c) {
                c = nums[i];
            }
        }
        return a + b + c;
    }
}
```

```cpp
class Solution {
public:
    int minimumCost(vector<int>& nums) {
        int a = nums[0], b = 100, c = 100;
        for (int i = 1; i < nums.size(); ++i) {
            if (nums[i] < b) {
                c = b;
                b = nums[i];
            } else if (nums[i] < c) {
                c = nums[i];
            }
        }
        return a + b + c;
    }
};
```

```go
func minimumCost(nums []int) int {
	a, b, c := nums[0], 100, 100
	for _, x := range nums[1:] {
		if x < b {
			b, c = x, b
		} else if x < c {
			c = x
		}
	}
	return a + b + c
}
```

```ts
function minimumCost(nums: number[]): number {
    let [a, b, c] = [nums[0], 100, 100];
    for (const x of nums.slice(1)) {
        if (x < b) {
            [b, c] = [x, b];
        } else if (x < c) {
            c = x;
        }
    }
    return a + b + c;
}
```

<!-- tabs:end -->

<!-- end -->
