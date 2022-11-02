# [2444. Count Subarrays With Fixed Bounds](https://leetcode.com/problems/count-subarrays-with-fixed-bounds)

[中文文档](/solution/2400-2499/2444.Count%20Subarrays%20With%20Fixed%20Bounds/README.md)

## Description

<p>You are given an integer array <code>nums</code> and two integers <code>minK</code> and <code>maxK</code>.</p>

<p>A <strong>fixed-bound subarray</strong> of <code>nums</code> is a subarray that satisfies the following conditions:</p>

<ul>
	<li>The <strong>minimum</strong> value in the subarray is equal to <code>minK</code>.</li>
	<li>The <strong>maximum</strong> value in the subarray is equal to <code>maxK</code>.</li>
</ul>

<p>Return <em>the <strong>number</strong> of fixed-bound subarrays</em>.</p>

<p>A <strong>subarray</strong> is a <strong>contiguous</strong> part of an array.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,3,5,2,7,5], minK = 1, maxK = 5
<strong>Output:</strong> 2
<strong>Explanation:</strong> The fixed-bound subarrays are [1,3,5] and [1,3,5,2].
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,1,1,1], minK = 1, maxK = 1
<strong>Output:</strong> 10
<strong>Explanation:</strong> Every subarray of nums is a fixed-bound subarray. There are 10 possible subarrays.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i], minK, maxK &lt;= 10<sup>6</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def countSubarrays(self, nums: List[int], minK: int, maxK: int) -> int:
        j1 = j2 = k = -1
        ans = 0
        for i, v in enumerate(nums):
            if v < minK or v > maxK:
                k = i
            if v == minK:
                j1 = i
            if v == maxK:
                j2 = i
            ans += max(0, min(j1, j2) - k)
        return ans
```

### **Java**

```java
class Solution {
    public long countSubarrays(int[] nums, int minK, int maxK) {
        long ans = 0;
        int j1 = -1, j2 = -1, k = -1;
        for (int i = 0; i < nums.length; ++i) {
            if (nums[i] < minK || nums[i] > maxK) {
                k = i;
            }
            if (nums[i] == minK) {
                j1 = i;
            }
            if (nums[i] == maxK) {
                j2 = i;
            }
            ans += Math.max(0, Math.min(j1, j2) - k);
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    long long countSubarrays(vector<int>& nums, int minK, int maxK) {
        long long ans = 0;
        int j1 = -1, j2 = -1, k = -1;
        for (int i = 0; i < nums.size(); ++i) {
            if (nums[i] < minK || nums[i] > maxK) k = i;
            if (nums[i] == minK) j1 = i;
            if (nums[i] == maxK) j2 = i;
            ans += max(0, min(j1, j2) - k);
        }
        return ans;
    }
};
```

### **Go**

```go
func countSubarrays(nums []int, minK int, maxK int) int64 {
	ans := 0
	j1, j2, k := -1, -1, -1
	for i, v := range nums {
		if v < minK || v > maxK {
			k = i
		}
		if v == minK {
			j1 = i
		}
		if v == maxK {
			j2 = i
		}
		ans += max(0, min(j1, j2)-k)
	}
	return int64(ans)
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
```

### **C**

```c
#define max(a,b) (((a) > (b)) ? (a) : (b))
#define min(a,b) (((a) < (b)) ? (a) : (b))

long long countSubarrays(int *nums, int numsSize, int minK, int maxK) {
    long long res = 0;
    int minIndex = -1;
    int maxIndex = -1;
    int k = -1;
    for (int i = 0; i < numsSize; i++) {
        int num = nums[i];
        if (num == minK) {
            minIndex = i;
        }
        if (num == maxK) {
            maxIndex = i;
        }
        if (num < minK || num > maxK) {
            k = i;
        }
        res += max(min(minIndex, maxIndex) - k, 0);
    }
    return res;
}
```

### **TypeScript**

```ts
function countSubarrays(nums: number[], minK: number, maxK: number): number {
    let res = 0;
    let minIndex = -1;
    let maxIndex = -1;
    let k = -1;
    nums.forEach((num, i) => {
        if (num === minK) {
            minIndex = i;
        }
        if (num === maxK) {
            maxIndex = i;
        }
        if (num < minK || num > maxK) {
            k = i;
        }
        res += Math.max(Math.min(minIndex, maxIndex) - k, 0);
    });
    return res;
}
```

### **Rust**

```rust
impl Solution {
    pub fn count_subarrays(nums: Vec<i32>, min_k: i32, max_k: i32) -> i64 {
        let mut res = 0;
        let mut min_index = -1;
        let mut max_index = -1;
        let mut k = -1;
        for i in 0..nums.len() {
            let num = nums[i];
            let i = i as i64;
            if num == min_k {
                min_index = i;
            }
            if num == max_k {
                max_index = i;
            }
            if num < min_k || num > max_k {
                k = i;
            }
            res += 0.max(min_index.min(max_index) - k);
        }
        res
    }
}
```

### **...**

```

```

<!-- tabs:end -->
