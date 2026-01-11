---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3700-3799/3777.Minimum%20Deletions%20to%20Make%20Alternating%20Substring/README_EN.md
rating: 2201
source: Weekly Contest 480 Q4
tags:
    - Segment Tree
    - String
---

<!-- problem:start -->

# [3777. Minimum Deletions to Make Alternating Substring](https://leetcode.com/problems/minimum-deletions-to-make-alternating-substring)

[中文文档](/solution/3700-3799/3777.Minimum%20Deletions%20to%20Make%20Alternating%20Substring/README.md)

## Description

<!-- description:start -->

<p>You are given a string <code>s</code> of length <code>n</code> consisting only of the characters <code>&#39;A&#39;</code> and <code>&#39;B&#39;</code>.</p>

<p>You are also given a 2D integer array <code>queries</code> of length <code>q</code>, where each <code>queries[i]</code> is one of the following:</p>

<ul>
	<li><code>[1, j]</code>: <strong>Flip</strong> the character at index <code>j</code> of <code>s</code> i.e. <code>&#39;A&#39;</code> changes to <code>&#39;B&#39;</code> (and vice versa). This operation mutates <code>s</code> and affects subsequent queries.</li>
	<li><code>[2, l, r]</code>: <strong>Compute</strong> the <strong>minimum</strong> number of character deletions required to make the <strong>substring</strong> <code>s[l..r]</code> <strong>alternating</strong>. This operation does not modify <code>s</code>; the length of <code>s</code> remains <code>n</code>.</li>
</ul>

<p>A <strong><span data-keyword="substring-nonempty">substring</span></strong> is <strong>alternating</strong> if no two <strong>adjacent</strong> characters are <strong>equal</strong>. A substring of length 1 is always alternating.</p>

<p>Return an integer array <code>answer</code>, where <code>answer[i]</code> is the result of the <code>i<sup>th</sup></code> query of type <code>[2, l, r]</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;ABA&quot;, queries = [[2,1,2],[1,1],[2,0,2]]</span></p>

<p><strong>Output:</strong> <span class="example-io">[0,2]</span></p>

<p><strong>Explanation:</strong></p>

<table style="border: 1px solid black;">
	<thead>
		<tr>
			<th align="center" style="border: 1px solid black;"><code><strong>i</strong></code></th>
			<th align="center" style="border: 1px solid black;"><code><strong>queries[i]</strong></code></th>
			<th align="center" style="border: 1px solid black;"><code><strong>j</strong></code></th>
			<th align="center" style="border: 1px solid black;"><code><strong>l</strong></code></th>
			<th align="center" style="border: 1px solid black;"><code><strong>r</strong></code></th>
			<th align="center" style="border: 1px solid black;"><strong><code>s</code> before query</strong></th>
			<th align="center" style="border: 1px solid black;"><code><strong>s[l..r]</strong></code></th>
			<th align="center" style="border: 1px solid black;"><strong>Result</strong></th>
			<th align="center" style="border: 1px solid black;"><strong>Answer</strong></th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td align="center" style="border: 1px solid black;">0</td>
			<td align="center" style="border: 1px solid black;">[2, 1, 2]</td>
			<td align="center" style="border: 1px solid black;">-</td>
			<td align="center" style="border: 1px solid black;">1</td>
			<td align="center" style="border: 1px solid black;">2</td>
			<td align="center" style="border: 1px solid black;"><code>&quot;ABA&quot;</code></td>
			<td align="center" style="border: 1px solid black;"><code>&quot;BA&quot;</code></td>
			<td align="center" style="border: 1px solid black;">Already alternating</td>
			<td align="center" style="border: 1px solid black;">0</td>
		</tr>
		<tr>
			<td align="center" style="border: 1px solid black;">1</td>
			<td align="center" style="border: 1px solid black;">[1, 1]</td>
			<td align="center" style="border: 1px solid black;">1</td>
			<td align="center" style="border: 1px solid black;">-</td>
			<td align="center" style="border: 1px solid black;">-</td>
			<td align="center" style="border: 1px solid black;"><code>&quot;ABA&quot;</code></td>
			<td align="center" style="border: 1px solid black;">-</td>
			<td align="center" style="border: 1px solid black;">Flip <code>s[1]</code> from <code>&#39;B&#39;</code> to <code>&#39;A&#39;</code></td>
			<td align="center" style="border: 1px solid black;">-</td>
		</tr>
		<tr>
			<td align="center" style="border: 1px solid black;">2</td>
			<td align="center" style="border: 1px solid black;">[2, 0, 2]</td>
			<td align="center" style="border: 1px solid black;">-</td>
			<td align="center" style="border: 1px solid black;">0</td>
			<td align="center" style="border: 1px solid black;">2</td>
			<td align="center" style="border: 1px solid black;"><code>&quot;AAA&quot;</code></td>
			<td align="center" style="border: 1px solid black;"><code>&quot;AAA&quot;</code></td>
			<td align="center" style="border: 1px solid black;">Delete any two <code>&#39;A&#39;</code>s to get <code>&quot;A&quot;</code></td>
			<td align="center" style="border: 1px solid black;">2</td>
		</tr>
	</tbody>
