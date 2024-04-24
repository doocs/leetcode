# [2955. 同端子串的数量 🔒](https://leetcode.cn/problems/number-of-same-end-substrings)

[English Version](/solution/2900-2999/2955.Number%20of%20Same-End%20Substrings/README_EN.md)

<!-- tags:数组,哈希表,字符串,计数,前缀和 -->

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个 <strong>下标从0开始</strong>&nbsp;的字符串 <code>s</code>，以及一个二维整数数组 <code>queries</code>，其中 <code>queries[i] = [l<sub>i</sub>, r<sub>i</sub>]</code> 表示 <code>s</code> 中从索引 <code>l<sub>i</sub></code> 开始到索引 <code>r<sub>i</sub></code> 结束的子串（<strong>包括两端</strong>），即 <code>s[l<sub>i</sub>..r<sub>i</sub>]</code>。</p>

<p>返回一个数组 <code>ans</code>，其中 <code>ans[i]</code> 是 <code>queries[i]</code> 的 <strong>同端</strong> 子串的数量。</p>

<p>如果一个&nbsp;<strong>下标从0开始 </strong>且长度为 <code>n</code> 的字符串 <code>t</code> 两端的字符相同，即 <code>t[0] == t[n - 1]</code>，则该字符串被称为 <strong>同端</strong>。</p>

<p><strong>子串</strong> 是一个字符串中连续的非空字符序列。</p>

<p>&nbsp;</p>

<p><b>示例 1：</b></p>

<pre>
<b>输入：</b>s = "abcaab", queries = [[0,0],[1,4],[2,5],[0,5]]
<b>输出：</b>[1,5,5,10]
<b>解释：</b>每个查询的同端子串如下：
第一个查询：s[0..0] 是 "a"，有 1 个同端子串："<strong><u>a</u></strong>"。
第二个查询：s[1..4] 是 "bcaa"，有 5 个同端子串："<strong><u>b</u></strong>caa", "b<strong><u>c</u></strong>aa", "bc<strong><u>a</u></strong>a", "bca<strong><u>a</u></strong>", "bc<strong><u>aa</u></strong>"。
第三个查询：s[2..5] 是 "caab"，有 5 个同端子串："<strong><u>c</u></strong>aab", "c<strong><u>a</u></strong>ab", "ca<strong><u>a</u></strong>b", "caa<strong><u>b</u></strong>", "c<strong><u>aa</u></strong>b"。
第四个查询：s[0..5] 是 "abcaab"，有 10 个同端子串："<strong><u>a</u></strong>bcaab", "a<strong><u>b</u></strong>caab", "ab<strong><u>c</u></strong>aab", "abc<strong><u>a</u></strong>ab", "abca<strong><u>a</u></strong>b", "abcaa<strong><u>b</u></strong>", "abc<strong><u>aa</u></strong>b", "<strong><u>abca</u></strong>ab", "<strong><u>abcaa</u></strong>b", "a<strong><u>bcaab</u></strong>"。</pre>

<p><b>示例 2：</b></p>

<pre>
<b>输入：</b>s = "abcd", queries = [[0,3]]
<b>输出：</b>[4]
<b>解释：</b>唯一的查询是 s[0..3]，它有 4 个同端子串："<strong><u>a</u></strong>bcd", "a<strong><u>b</u></strong>cd", "ab<strong><u>c</u></strong>d", "abc<strong><u>d</u></strong>"。
</pre>

<p>&nbsp;</p>

<p><b>提示：</b></p>

<ul>
	<li><code>2 &lt;= s.length &lt;= 3 * 10<sup>4</sup></code></li>
	<li><code>s</code> 仅包含小写英文字母。</li>
	<li><code>1 &lt;= queries.length &lt;= 3 * 10<sup>4</sup></code></li>
	<li><code>queries[i] = [l<sub>i</sub>, r<sub>i</sub>]</code></li>
	<li><code>0 &lt;= l<sub>i</sub> &lt;= r<sub>i</sub> &lt; s.length</code></li>
</ul>

## 解法

### 方法一：前缀和 + 枚举

我们可以预处理出每个字母的前缀和，记录在数组 $cnt$ 中，其中 $cnt[i][j]$ 表示第 $i$ 个字母在前 $j$ 个字符中出现的次数。这样，对于每个区间 $[l, r]$，我们可以枚举区间中的每个字母 $c$，利用前缀和数组快速计算出 $c$ 在区间中出现的次数 $x$，我们任取其中两个，即可组成一个同尾子串，子串数为 $C_x^2=\frac{x(x-1)}{2}$，加上区间中每个字母可以单独组成同尾子串的情况，一共有 $r - l + 1$ 个字母。因此，对于每个查询 $[l, r]$，满足条件的同尾子串数为 $r - l + 1 + \sum_{c \in \Sigma} \frac{x_c(x_c-1)}{2}$，其中 $x_c$ 表示字母 $c$ 在区间 $[l, r]$ 中出现的次数。

