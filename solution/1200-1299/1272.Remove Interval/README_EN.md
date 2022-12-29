# [1272. Remove Interval](https://leetcode.com/problems/remove-interval)

[中文文档](/solution/1200-1299/1272.Remove%20Interval/README.md)

## Description

<p>A set of real numbers can be represented as the union of several disjoint intervals, where each interval is in the form <code>[a, b)</code>. A real number <code>x</code> is in the set if one of its intervals <code>[a, b)</code> contains <code>x</code> (i.e. <code>a &lt;= x &lt; b</code>).</p>

<p>You are given a <strong>sorted</strong> list of disjoint intervals <code>intervals</code> representing a set of real numbers as described above, where <code>intervals[i] = [a<sub>i</sub>, b<sub>i</sub>]</code> represents the interval <code>[a<sub>i</sub>, b<sub>i</sub>)</code>. You are also given another interval <code>toBeRemoved</code>.</p>

<p>Return <em>the set of real numbers with the interval </em><code>toBeRemoved</code><em> <strong>removed</strong> from</em><em> </em><code>intervals</code><em>. In other words, return the set of real numbers such that every </em><code>x</code><em> in the set is in </em><code>intervals</code><em> but <strong>not</strong> in </em><code>toBeRemoved</code><em>. Your answer should be a <strong>sorted</strong> list of disjoint intervals as described above.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1200-1299/1272.Remove%20Interval/images/removeintervalex1.png" style="width: 510px; height: 319px;" />
<pre>
<strong>Input:</strong> intervals = [[0,2],[3,4],[5,7]], toBeRemoved = [1,6]
<strong>Output:</strong> [[0,1],[6,7]]
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1200-1299/1272.Remove%20Interval/images/removeintervalex2.png" style="width: 410px; height: 318px;" />
<pre>
<strong>Input:</strong> intervals = [[0,5]], toBeRemoved = [2,3]
<strong>Output:</strong> [[0,2],[3,5]]
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> intervals = [[-5,-4],[-3,-2],[1,2],[3,5],[8,9]], toBeRemoved = [-1,4]
<strong>Output:</strong> [[-5,-4],[-3,-2],[4,5],[8,9]]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= intervals.length &lt;= 10<sup>4</sup></code></li>
	<li><code>-10<sup>9</sup> &lt;= a<sub>i</sub> &lt; b<sub>i</sub> &lt;= 10<sup>9</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

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
