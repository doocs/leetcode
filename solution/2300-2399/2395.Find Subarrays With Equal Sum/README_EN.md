# [2395. Find Subarrays With Equal Sum](https://leetcode.com/problems/find-subarrays-with-equal-sum)

[中文文档](/solution/2300-2399/2395.Find%20Subarrays%20With%20Equal%20Sum/README.md)

## Description

<p>Given a <strong>0-indexed</strong> integer array <code>nums</code>, determine whether there exist <strong>two</strong> subarrays of length <code>2</code> with <strong>equal</strong> sum. Note that the two subarrays must begin at <strong>different</strong> indices.</p>

<p>Return <code>true</code><em> if these subarrays exist, and </em><code>false</code><em> otherwise.</em></p>

<p>A <b>subarray</b> is a contiguous non-empty sequence of elements within an array.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [4,2,4]
<strong>Output:</strong> true
<strong>Explanation:</strong> The subarrays with elements [4,2] and [2,4] have the same sum of 6.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,3,4,5]
<strong>Output:</strong> false
<strong>Explanation:</strong> No two subarrays of size 2 have the same sum.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [0,0,0]
<strong>Output:</strong> true
<strong>Explanation:</strong> The subarrays [nums[0],nums[1]] and [nums[1],nums[2]] have the same sum of 0. 
Note that even though the subarrays have the same content, the two subarrays are considered different because they are in different positions in the original array.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= nums.length &lt;= 1000</code></li>
	<li><code>-10<sup>9</sup> &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def findSubarrays(self, nums: List[int]) -> bool:
        vis = set()
        for a, b in pairwise(nums):
            if (x := a + b) in vis:
                return True
            vis.add(x)
        return False
```

### **Java**

```java
class Solution {
    public boolean findSubarrays(int[] nums) {
        Set<Integer> vis = new HashSet<>();
        for (int i = 1; i < nums.length; ++i) {
            if (!vis.add(nums[i - 1] + nums[i])) {
                return true;
            }
        }
        return false;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    bool findSubarrays(vector<int>& nums) {
        unordered_set<int> vis;
        for (int i = 1; i < nums.size(); ++i) {
            int x = nums[i - 1] + nums[i];
            if (vis.count(x)) {
                return true;
            }
            vis.insert(x);
        }
        return false;
    }
};
```

### **Go**

```go
func findSubarrays(nums []int) bool {
	vis := map[int]bool{}
	for i, b := range nums[1:] {
		x := nums[i] + b
		if vis[x] {
			return true
		}
		vis[x] = true
	}
	return false
}
```

### **TypeScript**

```ts
function findSubarrays(nums: number[]): boolean {
    const vis: Set<number> = new Set<number>();
    for (let i = 1; i < nums.length; ++i) {
        const x = nums[i - 1] + nums[i];
        if (vis.has(x)) {
            return true;
        }
        vis.add(x);
    }
    return false;
}
```

### **Rust**

```rust
use std::collections::HashSet;
impl Solution {
    pub fn find_subarrays(nums: Vec<i32>) -> bool {
        let n = nums.len();
        let mut set = HashSet::new();
        for i in 1..n {
            if !set.insert(nums[i - 1] + nums[i]) {
                return true;
            }
        }
        false
    }
}
```

### **C**

```c
bool findSubarrays(int *nums, int numsSize) {
    for (int i = 1; i < numsSize - 1; i++) {
        for (int j = i + 1; j < numsSize; j++) {
            if (nums[i - 1] + nums[i] == nums[j - 1] + nums[j]) {
                return true;
            }
        }
    }
    return false;
}
```

### **...**

```


```

<!-- tabs:end -->
