---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3200-3299/3292.Minimum%20Number%20of%20Valid%20Strings%20to%20Form%20Target%20II/README.md
rating: 2661
source: 第 415 场周赛 Q4
tags:
    - 线段树
    - 数组
    - 字符串
    - 二分查找
    - 动态规划
    - 字符串匹配
    - 哈希函数
    - 滚动哈希
---

<!-- problem:start -->

# [3292. 形成目标字符串需要的最少字符串数 II](https://leetcode.cn/problems/minimum-number-of-valid-strings-to-form-target-ii)

[English Version](/solution/3200-3299/3292.Minimum%20Number%20of%20Valid%20Strings%20to%20Form%20Target%20II/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个字符串数组 <code>words</code> 和一个字符串 <code>target</code>。</p>

<p>如果字符串 <code>x</code> 是 <code>words</code> 中<strong> 任意 </strong>字符串的 <span data-keyword="string-prefix">前缀</span>，则认为 <code>x</code> 是一个 <strong>有效</strong> 字符串。</p>

<p>现计划通过 <strong>连接 </strong>有效字符串形成 <code>target</code> ，请你计算并返回需要连接的 <strong>最少 </strong>字符串数量。如果无法通过这种方式形成 <code>target</code>，则返回 <code>-1</code>。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">words = ["abc","aaaaa","bcdef"], target = "aabcdabc"</span></p>

<p><strong>输出：</strong> <span class="example-io">3</span></p>

<p><strong>解释：</strong></p>

<p>target 字符串可以通过连接以下有效字符串形成：</p>

<ul>
	<li><code>words[1]</code> 的长度为 2 的前缀，即 <code>"aa"</code>。</li>
	<li><code>words[2]</code> 的长度为 3 的前缀，即 <code>"bcd"</code>。</li>
	<li><code>words[0]</code> 的长度为 3 的前缀，即 <code>"abc"</code>。</li>
</ul>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">words = ["abababab","ab"], target = "ababaababa"</span></p>

<p><strong>输出：</strong> <span class="example-io">2</span></p>

<p><strong>解释：</strong></p>

<p>target 字符串可以通过连接以下有效字符串形成：</p>

<ul>
	<li><code>words[0]</code> 的长度为 5 的前缀，即 <code>"ababa"</code>。</li>
	<li><code>words[0]</code> 的长度为 5 的前缀，即 <code>"ababa"</code>。</li>
</ul>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">words = ["abcdef"], target = "xyz"</span></p>

<p><strong>输出：</strong> <span class="example-io">-1</span></p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= words.length &lt;= 100</code></li>
	<li><code>1 &lt;= words[i].length &lt;= 5 * 10<sup>4</sup></code></li>
	<li>输入确保 <code>sum(words[i].length) &lt;= 10<sup>5</sup></code>.</li>
	<li><code>words[i]</code> &nbsp;只包含小写英文字母。</li>
	<li><code>1 &lt;= target.length &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>target</code> &nbsp;只包含小写英文字母。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一

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

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
