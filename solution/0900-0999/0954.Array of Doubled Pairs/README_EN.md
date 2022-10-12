# [954. Array of Doubled Pairs](https://leetcode.com/problems/array-of-doubled-pairs)

[中文文档](/solution/0900-0999/0954.Array%20of%20Doubled%20Pairs/README.md)

## Description

<p>Given an integer array of even length <code>arr</code>, return <code>true</code><em> if it is possible to reorder </em><code>arr</code><em> such that </em><code>arr[2 * i + 1] = 2 * arr[2 * i]</code><em> for every </em><code>0 &lt;= i &lt; len(arr) / 2</code><em>, or </em><code>false</code><em> otherwise</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> arr = [3,1,3,6]
<strong>Output:</strong> false
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> arr = [2,1,2,6]
<strong>Output:</strong> false
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> arr = [4,-2,2,-4]
<strong>Output:</strong> true
<strong>Explanation:</strong> We can take two groups, [-2,-4] and [2,4] to form [-2,-4,2,4] or [2,4,-2,-4].
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= arr.length &lt;= 3 * 10<sup>4</sup></code></li>
	<li><code>arr.length</code> is even.</li>
	<li><code>-10<sup>5</sup> &lt;= arr[i] &lt;= 10<sup>5</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def canReorderDoubled(self, arr: List[int]) -> bool:
        freq = Counter(arr)
        if freq[0] & 1:
            return False
        for x in sorted(freq, key=abs):
            if freq[x << 1] < freq[x]:
                return False
            freq[x << 1] -= freq[x]
        return True
```

### **Java**

```java
class Solution {
    public boolean canReorderDoubled(int[] arr) {
        Map<Integer, Integer> freq = new HashMap<>();
        for (int v : arr) {
            freq.put(v, freq.getOrDefault(v, 0) + 1);
        }
        if ((freq.getOrDefault(0, 0) & 1) != 0) {
            return false;
        }
        List<Integer> keys = new ArrayList<>(freq.keySet());
        keys.sort(Comparator.comparingInt(Math::abs));
        for (int k : keys) {
            if (freq.getOrDefault(k << 1, 0) < freq.get(k)) {
                return false;
            }
            freq.put(k << 1, freq.getOrDefault(k << 1, 0) - freq.get(k));
        }
        return true;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    bool canReorderDoubled(vector<int>& arr) {
        unordered_map<int, int> freq;
        for (int& v : arr) ++freq[v];
        if (freq[0] & 1) return false;
        vector<int> keys;
        for (auto& [k, _] : freq) keys.push_back(k);
        sort(keys.begin(), keys.end(), [](int a, int b) { return abs(a) < abs(b); });
        for (int& k : keys) {
            if (freq[k * 2] < freq[k]) return false;
            freq[k * 2] -= freq[k];
        }
        return true;
    }
};
```

### **Go**

```go
func canReorderDoubled(arr []int) bool {
	freq := make(map[int]int)
	for _, v := range arr {
		freq[v]++
	}
	if freq[0]%2 != 0 {
		return false
	}
	var keys []int
	for k := range freq {
		keys = append(keys, k)
	}
	sort.Slice(keys, func(i, j int) bool {
		return abs(keys[i]) < abs(keys[j])
	})
	for _, k := range keys {
		if freq[k*2] < freq[k] {
			return false
		}
		freq[k*2] -= freq[k]
	}
	return true
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}
```

### **...**

```

```

<!-- tabs:end -->
