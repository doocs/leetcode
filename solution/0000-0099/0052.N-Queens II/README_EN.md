# [52. N-Queens II](https://leetcode.com/problems/n-queens-ii)

[中文文档](/solution/0000-0099/0052.N-Queens%20II/README.md)

## Description

<p>The <strong>n-queens</strong> puzzle is the problem of placing <code>n</code> queens on an <code>n x n</code> chessboard such that no two queens attack each other.</p>

<p>Given an integer <code>n</code>, return <em>the number of distinct solutions to the&nbsp;<strong>n-queens puzzle</strong></em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0000-0099/0052.N-Queens%20II/images/queens.jpg" style="width: 600px; height: 268px;" />
<pre>
<strong>Input:</strong> n = 4
<strong>Output:</strong> 2
<strong>Explanation:</strong> There are two distinct solutions to the 4-queens puzzle as shown.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 1
<strong>Output:</strong> 1
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 9</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

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
