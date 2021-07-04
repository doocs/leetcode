# [1923. 最长公共子路径](https://leetcode-cn.com/problems/longest-common-subpath)

[English Version](/solution/1900-1999/1923.Longest%20Common%20Subpath/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>一个国家由 <code>n</code> 个编号为 <code>0</code> 到 <code>n - 1</code> 的城市组成。在这个国家里，<strong>每两个</strong> 城市之间都有一条道路连接。</p>

<p>总共有 <code>m</code> 个编号为 <code>0</code> 到 <code>m - 1</code> 的朋友想在这个国家旅游。他们每一个人的路径都会包含一些城市。每条路径都由一个整数数组表示，每个整数数组表示一个朋友按顺序访问过的城市序列。同一个城市在一条路径中可能 <strong>重复</strong> 出现，但同一个城市在一条路径中不会连续出现。</p>

<p>给你一个整数 <code>n</code> 和二维数组 <code>paths</code> ，其中 <code>paths[i]</code> 是一个整数数组，表示第 <code>i</code> 个朋友走过的路径，请你返回 <strong>每一个</strong> 朋友都走过的 <strong>最长公共子路径</strong> 的长度，如果不存在公共子路径，请你返回 <code>0</code> 。</p>

<p>一个 <strong>子路径</strong> 指的是一条路径中连续的城市序列。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<b>输入：</b>n = 5, paths = [[0,1,<strong>2,3</strong>,4],
                     [<strong>2,3</strong>,4],
                     [4,0,1,<strong>2,3</strong>]]
<b>输出：</b>2
<b>解释：</b>最长公共子路径为 [2,3] 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<b>输入：</b>n = 3, paths = [[0],[1],[2]]
<b>输出：</b>0
<b>解释：</b>三条路径没有公共子路径。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<b>输入：</b>n = 5, paths = [[<strong>0</strong>,1,2,3,4],
                     [4,3,2,1,<strong>0</strong>]]
<b>输出：</b>1
<b>解释：</b>最长公共子路径为 [0]，[1]，[2]，[3] 和 [4] 。它们长度都为 1 。</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= n <= 10<sup>5</sup></code></li>
	<li><code>m == paths.length</code></li>
	<li><code>2 <= m <= 10<sup>5</sup></code></li>
	<li><code>sum(paths[i].length) <= 10<sup>5</sup></code></li>
	<li><code>0 <= paths[i][j] < n</code></li>
	<li><code>paths[i]</code> 中同一个城市不会连续重复出现。</li>
</ul>


## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python

```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
