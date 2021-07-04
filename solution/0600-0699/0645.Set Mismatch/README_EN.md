# [645. Set Mismatch](https://leetcode.com/problems/set-mismatch)

[中文文档](/solution/0600-0699/0645.Set%20Mismatch/README.md)

## Description

<p>You have a set of integers <code>s</code>, which originally contains all the numbers from <code>1</code> to <code>n</code>. Unfortunately, due to some error, one of the numbers in <code>s</code> got duplicated to another number in the set, which results in <strong>repetition of one</strong> number and <strong>loss of another</strong> number.</p>

<p>You are given an integer array <code>nums</code> representing the data status of this set after the error.</p>

<p>Find the number that occurs twice and the number that is missing and return <em>them in the form of an array</em>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<pre><strong>Input:</strong> nums = [1,2,2,4]
<strong>Output:</strong> [2,3]
</pre><p><strong>Example 2:</strong></p>
<pre><strong>Input:</strong> nums = [1,1]
<strong>Output:</strong> [1,2]
</pre>
<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= nums.length &lt;= 10<sup>4</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>4</sup></code></li>
</ul>


## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def findErrorNums(self, nums: List[int]) -> List[int]:
        res = 0
        for num in nums:
            res ^= num
        for i in range(1, len(nums) + 1):
            res ^= i
        pos = 0
        while (res & 1) == 0:
            res >>= 1
            pos += 1
        a = b = 0
        for num in nums:
            if ((num >> pos) & 1) == 0:
                a ^= num
            else:
                b ^= num
        for i in range(1, len(nums) + 1):
            if ((i >> pos) & 1) == 0:
                a ^= i
            else:
                b ^= i
        for num in nums:
            if num == a:
                return [a, b]
        return [b, a]
```

### **Java**

```java
class Solution {
    public int[] findErrorNums(int[] nums) {
        int res = 0;
        for (int num : nums) {
            res ^= num;
        }
        for (int i = 1, n = nums.length; i < n + 1; ++i) {
            res ^= i;
        }
        int pos = 0;
        while ((res & 1) == 0) {
            res >>= 1;
            ++pos;
        }
        int a = 0, b = 0;
        for (int num : nums) {
            if (((num >> pos) & 1) == 0) {
                a ^= num;
            } else {
                b ^= num;
            }
        }
        for (int i = 1, n = nums.length; i < n + 1; ++i) {
            if (((i >> pos) & 1) == 0) {
                a ^= i;
            } else {
                b ^= i;
            }
        }
        for (int num : nums) {
            if (num == a) {
                return new int[]{a, b};
            }
        }
        return new int[]{b, a};
    }
}
```

### **Go**

```go
func findErrorNums(nums []int) []int {
	n := len(nums)
	for i := 0; i < n; i++ {
		for nums[i] != i+1 && nums[nums[i]-1] != nums[i] {
			nums[i], nums[nums[i]-1] = nums[nums[i]-1], nums[i]
		}
	}
	for i := 0; i < n; i++ {
		if nums[i] != i+1 {
			return []int{nums[i], i + 1}
		}
	}
	return []int{-1, -1}
}
```

### **...**

```

```

<!-- tabs:end -->
