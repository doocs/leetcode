# [755. Pour Water](https://leetcode.com/problems/pour-water)

[中文文档](/solution/0700-0799/0755.Pour%20Water/README.md)

## Description

<p>You are given an elevation map represents as an integer array <code>heights</code> where <code>heights[i]</code> representing the height of the terrain at index <code>i</code>. The width at each index is <code>1</code>. You are also given two integers <code>volume</code> and <code>k</code>. <code>volume</code> units of water will fall at index <code>k</code>.</p>

<p>Water first drops at the index <code>k</code> and rests on top of the highest terrain or water at that index. Then, it flows according to the following rules:</p>

<ul>
	<li>If the droplet would eventually fall by moving left, then move left.</li>
	<li>Otherwise, if the droplet would eventually fall by moving right, then move right.</li>
	<li>Otherwise, rise to its current position.</li>
</ul>

<p>Here, <strong>&quot;eventually fall&quot;</strong> means that the droplet will eventually be at a lower level if it moves in that direction. Also, level means the height of the terrain plus any water in that column.</p>

<p>We can assume there is infinitely high terrain on the two sides out of bounds of the array. Also, there could not be partial water being spread out evenly on more than one grid block, and each unit of water has to be in exactly one block.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0700-0799/0755.Pour%20Water/images/pour11-grid.jpg" style="width: 450px; height: 303px;" />
<pre>
<strong>Input:</strong> heights = [2,1,1,2,1,2,2], volume = 4, k = 3
<strong>Output:</strong> [2,2,2,3,2,2,2]
<strong>Explanation:</strong>
The first drop of water lands at index k = 3. When moving left or right, the water can only move to the same level or a lower level. (By level, we mean the total height of the terrain plus any water in that column.)
Since moving left will eventually make it fall, it moves left. (A droplet &quot;made to fall&quot; means go to a lower height than it was at previously.) Since moving left will not make it fall, it stays in place.
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0700-0799/0755.Pour%20Water/images/pour12-grid.jpg" style="width: 400px; height: 269px;" />
The next droplet falls at index k = 3. Since the new droplet moving left will eventually make it fall, it moves left. Notice that the droplet still preferred to move left, even though it could move right (and moving right makes it fall quicker.)
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0700-0799/0755.Pour%20Water/images/pour13-grid.jpg" style="width: 400px; height: 269px;" />
The third droplet falls at index k = 3. Since moving left would not eventually make it fall, it tries to move right. Since moving right would eventually make it fall, it moves right.
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0700-0799/0755.Pour%20Water/images/pour14-grid.jpg" style="width: 400px; height: 269px;" />
Finally, the fourth droplet falls at index k = 3. Since moving left would not eventually make it fall, it tries to move right. Since moving right would not eventually make it fall, it stays in place.
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0700-0799/0755.Pour%20Water/images/pour15-grid.jpg" style="width: 400px; height: 269px;" />
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> heights = [1,2,3,4], volume = 2, k = 2
<strong>Output:</strong> [2,3,3,4]
<strong>Explanation:</strong> The last droplet settles at index 1, since moving further left would not cause it to eventually fall to a lower height.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> heights = [3,1,3], volume = 5, k = 1
<strong>Output:</strong> [4,4,4]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= heights.length &lt;= 100</code></li>
	<li><code>0 &lt;= heights[i] &lt;= 99</code></li>
	<li><code>0 &lt;= volume &lt;= 2000</code></li>
	<li><code>0 &lt;= k &lt; heights.length</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def pourWater(self, heights: List[int], volume: int, k: int) -> List[int]:
        for _ in range(volume):
            for d in (-1, 1):
                i = j = k
                while 0 <= i + d < len(heights) and heights[i + d] <= heights[i]:
                    if heights[i + d] < heights[i]:
                        j = i + d
                    i += d
                if j != k:
                    heights[j] += 1
                    break
            else:
                heights[k] += 1
        return heights
```

### **Java**

```java
class Solution {
    public int[] pourWater(int[] heights, int volume, int k) {
        while (volume-- > 0) {
            boolean find = false;
            for (int d = -1; d < 2 && !find; d += 2) {
                int i = k, j = k;
                while (i + d >= 0 && i + d < heights.length && heights[i + d] <= heights[i]) {
                    if (heights[i + d] < heights[i]) {
                        j = i + d;
                    }
                    i += d;
                }
                if (j != k) {
                    find = true;
                    ++heights[j];
                }
            }
            if (!find) {
                ++heights[k];
            }
        }
        return heights;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<int> pourWater(vector<int>& heights, int volume, int k) {
        while (volume--) {
            bool find = false;
            for (int d = -1; d < 2 && !find; d += 2) {
                int i = k, j = k;
                while (i + d >= 0 && i + d < heights.size() && heights[i + d] <= heights[i]) {
                    if (heights[i + d] < heights[i]) {
                        j = i + d;
                    }
                    i += d;
                }
                if (j != k) {
                    find = true;
                    ++heights[j];
                }
            }
            if (!find) {
                ++heights[k];
            }
        }
        return heights;
    }
};
```

### **...**

```

```

<!-- tabs:end -->
