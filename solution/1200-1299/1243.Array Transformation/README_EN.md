# [1243. Array Transformation](https://leetcode.com/problems/array-transformation)

[中文文档](/solution/1200-1299/1243.Array%20Transformation/README.md)

## Description

<p>Given an initial array <code>arr</code>, every day you produce a new array using the array of the previous day.</p>

<p>On the <code>i</code>-th day, you do the following operations on the array of day&nbsp;<code>i-1</code>&nbsp;to produce the array of day <code>i</code>:</p>

<ol>
	<li>If an element is smaller than both its left neighbor and its right neighbor, then this element is incremented.</li>
	<li>If an element is bigger than both its left neighbor and its right neighbor, then this element is decremented.</li>
	<li>The first&nbsp;and last elements never change.</li>
</ol>

<p>After some days, the array does not change. Return that final array.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> arr = [6,2,3,4]
<strong>Output:</strong> [6,3,3,4]
<strong>Explanation: </strong>
On the first day, the array is changed from [6,2,3,4] to [6,3,3,4].
No more operations can be done to this array.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> arr = [1,6,3,4,3,5]
<strong>Output:</strong> [1,4,4,4,4,5]
<strong>Explanation: </strong>
On the first day, the array is changed from [1,6,3,4,3,5] to [1,5,4,3,4,5].
On the second day, the array is changed from [1,5,4,3,4,5] to [1,4,4,4,4,5].
No more operations can be done to this array.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>3 &lt;= arr.length &lt;= 100</code></li>
	<li><code>1 &lt;= arr[i] &lt;= 100</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def transformArray(self, arr: List[int]) -> List[int]:
        f = True
        while f:
            f = False
            t = arr[:]
            for i in range(1, len(t) - 1):
                if t[i] > t[i - 1] and t[i] > t[i + 1]:
                    arr[i] -= 1
                    f = True
                if t[i] < t[i - 1] and t[i] < t[i + 1]:
                    arr[i] += 1
                    f = True
        return arr
```

### **Java**

```java
class Solution {
    public List<Integer> transformArray(int[] arr) {
        boolean f = true;
        while (f) {
            f = false;
            int[] t = arr.clone();
            for (int i = 1; i < t.length - 1; ++i) {
                if (t[i] > t[i - 1] && t[i] > t[i + 1]) {
                    --arr[i];
                    f = true;
                }
                if (t[i] < t[i - 1] && t[i] < t[i + 1]) {
                    ++arr[i];
                    f = true;
                }
            }
        }
        List<Integer> ans = new ArrayList<>();
        for (int x : arr) {
            ans.add(x);
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<int> transformArray(vector<int>& arr) {
        bool f = true;
        while (f) {
            f = false;
            vector<int> t = arr;
            for (int i = 1; i < arr.size() - 1; ++i) {
                if (t[i] > t[i - 1] && t[i] > t[i + 1]) {
                    --arr[i];
                    f = true;
                }
                if (t[i] < t[i - 1] && t[i] < t[i + 1]) {
                    ++arr[i];
                    f = true;
                }
            }
        }
        return arr;
    }
};
```

### **Go**

```go
func transformArray(arr []int) []int {
	f := true
	for f {
		f = false
		t := make([]int, len(arr))
		copy(t, arr)
		for i := 1; i < len(arr)-1; i++ {
			if t[i] > t[i-1] && t[i] > t[i+1] {
				arr[i]--
				f = true
			}
			if t[i] < t[i-1] && t[i] < t[i+1] {
				arr[i]++
				f = true
			}
		}
	}
	return arr
}
```

### **...**

```

```

<!-- tabs:end -->
