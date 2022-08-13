# [2348. Number of Zero-Filled Subarrays](https://leetcode.com/problems/number-of-zero-filled-subarrays)

[中文文档](/solution/2300-2399/2348.Number%20of%20Zero-Filled%20Subarrays/README.md)

## Description

<p>Given an integer array <code>nums</code>, return <em>the number of <strong>subarrays</strong> filled with </em><code>0</code>.</p>

<p>A <strong>subarray</strong> is a contiguous non-empty sequence of elements within an array.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,3,0,0,2,0,0,4]
<strong>Output:</strong> 6
<strong>Explanation:</strong> 
There are 4 occurrences of [0] as a subarray.
There are 2 occurrences of [0,0] as a subarray.
There is no occurrence of a subarray with a size more than 2 filled with 0. Therefore, we return 6.</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [0,0,0,2,0,0]
<strong>Output:</strong> 9
<strong>Explanation:
</strong>There are 5 occurrences of [0] as a subarray.
There are 3 occurrences of [0,0] as a subarray.
There is 1 occurrence of [0,0,0] as a subarray.
There is no occurrence of a subarray with a size more than 3 filled with 0. Therefore, we return 9.
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [2,10,2019]
<strong>Output:</strong> 0
<strong>Explanation:</strong> There is no subarray filled with 0. Therefore, we return 0.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>-10<sup>9</sup> &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def zeroFilledSubarray(self, nums: List[int]) -> int:
        ans = 0
        cnt = 0
        for v in nums:
            if v == 0:
                cnt += 1
            else:
                ans += (1 + cnt) * cnt // 2
                cnt = 0
        ans += (1 + cnt) * cnt // 2
        return ans
```

### **Java**

```java
class Solution {
    public long zeroFilledSubarray(int[] nums) {
        long ans = 0;
        int cnt = 0;
        for (int v : nums) {
            if (v == 0) {
                ++cnt;
            } else {
                ans += (long) (1 + cnt) * cnt / 2;
                cnt = 0;
            }
        }
        ans += (long) (1 + cnt) * cnt / 2;
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    long long zeroFilledSubarray(vector<int>& nums) {
        long long ans = 0;
        int cnt = 0;
        for (int v : nums) {
            if (v == 0)
                ++cnt;
            else {
                ans += 1ll * (1 + cnt) * cnt / 2;
                cnt = 0;
            }
        }
        ans += 1ll * (1 + cnt) * cnt / 2;
        return ans;
    }
};
```

### **Go**

```go
func zeroFilledSubarray(nums []int) int64 {
	ans := 0
	cnt := 0
	for _, v := range nums {
		if v == 0 {
			cnt++
		} else {
			ans += (1 + cnt) * cnt / 2
			cnt = 0
		}
	}
	ans += (1 + cnt) * cnt / 2
	return int64(ans)
}
```

### **TypeScript**

```ts

```

### **...**

```

```

<!-- tabs:end -->
