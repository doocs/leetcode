---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0700-0799/0756.Pyramid%20Transition%20Matrix/README_EN.md
tags:
    - Bit Manipulation
    - Hash Table
    - String
    - Backtracking
---

<!-- problem:start -->

# [756. Pyramid Transition Matrix](https://leetcode.com/problems/pyramid-transition-matrix)

[中文文档](/solution/0700-0799/0756.Pyramid%20Transition%20Matrix/README.md)

## Description

<!-- description:start -->

<p>You are stacking blocks to form a pyramid. Each block has a color, which is represented by a single letter. Each row of blocks contains <strong>one less block</strong> than the row beneath it and is centered on top.</p>

<p>To make the pyramid aesthetically pleasing, there are only specific <strong>triangular patterns</strong> that are allowed. A triangular pattern consists of a <strong>single block</strong> stacked on top of <strong>two blocks</strong>. The patterns are given&nbsp;as a list of&nbsp;three-letter strings <code>allowed</code>, where the first two characters of a pattern represent the left and right bottom blocks respectively, and the third character is the top block.</p>

<ul>
	<li>For example, <code>&quot;ABC&quot;</code> represents a triangular pattern with a <code>&#39;C&#39;</code> block stacked on top of an <code>&#39;A&#39;</code> (left) and <code>&#39;B&#39;</code> (right) block. Note that this is different from <code>&quot;BAC&quot;</code> where <code>&#39;B&#39;</code> is on the left bottom and <code>&#39;A&#39;</code> is on the right bottom.</li>
</ul>

<p>You start with a bottom row of blocks <code>bottom</code>, given as a single string, that you <strong>must</strong> use as the base of the pyramid.</p>