</table>

<p>Thus, the answer is <code>[0, 2]</code>.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;ABB&quot;, queries = [[2,0,2],[1,2],[2,0,2]]</span></p>

<p><strong>Output:</strong> <span class="example-io">[1,0]</span></p>

<p><strong>Explanation:</strong></p>

<table style="border: 1px solid black;">
	<thead>
		<tr>
			<th align="center" style="border: 1px solid black;"><code><strong>i</strong></code></th>
			<th align="center" style="border: 1px solid black;"><code><strong>queries[i]</strong></code></th>
			<th align="center" style="border: 1px solid black;"><code><strong>j</strong></code></th>
			<th align="center" style="border: 1px solid black;"><code><strong>l</strong></code></th>
			<th align="center" style="border: 1px solid black;"><code><strong>r</strong></code></th>
			<th align="center" style="border: 1px solid black;"><strong><code>s</code> before query</strong></th>
			<th align="center" style="border: 1px solid black;"><code><strong>s[l..r]</strong></code></th>
			<th align="center" style="border: 1px solid black;"><strong>Result</strong></th>
			<th align="center" style="border: 1px solid black;"><strong>Answer</strong></th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td align="center" style="border: 1px solid black;">0</td>
			<td align="center" style="border: 1px solid black;">[2, 0, 2]</td>
			<td align="center" style="border: 1px solid black;">-</td>
			<td align="center" style="border: 1px solid black;">0</td>
			<td align="center" style="border: 1px solid black;">2</td>
			<td align="center" style="border: 1px solid black;"><code>&quot;ABB&quot;</code></td>
			<td align="center" style="border: 1px solid black;"><code>&quot;ABB&quot;</code></td>
			<td align="center" style="border: 1px solid black;">Delete one <code>&#39;B&#39;</code> to get <code>&quot;AB&quot;</code></td>
			<td align="center" style="border: 1px solid black;">1</td>
		</tr>
		<tr>
			<td align="center" style="border: 1px solid black;">1</td>
			<td align="center" style="border: 1px solid black;">[1, 2]</td>
			<td align="center" style="border: 1px solid black;">2</td>
			<td align="center" style="border: 1px solid black;">-</td>
			<td align="center" style="border: 1px solid black;">-</td>
			<td align="center" style="border: 1px solid black;"><code>&quot;ABB&quot;</code></td>
			<td align="center" style="border: 1px solid black;">-</td>
			<td align="center" style="border: 1px solid black;">Flip <code>s[2]</code> from <code>&#39;B&#39;</code> to <code>&#39;A&#39;</code></td>
			<td align="center" style="border: 1px solid black;">-</td>
		</tr>
		<tr>
			<td align="center" style="border: 1px solid black;">2</td>
			<td align="center" style="border: 1px solid black;">[2, 0, 2]</td>
			<td align="center" style="border: 1px solid black;">-</td>
			<td align="center" style="border: 1px solid black;">0</td>
			<td align="center" style="border: 1px solid black;">2</td>
			<td align="center" style="border: 1px solid black;"><code>&quot;ABA&quot;</code></td>
			<td align="center" style="border: 1px solid black;"><code>&quot;ABA&quot;</code></td>
			<td align="center" style="border: 1px solid black;">Already alternating</td>
			<td align="center" style="border: 1px solid black;">0</td>
		</tr>
	</tbody>
