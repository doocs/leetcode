---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0700-0799/0763.Partition%20Labels/README_EN.md
tags:
    - Greedy
    - Hash Table
    - Two Pointers
    - String
---

<!-- problem:start -->

# [763. Partition Labels](https://leetcode.com/problems/partition-labels)

[中文文档](/solution/0700-0799/0763.Partition%20Labels/README.md)

## Description

<!-- description:start -->

<p>You are given a string <code>s</code>. We want to partition the string into as many parts as possible so that each letter appears in at most one part. For example, the string <code>&quot;ababcc&quot;</code> can be partitioned into <code>[&quot;abab&quot;, &quot;cc&quot;]</code>, but partitions such as <code>[&quot;aba&quot;, &quot;bcc&quot;]</code> or <code>[&quot;ab&quot;, &quot;ab&quot;, &quot;cc&quot;]</code> are invalid.</p>

<p>Note that the partition is done so that after concatenating all the parts in order, the resultant string should be <code>s</code>.</p>

<p>Return <em>a list of integers representing the size of these parts</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;ababcbacadefegdehijhklij&quot;
<strong>Output:</strong> [9,7,8]
<strong>Explanation:</strong>
The partition is &quot;ababcbaca&quot;, &quot;defegde&quot;, &quot;hijhklij&quot;.
This is a partition so that each letter appears in at most one part.
A partition like &quot;ababcbacadefegde&quot;, &quot;hijhklij&quot; is incorrect, because it splits s into less parts.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;eccbbbbdec&quot;
<strong>Output:</strong> [10]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 500</code></li>
	<li><code>s</code> consists of lowercase English letters.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Greedy

We first use an array or hash table $\textit{last}$ to record the last occurrence of each letter in the string $s$.

Next, we use a greedy approach to partition the string into as many segments as possible.

Traverse the string $s$ from left to right, while maintaining the start index $j$ and end index $i$ of the current segment, both initially set to $0$.

For each letter $c$ visited, get the last occurrence position $\textit{last}[c]$. Since the end index of the current segment must not be less than $\textit{last}[c]$, let $\textit{mx} = \max(\textit{mx}, \textit{last}[c])$.

When visiting the index $\textit{mx}$, it means the current segment ends. The index range of the current segment is $[j,.. i]$, and the length is $i - j + 1$. We add this length to the result array. Then set $j = i + 1$ and continue to find the next segment.

Repeat the above process until the string traversal is complete to get the lengths of all segments.

Time complexity is $O(n)$, and space complexity is $O(|\Sigma|)$. Where $n$ is the length of the string $s$, and $|\Sigma|$ is the size of the character set. In this problem, $|\Sigma| = 26$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def partitionLabels(self, s: str) -> List[int]:
        last = {c: i for i, c in enumerate(s)}
        mx = j = 0
        ans = []
        for i, c in enumerate(s):
            mx = max(mx, last[c])
            if mx == i:
                ans.append(i - j + 1)
                j = i + 1
        return ans
```

#### Java

```java
class Solution {
    public List<Integer> partitionLabels(String s) {
        int[] last = new int[26];
        int n = s.length();
        for (int i = 0; i < n; ++i) {
            last[s.charAt(i) - 'a'] = i;
        }
        List<Integer> ans = new ArrayList<>();
        int mx = 0, j = 0;
        for (int i = 0; i < n; ++i) {
            mx = Math.max(mx, last[s.charAt(i) - 'a']);
            if (mx == i) {
                ans.add(i - j + 1);
                j = i + 1;
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
    vector<int> partitionLabels(string s) {
        int last[26] = {0};
        int n = s.size();
        for (int i = 0; i < n; ++i) {
            last[s[i] - 'a'] = i;
        }
        vector<int> ans;
        int mx = 0, j = 0;
        for (int i = 0; i < n; ++i) {
            mx = max(mx, last[s[i] - 'a']);
            if (mx == i) {
                ans.push_back(i - j + 1);
                j = i + 1;
            }
        }
        return ans;
    }
};
```

#### Go

```go
func partitionLabels(s string) (ans []int) {
	last := [26]int{}
	for i, c := range s {
		last[c-'a'] = i
	}
	var mx, j int
	for i, c := range s {
		mx = max(mx, last[c-'a'])
		if mx == i {
			ans = append(ans, i-j+1)
			j = i + 1
		}
	}
	return
}
```

#### TypeScript

```ts
function partitionLabels(s: string): number[] {
    const last: number[] = Array(26).fill(0);
    const idx = (c: string) => c.charCodeAt(0) - 'a'.charCodeAt(0);
    const n = s.length;
    for (let i = 0; i < n; ++i) {
        last[idx(s[i])] = i;
    }
    const ans: number[] = [];
    for (let i = 0, j = 0, mx = 0; i < n; ++i) {
        mx = Math.max(mx, last[idx(s[i])]);
        if (mx === i) {
            ans.push(i - j + 1);
            j = i + 1;
        }
    }
    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn partition_labels(s: String) -> Vec<i32> {
        let n = s.len();
        let bytes = s.as_bytes();
        let mut last = [0; 26];
        for i in 0..n {
            last[(bytes[i] - b'a') as usize] = i;
        }
        let mut ans = vec![];
        let mut j = 0;
        let mut mx = 0;
        for i in 0..n {
            mx = mx.max(last[(bytes[i] - b'a') as usize]);
            if mx == i {
                ans.push((i - j + 1) as i32);
                j = i + 1;
            }
        }
        ans
    }
}
```

#### JavaScript

```js
/**
 * @param {string} s
 * @return {number[]}
 */
var partitionLabels = function (s) {
    const last = new Array(26).fill(0);
    const idx = c => c.charCodeAt() - 'a'.charCodeAt();
    const n = s.length;
    for (let i = 0; i < n; ++i) {
        last[idx(s[i])] = i;
    }
    const ans = [];
    for (let i = 0, j = 0, mx = 0; i < n; ++i) {
        mx = Math.max(mx, last[idx(s[i])]);
        if (mx === i) {
            ans.push(i - j + 1);
            j = i + 1;
        }
    }
    return ans;
};
```

#### C#

```cs
public class Solution {
    public IList<int> PartitionLabels(string s) {
        int[] last = new int[26];
        int n = s.Length;
        for (int i = 0; i < n; i++) {
            last[s[i] - 'a'] = i;
        }
        IList<int> ans = new List<int>();
        for (int i = 0, j = 0, mx = 0; i < n; ++i) {
            mx = Math.Max(mx, last[s[i] - 'a']);
            if (mx == i) {
                ans.Add(i - j + 1);
                j = i + 1;
            }
        }
        return ans;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
