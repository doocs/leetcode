# [546. 移除盒子](https://leetcode.cn/problems/remove-boxes)

[English Version](/solution/0500-0599/0546.Remove%20Boxes/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给出一些不同颜色的盒子<meta charset="UTF-8" />&nbsp;<code>boxes</code>&nbsp;，盒子的颜色由不同的正数表示。</p>

<p>你将经过若干轮操作去去掉盒子，直到所有的盒子都去掉为止。每一轮你可以移除具有相同颜色的连续 <code>k</code> 个盒子（<code>k&nbsp;&gt;= 1</code>），这样一轮之后你将得到 <code>k * k</code> 个积分。</p>

<p>返回 <em>你能获得的最大积分和</em>&nbsp;。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>boxes = [1,3,2,2,2,3,4,3,1]
<strong>输出：</strong>23
<strong>解释：</strong>
[1, 3, 2, 2, 2, 3, 4, 3, 1] 
----&gt; [1, 3, 3, 4, 3, 1] (3*3=9 分) 
----&gt; [1, 3, 3, 3, 1] (1*1=1 分) 
----&gt; [1, 1] (3*3=9 分) 
----&gt; [] (2*2=4 分)
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>boxes = [1,1,1]
<strong>输出：</strong>9
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>boxes = [1]
<strong>输出：</strong>1
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= boxes.length &lt;= 100</code></li>
	<li><code>1 &lt;= boxes[i]&nbsp;&lt;= 100</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：记忆化搜索**

设计递归函数 `dfs(i, j, k)` 表示当前处理的区间为 `[i, j]`，且该区间的右边有 `k` 个与 `boxes[j]` 相同的元素，返回该区间的最大积分。答案即为 `dfs(0, n - 1, 0)`。

对于 `dfs(i, j, k)`，我们可以直接删除 `boxes[j]` 和其右边的 `k` 个元素，所得积分为 `dfs(i, j - 1, 0) + (k + 1) * (k + 1)`。

我们还可以在区间 `[i, j-1]` 内枚举下标 `h`，找到满足 `boxes[h] == boxes[j]` 的下标，那么我们就将区间 `[i, j - 1]` 分成两部分，即 `[i, h]` 和 `[h + 1, j - 1]`。其中 `[i, h]` 的部分可以与 `boxes[j]` 合并，所以积分为 `dfs(i, h, k + 1) + dfs(h + 1, j - 1, 0)`。求不同 `h` 下的最大值即可。

我们使用记忆化搜索来优化递归函数的时间复杂度。

时间复杂度 $O(n^4)$，空间复杂度 $O(n^3)$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def removeBoxes(self, boxes: List[int]) -> int:
        @cache
        def dfs(i, j, k):
            if i > j:
                return 0
            while i < j and boxes[j] == boxes[j - 1]:
                j, k = j - 1, k + 1
            ans = dfs(i, j - 1, 0) + (k + 1) * (k + 1)
            for h in range(i, j):
                if boxes[h] == boxes[j]:
                    ans = max(ans, dfs(h + 1, j - 1, 0) + dfs(i, h, k + 1))
            return ans

        n = len(boxes)
        ans = dfs(0, n - 1, 0)
        dfs.cache_clear()
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    private int[][][] f;
    private int[] b;

    public int removeBoxes(int[] boxes) {
        b = boxes;
        int n = b.length;
        f = new int[n][n][n];
        return dfs(0, n - 1, 0);
    }

    private int dfs(int i, int j, int k) {
        if (i > j) {
            return 0;
        }
        while (i < j && b[j] == b[j - 1]) {
            --j;
            ++k;
        }
        if (f[i][j][k] > 0) {
            return f[i][j][k];
        }
        int ans = dfs(i, j - 1, 0) + (k + 1) * (k + 1);
        for (int h = i; h < j; ++h) {
            if (b[h] == b[j]) {
                ans = Math.max(ans, dfs(h + 1, j - 1, 0) + dfs(i, h, k + 1));
            }
        }
        f[i][j][k] = ans;
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int removeBoxes(vector<int>& boxes) {
        int n = boxes.size();
        vector<vector<vector<int>>> f(n, vector<vector<int>>(n, vector<int>(n)));
        function<int(int, int, int)> dfs;
        dfs = [&](int i, int j, int k) {
            if (i > j) return 0;
            while (i < j && boxes[j] == boxes[j - 1]) {
                --j;
                ++k;
            }
            if (f[i][j][k]) return f[i][j][k];
            int ans = dfs(i, j - 1, 0) + (k + 1) * (k + 1);
            for (int h = i; h < j; ++h) {
                if (boxes[h] == boxes[j]) {
                    ans = max(ans, dfs(h + 1, j - 1, 0) + dfs(i, h, k + 1));
                }
            }
            f[i][j][k] = ans;
            return ans;
        };
        return dfs(0, n - 1, 0);
    }
};
```

### **Go**

```go
func removeBoxes(boxes []int) int {
	n := len(boxes)
	f := make([][][]int, n)
	for i := range f {
		f[i] = make([][]int, n)
		for j := range f[i] {
			f[i][j] = make([]int, n)
		}
	}
	var dfs func(i, j, k int) int
	dfs = func(i, j, k int) int {
		if i > j {
			return 0
		}
		for i < j && boxes[j] == boxes[j-1] {
			j, k = j-1, k+1
		}
		if f[i][j][k] > 0 {
			return f[i][j][k]
		}
		ans := dfs(i, j-1, 0) + (k+1)*(k+1)
		for h := i; h < j; h++ {
			if boxes[h] == boxes[j] {
				ans = max(ans, dfs(h+1, j-1, 0)+dfs(i, h, k+1))
			}
		}
		f[i][j][k] = ans
		return ans
	}
	return dfs(0, n-1, 0)
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
```

### **...**

```

```

<!-- tabs:end -->
