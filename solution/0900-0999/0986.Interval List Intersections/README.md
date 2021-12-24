# [986. 区间列表的交集](https://leetcode-cn.com/problems/interval-list-intersections)

[English Version](/solution/0900-0999/0986.Interval%20List%20Intersections/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定两个由一些<strong> 闭区间 </strong>组成的列表，<code>firstList</code> 和 <code>secondList</code> ，其中 <code>firstList[i] = [start<sub>i</sub>, end<sub>i</sub>]</code> 而 <code>secondList[j] = [start<sub>j</sub>, end<sub>j</sub>]</code> 。每个区间列表都是成对 <strong>不相交</strong> 的，并且 <strong>已经排序</strong> 。</p>

<p>返回这 <strong>两个区间列表的交集</strong> 。</p>

<p>形式上，<strong>闭区间</strong> <code>[a, b]</code>（其中 <code>a <= b</code>）表示实数 <code>x</code> 的集合，而 <code>a <= x <= b</code> 。</p>

<p>两个闭区间的 <strong>交集</strong> 是一组实数，要么为空集，要么为闭区间。例如，<code>[1, 3]</code> 和 <code>[2, 4]</code> 的交集为 <code>[2, 3]</code> 。</p>

<p> </p>

<p><strong>示例 1：</strong></p>
<img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/0900-0999/0986.Interval%20List%20Intersections/images/interval1.png" style="width: 700px; height: 194px;" />
<pre>
<strong>输入：</strong>firstList = [[0,2],[5,10],[13,23],[24,25]], secondList = [[1,5],[8,12],[15,24],[25,26]]
<strong>输出：</strong>[[1,2],[5,5],[8,10],[15,23],[24,24],[25,25]]
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>firstList = [[1,3],[5,9]], secondList = []
<strong>输出：</strong>[]
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>firstList = [], secondList = [[4,8],[10,12]]
<strong>输出：</strong>[]
</pre>

<p><strong>示例 4：</strong></p>

<pre>
<strong>输入：</strong>firstList = [[1,7]], secondList = [[3,10]]
<strong>输出：</strong>[[3,7]]
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>0 <= firstList.length, secondList.length <= 1000</code></li>
	<li><code>firstList.length + secondList.length >= 1</code></li>
	<li><code>0 <= start<sub>i</sub> < end<sub>i</sub> <= 10<sup>9</sup></code></li>
	<li><code>end<sub>i</sub> < start<sub>i+1</sub></code></li>
	<li><code>0 <= start<sub>j</sub> < end<sub>j</sub> <= 10<sup>9</sup> </code></li>
	<li><code>end<sub>j</sub> < start<sub>j+1</sub></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

双指针实现区间合并。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def intervalIntersection(self, firstList: List[List[int]], secondList: List[List[int]]) -> List[List[int]]:
        i = j = 0
        res = []
        while i < len(firstList) and j < len(secondList):
            l, r = max(firstList[i][0], secondList[j][0]), min(
                firstList[i][1], secondList[j][1])
            if l <= r:
                res.append([l, r])
            if firstList[i][1] < secondList[j][1]:
                i += 1
            else:
                j += 1
        return res
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int[][] intervalIntersection(int[][] firstList, int[][] secondList) {
        List<int[]> res = new ArrayList<>();
        for (int i = 0, j = 0; i < firstList.length && j < secondList.length;) {
            int l = Math.max(firstList[i][0], secondList[j][0]);
            int r = Math.min(firstList[i][1], secondList[j][1]);
            if (l <= r) {
                res.add(new int[]{l, r});
            }
            if (firstList[i][1] < secondList[j][1]) {
                ++i;
            } else {
                ++j;
            }
        }
        return res.toArray(new int[res.size()][]);
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<vector<int>> intervalIntersection(vector<vector<int>> &firstList, vector<vector<int>> &secondList) {
        vector<vector<int>> res;
        for (int i = 0, j = 0; i < firstList.size() && j < secondList.size();)
        {
            int l = max(firstList[i][0], secondList[j][0]);
            int r = min(firstList[i][1], secondList[j][1]);
            if (l <= r)
                res.push_back({l, r});
            if (firstList[i][1] < secondList[j][1])
                ++i;
            else
                ++j;
        }
        return res;
    }
};
```

### **Go**

```go
func intervalIntersection(firstList [][]int, secondList [][]int) [][]int {
	i, j := 0, 0
	var res [][]int
	for i < len(firstList) && j < len(secondList) {
		l, r := max(firstList[i][0], secondList[j][0]), min(firstList[i][1], secondList[j][1])
		if l <= r {
			res = append(res, []int{l, r})
		}
		if firstList[i][1] < secondList[j][1] {
			i++
		} else {
			j++
		}
	}
	return res
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}
```

### **...**

```

```

<!-- tabs:end -->
