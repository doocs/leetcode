---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1100-1199/1150.Check%20If%20a%20Number%20Is%20Majority%20Element%20in%20a%20Sorted%20Array/README_EN.md
rating: 1249
source: Biweekly Contest 6 Q1
tags:
    - Array
    - Binary Search
---

# [1150. Check If a Number Is Majority Element in a Sorted Array 🔒](https://leetcode.com/problems/check-if-a-number-is-majority-element-in-a-sorted-array)

[中文文档](/solution/1100-1199/1150.Check%20If%20a%20Number%20Is%20Majority%20Element%20in%20a%20Sorted%20Array/README.md)

## Description

<p>Given an integer array <code>nums</code> sorted in non-decreasing order and an integer <code>target</code>, return <code>true</code> <em>if</em> <code>target</code> <em>is a <strong>majority</strong> element, or </em><code>false</code><em> otherwise</em>.</p>

<p>A <strong>majority</strong> element in an array <code>nums</code> is an element that appears more than <code>nums.length / 2</code> times in the array.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [2,4,5,5,5,5,5,6,6], target = 5
<strong>Output:</strong> true
<strong>Explanation:</strong> The value 5 appears 5 times and the length of the array is 9.
Thus, 5 is a majority element because 5 &gt; 9/2 is true.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [10,100,101,101], target = 101
<strong>Output:</strong> false
<strong>Explanation:</strong> The value 101 appears 2 times and the length of the array is 4.
Thus, 101 is not a majority element because 2 &gt; 4/2 is false.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 1000</code></li>
	<li><code>1 &lt;= nums[i], target &lt;= 10<sup>9</sup></code></li>
	<li><code>nums</code> is sorted in non-decreasing order.</li>
</ul>

## Solutions

### Solution 1: Binary Search

We notice that the elements in the array $nums$ are non-decreasing, that is, the elements in the array $nums$ are monotonically increasing. Therefore, we can use the method of binary search to find the index $left$ of the first element in the array $nums$ that is greater than or equal to $target$, and the index $right$ of the first element in the array $nums$ that is greater than $target$. If $right - left > \frac{n}{2}$, it means that the number of occurrences of the element $target$ in the array $nums$ exceeds half of the length of the array, so return $true$, otherwise return $false$.

The time complexity is $O(\log n)$, and the space complexity is $O(1)$. Here, $n$ is the length of the array $nums$.

<!-- tabs:start -->

```python
class Solution:
    def isMajorityElement(self, nums: List[int], target: int) -> bool:
        left = bisect_left(nums, target)
        right = bisect_right(nums, target)
        return right - left > len(nums) // 2
```

```java
class Solution {
    public boolean isMajorityElement(int[] nums, int target) {
        int left = search(nums, target);
        int right = search(nums, target + 1);
        return right - left > nums.length / 2;
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
    bool isMajorityElement(vector<int>& nums, int target) {
        auto left = lower_bound(nums.begin(), nums.end(), target);
        auto right = upper_bound(nums.begin(), nums.end(), target);
        return right - left > nums.size() / 2;
    }
};
```

```go
func isMajorityElement(nums []int, target int) bool {
	left := sort.SearchInts(nums, target)
	right := sort.SearchInts(nums, target+1)
	return right-left > len(nums)/2
}
```

```ts
function isMajorityElement(nums: number[], target: number): boolean {
    const search = (x: number) => {
        let left = 0;
        let right = nums.length;
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
    const left = search(target);
    const right = search(target + 1);
    return right - left > nums.length >> 1;
}
```

<!-- tabs:end -->

### Solution 2: Binary Search (Optimized)

In Solution 1, we used binary search twice to find the index $left$ of the first element in the array $nums$ that is greater than or equal to $target$, and the index $right$ of the first element in the array $nums$ that is greater than $target$. However, we can use binary search once to find the index $left$ of the first element in the array $nums$ that is greater than or equal to $target$, and then judge whether $nums[left + \frac{n}{2}]$ is equal to $target$. If they are equal, it means that the number of occurrences of the element $target$ in the array $nums$ exceeds half of the length of the array, so return $true$, otherwise return $false$.

The time complexity is $O(\log n)$, and the space complexity is $O(1)$. Here, $n$ is the length of the array $nums$.

<!-- tabs:start -->

```python
class Solution:
    def isMajorityElement(self, nums: List[int], target: int) -> bool:
        left = bisect_left(nums, target)
        right = left + len(nums) // 2
        return right < len(nums) and nums[right] == target
```

```java
class Solution {
    public boolean isMajorityElement(int[] nums, int target) {
        int n = nums.length;
        int left = search(nums, target);
        int right = left + n / 2;
        return right < n && nums[right] == target;
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
    bool isMajorityElement(vector<int>& nums, int target) {
        int n = nums.size();
        int left = lower_bound(nums.begin(), nums.end(), target) - nums.begin();
        int right = left + n / 2;
        return right < n && nums[right] == target;
    }
};
```

```go
func isMajorityElement(nums []int, target int) bool {
	n := len(nums)
	left := sort.SearchInts(nums, target)
	right := left + n/2
	return right < n && nums[right] == target
}
```

```ts
function isMajorityElement(nums: number[], target: number): boolean {
    const search = (x: number) => {
        let left = 0;
        let right = n;
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
    const n = nums.length;
    const left = search(target);
    const right = left + (n >> 1);
    return right < n && nums[right] === target;
}
```

<!-- tabs:end -->

<!-- end -->
