# [554. Brick Wall](https://leetcode.com/problems/brick-wall)

[中文文档](/solution/0500-0599/0554.Brick%20Wall/README.md)

## Description

<p>There is a brick wall in front of you. The wall is rectangular and has several rows of bricks. The bricks have the same height but different width. You want to draw a vertical line from the <b>top</b> to the <b>bottom</b> and cross the <b>least</b> bricks.</p>

<p>The brick wall is represented by a list of rows. Each row is a list of integers representing the width of each brick in this row from left to right.</p>

<p>If your line go through the edge of a brick, then the brick is not considered as crossed. You need to find out how to draw the line to cross the least bricks and return the number of crossed bricks.</p>

<p><b>You cannot draw a line just along one of the two vertical edges of the wall, in which case the line will obviously cross no bricks. </b></p>

<p>&nbsp;</p>

<p><b>Example:</b></p>

<pre>

<b>Input:</b> [[1,2,2,1],

        [3,1,2],

        [1,3,2],

        [2,4],

        [3,1,2],

        [1,3,1,1]]



<b>Output:</b> 2



<b>Explanation:</b> 

<img src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/0500-0599/0554.Brick%20Wall/images/brick_wall.png" style="width: 100%; max-width: 350px" />

</pre>

<p>&nbsp;</p>

<p><b>Note:</b></p>

<ol>
	<li>The width sum of bricks in different rows are the same and won&#39;t exceed INT_MAX.</li>
	<li>The number of bricks in each row is in range [1,10,000]. The height of wall is in range [1,10,000]. Total number of bricks of the wall won&#39;t exceed 20,000.</li>
</ol>

## Solutions

The question can be understood as, let the vertical line pass through the edge of the brick as much as possible, use the hash table to process the frequency of the brick edge in different positions (not including the two vertical edges), and the final answer is the total number of rows minus the maximum Frequency.

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def leastBricks(self, wall: List[List[int]]) -> int:
        cnt = collections.defaultdict(int)
        for row in wall:
            width = 0
            for brick in row[:-1]:
                width += brick
                cnt[width] += 1
        if not cnt:
            return len(wall)
        return len(wall) - cnt[max(cnt, key=cnt.get)]
```

### **Java**

```java
class Solution {
    public int leastBricks(List<List<Integer>> wall) {
        Map<Integer, Integer> cnt = new HashMap<>();
        for (List<Integer> row : wall) {
            int width = 0;
            for (int i = 0, n = row.size() - 1; i < n; i++) {
                width += row.get(i);
                cnt.merge(width, 1, Integer::sum);
            }
        }
        int max = cnt.values().stream().max(Comparator.naturalOrder()).orElse(0);
        return wall.size() - max;
    }
}
```

### **Go**

```go
func leastBricks(wall [][]int) int {
	cnt := make(map[int]int)
	for _, row := range wall {
        width := 0
		for _, brick := range row[:len(row)-1] {
            width += brick
			cnt[width]++
		}
	}
	max := 0
	for _, v := range cnt {
		if v > max {
			max = v
		}
	}
	return len(wall) - max
}
```

### **JavaScript**

```js
/**
 * @param {number[][]} wall
 * @return {number}
 */
var leastBricks = function (wall) {
  const cnt = new Map();
  for (const row of wall) {
    let width = 0;
    for (let i = 0, n = row.length - 1; i < n; ++i) {
      width += row[i];
      cnt.set(width, (cnt.get(width) || 0) + 1);
    }
  }
  let max = 0;
  for (const v of cnt.values()) {
    max = Math.max(max, v);
  }
  return wall.length - max;
};
```

### **...**

```

```

<!-- tabs:end -->