<p>Given <code>bottom</code> and <code>allowed</code>, return <code>true</code><em> if you can build the pyramid all the way to the top such that <strong>every triangular pattern</strong> in the pyramid is in </em><code>allowed</code><em>, or </em><code>false</code><em> otherwise</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0700-0799/0756.Pyramid%20Transition%20Matrix/images/pyramid1-grid.jpg" style="width: 600px; height: 232px;" />
<pre>
<strong>Input:</strong> bottom = &quot;BCD&quot;, allowed = [&quot;BCC&quot;,&quot;CDE&quot;,&quot;CEA&quot;,&quot;FFF&quot;]
<strong>Output:</strong> true
<strong>Explanation:</strong> The allowed triangular patterns are shown on the right.
Starting from the bottom (level 3), we can build &quot;CE&quot; on level 2 and then build &quot;A&quot; on level 1.
There are three triangular patterns in the pyramid, which are &quot;BCC&quot;, &quot;CDE&quot;, and &quot;CEA&quot;. All are allowed.
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0700-0799/0756.Pyramid%20Transition%20Matrix/images/pyramid2-grid.jpg" style="width: 600px; height: 359px;" />
<pre>
<strong>Input:</strong> bottom = &quot;AAAA&quot;, allowed = [&quot;AAB&quot;,&quot;AAC&quot;,&quot;BCD&quot;,&quot;BBE&quot;,&quot;DEF&quot;]
<strong>Output:</strong> false
<strong>Explanation:</strong> The allowed triangular patterns are shown on the right.
Starting from the bottom (level 4), there are multiple ways to build level 3, but trying all the possibilites, you will get always stuck before building level 1.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= bottom.length &lt;= 6</code></li>
	<li><code>0 &lt;= allowed.length &lt;= 216</code></li>
	<li><code>allowed[i].length == 3</code></li>
	<li>The letters in all input strings are from the set <code>{&#39;A&#39;, &#39;B&#39;, &#39;C&#39;, &#39;D&#39;, &#39;E&#39;, &#39;F&#39;}</code>.</li>
	<li>All the values of <code>allowed</code> are <strong>unique</strong>.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Memoization

We define a hash table $d$ to store the allowed triangular patterns, where the key is a pair of two characters and the value is the corresponding list of characters, indicating that the two characters can form a triangular pattern with each item in the value list being the top of the triangle.

Starting from the bottom layer, for every two adjacent characters in each layer, if they can form a triangular pattern, we add the top character of the triangular pattern to the character list at the corresponding position in the next layer, then recursively process the next layer.

When the recursion reaches a single character, it means we have successfully built to the top of the pyramid, and we return $\textit{true}$. If at some layer, two adjacent characters cannot form a triangular pattern, we return $\textit{false}$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def pyramidTransition(self, bottom: str, allowed: List[str]) -> bool:
        @cache
        def dfs(s: str) -> bool:
            if len(s) == 1:
                return True
            t = []
            for a, b in pairwise(s):
                cs = d[a, b]
                if not cs:
                    return False
                t.append(cs)
            return any(dfs("".join(nxt)) for nxt in product(*t))

        d = defaultdict(list)
        for a, b, c in allowed:
            d[a, b].append(c)
        return dfs(bottom)
```

#### Java

```java
class Solution {
    private final int[][] d = new int[7][7];
    private final Map<String, Boolean> f = new HashMap<>();

    public boolean pyramidTransition(String bottom, List<String> allowed) {
        for (String s : allowed) {
            int a = s.charAt(0) - 'A', b = s.charAt(1) - 'A';
            d[a][b] |= 1 << (s.charAt(2) - 'A');
        }
        return dfs(bottom, new StringBuilder());
    }

    private boolean dfs(String s, StringBuilder t) {
        if (s.length() == 1) {
            return true;
        }
        if (t.length() + 1 == s.length()) {
            return dfs(t.toString(), new StringBuilder());
        }
        String k = s + "." + t.toString();
        Boolean res = f.get(k);
        if (res != null) {
            return res;
        }
        int a = s.charAt(t.length()) - 'A', b = s.charAt(t.length() + 1) - 'A';
        int cs = d[a][b];
        for (int i = 0; i < 7; ++i) {
            if (((cs >> i) & 1) == 1) {
                t.append((char) ('A' + i));
                if (dfs(s, t)) {
                    f.put(k, true);
                    return true;
                }
                t.setLength(t.length() - 1);
            }
        }
        f.put(k, false);
        return false;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int d[7][7];
    unordered_map<string, bool> f;

    bool pyramidTransition(string bottom, vector<string>& allowed) {
        memset(d, 0, sizeof(d));
        for (auto& s : allowed) {
            int a = s[0] - 'A', b = s[1] - 'A';
            d[a][b] |= 1 << (s[2] - 'A');
        }
        return dfs(bottom, "");
    }

    bool dfs(string& s, string t) {
        if (s.size() == 1) {
            return true;
        }
        if (t.size() + 1 == s.size()) {
            return dfs(t, "");
        }
        string k = s + "." + t;
        if (f.contains(k)) {
            return f[k];
        }
        int a = s[t.size()] - 'A', b = s[t.size() + 1] - 'A';
        int cs = d[a][b];
        for (int i = 0; i < 7; ++i) {
            if (cs >> i & 1) {
                if (dfs(s, t + (char) (i + 'A'))) {
                    f[k] = true;
                    return true;
                }
            }
        }
        f[k] = false;
        return false;
    }
};
```

#### Go

```go
func pyramidTransition(bottom string, allowed []string) bool {
	d := make([][]int, 7)
	for i := 0; i < 7; i++ {
		d[i] = make([]int, 7)
	}

	for _, s := range allowed {
		a := int(s[0] - 'A')
		b := int(s[1] - 'A')
		c := int(s[2] - 'A')
		d[a][b] |= 1 << c
	}

	f := make(map[string]bool)

	var dfs func(s string, t []byte) bool
	dfs = func(s string, t []byte) bool {
		if len(s) == 1 {
			return true
		}
		if len(t)+1 == len(s) {
			return dfs(string(t), []byte{})
		}

		key := s + "." + string(t)
		if v, ok := f[key]; ok {
			return v
		}

		i := len(t)
		a := int(s[i] - 'A')
		b := int(s[i+1] - 'A')
		cs := d[a][b]

		for c := 0; c < 7; c++ {
			if (cs>>c)&1 == 1 {
				t = append(t, byte('A'+c))
				if dfs(s, t) {
					f[key] = true
					return true
				}
				t = t[:len(t)-1]
			}
		}

		f[key] = false
		return false
	}

	return dfs(bottom, []byte{})
}
```

#### TypeScript

```ts
function pyramidTransition(bottom: string, allowed: string[]): boolean {
    const d: number[][] = Array.from({ length: 7 }, () => Array(7).fill(0));
    for (const s of allowed) {
        const a = s.charCodeAt(0) - 65;
        const b = s.charCodeAt(1) - 65;
        const c = s.charCodeAt(2) - 65;
        d[a][b] |= 1 << c;
    }

    const f = new Map<string, boolean>();

    const dfs = (s: string, t: string[]): boolean => {
        if (s.length === 1) return true;
        if (t.length + 1 === s.length) {
            return dfs(t.join(''), []);
        }

        const key = s + '.' + t.join('');
        if (f.has(key)) return f.get(key)!;

        const i = t.length;
        const a = s.charCodeAt(i) - 65;
        const b = s.charCodeAt(i + 1) - 65;
        let cs = d[a][b];

        for (let c = 0; c < 7; c++) {
            if ((cs >> c) & 1) {
                t.push(String.fromCharCode(65 + c));
                if (dfs(s, t)) {
                    f.set(key, true);
                    return true;
                }
                t.pop();
            }
        }

        f.set(key, false);
        return false;
    };

    return dfs(bottom, []);
}
```

#### Rust

```rust
use std::collections::HashMap;

impl Solution {
    pub fn pyramid_transition(bottom: String, allowed: Vec<String>) -> bool {
        let mut d = vec![vec![0; 7]; 7];
        for s in allowed {
            let a = (s.as_bytes()[0] - b'A') as usize;
            let b = (s.as_bytes()[1] - b'A') as usize;
            let c = (s.as_bytes()[2] - b'A') as usize;
            d[a][b] |= 1 << c;
        }

        let mut f = HashMap::<String, bool>::new();

        fn dfs(s: &str, t: &mut Vec<u8>, d: &Vec<Vec<i32>>, f: &mut HashMap<String, bool>) -> bool {
            if s.len() == 1 {
                return true;
            }

            if t.len() + 1 == s.len() {
                let next = String::from_utf8_lossy(t).to_string();
                let mut nt = Vec::new();
                return dfs(&next, &mut nt, d, f);
            }

            let key = format!("{}.{}", s, String::from_utf8_lossy(t));
            if let Some(&res) = f.get(&key) {
                return res;
            }

            let i = t.len();
            let a = (s.as_bytes()[i] - b'A') as usize;
            let b = (s.as_bytes()[i + 1] - b'A') as usize;
            let mut cs = d[a][b];

            for c in 0..7 {
                if (cs >> c) & 1 == 1 {
                    t.push(b'A' + c as u8);
                    if dfs(s, t, d, f) {
                        f.insert(key.clone(), true);
                        t.pop();
                        return true;
                    }
                    t.pop();
                }
            }

            f.insert(key, false);
            false
        }

        let mut t = Vec::new();
        dfs(&bottom, &mut t, &d, &mut f)
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
