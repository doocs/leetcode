# [1923. 最长公共子路径](https://leetcode.cn/problems/longest-common-subpath)

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

**方法一：字符串哈希**

**字符串哈希**是把一个任意长度的字符串映射成一个非负整数，并且其冲突的概率几乎为 0。字符串哈希用于计算字符串哈希值，快速判断两个字符串是否相等。

取一固定值 BASE，把字符串看作是 BASE 进制数，并分配一个大于 0 的数值，代表每种字符。一般来说，我们分配的数值都远小于 BASE。例如，对于小写字母构成的字符串，可以令 a=1, b=2, ..., z=26。取一固定值 MOD，求出该 BASE 进制对 M 的余数，作为该字符串的 hash 值。

一般来说，取 BASE=131 或者 BASE=13331，此时 hash 值产生的冲突概率极低。只要两个字符串 hash 值相同，我们就认为两个字符串是相等的。通常 MOD 取 2^64，C++ 里，可以直接使用 unsigned long long 类型存储这个 hash 值，在计算时不处理算术溢出问题，产生溢出时相当于自动对 2^64 取模，这样可以避免低效取模运算。

除了在极特殊构造的数据上，上述 hash 算法很难产生冲突，一般情况下上述 hash 算法完全可以出现在题目的标准答案中。我们还可以多取一些恰当的 BASE 和 MOD 的值（例如大质数），多进行几组 hash 运算，当结果都相同时才认为原字符串相等，就更加难以构造出使这个 hash 产生错误的数据。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
