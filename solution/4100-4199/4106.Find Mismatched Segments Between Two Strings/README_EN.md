---
comments: true
difficulty: Medium
---

<!-- problem:start -->

# [4106. Find Mismatched Segments Between Two Strings](https://leetcode.com/problems/find-mismatched-segments-between-two-strings)

[中文文档](/solution/4100-4199/4106.Find%20Mismatched%20Segments%20Between%20Two%20Strings/README.md)

## Description

<!-- description:start -->

<p>You are given two strings <code>s1</code> and <code>s2</code> of the same length.</p>

<p>A <strong>mismatched segment</strong> is a <strong>maximal contiguous</strong> range of indices where the characters of <code>s1</code> and <code>s2</code> differ at every index.</p>

<p>Return a 2D array containing <code>[sub1, sub2]</code> for each mismatched segment, where <code>sub1</code> and <code>sub2</code> are the corresponding <strong><span data-keyword="substring-nonempty">substrings</span></strong> from <code>s1</code> and <code>s2</code>, respectively. Return the segments in <strong>increasing</strong> order of their starting indices. If there are no mismatched segments, return an empty array.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s1 = &quot;earth&quot;, s2 = &quot;empty&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">[[&quot;ar&quot;,&quot;mp&quot;],[&quot;h&quot;,&quot;y&quot;]]</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>The characters at indices 1 and 2 differ, forming the mismatched segment <code>[&quot;ar&quot;,&quot;mp&quot;]</code>.</li>
	<li>The characters at index 4 differ, forming the mismatched segment <code>[&quot;h&quot;,&quot;y&quot;]</code>.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s1 = &quot;action&quot;, s2 = &quot;nation&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">[[&quot;ac&quot;,&quot;na&quot;]]</span></p>

<p><strong>Explanation:</strong></p>

<p>The characters at indices 0 and 1 differ, forming the mismatched segment <code>[&quot;ac&quot;,&quot;na&quot;]</code>. All remaining characters are equal in both strings.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s1 = &quot;hot&quot;, s2 = &quot;hot&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">[]</span></p>

<p><strong>Explanation:</strong></p>

<p>The two strings are identical, so there are no mismatched segments.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s1.length == s2.length &lt;= 100</code></li>
	<li><code>s1</code> and <code>s2</code> consist of lowercase English letters.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Two Pointers

<!-- thinking:start -->

> **Thinking**
>
> $n$ is at most $100$, so extending a run of mismatches from every start also finds every segment. The segments do not overlap, and adjacent starts rescan the same run.
>
> A segment has to be maximal. As soon as one index matches, the current segment ends, and the next segment can begin only to its right.
>
> Matching characters split the two strings into disjoint pieces. One left-to-right pass with two pointers is enough: skip equal characters, then take the whole following run of unequal characters at once.

<!-- thinking:end -->

We scan $\textit{s1}$ and $\textit{s2}$ from left to right, and let $i$ be the current index. If $\textit{s1}[i]=\textit{s2}[i]$, move $i$ one step to the right. Otherwise set $j=i+1$ and keep moving $j$ while $j<n$ and $\textit{s1}[j]\neq\textit{s2}[j]$. Every index in $[i,j)$ differs, and the range is maximal: either $i=0$ or $\textit{s1}[i-1]=\textit{s2}[i-1]$, and either $j=n$ or $\textit{s1}[j]=\textit{s2}[j]$. Append $\textit{s1}[i..j)$ and $\textit{s2}[i..j)$ to the answer, then set $i=j$.

The time complexity is $O(n)$, and the space complexity is $O(1)$, where $n$ is the length of the strings.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def findMismatchedSegments(self, s1: str, s2: str) -> list[list[str]]:
        ans = []
        n = len(s1)
        i = 0
        while i < n:
            if s1[i] == s2[i]:
                i += 1
                continue
            j = i + 1
            while j < n and s1[j] != s2[j]:
                j += 1
            ans.append([s1[i:j], s2[i:j]])
            i = j
        return ans
```

#### Java

```java
class Solution {
    public List<List<String>> findMismatchedSegments(String s1, String s2) {
        int n = s1.length();
        List<List<String>> ans = new ArrayList<>();
        for (int i = 0; i < n;) {
            if (s1.charAt(i) == s2.charAt(i)) {
                ++i;
                continue;
            }
            int j = i + 1;
            while (j < n && s1.charAt(j) != s2.charAt(j)) {
                ++j;
            }
            ans.add(List.of(s1.substring(i, j), s2.substring(i, j)));
            i = j;
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<vector<string>> findMismatchedSegments(string s1, string s2) {
        vector<vector<string>> ans;
        int n = s1.size();
        int i = 0;
        while (i < n) {
            if (s1[i] == s2[i]) {
                i++;
                continue;
            }
            int j = i + 1;
            while (j < n && s1[j] != s2[j]) {
                j++;
            }
            ans.push_back({s1.substr(i, j - i), s2.substr(i, j - i)});
            i = j;
        }
        return ans;
    }
};
```

#### Go

```go
func findMismatchedSegments(s1 string, s2 string) [][]string {
	ans := [][]string{}
	n := len(s1)
	i := 0
	for i < n {
		if s1[i] == s2[i] {
			i++
			continue
		}
		j := i + 1
		for j < n && s1[j] != s2[j] {
			j++
		}
		ans = append(ans, []string{s1[i:j], s2[i:j]})
		i = j
	}
	return ans
}
```

#### TypeScript

```ts
function findMismatchedSegments(s1: string, s2: string): string[][] {
    const ans: string[][] = [];
    const n = s1.length;
    let i = 0;
    while (i < n) {
        if (s1[i] === s2[i]) {
            i++;
            continue;
        }
        let j = i + 1;
        while (j < n && s1[j] !== s2[j]) {
            j++;
        }
        ans.push([s1.slice(i, j), s2.slice(i, j)]);
        i = j;
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
