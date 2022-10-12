# [1913. Maximum Product Difference Between Two Pairs](https://leetcode.com/problems/maximum-product-difference-between-two-pairs)

[中文文档](/solution/1900-1999/1913.Maximum%20Product%20Difference%20Between%20Two%20Pairs/README.md)

## Description

<p>The <strong>product difference</strong> between two pairs <code>(a, b)</code> and <code>(c, d)</code> is defined as <code>(a * b) - (c * d)</code>.</p>

<ul>

    <li>For example, the product difference between <code>(5, 6)</code> and <code>(2, 7)</code> is <code>(5 * 6) - (2 * 7) = 16</code>.</li>

</ul>

<p>Given an integer array <code>nums</code>, choose four <strong>distinct</strong> indices <code>w</code>, <code>x</code>, <code>y</code>, and <code>z</code> such that the <strong>product difference</strong> between pairs <code>(nums[w], nums[x])</code> and <code>(nums[y], nums[z])</code> is <strong>maximized</strong>.</p>

<p>Return <em>the <strong>maximum</strong> such product difference</em>.</p>

<p>&nbsp;</p>

<p><strong class="example">Example 1:</strong></p>

<pre>

<strong>Input:</strong> nums = [5,6,2,7,4]

<strong>Output:</strong> 34

<strong>Explanation:</strong> We can choose indices 1 and 3 for the first pair (6, 7) and indices 2 and 4 for the second pair (2, 4).

The product difference is (6 * 7) - (2 * 4) = 34.

</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>

<strong>Input:</strong> nums = [4,2,5,9,7,4,8]

<strong>Output:</strong> 64

<strong>Explanation:</strong> We can choose indices 3 and 6 for the first pair (9, 8) and indices 1 and 5 for the second pair (2, 4).

The product difference is (9 * 8) - (2 * 4) = 64.

</pre>

<p>&nbsp;</p>

<p><strong>Constraints:</strong></p>

<ul>

    <li><code>4 &lt;= nums.length &lt;= 10<sup>4</sup></code></li>

    <li><code>1 &lt;= nums[i] &lt;= 10<sup>4</sup></code></li>

</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def maxProductDifference(self, nums: List[int]) -> int:
        nums.sort()
        return nums[-1] * nums[-2] - nums[0] * nums[1]
```

### **Java**

```java
class Solution {
    public int maxProductDifference(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        return nums[n - 1] * nums[n - 2] - nums[0] * nums[1];
    }
}
```

### **JavaScript**

```js
/**
 * @param {number[]} nums
 * @return {number}
 */
var maxProductDifference = function (nums) {
    nums.sort((a, b) => a - b);
    let n = nums.length;
    let ans = nums[n - 1] * nums[n - 2] - nums[0] * nums[1];
    return ans;
};
```

### **C++**

```cpp
class Solution {
public:
    int maxProductDifference(vector<int>& nums) {
        sort(nums.begin(), nums.end());
        int n = nums.size();
        return nums[n - 1] * nums[n - 2] - nums[0] * nums[1];
    }
};
```

### **Go**

```go
func maxProductDifference(nums []int) int {
	sort.Ints(nums)
	n := len(nums)
	return nums[n-1]*nums[n-2] - nums[0]*nums[1]
}
```

### **...**

```

```

<!-- tabs:end -->
