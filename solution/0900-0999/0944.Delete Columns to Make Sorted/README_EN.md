# [944. Delete Columns to Make Sorted](https://leetcode.com/problems/delete-columns-to-make-sorted)

[中文文档](/solution/0900-0999/0944.Delete%20Columns%20to%20Make%20Sorted/README.md)

## Description

<p>You are given an array of <code>n</code> strings <code>strs</code>, all of the same length.</p>

<p>The strings can be arranged such that there is one on each line, making a grid. For example, <code>strs = [&quot;abc&quot;, &quot;bce&quot;, &quot;cae&quot;]</code> can be arranged as:</p>

<pre>
abc
bce
cae
</pre>

<p>You want to <strong>delete</strong> the columns that are <strong>not sorted lexicographically</strong>. In the above example (0-indexed), columns 0 (<code>&#39;a&#39;</code>, <code>&#39;b&#39;</code>, <code>&#39;c&#39;</code>) and 2 (<code>&#39;c&#39;</code>, <code>&#39;e&#39;</code>, <code>&#39;e&#39;</code>) are sorted while column 1 (<code>&#39;b&#39;</code>, <code>&#39;c&#39;</code>, <code>&#39;a&#39;</code>) is not, so you would delete column 1.</p>

<p>Return <em>the number of columns that you will delete</em>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> strs = [&quot;cba&quot;,&quot;daf&quot;,&quot;ghi&quot;]
<strong>Output:</strong> 1
<strong>Explanation:</strong> The grid looks as follows:
  cba
  daf
  ghi
Columns 0 and 2 are sorted, but column 1 is not, so you only need to delete 1 column.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> strs = [&quot;a&quot;,&quot;b&quot;]
<strong>Output:</strong> 0
<strong>Explanation:</strong> The grid looks as follows:
  a
  b
Column 0 is the only column and is sorted, so you will not delete any columns.
</pre>

<p><strong>Example 3:</strong></p>

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

## Solutions

<!-- tabs:start -->

### **Python3**

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

### **Java**

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

### **C++**

```cpp
class Solution {
public:
    int minDeletionSize(vector<string>& strs) {
        int n = strs.size();
        int m = strs[0].size();
        int res = 0;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n - 1; ++j) {
                if (strs[j][i] > strs[j + 1][i]) {
                    res++;
                    break;
                }
            }
        }
        return res;
    }
};
```

### **Rust**

```rust
impl Solution {
    pub fn min_deletion_size(strs: Vec<String>) -> i32 {
        let n = strs.len();
        let m = strs[0].len();
        let mut res = 0;
        for i in 0..m {
            for j in 1..n {
                if strs[j - 1].as_bytes()[i] > strs[j].as_bytes()[i] {
                    res += 1;
                    break;
                }
            }
        }
        res
    }
}
```

### **Go**

```go
func minDeletionSize(strs []string) int {
	m, n := len(strs[0]), len(strs)
	ans := 0
	for j := 0; j < m; j++ {
		for i := 1; i < n; i++ {
			if strs[i][j] < strs[i-1][j] {
				ans++
				break
			}
		}
	}
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
