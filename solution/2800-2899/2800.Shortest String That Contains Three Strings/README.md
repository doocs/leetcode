# [2800. 包含三个字符串的最短字符串](https://leetcode.cn/problems/shortest-string-that-contains-three-strings)

[English Version](/solution/2800-2899/2800.Shortest%20String%20That%20Contains%20Three%20Strings/README_EN.md)

<!-- tags:贪心,字符串,枚举 -->

<!-- difficulty:中等 -->

## 题目描述

<!-- 这里写题目描述 -->

给你三个字符串&nbsp;<code>a</code>&nbsp;，<code>b</code>&nbsp;和&nbsp;<code>c</code>&nbsp;， 你的任务是找到长度&nbsp;<strong>最短</strong>&nbsp;的字符串，且这三个字符串都是它的 <strong>子字符串</strong>&nbsp;。

<p>如果有多个这样的字符串，请你返回 <strong>字典序最小</strong>&nbsp;的一个。</p>

<p>请你返回满足题目要求的字符串。</p>

<p><strong>注意：</strong></p>

<ul>
	<li>两个长度相同的字符串 <code>a</code>&nbsp;和 <code>b</code>&nbsp;，如果在第一个不相同的字符处，<code>a</code>&nbsp;的字母在字母表中比 <code>b</code>&nbsp;的字母 <strong>靠前</strong>&nbsp;，那么字符串&nbsp;<code>a</code>&nbsp;比字符串&nbsp;<code>b</code> <strong>字典序小</strong>&nbsp;。</li>
	<li><strong>子字符串</strong>&nbsp;是一个字符串中一段连续的字符序列。</li>
</ul>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><code><span style=""><b>输入：</b></span>a</code> = "abc", <code>b</code> = "bca", <code>c</code> = "aaa"
<b>输出：</b>"aaabca"
<b>解释：</b>字符串 "aaabca" 包含所有三个字符串：a = ans[2...4] ，b = ans[3..5] ，c = ans[0..2] 。结果字符串的长度至少为 6 ，且"aaabca" 是字典序最小的一个。</pre>

<p><strong>示例 2：</strong></p>

<pre><code><span style=""><b>输入：</b></span>a</code> = "ab", <code>b</code> = "ba", <code>c</code> = "aba"
<b>输出：</b>"aba"
<strong>解释：</strong>字符串 "aba" 包含所有三个字符串：a = ans[0..1] ，b = ans[1..2] ，c = ans[0..2] 。由于 c 的长度为 3 ，结果字符串的长度至少为 3 。"aba" 是字典序最小的一个。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= a.length, b.length, c.length &lt;= 100</code></li>
	<li><code>a</code>&nbsp;，<code>b</code>&nbsp;，<code>c</code>&nbsp;只包含小写英文字母。</li>
</ul>

## 解法

### 方法一：枚举

我们枚举三个字符串的所有排列，然后对于每个排列，对三个字符串进行合并，找到最短的且字典序最小的字符串。

时间复杂度 $O(n^2)$，空间复杂度 $O(n)$。其中 $n$ 是三个字符串的长度的最大值。

<!-- tabs:start -->

```python
class Solution:
    def minimumString(self, a: str, b: str, c: str) -> str:
        def f(s: str, t: str) -> str:
            if s in t:
                return t
            if t in s:
                return s
            m, n = len(s), len(t)
            for i in range(min(m, n), 0, -1):
                if s[-i:] == t[:i]:
                    return s + t[i:]
            return s + t

        ans = ""
        for a, b, c in permutations((a, b, c)):
            s = f(f(a, b), c)
            if ans == "" or len(s) < len(ans) or (len(s) == len(ans) and s < ans):
                ans = s
        return ans
```

```java
class Solution {
    public String minimumString(String a, String b, String c) {
        String[] s = {a, b, c};
        int[][] perm = {{0, 1, 2}, {0, 2, 1}, {1, 0, 2}, {1, 2, 0}, {2, 1, 0}, {2, 0, 1}};
        String ans = "";
        for (var p : perm) {
            int i = p[0], j = p[1], k = p[2];
            String t = f(f(s[i], s[j]), s[k]);
            if ("".equals(ans) || t.length() < ans.length()
                || (t.length() == ans.length() && t.compareTo(ans) < 0)) {
                ans = t;
            }
        }
        return ans;
    }

    private String f(String s, String t) {
        if (s.contains(t)) {
            return s;
        }
        if (t.contains(s)) {
            return t;
        }
        int m = s.length(), n = t.length();
        for (int i = Math.min(m, n); i > 0; --i) {
            if (s.substring(m - i).equals(t.substring(0, i))) {
                return s + t.substring(i);
            }
        }
        return s + t;
    }
}
```

