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

### Solution 1

<!-- thinking:start -->

> **Thinking**
>
> We pick at most four non-overlapping weighted intervals to maximize the total weight, breaking ties by the lexicographically smallest index tuple. $n\le 5\times 10^4$ forbids subset search.
>
> This is weighted interval scheduling with a cap of four. After sorting by right endpoint, the next non-overlapping interval is a binary search.
>
> State $(i,\textit{left})$ starts at interval $i$ with $\textit{left}$ picks remaining. We either skip $i$ or take it and jump to $\textit{next}[i]$, comparing both weight and the index list so the lexicographically smallest optimum is kept.

<!-- thinking:end -->

<!-- tabs:start -->

#### Python3

```python

```

#### Java

```java
import java.util.*;

class Solution {

    static class State {
        long score;
        int[] ids;

        State(long score, int[] ids) {
            this.score = score;
            this.ids = ids;
        }
    }

    public int[] maximumWeight(List<List<Integer>> intervals) {
        int n = intervals.size();

        // [left, right, weight, originalIndex]
        long[][] arr = new long[n][4];

        for (int i = 0; i < n; i++) {
            arr[i][0] = intervals.get(i).get(0);
            arr[i][1] = intervals.get(i).get(1);
            arr[i][2] = intervals.get(i).get(2);
            arr[i][3] = i;
        }

        Arrays.sort(arr, (a, b) -> {
            if (a[0] != b[0]) {
                return Long.compare(a[0], b[0]);
            }
            return Long.compare(a[1], b[1]);
        });

        // next[i] = first interval whose left > arr[i].right
        int[] next = new int[n];

        for (int i = 0; i < n; i++) {
            int lo = i + 1;
            int hi = n;

            while (lo < hi) {
                int mid = lo + (hi - lo) / 2;

                if (arr[mid][0] > arr[i][1]) {
                    hi = mid;
                } else {
                    lo = mid + 1;
                }
            }

            next[i] = lo;
        }

        /*
         * dp[c][i] = best answer using intervals from i onward,
         * while selecting at most c intervals.
         */
        State[][] dp = new State[5][n + 1];

        for (int c = 0; c <= 4; c++) {
            dp[c][n] = new State(0, new int[0]);
        }

        for (int i = n - 1; i >= 0; i--) {

            dp[0][i] = new State(0, new int[0]);

            for (int c = 1; c <= 4; c++) {

                // Option 1: skip current interval
                State skip = dp[c][i + 1];

                // Option 2: take current interval
                State rest = dp[c - 1][next[i]];

                long takeScore = arr[i][2] + rest.score;

                int[] takeIds = insertSorted(
                    rest.ids,
                    (int) arr[i][3]
                );

                State take = new State(takeScore, takeIds);

                dp[c][i] = better(skip, take);
            }
        }

        return dp[4][0].ids;
    }

    private State better(State a, State b) {

        if (a.score > b.score) {
            return a;
        }

        if (b.score > a.score) {
            return b;
        }

        // Same score -> lexicographically smallest
        if (lexSmaller(a.ids, b.ids)) {
            return a;
        }

        return b;
    }

    private boolean lexSmaller(int[] a, int[] b) {
        int len = Math.min(a.length, b.length);

        for (int i = 0; i < len; i++) {
            if (a[i] != b[i]) {
                return a[i] < b[i];
            }
        }

        return a.length < b.length;
    }

    private int[] insertSorted(int[] arr, int value) {
        int[] result = new int[arr.length + 1];

        int i = 0;

        while (i < arr.length && arr[i] < value) {
            result[i] = arr[i];
            i++;
        }

        result[i] = value;

        while (i < arr.length) {
            result[i + 1] = arr[i];
            i++;
        }

        return result;
    }
}

```

#### C++

```cpp

```

#### Go

```go

```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
