# [260. Single Number III](https://leetcode.com/problems/single-number-iii)

[中文文档](/solution/0200-0299/0260.Single%20Number%20III/README.md)

## Description

<p>Given an integer array <code>nums</code>, in which exactly two elements appear only once and all the other elements appear exactly twice. Find the two elements that appear only once. You can return the answer in <strong>any order</strong>.</p>

<p>You must write an&nbsp;algorithm that runs in linear runtime complexity and uses&nbsp;only constant extra space.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,1,3,2,5]
<strong>Output:</strong> [3,5]
<strong>Explanation: </strong> [5, 3] is also a valid answer.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [-1,0]
<strong>Output:</strong> [-1,0]
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [0,1]
<strong>Output:</strong> [1,0]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= nums.length &lt;= 3 * 10<sup>4</sup></code></li>
	<li><code>-2<sup>31</sup> &lt;= nums[i] &lt;= 2<sup>31</sup> - 1</code></li>
	<li>Each integer in <code>nums</code> will appear twice, only two integers will appear once.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def singleNumber(self, nums: List[int]) -> List[int]:
        eor = 0
        for x in nums:
            eor ^= x
        lowbit = eor & (-eor)
        ans = [0, 0]
        for x in nums:
            if (x & lowbit) == 0:
                ans[0] ^= x
        ans[1] = eor ^ ans[0]
        return ans
```

### **Java**

```java
class Solution {
    public int[] singleNumber(int[] nums) {
        int eor = 0;
        for (int x : nums) {
            eor ^= x;
        }
        int lowbit = eor & (-eor);
        int[] ans = new int[2];
        for (int x : nums) {
            if ((x & lowbit) == 0) {
                ans[0] ^= x;
            }
        }
        ans[1] = eor ^ ans[0];
        return ans;
    }
}
```

### **JavaScript**

```js
/**
 * @param {number[]} nums
 * @return {number[]}
 */
var singleNumber = function (nums) {
    let eor = 0;
    for (const x of nums) {
        eor ^= x;
    }
    const lowbit = eor & -eor;
    let ans = [0];
    for (const x of nums) {
        if ((x & lowbit) == 0) {
            ans[0] ^= x;
        }
    }
    ans.push(eor ^ ans[0]);
    return ans;
};
```

### **C++**

```cpp
class Solution {
public:
    vector<int> singleNumber(vector<int>& nums) {
        long long eor = 0;
        for (int x : nums) eor ^= x;
        int lowbit = eor & (-eor);
        vector<int> ans(2);
        for (int x : nums)
            if ((x & lowbit) == 0) ans[0] ^= x;
        ans[1] = eor ^ ans[0];
        return ans;
    }
};
```

### **Go**

```go
func singleNumber(nums []int) []int {
	eor := 0
	for _, x := range nums {
		eor ^= x
	}
	lowbit := eor & (-eor)
	ans := make([]int, 2)
	for _, x := range nums {
		if (x & lowbit) == 0 {
			ans[0] ^= x
		}
	}
	ans[1] = eor ^ ans[0]
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
