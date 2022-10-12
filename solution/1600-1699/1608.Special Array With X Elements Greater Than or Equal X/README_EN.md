# [1608. Special Array With X Elements Greater Than or Equal X](https://leetcode.com/problems/special-array-with-x-elements-greater-than-or-equal-x)

[中文文档](/solution/1600-1699/1608.Special%20Array%20With%20X%20Elements%20Greater%20Than%20or%20Equal%20X/README.md)

## Description

<p>You are given an array <code>nums</code> of non-negative integers. <code>nums</code> is considered <strong>special</strong> if there exists a number <code>x</code> such that there are <strong>exactly</strong> <code>x</code> numbers in <code>nums</code> that are <strong>greater than or equal to</strong> <code>x</code>.</p>

<p>Notice that <code>x</code> <strong>does not</strong> have to be an element in <code>nums</code>.</p>

<p>Return <code>x</code> <em>if the array is <strong>special</strong>, otherwise, return </em><code>-1</code>. It can be proven that if <code>nums</code> is special, the value for <code>x</code> is <strong>unique</strong>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [3,5]
<strong>Output:</strong> 2
<strong>Explanation:</strong> There are 2 values (3 and 5) that are greater than or equal to 2.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [0,0]
<strong>Output:</strong> -1
<strong>Explanation:</strong> No numbers fit the criteria for x.
If x = 0, there should be 0 numbers &gt;= x, but there are 2.
If x = 1, there should be 1 number &gt;= x, but there are 0.
If x = 2, there should be 2 numbers &gt;= x, but there are 0.
x cannot be greater since there are only 2 numbers in nums.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [0,4,3,0,4]
<strong>Output:</strong> 3
<strong>Explanation:</strong> There are 3 values that are greater than or equal to 3.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 100</code></li>
	<li><code>0 &lt;= nums[i] &lt;= 1000</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def specialArray(self, nums: List[int]) -> int:
        for x in range(1, len(nums) + 1):
            cnt = sum(v >= x for v in nums)
            if cnt == x:
                return x
        return -1
```

```python
class Solution:
    def specialArray(self, nums: List[int]) -> int:
        nums.sort()
        n = len(nums)
        for x in range(1, n + 1):
            cnt = n - bisect_left(nums, x)
            if cnt == x:
                return x
        return -1
```

### **Java**

```java
class Solution {
    public int specialArray(int[] nums) {
        for (int x = 1; x <= nums.length; ++x) {
            int cnt = 0;
            for (int v : nums) {
                if (v >= x) {
                    ++cnt;
                }
            }
            if (cnt == x) {
                return x;
            }
        }
        return -1;
    }
}
```

```java
class Solution {
    public int specialArray(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        for (int x = 1; x <= n; ++x) {
            int left = 0, right = n;
            while (left < right) {
                int mid = (left + right) >> 1;
                if (nums[mid] >= x) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }
            int cnt = n - left;
            if (cnt == x) {
                return x;
            }
        }
        return -1;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int specialArray(vector<int>& nums) {
        for (int x = 1; x <= nums.size(); ++x) {
            int cnt = 0;
            for (int v : nums) cnt += v >= x;
            if (cnt == x) return x;
        }
        return -1;
    }
};
```

```cpp
class Solution {
public:
    int specialArray(vector<int>& nums) {
        int n = nums.size();
        sort(nums.begin(), nums.end());
        for (int x = 1; x <= n; ++x) {
            int cnt = n - (lower_bound(nums.begin(), nums.end(), x) - nums.begin());
            if (cnt == x) return x;
        }
        return -1;
    }
};
```

### **Go**

```go
func specialArray(nums []int) int {
	for x := 1; x <= len(nums); x++ {
		cnt := 0
		for _, v := range nums {
			if v >= x {
				cnt++
			}
		}
		if cnt == x {
			return x
		}
	}
	return -1
}
```

```go
func specialArray(nums []int) int {
	sort.Ints(nums)
	n := len(nums)
	for x := 1; x <= n; x++ {
		left, right := 0, n
		for left < right {
			mid := (left + right) >> 1
			if nums[mid] >= x {
				right = mid
			} else {
				left = mid + 1
			}
		}
		cnt := n - left
		if cnt == x {
			return x
		}
	}
	return -1
}
```

### **TypeScript**

```ts
function specialArray(nums: number[]): number {
    const n = nums.length;
    for (let i = 0; i <= n; i++) {
        if (i === nums.reduce((r, v) => r + (v >= i ? 1 : 0), 0)) {
            return i;
        }
    }
    return -1;
}
```

```ts
function specialArray(nums: number[]): number {
    const n = nums.length;
    let left = 0;
    let right = n + 1;
    while (left < right) {
        const mid = (left + right) >> 1;
        const count = nums.reduce((r, v) => r + (v >= mid ? 1 : 0), 0);

        if (count === mid) {
            return mid;
        }

        if (count > mid) {
            left = mid + 1;
        } else {
            right = mid;
        }
    }
    return -1;
}
```

### **Rust**

```rust
impl Solution {
    pub fn special_array(nums: Vec<i32>) -> i32 {
        let n = nums.len() as i32;
        for i in 0..=n {
            let mut count = 0;
            for &num in nums.iter() {
                if num >= i {
                    count += 1;
                }
            }
            if count == i {
                return i;
            }
        }
        -1
    }
}
```

```rust
use std::cmp::Ordering;
impl Solution {
    pub fn special_array(nums: Vec<i32>) -> i32 {
        let n = nums.len() as i32;
        let mut left = 0;
        let mut right = n + 1;
        while left < right {
            let mid = left + (right - left) / 2;
            let mut count = 0;
            for &num in nums.iter() {
                if num >= mid {
                    count += 1;
                }
            }
            match count.cmp(&mid) {
                Ordering::Equal => {
                    return mid;
                }
                Ordering::Less => {
                    right = mid;
                }
                Ordering::Greater => {
                    left = mid + 1;
                }
            }
        }
        -1
    }
}
```

### **...**

```

```

<!-- tabs:end -->
