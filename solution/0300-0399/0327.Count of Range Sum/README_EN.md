# [327. Count of Range Sum](https://leetcode.com/problems/count-of-range-sum)

[中文文档](/solution/0300-0399/0327.Count%20of%20Range%20Sum/README.md)

## Description

<p>Given an integer array <code>nums</code> and two integers <code>lower</code> and <code>upper</code>, return <em>the number of range sums that lie in</em> <code>[lower, upper]</code> <em>inclusive</em>.</p>

<p>Range sum <code>S(i, j)</code> is defined as the sum of the elements in <code>nums</code> between indices <code>i</code> and <code>j</code> inclusive, where <code>i &lt;= j</code>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [-2,5,-1], lower = -2, upper = 2
<strong>Output:</strong> 3
<strong>Explanation:</strong> The three ranges are: [0,0], [2,2], and [0,2] and their respective sums are: -2, -1, 2.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [0], lower = 0, upper = 0
<strong>Output:</strong> 1
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>4</sup></code></li>
	<li><code>-2<sup>31</sup> &lt;= nums[i] &lt;= 2<sup>31</sup> - 1</code></li>
	<li><code>-3 * 10<sup>4</sup> &lt;= lower &lt;= upper &lt;= 3 * 10<sup>4</sup></code></li>
</ul>

<p>&nbsp;</p>
<strong>Follow up:</strong> A naive algorithm of <code>O(n<sup>2</sup>)</code> is trivial, Could you do better than that?

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def arrayRankTransform(self, arr: List[int]) -> List[int]:
        def find(x):
            left, right = 0, len(t) - 1
            while left < right:
                mid = (left + right) >> 1
                if t[mid] >= x:
                    right = mid
                else:
                    left = mid + 1
            return left + 1

        t = sorted(set(arr))
        return [find(x) for x in arr]
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
