# [2463. Minimum Total Distance Traveled](https://leetcode.com/problems/minimum-total-distance-traveled)

[中文文档](/solution/2400-2499/2463.Minimum%20Total%20Distance%20Traveled/README.md)

## Description

<p>There are some robots and factories on the X-axis. You are given an integer array <code>robot</code> where <code>robot[i]</code> is the position of the <code>i<sup>th</sup></code> robot. You are also given a 2D integer array <code>factory</code> where <code>factory[j] = [position<sub>j</sub>, limit<sub>j</sub>]</code> indicates that <code>position<sub>j</sub></code> is the position of the <code>j<sup>th</sup></code> factory and that the <code>j<sup>th</sup></code> factory can repair at most <code>limit<sub>j</sub></code> robots.</p>

<p>The positions of each robot are <strong>unique</strong>. The positions of each factory are also <strong>unique</strong>. Note that a robot can be <strong>in the same position</strong> as a factory initially.</p>

<p>All the robots are initially broken; they keep moving in one direction. The direction could be the negative or the positive direction of the X-axis. When a robot reaches a factory that did not reach its limit, the factory repairs the robot, and it stops moving.</p>

<p><strong>At any moment</strong>, you can set the initial direction of moving for <strong>some</strong> robot. Your target is to minimize the total distance traveled by all the robots.</p>

<p>Return <em>the minimum total distance traveled by all the robots</em>. The test cases are generated such that all the robots can be repaired.</p>

<p><strong>Note that</strong></p>

<ul>
	<li>All robots move at the same speed.</li>
	<li>If two robots move in the same direction, they will never collide.</li>
	<li>If two robots move in opposite directions and they meet at some point, they do not collide. They cross each other.</li>
	<li>If a robot passes by a factory that reached its limits, it crosses it as if it does not exist.</li>
	<li>If the robot moved from a position <code>x</code> to a position <code>y</code>, the distance it moved is <code>|y - x|</code>.</li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2400-2499/2463.Minimum%20Total%20Distance%20Traveled/images/example1.jpg" style="width: 500px; height: 320px;" />
<pre>
<strong>Input:</strong> robot = [0,4,6], factory = [[2,2],[6,2]]
<strong>Output:</strong> 4
<strong>Explanation:</strong> As shown in the figure:
- The first robot at position 0 moves in the positive direction. It will be repaired at the first factory.
- The second robot at position 4 moves in the negative direction. It will be repaired at the first factory.
- The third robot at position 6 will be repaired at the second factory. It does not need to move.
The limit of the first factory is 2, and it fixed 2 robots.
The limit of the second factory is 2, and it fixed 1 robot.
The total distance is |2 - 0| + |2 - 4| + |6 - 6| = 4. It can be shown that we cannot achieve a better total distance than 4.
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2400-2499/2463.Minimum%20Total%20Distance%20Traveled/images/example-2.jpg" style="width: 500px; height: 329px;" />
<pre>
<strong>Input:</strong> robot = [1,-1], factory = [[-2,1],[2,1]]
<strong>Output:</strong> 2
<strong>Explanation:</strong> As shown in the figure:
- The first robot at position 1 moves in the positive direction. It will be repaired at the second factory.
- The second robot at position -1 moves in the negative direction. It will be repaired at the first factory.
The limit of the first factory is 1, and it fixed 1 robot.
The limit of the second factory is 1, and it fixed 1 robot.
The total distance is |2 - 1| + |(-2) - (-1)| = 2. It can be shown that we cannot achieve a better total distance than 2.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= robot.length, factory.length &lt;= 100</code></li>
	<li><code>factory[j].length == 2</code></li>
	<li><code>-10<sup>9</sup> &lt;= robot[i], position<sub>j</sub> &lt;= 10<sup>9</sup></code></li>
	<li><code>0 &lt;= limit<sub>j</sub> &lt;= robot.length</code></li>
	<li>The input will be generated such that it is always possible to repair every robot.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def minimumTotalDistance(self, robot: List[int], factory: List[List[int]]) -> int:
        @cache
        def dfs(i, j):
            if i == len(robot):
                return 0
            if j == len(factory):
                return inf
            ans = dfs(i, j + 1)
            t = 0
            for k in range(factory[j][1]):
                if i + k == len(robot):
                    break
                t += abs(robot[i + k] - factory[j][0])
                ans = min(ans, t + dfs(i + k + 1, j + 1))
            return ans

        robot.sort()
        factory.sort()
        ans = dfs(0, 0)
        dfs.cache_clear()
        return ans
```

### **Java**

```java
class Solution {
    private long[][] f;
    private List<Integer> robot;
    private int[][] factory;

    public long minimumTotalDistance(List<Integer> robot, int[][] factory) {
        Collections.sort(robot);
        Arrays.sort(factory, (a, b) -> a[0] - b[0]);
        this.robot = robot;
        this.factory = factory;
        f = new long[robot.size()][factory.length];
        return dfs(0, 0);
    }

    private long dfs(int i, int j) {
        if (i == robot.size()) {
            return 0;
        }
        if (j == factory.length) {
            return Long.MAX_VALUE / 1000;
        }
        if (f[i][j] != 0) {
            return f[i][j];
        }
        long ans = dfs(i, j + 1);
        long t = 0;
        for (int k = 0; k < factory[j][1]; ++k) {
            if (i + k == robot.size()) {
                break;
            }
            t += Math.abs(robot.get(i + k) - factory[j][0]);
            ans = Math.min(ans, t + dfs(i + k + 1, j + 1));
        }
        f[i][j] = ans;
        return ans;
    }
}
```

### **C++**

```cpp
using ll = long long;

class Solution {
public:
    long long minimumTotalDistance(vector<int>& robot, vector<vector<int>>& factory) {
        sort(robot.begin(), robot.end());
        sort(factory.begin(), factory.end());
        vector<vector<ll>> f(robot.size(), vector<ll>(factory.size()));
        function<ll(int i, int j)> dfs = [&](int i, int j) -> ll {
            if (i == robot.size()) return 0;
            if (j == factory.size()) return 1e15;
            if (f[i][j]) return f[i][j];
            ll ans = dfs(i, j + 1);
            ll t = 0;
            for (int k = 0; k < factory[j][1]; ++k) {
                if (i + k >= robot.size()) break;
                t += abs(robot[i + k] - factory[j][0]);
                ans = min(ans, t + dfs(i + k + 1, j + 1));
            }
            f[i][j] = ans;
            return ans;
        };
        return dfs(0, 0);
    }
};
```

### **Go**

```go
func minimumTotalDistance(robot []int, factory [][]int) int64 {
	sort.Ints(robot)
	sort.Slice(factory, func(i, j int) bool { return factory[i][0] < factory[j][0] })
	f := make([][]int, len(robot))
	for i := range f {
		f[i] = make([]int, len(factory))
	}
	var dfs func(i, j int) int
	dfs = func(i, j int) int {
		if i == len(robot) {
			return 0
		}
		if j == len(factory) {
			return 1e15
		}
		if f[i][j] != 0 {
			return f[i][j]
		}
		ans := dfs(i, j+1)
		t := 0
		for k := 0; k < factory[j][1]; k++ {
			if i+k >= len(robot) {
				break
			}
			t += abs(robot[i+k] - factory[j][0])
			ans = min(ans, t+dfs(i+k+1, j+1))
		}
		f[i][j] = ans
		return ans
	}
	return int64(dfs(0, 0))
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

### **TypeScript**

```ts

```

### **...**

```

```

<!-- tabs:end -->
