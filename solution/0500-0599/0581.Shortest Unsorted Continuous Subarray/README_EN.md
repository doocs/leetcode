---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0500-0599/0581.Shortest%20Unsorted%20Continuous%20Subarray/README_EN.md
tags:
    - Stack
    - Greedy
    - Array
    - Two Pointers
    - Sorting
    - Monotonic Stack
---

<!-- problem:start -->

# [581. Shortest Unsorted Continuous Subarray](https://leetcode.com/problems/shortest-unsorted-continuous-subarray)

[中文文档](/solution/0500-0599/0581.Shortest%20Unsorted%20Continuous%20Subarray/README.md)

## Description

<!-- description:start -->

<p>Given an integer array <code>nums</code>, you need to find one <b>continuous subarray</b> such that if you only sort this subarray in non-decreasing order, then the whole array will be sorted in non-decreasing order.</p>

<p>Return <em>the shortest such subarray and output its length</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [2,6,4,8,10,9,15]
<strong>Output:</strong> 5
<strong>Explanation:</strong> You need to sort [6, 4, 8, 10, 9] in ascending order to make the whole array sorted in ascending order.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,3,4]
<strong>Output:</strong> 0
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [1]
<strong>Output:</strong> 0
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>4</sup></code></li>
	<li><code>-10<sup>5</sup> &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
</ul>

<p>&nbsp;</p>
<strong>Follow up:</strong> Can you solve it in <code>O(n)</code> time complexity?

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Sorting

We can first sort the array, and then compare the sorted array with the original array to find the leftmost and rightmost positions where they differ. The length between them is the length of the shortest unsorted continuous subarray.

The time complexity is $O(n \times \log n)$, and the space complexity is $O(n)$. Here, $n$ is the length of the array.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def findUnsortedSubarray(self, nums: List[int]) -> int:
        arr = sorted(nums)
        l, r = 0, len(nums) - 1
        while l <= r and nums[l] == arr[l]:
            l += 1
        while l <= r and nums[r] == arr[r]:
            r -= 1
        return r - l + 1
