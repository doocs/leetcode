# [1588. Sum of All Odd Length Subarrays](https://leetcode.com/problems/sum-of-all-odd-length-subarrays)

[中文文档](/solution/1500-1599/1588.Sum%20of%20All%20Odd%20Length%20Subarrays/README.md)

## Description

<p>Given an array of positive integers&nbsp;<code>arr</code>, calculate the sum of all possible odd-length subarrays.</p>

<p>A subarray is a contiguous&nbsp;subsequence of the array.</p>

<p>Return&nbsp;<em>the sum of all odd-length subarrays of&nbsp;</em><code>arr</code>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> arr = [1,4,2,5,3]
<strong>Output:</strong> 58
<strong>Explanation: </strong>The odd-length subarrays of arr and their sums are:
[1] = 1
[4] = 4
[2] = 2
[5] = 5
[3] = 3
[1,4,2] = 7
[4,2,5] = 11
[2,5,3] = 10
[1,4,2,5,3] = 15
If we add all these together we get 1 + 4 + 2 + 5 + 3 + 7 + 11 + 10 + 15 = 58</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> arr = [1,2]
<strong>Output:</strong> 3
<b>Explanation: </b>There are only 2 subarrays of odd length, [1] and [2]. Their sum is 3.</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> arr = [10,11,12]
<strong>Output:</strong> 66
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= arr.length &lt;= 100</code></li>
	<li><code>1 &lt;= arr[i] &lt;= 1000</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def sumOddLengthSubarrays(self, arr: List[int]) -> int:
        n = len(arr)
        presum = [0] * (n + 1)
        for i in range(n):
            presum[i + 1] = presum[i] + arr[i]

        res = 0
        for i in range(n):
            for j in range(0, n, 2):
                if i + j < n:
                    res += presum[i + j + 1] - presum[i]
        return res
```

### **Java**

```java
class Solution {
    public int sumOddLengthSubarrays(int[] arr) {
        int n = arr.length;
        int[] presum = new int[n + 1];
        for (int i = 0; i < n; ++i) {
            presum[i + 1] = presum[i] + arr[i];
        }
        int res = 0;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; i + j < n; j += 2) {
                res += presum[i + j + 1] - presum[i];
            }
        }
        return res;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int sumOddLengthSubarrays(vector<int>& arr) {
        int n = arr.size();
        int presum[n + 1];
        for (int i = 0; i < n; ++i) presum[i + 1] = presum[i] + arr[i];
        int res = 0;
        for (int i = 0; i < n; ++i)
        {
            for (int j = 0; i + j < n; j += 2)
            {
                res += presum[i + j + 1] - presum[i];
            }
        }
        return res;
    }
};
```

### **Go**

```go
func sumOddLengthSubarrays(arr []int) int {
	n := len(arr)
	presum := make([]int, n+1)
	for i := range arr {
		presum[i+1] = presum[i] + arr[i]
	}
	res := 0
	for i := 0; i < n; i++ {
		for j := 0; i+j < n; j += 2 {
			res += presum[i+j+1] - presum[i]
		}
	}
	return res
}
```

### **...**

```

```

<!-- tabs:end -->
