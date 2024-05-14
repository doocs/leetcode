# [365. Water and Jug Problem](https://leetcode.com/problems/water-and-jug-problem)

[中文文档](/solution/0300-0399/0365.Water%20and%20Jug%20Problem/README.md)

<!-- tags:Depth-First Search,Breadth-First Search,Math -->

<!-- difficulty:Medium -->

## Description

<p>You are given two jugs with capacities <code>x</code> liters and <code>y</code> liters. You have an infinite water supply. Return whether the total amount of water in both jugs may reach <code>target</code> using the following operations:</p>

<ul>
	<li>Fill either jug completely with water.</li>
	<li>Completely empty either jug.</li>
	<li>Pour water from one jug into another until the receiving jug is full, or the transferring jug is empty.</li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Example 1: </strong></p>

<div class="example-block" style="border-color: var(--border-tertiary); border-left-width: 2px; color: var(--text-secondary); font-size: .875rem; margin-bottom: 1rem; margin-top: 1rem; overflow: visible; padding-left: 1rem;">
<p><strong>Input: </strong> <span class="example-io" style="font-family: Menlo,sans-serif; font-size: 0.85rem;"> x = 3, y = 5, target = 4 </span></p>

<p><strong>Output: </strong> <span class="example-io" style="font-family: Menlo,sans-serif; font-size: 0.85rem;"> true </span></p>

<p><strong>Explanation:</strong></p>

<p>Follow these steps to reach a total of 4 liters:</p>

<ol>
	<li>Fill the 5-liter jug (0, 5).</li>
	<li>Pour from the 5-liter jug into the 3-liter jug, leaving 2 liters (3, 2).</li>
	<li>Empty the 3-liter jug (0, 2).</li>
	<li>Transfer the 2 liters from the 5-liter jug to the 3-liter jug (2, 0).</li>
	<li>Fill the 5-liter jug again (2, 5).</li>
	<li>Pour from the 5-liter jug into the 3-liter jug until the 3-liter jug is full. This leaves 4 liters in the 5-liter jug (3, 4).</li>
	<li>Empty the 3-liter jug. Now, you have exactly 4 liters in the 5-liter jug (0, 4).</li>
</ol>

<p>Reference: The <a href="https://www.youtube.com/watch?v=BVtQNK_ZUJg&amp;ab_channel=notnek01" target="_blank">Die Hard</a> example.</p>
</div>

<p><strong class="example">Example 2: </strong></p>

<div class="example-block" style="border-color: var(--border-tertiary); border-left-width: 2px; color: var(--text-secondary); font-size: .875rem; margin-bottom: 1rem; margin-top: 1rem; overflow: visible; padding-left: 1rem;">
<p><strong>Input: </strong> <span class="example-io" style="font-family: Menlo,sans-serif; font-size: 0.85rem;"> x = 2, y = 6, target = 5 </span></p>

<p><strong>Output: </strong> <span class="example-io" style="font-family: Menlo,sans-serif; font-size: 0.85rem;"> false </span></p>
</div>

<p><strong class="example">Example 3: </strong></p>

<div class="example-block" style="border-color: var(--border-tertiary); border-left-width: 2px; color: var(--text-secondary); font-size: .875rem; margin-bottom: 1rem; margin-top: 1rem; overflow: visible; padding-left: 1rem;">
<p><strong>Input: </strong> <span class="example-io" style="font-family: Menlo,sans-serif; font-size: 0.85rem;"> x = 1, y = 2, target = 3 </span></p>

<p><strong>Output: </strong> <span class="example-io" style="font-family: Menlo,sans-serif; font-size: 0.85rem;"> true </span></p>

<p><strong>Explanation:</strong> Fill both jugs. The total amount of water in both jugs is equal to 3 now.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= x, y, target&nbsp;&lt;= 10<sup>3</sup></code></li>
</ul>

## Solutions

### Solution 1: DFS

Let's denote $jug1Capacity$ as $x$, $jug2Capacity$ as $y$, and $targetCapacity$ as $z$.

