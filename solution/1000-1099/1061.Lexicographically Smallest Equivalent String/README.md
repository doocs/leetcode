---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1000-1099/1061.Lexicographically%20Smallest%20Equivalent%20String/README.md
tags:
    - 并查集
    - 字符串
---

<!-- problem:start -->

# [1061. 按字典序排列最小的等效字符串](https://leetcode.cn/problems/lexicographically-smallest-equivalent-string)

[English Version](/solution/1000-1099/1061.Lexicographically%20Smallest%20Equivalent%20String/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给出长度相同的两个字符串<code>s1</code> 和&nbsp;<code>s2</code>&nbsp;，还有一个字符串&nbsp;<code>baseStr</code>&nbsp;。</p>

<p>其中 &nbsp;<code>s1[i]</code>&nbsp;和&nbsp;<code>s2[i]</code>&nbsp; 是一组等价字符。</p>

<ul>
	<li>举个例子，如果&nbsp;<code>s1 = "abc"</code> 且&nbsp;<code>s2 = "cde"</code>，那么就有&nbsp;<code>'a' == 'c', 'b' == 'd', 'c' == 'e'</code>。</li>
</ul>

<p>等价字符遵循任何等价关系的一般规则：</p>

<ul>
	<li><strong>&nbsp;自反性&nbsp;</strong>：<code>'a' == 'a'</code></li>
	<li>&nbsp;<strong>对称性&nbsp;</strong>：<code>'a' == 'b'</code> 则必定有 <code>'b' == 'a'</code></li>
	<li>&nbsp;<strong>传递性</strong> ：<code>'a' == 'b'</code> 且 <code>'b' == 'c'</code> 就表明 <code>'a' == 'c'</code></li>
</ul>

<p>例如，&nbsp;<code>s1 = "abc"</code>&nbsp;和&nbsp;<code>s2 = "cde"</code>&nbsp;的等价信息和之前的例子一样，那么&nbsp;<code>baseStr = "eed"</code>&nbsp;, <code>"acd"</code>&nbsp;或&nbsp;<code>"aab"</code>，这三个字符串都是等价的，而&nbsp;<code>"aab"</code>&nbsp;是&nbsp;<code>baseStr</code>&nbsp;的按字典序最小的等价字符串</p>

<p>利用<em>&nbsp;</em><code>s1</code>&nbsp;和&nbsp;<code>s2</code>&nbsp;的等价信息，找出并返回<em>&nbsp;</em><code>baseStr</code><em>&nbsp;</em>的按字典序排列最小的等价字符串。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>s1 = "parker", s2 = "morris", baseStr = "parser"
<strong>输出：</strong>"makkek"
<strong>解释：</strong>根据 <code>A</code> 和 <code>B</code> 中的等价信息，我们可以将这些字符分为 <code>[m,p]</code>, <code>[a,o]</code>, <code>[k,r,s]</code>, <code>[e,i]</code> 共 4 组。每组中的字符都是等价的，并按字典序排列。所以答案是 <code>"makkek"</code>。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>s1 = "hello", s2 = "world", baseStr = "hold"
<strong>输出：</strong>"hdld"
<strong>解释：</strong>根据 <code>A</code> 和 <code>B</code> 中的等价信息，我们可以将这些字符分为 <code>[h,w]</code>, <code>[d,e,o]</code>, <code>[l,r]</code> 共 3 组。所以只有 S 中的第二个字符 <code>'o'</code> 变成 <code>'d'</code>，最后答案为 <code>"hdld"</code>。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>s1 = "leetcode", s2 = "programs", baseStr = "sourcecode"
<strong>输出：</strong>"aauaaaaada"
<strong>解释：</strong>我们可以把 <code>A</code> 和 <code>B</code> 中的等价字符分为 <code>[a,o,e,r,s,c]</code>, <code>[l,p]</code>, <code>[g,t]</code> 和 <code>[d,m]</code> 共 4 组，因此 <code>S</code> 中除了 <code>'u'</code> 和 <code>'d'</code> 之外的所有字母都转化成了 <code>'a'</code>，最后答案为 <code>"aauaaaaada"</code>。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s1.length, s2.length, baseStr &lt;= 1000</code></li>
	<li><code>s1.length == s2.length</code></li>
	<li>字符串<code>s1</code>,&nbsp;<code>s2</code>, and&nbsp;<code>baseStr</code>&nbsp;仅由从&nbsp;<code>'a'</code> 到&nbsp;<code>'z'</code>&nbsp;的小写英文字母组成。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：并查集

我们可以使用并查集来处理等价字符的关系。每个字符可以看作一个节点，等价关系可以看作是连接这些节点的边。通过并查集，我们可以将所有等价的字符归为一类，并且在查询时能够快速找到每个字符的代表元素。我们在进行合并操作时，始终将代表元素设置为字典序最小的字符，这样可以确保最终得到的字符串是按字典序排列的最小等价字符串。

时间复杂度 $O((n + m) \times \log |\Sigma|)$，空间复杂度 $O(|\Sigma|)$。其中 $n$ 是字符串 $s1$ 和 $s2$ 的长度，而 $m$ 是字符串 $baseStr$ 的长度，而 $|\Sigma|$ 是字符集的大小，本题中 $|\Sigma| = 26$。

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
