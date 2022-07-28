# [1331. Rank Transform of an Array](https://leetcode.com/problems/rank-transform-of-an-array)

[中文文档](/solution/1300-1399/1331.Rank%20Transform%20of%20an%20Array/README.md)

## Description

<p>Given an array of integers&nbsp;<code>arr</code>, replace each element with its rank.</p>

<p>The rank represents how large the element is. The rank has the following rules:</p>

<ul>
	<li>Rank is an integer starting from 1.</li>
	<li>The larger the element, the larger the rank. If two elements are equal, their rank must be the same.</li>
	<li>Rank should be as small as possible.</li>
</ul>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> arr = [40,10,20,30]
<strong>Output:</strong> [4,1,2,3]
<strong>Explanation</strong>: 40 is the largest element. 10 is the smallest. 20 is the second smallest. 30 is the third smallest.</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> arr = [100,100,100]
<strong>Output:</strong> [1,1,1]
<strong>Explanation</strong>: Same elements share the same rank.
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> arr = [37,12,28,9,100,56,80,5,12]
<strong>Output:</strong> [5,3,4,2,8,6,7,1,3]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>0 &lt;= arr.length &lt;= 10<sup>5</sup></code></li>
	<li><code>-10<sup>9</sup>&nbsp;&lt;= arr[i] &lt;= 10<sup>9</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def arrayRankTransform(self, arr: List[int]) -> List[int]:
        t = sorted(set(arr))
        return [bisect_left(t, x) + 1 for x in arr]
```

```python
class Solution:
    def arrayRankTransform(self, arr: List[int]) -> List[int]:
        m = {v: i for i, v in enumerate(sorted(set(arr)), 1)}
        return [m[v] for v in arr]
```

### **Java**

```java
class Solution {
    public int[] arrayRankTransform(int[] arr) {
        Set<Integer> s = new HashSet<>();
        for (int v : arr) {
            s.add(v);
        }
        List<Integer> alls = new ArrayList<>(s);
        alls.sort((a, b) -> a - b);
        Map<Integer, Integer> m = new HashMap<>();
        for (int i = 0; i < alls.size(); ++i) {
            m.put(alls.get(i), i + 1);
        }
        int[] ans = new int[arr.length];
        for (int i = 0; i < arr.length; ++i) {
            ans[i] = m.get(arr[i]);
        }
        return ans;
    }
}
```

```java
class Solution {
    public int[] arrayRankTransform(int[] arr) {
        Set<Integer> s = new HashSet<>();
        for (int v : arr) {
            s.add(v);
        }
        List<Integer> alls = new ArrayList<>(s);
        alls.sort((a, b) -> a - b);
        int n = arr.length;
        int[] ans = new int[n];
        for (int i = 0; i < n; ++i) {
            ans[i] = Collections.binarySearch(alls, arr[i]) + 1;
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<int> arrayRankTransform(vector<int>& arr) {
        unordered_set<int> s(arr.begin(), arr.end());
        vector<int> alls(s.begin(), s.end());
        sort(alls.begin(), alls.end());
        unordered_map<int, int> m;
        for (int i = 0; i < alls.size(); ++i) m[alls[i]] = i + 1;
        vector<int> ans;
        for (int v : arr) ans.push_back(m[v]);
        return ans;
    }
};
```

```cpp
class Solution {
public:
    vector<int> arrayRankTransform(vector<int>& arr) {
        unordered_set<int> s(arr.begin(), arr.end());
        vector<int> alls(s.begin(), s.end());
        sort(alls.begin(), alls.end());
        vector<int> ans;
        for (int v: arr) ans.push_back(lower_bound(alls.begin(), alls.end(), v) - alls.begin() + 1);
        return ans;
    }
};
```

### **Go**

```go
func arrayRankTransform(arr []int) []int {
	s := make(map[int]bool)
	for _, v := range arr {
		s[v] = true
	}
	var alls []int
	for v := range s {
		alls = append(alls, v)
	}
	sort.Ints(alls)
	m := make(map[int]int)
	for i, v := range alls {
		m[v] = i + 1
	}
	var ans []int
	for _, v := range arr {
		ans = append(ans, m[v])
	}
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
