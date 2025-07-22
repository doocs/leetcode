---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3500-3599/3579.Minimum%20Steps%20to%20Convert%20String%20with%20Operations/README_EN.md
tags:
    - Greedy
    - String
    - Dynamic Programming
---

<!-- problem:start -->

# [3579. Minimum Steps to Convert String with Operations](https://leetcode.com/problems/minimum-steps-to-convert-string-with-operations)

[中文文档](/solution/3500-3599/3579.Minimum%20Steps%20to%20Convert%20String%20with%20Operations/README.md)

## Description

<!-- description:start -->

<p>You are given two strings, <code>word1</code> and <code>word2</code>, of equal length. You need to transform <code>word1</code> into <code>word2</code>.</p>

<p>For this, divide <code>word1</code> into one or more <strong>contiguous <span data-keyword="substring-nonempty">substrings</span></strong>. For each substring <code>substr</code> you can perform the following operations:</p>

<ol>
	<li>
	<p><strong>Replace:</strong> Replace the character at any one index of <code>substr</code> with another lowercase English letter.</p>
	</li>
	<li>
	<p><strong>Swap:</strong> Swap any two characters in <code>substr</code>.</p>
	</li>
	<li>
	<p><strong>Reverse Substring:</strong> Reverse <code>substr</code>.</p>
	</li>
</ol>

<p>Each of these counts as <strong>one</strong> operation and each character of each substring can be used in each type of operation at most once (i.e. no single index may be involved in more than one replace, one swap, or one reverse).</p>

<p>Return the <strong>minimum number of operations</strong> required to transform <code>word1</code> into <code>word2</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">word1 = &quot;abcdf&quot;, word2 = &quot;dacbe&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">4</span></p>

<p><strong>Explanation:</strong></p>

<p>Divide <code>word1</code> into <code>&quot;ab&quot;</code>, <code>&quot;c&quot;</code>, and <code>&quot;df&quot;</code>. The operations are:</p>

<ul>
	<li>For the substring <code>&quot;ab&quot;</code>,

    <ul>
    	<li>Perform operation of type 3 on <code>&quot;ab&quot; -&gt; &quot;ba&quot;</code>.</li>
    	<li>Perform operation of type 1 on <code>&quot;ba&quot; -&gt; &quot;da&quot;</code>.</li>
    </ul>
    </li>
    <li>For the substring <code>&quot;c&quot;</code> do no operations.</li>
    <li>For the substring <code>&quot;df&quot;</code>,
    <ul>
    	<li>Perform operation of type 1 on <code>&quot;df&quot; -&gt; &quot;bf&quot;</code>.</li>
    	<li>Perform operation of type 1 on <code>&quot;bf&quot; -&gt; &quot;be&quot;</code>.</li>
    </ul>
    </li>

</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">word1 = &quot;abceded&quot;, word2 = &quot;baecfef&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">4</span></p>

<p><strong>Explanation:</strong></p>

<p>Divide <code>word1</code> into <code>&quot;ab&quot;</code>, <code>&quot;ce&quot;</code>, and <code>&quot;ded&quot;</code>. The operations are:</p>

<ul>
	<li>For the substring <code>&quot;ab&quot;</code>,

    <ul>
    	<li>Perform operation of type 2 on <code>&quot;ab&quot; -&gt; &quot;ba&quot;</code>.</li>
    </ul>
    </li>
    <li>For the substring <code>&quot;ce&quot;</code>,
    <ul>
    	<li>Perform operation of type 2 on <code>&quot;ce&quot; -&gt; &quot;ec&quot;</code>.</li>
    </ul>
    </li>
    <li>For the substring <code>&quot;ded&quot;</code>,
    <ul>
    	<li>Perform operation of type 1 on <code>&quot;ded&quot; -&gt; &quot;fed&quot;</code>.</li>
    	<li>Perform operation of type 1 on <code>&quot;fed&quot; -&gt; &quot;fef&quot;</code>.</li>
    </ul>
    </li>

</ul>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">word1 = &quot;abcdef&quot;, word2 = &quot;fedabc&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong></p>

<p>Divide <code>word1</code> into <code>&quot;abcdef&quot;</code>. The operations are:</p>

