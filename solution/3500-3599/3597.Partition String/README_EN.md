---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3500-3599/3597.Partition%20String/README_EN.md
tags:
    - Trie
    - Hash Table
    - String
    - Simulation
---

<!-- problem:start -->

# [3597. Partition String](https://leetcode.com/problems/partition-string)

[中文文档](/solution/3500-3599/3597.Partition%20String/README.md)

## Description

<!-- description:start -->

<p>Given a string <code>s</code>, partition it into <strong>unique segments</strong> according to the following procedure:</p>

<ul>
	<li>Start building a segment beginning at index 0.</li>
	<li>Continue extending the current segment character by character until the current segment has not been seen before.</li>
	<li>Once the segment is unique, add it to your list of segments, mark it as seen, and begin a new segment from the next index.</li>
	<li>Repeat until you reach the end of <code>s</code>.</li>
</ul>

<p>Return an array of strings <code>segments</code>, where <code>segments[i]</code> is the <code>i<sup>th</sup></code> segment created.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;abbccccd&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">[&quot;a&quot;,&quot;b&quot;,&quot;bc&quot;,&quot;c&quot;,&quot;cc&quot;,&quot;d&quot;]</span></p>

<p><strong>Explanation:</strong></p>

<table style="border: 1px solid black;">
	<tbody>
		<tr>
			<th style="border: 1px solid black;">Index</th>
			<th style="border: 1px solid black;">Segment After Adding</th>
			<th style="border: 1px solid black;">Seen Segments</th>
			<th style="border: 1px solid black;">Current Segment Seen Before?</th>
			<th style="border: 1px solid black;">New Segment</th>
			<th style="border: 1px solid black;">Updated Seen Segments</th>
		</tr>
		<tr>
			<td style="border: 1px solid black;">0</td>
			<td style="border: 1px solid black;">&quot;a&quot;</td>
			<td style="border: 1px solid black;">[]</td>
			<td style="border: 1px solid black;">No</td>
			<td style="border: 1px solid black;">&quot;&quot;</td>
			<td style="border: 1px solid black;">[&quot;a&quot;]</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;">&quot;b&quot;</td>
			<td style="border: 1px solid black;">[&quot;a&quot;]</td>
			<td style="border: 1px solid black;">No</td>
			<td style="border: 1px solid black;">&quot;&quot;</td>
			<td style="border: 1px solid black;">[&quot;a&quot;, &quot;b&quot;]</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">2</td>
			<td style="border: 1px solid black;">&quot;b&quot;</td>
			<td style="border: 1px solid black;">[&quot;a&quot;, &quot;b&quot;]</td>
			<td style="border: 1px solid black;">Yes</td>
			<td style="border: 1px solid black;">&quot;b&quot;</td>
			<td style="border: 1px solid black;">[&quot;a&quot;, &quot;b&quot;]</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">3</td>
			<td style="border: 1px solid black;">&quot;bc&quot;</td>
			<td style="border: 1px solid black;">[&quot;a&quot;, &quot;b&quot;]</td>
			<td style="border: 1px solid black;">No</td>
			<td style="border: 1px solid black;">&quot;&quot;</td>
			<td style="border: 1px solid black;">[&quot;a&quot;, &quot;b&quot;, &quot;bc&quot;]</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">4</td>
			<td style="border: 1px solid black;">&quot;c&quot;</td>
			<td style="border: 1px solid black;">[&quot;a&quot;, &quot;b&quot;, &quot;bc&quot;]</td>
			<td style="border: 1px solid black;">No</td>
			<td style="border: 1px solid black;">&quot;&quot;</td>
			<td style="border: 1px solid black;">[&quot;a&quot;, &quot;b&quot;, &quot;bc&quot;, &quot;c&quot;]</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">5</td>
			<td style="border: 1px solid black;">&quot;c&quot;</td>
			<td style="border: 1px solid black;">[&quot;a&quot;, &quot;b&quot;, &quot;bc&quot;, &quot;c&quot;]</td>
			<td style="border: 1px solid black;">Yes</td>
			<td style="border: 1px solid black;">&quot;c&quot;</td>
			<td style="border: 1px solid black;">[&quot;a&quot;, &quot;b&quot;, &quot;bc&quot;, &quot;c&quot;]</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">6</td>
			<td style="border: 1px solid black;">&quot;cc&quot;</td>
			<td style="border: 1px solid black;">[&quot;a&quot;, &quot;b&quot;, &quot;bc&quot;, &quot;c&quot;]</td>
			<td style="border: 1px solid black;">No</td>
			<td style="border: 1px solid black;">&quot;&quot;</td>
			<td style="border: 1px solid black;">[&quot;a&quot;, &quot;b&quot;, &quot;bc&quot;, &quot;c&quot;, &quot;cc&quot;]</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">7</td>
			<td style="border: 1px solid black;">&quot;d&quot;</td>
			<td style="border: 1px solid black;">[&quot;a&quot;, &quot;b&quot;, &quot;bc&quot;, &quot;c&quot;, &quot;cc&quot;]</td>
			<td style="border: 1px solid black;">No</td>
			<td style="border: 1px solid black;">&quot;&quot;</td>
			<td style="border: 1px solid black;">[&quot;a&quot;, &quot;b&quot;, &quot;bc&quot;, &quot;c&quot;, &quot;cc&quot;, &quot;d&quot;]</td>
		</tr>
	</tbody>
