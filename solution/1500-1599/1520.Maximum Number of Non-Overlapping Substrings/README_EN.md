---
comments: true
difficulty: Hard
rating: 2362
source: Weekly Contest 198 Q3
tags:
    - Greedy
    - Hash Table
    - String
    - Sorting
---

<!-- problem:start -->

# [1520. Maximum Number of Non-Overlapping Substrings](https://leetcode.com/problems/maximum-number-of-non-overlapping-substrings)

[中文文档](/solution/1500-1599/1520.Maximum%20Number%20of%20Non-Overlapping%20Substrings/README.md)

## Description

<!-- description:start -->

<p>Given a string <code>s</code> of lowercase letters, you need to find the maximum number of <strong>non-empty</strong> substrings of <code>s</code> that meet the following conditions:</p>

<ol>
	<li>The substrings do not overlap, that is for any two substrings <code>s[i..j]</code> and <code>s[x..y]</code>, either <code>j &lt; x</code> or <code>i &gt; y</code> is true.</li>
	<li>A substring that contains a certain character <code>c</code> must also contain all occurrences of <code>c</code>.</li>
</ol>

<p>Find <em>the maximum number of substrings that meet the above conditions</em>. If there are multiple solutions with the same number of substrings, <em>return the one with minimum total length. </em>It can be shown that there exists a unique solution of minimum total length.</p>

<p>Notice that you can return the substrings in <strong>any</strong> order.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;adefaddaccc&quot;
<strong>Output:</strong> [&quot;e&quot;,&quot;f&quot;,&quot;ccc&quot;]
<b>Explanation:</b>&nbsp;The following are all the possible substrings that meet the conditions:
[
&nbsp; &quot;adefaddaccc&quot;
&nbsp; &quot;adefadda&quot;,
&nbsp; &quot;ef&quot;,
&nbsp; &quot;e&quot;,
  &quot;f&quot;,
&nbsp; &quot;ccc&quot;,
]
If we choose the first string, we cannot choose anything else and we&#39;d get only 1. If we choose &quot;adefadda&quot;, we are left with &quot;ccc&quot; which is the only one that doesn&#39;t overlap, thus obtaining 2 substrings. Notice also, that it&#39;s not optimal to choose &quot;ef&quot; since it can be split into two. Therefore, the optimal way is to choose [&quot;e&quot;,&quot;f&quot;,&quot;ccc&quot;] which gives us 3 substrings. No other solution of the same number of substrings exist.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;abbaccd&quot;
<strong>Output:</strong> [&quot;d&quot;,&quot;bb&quot;,&quot;cc&quot;]
<b>Explanation: </b>Notice that while the set of substrings [&quot;d&quot;,&quot;abba&quot;,&quot;cc&quot;] also has length 3, it&#39;s considered incorrect since it has larger total length.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s</code> contains only lowercase English letters.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Greedy

<!-- thinking:start -->

> **Thinking**
>
> We want as many non-overlapping substrings as possible, each containing every occurrence of the characters it uses. $n\le 10^5$, so we cannot test every substring.
>
> Each character has a first and last index. Starting from a left boundary, expand the interval to the rightmost occurrence of every character inside it, producing a minimal legal segment. These segments form an interval graph; picking them greedily by right endpoint maximizes the count and minimizes the total length.

<!-- thinking:end -->

We first use arrays or hash tables $\textit{first}$ and $\textit{last}$ to record the first and last occurrence of each letter in $s$.

Next, enumerate every letter $c$ that appears, taking $\textit{first}[c]$ as a candidate left bound $l$ and $\textit{last}[c]$ as the initial right bound $r$. Scan from $l$ to $r$. For each letter $ch$ encountered:

- If $\textit{first}[ch] < l$, this letter also occurs before $l$, so the current interval cannot cover all of its occurrences and is invalid;
- Otherwise update $r = \max(r, \textit{last}[ch])$ to include every occurrence of $ch$.

If the scan finishes successfully, we obtain a valid substring interval $[l, r]$.

These valid intervals can only be disjoint or nested; they never partially overlap. Therefore we sort them by right endpoint in ascending order and pick greedily: let $\textit{end}$ be the right endpoint of the last chosen interval, initially $-1$. Traverse the sorted intervals from left to right; if the current left endpoint is greater than $\textit{end}$, append the corresponding substring to the answer and update $\textit{end}$.

Sorting by right endpoint always chooses the interval that finishes earliest, so we obtain the maximum number of non-overlapping substrings. Nested shorter intervals have smaller right endpoints and are chosen first, so the total length is also minimized.

