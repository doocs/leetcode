---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0500-0599/0531.Lonely%20Pixel%20I/README_EN.md
tags:
    - Array
    - Hash Table
    - Matrix
---

# [531. Lonely Pixel I 🔒](https://leetcode.com/problems/lonely-pixel-i)

[中文文档](/solution/0500-0599/0531.Lonely%20Pixel%20I/README.md)

## Description

<p>Given an <code>m x n</code> <code>picture</code> consisting of black <code>&#39;B&#39;</code> and white <code>&#39;W&#39;</code> pixels, return <em>the number of <b>black</b> lonely pixels</em>.</p>

<p>A black lonely pixel is a character <code>&#39;B&#39;</code> that located at a specific position where the same row and same column don&#39;t have <strong>any other</strong> black pixels.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0500-0599/0531.Lonely%20Pixel%20I/images/pixel1.jpg" style="width: 242px; height: 242px;" />
<pre>
<strong>Input:</strong> picture = [[&quot;W&quot;,&quot;W&quot;,&quot;B&quot;],[&quot;W&quot;,&quot;B&quot;,&quot;W&quot;],[&quot;B&quot;,&quot;W&quot;,&quot;W&quot;]]
<strong>Output:</strong> 3
<strong>Explanation:</strong> All the three &#39;B&#39;s are black lonely pixels.
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0500-0599/0531.Lonely%20Pixel%20I/images/pixel2.jpg" style="width: 242px; height: 242px;" />
<pre>
<strong>Input:</strong> picture = [[&quot;B&quot;,&quot;B&quot;,&quot;B&quot;],[&quot;B&quot;,&quot;B&quot;,&quot;W&quot;],[&quot;B&quot;,&quot;B&quot;,&quot;B&quot;]]
<strong>Output:</strong> 0
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>m ==&nbsp;picture.length</code></li>
	<li><code>n ==&nbsp;picture[i].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 500</code></li>
	<li><code>picture[i][j]</code> is <code>&#39;W&#39;</code> or <code>&#39;B&#39;</code>.</li>
</ul>

## Solutions

### Solution 1: Counting + Enumeration

According to the problem description, we need to count the number of black pixels in each row and column, which are recorded in the arrays `rows` and `cols` respectively. Then we traverse each black pixel, check whether there is only one black pixel in its row and column. If so, we increment the answer by one.

The time complexity is $O(m \times n)$, and the space complexity is $O(m + n)$, where $m$ and $n$ are the number of rows and columns in the matrix respectively.

<!-- tabs:start -->

```python
class Solution:
    def findLonelyPixel(self, picture: List[List[str]]) -> int:
        rows = [0] * len(picture)
        cols = [0] * len(picture[0])
        for i, row in enumerate(picture):
            for j, x in enumerate(row):
                if x == "B":
                    rows[i] += 1
                    cols[j] += 1
        ans = 0
        for i, row in enumerate(picture):
            for j, x in enumerate(row):
                if x == "B" and rows[i] == 1 and cols[j] == 1:
                    ans += 1
        return ans
```

```java
class Solution {
    public int findLonelyPixel(char[][] picture) {
        int m = picture.length, n = picture[0].length;
        int[] rows = new int[m];
        int[] cols = new int[n];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (picture[i][j] == 'B') {
                    ++rows[i];
                    ++cols[j];
                }
            }
        }
        int ans = 0;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (picture[i][j] == 'B' && rows[i] == 1 && cols[j] == 1) {
                    ++ans;
                }
            }
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int findLonelyPixel(vector<vector<char>>& picture) {
        int m = picture.size(), n = picture[0].size();
        vector<int> rows(m);
        vector<int> cols(n);
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (picture[i][j] == 'B') {
                    ++rows[i];
                    ++cols[j];
                }
            }
        }
        int ans = 0;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (picture[i][j] == 'B' && rows[i] == 1 && cols[j] == 1) {
                    ++ans;
                }
            }
        }
        return ans;
    }
};
```

```go
func findLonelyPixel(picture [][]byte) (ans int) {
	rows := make([]int, len(picture))
	cols := make([]int, len(picture[0]))
	for i, row := range picture {
		for j, x := range row {
			if x == 'B' {
				rows[i]++
				cols[j]++
			}
		}
	}
	for i, row := range picture {
		for j, x := range row {
			if x == 'B' && rows[i] == 1 && cols[j] == 1 {
				ans++
			}
		}
	}
	return
}
```

```ts
function findLonelyPixel(picture: string[][]): number {
    const m = picture.length;
    const n = picture[0].length;
    const rows: number[] = Array(m).fill(0);
    const cols: number[] = Array(n).fill(0);
    for (let i = 0; i < m; ++i) {
        for (let j = 0; j < n; ++j) {
            if (picture[i][j] === 'B') {
                ++rows[i];
                ++cols[j];
            }
        }
    }
    let ans = 0;
    for (let i = 0; i < m; ++i) {
        for (let j = 0; j < n; ++j) {
            if (picture[i][j] === 'B' && rows[i] === 1 && cols[j] === 1) {
                ++ans;
            }
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- end -->
