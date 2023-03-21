# [2357. Make Array Zero by Subtracting Equal Amounts](https://leetcode.com/problems/make-array-zero-by-subtracting-equal-amounts)

[中文文档](/solution/2300-2399/2357.Make%20Array%20Zero%20by%20Subtracting%20Equal%20Amounts/README.md)

## Description

<p>You are given a non-negative integer array <code>nums</code>. In one operation, you must:</p>

<ul>
	<li>Choose a positive integer <code>x</code> such that <code>x</code> is less than or equal to the <strong>smallest non-zero</strong> element in <code>nums</code>.</li>
	<li>Subtract <code>x</code> from every <strong>positive</strong> element in <code>nums</code>.</li>
</ul>

<p>Return <em>the <strong>minimum</strong> number of operations to make every element in </em><code>nums</code><em> equal to </em><code>0</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,5,0,3,5]
<strong>Output:</strong> 3
<strong>Explanation:</strong>
In the first operation, choose x = 1. Now, nums = [0,4,0,2,4].
In the second operation, choose x = 2. Now, nums = [0,2,0,0,2].
In the third operation, choose x = 2. Now, nums = [0,0,0,0,0].
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [0]
<strong>Output:</strong> 0
<strong>Explanation:</strong> Each element in nums is already 0 so no operations are needed.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 100</code></li>
	<li><code>0 &lt;= nums[i] &lt;= 100</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def minimumOperations(self, nums: List[int]) -> int:
        return len({x for x in nums if x})
```

### **Java**

```java
class Solution {
    public int minimumOperations(int[] nums) {
        boolean[] s = new boolean[101];
        s[0] = true;
        int ans = 0;
        for (int x : nums) {
            if (!s[x]) {
                ++ans;
                s[x] = true;
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
    int minimumOperations(vector<int>& nums) {
        bool s[101]{};
        s[0] = true;
        int ans = 0;
        for (int& x : nums) {
            if (!s[x]) {
                ++ans;
                s[x] = true;
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func minimumOperations(nums []int) (ans int) {
	s := [101]bool{true}
	for _, x := range nums {
		if !s[x] {
			s[x] = true
			ans++
		}
	}
	return
}
```

### **TypeScript**

```ts
function minimumOperations(nums: number[]): number {
    const set = new Set(nums);
    set.delete(0);
    return set.size;
}
```

### **Rust**

```rust
use std::collections::HashSet;
impl Solution {
    pub fn minimum_operations(nums: Vec<i32>) -> i32 {
        let mut set = nums.iter().collect::<HashSet<&i32>>();
        set.remove(&0);
        set.len() as i32
    }
}
```

### **C**

```c
int minimumOperations(int *nums, int numsSize) {
    int vis[101] = {0};
    vis[0] = 1;
    int ans = 0;
    for (int i = 0; i < numsSize; i++) {
        if (vis[nums[i]]) {
            continue;
        }
        vis[nums[i]] = 1;
        ans++;
    }
    return ans;
}
```

### **...**

```

```

<!-- tabs:end -->
