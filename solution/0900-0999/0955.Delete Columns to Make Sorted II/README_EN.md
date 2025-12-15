---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0900-0999/0955.Delete%20Columns%20to%20Make%20Sorted%20II/README_EN.md
tags:
    - Greedy
    - Array
    - String
---

<!-- problem:start -->

# [955. Delete Columns to Make Sorted II](https://leetcode.com/problems/delete-columns-to-make-sorted-ii)

[中文文档](/solution/0900-0999/0955.Delete%20Columns%20to%20Make%20Sorted%20II/README.md)

## Description

<!-- description:start -->

<p>You are given an array of <code>n</code> strings <code>strs</code>, all of the same length.</p>

<p>We may choose any deletion indices, and we delete all the characters in those indices for each string.</p>

<p>For example, if we have <code>strs = [&quot;abcdef&quot;,&quot;uvwxyz&quot;]</code> and deletion indices <code>{0, 2, 3}</code>, then the final array after deletions is <code>[&quot;bef&quot;, &quot;vyz&quot;]</code>.</p>

<p>Suppose we chose a set of deletion indices <code>answer</code> such that after deletions, the final array has its elements in <strong>lexicographic</strong> order (i.e., <code>strs[0] &lt;= strs[1] &lt;= strs[2] &lt;= ... &lt;= strs[n - 1]</code>). Return <em>the minimum possible value of</em> <code>answer.length</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> strs = [&quot;ca&quot;,&quot;bb&quot;,&quot;ac&quot;]
<strong>Output:</strong> 1
<strong>Explanation:</strong> 
After deleting the first column, strs = [&quot;a&quot;, &quot;b&quot;, &quot;c&quot;].
Now strs is in lexicographic order (ie. strs[0] &lt;= strs[1] &lt;= strs[2]).
We require at least 1 deletion since initially strs was not in lexicographic order, so the answer is 1.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> strs = [&quot;xc&quot;,&quot;yb&quot;,&quot;za&quot;]
<strong>Output:</strong> 0
<strong>Explanation:</strong> 
strs is already in lexicographic order, so we do not need to delete anything.
Note that the rows of strs are not necessarily in lexicographic order:
i.e., it is NOT necessarily true that (strs[0][0] &lt;= strs[0][1] &lt;= ...)
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> strs = [&quot;zyx&quot;,&quot;wvu&quot;,&quot;tsr&quot;]
<strong>Output:</strong> 3
<strong>Explanation:</strong> We have to delete every column.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == strs.length</code></li>
	<li><code>1 &lt;= n &lt;= 100</code></li>
	<li><code>1 &lt;= strs[i].length &lt;= 100</code></li>
	<li><code>strs[i]</code> consists of lowercase English letters.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Greedy

When comparing strings in lexicographical order, we compare from left to right, and the first unequal character determines the ordering relationship between two strings. Therefore, we can traverse each column from left to right and determine whether the current column needs to be deleted.

We maintain a boolean array $\textit{st}$ of length $n - 1$, indicating whether the ordering relationship between adjacent string pairs has been determined. If the ordering relationship has been determined, then any subsequent character comparison between these two strings will not change their ordering relationship.

For each column $j$, we traverse all adjacent string pairs $(\textit{strs}[i], \textit{strs}[i + 1])$:

- If $\textit{st}[i]$ is false and $\textit{strs}[i][j] > \textit{strs}[i + 1][j]$, it means the current column must be deleted. We increment the answer by one and skip processing this column;
- Otherwise, if $\textit{st}[i]$ is false and $\textit{strs}[i][j] < \textit{strs}[i + 1][j]$, it means the current column determines the ordering relationship between these two strings. We set $\textit{st}[i]$ to true.

After traversing all columns, the answer is the number of columns that need to be deleted.

This greedy strategy is optimal because lexicographical order is determined by the first different column from left to right. If the current column is not deleted and causes incorrect ordering for some string pair, then regardless of subsequent columns, this error cannot be corrected, so the current column must be deleted. If the current column is not deleted and does not cause incorrect ordering for any string pair, then keeping the current column will not affect the final lexicographical ordering relationship.

