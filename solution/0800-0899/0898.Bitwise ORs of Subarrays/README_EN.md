# [898. Bitwise ORs of Subarrays](https://leetcode.com/problems/bitwise-ors-of-subarrays)

[中文文档](/solution/0800-0899/0898.Bitwise%20ORs%20of%20Subarrays/README.md)

## Description

<p>Given an integer array <code>arr</code>, return <em>the number of distinct bitwise ORs of all the non-empty subarrays of</em> <code>arr</code>.</p>

<p>The bitwise OR of a subarray is the bitwise OR of each integer in the subarray. The bitwise OR of a subarray of one integer is that integer.</p>

<p>A <strong>subarray</strong> is a contiguous non-empty sequence of elements within an array.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> arr = [0]
<strong>Output:</strong> 1
<strong>Explanation:</strong> There is only one possible result: 0.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> arr = [1,1,2]
<strong>Output:</strong> 3
<strong>Explanation:</strong> The possible subarrays are [1], [1], [2], [1, 1], [1, 2], [1, 1, 2].
These yield the results 1, 1, 2, 1, 3, 3.
There are 3 unique values, so the answer is 3.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> arr = [1,2,4]
<strong>Output:</strong> 6
<strong>Explanation:</strong> The possible results are 1, 2, 3, 4, 6, and 7.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= arr.length &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>0 &lt;= arr[i] &lt;= 10<sup>9</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def subarrayBitwiseORs(self, arr: List[int]) -> int:
        s = set()
        prev = 0
        for i, v in enumerate(arr):
            prev |= v
            curr = 0
            for j in range(i, -1, -1):
                curr |= arr[j]
                s.add(curr)
                if curr == prev:
                    break
        return len(s)
```

### **Java**

```java
class Solution {
    public int subarrayBitwiseORs(int[] arr) {
        Set<Integer> s = new HashSet<>();
        int prev = 0;
        for (int i = 0; i < arr.length; ++i) {
            prev |= arr[i];
            int curr = 0;
            for (int j = i; j >= 0; --j) {
                curr |= arr[j];
                s.add(curr);
                if (curr == prev) {
                    break;
                }
            }
        }
        return s.size();
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int subarrayBitwiseORs(vector<int>& arr) {
        unordered_set<int> s;
        int prev = 0;
        for (int i = 0; i < arr.size(); ++i) {
            prev |= arr[i];
            int curr = 0;
            for (int j = i; ~j; --j) {
                curr |= arr[j];
                s.insert(curr);
                if (curr == prev) break;
            }
        }
        return s.size();
    }
};
```

### **Go**

```go
func subarrayBitwiseORs(arr []int) int {
	s := map[int]bool{}
	prev := 0
	for i, v := range arr {
		prev |= v
		curr := 0
		for j := i; j >= 0; j-- {
			curr |= arr[j]
			s[curr] = true
			if curr == prev {
				break
			}
		}
	}
	return len(s)
}
```

### **...**

```

```

<!-- tabs:end -->
