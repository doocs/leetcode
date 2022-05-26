# [941. Valid Mountain Array](https://leetcode.com/problems/valid-mountain-array)

[中文文档](/solution/0900-0999/0941.Valid%20Mountain%20Array/README.md)

## Description

<p>Given an array of integers <code>arr</code>, return <em><code>true</code> if and only if it is a valid mountain array</em>.</p>

<p>Recall that arr is a mountain array if and only if:</p>

<ul>
	<li><code>arr.length &gt;= 3</code></li>
	<li>There exists some <code>i</code> with <code>0 &lt; i &lt; arr.length - 1</code> such that:
	<ul>
		<li><code>arr[0] &lt; arr[1] &lt; ... &lt; arr[i - 1] &lt; arr[i] </code></li>
		<li><code>arr[i] &gt; arr[i + 1] &gt; ... &gt; arr[arr.length - 1]</code></li>
	</ul>
	</li>
</ul>
<img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0900-0999/0941.Valid%20Mountain%20Array/images/hint_valid_mountain_array.png" width="500" />
<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<pre><strong>Input:</strong> arr = [2,1]
<strong>Output:</strong> false
</pre><p><strong>Example 2:</strong></p>
<pre><strong>Input:</strong> arr = [3,5,5]
<strong>Output:</strong> false
</pre><p><strong>Example 3:</strong></p>
<pre><strong>Input:</strong> arr = [0,3,2,1]
<strong>Output:</strong> true
</pre>
<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= arr.length &lt;= 10<sup>4</sup></code></li>
	<li><code>0 &lt;= arr[i] &lt;= 10<sup>4</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def validMountainArray(self, arr: List[int]) -> bool:
        n = len(arr)
        if n < 3:
            return False
        l, r = 0, n - 1
        while l + 1 < n - 1 and arr[l] < arr[l + 1]:
            l += 1
        while r - 1 > 0 and arr[r] < arr[r - 1]:
            r -= 1
        return l == r
```

### **Java**

```java
class Solution {

    public boolean validMountainArray(int[] arr) {
        int n = arr.length;
        if (n < 3) {
            return false;
        }
        int l = 0, r = n - 1;
        while (l + 1 < n - 1 && arr[l] < arr[l + 1]) {
            ++l;
        }
        while (r - 1 > 0 && arr[r] < arr[r - 1]) {
            --r;
        }
        return l == r;
    }
}

```

### **C++**

```cpp
class Solution {
public:
    bool validMountainArray(vector<int>& arr) {
        int n = arr.size();
        if (n < 3) return 0;
        int l = 0, r = n - 1;
        while (l + 1 < n - 1 && arr[l] < arr[l + 1]) ++l;
        while (r - 1 > 0 && arr[r] < arr[r - 1]) --r;
        return l == r;
    }
};
```

### **Go**

```go
func validMountainArray(arr []int) bool {
	n := len(arr)
	if n < 3 {
		return false
	}
	l, r := 0, n-1
	for l+1 < n-1 && arr[l] < arr[l+1] {
		l++
	}
	for r-1 > 0 && arr[r] < arr[r-1] {
		r--
	}
	return l == r
}
```

### **...**

```

```

<!-- tabs:end -->
