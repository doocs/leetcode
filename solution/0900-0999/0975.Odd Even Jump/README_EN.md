# [975. Odd Even Jump](https://leetcode.com/problems/odd-even-jump)

[中文文档](/solution/0900-0999/0975.Odd%20Even%20Jump/README.md)

## Description

<p>You are given an integer array <code>arr</code>. From some starting index, you can make a series of jumps. The (1<sup>st</sup>, 3<sup>rd</sup>, 5<sup>th</sup>, ...) jumps in the series are called <strong>odd-numbered jumps</strong>, and the (2<sup>nd</sup>, 4<sup>th</sup>, 6<sup>th</sup>, ...) jumps in the series are called <strong>even-numbered jumps</strong>. Note that the <strong>jumps</strong> are numbered, not the indices.</p>

<p>You may jump forward from index <code>i</code> to index <code>j</code> (with <code>i &lt; j</code>) in the following way:</p>

<ul>
	<li>During <strong>odd-numbered jumps</strong> (i.e., jumps 1, 3, 5, ...), you jump to the index <code>j</code> such that <code>arr[i] &lt;= arr[j]</code> and <code>arr[j]</code> is the smallest possible value. If there are multiple such indices <code>j</code>, you can only jump to the <strong>smallest</strong> such index <code>j</code>.</li>
	<li>During <strong>even-numbered jumps</strong> (i.e., jumps 2, 4, 6, ...), you jump to the index <code>j</code> such that <code>arr[i] &gt;= arr[j]</code> and <code>arr[j]</code> is the largest possible value. If there are multiple such indices <code>j</code>, you can only jump to the <strong>smallest</strong> such index <code>j</code>.</li>
	<li>It may be the case that for some index <code>i</code>, there are no legal jumps.</li>
</ul>

<p>A starting index is <strong>good</strong> if, starting from that index, you can reach the end of the array (index <code>arr.length - 1</code>) by jumping some number of times (possibly 0 or more than once).</p>

<p>Return <em>the number of <strong>good</strong> starting indices</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> arr = [10,13,12,14,15]
<strong>Output:</strong> 2
<strong>Explanation:</strong> 
From starting index i = 0, we can make our 1st jump to i = 2 (since arr[2] is the smallest among arr[1], arr[2], arr[3], arr[4] that is greater or equal to arr[0]), then we cannot jump any more.
From starting index i = 1 and i = 2, we can make our 1st jump to i = 3, then we cannot jump any more.
From starting index i = 3, we can make our 1st jump to i = 4, so we have reached the end.
From starting index i = 4, we have reached the end already.
In total, there are 2 different starting indices i = 3 and i = 4, where we can reach the end with some number of
jumps.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> arr = [2,3,1,1,4]
<strong>Output:</strong> 3
<strong>Explanation:</strong> 
From starting index i = 0, we make jumps to i = 1, i = 2, i = 3:
During our 1st jump (odd-numbered), we first jump to i = 1 because arr[1] is the smallest value in [arr[1], arr[2], arr[3], arr[4]] that is greater than or equal to arr[0].
During our 2nd jump (even-numbered), we jump from i = 1 to i = 2 because arr[2] is the largest value in [arr[2], arr[3], arr[4]] that is less than or equal to arr[1]. arr[3] is also the largest value, but 2 is a smaller index, so we can only jump to i = 2 and not i = 3
During our 3rd jump (odd-numbered), we jump from i = 2 to i = 3 because arr[3] is the smallest value in [arr[3], arr[4]] that is greater than or equal to arr[2].
We can&#39;t jump from i = 3 to i = 4, so the starting index i = 0 is not good.
In a similar manner, we can deduce that:
From starting index i = 1, we jump to i = 4, so we reach the end.
From starting index i = 2, we jump to i = 3, and then we can&#39;t jump anymore.
From starting index i = 3, we jump to i = 4, so we reach the end.
From starting index i = 4, we are already at the end.
In total, there are 3 different starting indices i = 1, i = 3, and i = 4, where we can reach the end with some
number of jumps.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> arr = [5,1,3,4,2]
<strong>Output:</strong> 3
<strong>Explanation:</strong> We can reach the end from starting indices 1, 2, and 4.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= arr.length &lt;= 2 * 10<sup>4</sup></code></li>
	<li><code>0 &lt;= arr[i] &lt; 10<sup>5</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

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
