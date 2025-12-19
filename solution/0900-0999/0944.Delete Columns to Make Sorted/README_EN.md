---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0900-0999/0944.Delete%20Columns%20to%20Make%20Sorted/README_EN.md
tags:
    - Array
    - String
---

<!-- problem:start -->

# [944. Delete Columns to Make Sorted](https://leetcode.com/problems/delete-columns-to-make-sorted)

[中文文档](/solution/0900-0999/0944.Delete%20Columns%20to%20Make%20Sorted/README.md)

## Description

<!-- description:start -->

<p>You are given an array of <code>n</code> strings <code>strs</code>, all of the same length.</p>

<p>The strings can be arranged such that there is one on each line, making a grid.</p>

<ul>
	<li>For example, <code>strs = [&quot;abc&quot;, &quot;bce&quot;, &quot;cae&quot;]</code> can be arranged as follows:</li>
</ul>

<pre>
abc
bce
cae
</pre>

<p>You want to <strong>delete</strong> the columns that are <strong>not sorted lexicographically</strong>. In the above example (<strong>0-indexed</strong>), columns 0 (<code>&#39;a&#39;</code>, <code>&#39;b&#39;</code>, <code>&#39;c&#39;</code>) and 2 (<code>&#39;c&#39;</code>, <code>&#39;e&#39;</code>, <code>&#39;e&#39;</code>) are sorted, while column 1 (<code>&#39;b&#39;</code>, <code>&#39;c&#39;</code>, <code>&#39;a&#39;</code>) is not, so you would delete column 1.</p>

<p>Return <em>the number of columns that you will delete</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> strs = [&quot;cba&quot;,&quot;daf&quot;,&quot;ghi&quot;]
<strong>Output:</strong> 1
<strong>Explanation:</strong> The grid looks as follows:
  cba
  daf
  ghi
Columns 0 and 2 are sorted, but column 1 is not, so you only need to delete 1 column.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> strs = [&quot;a&quot;,&quot;b&quot;]
<strong>Output:</strong> 0
<strong>Explanation:</strong> The grid looks as follows:
  a
  b
Column 0 is the only column and is sorted, so you will not delete any columns.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> strs = [&quot;zyx&quot;,&quot;wvu&quot;,&quot;tsr&quot;]
<strong>Output:</strong> 3
<strong>Explanation:</strong> The grid looks as follows:
  zyx
  wvu
  tsr
All 3 columns are not sorted, so you will delete all 3.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == strs.length</code></li>
	<li><code>1 &lt;= n &lt;= 100</code></li>
	<li><code>1 &lt;= strs[i].length &lt;= 1000</code></li>
	<li><code>strs[i]</code> consists of lowercase English letters.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Compare Column by Column

We denote the number of rows in the string array $\textit{strs}$ as $n$, and the number of columns as $m$.

We traverse each column, starting from the second row, and compare the character of the current row with that of the previous row column by column. If the character of the current row is less than that of the previous row, it indicates that the current column is not arranged in non-strictly increasing lexicographical order, and we need to delete it, incrementing the result by one, then break out of the inner loop.

Finally, we return the result.

The time complexity is $O(L)$, where $L$ is the total length of the strings in the array $\textit{strs}$. The space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minDeletionSize(self, strs: List[str]) -> int:
        m, n = len(strs[0]), len(strs)
        ans = 0
        for j in range(m):
            for i in range(1, n):
                if strs[i][j] < strs[i - 1][j]:
                    ans += 1
                    break
        return ans
```

#### Java

```java
class Solution {
    public int minDeletionSize(String[] strs) {
        int m = strs[0].length(), n = strs.length;
        int ans = 0;
        for (int j = 0; j < m; ++j) {
            for (int i = 1; i < n; ++i) {
                if (strs[i].charAt(j) < strs[i - 1].charAt(j)) {
                    ++ans;
                    break;
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
        int m = strs[0].size(), n = strs.size();
        int ans = 0;
        for (int j = 0; j < m; ++j) {
            for (int i = 1; i < n; ++i) {
                if (strs[i][j] < strs[i - 1][j]) {
                    ++ans;
                    break;
                }
            }
        }
        return ans;
    }
};
```

#### Go

```go
func minDeletionSize(strs []string) (ans int) {
	m, n := len(strs[0]), len(strs)
	for j := 0; j < m; j++ {
		for i := 1; i < n; i++ {
			if strs[i][j] < strs[i-1][j] {
				ans++
				break
			}
		}
	}
	return
}
```

#### TypeScript

```ts
function minDeletionSize(strs: string[]): number {
    const [m, n] = [strs[0].length, strs.length];
    let ans = 0;
    for (let j = 0; j < m; ++j) {
        for (let i = 1; i < n; ++i) {
            if (strs[i][j] < strs[i - 1][j]) {
                ++ans;
                break;
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
        let mut ans = 0;
        for j in 0..m {
            for i in 1..n {
                if strs[i].as_bytes()[j] < strs[i - 1].as_bytes()[j] {
                    ans += 1;
                    break;
                }
            }
        }
        ans
    }
}
```

#### C#

```cs
public class Solution {
    public int MinDeletionSize(string[] strs) {
        int m = strs[0].Length;
        int n = strs.Length;
        int ans = 0;

        for (int j = 0; j < m; ++j) {
            for (int i = 1; i < n; ++i) {
                if (strs[i][j] < strs[i - 1][j]) {
                    ++ans;
                    break;
                }
            }
        }

        return ans;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
