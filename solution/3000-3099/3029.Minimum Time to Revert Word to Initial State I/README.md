---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3000-3099/3029.Minimum%20Time%20to%20Revert%20Word%20to%20Initial%20State%20I/README.md
rating: 1659
tags:
    - 字符串
    - 字符串匹配
    - 哈希函数
    - 滚动哈希
---

# [3029. 将单词恢复初始状态所需的最短时间 I](https://leetcode.cn/problems/minimum-time-to-revert-word-to-initial-state-i)

[English Version](/solution/3000-3099/3029.Minimum%20Time%20to%20Revert%20Word%20to%20Initial%20State%20I/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个下标从 <strong>0</strong> 开始的字符串 <code>word</code> 和一个整数 <code>k</code> 。</p>

<p>在每一秒，你必须执行以下操作：</p>

<ul>
	<li>移除 <code>word</code> 的前 <code>k</code> 个字符。</li>
	<li>在 <code>word</code> 的末尾添加 <code>k</code> 个任意字符。</li>
</ul>

<p><strong>注意 </strong>添加的字符不必和移除的字符相同。但是，必须在每一秒钟都执行 <strong>两种 </strong>操作。</p>

<p>返回将 <code>word</code> 恢复到其 <strong>初始 </strong>状态所需的 <strong>最短 </strong>时间（该时间必须大于零）。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<strong>输入：</strong>word = "abacaba", k = 3
<strong>输出：</strong>2
<strong>解释：</strong>
第 1 秒，移除 word 的前缀 "aba"，并在末尾添加 "bac" 。因此，word 变为 "cababac"。
第 2 秒，移除 word 的前缀 "cab"，并在末尾添加 "aba" 。因此，word 变为 "abacaba" 并恢复到始状态。
可以证明，2 秒是 word 恢复到其初始状态所需的最短时间。
</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<strong>输入：</strong>word = "abacaba", k = 4
<strong>输出：</strong>1
<strong>解释：
</strong>第 1 秒，移除 word 的前缀 "abac"，并在末尾添加 "caba" 。因此，word 变为 "abacaba" 并恢复到初始状态。
可以证明，1 秒是 word 恢复到其初始状态所需的最短时间。
</pre>

<p><strong class="example">示例 3：</strong></p>

<pre>
<strong>输入：</strong>word = "abcbabcd", k = 2
<strong>输出：</strong>4
<strong>解释：</strong>
每一秒，我们都移除 word 的前 2 个字符，并在 word 末尾添加相同的字符。
4 秒后，word 变为 "abcbabcd" 并恢复到初始状态。
可以证明，4 秒是 word 恢复到其初始状态所需的最短时间。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= word.length &lt;= 50</code></li>
	<li><code>1 &lt;= k &lt;= word.length</code></li>
	<li><code>word</code>仅由小写英文字母组成。</li>
</ul>

## 解法

### 方法一：枚举

我们不妨假设，如果只操作一次，就能使得 `word` 恢复到初始状态，那么意味着 `word[k:]` 是 `word` 的前缀，即 `word[k:] == word[:n-k]`。

如果有多次操作，不妨设 $i$ 为操作次数，那么意味着 `word[k*i:]` 是 `word` 的前缀，即 `word[k*i:] == word[:n-k*i]`。

因此，我们可以枚举操作次数，判断 `word[k*i:]` 是否是 `word` 的前缀，如果是，则返回 $i$。

时间复杂度 $O(n^2)$，空间复杂度 $O(n)$。其中 $n$ 为 `word` 的长度。

<!-- tabs:start -->

```python
class Solution:
    def minimumTimeToInitialState(self, word: str, k: int) -> int:
        n = len(word)
        for i in range(k, n, k):
            if word[i:] == word[:-i]:
                return i // k
        return (n + k - 1) // k
```

```java
class Solution {
    public int minimumTimeToInitialState(String word, int k) {
        int n = word.length();
        for (int i = k; i < n; i += k) {
            if (word.substring(i).equals(word.substring(0, n - i))) {
                return i / k;
            }
        }
        return (n + k - 1) / k;
    }
}
```

```cpp
class Solution {
public:
    int minimumTimeToInitialState(string word, int k) {
        int n = word.size();
        for (int i = k; i < n; i += k) {
            if (word.substr(i) == word.substr(0, n - i)) {
                return i / k;
            }
        }
        return (n + k - 1) / k;
    }
};
```

```go
func minimumTimeToInitialState(word string, k int) int {
	n := len(word)
	for i := k; i < n; i += k {
		if word[i:] == word[:n-i] {
			return i / k
		}
	}
	return (n + k - 1) / k
}
```

```ts
function minimumTimeToInitialState(word: string, k: number): number {
    const n = word.length;
    for (let i = k; i < n; i += k) {
        if (word.slice(i) === word.slice(0, -i)) {
            return Math.floor(i / k);
        }
    }
    return Math.floor((n + k - 1) / k);
}
```

<!-- tabs:end -->

### 方法二：枚举 + 字符串哈希

我们也可以在方法一的基础上，利用字符串哈希来判断两个字符串是否相等。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为 `word` 的长度。

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
