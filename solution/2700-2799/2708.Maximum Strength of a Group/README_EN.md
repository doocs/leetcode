---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2700-2799/2708.Maximum%20Strength%20of%20a%20Group/README_EN.md
rating: 1502
source: Biweekly Contest 105 Q3
tags:
    - Greedy
    - Bit Manipulation
    - Array
    - Dynamic Programming
    - Backtracking
    - Enumeration
    - Sorting
---

# [2708. Maximum Strength of a Group](https://leetcode.com/problems/maximum-strength-of-a-group)

[中文文档](/solution/2700-2799/2708.Maximum%20Strength%20of%20a%20Group/README.md)

## Description

<p>You are given a <strong>0-indexed</strong> integer array <code>nums</code> representing the score of students in an exam. The teacher would like to form one <strong>non-empty</strong> group of students with maximal <strong>strength</strong>, where the strength of a group of students of indices <code>i<sub>0</sub></code>, <code>i<sub>1</sub></code>, <code>i<sub>2</sub></code>, ... , <code>i<sub>k</sub></code> is defined as <code>nums[i<sub>0</sub>] * nums[i<sub>1</sub>] * nums[i<sub>2</sub>] * ... * nums[i<sub>k</sub>​]</code>.</p>

<p>Return <em>the maximum strength of a group the teacher can create</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [3,-1,-5,2,5,-9]
<strong>Output:</strong> 1350
<strong>Explanation:</strong> One way to form a group of maximal strength is to group the students at indices [0,2,3,4,5]. Their strength is 3 * (-5) * 2 * 5 * (-9) = 1350, which we can show is optimal.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [-4,-5,-4]
<strong>Output:</strong> 20
<strong>Explanation:</strong> Group the students at indices [0, 1] . Then, we&rsquo;ll have a resulting strength of 20. We cannot achieve greater strength.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 13</code></li>
	<li><code>-9 &lt;= nums[i] &lt;= 9</code></li>
</ul>

## Solutions

### Solution 1: Binary Enumeration

The problem is actually to find the maximum product of all subsets. Since the length of the array does not exceed $13$, we can consider using the method of binary enumeration.

We enumerate all subsets in the range of $[1, 2^n)$, and for each subset, we calculate its product, and finally return the maximum value.

The time complexity is $O(2^n \times n)$, where $n$ is the length of the array. The space complexity is $O(1)$.

<!-- tabs:start -->

```python
class Solution:
    def maxStrength(self, nums: List[int]) -> int:
        ans = -inf
        for i in range(1, 1 << len(nums)):
            t = 1
            for j, x in enumerate(nums):
                if i >> j & 1:
                    t *= x
            ans = max(ans, t)
        return ans
```

```java
class Solution {
    public long maxStrength(int[] nums) {
        long ans = (long) -1e14;
        int n = nums.length;
        for (int i = 1; i < 1 << n; ++i) {
            long t = 1;
            for (int j = 0; j < n; ++j) {
                if ((i >> j & 1) == 1) {
                    t *= nums[j];
                }
            }
            ans = Math.max(ans, t);
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    long long maxStrength(vector<int>& nums) {
        long long ans = -1e14;
        int n = nums.size();
        for (int i = 1; i < 1 << n; ++i) {
            long long t = 1;
            for (int j = 0; j < n; ++j) {
                if (i >> j & 1) {
                    t *= nums[j];
                }
            }
            ans = max(ans, t);
        }
        return ans;
    }
};
```

```go
func maxStrength(nums []int) int64 {
	ans := int64(-1e14)
	for i := 1; i < 1<<len(nums); i++ {
		var t int64 = 1
		for j, x := range nums {
			if i>>j&1 == 1 {
				t *= int64(x)
			}
		}
		ans = max(ans, t)
	}
	return ans
}
```

