---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1000-1099/1095.Find%20in%20Mountain%20Array/README_EN.md
rating: 1827
source: Weekly Contest 142 Q3
tags:
    - Array
    - Binary Search
    - Interactive
---

<!-- problem:start -->

# [1095. Find in Mountain Array](https://leetcode.com/problems/find-in-mountain-array)

[中文文档](/solution/1000-1099/1095.Find%20in%20Mountain%20Array/README.md)

## Description

<!-- description:start -->

<p><em>(This problem is an <strong>interactive problem</strong>.)</em></p>

<p>You may recall that an array <code>arr</code> is a <strong>mountain array</strong> if and only if:</p>

<ul>
	<li><code>arr.length &gt;= 3</code></li>
	<li>There exists some <code>i</code> with <code>0 &lt; i &lt; arr.length - 1</code> such that:
	<ul>
		<li><code>arr[0] &lt; arr[1] &lt; ... &lt; arr[i - 1] &lt; arr[i]</code></li>
		<li><code>arr[i] &gt; arr[i + 1] &gt; ... &gt; arr[arr.length - 1]</code></li>
	</ul>
	</li>
</ul>

<p>Given a mountain array <code>mountainArr</code>, return the <strong>minimum</strong> <code>index</code> such that <code>mountainArr.get(index) == target</code>. If such an <code>index</code> does not exist, return <code>-1</code>.</p>

<p><strong>You cannot access the mountain array directly.</strong> You may only access the array using a <code>MountainArray</code> interface:</p>

<ul>
	<li><code>MountainArray.get(k)</code> returns the element of the array at index <code>k</code> (0-indexed).</li>
	<li><code>MountainArray.length()</code> returns the length of the array.</li>
</ul>

<p>Submissions making more than <code>100</code> calls to <code>MountainArray.get</code> will be judged <em>Wrong Answer</em>. Also, any solutions that attempt to circumvent the judge will result in disqualification.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> array = [1,2,3,4,5,3,1], target = 3
<strong>Output:</strong> 2
<strong>Explanation:</strong> 3 exists in the array, at index=2 and index=5. Return the minimum index, which is 2.</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> array = [0,1,2,4,2,1], target = 3
<strong>Output:</strong> -1
<strong>Explanation:</strong> 3 does not exist in <code>the array,</code> so we return -1.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>3 &lt;= mountain_arr.length() &lt;= 10<sup>4</sup></code></li>
	<li><code>0 &lt;= target &lt;= 10<sup>9</sup></code></li>
	<li><code>0 &lt;= mountain_arr.get(index) &lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1

<!-- tabs:start -->

```python
# """
# This is MountainArray's API interface.
# You should not implement it, or speculate about its implementation
# """
# class MountainArray:
#    def get(self, index: int) -> int:
#    def length(self) -> int:


class Solution:
    def findInMountainArray(self, target: int, mountain_arr: 'MountainArray') -> int:
        def search(l: int, r: int, k: int) -> int:
            while l < r:
                mid = (l + r) >> 1
                if k * mountain_arr.get(mid) >= k * target:
                    r = mid
                else:
                    l = mid + 1
            return -1 if mountain_arr.get(l) != target else l

        n = mountain_arr.length()
        l, r = 0, n - 1
        while l < r:
            mid = (l + r) >> 1
            if mountain_arr.get(mid) > mountain_arr.get(mid + 1):
                r = mid
            else:
                l = mid + 1
        ans = search(0, l, 1)
        return search(l + 1, n - 1, -1) if ans == -1 else ans
```

```java
/**
 * // This is MountainArray's API interface.
 * // You should not implement it, or speculate about its implementation
 * interface MountainArray {
 *     public int get(int index) {}
 *     public int length() {}
 * }
 */

class Solution {
    private MountainArray mountainArr;
    private int target;

    public int findInMountainArray(int target, MountainArray mountainArr) {
        int n = mountainArr.length();
        int l = 0, r = n - 1;
        while (l < r) {
            int mid = (l + r) >>> 1;
            if (mountainArr.get(mid) > mountainArr.get(mid + 1)) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        this.mountainArr = mountainArr;
        this.target = target;
        int ans = search(0, l, 1);
        return ans == -1 ? search(l + 1, n - 1, -1) : ans;
    }

    private int search(int l, int r, int k) {
        while (l < r) {
            int mid = (l + r) >>> 1;
            if (k * mountainArr.get(mid) >= k * target) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return mountainArr.get(l) == target ? l : -1;
    }
}
```