The time complexity is $O(n \times |\Sigma|)$, and the space complexity is $O(|\Sigma|)$. Here $n$ is the length of $s$, and $|\Sigma|$ is the size of the character set. In this problem, $|\Sigma| = 26$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxNumOfSubstrings(self, s: str) -> List[str]:
        first, last = {}, {}
        for i, c in enumerate(s):
            if c not in first:
                first[c] = i
            last[c] = i
        segs = []
        for l in first.values():
            r, i = last[s[l]], l
            while i <= r:
                if first[s[i]] < l:
                    break
                r = max(r, last[s[i]])
                i += 1
            if i > r:
                segs.append((l, r))
        segs.sort(key=lambda x: x[1])
        ans, end = [], -1
        for l, r in segs:
            if l > end:
                ans.append(s[l : r + 1])
                end = r
        return ans
```

#### Java

```java
class Solution {
    public List<String> maxNumOfSubstrings(String s) {
        int n = s.length();
        int[] first = new int[26];
        int[] last = new int[26];
        Arrays.fill(first, -1);
        for (int i = 0; i < n; ++i) {
            int x = s.charAt(i) - 'a';
            if (first[x] == -1) {
                first[x] = i;
            }
            last[x] = i;
        }
        List<int[]> segs = new ArrayList<>();
        for (int x = 0; x < 26; ++x) {
            if (first[x] == -1) {
                continue;
            }
            int l = first[x], r = last[x];
            int i = l;
            for (; i <= r; ++i) {
                int y = s.charAt(i) - 'a';
                if (first[y] < l) {
                    break;
                }
                r = Math.max(r, last[y]);
            }
            if (i > r) {
                segs.add(new int[] {l, r});
            }
        }
        segs.sort((a, b) -> a[1] - b[1]);
        List<String> ans = new ArrayList<>();
        int end = -1;
        for (int[] e : segs) {
            int l = e[0], r = e[1];
            if (l > end) {
                ans.add(s.substring(l, r + 1));
                end = r;
            }
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<string> maxNumOfSubstrings(string s) {
        int n = s.size();
        int first[26], last[26];
        memset(first, -1, sizeof(first));
        for (int i = 0; i < n; ++i) {
            int x = s[i] - 'a';
            if (first[x] == -1) {
                first[x] = i;
            }
            last[x] = i;
        }
        vector<pair<int, int>> segs;
        for (int x = 0; x < 26; ++x) {
            if (first[x] == -1) {
                continue;
            }
            int l = first[x], r = last[x];
            int i = l;
            for (; i <= r; ++i) {
                int y = s[i] - 'a';
                if (first[y] < l) {
                    break;
                }
                r = max(r, last[y]);
            }
            if (i > r) {
                segs.emplace_back(l, r);
            }
        }
        sort(segs.begin(), segs.end(), [](const auto& a, const auto& b) {
            return a.second < b.second;
        });
        vector<string> ans;
        int end = -1;
        for (auto [l, r] : segs) {
            if (l > end) {
                ans.emplace_back(s.substr(l, r - l + 1));
                end = r;
            }
        }
        return ans;
    }
};
```

#### Go

```go
func maxNumOfSubstrings(s string) (ans []string) {
	first := [26]int{}
	last := [26]int{}
	for i := range first {
		first[i] = -1
	}
	for i := range s {
		x := int(s[i] - 'a')
		if first[x] == -1 {
			first[x] = i
		}
		last[x] = i
	}
	var segs [][2]int
	for x, l := range first {
		if l == -1 {
			continue
		}
		r := last[x]
		i := l
		for ; i <= r; i++ {
			y := int(s[i] - 'a')
			if first[y] < l {
				break
			}
			r = max(r, last[y])
		}
		if i > r {
			segs = append(segs, [2]int{l, r})
		}
	}
	sort.Slice(segs, func(i, j int) bool { return segs[i][1] < segs[j][1] })
	end := -1
	for _, e := range segs {
		l, r := e[0], e[1]
		if l > end {
			ans = append(ans, s[l:r+1])
			end = r
		}
	}
	return
}
```

#### TypeScript

```ts
function maxNumOfSubstrings(s: string): string[] {
    const n = s.length;
    const idx = (c: string) => c.charCodeAt(0) - 97;
    const first = Array(26).fill(-1);
    const last = Array(26).fill(0);
    for (let i = 0; i < n; ++i) {
        const x = idx(s[i]);
        if (first[x] === -1) {
            first[x] = i;
        }
        last[x] = i;
    }
    const segs: number[][] = [];
    for (let x = 0; x < 26; ++x) {
        if (first[x] === -1) {
            continue;
        }
        let l = first[x],
            r = last[x];
        let i = l;
        for (; i <= r; ++i) {
            const y = idx(s[i]);
            if (first[y] < l) {
                break;
            }
            r = Math.max(r, last[y]);
        }
        if (i > r) {
            segs.push([l, r]);
        }
    }
    segs.sort((a, b) => a[1] - b[1]);
    const ans: string[] = [];
    let end = -1;
    for (const [l, r] of segs) {
        if (l > end) {
            ans.push(s.slice(l, r + 1));
            end = r;
        }
    }
    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn max_num_of_substrings(s: String) -> Vec<String> {
        let n = s.len();
        let bs = s.as_bytes();
        let mut first = [-1; 26];
        let mut last = [0; 26];
        for i in 0..n {
            let x = (bs[i] - b'a') as usize;
            if first[x] == -1 {
                first[x] = i as i32;
            }
            last[x] = i as i32;
        }
        let mut segs = vec![];
        for x in 0..26 {
            if first[x] == -1 {
                continue;
            }
            let l = first[x];
            let mut r = last[x];
            let mut i = l;
            while i <= r {
                let y = (bs[i as usize] - b'a') as usize;
                if first[y] < l {
                    break;
                }
                r = r.max(last[y]);
                i += 1;
            }
            if i > r {
                segs.push((l, r));
            }
        }
        segs.sort_by_key(|&(_, r)| r);
        let mut ans = vec![];
        let mut end = -1;
        for (l, r) in segs {
            if l > end {
                ans.push(s[l as usize..=r as usize].to_string());
                end = r;
            }
        }
        ans
    }
}
```

#### C#

```cs
public class Solution {
    public IList<string> MaxNumOfSubstrings(string s) {
        int n = s.Length;
        int[] first = new int[26];
        int[] last = new int[26];
        Array.Fill(first, -1);
        for (int i = 0; i < n; ++i) {
            int x = s[i] - 'a';
            if (first[x] == -1) {
                first[x] = i;
            }
            last[x] = i;
        }
        List<int[]> segs = new List<int[]>();
        for (int x = 0; x < 26; ++x) {
            if (first[x] == -1) {
                continue;
            }
            int l = first[x], r = last[x];
            int i = l;
            for (; i <= r; ++i) {
                int y = s[i] - 'a';
                if (first[y] < l) {
                    break;
                }
                r = Math.Max(r, last[y]);
            }
            if (i > r) {
                segs.Add(new int[] { l, r });
            }
        }
        segs.Sort((a, b) => a[1] - b[1]);
        IList<string> ans = new List<string>();
        int end = -1;
        foreach (var e in segs) {
            int l = e[0], r = e[1];
            if (l > end) {
                ans.Add(s.Substring(l, r - l + 1));
                end = r;
            }
        }
        return ans;
    }
}
```
#### TypeScript

```ts
function maxNumOfSubstrings(s: string): string[] {
    const n = s.length;
    const first = new Array(26).fill(n);
    const last = new Array(26).fill(-1);

    // Step 1: Find first and last occurrence of each character
    for (let i = 0; i < n; i++) {
        const c = s.charCodeAt(i) - 97;
        first[c] = Math.min(first[c], i);
        last[c] = Math.max(last[c], i);
    }

    // Step 2: Build minimal valid intervals
    const intervals: [number, number][] = [];

    for (let c = 0; c < 26; c++) {
        if (last[c] === -1) continue;

        let l = first[c];
        let r = last[c];
        let valid = true;

        let i = l;
        while (i <= r) {
            const ch = s.charCodeAt(i) - 97;
            if (first[ch] < l) {
                // Must extend left past our start — not a minimal valid substring
                valid = false;
                break;
            }
            r = Math.max(r, last[ch]);
            i++;
        }

        if (valid) {
            intervals.push([l, r]);
        }
    }

    // Step 3: Greedy interval scheduling — sort by right endpoint
    intervals.sort((a, b) => a[1] - b[1]);

    const result: string[] = [];
    let prevEnd = -1;

    for (const [l, r] of intervals) {
        if (l > prevEnd) {
            result.push(s.substring(l, r + 1));
            prevEnd = r;
        }
    }

    return result;
}
```
<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
