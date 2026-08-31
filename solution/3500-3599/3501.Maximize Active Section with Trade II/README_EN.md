---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3500-3599/3501.Maximize%20Active%20Section%20with%20Trade%20II/README_EN.md
rating: 2940
source: Biweekly Contest 153 Q4
tags:
    - Segment Tree
    - Array
    - String
    - Binary Search
---

<!-- problem:start -->

# [3501. Maximize Active Section with Trade II](https://leetcode.com/problems/maximize-active-section-with-trade-ii)

[中文文档](/solution/3500-3599/3501.Maximize%20Active%20Section%20with%20Trade%20II/README.md)

## Description

<!-- description:start -->

<p>You are given a binary string <code>s</code> of length <code>n</code>, where:</p>

<ul>
	<li><code>&#39;1&#39;</code> represents an <strong>active</strong> section.</li>
	<li><code>&#39;0&#39;</code> represents an <strong>inactive</strong> section.</li>
</ul>

<p>You can perform <strong>at most one trade</strong> to maximize the number of active sections in <code>s</code>. In a trade, you:</p>

<ul>
	<li>Convert a contiguous block of <code>&#39;1&#39;</code>s that is surrounded by <code>&#39;0&#39;</code>s to all <code>&#39;0&#39;</code>s.</li>
	<li>Afterward, convert a contiguous block of <code>&#39;0&#39;</code>s that is surrounded by <code>&#39;1&#39;</code>s to all <code>&#39;1&#39;</code>s.</li>
</ul>

<p>Additionally, you are given a <strong>2D array</strong> <code>queries</code>, where <code>queries[i] = [l<sub>i</sub>, r<sub>i</sub>]</code> represents a <span data-keyword="substring-nonempty">substring</span> <code>s[l<sub>i</sub>...r<sub>i</sub>]</code>.</p>

<p>For each query, determine the <strong>maximum</strong> possible number of active sections in <code>s</code> after making the optimal trade on the substring <code>s[l<sub>i</sub>...r<sub>i</sub>]</code>.</p>

<p>Return an array <code>answer</code>, where <code>answer[i]</code> is the result for <code>queries[i]</code>.</p>

<p><strong>Note</strong></p>

<ul>
	<li>For each query, treat <code>s[l<sub>i</sub>...r<sub>i</sub>]</code> as if it is <strong>augmented</strong> with a <code>&#39;1&#39;</code> at both ends, forming <code>t = &#39;1&#39; + s[l<sub>i</sub>...r<sub>i</sub>] + &#39;1&#39;</code>. The augmented <code>&#39;1&#39;</code>s <strong>do not</strong> contribute to the final count.</li>
	<li>The queries are independent of each other.</li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;01&quot;, queries = [[0,1]]</span></p>

<p><strong>Output:</strong> <span class="example-io">[1]</span></p>

<p><strong>Explanation:</strong></p>

<p>Because there is no block of <code>&#39;1&#39;</code>s surrounded by <code>&#39;0&#39;</code>s, no valid trade is possible. The maximum number of active sections is 1.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;0100&quot;, queries = [[0,3],[0,2],[1,3],[2,3]]</span></p>

<p><strong>Output:</strong> <span class="example-io">[4,3,1,1]</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>
	<p>Query <code>[0, 3]</code> &rarr; Substring <code>&quot;0100&quot;</code> &rarr; Augmented to <code>&quot;101001&quot;</code><br />
	Choose <code>&quot;0100&quot;</code>, convert <code>&quot;0100&quot;</code> &rarr; <code>&quot;0000&quot;</code> &rarr; <code>&quot;1111&quot;</code>.<br />
	The final string without augmentation is <code>&quot;1111&quot;</code>. The maximum number of active sections is 4.</p>
	</li>
	<li>
	<p>Query <code>[0, 2]</code> &rarr; Substring <code>&quot;010&quot;</code> &rarr; Augmented to <code>&quot;10101&quot;</code><br />
	Choose <code>&quot;010&quot;</code>, convert <code>&quot;010&quot;</code> &rarr; <code>&quot;000&quot;</code> &rarr; <code>&quot;111&quot;</code>.<br />
	The final string without augmentation is <code>&quot;1110&quot;</code>. The maximum number of active sections is 3.</p>
	</li>
	<li>
	<p>Query <code>[1, 3]</code> &rarr; Substring <code>&quot;100&quot;</code> &rarr; Augmented to <code>&quot;11001&quot;</code><br />
	Because there is no block of <code>&#39;1&#39;</code>s surrounded by <code>&#39;0&#39;</code>s, no valid trade is possible. The maximum number of active sections is 1.</p>
	</li>
	<li>
	<p>Query <code>[2, 3]</code> &rarr; Substring <code>&quot;00&quot;</code> &rarr; Augmented to <code>&quot;1001&quot;</code><br />
	Because there is no block of <code>&#39;1&#39;</code>s surrounded by <code>&#39;0&#39;</code>s, no valid trade is possible. The maximum number of active sections is 1.</p>
	</li>