```ts
function maxStrength(nums: number[]): number {
    let ans = -Infinity;
    const n = nums.length;
    for (let i = 1; i < 1 << n; ++i) {
        let t = 1;
        for (let j = 0; j < n; ++j) {
            if ((i >> j) & 1) {
                t *= nums[j];
            }
        }
        ans = Math.max(ans, t);
    }
    return ans;
}
```

<!-- tabs:end -->

### Solution 2: Sorting + Greedy

First, we can sort the array. Based on the characteristics of the array, we can draw the following conclusions:

-   If there is only one element in the array, then the maximum strength value is this element.
-   If there are two or more elements in the array, and $nums[1] = nums[n - 1] = 0$, then the maximum strength value is $0$.
-   Otherwise, we traverse the array from small to large. If the current element is less than $0$ and the next element is also less than $0$, then we multiply these two elements and accumulate the product into the answer. Otherwise, if the current element is less than or equal to $0$, we skip it directly. If the current element is greater than $0$, we multiply this element into the answer. Finally, we return the answer.

The time complexity is $O(n \times \log n)$, and the space complexity is $O(\log n)$. Where $n$ is the length of the array.

<!-- tabs:start -->

```python
class Solution:
    def maxStrength(self, nums: List[int]) -> int:
        nums.sort()
        n = len(nums)
        if n == 1:
            return nums[0]
        if nums[1] == nums[-1] == 0:
            return 0
        ans, i = 1, 0
        while i < n:
            if nums[i] < 0 and i + 1 < n and nums[i + 1] < 0:
                ans *= nums[i] * nums[i + 1]
                i += 2
            elif nums[i] <= 0:
                i += 1
            else:
                ans *= nums[i]
                i += 1
        return ans
```

```java
class Solution {
    public long maxStrength(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        if (n == 1) {
            return nums[0];
        }
        if (nums[1] == 0 && nums[n - 1] == 0) {
            return 0;
        }
        long ans = 1;
        int i = 0;
        while (i < n) {
            if (nums[i] < 0 && i + 1 < n && nums[i + 1] < 0) {
                ans *= nums[i] * nums[i + 1];
                i += 2;
            } else if (nums[i] <= 0) {
                i += 1;
            } else {
                ans *= nums[i];
                i += 1;
            }
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    long long maxStrength(vector<int>& nums) {
        sort(nums.begin(), nums.end());
        int n = nums.size();
        if (n == 1) {
            return nums[0];
        }
        if (nums[1] == 0 && nums[n - 1] == 0) {
            return 0;
        }
        long long ans = 1;
        int i = 0;
        while (i < n) {
            if (nums[i] < 0 && i + 1 < n && nums[i + 1] < 0) {
                ans *= nums[i] * nums[i + 1];
                i += 2;
            } else if (nums[i] <= 0) {
                i += 1;
            } else {
                ans *= nums[i];
                i += 1;
            }
        }
        return ans;
    }
};
```

```go
func maxStrength(nums []int) int64 {
	sort.Ints(nums)
	n := len(nums)
	if n == 1 {
		return int64(nums[0])
	}
	if nums[1] == 0 && nums[n-1] == 0 {
		return 0
	}
	ans := int64(1)
	for i := 0; i < n; i++ {
		if nums[i] < 0 && i+1 < n && nums[i+1] < 0 {
			ans *= int64(nums[i] * nums[i+1])
			i++
		} else if nums[i] > 0 {
			ans *= int64(nums[i])
		}
	}
	return ans
}
```

```ts
function maxStrength(nums: number[]): number {
    nums.sort((a, b) => a - b);
    const n = nums.length;
    if (n === 1) {
        return nums[0];
    }
    if (nums[1] === 0 && nums[n - 1] === 0) {
        return 0;
    }
    let ans = 1;
    for (let i = 0; i < n; ++i) {
        if (nums[i] < 0 && i + 1 < n && nums[i + 1] < 0) {
            ans *= nums[i] * nums[i + 1];
            ++i;
        } else if (nums[i] > 0) {
            ans *= nums[i];
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- end -->
