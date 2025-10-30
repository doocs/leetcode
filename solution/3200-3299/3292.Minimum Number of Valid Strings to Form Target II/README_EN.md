---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3200-3299/3292.Minimum%20Number%20of%20Valid%20Strings%20to%20Form%20Target%20II/README_EN.md
rating: 2661
source: Weekly Contest 415 Q4
tags:
    - Segment Tree
    - Array
    - String
    - Binary Search
    - Dynamic Programming
    - String Matching
    - Hash Function
    - Rolling Hash
---

<!-- problem:start -->

# [3292. Minimum Number of Valid Strings to Form Target II](https://leetcode.com/problems/minimum-number-of-valid-strings-to-form-target-ii)

[中文文档](/solution/3200-3299/3292.Minimum%20Number%20of%20Valid%20Strings%20to%20Form%20Target%20II/README.md)

## Description

<!-- description:start -->

<p>You are given an array of strings <code>words</code> and a string <code>target</code>.</p>

<p>A string <code>x</code> is called <strong>valid</strong> if <code>x</code> is a <span data-keyword="string-prefix">prefix</span> of <strong>any</strong> string in <code>words</code>.</p>

<p>Return the <strong>minimum</strong> number of <strong>valid</strong> strings that can be <em>concatenated</em> to form <code>target</code>. If it is <strong>not</strong> possible to form <code>target</code>, return <code>-1</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">words = [&quot;abc&quot;,&quot;aaaaa&quot;,&quot;bcdef&quot;], target = &quot;aabcdabc&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">3</span></p>

<p><strong>Explanation:</strong></p>

<p>The target string can be formed by concatenating:</p>

<ul>
	<li>Prefix of length 2 of <code>words[1]</code>, i.e. <code>&quot;aa&quot;</code>.</li>
	<li>Prefix of length 3 of <code>words[2]</code>, i.e. <code>&quot;bcd&quot;</code>.</li>
	<li>Prefix of length 3 of <code>words[0]</code>, i.e. <code>&quot;abc&quot;</code>.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">words = [&quot;abababab&quot;,&quot;ab&quot;], target = &quot;ababaababa&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong></p>

<p>The target string can be formed by concatenating:</p>

<ul>
	<li>Prefix of length 5 of <code>words[0]</code>, i.e. <code>&quot;ababa&quot;</code>.</li>
	<li>Prefix of length 5 of <code>words[0]</code>, i.e. <code>&quot;ababa&quot;</code>.</li>
</ul>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">words = [&quot;abcdef&quot;], target = &quot;xyz&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">-1</span></p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= words.length &lt;= 100</code></li>
	<li><code>1 &lt;= words[i].length &lt;= 5 * 10<sup>4</sup></code></li>
	<li>The input is generated such that <code>sum(words[i].length) &lt;= 10<sup>5</sup></code>.</li>
	<li><code>words[i]</code> consists only of lowercase English letters.</li>
	<li><code>1 &lt;= target.length &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>target</code> consists only of lowercase English letters.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: String Hashing + Binary Search + Greedy

Due to the large data scale of this problem, using the "Trie + Memoization" method will time out. We need to find a more efficient solution.

Consider starting from the $i$-th character of the string $\textit{target}$ and finding the maximum matching substring length, denoted as $\textit{dist}$. For any $j \in [i, i + \textit{dist} - 1]$, we can find a string in $\textit{words}$ such that $\textit{target}[i..j]$ is a prefix of this string. This has a monotonic property, so we can use binary search to determine $\textit{dist}$.

Specifically, we first preprocess the hash values of all prefixes of strings in $\textit{words}$ and store them in the array $\textit{s}$ grouped by prefix length. Additionally, we preprocess the hash values of $\textit{target}$ and store them in $\textit{hashing}$ to facilitate querying the hash value of any $\textit{target}[l..r]$.

Next, we design a function $\textit{f}(i)$ to represent the maximum matching substring length starting from the $i$-th character of the string $\textit{target}$. We can determine $\textit{f}(i)$ using binary search.