The time complexity is $O(n \times m)$ and the space complexity is $O(n)$, where $n$ and $m$ are the length of the string array and the length of each string, respectively.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minDeletionSize(self, strs: List[str]) -> int:
        n = len(strs)
        m = len(strs[0])
        st = [False] * (n - 1)
        ans = 0
        for j in range(m):
            must_del = False
            for i in range(n - 1):
                if not st[i] and strs[i][j] > strs[i + 1][j]:
                    must_del = True
                    break
            if must_del:
                ans += 1
            else:
                for i in range(n - 1):
                    if not st[i] and strs[i][j] < strs[i + 1][j]:
                        st[i] = True
        return ans
```

#### Java

```java
class Solution {
    public int minDeletionSize(String[] strs) {
        int n = strs.length;
        int m = strs[0].length();
        boolean[] st = new boolean[n - 1];
        int ans = 0;
        for (int j = 0; j < m; ++j) {
            boolean mustDel = false;
            for (int i = 0; i < n - 1; ++i) {
                if (!st[i] && strs[i].charAt(j) > strs[i + 1].charAt(j)) {
                    mustDel = true;
                    break;
                }
            }
            if (mustDel) {
                ++ans;
            } else {
                for (int i = 0; i < n - 1; ++i) {
                    if (!st[i] && strs[i].charAt(j) < strs[i + 1].charAt(j)) {
                        st[i] = true;
                    }
                }
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
    int minDeletionSize(vector<string>& strs) {
        int n = strs.size();
        int m = strs[0].size();
        vector<bool> st(n - 1, false);
        int ans = 0;
        for (int j = 0; j < m; ++j) {
            bool mustDel = false;
            for (int i = 0; i < n - 1; ++i) {
                if (!st[i] && strs[i][j] > strs[i + 1][j]) {
                    mustDel = true;
                    break;
                }
            }
            if (mustDel) {
                ++ans;
            } else {
                for (int i = 0; i < n - 1; ++i) {
                    if (!st[i] && strs[i][j] < strs[i + 1][j]) {
                        st[i] = true;
                    }
                }
            }
        }
        return ans;
    }
};
```

#### Go

```go
func minDeletionSize(strs []string) int {
	n := len(strs)
	m := len(strs[0])
	st := make([]bool, n-1)
	ans := 0
	for j := 0; j < m; j++ {
		mustDel := false
		for i := 0; i < n-1; i++ {
			if !st[i] && strs[i][j] > strs[i+1][j] {
				mustDel = true
				break
			}
		}
		if mustDel {
			ans++
		} else {
			for i := 0; i < n-1; i++ {
				if !st[i] && strs[i][j] < strs[i+1][j] {
					st[i] = true
				}
			}
		}
	}
	return ans
}
```

#### TypeScript

```ts
function minDeletionSize(strs: string[]): number {
    const n = strs.length;
    const m = strs[0].length;
    const st: boolean[] = Array(n - 1).fill(false);
    let ans = 0;

    for (let j = 0; j < m; j++) {
        let mustDel = false;
        for (let i = 0; i < n - 1; i++) {
            if (!st[i] && strs[i][j] > strs[i + 1][j]) {
                mustDel = true;
                break;
            }
        }
        if (mustDel) {
            ans++;
        } else {
            for (let i = 0; i < n - 1; i++) {
                if (!st[i] && strs[i][j] < strs[i + 1][j]) {
                    st[i] = true;
                }
            }
        }
    }

    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn min_deletion_size(strs: Vec<String>) -> i32 {
        let n = strs.len();
        let m = strs[0].len();
        let mut st = vec![false; n - 1];
        let mut ans = 0;

        for j in 0..m {
            let mut must_del = false;
            for i in 0..n - 1 {
                if !st[i] && strs[i].as_bytes()[j] > strs[i + 1].as_bytes()[j] {
                    must_del = true;
                    break;
                }
            }
            if must_del {
                ans += 1;
            } else {
                for i in 0..n - 1 {
                    if !st[i] && strs[i].as_bytes()[j] < strs[i + 1].as_bytes()[j] {
                        st[i] = true;
                    }
                }
            }
        }

        ans
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
