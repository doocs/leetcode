---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3200-3299/3261.Count%20Substrings%20That%20Satisfy%20K-Constraint%20II/README_EN.md
rating: 2658
source: Weekly Contest 411 Q4
tags:
    - Array
    - String
    - Binary Search
    - Prefix Sum
    - Sliding Window
---

<!-- problem:start -->

# [3261. Count Substrings That Satisfy K-Constraint II](https://leetcode.com/problems/count-substrings-that-satisfy-k-constraint-ii)

[中文文档](/solution/3200-3299/3261.Count%20Substrings%20That%20Satisfy%20K-Constraint%20II/README.md)

## Description

<!-- description:start -->

<p>You are given a <strong>binary</strong> string <code>s</code> and an integer <code>k</code>.</p>

<p>You are also given a 2D integer array <code>queries</code>, where <code>queries[i] = [l<sub>i</sub>, r<sub>i</sub>]</code>.</p>

<p>A <strong>binary string</strong> satisfies the <strong>k-constraint</strong> if <strong>either</strong> of the following conditions holds:</p>

<ul>
	<li>The number of <code>0</code>&#39;s in the string is at most <code>k</code>.</li>
	<li>The number of <code>1</code>&#39;s in the string is at most <code>k</code>.</li>
</ul>

<p>Return an integer array <code>answer</code>, where <code>answer[i]</code> is the number of <span data-keyword="substring-nonempty">substrings</span> of <code>s[l<sub>i</sub>..r<sub>i</sub>]</code> that satisfy the <strong>k-constraint</strong>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;0001111&quot;, k = 2, queries = [[0,6]]</span></p>

<p><strong>Output:</strong> <span class="example-io">[26]</span></p>

<p><strong>Explanation:</strong></p>

<p>For the query <code>[0, 6]</code>, all substrings of <code>s[0..6] = &quot;0001111&quot;</code> satisfy the k-constraint except for the substrings <code>s[0..5] = &quot;000111&quot;</code> and <code>s[0..6] = &quot;0001111&quot;</code>.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;010101&quot;, k = 1, queries = [[0,5],[1,4],[2,3]]</span></p>

<p><strong>Output:</strong> <span class="example-io">[15,9,3]</span></p>

<p><strong>Explanation:</strong></p>

<p>The substrings of <code>s</code> with a length greater than 3 do not satisfy the k-constraint.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s[i]</code> is either <code>&#39;0&#39;</code> or <code>&#39;1&#39;</code>.</li>
	<li><code>1 &lt;= k &lt;= s.length</code></li>
	<li><code>1 &lt;= queries.length &lt;= 10<sup>5</sup></code></li>
	<li><code>queries[i] == [l<sub>i</sub>, r<sub>i</sub>]</code></li>
	<li><code>0 &lt;= l<sub>i</sub> &lt;= r<sub>i</sub> &lt; s.length</code></li>
	<li>All queries are distinct.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Sliding Window + Prefix Sum

We use two variables $\textit{cnt0}$ and $\textit{cnt1}$ to record the number of $0$s and $1$s in the current window, respectively. Pointers $i$ and $j$ mark the left and right boundaries of the window. We use an array $d$ to record the first position to the right of each position $i$ that does not satisfy the $k$ constraint, initially setting $d[i] = n$. Additionally, we use a prefix sum array $\textit{pre}[i]$ of length $n + 1$ to record the number of substrings that satisfy the $k$ constraint with the right boundary at position $i$.

When we move the window to the right, if the number of $0$s and $1$s in the window both exceed $k$, we update $d[i]$ to $j$, indicating that the first position to the right of $i$ that does not satisfy the $k$ constraint is $j$. Then we move $i$ one position to the right until the number of $0$s and $1$s in the window are both less than or equal to $k$. At this point, we can calculate the number of substrings that satisfy the $k$ constraint with the right boundary at $j$, which is $j - i + 1$, and update this in the prefix sum array.

Finally, for each query $[l, r]$, we first find the first position $p$ to the right of $l$ that does not satisfy the $k$ constraint, which is $p = \min(r + 1, d[l])$. All substrings in the range $[l, p - 1]$ satisfy the $k$ constraint, and the number of such substrings is $(1 + p - l) \times (p - l) / 2$. Then, we calculate the number of substrings that satisfy the $k$ constraint with the right boundary in the range $[p, r]$, which is $\textit{pre}[r + 1] - \textit{pre}[p]$. Finally, we add the two results together.

