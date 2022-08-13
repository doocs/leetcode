# [1200. Minimum Absolute Difference](https://leetcode.com/problems/minimum-absolute-difference)

[中文文档](/solution/1200-1299/1200.Minimum%20Absolute%20Difference/README.md)

## Description

<p>Given an array of <strong>distinct</strong> integers <code>arr</code>, find all pairs of elements with the minimum absolute difference of any two elements.</p>

<p>Return a list of pairs in ascending order(with respect to pairs), each pair <code>[a, b]</code> follows</p>

<ul>
	<li><code>a, b</code> are from <code>arr</code></li>
	<li><code>a &lt; b</code></li>
	<li><code>b - a</code> equals to the minimum absolute difference of any two elements in <code>arr</code></li>
</ul>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> arr = [4,2,1,3]
<strong>Output:</strong> [[1,2],[2,3],[3,4]]
<strong>Explanation: </strong>The minimum absolute difference is 1. List all pairs with difference equal to 1 in ascending order.</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> arr = [1,3,6,10,15]
<strong>Output:</strong> [[1,3]]
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> arr = [3,8,-10,23,19,-4,-14,27]
<strong>Output:</strong> [[-14,-10],[19,23],[23,27]]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= arr.length &lt;= 10<sup>5</sup></code></li>
	<li><code>-10<sup>6</sup> &lt;= arr[i] &lt;= 10<sup>6</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def minimumAbsDifference(self, arr: List[int]) -> List[List[int]]:
        arr.sort()
        ans = []
        mi = inf
        for a, b in pairwise(arr):
            d = b - a
            if d < mi:
                ans = [(a, b)]
                mi = d
            elif d == mi:
                ans.append((a, b))
        return ans
```

### **Java**

```java
class Solution {
    public List<List<Integer>> minimumAbsDifference(int[] arr) {
        Arrays.sort(arr);
        List<List<Integer>> ans = new ArrayList<>();
        int n = arr.length;
        int mi = Integer.MAX_VALUE;
        for (int i = 0; i < n - 1; ++i) {
            int a = arr[i], b = arr[i + 1];
            int d = b - a;
            if (d < mi) {
                ans.clear();
                ans.add(Arrays.asList(a, b));
                mi = d;
            } else if (d == mi) {
                ans.add(Arrays.asList(a, b));
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
    vector<vector<int>> minimumAbsDifference(vector<int>& arr) {
        sort(arr.begin(), arr.end());
        int mi = INT_MAX;
        int n = arr.size();
        vector<vector<int>> ans;
        for (int i = 0; i < n - 1; ++i) {
            int a = arr[i], b = arr[i + 1];
            int d = b - a;
            if (d < mi) {
                mi = d;
                ans.clear();
                ans.push_back({a, b});
            } else if (d == mi)
                ans.push_back({a, b});
        }
        return ans;
    }
};
```

### **Go**

```go
func minimumAbsDifference(arr []int) [][]int {
	sort.Ints(arr)
	mi := math.MaxInt32
	var ans [][]int
	for i, a := range arr[:len(arr)-1] {
		b := arr[i+1]
		d := b - a
		if d < mi {
			mi = d
			ans = [][]int{[]int{a, b}}
		} else if d == mi {
			ans = append(ans, []int{a, b})
		}
	}
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
