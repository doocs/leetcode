# [1274. Number of Ships in a Rectangle](https://leetcode.com/problems/number-of-ships-in-a-rectangle)

[中文文档](/solution/1200-1299/1274.Number%20of%20Ships%20in%20a%20Rectangle/README.md)

## Description

<p><em>(This problem is an <strong>interactive problem</strong>.)</em></p>

<p>Each ship is located at an integer point on the sea represented by a cartesian plane, and each integer point may contain at most 1 ship.</p>

<p>You have a function <code>Sea.hasShips(topRight, bottomLeft)</code> which takes two points as arguments and returns <code>true</code> If there is at least one ship in the rectangle represented by the two points, including on the boundary.</p>

<p>Given two points: the top right and bottom left corners of a rectangle, return the number of ships present in that rectangle. It is guaranteed that there are <strong>at most 10 ships</strong> in that rectangle.</p>

<p>Submissions making <strong>more than 400 calls</strong> to <code>hasShips</code> will be judged <em>Wrong Answer</em>. Also, any solutions that attempt to circumvent the judge will result in disqualification.</p>

<p>&nbsp;</p>
<p><strong class="example">Example :</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1200-1299/1274.Number%20of%20Ships%20in%20a%20Rectangle/images/1445_example_1.png" style="width: 496px; height: 500px;" />
<pre>
<strong>Input:</strong> 
ships = [[1,1],[2,2],[3,3],[5,5]], topRight = [4,4], bottomLeft = [0,0]
<strong>Output:</strong> 3
<strong>Explanation:</strong> From [0,0] to [4,4] we can count 3 ships within the range.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> ans = [[1,1],[2,2],[3,3]], topRight = [1000,1000], bottomLeft = [0,0]
<strong>Output:</strong> 3
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li>On the input <code>ships</code> is only given to initialize the map internally. You must solve this problem &quot;blindfolded&quot;. In other words, you must find the answer using the given <code>hasShips</code> API, without knowing the <code>ships</code> position.</li>
	<li><code>0 &lt;= bottomLeft[0] &lt;= topRight[0] &lt;= 1000</code></li>
	<li><code>0 &lt;= bottomLeft[1] &lt;= topRight[1] &lt;= 1000</code></li>
	<li><code>topRight != bottomLeft</code></li>
</ul>

## Solutions

### Solution 1: Recursion + Divide and Conquer

Since there are at most $10$ ships in the rectangle, we can divide the rectangle into four sub-rectangles, calculate the number of ships in each sub-rectangle, and then add the number of ships in the four sub-rectangles. If there are no ships in a sub-rectangle, then there is no need to continue dividing.

The time complexity is $O(C \times \log \max(m, n))$, and the space complexity is $O(\log \max(m, n))$. Where $C$ is the number of ships, and $m$ and $n$ are the length and width of the rectangle, respectively.

<!-- tabs:start -->

```python
# """
# This is Sea's API interface.
# You should not implement it, or speculate about its implementation
# """
# class Sea:
#    def hasShips(self, topRight: 'Point', bottomLeft: 'Point') -> bool:
#
# class Point:
# 	def __init__(self, x: int, y: int):
# 		self.x = x
# 		self.y = y


class Solution:
    def countShips(self, sea: "Sea", topRight: "Point", bottomLeft: "Point") -> int:
        def dfs(topRight, bottomLeft):
            x1, y1 = bottomLeft.x, bottomLeft.y
            x2, y2 = topRight.x, topRight.y
            if x1 > x2 or y1 > y2:
                return 0
            if not sea.hasShips(topRight, bottomLeft):
                return 0
            if x1 == x2 and y1 == y2:
                return 1
            midx = (x1 + x2) >> 1
            midy = (y1 + y2) >> 1
            a = dfs(topRight, Point(midx + 1, midy + 1))
            b = dfs(Point(midx, y2), Point(x1, midy + 1))
            c = dfs(Point(midx, midy), bottomLeft)
            d = dfs(Point(x2, midy), Point(midx + 1, y1))
            return a + b + c + d

        return dfs(topRight, bottomLeft)
```

