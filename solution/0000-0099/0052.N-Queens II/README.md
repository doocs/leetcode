# [52. N 皇后 II](https://leetcode.cn/problems/n-queens-ii)

[English Version](/solution/0000-0099/0052.N-Queens%20II/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p><strong>n&nbsp;皇后问题</strong> 研究的是如何将 <code>n</code>&nbsp;个皇后放置在 <code>n × n</code> 的棋盘上，并且使皇后彼此之间不能相互攻击。</p>

<p>给你一个整数 <code>n</code> ，返回 <strong>n 皇后问题</strong> 不同的解决方案的数量。</p>

<p>&nbsp;</p>

<div class="original__bRMd">
<div>
<p><strong>示例 1：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0000-0099/0052.N-Queens%20II/images/queens.jpg" style="width: 600px; height: 268px;" />
<pre>
<strong>输入：</strong>n = 4
<strong>输出：</strong>2
<strong>解释：</strong>如上图所示，4 皇后问题存在两个不同的解法。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>n = 1
<strong>输出：</strong>1
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 9</code></li>
</ul>
</div>
</div>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：回溯**

我们设计一个函数 $dfs(i)$，表示从第 $i$ 行开始搜索，搜索到的结果累加到答案中。

在第 $i$ 行，我们枚举第 $i$ 行的每一列，如果当前列不与前面已经放置的皇后发生冲突，那么我们就可以放置一个皇后，然后继续搜索下一行，即调用 $dfs(i + 1)$。

如果发生冲突，那么我们就跳过当前列，继续枚举下一列。

判断是否发生冲突，我们需要用三个数组分别记录每一列、每一条正对角线、每一条反对角线是否已经放置了皇后。

具体地，我们用 $cols$ 数组记录每一列是否已经放置了皇后，用 $dg$ 数组记录每一条正对角线是否已经放置了皇后，用 $udg$ 数组记录每一条反对角线是否已经放置了皇后。

时间复杂度 $O(n!)$，空间复杂度 $O(n)$。其中 $n$ 是皇后的数量。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def totalNQueens(self, n: int) -> int:
        def dfs(i):
            if i == n:
                nonlocal ans
                ans += 1
                return
            for j in range(n):
                a, b = i + j, i - j + n
                if cols[j] or dg[a] or udg[b]:
                    continue
                cols[j] = dg[a] = udg[b] = True
                dfs(i + 1)
                cols[j] = dg[a] = udg[b] = False

        cols = [False] * 10
        dg = [False] * 20
        udg = [False] * 20
        ans = 0
        dfs(0)
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    private int n;
    private int ans;
    private boolean[] cols = new boolean[10];
    private boolean[] dg = new boolean[20];
    private boolean[] udg = new boolean[20];

    public int totalNQueens(int n) {
        this.n = n;
        dfs(0);
        return ans;
    }

    private void dfs(int i) {
        if (i == n) {
            ++ans;
            return;
        }
        for (int j = 0; j < n; ++j) {
            int a = i + j, b = i - j + n;
            if (cols[j] || dg[a] || udg[b]) {
                continue;
            }
            cols[j] = true;
            dg[a] = true;
            udg[b] = true;
            dfs(i + 1);
            cols[j] = false;
            dg[a] = false;
            udg[b] = false;
        }
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int totalNQueens(int n) {
        bitset<10> cols;
        bitset<20> dg;
        bitset<20> udg;
        int ans = 0;
        function<void(int)> dfs = [&](int i) {
            if (i == n) {
                ++ans;
                return;
            }
            for (int j = 0; j < n; ++j) {
                int a = i + j, b = i - j + n;
                if (cols[j] || dg[a] || udg[b]) continue;
                cols[j] = dg[a] = udg[b] = 1;
                dfs(i + 1);
                cols[j] = dg[a] = udg[b] = 0;
            }
        };
        dfs(0);
        return ans;
    }
};
```

### **Go**

```go
func totalNQueens(n int) (ans int) {
	cols := [10]bool{}
	dg := [20]bool{}
	udg := [20]bool{}
	var dfs func(int)
	dfs = func(i int) {
		if i == n {
			ans++
			return
		}
		for j := 0; j < n; j++ {
			a, b := i+j, i-j+n
			if cols[j] || dg[a] || udg[b] {
				continue
			}
			cols[j], dg[a], udg[b] = true, true, true
			dfs(i + 1)
			cols[j], dg[a], udg[b] = false, false, false
		}
	}
	dfs(0)
	return
}
```

### **...**

```

```

<!-- tabs:end -->
