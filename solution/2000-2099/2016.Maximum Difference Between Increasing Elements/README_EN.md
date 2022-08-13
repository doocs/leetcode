# [2016. Maximum Difference Between Increasing Elements](https://leetcode.com/problems/maximum-difference-between-increasing-elements)

[中文文档](/solution/2000-2099/2016.Maximum%20Difference%20Between%20Increasing%20Elements/README.md)

## Description

<p>Given a <strong>0-indexed</strong> integer array <code>nums</code> of size <code>n</code>, find the <strong>maximum difference</strong> between <code>nums[i]</code> and <code>nums[j]</code> (i.e., <code>nums[j] - nums[i]</code>), such that <code>0 &lt;= i &lt; j &lt; n</code> and <code>nums[i] &lt; nums[j]</code>.</p>

<p>Return <em>the <strong>maximum difference</strong>. </em>If no such <code>i</code> and <code>j</code> exists, return <code>-1</code>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [7,<strong><u>1</u></strong>,<strong><u>5</u></strong>,4]
<strong>Output:</strong> 4
<strong>Explanation:</strong>
The maximum difference occurs with i = 1 and j = 2, nums[j] - nums[i] = 5 - 1 = 4.
Note that with i = 1 and j = 0, the difference nums[j] - nums[i] = 7 - 1 = 6, but i &gt; j, so it is not valid.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [9,4,3,2]
<strong>Output:</strong> -1
<strong>Explanation:</strong>
There is no i and j such that i &lt; j and nums[i] &lt; nums[j].
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [<strong><u>1</u></strong>,5,2,<strong><u>10</u></strong>]
<strong>Output:</strong> 9
<strong>Explanation:</strong>
The maximum difference occurs with i = 0 and j = 3, nums[j] - nums[i] = 10 - 1 = 9.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == nums.length</code></li>
	<li><code>2 &lt;= n &lt;= 1000</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def maximumDifference(self, nums: List[int]) -> int:
        mi = nums[0]
        ans, n = -1, len(nums)
        for i in range(1, n):
            if nums[i] > mi:
                ans = max(ans, nums[i] - mi)
            else:
                mi = nums[i]
        return ans
```

### **Java**

```java
class Solution {
    public int maximumDifference(int[] nums) {
        int mi = nums[0];
        int ans = -1;
        for (int i = 1; i < nums.length; ++i) {
            if (nums[i] > mi) {
                ans = Math.max(ans, nums[i] - mi);
            } else {
                mi = nums[i];
            }
        }
        return ans;
    }
}
```

### **TypeScript**

```ts
function maximumDifference(nums: number[]): number {
    const n = nums.length;
    let min = nums[0];
    let res = -1;
    for (let i = 1; i < n; i++) {
        res = Math.max(res, nums[i] - min);
        min = Math.min(min, nums[i]);
    }
    return res === 0 ? -1 : res;
}
```

### **Rust**

```rust
impl Solution {
    pub fn maximum_difference(nums: Vec<i32>) -> i32 {
        let mut min = nums[0];
        let mut res = -1;
        for i in 1..nums.len() {
            res = res.max(nums[i] - min);
            min = min.min(nums[i]);
        }
        match res {
            0 => -1,
            _ => res,
        }
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int maximumDifference(vector<int>& nums) {
        int mi = nums[0];
        int ans = -1;
        for (int i = 1, n = nums.size(); i < n; ++i) {
            if (nums[i] > mi)
                ans = max(ans, nums[i] - mi);
            else
                mi = nums[i];
        }
        return ans;
    }
};
```

### **Go**

```go
func maximumDifference(nums []int) int {
	mi, ans := nums[0], -1
	for i, n := 1, len(nums); i < n; i++ {
		if nums[i] > mi {
			ans = max(ans, nums[i]-mi)
		} else {
			mi = nums[i]
		}
	}
	return ans
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
```

### **...**

```

```

<!-- tabs:end -->
