# [2070. Most Beautiful Item for Each Query](https://leetcode.com/problems/most-beautiful-item-for-each-query)

[中文文档](/solution/2000-2099/2070.Most%20Beautiful%20Item%20for%20Each%20Query/README.md)

## Description

<p>You are given a 2D integer array <code>items</code> where <code>items[i] = [price<sub>i</sub>, beauty<sub>i</sub>]</code> denotes the <strong>price</strong> and <strong>beauty</strong> of an item respectively.</p>

<p>You are also given a <strong>0-indexed</strong> integer array <code>queries</code>. For each <code>queries[j]</code>, you want to determine the <strong>maximum beauty</strong> of an item whose <strong>price</strong> is <strong>less than or equal</strong> to <code>queries[j]</code>. If no such item exists, then the answer to this query is <code>0</code>.</p>

<p>Return <em>an array </em><code>answer</code><em> of the same length as </em><code>queries</code><em> where </em><code>answer[j]</code><em> is the answer to the </em><code>j<sup>th</sup></code><em> query</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> items = [[1,2],[3,2],[2,4],[5,6],[3,5]], queries = [1,2,3,4,5,6]
<strong>Output:</strong> [2,4,5,5,6,6]
<strong>Explanation:</strong>
- For queries[0]=1, [1,2] is the only item which has price &lt;= 1. Hence, the answer for this query is 2.
- For queries[1]=2, the items which can be considered are [1,2] and [2,4]. 
  The maximum beauty among them is 4.
- For queries[2]=3 and queries[3]=4, the items which can be considered are [1,2], [3,2], [2,4], and [3,5].
  The maximum beauty among them is 5.
- For queries[4]=5 and queries[5]=6, all items can be considered.
  Hence, the answer for them is the maximum beauty of all items, i.e., 6.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> items = [[1,2],[1,2],[1,3],[1,4]], queries = [1]
<strong>Output:</strong> [4]
<strong>Explanation:</strong> 
The price of every item is equal to 1, so we choose the item with the maximum beauty 4. 
Note that multiple items can have the same price and/or beauty.  
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> items = [[10,1000]], queries = [5]
<strong>Output:</strong> [0]
<strong>Explanation:</strong>
No item has a price less than or equal to 5, so no item can be chosen.
Hence, the answer to the query is 0.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= items.length, queries.length &lt;= 10<sup>5</sup></code></li>
	<li><code>items[i].length == 2</code></li>
	<li><code>1 &lt;= price<sub>i</sub>, beauty<sub>i</sub>, queries[j] &lt;= 10<sup>9</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def maximumBeauty(self, items: List[List[int]], queries: List[int]) -> List[int]:
        items.sort()
        prices = [p for p, _ in items]
        mx = [items[0][1]]
        for _, b in items[1:]:
            mx.append(max(mx[-1], b))
        ans = [0] * len(queries)
        for i, q in enumerate(queries):
            j = bisect_right(prices, q)
            if j:
                ans[i] = mx[j - 1]
        return ans
```

### **Java**

```java
class Solution {
    public int[] maximumBeauty(int[][] items, int[] queries) {
        Arrays.sort(items, (a, b) -> a[0] - b[0]);
        for (int i = 1; i < items.length; ++i) {
            items[i][1] = Math.max(items[i - 1][1], items[i][1]);
        }
        int n = queries.length;
        int[] ans = new int[n];
        for (int i = 0; i < n; ++i) {
            int left = 0, right = items.length;
            while (left < right) {
                int mid = (left + right) >> 1;
                if (items[mid][0] > queries[i]) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }
            if (left > 0) {
                ans[i] = items[left - 1][1];
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
    vector<int> maximumBeauty(vector<vector<int>>& items, vector<int>& queries) {
        sort(items.begin(), items.end());
        for (int i = 1; i < items.size(); ++i) items[i][1] = max(items[i - 1][1], items[i][1]);
        int n = queries.size();
        vector<int> ans(n);
        for (int i = 0; i < n; ++i) {
            int left = 0, right = items.size();
            while (left < right) {
                int mid = (left + right) >> 1;
                if (items[mid][0] > queries[i])
                    right = mid;
                else
                    left = mid + 1;
            }
            if (left) ans[i] = items[left - 1][1];
        }
        return ans;
    }
};
```

### **Go**

```go
func maximumBeauty(items [][]int, queries []int) []int {
	sort.Slice(items, func(i, j int) bool {
		return items[i][0] < items[j][0]
	})
	for i := 1; i < len(items); i++ {
		items[i][1] = max(items[i-1][1], items[i][1])
	}
	n := len(queries)
	ans := make([]int, n)
	for i, v := range queries {
		left, right := 0, len(items)
		for left < right {
			mid := (left + right) >> 1
			if items[mid][0] > v {
				right = mid
			} else {
				left = mid + 1
			}
		}
		if left > 0 {
			ans[i] = items[left-1][1]
		}
	}
	return ans
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
```

### **...**

```

```

<!-- tabs:end -->
