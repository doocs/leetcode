# [1653. Minimum Deletions to Make String Balanced](https://leetcode.com/problems/minimum-deletions-to-make-string-balanced)

[中文文档](/solution/1600-1699/1653.Minimum%20Deletions%20to%20Make%20String%20Balanced/README.md)

## Description

<p>You are given a string <code>s</code> consisting only of characters <code>&#39;a&#39;</code> and <code>&#39;b&#39;</code>​​​​.</p>

<p>You can delete any number of characters in <code>s</code> to make <code>s</code> <strong>balanced</strong>. <code>s</code> is <strong>balanced</strong> if there is no pair of indices <code>(i,j)</code> such that <code>i &lt; j</code> and <code>s[i] = &#39;b&#39;</code> and <code>s[j]= &#39;a&#39;</code>.</p>

<p>Return <em>the <strong>minimum</strong> number of deletions needed to make </em><code>s</code><em> <strong>balanced</strong></em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;aababbab&quot;
<strong>Output:</strong> 2
<strong>Explanation:</strong> You can either:
Delete the characters at 0-indexed positions 2 and 6 (&quot;aa<u>b</u>abb<u>a</u>b&quot; -&gt; &quot;aaabbb&quot;), or
Delete the characters at 0-indexed positions 3 and 6 (&quot;aab<u>a</u>bb<u>a</u>b&quot; -&gt; &quot;aabbbb&quot;).
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;bbaaaaabb&quot;
<strong>Output:</strong> 2
<strong>Explanation:</strong> The only solution is to delete the first two characters.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s[i]</code> is&nbsp;<code>&#39;a&#39;</code> or <code>&#39;b&#39;</code>​​.</li>
</ul>

## Solutions

Dynamic programming.

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def minimumDeletions(self, s: str) -> int:
        n = len(s)
        f = [0] * (n + 1)
        b = 0
        for i, c in enumerate(s, 1):
            if c == 'b':
                f[i] = f[i - 1]
                b += 1
            else:
                f[i] = min(f[i - 1] + 1, b)
        return f[n]
```

```python
class Solution:
    def minimumDeletions(self, s: str) -> int:
        ans = b = 0
        for c in s:
            if c == 'b':
                b += 1
            else:
                ans = min(ans + 1, b)
        return ans
```

```python
class Solution:
    def minimumDeletions(self, s: str) -> int:
        lb, ra = 0, s.count('a')
        ans = len(s)
        for c in s:
            ra -= c == 'a'
            ans = min(ans, lb + ra)
            lb += c == 'b'
        return ans
```

### **Java**

```java
class Solution {
    public int minimumDeletions(String s) {
        int n = s.length();
        int[] f = new int[n + 1];
        int b = 0;
        for (int i = 1; i <= n; ++i) {
            if (s.charAt(i - 1) == 'b') {
                f[i] = f[i - 1];
                ++b;
            } else {
                f[i] = Math.min(f[i - 1] + 1, b);
            }
        }
        return f[n];
    }
}
```

```java
class Solution {
    public int minimumDeletions(String s) {
        int n = s.length();
        int ans = 0, b = 0;
        for (int i = 0; i < n; ++i) {
            if (s.charAt(i) == 'b') {
                ++b;
            } else {
                ans = Math.min(ans + 1, b);
            }
        }
        return ans;
    }
}
```

```java
class Solution {
    public int minimumDeletions(String s) {
        int lb = 0, ra = 0;
        int n = s.length();
        for (int i = 0; i < n; ++i) {
            if (s.charAt(i) == 'a') {
                ++ra;
            }
        }
        int ans = n;
        for (int i = 0; i < n; ++i) {
            ra -= (s.charAt(i) == 'a' ? 1 : 0);
            ans = Math.min(ans, lb + ra);
            lb += (s.charAt(i) == 'b' ? 1 : 0);
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int minimumDeletions(string s) {
        int n = s.size();
        int f[n + 1];
        memset(f, 0, sizeof(f));
        int b = 0;
        for (int i = 1; i <= n; ++i) {
            if (s[i - 1] == 'b') {
                f[i] = f[i - 1];
                ++b;
            } else {
                f[i] = min(f[i - 1] + 1, b);
            }
        }
        return f[n];
    }
};
```

```cpp
class Solution {
public:
    int minimumDeletions(string s) {
        int ans = 0, b = 0;
        for (char& c : s) {
            if (c == 'b') {
                ++b;
            } else {
                ans = min(ans + 1, b);
            }
        }
        return ans;
    }
};
```

```cpp
class Solution {
public:
    int minimumDeletions(string s) {
        int lb = 0, ra = count(s.begin(), s.end(), 'a');
        int ans = ra;
        for (char& c : s) {
            ra -= c == 'a';
            ans = min(ans, lb + ra);
            lb += c == 'b';
        }
        return ans;
    }
};
```

### **Go**

```go
func minimumDeletions(s string) int {
	n := len(s)
	f := make([]int, n+1)
	b := 0
	for i, c := range s {
		i++
		if c == 'b' {
			f[i] = f[i-1]
			b++
		} else {
			f[i] = min(f[i-1]+1, b)
		}
	}
	return f[n]
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}
```

```go
func minimumDeletions(s string) int {
	ans, b := 0, 0
	for _, c := range s {
		if c == 'b' {
			b++
		} else {
			ans = min(ans+1, b)
		}
	}
	return ans
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}
```

```go
func minimumDeletions(s string) int {
	lb, ra := 0, strings.Count(s, "a")
	ans := ra
	for _, c := range s {
		if c == 'a' {
			ra--
		}
		if t := lb + ra; ans > t {
			ans = t
		}
		if c == 'b' {
			lb++
		}
	}
	return ans
}
```

### **TypeScript**

```ts
function minimumDeletions(s: string): number {
    const n = s.length;
    const f = new Array(n + 1).fill(0);
    let b = 0;
    for (let i = 1; i <= n; ++i) {
        if (s.charAt(i - 1) === 'b') {
            f[i] = f[i - 1];
            ++b;
        } else {
            f[i] = Math.min(f[i - 1] + 1, b);
        }
    }
    return f[n];
}
```

```ts
function minimumDeletions(s: string): number {
    const n = s.length;
    let ans = 0,
        b = 0;
    for (let i = 0; i < n; ++i) {
        if (s.charAt(i) === 'b') {
            ++b;
        } else {
            ans = Math.min(ans + 1, b);
        }
    }
    return ans;
}
```

```ts
function minimumDeletions(s: string): number {
    let lb = 0,
        ra = 0;
    const n = s.length;
    for (let i = 0; i < n; ++i) {
        if (s.charAt(i) === 'a') {
            ++ra;
        }
    }
    let ans = n;
    for (let i = 0; i < n; ++i) {
        ra -= s.charAt(i) === 'a' ? 1 : 0;
        ans = Math.min(ans, lb + ra);
        lb += s.charAt(i) === 'b' ? 1 : 0;
    }
    return ans;
}
```

### **...**

```

```

<!-- tabs:end -->
