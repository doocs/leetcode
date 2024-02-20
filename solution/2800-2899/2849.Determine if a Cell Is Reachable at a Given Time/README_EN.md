# [2849. Determine if a Cell Is Reachable at a Given Time](https://leetcode.com/problems/determine-if-a-cell-is-reachable-at-a-given-time)

[中文文档](/solution/2800-2899/2849.Determine%20if%20a%20Cell%20Is%20Reachable%20at%20a%20Given%20Time/README.md)

<!-- tags:Math -->

## Description

<p>You are given four integers <code>sx</code>, <code>sy</code>, <code>fx</code>, <code>fy</code>, and a <strong>non-negative</strong> integer <code>t</code>.</p>

<p>In an infinite 2D grid, you start at the cell <code>(sx, sy)</code>. Each second, you <strong>must</strong> move to any of its adjacent cells.</p>

<p>Return <code>true</code> <em>if you can reach cell </em><code>(fx, fy)</code> <em>after<strong> exactly</strong></em> <code>t</code> <strong><em>seconds</em></strong>, <em>or</em> <code>false</code> <em>otherwise</em>.</p>

<p>A cell&#39;s <strong>adjacent cells</strong> are the 8 cells around it that share at least one corner with it. You can visit the same cell several times.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2800-2899/2849.Determine%20if%20a%20Cell%20Is%20Reachable%20at%20a%20Given%20Time/images/example2.svg" style="width: 443px; height: 243px;" />
<pre>
<strong>Input:</strong> sx = 2, sy = 4, fx = 7, fy = 7, t = 6
<strong>Output:</strong> true
<strong>Explanation:</strong> Starting at cell (2, 4), we can reach cell (7, 7) in exactly 6 seconds by going through the cells depicted in the picture above. 
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2800-2899/2849.Determine%20if%20a%20Cell%20Is%20Reachable%20at%20a%20Given%20Time/images/example1.svg" style="width: 383px; height: 202px;" />
<pre>
<strong>Input:</strong> sx = 3, sy = 1, fx = 7, fy = 3, t = 3
<strong>Output:</strong> false
<strong>Explanation:</strong> Starting at cell (3, 1), it takes at least 4 seconds to reach cell (7, 3) by going through the cells depicted in the picture above. Hence, we cannot reach cell (7, 3) at the third second.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= sx, sy, fx, fy &lt;= 10<sup>9</sup></code></li>
	<li><code>0 &lt;= t &lt;= 10<sup>9</sup></code></li>
</ul>

## Solutions

### Solution 1

<!-- tabs:start -->

```python
class Solution:
    def isReachableAtTime(self, sx: int, sy: int, fx: int, fy: int, t: int) -> bool:
        if sx == fx and sy == fy:
            return t != 1
        dx = abs(sx - fx)
        dy = abs(sy - fy)
        return max(dx, dy) <= t
```

```java
class Solution {
    public boolean isReachableAtTime(int sx, int sy, int fx, int fy, int t) {
        if (sx == fx && sy == fy) {
            return t != 1;
        }
        int dx = Math.abs(sx - fx);
        int dy = Math.abs(sy - fy);
        return Math.max(dx, dy) <= t;
    }
}
```

```cpp
class Solution {
public:
    bool isReachableAtTime(int sx, int sy, int fx, int fy, int t) {
        if (sx == fx && sy == fy) {
            return t != 1;
        }
        int dx = abs(fx - sx), dy = abs(fy - sy);
        return max(dx, dy) <= t;
    }
};
```

```go
func isReachableAtTime(sx int, sy int, fx int, fy int, t int) bool {
	if sx == fx && sy == fy {
		return t != 1
	}
	dx := abs(sx - fx)
	dy := abs(sy - fy)
	return max(dx, dy) <= t
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}
```

```ts
function isReachableAtTime(sx: number, sy: number, fx: number, fy: number, t: number): boolean {
    if (sx === fx && sy === fy) {
        return t !== 1;
    }
    const dx = Math.abs(sx - fx);
    const dy = Math.abs(sy - fy);
    return Math.max(dx, dy) <= t;
}
```

```cs
public class Solution {
    public bool IsReachableAtTime(int sx, int sy, int fx, int fy, int t) {
        if (sx == fx && sy == fy)
            return t != 1;
        return Math.Max(Math.Abs(sx - fx), Math.Abs(sy - fy)) <= t;
    }
}
```

<!-- tabs:end -->

<!-- end -->
