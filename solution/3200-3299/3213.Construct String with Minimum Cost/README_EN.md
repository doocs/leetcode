---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3200-3299/3213.Construct%20String%20with%20Minimum%20Cost/README_EN.md
---

<!-- problem:start -->

# [3213. Construct String with Minimum Cost](https://leetcode.com/problems/construct-string-with-minimum-cost)

[中文文档](/solution/3200-3299/3213.Construct%20String%20with%20Minimum%20Cost/README.md)

## Description

<!-- description:start -->

<p>You are given a string <code>target</code>, an array of strings <code>words</code>, and an integer array <code>costs</code>, both arrays of the same length.</p>

<p>Imagine an empty string <code>s</code>.</p>

<p>You can perform the following operation any number of times (including <strong>zero</strong>):</p>

<ul>
	<li>Choose an index <code>i</code> in the range <code>[0, words.length - 1]</code>.</li>
	<li>Append <code>words[i]</code> to <code>s</code>.</li>
	<li>The cost of operation is <code>costs[i]</code>.</li>
</ul>

<p>Return the <strong>minimum</strong> cost to make <code>s</code> equal to <code>target</code>. If it&#39;s not possible, return <code>-1</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">target = &quot;abcdef&quot;, words = [&quot;abdef&quot;,&quot;abc&quot;,&quot;d&quot;,&quot;def&quot;,&quot;ef&quot;], costs = [100,1,1,10,5]</span></p>

<p><strong>Output:</strong> <span class="example-io">7</span></p>

<p><strong>Explanation:</strong></p>

<p>The minimum cost can be achieved by performing the following operations:</p>

<ul>
	<li>Select index 1 and append <code>&quot;abc&quot;</code> to <code>s</code> at a cost of 1, resulting in <code>s = &quot;abc&quot;</code>.</li>
	<li>Select index 2 and append <code>&quot;d&quot;</code> to <code>s</code> at a cost of 1, resulting in <code>s = &quot;abcd&quot;</code>.</li>
	<li>Select index 4 and append <code>&quot;ef&quot;</code> to <code>s</code> at a cost of 5, resulting in <code>s = &quot;abcdef&quot;</code>.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">target = &quot;aaaa&quot;, words = [&quot;z&quot;,&quot;zz&quot;,&quot;zzz&quot;], costs = [1,10,100]</span></p>

<p><strong>Output:</strong> <span class="example-io">-1</span></p>

<p><strong>Explanation:</strong></p>

<p>It is impossible to make <code>s</code> equal to <code>target</code>, so we return -1.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= target.length &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>1 &lt;= words.length == costs.length &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>1 &lt;= words[i].length &lt;= target.length</code></li>
	<li>The total sum of <code>words[i].length</code> is less than or equal to <code>5 * 10<sup>4</sup></code>.</li>
	<li><code>target</code> and <code>words[i]</code> consist only of lowercase English letters.</li>
	<li><code>1 &lt;= costs[i] &lt;= 10<sup>4</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: String Hashing + Dynamic Programming + Enumerating Length

We define $f[i]$ as the minimum cost to construct the first $i$ characters of $\textit{target}$, with the initial condition $f[0] = 0$ and all other values set to infinity. The answer is $f[n]$, where $n$ is the length of $\textit{target}$.

For the current $f[i]$, consider enumerating the length $j$ of the word. If $j \leq i$, then we can consider the hash value of the segment from $i - j + 1$ to $i$. If this hash value corresponds to an existing word, then we can transition from $f[i - j]$ to $f[i]$. The state transition equation is as follows:

$$
f[i] = \min(f[i], f[i - j] + \textit{cost}[k])
$$

where $\textit{cost}[k]$ represents the minimum cost of a word of length $j$ whose hash value matches $\textit{target}[i - j + 1, i]$.

