---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3200-3299/3213.Construct%20String%20with%20Minimum%20Cost/README.md
tags:
    - 数组
    - 字符串
    - 动态规划
    - 后缀数组
---

<!-- problem:start -->

# [3213. 最小代价构造字符串](https://leetcode.cn/problems/construct-string-with-minimum-cost)

[English Version](/solution/3200-3299/3213.Construct%20String%20with%20Minimum%20Cost/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个字符串 <code>target</code>、一个字符串数组 <code>words</code> 以及一个整数数组 <code>costs</code>，这两个数组长度相同。</p>

<p>设想一个空字符串 <code>s</code>。</p>

<p>你可以执行以下操作任意次数（包括<strong>零</strong>次）：</p>

<ul>
	<li>选择一个在范围&nbsp; <code>[0, words.length - 1]</code> 的索引 <code>i</code>。</li>
	<li>将 <code>words[i]</code> 追加到 <code>s</code>。</li>
	<li>该操作的成本是 <code>costs[i]</code>。</li>
</ul>

<p>返回使 <code>s</code> 等于 <code>target</code> 的 <strong>最小</strong> 成本。如果不可能，返回 <code>-1</code>。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">target = "abcdef", words = ["abdef","abc","d","def","ef"], costs = [100,1,1,10,5]</span></p>

<p><strong>输出：</strong> <span class="example-io">7</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li>选择索引 1 并以成本 1 将 <code>"abc"</code> 追加到 <code>s</code>，得到 <code>s = "abc"</code>。</li>
	<li>选择索引 2 并以成本 1 将 <code>"d"</code> 追加到 <code>s</code>，得到 <code>s = "abcd"</code>。</li>
	<li>选择索引 4 并以成本 5 将 <code>"ef"</code> 追加到 <code>s</code>，得到 <code>s = "abcdef"</code>。</li>
</ul>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">target = "aaaa", words = ["z","zz","zzz"], costs = [1,10,100]</span></p>

<p><strong>输出：</strong> <span class="example-io">-1</span></p>

<p><strong>解释：</strong></p>

<p>无法使 <code>s</code> 等于 <code>target</code>，因此返回 -1。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= target.length &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>1 &lt;= words.length == costs.length &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>1 &lt;= words[i].length &lt;= target.length</code></li>
	<li>所有 <code>words[i].length</code> 的总和小于或等于 <code>5 * 10<sup>4</sup></code></li>
	<li><code>target</code> 和 <code>words[i]</code> 仅由小写英文字母组成。</li>
	<li><code>1 &lt;= costs[i] &lt;= 10<sup>4</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：字符串哈希 + 动态规划 + 枚举长度

我们定义 $f[i]$ 表示构造 $\textit{target}$ 前 $i$ 个字符的最小代价，初始时 $f[0] = 0$，其余值均为无穷大。答案为 $f[n]$，其中 $n$ 是 $\textit{target}$ 的长度。

对于当前 $f[i]$，考虑枚举单词的长度 $j$，如果 $j \leq i$，那么我们可以考虑从 $i - j + 1$ 到 $i$ 这段区间的哈希值，如果这个哈希值对应的单词存在，那么我们可以转移从 $f[i - j]$ 转移到 $f[i]$。状态转移方程如下：

$$
f[i] = \min(f[i], f[i - j] + \textit{cost}[k])
$$

其中 $textit{cost}[k]$ 表示长度为 $j$ 的单词且哈希值与 $\textit{target}[i - j + 1, i]$ 相同的单词的最小代价。

时间复杂度 $O(n \times \sqrt{L})$，空间复杂度 $O(n)$。其中 $n$ 是 $\textit{target}$ 的长度，而 $L$ 是数组 $\textit{words}$ 中所有单词的长度之和。

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
