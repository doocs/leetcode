---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1500-1599/1574.Shortest%20Subarray%20to%20be%20Removed%20to%20Make%20Array%20Sorted/README_EN.md
rating: 1931
source: Biweekly Contest 34 Q3
tags:
    - Stack
    - Array
    - Two Pointers
    - Binary Search
    - Monotonic Stack
---

<!-- problem:start -->

# [1574. Shortest Subarray to be Removed to Make Array Sorted](https://leetcode.com/problems/shortest-subarray-to-be-removed-to-make-array-sorted)

[中文文档](/solution/1500-1599/1574.Shortest%20Subarray%20to%20be%20Removed%20to%20Make%20Array%20Sorted/README.md)

## Description

<!-- description:start -->

<p>Given an integer array <code>arr</code>, remove a subarray (can be empty) from <code>arr</code> such that the remaining elements in <code>arr</code> are <strong>non-decreasing</strong>.</p>

<p>Return <em>the length of the shortest subarray to remove</em>.</p>

<p>A <strong>subarray</strong> is a contiguous subsequence of the array.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> arr = [1,2,3,10,4,2,3,5]
<strong>Output:</strong> 3
<strong>Explanation:</strong> The shortest subarray we can remove is [10,4,2] of length 3. The remaining elements after that will be [1,2,3,3,5] which are sorted.
Another correct solution is to remove the subarray [3,10,4].
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> arr = [5,4,3,2,1]
<strong>Output:</strong> 4
<strong>Explanation:</strong> Since the array is strictly decreasing, we can only keep a single element. Therefore we need to remove a subarray of length 4, either [5,4,3,2] or [4,3,2,1].
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> arr = [1,2,3]
<strong>Output:</strong> 0
<strong>Explanation:</strong> The array is already non-decreasing. We do not need to remove any elements.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= arr.length &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= arr[i] &lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Two Pointers + Binary Search

First, we find the longest non-decreasing prefix and the longest non-decreasing suffix of the array, denoted as $\textit{nums}[0..i]$ and $\textit{nums}[j..n-1]$, respectively.

If $i \geq j$, it means the array is already non-decreasing, so we return $0$.

Otherwise, we can choose to delete the right suffix or the left prefix. Therefore, initially, the answer is $\min(n - i - 1, j)$.

Next, we enumerate the right endpoint $l$ of the left prefix. For each $l$, we can use binary search to find the first position greater than or equal to $\textit{nums}[l]$ in $\textit{nums}[j..n-1]$, denoted as $r$. At this point, we can delete $\textit{nums}[l+1..r-1]$ and update the answer $\textit{ans} = \min(\textit{ans}, r - l - 1)$. Continue enumerating $l$ to get the final answer.

The time complexity is $O(n \times \log n)$, where $n$ is the length of the array. The space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def findLengthOfShortestSubarray(self, arr: List[int]) -> int:
        n = len(arr)
        i, j = 0, n - 1
        while i + 1 < n and arr[i] <= arr[i + 1]:
            i += 1
        while j - 1 >= 0 and arr[j - 1] <= arr[j]:
            j -= 1
        if i >= j:
            return 0
        ans = min(n - i - 1, j)
        for l in range(i + 1):
            r = bisect_left(arr, arr[l], lo=j)
            ans = min(ans, r - l - 1)
        return ans
```

#### Java

```java
class Solution {
    public int findLengthOfShortestSubarray(int[] arr) {
        int n = arr.length;
        int i = 0, j = n - 1;
        while (i + 1 < n && arr[i] <= arr[i + 1]) {
            ++i;
        }
        while (j - 1 >= 0 && arr[j - 1] <= arr[j]) {
            --j;
        }
        if (i >= j) {
            return 0;
        }
        int ans = Math.min(n - i - 1, j);
        for (int l = 0; l <= i; ++l) {
            int r = search(arr, arr[l], j);
            ans = Math.min(ans, r - l - 1);
        }
        return ans;
    }

