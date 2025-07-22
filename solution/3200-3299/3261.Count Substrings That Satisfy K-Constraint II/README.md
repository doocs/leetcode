---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3200-3299/3261.Count%20Substrings%20That%20Satisfy%20K-Constraint%20II/README.md
rating: 2658
source: 第 411 场周赛 Q4
tags:
    - 数组
    - 字符串
    - 二分查找
    - 前缀和
    - 滑动窗口
---

<!-- problem:start -->

# [3261. 统计满足 K 约束的子字符串数量 II](https://leetcode.cn/problems/count-substrings-that-satisfy-k-constraint-ii)

[English Version](/solution/3200-3299/3261.Count%20Substrings%20That%20Satisfy%20K-Constraint%20II/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个 <strong>二进制</strong> 字符串 <code>s</code> 和一个整数 <code>k</code>。</p>

<p>另给你一个二维整数数组 <code>queries</code> ，其中 <code>queries[i] = [l<sub>i</sub>, r<sub>i</sub>]</code> 。</p>

<p>如果一个 <strong>二进制字符串</strong> 满足以下任一条件，则认为该字符串满足 <strong>k 约束</strong>：</p>

<ul>
	<li>字符串中 <code>0</code> 的数量最多为 <code>k</code>。</li>
	<li>字符串中 <code>1</code> 的数量最多为 <code>k</code>。</li>
</ul>

<p>返回一个整数数组 <code>answer</code> ，其中 <code>answer[i]</code> 表示 <code>s[l<sub>i</sub>..r<sub>i</sub>]</code> 中满足 <strong>k 约束</strong> 的 <span data-keyword="substring-nonempty">子字符串</span> 的数量。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong><span class="example-io">s = "0001111", k = 2, queries = [[0,6]]</span></p>

<p><strong>输出：</strong><span class="example-io">[26]</span></p>

<p><strong>解释：</strong></p>

<p>对于查询 <code>[0, 6]</code>， <code>s[0..6] = "0001111"</code> 的所有子字符串中，除 <code>s[0..5] = "000111"</code> 和 <code>s[0..6] = "0001111"</code> 外，其余子字符串都满足 k 约束。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong><span class="example-io">s = "010101", k = 1, queries = [[0,5],[1,4],[2,3]]</span></p>

<p><strong>输出：</strong><span class="example-io">[15,9,3]</span></p>

<p><strong>解释：</strong></p>

<p><code>s</code> 的所有子字符串中，长度大于 3 的子字符串都不满足 k 约束。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s[i]</code> 是 <code>'0'</code> 或 <code>'1'</code></li>
	<li><code>1 &lt;= k &lt;= s.length</code></li>
	<li><code>1 &lt;= queries.length &lt;= 10<sup>5</sup></code></li>
	<li><code>queries[i] == [l<sub>i</sub>, r<sub>i</sub>]</code></li>
	<li><code>0 &lt;= l<sub>i</sub> &lt;= r<sub>i</sub> &lt; s.length</code></li>
	<li>所有查询互不相同</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：滑动窗口 + 前缀和

我们用两个变量 $\textit{cnt0}$ 和 $\textit{cnt1}$ 分别记录当前窗口内的 $0$ 和 $1$ 的个数，指针 $i$ 和 $j$ 分别标识窗口的左右边界。用一个数组 $d$ 记录每个位置 $i$ 右边第一个不满足 $k$ 约束的位置，初始时 $d[i] = n$。另外，用一个长度为 $n + 1$ 的前缀和数组 $\textit{pre}[i]$ 记录以前 $i$ 个位置作为右边界的满足 $k$ 约束的子字符串的个数。

当我们右移窗口时，如果窗口内的 $0$ 和 $1$ 的个数都大于 $k$，我们将 $d[i]$ 更新为 $j$，表示位置 $i$ 右边第一个不满足 $k$ 约束的位置。然后我们将 $i$ 右移一位，直到窗口内的 $0$ 和 $1$ 的个数都不大于 $k$。此时，我们可以计算出以 $j$ 为右边界的满足 $k$ 约束的子字符串的个数，即 $j - i + 1$，我们更新到前缀和数组中。

最后，对于每个查询 $[l, r]$，我们首先找出 $l$ 右边第一个不满足 $k$ 约束的位置 $p$，那么 $p = \min(r + 1, d[l])$，那么 $[l, p - 1]$ 的所有子字符串都满足 $k$ 约束，个数为 $(1 + p - l) \times (p - l) / 2$，然后，我们计算以 $[p, r]$ 为右边界的满足 $k$ 约束的子字符串的个数，即 $\textit{pre}[r + 1] - \textit{pre}[p]$，最后将两者相加即可。

时间复杂度 $O(n + m)$，空间复杂度 $O(n)$。其中 $n$ 和 $m$ 分别为字符串 $s$ 的长度和查询数组 $\textit{queries}$ 的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def countKConstraintSubstrings(
        self, s: str, k: int, queries: List[List[int]]
    ) -> List[int]:
        cnt = [0, 0]
        i, n = 0, len(s)
        d = [n] * n
        pre = [0] * (n + 1)
        for j, x in enumerate(map(int, s)):
            cnt[x] += 1
            while cnt[0] > k and cnt[1] > k:
                d[i] = j
                cnt[int(s[i])] -= 1
                i += 1
            pre[j + 1] = pre[j] + j - i + 1
        ans = []
        for l, r in queries:
            p = min(r + 1, d[l])
            a = (1 + p - l) * (p - l) // 2
            b = pre[r + 1] - pre[p]
            ans.append(a + b)
        return ans
```

#### Java

```java
class Solution {
    public long[] countKConstraintSubstrings(String s, int k, int[][] queries) {
        int[] cnt = new int[2];
        int n = s.length();
        int[] d = new int[n];
        Arrays.fill(d, n);
        long[] pre = new long[n + 1];
        for (int i = 0, j = 0; j < n; ++j) {
            cnt[s.charAt(j) - '0']++;
            while (cnt[0] > k && cnt[1] > k) {
                d[i] = j;
                cnt[s.charAt(i++) - '0']--;
            }
            pre[j + 1] = pre[j] + j - i + 1;
        }
        int m = queries.length;
        long[] ans = new long[m];
        for (int i = 0; i < m; ++i) {
            int l = queries[i][0], r = queries[i][1];
            int p = Math.min(r + 1, d[l]);
            long a = (1L + p - l) * (p - l) / 2;
            long b = pre[r + 1] - pre[p];
            ans[i] = a + b;
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<long long> countKConstraintSubstrings(string s, int k, vector<vector<int>>& queries) {
        int cnt[2]{};
        int n = s.size();
        vector<int> d(n, n);
        long long pre[n + 1];
        pre[0] = 0;
        for (int i = 0, j = 0; j < n; ++j) {
            cnt[s[j] - '0']++;
            while (cnt[0] > k && cnt[1] > k) {
                d[i] = j;
                cnt[s[i++] - '0']--;
            }
            pre[j + 1] = pre[j] + j - i + 1;
        }
        vector<long long> ans;
        for (const auto& q : queries) {
            int l = q[0], r = q[1];
            int p = min(r + 1, d[l]);
            long long a = (1LL + p - l) * (p - l) / 2;
            long long b = pre[r + 1] - pre[p];
            ans.push_back(a + b);
        }
        return ans;
    }
};
```

#### Go

```go
func countKConstraintSubstrings(s string, k int, queries [][]int) (ans []int64) {
	cnt := [2]int{}
	n := len(s)
	d := make([]int, n)
	for i := range d {
		d[i] = n
	}
	pre := make([]int, n+1)
	for i, j := 0, 0; j < n; j++ {
		cnt[s[j]-'0']++
		for cnt[0] > k && cnt[1] > k {
			d[i] = j
			cnt[s[i]-'0']--
			i++
		}
		pre[j+1] = pre[j] + j - i + 1
	}
	for _, q := range queries {
		l, r := q[0], q[1]
		p := min(r+1, d[l])
		a := (1 + p - l) * (p - l) / 2
		b := pre[r+1] - pre[p]
		ans = append(ans, int64(a+b))
	}
	return
}
```

#### TypeScript

```ts
function countKConstraintSubstrings(s: string, k: number, queries: number[][]): number[] {
    const cnt: [number, number] = [0, 0];
    const n = s.length;
    const d: number[] = Array(n).fill(n);
    const pre: number[] = Array(n + 1).fill(0);
    for (let i = 0, j = 0; j < n; ++j) {
        cnt[+s[j]]++;
        while (Math.min(cnt[0], cnt[1]) > k) {
            d[i] = j;
            cnt[+s[i++]]--;
        }
        pre[j + 1] = pre[j] + j - i + 1;
    }
    const ans: number[] = [];
    for (const [l, r] of queries) {
        const p = Math.min(r + 1, d[l]);
        const a = ((1 + p - l) * (p - l)) / 2;
        const b = pre[r + 1] - pre[p];
        ans.push(a + b);
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
