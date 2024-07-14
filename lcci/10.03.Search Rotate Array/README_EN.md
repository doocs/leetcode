---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/lcci/10.03.Search%20Rotate%20Array/README_EN.md
---

<!-- problem:start -->

# [10.03. Search Rotate Array](https://leetcode.cn/problems/search-rotate-array-lcci)

[中文文档](/lcci/10.03.Search%20Rotate%20Array/README.md)

## Description

<!-- description:start -->

<p>Given a sorted array of n integers that has been rotated an unknown number of times, write code to find an element in the array. You may assume that the array was originally sorted in increasing order. If there are more than one target elements in the array, return the smallest index.</p>
<p><strong>Example1:</strong></p>
<pre>

<strong> Input</strong>: arr = [15, 16, 19, 20, 25, 1, 3, 4, 5, 7, 10, 14], target = 5

<strong> Output</strong>: 8 (the index of 5 in the array)

</pre>
<p><strong>Example2:</strong></p>
<pre>

<strong> Input</strong>: arr = [15, 16, 19, 20, 25, 1, 3, 4, 5, 7, 10, 14], target = 11

<strong> Output</strong>: -1 (not found)

</pre>
<p><strong>Note:</strong></p>
<ol>
	<li><code>1 &lt;= arr.length &lt;= 1000000</code></li>
</ol>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Binary Search

We define the left boundary of the binary search as $l=0$ and the right boundary as $r=n-1$, where $n$ is the length of the array.

In each binary search process, we get the current midpoint $mid=(l+r)/2$.

-   If $nums[mid] > nums[r]$, it means that $[l,mid]$ is ordered. If $nums[l] \leq target \leq nums[mid]$, it means that $target$ is in $[l,mid]$, otherwise $target$ is in $[mid+1,r]$.
-   If $nums[mid] < nums[r]$, it means that $[mid+1,r]$ is ordered. If $nums[mid] < target \leq nums[r]$, it means that $target$ is in $[mid+1,r]$, otherwise $target$ is in $[l,mid]$.
-   If $nums[mid] = nums[r]$, it means that the elements $nums[mid]$ and $nums[r]$ are equal. At this time, we cannot determine which interval $target$ is in, we can only decrease $r$ by $1$.

After the binary search ends, if $nums[l] = target$, it means that the target value $target$ exists in the array, otherwise it does not exist.

Note that if initially $nums[l] = nums[r]$, we loop to decrease $r$ by $1$ until $nums[l] \neq nums[r]$.

The time complexity is approximately $O(\log n)$, and the space complexity is $O(1)$. Here, $n$ is the length of the array.

Similar problems:

-   [81. Search in Rotated Sorted Array II](https://github.com/doocs/leetcode/blob/main/solution/0000-0099/0081.Search%20in%20Rotated%20Sorted%20Array%20II/README.md)

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def search(self, arr: List[int], target: int) -> int:
        l, r = 0, len(arr) - 1
        while arr[l] == arr[r]:
            r -= 1
        while l < r:
            mid = (l + r) >> 1
            if arr[mid] > arr[r]:
                if arr[l] <= target <= arr[mid]:
                    r = mid
                else:
                    l = mid + 1
            elif arr[mid] < arr[r]:
                if arr[mid] < target <= arr[r]:
                    l = mid + 1
                else:
                    r = mid
            else:
                r -= 1
        return l if arr[l] == target else -1
```

#### Java

```java
class Solution {
    public int search(int[] arr, int target) {
        int l = 0, r = arr.length - 1;
        while (arr[l] == arr[r]) {
            --r;
        }
        while (l < r) {
            int mid = (l + r) >> 1;
            if (arr[mid] > arr[r]) {
                if (arr[l] <= target && target <= arr[mid]) {
                    r = mid;
                } else {
                    l = mid + 1;
                }
            } else if (arr[mid] < arr[r]) {
                if (arr[mid] < target && target <= arr[r]) {
                    l = mid + 1;
                } else {
                    r = mid;
                }
            } else {
                --r;
            }
        }
        return arr[l] == target ? l : -1;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int search(vector<int>& arr, int target) {
        int l = 0, r = arr.size() - 1;
        while (arr[l] == arr[r]) {
            --r;
        }
        while (l < r) {
            int mid = (l + r) >> 1;
            if (arr[mid] > arr[r]) {
                if (arr[l] <= target && target <= arr[mid]) {
                    r = mid;
                } else {
                    l = mid + 1;
                }
            } else if (arr[mid] < arr[r]) {
                if (arr[mid] < target && target <= arr[r]) {
                    l = mid + 1;
                } else {
                    r = mid;
                }
            } else {
                --r;
            }
        }
        return arr[l] == target ? l : -1;
    }
};
```

#### Go

```go
func search(arr []int, target int) int {
	l, r := 0, len(arr)-1
	for arr[l] == arr[r] {
		r--
	}
	for l < r {
		mid := (l + r) >> 1
		if arr[mid] > arr[r] {
			if arr[l] <= target && target <= arr[mid] {
				r = mid
			} else {
				l = mid + 1
			}
		} else if arr[mid] < arr[r] {
			if arr[mid] < target && target <= arr[r] {
				l = mid + 1
			} else {
				r = mid
			}
		} else {
			r--
		}
	}
	if arr[l] == target {
		return l
	}
	return -1
}
```

#### TypeScript

```ts
function search(arr: number[], target: number): number {
    let [l, r] = [0, arr.length - 1];
    while (arr[l] === arr[r]) {
        --r;
    }
    while (l < r) {
        const mid = (l + r) >> 1;
        if (arr[mid] > arr[r]) {
            if (arr[l] <= target && target <= arr[mid]) {
                r = mid;
            } else {
                l = mid + 1;
            }
        } else if (arr[mid] < arr[r]) {
            if (arr[mid] < target && target <= arr[r]) {
                l = mid + 1;
            } else {
                r = mid;
            }
        } else {
            --r;
        }
    }
    return arr[l] === target ? l : -1;
}
```

#### Swift

```swift
class Solution {
    func search(_ arr: [Int], _ target: Int) -> Int {
        var l = 0
        var r = arr.count - 1

        while arr[l] == arr[r] && l < r {
            r -= 1
        }

        while l < r {
            let mid = (l + r) >> 1
            if arr[mid] > arr[r] {
                if arr[l] <= target && target <= arr[mid] {
                    r = mid
                } else {
                    l = mid + 1
                }
            } else if arr[mid] < arr[r] {
                if arr[mid] < target && target <= arr[r] {
                    l = mid + 1
                } else {
                    r = mid
                }
            } else {
                r -= 1
            }
        }

        return arr[l] == target ? l : -1
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
