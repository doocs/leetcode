# [975. 奇偶跳](https://leetcode.cn/problems/odd-even-jump)

[English Version](/solution/0900-0999/0975.Odd%20Even%20Jump/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个整数数组 <code>A</code>，你可以从某一起始索引出发，跳跃一定次数。在你跳跃的过程中，第 1、3、5... 次跳跃称为奇数跳跃，而第 2、4、6... 次跳跃称为偶数跳跃。</p>

<p>你可以按以下方式从索引 <code>i</code>&nbsp;向后跳转到索引 <code>j</code>（其中 <code>i &lt; j</code>）：</p>

<ul>
	<li>在进行奇数跳跃时（如，第&nbsp;1，3，5... 次跳跃），你将会跳到索引 <code>j</code>，使得 <code>A[i] &lt;=&nbsp;A[j]</code>，<code>A[j]</code> 是可能的最小值。如果存在多个这样的索引 <code>j</code>，你只能跳到满足要求的<strong>最小</strong>索引 <code>j</code> 上。</li>
	<li>在进行偶数跳跃时（如，第&nbsp;2，4，6... 次跳跃），你将会跳到索引&nbsp;<code>j</code>，使得 <code>A[i] &gt;= A[j]</code>，<code>A[j]</code> 是可能的最大值。如果存在多个这样的索引 <code>j</code>，你只能跳到满足要求的<strong>最小</strong>索引 <code>j</code>&nbsp;上。</li>
	<li>（对于某些索引 <code>i</code>，可能无法进行合乎要求的跳跃。）</li>
</ul>

<p>如果从某一索引开始跳跃一定次数（可能是 0 次或多次），就可以到达数组的末尾（索引 <code>A.length - 1</code>），那么该索引就会被认为是好的起始索引。</p>

<p>返回好的起始索引的数量。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>[10,13,12,14,15]
<strong>输出：</strong>2
<strong>解释： </strong>
从起始索引 i = 0 出发，我们可以跳到 i = 2，（因为 A[2] 是 A[1]，A[2]，A[3]，A[4] 中大于或等于 A[0] 的最小值），然后我们就无法继续跳下去了。
从起始索引 i = 1 和 i = 2 出发，我们可以跳到 i = 3，然后我们就无法继续跳下去了。
从起始索引 i = 3 出发，我们可以跳到 i = 4，到达数组末尾。
从起始索引 i = 4 出发，我们已经到达数组末尾。
总之，我们可以从 2 个不同的起始索引（i = 3, i = 4）出发，通过一定数量的跳跃到达数组末尾。
</pre>

<p><strong>示例&nbsp;2：</strong></p>

<pre><strong>输入：</strong>[2,3,1,1,4]
<strong>输出：</strong>3
<strong>解释：</strong>
从起始索引 i=0 出发，我们依次可以跳到 i = 1，i = 2，i = 3：

在我们的第一次跳跃（奇数）中，我们先跳到 i = 1，因为 A[1] 是（A[1]，A[2]，A[3]，A[4]）中大于或等于 A[0] 的最小值。

在我们的第二次跳跃（偶数）中，我们从 i = 1 跳到 i = 2，因为 A[2] 是（A[2]，A[3]，A[4]）中小于或等于 A[1] 的最大值。A[3] 也是最大的值，但 2 是一个较小的索引，所以我们只能跳到 i = 2，而不能跳到 i = 3。

在我们的第三次跳跃（奇数）中，我们从 i = 2 跳到 i = 3，因为 A[3] 是（A[3]，A[4]）中大于或等于 A[2] 的最小值。

我们不能从 i = 3 跳到 i = 4，所以起始索引 i = 0 不是好的起始索引。

类似地，我们可以推断：
从起始索引 i = 1 出发， 我们跳到 i = 4，这样我们就到达数组末尾。
从起始索引 i = 2 出发， 我们跳到 i = 3，然后我们就不能再跳了。
从起始索引 i = 3 出发， 我们跳到 i = 4，这样我们就到达数组末尾。
从起始索引 i = 4 出发，我们已经到达数组末尾。
总之，我们可以从 3 个不同的起始索引（i = 1, i = 3, i = 4）出发，通过一定数量的跳跃到达数组末尾。
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>[5,1,3,4,2]
<strong>输出：</strong>3
<strong>解释： </strong>
我们可以从起始索引 1，2，4 出发到达数组末尾。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ol>
	<li><code>1 &lt;= A.length &lt;= 20000</code></li>
	<li><code>0 &lt;= A[i] &lt; 100000</code></li>
</ol>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：有序集合 + 记忆化搜索**

我们先利用有序集合，预处理出每个位置能跳到的位置，记录在数组 $g$ 中，其中 $g[i][1]$ 和 $g[i][0]$ 分别表示当前位置是奇数次跳还是偶数次跳时能跳到的位置。如果不能跳到任何位置，那么 $g[i][1]$ 和 $g[i][0]$ 都为 $-1$。

然后利用记忆化搜索，从每个位置出发，且当前是奇数次跳跃，判断是否能跳到数组末尾，如果能，那么结果加一。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(n)$。其中 $n$ 为数组长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
from sortedcontainers import SortedDict


class Solution:
    def oddEvenJumps(self, arr: List[int]) -> int:
        @cache
        def dfs(i: int, k: int) -> bool:
            if i == n - 1:
                return True
            if g[i][k] == -1:
                return False
            return dfs(g[i][k], k ^ 1)

        n = len(arr)
        g = [[0] * 2 for _ in range(n)]
        sd = SortedDict()
        for i in range(n - 1, -1, -1):
            j = sd.bisect_left(arr[i])
            g[i][1] = sd.values()[j] if j < len(sd) else -1
            j = sd.bisect_right(arr[i]) - 1
            g[i][0] = sd.values()[j] if j >= 0 else -1
            sd[arr[i]] = i
        return sum(dfs(i, 1) for i in range(n))
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    private int n;
    private Integer[][] f;
    private int[][] g;

    public int oddEvenJumps(int[] arr) {
        TreeMap<Integer, Integer> tm = new TreeMap<>();
        n = arr.length;
        f = new Integer[n][2];
        g = new int[n][2];
        for (int i = n - 1; i >= 0; --i) {
            var hi = tm.ceilingEntry(arr[i]);
            g[i][1] = hi == null ? -1 : hi.getValue();
            var lo = tm.floorEntry(arr[i]);
            g[i][0] = lo == null ? -1 : lo.getValue();
            tm.put(arr[i], i);
        }
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            ans += dfs(i, 1);
        }
        return ans;
    }

    private int dfs(int i, int k) {
        if (i == n - 1) {
            return 1;
        }
        if (g[i][k] == -1) {
            return 0;
        }
        if (f[i][k] != null) {
            return f[i][k];
        }
        return f[i][k] = dfs(g[i][k], k ^ 1);
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int oddEvenJumps(vector<int>& arr) {
        int n = arr.size();
        map<int, int> d;
        int f[n][2];
        int g[n][2];
        memset(f, 0, sizeof(f));
        for (int i = n - 1; ~i; --i) {
            auto it = d.lower_bound(arr[i]);
            g[i][1] = it == d.end() ? -1 : it->second;
            it = d.upper_bound(arr[i]);
            g[i][0] = it == d.begin() ? -1 : prev(it)->second;
            d[arr[i]] = i;
        }
        function<int(int, int)> dfs = [&](int i, int k) -> int {
            if (i == n - 1) {
                return 1;
            }
            if (g[i][k] == -1) {
                return 0;
            }
            if (f[i][k] != 0) {
                return f[i][k];
            }
            return f[i][k] = dfs(g[i][k], k ^ 1);
        };
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            ans += dfs(i, 1);
        }
        return ans;
    }
};
```

### **Go**

```go
func oddEvenJumps(arr []int) (ans int) {
	n := len(arr)
	rbt := redblacktree.NewWithIntComparator()
	f := make([][2]int, n)
	g := make([][2]int, n)
	for i := n - 1; i >= 0; i-- {
		if v, ok := rbt.Ceiling(arr[i]); ok {
			g[i][1] = v.Value.(int)
		} else {
			g[i][1] = -1
		}
		if v, ok := rbt.Floor(arr[i]); ok {
			g[i][0] = v.Value.(int)
		} else {
			g[i][0] = -1
		}
		rbt.Put(arr[i], i)
	}
	var dfs func(int, int) int
	dfs = func(i, k int) int {
		if i == n-1 {
			return 1
		}
		if g[i][k] == -1 {
			return 0
		}
		if f[i][k] != 0 {
			return f[i][k]
		}
		f[i][k] = dfs(g[i][k], k^1)
		return f[i][k]
	}
	for i := 0; i < n; i++ {
		if dfs(i, 1) == 1 {
			ans++
		}
	}
	return
}
```

### **...**

```

```

<!-- tabs:end -->
