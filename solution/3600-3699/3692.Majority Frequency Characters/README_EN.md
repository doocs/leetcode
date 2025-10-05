---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3600-3699/3692.Majority%20Frequency%20Characters/README_EN.md
rating: 1384
source: Biweekly Contest 166 Q1
---

<!-- problem:start -->

# [3692. Majority Frequency Characters](https://leetcode.com/problems/majority-frequency-characters)

[中文文档](/solution/3600-3699/3692.Majority%20Frequency%20Characters/README.md)

## Description

<!-- description:start -->

<p>You are given a string <code>s</code> consisting of lowercase English letters.</p>

<p>The <strong>frequency group</strong> for a value <code>k</code> is the set of characters that appear exactly <code>k</code> times in s.</p>

<p>The <strong>majority frequency group</strong> is the frequency group that contains the largest number of <strong>distinct</strong> characters.</p>

<p>Return a string containing all characters in the majority frequency group, in <strong>any</strong> order. If two or more frequency groups tie for that largest size, pick the group whose frequency <code>k</code> is <strong>larger</strong>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;aaabbbccdddde&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">&quot;ab&quot;</span></p>

<p><strong>Explanation:</strong></p>

<table style="border: 1px solid black;">
	<thead>
		<tr>
			<th style="border: 1px solid black;">Frequency (k)</th>
			<th style="border: 1px solid black;">Distinct characters in group</th>
			<th style="border: 1px solid black;">Group size</th>
			<th style="border: 1px solid black;">Majority?</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td style="border: 1px solid black;">4</td>
			<td style="border: 1px solid black;">{d}</td>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;">No</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">3</td>
			<td style="border: 1px solid black;">{a, b}</td>
			<td style="border: 1px solid black;">2</td>
			<td style="border: 1px solid black;"><strong>Yes</strong></td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">2</td>
			<td style="border: 1px solid black;">{c}</td>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;">No</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;">{e}</td>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;">No</td>
		</tr>
	</tbody>
</table>

<p>Both characters <code>&#39;a&#39;</code> and <code>&#39;b&#39;</code> share the same frequency 3, they are in the majority frequency group. <code>&quot;ba&quot;</code> is also a valid answer.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;abcd&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">&quot;abcd&quot;</span></p>

<p><strong>Explanation:</strong></p>

<table style="border: 1px solid black;">
	<thead>
		<tr>
			<th style="border: 1px solid black;">Frequency (k)</th>
			<th style="border: 1px solid black;">Distinct characters in group</th>
			<th style="border: 1px solid black;">Group size</th>
			<th style="border: 1px solid black;">Majority?</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;">{a, b, c, d}</td>
			<td style="border: 1px solid black;">4</td>
			<td style="border: 1px solid black;"><strong>Yes</strong></td>
		</tr>
	</tbody>
</table>

<p>All characters share the same frequency 1, they are all in the majority frequency group.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;pfpfgi&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">&quot;fp&quot;</span></p>

<p><strong>Explanation:</strong></p>

<table style="border: 1px solid black;">
	<thead>
		<tr>
			<th style="border: 1px solid black;">Frequency (k)</th>
			<th style="border: 1px solid black;">Distinct characters in group</th>
			<th style="border: 1px solid black;">Group size</th>
			<th style="border: 1px solid black;">Majority?</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td style="border: 1px solid black;">2</td>
			<td style="border: 1px solid black;">{p, f}</td>
			<td style="border: 1px solid black;">2</td>
			<td style="border: 1px solid black;"><strong>Yes</strong></td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;">{g, i}</td>
			<td style="border: 1px solid black;">2</td>
			<td style="border: 1px solid black;">No (tied size, lower frequency)</td>
		</tr>
	</tbody>
</table>

<p>Both characters <code>&#39;p&#39;</code> and <code>&#39;f&#39;</code> share the same frequency 2, they are in the majority frequency group. There is a tie in group size with frequency 1, but we pick the higher frequency: 2.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 100</code></li>
	<li><code>s</code> consists only of lowercase English letters.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution: Hash Table

