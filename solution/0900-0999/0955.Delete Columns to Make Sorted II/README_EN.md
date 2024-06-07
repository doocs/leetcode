---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0900-0999/0955.Delete%20Columns%20to%20Make%20Sorted%20II/README_EN.md
tags:
    - Greedy
    - Array
    - String
---

<!-- problem:start -->

# [955. Delete Columns to Make Sorted II](https://leetcode.com/problems/delete-columns-to-make-sorted-ii)

[中文文档](/solution/0900-0999/0955.Delete%20Columns%20to%20Make%20Sorted%20II/README.md)

## Description

<!-- description:start -->

<p>You are given an array of <code>n</code> strings <code>strs</code>, all of the same length.</p>

<p>We may choose any deletion indices, and we delete all the characters in those indices for each string.</p>

<p>For example, if we have <code>strs = [&quot;abcdef&quot;,&quot;uvwxyz&quot;]</code> and deletion indices <code>{0, 2, 3}</code>, then the final array after deletions is <code>[&quot;bef&quot;, &quot;vyz&quot;]</code>.</p>

<p>Suppose we chose a set of deletion indices <code>answer</code> such that after deletions, the final array has its elements in <strong>lexicographic</strong> order (i.e., <code>strs[0] &lt;= strs[1] &lt;= strs[2] &lt;= ... &lt;= strs[n - 1]</code>). Return <em>the minimum possible value of</em> <code>answer.length</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> strs = [&quot;ca&quot;,&quot;bb&quot;,&quot;ac&quot;]
<strong>Output:</strong> 1
<strong>Explanation:</strong> 
After deleting the first column, strs = [&quot;a&quot;, &quot;b&quot;, &quot;c&quot;].
Now strs is in lexicographic order (ie. strs[0] &lt;= strs[1] &lt;= strs[2]).
We require at least 1 deletion since initially strs was not in lexicographic order, so the answer is 1.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> strs = [&quot;xc&quot;,&quot;yb&quot;,&quot;za&quot;]
<strong>Output:</strong> 0
<strong>Explanation:</strong> 
strs is already in lexicographic order, so we do not need to delete anything.
Note that the rows of strs are not necessarily in lexicographic order:
i.e., it is NOT necessarily true that (strs[0][0] &lt;= strs[0][1] &lt;= ...)
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> strs = [&quot;zyx&quot;,&quot;wvu&quot;,&quot;tsr&quot;]
<strong>Output:</strong> 3
<strong>Explanation:</strong> We have to delete every column.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == strs.length</code></li>
	<li><code>1 &lt;= n &lt;= 100</code></li>
	<li><code>1 &lt;= strs[i].length &lt;= 100</code></li>
	<li><code>strs[i]</code> consists of lowercase English letters.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1

<!-- tabs:start -->

#### Java

```java
class Solution {
    public int minDeletionSize(String[] A) {
        if (A == null || A.length <= 1) {
            return 0;
        }
        int len = A.length, wordLen = A[0].length(), res = 0;
        boolean[] cut = new boolean[len];
    search:
        for (int j = 0; j < wordLen; j++) {
            // 判断第 j 列是否应当保留
            for (int i = 0; i < len - 1; i++) {
                if (!cut[i] && A[i].charAt(j) > A[i + 1].charAt(j)) {
                    res += 1;
                    continue search;
                }
            }
            // 更新 cut 的信息
            for (int i = 0; i < len - 1; i++) {
                if (A[i].charAt(j) < A[i + 1].charAt(j)) {
                    cut[i] = true;
                }
            }
        }
        return res;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
