# [2363. Merge Similar Items](https://leetcode.com/problems/merge-similar-items)

[中文文档](/solution/2300-2399/2363.Merge%20Similar%20Items/README.md)

## Description

<p>You are given two 2D integer arrays, <code>items1</code> and <code>items2</code>, representing two sets of items. Each array <code>items</code> has the following properties:</p>

<ul>
	<li><code>items[i] = [value<sub>i</sub>, weight<sub>i</sub>]</code> where <code>value<sub>i</sub></code> represents the <strong>value</strong> and <code>weight<sub>i</sub></code> represents the <strong>weight </strong>of the <code>i<sup>th</sup></code> item.</li>
	<li>The value of each item in <code>items</code> is <strong>unique</strong>.</li>
</ul>

<p>Return <em>a 2D integer array</em> <code>ret</code> <em>where</em> <code>ret[i] = [value<sub>i</sub>, weight<sub>i</sub>]</code><em>,</em> <em>with</em> <code>weight<sub>i</sub></code> <em>being the <strong>sum of weights</strong> of all items with value</em> <code>value<sub>i</sub></code>.</p>

<p><strong>Note:</strong> <code>ret</code> should be returned in <strong>ascending</strong> order by value.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> items1 = [[1,1],[4,5],[3,8]], items2 = [[3,1],[1,5]]
<strong>Output:</strong> [[1,6],[3,9],[4,5]]
<strong>Explanation:</strong> 
The item with value = 1 occurs in items1 with weight = 1 and in items2 with weight = 5, total weight = 1 + 5 = 6.
The item with value = 3 occurs in items1 with weight = 8 and in items2 with weight = 1, total weight = 8 + 1 = 9.
The item with value = 4 occurs in items1 with weight = 5, total weight = 5.  
Therefore, we return [[1,6],[3,9],[4,5]].
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> items1 = [[1,1],[3,2],[2,3]], items2 = [[2,1],[3,2],[1,3]]
<strong>Output:</strong> [[1,4],[2,4],[3,4]]
<strong>Explanation:</strong> 
The item with value = 1 occurs in items1 with weight = 1 and in items2 with weight = 3, total weight = 1 + 3 = 4.
The item with value = 2 occurs in items1 with weight = 3 and in items2 with weight = 1, total weight = 3 + 1 = 4.
The item with value = 3 occurs in items1 with weight = 2 and in items2 with weight = 2, total weight = 2 + 2 = 4.
Therefore, we return [[1,4],[2,4],[3,4]].</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> items1 = [[1,3],[2,2]], items2 = [[7,1],[2,2],[1,4]]
<strong>Output:</strong> [[1,7],[2,4],[7,1]]
<strong>Explanation:
</strong>The item with value = 1 occurs in items1 with weight = 3 and in items2 with weight = 4, total weight = 3 + 4 = 7. 
The item with value = 2 occurs in items1 with weight = 2 and in items2 with weight = 2, total weight = 2 + 2 = 4. 
The item with value = 7 occurs in items2 with weight = 1, total weight = 1.
Therefore, we return [[1,7],[2,4],[7,1]].
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= items1.length, items2.length &lt;= 1000</code></li>
	<li><code>items1[i].length == items2[i].length == 2</code></li>
	<li><code>1 &lt;= value<sub>i</sub>, weight<sub>i</sub> &lt;= 1000</code></li>
	<li>Each <code>value<sub>i</sub></code> in <code>items1</code> is <strong>unique</strong>.</li>
	<li>Each <code>value<sub>i</sub></code> in <code>items2</code> is <strong>unique</strong>.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def mergeSimilarItems(
        self, items1: List[List[int]], items2: List[List[int]]
    ) -> List[List[int]]:
        cnt = [0] * 1010
        for v, w in chain(items1, items2):
            cnt[v] += w
        return [[i, v] for i, v in enumerate(cnt) if v]
```

### **Java**

```java
class Solution {
    public List<List<Integer>> mergeSimilarItems(int[][] items1, int[][] items2) {
        int[] cnt = new int[1010];
        for (int[] e : items1) {
            cnt[e[0]] += e[1];
        }
        for (int[] e : items2) {
            cnt[e[0]] += e[1];
        }
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < cnt.length; ++i) {
            if (cnt[i] > 0) {
                ans.add(Arrays.asList(i, cnt[i]));
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
    vector<vector<int>> mergeSimilarItems(vector<vector<int>>& items1, vector<vector<int>>& items2) {
        vector<int> cnt(1010);
        for (auto& e : items1) cnt[e[0]] += e[1];
        for (auto& e : items2) cnt[e[0]] += e[1];
        vector<vector<int>> ans;
        for (int i = 0; i < cnt.size(); ++i)
            if (cnt[i]) ans.push_back({i, cnt[i]});
        return ans;
    }
};
```

### **Go**

```go
func mergeSimilarItems(items1 [][]int, items2 [][]int) [][]int {
	cnt := make([]int, 1010)
	for _, e := range items1 {
		cnt[e[0]] += e[1]
	}
	for _, e := range items2 {
		cnt[e[0]] += e[1]
	}
	ans := [][]int{}
	for i, v := range cnt {
		if v > 0 {
			ans = append(ans, []int{i, v})
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