```java
/**
 * // This is Sea's API interface.
 * // You should not implement it, or speculate about its implementation
 * class Sea {
 *     public boolean hasShips(int[] topRight, int[] bottomLeft);
 * }
 */

class Solution {
    public int countShips(Sea sea, int[] topRight, int[] bottomLeft) {
        int x1 = bottomLeft[0], y1 = bottomLeft[1];
        int x2 = topRight[0], y2 = topRight[1];
        if (x1 > x2 || y1 > y2) {
            return 0;
        }
        if (!sea.hasShips(topRight, bottomLeft)) {
            return 0;
        }
        if (x1 == x2 && y1 == y2) {
            return 1;
        }
        int midx = (x1 + x2) >> 1;
        int midy = (y1 + y2) >> 1;
        int a = countShips(sea, topRight, new int[] {midx + 1, midy + 1});
        int b = countShips(sea, new int[] {midx, y2}, new int[] {x1, midy + 1});
        int c = countShips(sea, new int[] {midx, midy}, bottomLeft);
        int d = countShips(sea, new int[] {x2, midy}, new int[] {midx + 1, y1});
        return a + b + c + d;
    }
}
```

```cpp
/**
 * // This is Sea's API interface.
 * // You should not implement it, or speculate about its implementation
 * class Sea {
 *   public:
 *     bool hasShips(vector<int> topRight, vector<int> bottomLeft);
 * };
 */

class Solution {
public:
    int countShips(Sea sea, vector<int> topRight, vector<int> bottomLeft) {
        int x1 = bottomLeft[0], y1 = bottomLeft[1];
        int x2 = topRight[0], y2 = topRight[1];
        if (x1 > x2 || y1 > y2) {
            return 0;
        }
        if (!sea.hasShips(topRight, bottomLeft)) {
            return 0;
        }
        if (x1 == x2 && y1 == y2) {
            return 1;
        }
        int midx = (x1 + x2) >> 1;
        int midy = (y1 + y2) >> 1;
        int a = countShips(sea, topRight, {midx + 1, midy + 1});
        int b = countShips(sea, {midx, y2}, {x1, midy + 1});
        int c = countShips(sea, {midx, midy}, bottomLeft);
        int d = countShips(sea, {x2, midy}, {midx + 1, y1});
        return a + b + c + d;
    }
};
```

```go
/**
 * // This is Sea's API interface.
 * // You should not implement it, or speculate about its implementation
 * type Sea struct {
 *     func hasShips(topRight, bottomLeft []int) bool {}
 * }
 */

func countShips(sea Sea, topRight, bottomLeft []int) int {
	x1, y1 := bottomLeft[0], bottomLeft[1]
	x2, y2 := topRight[0], topRight[1]
	if x1 > x2 || y1 > y2 {
		return 0
	}
	if !sea.hasShips(topRight, bottomLeft) {
		return 0
	}
	if x1 == x2 && y1 == y2 {
		return 1
	}
	midx := (x1 + x2) >> 1
	midy := (y1 + y2) >> 1
	a := countShips(sea, topRight, []int{midx + 1, midy + 1})
	b := countShips(sea, []int{midx, y2}, []int{x1, midy + 1})
	c := countShips(sea, []int{midx, midy}, bottomLeft)
	d := countShips(sea, []int{x2, midy}, []int{midx + 1, y1})
	return a + b + c + d
}
```

```ts
/**
 * // This is the Sea's API interface.
 * // You should not implement it, or speculate about its implementation
 * class Sea {
 *      hasShips(topRight: number[], bottomLeft: number[]): boolean {}
 * }
 */

function countShips(sea: Sea, topRight: number[], bottomLeft: number[]): number {
    const [x1, y1] = bottomLeft;
    const [x2, y2] = topRight;
    if (x1 > x2 || y1 > y2 || !sea.hasShips(topRight, bottomLeft)) {
        return 0;
    }
    if (x1 === x2 && y1 === y2) {
        return 1;
    }
    const midx = (x1 + x2) >> 1;
    const midy = (y1 + y2) >> 1;
    const a = countShips(sea, topRight, [midx + 1, midy + 1]);
    const b = countShips(sea, [midx, y2], [x1, midy + 1]);
    const c = countShips(sea, [midx, midy], bottomLeft);
    const d = countShips(sea, [x2, midy], [midx + 1, y1]);
    return a + b + c + d;
}
```

<!-- tabs:end -->

<!-- end -->
