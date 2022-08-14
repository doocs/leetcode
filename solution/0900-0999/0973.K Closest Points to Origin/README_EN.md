# [973. K Closest Points to Origin](https://leetcode.com/problems/k-closest-points-to-origin)

[中文文档](/solution/0900-0999/0973.K%20Closest%20Points%20to%20Origin/README.md)

## Description

<p>Given an array of <code>points</code> where <code>points[i] = [x<sub>i</sub>, y<sub>i</sub>]</code> represents a point on the <strong>X-Y</strong> plane and an integer <code>k</code>, return the <code>k</code> closest points to the origin <code>(0, 0)</code>.</p>

<p>The distance between two points on the <strong>X-Y</strong> plane is the Euclidean distance (i.e., <code>&radic;(x<sub>1</sub> - x<sub>2</sub>)<sup>2</sup> + (y<sub>1</sub> - y<sub>2</sub>)<sup>2</sup></code>).</p>

<p>You may return the answer in <strong>any order</strong>. The answer is <strong>guaranteed</strong> to be <strong>unique</strong> (except for the order that it is in).</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0900-0999/0973.K%20Closest%20Points%20to%20Origin/images/closestplane1.jpg" style="width: 400px; height: 400px;" />
<pre>
<strong>Input:</strong> points = [[1,3],[-2,2]], k = 1
<strong>Output:</strong> [[-2,2]]
<strong>Explanation:</strong>
The distance between (1, 3) and the origin is sqrt(10).
The distance between (-2, 2) and the origin is sqrt(8).
Since sqrt(8) &lt; sqrt(10), (-2, 2) is closer to the origin.
We only want the closest k = 1 points from the origin, so the answer is just [[-2,2]].
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> points = [[3,3],[5,-1],[-2,4]], k = 2
<strong>Output:</strong> [[3,3],[-2,4]]
<strong>Explanation:</strong> The answer [[-2,4],[3,3]] would also be accepted.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= k &lt;= points.length &lt;= 10<sup>4</sup></code></li>
	<li><code>-10<sup>4</sup> &lt; x<sub>i</sub>, y<sub>i</sub> &lt; 10<sup>4</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python

```

### **Java**

```java
import java.util.*;


/**
 * @author Furaha Damien
 */


class Solution {


    // Helper inner class
    public class Point {
        int x;
        int y;
        int distance;


        public Point(int x, int y, int distance) {
            this.x = x;
            this.y = y;
            this.distance = distance;
        }
    }


    public int[][] kClosest(int[][] points, int K) {


        PriorityQueue<Point> que = new PriorityQueue<Point>((a, b) -> (a.distance - b.distance));
        int[][] res = new int[K][2];


        for (int[] temp : points) {
            int dist = (temp[0] * temp[0] + temp[1] * temp[1]);
            que.offer(new Point(temp[0], temp[1], dist));
        }
        for (int i = 0; i < K; i++) {
            Point curr = que.poll();
            res[i][0] = curr.x;
            res[i][1] = curr.y;
        }
        return res;


    }
}
```

### **TypeScript**

```ts
function kClosest(points: number[][], k: number): number[][] {
    return points
        .sort((a, b) => a[0] ** 2 + a[1] ** 2 - (b[0] ** 2 + b[1] ** 2))
        .slice(0, k);
}
```

### **Rust**

```rust
impl Solution {
    pub fn k_closest(mut points: Vec<Vec<i32>>, k: i32) -> Vec<Vec<i32>> {
        points.sort_unstable_by(|a, b| {
            (a[0].pow(2) + a[1].pow(2)).cmp(&(b[0].pow(2) + b[1].pow(2)))
        });
        points[0..k as usize].to_vec()
    }
}
```

### **...**

```

```

<!-- tabs:end -->