<ul>
	<li>For the substring <code>&quot;abcdef&quot;</code>,

    <ul>
    	<li>Perform operation of type 3 on <code>&quot;abcdef&quot; -&gt; &quot;fedcba&quot;</code>.</li>
    	<li>Perform operation of type 2 on <code>&quot;fedcba&quot; -&gt; &quot;fedabc&quot;</code>.</li>
    </ul>
    </li>

</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= word1.length == word2.length &lt;= 100</code></li>
	<li><code>word1</code> and <code>word2</code> consist only of lowercase English letters.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Greedy + Dynamic Programming

We define $f[i]$ as the minimum number of operations required to convert the first $i$ characters of $\textit{word1}$ to the first $i$ characters of $\textit{word2}$. The answer is $f[n]$, where $n$ is the length of both $\textit{word1}$ and $\textit{word2}$.

We can compute $f[i]$ by enumerating all possible split points. For each split point $j$, we need to calculate the minimum number of operations required to convert $\textit{word1}[j:i]$ to $\textit{word2}[j:i]$.

We can use a helper function $\text{calc}(l, r, \text{rev})$ to compute the minimum number of operations needed to convert $\textit{word1}[l:r]$ to $\textit{word2}[l:r]$, where $\text{rev}$ indicates whether to reverse the substring. Since the result of performing other operations before or after a reversal is the same, we only need to consider not reversing, and reversing once before other operations. Therefore, $f[i] = \min_{j < i} (f[j] + \min(\text{calc}(j, i-1, \text{false}), 1 + \text{calc}(j, i-1, \text{true})))$.

Next, we need to implement the $\text{calc}(l, r, \text{rev})$ function. We use a 2D array $cnt$ to record the pairing status of characters between $\textit{word1}$ and $\textit{word2}$. For each character pair $(a, b)$, if $a \neq b$, we check whether $cnt[b][a] > 0$. If so, we can pair them and reduce one operation; otherwise, we need to add one operation and increment $cnt[a][b]$ by $1$.

The time complexity is $O(n^3 + |\Sigma|^2)$ and the space complexity is $O(n + |\Sigma|^2)$, where $n$ is the length of the string and $|\Sigma|$ is the size of the character set (which is $26$ in this problem).

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minOperations(self, word1: str, word2: str) -> int:
        def calc(l: int, r: int, rev: bool) -> int:
            cnt = Counter()
            res = 0
            for i in range(l, r + 1):
                j = r - (i - l) if rev else i
                a, b = word1[j], word2[i]
                if a != b:
                    if cnt[(b, a)] > 0:
                        cnt[(b, a)] -= 1
                    else:
                        cnt[(a, b)] += 1
                        res += 1
            return res

        n = len(word1)
        f = [inf] * (n + 1)
        f[0] = 0
        for i in range(1, n + 1):
            for j in range(i):
                t = min(calc(j, i - 1, False), 1 + calc(j, i - 1, True))
                f[i] = min(f[i], f[j] + t)
        return f[n]