</ul>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;1000100&quot;, queries = [[1,5],[0,6],[0,4]]</span></p>

<p><strong>Output:</strong> <span class="example-io">[6,7,2]</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li data-end="383" data-start="217">
	<p data-end="383" data-start="219">Query <code>[1, 5]</code> &rarr; Substring <code data-end="255" data-start="246">&quot;00010&quot;</code> &rarr; Augmented to <code data-end="282" data-start="271">&quot;1000101&quot;</code><br data-end="285" data-start="282" />
	Choose <code data-end="303" data-start="294">&quot;00010&quot;</code>, convert <code data-end="322" data-start="313">&quot;00010&quot;</code> &rarr; <code data-end="322" data-start="313">&quot;00000&quot;</code> &rarr; <code data-end="334" data-start="325">&quot;11111&quot;</code>.<br />
	The final string without augmentation is <code data-end="404" data-start="396">&quot;1111110&quot;</code>. The maximum number of active sections is 6.</p>
	</li>
	<li data-end="561" data-start="385">
	<p data-end="561" data-start="387">Query <code>[0, 6]</code> &rarr; Substring <code data-end="425" data-start="414">&quot;1000100&quot;</code> &rarr; Augmented to <code data-end="454" data-start="441">&quot;110001001&quot;</code><br data-end="457" data-start="454" />
	Choose <code data-end="477" data-start="466">&quot;000100&quot;</code>, convert <code data-end="498" data-start="487">&quot;000100&quot;</code> &rarr; <code data-end="498" data-start="487">&quot;000000&quot;</code> &rarr; <code data-end="512" data-start="501">&quot;111111&quot;</code>.<br />
	The final string without augmentation is <code data-end="404" data-start="396">&quot;1111111&quot;</code>. The maximum number of active sections is 7.</p>
	</li>
	<li data-end="741" data-start="563">
	<p data-end="741" data-start="565">Query <code>[0, 4]</code> &rarr; Substring <code data-end="601" data-start="592">&quot;10001&quot;</code> &rarr; Augmented to <code data-end="627" data-start="617">&quot;1100011&quot;</code><br data-end="630" data-start="627" />
	Because there is no block of <code>&#39;1&#39;</code>s surrounded by <code>&#39;0&#39;</code>s, no valid trade is possible. The maximum number of active sections is 2.</p>
	</li>
</ul>
</div>

<p><strong class="example">Example 4:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;01010&quot;, queries = [[0,3],[1,4],[1,3]]</span></p>

