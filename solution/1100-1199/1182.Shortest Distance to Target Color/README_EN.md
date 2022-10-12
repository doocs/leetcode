# [1182. Shortest Distance to Target Color](https://leetcode.com/problems/shortest-distance-to-target-color)

[中文文档](/solution/1100-1199/1182.Shortest%20Distance%20to%20Target%20Color/README.md)

## Description

<p>You are given an array <code>colors</code>, in which there are three colors: <code>1</code>, <code>2</code> and&nbsp;<code>3</code>.</p>

<p>You are also given some queries. Each query consists of two integers <code>i</code>&nbsp;and <code>c</code>, return&nbsp;the shortest distance between the given index&nbsp;<code>i</code> and the target color <code>c</code>. If there is no solution return <code>-1</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> colors = [1,1,2,1,3,2,2,3,3], queries = [[1,3],[2,2],[6,1]]
<strong>Output:</strong> [3,0,3]
<strong>Explanation: </strong>
The nearest 3 from index 1 is at index 4 (3 steps away).
The nearest 2 from index 2 is at index 2 itself (0 steps away).
The nearest 1 from index 6 is at index 3 (3 steps away).
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> colors = [1,2], queries = [[0,3]]
<strong>Output:</strong> [-1]
<strong>Explanation: </strong>There is no 3 in the array.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= colors.length &lt;= 5*10^4</code></li>
	<li><code>1 &lt;= colors[i] &lt;= 3</code></li>
	<li><code>1&nbsp;&lt;= queries.length &lt;= 5*10^4</code></li>
	<li><code>queries[i].length == 2</code></li>
	<li><code>0 &lt;= queries[i][0] &lt;&nbsp;colors.length</code></li>
	<li><code>1 &lt;= queries[i][1] &lt;= 3</code></li>
</ul>

## Solutions

Binary search.

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def shortestDistanceColor(
        self, colors: List[int], queries: List[List[int]]
    ) -> List[int]:
        color_indexes = defaultdict(list)
        for i, c in enumerate(colors):
            color_indexes[c].append(i)
        res = []
        for i, c in queries:
            if c not in color_indexes:
                res.append(-1)
            else:
                t = color_indexes[c]
                left, right = 0, len(t) - 1
                while left < right:
                    mid = (left + right) >> 1
                    if t[mid] >= i:
                        right = mid
                    else:
                        left = mid + 1
                val = abs(t[left] - i)
                if left > 0:
                    val = min(val, abs(t[left - 1] - i))
                if left < len(t) - 1:
                    val = min(val, abs(t[left + 1] - i))
                res.append(val)
        return res
```

### **Java**

```java
class Solution {
    public List<Integer> shortestDistanceColor(int[] colors, int[][] queries) {
        Map<Integer, List<Integer>> colorIndexes = new HashMap<>();
        for (int i = 0; i < colors.length; ++i) {
            int c = colors[i];
            colorIndexes.computeIfAbsent(c, k -> new ArrayList<>()).add(i);
        }
        List<Integer> res = new ArrayList<>();
        for (int[] query : queries) {
            int i = query[0], c = query[1];
            if (!colorIndexes.containsKey(c)) {
                res.add(-1);
                continue;
            }
            List<Integer> t = colorIndexes.get(c);
            int left = 0, right = t.size() - 1;
            while (left < right) {
                int mid = (left + right) >> 1;
                if (t.get(mid) >= i) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }
            int val = Math.abs(t.get(left) - i);
            if (left > 0) {
                val = Math.min(val, Math.abs(t.get(left - 1) - i));
            }
            if (left < t.size() - 1) {
                val = Math.min(val, Math.abs(t.get(left + 1) - i));
            }
            res.add(val);
        }
        return res;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
