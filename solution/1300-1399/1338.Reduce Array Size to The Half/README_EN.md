# [1338. Reduce Array Size to The Half](https://leetcode.com/problems/reduce-array-size-to-the-half)

[中文文档](/solution/1300-1399/1338.Reduce%20Array%20Size%20to%20The%20Half/README.md)

## Description

<p>You are given an integer array <code>arr</code>. You can choose a set of integers and remove all the occurrences of these integers in the array.</p>

<p>Return <em>the minimum size of the set so that <strong>at least</strong> half of the integers of the array are removed</em>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> arr = [3,3,3,3,5,5,5,2,2,7]
<strong>Output:</strong> 2
<strong>Explanation:</strong> Choosing {3,7} will make the new array [5,5,5,2,2] which has size 5 (i.e equal to half of the size of the old array).
Possible sets of size 2 are {3,5},{3,2},{5,2}.
Choosing set {2,7} is not possible as it will make the new array [3,3,3,3,5,5,5] which has a size greater than half of the size of the old array.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> arr = [7,7,7,7,7,7]
<strong>Output:</strong> 1
<strong>Explanation:</strong> The only possible set you can choose is {7}. This will make the new array empty.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= arr.length &lt;= 10<sup>5</sup></code></li>
	<li><code>arr.length</code> is even.</li>
	<li><code>1 &lt;= arr[i] &lt;= 10<sup>5</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def minSetSize(self, arr: List[int]) -> int:
        couter = Counter(arr)
        ans = n = 0
        for _, cnt in couter.most_common():
            n += cnt
            ans += 1
            if n * 2 >= len(arr):
                break
        return ans
```

### **Java**

```java
class Solution {
    public int minSetSize(int[] arr) {
        Map<Integer, Integer> counter = new HashMap<>();
        for (int v : arr) {
            counter.put(v, counter.getOrDefault(v, 0) + 1);
        }
        List<Integer> t = new ArrayList<>();
        for (int cnt : counter.values()) {
            t.add(cnt);
        }
        Collections.sort(t, Collections.reverseOrder());
        int ans = 0;
        int n = 0;
        for (int cnt : t) {
            n += cnt;
            ++ans;
            if (n * 2 >= arr.length) {
                break;
            }
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int minSetSize(vector<int>& arr) {
        unordered_map<int, int> counter;
        for (int v : arr) ++counter[v];
        vector<int> t;
        for (auto& [k, v] : counter) t.push_back(v);
        sort(t.begin(), t.end(), greater<int>());
        int ans = 0;
        int n = 0;
        for (int cnt : t) {
            n += cnt;
            ++ans;
            if (n * 2 >= arr.size()) break;
        }
        return ans;
    }
};
```

### **Go**

```go
func minSetSize(arr []int) int {
	counter := make(map[int]int)
	for _, v := range arr {
		counter[v]++
	}
	var t []int
	for _, v := range counter {
		t = append(t, v)
	}
	sort.Slice(t, func(i, j int) bool {
		return t[i] > t[j]
	})
	ans, n := 0, 0
	for _, cnt := range t {
		n += cnt
		ans++
		if n*2 >= len(arr) {
			break
		}
	}
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
