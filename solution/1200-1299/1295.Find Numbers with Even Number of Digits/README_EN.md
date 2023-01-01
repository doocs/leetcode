# [1295. Find Numbers with Even Number of Digits](https://leetcode.com/problems/find-numbers-with-even-number-of-digits)

[中文文档](/solution/1200-1299/1295.Find%20Numbers%20with%20Even%20Number%20of%20Digits/README.md)

## Description

<p>Given an array <code>nums</code> of integers, return how many of them contain an <strong>even number</strong> of digits.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [12,345,2,6,7896]
<strong>Output:</strong> 2
<strong>Explanation: 
</strong>12 contains 2 digits (even number of digits).&nbsp;
345 contains 3 digits (odd number of digits).&nbsp;
2 contains 1 digit (odd number of digits).&nbsp;
6 contains 1 digit (odd number of digits).&nbsp;
7896 contains 4 digits (even number of digits).&nbsp;
Therefore only 12 and 7896 contain an even number of digits.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [555,901,482,1771]
<strong>Output:</strong> 1 
<strong>Explanation: </strong>
Only 1771 contains an even number of digits.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 500</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def findNumbers(self, nums: List[int]) -> int:
        return sum(len(str(v)) % 2 == 0 for v in nums)
```

### **Java**

```java
class Solution {
    public int findNumbers(int[] nums) {
        int ans = 0;
        for (int v : nums) {
            if (String.valueOf(v).length() % 2 == 0) {
                ++ans;
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
    int findNumbers(vector<int>& nums) {
        int ans = 0;
        for (int& v : nums) {
            ans += to_string(v).size() % 2 == 0;
        }
        return ans;
    }
};
```

### **Go**

```go
func findNumbers(nums []int) (ans int) {
	for _, v := range nums {
		if len(strconv.Itoa(v))%2 == 0 {
			ans++
		}
	}
	return
}
```

### **JavaScript**

```js
/**
 * @param {number[]} nums
 * @return {number}
 */
var findNumbers = function (nums) {
    let ans = 0;
    for (const v of nums) {
        ans += String(v).length % 2 == 0;
    }
    return ans;
};
```

### **...**

```

```

<!-- tabs:end -->