Define the left boundary of the binary search as $l = 0$ and the right boundary as $r = \min(n - i, m)$, where $n$ is the length of the string $\textit{target}$ and $m$ is the maximum length of strings in $\textit{words}$. During the binary search, we need to check if $\textit{target}[i..i+\textit{mid}-1]$ is one of the hash values in $\textit{s}[\textit{mid}]$. If it is, update the left boundary $l$ to $\textit{mid}$; otherwise, update the right boundary $r$ to $\textit{mid} - 1$. After the binary search, return $l$.

After calculating $\textit{f}(i)$, the problem becomes a classic greedy problem. Starting from $i = 0$, for each position $i$, the farthest position we can move to is $i + \textit{f}(i)$. We need to find the minimum number of moves to reach the end.

We define $\textit{last}$ to represent the last moved position and $\textit{mx}$ to represent the farthest position we can move to from the current position. Initially, $\textit{last} = \textit{mx} = 0$. We traverse from $i = 0$. If $i$ equals $\textit{last}$, it means we need to move again. If $\textit{last} = \textit{mx}$, it means we cannot move further, so we return $-1$. Otherwise, we update $\textit{last}$ to $\textit{mx}$ and increment the answer by one.

After the traversal, return the answer.

The time complexity is $O(n \times \log n + L)$, and the space complexity is $O(n + L)$. Here, $n$ is the length of the string $\textit{target}$, and $L$ is the total length of all valid strings.

<!-- tabs:start -->

#### Python3

```python
class Hashing:
    __slots__ = ["mod", "h", "p"]

    def __init__(self, s: List[str], base: int, mod: int):
        self.mod = mod
        self.h = [0] * (len(s) + 1)
        self.p = [1] * (len(s) + 1)
        for i in range(1, len(s) + 1):
            self.h[i] = (self.h[i - 1] * base + ord(s[i - 1])) % mod
            self.p[i] = (self.p[i - 1] * base) % mod

    def query(self, l: int, r: int) -> int:
        return (self.h[r] - self.h[l - 1] * self.p[r - l + 1]) % self.mod


class Solution:
    def minValidStrings(self, words: List[str], target: str) -> int:
        def f(i: int) -> int:
            l, r = 0, min(n - i, m)
            while l < r:
                mid = (l + r + 1) >> 1
                sub = hashing.query(i + 1, i + mid)
                if sub in s[mid]:
                    l = mid
                else:
                    r = mid - 1
            return l

        base, mod = 13331, 998244353
        hashing = Hashing(target, base, mod)
        m = max(len(w) for w in words)
        s = [set() for _ in range(m + 1)]
        for w in words:
            h = 0
            for j, c in enumerate(w, 1):
                h = (h * base + ord(c)) % mod
                s[j].add(h)
        ans = last = mx = 0
        n = len(target)
        for i in range(n):
            dist = f(i)
            mx = max(mx, i + dist)
            if i == last:
                if i == mx:
                    return -1
                last = mx
                ans += 1
        return ans
```

#### Java

```java
class Hashing {
    private final long[] p;
    private final long[] h;
    private final long mod;

    public Hashing(String word, long base, int mod) {
        int n = word.length();
        p = new long[n + 1];
        h = new long[n + 1];
        p[0] = 1;
        this.mod = mod;
        for (int i = 1; i <= n; i++) {
            p[i] = p[i - 1] * base % mod;
            h[i] = (h[i - 1] * base + word.charAt(i - 1)) % mod;
        }
    }

    public long query(int l, int r) {
        return (h[r] - h[l - 1] * p[r - l + 1] % mod + mod) % mod;
    }
}

class Solution {
    private Hashing hashing;
    private Set<Long>[] s;

    public int minValidStrings(String[] words, String target) {
        int base = 13331, mod = 998244353;
        hashing = new Hashing(target, base, mod);
        int m = Arrays.stream(words).mapToInt(String::length).max().orElse(0);
        s = new Set[m + 1];
        Arrays.setAll(s, k -> new HashSet<>());
        for (String w : words) {
            long h = 0;
            for (int j = 0; j < w.length(); j++) {
                h = (h * base + w.charAt(j)) % mod;
                s[j + 1].add(h);
            }
        }

        int ans = 0;
        int last = 0;
        int mx = 0;
        int n = target.length();
        for (int i = 0; i < n; i++) {
            int dist = f(i, n, m);
            mx = Math.max(mx, i + dist);
            if (i == last) {
                if (i == mx) {
                    return -1;
                }
                last = mx;
                ans++;
            }
        }
        return ans;
    }

    private int f(int i, int n, int m) {
        int l = 0, r = Math.min(n - i, m);
        while (l < r) {
            int mid = (l + r + 1) >> 1;
            long sub = hashing.query(i + 1, i + mid);
            if (s[mid].contains(sub)) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }
        return l;
    }
}
```

