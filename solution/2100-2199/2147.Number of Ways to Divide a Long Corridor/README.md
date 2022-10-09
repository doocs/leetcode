# [2147. 分隔长廊的方案数](https://leetcode.cn/problems/number-of-ways-to-divide-a-long-corridor)

[English Version](/solution/2100-2199/2147.Number%20of%20Ways%20to%20Divide%20a%20Long%20Corridor/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>在一个图书馆的长廊里，有一些座位和装饰植物排成一列。给你一个下标从 <strong>0</strong>&nbsp;开始，长度为 <code>n</code>&nbsp;的字符串&nbsp;<code>corridor</code>&nbsp;，它包含字母&nbsp;<code>'S'</code> 和&nbsp;<code>'P'</code>&nbsp;，其中每个&nbsp;<code>'S'</code>&nbsp;表示一个座位，每个&nbsp;<code>'P'</code>&nbsp;表示一株植物。</p>

<p>在下标 <code>0</code>&nbsp;的左边和下标 <code>n - 1</code>&nbsp;的右边 <strong>已经</strong>&nbsp;分别各放了一个屏风。你还需要额外放置一些屏风。每一个位置&nbsp;<code>i - 1</code> 和&nbsp;<code>i</code>&nbsp;之间（<code>1 &lt;= i &lt;= n - 1</code>），至多能放一个屏风。</p>

<p>请你将走廊用屏风划分为若干段，且每一段内都 <strong>恰好有两个座位</strong>&nbsp;，而每一段内植物的数目没有要求。可能有多种划分方案，如果两个方案中有任何一个屏风的位置不同，那么它们被视为 <strong>不同</strong> 方案。</p>

<p>请你返回划分走廊的方案数。由于答案可能很大，请你返回它对&nbsp;<code>10<sup>9</sup> + 7</code>&nbsp;<strong>取余</strong>&nbsp;的结果。如果没有任何方案，请返回&nbsp;<code>0</code>&nbsp;。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2100-2199/2147.Number%20of%20Ways%20to%20Divide%20a%20Long%20Corridor/images/1.png" style="width: 410px; height: 199px;"></p>

<pre><b>输入：</b>corridor = "SSPPSPS"
<b>输出：</b>3
<b>解释：</b>总共有 3 种不同分隔走廊的方案。
上图中黑色的竖线表示已经放置好的屏风。
上图每种方案中，每一段都恰好有 <strong>两个</strong>&nbsp;座位。
</pre>

<p><strong>示例 2：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2100-2199/2147.Number%20of%20Ways%20to%20Divide%20a%20Long%20Corridor/images/2.png" style="width: 357px; height: 68px;"></p>

<pre><b>输入：</b>corridor = "PPSPSP"
<b>输出：</b>1
<b>解释：</b>只有 1 种分隔走廊的方案，就是不放置任何屏风。
放置任何的屏风都会导致有一段无法恰好有 2 个座位。
</pre>

<p><strong>示例 3：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2100-2199/2147.Number%20of%20Ways%20to%20Divide%20a%20Long%20Corridor/images/3.png" style="width: 115px; height: 68px;"></p>

<pre><b>输入：</b>corridor = "S"
<b>输出：</b>0
<b>解释：</b>没有任何方案，因为总是有一段无法恰好有 2 个座位。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == corridor.length</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>corridor[i]</code>&nbsp;要么是&nbsp;<code>'S'</code>&nbsp;，要么是&nbsp;<code>'P'</code> 。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：记忆化搜索**

设计函数 `dfs(i, cnt)` 表示从下标 `i` 开始，且当前已经分配了 `cnt` 个座位的方案数。

对于下标 `i` 处的字符，如果是 `S`，那么 `cnt` 加 `1`，如果此时 `cnt` 超过 `2`，那么直接返回 `0`。

否则我们可以选择不放置屏风，此时的方案数为 `dfs(i + 1, cnt)`；如果此时 `cnt` 为 `2`，我们还可以选择放置屏风，此时的方案数为 `dfs(i + 1, 0)`。

最终返回方案数，记忆化搜索即可。

时间复杂度 $O(n\times 3)$，空间复杂度 $O(n\times 3)$。其中 $n$ 为字符串 `corridor` 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def numberOfWays(self, corridor: str) -> int:
        @cache
        def dfs(i, cnt):
            if i == n:
                return int(cnt == 2)
            cnt += corridor[i] == 'S'
            if cnt > 2:
                return 0
            ans = dfs(i + 1, cnt)
            if cnt == 2:
                ans += dfs(i + 1, 0)
                ans %= mod
            return ans

        n = len(corridor)
        mod = 10**9 + 7
        ans = dfs(0, 0)
        dfs.cache_clear()
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    private String s;
    private int n;
    private int[][] f;
    private static final int MOD = (int) 1e9 + 7;

    public int numberOfWays(String corridor) {
        s = corridor;
        n = s.length();
        f = new int[n][3];
        for (var e : f) {
            Arrays.fill(e, -1);
        }
        return dfs(0, 0);
    }

    private int dfs(int i, int cnt) {
        if (i == n) {
            return cnt == 2 ? 1 : 0;
        }
        cnt += s.charAt(i) == 'S' ? 1 : 0;
        if (cnt > 2) {
            return 0;
        }
        if (f[i][cnt] != -1) {
            return f[i][cnt];
        }
        int ans = dfs(i + 1, cnt);
        if (cnt == 2) {
            ans += dfs(i + 1, 0);
            ans %= MOD;
        }
        f[i][cnt] = ans;
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    const int mod = 1e9 + 7;

    int numberOfWays(string corridor) {
        int n = corridor.size();
        vector<vector<int>> f(n, vector<int>(3, -1));
        function<int(int, int)> dfs;
        dfs = [&](int i, int cnt) {
            if (i == n) return cnt == 2 ? 1 : 0;
            cnt += corridor[i] == 'S';
            if (cnt > 2) return 0;
            if (f[i][cnt] != -1) return f[i][cnt];
            int ans = dfs(i + 1, cnt);
            if (cnt == 2) {
                ans += dfs(i + 1, 0);
                ans %= mod;
            }
            f[i][cnt] = ans;
            return ans;
        };
        return dfs(0, 0);
    }
};
```

### **Go**

```go
func numberOfWays(corridor string) int {
	n := len(corridor)
	var mod int = 1e9 + 7
	f := make([][]int, n)
	for i := range f {
		f[i] = make([]int, 3)
		for j := range f[i] {
			f[i][j] = -1
		}
	}
	var dfs func(i, cnt int) int
	dfs = func(i, cnt int) int {
		if i == n {
			if cnt == 2 {
				return 1
			}
			return 0
		}
		if corridor[i] == 'S' {
			cnt++
		}
		if cnt > 2 {
			return 0
		}
		if f[i][cnt] != -1 {
			return f[i][cnt]
		}
		ans := dfs(i+1, cnt)
		if cnt == 2 {
			ans += dfs(i+1, 0)
			ans %= mod
		}
		f[i][cnt] = ans
		return ans
	}
	return dfs(0, 0)
}
```

### **TypeScript**

```ts

```

### **...**

```

```

<!-- tabs:end -->
