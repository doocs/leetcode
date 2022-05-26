# [1861. 旋转盒子](https://leetcode.cn/problems/rotating-the-box)

[English Version](/solution/1800-1899/1861.Rotating%20the%20Box/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个 <code>m x n</code> 的字符矩阵 <code>box</code> ，它表示一个箱子的侧视图。箱子的每一个格子可能为：</p>

<ul>
	<li><code>'#'</code> 表示石头</li>
	<li><code>'*'</code> 表示固定的障碍物</li>
	<li><code>'.'</code> 表示空位置</li>
</ul>

<p>这个箱子被 <strong>顺时针旋转 90 度</strong> ，由于重力原因，部分石头的位置会发生改变。每个石头会垂直掉落，直到它遇到障碍物，另一个石头或者箱子的底部。重力 <strong>不会</strong> 影响障碍物的位置，同时箱子旋转不会产生<strong>惯性</strong> ，也就是说石头的水平位置不会发生改变。</p>

<p>题目保证初始时 <code>box</code> 中的石头要么在一个障碍物上，要么在另一个石头上，要么在箱子的底部。</p>

<p>请你返回一个<em> </em><code>n x m</code>的矩阵，表示按照上述旋转后，箱子内的结果。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1800-1899/1861.Rotating%20the%20Box/images/rotatingtheboxleetcodewithstones.png" style="width: 300px; height: 150px;"></p>

<pre><b>输入：</b>box = [["#",".","#"]]
<b>输出：</b>[["."],
      ["#"],
      ["#"]]
</pre>

<p><strong>示例 2：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1800-1899/1861.Rotating%20the%20Box/images/rotatingtheboxleetcode2withstones.png" style="width: 375px; height: 195px;"></p>

<pre><b>输入：</b>box = [["#",".","*","."],
            ["#","#","*","."]]
<b>输出：</b>[["#","."],
      ["#","#"],
      ["*","*"],
      [".","."]]
</pre>

<p><strong>示例 3：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1800-1899/1861.Rotating%20the%20Box/images/rotatingtheboxleetcode3withstone.png" style="width: 400px; height: 218px;"></p>

<pre><b>输入：</b>box = [["#","#","*",".","*","."],
            ["#","#","#","*",".","."],
            ["#","#","#",".","#","."]]
<b>输出：</b>[[".","#","#"],
      [".","#","#"],
      ["#","#","*"],
      ["#","*","."],
      ["#",".","*"],
      ["#",".","."]]
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>m == box.length</code></li>
	<li><code>n == box[i].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 500</code></li>
	<li><code>box[i][j]</code> 只可能是 <code>'#'</code> ，<code>'*'</code> 或者 <code>'.'</code> 。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

先旋转，再挪动箱子。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def rotateTheBox(self, box: List[List[str]]) -> List[List[str]]:
        m, n = len(box), len(box[0])
        res = [[None] * m for _ in range(n)]
        for i in range(m):
            for j in range(n):
                res[j][m - i - 1] = box[i][j]
        for j in range(m):
            q = deque()
            for i in range(n - 1, -1, -1):
                if res[i][j] == '*':
                    q.clear()
                    continue
                if res[i][j] == '.':
                    q.append(i)
                else:
                    if not q:
                        continue
                    res[q.popleft()][j] = '#'
                    res[i][j] = '.'
                    q.append(i)
        return res
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public char[][] rotateTheBox(char[][] box) {
        int m = box.length, n = box[0].length;
        char[][] res = new char[n][m];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                res[j][m - i - 1] = box[i][j];
            }
        }
        for (int j = 0; j < m; ++j) {
            Deque<Integer> q = new ArrayDeque<>();
            for (int i = n - 1; i >= 0; --i) {
                if (res[i][j] == '*') {
                    q.clear();
                    continue;
                }
                if (res[i][j] == '.') {
                    q.offer(i);
                } else {
                    if (q.isEmpty()) {
                        continue;
                    }
                    res[q.poll()][j] = '#';
                    res[i][j] = '.';
                    q.offer(i);
                }
            }
        }
        return res;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
