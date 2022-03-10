# [1207. Unique Number of Occurrences](https://leetcode.com/problems/unique-number-of-occurrences)

[中文文档](/solution/1200-1299/1207.Unique%20Number%20of%20Occurrences/README.md)

## Description

<p>Given an array of integers <code>arr</code>, return <code>true</code> if the number of occurrences of each value in the array is <strong>unique</strong>, or <code>false</code> otherwise.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> arr = [1,2,2,1,1,3]
<strong>Output:</strong> true
<strong>Explanation:</strong>&nbsp;The value 1 has 3 occurrences, 2 has 2 and 3 has 1. No two values have the same number of occurrences.</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> arr = [1,2]
<strong>Output:</strong> false
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> arr = [-3,0,1,-3,1,1,1,-3,10,0]
<strong>Output:</strong> true
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= arr.length&nbsp;&lt;= 1000</code></li>
	<li><code>-1000 &lt;= arr[i] &lt;= 1000</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def uniqueOccurrences(self, arr: List[int]) -> bool:
        counter = Counter(arr)
        s = set()
        for num in counter.values():
            if num in s:
                return False
            s.add(num)
        return True
```

### **Java**

```java
class Solution {
    public boolean uniqueOccurrences(int[] arr) {
        Map<Integer, Integer> counter = new HashMap<>();
        for (int e : arr) {
            counter.put(e, counter.getOrDefault(e, 0) + 1);
        }
        Set<Integer> s = new HashSet<>();
        for (int num : counter.values()) {
            if (s.contains(num)) {
                return false;
            }
            s.add(num);
        }
        return true;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    bool uniqueOccurrences(vector<int>& arr) {
        unordered_map<int, int> counter;
        for (auto e : arr) {
            ++counter[e];
        }
        unordered_set<int> s;
        for (auto e : counter) {
            int num = e.second;
            if (s.count(num)) return false;
            s.insert(num);
        }
        return true;
    }
};
```

### **Go**

```go
func uniqueOccurrences(arr []int) bool {
	counter := make(map[int]int)
	for _, e := range arr {
		counter[e]++
	}
	s := make(map[int]bool)
	for _, num := range counter {
		if s[num] {
			return false
		}
		s[num] = true
	}
	return true
}
```

### **...**

```

```

<!-- tabs:end -->
