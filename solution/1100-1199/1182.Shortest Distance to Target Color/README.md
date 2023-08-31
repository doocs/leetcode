# [1182. 与目标颜色间的最短距离](https://leetcode.cn/problems/shortest-distance-to-target-color)

[English Version](/solution/1100-1199/1182.Shortest%20Distance%20to%20Target%20Color/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个数组&nbsp;<code>colors</code>，里面有&nbsp;&nbsp;<code>1</code>、<code>2</code>、&nbsp;<code>3</code> 三种颜色。</p>

<p>我们需要在&nbsp;<code>colors</code> 上进行一些查询操作 <code>queries</code>，其中每个待查项都由两个整数 <code>i</code> 和 <code>c</code> 组成。</p>

<p>现在请你帮忙设计一个算法，查找从索引&nbsp;<code>i</code>&nbsp;到具有目标颜色&nbsp;<code>c</code>&nbsp;的元素之间的最短距离。</p>

<p>如果不存在解决方案，请返回&nbsp;<code>-1</code>。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>colors = [1,1,2,1,3,2,2,3,3], queries = [[1,3],[2,2],[6,1]]
<strong>输出：</strong>[3,0,3]
<strong>解释： </strong>
距离索引 1 最近的颜色 3 位于索引 4（距离为 3）。
距离索引 2 最近的颜色 2 就是它自己（距离为 0）。
距离索引 6 最近的颜色 1 位于索引 3（距离为 3）。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>colors = [1,2], queries = [[0,3]]
<strong>输出：</strong>[-1]
<strong>解释：</strong>colors 中没有颜色 3。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= colors.length &lt;= 5*10^4</code></li>
	<li><code>1 &lt;= colors[i] &lt;= 3</code></li>
	<li><code>1&nbsp;&lt;= queries.length &lt;= 5*10^4</code></li>
	<li><code>queries[i].length == 2</code></li>
	<li><code>0 &lt;= queries[i][0] &lt;&nbsp;colors.length</code></li>
	<li><code>1 &lt;= queries[i][1] &lt;= 3</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：预处理**

我们可以预处理出每个位置到左边最近的颜色 $1$,$2$,$3$ 的距离，以及每个位置到右边最近的颜色 $1$,$2$,$3$ 的距离，记录在数组 $left$ 和 $right$ 中。初始时 $left[0][0] = left[0][1] = left[0][2] = -\infty$，而 $right[n][0] = right[n][1] = right[n][2] = \infty$，其中 $n$ 是数组 $colors$ 的长度。

然后对于每个查询 $(i, c)$，最小距离就是 $d = \min(i - left[i + 1][c - 1], right[i][c - 1][i] - i)$，如果 $d \gt n$，则不存在解决方案，此次查询的答案为 $-1$。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 是数组 $colors$ 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def shortestDistanceColor(
        self, colors: List[int], queries: List[List[int]]
    ) -> List[int]:
        n = len(colors)
        right = [[inf] * 3 for _ in range(n + 1)]
        for i in range(n - 1, -1, -1):
            for j in range(3):
                right[i][j] = right[i + 1][j]
            right[i][colors[i] - 1] = i
        left = [[-inf] * 3 for _ in range(n + 1)]
        for i, c in enumerate(colors, 1):
            for j in range(3):
                left[i][j] = left[i - 1][j]
            left[i][c - 1] = i - 1
        ans = []
        for i, c in queries:
            d = min(i - left[i + 1][c - 1], right[i][c - 1] - i)
            ans.append(-1 if d > n else d)
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public List<Integer> shortestDistanceColor(int[] colors, int[][] queries) {
        int n = colors.length;
        final int inf = 1 << 30;
        int[][] right = new int[n + 1][3];
        Arrays.fill(right[n], inf);
        for (int i = n - 1; i >= 0; --i) {
            for (int j = 0; j < 3; ++j) {
                right[i][j] = right[i + 1][j];
            }
            right[i][colors[i] - 1] = i;
        }
        int[][] left = new int[n + 1][3];
        Arrays.fill(left[0], -inf);
        for (int i = 1; i <= n; ++i) {
            for (int j = 0; j < 3; ++j) {
                left[i][j] = left[i - 1][j];
            }
            left[i][colors[i - 1] - 1] = i - 1;
        }
        List<Integer> ans = new ArrayList<>();
        for (int[] q : queries) {
            int i = q[0], c = q[1] - 1;
            int d = Math.min(i - left[i + 1][c], right[i][c] - i);
            ans.add(d > n ? -1 : d);
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<int> shortestDistanceColor(vector<int>& colors, vector<vector<int>>& queries) {
        int n = colors.size();
        int right[n + 1][3];
        const int inf = 1 << 30;
        fill(right[n], right[n] + 3, inf);
        for (int i = n - 1; i >= 0; --i) {
            for (int j = 0; j < 3; ++j) {
                right[i][j] = right[i + 1][j];
            }
            right[i][colors[i] - 1] = i;
        }
        int left[n + 1][3];
        fill(left[0], left[0] + 3, -inf);
        for (int i = 1; i <= n; ++i) {
            for (int j = 0; j < 3; ++j) {
                left[i][j] = left[i - 1][j];
            }
            left[i][colors[i - 1] - 1] = i - 1;
        }
        vector<int> ans;
        for (auto& q : queries) {
            int i = q[0], c = q[1] - 1;
            int d = min(i - left[i + 1][c], right[i][c] - i);
            ans.push_back(d > n ? -1 : d);
        }
        return ans;
    }
};
```

### **Go**

```go
func shortestDistanceColor(colors []int, queries [][]int) (ans []int) {
	n := len(colors)
	const inf = 1 << 30
	right := make([][3]int, n+1)
	left := make([][3]int, n+1)
	right[n] = [3]int{inf, inf, inf}
	left[0] = [3]int{-inf, -inf, -inf}
	for i := n - 1; i >= 0; i-- {
		for j := 0; j < 3; j++ {
			right[i][j] = right[i+1][j]
		}
		right[i][colors[i]-1] = i
	}
	for i := 1; i <= n; i++ {
		for j := 0; j < 3; j++ {
			left[i][j] = left[i-1][j]
		}
		left[i][colors[i-1]-1] = i - 1
	}
	for _, q := range queries {
		i, c := q[0], q[1]-1
		d := min(i-left[i+1][c], right[i][c]-i)
		if d > n {
			d = -1
		}
		ans = append(ans, d)
	}
	return
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}
```

### **TypeScript**

```ts
function shortestDistanceColor(colors: number[], queries: number[][]): number[] {
    const n = colors.length;
    const inf = 1 << 30;
    const right: number[][] = Array(n + 1)
        .fill(0)
        .map(() => Array(3).fill(inf));
    const left: number[][] = Array(n + 1)
        .fill(0)
        .map(() => Array(3).fill(-inf));
    for (let i = n - 1; i >= 0; --i) {
        for (let j = 0; j < 3; ++j) {
            right[i][j] = right[i + 1][j];
        }
        right[i][colors[i] - 1] = i;
    }
    for (let i = 1; i <= n; ++i) {
        for (let j = 0; j < 3; ++j) {
            left[i][j] = left[i - 1][j];
        }
        left[i][colors[i - 1] - 1] = i - 1;
    }
    const ans: number[] = [];
    for (const [i, c] of queries) {
        const d = Math.min(i - left[i + 1][c - 1], right[i][c - 1] - i);
        ans.push(d > n ? -1 : d);
    }
    return ans;
}
```

### **...**

```

```

<!-- tabs:end -->