<p><strong>Output:</strong> <span class="example-io">[4,4,2]</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>
	<p>Query <code>[0, 3]</code> &rarr; Substring <code>&quot;0101&quot;</code> &rarr; Augmented to <code>&quot;101011&quot;</code><br />
	Choose <code>&quot;010&quot;</code>, convert <code>&quot;010&quot;</code> &rarr; <code>&quot;000&quot;</code> &rarr; <code>&quot;111&quot;</code>.<br />
	The final string without augmentation is <code>&quot;11110&quot;</code>. The maximum number of active sections is 4.</p>
	</li>
	<li>
	<p>Query <code>[1, 4]</code> &rarr; Substring <code>&quot;1010&quot;</code> &rarr; Augmented to <code>&quot;110101&quot;</code><br />
	Choose <code>&quot;010&quot;</code>, convert <code>&quot;010&quot;</code> &rarr; <code>&quot;000&quot;</code> &rarr; <code>&quot;111&quot;</code>.<br />
	The final string without augmentation is <code>&quot;01111&quot;</code>. The maximum number of active sections is 4.</p>
	</li>
	<li>
	<p>Query <code>[1, 3]</code> &rarr; Substring <code>&quot;101&quot;</code> &rarr; Augmented to <code>&quot;11011&quot;</code><br />
	Because there is no block of <code>&#39;1&#39;</code>s surrounded by <code>&#39;0&#39;</code>s, no valid trade is possible. The maximum number of active sections is 2.</p>
	</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n == s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= queries.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s[i]</code> is either <code>&#39;0&#39;</code> or <code>&#39;1&#39;</code>.</li>
	<li><code>queries[i] = [l<sub>i</sub>, r<sub>i</sub>]</code></li>
	<li><code>0 &lt;= l<sub>i</sub> &lt;= r<sub>i</sub> &lt; n</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Sparse Table

A valid trade picks two consecutive `'0'` runs separated by `'1'`s, flips the `'1'`s to `'0'`s, then flips the merged `'0'` run back to `'1'`s. The net gain is the sum of the two `'0'` run lengths, and the original number of `'1'`s stays the same. Thus the answer for each query is the total number of `'1'`s in $s$, plus the maximum gain obtainable inside that query range.

Query $[l, r]$ may only operate on $s[l..r]$, treated as $t = \texttt{'1'} + s[l..r] + \texttt{'1'}$. Therefore:

- Any pair of adjacent `'0'` runs completely inside the range is a candidate, with gain equal to the sum of their lengths;
- If $s[l]$ or $s[r]$ lies in the middle of a `'0'` run, the suffix / prefix of that run inside the query range can also be paired.

We record every `'0'` run as $(\textit{start}, \textit{len})$ and build a sparse table on the sums of adjacent runs. For each query:

1. Query the sparse table for the maximum adjacent-run sum fully inside the range;
2. Also consider the leftover left run with the next run, the leftover right run with the previous run, and the special case where the two leftovers enclose a single `'1'` run.

Add the best gain to the global `'1'` count. If no trade is possible, the gain is $0$.

Time complexity is $O(n \log n + q)$, and space complexity is $O(n \log n)$, where $n$ is the length of $s$ and $q$ is the number of queries.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxActiveSectionsAfterTrade(
        self, s: str, queries: List[List[int]]
    ) -> List[int]:
        n = len(s)
        active = s.count('1')
        if '0' not in s:
            return [active] * len(queries)

        zeros = []
        idx = [0] * n
        for i in range(n):
            if s[i] == '0':
                if i and s[i - 1] == '0':
                    zeros[-1][1] += 1
                else:
                    zeros.append([i, 1])
            idx[i] = len(zeros) - 1

        m = len(zeros) - 1
        K = m.bit_length() if m else 0
        st = [[0] * max(m, 0) for _ in range(max(K, 1))]
        for i in range(m):
            st[0][i] = zeros[i][1] + zeros[i + 1][1]
        for k in range(1, K):
            for i in range(m - (1 << k) + 1):
                st[k][i] = max(st[k - 1][i], st[k - 1][i + (1 << (k - 1))])

        def query(l: int, r: int) -> int:
            if l > r or m <= 0:
                return 0
            k = (r - l + 1).bit_length() - 1
            return max(st[k][l], st[k][r - (1 << k) + 1])

        ans = []
        for L, R in queries:
            iL, iR = idx[L], idx[R]
            cntL = -1 if iL < 0 else zeros[iL][1] - (L - zeros[iL][0])
            cntR = -1 if iR < 0 else R - zeros[iR][0] + 1
            start = iL + 1
            end = iR - (s[R] == '0')
            best = active
            if start < end:
                best = max(best, active + query(start, end - 1))
            if s[L] == '0' and s[R] == '0' and iL + 1 == iR:
                best = max(best, active + cntL + cntR)
            if s[L] == '0' and iL + 1 < iR + (s[R] == '1'):
                best = max(best, active + cntL + zeros[iL + 1][1])
            if s[R] == '0' and iL < iR - 1:
                best = max(best, active + cntR + zeros[iR - 1][1])
            ans.append(best)
        return ans
