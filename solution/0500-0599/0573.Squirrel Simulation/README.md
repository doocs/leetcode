# [573. 松鼠模拟](https://leetcode.cn/problems/squirrel-simulation)

[English Version](/solution/0500-0599/0573.Squirrel%20Simulation/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>现在有一棵树，一只松鼠和一些坚果。位置由二维网格的单元格表示。你的目标是找到松鼠收集所有坚果的<strong>最小路程</strong>，且坚果是一颗接一颗地被放在树下。松鼠一次最多只能携带<strong>一颗</strong>坚果，松鼠可以向上，向下，向左和向右四个方向移动到相邻的单元格。移动次数表示路程。</p>

<p><strong>输入 1:</strong></p>

<pre><strong>输入:</strong> 
高度 : 5
宽度 : 7
树的位置 : [2,2]
松鼠 : [4,4]
坚果 : [[3,0], [2,5]]
<strong>输出:</strong> 12
<strong>解释:</strong>
<img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0500-0599/0573.Squirrel%20Simulation/images/squirrel_simulation.png" style="width: 40%;">​​​​​
</pre>

<p><strong>注意:</strong></p>

<ol>
	<li>所有给定的位置不会重叠。</li>
	<li>松鼠一次最多只能携带一颗坚果。</li>
	<li>给定的坚果位置没有顺序。</li>
	<li>高度和宽度是正整数。 3 &lt;= 高度 * 宽度 &lt;= 10,000。</li>
	<li>给定的网格至少包含一颗坚果，唯一的一棵树和一只松鼠。</li>
</ol>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：路径分析**

我们观察松鼠的移动路径，可以发现，松鼠会首先移动到某个坚果的位置，然后移动到树的位置。接下来，松鼠的移动路径之和等于“其余坚果到树的位置之和”再乘以 $2$。

因此，我们只需要选出一个坚果，作为松鼠的第一个目标，使得其到树的位置之和最小，即可得到最小路径。

时间复杂度 $O(n)$，空间复杂度 $O(1)$。其中 $n$ 为坚果的数量。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def minDistance(
        self,
        height: int,
        width: int,
        tree: List[int],
        squirrel: List[int],
        nuts: List[List[int]],
    ) -> int:
        x, y, a, b = *tree, *squirrel
        s = sum(abs(i - x) + abs(j - y) for i, j in nuts) * 2
        ans = inf
        for i, j in nuts:
            c = abs(i - x) + abs(j - y)
            d = abs(i - a) + abs(j - b) + c
            ans = min(ans, s + d - c * 2)
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int minDistance(int height, int width, int[] tree, int[] squirrel, int[][] nuts) {
        int ans = Integer.MAX_VALUE;
        int s = 0;
        for (int[] a : nuts) {
            s += f(a, tree);
        }
        s *= 2;
        for (int[] a : nuts) {
            int c = f(a, tree);
            int d = f(a, squirrel) + c;
            ans = Math.min(ans, s + d - c * 2);
        }
        return ans;
    }

    private int f(int[] a, int[] b) {
        return Math.abs(a[0] - b[0]) + Math.abs(a[1] - b[1]);
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int minDistance(int height, int width, vector<int>& tree, vector<int>& squirrel, vector<vector<int>>& nuts) {
        int ans = INT_MAX;
        int s = 0;
        for (auto& a : nuts) {
            s += f(a, tree);
        }
        s *= 2;
        for (auto& a : nuts) {
            int c = f(a, tree);
            int d = f(a, squirrel) + c;
            ans = min(ans, s + d - c * 2);
        }
        return ans;
    }

    int f(vector<int>& a, vector<int>& b) {
        return abs(a[0] - b[0]) + abs(a[1] - b[1]);
    }
};
```

### **Go**

```go
func minDistance(height int, width int, tree []int, squirrel []int, nuts [][]int) int {
	f := func(a, b []int) int {
		return abs(a[0]-b[0]) + abs(a[1]-b[1])
	}
	ans := math.MaxInt32
	s := 0
	for _, a := range nuts {
		s += f(a, tree)
	}
	s *= 2
	for _, a := range nuts {
		c := f(a, tree)
		d := f(a, squirrel) + c
		ans = min(ans, s+d-c*2)
	}
	return ans
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}
```

### **...**

```

```

<!-- tabs:end -->