```cpp
class Solution {
public:
    string minimumString(string a, string b, string c) {
        vector<string> s = {a, b, c};
        vector<vector<int>> perm = {{0, 1, 2}, {0, 2, 1}, {1, 0, 2}, {1, 2, 0}, {2, 1, 0}, {2, 0, 1}};
        string ans = "";
        for (auto& p : perm) {
            int i = p[0], j = p[1], k = p[2];
            string t = f(f(s[i], s[j]), s[k]);
            if (ans == "" || t.size() < ans.size() || (t.size() == ans.size() && t < ans)) {
                ans = t;
            }
        }
        return ans;
    }

    string f(string s, string t) {
        if (s.find(t) != string::npos) {
            return s;
        }
        if (t.find(s) != string::npos) {
            return t;
        }
        int m = s.size(), n = t.size();
        for (int i = min(m, n); i; --i) {
            if (s.substr(m - i) == t.substr(0, i)) {
                return s + t.substr(i);
            }
        }
        return s + t;
    };
};
```

```go
func minimumString(a string, b string, c string) string {
	f := func(s, t string) string {
		if strings.Contains(s, t) {
			return s
		}
		if strings.Contains(t, s) {
			return t
		}
		m, n := len(s), len(t)
		for i := min(m, n); i > 0; i-- {
			if s[m-i:] == t[:i] {
				return s + t[i:]
			}
		}
		return s + t
	}
	s := [3]string{a, b, c}
	ans := ""
	for _, p := range [][]int{{0, 1, 2}, {0, 2, 1}, {1, 0, 2}, {1, 2, 0}, {2, 0, 1}, {2, 1, 0}} {
		i, j, k := p[0], p[1], p[2]
		t := f(f(s[i], s[j]), s[k])
		if ans == "" || len(t) < len(ans) || (len(t) == len(ans) && t < ans) {
			ans = t
		}
	}
	return ans
}
```

```ts
function minimumString(a: string, b: string, c: string): string {
    const f = (s: string, t: string): string => {
        if (s.includes(t)) {
            return s;
        }
        if (t.includes(s)) {
            return t;
        }
        const m = s.length;
        const n = t.length;
        for (let i = Math.min(m, n); i > 0; --i) {
            if (s.slice(-i) === t.slice(0, i)) {
                return s + t.slice(i);
            }
        }
        return s + t;
    };
    const s: string[] = [a, b, c];
    const perm: number[][] = [
        [0, 1, 2],
        [0, 2, 1],
        [1, 0, 2],
        [1, 2, 0],
        [2, 0, 1],
        [2, 1, 0],
    ];
    let ans = '';
    for (const [i, j, k] of perm) {
        const t = f(f(s[i], s[j]), s[k]);
        if (ans === '' || t.length < ans.length || (t.length === ans.length && t < ans)) {
            ans = t;
        }
    }
    return ans;
}
```

```rust
impl Solution {
    fn f(s1: String, s2: String) -> String {
        if s1.contains(&s2) {
            return s1;
        }
        if s2.contains(&s1) {
            return s2;
        }
        for i in 0..s1.len() {
            let s = &s1[i..];
            if s2.starts_with(s) {
                let n = s.len();
                return s1 + &s2[n..];
            }
        }
        s1 + s2.as_str()
    }

    pub fn minimum_string(a: String, b: String, c: String) -> String {
        let s = [&a, &b, &c];
        let perm = [
            [0, 1, 2],
            [0, 2, 1],
            [1, 0, 2],
            [1, 2, 0],
            [2, 0, 1],
            [2, 1, 0],
        ];
        let mut ans = String::new();
        for [i, j, k] in perm.iter() {
            let r = Self::f(Self::f(s[*i].clone(), s[*j].clone()), s[*k].clone());
            if ans == "" || r.len() < ans.len() || (r.len() == ans.len() && r < ans) {
                ans = r;
            }
        }
        ans
    }
}
```

<!-- tabs:end -->

<!-- end -->
