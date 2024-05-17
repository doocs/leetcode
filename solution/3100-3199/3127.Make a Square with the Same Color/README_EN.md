---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3100-3199/3127.Make%20a%20Square%20with%20the%20Same%20Color/README_EN.md
rating: 1337
source: Biweekly Contest 129 Q1
tags:
    - Array
    - Enumeration
    - Matrix
---

<!-- problem:start -->

# [3127. Make a Square with the Same Color](https://leetcode.com/problems/make-a-square-with-the-same-color)

[中文文档](/solution/3100-3199/3127.Make%20a%20Square%20with%20the%20Same%20Color/README.md)

## Description

<!-- description:start -->

<p>You are given a 2D matrix <code>grid</code> of size <code>3 x 3</code> consisting only of characters <code>&#39;B&#39;</code> and <code>&#39;W&#39;</code>. Character <code>&#39;W&#39;</code> represents the white color<!-- notionvc: 06a49cc0-a296-4bd2-9bfe-c8818edeb53a -->, and character <code>&#39;B&#39;</code> represents the black color<!-- notionvc: 06a49cc0-a296-4bd2-9bfe-c8818edeb53a -->.</p>

<p>Your task is to change the color of <strong>at most one</strong> cell<!-- notionvc: c04cb478-8dd5-49b1-80bb-727c6b1e0232 --> so that the matrix has a <code>2 x 2</code> square where all cells are of the same color.<!-- notionvc: adf957e1-fa0f-40e5-9a2e-933b95e276a7 --></p>

<p>Return <code>true</code> if it is possible to create a <code>2 x 2</code> square of the same color, otherwise, return <code>false</code>.</p>

<p>&nbsp;</p>
<style type="text/css">.grid-container {
  display: grid;
  grid-template-columns: 30px 30px 30px;
  padding: 10px;
}
.grid-item {
  background-color: black;
  border: 1px solid gray;
  height: 30px;
  font-size: 30px;
  text-align: center;
}
.grid-item-white {
  background-color: white;
}
</style>
<style class="darkreader darkreader--sync" media="screen" type="text/css">
</style>
<p><strong class="example">Example 1:</strong></p>

<div class="grid-container">
<div class="grid-item">&nbsp;</div>

<div class="grid-item grid-item-white">&nbsp;</div>

<div class="grid-item">&nbsp;</div>

<div class="grid-item">&nbsp;</div>

<div class="grid-item grid-item-white">&nbsp;</div>

<div class="grid-item grid-item-white">&nbsp;</div>

<div class="grid-item">&nbsp;</div>

<div class="grid-item grid-item-white">&nbsp;</div>

<div class="grid-item">&nbsp;</div>
</div>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">grid = [[&quot;B&quot;,&quot;W&quot;,&quot;B&quot;],[&quot;B&quot;,&quot;W&quot;,&quot;W&quot;],[&quot;B&quot;,&quot;W&quot;,&quot;B&quot;]]</span></p>

<p><strong>Output:</strong> <span class="example-io">true</span></p>

<p><strong>Explanation:</strong></p>

<p>It can be done by changing the color of the <code>grid[0][2]</code>.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="grid-container">
<div class="grid-item">&nbsp;</div>

<div class="grid-item grid-item-white">&nbsp;</div>

<div class="grid-item">&nbsp;</div>

<div class="grid-item grid-item-white">&nbsp;</div>

<div class="grid-item">&nbsp;</div>

<div class="grid-item grid-item-white">&nbsp;</div>

<div class="grid-item">&nbsp;</div>

<div class="grid-item grid-item-white">&nbsp;</div>

<div class="grid-item">&nbsp;</div>
</div>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">grid = [[&quot;B&quot;,&quot;W&quot;,&quot;B&quot;],[&quot;W&quot;,&quot;B&quot;,&quot;W&quot;],[&quot;B&quot;,&quot;W&quot;,&quot;B&quot;]]</span></p>

<p><strong>Output:</strong> <span class="example-io">false</span></p>

<p><strong>Explanation:</strong></p>

<p>It cannot be done by changing at most one cell.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="grid-container">
<div class="grid-item">&nbsp;</div>

<div class="grid-item grid-item-white">&nbsp;</div>

<div class="grid-item">&nbsp;</div>

<div class="grid-item">&nbsp;</div>

<div class="grid-item grid-item-white">&nbsp;</div>

