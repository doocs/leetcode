# [2249. 统计圆内格点数目](https://leetcode.cn/problems/count-lattice-points-inside-a-circle)

[English Version](/solution/2200-2299/2249.Count%20Lattice%20Points%20Inside%20a%20Circle/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个二维整数数组 <code>circles</code> ，其中 <code>circles[i] = [x<sub>i</sub>, y<sub>i</sub>, r<sub>i</sub>]</code> 表示网格上圆心为 <code>(x<sub>i</sub>, y<sub>i</sub>)</code> 且半径为 <code>r<sub>i</sub></code> 的第 <code>i</code> 个圆，返回出现在<strong> 至少一个 </strong>圆内的 <strong>格点数目</strong> 。</p>

<p><strong>注意：</strong></p>

<ul>
	<li><strong>格点</strong> 是指整数坐标对应的点。</li>
	<li><strong>圆周上的点</strong> 也被视为出现在圆内的点。</li>
</ul>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2200-2299/2249.Count%20Lattice%20Points%20Inside%20a%20Circle/images/exa-11.png" style="width: 300px; height: 300px;" /></p>

<pre>
<strong>输入：</strong>circles = [[2,2,1]]
<strong>输出：</strong>5
<strong>解释：</strong>
给定的圆如上图所示。
出现在圆内的格点为 (1, 2)、(2, 1)、(2, 2)、(2, 3) 和 (3, 2)，在图中用绿色标识。
像 (1, 1) 和 (1, 3) 这样用红色标识的点，并未出现在圆内。
因此，出现在至少一个圆内的格点数目是 5 。</pre>

<p><strong>示例 2：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2200-2299/2249.Count%20Lattice%20Points%20Inside%20a%20Circle/images/exa-22.png" style="width: 300px; height: 300px;" /></p>

<pre>
<strong>输入：</strong>circles = [[2,2,2],[3,4,1]]
<strong>输出：</strong>16
<strong>解释：</strong>
给定的圆如上图所示。
共有 16 个格点出现在至少一个圆内。
其中部分点的坐标是 (0, 2)、(2, 0)、(2, 4)、(3, 2) 和 (4, 4) 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= circles.length &lt;= 200</code></li>
	<li><code>circles[i].length == 3</code></li>
	<li><code>1 &lt;= x<sub>i</sub>, y<sub>i</sub> &lt;= 100</code></li>
	<li><code>1 &lt;= r<sub>i</sub> &lt;= min(x<sub>i</sub>, y<sub>i</sub>)</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def countLatticePoints(self, circles: List[List[int]]) -> int:
        ans = 0
        imx = max(x + r for x, _, r in circles)
        jmx = max(y + r for _, y, r in circles)
        for i in range(imx + 1):
            for j in range(jmx + 1):
                for x, y, r in circles:
                    x, y = x - i, y - j
                    if x * x + y * y <= r * r:
                        ans += 1
                        break
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int countLatticePoints(int[][] circles) {
        int ans = 0;
        for (int i = 0; i <= 200; i++) {
            for (int j = 0; j <= 200; j++) {
                for (int[] circle : circles) {
                    int x = circle[0], y = circle[1], r = circle[2];
                    if ((i - x) * (i - x) + (j - y) * (j - y) <= r * r) {
                        ans++;
                        break;
                    }
                }
            }
        }
        return ans;
    }
}
```

### **TypeScript**

```ts
function countLatticePoints(circles: number[][]): number {
    const n = circles.length;
    let minX = Number.MAX_SAFE_INTEGER,
        minY = minX,
        maxX = Number.MIN_SAFE_INTEGER,
        maxY = maxX;
    let squares = [];
    for (let [x, y, r] of circles) {
        minX = Math.min(x - r, minX);
        minY = Math.min(y - r, minY);
        maxX = Math.max(x + r, maxX);
        maxY = Math.max(y + r, maxY);
        squares.push(r ** 2);
    }
    let ans = 0;
    for (let i = minX; i <= maxX; i++) {
        for (let j = minY; j <= maxY; j++) {
            for (let k = 0; k < n; k++) {
                const [x, y] = circles[k];
                if ((i - x) ** 2 + (j - y) ** 2 <= squares[k]) {
                    ans++;
                    break;
                }
            }
        }
    }
    return ans;
}
```

### **C++**

```cpp
class Solution {
public:
    int countLatticePoints(vector<vector<int>>& circles) {
        int ans = 0;
        for (int i = 0; i <= 200; ++i) {
            for (int j = 0; j <= 200; ++j) {
                for (auto& c : circles) {
                    int x = c[0] - i, y = c[1] - j, r = c[2];
                    if (x * x + y * y <= r * r) {
                        ++ans;
                        break;
                    }
                }
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func countLatticePoints(circles [][]int) int {
	ans := 0
	for i := 0; i <= 200; i++ {
		for j := 0; j <= 200; j++ {
			for _, c := range circles {
				x, y, r := c[0]-i, c[1]-j, c[2]
				if x*x+y*y <= r*r {
					ans++
					break
				}
			}
		}
	}
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
