---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2900-2999/2955.Number%20of%20Same-End%20Substrings/README_EN.md
tags:
    - Array
    - Hash Table
    - String
    - Counting
    - Prefix Sum
---

<!-- problem:start -->

# [2955. Number of Same-End Substrings 🔒](https://leetcode.com/problems/number-of-same-end-substrings)

[中文文档](/solution/2900-2999/2955.Number%20of%20Same-End%20Substrings/README.md)

## Description

<!-- description:start -->

<p>You are given a <strong>0-indexed</strong> string <code>s</code>, and a 2D array of integers <code>queries</code>, where <code>queries[i] = [l<sub>i</sub>, r<sub>i</sub>]</code> indicates a substring of <code>s</code> starting from the index <code>l<sub>i</sub></code> and ending at the index <code>r<sub>i</sub></code> (both <strong>inclusive</strong>), i.e. <code>s[l<sub>i</sub>..r<sub>i</sub>]</code>.</p>

<p>Return <em>an array </em><code>ans</code><em> where</em> <code>ans[i]</code> <em>is the number of <strong>same-end</strong> substrings of</em> <code>queries[i]</code>.</p>

<p>A <strong>0-indexed</strong> string <code>t</code> of length <code>n</code> is called <strong>same-end</strong> if it has the same character at both of its ends, i.e., <code>t[0] == t[n - 1]</code>.</p>

<p>A <b>substring</b> is a contiguous non-empty sequence of characters within a string.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;abcaab&quot;, queries = [[0,0],[1,4],[2,5],[0,5]]
<strong>Output:</strong> [1,5,5,10]
<strong>Explanation:</strong> Here is the same-end substrings of each query:
1<sup>st</sup> query: s[0..0] is &quot;a&quot; which has 1 same-end substring: &quot;<strong><u>a</u></strong>&quot;.
2<sup>nd</sup> query: s[1..4] is &quot;bcaa&quot; which has 5 same-end substrings: &quot;<strong><u>b</u></strong>caa&quot;, &quot;b<strong><u>c</u></strong>aa&quot;, &quot;bc<strong><u>a</u></strong>a&quot;, &quot;bca<strong><u>a</u></strong>&quot;, &quot;bc<strong><u>aa</u></strong>&quot;.
3<sup>rd</sup> query: s[2..5] is &quot;caab&quot; which has 5 same-end substrings: &quot;<strong><u>c</u></strong>aab&quot;, &quot;c<strong><u>a</u></strong>ab&quot;, &quot;ca<strong><u>a</u></strong>b&quot;, &quot;caa<strong><u>b</u></strong>&quot;, &quot;c<strong><u>aa</u></strong>b&quot;.
4<sup>th</sup> query: s[0..5] is &quot;abcaab&quot; which has 10 same-end substrings: &quot;<strong><u>a</u></strong>bcaab&quot;, &quot;a<strong><u>b</u></strong>caab&quot;, &quot;ab<strong><u>c</u></strong>aab&quot;, &quot;abc<strong><u>a</u></strong>ab&quot;, &quot;abca<strong><u>a</u></strong>b&quot;, &quot;abcaa<strong><u>b</u></strong>&quot;, &quot;abc<strong><u>aa</u></strong>b&quot;, &quot;<strong><u>abca</u></strong>ab&quot;, &quot;<strong><u>abcaa</u></strong>b&quot;, &quot;a<strong><u>bcaab</u></strong>&quot;.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;abcd&quot;, queries = [[0,3]]
<strong>Output:</strong> [4]
<strong>Explanation:</strong> The only query is s[0..3] which is &quot;abcd&quot;. It has 4 same-end substrings: &quot;<strong><u>a</u></strong>bcd&quot;, &quot;a<strong><u>b</u></strong>cd&quot;, &quot;ab<strong><u>c</u></strong>d&quot;, &quot;abc<strong><u>d</u></strong>&quot;.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= s.length &lt;= 3 * 10<sup>4</sup></code></li>
	<li><code>s</code> consists only of lowercase English letters.</li>
	<li><code>1 &lt;= queries.length &lt;= 3 * 10<sup>4</sup></code></li>
	<li><code>queries[i] = [l<sub>i</sub>, r<sub>i</sub>]</code></li>
	<li><code>0 &lt;= l<sub>i</sub> &lt;= r<sub>i</sub> &lt; s.length</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Prefix Sum + Enumeration

We can preprocess the prefix sum for each letter and record it in the array $cnt$, where $cnt[i][j]$ represents the number of times the $i$-th letter appears in the first $j$ characters. In this way, for each interval $[l, r]$, we can enumerate each letter $c$ in the interval, quickly calculate the number of times $c$ appears in the interval $x$ using the prefix sum array. We can arbitrarily choose two of them to form a tail-equal substring, the number of substrings is $C_x^2=\frac{x(x-1)}{2}$, plus the situation where each letter in the interval can form a tail-equal substring alone, there are $r - l + 1$ letters in total. Therefore, for each query $[l, r]$, the number of tail-equal substrings that meet the conditions is $r - l + 1 + \sum_{c \in \Sigma} \frac{x_c(x_c-1)}{2}$, where $x_c$ represents the number of times the letter $c$ appears in the interval $[l, r]$.