<div class="grid-item grid-item-white">&nbsp;</div>

<div class="grid-item">&nbsp;</div>

<div class="grid-item grid-item-white">&nbsp;</div>

<div class="grid-item grid-item-white">&nbsp;</div>
</div>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">grid = [[&quot;B&quot;,&quot;W&quot;,&quot;B&quot;],[&quot;B&quot;,&quot;W&quot;,&quot;W&quot;],[&quot;B&quot;,&quot;W&quot;,&quot;W&quot;]]</span></p>

<p><strong>Output:</strong> <span class="example-io">true</span></p>

<p><strong>Explanation:</strong></p>

<p>The <code>grid</code> already contains a <code>2 x 2</code> square of the same color.<!-- notionvc: 9a8b2d3d-1e73-457a-abe0-c16af51ad5c2 --></p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>grid.length == 3</code></li>
	<li><code>grid[i].length == 3</code></li>
	<li><code>grid[i][j]</code> is either <code>&#39;W&#39;</code> or <code>&#39;B&#39;</code>.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Enumeration

We can enumerate each $2 \times 2$ square, count the number of black and white cells. If the counts are not equal, then we can construct a square of the same color, and return `true`.

Otherwise, return `false` after the traversal.

The time complexity is $O(1)$, and the space complexity is $O(1)$.

<!-- tabs:start -->

```python
class Solution:
    def canMakeSquare(self, grid: List[List[str]]) -> bool:
        for i in range(0, 2):
            for j in range(0, 2):
                cnt1 = cnt2 = 0
                for a, b in pairwise((0, 0, 1, 1, 0)):
                    x, y = i + a, j + b
                    cnt1 += grid[x][y] == "W"
                    cnt2 += grid[x][y] == "B"
                if cnt1 != cnt2:
                    return True
        return False
```

```java
class Solution {
    public boolean canMakeSquare(char[][] grid) {
        final int[] dirs = {0, 0, 1, 1, 0};
        for (int i = 0; i < 2; ++i) {
            for (int j = 0; j < 2; ++j) {
                int cnt1 = 0, cnt2 = 0;
                for (int k = 0; k < 4; ++k) {
                    int x = i + dirs[k], y = j + dirs[k + 1];
                    cnt1 += grid[x][y] == 'W' ? 1 : 0;
                    cnt2 += grid[x][y] == 'B' ? 1 : 0;
                }
                if (cnt1 != cnt2) {
                    return true;
                }
            }
        }
        return false;
    }
}
```

```cpp
class Solution {
public:
    bool canMakeSquare(vector<vector<char>>& grid) {
        int dirs[5] = {0, 0, 1, 1, 0};
        for (int i = 0; i < 2; ++i) {
            for (int j = 0; j < 2; ++j) {
                int cnt1 = 0, cnt2 = 0;
                for (int k = 0; k < 4; ++k) {
                    int x = i + dirs[k], y = j + dirs[k + 1];
                    cnt1 += grid[x][y] == 'W';
                    cnt2 += grid[x][y] == 'B';
                }
                if (cnt1 != cnt2) {
                    return true;
                }
            }
        }
        return false;
    }
};
```

```go
func canMakeSquare(grid [][]byte) bool {
	dirs := [5]int{0, 0, 1, 1, 0}
	for i := 0; i < 2; i++ {
		for j := 0; j < 2; j++ {
			cnt1, cnt2 := 0, 0
			for k := 0; k < 4; k++ {
				x, y := i+dirs[k], j+dirs[k+1]
				if grid[x][y] == 'W' {
					cnt1++
				} else {
					cnt2++
				}
			}
			if cnt1 != cnt2 {
				return true
			}
		}
	}
	return false
}
```

```ts
function canMakeSquare(grid: string[][]): boolean {
    const dirs: number[] = [0, 0, 1, 1, 0];
    for (let i = 0; i < 2; ++i) {
        for (let j = 0; j < 2; ++j) {
            let [cnt1, cnt2] = [0, 0];
            for (let k = 0; k < 4; ++k) {
                const [x, y] = [i + dirs[k], j + dirs[k + 1]];
                if (grid[x][y] === 'W') {
                    ++cnt1;
                } else {
                    ++cnt2;
                }
            }
            if (cnt1 !== cnt2) {
                return true;
            }
        }
    }
    return false;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