The time complexity is $O(n + m)$, and the space complexity is $O(n)$. Here, $n$ and $m$ are the lengths of the string $s$ and the query array $\textit{queries}$, respectively.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def countKConstraintSubstrings(
        self, s: str, k: int, queries: List[List[int]]
    ) -> List[int]:
        cnt = [0, 0]
        i, n = 0, len(s)
        d = [n] * n
        pre = [0] * (n + 1)
        for j, x in enumerate(map(int, s)):
            cnt[x] += 1
            while cnt[0] > k and cnt[1] > k:
                d[i] = j
                cnt[int(s[i])] -= 1
                i += 1
            pre[j + 1] = pre[j] + j - i + 1
        ans = []
        for l, r in queries:
            p = min(r + 1, d[l])
            a = (1 + p - l) * (p - l) // 2
            b = pre[r + 1] - pre[p]
            ans.append(a + b)
        return ans
```

#### Java

```java
class Solution {
    public long[] countKConstraintSubstrings(String s, int k, int[][] queries) {
        int[] cnt = new int[2];
        int n = s.length();
        int[] d = new int[n];
        Arrays.fill(d, n);
        long[] pre = new long[n + 1];
        for (int i = 0, j = 0; j < n; ++j) {
            cnt[s.charAt(j) - '0']++;
            while (cnt[0] > k && cnt[1] > k) {
                d[i] = j;
                cnt[s.charAt(i++) - '0']--;
            }
            pre[j + 1] = pre[j] + j - i + 1;
        }
        int m = queries.length;
        long[] ans = new long[m];
        for (int i = 0; i < m; ++i) {
            int l = queries[i][0], r = queries[i][1];
            int p = Math.min(r + 1, d[l]);
            long a = (1L + p - l) * (p - l) / 2;
            long b = pre[r + 1] - pre[p];
            ans[i] = a + b;
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<long long> countKConstraintSubstrings(string s, int k, vector<vector<int>>& queries) {
        int cnt[2]{};
        int n = s.size();
        vector<int> d(n, n);
        long long pre[n + 1];
        pre[0] = 0;
        for (int i = 0, j = 0; j < n; ++j) {
            cnt[s[j] - '0']++;
            while (cnt[0] > k && cnt[1] > k) {
                d[i] = j;
                cnt[s[i++] - '0']--;
            }
            pre[j + 1] = pre[j] + j - i + 1;
        }
        vector<long long> ans;
        for (const auto& q : queries) {
            int l = q[0], r = q[1];
            int p = min(r + 1, d[l]);
            long long a = (1LL + p - l) * (p - l) / 2;
            long long b = pre[r + 1] - pre[p];
            ans.push_back(a + b);
        }
        return ans;
    }
};
```

#### Go

```go
func countKConstraintSubstrings(s string, k int, queries [][]int) (ans []int64) {
	cnt := [2]int{}
	n := len(s)
	d := make([]int, n)
	for i := range d {
		d[i] = n
	}
	pre := make([]int, n+1)
	for i, j := 0, 0; j < n; j++ {
		cnt[s[j]-'0']++
		for cnt[0] > k && cnt[1] > k {
			d[i] = j
			cnt[s[i]-'0']--
			i++
		}
		pre[j+1] = pre[j] + j - i + 1
	}
	for _, q := range queries {
		l, r := q[0], q[1]
		p := min(r+1, d[l])
		a := (1 + p - l) * (p - l) / 2
		b := pre[r+1] - pre[p]
		ans = append(ans, int64(a+b))
	}
	return
}
```

#### TypeScript

```ts
function countKConstraintSubstrings(s: string, k: number, queries: number[][]): number[] {
    const cnt: [number, number] = [0, 0];
    const n = s.length;
    const d: number[] = Array(n).fill(n);
    const pre: number[] = Array(n + 1).fill(0);
    for (let i = 0, j = 0; j < n; ++j) {
        cnt[+s[j]]++;
        while (Math.min(cnt[0], cnt[1]) > k) {
            d[i] = j;
            cnt[+s[i++]]--;
        }
        pre[j + 1] = pre[j] + j - i + 1;
    }
    const ans: number[] = [];
    for (const [l, r] of queries) {
        const p = Math.min(r + 1, d[l]);
        const a = ((1 + p - l) * (p - l)) / 2;
        const b = pre[r + 1] - pre[p];
        ans.push(a + b);
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
