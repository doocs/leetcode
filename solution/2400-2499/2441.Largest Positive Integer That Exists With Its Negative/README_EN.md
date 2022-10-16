# [2441. Largest Positive Integer That Exists With Its Negative](https://leetcode.com/problems/largest-positive-integer-that-exists-with-its-negative)

[中文文档](/solution/2400-2499/2441.Largest%20Positive%20Integer%20That%20Exists%20With%20Its%20Negative/README.md)

## Description

<p>Given an integer array <code>nums</code> that <strong>does not contain</strong> any zeros, find <strong>the largest positive</strong> integer <code>k</code> such that <code>-k</code> also exists in the array.</p>

<p>Return <em>the positive integer </em><code>k</code>. If there is no such integer, return <code>-1</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [-1,2,-3,3]
<strong>Output:</strong> 3
<strong>Explanation:</strong> 3 is the only valid k we can find in the array.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [-1,10,6,7,-7,1]
<strong>Output:</strong> 7
<strong>Explanation:</strong> Both 1 and 7 have their corresponding negative values in the array. 7 has a larger value.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [-10,8,6,7,-2,-3]
<strong>Output:</strong> -1
<strong>Explanation:</strong> There is no a single valid k, we return -1.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 1000</code></li>
	<li><code>-1000 &lt;= nums[i] &lt;= 1000</code></li>
	<li><code>nums[i] != 0</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def findMaxK(self, nums: List[int]) -> int:
        ans = -1
        s = set(nums)
        for v in s:
            if -v in s:
                ans = max(ans, v)
        return ans
```

### **Java**

```java
class Solution {
    public int findMaxK(int[] nums) {
        int ans = -1;
        Set<Integer> s = new HashSet<>();
        for (int v : nums) {
            s.add(v);
        }
        for (int v : s) {
            if (s.contains(-v)) {
                ans = Math.max(ans, v);
            }
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int findMaxK(vector<int>& nums) {
        unordered_set<int> s(nums.begin(), nums.end());
        int ans = -1;
        for (int& v : nums) {
            if (s.count(-v)) {
                ans = max(ans, v);
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func findMaxK(nums []int) int {
	s := map[int]bool{}
	for _, v := range nums {
		s[v] = true
	}
	ans := -1
	for v := range s {
		if s[-v] && ans < v {
			ans = v
		}
	}
	return ans
}
```

### **TypeScript**

```ts
function findMaxK(nums: number[]): number {
    const set = new Set(nums);
    let res = -1;
    for (const num of set) {
        if (set.has(-num)) {
            res = Math.max(num, res);
        }
    }
    return res;
}
```

### **Rust**

```rust
use std::collections::HashSet;
impl Solution {
    pub fn find_max_k(nums: Vec<i32>) -> i32 {
        let set = nums.into_iter().collect::<HashSet<i32>>();
        let mut res = -1;
        for &num in set.iter() {
            if set.contains(&(-num)) {
                res = res.max(num);
            }
        }
        res
    }
}
```

### **...**

```

```

<!-- tabs:end -->
