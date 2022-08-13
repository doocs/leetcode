# [896. Monotonic Array](https://leetcode.com/problems/monotonic-array)

[中文文档](/solution/0800-0899/0896.Monotonic%20Array/README.md)

## Description

<p>An array is <strong>monotonic</strong> if it is either monotone increasing or monotone decreasing.</p>

<p>An array <code>nums</code> is monotone increasing if for all <code>i &lt;= j</code>, <code>nums[i] &lt;= nums[j]</code>. An array <code>nums</code> is monotone decreasing if for all <code>i &lt;= j</code>, <code>nums[i] &gt;= nums[j]</code>.</p>

<p>Given an integer array <code>nums</code>, return <code>true</code><em> if the given array is monotonic, or </em><code>false</code><em> otherwise</em>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,2,3]
<strong>Output:</strong> true
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [6,5,4,4]
<strong>Output:</strong> true
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,3,2]
<strong>Output:</strong> false
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>-10<sup>5</sup> &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def isMonotonic(self, nums: List[int]) -> bool:
        isIncr = isDecr = False
        for i, v in enumerate(nums[1:]):
            if v < nums[i]:
                isIncr = True
            elif v > nums[i]:
                isDecr = True
            if isIncr and isDecr:
                return False
        return True
```

### **Java**

```java
class Solution {
    public boolean isMonotonic(int[] nums) {
        boolean isIncr = false, isDecr = false;
        for (int i = 1; i < nums.length; ++i) {
            if (nums[i] < nums[i - 1]) {
                isIncr = true;
            } else if (nums[i] > nums[i - 1]) {
                isDecr = true;
            }
            if (isIncr && isDecr) {
                return false;
            }
        }
        return true;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    bool isMonotonic(vector<int>& nums) {
        bool isIncr = false;
        bool isDecr = false;
        for (int i = 1; i < nums.size(); ++i) {
            if (nums[i] < nums[i - 1]) isIncr = true;
            if (nums[i] > nums[i - 1]) isDecr = true;
            if (isIncr && isDecr) return false;
        }
        return true;
    }
};
```

### **Go**

```go
func isMonotonic(nums []int) bool {
	isIncr, isDecr := false, false
	for i, v := range nums[1:] {
		if v < nums[i] {
			isIncr = true
		} else if v > nums[i] {
			isDecr = true
		}
		if isIncr && isDecr {
			return false
		}
	}
	return true
}
```

### **JavaScript**

```js
/**
 * @param {number[]} nums
 * @return {boolean}
 */
var isMonotonic = function (nums) {
    let isIncr = false;
    let isDecr = false;
    for (let i = 1; i < nums.length; ++i) {
        if (nums[i] < nums[i - 1]) {
            isIncr = true;
        }
        if (nums[i] > nums[i - 1]) {
            isDecr = true;
        }
        if (isIncr && isDecr) {
            return false;
        }
    }
    return true;
};
```

### **TypeScript**

```ts
function isMonotonic(nums: number[]): boolean {
    const n = nums.length;
    let isOrder = false;
    let isDecs = false;
    for (let i = 1; i < n; i++) {
        const pre = nums[i - 1];
        const cur = nums[i];
        if (pre < cur) {
            isOrder = true;
        } else if (pre > cur) {
            isDecs = true;
        }
        if (isOrder && isDecs) {
            return false;
        }
    }
    return true;
}
```

### **Rust**

```rust
impl Solution {
    pub fn is_monotonic(nums: Vec<i32>) -> bool {
        let n = nums.len();
        let mut is_order = false;
        let mut is_decs = false;
        for i in 1..n {
            let pre = nums[i - 1];
            let cur = nums[i];
            if pre < cur {
                is_order = true;
            } else if pre > cur {
                is_decs = true;
            }
            if is_order && is_decs {
                return false;
            }
        }
        true
    }
}
```

### **...**

```

```

<!-- tabs:end -->