    private int search(int[] arr, int x, int left) {
        int right = arr.length;
        while (left < right) {
            int mid = (left + right) >> 1;
            if (arr[mid] >= x) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int findLengthOfShortestSubarray(vector<int>& arr) {
        int n = arr.size();
        int i = 0, j = n - 1;
        while (i + 1 < n && arr[i] <= arr[i + 1]) {
            ++i;
        }
        while (j - 1 >= 0 && arr[j - 1] <= arr[j]) {
            --j;
        }
        if (i >= j) {
            return 0;
        }
        int ans = min(n - 1 - i, j);
        for (int l = 0; l <= i; ++l) {
            int r = lower_bound(arr.begin() + j, arr.end(), arr[l]) - arr.begin();
            ans = min(ans, r - l - 1);
        }
        return ans;
    }
};
```

#### Go

```go
func findLengthOfShortestSubarray(arr []int) int {
	n := len(arr)
	i, j := 0, n-1
	for i+1 < n && arr[i] <= arr[i+1] {
		i++
	}
	for j-1 >= 0 && arr[j-1] <= arr[j] {
		j--
	}
	if i >= j {
		return 0
	}
	ans := min(n-i-1, j)
	for l := 0; l <= i; l++ {
		r := j + sort.SearchInts(arr[j:], arr[l])
		ans = min(ans, r-l-1)
	}
	return ans
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Solution 2: Two Pointers

Similar to Solution 1, we first find the longest non-decreasing prefix and the longest non-decreasing suffix of the array, denoted as $\textit{nums}[0..i]$ and $\textit{nums}[j..n-1]$, respectively.

If $i \geq j$, it means the array is already non-decreasing, so we return $0$.

Otherwise, we can choose to delete the right suffix or the left prefix. Therefore, initially, the answer is $\min(n - i - 1, j)$.

Next, we enumerate the right endpoint $l$ of the left prefix. For each $l$, we directly use two pointers to find the first position greater than or equal to $\textit{nums}[l]$ in $\textit{nums}[j..n-1]$, denoted as $r$. At this point, we can delete $\textit{nums}[l+1..r-1]$ and update the answer $\textit{ans} = \min(\textit{ans}, r - l - 1)$. Continue enumerating $l$ to get the final answer.

The time complexity is $O(n)$, where $n$ is the length of the array. The space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def findLengthOfShortestSubarray(self, arr: List[int]) -> int:
        n = len(arr)
        i, j = 0, n - 1
        while i + 1 < n and arr[i] <= arr[i + 1]:
            i += 1
        while j - 1 >= 0 and arr[j - 1] <= arr[j]:
            j -= 1
        if i >= j:
            return 0
        ans = min(n - i - 1, j)
        r = j
        for l in range(i + 1):
            while r < n and arr[r] < arr[l]:
                r += 1
            ans = min(ans, r - l - 1)
        return ans
```

#### Java

```java
class Solution {
    public int findLengthOfShortestSubarray(int[] arr) {
        int n = arr.length;
        int i = 0, j = n - 1;
        while (i + 1 < n && arr[i] <= arr[i + 1]) {
            ++i;
        }
        while (j - 1 >= 0 && arr[j - 1] <= arr[j]) {
            --j;
        }
        if (i >= j) {
            return 0;
        }
        int ans = Math.min(n - i - 1, j);
        for (int l = 0, r = j; l <= i; ++l) {
            while (r < n && arr[r] < arr[l]) {
                ++r;
            }
            ans = Math.min(ans, r - l - 1);
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int findLengthOfShortestSubarray(vector<int>& arr) {
        int n = arr.size();
        int i = 0, j = n - 1;
        while (i + 1 < n && arr[i] <= arr[i + 1]) {
            ++i;
        }
        while (j - 1 >= 0 && arr[j - 1] <= arr[j]) {
            --j;
        }
        if (i >= j) {
            return 0;
        }
        int ans = min(n - 1 - i, j);
        for (int l = 0, r = j; l <= i; ++l) {
            while (r < n && arr[r] < arr[l]) {
                ++r;
            }
            ans = min(ans, r - l - 1);
        }
        return ans;
    }
};
```

#### Go

```go
func findLengthOfShortestSubarray(arr []int) int {
	n := len(arr)
	i, j := 0, n-1
	for i+1 < n && arr[i] <= arr[i+1] {
		i++
	}
	for j-1 >= 0 && arr[j-1] <= arr[j] {
		j--
	}
	if i >= j {
		return 0
	}
	ans := min(n-i-1, j)
	r := j
	for l := 0; l <= i; l++ {
		for r < n && arr[r] < arr[l] {
			r += 1
		}
		ans = min(ans, r-l-1)
	}
	return ans
}
```

#### TypeScript

```ts
function findLengthOfShortestSubarray(arr: number[]): number {
    let [l, r, n] = [0, arr.length - 1, arr.length];

    while (r && arr[r - 1] <= arr[r]) r--;
    if (r === 0) return 0;

    let ans = r;
    while (l < r && (!l || arr[l - 1] <= arr[l])) {
        while (r < n && arr[l] > arr[r]) r++;
        ans = Math.min(ans, r - l - 1);
        l++;
    }

    return ans;
}
```

#### JavaScript

```js
function findLengthOfShortestSubarray(arr) {
    let [l, r, n] = [0, arr.length - 1, arr.length];

    while (r && arr[r - 1] <= arr[r]) r--;
    if (r === 0) return 0;

    let ans = r;
    while (l < r && (!l || arr[l - 1] <= arr[l])) {
        while (r < n && arr[l] > arr[r]) r++;
        ans = Math.min(ans, r - l - 1);
        l++;
    }

    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
