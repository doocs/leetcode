---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1000-1099/1061.Lexicographically%20Smallest%20Equivalent%20String/README_EN.md
tags:
    - Union Find
    - String
---

<!-- problem:start -->

# [1061. Lexicographically Smallest Equivalent String](https://leetcode.com/problems/lexicographically-smallest-equivalent-string)

[中文文档](/solution/1000-1099/1061.Lexicographically%20Smallest%20Equivalent%20String/README.md)

## Description

<!-- description:start -->

<p>You are given two strings of the same length <code>s1</code> and <code>s2</code> and a string <code>baseStr</code>.</p>

<p>We say <code>s1[i]</code> and <code>s2[i]</code> are equivalent characters.</p>

<ul>
	<li>For example, if <code>s1 = &quot;abc&quot;</code> and <code>s2 = &quot;cde&quot;</code>, then we have <code>&#39;a&#39; == &#39;c&#39;</code>, <code>&#39;b&#39; == &#39;d&#39;</code>, and <code>&#39;c&#39; == &#39;e&#39;</code>.</li>
</ul>

<p>Equivalent characters follow the usual rules of any equivalence relation:</p>

<ul>
	<li><strong>Reflexivity:</strong> <code>&#39;a&#39; == &#39;a&#39;</code>.</li>
	<li><strong>Symmetry:</strong> <code>&#39;a&#39; == &#39;b&#39;</code> implies <code>&#39;b&#39; == &#39;a&#39;</code>.</li>
	<li><strong>Transitivity:</strong> <code>&#39;a&#39; == &#39;b&#39;</code> and <code>&#39;b&#39; == &#39;c&#39;</code> implies <code>&#39;a&#39; == &#39;c&#39;</code>.</li>
</ul>

<p>For example, given the equivalency information from <code>s1 = &quot;abc&quot;</code> and <code>s2 = &quot;cde&quot;</code>, <code>&quot;acd&quot;</code> and <code>&quot;aab&quot;</code> are equivalent strings of <code>baseStr = &quot;eed&quot;</code>, and <code>&quot;aab&quot;</code> is the lexicographically smallest equivalent string of <code>baseStr</code>.</p>

<p>Return <em>the lexicographically smallest equivalent string of </em><code>baseStr</code><em> by using the equivalency information from </em><code>s1</code><em> and </em><code>s2</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s1 = &quot;parker&quot;, s2 = &quot;morris&quot;, baseStr = &quot;parser&quot;
<strong>Output:</strong> &quot;makkek&quot;
<strong>Explanation:</strong> Based on the equivalency information in s1 and s2, we can group their characters as [m,p], [a,o], [k,r,s], [e,i].
The characters in each group are equivalent and sorted in lexicographical order.
So the answer is &quot;makkek&quot;.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s1 = &quot;hello&quot;, s2 = &quot;world&quot;, baseStr = &quot;hold&quot;
<strong>Output:</strong> &quot;hdld&quot;
<strong>Explanation: </strong>Based on the equivalency information in s1 and s2, we can group their characters as [h,w], [d,e,o], [l,r].
So only the second letter &#39;o&#39; in baseStr is changed to &#39;d&#39;, the answer is &quot;hdld&quot;.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> s1 = &quot;leetcode&quot;, s2 = &quot;programs&quot;, baseStr = &quot;sourcecode&quot;
<strong>Output:</strong> &quot;aauaaaaada&quot;
<strong>Explanation:</strong> We group the equivalent characters in s1 and s2 as [a,o,e,r,s,c], [l,p], [g,t] and [d,m], thus all letters in baseStr except &#39;u&#39; and &#39;d&#39; are transformed to &#39;a&#39;, the answer is &quot;aauaaaaada&quot;.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s1.length, s2.length, baseStr &lt;= 1000</code></li>
	<li><code>s1.length == s2.length</code></li>
	<li><code>s1</code>, <code>s2</code>, and <code>baseStr</code> consist of lowercase English letters.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Union Find

We can use Union Find (Disjoint Set Union, DSU) to handle the equivalence relations between characters. Each character can be regarded as a node, and the equivalence relations can be seen as edges connecting these nodes. With Union Find, we can group all equivalent characters together and quickly find the representative element for each character during queries. When performing union operations, we always set the representative element to be the lexicographically smallest character. This ensures that the final string is the lexicographically smallest equivalent string.

The time complexity is $O((n + m) \times \log |\Sigma|)$ and the space complexity is $O(|\Sigma|)$, where $n$ is the length of strings $s1$ and $s2$, $m$ is the length of $baseStr$, and $|\Sigma|$ is the size of the character set, which is $26$ in this problem.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def smallestEquivalentString(self, s1: str, s2: str, baseStr: str) -> str:
        def find(x: int) -> int:
            if p[x] != x:
                p[x] = find(p[x])
            return p[x]

        p = list(range(26))
        for a, b in zip(s1, s2):
            x, y = ord(a) - ord("a"), ord(b) - ord("a")
            px, py = find(x), find(y)
            if px < py:
                p[py] = px
            else:
                p[px] = py
        return "".join(chr(find(ord(c) - ord("a")) + ord("a")) for c in baseStr)
