# [16.21. Sum Swap](https://leetcode.cn/problems/sum-swap-lcci)

[中文文档](/lcci/16.21.Sum%20Swap/README.md)

## Description

<p>Given two arrays of integers, find a pair of values (one value from each array) that you can swap to give the two arrays the same sum.</p>

<p>Return an array, where the first element is the element in the first array that will be swapped, and the second element is another one in the second array. If there are more than one answers, return any one of them. If there is no answer, return an empty array.</p>

<p><strong>Example:</strong></p>

<pre>

<strong>Input:</strong> array1 = [4, 1, 2, 1, 1, 2], array2 = [3, 6, 3, 3]

<strong>Output:</strong> [1, 3]

</pre>

<p><strong>Example:</strong></p>

<pre>

<strong>Input:</strong> array1 = <code>[1, 2, 3], array2 = [4, 5, 6]</code>

<strong>Output: </strong>[]</pre>

<p><strong>Note: </strong></p>

<ul>
	<li><code>1 &lt;= array1.length, array2.length &lt;= 100000</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def findSwapValues(self, array1: List[int], array2: List[int]) -> List[int]:
        diff = sum(array1) - sum(array2)
        if diff & 1:
            return []
        diff >>= 1
        s = set(array2)
        for a in array1:
            b = a - diff
            if b in s:
                return [a, b]
        return []
```

### **Java**

```java
class Solution {
    public int[] findSwapValues(int[] array1, int[] array2) {
        int s1 = 0, s2 = 0;
        Set<Integer> s = new HashSet<>();
        for (int a : array1) {
            s1 += a;
        }
        for (int b : array2) {
            s.add(b);
            s2 += b;
        }
        int diff = s1 - s2;
        if ((diff & 1) == 1) {
            return new int[]{};
        }
        diff >>= 1;
        for (int a : array1) {
            int b = a - diff;
            if (s.contains(b)) {
                return new int[]{a, b};
            }
        }
        return new int[]{};
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<int> findSwapValues(vector<int>& array1, vector<int>& array2) {
        int s1 = 0, s2 = 0;
        unordered_set<int> s;
        for (int a : array1) s1 += a;
        for (int b : array2) {
            s2 += b;
            s.insert(b);
        }
        int diff = s1 - s2;
        if (diff & 1) {
            return {};
        }
        diff >>= 1;
        for (int a : array1) {
            int b = a - diff;
            if (s.count(b)) {
                return {a, b};
            }
        }
        return {};
    }
};
```

### **Go**

```go
func findSwapValues(array1 []int, array2 []int) []int {
	s1, s2 := 0, 0
	for _, a := range array1 {
		s1 += a
	}
	s := make(map[int]bool)
	for _, b := range array2 {
		s2 += b
		s[b] = true
	}
	diff := s1 - s2
	if (diff & 1) == 1 {
		return []int{}
	}
	diff >>= 1
	for _, a := range array1 {
		b := a - diff
		if s[b] {
			return []int{a, b}
		}
	}
	return []int{}
}
```

### **...**

```

```

<!-- tabs:end -->
