# [2767. Partition String Into Minimum Beautiful Substrings](https://leetcode.com/problems/partition-string-into-minimum-beautiful-substrings)

[中文文档](/solution/2700-2799/2767.Partition%20String%20Into%20Minimum%20Beautiful%20Substrings/README.md)

## Description

<p>Given a binary string <code>s</code>, partition the string into one or more <strong>substrings</strong> such that each substring is <strong>beautiful</strong>.</p>

<p>A string is <strong>beautiful</strong> if:</p>

<ul>
	<li>It doesn&#39;t contain leading zeros.</li>
	<li>It&#39;s the <strong>binary</strong> representation of a number that is a power of <code>5</code>.</li>
</ul>

<p>Return <em>the <strong>minimum</strong> number of substrings in such partition. </em>If it is impossible to partition the string <code>s</code> into beautiful substrings,&nbsp;return <code>-1</code>.</p>

<p>A <strong>substring</strong> is a contiguous sequence of characters in a string.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;1011&quot;
<strong>Output:</strong> 2
<strong>Explanation:</strong> We can paritition the given string into [&quot;101&quot;, &quot;1&quot;].
- The string &quot;101&quot; does not contain leading zeros and is the binary representation of integer 5<sup>1</sup> = 5.
- The string &quot;1&quot; does not contain leading zeros and is the binary representation of integer 5<sup>0</sup> = 1.
It can be shown that 2 is the minimum number of beautiful substrings that s can be partitioned into.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;111&quot;
<strong>Output:</strong> 3
<strong>Explanation:</strong> We can paritition the given string into [&quot;1&quot;, &quot;1&quot;, &quot;1&quot;].
- The string &quot;1&quot; does not contain leading zeros and is the binary representation of integer 5<sup>0</sup> = 1.
It can be shown that 3 is the minimum number of beautiful substrings that s can be partitioned into.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;0&quot;
<strong>Output:</strong> -1
<strong>Explanation:</strong> We can not partition the given string into beautiful substrings.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 15</code></li>
	<li><code>s[i]</code> is either <code>&#39;0&#39;</code> or <code>&#39;1&#39;</code>.</li>
</ul>

## Solutions

**Solution 1: Memoization Search**

Since the problem requires us to judge whether a string is the binary representation of a power of $5$, we might as well first preprocess all the powers of $5$ and record them in a hash table $ss$.

Next, we design a function $dfs(i)$, which indicates the minimum number of cuts from the $i$-th character of the string $s$ to the end of the string. Then the answer is $dfs(0)$.

The calculation method of function $dfs(i)$ is as follows:

-   If $i \geq n$, it means that all the characters have been processed, and the answer is $0$;
-   If $s[i] = 0$, it means that the current string contains leading $0$, which does not conform to the definition of a beautiful string, so the answer is infinite;
-   Otherwise, we enumerate the end position $j$ of the substring from $i$, and use $x$ to represent the decimal value of the substring $s[i..j]$. If $x$ is in the hash table $ss$, then we can take $s[i..j]$ as a beautiful substring, and the answer is $1 + dfs(j + 1)$. We need to enumerate all possible $j$ and take the minimum value of all answers.

In order to avoid repeated calculation, we can use the method of memory search.

In the main function, we first preprocess all the powers of $5$, and then call $dfs(0)$. If the return value is infinite, it means that the string $s$ cannot be divided into beautiful substrings, and the answer returns $-1$, otherwise, return the value of $dfs(0)$.

Time complexity $O(n^2)$, space complexity $O(n)$. Where $n$ is the length of string $s$.

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def minimumBeautifulSubstrings(self, s: str) -> int:
        @cache
        def dfs(i: int) -> int:
            if i >= n:
                return 0
            if s[i] == "0":
                return inf
            x = 0
            ans = inf
            for j in range(i, n):
                x = x << 1 | int(s[j])
                if x in ss:
                    ans = min(ans, 1 + dfs(j + 1))
            return ans

        n = len(s)
        x = 1
        ss = {x}
        for i in range(n):
            x *= 5
            ss.add(x)
        ans = dfs(0)
        return -1 if ans == inf else ans