```

#### Java

```java
class Solution {
    public List<Integer> maxActiveSectionsAfterTrade(String s, int[][] queries) {
        int n = s.length();
        int active = 0;
        for (int i = 0; i < n; ++i) {
            if (s.charAt(i) == '1') {
                ++active;
            }
        }
        List<Integer> ans = new ArrayList<>();
        if (s.indexOf('0') < 0) {
            for (int i = 0; i < queries.length; ++i) {
                ans.add(active);
            }
            return ans;
        }

        int[][] zeros = new int[n][2];
        int z = 0;
        int[] idx = new int[n];
        for (int i = 0; i < n; ++i) {
            if (s.charAt(i) == '0') {
                if (i > 0 && s.charAt(i - 1) == '0') {
                    ++zeros[z - 1][1];
                } else {
                    zeros[z][0] = i;
                    zeros[z++][1] = 1;
                }
            }
            idx[i] = z - 1;
        }

        int m = z - 1;
        int K = m > 0 ? 32 - Integer.numberOfLeadingZeros(m) : 0;
        int[][] st = new int[Math.max(K, 1)][Math.max(m, 0)];
        for (int i = 0; i < m; ++i) {
            st[0][i] = zeros[i][1] + zeros[i + 1][1];
        }
        for (int k = 1; k < K; ++k) {
            for (int i = 0; i + (1 << k) <= m; ++i) {
                st[k][i] = Math.max(st[k - 1][i], st[k - 1][i + (1 << (k - 1))]);
            }
        }

        for (int[] q : queries) {
            int L = q[0], R = q[1];
            int iL = idx[L], iR = idx[R];
            int cntL = iL < 0 ? -1 : zeros[iL][1] - (L - zeros[iL][0]);
            int cntR = iR < 0 ? -1 : R - zeros[iR][0] + 1;
            int start = iL + 1;
            int end = iR - (s.charAt(R) == '0' ? 1 : 0);
            int best = active;
            if (start < end) {
                best = Math.max(best, active + query(st, m, start, end - 1));
            }
            if (s.charAt(L) == '0' && s.charAt(R) == '0' && iL + 1 == iR) {
                best = Math.max(best, active + cntL + cntR);
            }
            if (s.charAt(L) == '0' && iL + 1 < iR + (s.charAt(R) == '1' ? 1 : 0)) {
                best = Math.max(best, active + cntL + zeros[iL + 1][1]);
            }
            if (s.charAt(R) == '0' && iL < iR - 1) {
                best = Math.max(best, active + cntR + zeros[iR - 1][1]);
            }
            ans.add(best);
        }
        return ans;
    }

