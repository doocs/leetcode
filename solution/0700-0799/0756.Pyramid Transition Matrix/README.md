---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0700-0799/0756.Pyramid%20Transition%20Matrix/README.md
tags:
    - 位运算
    - 哈希表
    - 字符串
    - 回溯
---

<!-- problem:start -->

# [756. 金字塔转换矩阵](https://leetcode.cn/problems/pyramid-transition-matrix)

[English Version](/solution/0700-0799/0756.Pyramid%20Transition%20Matrix/README_EN.md)

## 题目描述

<!-- description:start -->

<p>你正在把积木堆成金字塔。每个块都有一个颜色，用一个字母表示。每一行的块比它下面的行 <strong>少一个块</strong> ，并且居中。</p>

<p>为了使金字塔美观，只有特定的 <strong>三角形图案</strong> 是允许的。一个三角形的图案由&nbsp;<strong>两个块</strong>&nbsp;和叠在上面的 <strong>单个块</strong> 组成。模式是以三个字母字符串的列表形式&nbsp;<code>allowed</code>&nbsp;给出的，其中模式的前两个字符分别表示左右底部块，第三个字符表示顶部块。</p>

<ul>
	<li>例如，<code>"ABC"</code>&nbsp;表示一个三角形图案，其中一个 <code>“C”</code> 块堆叠在一个&nbsp;<code>'A'</code>&nbsp;块(左)和一个&nbsp;<code>'B'</code>&nbsp;块(右)之上。请注意，这与 <code>"BAC"</code>&nbsp;不同，<code>"B"</code>&nbsp;在左下角，<code>"A"</code>&nbsp;在右下角。</li>
</ul>

<p>你从作为单个字符串给出的底部的一排积木&nbsp;<code>bottom</code>&nbsp;开始，<strong>必须</strong>&nbsp;将其作为金字塔的底部。</p>

<p>在给定&nbsp;<code>bottom</code>&nbsp;和&nbsp;<code>allowed</code>&nbsp;的情况下，如果你能一直构建到金字塔顶部，使金字塔中的 <strong>每个三角形图案</strong> 都是在&nbsp;<code>allowed</code>&nbsp;中的，则返回 <code>true</code> ，否则返回 <code>false</code> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0700-0799/0756.Pyramid%20Transition%20Matrix/images/pyramid1-grid.jpg" style="height: 232px; width: 600px;" /></p>

<pre>
<strong>输入：</strong>bottom = "BCD", allowed = ["BCC","CDE","CEA","FFF"]
<strong>输出：</strong>true
<strong>解释：</strong>允许的三角形图案显示在右边。
从最底层(第 3 层)开始，我们可以在第 2 层构建“CE”，然后在第 1 层构建“A”。
金字塔中有三种三角形图案，分别是 “BCC”、“CDE” 和 “CEA”。都是允许的。
</pre>

<p><strong>示例 2：</strong></p>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0700-0799/0756.Pyramid%20Transition%20Matrix/images/pyramid2-grid.jpg" style="height: 359px; width: 600px;" /></p>

<pre>
<strong>输入：</strong>bottom = "AAAA", allowed = ["AAB","AAC","BCD","BBE","DEF"]
<strong>输出：</strong>false
<strong>解释：</strong>允许的三角形图案显示在右边。
从最底层(即第 4 层)开始，创造第 3 层有多种方法，但如果尝试所有可能性，你便会在创造第 1 层前陷入困境。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= bottom.length &lt;= 6</code></li>
	<li><code>0 &lt;= allowed.length &lt;= 216</code></li>
	<li><code>allowed[i].length == 3</code></li>
	<li>所有输入字符串中的字母来自集合&nbsp;<code>{'A', 'B', 'C', 'D', 'E', 'F'}</code>。</li>
	<li>&nbsp;<code>allowed</code>&nbsp;中所有值都是 <strong>唯一的</strong></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：记忆化搜索

我们定义一个哈希表 $d$ 来存储允许的三角形图案，其中键为两个字符，值为对应的字符列表，表示两个字符可以组成一个三角形图案，三角形图案的顶部为值列表的每一项。

从最底层开始，对于每一层的每两个相邻的字符，如果它们可以组成一个三角形图案，那么就将三角形图案的顶部字符加入到下一层的对应位置的字符列表中，然后对下一层进行递归处理。

当递归到只有一个字符时，说明已经成功构建到金字塔顶部，返回 $\textit{true}$。如果在某一层的某两个相邻字符无法组成三角形图案，则返回 $\textit{false}$。

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
