# [1923. Longest Common Subpath](https://leetcode.com/problems/longest-common-subpath)

[中文文档](/solution/1900-1999/1923.Longest%20Common%20Subpath/README.md)

## Description

<p>There is a country of <code>n</code> cities numbered from <code>0</code> to <code>n - 1</code>. In this country, there is a road connecting <b>every pair</b> of cities.</p>

<p>There are <code>m</code> friends numbered from <code>0</code> to <code>m - 1</code> who are traveling through the country. Each one of them will take a path consisting of some cities. Each path is represented by an integer array that contains the visited cities in order. The path may contain a city <strong>more than once</strong>, but the same city will not be listed consecutively.</p>

<p>Given an integer <code>n</code> and a 2D integer array <code>paths</code> where <code>paths[i]</code> is an integer array representing the path of the <code>i<sup>th</sup></code> friend, return <em>the length of the <strong>longest common subpath</strong> that is shared by <strong>every</strong> friend&#39;s path, or </em><code>0</code><em> if there is no common subpath at all</em>.</p>

<p>A <strong>subpath</strong> of a path is a contiguous sequence of cities within that path.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> n = 5, paths = [[0,1,<u>2,3</u>,4],
                       [<u>2,3</u>,4],
                       [4,0,1,<u>2,3</u>]]
<strong>Output:</strong> 2
<strong>Explanation:</strong> The longest common subpath is [2,3].
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 3, paths = [[0],[1],[2]]
<strong>Output:</strong> 0
<strong>Explanation:</strong> There is no common subpath shared by the three paths.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> n = 5, paths = [[<u>0</u>,1,2,3,4],
                       [4,3,2,1,<u>0</u>]]
<strong>Output:</strong> 1
<strong>Explanation:</strong> The possible longest common subpaths are [0], [1], [2], [3], and [4]. All have a length of 1.</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>m == paths.length</code></li>
	<li><code>2 &lt;= m &lt;= 10<sup>5</sup></code></li>
	<li><code>sum(paths[i].length) &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= paths[i][j] &lt; n</code></li>
	<li>The same city is not listed multiple times consecutively in <code>paths[i]</code>.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def longestCommonSubpath(self, n: int, paths: List[List[int]]) -> int:
        def get(l, r, h):
            return (h[r] - h[l - 1] * p[r - l + 1]) % mod

        def check(l):
            cnt = Counter()
            for k, path in enumerate(paths):
                vis = set()
                for i in range(len(path) - l + 1):
                    j = i + l - 1
                    x = get(i + 1, j + 1, hh[k])
                    if x not in vis:
                        vis.add(x)
                        cnt[x] += 1
            return max(cnt.values()) == len(paths)

        base = 133331
        mod = 2**64 + 1
        p = [0] * 100010
        p[0] = 1
        for i in range(1, len(p)):
            p[i] = (p[i - 1] * base) % mod
        hh = []
        for path in paths:
            h = [0] * (len(path) + 10)
            for j, c in enumerate(path):
                h[j + 1] = (h[j] * base) % mod + c
            hh.append(h)
        left, right = 0, min(len(path) for path in paths)
        while left < right:
            mid = (left + right + 1) >> 1
            if check(mid):
                left = mid
            else:
                right = mid - 1
        return left
```

### **Java**

```java
class Solution {
    int N = 100010;
    long[] h = new long[N];
    long[] p = new long[N];
    private int[][] paths;
    Map<Long, Integer> cnt = new HashMap<>();
    Map<Long, Integer> inner = new HashMap<>();

    public int longestCommonSubpath(int n, int[][] paths) {
        int left = 0, right = N;
        for (int[] path : paths) {
            right = Math.min(right, path.length);
        }
        this.paths = paths;
        while (left < right) {
            int mid = (left + right + 1) >> 1;
            if (check(mid)) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }

    private boolean check(int mid) {
        cnt.clear();
        inner.clear();
        p[0] = 1;
        for (int j = 0; j < paths.length; ++j) {
            int n = paths[j].length;
            for (int i = 1; i <= n; ++i) {
                p[i] = p[i - 1] * 133331;
                h[i] = h[i - 1] * 133331 + paths[j][i - 1];
            }
            for (int i = mid; i <= n; ++i) {
                long val = get(i - mid + 1, i);
                if (!inner.containsKey(val) || inner.get(val) != j) {
                    inner.put(val, j);
                    cnt.put(val, cnt.getOrDefault(val, 0) + 1);
                }
            }
        }
        int max = 0;
        for (int val : cnt.values()) {
            max = Math.max(max, val);
        }
        return max == paths.length;
    }

    private long get(int l, int r) {
        return h[r] - h[l - 1] * p[r - l + 1];
    }
}
```

### **...**

```

```

<!-- tabs:end -->
