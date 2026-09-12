---
comments: true
difficulty: Hard
rating: 2723
source: Weekly Contest 431 Q4
tags:
    - Array
    - Binary Search
    - Dynamic Programming
    - Sorting
---

<!-- problem:start -->

# [3414. Maximum Score of Non-overlapping Intervals](https://leetcode.com/problems/maximum-score-of-non-overlapping-intervals)

[中文文档](/solution/3400-3499/3414.Maximum%20Score%20of%20Non-overlapping%20Intervals/README.md)

## Description

<!-- description:start -->

<p>You are given a 2D integer array <code>intervals</code>, where <code>intervals[i] = [l<sub>i</sub>, r<sub>i</sub>, weight<sub>i</sub>]</code>. Interval <code>i</code> starts at position <code>l<sub>i</sub></code> and ends at <code>r<sub>i</sub></code>, and has a weight of <code>weight<sub>i</sub></code>. You can choose <em>up to</em> 4 <strong>non-overlapping</strong> intervals. The <strong>score</strong> of the chosen intervals is defined as the total sum of their weights.</p>

<p>Return the <span data-keyword="lexicographically-smaller-array">lexicographically smallest</span> array of at most 4 indices from <code>intervals</code> with <strong>maximum</strong> score, representing your choice of non-overlapping intervals.</p>

<p>Two intervals are said to be <strong>non-overlapping</strong> if they do not share any points. In particular, intervals sharing a left or right boundary are considered overlapping.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">intervals = [[1,3,2],[4,5,2],[1,5,5],[6,9,3],[6,7,1],[8,9,1]]</span></p>

<p><strong>Output:</strong> <span class="example-io">[2,3]</span></p>

<p><strong>Explanation:</strong></p>

<p>You can choose the intervals with indices 2, and 3 with respective weights of 5, and 3.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">intervals = [[5,8,1],[6,7,7],[4,7,3],[9,10,6],[7,8,2],[11,14,3],[3,5,5]]</span></p>

<p><strong>Output:</strong> <span class="example-io">[1,3,5,6]</span></p>

<p><strong>Explanation:</strong></p>

<p>You can choose the intervals with indices 1, 3, 5, and 6 with respective weights of 7, 6, 3, and 5.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= intevals.length &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>intervals[i].length == 3</code></li>
	<li><code>intervals[i] = [l<sub>i</sub>, r<sub>i</sub>, weight<sub>i</sub>]</code></li>
	<li><code>1 &lt;= l<sub>i</sub> &lt;= r<sub>i</sub> &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= weight<sub>i</sub> &lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Sorting + Binary Search + Dynamic Programming

<!-- thinking:start -->

> **Thinking**
>
> We pick at most four non-overlapping weighted intervals to maximize the total weight, breaking ties by the lexicographically smallest index tuple. $n\le 5\times 10^4$ forbids subset search.
>
> This is weighted interval scheduling with a cap of four. After sorting by left endpoint, the next non-overlapping interval is a binary search.
>
> State $(i,k)$ starts at interval $i$ with $k$ picks remaining. We either skip $i$ or take it and jump to $\textit{nxt}[i]$, comparing both weight and the index list so the lexicographically smallest optimum is kept.

<!-- thinking:end -->

Copy the intervals and record each original index, then sort by left endpoint. For each interval $i$, binary-search the first position $\textit{nxt}[i]$ whose left endpoint is strictly greater than $i$'s right endpoint (shared endpoints count as overlap).

Let $f[i][k]$ be the maximum weight obtainable from interval $i$ onward with at most $k$ picks, and let $g[i][k]$ store the corresponding lexicographically smallest index list. Transition from the back: skipping $i$ inherits $f[i+1][k]$; taking $i$ inserts its original index into $g[\textit{nxt}[i]][k-1]$ and adds the current weight. Keep the larger weight, or the lexicographically smaller index list on a tie. The answer is $g[0][4]$.

The time complexity is $O(n \times \log n)$ and the space complexity is $O(n)$. At most $4$ intervals are chosen, so inserting and comparing index lists is constant time.

