---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3200-3299/3274.Check%20if%20Two%20Chessboard%20Squares%20Have%20the%20Same%20Color/README.md
---

<!-- problem:start -->

# [3274. 检查棋盘方格颜色是否相同](https://leetcode.cn/problems/check-if-two-chessboard-squares-have-the-same-color)

[English Version](/solution/3200-3299/3274.Check%20if%20Two%20Chessboard%20Squares%20Have%20the%20Same%20Color/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你两个字符串 <code>coordinate1</code> 和 <code>coordinate2</code>，代表 <code>8 x 8</code> 国际象棋棋盘上的两个方格的坐标。</p>

<p>以下是棋盘的参考图。</p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3200-3299/3274.Check%20if%20Two%20Chessboard%20Squares%20Have%20the%20Same%20Color/images/screenshot-2021-02-20-at-22159-pm.png" style="width: 400px; height: 396px;" /></p>

<p>如果这两个方格颜色相同，返回 <code>true</code>，否则返回 <code>false</code>。</p>

<p>坐标总是表示有效的棋盘方格。坐标的格式总是先字母（表示列），再数字（表示行）。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">coordinate1 = "a1", coordinate2 = "c3"</span></p>

<p><strong>输出：</strong> <span class="example-io">true</span></p>

<p><strong>解释：</strong></p>

<p>两个方格均为黑色。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">coordinate1 = "a1", coordinate2 = "h3"</span></p>

<p><strong>输出：</strong> <span class="example-io">false</span></p>

<p><strong>解释：</strong></p>

<p>方格 <code>"a1"</code> 是黑色，而 <code>"h3"</code> 是白色。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>coordinate1.length == coordinate2.length == 2</code></li>
	<li><code>'a' &lt;= coordinate1[0], coordinate2[0] &lt;= 'h'</code></li>
	<li><code>'1' &lt;= coordinate1[1], coordinate2[1] &lt;= '8'</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：数学

我们计算两个坐标的横纵坐标的差值，如果两个坐标的横纵坐标的差值之和为偶数，那么这两个坐标的方格颜色相同，否则不同。

时间复杂度 $O(1)$，空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def checkTwoChessboards(self, coordinate1: str, coordinate2: str) -> bool:
        x = ord(coordinate1[0]) - ord(coordinate2[0])
        y = int(coordinate1[1]) - int(coordinate2[1])
        return (x + y) % 2 == 0
```

#### Java

```java
class Solution {
    public boolean checkTwoChessboards(String coordinate1, String coordinate2) {
        int x = coordinate1.charAt(0) - coordinate2.charAt(0);
        int y = coordinate1.charAt(1) - coordinate2.charAt(1);
        return (x + y) % 2 == 0;
    }
}
```

#### C++

```cpp
class Solution {
public:
    bool checkTwoChessboards(string coordinate1, string coordinate2) {
        int x = coordinate1[0] - coordinate2[0];
        int y = coordinate1[1] - coordinate2[1];
        return (x + y) % 2 == 0;
    }
};
```

#### Go

```go
func checkTwoChessboards(coordinate1 string, coordinate2 string) bool {
	x := coordinate1[0] - coordinate2[0]
	y := coordinate1[1] - coordinate2[1]
	return (x+y)%2 == 0
}
```

#### TypeScript

```ts
function checkTwoChessboards(coordinate1: string, coordinate2: string): boolean {
    const x = coordinate1.charCodeAt(0) - coordinate2.charCodeAt(0);
    const y = coordinate1.charCodeAt(1) - coordinate2.charCodeAt(1);
    return (x + y) % 2 === 0;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
