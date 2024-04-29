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

### Solution 1

<!-- tabs:start -->

```python
class Solution:
    def findMagicIndex(self, nums: List[int]) -> int:
        def find(nums, left, right):
            if left > right:
                return -1
            mid = (left + right) >> 1
            left_index = find(nums, left, mid - 1)
            if left_index != -1:
                return left_index
            if nums[mid] == mid:
                return mid
            return find(nums, mid + 1, right)

        return find(nums, 0, len(nums) - 1)
```

```java
class Solution {
    public int findMagicIndex(int[] nums) {
        int left = 0, right = nums.length - 1;
        return find(nums, left, right);
    }

    private int find(int[] nums, int left, int right) {
        if (left > right) {
            return -1;
        }
        int mid = (left + right) >> 1;
        int leftIndex = find(nums, left, mid - 1);
        if (leftIndex != -1) {
            return leftIndex;
        }
        if (nums[mid] == mid) {
            return mid;
        }
        return find(nums, mid + 1, right);
    }
}
```

```cpp
class Solution {
public:
    int findMagicIndex(vector<int>& nums) {
        return find(nums, 0, nums.size() - 1);
    }

    int find(vector<int>& nums, int left, int right) {
        if (left > right) {
            return -1;
        }
        int mid = left + right >> 1;
        int leftIndex = find(nums, left, mid - 1);
        if (leftIndex != -1) {
            return leftIndex;
        }
        if (nums[mid] == mid) {
            return mid;
        }
        return find(nums, mid + 1, right);
    }
};
```

```go
func findMagicIndex(nums []int) int {
	return find(nums, 0, len(nums)-1)
}

func find(nums []int, left, right int) int {
	if left > right {
		return -1
	}
	mid := (left + right) >> 1
	leftIndex := find(nums, left, mid-1)
	if leftIndex != -1 {
		return leftIndex
	}
	if nums[mid] == mid {
		return mid
	}
	return find(nums, mid+1, right)
}
```

```ts
function findMagicIndex(nums: number[]): number {
    const n = nums.length;
    const find = (l: number, r: number): number => {
        if (l > r || nums[r] < 0) {
            return -1;
        }
        const mid = l + Math.floor((r - l) / 2);
        if (nums[mid] >= l) {
            const res = find(l, mid - 1);
            if (res !== -1) {
                return res;
            }
        }
        if (nums[mid] === mid) {
            return mid;
        }
        return find(mid + 1, r);
    };
    return find(0, n - 1);
}
```

```rust
impl Solution {
    fn find(nums: &Vec<i32>, l: usize, r: usize) -> i32 {
        if l >= r || nums[r - 1] < 0 {
            return -1;
        }
        let mid = l + (r - l) / 2;
        if nums[mid] >= (l as i32) {
            let res = Self::find(nums, l, mid);
            if res != -1 {
                return res;
            }
        }
        if nums[mid] == (mid as i32) {
            return mid as i32;
        }
        Self::find(nums, mid + 1, r)
    }

    pub fn find_magic_index(nums: Vec<i32>) -> i32 {
        Self::find(&nums, 0, nums.len())
    }
}
```

```js
/**
 * @param {number[]} nums
 * @return {number}
 */
var findMagicIndex = function (nums) {
    return helper(nums, 0, nums.length - 1);
};

function helper(nums, left, right) {
    if (left > right) return -1;
    let mid = Math.floor((left + right) / 2);
    let leftIndex = helper(nums, left, mid - 1);
    if (leftIndex != -1) return leftIndex;
    if (nums[mid] == mid) return mid;
    return helper(nums, mid + 1, right);
}
```

<!-- tabs:end -->

### Solution 2

<!-- tabs:start -->

```ts
function findMagicIndex(nums: number[]): number {
    const n = nums.length;
    let i = 0;
    while (i < n) {
        if (nums[i] === i) {
            return i;
        }
        i = Math.max(nums[i], i + 1);
    }
    return -1;
}
```

```rust
impl Solution {
    pub fn find_magic_index(nums: Vec<i32>) -> i32 {
        let n = nums.len();
        let mut i = 0 as i32;
        while (i as usize) < n {
            let num = nums[i as usize];
            if num == i {
                return i;
            }
            i = num.max(i + 1);
        }
        -1
    }
}
```

```swift
class Solution {
    func findMagicIndex(_ nums: [Int]) -> Int {
        let left = 0
        let right = nums.count - 1
        return find(nums, left, right)
    }

    private func find(_ nums: [Int], _ left: Int, _ right: Int) -> Int {
        if left > right {
            return -1
        }
        let mid = (left + right) >> 1
        let leftIndex = find(nums, left, mid - 1)
        if leftIndex != -1 {
            return leftIndex
        }
        if nums[mid] == mid {
            return mid
        }
        return find(nums, mid + 1, right)
    }
}
```

<!-- tabs:end -->

<!-- end -->
