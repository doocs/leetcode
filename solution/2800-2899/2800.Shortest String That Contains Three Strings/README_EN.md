# [2800. Shortest String That Contains Three Strings](https://leetcode.com/problems/shortest-string-that-contains-three-strings)

[中文文档](/solution/2800-2899/2800.Shortest%20String%20That%20Contains%20Three%20Strings/README.md)

<!-- tags:Greedy,String,Enumeration -->

## Description

Given three strings <code>a</code>, <code>b</code>, and <code>c</code>, your task is to find a string that has the<strong> minimum</strong> length and contains all three strings as <strong>substrings</strong>.

<p>If there are multiple such strings, return the<em> </em><strong>lexicographically<em> </em>smallest </strong>one.</p>

<p>Return <em>a string denoting the answer to the problem.</em></p>

<p><strong>Notes</strong></p>

<ul>
	<li>A string <code>a</code> is <strong>lexicographically smaller</strong> than a string <code>b</code> (of the same length) if in the first position where <code>a</code> and <code>b</code> differ, string <code>a</code> has a letter that appears <strong>earlier </strong>in the alphabet than the corresponding letter in <code>b</code>.</li>
	<li>A <strong>substring</strong> is a contiguous sequence of characters within a string.</li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> a = &quot;abc&quot;, b = &quot;bca&quot;, c = &quot;aaa&quot;
<strong>Output:</strong> &quot;aaabca&quot;
<strong>Explanation:</strong>  We show that &quot;aaabca&quot; contains all the given strings: a = ans[2...4], b = ans[3..5], c = ans[0..2]. It can be shown that the length of the resulting string would be at least 6 and &quot;aaabca&quot; is the lexicographically smallest one.</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> a = &quot;ab&quot;, b = &quot;ba&quot;, c = &quot;aba&quot;
<strong>Output:</strong> &quot;aba&quot;
<strong>Explanation: </strong>We show that the string &quot;aba&quot; contains all the given strings: a = ans[0..1], b = ans[1..2], c = ans[0..2]. Since the length of c is 3, the length of the resulting string would be at least 3. It can be shown that &quot;aba&quot; is the lexicographically smallest one.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= a.length, b.length, c.length &lt;= 100</code></li>
	<li><code>a</code>, <code>b</code>, <code>c</code> consist only of lowercase English letters.</li>
</ul>

## Solutions

### Solution 1: Enumeration

We enumerate all permutations of the three strings, and for each permutation, we merge the three strings to find the shortest string with the smallest lexicographical order.

The time complexity is $O(n^2)$, and the space complexity is $O(n)$. Where $n$ is the maximum length of the three strings.

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
