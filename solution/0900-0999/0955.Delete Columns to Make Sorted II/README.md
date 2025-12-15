---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0900-0999/0955.Delete%20Columns%20to%20Make%20Sorted%20II/README.md
tags:
    - 贪心
    - 数组
    - 字符串
---

<!-- problem:start -->

# [955. 删列造序 II](https://leetcode.cn/problems/delete-columns-to-make-sorted-ii)

[English Version](/solution/0900-0999/0955.Delete%20Columns%20to%20Make%20Sorted%20II/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定由 <code>n</code> 个字符串组成的数组 <code>strs</code>，其中每个字符串长度相等。</p>

<p>选取一个删除索引序列，对于 <code>strs</code> 中的每个字符串，删除对应每个索引处的字符。</p>

<p>比如，有 <code>strs = ["abcdef", "uvwxyz"]</code>，删除索引序列 <code>{0, 2, 3}</code>，删除后 <code>strs</code> 为<code>["bef", "vyz"]</code>。</p>

<p>假设，我们选择了一组删除索引 <code>answer</code>，那么在执行删除操作之后，最终得到的数组的元素是按 <strong>字典序</strong>（<code>strs[0] <= strs[1] <= strs[2] ... <= strs[n - 1]</code>）排列的，然后请你返回 <code>answer.length</code> 的最小可能值。</p>

<p> </p>

<ol>
</ol>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>strs = ["ca","bb","ac"]
<strong>输出：</strong>1
<strong>解释： </strong>
删除第一列后，strs = ["a", "b", "c"]。
现在 strs 中元素是按字典排列的 (即，strs[0] <= strs[1] <= strs[2])。
我们至少需要进行 1 次删除，因为最初 strs 不是按字典序排列的，所以答案是 1。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>strs = ["xc","yb","za"]
<strong>输出：</strong>0
<strong>解释：</strong>
strs 的列已经是按字典序排列了，所以我们不需要删除任何东西。
注意 strs 的行不需要按字典序排列。
也就是说，strs[0][0] <= strs[0][1] <= ... 不一定成立。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>strs = ["zyx","wvu","tsr"]
<strong>输出：</strong>3
<strong>解释：</strong>
我们必须删掉每一列。
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == strs.length</code></li>
	<li><code>1 <= n <= 100</code></li>
	<li><code>1 <= strs[i].length <= 100</code></li>
	<li><code>strs[i]</code> 由小写英文字母组成</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：贪心

字符串按字典序比较时，从左到右比较，第一个不相等的字符决定了两个字符串的大小关系。因此我们可以从左到右遍历每一列，判断当前列是否需要删除。

我们维护一个长度为 $n - 1$ 的布尔数组 $\textit{st}$，表示相邻的字符串对是否已经确定了大小关系。如果已经确定了大小关系，那么后续在这两个字符串之间的任何字符比较都不会改变它们的大小关系。

对于每一列 $j$，我们遍历所有相邻的字符串对 $(\textit{strs}[i], \textit{strs}[i + 1])$：

- 如果 $\textit{st}[i]$ 为假且 $\textit{strs}[i][j] > \textit{strs}[i + 1][j]$，说明当前列必须删除，我们将答案加一并跳过该列的处理；
- 否则，如果 $\textit{st}[i]$ 为假且 $\textit{strs}[i][j] < \textit{strs}[i + 1][j]$，说明当前列确定了这两个字符串的大小关系，我们将 $\textit{st}[i]$ 设为真。

遍历完所有列后，答案即为需要删除的列数。

这个贪心策略是最优的，因为字典序由从左到右第一个不同列决定。若当前列不删除且导致某对字符串顺序错误，则无论后续列如何，都无法修正这一错误，因此必须删除当前列。若当前列不删除且不导致任何字符串对顺序错误，则保留当前列不会影响最终的字典序关系。

时间复杂度 $O(n \times m)$，空间复杂度 $O(n)$，其中 $n$ 和 $m$ 分别为字符串数组的长度和每个字符串的长度。

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
