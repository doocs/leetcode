---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3500-3599/3597.Partition%20String/README.md
tags:
    - 字典树
    - 哈希表
    - 字符串
    - 模拟
---

<!-- problem:start -->

# [3597. 分割字符串](https://leetcode.cn/problems/partition-string)

[English Version](/solution/3500-3599/3597.Partition%20String/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个字符串 <code>s</code>，按照以下步骤将其分割为 <strong>互不相同的段&nbsp;</strong>：</p>

<ul>
	<li>从下标&nbsp;0 开始构建一个段。</li>
	<li>逐字符扩展当前段，直到该段之前未曾出现过。</li>
	<li>只要当前段是唯一的，就将其加入段列表，标记为已经出现过，并从下一个下标开始构建新的段。</li>
	<li>重复上述步骤，直到处理完整个字符串 <code>s</code>。</li>
</ul>

<p>返回字符串数组 <code>segments</code>，其中 <code>segments[i]</code> 表示创建的第 <code>i</code> 段。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">s = "abbccccd"</span></p>

<p><strong>输出：</strong> <span class="example-io">["a","b","bc","c","cc","d"]</span></p>

<p><strong>解释：</strong></p>

<table style="border: 1px solid black;">
	<tbody>
		<tr>
			<th style="border: 1px solid black;">下标</th>
			<th style="border: 1px solid black;">添加后的段</th>
			<th style="border: 1px solid black;">已经出现过的段</th>
			<th style="border: 1px solid black;">当前段是否已经出现过？</th>
			<th style="border: 1px solid black;">新段</th>
			<th style="border: 1px solid black;">更新后已经出现过的段</th>
		</tr>
		<tr>
			<td style="border: 1px solid black;">0</td>
			<td style="border: 1px solid black;">"a"</td>
			<td style="border: 1px solid black;">[]</td>
			<td style="border: 1px solid black;">否</td>
			<td style="border: 1px solid black;">""</td>
			<td style="border: 1px solid black;">["a"]</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;">"b"</td>
			<td style="border: 1px solid black;">["a"]</td>
			<td style="border: 1px solid black;">否</td>
			<td style="border: 1px solid black;">""</td>
			<td style="border: 1px solid black;">["a", "b"]</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">2</td>
			<td style="border: 1px solid black;">"b"</td>
			<td style="border: 1px solid black;">["a", "b"]</td>
			<td style="border: 1px solid black;">是</td>
			<td style="border: 1px solid black;">"b"</td>
			<td style="border: 1px solid black;">["a", "b"]</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">3</td>
			<td style="border: 1px solid black;">"bc"</td>
			<td style="border: 1px solid black;">["a", "b"]</td>
			<td style="border: 1px solid black;">否</td>
			<td style="border: 1px solid black;">""</td>
			<td style="border: 1px solid black;">["a", "b", "bc"]</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">4</td>
			<td style="border: 1px solid black;">"c"</td>
			<td style="border: 1px solid black;">["a", "b", "bc"]</td>
			<td style="border: 1px solid black;">否</td>
			<td style="border: 1px solid black;">""</td>
			<td style="border: 1px solid black;">["a", "b", "bc", "c"]</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">5</td>
			<td style="border: 1px solid black;">"c"</td>
			<td style="border: 1px solid black;">["a", "b", "bc", "c"]</td>
			<td style="border: 1px solid black;">是</td>
			<td style="border: 1px solid black;">"c"</td>
			<td style="border: 1px solid black;">["a", "b", "bc", "c"]</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">6</td>
			<td style="border: 1px solid black;">"cc"</td>
			<td style="border: 1px solid black;">["a", "b", "bc", "c"]</td>
			<td style="border: 1px solid black;">否</td>
			<td style="border: 1px solid black;">""</td>
			<td style="border: 1px solid black;">["a", "b", "bc", "c", "cc"]</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">7</td>
			<td style="border: 1px solid black;">"d"</td>
			<td style="border: 1px solid black;">["a", "b", "bc", "c", "cc"]</td>
			<td style="border: 1px solid black;">否</td>
			<td style="border: 1px solid black;">""</td>
			<td style="border: 1px solid black;">["a", "b", "bc", "c", "cc", "d"]</td>
		</tr>
	</tbody>
</table>

<p>因此，最终输出为 <code>["a", "b", "bc", "c", "cc", "d"]</code>。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">s = "aaaa"</span></p>

<p><strong>输出：</strong> <span class="example-io">["a","aa"]</span></p>

<p><strong>解释：</strong></p>

<table style="border: 1px solid black;">
	<tbody>
		<tr>
			<th style="border: 1px solid black;">下标</th>
			<th style="border: 1px solid black;">添加后的段</th>
			<th style="border: 1px solid black;">已经出现过的段</th>
			<th style="border: 1px solid black;">当前段是否已经出现过？</th>
			<th style="border: 1px solid black;">新段</th>
			<th style="border: 1px solid black;">更新后已经出现过的段</th>
		</tr>
		<tr>
			<td style="border: 1px solid black;">0</td>
			<td style="border: 1px solid black;">"a"</td>
			<td style="border: 1px solid black;">[]</td>
			<td style="border: 1px solid black;">否</td>
			<td style="border: 1px solid black;">""</td>
			<td style="border: 1px solid black;">["a"]</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;">"a"</td>
			<td style="border: 1px solid black;">["a"]</td>
			<td style="border: 1px solid black;">是</td>
			<td style="border: 1px solid black;">"a"</td>
			<td style="border: 1px solid black;">["a"]</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">2</td>
			<td style="border: 1px solid black;">"aa"</td>
			<td style="border: 1px solid black;">["a"]</td>
			<td style="border: 1px solid black;">否</td>
			<td style="border: 1px solid black;">""</td>
			<td style="border: 1px solid black;">["a", "aa"]</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">3</td>
			<td style="border: 1px solid black;">"a"</td>
			<td style="border: 1px solid black;">["a", "aa"]</td>
			<td style="border: 1px solid black;">是</td>
			<td style="border: 1px solid black;">"a"</td>
			<td style="border: 1px solid black;">["a", "aa"]</td>
		</tr>
	</tbody>
</table>

<p>因此，最终输出为 <code>["a", "aa"]</code>。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s</code> 仅包含小写英文字母。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：哈希表 + 模拟

我们可以用一个哈希表 $\textit{vis}$ 来记录已经出现过的段。然后我们遍历字符串 $s$，逐字符构建当前段 $t$，直到该段之前未曾出现过。每当我们构建出一个新的段时，就将其加入到结果列表中，并将其标记为已经出现过。

遍历结束后，返回结果列表即可。

时间复杂度 $O(n \times \sqrt{n})$，空间复杂度 $O(n)$，其中 $n$ 是字符串 $s$ 的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def partitionString(self, s: str) -> List[str]:
        vis = set()
        ans = []
        t = ""
        for c in s:
            t += c
            if t not in vis:
                vis.add(t)
                ans.append(t)
                t = ""
        return ans
```

#### Java

```java
class Solution {
    public List<String> partitionString(String s) {
        Set<String> vis = new HashSet<>();
        List<String> ans = new ArrayList<>();
        String t = "";
        for (char c : s.toCharArray()) {
            t += c;
            if (vis.add(t)) {
                ans.add(t);
                t = "";
            }
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<string> partitionString(string s) {
        unordered_set<string> vis;
        vector<string> ans;
        string t = "";
        for (char c : s) {
            t += c;
            if (!vis.contains(t)) {
                vis.insert(t);
                ans.push_back(t);
                t = "";
            }
        }
        return ans;
    }
};
```

#### Go

```go
func partitionString(s string) (ans []string) {
	vis := make(map[string]bool)
	t := ""
	for _, c := range s {
		t += string(c)
		if !vis[t] {
			vis[t] = true
			ans = append(ans, t)
			t = ""
		}
	}
	return
}
```

#### TypeScript

```ts
function partitionString(s: string): string[] {
    const vis = new Set<string>();
    const ans: string[] = [];
    let t = '';
    for (const c of s) {
        t += c;
        if (!vis.has(t)) {
            vis.add(t);
            ans.push(t);
            t = '';
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法二：字符串哈希 + 哈希表 + 模拟

我们可以使用字符串哈希来加速段的查找。具体地，我们可以为每个段计算一个哈希值，并将其存储在一个哈希表中。这样，我们就可以在常数时间内判断一个段是否已经出现过。

具体地，我们首先根据字符串 $s$ 创建一个字符串哈希类 $\textit{Hashing}$，该类支持计算字符串的哈希值。然后，我们遍历字符串 $s$，用两个指针 $l$ 和 $r$ 来表示当前段的起始和结束位置（下标从 $1$ 开始）。每次扩展 $r$，我们计算当前段的哈希值 $x$，如果该哈希值不在哈希表中，则将其加入结果列表，并将其哈希值标记为已经出现过。否则，我们继续扩展 $r$，直到找到一个新的段。

遍历结束后，返回结果列表即可。

时间复杂度 $O(n)$，空间复杂度 $O(n)$，其中 $n$ 是字符串 $s$ 的长度。

<!-- tabs:start -->

#### Python3

```python
class Hashing:
    __slots__ = ["mod", "h", "p"]

    def __init__(
        self, s: Union[str, List[str]], base: int = 13331, mod: int = 998244353
    ):
        self.mod = mod
        self.h = [0] * (len(s) + 1)
        self.p = [1] * (len(s) + 1)
        for i in range(1, len(s) + 1):
            self.h[i] = (self.h[i - 1] * base + ord(s[i - 1])) % mod
            self.p[i] = (self.p[i - 1] * base) % mod

    def query(self, l: int, r: int) -> int:
        return (self.h[r] - self.h[l - 1] * self.p[r - l + 1]) % self.mod


class Solution:
    def partitionString(self, s: str) -> List[str]:
        hashing = Hashing(s)
        vis = set()
        l = 1
        ans = []
        for r, c in enumerate(s, 1):
            x = hashing.query(l, r)
            if x not in vis:
                vis.add(x)
                ans.append(s[l - 1 : r])
                l = r + 1
        return ans
```

#### Java

```java
class Hashing {
    private final long[] p;
    private final long[] h;
    private final long mod;

    public Hashing(String word) {
        this(word, 13331, 998244353);
    }

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
    public List<String> partitionString(String s) {
        Hashing hashing = new Hashing(s);
        Set<Long> vis = new HashSet<>();
        List<String> ans = new ArrayList<>();
        for (int l = 1, r = 1; r <= s.length(); ++r) {
            long x = hashing.query(l, r);
            if (vis.add(x)) {
                ans.add(s.substring(l - 1, r));
                l = r + 1;
            }
        }
        return ans;
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
    Hashing(const string& word, long long base = 13331, long long mod = 998244353) {
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

    long long query(int l, int r) const {
        return (h[r] - h[l - 1] * p[r - l + 1] % mod + mod) % mod;
    }
};

class Solution {
public:
    vector<string> partitionString(const string& s) {
        Hashing hashing(s);
        unordered_set<long long> vis;
        vector<string> ans;
        int l = 1;
        for (int r = 1; r <= (int) s.size(); ++r) {
            long long x = hashing.query(l, r);
            if (!vis.contains(x)) {
                vis.insert(x);
                ans.push_back(s.substr(l - 1, r - l + 1));
                l = r + 1;
            }
        }
        return ans;
    }
};
```

#### Go

```go
type Hashing struct {
	p, h []int64
	mod  int64
}

func NewHashing(s string, base, mod int64) *Hashing {
	n := len(s)
	p := make([]int64, n+1)
	h := make([]int64, n+1)
	p[0] = 1
	for i := 1; i <= n; i++ {
		p[i] = p[i-1] * base % mod
		h[i] = (h[i-1]*base + int64(s[i-1])) % mod
	}
	return &Hashing{p, h, mod}
}

func (hs *Hashing) Query(l, r int) int64 {
	return (hs.h[r] - hs.h[l-1]*hs.p[r-l+1]%hs.mod + hs.mod) % hs.mod
}

func partitionString(s string) (ans []string) {
	n := len(s)
	hashing := NewHashing(s, 13331, 998244353)
	vis := make(map[int64]bool)
	l := 1
	for r := 1; r <= n; r++ {
		x := hashing.Query(l, r)
		if !vis[x] {
			vis[x] = true
			ans = append(ans, s[l-1:r])
			l = r + 1
		}
	}
	return
}
```

#### TypeScript

```ts
class Hashing {
    private p: bigint[];
    private h: bigint[];
    private mod: bigint;

    constructor(s: string, base: bigint = 13331n, mod: bigint = 998244353n) {
        const n = s.length;
        this.mod = mod;
        this.p = new Array<bigint>(n + 1).fill(1n);
        this.h = new Array<bigint>(n + 1).fill(0n);
        for (let i = 1; i <= n; i++) {
            this.p[i] = (this.p[i - 1] * base) % mod;
            this.h[i] = (this.h[i - 1] * base + BigInt(s.charCodeAt(i - 1))) % mod;
        }
    }

    query(l: number, r: number): bigint {
        return (this.h[r] - ((this.h[l - 1] * this.p[r - l + 1]) % this.mod) + this.mod) % this.mod;
    }
}

function partitionString(s: string): string[] {
    const n = s.length;
    const hashing = new Hashing(s);
    const vis = new Set<string>();
    const ans: string[] = [];
    let l = 1;
    for (let r = 1; r <= n; r++) {
        const x = hashing.query(l, r).toString();
        if (!vis.has(x)) {
            vis.add(x);
            ans.push(s.slice(l - 1, r));
            l = r + 1;
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
