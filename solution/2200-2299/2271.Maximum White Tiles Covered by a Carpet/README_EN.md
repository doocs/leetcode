# [2271. Maximum White Tiles Covered by a Carpet](https://leetcode.com/problems/maximum-white-tiles-covered-by-a-carpet)

[中文文档](/solution/2200-2299/2271.Maximum%20White%20Tiles%20Covered%20by%20a%20Carpet/README.md)

## Description

<p>You are given a 2D integer array <code>tiles</code> where <code>tiles[i] = [l<sub>i</sub>, r<sub>i</sub>]</code> represents that every tile <code>j</code> in the range <code>l<sub>i</sub> &lt;= j &lt;= r<sub>i</sub></code> is colored white.</p>

<p>You are also given an integer <code>carpetLen</code>, the length of a single carpet that can be placed <strong>anywhere</strong>.</p>

<p>Return <em>the <strong>maximum</strong> number of white tiles that can be covered by the carpet</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2200-2299/2271.Maximum%20White%20Tiles%20Covered%20by%20a%20Carpet/images/example1drawio3.png" style="width: 644px; height: 158px;" />
<pre>
<strong>Input:</strong> tiles = [[1,5],[10,11],[12,18],[20,25],[30,32]], carpetLen = 10
<strong>Output:</strong> 9
<strong>Explanation:</strong> Place the carpet starting on tile 10. 
It covers 9 white tiles, so we return 9.
Note that there may be other places where the carpet covers 9 white tiles.
It can be shown that the carpet cannot cover more than 9 white tiles.
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2200-2299/2271.Maximum%20White%20Tiles%20Covered%20by%20a%20Carpet/images/example2drawio.png" style="width: 231px; height: 168px;" />
<pre>
<strong>Input:</strong> tiles = [[10,11],[1,1]], carpetLen = 2
<strong>Output:</strong> 2
<strong>Explanation:</strong> Place the carpet starting on tile 10. 
It covers 2 white tiles, so we return 2.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= tiles.length &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>tiles[i].length == 2</code></li>
	<li><code>1 &lt;= l<sub>i</sub> &lt;= r<sub>i</sub> &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= carpetLen &lt;= 10<sup>9</sup></code></li>
	<li>The <code>tiles</code> are <strong>non-overlapping</strong>.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def maximumWhiteTiles(self, tiles: List[List[int]], carpetLen: int) -> int:
        tiles.sort()
        n = len(tiles)
        s = ans = j = 0
        for i, (li, ri) in enumerate(tiles):
            while j < n and tiles[j][1] - li + 1 <= carpetLen:
                s += tiles[j][1] - tiles[j][0] + 1
                j += 1
            if j < n and li + carpetLen > tiles[j][0]:
                ans = max(ans, s + li + carpetLen - tiles[j][0])
            else:
                ans = max(ans, s)
            s -= (ri - li + 1)
        return ans
```

### **Java**

```java
class Solution {
    public int maximumWhiteTiles(int[][] tiles, int carpetLen) {
        Arrays.sort(tiles, (a, b) -> a[0] - b[0]);
        int n = tiles.length;
        int s = 0, ans = 0;
        for (int i = 0, j = 0; i < n; ++i) {
            while (j < n && tiles[j][1] - tiles[i][0] + 1 <= carpetLen) {
                s += tiles[j][1] - tiles[j][0] + 1;
                ++j;
            }
            if (j < n && tiles[i][0] + carpetLen > tiles[j][0]) {
                ans = Math.max(ans, s + tiles[i][0] + carpetLen - tiles[j][0]);
            } else {
                ans = Math.max(ans, s);
            }
            s -= (tiles[i][1] - tiles[i][0] + 1);
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int maximumWhiteTiles(vector<vector<int>>& tiles, int carpetLen) {
        sort(tiles.begin(), tiles.end());
        int s = 0, ans = 0, n = tiles.size();
        for (int i = 0, j = 0; i < n; ++i) {
            while (j < n && tiles[j][1] - tiles[i][0] + 1 <= carpetLen) {
                s += tiles[j][1] - tiles[j][0] + 1;
                ++j;
            }
            if (j < n && tiles[i][0] + carpetLen > tiles[j][0]) {
                ans = max(ans, s + tiles[i][0] + carpetLen - tiles[j][0]);
            } else {
                ans = max(ans, s);
            }
            s -= (tiles[i][1] - tiles[i][0] + 1);
        }
        return ans;
    }
};
```

### **Go**

```go
func maximumWhiteTiles(tiles [][]int, carpetLen int) int {
	sort.Slice(tiles, func(i, j int) bool { return tiles[i][0] < tiles[j][0] })
	n := len(tiles)
	s, ans := 0, 0
	for i, j := 0, 0; i < n; i++ {
		for j < n && tiles[j][1]-tiles[i][0]+1 <= carpetLen {
			s += tiles[j][1] - tiles[j][0] + 1
			j++
		}
		if j < n && tiles[i][0]+carpetLen > tiles[j][0] {
			ans = max(ans, s+tiles[i][0]+carpetLen-tiles[j][0])
		} else {
			ans = max(ans, s)
		}
		s -= (tiles[i][1] - tiles[i][0] + 1)
	}
	return ans
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

```

### **...**

```

```

<!-- tabs:end -->