We first use an array or hash table $\textit{cnt}$ to count the frequency of each character in the string. Then, we use another hash table $\textit{f}$ to group characters with the same frequency $k$ into the same list, i.e., $\textit{f}[k]$ stores all characters with frequency $k$.

Next, we iterate through the hash table $\textit{f}$ to find the frequency group with the maximum group size. If multiple frequency groups have the same maximum size, we choose the one with the larger frequency $k$. Finally, we concatenate all characters in that frequency group into a string and return it.

The time complexity is $O(n)$ and the space complexity is $O(n)$, where $n$ is the length of string $\textit{s}$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def majorityFrequencyGroup(self, s: str) -> str:
        cnt = Counter(s)
        f = defaultdict(list)
        for c, v in cnt.items():
            f[v].append(c)
        mx = mv = 0
        ans = []
        for v, cs in f.items():
            if mx < len(cs) or (mx == len(cs) and mv < v):
                mx = len(cs)
                mv = v
                ans = cs
        return "".join(ans)
```

#### Java

```java
class Solution {
    public String majorityFrequencyGroup(String s) {
        int[] cnt = new int[26];
        for (char c : s.toCharArray()) {
            ++cnt[c - 'a'];
        }
        Map<Integer, StringBuilder> f = new HashMap<>();
        for (int i = 0; i < cnt.length; ++i) {
            if (cnt[i] > 0) {
                f.computeIfAbsent(cnt[i], k -> new StringBuilder()).append((char) ('a' + i));
            }
        }
        int mx = 0;
        int mv = 0;
        String ans = "";
        for (var e : f.entrySet()) {
            int v = e.getKey();
            var cs = e.getValue();
            if (mx < cs.length() || (mx == cs.length() && mv < v)) {
                mx = cs.length();
                mv = v;
                ans = cs.toString();
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
    string majorityFrequencyGroup(string s) {
        vector<int> cnt(26, 0);
        for (char c : s) {
            ++cnt[c - 'a'];
        }

        unordered_map<int, string> f;
        for (int i = 0; i < 26; ++i) {
            if (cnt[i] > 0) {
                f[cnt[i]].push_back('a' + i);
            }
        }

        int mx = 0, mv = 0;
        string ans;
        for (auto& e : f) {
            int v = e.first;
            string& cs = e.second;
            if (mx < (int) cs.size() || (mx == (int) cs.size() && mv < v)) {
                mx = cs.size();
                mv = v;
                ans = cs;
            }
        }
        return ans;
    }
};
```

#### Go

```go
func majorityFrequencyGroup(s string) string {
	cnt := make([]int, 26)
	for _, c := range s {
		cnt[c-'a']++
	}

	f := make(map[int][]byte)
	for i, v := range cnt {
		if v > 0 {
			f[v] = append(f[v], byte('a'+i))
		}
	}

	mx, mv := 0, 0
	var ans []byte
	for v, cs := range f {
		if len(cs) > mx || (len(cs) == mx && v > mv) {
			mx = len(cs)
			mv = v
			ans = cs
		}
	}
	return string(ans)
}
```

#### TypeScript

```ts
function majorityFrequencyGroup(s: string): string {
    const cnt: Record<string, number> = {};
    for (const c of s) {
        cnt[c] = (cnt[c] || 0) + 1;
    }
    const f = new Map<number, string[]>();
    for (const [c, v] of Object.entries(cnt)) {
        if (!f.has(v)) {
            f.set(v, []);
        }
        f.get(v)!.push(c);
    }
    let [mx, mv] = [0, 0];
    let ans = '';
    f.forEach((cs, v) => {
        if (mx < cs.length || (mx == cs.length && mv < v)) {
            mx = cs.length;
            mv = v;
            ans = cs.join('');
        }
    });
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
