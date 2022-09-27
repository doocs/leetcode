# [2418. Sort the People](https://leetcode.com/problems/sort-the-people)

[中文文档](/solution/2400-2499/2418.Sort%20the%20People/README.md)

## Description

<p>You are given an array of strings <code>names</code>, and an array <code>heights</code> that consists of <strong>distinct</strong> positive integers. Both arrays are of length <code>n</code>.</p>

<p>For each index <code>i</code>, <code>names[i]</code> and <code>heights[i]</code> denote the name and height of the <code>i<sup>th</sup></code> person.</p>

<p>Return <code>names</code><em> sorted in <strong>descending</strong> order by the people&#39;s heights</em>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> names = [&quot;Mary&quot;,&quot;John&quot;,&quot;Emma&quot;], heights = [180,165,170]
<strong>Output:</strong> [&quot;Mary&quot;,&quot;Emma&quot;,&quot;John&quot;]
<strong>Explanation:</strong> Mary is the tallest, followed by Emma and John.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> names = [&quot;Alice&quot;,&quot;Bob&quot;,&quot;Bob&quot;], heights = [155,185,150]
<strong>Output:</strong> [&quot;Bob&quot;,&quot;Alice&quot;,&quot;Bob&quot;]
<strong>Explanation:</strong> The first Bob is the tallest, followed by Alice and the second Bob.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == names.length == heights.length</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>3</sup></code></li>
	<li><code>1 &lt;= names[i].length &lt;= 20</code></li>
	<li><code>1 &lt;= heights[i] &lt;= 10<sup>5</sup></code></li>
	<li><code>names[i]</code> consists of lower and upper case English letters.</li>
	<li>All the values of <code>heights</code> are distinct.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def sortPeople(self, names: List[str], heights: List[int]) -> List[str]:
        idx = list(range(len(heights)))
        idx.sort(key=lambda i: -heights[i])
        return [names[i] for i in idx]
```

### **Java**

```java
class Solution {
    public String[] sortPeople(String[] names, int[] heights) {
        int n = heights.length;
        int[][] arr = new int[n][2];
        for (int i = 0; i < n; ++i) {
            arr[i] = new int[] {heights[i], i};
        }
        Arrays.sort(arr, (a, b) -> b[0] - a[0]);
        String[] ans = new String[n];
        for (int i = 0; i < n; ++i) {
            ans[i] = names[arr[i][1]];
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<string> sortPeople(vector<string>& names, vector<int>& heights) {
        int n = heights.size();
        vector<pair<int, int>> arr(n);
        for (int i = 0; i < n; ++i) {
            arr[i] = {-heights[i], i};
        }
        sort(arr.begin(), arr.end());
        vector<string> ans(n);
        for (int i = 0; i < n; ++i) {
            ans[i] = names[arr[i].second];
        }
        return ans;
    }
};
```

### **Go**

```go
func sortPeople(names []string, heights []int) []string {
	n := len(heights)
	type pair struct{ v, i int }
	arr := make([]pair, n)
	for i, v := range heights {
		arr[i] = pair{v, i}
	}
	sort.Slice(arr, func(i, j int) bool { return arr[i].v > arr[j].v })
	ans := make([]string, n)
	for i, v := range arr {
		ans[i] = names[v.i]
	}
	return ans
}
```

### **TypeScript**

```ts
function sortPeople(names: string[], heights: number[]): string[] {
    return names
        .map<[string, number]>((s, i) => [s, heights[i]])
        .sort((a, b) => b[1] - a[1])
        .map(([v]) => v);
}
```

### **Rust**

```rust
impl Solution {
    pub fn sort_people(names: Vec<String>, heights: Vec<i32>) -> Vec<String> {
        let mut combine: Vec<(String, i32)> = names.into_iter().zip(heights.into_iter()).collect();
        combine.sort_by(|a, b| b.1.cmp(&a.1));
        combine.iter().map(|s| s.0.clone()).collect()
    }
}
```

### **...**

```

```

<!-- tabs:end -->
