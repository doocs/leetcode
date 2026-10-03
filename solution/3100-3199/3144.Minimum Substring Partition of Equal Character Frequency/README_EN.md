---
comments: true
difficulty: Medium
rating: 1917
source: Biweekly Contest 130 Q3
tags:
    - Hash Table
    - String
    - Dynamic Programming
    - Counting
---

<!-- problem:start -->

# [3144. Minimum Substring Partition of Equal Character Frequency](https://leetcode.com/problems/minimum-substring-partition-of-equal-character-frequency)

[中文文档](/solution/3100-3199/3144.Minimum%20Substring%20Partition%20of%20Equal%20Character%20Frequency/README.md)

## Description

<!-- description:start -->

<p>Given a string <code>s</code>, you need to partition it into one or more <strong>balanced</strong> <span data-keyword="substring">substrings</span>. For example, if <code>s == &quot;ababcc&quot;</code> then <code>(&quot;abab&quot;, &quot;c&quot;, &quot;c&quot;)</code>, <code>(&quot;ab&quot;, &quot;abc&quot;, &quot;c&quot;)</code>, and <code>(&quot;ababcc&quot;)</code> are all valid partitions, but <code>(&quot;a&quot;, <strong>&quot;bab&quot;</strong>, &quot;cc&quot;)</code>, <code>(<strong>&quot;aba&quot;</strong>, &quot;bc&quot;, &quot;c&quot;)</code>, and <code>(&quot;ab&quot;, <strong>&quot;abcc&quot;</strong>)</code> are not. The unbalanced substrings are bolded.</p>

<p>Return the <strong>minimum</strong> number of substrings that you can partition <code>s</code> into.</p>

<p><strong>Note:</strong> A <strong>balanced</strong> string is a string where each character in the string occurs the same number of times.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;fabccddg&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">3</span></p>

<p><strong>Explanation:</strong></p>

<p>We can partition the string <code>s</code> into 3 substrings in one of the following ways: <code>(&quot;fab, &quot;ccdd&quot;, &quot;g&quot;)</code>, or <code>(&quot;fabc&quot;, &quot;cd&quot;, &quot;dg&quot;)</code>.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;abababaccddb&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong></p>

<p>We can partition the string <code>s</code> into 2 substrings like so: <code>(&quot;abab&quot;, &quot;abaccddb&quot;)</code>.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 1000</code></li>
	<li><code>s</code> consists only of English lowercase letters.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Memoized Search + Hash Table

<!-- thinking:start -->

> **Thinking**
>
> $s$ must be split into as few balanced pieces as possible, each with equal character frequencies. Full partitions are exponential; $n\le 1000$ allows quadratic DP.
>
> From index $i$, extending $j$ can test balance with frequency maps: a piece is legal when only one frequency remains. The optimum depends only on the start index.
>
> Memoize $dfs(i)$: maintain $cnt$ and $freq$, and when $freq$ has size $1$ take $1+dfs(j+1)$. The empty suffix returns $0$.

<!-- thinking:end -->

We design a function $\textit{dfs}(i)$, which represents the minimum number of substrings starting from $s[i]$. The answer is $\textit{dfs}(0)$.

The calculation process of the function $\textit{dfs}(i)$ is as follows:

If $i \geq n$, it means all characters have been processed, so return $0$.

Otherwise, we maintain a hash table $\textit{cnt}$ to represent the frequency of each character in the current substring. Additionally, we maintain a hash table $\textit{freq}$ to represent the frequency of each character's occurrence count.

Then we enumerate $j$ from $i$ to $n-1$, representing the end position of the current substring. For each $j$, we update $\textit{cnt}$ and $\textit{freq}$, then check if the size of $\textit{freq}$ is $1$. If it is, we can split from $j+1$, and the answer is $1 + \textit{dfs}(j+1)$. We take the minimum answer among all $j$ as the return value of the function.

To avoid repeated calculations, we use memoized search.

