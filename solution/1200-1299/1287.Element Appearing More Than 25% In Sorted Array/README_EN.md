# [1287. Element Appearing More Than 25% In Sorted Array](https://leetcode.com/problems/element-appearing-more-than-25-in-sorted-array)

[中文文档](/solution/1200-1299/1287.Element%20Appearing%20More%20Than%2025%25%20In%20Sorted%20Array/README.md)

## Description

<p>Given an integer array <strong>sorted</strong> in non-decreasing order, there is exactly one integer in the array that occurs more than 25% of the time, return that integer.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> arr = [1,2,2,6,6,6,6,7,10]
<strong>Output:</strong> 6
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> arr = [1,1]
<strong>Output:</strong> 1
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= arr.length &lt;= 10<sup>4</sup></code></li>
	<li><code>0 &lt;= arr[i] &lt;= 10<sup>5</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def findSpecialInteger(self, arr: List[int]) -> int:
        n = len(arr)
        for i, val in enumerate(arr):
            if val == arr[i + (n >> 2)]:
                return val
        return 0
```

### **Java**

```java
class Solution {
    public int findSpecialInteger(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n; ++i) {
            if (arr[i] == arr[i + (n >> 2)]) {
                return arr[i];
            }
        }
        return 0;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int findSpecialInteger(vector<int>& arr) {
        int n = arr.size();
        for (int i = 0; i < n; ++i)
            if (arr[i] == arr[i + (n >> 2)]) return arr[i];
        return 0;
    }
};
```

### **Go**

```go
func findSpecialInteger(arr []int) int {
	n := len(arr)
	for i, val := range arr {
		if val == arr[i+(n>>2)] {
			return val
		}
	}
	return 0
}
```

### **JavaScript**

```js
/**
 * @param {number[]} arr
 * @return {number}
 */
var findSpecialInteger = function (arr) {
    const n = arr.length;
    for (let i = 0; i < n; ++i) {
        if (arr[i] == arr[i + (n >> 2)]) {
            return arr[i];
        }
    }
    return 0;
};
```

### **...**

```

```

<!-- tabs:end -->