时间复杂度 $O((n + m) \times |\Sigma|)$，空间复杂度 $O(n \times |\Sigma|)$。其中 $n$ 和 $m$ 分别为字符串 $s$ 的长度和查询数，而 $\Sigma$ 表示字符串 $s$ 中出现的字母集合，本题中 $|\Sigma|=26$。

<!-- tabs:start -->

```python
class Solution:
    def sameEndSubstringCount(self, s: str, queries: List[List[int]]) -> List[int]:
        n = len(s)
        cs = set(s)
        cnt = {c: [0] * (n + 1) for c in cs}
        for i, a in enumerate(s, 1):
            for c in cs:
                cnt[c][i] = cnt[c][i - 1]
            cnt[a][i] += 1
        ans = []
        for l, r in queries:
            t = r - l + 1
            for c in cs:
                x = cnt[c][r + 1] - cnt[c][l]
                t += x * (x - 1) // 2
            ans.append(t)
        return ans
```

```java
class Solution {
    public int[] sameEndSubstringCount(String s, int[][] queries) {
        int n = s.length();
        int[][] cnt = new int[26][n + 1];
        for (int j = 1; j <= n; ++j) {
            for (int i = 0; i < 26; ++i) {
                cnt[i][j] = cnt[i][j - 1];
            }
            cnt[s.charAt(j - 1) - 'a'][j]++;
        }
        int m = queries.length;
        int[] ans = new int[m];
        for (int k = 0; k < m; ++k) {
            int l = queries[k][0], r = queries[k][1];
            ans[k] = r - l + 1;
            for (int i = 0; i < 26; ++i) {
                int x = cnt[i][r + 1] - cnt[i][l];
                ans[k] += x * (x - 1) / 2;
            }
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    vector<int> sameEndSubstringCount(string s, vector<vector<int>>& queries) {
        int n = s.size();
        int cnt[26][n + 1];
        memset(cnt, 0, sizeof(cnt));
        for (int j = 1; j <= n; ++j) {
            for (int i = 0; i < 26; ++i) {
                cnt[i][j] = cnt[i][j - 1];
            }
            cnt[s[j - 1] - 'a'][j]++;
        }
        vector<int> ans;
        for (auto& q : queries) {
            int l = q[0], r = q[1];
            ans.push_back(r - l + 1);
            for (int i = 0; i < 26; ++i) {
                int x = cnt[i][r + 1] - cnt[i][l];
                ans.back() += x * (x - 1) / 2;
            }
        }
        return ans;
    }
};
```

```go
func sameEndSubstringCount(s string, queries [][]int) []int {
	n := len(s)
	cnt := make([][]int, 26)
	for i := 0; i < 26; i++ {
		cnt[i] = make([]int, n+1)
	}

	for j := 1; j <= n; j++ {
		for i := 0; i < 26; i++ {
			cnt[i][j] = cnt[i][j-1]
		}
		cnt[s[j-1]-'a'][j]++
	}

	var ans []int
	for _, q := range queries {
		l, r := q[0], q[1]
		ans = append(ans, r-l+1)
		for i := 0; i < 26; i++ {
			x := cnt[i][r+1] - cnt[i][l]
			ans[len(ans)-1] += x * (x - 1) / 2
		}
	}

	return ans
}
```

```ts
function sameEndSubstringCount(s: string, queries: number[][]): number[] {
    const n: number = s.length;
    const cnt: number[][] = Array.from({ length: 26 }, () => Array(n + 1).fill(0));
    for (let j = 1; j <= n; j++) {
        for (let i = 0; i < 26; i++) {
            cnt[i][j] = cnt[i][j - 1];
        }
        cnt[s.charCodeAt(j - 1) - 'a'.charCodeAt(0)][j]++;
    }
    const ans: number[] = [];
    for (const [l, r] of queries) {
        ans.push(r - l + 1);
        for (let i = 0; i < 26; i++) {
            const x: number = cnt[i][r + 1] - cnt[i][l];
            ans[ans.length - 1] += (x * (x - 1)) / 2;
        }
    }
    return ans;
}
```

```rust
impl Solution {
    pub fn same_end_substring_count(s: String, queries: Vec<Vec<i32>>) -> Vec<i32> {
        let n = s.len();
        let mut cnt: Vec<Vec<i32>> = vec![vec![0; n + 1]; 26];
        for j in 1..=n {
            for i in 0..26 {
                cnt[i][j] = cnt[i][j - 1];
            }
            cnt[(s.as_bytes()[j - 1] as usize) - (b'a' as usize)][j] += 1;
        }
        let mut ans: Vec<i32> = Vec::new();
        for q in queries.iter() {
            let l = q[0] as usize;
            let r = q[1] as usize;
            let mut t = (r - l + 1) as i32;
            for i in 0..26 {
                let x = cnt[i][r + 1] - cnt[i][l];
                t += (x * (x - 1)) / 2;
            }
            ans.push(t);
        }
        ans
    }
}
```

<!-- tabs:end -->

<!-- end -->
