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
        s = 0
        for i, x in enumerate(arr):
            s = max(s, 0) + x
            left[i] = s
        s = 0
        for i in range(n - 1, -1, -1):
            s = max(s, 0) + arr[i]
            right[i] = s
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
        int ans = -(1 << 30);
        for (int i = 0, s = 0; i < n; ++i) {
            s = Math.max(s, 0) + arr[i];
            left[i] = s;
            ans = Math.max(ans, left[i]);
        }
        for (int i = n - 1, s = 0; i >= 0; --i) {
            s = Math.max(s, 0) + arr[i];
            right[i] = s;
        }
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
        for (int i = 0, s = 0; i < n; ++i) {
            s = max(s, 0) + arr[i];
            left[i] = s;
        }
        for (int i = n - 1, s = 0; ~i; --i) {
            s = max(s, 0) + arr[i];
            right[i] = s;
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
	for i, s := 0, 0; i < n; i++ {
		s = max(s, 0) + arr[i]
		left[i] = s
	}
	for i, s := n-1, 0; i >= 0; i-- {
		s = max(s, 0) + arr[i]
		right[i] = s
	}
	ans := slices.Max(left)
	for i := 1; i < n-1; i++ {
		ans = max(ans, left[i-1]+right[i+1])
	}
	return ans
}
```

### **TypeScript**

```ts
function maximumSum(arr: number[]): number {
    const n = arr.length;
    const left: number[] = Array(n).fill(0);
    const right: number[] = Array(n).fill(0);
    for (let i = 0, s = 0; i < n; ++i) {
        s = Math.max(s, 0) + arr[i];
        left[i] = s;
    }
    for (let i = n - 1, s = 0; i >= 0; --i) {
        s = Math.max(s, 0) + arr[i];
        right[i] = s;
    }
    let ans = Math.max(...left);
    for (let i = 1; i < n - 1; ++i) {
        ans = Math.max(ans, left[i - 1] + right[i + 1]);
    }
    return ans;
}
```

### **...**

```

```

<!-- tabs:end -->
