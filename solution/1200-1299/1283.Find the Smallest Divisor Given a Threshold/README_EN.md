# [1283. Find the Smallest Divisor Given a Threshold](https://leetcode.com/problems/find-the-smallest-divisor-given-a-threshold)

[中文文档](/solution/1200-1299/1283.Find%20the%20Smallest%20Divisor%20Given%20a%20Threshold/README.md)

## Description

<p>Given an array of integers <code>nums</code> and an integer <code>threshold</code>, we will choose a positive integer <code>divisor</code>, divide all the array by it, and sum the division&#39;s result. Find the <strong>smallest</strong> <code>divisor</code> such that the result mentioned above is less than or equal to <code>threshold</code>.</p>

<p>Each result of the division is rounded to the nearest integer greater than or equal to that element. (For example: <code>7/3 = 3</code> and <code>10/2 = 5</code>).</p>

<p>The test cases are generated so&nbsp;that there will be an answer.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,5,9], threshold = 6
<strong>Output:</strong> 5
<strong>Explanation:</strong> We can get a sum to 17 (1+2+5+9) if the divisor is 1. 
If the divisor is 4 we can get a sum of 7 (1+1+2+3) and if the divisor is 5 the sum will be 5 (1+1+1+2). 
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [44,22,33,11,1], threshold = 5
<strong>Output:</strong> 44
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>6</sup></code></li>
	<li><code>nums.length &lt;= threshold &lt;= 10<sup>6</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def smallestDivisor(self, nums: List[int], threshold: int) -> int:
        left, right = 1, 10**6
        while left < right:
            mid = (left + right) >> 1
            s = sum((v + mid - 1) // mid for v in nums)
            if s <= threshold:
                right = mid
            else:
                left = mid + 1
        return left
```

### **Java**

```java
class Solution {
    public int smallestDivisor(int[] nums, int threshold) {
        int left = 1, right = 1000000;
        while (left < right) {
            int mid = (left + right) >> 1;
            int s = 0;
            for (int v : nums) {
                s += (v + mid - 1) / mid;
            }
            if (s <= threshold) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int smallestDivisor(vector<int>& nums, int threshold) {
        int left = 1, right = 1e6;
        while (left < right) {
            int mid = (left + right) >> 1;
            int s = 0;
            for (int& v : nums) s += (v + mid - 1) / mid;
            if (s <= threshold)
                right = mid;
            else
                left = mid + 1;
        }
        return left;
    }
};
```

### **Go**

```go
func smallestDivisor(nums []int, threshold int) int {
	left, right := 1, 1000000
	for left < right {
		mid := (left + right) >> 1
		s := 0
		for _, v := range nums {
			s += (v + mid - 1) / mid
		}
		if s <= threshold {
			right = mid
		} else {
			left = mid + 1
		}
	}
	return left
}
```

### **JavaScript**

```js
/**
 * @param {number[]} nums
 * @param {number} threshold
 * @return {number}
 */
var smallestDivisor = function (nums, threshold) {
    let left = 1,
        right = 1000000;
    while (left < right) {
        const mid = (left + right) >> 1;
        let s = 0;
        for (let v of nums) {
            s += Math.ceil(v / mid);
        }
        if (s <= threshold) {
            right = mid;
        } else {
            left = mid + 1;
        }
    }
    return left;
};
```

### **TypeScript**

```ts
function smallestDivisor(nums: number[], threshold: number): number {
    let left = 1,
        right = 1000000;
    while (left < right) {
        const mid = (left + right) >> 1;
        let s = 0;
        for (let v of nums) {
            s += Math.ceil(v / mid);
        }
        if (s <= threshold) {
            right = mid;
        } else {
            left = mid + 1;
        }
    }
    return left;
}
```

### **...**

```

```

<!-- tabs:end -->