</table>

<p>Hence, the final output is <code>[&quot;a&quot;, &quot;b&quot;, &quot;bc&quot;, &quot;c&quot;, &quot;cc&quot;, &quot;d&quot;]</code>.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;aaaa&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">[&quot;a&quot;,&quot;aa&quot;]</span></p>

<p><strong>Explanation:</strong></p>

<table style="border: 1px solid black;">
	<tbody>
		<tr>
			<th style="border: 1px solid black;">Index</th>
			<th style="border: 1px solid black;">Segment After Adding</th>
			<th style="border: 1px solid black;">Seen Segments</th>
			<th style="border: 1px solid black;">Current Segment Seen Before?</th>
			<th style="border: 1px solid black;">New Segment</th>
			<th style="border: 1px solid black;">Updated Seen Segments</th>
		</tr>
		<tr>
			<td style="border: 1px solid black;">0</td>
			<td style="border: 1px solid black;">&quot;a&quot;</td>
			<td style="border: 1px solid black;">[]</td>
			<td style="border: 1px solid black;">No</td>
			<td style="border: 1px solid black;">&quot;&quot;</td>
			<td style="border: 1px solid black;">[&quot;a&quot;]</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;">&quot;a&quot;</td>
			<td style="border: 1px solid black;">[&quot;a&quot;]</td>
			<td style="border: 1px solid black;">Yes</td>
			<td style="border: 1px solid black;">&quot;a&quot;</td>
			<td style="border: 1px solid black;">[&quot;a&quot;]</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">2</td>
			<td style="border: 1px solid black;">&quot;aa&quot;</td>
			<td style="border: 1px solid black;">[&quot;a&quot;]</td>
			<td style="border: 1px solid black;">No</td>
			<td style="border: 1px solid black;">&quot;&quot;</td>
			<td style="border: 1px solid black;">[&quot;a&quot;, &quot;aa&quot;]</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">3</td>
			<td style="border: 1px solid black;">&quot;a&quot;</td>
			<td style="border: 1px solid black;">[&quot;a&quot;, &quot;aa&quot;]</td>
			<td style="border: 1px solid black;">Yes</td>
			<td style="border: 1px solid black;">&quot;a&quot;</td>
			<td style="border: 1px solid black;">[&quot;a&quot;, &quot;aa&quot;]</td>
		</tr>
	</tbody>
</table>

<p>Hence, the final output is <code>[&quot;a&quot;, &quot;aa&quot;]</code>.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s</code> contains only lowercase English letters. </li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Hash Table + Simulation

We can use a hash table $\textit{vis}$ to record the segments that have already appeared. Then, we traverse the string $s$, building the current segment $t$ character by character until this segment has not appeared before. Each time we construct a new segment, we add it to the result list and mark it as seen.

After the traversal, we simply return the result list.

The time complexity is $O(n \times \sqrt{n})$, and the space complexity is $O(n)$, where $n$ is the length of the string $s$.

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

### Solution 2: String Hashing + Hash Table + Simulation

We can use string hashing to speed up the lookup of segments. Specifically, we can compute a hash value for each segment and store it in a hash table. In this way, we can determine in constant time whether a segment has already appeared.

In detail, we first create a string hashing class $\textit{Hashing}$ based on the string $s$, which supports computing the hash value of any substring. Then, we traverse the string $s$ using two pointers $l$ and $r$ to represent the start and end positions of the current segment (indices starting from $1$). Each time we extend $r$, we compute the hash value $x$ of the current segment. If this hash value is not in the hash table, we add the segment to the result list and mark its hash value as seen. Otherwise, we continue to extend $r$ until we find a new segment.

After the traversal, we return the result list.

The time complexity is $O(n)$, and the space complexity is $O(n)$, where $n$ is the length of the string $s$.

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