```

#### Java

```java
class Solution {
    public int findUnsortedSubarray(int[] nums) {
        int[] arr = nums.clone();
        Arrays.sort(arr);
        int l = 0, r = arr.length - 1;
        while (l <= r && nums[l] == arr[l]) {
            l++;
        }
        while (l <= r && nums[r] == arr[r]) {
            r--;
        }
        return r - l + 1;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int findUnsortedSubarray(vector<int>& nums) {
        vector<int> arr = nums;
        sort(arr.begin(), arr.end());
        int l = 0, r = arr.size() - 1;
        while (l <= r && arr[l] == nums[l]) {
            l++;
        }
        while (l <= r && arr[r] == nums[r]) {
            r--;
        }
        return r - l + 1;
    }
};
```

#### Go

```go
func findUnsortedSubarray(nums []int) int {
	arr := make([]int, len(nums))
	copy(arr, nums)
	sort.Ints(arr)
	l, r := 0, len(arr)-1
	for l <= r && nums[l] == arr[l] {
		l++
	}
	for l <= r && nums[r] == arr[r] {
		r--
	}
	return r - l + 1
}
```

#### TypeScript

```ts
function findUnsortedSubarray(nums: number[]): number {
    const arr = [...nums];
    arr.sort((a, b) => a - b);
    let [l, r] = [0, arr.length - 1];
    while (l <= r && arr[l] === nums[l]) {
        ++l;
    }
    while (l <= r && arr[r] === nums[r]) {
        --r;
    }
    return r - l + 1;
}
```

#### Rust

```rust
impl Solution {
    pub fn find_unsorted_subarray(nums: Vec<i32>) -> i32 {
        let mut arr = nums.clone();
        arr.sort();
        let mut l = 0usize;
        while l < nums.len() && nums[l] == arr[l] {
            l += 1;
        }
        if l == nums.len() {
            return 0;
        }
        let mut r = nums.len() - 1;
        while r > l && nums[r] == arr[r] {
            r -= 1;
        }
        (r - l + 1) as i32
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Solution 2: Maintaining the Maximum Value on the Left and the Minimum Value on the Right

We can traverse the array from left to right and maintain a maximum value $mx$. If the current value is less than $mx$, it means that the current value is not in the correct position, and we update the right boundary $r$ to the current position. Similarly, we can traverse the array from right to left and maintain a minimum value $mi$. If the current value is greater than $mi$, it means that the current value is not in the correct position, and we update the left boundary $l$ to the current position. At initialization, we set $l$ and $r$ to $-1$. If $l$ and $r$ are not updated, it means that the array is already sorted, and we return $0$. Otherwise, we return $r - l + 1$.

The time complexity is $O(n)$, and the space complexity is $O(1)$. Here, $n$ is the length of the array.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def findUnsortedSubarray(self, nums: List[int]) -> int:
        mi, mx = inf, -inf
        l = r = -1
        n = len(nums)
        for i, x in enumerate(nums):
            if mx > x:
                r = i
            else:
                mx = x
            if mi < nums[n - i - 1]:
                l = n - i - 1
            else:
                mi = nums[n - i - 1]
        return 0 if r == -1 else r - l + 1
```

#### Java

```java
class Solution {
    public int findUnsortedSubarray(int[] nums) {
        final int inf = 1 << 30;
        int n = nums.length;
        int l = -1, r = -1;
        int mi = inf, mx = -inf;
        for (int i = 0; i < n; ++i) {
            if (mx > nums[i]) {
                r = i;
            } else {
                mx = nums[i];
            }
            if (mi < nums[n - i - 1]) {
                l = n - i - 1;
            } else {
                mi = nums[n - i - 1];
            }
        }
        return r == -1 ? 0 : r - l + 1;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int findUnsortedSubarray(vector<int>& nums) {
        const int inf = 1e9;
        int n = nums.size();
        int l = -1, r = -1;
        int mi = inf, mx = -inf;
        for (int i = 0; i < n; ++i) {
            if (mx > nums[i]) {
                r = i;
            } else {
                mx = nums[i];
            }
            if (mi < nums[n - i - 1]) {
                l = n - i - 1;
            } else {
                mi = nums[n - i - 1];
            }
        }
        return r == -1 ? 0 : r - l + 1;
    }
};
```

#### Go

```go
func findUnsortedSubarray(nums []int) int {
	const inf = 1 << 30
	n := len(nums)
	l, r := -1, -1
	mi, mx := inf, -inf
	for i, x := range nums {
		if mx > x {
			r = i
		} else {
			mx = x
		}
		if mi < nums[n-i-1] {
			l = n - i - 1
		} else {
			mi = nums[n-i-1]
		}
	}
	if r == -1 {
		return 0
	}
	return r - l + 1
}
```

#### TypeScript

```ts
function findUnsortedSubarray(nums: number[]): number {
    let [l, r] = [-1, -1];
    let [mi, mx] = [Infinity, -Infinity];
    const n = nums.length;
    for (let i = 0; i < n; ++i) {
        if (mx > nums[i]) {
            r = i;
        } else {
            mx = nums[i];
        }
        if (mi < nums[n - i - 1]) {
            l = n - i - 1;
        } else {
            mi = nums[n - i - 1];
        }
    }
    return r === -1 ? 0 : r - l + 1;
}
```

#### Rust

```rust
impl Solution {
    pub fn find_unsorted_subarray(nums: Vec<i32>) -> i32 {
        let inf = 1 << 30;
        let n = nums.len();
        let mut l = -1;
        let mut r = -1;
        let mut mi = inf;
        let mut mx = -inf;

        for i in 0..n {
            if mx > nums[i] {
                r = i as i32;
            } else {
                mx = nums[i];
            }

            if mi < nums[n - i - 1] {
                l = (n - i - 1) as i32;
            } else {
                mi = nums[n - i - 1];
            }
        }

        if r == -1 {
            0
        } else {
            r - l + 1
        }
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
