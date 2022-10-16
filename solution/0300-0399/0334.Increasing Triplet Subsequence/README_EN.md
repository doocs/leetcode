# [334. Increasing Triplet Subsequence](https://leetcode.com/problems/increasing-triplet-subsequence)

[中文文档](/solution/0300-0399/0334.Increasing%20Triplet%20Subsequence/README.md)

## Description

<p>Given an integer array <code>nums</code>, return <code>true</code><em> if there exists a triple of indices </em><code>(i, j, k)</code><em> such that </em><code>i &lt; j &lt; k</code><em> and </em><code>nums[i] &lt; nums[j] &lt; nums[k]</code>. If no such indices exists, return <code>false</code>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,3,4,5]
<strong>Output:</strong> true
<strong>Explanation:</strong> Any triplet where i &lt; j &lt; k is valid.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [5,4,3,2,1]
<strong>Output:</strong> false
<strong>Explanation:</strong> No triplet exists.
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [2,1,5,0,4,6]
<strong>Output:</strong> true
<strong>Explanation:</strong> The triplet (3, 4, 5) is valid because nums[3] == 0 &lt; nums[4] == 4 &lt; nums[5] == 6.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 5 * 10<sup>5</sup></code></li>
	<li><code>-2<sup>31</sup> &lt;= nums[i] &lt;= 2<sup>31</sup> - 1</code></li>
</ul>

<p>&nbsp;</p>
<strong>Follow up:</strong> Could you implement a solution that runs in <code>O(n)</code> time complexity and <code>O(1)</code> space complexity?

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def increasingTriplet(self, nums: List[int]) -> bool:
        mi, mid = inf, inf
        for num in nums:
            if num > mid:
                return True
            if num <= mi:
                mi = num
            else:
                mid = num
        return False
```

### **Java**

```java
class Solution {
    public boolean increasingTriplet(int[] nums) {
        int n = nums.length;
        int[] lmi = new int[n];
        int[] rmx = new int[n];
        lmi[0] = Integer.MAX_VALUE;
        rmx[n - 1] = Integer.MIN_VALUE;
        for (int i = 1; i < n; ++i) {
            lmi[i] = Math.min(lmi[i - 1], nums[i - 1]);
        }
        for (int i = n - 2; i >= 0; --i) {
            rmx[i] = Math.max(rmx[i + 1], nums[i + 1]);
        }
        for (int i = 0; i < n; ++i) {
            if (lmi[i] < nums[i] && nums[i] < rmx[i]) {
                return true;
            }
        }
        return false;
    }
}
```

```java
class Solution {
    public boolean increasingTriplet(int[] nums) {
        int min = Integer.MAX_VALUE, mid = Integer.MAX_VALUE;
        for (int num : nums) {
            if (num > mid) {
                return true;
            }
            if (num <= min) {
                min = num;
            } else {
                mid = num;
            }
        }
        return false;
    }
}
```

### **TypeScript**

```ts
function increasingTriplet(nums: number[]): boolean {
    let n = nums.length;
    if (n < 3) return false;
    let min = nums[0],
        mid = Number.MAX_SAFE_INTEGER;
    for (let num of nums) {
        if (num <= min) {
            min = num;
        } else if (num <= mid) {
            mid = num;
        } else if (num > mid) {
            return true;
        }
    }
    return false;
}
```

### **C++**

```cpp
class Solution {
public:
    bool increasingTriplet(vector<int>& nums) {
        int mi = INT_MAX, mid = INT_MAX;
        for (int num : nums) {
            if (num > mid) return true;
            if (num <= mi)
                mi = num;
            else
                mid = num;
        }
        return false;
    }
};
```

### **Go**

```go
func increasingTriplet(nums []int) bool {
	min, mid := math.MaxInt32, math.MaxInt32
	for _, num := range nums {
		if num > mid {
			return true
		}
		if num <= min {
			min = num
		} else {
			mid = num
		}
	}
	return false
}
```

### **Rust**

```rust
impl Solution {
    pub fn increasing_triplet(nums: Vec<i32>) -> bool {
        let n = nums.len();
        if n < 3 {
            return false;
        }
        let mut min = i32::MAX;
        let mut mid = i32::MAX;
        for num in nums.into_iter() {
            if num <= min {
                min = num;
            } else if num <= mid {
                mid = num;
            } else {
                return true;
            }
        }
        false
    }
}
```

### **...**

```

```

<!-- tabs:end -->
