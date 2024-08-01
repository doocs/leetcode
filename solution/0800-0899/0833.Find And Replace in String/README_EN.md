---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0800-0899/0833.Find%20And%20Replace%20in%20String/README_EN.md
tags:
    - Array
    - Hash Table
    - String
    - Sorting
---

<!-- problem:start -->

# [833. Find And Replace in String](https://leetcode.com/problems/find-and-replace-in-string)

[中文文档](/solution/0800-0899/0833.Find%20And%20Replace%20in%20String/README.md)

## Description

<!-- description:start -->

<p>You are given a <strong>0-indexed</strong> string <code>s</code> that you must perform <code>k</code> replacement operations on. The replacement operations are given as three <strong>0-indexed</strong> parallel arrays, <code>indices</code>, <code>sources</code>, and <code>targets</code>, all of length <code>k</code>.</p>

<p>To complete the <code>i<sup>th</sup></code> replacement operation:</p>

<ol>
	<li>Check if the <strong>substring</strong> <code>sources[i]</code> occurs at index <code>indices[i]</code> in the <strong>original string</strong> <code>s</code>.</li>
	<li>If it does not occur, <strong>do nothing</strong>.</li>
	<li>Otherwise if it does occur, <strong>replace</strong> that substring with <code>targets[i]</code>.</li>
</ol>

<p>For example, if <code>s = &quot;<u>ab</u>cd&quot;</code>, <code>indices[i] = 0</code>, <code>sources[i] = &quot;ab&quot;</code>, and <code>targets[i] = &quot;eee&quot;</code>, then the result of this replacement will be <code>&quot;<u>eee</u>cd&quot;</code>.</p>

<p>All replacement operations must occur <strong>simultaneously</strong>, meaning the replacement operations should not affect the indexing of each other. The testcases will be generated such that the replacements will <strong>not overlap</strong>.</p>

<ul>
	<li>For example, a testcase with <code>s = &quot;abc&quot;</code>, <code>indices = [0, 1]</code>, and <code>sources = [&quot;ab&quot;,&quot;bc&quot;]</code> will not be generated because the <code>&quot;ab&quot;</code> and <code>&quot;bc&quot;</code> replacements overlap.</li>
</ul>

<p>Return <em>the <strong>resulting string</strong> after performing all replacement operations on </em><code>s</code>.</p>

<p>A <strong>substring</strong> is a contiguous sequence of characters in a string.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0800-0899/0833.Find%20And%20Replace%20in%20String/images/833-ex1.png" style="width: 411px; height: 251px;" />
<pre>
<strong>Input:</strong> s = &quot;abcd&quot;, indices = [0, 2], sources = [&quot;a&quot;, &quot;cd&quot;], targets = [&quot;eee&quot;, &quot;ffff&quot;]
<strong>Output:</strong> &quot;eeebffff&quot;
<strong>Explanation:</strong>
&quot;a&quot; occurs at index 0 in s, so we replace it with &quot;eee&quot;.
&quot;cd&quot; occurs at index 2 in s, so we replace it with &quot;ffff&quot;.
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0800-0899/0833.Find%20And%20Replace%20in%20String/images/833-ex2-1.png" style="width: 411px; height: 251px;" />
<pre>
<strong>Input:</strong> s = &quot;abcd&quot;, indices = [0, 2], sources = [&quot;ab&quot;,&quot;ec&quot;], targets = [&quot;eee&quot;,&quot;ffff&quot;]
<strong>Output:</strong> &quot;eeecd&quot;
<strong>Explanation:</strong>
&quot;ab&quot; occurs at index 0 in s, so we replace it with &quot;eee&quot;.
&quot;ec&quot; does not occur at index 2 in s, so we do nothing.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 1000</code></li>
	<li><code>k == indices.length == sources.length == targets.length</code></li>
	<li><code>1 &lt;= k &lt;= 100</code></li>
	<li><code>0 &lt;= indexes[i] &lt; s.length</code></li>
	<li><code>1 &lt;= sources[i].length, targets[i].length &lt;= 50</code></li>
	<li><code>s</code> consists of only lowercase English letters.</li>
	<li><code>sources[i]</code> and <code>targets[i]</code> consist of only lowercase English letters.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def findReplaceString(
        self, s: str, indices: List[int], sources: List[str], targets: List[str]
    ) -> str:
        n = len(s)
        d = [-1] * n
        for k, (i, src) in enumerate(zip(indices, sources)):
            if s.startswith(src, i):
                d[i] = k
        ans = []
        i = 0
        while i < n:
            if ~d[i]:
                ans.append(targets[d[i]])
                i += len(sources[d[i]])
            else:
                ans.append(s[i])
                i += 1
        return "".join(ans)
```

#### Java

```java
class Solution {
    public String findReplaceString(String s, int[] indices, String[] sources, String[] targets) {
        int n = s.length();
        var d = new int[n];
        Arrays.fill(d, -1);
        for (int k = 0; k < indices.length; ++k) {
            int i = indices[k];
            if (s.startsWith(sources[k], i)) {
                d[i] = k;
            }
        }
        var ans = new StringBuilder();
        for (int i = 0; i < n;) {
            if (d[i] >= 0) {
                ans.append(targets[d[i]]);
                i += sources[d[i]].length();
            } else {
                ans.append(s.charAt(i++));
            }
        }
        return ans.toString();
    }
}
```

#### C++

```cpp
class Solution {
public:
    string findReplaceString(string s, vector<int>& indices, vector<string>& sources, vector<string>& targets) {
        int n = s.size();
        vector<int> d(n, -1);
        for (int k = 0; k < indices.size(); ++k) {
            int i = indices[k];
            if (s.compare(i, sources[k].size(), sources[k]) == 0) {
                d[i] = k;
            }
        }
        string ans;
        for (int i = 0; i < n;) {
            if (~d[i]) {
                ans += targets[d[i]];
                i += sources[d[i]].size();
            } else {
                ans += s[i++];
            }
        }
        return ans;
    }
};
```

#### Go

```go
func findReplaceString(s string, indices []int, sources []string, targets []string) string {
	n := len(s)
	d := make([]int, n)
	for k, i := range indices {
		if strings.HasPrefix(s[i:], sources[k]) {
			d[i] = k + 1
		}
	}
	ans := &strings.Builder{}
	for i := 0; i < n; {
		if d[i] > 0 {
			ans.WriteString(targets[d[i]-1])
			i += len(sources[d[i]-1])
		} else {
			ans.WriteByte(s[i])
			i++
		}
	}
	return ans.String()
}
```

#### TypeScript

```ts
function findReplaceString(
    s: string,
    indices: number[],
    sources: string[],
    targets: string[],
): string {
    const n = s.length;
    const d: number[] = Array(n).fill(-1);
    for (let k = 0; k < indices.length; ++k) {
        const [i, src] = [indices[k], sources[k]];
        if (s.startsWith(src, i)) {
            d[i] = k;
        }
    }
    const ans: string[] = [];
    for (let i = 0; i < n; ) {
        if (d[i] >= 0) {
            ans.push(targets[d[i]]);
            i += sources[d[i]].length;
        } else {
            ans.push(s[i++]);
        }
    }
    return ans.join('');
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