```cpp
/**
 * // This is the MountainArray's API interface.
 * // You should not implement it, or speculate about its implementation
 * class MountainArray {
 *   public:
 *     int get(int index);
 *     int length();
 * };
 */

class Solution {
public:
    int findInMountainArray(int target, MountainArray& mountainArr) {
        int n = mountainArr.length();
        int l = 0, r = n - 1;
        while (l < r) {
            int mid = (l + r) >> 1;
            if (mountainArr.get(mid) > mountainArr.get(mid + 1)) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        auto search = [&](int l, int r, int k) -> int {
            while (l < r) {
                int mid = (l + r) >> 1;
                if (k * mountainArr.get(mid) >= k * target) {
                    r = mid;
                } else {
                    l = mid + 1;
                }
            }
            return mountainArr.get(l) == target ? l : -1;
        };
        int ans = search(0, l, 1);
        return ans == -1 ? search(l + 1, n - 1, -1) : ans;
    }
};
```

```go
/**
 * // This is the MountainArray's API interface.
 * // You should not implement it, or speculate about its implementation
 * type MountainArray struct {
 * }
 *
 * func (this *MountainArray) get(index int) int {}
 * func (this *MountainArray) length() int {}
 */

func findInMountainArray(target int, mountainArr *MountainArray) int {
	n := mountainArr.length()
	l, r := 0, n-1
	for l < r {
		mid := (l + r) >> 1
		if mountainArr.get(mid) > mountainArr.get(mid+1) {
			r = mid
		} else {
			l = mid + 1
		}
	}
	search := func(l, r, k int) int {
		for l < r {
			mid := (l + r) >> 1
			if k*mountainArr.get(mid) >= k*target {
				r = mid
			} else {
				l = mid + 1
			}
		}
		if mountainArr.get(l) == target {
			return l
		}
		return -1
	}
	ans := search(0, l, 1)
	if ans == -1 {
		return search(l+1, n-1, -1)
	}
	return ans
}
```

```ts
/**
 * // This is the MountainArray's API interface.
 * // You should not implement it, or speculate about its implementation
 * class Master {
 *      get(index: number): number {}
 *
 *      length(): number {}
 * }
 */

function findInMountainArray(target: number, mountainArr: MountainArray) {
    const n = mountainArr.length();
    let l = 0;
    let r = n - 1;
    while (l < r) {
        const mid = (l + r) >> 1;
        if (mountainArr.get(mid) > mountainArr.get(mid + 1)) {
            r = mid;
        } else {
            l = mid + 1;
        }
    }
    const search = (l: number, r: number, k: number): number => {
        while (l < r) {
            const mid = (l + r) >> 1;
            if (k * mountainArr.get(mid) >= k * target) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return mountainArr.get(l) === target ? l : -1;
    };
    const ans = search(0, l, 1);
    return ans === -1 ? search(l + 1, n - 1, -1) : ans;
}
```

```rust
impl Solution {
    #[allow(dead_code)]
    pub fn find_in_mountain_array(target: i32, mountain_arr: &MountainArray) -> i32 {
        let n = mountain_arr.length();

        // First find the maximum element in the array
        let mut l = 0;
        let mut r = n - 1;

        while l < r {
            let mid = (l + r) >> 1;
            if mountain_arr.get(mid) > mountain_arr.get(mid + 1) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }

        let left = Self::binary_search(mountain_arr, 0, l, 1, target);

        if left == -1 {
            Self::binary_search(mountain_arr, l, n - 1, -1, target)
        } else {
            left
        }
    }

    #[allow(dead_code)]
    fn binary_search(m: &MountainArray, mut l: i32, mut r: i32, k: i32, target: i32) -> i32 {
        let n = m.length();

        while l < r {
            let mid = (l + r) >> 1;
            if k * m.get(mid) >= k * target {
                r = mid;
            } else {
                l = mid + 1;
            }
        }

        if m.get(l) == target {
            l
        } else {
            -1
        }
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