```

#### Java

```java
class Solution {
    public int minOperations(String word1, String word2) {
        int n = word1.length();
        int[] f = new int[n + 1];
        Arrays.fill(f, Integer.MAX_VALUE);
        f[0] = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                int a = calc(word1, word2, j, i - 1, false);
                int b = 1 + calc(word1, word2, j, i - 1, true);
                int t = Math.min(a, b);
                f[i] = Math.min(f[i], f[j] + t);
            }
        }
        return f[n];
    }

    private int calc(String word1, String word2, int l, int r, boolean rev) {
        int[][] cnt = new int[26][26];
        int res = 0;
        for (int i = l; i <= r; i++) {
            int j = rev ? r - (i - l) : i;
            int a = word1.charAt(j) - 'a';
            int b = word2.charAt(i) - 'a';
            if (a != b) {
                if (cnt[b][a] > 0) {
                    cnt[b][a]--;
                } else {
                    cnt[a][b]++;
                    res++;
                }
            }
        }
        return res;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int minOperations(string word1, string word2) {
        int n = word1.length();
        vector<int> f(n + 1, INT_MAX);
        f[0] = 0;

        for (int i = 1; i <= n; ++i) {
            for (int j = 0; j < i; ++j) {
                int a = calc(word1, word2, j, i - 1, false);
                int b = 1 + calc(word1, word2, j, i - 1, true);
                int t = min(a, b);
                f[i] = min(f[i], f[j] + t);
            }
        }

        return f[n];
    }

private:
    int calc(const string& word1, const string& word2, int l, int r, bool rev) {
        int cnt[26][26] = {0};
        int res = 0;

        for (int i = l; i <= r; ++i) {
            int j = rev ? r - (i - l) : i;
            int a = word1[j] - 'a';
            int b = word2[i] - 'a';

            if (a != b) {
                if (cnt[b][a] > 0) {
                    cnt[b][a]--;
                } else {
                    cnt[a][b]++;
                    res++;
                }
            }
        }

        return res;
    }
};
```

#### Go

```go
func minOperations(word1 string, word2 string) int {
	n := len(word1)
	f := make([]int, n+1)
	for i := range f {
		f[i] = math.MaxInt32
	}
	f[0] = 0

	calc := func(l, r int, rev bool) int {
		var cnt [26][26]int
		res := 0

		for i := l; i <= r; i++ {
			j := i
			if rev {
				j = r - (i - l)
			}
			a := word1[j] - 'a'
			b := word2[i] - 'a'

			if a != b {
				if cnt[b][a] > 0 {
					cnt[b][a]--
				} else {
					cnt[a][b]++
					res++
				}
			}
		}

		return res
	}

	for i := 1; i <= n; i++ {
		for j := 0; j < i; j++ {
			a := calc(j, i-1, false)
			b := 1 + calc(j, i-1, true)
			t := min(a, b)
			f[i] = min(f[i], f[j]+t)
		}
	}

	return f[n]
}
```

#### TypeScript

```ts
function minOperations(word1: string, word2: string): number {
    const n = word1.length;
    const f = Array(n + 1).fill(Number.MAX_SAFE_INTEGER);
    f[0] = 0;

    function calc(l: number, r: number, rev: boolean): number {
        const cnt: number[][] = Array.from({ length: 26 }, () => Array(26).fill(0));
        let res = 0;

        for (let i = l; i <= r; i++) {
            const j = rev ? r - (i - l) : i;
            const a = word1.charCodeAt(j) - 97;
            const b = word2.charCodeAt(i) - 97;

            if (a !== b) {
                if (cnt[b][a] > 0) {
                    cnt[b][a]--;
                } else {
                    cnt[a][b]++;
                    res++;
                }
            }
        }

        return res;
    }

    for (let i = 1; i <= n; i++) {
        for (let j = 0; j < i; j++) {
            const a = calc(j, i - 1, false);
            const b = 1 + calc(j, i - 1, true);
            const t = Math.min(a, b);
            f[i] = Math.min(f[i], f[j] + t);
        }
    }

    return f[n];
}
```

#### Rust

```rust
impl Solution {
    pub fn min_operations(word1: String, word2: String) -> i32 {
        let n = word1.len();
        let word1 = word1.as_bytes();
        let word2 = word2.as_bytes();
        let mut f = vec![i32::MAX; n + 1];
        f[0] = 0;

        for i in 1..=n {
            for j in 0..i {
                let a = Self::calc(word1, word2, j, i - 1, false);
                let b = 1 + Self::calc(word1, word2, j, i - 1, true);
                let t = a.min(b);
                f[i] = f[i].min(f[j] + t);
            }
        }

        f[n]
    }

    fn calc(word1: &[u8], word2: &[u8], l: usize, r: usize, rev: bool) -> i32 {
        let mut cnt = [[0i32; 26]; 26];
        let mut res = 0;

        for i in l..=r {
            let j = if rev { r - (i - l) } else { i };
            let a = (word1[j] - b'a') as usize;
            let b = (word2[i] - b'a') as usize;

            if a != b {
                if cnt[b][a] > 0 {
                    cnt[b][a] -= 1;
                } else {
                    cnt[a][b] += 1;
                    res += 1;
                }
            }
        }

        res
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
