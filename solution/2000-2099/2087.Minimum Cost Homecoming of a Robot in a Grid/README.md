# [2087. 网格图中机器人回家的最小代价](https://leetcode.cn/problems/minimum-cost-homecoming-of-a-robot-in-a-grid)

[English Version](/solution/2000-2099/2087.Minimum%20Cost%20Homecoming%20of%20a%20Robot%20in%20a%20Grid/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个&nbsp;<code>m x n</code>&nbsp;的网格图，其中&nbsp;<code>(0, 0)</code>&nbsp;是最左上角的格子，<code>(m - 1, n - 1)</code>&nbsp;是最右下角的格子。给你一个整数数组&nbsp;<code>startPos</code>&nbsp;，<code>startPos = [start<sub>row</sub>, start<sub>col</sub>]</code>&nbsp;表示 <strong>初始</strong>&nbsp;有一个 <strong>机器人</strong>&nbsp;在格子&nbsp;<code>(start<sub>row</sub>, start<sub>col</sub>)</code>&nbsp;处。同时给你一个整数数组&nbsp;<code>homePos</code>&nbsp;，<code>homePos = [home<sub>row</sub>, home<sub>col</sub>]</code>&nbsp;表示机器人的 <strong>家</strong>&nbsp;在格子&nbsp;<code>(home<sub>row</sub>, home<sub>col</sub>)</code>&nbsp;处。</p>

<p>机器人需要回家。每一步它可以往四个方向移动：<strong>上</strong>，<strong>下</strong>，<strong>左</strong>，<strong>右</strong>，同时机器人不能移出边界。每一步移动都有一定代价。再给你两个下标从&nbsp;<strong>0</strong>&nbsp;开始的额整数数组：长度为&nbsp;<code>m</code>&nbsp;的数组&nbsp;<code>rowCosts</code> &nbsp;和长度为 <code>n</code>&nbsp;的数组&nbsp;<code>colCosts</code>&nbsp;。</p>

<ul>
	<li>如果机器人往 <strong>上</strong>&nbsp;或者往 <strong>下</strong>&nbsp;移动到第 <code>r</code>&nbsp;<strong>行</strong>&nbsp;的格子，那么代价为&nbsp;<code>rowCosts[r]</code>&nbsp;。</li>
	<li>如果机器人往 <strong>左</strong>&nbsp;或者往 <strong>右</strong>&nbsp;移动到第 <code>c</code>&nbsp;<strong>列</strong> 的格子，那么代价为&nbsp;<code>colCosts[c]</code>&nbsp;。</li>
</ul>

<p>请你返回机器人回家需要的 <strong>最小总代价</strong>&nbsp;。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2000-2099/2087.Minimum%20Cost%20Homecoming%20of%20a%20Robot%20in%20a%20Grid/images/eg-1.png" style="width: 282px; height: 217px;"></p>

<pre><strong>输入：</strong>startPos = [1, 0], homePos = [2, 3], rowCosts = [5, 4, 3], colCosts = [8, 2, 6, 7]
<b>输出：</b>18
<b>解释：</b>一个最优路径为：
从 (1, 0) 开始
-&gt; 往下走到 (<em><strong>2</strong></em>, 0) 。代价为 rowCosts[2] = 3 。
-&gt; 往右走到 (2, <em><strong>1</strong></em>) 。代价为 colCosts[1] = 2 。
-&gt; 往右走到 (2, <em><strong>2</strong></em>) 。代价为 colCosts[2] = 6 。
-&gt; 往右走到 (2, <em><strong>3</strong></em>) 。代价为 colCosts[3] = 7 。
总代价为 3 + 2 + 6 + 7 = 18</pre>

<p><strong>示例 2：</strong></p>

<pre><b>输入：</b>startPos = [0, 0], homePos = [0, 0], rowCosts = [5], colCosts = [26]
<b>输出：</b>0
<b>解释：</b>机器人已经在家了，所以不需要移动。总代价为 0 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>m == rowCosts.length</code></li>
	<li><code>n == colCosts.length</code></li>
	<li><code>1 &lt;= m, n &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= rowCosts[r], colCosts[c] &lt;= 10<sup>4</sup></code></li>
	<li><code>startPos.length == 2</code></li>
	<li><code>homePos.length == 2</code></li>
	<li><code>0 &lt;= start<sub>row</sub>, home<sub>row</sub> &lt; m</code></li>
	<li><code>0 &lt;= start<sub>col</sub>, home<sub>col</sub> &lt; n</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：贪心**

设机器人当前位置为 $(i, j)$，目标位置为 $(x, y)$。

-   如果 $i \lt x$，则机器人往下移动，代价为 $rowCosts[i + 1] + rowCosts[i + 2] + \cdots + rowCosts[x]$。
-   如果 $i \gt x$，则机器人往上移动，代价为 $rowCosts[x] + rowCosts[x + 1] + \cdots + rowCosts[i - 1]$。
-   如果 $j \lt y$，则机器人往右移动，代价为 $colCosts[j + 1] + colCosts[j + 2] + \cdots + colCosts[y]$。
-   如果 $j \gt y$，则机器人往左移动，代价为 $colCosts[y] + colCosts[y + 1] + \cdots + colCosts[j - 1]$。

时间复杂度 $O(m + n)$，空间复杂度 $O(1)$。其中 $m$ 和 $n$ 分别为 $rowCosts$ 和 $colCosts$ 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def minCost(self, startPos: List[int], homePos: List[int], rowCosts: List[int], colCosts: List[int]) -> int:
        i, j = startPos
        x, y = homePos
        ans = 0
        if i < x:
            ans += sum(rowCosts[i + 1: x + 1])
        else:
            ans += sum(rowCosts[x: i])
        if j < y:
            ans += sum(colCosts[j + 1: y + 1])
        else:
            ans += sum(colCosts[y: j])
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int minCost(int[] startPos, int[] homePos, int[] rowCosts, int[] colCosts) {
        int i = startPos[0], j = startPos[1];
        int x = homePos[0], y = homePos[1];
        int ans = 0;
        if (i < x) {
            for (int k = i + 1; k <= x; ++k) {
                ans += rowCosts[k];
            }
        } else {
            for (int k = x; k < i; ++k) {
                ans += rowCosts[k];
            }
        }
        if (j < y) {
            for (int k = j + 1; k <= y; ++k) {
                ans += colCosts[k];
            }
        } else {
            for (int k = y; k < j; ++k) {
                ans += colCosts[k];
            }
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int minCost(vector<int>& startPos, vector<int>& homePos, vector<int>& rowCosts, vector<int>& colCosts) {
        int i = startPos[0], j = startPos[1];
        int x = homePos[0], y = homePos[1];
        int ans = 0;
        if (i < x) {
            ans += accumulate(rowCosts.begin() + i + 1, rowCosts.begin() + x + 1, 0);
        } else {
            ans += accumulate(rowCosts.begin() + x, rowCosts.begin() + i, 0);
        }
        if (j < y) {
            ans += accumulate(colCosts.begin() + j + 1, colCosts.begin() + y + 1, 0);
        } else {
            ans += accumulate(colCosts.begin() + y, colCosts.begin() + j, 0);
        }
        return ans;
    }
};
```

### **Go**

```go
func minCost(startPos []int, homePos []int, rowCosts []int, colCosts []int) (ans int) {
	i, j := startPos[0], startPos[1]
	x, y := homePos[0], homePos[1]
	if i < x {
		ans += sum(rowCosts, i+1, x+1)
	} else {
		ans += sum(rowCosts, x, i)
	}
	if j < y {
		ans += sum(colCosts, j+1, y+1)
	} else {
		ans += sum(colCosts, y, j)
	}
	return
}

func sum(nums []int, i, j int) (s int) {
	for k := i; k < j; k++ {
		s += nums[k]
	}
	return
}
```

### **...**

```

```

<!-- tabs:end -->