```

#### Java

```java
class Solution {
    private final int[] p = new int[26];

    public String smallestEquivalentString(String s1, String s2, String baseStr) {
        for (int i = 0; i < p.length; ++i) {
            p[i] = i;
        }
        for (int i = 0; i < s1.length(); ++i) {
            int x = s1.charAt(i) - 'a';
            int y = s2.charAt(i) - 'a';
            int px = find(x), py = find(y);
            if (px < py) {
                p[py] = px;
            } else {
                p[px] = py;
            }
        }
        char[] s = baseStr.toCharArray();
        for (int i = 0; i < s.length; ++i) {
            s[i] = (char) ('a' + find(s[i] - 'a'));
        }
        return String.valueOf(s);
    }

    private int find(int x) {
        if (p[x] != x) {
            p[x] = find(p[x]);
        }
        return p[x];
    }
}
```

#### C++

```cpp
class Solution {
public:
    string smallestEquivalentString(string s1, string s2, string baseStr) {
        vector<int> p(26);
        iota(p.begin(), p.end(), 0);
        auto find = [&](this auto&& find, int x) -> int {
            if (p[x] != x) {
                p[x] = find(p[x]);
            }
            return p[x];
        };
        for (int i = 0; i < s1.length(); ++i) {
            int x = s1[i] - 'a';
            int y = s2[i] - 'a';
            int px = find(x), py = find(y);
            if (px < py) {
                p[py] = px;
            } else {
                p[px] = py;
            }
        }
        string s;
        for (char c : baseStr) {
            s.push_back('a' + find(c - 'a'));
        }
        return s;
    }
};
```

#### Go

```go
func smallestEquivalentString(s1 string, s2 string, baseStr string) string {
	p := make([]int, 26)
	for i := 0; i < 26; i++ {
		p[i] = i
	}

	var find func(int) int
	find = func(x int) int {
		if p[x] != x {
			p[x] = find(p[x])
		}
		return p[x]
	}

	for i := 0; i < len(s1); i++ {
		x := int(s1[i] - 'a')
		y := int(s2[i] - 'a')
		px := find(x)
		py := find(y)
		if px < py {
			p[py] = px
		} else {
			p[px] = py
		}
	}

	var s []byte
	for i := 0; i < len(baseStr); i++ {
		s = append(s, byte('a'+find(int(baseStr[i]-'a'))))
	}

	return string(s)
}
```

#### TypeScript

```ts
function smallestEquivalentString(s1: string, s2: string, baseStr: string): string {
    const p: number[] = Array.from({ length: 26 }, (_, i) => i);

    const find = (x: number): number => {
        if (p[x] !== x) {
            p[x] = find(p[x]);
        }
        return p[x];
    };

    for (let i = 0; i < s1.length; i++) {
        const x = s1.charCodeAt(i) - 'a'.charCodeAt(0);
        const y = s2.charCodeAt(i) - 'a'.charCodeAt(0);
        const px = find(x);
        const py = find(y);
        if (px < py) {
            p[py] = px;
        } else {
            p[px] = py;
        }
    }

    const s: string[] = [];
    for (let i = 0; i < baseStr.length; i++) {
        const c = baseStr.charCodeAt(i) - 'a'.charCodeAt(0);
        s.push(String.fromCharCode('a'.charCodeAt(0) + find(c)));
    }
    return s.join('');
}
```

#### Rust

```rust
impl Solution {
    pub fn smallest_equivalent_string(s1: String, s2: String, base_str: String) -> String {
        fn find(x: usize, p: &mut Vec<usize>) -> usize {
            if p[x] != x {
                p[x] = find(p[x], p);
            }
            p[x]
        }

        let mut p = (0..26).collect::<Vec<_>>();
        for (a, b) in s1.bytes().zip(s2.bytes()) {
            let x = (a - b'a') as usize;
            let y = (b - b'a') as usize;
            let px = find(x, &mut p);
            let py = find(y, &mut p);
            if px < py {
                p[py] = px;
            } else {
                p[px] = py;
            }
        }

        base_str
            .bytes()
            .map(|c| (b'a' + find((c - b'a') as usize, &mut p) as u8) as char)
            .collect()
    }
}
```

#### C#

```cs
public class Solution {
    public string SmallestEquivalentString(string s1, string s2, string baseStr) {
        int[] p = new int[26];
        for (int i = 0; i < 26; i++) {
            p[i] = i;
        }

        int Find(int x) {
            if (p[x] != x) {
                p[x] = Find(p[x]);
            }
            return p[x];
        }

        for (int i = 0; i < s1.Length; i++) {
            int x = s1[i] - 'a';
            int y = s2[i] - 'a';
            int px = Find(x);
            int py = Find(y);
            if (px < py) {
                p[py] = px;
            } else {
                p[px] = py;
            }
        }

        var res = new System.Text.StringBuilder();
        foreach (char c in baseStr) {
            int idx = Find(c - 'a');
            res.Append((char)(idx + 'a'));
        }

        return res.ToString();
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