```

### **Java**

```java
class Solution {
    private Integer[] f;
    private String s;
    private Set<Long> ss = new HashSet<>();
    private int n;

    public int minimumBeautifulSubstrings(String s) {
        n = s.length();
        this.s = s;
        f = new Integer[n];
        long x = 1;
        for (int i = 0; i <= n; ++i) {
            ss.add(x);
            x *= 5;
        }
        int ans = dfs(0);
        return ans > n ? -1 : ans;
    }

    private int dfs(int i) {
        if (i >= n) {
            return 0;
        }
        if (s.charAt(i) == '0') {
            return n + 1;
        }
        if (f[i] != null) {
            return f[i];
        }
        long x = 0;
        int ans = n + 1;
        for (int j = i; j < n; ++j) {
            x = x << 1 | (s.charAt(j) - '0');
            if (ss.contains(x)) {
                ans = Math.min(ans, 1 + dfs(j + 1));
            }
        }
        return f[i] = ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int minimumBeautifulSubstrings(string s) {
        unordered_set<long long> ss;
        int n = s.size();
        long long x = 1;
        for (int i = 0; i <= n; ++i) {
            ss.insert(x);
            x *= 5;
        }
        int f[n];
        memset(f, -1, sizeof(f));
        function<int(int)> dfs = [&](int i) {
            if (i >= n) {
                return 0;
            }
            if (s[i] == '0') {
                return n + 1;
            }
            if (f[i] != -1) {
                return f[i];
            }
            long long x = 0;
            int ans = n + 1;
            for (int j = i; j < n; ++j) {
                x = x << 1 | (s[j] - '0');
                if (ss.count(x)) {
                    ans = min(ans, 1 + dfs(j + 1));
                }
            }
            return f[i] = ans;
        };
        int ans = dfs(0);
        return ans > n ? -1 : ans;
    }
};
```

### **Go**

```go
func minimumBeautifulSubstrings(s string) int {
	ss := map[int]bool{}
	n := len(s)
	x := 1
	f := make([]int, n+1)
	for i := 0; i <= n; i++ {
		ss[x] = true
		x *= 5
		f[i] = -1
	}
	var dfs func(int) int
	dfs = func(i int) int {
		if i >= n {
			return 0
		}
		if s[i] == '0' {
			return n + 1
		}
		if f[i] != -1 {
			return f[i]
		}
		f[i] = n + 1
		x := 0
		for j := i; j < n; j++ {
			x = x<<1 | int(s[j]-'0')
			if ss[x] {
				f[i] = min(f[i], 1+dfs(j+1))
			}
		}
		return f[i]
	}
	ans := dfs(0)
	if ans > n {
		return -1
	}
	return ans
}
```

### **TypeScript**

```ts
function minimumBeautifulSubstrings(s: string): number {
    const ss: Set<number> = new Set();
    const n = s.length;
    const f: number[] = new Array(n).fill(-1);
    for (let i = 0, x = 1; i <= n; ++i) {
        ss.add(x);
        x *= 5;
    }
    const dfs = (i: number): number => {
        if (i === n) {
            return 0;
        }
        if (s[i] === '0') {
            return n + 1;
        }
        if (f[i] !== -1) {
            return f[i];
        }
        f[i] = n + 1;
        for (let j = i, x = 0; j < n; ++j) {
            x = (x << 1) | (s[j] === '1' ? 1 : 0);
            if (ss.has(x)) {
                f[i] = Math.min(f[i], 1 + dfs(j + 1));
            }
        }
        return f[i];
    };
    const ans = dfs(0);
    return ans > n ? -1 : ans;
}
```

### **...**

```

```

<!-- tabs:end -->
