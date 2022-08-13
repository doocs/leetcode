# [1182. 与目标颜色间的最短距离](https://leetcode.cn/problems/shortest-distance-to-target-color)

[English Version](/solution/1100-1199/1182.Shortest%20Distance%20to%20Target%20Color/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个数组&nbsp;<code>colors</code>，里面有&nbsp;&nbsp;<code>1</code>、<code>2</code>、&nbsp;<code>3</code> 三种颜色。</p>

<p>我们需要在&nbsp;<code>colors</code> 上进行一些查询操作 <code>queries</code>，其中每个待查项都由两个整数 <code>i</code> 和 <code>c</code> 组成。</p>

<p>现在请你帮忙设计一个算法，查找从索引&nbsp;<code>i</code>&nbsp;到具有目标颜色&nbsp;<code>c</code>&nbsp;的元素之间的最短距离。</p>

<p>如果不存在解决方案，请返回&nbsp;<code>-1</code>。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>colors = [1,1,2,1,3,2,2,3,3], queries = [[1,3],[2,2],[6,1]]
<strong>输出：</strong>[3,0,3]
<strong>解释： </strong>
距离索引 1 最近的颜色 3 位于索引 4（距离为 3）。
距离索引 2 最近的颜色 2 就是它自己（距离为 0）。
距离索引 6 最近的颜色 1 位于索引 3（距离为 3）。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>colors = [1,2], queries = [[0,3]]
<strong>输出：</strong>[-1]
<strong>解释：</strong>colors 中没有颜色 3。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= colors.length &lt;= 5*10^4</code></li>
	<li><code>1 &lt;= colors[i] &lt;= 3</code></li>
	<li><code>1&nbsp;&lt;= queries.length &lt;= 5*10^4</code></li>
	<li><code>queries[i].length == 2</code></li>
	<li><code>0 &lt;= queries[i][0] &lt;&nbsp;colors.length</code></li>
	<li><code>1 &lt;= queries[i][1] &lt;= 3</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

二分查找。

先用哈希表记录每种颜色的索引位置。然后遍历 `queries`，如果当前 `color` 不在哈希表中，说明不存在解决方案，此时此 `query` 对应的结果元素是 `-1`。否则，在哈希表中取出当前 `color` 对应的索引列表，二分查找即可。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
