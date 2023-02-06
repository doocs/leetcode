# [1207. Unique Number of Occurrences](https://leetcode.com/problems/unique-number-of-occurrences)

[中文文档](/solution/1200-1299/1207.Unique%20Number%20of%20Occurrences/README.md)

## Description

<p>Given an array of integers <code>arr</code>, return <code>true</code> <em>if the number of occurrences of each value in the array is <strong>unique</strong> or </em><code>false</code><em> otherwise</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> arr = [1,2,2,1,1,3]
<strong>Output:</strong> true
<strong>Explanation:</strong>&nbsp;The value 1 has 3 occurrences, 2 has 2 and 3 has 1. No two values have the same number of occurrences.</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> arr = [1,2]
<strong>Output:</strong> false
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> arr = [-3,0,1,-3,1,1,1,-3,10,0]
<strong>Output:</strong> true
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= arr.length &lt;= 1000</code></li>
	<li><code>-1000 &lt;= arr[i] &lt;= 1000</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def uniqueOccurrences(self, arr: List[int]) -> bool:
        cnt = Counter(arr)
        return len(set(cnt.values())) == len(cnt)
```

### **Java**

```java
class Solution {
    public boolean uniqueOccurrences(int[] arr) {
        Map<Integer, Integer> cnt = new HashMap<>();
        for (int x : arr) {
            cnt.merge(x, 1, Integer::sum);
        }
        return new HashSet<>(cnt.values()).size() == cnt.size();
    }
}
```

### **C++**

```cpp
class Solution {
public:
    bool uniqueOccurrences(vector<int>& arr) {
        unordered_map<int, int> cnt;
        for (int& x : arr) {
            ++cnt[x];
        }
        unordered_set<int> vis;
        for (auto& [_, v] : cnt) {
            if (vis.count(v)) {
                return false;
            }
            vis.insert(v);
        }
        return true;
    }
};
```

### **Go**

```go
func uniqueOccurrences(arr []int) bool {
	cnt := map[int]int{}
	for _, x := range arr {
		cnt[x]++
	}
	vis := map[int]bool{}
	for _, v := range cnt {
		if vis[v] {
			return false
		}
		vis[v] = true
	}
	return true
}
```

### **...**

```

```

<!-- tabs:end -->
