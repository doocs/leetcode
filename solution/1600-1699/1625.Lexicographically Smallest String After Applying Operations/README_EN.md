---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1600-1699/1625.Lexicographically%20Smallest%20String%20After%20Applying%20Operations/README_EN.md
rating: 1992
source: Weekly Contest 211 Q2
tags:
    - Depth-First Search
    - Breadth-First Search
    - String
    - Enumeration
---

<!-- problem:start -->

# [1625. Lexicographically Smallest String After Applying Operations](https://leetcode.com/problems/lexicographically-smallest-string-after-applying-operations)

[中文文档](/solution/1600-1699/1625.Lexicographically%20Smallest%20String%20After%20Applying%20Operations/README.md)

## Description

<!-- description:start -->

<p>You are given a string <code>s</code> of <strong>even length</strong> consisting of digits from <code>0</code> to <code>9</code>, and two integers <code>a</code> and <code>b</code>.</p>

<p>You can apply either of the following two operations any number of times and in any order on <code>s</code>:</p>

<ul>
	<li>Add <code>a</code> to all odd indices of <code>s</code> <strong>(0-indexed)</strong>. Digits post <code>9</code> are cycled back to <code>0</code>. For example, if <code>s = &quot;3456&quot;</code> and <code>a = 5</code>, <code>s</code> becomes <code>&quot;3951&quot;</code>.</li>
	<li>Rotate <code>s</code> to the right by <code>b</code> positions. For example, if <code>s = &quot;3456&quot;</code> and <code>b = 1</code>, <code>s</code> becomes <code>&quot;6345&quot;</code>.</li>
</ul>

<p>Return <em>the <strong>lexicographically smallest</strong> string you can obtain by applying the above operations any number of times on</em> <code>s</code>.</p>

<p>A string <code>a</code> is lexicographically smaller than a string <code>b</code> (of the same length) if in the first position where <code>a</code> and <code>b</code> differ, string <code>a</code> has a letter that appears earlier in the alphabet than the corresponding letter in <code>b</code>. For example, <code>&quot;0158&quot;</code> is lexicographically smaller than <code>&quot;0190&quot;</code> because the first position they differ is at the third letter, and <code>&#39;5&#39;</code> comes before <code>&#39;9&#39;</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;5525&quot;, a = 9, b = 2
<strong>Output:</strong> &quot;2050&quot;
<strong>Explanation:</strong> We can apply the following operations:
Start:  &quot;5525&quot;
Rotate: &quot;2555&quot;
Add:    &quot;2454&quot;
Add:    &quot;2353&quot;
Rotate: &quot;5323&quot;
Add:    &quot;5222&quot;
Add:    &quot;5121&quot;
Rotate: &quot;2151&quot;
Add:    &quot;2050&quot;​​​​​
There is no way to obtain a string that is lexicographically smaller than &quot;2050&quot;.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;74&quot;, a = 5, b = 1
<strong>Output:</strong> &quot;24&quot;
<strong>Explanation:</strong> We can apply the following operations:
Start:  &quot;74&quot;
Rotate: &quot;47&quot;
​​​​​​​Add:    &quot;42&quot;
​​​​​​​Rotate: &quot;24&quot;​​​​​​​​​​​​
There is no way to obtain a string that is lexicographically smaller than &quot;24&quot;.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;0011&quot;, a = 4, b = 2
<strong>Output:</strong> &quot;0011&quot;
<strong>Explanation:</strong> There are no sequence of operations that will give us a lexicographically smaller string than &quot;0011&quot;.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= s.length &lt;= 100</code></li>
	<li><code>s.length</code> is even.</li>
	<li><code>s</code> consists of digits from <code>0</code> to <code>9</code> only.</li>
	<li><code>1 &lt;= a &lt;= 9</code></li>
	<li><code>1 &lt;= b &lt;= s.length - 1</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: BFS

