# [2239. Find Closest Number to Zero](https://leetcode.com/problems/find-closest-number-to-zero)

[中文文档](/solution/2200-2299/2239.Find%20Closest%20Number%20to%20Zero/README.md)

## Description

<p>Given an integer array <code>nums</code> of size <code>n</code>, return <em>the number with the value <strong>closest</strong> to </em><code>0</code><em> in </em><code>nums</code>. If there are multiple answers, return <em>the number with the <strong>largest</strong> value</em>.</p>
<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [-4,-2,1,4,8]
<strong>Output:</strong> 1
<strong>Explanation:</strong>
The distance from -4 to 0 is |-4| = 4.
The distance from -2 to 0 is |-2| = 2.
The distance from 1 to 0 is |1| = 1.
The distance from 4 to 0 is |4| = 4.
The distance from 8 to 0 is |8| = 8.
Thus, the closest number to 0 in the array is 1.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [2,-1,1]
<strong>Output:</strong> 1
<strong>Explanation:</strong> 1 and -1 are both the closest numbers to 0, so 1 being larger is returned.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 1000</code></li>
	<li><code>-10<sup>5</sup> &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def findClosestNumber(self, nums: List[int]) -> int:
        ans, d = 0, 1000000
        for v in nums:
            if (t := abs(v)) < d or (t == d and v > ans):
                ans, d = v, t
        return ans
```

### **Java**

```java
class Solution {
    public int findClosestNumber(int[] nums) {
        int ans = 0, d = 1000000;
        for (int v : nums) {
            int t = Math.abs(v);
            if (t < d || (t == d && v > ans)) {
                ans = v;
                d = t;
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
    int findClosestNumber(vector<int>& nums) {
        int ans = 0, d = 1e6;
        for (int& v : nums) {
            int t = abs(v);
            if (t < d || (t == d && v > ans)) {
                ans = v;
                d = t;
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func findClosestNumber(nums []int) int {
	ans, d := 0, 1000000
	for _, v := range nums {
		t := abs(v)
		if t < d || (t == d && v > ans) {
			ans, d = v, t
		}
	}
	return ans
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}
```

### **TypeScript**

```ts

```

### **...**

```

```

<!-- tabs:end -->
