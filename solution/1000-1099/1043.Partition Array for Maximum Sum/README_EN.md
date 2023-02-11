# [1043. Partition Array for Maximum Sum](https://leetcode.com/problems/partition-array-for-maximum-sum)

[中文文档](/solution/1000-1099/1043.Partition%20Array%20for%20Maximum%20Sum/README.md)

## Description

<p>Given an integer array <code>arr</code>, partition the array into (contiguous) subarrays of length <strong>at most</strong> <code>k</code>. After partitioning, each subarray has their values changed to become the maximum value of that subarray.</p>

<p>Return <em>the largest sum of the given array after partitioning. Test cases are generated so that the answer fits in a <strong>32-bit</strong> integer.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> arr = [1,15,7,9,2,5,10], k = 3
<strong>Output:</strong> 84
<strong>Explanation:</strong> arr becomes [15,15,15,9,10,10,10]
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> arr = [1,4,1,5,7,3,6,1,9,9,3], k = 4
<strong>Output:</strong> 83
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> arr = [1], k = 1
<strong>Output:</strong> 1
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= arr.length &lt;= 500</code></li>
	<li><code>0 &lt;= arr[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= k &lt;= arr.length</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def maxSumAfterPartitioning(self, arr: List[int], k: int) -> int:
        n = len(arr)
        f = [0] * (n + 1)
        for i in range(n):
            mx = 0
            for j in range(i, max(-1, i - k), -1):
                mx = max(mx, arr[j])
                t = mx * (i - j + 1) + f[j]
                f[i + 1] = max(f[i + 1], t)
        return f[n]
```

### **Java**

```java
class Solution {
    public int maxSumAfterPartitioning(int[] arr, int k) {
        int n = arr.length;
        int[] f = new int[n + 1];
        for (int i = 0; i < n; ++i) {
            int mx = 0;
            for (int j = i; j >= Math.max(0, i - k + 1); --j) {
                mx = Math.max(mx, arr[j]);
                int t = mx * (i - j + 1) + f[j];
                f[i + 1] = Math.max(f[i + 1], t);
            }
        }
        return f[n];
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int maxSumAfterPartitioning(vector<int>& arr, int k) {
        int n = arr.size();
        int f[n + 1];
        memset(f, 0, sizeof f);
        for (int i = 0; i < n; ++i) {
            int mx = 0;
            for (int j = i; j >= max(0, i - k + 1); --j) {
                mx = max(mx, arr[j]);
                int t = mx * (i - j + 1) + f[j];
                f[i + 1] = max(f[i + 1], t);
            }
        }
        return f[n];
    }
};
```

### **Go**

```go
func maxSumAfterPartitioning(arr []int, k int) int {
	n := len(arr)
	f := make([]int, n+1)
	for i := 0; i < n; i++ {
		mx := 0
		for j := i; j >= max(0, i-k+1); j-- {
			mx = max(mx, arr[j])
			t := mx*(i-j+1) + f[j]
			f[i+1] = max(f[i+1], t)
		}
	}
	return f[n]
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
