# [2271. 毯子覆盖的最多白色砖块数](https://leetcode.cn/problems/maximum-white-tiles-covered-by-a-carpet)

[English Version](/solution/2200-2299/2271.Maximum%20White%20Tiles%20Covered%20by%20a%20Carpet/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个二维整数数组&nbsp;<code>tiles</code>&nbsp;，其中&nbsp;<code>tiles[i] = [l<sub>i</sub>, r<sub>i</sub>]</code>&nbsp;，表示所有在&nbsp;<code>l<sub>i</sub> &lt;= j &lt;= r<sub>i</sub></code>&nbsp;之间的每个瓷砖位置 <code>j</code>&nbsp;都被涂成了白色。</p>

<p>同时给你一个整数&nbsp;<code>carpetLen</code>&nbsp;，表示可以放在&nbsp;<strong>任何位置</strong>&nbsp;的一块毯子。</p>

<p>请你返回使用这块毯子，<strong>最多</strong>&nbsp;可以盖住多少块瓷砖。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2200-2299/2271.Maximum%20White%20Tiles%20Covered%20by%20a%20Carpet/images/example1drawio3.png" style="width: 644px; height: 158px;" /></p>

<pre>
<b>输入：</b>tiles = [[1,5],[10,11],[12,18],[20,25],[30,32]], carpetLen = 10
<b>输出：</b>9
<b>解释：</b>将毯子从瓷砖 10 开始放置。
总共覆盖 9 块瓷砖，所以返回 9 。
注意可能有其他方案也可以覆盖 9 块瓷砖。
可以看出，瓷砖无法覆盖超过 9 块瓷砖。
</pre>

<p><strong>示例 2：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2200-2299/2271.Maximum%20White%20Tiles%20Covered%20by%20a%20Carpet/images/example2drawio.png" style="width: 231px; height: 168px;" /></p>

<pre>
<strong>输入：</strong>tiles = [[10,11],[1,1]], carpetLen = 2
<b>输出：</b>2
<b>解释：</b>将毯子从瓷砖 10 开始放置。
总共覆盖 2 块瓷砖，所以我们返回 2 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= tiles.length &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>tiles[i].length == 2</code></li>
	<li><code>1 &lt;= l<sub>i</sub> &lt;= r<sub>i</sub> &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= carpetLen &lt;= 10<sup>9</sup></code></li>
	<li><code>tiles</code>&nbsp;互相 <strong>不会重叠</strong>&nbsp;。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：贪心 + 排序 + 滑动窗口**

直觉上，毯子的左端点一定与某块瓷砖的左端点重合，这样才能使得毯子覆盖的瓷砖最多。

我们可以来简单证明一下。

如果毯子落在某块瓷砖的中间某个位置，将毯子右移一个，毯子覆盖的瓷砖数量可能减少，也可能不变，但不可能增加；将毯子左移一个，毯子覆盖的瓷砖数量可能不变，也可能增加，但不可能减少。

也就是说，将毯子左移至某块瓷砖的左端点，一定可以使得毯子覆盖的瓷砖数量最多。

因此，我们可以将所有瓷砖按照左端点从小到大排序，然后枚举每块瓷砖的左端点，计算出以该左端点为起点的毯子覆盖的瓷砖数量，取最大值即可。

为了计算以某块瓷砖的左端点为起点的毯子覆盖的瓷砖数量，我们可以使用滑动窗口的思想，维护一个右端点不断右移的窗口，窗口内的瓷砖数量即为毯子覆盖的瓷砖数量。

时间复杂度 $O(n\log n)$，其中 $n$ 为瓷砖的数量。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
