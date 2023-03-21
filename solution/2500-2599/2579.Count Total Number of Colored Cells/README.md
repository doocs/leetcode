# [2579. 统计染色格子数](https://leetcode.cn/problems/count-total-number-of-colored-cells)

[English Version](/solution/2500-2599/2579.Count%20Total%20Number%20of%20Colored%20Cells/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>有一个无穷大的二维网格图，一开始所有格子都未染色。给你一个正整数&nbsp;<code>n</code>&nbsp;，表示你需要执行以下步骤&nbsp;<code>n</code>&nbsp;分钟：</p>

<ul>
	<li>第一分钟，将 <strong>任一</strong> 格子染成蓝色。</li>
	<li>之后的每一分钟，将与蓝色格子相邻的 <strong>所有</strong> 未染色格子染成蓝色。</li>
</ul>

<p>下图分别是 1、2、3 分钟后的网格图。</p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2500-2599/2579.Count%20Total%20Number%20of%20Colored%20Cells/images/example-copy-2.png" style="width: 500px; height: 279px;">
<p>请你返回 <code>n</code>&nbsp;分钟之后 <strong>被染色的格子&nbsp;</strong>数目。</p>

<p>&nbsp;</p>

<p><b>示例 1：</b></p>

<pre><b>输入：</b>n = 1
<b>输出：</b>1
<b>解释：</b>1 分钟后，只有 1 个蓝色的格子，所以返回 1 。
</pre>

<p><strong>示例 2：</strong></p>

<pre><b>输入：</b>n = 2
<b>输出：</b>5
<b>解释：</b>2 分钟后，有 4 个在边缘的蓝色格子和 1 个在中间的蓝色格子，所以返回 5 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：数学**

我们观察发现，第 $n$ 分钟后，网格中共有 $2 \times n - 1$ 列，每一列上的数字分别为 $1, 3, 5, \cdots, 2 \times n - 1, 2 \times n - 3, \cdots, 3, 1$。左右两部分均为等差数列，求和可以得到答案 $2 \times n \times (n - 1) + 1$。

时间复杂度 $O(1)$，空间复杂度 $O(1)$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def coloredCells(self, n: int) -> int:
        return 2 * n * (n - 1) + 1
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public long coloredCells(int n) {
        return 2L * n * (n - 1) + 1;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    long long coloredCells(int n) {
        return 2LL * n * (n - 1) + 1;
    }
};
```

### **Go**

```go
func coloredCells(n int) int64 {
	return int64(2*n*(n-1) + 1)
}
```

### **TypeScript**

```ts
function coloredCells(n: number): number {
    return 2 * n * (n - 1) + 1;
}
```

### **...**

```

```

<!-- tabs:end -->
