# [169. Majority Element](https://leetcode.com/problems/majority-element)

[中文文档](/solution/0100-0199/0169.Majority%20Element/README.md)

## Description

<p>Given an array <code>nums</code> of size <code>n</code>, return <em>the majority element</em>.</p>

<p>The majority element is the element that appears more than <code>&lfloor;n / 2&rfloor;</code> times. You may assume that the majority element always exists in the array.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<pre><strong>Input:</strong> nums = [3,2,3]
<strong>Output:</strong> 3
</pre><p><strong>Example 2:</strong></p>
<pre><strong>Input:</strong> nums = [2,2,1,1,1,2,2]
<strong>Output:</strong> 2
</pre>
<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == nums.length</code></li>
	<li><code>1 &lt;= n &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>-10<sup>9</sup> &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
</ul>

<p>&nbsp;</p>
<strong>Follow-up:</strong> Could you solve the problem in linear time and in <code>O(1)</code> space?

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def majorityElement(self, nums: List[int]) -> int:
        cnt = m = 0
        for v in nums:
            if cnt == 0:
                m, cnt = v, 1
            else:
                cnt += 1 if m == v else -1
        return m
```

### **Java**

```java
class Solution {
    public int majorityElement(int[] nums) {
        int cnt = 0, m = 0;
        for (int v : nums) {
            if (cnt == 0) {
                m = v;
                cnt = 1;
            } else {
                cnt += (m == v ? 1 : -1);
            }
        }
        return m;
    }
}
```

### **JavaScript**

```js
/**
 * @param {number[]} nums
 * @return {number}
 */
var majorityElement = function (nums) {
    let cnt = 0,
        m = 0;
    for (const v of nums) {
        if (cnt == 0) {
            m = v;
            cnt = 1;
        } else {
            cnt += m == v ? 1 : -1;
        }
    }
    return m;
};
```

### **C++**

```cpp
class Solution {
public:
    int majorityElement(vector<int>& nums) {
        int cnt = 0, m = 0;
        for (int& v : nums) {
            if (cnt == 0) {
                m = v;
                cnt = 1;
            } else
                cnt += (m == v ? 1 : -1);
        }
        return m;
    }
};
```

### **C#**

```cs
public class Solution {
    public int MajorityElement(int[] nums) {
        int cnt = 0, m = 0;
        foreach (int v in nums)
        {
            if (cnt == 0)
            {
                m = v;
                cnt = 1;
            }
            else
            {
                cnt += m == v ? 1 : -1;
            }
        }
        return m;
    }
}
```

### **Go**

```go
func majorityElement(nums []int) int {
	cnt, m := 0, 0
	for _, v := range nums {
		if cnt == 0 {
			m, cnt = v, 1
		} else {
			if m == v {
				cnt++
			} else {
				cnt--
			}
		}
	}
	return m
}
```

### **Rust**

```rust
impl Solution {
    pub fn majority_element(nums: Vec<i32>) -> i32 {
        let mut m = 0;
        let mut cnt = 0;
        for &v in nums.iter() {
            if cnt == 0 {
                m = v;
                cnt = 1;
            } else {
                cnt += if m == v { 1 } else { -1 };
            }
        }
        m
    }
}
```

### **...**

```

```

<!-- tabs:end -->