The time complexity is $O(n^2)$, and the space complexity is $O(n \times |\Sigma|)$. Here, $n$ is the length of the string $s$, and $|\Sigma|$ represents the size of the character set, which is $26$ in this problem.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minimumSubstringsInPartition(self, s: str) -> int:
        @cache
        def dfs(i: int) -> int:
            if i >= n:
                return 0
            cnt = defaultdict(int)
            freq = defaultdict(int)
            ans = n - i
            for j in range(i, n):
                if cnt[s[j]]:
                    freq[cnt[s[j]]] -= 1
                    if not freq[cnt[s[j]]]:
                        freq.pop(cnt[s[j]])
                cnt[s[j]] += 1
                freq[cnt[s[j]]] += 1
                if len(freq) == 1 and (t := 1 + dfs(j + 1)) < ans:
                    ans = t
            return ans

        n = len(s)
        return dfs(0)
```

#### Java

```java
class Solution {
    private int n;
    private char[] s;
    private Integer[] f;

    public int minimumSubstringsInPartition(String s) {
        n = s.length();
        f = new Integer[n];
        this.s = s.toCharArray();
        return dfs(0);
    }

    private int dfs(int i) {
        if (i >= n) {
            return 0;
        }
        if (f[i] != null) {
            return f[i];
        }
        int[] cnt = new int[26];
        Map<Integer, Integer> freq = new HashMap<>(26);
        int ans = n - i;
        for (int j = i; j < n; ++j) {
            int k = s[j] - 'a';
            if (cnt[k] > 0) {
                if (freq.merge(cnt[k], -1, Integer::sum) == 0) {
                    freq.remove(cnt[k]);
                }
            }
            ++cnt[k];
            freq.merge(cnt[k], 1, Integer::sum);
            if (freq.size() == 1) {
                ans = Math.min(ans, 1 + dfs(j + 1));
            }
        }
        return f[i] = ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int minimumSubstringsInPartition(string s) {
        int n = s.size();
        int f[n];
        memset(f, -1, sizeof(f));
        auto dfs = [&](this auto&& dfs, int i) -> int {
            if (i >= n) {
                return 0;
            }
            if (f[i] != -1) {
                return f[i];
            }
            f[i] = n - i;
            int cnt[26]{};
            unordered_map<int, int> freq;
            for (int j = i; j < n; ++j) {
                int k = s[j] - 'a';
                if (cnt[k]) {
                    freq[cnt[k]]--;
                    if (freq[cnt[k]] == 0) {
                        freq.erase(cnt[k]);
                    }
                }
                ++cnt[k];
                ++freq[cnt[k]];
                if (freq.size() == 1) {
                    f[i] = min(f[i], 1 + dfs(j + 1));
                }
            }
            return f[i];
        };
        return dfs(0);
    }
};
```

#### Go

```go
func minimumSubstringsInPartition(s string) int {
	n := len(s)
	f := make([]int, n)
	for i := range f {
		f[i] = -1
	}
	var dfs func(int) int
	dfs = func(i int) int {
		if i >= n {
			return 0
		}
		if f[i] != -1 {
			return f[i]
		}
		cnt := [26]int{}
		freq := map[int]int{}
		f[i] = n - i
		for j := i; j < n; j++ {
			k := int(s[j] - 'a')
			if cnt[k] > 0 {
				freq[cnt[k]]--
				if freq[cnt[k]] == 0 {
					delete(freq, cnt[k])
				}
			}
			cnt[k]++
			freq[cnt[k]]++
			if len(freq) == 1 {
				f[i] = min(f[i], 1+dfs(j+1))
			}
		}
		return f[i]
	}
	return dfs(0)
}
```

#### TypeScript

```ts
function minimumSubstringsInPartition(s: string): number {
    const n = s.length;
    const f: number[] = Array(n).fill(-1);
    const dfs = (i: number): number => {
        if (i >= n) {
            return 0;
        }
        if (f[i] !== -1) {
            return f[i];
        }
        const cnt: Map<number, number> = new Map();
        const freq: Map<number, number> = new Map();
        f[i] = n - i;
        for (let j = i; j < n; ++j) {
            const k = s.charCodeAt(j) - 97;
            if (freq.has(cnt.get(k)!)) {
                freq.set(cnt.get(k)!, freq.get(cnt.get(k)!)! - 1);
                if (freq.get(cnt.get(k)!) === 0) {
                    freq.delete(cnt.get(k)!);
                }
            }
            cnt.set(k, (cnt.get(k) || 0) + 1);
            freq.set(cnt.get(k)!, (freq.get(cnt.get(k)!) || 0) + 1);
            if (freq.size === 1) {
                f[i] = Math.min(f[i], 1 + dfs(j + 1));
            }
        }
        return f[i];
    };
    return dfs(0);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Solution 2: Memoized Search (Optimization)

<!-- thinking:start -->

> **Thinking**
>
> Method 1 keeps a second map of frequency-of-frequencies, which adds constants and fiddly updates.
>
> Balance is equivalent to length equaling (maximum frequency) times (number of distinct letters). Only $cnt$ and that maximum $m$ are required.
>
> While extending $j$, update $m$ and recurse when $j-i+1=m\cdot|cnt|$. The state is still the start index, with a shorter inner loop.

<!-- thinking:end -->

We can optimize Solution 1 by not maintaining the $\textit{freq}$ hash table. Instead, we only need to maintain a hash table $\textit{cnt}$, which represents the frequency of each character in the current substring. Additionally, we maintain two variables $k$ and $m$ to represent the number of distinct characters in the current substring and the maximum frequency of any character, respectively. For a substring $s[i..j]$, if $j-i+1 = m \times k$, then this substring is a balanced substring.

The time complexity is $O(n^2)$, and the space complexity is $O(n \times |\Sigma|)$. Here, $n$ is the length of the string $s$, and $|\Sigma|$ represents the size of the character set, which is $26$ in this problem.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minimumSubstringsInPartition(self, s: str) -> int:
        @cache
        def dfs(i: int) -> int:
            if i >= n:
                return 0
            cnt = defaultdict(int)
            m = 0
            ans = n - i
            for j in range(i, n):
                cnt[s[j]] += 1
                m = max(m, cnt[s[j]])
                if j - i + 1 == m * len(cnt):
                    ans = min(ans, 1 + dfs(j + 1))
            return ans

        n = len(s)
        ans = dfs(0)
        dfs.cache_clear()
        return ans
```

#### Java

```java
class Solution {
    private int n;
    private char[] s;
    private Integer[] f;

    public int minimumSubstringsInPartition(String s) {
        n = s.length();
        f = new Integer[n];
        this.s = s.toCharArray();
        return dfs(0);
    }

    private int dfs(int i) {
        if (i >= n) {
            return 0;
        }
        if (f[i] != null) {
            return f[i];
        }
        int[] cnt = new int[26];
        int ans = n - i;
        int k = 0, m = 0;
        for (int j = i; j < n; ++j) {
            k += ++cnt[s[j] - 'a'] == 1 ? 1 : 0;
            m = Math.max(m, cnt[s[j] - 'a']);
            if (j - i + 1 == k * m) {
                ans = Math.min(ans, 1 + dfs(j + 1));
            }
        }
        return f[i] = ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int minimumSubstringsInPartition(string s) {
        int n = s.size();
        int f[n];
        memset(f, -1, sizeof(f));
        auto dfs = [&](this auto&& dfs, int i) -> int {
            if (i >= n) {
                return 0;
            }
            if (f[i] != -1) {
                return f[i];
            }
            f[i] = n - i;
            int cnt[26]{};
            int k = 0, m = 0;
            for (int j = i; j < n; ++j) {
                k += ++cnt[s[j] - 'a'] == 1 ? 1 : 0;
                m = max(m, cnt[s[j] - 'a']);
                if (j - i + 1 == k * m) {
                    f[i] = min(f[i], 1 + dfs(j + 1));
                }
            }
            return f[i];
        };
        return dfs(0);
    }
};
```

#### Go

```go
func minimumSubstringsInPartition(s string) int {
	n := len(s)
	f := make([]int, n)
	for i := range f {
		f[i] = -1
	}
	var dfs func(int) int
	dfs = func(i int) int {
		if i >= n {
			return 0
		}
		if f[i] != -1 {
			return f[i]
		}
		cnt := [26]int{}
		f[i] = n - i
		k, m := 0, 0
		for j := i; j < n; j++ {
			x := int(s[j] - 'a')
			cnt[x]++
			if cnt[x] == 1 {
				k++
			}
			m = max(m, cnt[x])
			if j-i+1 == k*m {
				f[i] = min(f[i], 1+dfs(j+1))
			}
		}
		return f[i]
	}
	return dfs(0)
}
```

#### TypeScript

```ts
function minimumSubstringsInPartition(s: string): number {
    const n = s.length;
    const f: number[] = Array(n).fill(-1);
    const dfs = (i: number): number => {
        if (i >= n) {
            return 0;
        }
        if (f[i] !== -1) {
            return f[i];
        }
        const cnt: number[] = Array(26).fill(0);
        f[i] = n - i;
        let [k, m] = [0, 0];
        for (let j = i; j < n; ++j) {
            const x = s.charCodeAt(j) - 97;
            k += ++cnt[x] === 1 ? 1 : 0;
            m = Math.max(m, cnt[x]);
            if (j - i + 1 === k * m) {
                f[i] = Math.min(f[i], 1 + dfs(j + 1));
            }
        }
        return f[i];
    };
    return dfs(0);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Solution 3: Dynamic Programming

<!-- thinking:start -->

> **Thinking**
>
> Solution 2 already fills this minimum from the end of the string. The same transition can be written on prefixes.
>
> Let $f[i]$ be the fewest pieces for the prefix of length $i$. For each right end $i$, expand left to $j$ and relax $f[i+1]$ with $f[j]+1$ when the piece is balanced.
>
> The table yields $f[n]$, using linear DP memory plus one count map.

<!-- thinking:end -->

The minimum number of pieces can also be stored on prefixes. Define the state $f[i]$ as the minimum number of substrings required to partition the first $i$ characters. Initially, $f[0] = 0$, and the rest $f[i] = +\infty$ or $f[i] = n$.

Next, we enumerate $i$ from $0$ to $n-1$. For each $i$, we maintain a hash table $\textit{cnt}$ to represent the frequency of each character in the current substring. Additionally, we maintain two variables $k$ and $m$ to represent the number of distinct characters in the current substring and the maximum frequency of any character, respectively. For a substring $s[j..i]$, if $i-j+1 = m \times k$, then this substring is a balanced substring. At this point, we can partition from $j$, so $f[i+1] = \min(f[i+1], f[j] + 1)$.

The final answer is $f[n]$.

The time complexity is $O(n^2)$, and the space complexity is $O(n + |\Sigma|)$. Here, $n$ is the length of the string $s$, and $|\Sigma|$ represents the size of the character set, which is $26$ in this problem.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minimumSubstringsInPartition(self, s: str) -> int:
        n = len(s)
        f = [inf] * (n + 1)
        f[0] = 0
        for i in range(n):
            cnt = defaultdict(int)
            m = 0
            for j in range(i, -1, -1):
                cnt[s[j]] += 1
                m = max(m, cnt[s[j]])
                if i - j + 1 == len(cnt) * m:
                    f[i + 1] = min(f[i + 1], f[j] + 1)
        return f[n]
```

#### Java

```java
class Solution {
    public int minimumSubstringsInPartition(String s) {
        int n = s.length();
        char[] cs = s.toCharArray();
        int[] f = new int[n + 1];
        Arrays.fill(f, n);
        f[0] = 0;
        for (int i = 0; i < n; ++i) {
            int[] cnt = new int[26];
            int k = 0, m = 0;
            for (int j = i; j >= 0; --j) {
                k += ++cnt[cs[j] - 'a'] == 1 ? 1 : 0;
                m = Math.max(m, cnt[cs[j] - 'a']);
                if (i - j + 1 == k * m) {
                    f[i + 1] = Math.min(f[i + 1], 1 + f[j]);
                }
            }
        }
        return f[n];
    }
}
```

#### C++

```cpp
class Solution {
public:
    int minimumSubstringsInPartition(string s) {
        int n = s.size();
        vector<int> f(n + 1, n);
        f[0] = 0;
        for (int i = 0; i < n; ++i) {
            int cnt[26]{};
            int k = 0, m = 0;
            for (int j = i; ~j; --j) {
                k += ++cnt[s[j] - 'a'] == 1;
                m = max(m, cnt[s[j] - 'a']);
                if (i - j + 1 == k * m) {
                    f[i + 1] = min(f[i + 1], f[j] + 1);
                }
            }
        }
        return f[n];
    }
};
```

#### Go

```go
func minimumSubstringsInPartition(s string) int {
	n := len(s)
	f := make([]int, n+1)
	for i := range f {
		f[i] = n
	}
	f[0] = 0
	for i := 0; i < n; i++ {
		cnt := [26]int{}
		k, m := 0, 0
		for j := i; j >= 0; j-- {
			x := int(s[j] - 'a')
			cnt[x]++
			if cnt[x] == 1 {
				k++
			}
			m = max(m, cnt[x])
			if i-j+1 == k*m {
				f[i+1] = min(f[i+1], 1+f[j])
			}
		}
	}
	return f[n]
}
```

#### TypeScript

```ts
function minimumSubstringsInPartition(s: string): number {
    const n = s.length;
    const f: number[] = Array(n + 1).fill(n);
    f[0] = 0;
    for (let i = 0; i < n; ++i) {
        const cnt: number[] = Array(26).fill(0);
        let [k, m] = [0, 0];
        for (let j = i; ~j; --j) {
            const x = s.charCodeAt(j) - 97;
            k += ++cnt[x] === 1 ? 1 : 0;
            m = Math.max(m, cnt[x]);
            if (i - j + 1 === k * m) {
                f[i + 1] = Math.min(f[i + 1], 1 + f[j]);
            }
        }
    }
    return f[n];
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Solution 4: Dynamic Programming + Hash Table

<!-- thinking:start -->

> **Thinking**
>
> Enumerating every partition is exponential. The string length reaches $1000$, and a single character is already a balanced piece, so the first transition always moves to the next index and the search has depth $n$. Whether a piece is balanced depends only on the frequencies inside it, so the answer from index $i$ depends only on later answers. Let $f[i]$ be the fewest pieces starting at $i$, with $f[n]=0$, and scan right endpoints from the end, updating with $1+f[j+1]$ when $freq$ contains only one frequency.

<!-- thinking:end -->

Let $f[i]$ be the minimum number of substrings starting at index $i$, with $f[n]=0$. For $i$ from $n-1$ down to $0$, keep a hash table $\textit{cnt}$ of character frequencies in the current piece and a hash table $\textit{freq}$ of how often each frequency occurs. Enumerate the right endpoint $j$ from $i$ to $n-1$, append $s[j]$, and update both tables. If $\textit{freq}$ has size $1$, then $s[i..j]$ is balanced and $f[i]$ is updated with $1+f[j+1]$. Every character can stand alone, so $n-i$ is a valid upper bound. The answer is $f[0]$.

The time complexity is $O(n^2)$, and the space complexity is $O(n + |\Sigma|)$. Here, $n$ is the length of the string $s$, and $|\Sigma|$ represents the size of the character set, which is $26$ in this problem.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minimumSubstringsInPartition(self, s: str) -> int:
        n = len(s)
        f = [0] * (n + 1)
        for i in range(n - 1, -1, -1):
            cnt = defaultdict(int)
            freq = defaultdict(int)
            ans = n - i
            for j in range(i, n):
                if cnt[s[j]]:
                    freq[cnt[s[j]]] -= 1
                    if not freq[cnt[s[j]]]:
                        freq.pop(cnt[s[j]])
                cnt[s[j]] += 1
                freq[cnt[s[j]]] += 1
                if len(freq) == 1:
                    ans = min(ans, 1 + f[j + 1])
            f[i] = ans
        return f[0]
```

#### Java

```java
class Solution {
    public int minimumSubstringsInPartition(String s) {
        int n = s.length();
        char[] cs = s.toCharArray();
        int[] f = new int[n + 1];
        for (int i = n - 1; i >= 0; --i) {
            int[] cnt = new int[26];
            Map<Integer, Integer> freq = new HashMap<>(26);
            int ans = n - i;
            for (int j = i; j < n; ++j) {
                int k = cs[j] - 'a';
                if (cnt[k] > 0) {
                    if (freq.merge(cnt[k], -1, Integer::sum) == 0) {
                        freq.remove(cnt[k]);
                    }
                }
                ++cnt[k];
                freq.merge(cnt[k], 1, Integer::sum);
                if (freq.size() == 1) {
                    ans = Math.min(ans, 1 + f[j + 1]);
                }
            }
            f[i] = ans;
        }
        return f[0];
    }
}
```

#### C++

```cpp
class Solution {
public:
    int minimumSubstringsInPartition(string s) {
        int n = s.size();
        vector<int> f(n + 1);
        for (int i = n - 1; i >= 0; --i) {
            int cnt[26]{};
            unordered_map<int, int> freq;
            int ans = n - i;
            for (int j = i; j < n; ++j) {
                int k = s[j] - 'a';
                if (cnt[k]) {
                    freq[cnt[k]]--;
                    if (freq[cnt[k]] == 0) {
                        freq.erase(cnt[k]);
                    }
                }
                ++cnt[k];
                ++freq[cnt[k]];
                if (freq.size() == 1) {
                    ans = min(ans, 1 + f[j + 1]);
                }
            }
            f[i] = ans;
        }
        return f[0];
    }
};
```

#### Go

```go
func minimumSubstringsInPartition(s string) int {
	n := len(s)
	f := make([]int, n+1)
	for i := n - 1; i >= 0; i-- {
		cnt := [26]int{}
		freq := map[int]int{}
		ans := n - i
		for j := i; j < n; j++ {
			k := int(s[j] - 'a')
			if cnt[k] > 0 {
				freq[cnt[k]]--
				if freq[cnt[k]] == 0 {
					delete(freq, cnt[k])
				}
			}
			cnt[k]++
			freq[cnt[k]]++
			if len(freq) == 1 {
				ans = min(ans, 1+f[j+1])
			}
		}
		f[i] = ans
	}
	return f[0]
}
```

#### TypeScript

```ts
function minimumSubstringsInPartition(s: string): number {
    const n = s.length;
    const f: number[] = Array(n + 1).fill(0);
    for (let i = n - 1; i >= 0; --i) {
        const cnt: Map<number, number> = new Map();
        const freq: Map<number, number> = new Map();
        let ans = n - i;
        for (let j = i; j < n; ++j) {
            const k = s.charCodeAt(j) - 97;
            if (freq.has(cnt.get(k)!)) {
                freq.set(cnt.get(k)!, freq.get(cnt.get(k)!)! - 1);
                if (freq.get(cnt.get(k)!) === 0) {
                    freq.delete(cnt.get(k)!);
                }
            }
            cnt.set(k, (cnt.get(k) || 0) + 1);
            freq.set(cnt.get(k)!, (freq.get(cnt.get(k)!) || 0) + 1);
            if (freq.size === 1) {
                ans = Math.min(ans, 1 + f[j + 1]);
            }
        }
        f[i] = ans;
    }
    return f[0];
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Solution 5: Dynamic Programming (Optimization)

<!-- thinking:start -->

> **Thinking**
>
> Solution 1 deletes the old frequency and inserts the new one on every appended character. Balance is the same as the piece length equaling the maximum frequency times the number of distinct letters, so one $\textit{cnt}$ is enough. The answer at index $i$ still depends only on the right, so $f[i]$ is filled from the end. While extending $j$, track the number of distinct letters $k$ and the maximum frequency $m$, and update with $1+f[j+1]$ when $j-i+1=k\cdot m$.

<!-- thinking:end -->

The $\textit{freq}$ table in Solution 1 can be dropped. Keep only the character counts $\textit{cnt}$, the number of distinct letters $k$, and the maximum frequency $m$. $f[i]$ is still the fewest pieces starting at index $i$, with $f[n]=0$. Scan the start index from the right and extend $j$ to the right. If $j-i+1 = m \times k$, then $s[i..j]$ is balanced and $f[i]$ is updated with $1+f[j+1]$. The answer is $f[0]$.

The time complexity is $O(n^2)$, and the space complexity is $O(n + |\Sigma|)$. Here, $n$ is the length of the string $s$, and $|\Sigma|$ represents the size of the character set, which is $26$ in this problem.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minimumSubstringsInPartition(self, s: str) -> int:
        n = len(s)
        f = [0] * (n + 1)
        for i in range(n - 1, -1, -1):
            cnt = defaultdict(int)
            m = 0
            ans = n - i
            for j in range(i, n):
                cnt[s[j]] += 1
                m = max(m, cnt[s[j]])
                if j - i + 1 == m * len(cnt):
                    ans = min(ans, 1 + f[j + 1])
            f[i] = ans
        return f[0]
```

#### Java

```java
class Solution {
    public int minimumSubstringsInPartition(String s) {
        int n = s.length();
        char[] cs = s.toCharArray();
        int[] f = new int[n + 1];
        for (int i = n - 1; i >= 0; --i) {
            int[] cnt = new int[26];
            int ans = n - i;
            int k = 0, m = 0;
            for (int j = i; j < n; ++j) {
                k += ++cnt[cs[j] - 'a'] == 1 ? 1 : 0;
                m = Math.max(m, cnt[cs[j] - 'a']);
                if (j - i + 1 == k * m) {
                    ans = Math.min(ans, 1 + f[j + 1]);
                }
            }
            f[i] = ans;
        }
        return f[0];
    }
}
```

#### C++

```cpp
class Solution {
public:
    int minimumSubstringsInPartition(string s) {
        int n = s.size();
        vector<int> f(n + 1);
        for (int i = n - 1; i >= 0; --i) {
            int cnt[26]{};
            int ans = n - i;
            int k = 0, m = 0;
            for (int j = i; j < n; ++j) {
                k += ++cnt[s[j] - 'a'] == 1 ? 1 : 0;
                m = max(m, cnt[s[j] - 'a']);
                if (j - i + 1 == k * m) {
                    ans = min(ans, 1 + f[j + 1]);
                }
            }
            f[i] = ans;
        }
        return f[0];
    }
};
```

#### Go

```go
func minimumSubstringsInPartition(s string) int {
	n := len(s)
	f := make([]int, n+1)
	for i := n - 1; i >= 0; i-- {
		cnt := [26]int{}
		ans := n - i
		k, m := 0, 0
		for j := i; j < n; j++ {
			x := int(s[j] - 'a')
			cnt[x]++
			if cnt[x] == 1 {
				k++
			}
			m = max(m, cnt[x])
			if j-i+1 == k*m {
				ans = min(ans, 1+f[j+1])
			}
		}
		f[i] = ans
	}
	return f[0]
}
```

#### TypeScript

```ts
function minimumSubstringsInPartition(s: string): number {
    const n = s.length;
    const f: number[] = Array(n + 1).fill(0);
    for (let i = n - 1; i >= 0; --i) {
        const cnt: number[] = Array(26).fill(0);
        let ans = n - i;
        let [k, m] = [0, 0];
        for (let j = i; j < n; ++j) {
            const x = s.charCodeAt(j) - 97;
            k += ++cnt[x] === 1 ? 1 : 0;
            m = Math.max(m, cnt[x]);
            if (j - i + 1 === k * m) {
                ans = Math.min(ans, 1 + f[j + 1]);
            }
        }
        f[i] = ans;
    }
    return f[0];
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
