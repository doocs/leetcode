# [1776. 车队 II](https://leetcode.cn/problems/car-fleet-ii)

[English Version](/solution/1700-1799/1776.Car%20Fleet%20II/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>在一条单车道上有 <code>n</code> 辆车，它们朝着同样的方向行驶。给你一个长度为 <code>n</code> 的数组 <code>cars</code> ，其中 <code>cars[i] = [position<sub>i</sub>, speed<sub>i</sub>]</code> ，它表示：</p>

<ul>
	<li><code>position<sub>i</sub></code> 是第 <code>i</code> 辆车和道路起点之间的距离（单位：米）。题目保证 <code>position<sub>i</sub> < position<sub>i+1</sub></code><sub> </sub>。</li>
	<li><code>speed<sub>i</sub></code> 是第 <code>i</code> 辆车的初始速度（单位：米/秒）。</li>
</ul>

<p>简单起见，所有车子可以视为在数轴上移动的点。当两辆车占据同一个位置时，我们称它们相遇了。一旦两辆车相遇，它们会合并成一个车队，这个车队里的车有着同样的位置和相同的速度，速度为这个车队里 <strong>最慢</strong> 一辆车的速度。</p>

<p>请你返回一个数组 <code>answer</code> ，其中 <code>answer[i]</code> 是第 <code>i</code> 辆车与下一辆车相遇的时间（单位：秒），如果这辆车不会与下一辆车相遇，则 <code>answer[i]</code> 为 <code>-1</code> 。答案精度误差需在 <code>10<sup>-5</sup></code> 以内。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<b>输入：</b>cars = [[1,2],[2,1],[4,3],[7,2]]
<b>输出：</b>[1.00000,-1.00000,3.00000,-1.00000]
<b>解释：</b>经过恰好 1 秒以后，第一辆车会与第二辆车相遇，并形成一个 1 m/s 的车队。经过恰好 3 秒以后，第三辆车会与第四辆车相遇，并形成一个 2 m/s 的车队。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<b>输入：</b>cars = [[3,4],[5,4],[6,3],[9,1]]
<b>输出：</b>[2.00000,1.00000,1.50000,-1.00000]
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= cars.length <= 10<sup>5</sup></code></li>
	<li><code>1 <= position<sub>i</sub>, speed<sub>i</sub> <= 10<sup>6</sup></code></li>
	<li><code>position<sub>i</sub> < position<sub>i+1</sub></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：栈**

由于每一辆车最终追上其右边第一辆车的时间与其左边的车没有关系，因此，我们可以从右往左遍历，计算每辆车与其右边第一辆车相遇的时间。

具体地，我们维护一个栈，栈中存放的是车辆的编号，栈顶元素表示当前最慢的车辆编号，栈底元素表示当前最快的车辆编号。

当我们遍历到第 $i$ 辆车时，如果第 $i$ 辆车的速度大于栈顶元素对应的车辆 $j$ 的速度，此时我们计算两车相遇的时间，记为 $t$。如果车辆 $j$ 与右边车辆不会相遇，或者 $t$ 小于等于 $j$ 与右辆车相遇的时间，说明车辆 $i$ 可以在 $t$ 时间追上车辆 $j$，更新答案。否则，说明车辆 $i$ 不会与车辆 $j$ 相遇，我们将车辆 $j$ 出栈，继续判断车辆 $i$ 与栈顶元素对应的车辆相遇的时间。如果栈为空，说明车辆 $i$ 不会与任何车辆相遇，更新答案为 $-1$。最后，将车辆 $i$ 入栈。

遍历完所有车辆后，即可得到答案。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为车辆数量。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def getCollisionTimes(self, cars: List[List[int]]) -> List[float]:
        stk = []
        n = len(cars)
        ans = [-1] * n
        for i in range(n - 1, -1, -1):
            while stk:
                j = stk[-1]
                if cars[i][1] > cars[j][1]:
                    t = (cars[j][0] - cars[i][0]) / (cars[i][1] - cars[j][1])
                    if ans[j] == -1 or t <= ans[j]:
                        ans[i] = t
                        break
                stk.pop()
            stk.append(i)
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public double[] getCollisionTimes(int[][] cars) {
        int n = cars.length;
        double[] ans = new double[n];
        Arrays.fill(ans, -1.0);
        Deque<Integer> stk = new ArrayDeque<>();
        for (int i = n - 1; i >= 0; --i) {
            while (!stk.isEmpty()) {
                int j = stk.peek();
                if (cars[i][1] > cars[j][1]) {
                    double t = (cars[j][0] - cars[i][0]) * 1.0 / (cars[i][1] - cars[j][1]);
                    if (ans[j] < 0 || t <= ans[j]) {
                        ans[i] = t;
                        break;
                    }
                }
                stk.pop();
            }
            stk.push(i);
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<double> getCollisionTimes(vector<vector<int>>& cars) {
        int n = cars.size();
        vector<double> ans(n, -1.0);
        stack<int> stk;
        for (int i = n - 1; ~i; --i) {
            while (stk.size()) {
                int j = stk.top();
                if (cars[i][1] > cars[j][1]) {
                    double t = (cars[j][0] - cars[i][0]) * 1.0 / (cars[i][1] - cars[j][1]);
                    if (ans[j] < 0 || t <= ans[j]) {
                        ans[i] = t;
                        break;
                    }
                }
                stk.pop();
            }
            stk.push(i);
        }
        return ans;
    }
};
```

### **Go**

```go
func getCollisionTimes(cars [][]int) []float64 {
	n := len(cars)
	ans := make([]float64, n)
	for i := range ans {
		ans[i] = -1.0
	}
	stk := []int{}
	for i := n - 1; i >= 0; i-- {
		for len(stk) > 0 {
			j := stk[len(stk)-1]
			if cars[i][1] > cars[j][1] {
				t := float64(cars[j][0]-cars[i][0]) / float64(cars[i][1]-cars[j][1])
				if ans[j] < 0 || t <= ans[j] {
					ans[i] = t
					break
				}
			}
			stk = stk[:len(stk)-1]
		}
		stk = append(stk, i)
	}
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
