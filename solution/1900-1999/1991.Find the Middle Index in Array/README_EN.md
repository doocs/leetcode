# [1991. Find the Middle Index in Array](https://leetcode.com/problems/find-the-middle-index-in-array)

[中文文档](/solution/1900-1999/1991.Find%20the%20Middle%20Index%20in%20Array/README.md)

## Description

<p>Given a <strong>0-indexed</strong> integer array <code>nums</code>, find the <strong>leftmost</strong> <code>middleIndex</code> (i.e., the smallest amongst all the possible ones).</p>

<p>A <code>middleIndex</code> is an index where <code>nums[0] + nums[1] + ... + nums[middleIndex-1] == nums[middleIndex+1] + nums[middleIndex+2] + ... + nums[nums.length-1]</code>.</p>

<p>If <code>middleIndex == 0</code>, the left side sum is considered to be <code>0</code>. Similarly, if <code>middleIndex == nums.length - 1</code>, the right side sum is considered to be <code>0</code>.</p>

<p>Return <em>the <strong>leftmost</strong> </em><code>middleIndex</code><em> that satisfies the condition, or </em><code>-1</code><em> if there is no such index</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [2,3,-1,<u>8</u>,4]
<strong>Output:</strong> 3
<strong>Explanation:</strong> The sum of the numbers before index 3 is: 2 + 3 + -1 = 4
The sum of the numbers after index 3 is: 4 = 4
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,-1,<u>4</u>]
<strong>Output:</strong> 2
<strong>Explanation:</strong> The sum of the numbers before index 2 is: 1 + -1 = 0
The sum of the numbers after index 2 is: 0
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [2,5]
<strong>Output:</strong> -1
<strong>Explanation:</strong> There is no valid middleIndex.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 100</code></li>
	<li><code>-1000 &lt;= nums[i] &lt;= 1000</code></li>
</ul>

<p>&nbsp;</p>
<p><strong>Note:</strong> This question is the same as&nbsp;724:&nbsp;<a href="https://leetcode.com/problems/find-pivot-index/" target="_blank">https://leetcode.com/problems/find-pivot-index/</a></p>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def findMiddleIndex(self, nums: List[int]) -> int:
        left, right = 0, sum(nums)
        for i, x in enumerate(nums):
            right -= x
            if left == right:
                return i
            left += x
        return -1
```

### **Java**

```java
class Solution {
    public int findMiddleIndex(int[] nums) {
        int left = 0, right = Arrays.stream(nums).sum();
        for (int i = 0; i < nums.length; ++i) {
            right -= nums[i];
            if (left == right) {
                return i;
            }
            left += nums[i];
        }
        return -1;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int findMiddleIndex(vector<int>& nums) {
        int left = 0, right = accumulate(nums.begin(), nums.end(), 0);
        for (int i = 0; i < nums.size(); ++i) {
            right -= nums[i];
            if (left == right) {
                return i;
            }
            left += nums[i];
        }
        return -1;
    }
};
```

### **Go**

```go
func findMiddleIndex(nums []int) int {
	var left, right int
	for _, x := range nums {
		right += x
	}
	for i, x := range nums {
		right -= x
		if left == right {
			return i
		}
		left += x
	}
	return -1
}
```

### **JavaScript**

```js
/**
 * @param {number[]} nums
 * @return {number}
 */
var findMiddleIndex = function (nums) {
    let left = 0,
        right = nums.reduce((a, b) => a + b);
    for (let i = 0; i < nums.length; ++i) {
        right -= nums[i];
        if (left == right) {
            return i;
        }
        left += nums[i];
    }
    return -1;
};
```

### **TypeScript**

```ts
function findMiddleIndex(nums: number[]): number {
    let left = 0,
        right = nums.reduce((a, b) => a + b);
    for (let i = 0; i < nums.length; ++i) {
        right -= nums[i];
        if (left == right) {
            return i;
        }
        left += nums[i];
    }
    return -1;
}
```

### **...**

```

```

<!-- tabs:end -->
