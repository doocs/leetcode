# [1122. Relative Sort Array](https://leetcode.com/problems/relative-sort-array)

[中文文档](/solution/1100-1199/1122.Relative%20Sort%20Array/README.md)

## Description

<p>Given two arrays <code>arr1</code> and <code>arr2</code>, the elements of <code>arr2</code> are distinct, and all elements in <code>arr2</code> are also in <code>arr1</code>.</p>

<p>Sort the elements of <code>arr1</code> such that the relative ordering of items in <code>arr1</code> are the same as in <code>arr2</code>. Elements that do not appear in <code>arr2</code> should be placed at the end of <code>arr1</code> in <strong>ascending</strong> order.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> arr1 = [2,3,1,3,2,4,6,7,9,2,19], arr2 = [2,1,4,3,9,6]
<strong>Output:</strong> [2,2,2,1,4,3,3,9,6,7,19]
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> arr1 = [28,6,22,8,44,17], arr2 = [22,28,8,6]
<strong>Output:</strong> [22,28,8,6,17,44]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= arr1.length, arr2.length &lt;= 1000</code></li>
	<li><code>0 &lt;= arr1[i], arr2[i] &lt;= 1000</code></li>
	<li>All the elements of <code>arr2</code> are <strong>distinct</strong>.</li>
	<li>Each&nbsp;<code>arr2[i]</code> is in <code>arr1</code>.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def relativeSortArray(self, arr1: List[int], arr2: List[int]) -> List[int]:
        mp = {num: i for i, num in enumerate(arr2)}
        arr1.sort(key=lambda x: (mp.get(x, 10000), x))
        return arr1
```

```python
class Solution:
    def relativeSortArray(self, arr1: List[int], arr2: List[int]) -> List[int]:
        mp = [0] * 1001
        for x in arr1:
            mp[x] += 1
        i = 0
        for x in arr2:
            while mp[x] > 0:
                arr1[i] = x
                mp[x] -= 1
                i += 1
        for x, cnt in enumerate(mp):
            for _ in range(cnt):
                arr1[i] = x
                i += 1
        return arr1
```

### **Java**

```java
class Solution {
    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        int[] mp = new int[1001];
        for (int x : arr1) {
            ++mp[x];
        }
        int i = 0;
        for (int x : arr2) {
            while (mp[x]-- > 0) {
                arr1[i++] = x;
            }
        }
        for (int j = 0; j < mp.length; ++j) {
            while (mp[j]-- > 0) {
                arr1[i++] = j;
            }
        }
        return arr1;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<int> relativeSortArray(vector<int>& arr1, vector<int>& arr2) {
        vector<int> mp(1001);
        for (int x : arr1) ++mp[x];
        int i = 0;
        for (int x : arr2) {
            while (mp[x]-- > 0) arr1[i++] = x;
        }
        for (int j = 0; j < mp.size(); ++j) {
            while (mp[j]-- > 0) arr1[i++] = j;
        }
        return arr1;
    }
};
```

### **Go**

```go
func relativeSortArray(arr1 []int, arr2 []int) []int {
	mp := make([]int, 1001)
	for _, x := range arr1 {
		mp[x]++
	}
	i := 0
	for _, x := range arr2 {
		for mp[x] > 0 {
			arr1[i] = x
			mp[x]--
			i++
		}
	}
	for j, cnt := range mp {
		for cnt > 0 {
			arr1[i] = j
			i++
			cnt--
		}
	}
	return arr1
}
```

### **...**

```

```

<!-- tabs:end -->
