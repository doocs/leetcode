---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3700-3799/3714.Longest%20Balanced%20Substring%20II/README_EN.md
rating: 2201
source: Weekly Contest 471 Q3
tags:
    - Hash Table
    - String
    - Prefix Sum
---

<!-- problem:start -->

# [3714. Longest Balanced Substring II](https://leetcode.com/problems/longest-balanced-substring-ii)

[中文文档](/solution/3700-3799/3714.Longest%20Balanced%20Substring%20II/README.md)

## Description

<!-- description:start -->

<p>You are given a string <code>s</code> consisting only of the characters <code>&#39;a&#39;</code>, <code>&#39;b&#39;</code>, and <code>&#39;c&#39;</code>.</p>

<p>A <strong><span data-keyword="substring-nonempty">substring</span></strong> of <code>s</code> is called <strong>balanced</strong> if all <strong>distinct</strong> characters in the <strong>substring</strong> appear the <strong>same</strong> number of times.</p>

<p>Return the <strong>length of the longest balanced substring</strong> of <code>s</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;abbac&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">4</span></p>

<p><strong>Explanation:</strong></p>

<p>The longest balanced substring is <code>&quot;abba&quot;</code> because both distinct characters <code>&#39;a&#39;</code> and <code>&#39;b&#39;</code> each appear exactly 2 times.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;aabcc&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">3</span></p>

<p><strong>Explanation:</strong></p>

<p>The longest balanced substring is <code>&quot;abc&quot;</code> because all distinct characters <code>&#39;a&#39;</code>, <code>&#39;b&#39;</code> and <code>&#39;c&#39;</code> each appear exactly 1 time.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;aba&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong></p>

<p>One of the longest balanced substrings is <code>&quot;ab&quot;</code> because both distinct characters <code>&#39;a&#39;</code> and <code>&#39;b&#39;</code> each appear exactly 1 time. Another longest balanced substring is <code>&quot;ba&quot;</code>.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s</code> contains only the characters <code>&#39;a&#39;</code>, <code>&#39;b&#39;</code>, and <code>&#39;c&#39;</code>.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def longestBalanced(self, s: str) -> int:
        def calc1(s: str) -> int:
            res = 0
            i, n = 0, len(s)
            while i < n:
                j = i + 1
                while j < n and s[j] == s[i]:
                    j += 1
                res = max(res, j - i)
                i = j
            return res

        def calc2(s: str, a: str, b: str) -> int:
            res = 0
            i, n = 0, len(s)
            while i < n:
                while i < n and s[i] not in (a, b):
                    i += 1
                pos = {0: i - 1}
                d = 0
                while i < n and s[i] in (a, b):
                    d += 1 if s[i] == a else -1
                    if d in pos:
                        res = max(res, i - pos[d])
                    else:
                        pos[d] = i
                    i += 1
            return res

        def calc3(s: str) -> int:
            pos = {(0, 0): -1}
            cnt = Counter()
            res = 0
            for i, c in enumerate(s):
                cnt[c] += 1
                k = (cnt["a"] - cnt["b"], cnt["b"] - cnt["c"])
                if k in pos:
                    res = max(res, i - pos[k])
                else:
                    pos[k] = i
            return res

        x = calc1(s)
        y = max(calc2(s, "a", "b"), calc2(s, "b", "c"), calc2(s, "a", "c"))
        z = calc3(s)
        return max(x, y, z)
```

#### Java

```java
class Solution {
    public int longestBalanced(String s) {
        char[] cs = s.toCharArray();
        int x = calc1(cs);
        int y = Math.max(calc2(cs, 'a', 'b'), Math.max(calc2(cs, 'b', 'c'), calc2(cs, 'a', 'c')));
        int z = calc3(cs);
        return Math.max(x, Math.max(y, z));
    }

    private int calc1(char[] s) {
        int res = 0;
        int i = 0, n = s.length;
        while (i < n) {
            int j = i + 1;
            while (j < n && s[j] == s[i]) {
                j++;
            }
            res = Math.max(res, j - i);
            i = j;
        }
        return res;
    }

    private int calc2(char[] s, char a, char b) {
        int res = 0;
        int i = 0, n = s.length;
        while (i < n) {
            while (i < n && s[i] != a && s[i] != b) {
                i++;
            }
            Map<Integer, Integer> pos = new HashMap<>();
            pos.put(0, i - 1);
            int d = 0;
            while (i < n && (s[i] == a || s[i] == b)) {
                d += (s[i] == a) ? 1 : -1;
                Integer prev = pos.get(d);
                if (prev != null) {
                    res = Math.max(res, i - prev);
                } else {
                    pos.put(d, i);
                }
                i++;
            }
        }
        return res;
    }

    private int calc3(char[] s) {
        Map<Long, Integer> pos = new HashMap<>();
        pos.put(f(0, 0), -1);

        int[] cnt = new int[3];
        int res = 0;

        for (int i = 0; i < s.length; i++) {
            char c = s[i];
            ++cnt[c - 'a'];
            int x = cnt[0] - cnt[1];
            int y = cnt[1] - cnt[2];
            long k = f(x, y);

            Integer prev = pos.get(k);
            if (prev != null) {
                res = Math.max(res, i - prev);
            } else {
                pos.put(k, i);
            }
        }
        return res;
    }

