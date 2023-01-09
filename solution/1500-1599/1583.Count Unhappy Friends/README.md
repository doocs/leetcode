# [1583. 统计不开心的朋友](https://leetcode.cn/problems/count-unhappy-friends)

[English Version](/solution/1500-1599/1583.Count%20Unhappy%20Friends/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一份 <code>n</code> 位朋友的亲近程度列表，其中 <code>n</code> 总是 <strong>偶数</strong> 。</p>

<p>对每位朋友 <code>i</code>，<code>preferences[i]</code> 包含一份 <strong>按亲近程度从高</strong><strong>到低排列</strong> 的朋友列表。换句话说，排在列表前面的朋友与 <code>i</code> 的亲近程度比排在列表后面的朋友更高。每个列表中的朋友均以 <code>0</code> 到 <code>n-1</code> 之间的整数表示。</p>

<p>所有的朋友被分成几对，配对情况以列表 <code>pairs</code> 给出，其中 <code>pairs[i] = [x<sub>i</sub>, y<sub>i</sub>]</code> 表示 <code>x<sub>i</sub></code> 与 <code>y<sub>i</sub></code> 配对，且 <code>y<sub>i</sub></code> 与 <code>x<sub>i</sub></code> 配对。</p>

<p>但是，这样的配对情况可能会使其中部分朋友感到不开心。在 <code>x</code> 与 <code>y</code> 配对且 <code>u</code> 与 <code>v</code> 配对的情况下，如果同时满足下述两个条件，<code>x</code> 就会不开心：</p>

<ul>
	<li><code>x</code> 与 <code>u</code> 的亲近程度胜过 <code>x</code> 与 <code>y</code>，且</li>
	<li><code>u</code> 与 <code>x</code> 的亲近程度胜过 <code>u</code> 与 <code>v</code></li>
</ul>

<p>返回 <strong>不开心的朋友的数目</strong> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>n = 4, preferences = [[1, 2, 3], [3, 2, 0], [3, 1, 0], [1, 2, 0]], pairs = [[0, 1], [2, 3]]
<strong>输出：</strong>2
<strong>解释：</strong>
朋友 1 不开心，因为：
- <strong>1 与 0 </strong>配对，但 <strong>1 与 3</strong> 的亲近程度比 <strong>1 与 0</strong> 高，且
- <strong>3 与 1</strong> 的亲近程度比 <strong>3 与 2</strong> 高。
朋友 3 不开心，因为：
- 3 与 2 配对，但 <strong>3 与 1</strong> 的亲近程度比 <strong>3 与 2</strong> 高，且
- <strong>1 与 3</strong> 的亲近程度比 <strong>1 与 0</strong> 高。
朋友 0 和 2 都是开心的。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>n = 2, preferences = [[1], [0]], pairs = [[1, 0]]
<strong>输出：</strong>0
<strong>解释：</strong>朋友 0 和 1 都开心。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>n = 4, preferences = [[1, 3, 2], [2, 3, 0], [1, 3, 0], [0, 2, 1]], pairs = [[1, 3], [0, 2]]
<strong>输出：</strong>4
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= n &lt;= 500</code></li>
	<li><code>n</code> 是偶数</li>
	<li><code>preferences.length&nbsp;== n</code></li>
	<li><code>preferences[i].length&nbsp;== n - 1</code></li>
	<li><code>0 &lt;= preferences[i][j] &lt;= n - 1</code></li>
	<li><code>preferences[i]</code> 不包含 <code>i</code></li>
	<li><code>preferences[i]</code> 中的所有值都是独一无二的</li>
	<li><code>pairs.length&nbsp;== n/2</code></li>
	<li><code>pairs[i].length&nbsp;== 2</code></li>
	<li><code>x<sub>i</sub> != y<sub>i</sub></code></li>
	<li><code>0 &lt;= x<sub>i</sub>, y<sub>i</sub>&nbsp;&lt;= n - 1</code></li>
	<li>每位朋友都 <strong>恰好</strong> 被包含在一对中</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：数组 + 枚举**

我们用数组 $d$ 记录每个朋友与其它朋友的亲近程度，其中 $d[i][j]$ 表示朋友 $i$ 对 $j$ 的亲近程度（值越小，越亲近），另外，用数组 $p$ 记录每个朋友的配对朋友。

我们枚举每个朋友 $x$，对于 $x$ 的配对朋友 $y$，我们找到 $x$ 对 $y$ 的亲近程度 $d[x][y]$，然后枚举比 $d[x][y]$ 更亲近的其它朋友 $u$，如果存在 $u$ 对 $x$ 的亲近程度 $d[u][x]$ 比 $d[u][y]$ 更高，那么 $x$ 就是不开心的朋友，将结果加一即可。

枚举结束后，即可得到不开心的朋友的数目。

时间复杂度 $O(n^2)$，空间复杂度 $O(n^2)$。其中 $n$ 为朋友的数目。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def unhappyFriends(
        self, n: int, preferences: List[List[int]], pairs: List[List[int]]
    ) -> int:
        d = [{p: i for i, p in enumerate(v)} for v in preferences]
        p = {}
        for x, y in pairs:
            p[x] = y
            p[y] = x
        ans = 0
        for x in range(n):
            y = p[x]
            ans += any(d[u][x] < d[u][p[u]] for u in preferences[x][: d[x][y]])
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int unhappyFriends(int n, int[][] preferences, int[][] pairs) {
        int[][] d = new int[n][n];
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n - 1; ++j) {
                d[i][preferences[i][j]] = j;
            }
        }
        int[] p = new int[n];
        for (var e : pairs) {
            int x = e[0], y = e[1];
            p[x] = y;
            p[y] = x;
        }
        int ans = 0;
        for (int x = 0; x < n; ++x) {
            int y = p[x];
            int find = 0;
            for (int i = 0; i < d[x][y]; ++i) {
                int u = preferences[x][i];
                if (d[u][x] < d[u][p[u]]) {
                    find = 1;
                    break;
                }
            }
            ans += find;
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int unhappyFriends(int n, vector<vector<int>>& preferences, vector<vector<int>>& pairs) {
        int d[n][n];
        int p[n];
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n - 1; ++j) {
                d[i][preferences[i][j]] = j;
            }
        }
        for (auto& e : pairs) {
            int x = e[0], y = e[1];
            p[x] = y;
            p[y] = x;
        }
        int ans = 0;
        for (int x = 0; x < n; ++x) {
            int y = p[x];
            int find = 0;
            for (int i = 0; i < d[x][y]; ++i) {
                int u = preferences[x][i];
                if (d[u][x] < d[u][p[u]]) {
                    find = 1;
                    break;
                }
            }
            ans += find;
        }
        return ans;
    }
};
```

### **Go**

```go
func unhappyFriends(n int, preferences [][]int, pairs [][]int) (ans int) {
	d := make([][]int, n)
	p := make([]int, n)
	for i := range d {
		d[i] = make([]int, n)
		for j := 0; j < n-1; j++ {
			d[i][preferences[i][j]] = j
		}
	}
	for _, e := range pairs {
		x, y := e[0], e[1]
		p[x] = y
		p[y] = x
	}
	for x := 0; x < n; x++ {
		y := p[x]
		find := 0
		for i := 0; i < d[x][y]; i++ {
			u := preferences[x][i]
			if d[u][x] < d[u][p[u]] {
				find = 1
				break
			}
		}
		ans += find
	}
	return
}
```

### **...**

```

```

<!-- tabs:end -->
