---
comments: true
difficulty: 困难
rating: 2362
source: 第 198 场周赛 Q3
tags:
    - 贪心
    - 哈希表
    - 字符串
    - 排序
---

<!-- problem:start -->

# [1520. 最多的不重叠子字符串](https://leetcode.cn/problems/maximum-number-of-non-overlapping-substrings)

[English Version](/solution/1500-1599/1520.Maximum%20Number%20of%20Non-Overlapping%20Substrings/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个只包含小写字母的字符串&nbsp;<code>s</code>&nbsp;，你需要找到 <code>s</code>&nbsp;中最多数目的非空子字符串，满足如下条件：</p>

<ol>
	<li>这些字符串之间互不重叠，也就是说对于任意两个子字符串&nbsp;<code>s[i..j]</code> 和&nbsp;<code>s[x..y]</code>&nbsp;，要么&nbsp;<code>j &lt; x</code>&nbsp;要么&nbsp;<code>i &gt; y</code>&nbsp;。</li>
	<li>如果一个子字符串包含字符&nbsp;<code>char</code> ，那么&nbsp;<code>s</code>&nbsp;中所有&nbsp;<code>char</code> 字符都应该在这个子字符串中。</li>
</ol>

<p>请你找到满足上述条件的最多子字符串数目。如果有多个解法有相同的子字符串数目，请返回这些子字符串总长度最小的一个解。可以证明最小总长度解是唯一的。</p>

<p>请注意，你可以以 <strong>任意</strong>&nbsp;顺序返回最优解的子字符串。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>s = "adefaddaccc"
<strong>输出：</strong>["e","f","ccc"]
<strong>解释：</strong>下面为所有满足第二个条件的子字符串：
[
&nbsp; "adefaddaccc"
&nbsp; "adefadda",
&nbsp; "ef",
&nbsp; "e",
  "f",
&nbsp; "ccc",
]
如果我们选择第一个字符串，那么我们无法再选择其他任何字符串，所以答案为 1 。如果我们选择 "adefadda" ，剩下子字符串中我们只可以选择 "ccc" ，它是唯一不重叠的子字符串，所以答案为 2 。同时我们可以发现，选择 "ef" 不是最优的，因为它可以被拆分成 2 个子字符串。所以最优解是选择 ["e","f","ccc"] ，答案为 3 。不存在别的相同数目子字符串解。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>s = "abbaccd"
<strong>输出：</strong>["d","bb","cc"]
<strong>解释：</strong>注意到解 ["d","abba","cc"] 答案也为 3 ，但它不是最优解，因为它的总长度更长。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10^5</code></li>
	<li><code>s</code>&nbsp;只包含小写英文字母。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：贪心

<!-- thinking:start -->

> **思考**
>
> 要选出尽量多段互不重叠的子串，且每段必须覆盖其中每个字符在整个字符串里的全部出现。$n\le 10^5$，不能枚举全部子串再验证。
>
> 每个字符有确定的首次与末次出现。从某一左端出发，不断把区间扩大到内部字符的最右出现，得到一条不可再缩的合法段。所有这样的极小段构成区间图，按右端点贪心选取互不重叠者，即可同时达到数量最多且总长度最短。

<!-- thinking:end -->

我们先用数组或哈希表 $\textit{first}$ 和 $\textit{last}$ 记录每个字母在 $s$ 中首次和末次出现的下标。

接下来枚举每一个出现过的字母 $c$，以 $\textit{first}[c]$ 作为候选左端点 $l$，以 $\textit{last}[c]$ 作为初始右端点 $r$。从 $l$ 向右扫描到 $r$，对扫到的每个字母 $ch$：

- 若 $\textit{first}[ch] < l$，说明该字母在 $l$ 之前还出现过，当前区间无法覆盖其全部出现，这段候选不合法，直接放弃；
- 否则将 $r$ 更新为 $\max(r, \textit{last}[ch])$，把该字母的全部出现纳入区间。

若扫描能够顺利结束，则得到一段合法子串区间 $[l, r]$。

可以证明，这些合法区间之间只可能互不相交或相互包含，不会部分重叠。因此我们将所有合法区间按右端点升序排序，再贪心选取：用变量 $\textit{end}$ 记录上一个已选区间的右端点，初始为 $-1$。从左到右遍历排序后的区间，若当前区间左端点大于 $\textit{end}$，则将其对应子串加入答案，并更新 $\textit{end}$。

按右端点排序保证了每次选择的都是结束最早的区间，从而可以选出数量最多的互不重叠子串；由于相互包含时更短的区间右端点更小，也会被优先选中，因此总长度同时最短。

时间复杂度 $O(n \times |\Sigma|)$，空间复杂度 $O(|\Sigma|)$。其中 $n$ 是字符串 $s$ 的长度，而 $|\Sigma|$ 是字符集的大小。本题中 $|\Sigma| = 26$。

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