    private long f(int x, int y) {
        return (x + 100000) << 20 | (y + 100000);
    }
}
```

#### C++

```cpp
class Solution {
public:
    int longestBalanced(string s) {
        int x = calc1(s);
        int y = max({calc2(s, 'a', 'b'), calc2(s, 'b', 'c'), calc2(s, 'a', 'c')});
        int z = calc3(s);
        return max({x, y, z});
    }

private:
    int calc1(const string& s) {
        int res = 0;
        int i = 0, n = s.size();
        while (i < n) {
            int j = i + 1;
            while (j < n && s[j] == s[i]) {
                ++j;
            }
            res = max(res, j - i);
            i = j;
        }
        return res;
    }

    int calc2(const string& s, char a, char b) {
        int res = 0;
        int i = 0, n = s.size();
        while (i < n) {
            while (i < n && s[i] != a && s[i] != b) {
                ++i;
            }

            unordered_map<int, int> pos;
            pos[0] = i - 1;

            int d = 0;
            while (i < n && (s[i] == a || s[i] == b)) {
                d += (s[i] == a) ? 1 : -1;
                auto it = pos.find(d);
                if (it != pos.end()) {
                    res = max(res, i - it->second);
                } else {
                    pos[d] = i;
                }
                i++;
            }
        }
        return res;
    }

    static long long f(int x, int y) {
        return ((long long) (x + 100000) << 20) | (long long) (y + 100000);
    }

    int calc3(const string& s) {
        unordered_map<long long, int> pos;
        pos[f(0, 0)] = -1;

        int cnt[3] = {0, 0, 0};
        int res = 0;

        for (int i = 0; i < (int) s.size(); i++) {
            char c = s[i];
            ++cnt[c - 'a'];
            int x = cnt[0] - cnt[1];
            int y = cnt[1] - cnt[2];
            long long k = f(x, y);

            auto it = pos.find(k);
            if (it != pos.end()) {
                res = max(res, i - it->second);
            } else {
                pos[k] = i;
            }
        }
        return res;
    }
};
```

#### Go

```go
func longestBalanced(s string) int {
	x := calc1(s)
	y := max(calc2(s, 'a', 'b'), calc2(s, 'b', 'c'), calc2(s, 'a', 'c'))
	z := calc3(s)
	return max(x, max(y, z))
}

func calc1(s string) int {
	res := 0
	n := len(s)
	i := 0
	for i < n {
		j := i + 1
		for j < n && s[j] == s[i] {
			j++
		}
		if j-i > res {
			res = j - i
		}
		i = j
	}
	return res
}

func calc2(s string, a, b byte) int {
	res := 0
	n := len(s)
	i := 0
	for i < n {
		for i < n && s[i] != a && s[i] != b {
			i++
		}
		pos := map[int]int{0: i - 1}
		d := 0
		for i < n && (s[i] == a || s[i] == b) {
			if s[i] == a {
				d++
			} else {
				d--
			}
			if prev, ok := pos[d]; ok {
				if i-prev > res {
					res = i - prev
				}
			} else {
				pos[d] = i
			}
			i++
		}
	}
	return res
}

type key struct {
	x, y int
}

func calc3(s string) int {
	pos := make(map[key]int)
	pos[key{0, 0}] = -1

	cnt := [3]int{}
	res := 0

	for i := 0; i < len(s); i++ {
		c := s[i]
		cnt[c-'a']++
		x := cnt[0] - cnt[1]
		y := cnt[1] - cnt[2]
		k := key{x, y}

		if j, ok := pos[k]; ok {
			if i-j > res {
				res = i - j
			}
		} else {
			pos[k] = i
		}
	}
	return res
}
```

#### TypeScript

```ts
function longestBalanced(s: string): number {
    const x = calc1(s);
    const y = Math.max(calc2(s, 'a', 'b'), calc2(s, 'b', 'c'), calc2(s, 'a', 'c'));
    const z = calc3(s);
    return Math.max(x, y, z);
}

function calc1(s: string): number {
    let res = 0;
    const n = s.length;
    let i = 0;
    while (i < n) {
        let j = i + 1;
        while (j < n && s[j] === s[i]) j++;
        res = Math.max(res, j - i);
        i = j;
    }
    return res;
}

function calc2(s: string, a: string, b: string): number {
    let res = 0;
    const n = s.length;
    let i = 0;

    while (i < n) {
        while (i < n && s[i] !== a && s[i] !== b) i++;

        const pos = new Map<number, number>();
        pos.set(0, i - 1);

        let d = 0;
        while (i < n && (s[i] === a || s[i] === b)) {
            d += s[i] === a ? 1 : -1;

            const prev = pos.get(d);
            if (prev !== undefined) {
                res = Math.max(res, i - prev);
            } else {
                pos.set(d, i);
            }
            i++;
        }
    }
    return res;
}

function calc3(s: string): number {
    const pos = new Map<string, number>();
    pos.set(key(0, 0), -1);

    const cnt = [0, 0, 0];
    let res = 0;

    for (let i = 0; i < s.length; i++) {
        const c = s.charCodeAt(i) - 97;
        cnt[c]++;

        const x = cnt[0] - cnt[1];
        const y = cnt[1] - cnt[2];
        const k = key(x, y);

        const prev = pos.get(k);
        if (prev !== undefined) {
            res = Math.max(res, i - prev);
        } else {
            pos.set(k, i);
        }
    }
    return res;
}

function key(x: number, y: number): string {
    return x + '#' + y;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
