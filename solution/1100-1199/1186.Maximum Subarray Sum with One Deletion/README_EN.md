# [1186. Maximum Subarray Sum with One Deletion](https://leetcode.com/problems/maximum-subarray-sum-with-one-deletion)

[中文文档](/solution/1100-1199/1186.Maximum%20Subarray%20Sum%20with%20One%20Deletion/README.md)

## Description

<p>Given an array of integers, return the maximum sum for a <strong>non-empty</strong>&nbsp;subarray (contiguous elements) with at most one element deletion.&nbsp;In other words, you want to choose a subarray and optionally delete one element from it so that there is still at least one element left and the&nbsp;sum of the remaining elements is maximum possible.</p>

<p>Note that the subarray needs to be <strong>non-empty</strong> after deleting one element.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> arr = [1,-2,0,3]
<strong>Output:</strong> 4
<strong>Explanation: </strong>Because we can choose [1, -2, 0, 3] and drop -2, thus the subarray [1, 0, 3] becomes the maximum value.</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> arr = [1,-2,-2,3]
<strong>Output:</strong> 3
<strong>Explanation: </strong>We just choose [3] and it&#39;s the maximum sum.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> arr = [-1,-1,-1,-1]
<strong>Output:</strong> -1
<strong>Explanation:</strong>&nbsp;The final subarray needs to be non-empty. You can&#39;t choose [-1] and delete -1 from it, then get an empty subarray to make the sum equals to 0.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= arr.length &lt;= 10<sup>5</sup></code></li>
	<li><code>-10<sup>4</sup> &lt;= arr[i] &lt;= 10<sup>4</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def maximumSum(self, arr: List[int]) -> int:
        n = len(arr)
        left = [0] * n
        right = [0] * n
        t = 0
        for i, v in enumerate(arr):
            t = max(t, 0) + v
            left[i] = t
        t = 0
        for i in range(n - 1, -1, -1):
            t = max(t, 0) + arr[i]
            right[i] = t
        ans = max(left)
        for i in range(1, n - 1):
            ans = max(ans, left[i - 1] + right[i + 1])
        return ans
```

### **Java**

```java
class Solution {
    public int maximumSum(int[] arr) {
        int n = arr.length;
        int[] left = new int[n];
        int[] right = new int[n];
        int t = 0;
        for (int i = 0; i < n; ++i) {
            t = Math.max(t, 0) + arr[i];
            left[i] = t;
        }
        t = 0;
        for (int i = n - 1; i >= 0; --i) {
            t = Math.max(t, 0) + arr[i];
            right[i] = t;
        }
        int ans = Arrays.stream(left).max().getAsInt();
        for (int i = 1; i < n - 1; ++i) {
            ans = Math.max(ans, left[i - 1] + right[i + 1]);
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int maximumSum(vector<int>& arr) {
        int n = arr.size();
        int left[n];
        int right[n];
        for (int i = 0, t = 0; i < n; ++i) {
            t = max(t, 0) + arr[i];
            left[i] = t;
        }
        for (int i = n - 1, t = 0; ~i; --i) {
            t = max(t, 0) + arr[i];
            right[i] = t;
        }
        int ans = *max_element(left, left + n);
        for (int i = 1; i < n - 1; ++i) {
            ans = max(ans, left[i - 1] + right[i + 1]);
        }
        return ans;
    }
};
```

### **Go**

```go
func maximumSum(arr []int) int {
	n := len(arr)
	left := make([]int, n)
	right := make([]int, n)
	t := 0
	ans := math.MinInt32
	for i, v := range arr {
		t = max(t, 0) + v
		left[i] = t
		ans = max(ans, left[i])
	}
	t = 0
	for i := n - 1; i >= 0; i-- {
		t = max(t, 0) + arr[i]
		right[i] = t
	}
	for i := 1; i < n-1; i++ {
		ans = max(ans, left[i-1]+right[i+1])
	}
	return ans
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
