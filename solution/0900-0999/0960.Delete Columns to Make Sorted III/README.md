---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0900-0999/0960.Delete%20Columns%20to%20Make%20Sorted%20III/README.md
tags:
    - 数组
    - 字符串
    - 动态规划
---

<!-- problem:start -->

# [960. 删列造序 III](https://leetcode.cn/problems/delete-columns-to-make-sorted-iii)

[English Version](/solution/0900-0999/0960.Delete%20Columns%20to%20Make%20Sorted%20III/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定由<meta charset="UTF-8" />&nbsp;<code>n</code>&nbsp;个小写字母字符串组成的数组<meta charset="UTF-8" />&nbsp;<code>strs</code>&nbsp;，其中每个字符串长度相等。</p>

<p>选取一个删除索引序列，对于<meta charset="UTF-8" />&nbsp;<code>strs</code>&nbsp;中的每个字符串，删除对应每个索引处的字符。</p>

<p>比如，有<meta charset="UTF-8" />&nbsp;<code>strs = ["abcdef","uvwxyz"]</code>&nbsp;，删除索引序列<meta charset="UTF-8" />&nbsp;<code>{0, 2, 3}</code>&nbsp;，删除后为<meta charset="UTF-8" />&nbsp;<code>["bef", "vyz"]</code>&nbsp;。</p>

<p>假设，我们选择了一组删除索引<meta charset="UTF-8" />&nbsp;<code>answer</code>&nbsp;，那么在执行删除操作之后，最终得到的数组的行中的 <strong>每个元素</strong> 都是按<strong>字典序</strong>排列的（即&nbsp;<code>(strs[0][0] &lt;= strs[0][1] &lt;= ... &lt;= strs[0][strs[0].length - 1])</code>&nbsp;和&nbsp;<code>(strs[1][0] &lt;= strs[1][1] &lt;= ... &lt;= strs[1][strs[1].length - 1])</code> ，依此类推）。</p>

<p>请返回<meta charset="UTF-8" /><em>&nbsp;<code>answer.length</code>&nbsp;的最小可能值</em>&nbsp;。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>strs = ["babca","bbazb"]
<strong>输出：</strong>3
<strong>解释：
</strong>删除 0、1 和 4 这三列后，最终得到的数组是 strs = ["bc", "az"]。
这两行是分别按字典序排列的（即，strs[0][0] &lt;= strs[0][1] 且 strs[1][0] &lt;= strs[1][1]）。
注意，strs[0] &gt; strs[1] —— 数组 strs 不一定是按字典序排列的。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>strs = ["edcba"]
<strong>输出：</strong>4
<strong>解释：</strong>如果删除的列少于 4 列，则剩下的行都不会按字典序排列。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>strs = ["ghi","def","abc"]
<strong>输出：</strong>0
<strong>解释：</strong>所有行都已按字典序排列。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>
<meta charset="UTF-8" />

<ul>
	<li><code>n == strs.length</code></li>
	<li><code>1 &lt;= n &lt;= 100</code></li>
	<li><code>1 &lt;= strs[i].length &lt;= 100</code></li>
	<li><code>strs[i]</code>&nbsp;由小写英文字母组成</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：动态规划

我们定义 $f[i]$ 表示以第 $i$ 列结尾的最长不下降子序列的长度，初始时 $f[i] = 1$，答案即为 $n - \max(f)$。

考虑计算 $f[i]$，我们可以枚举 $j < i$，如果对于所有的字符串 $s$，有 $s[j] \le s[i]$，那么 $f[i] = \max(f[i], f[j] + 1)$。

最后，我们返回 $n - \max(f)$。

时间复杂度 $O(n^2 \times m)$，空间复杂度 $O(n)$。其中 $n$ 和 $m$ 分别是数组 $\textit{strs}$ 每个字符串的长度和数组的长度。

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
