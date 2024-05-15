---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/lcci/08.03.Magic%20Index/README_EN.md
---

# [08.03. Magic Index](https://leetcode.cn/problems/magic-index-lcci)

[中文文档](/lcci/08.03.Magic%20Index/README.md)

## Description

<p>A magic index in an array <code>A[0...n-1]</code> is defined to be an index such that <code>A[i] = i</code>. Given a sorted array of distinct integers, write a method to find a magic index, if one exists, in array A. If not, return -1. If there are more than one magic index, return the smallest one.</p>

<p><strong>Example1:</strong></p>

<pre>

<strong> Input</strong>: nums = [0, 2, 3, 4, 5]

<strong> Output</strong>: 0

</pre>

<p><strong>Example2:</strong></p>

<pre>

<strong> Input</strong>: nums = [1, 1, 1]

<strong> Output</strong>: 1

</pre>

<p><strong>Note:</strong></p>

<ol>
	<li><code>1 &lt;= nums.length &lt;= 1000000</code></li>
</ol>

## Solutions

### Solution 1: Binary Search

We design a function $dfs(i, j)$ to find the magic index in the array $nums[i, j]$. If found, return the value of the magic index, otherwise return $-1$. So the answer is $dfs(0, n-1)$.

The implementation of the function $dfs(i, j)$ is as follows:

1. If $i > j$, return $-1$.
2. Otherwise, we take the middle position $mid = (i + j) / 2$, then recursively call $dfs(i, mid-1)$. If the return value is not $-1$, it means that the magic index is found in the left half, return it directly. Otherwise, if $nums[mid] = mid$, it means that the magic index is found, return it directly. Otherwise, recursively call $dfs(mid+1, j)$ and return.

In the worst case, the time complexity is $O(n)$, and the space complexity is $O(n)$. Where $n$ is the length of the array $nums$.

<!-- tabs:start -->

```python
class Solution:
    def findMagicIndex(self, nums: List[int]) -> int:
        def dfs(i: int, j: int) -> int:
            if i > j:
                return -1
            mid = (i + j) >> 1
            l = dfs(i, mid - 1)
            if l != -1:
                return l
            if nums[mid] == mid:
                return mid
            return dfs(mid + 1, j)

        return dfs(0, len(nums) - 1)
```

```java
class Solution {
    public int findMagicIndex(int[] nums) {
        return dfs(nums, 0, nums.length - 1);
    }

    private int dfs(int[] nums, int i, int j) {
        if (i > j) {
            return -1;
        }
        int mid = (i + j) >> 1;
        int l = dfs(nums, i, mid - 1);
        if (l != -1) {
            return l;
        }
        if (nums[mid] == mid) {
            return mid;
        }
        return dfs(nums, mid + 1, j);
    }
}
```

```cpp
class Solution {
public:
    int findMagicIndex(vector<int>& nums) {
        function<int(int, int)> dfs = [&](int i, int j) {
            if (i > j) {
                return -1;
            }
            int mid = (i + j) >> 1;
            int l = dfs(i, mid - 1);
            if (l != -1) {
                return l;
            }
            if (nums[mid] == mid) {
                return mid;
            }
            return dfs(mid + 1, j);
        };
        return dfs(0, nums.size() - 1);
    }
};
```

```go
func findMagicIndex(nums []int) int {
	var dfs func(i, j int) int
	dfs = func(i, j int) int {
		if i > j {
			return -1
		}
		mid := (i + j) >> 1
		if l := dfs(i, mid-1); l != -1 {
			return l
		}
		if nums[mid] == mid {
			return mid
		}
		return dfs(mid+1, j)
	}
	return dfs(0, len(nums)-1)
}
```

```ts
function findMagicIndex(nums: number[]): number {
    const dfs = (i: number, j: number): number => {
        if (i > j) {
            return -1;
        }
        const mid = (i + j) >> 1;
        const l = dfs(i, mid - 1);
        if (l !== -1) {
            return l;
        }
        if (nums[mid] === mid) {
            return mid;
        }
        return dfs(mid + 1, j);
    };
    return dfs(0, nums.length - 1);
}
```

```rust
impl Solution {
    fn dfs(nums: &Vec<i32>, i: usize, j: usize) -> i32 {
        if i >= j || nums[j - 1] < 0 {
            return -1;
        }
        let mid = (i + j) >> 1;
        if nums[mid] >= (i as i32) {
            let l = Self::dfs(nums, i, mid);
            if l != -1 {
                return l;
            }
        }
        if nums[mid] == (mid as i32) {
            return mid as i32;
        }
        Self::dfs(nums, mid + 1, j)
    }

    pub fn find_magic_index(nums: Vec<i32>) -> i32 {
        Self::dfs(&nums, 0, nums.len())
    }
}
```

```js
/**
 * @param {number[]} nums
 * @return {number}
 */
var findMagicIndex = function (nums) {
    const dfs = (i, j) => {
        if (i > j) {
            return -1;
        }
        const mid = (i + j) >> 1;
        const l = dfs(i, mid - 1);
        if (l !== -1) {
            return l;
        }
        if (nums[mid] === mid) {
            return mid;
        }
        return dfs(mid + 1, j);
    };
    return dfs(0, nums.length - 1);
};
```

```swift
class Solution {
    func findMagicIndex(_ nums: [Int]) -> Int {
        return find(nums, 0, nums.count - 1)
    }

    private func find(_ nums: [Int], _ i: Int, _ j: Int) -> Int {
        if i > j {
            return -1
        }
        let mid = (i + j) >> 1
        let l = find(nums, i, mid - 1)
        if l != -1 {
            return l
        }
        if nums[mid] == mid {
            return mid
        }
        return find(nums, mid + 1, j)
    }
}
```

<!-- tabs:end -->

<!-- end -->
