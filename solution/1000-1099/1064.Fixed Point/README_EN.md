# [1064. Fixed Point](https://leetcode.com/problems/fixed-point)

[中文文档](/solution/1000-1099/1064.Fixed%20Point/README.md)

## Description

<p>Given an array of distinct integers <code>arr</code>, where <code>arr</code> is sorted in <strong>ascending order</strong>, return the smallest index <code>i</code> that satisfies <code>arr[i] == i</code>. If there is no such index, return <code>-1</code>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> arr = [-10,-5,0,3,7]
<strong>Output:</strong> 3
<strong>Explanation:</strong> For the given array, <code>arr[0] = -10, arr[1] = -5, arr[2] = 0, arr[3] = 3</code>, thus the output is 3.</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> arr = [0,2,5,8,17]
<strong>Output:</strong> 0
<strong>Explanation:</strong> <code>arr[0] = 0</code>, thus the output is 0.</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> arr = [-10,-5,3,4,7,9]
<strong>Output:</strong> -1
<strong>Explanation:</strong> There is no such <code>i</code> that <code>arr[i] == i</code>, thus the output is -1.</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= arr.length &lt; 10<sup>4</sup></code></li>
	<li><code>-10<sup>9</sup> &lt;= arr[i] &lt;= 10<sup>9</sup></code></li>
</ul>

<p>&nbsp;</p>
<strong>Follow up:</strong> The <code>O(n)</code> solution is very straightforward. Can we do better?

## Solutions

Binary search.

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def fixedPoint(self, arr: List[int]) -> int:
        left, right = 0, len(arr) - 1
        while left < right:
            mid = (left + right) >> 1
            if arr[mid] >= mid:
                right = mid
            else:
                left = mid + 1
        return left if arr[left] == left else -1
```

### **Java**

```java
class Solution {
    public int fixedPoint(int[] arr) {
        int left = 0, right = arr.length - 1;
        while (left < right) {
            int mid = (left + right) >> 1;
            if (arr[mid] >= mid) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return arr[left] == left ? left : -1;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int fixedPoint(vector<int>& arr) {
        int left = 0, right = arr.size() - 1;
        while (left < right) {
            int mid = left + right >> 1;
            if (arr[mid] >= mid) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return arr[left] == left ? left : -1;
    }
};
```

### **Go**

```go
func fixedPoint(arr []int) int {
	left, right := 0, len(arr)-1
	for left < right {
		mid := (left + right) >> 1
		if arr[mid] >= mid {
			right = mid
		} else {
			left = mid + 1
		}
	}
	if arr[left] == left {
		return left
	}
	return -1
}
```

### **...**

```

```

<!-- tabs:end -->
