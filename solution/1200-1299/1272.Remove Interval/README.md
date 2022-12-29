# [1272. 删除区间](https://leetcode.cn/problems/remove-interval)

[English Version](/solution/1200-1299/1272.Remove%20Interval/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>实数集合可以表示为若干不相交区间的并集，其中每个区间的形式为 <code>[a, b)</code>（左闭右开），表示满足&nbsp;<code>a &lt;= x &lt; b</code> 的所有实数&nbsp; <code>x</code>&nbsp;的集合。如果某个区间&nbsp;<code>[a, b)</code> 中包含实数 <code>x</code> ，则称实数 <code>x</code> 在集合中。</p>

<p>给你一个 <strong>有序的</strong> 不相交区间列表 <code>intervals</code>&nbsp;。<code>intervals</code> 表示一个实数集合，其中每一项 <code>intervals[i] = [a<sub>i</sub>, b<sub>i</sub>]</code> 都表示一个区间 <code>[a<sub>i</sub>, b<sub>i</sub>)</code> 。再给你一个要删除的区间 <code>toBeRemoved</code> 。</p>

<p>返回 <em>一组实数，该实数表示<code>intervals</code> 中&nbsp;<strong>删除</strong>&nbsp;了 <code>toBeRemoved</code> 的部分</em>&nbsp;。<em>换句话说，返回实数集合，并满足集合中的每个实数 <code>x</code> 都在&nbsp;<code>intervals</code> 中，但不在 <code>toBeRemoved</code> 中。你的答案应该是一个如上所述的 <strong>有序的</strong> 不相连的间隔列表&nbsp;。</em></p>

<p>&nbsp;</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1200-1299/1272.Remove%20Interval/images/removeintervalex1.png" />
<pre>
<strong>输入：</strong>intervals = [[0,2],[3,4],[5,7]], toBeRemoved = [1,6]
<strong>输出：</strong>[[0,1],[6,7]]
</pre>

<p><strong>示例 2：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1200-1299/1272.Remove%20Interval/images/removeintervalex2.png" />
<pre>
<strong>输入：</strong>intervals = [[0,5]], toBeRemoved = [2,3]
<strong>输出：</strong>[[0,2],[3,5]]
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>intervals = [[-5,-4],[-3,-2],[1,2],[3,5],[8,9]], toBeRemoved = [-1,4]
<strong>输出：</strong>[[-5,-4],[-3,-2],[4,5],[8,9]]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= intervals.length &lt;= 10<sup>4</sup></code></li>
	<li><code>-10<sup>9</sup> &lt;= a<sub>i</sub> &lt; b<sub>i</sub> &lt;= 10<sup>9</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：分类讨论**

我们记要删除的区间为 $[x, y)$，遍历区间列表，对于每个区间 $[a, b)$，有以下三种情况：

-   $a \geq y$ 或 $b \leq x$，表示该区间与要删除的区间没有交集，直接将该区间加入答案；
-   $a \lt x$, $b \gt y$，表示该区间与要删除的区间有交集，将该区间分成两个区间加入答案；
-   $a \geq x$, $b \leq y$，表示该区间被要删除的区间完全覆盖，不加入答案。

时间复杂度 $O(n)$，空间复杂度 $O(1)$。其中 $n$ 为区间列表的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def removeInterval(self, intervals: List[List[int]], toBeRemoved: List[int]) -> List[List[int]]:
        x, y = toBeRemoved
        ans = []
        for a, b in intervals:
            if a >= y or b <= x:
                ans.append([a, b])
            else:
                if a < x:
                    ans.append([a, x])
                if b > y:
                    ans.append([y, b])
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public List<List<Integer>> removeInterval(int[][] intervals, int[] toBeRemoved) {
        int x = toBeRemoved[0], y = toBeRemoved[1];
        List<List<Integer>> ans = new ArrayList<>();
        for (var e : intervals) {
            int a = e[0], b = e[1];
            if (a >= y || b <= x) {
                ans.add(Arrays.asList(a, b));
            } else {
                if (a < x) {
                    ans.add(Arrays.asList(a, x));
                }
                if (b > y) {
                    ans.add(Arrays.asList(y, b));
                }
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
    vector<vector<int>> removeInterval(vector<vector<int>>& intervals, vector<int>& toBeRemoved) {
        int x = toBeRemoved[0], y = toBeRemoved[1];
        vector<vector<int>> ans;
        for (auto& e : intervals) {
            int a = e[0], b = e[1];
            if (a >= y || b <= x) {
                ans.push_back(e);
            } else {
                if (a < x) {
                    ans.push_back({a, x});
                }
                if (b > y) {
                    ans.push_back({y, b});
                }
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func removeInterval(intervals [][]int, toBeRemoved []int) (ans [][]int) {
	x, y := toBeRemoved[0], toBeRemoved[1]
	for _, e := range intervals {
		a, b := e[0], e[1]
		if a >= y || b <= x {
			ans = append(ans, e)
		} else {
			if a < x {
				ans = append(ans, []int{a, x})
			}
			if b > y {
				ans = append(ans, []int{y, b})
			}
		}
	}
	return
}
```

### **...**

```

```

<!-- tabs:end -->