    private int query(int[][] st, int m, int l, int r) {
        if (l > r || m <= 0) {
            return 0;
        }
        int k = 31 - Integer.numberOfLeadingZeros(r - l + 1);
        return Math.max(st[k][l], st[k][r - (1 << k) + 1]);
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<int> maxActiveSectionsAfterTrade(string s, vector<vector<int>>& queries) {
        int n = s.size();
        int active = count(s.begin(), s.end(), '1');
        if (s.find('0') == string::npos) {
            return vector<int>(queries.size(), active);
        }

        vector<pair<int, int>> zeros;
        vector<int> idx(n);
        for (int i = 0; i < n; ++i) {
            if (s[i] == '0') {
                if (i && s[i - 1] == '0') {
                    ++zeros.back().second;
                } else {
                    zeros.emplace_back(i, 1);
                }
            }
            idx[i] = (int) zeros.size() - 1;
        }

        int m = (int) zeros.size() - 1;
        int K = m ? 32 - __builtin_clz(m) : 0;
        vector<vector<int>> st(max(K, 1), vector<int>(max(m, 0)));
        for (int i = 0; i < m; ++i) {
            st[0][i] = zeros[i].second + zeros[i + 1].second;
        }
        for (int k = 1; k < K; ++k) {
            for (int i = 0; i + (1 << k) <= m; ++i) {
                st[k][i] = max(st[k - 1][i], st[k - 1][i + (1 << (k - 1))]);
            }
        }

        auto query = [&](int l, int r) {
            if (l > r || m <= 0) {
                return 0;
            }
            int k = 31 - __builtin_clz(r - l + 1);
            return max(st[k][l], st[k][r - (1 << k) + 1]);
        };

        vector<int> ans;
        ans.reserve(queries.size());
        for (auto& q : queries) {
            int L = q[0], R = q[1];
            int iL = idx[L], iR = idx[R];
            int cntL = iL < 0 ? -1 : zeros[iL].second - (L - zeros[iL].first);
            int cntR = iR < 0 ? -1 : R - zeros[iR].first + 1;
            int start = iL + 1;
            int end = iR - (s[R] == '0');
            int best = active;
            if (start < end) {
                best = max(best, active + query(start, end - 1));
            }
            if (s[L] == '0' && s[R] == '0' && iL + 1 == iR) {
                best = max(best, active + cntL + cntR);
            }
            if (s[L] == '0' && iL + 1 < iR + (s[R] == '1')) {
                best = max(best, active + cntL + zeros[iL + 1].second);
            }
            if (s[R] == '0' && iL < iR - 1) {
                best = max(best, active + cntR + zeros[iR - 1].second);
            }
            ans.push_back(best);
        }
        return ans;
    }
};
```

#### Go

```go
func maxActiveSectionsAfterTrade(s string, queries [][]int) []int {
	n := len(s)
	active := 0
	for i := 0; i < n; i++ {
		if s[i] == '1' {
			active++
		}
	}
	if strings.IndexByte(s, '0') < 0 {
		ans := make([]int, len(queries))
		for i := range ans {
			ans[i] = active
		}
		return ans
	}

	zeros := make([][2]int, 0, n)
	idx := make([]int, n)
	for i := 0; i < n; i++ {
		if s[i] == '0' {
			if i > 0 && s[i-1] == '0' {
				zeros[len(zeros)-1][1]++
			} else {
				zeros = append(zeros, [2]int{i, 1})
			}
		}
		idx[i] = len(zeros) - 1
	}

	m := len(zeros) - 1
	K := 0
	if m > 0 {
		K = bits.Len(uint(m))
	}
	st := make([][]int, max(K, 1))
	for k := range st {
		st[k] = make([]int, max(m, 0))
	}
	for i := 0; i < m; i++ {
		st[0][i] = zeros[i][1] + zeros[i+1][1]
	}
	for k := 1; k < K; k++ {
		for i := 0; i+(1<<k) <= m; i++ {
			st[k][i] = max(st[k-1][i], st[k-1][i+(1<<(k-1))])
		}
	}

	query := func(l, r int) int {
		if l > r || m <= 0 {
			return 0
		}
		k := bits.Len(uint(r-l+1)) - 1
		return max(st[k][l], st[k][r-(1<<k)+1])
	}

	ans := make([]int, 0, len(queries))
	for _, q := range queries {
		L, R := q[0], q[1]
		iL, iR := idx[L], idx[R]
		cntL, cntR := -1, -1
		if iL >= 0 {
			cntL = zeros[iL][1] - (L - zeros[iL][0])
		}
		if iR >= 0 {
			cntR = R - zeros[iR][0] + 1
		}
		start := iL + 1
		end := iR
		if s[R] == '0' {
			end--
		}
		best := active
		if start < end {
			best = max(best, active+query(start, end-1))
		}
		if s[L] == '0' && s[R] == '0' && iL+1 == iR {
			best = max(best, active+cntL+cntR)
		}
		add := 0
		if s[R] == '1' {
			add = 1
		}
		if s[L] == '0' && iL+1 < iR+add {
			best = max(best, active+cntL+zeros[iL+1][1])
		}
		if s[R] == '0' && iL < iR-1 {
			best = max(best, active+cntR+zeros[iR-1][1])
		}
		ans = append(ans, best)
	}
	return ans
}
```

#### Rust

```rust
impl Solution {
    pub fn max_active_sections_after_trade(s: String, queries: Vec<Vec<i32>>) -> Vec<i32> {
        let bytes = s.as_bytes();
        let length = bytes.len();
        let total_ones = bytes.iter().filter(|byte| **byte == b'1').count() as i32;
        if !bytes.contains(&b'0') {
            return vec![total_ones; queries.len()];
        }
        let mut zero_blocks: Vec<(usize, usize)> = Vec::new();
        let mut zero_block_at_position = Vec::with_capacity(length);
        for index in 0..length {
            if bytes[index] == b'0' {
                if index > 0 && bytes[index - 1] == b'0' {
                    zero_blocks.last_mut().unwrap().1 += 1;
                } else {
                    zero_blocks.push((index, 1usize));
                }
            }
            zero_block_at_position.push(zero_blocks.len() as isize - 1);
        }
        let zero_block_count = zero_blocks.len();
        let adjacent_pair_count = zero_block_count.saturating_sub(1);
        let sparse_level_count = if adjacent_pair_count == 0 {
            0
        } else {
            usize::BITS as usize - adjacent_pair_count.leading_zeros() as usize
        };
        let mut sparse_table = vec![0; adjacent_pair_count * sparse_level_count];
        for pair_index in 0..adjacent_pair_count {
            sparse_table[pair_index] =
                (zero_blocks[pair_index].1 + zero_blocks[pair_index + 1].1) as i32;
        }
        for level in 1..sparse_level_count {
            let half_span = 1usize << (level - 1);
            let span = 1usize << level;
            for start in 0..=adjacent_pair_count - span {
                sparse_table[level * adjacent_pair_count + start] = sparse_table
                    [(level - 1) * adjacent_pair_count + start]
                    .max(sparse_table[(level - 1) * adjacent_pair_count + start + half_span]);
            }
        }
        let max_pair_sum = |left_pair: usize, right_pair: usize| -> i32 {
            let right_pair = right_pair.min(adjacent_pair_count - 1);
            if left_pair > right_pair {
                return 0;
            }
            let level =
                usize::BITS as usize - (right_pair - left_pair + 1).leading_zeros() as usize - 1;
            let span = 1usize << level;
            sparse_table[level * adjacent_pair_count + left_pair]
                .max(sparse_table[level * adjacent_pair_count + right_pair - span + 1])
        };
        queries
            .into_iter()
            .map(|query| {
                let left = query[0] as usize;
                let right = query[1] as usize;
                let left_block_index = zero_block_at_position[left];
                let right_block_index = zero_block_at_position[right];
                let left_zero_count = if left_block_index == -1 {
                    -1
                } else {
                    let block_index = left_block_index as usize;
                    zero_blocks[block_index].1 as i32 - (left - zero_blocks[block_index].0) as i32
                };
                let right_zero_count = if right_block_index == -1 {
                    -1
                } else {
                    let block_index = right_block_index as usize;
                    (right - zero_blocks[block_index].0 + 1) as i32
                };
                let first_internal_pair = left_block_index + 1;
                let last_internal_pair = (if bytes[right] == b'1' {
                    right_block_index
                } else {
                    right_block_index - 1
                }) - 1;
                let last_full_zero_block = if bytes[right] == b'1' {
                    right_block_index
                } else {
                    right_block_index - 1
                };
                let mut best_total = total_ones;
                if bytes[left] == b'0'
                    && bytes[right] == b'0'
                    && left_block_index + 1 == right_block_index
                {
                    best_total = best_total.max(total_ones + left_zero_count + right_zero_count);
                } else if first_internal_pair <= last_internal_pair {
                    best_total = best_total.max(
                        total_ones
                            + max_pair_sum(
                                first_internal_pair as usize,
                                last_internal_pair as usize,
                            ),
                    );
                }
                if bytes[left] == b'0' && left_block_index + 1 <= last_full_zero_block {
                    best_total = best_total.max(
                        total_ones
                            + left_zero_count
                            + zero_blocks[(left_block_index + 1) as usize].1 as i32,
                    );
                }
                if bytes[right] == b'0' && left_block_index < right_block_index - 1 {
                    best_total = best_total.max(
                        total_ones
                            + right_zero_count
                            + zero_blocks[(right_block_index - 1) as usize].1 as i32,
                    );
                }
                best_total
            })
            .collect()
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