</table>

<p>Thus, the answer is <code>[1, 0]</code>.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;BABA&quot;, queries = [[2,0,3],[1,1],[2,1,3]]</span></p>

<p><strong>Output:</strong> <span class="example-io">[0,1]</span></p>

<p><strong>Explanation:</strong></p>

<table style="border: 1px solid black;">
	<thead>
		<tr>
			<th align="center" style="border: 1px solid black;"><code><strong>i</strong></code></th>
			<th align="center" style="border: 1px solid black;"><code><strong>queries[i]</strong></code></th>
			<th align="center" style="border: 1px solid black;"><code><strong>j</strong></code></th>
			<th align="center" style="border: 1px solid black;"><code><strong>l</strong></code></th>
			<th align="center" style="border: 1px solid black;"><code><strong>r</strong></code></th>
			<th align="center" style="border: 1px solid black;"><strong><code>s</code> before query</strong></th>
			<th align="center" style="border: 1px solid black;"><code><strong>s[l..r]</strong></code></th>
			<th align="center" style="border: 1px solid black;"><strong>Result</strong></th>
			<th align="center" style="border: 1px solid black;"><strong>Answer</strong></th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td align="center" style="border: 1px solid black;">0</td>
			<td align="center" style="border: 1px solid black;">[2, 0, 3]</td>
			<td align="center" style="border: 1px solid black;">-</td>
			<td align="center" style="border: 1px solid black;">0</td>
			<td align="center" style="border: 1px solid black;">3</td>
			<td align="center" style="border: 1px solid black;"><code>&quot;BABA&quot;</code></td>
			<td align="center" style="border: 1px solid black;"><code>&quot;BABA&quot;</code></td>
			<td align="center" style="border: 1px solid black;">Already alternating</td>
			<td align="center" style="border: 1px solid black;">0</td>
		</tr>
		<tr>
			<td align="center" style="border: 1px solid black;">1</td>
			<td align="center" style="border: 1px solid black;">[1, 1]</td>
			<td align="center" style="border: 1px solid black;">1</td>
			<td align="center" style="border: 1px solid black;">-</td>
			<td align="center" style="border: 1px solid black;">-</td>
			<td align="center" style="border: 1px solid black;"><code>&quot;BABA&quot;</code></td>
			<td align="center" style="border: 1px solid black;">-</td>
			<td align="center" style="border: 1px solid black;">Flip <code>s[1]</code> from <code>&#39;A&#39;</code> to <code>&#39;B&#39;</code></td>
			<td align="center" style="border: 1px solid black;">-</td>
		</tr>
		<tr>
			<td align="center" style="border: 1px solid black;">2</td>
			<td align="center" style="border: 1px solid black;">[2, 1, 3]</td>
			<td align="center" style="border: 1px solid black;">-</td>
			<td align="center" style="border: 1px solid black;">1</td>
			<td align="center" style="border: 1px solid black;">3</td>
			<td align="center" style="border: 1px solid black;"><code>&quot;BBBA&quot;</code></td>
			<td align="center" style="border: 1px solid black;"><code>&quot;BBA&quot;</code></td>
			<td align="center" style="border: 1px solid black;">Delete one <code>&#39;B&#39;</code> to get <code>&quot;BA&quot;</code></td>
			<td align="center" style="border: 1px solid black;">1</td>
		</tr>
	</tbody>
</table>

<p>Thus, the answer is <code>[0, 1]</code>.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n == s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s[i]</code> is either <code>&#39;A&#39;</code> or <code>&#39;B&#39;</code>.</li>
	<li><code>1 &lt;= q == queries.length &lt;= 10<sup>5</sup></code></li>
	<li><code>queries[i].length == 2</code> or <code>3</code>
	<ul>
		<li><code>queries[i] == [1, j]</code> or,</li>
		<li><code>queries[i] == [2, l, r]</code></li>
		<li><code>0 &lt;= j &lt;= n - 1</code></li>
		<li><code>0 &lt;= l &lt;= r &lt;= n - 1</code></li>
	</ul>
	</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Binary Indexed Tree

