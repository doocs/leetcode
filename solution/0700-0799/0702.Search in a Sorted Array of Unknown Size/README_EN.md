---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0700-0799/0702.Search%20in%20a%20Sorted%20Array%20of%20Unknown%20Size/README_EN.md
tags:
    - Array
    - Binary Search
    - Interactive
---

<!-- problem:start -->

# [702. Search in a Sorted Array of Unknown Size 🔒](https://leetcode.com/problems/search-in-a-sorted-array-of-unknown-size)

[中文文档](/solution/0700-0799/0702.Search%20in%20a%20Sorted%20Array%20of%20Unknown%20Size/README.md)

## Description

<!-- description:start -->

<p>This is an <strong><em>interactive problem</em></strong>.</p>

<p>You have a sorted array of <strong>unique</strong> elements and an <strong>unknown size</strong>. You do not have an access to the array but you can use the <code>ArrayReader</code> interface to access it. You can call <code>ArrayReader.get(i)</code> that:</p>

<ul>
	<li>returns the value at the <code>i<sup>th</sup></code> index (<strong>0-indexed</strong>) of the secret array (i.e., <code>secret[i]</code>), or</li>
	<li>returns <code>2<sup>31</sup> - 1</code> if the <code>i</code> is out of the boundary of the array.</li>
</ul>

<p>You are also given an integer <code>target</code>.</p>

<p>Return the index <code>k</code> of the hidden array where <code>secret[k] == target</code> or return <code>-1</code> otherwise.</p>

<p>You must write an algorithm with <code>O(log n)</code> runtime complexity.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> secret = [-1,0,3,5,9,12], target = 9
<strong>Output:</strong> 4
<strong>Explanation:</strong> 9 exists in secret and its index is 4.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> secret = [-1,0,3,5,9,12], target = 2
<strong>Output:</strong> -1
<strong>Explanation:</strong> 2 does not exist in secret so return -1.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= secret.length &lt;= 10<sup>4</sup></code></li>
	<li><code>-10<sup>4</sup> &lt;= secret[i], target &lt;= 10<sup>4</sup></code></li>
	<li><code>secret</code> is sorted in a strictly increasing order.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Binary Search

First, we define a pointer $r = 1$. Each time, we check if the value at position $r$ is less than the target value. If it is, we multiply $r$ by $2$, i.e., shift it left by one bit, until the value at position $r$ is greater than or equal to the target value. At this point, we can determine that the target value is within the interval $[r / 2, r]$.

Next, we define a pointer $l = r / 2$, and then we can use the binary search method to find the position of the target value within the interval $[l, r]$.

The time complexity is $O(\log M)$, where $M$ is the position of the target value. The space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
# """
# This is ArrayReader's API interface.
# You should not implement it, or speculate about its implementation
# """
# class ArrayReader:
#    def get(self, index: int) -> int:


class Solution:
    def search(self, reader: "ArrayReader", target: int) -> int:
        r = 1
        while reader.get(r) < target:
            r <<= 1
        l = r >> 1
        while l < r:
            mid = (l + r) >> 1
            if reader.get(mid) >= target:
                r = mid
            else:
                l = mid + 1
        return l if reader.get(l) == target else -1
```

#### Java

```java
/**
 * // This is ArrayReader's API interface.
 * // You should not implement it, or speculate about its implementation
 * interface ArrayReader {
 *     public int get(int index) {}
 * }
 */

class Solution {
    public int search(ArrayReader reader, int target) {
        int r = 1;
        while (reader.get(r) < target) {
            r <<= 1;
        }
        int l = r >> 1;
        while (l < r) {
            int mid = (l + r) >> 1;
            if (reader.get(mid) >= target) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return reader.get(l) == target ? l : -1;
    }
}
```

#### C++

```cpp
/**
 * // This is the ArrayReader's API interface.
 * // You should not implement it, or speculate about its implementation
 * class ArrayReader {
 *   public:
 *     int get(int index);
 * };
 */

class Solution {
public:
    int search(const ArrayReader& reader, int target) {
        int r = 1;
        while (reader.get(r) < target) {
            r <<= 1;
        }
        int l = r >> 1;
        while (l < r) {
            int mid = (l + r) >> 1;
            if (reader.get(mid) >= target) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return reader.get(l) == target ? l : -1;
    }
};
```

#### Go

```go
/**
 * // This is the ArrayReader's API interface.
 * // You should not implement it, or speculate about its implementation
 * type ArrayReader struct {
 * }
 *
 * func (this *ArrayReader) get(index int) int {}
 */

func search(reader ArrayReader, target int) int {
	r := 1
	for reader.get(r) < target {
		r <<= 1
	}
	l := r >> 1
	for l < r {
		mid := (l + r) >> 1
		if reader.get(mid) >= target {
			r = mid
		} else {
			l = mid + 1
		}
	}
	if reader.get(l) == target {
		return l
	}
	return -1
}
```

#### TypeScript

```ts
/**
 * class ArrayReader {
 *		// This is the ArrayReader's API interface.
 *		// You should not implement it, or speculate about its implementation
 *		get(index: number): number {};
 *  };
 */

function search(reader: ArrayReader, target: number): number {
    let r = 1;
    while (reader.get(r) < target) {
        r <<= 1;
    }
    let l = r >> 1;
    while (l < r) {
        const mid = (l + r) >> 1;
        if (reader.get(mid) >= target) {
            r = mid;
        } else {
            l = mid + 1;
        }
    }
    return reader.get(l) === target ? l : -1;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
