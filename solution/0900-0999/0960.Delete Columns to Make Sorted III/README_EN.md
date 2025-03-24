---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0900-0999/0960.Delete%20Columns%20to%20Make%20Sorted%20III/README_EN.md
tags:
    - Array
    - String
    - Dynamic Programming
---

<!-- problem:start -->

# [960. Delete Columns to Make Sorted III](https://leetcode.com/problems/delete-columns-to-make-sorted-iii)

[中文文档](/solution/0900-0999/0960.Delete%20Columns%20to%20Make%20Sorted%20III/README.md)

## Description

<!-- description:start -->

<p>You are given an array of <code>n</code> strings <code>strs</code>, all of the same length.</p>

<p>We may choose any deletion indices, and we delete all the characters in those indices for each string.</p>

<p>For example, if we have <code>strs = [&quot;abcdef&quot;,&quot;uvwxyz&quot;]</code> and deletion indices <code>{0, 2, 3}</code>, then the final array after deletions is <code>[&quot;bef&quot;, &quot;vyz&quot;]</code>.</p>

<p>Suppose we chose a set of deletion indices <code>answer</code> such that after deletions, the final array has <strong>every string (row) in lexicographic</strong> order. (i.e., <code>(strs[0][0] &lt;= strs[0][1] &lt;= ... &lt;= strs[0][strs[0].length - 1])</code>, and <code>(strs[1][0] &lt;= strs[1][1] &lt;= ... &lt;= strs[1][strs[1].length - 1])</code>, and so on). Return <em>the minimum possible value of</em> <code>answer.length</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> strs = [&quot;babca&quot;,&quot;bbazb&quot;]
<strong>Output:</strong> 3
<strong>Explanation:</strong> After deleting columns 0, 1, and 4, the final array is strs = [&quot;bc&quot;, &quot;az&quot;].
Both these rows are individually in lexicographic order (ie. strs[0][0] &lt;= strs[0][1] and strs[1][0] &lt;= strs[1][1]).
Note that strs[0] &gt; strs[1] - the array strs is not necessarily in lexicographic order.</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> strs = [&quot;edcba&quot;]
<strong>Output:</strong> 4
<strong>Explanation:</strong> If we delete less than 4 columns, the only row will not be lexicographically sorted.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> strs = [&quot;ghi&quot;,&quot;def&quot;,&quot;abc&quot;]
<strong>Output:</strong> 0
<strong>Explanation:</strong> All rows are already lexicographically sorted.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == strs.length</code></li>
	<li><code>1 &lt;= n &lt;= 100</code></li>
	<li><code>1 &lt;= strs[i].length &lt;= 100</code></li>
	<li><code>strs[i]</code> consists of lowercase English letters.</li>
</ul>

<ul>
	<li>&nbsp;</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Dynamic Programming

We define $f[i]$ as the length of the longest non-decreasing subsequence ending at column $i$. Initially, $f[i] = 1$, and the final answer is $n - \max(f)$.

To compute $f[i]$, we iterate over all $j < i$. If for all strings $s$, we have $s[j] \leq s[i]$, then we update $f[i]$ as follows:
$$ f[i] = \max(f[i], f[j] + 1) $$

Finally, we return $n - \max(f)$.

The time complexity is $O(n^2 \times m)$, and the space complexity is $O(n)$, where $n$ is the length of each string in the array $\textit{strs}$, and $m$ is the number of strings in the array.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minDeletionSize(self, strs: List[str]) -> int:
        n = len(strs[0])
        f = [1] * n
        for i in range(n):
            for j in range(i):
                if all(s[j] <= s[i] for s in strs):
                    f[i] = max(f[i], f[j] + 1)
        return n - max(f)
```

#### Java

```java
class Solution {
    public int minDeletionSize(String[] strs) {
        int n = strs[0].length();
        int[] f = new int[n];
        Arrays.fill(f, 1);
        for (int i = 1; i < n; ++i) {
            for (int j = 0; j < i; ++j) {
                boolean ok = true;
                for (String s : strs) {
                    if (s.charAt(j) > s.charAt(i)) {
                        ok = false;
                        break;
                    }
                }
                if (ok) {
                    f[i] = Math.max(f[i], f[j] + 1);
                }
            }
        }
        return n - Arrays.stream(f).max().getAsInt();
    }
}
```

#### C++

```cpp
class Solution {
public:
    int minDeletionSize(vector<string>& strs) {
        int n = strs[0].size();
        vector<int> f(n, 1);
        for (int i = 1; i < n; ++i) {
            for (int j = 0; j < i; ++j) {
                if (ranges::all_of(strs, [&](const string& s) { return s[j] <= s[i]; })) {
                    f[i] = max(f[i], f[j] + 1);
                }
            }
        }
        return n - ranges::max(f);
    }
};
```

#### Go

```go
func minDeletionSize(strs []string) int {
	n := len(strs[0])
	f := make([]int, n)
	for i := range f {
		f[i] = 1
	}
	for i := 1; i < n; i++ {
		for j := 0; j < i; j++ {
			ok := true
			for _, s := range strs {
				if s[j] > s[i] {
					ok = false
					break
				}
			}
			if ok {
				f[i] = max(f[i], f[j]+1)
			}
		}
	}
	return n - slices.Max(f)
}
```

#### TypeScript

```ts
function minDeletionSize(strs: string[]): number {
    const n = strs[0].length;
    const f: number[] = Array(n).fill(1);
    for (let i = 1; i < n; i++) {
        for (let j = 0; j < i; j++) {
            let ok = true;
            for (const s of strs) {
                if (s[j] > s[i]) {
                    ok = false;
                    break;
                }
            }
            if (ok) {
                f[i] = Math.max(f[i], f[j] + 1);
            }
        }
    }
    return n - Math.max(...f);
}
```

#### Rust

```rust
impl Solution {
    pub fn min_deletion_size(strs: Vec<String>) -> i32 {
        let n = strs[0].len();
        let mut f = vec![1; n];

        for i in 1..n {
            for j in 0..i {
                if strs.iter().all(|s| s.as_bytes()[j] <= s.as_bytes()[i]) {
                    f[i] = f[i].max(f[j] + 1);
                }
            }
        }

        (n - *f.iter().max().unwrap()) as i32
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