Next, we design a function $dfs(i, j)$, which represents whether we can get $z$ liters of water when there are $i$ liters of water in $jug1$ and $j$ liters of water in $jug2$.

The execution process of the function $dfs(i, j)$ is as follows:

-   If $(i, j)$ has been visited, return $false$.
-   If $i = z$ or $j = z$ or $i + j = z$, return $true$.
-   If we can get $z$ liters of water by filling $jug1$ or $jug2$, or emptying $jug1$ or $jug2$, return $true$.
-   If we can get $z$ liters of water by pouring water from $jug1$ into $jug2$, or pouring water from $jug2$ into $jug1$, return $true$.

The answer is $dfs(0, 0)$.

The time complexity is $O(x + y)$, and the space complexity is $O(x + y)$. Here, $x$ and $y$ are the sizes of $jug1Capacity$ and $jug2Capacity$ respectively.

<!-- tabs:start -->

```python
class Solution:
    def canMeasureWater(self, x: int, y: int, z: int) -> bool:
        def dfs(i: int, j: int) -> bool:
            if (i, j) in vis:
                return False
            vis.add((i, j))
            if i == z or j == z or i + j == z:
                return True
            if dfs(x, j) or dfs(i, y) or dfs(0, j) or dfs(i, 0):
                return True
            a = min(i, y - j)
            b = min(j, x - i)
            return dfs(i - a, j + a) or dfs(i + b, j - b)

        vis = set()
        return dfs(0, 0)
```

```java
class Solution {
    private Set<Long> vis = new HashSet<>();
    private int x, y, z;

    public boolean canMeasureWater(int jug1Capacity, int jug2Capacity, int targetCapacity) {
        x = jug1Capacity;
        y = jug2Capacity;
        z = targetCapacity;
        return dfs(0, 0);
    }

    private boolean dfs(int i, int j) {
        long st = f(i, j);
        if (!vis.add(st)) {
            return false;
        }
        if (i == z || j == z || i + j == z) {
            return true;
        }
        if (dfs(x, j) || dfs(i, y) || dfs(0, j) || dfs(i, 0)) {
            return true;
        }
        int a = Math.min(i, y - j);
        int b = Math.min(j, x - i);
        return dfs(i - a, j + a) || dfs(i + b, j - b);
    }

    private long f(int i, int j) {
        return i * 1000000L + j;
    }
}
```

```cpp
class Solution {
public:
    bool canMeasureWater(int x, int y, int z) {
        using pii = pair<int, int>;
        stack<pii> stk;
        stk.emplace(0, 0);
        auto hash_function = [](const pii& o) { return hash<int>()(o.first) ^ hash<int>()(o.second); };
        unordered_set<pii, decltype(hash_function)> vis(0, hash_function);
        while (stk.size()) {
            auto st = stk.top();
            stk.pop();
            if (vis.count(st)) {
                continue;
            }
            vis.emplace(st);
            auto [i, j] = st;
            if (i == z || j == z || i + j == z) {
                return true;
            }
            stk.emplace(x, j);
            stk.emplace(i, y);
            stk.emplace(0, j);
            stk.emplace(i, 0);
            int a = min(i, y - j);
            int b = min(j, x - i);
            stk.emplace(i - a, j + a);
            stk.emplace(i + b, j - b);
        }
        return false;
    }
};
```

```go
func canMeasureWater(x int, y int, z int) bool {
	type pair struct{ x, y int }
	vis := map[pair]bool{}
	var dfs func(int, int) bool
	dfs = func(i, j int) bool {
		st := pair{i, j}
		if vis[st] {
			return false
		}
		vis[st] = true
		if i == z || j == z || i+j == z {
			return true
		}
		if dfs(x, j) || dfs(i, y) || dfs(0, j) || dfs(i, 0) {
			return true
		}
		a := min(i, y-j)
		b := min(j, x-i)
		return dfs(i-a, j+a) || dfs(i+b, j-b)
	}
	return dfs(0, 0)
}
```

<!-- tabs:end -->

<!-- end -->