<!-- tabs:start -->

#### Python3

```python

```

#### Java

```java
class Solution {
    public int[] maximumWeight(List<List<Integer>> intervals) {
        int n = intervals.size();
        int[][] arr = new int[n][4];
        for (int i = 0; i < n; ++i) {
            List<Integer> e = intervals.get(i);
            arr[i] = new int[] {e.get(0), e.get(1), e.get(2), i};
        }
        Arrays.sort(arr,
            (a, b) -> a[0] != b[0] ? Integer.compare(a[0], b[0]) : Integer.compare(a[1], b[1]));
        int[] nxt = new int[n];
        for (int i = 0; i < n; ++i) {
            nxt[i] = search(arr, arr[i][1], i + 1);
        }
        long[][] f = new long[n + 1][5];
        int[][][] g = new int[n + 1][5][];
        for (int k = 0; k < 5; ++k) {
            g[n][k] = new int[0];
        }
        for (int i = n - 1; i >= 0; --i) {
            g[i][0] = new int[0];
            for (int k = 1; k < 5; ++k) {
                long s1 = f[i + 1][k];
                int[] a1 = g[i + 1][k];
                long s2 = f[nxt[i]][k - 1] + arr[i][2];
                int[] a2 = insert(g[nxt[i]][k - 1], arr[i][3]);
                if (s2 > s1 || (s2 == s1 && less(a2, a1))) {
                    f[i][k] = s2;
                    g[i][k] = a2;
                } else {
                    f[i][k] = s1;
                    g[i][k] = a1;
                }
            }
        }
        return g[0][4];
    }

    private int search(int[][] arr, int x, int l) {
        int r = arr.length;
        while (l < r) {
            int mid = (l + r) >> 1;
            if (arr[mid][0] > x) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }

    private int[] insert(int[] a, int x) {
        int n = a.length;
        int[] b = new int[n + 1];
        int i = 0;
        while (i < n && a[i] < x) {
            b[i] = a[i];
            ++i;
        }
        b[i] = x;
        while (i < n) {
            b[i + 1] = a[i];
            ++i;
        }
        return b;
    }

    private boolean less(int[] a, int[] b) {
        int m = Math.min(a.length, b.length);
        for (int i = 0; i < m; ++i) {
            if (a[i] != b[i]) {
                return a[i] < b[i];
            }
        }
        return a.length < b.length;
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<int> maximumWeight(vector<vector<int>>& intervals) {
        int n = intervals.size();
        vector<array<int, 4>> arr(n);
        for (int i = 0; i < n; ++i) {
            arr[i] = {intervals[i][0], intervals[i][1], intervals[i][2], i};
        }
        ranges::sort(arr);
        vector<int> nxt(n);
        for (int i = 0; i < n; ++i) {
            int l = i + 1, r = n;
            while (l < r) {
                int mid = (l + r) >> 1;
                if (arr[mid][0] > arr[i][1]) {
                    r = mid;
                } else {
                    l = mid + 1;
                }
            }
            nxt[i] = l;
        }
        vector<vector<long long>> f(n + 1, vector<long long>(5));
        vector<vector<vector<int>>> g(n + 1, vector<vector<int>>(5));
        for (int i = n - 1; i >= 0; --i) {
            for (int k = 1; k < 5; ++k) {
                long long s1 = f[i + 1][k];
                vector<int> a1 = g[i + 1][k];
                long long s2 = f[nxt[i]][k - 1] + arr[i][2];
                vector<int> a2 = g[nxt[i]][k - 1];
                a2.insert(ranges::lower_bound(a2, arr[i][3]), arr[i][3]);
                if (s2 > s1 || (s2 == s1 && a2 < a1)) {
                    f[i][k] = s2;
                    g[i][k] = move(a2);
                } else {
                    f[i][k] = s1;
                    g[i][k] = move(a1);
                }
            }
        }
        return g[0][4];
    }
};
```

#### Go

```go

```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
