---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2300-2399/2343.Query%20Kth%20Smallest%20Trimmed%20Number/README_EN.md
rating: 1651
source: Weekly Contest 302 Q3
tags:
    - Array
    - String
    - Divide and Conquer
    - Quickselect
    - Radix Sort
    - Sorting
    - Heap (Priority Queue)
---

<!-- problem:start -->

# [2343. Query Kth Smallest Trimmed Number](https://leetcode.com/problems/query-kth-smallest-trimmed-number)

[中文文档](/solution/2300-2399/2343.Query%20Kth%20Smallest%20Trimmed%20Number/README.md)

## Description

<!-- description:start -->

<p>You are given a <strong>0-indexed</strong> array of strings <code>nums</code>, where each string is of <strong>equal length</strong> and consists of only digits.</p>

<p>You are also given a <strong>0-indexed</strong> 2D integer array <code>queries</code> where <code>queries[i] = [k<sub>i</sub>, trim<sub>i</sub>]</code>. For each <code>queries[i]</code>, you need to:</p>

<ul>
	<li><strong>Trim</strong> each number in <code>nums</code> to its <strong>rightmost</strong> <code>trim<sub>i</sub></code> digits.</li>
	<li>Determine the <strong>index</strong> of the <code>k<sub>i</sub><sup>th</sup></code> smallest trimmed number in <code>nums</code>. If two trimmed numbers are equal, the number with the <strong>lower</strong> index is considered to be smaller.</li>
	<li>Reset each number in <code>nums</code> to its original length.</li>
</ul>

<p>Return <em>an array </em><code>answer</code><em> of the same length as </em><code>queries</code>,<em> where </em><code>answer[i]</code><em> is the answer to the </em><code>i<sup>th</sup></code><em> query.</em></p>

<p><strong>Note</strong>:</p>

<ul>
	<li>To trim to the rightmost <code>x</code> digits means to keep removing the leftmost digit, until only <code>x</code> digits remain.</li>
	<li>Strings in <code>nums</code> may contain leading zeros.</li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [&quot;102&quot;,&quot;473&quot;,&quot;251&quot;,&quot;814&quot;], queries = [[1,1],[2,3],[4,2],[1,2]]
<strong>Output:</strong> [2,2,1,0]
<strong>Explanation:</strong>
1. After trimming to the last digit, nums = [&quot;2&quot;,&quot;3&quot;,&quot;1&quot;,&quot;4&quot;]. The smallest number is 1 at index 2.
2. Trimmed to the last 3 digits, nums is unchanged. The 2<sup>nd</sup> smallest number is 251 at index 2.
3. Trimmed to the last 2 digits, nums = [&quot;02&quot;,&quot;73&quot;,&quot;51&quot;,&quot;14&quot;]. The 4<sup>th</sup> smallest number is 73.
4. Trimmed to the last 2 digits, the smallest number is 2 at index 0.
   Note that the trimmed number &quot;02&quot; is evaluated as 2.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [&quot;24&quot;,&quot;37&quot;,&quot;96&quot;,&quot;04&quot;], queries = [[2,1],[2,2]]
<strong>Output:</strong> [3,0]
<strong>Explanation:</strong>
1. Trimmed to the last digit, nums = [&quot;4&quot;,&quot;7&quot;,&quot;6&quot;,&quot;4&quot;]. The 2<sup>nd</sup> smallest number is 4 at index 3.
   There are two occurrences of 4, but the one at index 0 is considered smaller than the one at index 3.
2. Trimmed to the last 2 digits, nums is unchanged. The 2<sup>nd</sup> smallest number is 24.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 100</code></li>
	<li><code>1 &lt;= nums[i].length &lt;= 100</code></li>
	<li><code>nums[i]</code> consists of only digits.</li>
	<li>All <code>nums[i].length</code> are <strong>equal</strong>.</li>
	<li><code>1 &lt;= queries.length &lt;= 100</code></li>
	<li><code>queries[i].length == 2</code></li>
	<li><code>1 &lt;= k<sub>i</sub> &lt;= nums.length</code></li>
	<li><code>1 &lt;= trim<sub>i</sub> &lt;= nums[i].length</code></li>
</ul>

<p>&nbsp;</p>
<p><strong>Follow up:</strong> Could you use the <strong>Radix Sort Algorithm</strong> to solve this problem? What will be the complexity of that solution?</p>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Simulation

According to the problem description, we can simulate the cropping process, then sort the cropped strings, and finally find the corresponding number based on the index.

The time complexity is $O(m \times n \times \log n \times s)$, and the space complexity is $O(n)$. Here, $m$ and $n$ are the lengths of $\textit{nums}$ and $\textit{queries}$ respectively, and $s$ is the length of the string $\textit{nums}[i]$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def smallestTrimmedNumbers(
        self, nums: List[str], queries: List[List[int]]
    ) -> List[int]:
        ans = []
        for k, trim in queries:
            t = sorted((v[-trim:], i) for i, v in enumerate(nums))
            ans.append(t[k - 1][1])
        return ans
```

#### Java

```java
class Solution {
    public int[] smallestTrimmedNumbers(String[] nums, int[][] queries) {
        int n = nums.length;
        int m = queries.length;
        int[] ans = new int[m];
        String[][] t = new String[n][2];
        for (int i = 0; i < m; ++i) {
            int k = queries[i][0], trim = queries[i][1];
            for (int j = 0; j < n; ++j) {
                t[j] = new String[] {nums[j].substring(nums[j].length() - trim), String.valueOf(j)};
            }
            Arrays.sort(t, (a, b) -> {
                int x = a[0].compareTo(b[0]);
                return x == 0 ? Long.compare(Integer.valueOf(a[1]), Integer.valueOf(b[1])) : x;
            });
            ans[i] = Integer.valueOf(t[k - 1][1]);
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<int> smallestTrimmedNumbers(vector<string>& nums, vector<vector<int>>& queries) {
        int n = nums.size();
        vector<pair<string, int>> t(n);
        vector<int> ans;
        for (auto& q : queries) {
            int k = q[0], trim = q[1];
            for (int j = 0; j < n; ++j) {
                t[j] = {nums[j].substr(nums[j].size() - trim), j};
            }
            sort(t.begin(), t.end());
            ans.push_back(t[k - 1].second);
        }
        return ans;
    }
};
```

#### Go

```go
func smallestTrimmedNumbers(nums []string, queries [][]int) []int {
	type pair struct {
		s string
		i int
	}
	ans := make([]int, len(queries))
	t := make([]pair, len(nums))
	for i, q := range queries {
		for j, s := range nums {
			t[j] = pair{s[len(s)-q[1]:], j}
		}
		sort.Slice(t, func(i, j int) bool { a, b := t[i], t[j]; return a.s < b.s || a.s == b.s && a.i < b.i })
		ans[i] = t[q[0]-1].i
	}
	return ans
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
