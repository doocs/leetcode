---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2500-2599/2529.Maximum%20Count%20of%20Positive%20Integer%20and%20Negative%20Integer/README_EN.md
rating: 1195
tags:
    - Array
    - Binary Search
    - Counting
---

# [2529. Maximum Count of Positive Integer and Negative Integer](https://leetcode.com/problems/maximum-count-of-positive-integer-and-negative-integer)

[中文文档](/solution/2500-2599/2529.Maximum%20Count%20of%20Positive%20Integer%20and%20Negative%20Integer/README.md)

## Description

<p>Given an array <code>nums</code> sorted in <strong>non-decreasing</strong> order, return <em>the maximum between the number of positive integers and the number of negative integers.</em></p>

<ul>
	<li>In other words, if the number of positive integers in <code>nums</code> is <code>pos</code> and the number of negative integers is <code>neg</code>, then return the maximum of <code>pos</code> and <code>neg</code>.</li>
</ul>

<p><strong>Note</strong> that <code>0</code> is neither positive nor negative.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [-2,-1,-1,1,2,3]
<strong>Output:</strong> 3
<strong>Explanation:</strong> There are 3 positive integers and 3 negative integers. The maximum count among them is 3.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [-3,-2,-1,0,0,1,2]
<strong>Output:</strong> 3
<strong>Explanation:</strong> There are 2 positive integers and 3 negative integers. The maximum count among them is 3.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [5,20,66,1314]
<strong>Output:</strong> 4
<strong>Explanation:</strong> There are 4 positive integers and 0 negative integers. The maximum count among them is 4.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 2000</code></li>
	<li><code>-2000 &lt;= nums[i] &lt;= 2000</code></li>
	<li><code>nums</code> is sorted in a <strong>non-decreasing order</strong>.</li>
</ul>

<p>&nbsp;</p>
<p><strong>Follow up:</strong> Can you solve the problem in <code>O(log(n))</code> time complexity?</p>

## Solutions

### Solution 1: Traversal

We can directly traverse the array, count the number of positive and negative integers $a$ and $b$, and return the larger of $a$ and $b$.

The time complexity is $O(n)$, where $n$ is the length of the array. The space complexity is $O(1)$.

<!-- tabs:start -->

```python
class Solution:
    def maximumCount(self, nums: List[int]) -> int:
        a = sum(x > 0 for x in nums)
        b = sum(x < 0 for x in nums)
        return max(a, b)
```

```java
class Solution {
    public int maximumCount(int[] nums) {
        int a = 0, b = 0;
        for (int x : nums) {
            if (x > 0) {
                ++a;
            } else if (x < 0) {
                ++b;
            }
        }
        return Math.max(a, b);
    }
}
```

```cpp
class Solution {
public:
    int maximumCount(vector<int>& nums) {
        int a = 0, b = 0;
        for (int x : nums) {
            if (x > 0) {
                ++a;
            } else if (x < 0) {
                ++b;
            }
        }
        return max(a, b);
    }
};
```

```go
func maximumCount(nums []int) int {
	var a, b int
	for _, x := range nums {
		if x > 0 {
			a++
		} else if x < 0 {
			b++
		}
	}
	return max(a, b)
}
```

```ts
function maximumCount(nums: number[]): number {
    let [a, b] = [0, 0];
    for (const x of nums) {
        if (x > 0) {
            ++a;
        } else if (x < 0) {
            ++b;
        }
    }
    return Math.max(a, b);
}
```

```rust
impl Solution {
    pub fn maximum_count(nums: Vec<i32>) -> i32 {
        let mut a = 0;
        let mut b = 0;

        for x in nums {
            if x > 0 {
                a += 1;
            } else if x < 0 {
                b += 1;
            }
        }

        std::cmp::max(a, b)
    }
}
```

```c
#define max(a, b) (a > b ? a : b)

int maximumCount(int* nums, int numsSize) {
    int a = 0, b = 0;
    for (int i = 0; i < numsSize; ++i) {
        if (nums[i] > 0) {
            ++a;
        } else if (nums[i] < 0) {
            ++b;
        }
    }
    return max(a, b);
}
```

<!-- tabs:end -->

### Solution 2: Binary Search

Since the array is sorted in non-decreasing order, we can use binary search to find the index $i$ of the first element that is greater than or equal to $1$, and the index $j$ of the first element that is greater than or equal to $0$. The number of positive integers is $a = n - i$, and the number of negative integers is $b = j$. We return the larger of $a$ and $b$.

The time complexity is $O(\log n)$, where $n$ is the length of the array. The space complexity is $O(1)$.

<!-- tabs:start -->

```python
class Solution:
    def maximumCount(self, nums: List[int]) -> int:
        a = len(nums) - bisect_left(nums, 1)
        b = bisect_left(nums, 0)
        return max(a, b)
```

```java
class Solution {
    public int maximumCount(int[] nums) {
        int a = nums.length - search(nums, 1);
        int b = search(nums, 0);
        return Math.max(a, b);
    }

    private int search(int[] nums, int x) {
        int left = 0, right = nums.length;
        while (left < right) {
            int mid = (left + right) >> 1;
            if (nums[mid] >= x) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
}
```

```cpp
class Solution {
public:
    int maximumCount(vector<int>& nums) {
        int a = nums.end() - lower_bound(nums.begin(), nums.end(), 1);
        int b = lower_bound(nums.begin(), nums.end(), 0) - nums.begin();
        return max(a, b);
    }
};
```

```go
func maximumCount(nums []int) int {
	a := len(nums) - sort.SearchInts(nums, 1)
	b := sort.SearchInts(nums, 0)
	return max(a, b)
}
```

```ts
function maximumCount(nums: number[]): number {
    const search = (x: number): number => {
        let [left, right] = [0, nums.length];
        while (left < right) {
            const mid = (left + right) >> 1;
            if (nums[mid] >= x) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    };
    const i = search(1);
    const j = search(0);
    const [a, b] = [nums.length - i, j];
    return Math.max(a, b);
}
```

```rust
impl Solution {
    fn search(nums: &Vec<i32>, x: i32) -> usize {
        let mut left = 0;
        let mut right = nums.len();
        while left < right {
            let mid = (left + right) >> 1;
            if nums[mid] >= x {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        left
    }

    pub fn maximum_count(nums: Vec<i32>) -> i32 {
        let n = nums.len();
        let i = Self::search(&nums, 1);
        let j = Self::search(&nums, 0);
        (n - i).max(j) as i32
    }
}
```

```c
#define max(a, b) (a > b ? a : b)

int search(int* nums, int numsSize, int x) {
    int left = 0;
    int right = numsSize;
    while (left < right) {
        int mid = (left + right) >> 1;
        if (nums[mid] >= x) {
            right = mid;
        } else {
            left = mid + 1;
        }
    }
    return left;
}

int maximumCount(int* nums, int numsSize) {
    int i = search(nums, numsSize, 1);
    int j = search(nums, numsSize, 0);
    return max(numsSize - i, j);
}
```

<!-- tabs:end -->

<!-- end -->
