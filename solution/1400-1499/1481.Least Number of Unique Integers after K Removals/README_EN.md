# [1481. Least Number of Unique Integers after K Removals](https://leetcode.com/problems/least-number-of-unique-integers-after-k-removals)

[中文文档](/solution/1400-1499/1481.Least%20Number%20of%20Unique%20Integers%20after%20K%20Removals/README.md)

## Description

<p>Given an array of integers&nbsp;<code>arr</code>&nbsp;and an integer <code>k</code>.&nbsp;Find the <em>least number of unique integers</em>&nbsp;after removing <strong>exactly</strong> <code>k</code> elements<b>.</b></p>

<ol>

</ol>

<p>&nbsp;</p>

<p><strong>Example 1:</strong></p>

<pre>

<strong>Input: </strong>arr = [5,5,4], k = 1

<strong>Output: </strong>1

<strong>Explanation</strong>: Remove the single 4, only 5 is left.

</pre>

<strong>Example 2:</strong>

<pre>

<strong>Input: </strong>arr = [4,3,1,1,3,3,2], k = 3

<strong>Output: </strong>2

<strong>Explanation</strong>: Remove 4, 2 and either one of the two 1s or three 3s. 1 and 3 will be left.</pre>

<p>&nbsp;</p>

<p><strong>Constraints:</strong></p>

<ul>
    <li><code>1 &lt;= arr.length&nbsp;&lt;= 10^5</code></li>
    <li><code>1 &lt;= arr[i] &lt;= 10^9</code></li>
    <li><code>0 &lt;= k&nbsp;&lt;= arr.length</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def findLeastNumOfUniqueInts(self, arr: List[int], k: int) -> int:
        counter = Counter(arr)
        t = sorted(counter.items(), key=lambda x: x[1])
        for v, cnt in t:
            if k >= cnt:
                k -= cnt
                counter.pop(v)
            else:
                break
        return len(counter)
```

### **Java**

```java
class Solution {
    public int findLeastNumOfUniqueInts(int[] arr, int k) {
        Map<Integer, Integer> counter = new HashMap<>();
        for (int v : arr) {
            counter.put(v, counter.getOrDefault(v, 0) + 1);
        }
        List<Map.Entry<Integer, Integer>> t = new ArrayList<>(counter.entrySet());
        Collections.sort(t, Comparator.comparingInt(Map.Entry::getValue));
        for (Map.Entry<Integer, Integer> e : t) {
            int v = e.getKey();
            int cnt = e.getValue();
            if (k >= cnt) {
                k -= cnt;
                counter.remove(v);
            } else {
                break;
            }
        }
        return counter.size();
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int findLeastNumOfUniqueInts(vector<int>& arr, int k) {
        unordered_map<int, int> counter;
        for (int v : arr) ++counter[v];
        vector<pair<int, int>> t(counter.begin(), counter.end());
        sort(t.begin(), t.end(), [](const auto& a, const auto& b) { return a.second < b.second; });
        for (auto [v, cnt] : t) {
            if (k >= cnt) {
                k -= cnt;
                counter.erase(v);
            } else
                break;
        }
        return counter.size();
    }
};
```

### **Go**

```go
func findLeastNumOfUniqueInts(arr []int, k int) int {
	counter := make(map[int]int)
	for _, v := range arr {
		counter[v]++
	}
	var t [][]int
	for v, cnt := range counter {
		t = append(t, []int{v, cnt})
	}
	sort.Slice(t, func(i, j int) bool {
		return t[i][1] < t[j][1]
	})
	for _, e := range t {
		v, cnt := e[0], e[1]
		if k >= cnt {
			k -= cnt
			delete(counter, v)
		} else {
			break
		}
	}
	return len(counter)
}
```

### **...**

```

```

<!-- tabs:end -->
