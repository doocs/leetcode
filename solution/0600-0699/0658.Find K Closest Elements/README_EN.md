---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0600-0699/0658.Find%20K%20Closest%20Elements/README_EN.md
tags:
    - Array
    - Two Pointers
    - Binary Search
    - Sorting
    - Sliding Window
    - Heap (Priority Queue)
---

<!-- problem:start -->

# [658. Find K Closest Elements](https://leetcode.com/problems/find-k-closest-elements)

[中文文档](/solution/0600-0699/0658.Find%20K%20Closest%20Elements/README.md)

## Description

<p>Given a <strong>sorted</strong> integer array <code>arr</code>, two integers <code>k</code> and <code>x</code>, return the <code>k</code> closest integers to <code>x</code> in the array. The result should also be sorted in ascending order.</p>

<p>An integer <code>a</code> is closer to <code>x</code> than an integer <code>b</code> if:</p>

<ul>
	<li><code>|a - x| &lt; |b - x|</code>, or</li>
	<li><code>|a - x| == |b - x|</code> and <code>a &lt; b</code></li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<pre><strong>Input:</strong> arr = [1,2,3,4,5], k = 4, x = 3
<strong>Output:</strong> [1,2,3,4]
</pre><p><strong class="example">Example 2:</strong></p>
<pre><strong>Input:</strong> arr = [1,2,3,4,5], k = 4, x = -1
<strong>Output:</strong> [1,2,3,4]
</pre>
<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= k &lt;= arr.length</code></li>
	<li><code>1 &lt;= arr.length &lt;= 10<sup>4</sup></code></li>
	<li><code>arr</code> is sorted in <strong>ascending</strong> order.</li>
	<li><code>-10<sup>4</sup> &lt;= arr[i], x &lt;= 10<sup>4</sup></code></li>
</ul>

## Solutions

<!-- solution:start -->

### Solution 1: Sort

<!-- tabs:start -->

```python
class Solution:
    def findClosestElements(self, arr: List[int], k: int, x: int) -> List[int]:
        arr.sort(key=lambda v: abs(v - x))
        return sorted(arr[:k])
```

```java
class Solution {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        List<Integer> ans = Arrays.stream(arr)
                                .boxed()
                                .sorted((a, b) -> {
                                    int v = Math.abs(a - x) - Math.abs(b - x);
                                    return v == 0 ? a - b : v;
                                })
                                .collect(Collectors.toList());
        ans = ans.subList(0, k);
        Collections.sort(ans);
        return ans;
    }
}
```

```cpp
int target;

class Solution {
public:
    static bool cmp(int& a, int& b) {
        int v = abs(a - target) - abs(b - target);
        return v == 0 ? a < b : v < 0;
    }

    vector<int> findClosestElements(vector<int>& arr, int k, int x) {
        target = x;
        sort(arr.begin(), arr.end(), cmp);
        vector<int> ans(arr.begin(), arr.begin() + k);
        sort(ans.begin(), ans.end());
        return ans;
    }
};
```

```go
func findClosestElements(arr []int, k int, x int) []int {
	sort.Slice(arr, func(i, j int) bool {
		v := abs(arr[i]-x) - abs(arr[j]-x)
		if v == 0 {
			return arr[i] < arr[j]
		}
		return v < 0
	})
	ans := arr[:k]
	sort.Ints(ans)
	return ans
}

func abs(x int) int {
	if x >= 0 {
		return x
	}
	return -x
}
```

```ts
function findClosestElements(arr: number[], k: number, x: number): number[] {
    let l = 0;
    let r = arr.length;
    while (r - l > k) {
        if (x - arr[l] <= arr[r - 1] - x) {
            --r;
        } else {
            ++l;
        }
    }
    return arr.slice(l, r);
}
```

```rust
impl Solution {
    pub fn find_closest_elements(arr: Vec<i32>, k: i32, x: i32) -> Vec<i32> {
        let n = arr.len();
        let mut l = 0;
        let mut r = n;
        while r - l != (k as usize) {
            if x - arr[l] <= arr[r - 1] - x {
                r -= 1;
            } else {
                l += 1;
            }
        }
        arr[l..r].to_vec()
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Solution 2: Binary search

<!-- tabs:start -->

```python
class Solution:
    def findClosestElements(self, arr: List[int], k: int, x: int) -> List[int]:
        l, r = 0, len(arr)
        while r - l > k:
            if x - arr[l] <= arr[r - 1] - x:
                r -= 1
            else:
                l += 1
        return arr[l:r]
```

```java
class Solution {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        int l = 0, r = arr.length;
        while (r - l > k) {
            if (x - arr[l] <= arr[r - 1] - x) {
                --r;
            } else {
                ++l;
            }
        }
        List<Integer> ans = new ArrayList<>();
        for (int i = l; i < r; ++i) {
            ans.add(arr[i]);
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    vector<int> findClosestElements(vector<int>& arr, int k, int x) {
        int l = 0, r = arr.size();
        while (r - l > k) {
            if (x - arr[l] <= arr[r - 1] - x) {
                --r;
            } else {
                ++l;
            }
        }
        return vector<int>(arr.begin() + l, arr.begin() + r);
    }
};
```

```go
func findClosestElements(arr []int, k int, x int) []int {
	l, r := 0, len(arr)
	for r-l > k {
		if x-arr[l] <= arr[r-1]-x {
			r--
		} else {
			l++
		}
	}
	return arr[l:r]
}
```

```ts
function findClosestElements(arr: number[], k: number, x: number): number[] {
    let left = 0;
    let right = arr.length - k;
    while (left < right) {
        const mid = (left + right) >> 1;
        if (x - arr[mid] <= arr[mid + k] - x) {
            right = mid;
        } else {
            left = mid + 1;
        }
    }
    return arr.slice(left, left + k);
}
```

```rust
impl Solution {
    pub fn find_closest_elements(arr: Vec<i32>, k: i32, x: i32) -> Vec<i32> {
        let k = k as usize;
        let n = arr.len();
        let mut left = 0;
        let mut right = n - k;
        while left < right {
            let mid = left + (right - left) / 2;
            if x - arr[mid] > arr[mid + k] - x {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        arr[left..left + k].to_vec()
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Solution 3

<!-- tabs:start -->

```python
class Solution:
    def findClosestElements(self, arr: List[int], k: int, x: int) -> List[int]:
        left, right = 0, len(arr) - k
        while left < right:
            mid = (left + right) >> 1
            if x - arr[mid] <= arr[mid + k] - x:
                right = mid
            else:
                left = mid + 1
        return arr[left : left + k]
```

```java
class Solution {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        int left = 0;
        int right = arr.length - k;
        while (left < right) {
            int mid = (left + right) >> 1;
            if (x - arr[mid] <= arr[mid + k] - x) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        List<Integer> ans = new ArrayList<>();
        for (int i = left; i < left + k; ++i) {
            ans.add(arr[i]);
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    vector<int> findClosestElements(vector<int>& arr, int k, int x) {
        int left = 0, right = arr.size() - k;
        while (left < right) {
            int mid = (left + right) >> 1;
            if (x - arr[mid] <= arr[mid + k] - x)
                right = mid;
            else
                left = mid + 1;
        }
        return vector<int>(arr.begin() + left, arr.begin() + left + k);
    }
};
```

```go
func findClosestElements(arr []int, k int, x int) []int {
	left, right := 0, len(arr)-k
	for left < right {
		mid := (left + right) >> 1
		if x-arr[mid] <= arr[mid+k]-x {
			right = mid
		} else {
			left = mid + 1
		}
	}
	return arr[left : left+k]
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
