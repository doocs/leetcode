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

**方法一：枚举**

枚举所有的格点，判断其是否在圆内，如果在圆内，则答案加一。

枚举的时候，可以将所有圆的最大横纵坐标求出来，作为枚举的上界。

时间复杂度 $O(X \times Y \times n)$，空间复杂度 $O(1)$。其中 $X$ 和 $Y$ 分别为所有圆的最大横纵坐标，而 $n$ 为圆的个数。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def countLatticePoints(self, circles: List[List[int]]) -> int:
        ans = 0
        mx = max(x + r for x, _, r in circles)
        my = max(y + r for _, y, r in circles)
        for i in range(mx + 1):
            for j in range(my + 1):
                for x, y, r in circles:
                    dx, dy = i - x, j - y
                    if dx * dx + dy * dy <= r * r:
                        ans += 1
                        break
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int countLatticePoints(int[][] circles) {
        int mx = 0, my = 0;
        for (var c : circles) {
            mx = Math.max(mx, c[0] + c[2]);
            my = Math.max(my, c[1] + c[2]);
        }
        int ans = 0;
        for (int i = 0; i <= mx; ++i) {
            for (int j = 0; j <= my; ++j) {
                for (var c : circles) {
                    int dx = i - c[0], dy = j - c[1];
                    if (dx * dx + dy * dy <= c[2] * c[2]) {
                        ++ans;
                        break;
                    }
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
    int countLatticePoints(vector<vector<int>>& circles) {
        int mx = 0, my = 0;
        for (auto& c : circles) {
            mx = max(mx, c[0] + c[2]);
            my = max(my, c[1] + c[2]);
        }
        int ans = 0;
        for (int i = 0; i <= mx; ++i) {
            for (int j = 0; j <= my; ++j) {
                for (auto& c : circles) {
                    int dx = i - c[0], dy = j - c[1];
                    if (dx * dx + dy * dy <= c[2] * c[2]) {
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
func countLatticePoints(circles [][]int) (ans int) {
	mx, my := 0, 0
	for _, c := range circles {
		mx = max(mx, c[0]+c[2])
		my = max(my, c[1]+c[2])
	}
	for i := 0; i <= mx; i++ {
		for j := 0; j <= my; j++ {
			for _, c := range circles {
				dx, dy := i-c[0], j-c[1]
				if dx*dx+dy*dy <= c[2]*c[2] {
					ans++
					break
				}
			}
		}
	}
	return
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
```

### **TypeScript**

```ts
function countLatticePoints(circles: number[][]): number {
    let mx = 0;
    let my = 0;
    for (const [x, y, r] of circles) {
        mx = Math.max(mx, x + r);
        my = Math.max(my, y + r);
    }
    let ans = 0;
    for (let i = 0; i <= mx; ++i) {
        for (let j = 0; j <= my; ++j) {
            for (const [x, y, r] of circles) {
                const dx = i - x;
                const dy = j - y;
                if (dx * dx + dy * dy <= r * r) {
                    ++ans;
                    break;
                }
            }
        }
    }
    return ans;
}
```

### **...**

```

```

<!-- tabs:end -->
