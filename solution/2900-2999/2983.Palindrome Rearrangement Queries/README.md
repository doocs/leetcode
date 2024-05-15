---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2900-2999/2983.Palindrome%20Rearrangement%20Queries/README.md
rating: 2779
tags:
    - 哈希表
    - 字符串
    - 前缀和
---

# [2983. 回文串重新排列查询](https://leetcode.cn/problems/palindrome-rearrangement-queries)

[English Version](/solution/2900-2999/2983.Palindrome%20Rearrangement%20Queries/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个长度为 <strong>偶数</strong>&nbsp;<code>n</code>&nbsp;，下标从 <strong>0</strong>&nbsp;开始的字符串&nbsp;<code>s</code>&nbsp;。</p>

<p>同时给你一个下标从 <strong>0</strong>&nbsp;开始的二维整数数组&nbsp;<code>queries</code>&nbsp;，其中&nbsp;<code>queries[i] = [a<sub>i</sub>, b<sub>i</sub>, c<sub>i</sub>, d<sub>i</sub>]</code>&nbsp;。</p>

<p>对于每个查询&nbsp;<code>i</code>&nbsp;，你需要执行以下操作：</p>

<ul>
	<li>将下标在范围&nbsp;<code>0 &lt;= a<sub>i</sub> &lt;= b<sub>i</sub> &lt; n / 2</code>&nbsp;内的&nbsp;<strong>子字符串</strong>&nbsp;<code>s[a<sub>i</sub>:b<sub>i</sub>]</code>&nbsp;中的字符重新排列。</li>
	<li>将下标在范围 <code>n / 2 &lt;= c<sub>i</sub> &lt;= d<sub>i</sub> &lt; n</code>&nbsp;内的 <strong>子字符串</strong>&nbsp;<code>s[c<sub>i</sub>:d<sub>i</sub>]</code>&nbsp;中的字符重新排列。</li>
</ul>

<p>对于每个查询，你的任务是判断执行操作后能否让 <code>s</code>&nbsp;变成一个 <strong>回文串</strong> 。</p>

<p>每个查询与其他查询都是 <strong>独立的</strong>&nbsp;。</p>

<p>请你返回一个下标从 <strong>0</strong>&nbsp;开始的数组<em>&nbsp;</em><code>answer</code>&nbsp;，如果第&nbsp;<code>i</code>&nbsp;个查询执行操作后，可以将&nbsp;<code>s</code>&nbsp;变为一个回文串，那么<em>&nbsp;</em><code>answer[i] =&nbsp;true</code>，否则为<em>&nbsp;</em><code>false</code>&nbsp;。</p>

<ul>
	<li><strong>子字符串</strong>&nbsp;指的是一个字符串中一段连续的字符序列。</li>
	<li><code>s[x:y]</code>&nbsp;表示 <code>s</code>&nbsp;中从下标 <code>x</code>&nbsp;到 <code>y</code>&nbsp;且两个端点 <strong>都包含</strong>&nbsp;的子字符串。</li>
</ul>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<b>输入：</b>s = "abcabc", queries = [[1,1,3,5],[0,2,5,5]]
<b>输出：</b>[true,true]
<b>解释：</b>这个例子中，有 2 个查询：
第一个查询：
- a<sub>0</sub> = 1, b<sub>0</sub> = 1, c<sub>0</sub> = 3, d<sub>0</sub> = 5
- 你可以重新排列 s[1:1] =&gt; a<em><strong>b</strong></em>cabc 和 s[3:5] =&gt; abc<em><strong>abc</strong></em>&nbsp;。
- 为了让 s 变为回文串，s[3:5] 可以重新排列得到 =&gt; abc<strong><em>cba </em></strong>。
- 现在 s 是一个回文串。所以 answer[0] = true 。
第二个查询：
- a<sub>1</sub> = 0, b<sub>1</sub> = 2, c<sub>1</sub> = 5, d<sub>1</sub> = 5.
- 你可以重新排列 s[0:2] =&gt; <em><strong>abc</strong></em>abc 和 s[5:5] =&gt; abcab<strong><em>c</em></strong>&nbsp;。
- 为了让 s 变为回文串，s[0:2] 可以重新排列得到 =&gt; <em><strong>cba</strong></em>abc 。
- 现在 s 是一个回文串，所以 answer[1] = true 。
</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<b>输入：</b>s = "abbcdecbba", queries = [[0,2,7,9]]
<b>输出：</b>[false]
<b>解释：</b>这个示例中，只有一个查询。
a<sub>0</sub> = 0, b<sub>0</sub> = 2, c<sub>0</sub> = 7, d<sub>0</sub> = 9.
你可以重新排列 s[0:2] =&gt; <em><strong>abb</strong></em>cdecbba 和 s[7:9] =&gt; abbcdec<em><strong>bba</strong></em>&nbsp;。
无法通过重新排列这些子字符串使 s 变为一个回文串，因为 s[3:6] 不是一个回文串。
所以 answer[0] = false 。</pre>

<p><strong class="example">示例 3：</strong></p>

<pre>
<b>输入：</b>s = "acbcab", queries = [[1,2,4,5]]
<b>输出：</b>[true]
<strong>解释：</strong>这个示例中，只有一个查询。
a<sub>0</sub> = 1, b<sub>0</sub> = 2, c<sub>0</sub> = 4, d<sub>0</sub> = 5.
你可以重新排列 s[1:2] =&gt; a<em><strong>cb</strong></em>cab 和 s[4:5] =&gt; acbc<strong><em>ab</em></strong>&nbsp;。
为了让 s 变为回文串，s[1:2] 可以重新排列得到 =&gt; a<em><strong>bc</strong></em>cab<code>&nbsp;</code>。
然后 s[4:5] 重新排列得到 abcc<em><strong>ba</strong></em>&nbsp;。
现在 s 是一个回文串，所以 answer[0] = true 。</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= n == s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= queries.length &lt;= 10<sup>5</sup></code></li>
	<li><code>queries[i].length == 4</code></li>
	<li><code>a<sub>i</sub> == queries[i][0], b<sub>i</sub> == queries[i][1]</code></li>
	<li><code>c<sub>i</sub> == queries[i][2], d<sub>i</sub> == queries[i][3]</code></li>
	<li><code>0 &lt;= a<sub>i</sub> &lt;= b<sub>i</sub> &lt; n / 2</code></li>
	<li><code>n / 2 &lt;= c<sub>i</sub> &lt;= d<sub>i</sub> &lt; n </code></li>
	<li><code>n</code>&nbsp;是一个偶数。</li>
	<li><code>s</code> 只包含小写英文字母。</li>
</ul>

## 解法

### 方法一：前缀和 + 分类讨论

我们记字符串 $s$ 的长度为 $n$，那么一半的长度为 $m = \frac{n}{2}$。接下来，我们把字符串 $s$ 分成长度相等的两段，其中第二段反转后得到字符串 $t$，第一段记为 $s$。那么对于每个查询 $[a_i, b_i, c_i, d_i]$，其中 $c_i$ 和 $d_i$ 需要变换为 $n - 1 - d_i$ 和 $n - 1 - c_i$。问题转化为：对于每个查询 $[a_i, b_i, c_i, d_i]$，判断 $s[a_i, b_i]$ 和 $t[c_i, d_i]$ 是否可以通过重新排列，使得字符串 $s$ 和 $t$ 相等。

我们预处理以下信息：

1. 字符串 $s$ 的前缀和数组 $pre_1$，其中 $pre_1[i][j]$ 表示字符串 $s$ 前 $i$ 个字符中字符 $j$ 的数量；
2. 字符串 $t$ 的前缀和数组 $pre_2$，其中 $pre_2[i][j]$ 表示字符串 $t$ 前 $i$ 个字符中字符 $j$ 的数量；
3. 字符串 $s$ 和 $t$ 的差异数组 $diff$，其中 $diff[i]$ 表示字符串 $s$ 和 $t$ 的前 $i$ 个字符中不同的字符数量。

对于每个查询 $[a_i, b_i, c_i, d_i]$，我们不妨假设 $a_i \le c_i$，那么我们需要讨论以下几种情况：

1. 字符串 $s$ 和 $t$ 的前缀子串 $s[..a_i-1]$ 和 $t[..a_i-1]$ 必须相等，并且后缀子串 $s[\max(b_i, d_i)+1..]$ 和 $t[\max(b_i, d_i)..]$ 也必须相等，否则无法通过重新排列使得字符串 $s$ 和 $t$ 相等；
1. 如果 $d_i \le b_i$，说明区间 $[a_i, b_i]$ 包含区间 $[c_i, d_i]$，那么如果 $s[a_i, b_i]$ 和 $t[a_i, b_i]$ 这两个子串包含的字符数量相同，那么就可以通过重新排列使得字符串 $s$ 和 $t$ 相等，否则无法通过重新排列使得字符串 $s$ 和 $t$ 相等；
1. 如果 $b_i < c_i$，说明区间 $[a_i, b_i]$ 和区间 $[c_i, d_i]$ 不相交，那么 $s[b_i+1, c_i-1]$ 和 $t[b_i+1, c_i-1]$ 这两个子串必须相等，并且 $s[a_i, b_i]$ 和 $t[a_i, b_i]$ 这两个子串必须相等，同时 $s[c_i, d_i]$ 和 $t[c_i, d_i]$ 这两个子串必须相等，否则无法通过重新排列使得字符串 $s$ 和 $t$ 相等。
1. 如果 $c_i \le b_i < d_i$，说明区间 $[a_i, b_i]$ 和区间 $[c_i, d_i]$ 相交，那么 $s[a_i, b_i]$ 包含的字符，减去 $t[a_i, c_i-1]$ 包含的字符，必须等于 $t[c_i, d_i]$ 包含的字符，减去 $s[b_i+1, d_i]$ 包含的字符，否则无法通过重新排列使得字符串 $s$ 和 $t$ 相等。

基于上述分析，我们遍历每个查询 $[a_i, b_i, c_i, d_i]$，判断是否满足上述条件即可。

时间复杂度 $O((n + q) \times |\Sigma|)$，空间复杂度 $O(n \times |\Sigma|)$。其中 $n$ 和 $q$ 分别是字符串 $s$ 的长度和查询数组 $queries$ 的长度；而 $|\Sigma|$ 是字符集的大小，本题中字符集为小写英文字母，因此 $|\Sigma| = 26$。

<!-- tabs:start -->

```python
class Solution:
    def canMakePalindromeQueries(self, s: str, queries: List[List[int]]) -> List[bool]:
        def count(pre: List[List[int]], i: int, j: int) -> List[int]:
            return [x - y for x, y in zip(pre[j + 1], pre[i])]

        def sub(cnt1: List[int], cnt2: List[int]) -> List[int]:
            res = []
            for x, y in zip(cnt1, cnt2):
                if x - y < 0:
                    return []
                res.append(x - y)
            return res

        def check(
            pre1: List[List[int]], pre2: List[List[int]], a: int, b: int, c: int, d: int
        ) -> bool:
            if diff[a] > 0 or diff[m] - diff[max(b, d) + 1] > 0:
                return False
            if d <= b:
                return count(pre1, a, b) == count(pre2, a, b)
            if b < c:
                return (
                    diff[c] - diff[b + 1] == 0
                    and count(pre1, a, b) == count(pre2, a, b)
                    and count(pre1, c, d) == count(pre2, c, d)
                )
            cnt1 = sub(count(pre1, a, b), count(pre2, a, c - 1))
            cnt2 = sub(count(pre2, c, d), count(pre1, b + 1, d))
            return bool(cnt1) and bool(cnt2) and cnt1 == cnt2

        n = len(s)
        m = n // 2
        t = s[m:][::-1]
        s = s[:m]
        pre1 = [[0] * 26 for _ in range(m + 1)]
        pre2 = [[0] * 26 for _ in range(m + 1)]
        diff = [0] * (m + 1)
        for i, (c1, c2) in enumerate(zip(s, t), 1):
            pre1[i] = pre1[i - 1][:]
            pre2[i] = pre2[i - 1][:]
            pre1[i][ord(c1) - ord("a")] += 1
            pre2[i][ord(c2) - ord("a")] += 1
            diff[i] = diff[i - 1] + int(c1 != c2)
        ans = []
        for a, b, c, d in queries:
            c, d = n - 1 - d, n - 1 - c
            ok = (
                check(pre1, pre2, a, b, c, d)
                if a <= c
                else check(pre2, pre1, c, d, a, b)
            )
            ans.append(ok)
        return ans
```

```java
class Solution {
    public boolean[] canMakePalindromeQueries(String s, int[][] queries) {
        int n = s.length();
        int m = n / 2;
        String t = new StringBuilder(s.substring(m)).reverse().toString();
        s = s.substring(0, m);
        int[][] pre1 = new int[m + 1][0];
        int[][] pre2 = new int[m + 1][0];
        int[] diff = new int[m + 1];
        pre1[0] = new int[26];
        pre2[0] = new int[26];
        for (int i = 1; i <= m; ++i) {
            pre1[i] = pre1[i - 1].clone();
            pre2[i] = pre2[i - 1].clone();
            ++pre1[i][s.charAt(i - 1) - 'a'];
            ++pre2[i][t.charAt(i - 1) - 'a'];
            diff[i] = diff[i - 1] + (s.charAt(i - 1) == t.charAt(i - 1) ? 0 : 1);
        }
        boolean[] ans = new boolean[queries.length];
        for (int i = 0; i < queries.length; ++i) {
            int[] q = queries[i];
            int a = q[0], b = q[1];
            int c = n - 1 - q[3], d = n - 1 - q[2];
            ans[i] = a <= c ? check(pre1, pre2, diff, a, b, c, d)
                            : check(pre2, pre1, diff, c, d, a, b);
        }
        return ans;
    }

    private boolean check(int[][] pre1, int[][] pre2, int[] diff, int a, int b, int c, int d) {
        if (diff[a] > 0 || diff[diff.length - 1] - diff[Math.max(b, d) + 1] > 0) {
            return false;
        }
        if (d <= b) {
            return Arrays.equals(count(pre1, a, b), count(pre2, a, b));
        }
        if (b < c) {
            return diff[c] - diff[b + 1] == 0 && Arrays.equals(count(pre1, a, b), count(pre2, a, b))
                && Arrays.equals(count(pre1, c, d), count(pre2, c, d));
        }
        int[] cnt1 = sub(count(pre1, a, b), count(pre2, a, c - 1));
        int[] cnt2 = sub(count(pre2, c, d), count(pre1, b + 1, d));
        return cnt1 != null && cnt2 != null && Arrays.equals(cnt1, cnt2);
    }

    private int[] count(int[][] pre, int i, int j) {
        int[] cnt = new int[26];
        for (int k = 0; k < 26; ++k) {
            cnt[k] = pre[j + 1][k] - pre[i][k];
        }
        return cnt;
    }

    private int[] sub(int[] cnt1, int[] cnt2) {
        int[] cnt = new int[26];
        for (int i = 0; i < 26; ++i) {
            cnt[i] = cnt1[i] - cnt2[i];
            if (cnt[i] < 0) {
                return null;
            }
        }
        return cnt;
    }
}
```

```cpp
class Solution {
public:
    vector<bool> canMakePalindromeQueries(string s, vector<vector<int>>& queries) {
        int n = s.length();
        int m = n / 2;
        string t = string(s.begin() + m, s.end());
        reverse(t.begin(), t.end());
        s = string(s.begin(), s.begin() + m);

        vector<vector<int>> pre1(m + 1, vector<int>(26));
        vector<vector<int>> pre2(m + 1, vector<int>(26));
        vector<int> diff(m + 1, 0);
        for (int i = 1; i <= m; ++i) {
            pre1[i] = pre1[i - 1];
            pre2[i] = pre2[i - 1];
            ++pre1[i][s[i - 1] - 'a'];
            ++pre2[i][t[i - 1] - 'a'];
            diff[i] = diff[i - 1] + (s[i - 1] == t[i - 1] ? 0 : 1);
        }

        vector<bool> ans(queries.size(), false);
        for (int i = 0; i < queries.size(); ++i) {
            vector<int> q = queries[i];
            int a = q[0], b = q[1];
            int c = n - 1 - q[3], d = n - 1 - q[2];
            ans[i] = (a <= c) ? check(pre1, pre2, diff, a, b, c, d) : check(pre2, pre1, diff, c, d, a, b);
        }
        return ans;
    }

private:
    bool check(const vector<vector<int>>& pre1, const vector<vector<int>>& pre2, const vector<int>& diff, int a, int b, int c, int d) {
        if (diff[a] > 0 || diff[diff.size() - 1] - diff[max(b, d) + 1] > 0) {
            return false;
        }

        if (d <= b) {
            return count(pre1, a, b) == count(pre2, a, b);
        }

        if (b < c) {
            return diff[c] - diff[b + 1] == 0 && count(pre1, a, b) == count(pre2, a, b) && count(pre1, c, d) == count(pre2, c, d);
        }

        vector<int> cnt1 = sub(count(pre1, a, b), count(pre2, a, c - 1));
        vector<int> cnt2 = sub(count(pre2, c, d), count(pre1, b + 1, d));

        return cnt1 != vector<int>() && cnt2 != vector<int>() && cnt1 == cnt2;
    }

    vector<int> count(const vector<vector<int>>& pre, int i, int j) {
        vector<int> cnt(26);
        for (int k = 0; k < 26; ++k) {
            cnt[k] = pre[j + 1][k] - pre[i][k];
        }
        return cnt;
    }

    vector<int> sub(const vector<int>& cnt1, const vector<int>& cnt2) {
        vector<int> cnt(26);
        for (int i = 0; i < 26; ++i) {
            cnt[i] = cnt1[i] - cnt2[i];
            if (cnt[i] < 0) {
                return vector<int>();
            }
        }
        return cnt;
    }
};
```

```go
func canMakePalindromeQueries(s string, queries [][]int) (ans []bool) {
	n := len(s)
	m := n / 2
	t := reverse(s[m:])
	s = s[:m]

	pre1 := make([][]int, m+1)
	pre2 := make([][]int, m+1)
	diff := make([]int, m+1)
	pre1[0] = make([]int, 26)
	pre2[0] = make([]int, 26)

	for i := 1; i <= m; i++ {
		pre1[i] = slices.Clone(pre1[i-1])
		pre2[i] = slices.Clone(pre2[i-1])
		pre1[i][int(s[i-1]-'a')]++
		pre2[i][int(t[i-1]-'a')]++
		diff[i] = diff[i-1]
		if s[i-1] != t[i-1] {
			diff[i]++
		}
	}
	for _, q := range queries {
		a, b := q[0], q[1]
		c, d := n-1-q[3], n-1-q[2]
		if a <= c {
			ans = append(ans, check(pre1, pre2, diff, a, b, c, d))
		} else {
			ans = append(ans, check(pre2, pre1, diff, c, d, a, b))
		}
	}
	return
}

func check(pre1, pre2 [][]int, diff []int, a, b, c, d int) bool {
	if diff[a] > 0 || diff[len(diff)-1]-diff[max(b, d)+1] > 0 {
		return false
	}

	if d <= b {
		return slices.Equal(count(pre1, a, b), count(pre2, a, b))
	}

	if b < c {
		return diff[c]-diff[b+1] == 0 && slices.Equal(count(pre1, a, b), count(pre2, a, b)) && slices.Equal(count(pre1, c, d), count(pre2, c, d))
	}

	cnt1 := sub(count(pre1, a, b), count(pre2, a, c-1))
	cnt2 := sub(count(pre2, c, d), count(pre1, b+1, d))

	return !slices.Equal(cnt1, []int{}) && !slices.Equal(cnt2, []int{}) && slices.Equal(cnt1, cnt2)
}

func count(pre [][]int, i, j int) []int {
	cnt := make([]int, 26)
	for k := 0; k < 26; k++ {
		cnt[k] = pre[j+1][k] - pre[i][k]
	}
	return cnt
}

func sub(cnt1, cnt2 []int) []int {
	cnt := make([]int, 26)
	for i := 0; i < 26; i++ {
		cnt[i] = cnt1[i] - cnt2[i]
		if cnt[i] < 0 {
			return []int{}
		}
	}
	return cnt
}

func reverse(s string) string {
	runes := []rune(s)
	for i, j := 0, len(runes)-1; i < j; i, j = i+1, j-1 {
		runes[i], runes[j] = runes[j], runes[i]
	}
	return string(runes)
}
```

```ts
function canMakePalindromeQueries(s: string, queries: number[][]): boolean[] {
    const n: number = s.length;
    const m: number = n >> 1;
    const t: string = s.slice(m).split('').reverse().join('');
    s = s.slice(0, m);

    const pre1: number[][] = Array.from({ length: m + 1 }, () => Array(26).fill(0));
    const pre2: number[][] = Array.from({ length: m + 1 }, () => Array(26).fill(0));
    const diff: number[] = Array(m + 1).fill(0);
    for (let i = 1; i <= m; ++i) {
        pre1[i] = [...pre1[i - 1]];
        pre2[i] = [...pre2[i - 1]];
        ++pre1[i][s.charCodeAt(i - 1) - 'a'.charCodeAt(0)];
        ++pre2[i][t.charCodeAt(i - 1) - 'a'.charCodeAt(0)];
        diff[i] = diff[i - 1] + (s[i - 1] === t[i - 1] ? 0 : 1);
    }

    const ans: boolean[] = Array(queries.length).fill(false);
    for (let i = 0; i < queries.length; ++i) {
        const q: number[] = queries[i];
        const [a, b] = [q[0], q[1]];
        const [c, d] = [n - 1 - q[3], n - 1 - q[2]];
        ans[i] = a <= c ? check(pre1, pre2, diff, a, b, c, d) : check(pre2, pre1, diff, c, d, a, b);
    }
    return ans;
}

function check(
    pre1: number[][],
    pre2: number[][],
    diff: number[],
    a: number,
    b: number,
    c: number,
    d: number,
): boolean {
    if (diff[a] > 0 || diff[diff.length - 1] - diff[Math.max(b, d) + 1] > 0) {
        return false;
    }

    if (d <= b) {
        return arraysEqual(count(pre1, a, b), count(pre2, a, b));
    }

    if (b < c) {
        return (
            diff[c] - diff[b + 1] === 0 &&
            arraysEqual(count(pre1, a, b), count(pre2, a, b)) &&
            arraysEqual(count(pre1, c, d), count(pre2, c, d))
        );
    }

    const cnt1: number[] | null = sub(count(pre1, a, b), count(pre2, a, c - 1));
    const cnt2: number[] | null = sub(count(pre2, c, d), count(pre1, b + 1, d));

    return cnt1 !== null && cnt2 !== null && arraysEqual(cnt1, cnt2);
}

function count(pre: number[][], i: number, j: number): number[] {
    const cnt: number[] = new Array(26).fill(0);
    for (let k = 0; k < 26; ++k) {
        cnt[k] = pre[j + 1][k] - pre[i][k];
    }
    return cnt;
}

function sub(cnt1: number[], cnt2: number[]): number[] | null {
    const cnt: number[] = new Array(26).fill(0);
    for (let i = 0; i < 26; ++i) {
        cnt[i] = cnt1[i] - cnt2[i];
        if (cnt[i] < 0) {
            return null;
        }
    }
    return cnt;
}

function arraysEqual(arr1: number[], arr2: number[]): boolean {
    return JSON.stringify(arr1) === JSON.stringify(arr2);
}
```

<!-- tabs:end -->

<!-- end -->