#### C++

```cpp
class Hashing {
private:
    vector<long long> p;
    vector<long long> h;
    long long mod;

public:
    Hashing(const string& word, long long base, int mod) {
        int n = word.size();
        p.resize(n + 1);
        h.resize(n + 1);
        p[0] = 1;
        this->mod = mod;
        for (int i = 1; i <= n; i++) {
            p[i] = (p[i - 1] * base) % mod;
            h[i] = (h[i - 1] * base + word[i - 1]) % mod;
        }
    }

    long long query(int l, int r) {
        return (h[r] - h[l - 1] * p[r - l + 1] % mod + mod) % mod;
    }
};

class Solution {
public:
    int minValidStrings(vector<string>& words, string target) {
        int base = 13331, mod = 998244353;
        Hashing hashing(target, base, mod);
        int m = 0, n = target.size();
        for (const string& word : words) {
            m = max(m, (int) word.size());
        }

        vector<unordered_set<long long>> s(m + 1);
        for (const string& w : words) {
            long long h = 0;
            for (int j = 0; j < w.size(); j++) {
                h = (h * base + w[j]) % mod;
                s[j + 1].insert(h);
            }
        }

        auto f = [&](int i) -> int {
            int l = 0, r = min(n - i, m);
            while (l < r) {
                int mid = (l + r + 1) >> 1;
                long long sub = hashing.query(i + 1, i + mid);
                if (s[mid].count(sub)) {
                    l = mid;
                } else {
                    r = mid - 1;
                }
            }
            return l;
        };

        int ans = 0, last = 0, mx = 0;
        for (int i = 0; i < n; i++) {
            int dist = f(i);
            mx = max(mx, i + dist);
            if (i == last) {
                if (i == mx) {
                    return -1;
                }
                last = mx;
                ans++;
            }
        }
        return ans;
    }
};
```

#### Go

```go
type Hashing struct {
	p   []int64
	h   []int64
	mod int64
}

func NewHashing(word string, base int64, mod int64) *Hashing {
	n := len(word)
	p := make([]int64, n+1)
	h := make([]int64, n+1)
	p[0] = 1
	for i := 1; i <= n; i++ {
		p[i] = (p[i-1] * base) % mod
		h[i] = (h[i-1]*base + int64(word[i-1])) % mod
	}
	return &Hashing{p, h, mod}
}

func (hashing *Hashing) Query(l, r int) int64 {
	return (hashing.h[r] - hashing.h[l-1]*hashing.p[r-l+1]%hashing.mod + hashing.mod) % hashing.mod
}

func minValidStrings(words []string, target string) (ans int) {
	base, mod := int64(13331), int64(998244353)
	hashing := NewHashing(target, base, mod)

	m, n := 0, len(target)
	for _, w := range words {
		m = max(m, len(w))
	}

	s := make([]map[int64]bool, m+1)

	f := func(i int) int {
		l, r := 0, int(math.Min(float64(n-i), float64(m)))
		for l < r {
			mid := (l + r + 1) >> 1
			sub := hashing.Query(i+1, i+mid)
			if s[mid][sub] {
				l = mid
			} else {
				r = mid - 1
			}
		}
		return l
	}

	for _, w := range words {
		h := int64(0)
		for j := 0; j < len(w); j++ {
			h = (h*base + int64(w[j])) % mod
			if s[j+1] == nil {
				s[j+1] = make(map[int64]bool)
			}
			s[j+1][h] = true
		}
	}

	var last, mx int

	for i := 0; i < n; i++ {
		dist := f(i)
		mx = max(mx, i+dist)
		if i == last {
			if i == mx {
				return -1
			}
			last = mx
			ans++
		}
	}
	return ans
}
```

#### TypeScript