Since the data scale of this problem is relatively small, we can use BFS to brute-force search all possible states and then take the lexicographically smallest state.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def findLexSmallestString(self, s: str, a: int, b: int) -> str:
        q = deque([s])
        vis = {s}
        ans = s
        while q:
            s = q.popleft()
            if ans > s:
                ans = s
            t1 = ''.join(
                [str((int(c) + a) % 10) if i & 1 else c for i, c in enumerate(s)]
            )
            t2 = s[-b:] + s[:-b]
            for t in (t1, t2):
                if t not in vis:
                    vis.add(t)
                    q.append(t)
        return ans
```

#### Java

```java
class Solution {
    public String findLexSmallestString(String s, int a, int b) {
        Deque<String> q = new ArrayDeque<>();
        q.offer(s);
        Set<String> vis = new HashSet<>();
        vis.add(s);
        String ans = s;
        int n = s.length();
        while (!q.isEmpty()) {
            s = q.poll();
            if (ans.compareTo(s) > 0) {
                ans = s;
            }
            char[] cs = s.toCharArray();
            for (int i = 1; i < n; i += 2) {
                cs[i] = (char) (((cs[i] - '0' + a) % 10) + '0');
            }
            String t1 = String.valueOf(cs);
            String t2 = s.substring(n - b) + s.substring(0, n - b);
            for (String t : List.of(t1, t2)) {
                if (vis.add(t)) {
                    q.offer(t);
                }
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
    string findLexSmallestString(string s, int a, int b) {
        queue<string> q{{s}};
        unordered_set<string> vis{{s}};
        string ans = s;
        int n = s.size();
        while (!q.empty()) {
            s = q.front();
            q.pop();
            ans = min(ans, s);
            string t1 = s;
            for (int i = 1; i < n; i += 2) {
                t1[i] = (t1[i] - '0' + a) % 10 + '0';
            }
            string t2 = s.substr(n - b) + s.substr(0, n - b);
            for (auto& t : {t1, t2}) {
                if (!vis.count(t)) {
                    vis.insert(t);
                    q.emplace(t);
                }
            }
        }
        return ans;
    }
};
```

#### Go

```go
func findLexSmallestString(s string, a int, b int) string {
	q := []string{s}
	vis := map[string]bool{s: true}
	ans := s
	n := len(s)
	for len(q) > 0 {
		s = q[0]
		q = q[1:]
		if ans > s {
			ans = s
		}
		t1 := []byte(s)
		for i := 1; i < n; i += 2 {
			t1[i] = byte((int(t1[i]-'0')+a)%10 + '0')
		}
		t2 := s[n-b:] + s[:n-b]
		for _, t := range []string{string(t1), t2} {
			if !vis[t] {
				vis[t] = true
				q = append(q, t)
			}
		}
	}
	return ans
}
```

#### TypeScript

```ts
function findLexSmallestString(s: string, a: number, b: number): string {
    const q: string[] = [s];
    const vis = new Set<string>([s]);
    let ans = s;
    let i = 0;
    while (i < q.length) {
        s = q[i++];
        if (ans > s) {
            ans = s;
        }
        const t1 = s
            .split('')
            .map((c, j) => (j & 1 ? String((Number(c) + a) % 10) : c))
            .join('');
        const t2 = s.slice(-b) + s.slice(0, -b);
        for (const t of [t1, t2]) {
            if (!vis.has(t)) {
                vis.add(t);
                q.push(t);
            }
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Solution 2: Enumeration

We observe that for the addition operation, a digit will return to its original state after at most $10$ additions; for the rotation operation, the string will also return to its original state after at most $n$ rotations.

Therefore, the rotation operation produces at most $n$ states. If the rotation count $b$ is even, the addition operation only affects digits at odd indices, resulting in a total of $n \times 10$ states; if the rotation count $b$ is odd, the addition operation affects both odd and even index digits, resulting in a total of $n \times 10 \times 10$ states.

Thus, we can directly enumerate all possible string states and take the lexicographically smallest one.

The time complexity is $O(n^2 \times 10^2)$ and the space complexity is $O(n)$, where $n$ is the length of string $s$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def findLexSmallestString(self, s: str, a: int, b: int) -> str:
        ans = s
        n = len(s)
        s = list(s)
        for _ in range(n):
            s = s[-b:] + s[:-b]
            for j in range(10):
                for k in range(1, n, 2):
                    s[k] = str((int(s[k]) + a) % 10)
                if b & 1:
                    for p in range(10):
                        for k in range(0, n, 2):
                            s[k] = str((int(s[k]) + a) % 10)
                        t = ''.join(s)
                        if ans > t:
                            ans = t
                else:
                    t = ''.join(s)
                    if ans > t:
                        ans = t
        return ans
```

#### Java

```java
class Solution {
    public String findLexSmallestString(String s, int a, int b) {
        int n = s.length();
        String ans = s;
        for (int i = 0; i < n; ++i) {
            s = s.substring(b) + s.substring(0, b);
            char[] cs = s.toCharArray();
            for (int j = 0; j < 10; ++j) {
                for (int k = 1; k < n; k += 2) {
                    cs[k] = (char) (((cs[k] - '0' + a) % 10) + '0');
                }
                if ((b & 1) == 1) {
                    for (int p = 0; p < 10; ++p) {
                        for (int k = 0; k < n; k += 2) {
                            cs[k] = (char) (((cs[k] - '0' + a) % 10) + '0');
                        }
                        s = String.valueOf(cs);
                        if (ans.compareTo(s) > 0) {
                            ans = s;
                        }
                    }
                } else {
                    s = String.valueOf(cs);
                    if (ans.compareTo(s) > 0) {
                        ans = s;
                    }
                }
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
    string findLexSmallestString(string s, int a, int b) {
        int n = s.size();
        string ans = s;
        for (int i = 0; i < n; ++i) {
            s = s.substr(n - b) + s.substr(0, n - b);
            for (int j = 0; j < 10; ++j) {
                for (int k = 1; k < n; k += 2) {
                    s[k] = (s[k] - '0' + a) % 10 + '0';
                }
                if (b & 1) {
                    for (int p = 0; p < 10; ++p) {
                        for (int k = 0; k < n; k += 2) {
                            s[k] = (s[k] - '0' + a) % 10 + '0';
                        }
                        ans = min(ans, s);
                    }
                } else {
                    ans = min(ans, s);
                }
            }
        }
        return ans;
    }
};
```

#### Go

```go
func findLexSmallestString(s string, a int, b int) string {
	n := len(s)
	ans := s
	for _ = range s {
		s = s[n-b:] + s[:n-b]
		cs := []byte(s)
		for j := 0; j < 10; j++ {
			for k := 1; k < n; k += 2 {
				cs[k] = byte((int(cs[k]-'0')+a)%10 + '0')
			}
			if b&1 == 1 {
				for p := 0; p < 10; p++ {
					for k := 0; k < n; k += 2 {
						cs[k] = byte((int(cs[k]-'0')+a)%10 + '0')
					}
					s = string(cs)
					if ans > s {
						ans = s
					}
				}
			} else {
				s = string(cs)
				if ans > s {
					ans = s
				}
			}
		}
	}
	return ans
}
```

#### TypeScript

```ts
function findLexSmallestString(s: string, a: number, b: number): string {
    let ans = s;
    const n = s.length;
    let arr = s.split('');
    for (let _ = 0; _ < n; _++) {
        arr = arr.slice(-b).concat(arr.slice(0, -b));
        for (let j = 0; j < 10; j++) {
            for (let k = 1; k < n; k += 2) {
                arr[k] = String((Number(arr[k]) + a) % 10);
            }
            if (b & 1) {
                for (let p = 0; p < 10; p++) {
                    for (let k = 0; k < n; k += 2) {
                        arr[k] = String((Number(arr[k]) + a) % 10);
                    }
                    const t = arr.join('');
                    if (ans > t) {
                        ans = t;
                    }
                }
            } else {
                const t = arr.join('');
                if (ans > t) {
                    ans = t;
                }
            }
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
