---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1700-1799/1752.Check%20if%20Array%20Is%20Sorted%20and%20Rotated/README_EN.md
rating: 1324
source: Weekly Contest 227 Q1
tags:
    - Array
---

<!-- problem:start -->

# [1752. Check if Array Is Sorted and Rotated](https://leetcode.com/problems/check-if-array-is-sorted-and-rotated)

[中文文档](/solution/1700-1799/1752.Check%20if%20Array%20Is%20Sorted%20and%20Rotated/README.md)

## Description

<!-- description:start -->

<p>Given an array <code>nums</code>, return <code>true</code><em> if the array was originally sorted in non-decreasing order, then rotated <strong>some</strong> number of positions (including zero)</em>. Otherwise, return <code>false</code>.</p>

<p>There may be <strong>duplicates</strong> in the original array.</p>

<p><strong>Note:</strong> An array <code>A</code> rotated by <code>x</code> positions results in an array <code>B</code> of the same length such that <code>B[i] == A[(i+x) % A.length]</code> for every valid index <code>i</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [3,4,5,1,2]
<strong>Output:</strong> true
<strong>Explanation:</strong> [1,2,3,4,5] is the original sorted array.
You can rotate the array by x = 3 positions to begin on the element of value 3: [3,4,5,1,2].
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [2,1,3,4]
<strong>Output:</strong> false
<strong>Explanation:</strong> There is no sorted array once rotated that can make nums.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,3]
<strong>Output:</strong> true
<strong>Explanation:</strong> [1,2,3] is the original sorted array.
You can rotate the array by x = 0 positions (i.e. no rotation) to make nums.
</pre>

<div class="simple-translate-system-theme" id="simple-translate">
<div>
<div class="simple-translate-button " style="background-image: url(&quot;moz-extension://8a9ffb6b-7e69-4e93-aae1-436a1448eff6/icons/512.png&quot;); height: 22px; width: 22px; top: 10px; left: 10px;">&nbsp;</div>

<div class="simple-translate-panel " style="width: 300px; height: 200px; top: 0px; left: 0px; font-size: 13px;">
<div class="simple-translate-result-wrapper" style="overflow: hidden;">
<div class="simple-translate-move" draggable="true">&nbsp;</div>

<div class="simple-translate-result-contents">
<p class="simple-translate-result" dir="auto">&nbsp;</p>

<p class="simple-translate-candidate" dir="auto">&nbsp;</p>
</div>
</div>
</div>
</div>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 100</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 100</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def check(self, nums: List[int]) -> bool:
        return sum(nums[i - 1] > v for i, v in enumerate(nums)) <= 1
```

#### Java

```java
class Solution {
    public boolean check(int[] nums) {
        int cnt = 0;
        for (int i = 0, n = nums.length; i < n; ++i) {
            if (nums[i] > nums[(i + 1) % n]) {
                ++cnt;
            }
        }
        return cnt <= 1;
    }
}
```

#### C++

```cpp
class Solution {
public:
    bool check(vector<int>& nums) {
        int cnt = 0;
        for (int i = 0, n = nums.size(); i < n; ++i) {
            cnt += nums[i] > (nums[(i + 1) % n]);
        }
        return cnt <= 1;
    }
};
```

#### Go

```go
func check(nums []int) bool {
	cnt := 0
	for i, v := range nums {
		if v > nums[(i+1)%len(nums)] {
			cnt++
		}
	}
	return cnt <= 1
}
```

#### TypeScript

```ts
function check(nums: number[]): boolean {
    const n = nums.length;
    return nums.reduce((r, v, i) => r + (v > nums[(i + 1) % n] ? 1 : 0), 0) <= 1;
}
```

#### Rust

```rust
impl Solution {
    pub fn check(nums: Vec<i32>) -> bool {
        let n = nums.len();
        let mut count = 0;
        for i in 0..n {
            if nums[i] > nums[(i + 1) % n] {
                count += 1;
            }
        }
        count <= 1
    }
}
```

#### C

```c
bool check(int* nums, int numsSize) {
    int count = 0;
    for (int i = 0; i < numsSize; i++) {
        if (nums[i] > nums[(i + 1) % numsSize]) {
            count++;
        }
    }
    return count <= 1;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