We can convert the string $s$ into an array $\textit{nums}$ of length $n$, where $\textit{nums}[0] = 0$, and for $1 \leq i < n$, if $s[i] = s[i-1]$, then $\textit{nums}[i] = 1$, otherwise $\textit{nums}[i] = 0$. This way $\textit{nums}[i]$ represents whether there are adjacent and equal characters at index $i$. Then calculating the minimum number of character deletions required to make the substring $s[l..r]$ an alternating string in the interval $[l, r]$ is equivalent to calculating the sum of elements in the $\textit{nums}$ array over the interval $[l+1, r]$.

To handle queries efficiently, we can use a Binary Indexed Tree to maintain the prefix sum of the $\textit{nums}$ array. For queries of type $[1, j]$, we need to flip $\textit{nums}[j]$ and $\textit{nums}[j+1]$ (if $j+1 < n$), and update the Binary Indexed Tree. For queries of type $[2, l, r]$, we can quickly calculate the sum of elements over the interval $[l+1, r]$ through the Binary Indexed Tree.

The time complexity is $O((n + q) \log n)$, and the space complexity is $O(n)$, where $n$ is the length of the string $s$, and $q$ is the number of queries.

<!-- tabs:start -->

#### Python3

```python
class BinaryIndexedTree:
    __slots__ = "n", "c"

    def __init__(self, n: int):
        self.n = n
        self.c = [0] * (n + 1)

    def update(self, x: int, delta: int) -> None:
        while x <= self.n:
            self.c[x] += delta
            x += x & -x

    def query(self, x: int) -> int:
        s = 0
        while x:
            s += self.c[x]
            x -= x & -x
        return s


class Solution:
    def minDeletions(self, s: str, queries: List[List[int]]) -> List[int]:
        n = len(s)
        nums = [0] * n
        bit = BinaryIndexedTree(n)
        for i in range(1, n):
            nums[i] = int(s[i] == s[i - 1])
            if nums[i]:
                bit.update(i + 1, 1)
        ans = []
        for q in queries:
            if q[0] == 1:
                j = q[1]
                delta = (nums[j] ^ 1) - nums[j]
                nums[j] ^= 1
                bit.update(j + 1, delta)
                if j + 1 < n:
                    delta = (nums[j + 1] ^ 1) - nums[j + 1]
                    nums[j + 1] ^= 1
                    bit.update(j + 2, delta)
            else:
                _, l, r = q
                ans.append(bit.query(r + 1) - bit.query(l + 1))
        return ans
```

#### Java

```java
class BinaryIndexedTree {
    int n;
    int[] c;

    BinaryIndexedTree(int n) {
        this.n = n;
        this.c = new int[n + 1];
    }

    void update(int x, int delta) {
        while (x <= n) {
            c[x] += delta;
            x += x & -x;
        }
    }

    int query(int x) {
        int s = 0;
        while (x > 0) {
            s += c[x];
            x -= x & -x;
        }
        return s;
    }
}

class Solution {
    public int[] minDeletions(String s, int[][] queries) {
        int n = s.length();
        int[] nums = new int[n];
        BinaryIndexedTree bit = new BinaryIndexedTree(n);

        for (int i = 1; i < n; i++) {
            nums[i] = (s.charAt(i) == s.charAt(i - 1)) ? 1 : 0;
            if (nums[i] == 1) {
                bit.update(i + 1, 1);
            }
        }

        int cnt = 0;
        for (int[] q : queries) {
            if (q[0] == 2) {
                cnt++;
            }
        }

        int[] ans = new int[cnt];
        int idx = 0;

        for (int[] q : queries) {
            if (q[0] == 1) {
                int j = q[1];

                int delta = (nums[j] ^ 1) - nums[j];
                nums[j] ^= 1;
                bit.update(j + 1, delta);

                if (j + 1 < n) {
                    delta = (nums[j + 1] ^ 1) - nums[j + 1];
                    nums[j + 1] ^= 1;
                    bit.update(j + 2, delta);
                }
            } else {
                int l = q[1];
                int r = q[2];
                ans[idx++] = bit.query(r + 1) - bit.query(l + 1);
            }
        }
        return ans;
    }
}
```

#### C++

