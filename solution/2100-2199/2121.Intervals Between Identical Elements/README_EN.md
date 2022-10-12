# [2121. Intervals Between Identical Elements](https://leetcode.com/problems/intervals-between-identical-elements)

[中文文档](/solution/2100-2199/2121.Intervals%20Between%20Identical%20Elements/README.md)

## Description

<p>You are given a <strong>0-indexed</strong> array of <code>n</code> integers <code>arr</code>.</p>

<p>The <strong>interval</strong> between two elements in <code>arr</code> is defined as the <strong>absolute difference</strong> between their indices. More formally, the <strong>interval</strong> between <code>arr[i]</code> and <code>arr[j]</code> is <code>|i - j|</code>.</p>

<p>Return <em>an array</em> <code>intervals</code> <em>of length</em> <code>n</code> <em>where</em> <code>intervals[i]</code> <em>is <strong>the sum of intervals</strong> between </em><code>arr[i]</code><em> and each element in </em><code>arr</code><em> with the same value as </em><code>arr[i]</code><em>.</em></p>

<p><strong>Note:</strong> <code>|x|</code> is the absolute value of <code>x</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> arr = [2,1,3,1,2,3,3]
<strong>Output:</strong> [4,2,7,2,4,4,5]
<strong>Explanation:</strong>
- Index 0: Another 2 is found at index 4. |0 - 4| = 4
- Index 1: Another 1 is found at index 3. |1 - 3| = 2
- Index 2: Two more 3s are found at indices 5 and 6. |2 - 5| + |2 - 6| = 7
- Index 3: Another 1 is found at index 1. |3 - 1| = 2
- Index 4: Another 2 is found at index 0. |4 - 0| = 4
- Index 5: Two more 3s are found at indices 2 and 6. |5 - 2| + |5 - 6| = 4
- Index 6: Two more 3s are found at indices 2 and 5. |6 - 2| + |6 - 5| = 5
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> arr = [10,5,10,10]
<strong>Output:</strong> [5,0,3,4]
<strong>Explanation:</strong>
- Index 0: Two more 10s are found at indices 2 and 3. |0 - 2| + |0 - 3| = 5
- Index 1: There is only one 5 in the array, so its sum of intervals to identical elements is 0.
- Index 2: Two more 10s are found at indices 0 and 3. |2 - 0| + |2 - 3| = 3
- Index 3: Two more 10s are found at indices 0 and 2. |3 - 0| + |3 - 2| = 4
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == arr.length</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= arr[i] &lt;= 10<sup>5</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def getDistances(self, arr: List[int]) -> List[int]:
        d = defaultdict(list)
        n = len(arr)
        for i, v in enumerate(arr):
            d[v].append(i)
        ans = [0] * n
        for v in d.values():
            m = len(v)
            val = sum(v) - v[0] * m
            for i, p in enumerate(v):
                delta = v[i] - v[i - 1] if i >= 1 else 0
                val += i * delta - (m - i) * delta
                ans[p] = val
        return ans
```

### **Java**

```java
class Solution {
    public long[] getDistances(int[] arr) {
        Map<Integer, List<Integer>> d = new HashMap<>();
        int n = arr.length;
        for (int i = 0; i < n; ++i) {
            d.computeIfAbsent(arr[i], k -> new ArrayList<>()).add(i);
        }
        long[] ans = new long[n];
        for (List<Integer> v : d.values()) {
            int m = v.size();
            long val = 0;
            for (int e : v) {
                val += e;
            }
            val -= (m * v.get(0));
            for (int i = 0; i < v.size(); ++i) {
                int delta = i >= 1 ? v.get(i) - v.get(i - 1) : 0;
                val += i * delta - (m - i) * delta;
                ans[v.get(i)] = val;
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
    vector<long long> getDistances(vector<int>& arr) {
        unordered_map<int, vector<int>> d;
        int n = arr.size();
        for (int i = 0; i < n; ++i) d[arr[i]].push_back(i);
        vector<long long> ans(n);
        for (auto& item : d) {
            auto& v = item.second;
            int m = v.size();
            long long val = 0;
            for (int e : v) val += e;
            val -= m * v[0];
            for (int i = 0; i < v.size(); ++i) {
                int delta = i >= 1 ? v[i] - v[i - 1] : 0;
                val += i * delta - (m - i) * delta;
                ans[v[i]] = val;
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func getDistances(arr []int) []int64 {
	d := make(map[int][]int)
	n := len(arr)
	for i, v := range arr {
		d[v] = append(d[v], i)
	}
	ans := make([]int64, n)
	for _, v := range d {
		m := len(v)
		val := 0
		for _, e := range v {
			val += e
		}
		val -= m * v[0]
		for i, p := range v {
			delta := 0
			if i >= 1 {
				delta = v[i] - v[i-1]
			}
			val += i*delta - (m-i)*delta
			ans[p] = int64(val)
		}
	}
	return ans
}
```

### **TypeScript**

```ts

```

### **...**

```

```

<!-- tabs:end -->
