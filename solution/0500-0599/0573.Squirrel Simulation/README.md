# [573. 松鼠模拟](https://leetcode.cn/problems/squirrel-simulation)

[English Version](/solution/0500-0599/0573.Squirrel%20Simulation/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你两个整数 <code>height</code> 和 <code>width</code> ，代表一个大小为 <code>height x width</code> 的花园。你还得到了以下信息：</p>

<ul>
	<li>一个数组 <code>tree</code> ，其中 <code>tree = [tree<sub>r</sub>, tree<sub>c</sub>]</code> 是花园中树的位置，</li>
	<li>一个数组 <code>squirrel</code> ，其中 <code>squirrel = [squirrel<sub>r</sub>, squirrel<sub>c</sub>]</code> 是花园中松鼠的位置，</li>
	<li>一个数组 <code>nuts</code> ，其中 <code>nuts[i] = [nut<sub>i<sub>r</sub></sub>, nut<sub>i<sub>c</sub></sub>]</code> 是花园中第 <code>i<sup>th</sup></code> 个坚果的位置。</li>
</ul>

<p>松鼠一次最多只能携带一个坚果，并且能够向上、下、左、右四个方向移动到相邻的单元格。</p>

<p>返回松鼠收集所有坚果并逐一放在树下的 <strong>最小距离</strong> 。</p>

<p><strong>距离</strong> 是指移动的次数。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0500-0599/0573.Squirrel%20Simulation/images/squirrel1-grid.jpg" style="width: 573px; height: 413px;" />
<pre>
<strong>输入：</strong>height = 5, width = 7, tree = [2,2], squirrel = [4,4], nuts = [[3,0], [2,5]]
<strong>输出：</strong>12
<strong>解释：</strong>为实现最小的距离，松鼠应该先摘 [2, 5] 位置的坚果。
</pre>

<p><strong class="example">示例 2：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0500-0599/0573.Squirrel%20Simulation/images/squirrel2-grid.jpg" style="width: 253px; height: 93px;" />
<pre>
<strong>输入：</strong>height = 1, width = 3, tree = [0,1], squirrel = [0,0], nuts = [[0,2]]
<strong>输出：</strong>3
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= height, width &lt;= 100</code></li>
	<li><code>tree.length == 2</code></li>
	<li><code>squirrel.length == 2</code></li>
	<li><code>1 &lt;= nuts.length &lt;= 5000</code></li>
	<li><code>nuts[i].length == 2</code></li>
	<li><code>0 &lt;= tree<sub>r</sub>, squirrel<sub>r</sub>, nut<sub>i<sub>r</sub></sub> &lt;= height</code></li>
	<li><code>0 &lt;= tree<sub>c</sub>, squirrel<sub>c</sub>, nut<sub>i<sub>c</sub></sub> &lt;= width</code></li>
</ul>

## 解法

### 方法一：路径分析

我们观察松鼠的移动路径，可以发现，松鼠会首先移动到某个坚果的位置，然后移动到树的位置。接下来，松鼠的移动路径之和等于“其余坚果到树的位置之和”再乘以 $2$。

因此，我们只需要选出一个坚果，作为松鼠的第一个目标，使得其到树的位置之和最小，即可得到最小路径。

时间复杂度 $O(n)$，空间复杂度 $O(1)$。其中 $n$ 为坚果的数量。

<!-- tabs:start -->

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

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}
```

<!-- tabs:end -->

<!-- end -->
