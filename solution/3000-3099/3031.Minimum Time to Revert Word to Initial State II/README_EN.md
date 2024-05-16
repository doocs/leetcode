---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3000-3099/3031.Minimum%20Time%20to%20Revert%20Word%20to%20Initial%20State%20II/README_EN.md
rating: 2277
source: Weekly Contest 383 Q4
tags:
    - String
    - String Matching
    - Hash Function
    - Rolling Hash
---

# [3031. Minimum Time to Revert Word to Initial State II](https://leetcode.com/problems/minimum-time-to-revert-word-to-initial-state-ii)

[中文文档](/solution/3000-3099/3031.Minimum%20Time%20to%20Revert%20Word%20to%20Initial%20State%20II/README.md)

## Description

<p>You are given a <strong>0-indexed</strong> string <code>word</code> and an integer <code>k</code>.</p>

<p>At every second, you must perform the following operations:</p>

<ul>
	<li>Remove the first <code>k</code> characters of <code>word</code>.</li>
	<li>Add any <code>k</code> characters to the end of <code>word</code>.</li>
</ul>

<p><strong>Note</strong> that you do not necessarily need to add the same characters that you removed. However, you must perform <strong>both</strong> operations at every second.</p>

<p>Return <em>the <strong>minimum</strong> time greater than zero required for</em> <code>word</code> <em>to revert to its <strong>initial</strong> state</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> word = &quot;abacaba&quot;, k = 3
<strong>Output:</strong> 2
<strong>Explanation:</strong> At the 1st second, we remove characters &quot;aba&quot; from the prefix of word, and add characters &quot;bac&quot; to the end of word. Thus, word becomes equal to &quot;cababac&quot;.
At the 2nd second, we remove characters &quot;cab&quot; from the prefix of word, and add &quot;aba&quot; to the end of word. Thus, word becomes equal to &quot;abacaba&quot; and reverts to its initial state.
It can be shown that 2 seconds is the minimum time greater than zero required for word to revert to its initial state.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> word = &quot;abacaba&quot;, k = 4
<strong>Output:</strong> 1
<strong>Explanation:</strong> At the 1st second, we remove characters &quot;abac&quot; from the prefix of word, and add characters &quot;caba&quot; to the end of word. Thus, word becomes equal to &quot;abacaba&quot; and reverts to its initial state.
It can be shown that 1 second is the minimum time greater than zero required for word to revert to its initial state.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> word = &quot;abcbabcd&quot;, k = 2
<strong>Output:</strong> 4
<strong>Explanation:</strong> At every second, we will remove the first 2 characters of word, and add the same characters to the end of word.
After 4 seconds, word becomes equal to &quot;abcbabcd&quot; and reverts to its initial state.
It can be shown that 4 seconds is the minimum time greater than zero required for word to revert to its initial state.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= word.length &lt;= 10<sup>6</sup></code></li>
	<li><code>1 &lt;= k &lt;= word.length</code></li>
	<li><code>word</code> consists only of lowercase English letters.</li>
</ul>

## Solutions

### Solution 1

<!-- tabs:start -->

```python
class Hashing:
    __slots__ = ["mod", "h", "p"]

    def __init__(self, s: str, base: int, mod: int):
        self.mod = mod
        self.h = [0] * (len(s) + 1)
        self.p = [1] * (len(s) + 1)
        for i in range(1, len(s) + 1):
            self.h[i] = (self.h[i - 1] * base + ord(s[i - 1])) % mod
            self.p[i] = (self.p[i - 1] * base) % mod

    def query(self, l: int, r: int) -> int:
        return (self.h[r] - self.h[l - 1] * self.p[r - l + 1]) % self.mod


class Solution:
    def minimumTimeToInitialState(self, word: str, k: int) -> int:
        hashing = Hashing(word, 13331, 998244353)
        n = len(word)
        for i in range(k, n, k):
            if hashing.query(1, n - i) == hashing.query(i + 1, n):
                return i // k
        return (n + k - 1) // k
```

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
            h[i] = (h[i - 1] * base + word.charAt(i - 1) - 'a') % mod;
        }
    }

    public long query(int l, int r) {
        return (h[r] - h[l - 1] * p[r - l + 1] % mod + mod) % mod;
    }
}

class Solution {
    public int minimumTimeToInitialState(String word, int k) {
        Hashing hashing = new Hashing(word, 13331, 998244353);
        int n = word.length();
        for (int i = k; i < n; i += k) {
            if (hashing.query(1, n - i) == hashing.query(i + 1, n)) {
                return i / k;
            }
        }
        return (n + k - 1) / k;
    }
}
```

```cpp
class Hashing {
private:
    vector<long long> p;
    vector<long long> h;
    long long mod;

public:
    Hashing(string word, long long base, int mod) {
        int n = word.size();
        p.resize(n + 1);
        h.resize(n + 1);
        p[0] = 1;
        this->mod = mod;
        for (int i = 1; i <= n; i++) {
            p[i] = (p[i - 1] * base) % mod;
            h[i] = (h[i - 1] * base + word[i - 1] - 'a') % mod;
        }
    }

    long long query(int l, int r) {
        return (h[r] - h[l - 1] * p[r - l + 1] % mod + mod) % mod;
    }
};

class Solution {
public:
    int minimumTimeToInitialState(string word, int k) {
        Hashing hashing(word, 13331, 998244353);
        int n = word.size();
        for (int i = k; i < n; i += k) {
            if (hashing.query(1, n - i) == hashing.query(i + 1, n)) {
                return i / k;
            }
        }
        return (n + k - 1) / k;
    }
};
```

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
		h[i] = (h[i-1]*base + int64(word[i-1]-'a')) % mod
	}
	return &Hashing{p, h, mod}
}

func (hashing *Hashing) Query(l, r int) int64 {
	return (hashing.h[r] - hashing.h[l-1]*hashing.p[r-l+1]%hashing.mod + hashing.mod) % hashing.mod
}

func minimumTimeToInitialState(word string, k int) int {
	hashing := NewHashing(word, 13331, 998244353)
	n := len(word)
	for i := k; i < n; i += k {
		if hashing.Query(1, n-i) == hashing.Query(i+1, n) {
			return i / k
		}
	}
	return (n + k - 1) / k
}
```

<!-- tabs:end -->

<!-- end -->