```cpp
class BinaryIndexedTree {
public:
    int n;
    vector<int> c;

    BinaryIndexedTree(int n)
        : n(n)
        , c(n + 1, 0) {}

    void update(int x, int delta) {
        while (x <= n) {
            c[x] += delta;
            x += x & -x;
        }
    }

    int query(int x) {
        int s = 0;
        while (x > 0) {
            s += c[x];
            x -= x & -x;
        }
        return s;
    }
};

class Solution {
public:
    vector<int> minDeletions(string s, vector<vector<int>>& queries) {
        int n = s.size();
        vector<int> nums(n, 0);
        BinaryIndexedTree bit(n);

        for (int i = 1; i < n; i++) {
            nums[i] = (s[i] == s[i - 1]);
            if (nums[i]) {
                bit.update(i + 1, 1);
            }
        }

        vector<int> ans;

        for (auto& q : queries) {
            if (q[0] == 1) {
                int j = q[1];

                int delta = (nums[j] ^ 1) - nums[j];
                nums[j] ^= 1;
                bit.update(j + 1, delta);

                if (j + 1 < n) {
                    delta = (nums[j + 1] ^ 1) - nums[j + 1];
                    nums[j + 1] ^= 1;
                    bit.update(j + 2, delta);
                }
            } else {
                int l = q[1];
                int r = q[2];
                ans.push_back(bit.query(r + 1) - bit.query(l + 1));
            }
        }
        return ans;
    }
};
```

#### Go

```go
type binaryIndexedTree struct {
	n int
	c []int
}

func newBinaryIndexedTree(n int) *binaryIndexedTree {
	return &binaryIndexedTree{
		n: n,
		c: make([]int, n+1),
	}
}

func (bit *binaryIndexedTree) update(x, delta int) {
	for x <= bit.n {
		bit.c[x] += delta
		x += x & -x
	}
}

func (bit *binaryIndexedTree) query(x int) int {
	s := 0
	for x > 0 {
		s += bit.c[x]
		x -= x & -x
	}
	return s
}

func minDeletions(s string, queries [][]int) []int {
	n := len(s)
	nums := make([]int, n)
	bit := newBinaryIndexedTree(n)

	for i := 1; i < n; i++ {
		if s[i] == s[i-1] {
			nums[i] = 1
			bit.update(i+1, 1)
		}
	}

	ans := make([]int, 0)

	for _, q := range queries {
		if q[0] == 1 {
			j := q[1]

			delta := (nums[j] ^ 1 - nums[j])
			nums[j] ^= 1
			bit.update(j+1, delta)

			if j+1 < n {
				delta = (nums[j+1] ^ 1 - nums[j+1])
				nums[j+1] ^= 1
				bit.update(j+2, delta)
			}
		} else {
			l, r := q[1], q[2]
			ans = append(ans, bit.query(r+1)-bit.query(l+1))
		}
	}

	return ans
}
```

#### TypeScript

```ts
class BinaryIndexedTree {
    n: number;
    c: number[];

    constructor(n: number) {
        this.n = n;
        this.c = Array(n + 1).fill(0);
    }

    update(x: number, delta: number): void {
        while (x <= this.n) {
            this.c[x] += delta;
            x += x & -x;
        }
    }

    query(x: number): number {
        let s = 0;
        while (x > 0) {
            s += this.c[x];
            x -= x & -x;
        }
        return s;
    }
}

function minDeletions(s: string, queries: number[][]): number[] {
    const n = s.length;
    const nums: number[] = Array(n).fill(0);
    const bit = new BinaryIndexedTree(n);

    for (let i = 1; i < n; i++) {
        if (s[i] === s[i - 1]) {
            nums[i] = 1;
            bit.update(i + 1, 1);
        }
    }

    const ans: number[] = [];

    for (const q of queries) {
        if (q[0] === 1) {
            const j = q[1];

            let delta = (nums[j] ^ 1) - nums[j];
            nums[j] ^= 1;
            bit.update(j + 1, delta);

            if (j + 1 < n) {
                delta = (nums[j + 1] ^ 1) - nums[j + 1];
                nums[j + 1] ^= 1;
                bit.update(j + 2, delta);
            }
        } else {
            const l = q[1],
                r = q[2];
            ans.push(bit.query(r + 1) - bit.query(l + 1));
        }
    }

    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