The time complexity is $O((n + m) \times |\Sigma|)$, and the space complexity is $O(n \times |\Sigma|)$. Here, $n$ and $m$ are the lengths of the string $s$ and the number of queries, respectively, and $\Sigma$ represents the set of letters appearing in the string $s$, in this problem $|\Sigma|=26$.

<!-- tabs:start -->

```python
class Solution:
    def sameEndSubstringCount(self, s: str, queries: List[List[int]]) -> List[int]:
        n = len(s)
        cs = set(s)
        cnt = {c: [0] * (n + 1) for c in cs}
        for i, a in enumerate(s, 1):
            for c in cs:
                cnt[c][i] = cnt[c][i - 1]
            cnt[a][i] += 1
        ans = []
        for l, r in queries:
            t = r - l + 1
            for c in cs:
                x = cnt[c][r + 1] - cnt[c][l]
                t += x * (x - 1) // 2
            ans.append(t)
        return ans
```

```java
class Solution {
    public int[] sameEndSubstringCount(String s, int[][] queries) {
        int n = s.length();
        int[][] cnt = new int[26][n + 1];
        for (int j = 1; j <= n; ++j) {
            for (int i = 0; i < 26; ++i) {
                cnt[i][j] = cnt[i][j - 1];
            }
            cnt[s.charAt(j - 1) - 'a'][j]++;
        }
        int m = queries.length;
        int[] ans = new int[m];
        for (int k = 0; k < m; ++k) {
            int l = queries[k][0], r = queries[k][1];
            ans[k] = r - l + 1;
            for (int i = 0; i < 26; ++i) {
                int x = cnt[i][r + 1] - cnt[i][l];
                ans[k] += x * (x - 1) / 2;
            }
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    vector<int> sameEndSubstringCount(string s, vector<vector<int>>& queries) {
        int n = s.size();
        int cnt[26][n + 1];
        memset(cnt, 0, sizeof(cnt));
        for (int j = 1; j <= n; ++j) {
            for (int i = 0; i < 26; ++i) {
                cnt[i][j] = cnt[i][j - 1];
            }
            cnt[s[j - 1] - 'a'][j]++;
        }
        vector<int> ans;
        for (auto& q : queries) {
            int l = q[0], r = q[1];
            ans.push_back(r - l + 1);
            for (int i = 0; i < 26; ++i) {
                int x = cnt[i][r + 1] - cnt[i][l];
                ans.back() += x * (x - 1) / 2;
            }
        }
        return ans;
    }
};
```

```go
func sameEndSubstringCount(s string, queries [][]int) []int {
	n := len(s)
	cnt := make([][]int, 26)
	for i := 0; i < 26; i++ {
		cnt[i] = make([]int, n+1)
	}

	for j := 1; j <= n; j++ {
		for i := 0; i < 26; i++ {
			cnt[i][j] = cnt[i][j-1]
		}
		cnt[s[j-1]-'a'][j]++
	}

	var ans []int
	for _, q := range queries {
		l, r := q[0], q[1]
		ans = append(ans, r-l+1)
		for i := 0; i < 26; i++ {
			x := cnt[i][r+1] - cnt[i][l]
			ans[len(ans)-1] += x * (x - 1) / 2
		}
	}

	return ans
}
```

```ts
function sameEndSubstringCount(s: string, queries: number[][]): number[] {
    const n: number = s.length;
    const cnt: number[][] = Array.from({ length: 26 }, () => Array(n + 1).fill(0));
    for (let j = 1; j <= n; j++) {
        for (let i = 0; i < 26; i++) {
            cnt[i][j] = cnt[i][j - 1];
        }
        cnt[s.charCodeAt(j - 1) - 'a'.charCodeAt(0)][j]++;
    }
    const ans: number[] = [];
    for (const [l, r] of queries) {
        ans.push(r - l + 1);
        for (let i = 0; i < 26; i++) {
            const x: number = cnt[i][r + 1] - cnt[i][l];
            ans[ans.length - 1] += (x * (x - 1)) / 2;
        }
    }
    return ans;
}
```

```rust
impl Solution {
    pub fn same_end_substring_count(s: String, queries: Vec<Vec<i32>>) -> Vec<i32> {
        let n = s.len();
        let mut cnt: Vec<Vec<i32>> = vec![vec![0; n + 1]; 26];
        for j in 1..=n {
            for i in 0..26 {
                cnt[i][j] = cnt[i][j - 1];
            }
            cnt[(s.as_bytes()[j - 1] as usize) - (b'a' as usize)][j] += 1;
        }
        let mut ans: Vec<i32> = Vec::new();
        for q in queries.iter() {
            let l = q[0] as usize;
            let r = q[1] as usize;
            let mut t = (r - l + 1) as i32;
            for i in 0..26 {
                let x = cnt[i][r + 1] - cnt[i][l];
                t += (x * (x - 1)) / 2;
            }
            ans.push(t);
        }
        ans
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