The time complexity is $O(n \times \sqrt{L})$, and the space complexity is $O(n)$. Here, $n$ is the length of $\textit{target}$, and $L$ is the sum of the lengths of all words in the array $\textit{words}$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minimumCost(self, target: str, words: List[str], costs: List[int]) -> int:
        base, mod = 13331, 998244353
        n = len(target)
        h = [0] * (n + 1)
        p = [1] * (n + 1)
        for i, c in enumerate(target, 1):
            h[i] = (h[i - 1] * base + ord(c)) % mod
            p[i] = (p[i - 1] * base) % mod
        f = [0] + [inf] * n
        ss = sorted(set(map(len, words)))
        d = defaultdict(lambda: inf)
        min = lambda a, b: a if a < b else b
        for w, c in zip(words, costs):
            x = 0
            for ch in w:
                x = (x * base + ord(ch)) % mod
            d[x] = min(d[x], c)
        for i in range(1, n + 1):
            for j in ss:
                if j > i:
                    break
                x = (h[i] - h[i - j] * p[j]) % mod
                f[i] = min(f[i], f[i - j] + d[x])
        return f[n] if f[n] < inf else -1
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
    public int minimumCost(String target, String[] words, int[] costs) {
        final int base = 13331;
        final int mod = 998244353;
        final int inf = Integer.MAX_VALUE / 2;

        int n = target.length();
        Hashing hashing = new Hashing(target, base, mod);

        int[] f = new int[n + 1];
        Arrays.fill(f, inf);
        f[0] = 0;

        TreeSet<Integer> ss = new TreeSet<>();
        for (String w : words) {
            ss.add(w.length());
        }

        Map<Long, Integer> d = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            long x = 0;
            for (char c : words[i].toCharArray()) {
                x = (x * base + c) % mod;
            }
            d.merge(x, costs[i], Integer::min);
        }

        for (int i = 1; i <= n; i++) {
            for (int j : ss) {
                if (j > i) {
                    break;
                }
                long x = hashing.query(i - j + 1, i);
                f[i] = Math.min(f[i], f[i - j] + d.getOrDefault(x, inf));
            }
        }

        return f[n] >= inf ? -1 : f[n];
    }
}
```

#### C++

```cpp
class Hashing {
private:
    vector<long> p, h;
    long mod;

public:
    Hashing(const string& word, long base, long mod)
        : p(word.size() + 1, 1)
        , h(word.size() + 1, 0)
        , mod(mod) {
        for (int i = 1; i <= word.size(); ++i) {
            p[i] = p[i - 1] * base % mod;
            h[i] = (h[i - 1] * base + word[i - 1]) % mod;
        }
    }

    long query(int l, int r) {
        return (h[r] - h[l - 1] * p[r - l + 1] % mod + mod) % mod;
    }
};

class Solution {
public:
    int minimumCost(string target, vector<string>& words, vector<int>& costs) {
        const int base = 13331;
        const int mod = 998244353;
        const int inf = INT_MAX / 2;

        int n = target.size();
        Hashing hashing(target, base, mod);

        vector<int> f(n + 1, inf);
        f[0] = 0;

        set<int> ss;
        for (const string& w : words) {
            ss.insert(w.size());
        }

        unordered_map<long, int> d;
        for (int i = 0; i < words.size(); ++i) {
            long x = 0;
            for (char c : words[i]) {
                x = (x * base + c) % mod;
            }
            d[x] = d.find(x) == d.end() ? costs[i] : min(d[x], costs[i]);
        }

        for (int i = 1; i <= n; ++i) {
            for (int j : ss) {
                if (j > i) {
                    break;
                }
                long x = hashing.query(i - j + 1, i);
                if (d.contains(x)) {
                    f[i] = min(f[i], f[i - j] + d[x]);
                }
            }
        }

        return f[n] >= inf ? -1 : f[n];
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

func NewHashing(word string, base, mod int64) *Hashing {
	n := len(word)
	p := make([]int64, n+1)
	h := make([]int64, n+1)
	p[0] = 1
	for i := 1; i <= n; i++ {
		p[i] = p[i-1] * base % mod
		h[i] = (h[i-1]*base + int64(word[i-1])) % mod
	}
	return &Hashing{p, h, mod}
}

func (hs *Hashing) query(l, r int) int64 {
	return (hs.h[r] - hs.h[l-1]*hs.p[r-l+1]%hs.mod + hs.mod) % hs.mod
}

func minimumCost(target string, words []string, costs []int) int {
	const base = 13331
	const mod = 998244353
	const inf = math.MaxInt32 / 2

	n := len(target)
	hashing := NewHashing(target, base, mod)

	f := make([]int, n+1)
	for i := range f {
		f[i] = inf
	}
	f[0] = 0

	ss := make(map[int]struct{})
	for _, w := range words {
		ss[len(w)] = struct{}{}
	}
	lengths := make([]int, 0, len(ss))
	for length := range ss {
		lengths = append(lengths, length)
	}
	sort.Ints(lengths)

	d := make(map[int64]int)
	for i, w := range words {
		var x int64
		for _, c := range w {
			x = (x*base + int64(c)) % mod
		}
		if existingCost, exists := d[x]; exists {
			if costs[i] < existingCost {
				d[x] = costs[i]
			}
		} else {
			d[x] = costs[i]
		}
	}

	for i := 1; i <= n; i++ {
		for _, j := range lengths {
			if j > i {
				break
			}
			x := hashing.query(i-j+1, i)
			if cost, ok := d[x]; ok {
				f[i] = min(f[i], f[i-j]+cost)
			}
		}
	}

	if f[n] >= inf {
		return -1
	}
	return f[n]
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
