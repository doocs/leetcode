# [1232. 缀点成线](https://leetcode.cn/problems/check-if-it-is-a-straight-line)

[English Version](/solution/1200-1299/1232.Check%20If%20It%20Is%20a%20Straight%20Line/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个数组&nbsp;<code>coordinates</code>&nbsp;，其中&nbsp;<code>coordinates[i] = [x, y]</code>&nbsp;，<meta charset="UTF-8" />&nbsp;<code>[x, y]</code>&nbsp;表示横坐标为 <code>x</code>、纵坐标为 <code>y</code>&nbsp;的点。请你来判断，这些点是否在该坐标系中属于同一条直线上。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1200-1299/1232.Check%20If%20It%20Is%20a%20Straight%20Line/images/untitled-diagram-2.jpg" /></p>

<pre>
<strong>输入：</strong>coordinates = [[1,2],[2,3],[3,4],[4,5],[5,6],[6,7]]
<strong>输出：</strong>true
</pre>

<p><strong>示例 2：</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1200-1299/1232.Check%20If%20It%20Is%20a%20Straight%20Line/images/untitled-diagram-1.jpg" /></strong></p>

<pre>
<strong>输入：</strong>coordinates = [[1,1],[2,2],[3,4],[4,5],[5,6],[7,7]]
<strong>输出：</strong>false
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;=&nbsp;coordinates.length &lt;= 1000</code></li>
	<li><code>coordinates[i].length == 2</code></li>
	<li><code>-10^4 &lt;=&nbsp;coordinates[i][0],&nbsp;coordinates[i][1] &lt;= 10^4</code></li>
	<li><code>coordinates</code>&nbsp;中不含重复的点</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：数学**

时间复杂度 $O(n)$，空间复杂度 $O(1)$。其中 $n$ 表示 `coordinates` 数组的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def checkStraightLine(self, coordinates: List[List[int]]) -> bool:
        x1, y1 = coordinates[0]
        x2, y2 = coordinates[1]
        for x, y in coordinates[2:]:
            if (x - x1) * (y2 - y1) != (y - y1) * (x2 - x1):
                return False
        return True
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public boolean checkStraightLine(int[][] coordinates) {
        int x1 = coordinates[0][0], y1 = coordinates[0][1];
        int x2 = coordinates[1][0], y2 = coordinates[1][1];
        for (int i = 2; i < coordinates.length; ++i) {
            int x = coordinates[i][0], y = coordinates[i][1];
            if ((x - x1) * (y2 - y1) != (y - y1) * (x2 - x1)) {
                return false;
            }
        }
        return true;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    bool checkStraightLine(vector<vector<int>>& coordinates) {
        int x1 = coordinates[0][0], y1 = coordinates[0][1];
        int x2 = coordinates[1][0], y2 = coordinates[1][1];
        for (int i = 2; i < coordinates.size(); ++i) {
            int x = coordinates[i][0], y = coordinates[i][1];
            if ((x - x1) * (y2 - y1) != (y - y1) * (x2 - x1)) {
                return false;
            }
        }
        return true;
    }
};
```

### **Go**

```go
func checkStraightLine(coordinates [][]int) bool {
	x1, y1 := coordinates[0][0], coordinates[0][1]
	x2, y2 := coordinates[1][0], coordinates[1][1]
	for i := 2; i < len(coordinates); i++ {
		x, y := coordinates[i][0], coordinates[i][1]
		if (x-x1)*(y2-y1) != (y-y1)*(x2-x1) {
			return false
		}
	}
	return true
}
```

### **...**

```

```

<!-- tabs:end -->