```ts
function minValidStrings(words: string[], target: string): number {
    class Hashing {
        private p: bigint[];
        private h: bigint[];
        private mod: bigint;

        constructor(word: string, base: bigint, mod: bigint) {
            const n = word.length;
            this.p = new Array<bigint>(n + 1).fill(0n);
            this.h = new Array<bigint>(n + 1).fill(0n);
            this.mod = mod;
            this.p[0] = 1n;
            for (let i = 1; i <= n; ++i) {
                this.p[i] = (this.p[i - 1] * base) % mod;
                this.h[i] = (this.h[i - 1] * base + BigInt(word.charCodeAt(i - 1))) % mod;
            }
        }

        query(l: number, r: number): bigint {
            const res =
                (this.h[r] - ((this.h[l - 1] * this.p[r - l + 1]) % this.mod) + this.mod) %
                this.mod;
            return res;
        }
    }

    const base = 13331n;
    const mod = 998244353n;
    const hashing = new Hashing(target, base, mod);

    const m = Math.max(0, ...words.map(w => w.length));
    const s: Set<bigint>[] = Array.from({ length: m + 1 }, () => new Set<bigint>());

    for (const w of words) {
        let h = 0n;
        for (let j = 0; j < w.length; ++j) {
            h = (h * base + BigInt(w.charCodeAt(j))) % mod;
            s[j + 1].add(h);
        }
    }

    const n = target.length;
    let ans = 0;
    let last = 0;
    let mx = 0;

    const f = (i: number): number => {
        let l = 0;
        let r = Math.min(n - i, m);
        while (l < r) {
            const mid = (l + r + 1) >> 1;
            const sub = hashing.query(i + 1, i + mid);
            if (s[mid].has(sub)) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }
        return l;
    };

    for (let i = 0; i < n; ++i) {
        const dist = f(i);
        mx = Math.max(mx, i + dist);
        if (i === last) {
            if (i === mx) {
                return -1;
            }
            last = mx;
            ans++;
        }
    }

    return ans;
}
```

#### Rust

```rust
use std::collections::HashSet;
use std::cmp::max;

struct Hashing {
    p: Vec<i64>,
    h: Vec<i64>,
    base: i64,
    modv: i64,
}

impl Hashing {
    fn new(word: &str, base: i64, modv: i64) -> Self {
        let n = word.len();
        let mut p = vec![0; n + 1];
        let mut h = vec![0; n + 1];
        let bytes = word.as_bytes();
        p[0] = 1;
        for i in 1..=n {
            p[i] = p[i - 1] * base % modv;
            h[i] = (h[i - 1] * base + bytes[i - 1] as i64) % modv;
        }
        Self { p, h, base, modv }
    }

    fn query(&self, l: usize, r: usize) -> i64 {
        let mut res = self.h[r] - self.h[l - 1] * self.p[r - l + 1] % self.modv;
        if res < 0 {
            res += self.modv;
        }
        res % self.modv
    }
}

impl Solution {
    pub fn min_valid_strings(words: Vec<String>, target: String) -> i32 {
        let base = 13331;
        let modv = 998_244_353;
        let hashing = Hashing::new(&target, base, modv);
        let m = words.iter().map(|w| w.len()).max().unwrap_or(0);
        let mut s: Vec<HashSet<i64>> = vec![HashSet::new(); m + 1];

        for w in &words {
            let mut h = 0i64;
            for (j, &b) in w.as_bytes().iter().enumerate() {
                h = (h * base + b as i64) % modv;
                s[j + 1].insert(h);
            }
        }

        let n = target.len();
        let bytes = target.as_bytes();
        let mut ans = 0;
        let mut last = 0;
        let mut mx = 0;

        let f = |i: usize, n: usize, m: usize, s: &Vec<HashSet<i64>>, hashing: &Hashing| -> usize {
            let mut l = 0;
            let mut r = std::cmp::min(n - i, m);
            while l < r {
                let mid = (l + r + 1) >> 1;
                let sub = hashing.query(i + 1, i + mid);
                if s[mid].contains(&sub) {
                    l = mid;
                } else {
                    r = mid - 1;
                }
            }
            l
        };

        for i in 0..n {
            let dist = f(i, n, m, &s, &hashing);
            mx = max(mx, i + dist);
            if i == last {
                if i == mx {
                    return -1;
                }
                last = mx;
                ans += 1;
            }
        }

        ans
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
