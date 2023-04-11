# [1147. Longest Chunked Palindrome Decomposition](https://leetcode.com/problems/longest-chunked-palindrome-decomposition)

[中文文档](/solution/1100-1199/1147.Longest%20Chunked%20Palindrome%20Decomposition/README.md)

## Description

<p>You are given a string <code>text</code>. You should split it to k substrings <code>(subtext<sub>1</sub>, subtext<sub>2</sub>, ..., subtext<sub>k</sub>)</code> such that:</p>

<ul>
	<li><code>subtext<sub>i</sub></code> is a <strong>non-empty</strong> string.</li>
	<li>The concatenation of all the substrings is equal to <code>text</code> (i.e., <code>subtext<sub>1</sub> + subtext<sub>2</sub> + ... + subtext<sub>k</sub> == text</code>).</li>
	<li><code>subtext<sub>i</sub> == subtext<sub>k - i + 1</sub></code> for all valid values of <code>i</code> (i.e., <code>1 &lt;= i &lt;= k</code>).</li>
</ul>

<p>Return the largest possible value of <code>k</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> text = &quot;ghiabcdefhelloadamhelloabcdefghi&quot;
<strong>Output:</strong> 7
<strong>Explanation:</strong> We can split the string on &quot;(ghi)(abcdef)(hello)(adam)(hello)(abcdef)(ghi)&quot;.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> text = &quot;merchant&quot;
<strong>Output:</strong> 1
<strong>Explanation:</strong> We can split the string on &quot;(merchant)&quot;.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> text = &quot;antaprezatepzapreanta&quot;
<strong>Output:</strong> 11
<strong>Explanation:</strong> We can split the string on &quot;(a)(nt)(a)(pre)(za)(tep)(za)(pre)(a)(nt)(a)&quot;.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= text.length &lt;= 1000</code></li>
	<li><code>text</code> consists only of lowercase English characters.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def longestDecomposition(self, text: str) -> int:
        n = len(text)
        if n < 2:
            return n
        for i in range(n // 2 + 1):
            if text[:i] == text[-i:]:
                return 2 + self.longestDecomposition(text[i:-i])
        return 1
```

```python
class Solution:
    def longestDecomposition(self, text: str) -> int:
        ans = 0
        i, j = 0, len(text) - 1
        while i <= j:
            k = 1
            ok = False
            while i + k - 1 < j - k + 1:
                if text[i: i + k] == text[j - k + 1: j + 1]:
                    ans += 2
                    i += k
                    j -= k
                    ok = True
                    break
                k += 1
            if not ok:
                ans += 1
                break
        return ans
```

```python
class Solution:
    def longestDecomposition(self, text: str) -> int:
        def get(l, r):
            return (h[r] - h[l - 1] * p[r - l + 1]) % mod

        n = len(text)
        base = 131
        mod = int(1e9) + 7
        h = [0] * (n + 10)
        p = [1] * (n + 10)
        for i, c in enumerate(text):
            t = ord(c) - ord('a') + 1
            h[i + 1] = (h[i] * base) % mod + t
            p[i + 1] = (p[i] * base) % mod


        ans = 0
        i, j = 0, n - 1
        while i <= j:
            k = 1
            ok = False
            while i + k - 1 < j - k + 1:
                if get(i + 1, i + k) == get(j - k + 2, j + 1):
                    ans += 2
                    i += k
                    j -= k
                    ok = True
                    break
                k += 1
            if not ok:
                ans += 1
                break
        return ans
```

### **Java**

```java
class Solution {
    public int longestDecomposition(String text) {
        int n = text.length();
        if (n < 2) {
            return n;
        }
        for (int i = 1; i <= n >> 1; ++i) {
            if (text.substring(0, i).equals(text.substring(n - i))) {
                return 2 + longestDecomposition(text.substring(i, n - i));
            }
        }
        return 1;
    }
}
```

```java
class Solution {
    public int longestDecomposition(String text) {
        int ans = 0;
        for (int i = 0, j = text.length() - 1; i <= j;) {
            boolean ok = false;
            for (int k = 1; i + k - 1 < j - k + 1; ++k) {
                if (check(text, i, j - k + 1, k)) {
                    ans += 2;
                    i += k;
                    j -= k;
                    ok = true;
                    break;
                }
            }
            if (!ok) {
                ++ans;
                break;
            }
        }
        return ans;
    }

    private boolean check(String s, int i, int j, int k) {
        while (k-- > 0) {
            if (s.charAt(i++) != s.charAt(j++)) {
                return false;
            }
        }
        return true;
    }
}
```

```java
class Solution {
    private long[] h;
    private long[] p;

    public int longestDecomposition(String text) {
        int n = text.length();
        int base = 131;
        h = new long[n + 10];
        p = new long[n + 10];
        p[0] = 1;
        for (int i = 0; i < n; ++i) {
            int t = text.charAt(i) - 'a' + 1;
            h[i + 1] = h[i] * base + t;
            p[i + 1] = p[i] * base;
        }
        int ans = 0;
        for (int i = 0, j = n - 1; i <= j;) {
            boolean ok = false;
            for (int k = 1; i + k - 1 < j - k + 1; ++k) {
                if (get(i + 1, i + k) == get(j - k + 2, j + 1)) {
                    ans += 2;
                    i += k;
                    j -= k;
                    ok = true;
                    break;
                }
            }
            if (!ok) {
                ++ans;
                break;
            }
        }
        return ans;
    }

    private long get(int i, int j) {
        return h[j] - h[i - 1] * p[j - i + 1];
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int longestDecomposition(string text) {
        int n = text.size();
        if (n < 2) return n;
        for (int i = 1; i <= n >> 1; ++i) {
            if (text.substr(0, i) == text.substr(n - i)) {
                return 2 + longestDecomposition(text.substr(i, n - i - i));
            }
        }
        return 1;
    }
};
```

```cpp
class Solution {
public:
    int longestDecomposition(string text) {
        int ans = 0;
        auto check = [&](int i, int j, int k) -> bool {
            while (k--) {
                if (text[i++] != text[j++]) {
                    return false;
                }
            }
            return true;
        };
        for (int i = 0, j = text.size() - 1; i <= j;) {
            bool ok = false;
            for (int k = 1; i + k - 1 < j - k + 1; ++k) {
                if (check(i, j - k + 1, k)) {
                    ans += 2;
                    i += k;
                    j -= k;
                    ok = true;
                    break;
                }
            }
            if (!ok) {
                ans += 1;
                break;
            }
        }
        return ans;
    }
};
```

```cpp
class Solution {
public:
    int longestDecomposition(string text) {
        using ull = unsigned long long;
        int n = text.size();
        int base = 131;
        ull p[n + 10];
        ull h[n + 10];
        p[0] = 1;
        h[0] = 0;
        for (int i = 0; i < n; ++i) {
            int t = text[i] - 'a' + 1;
            p[i + 1] = p[i] * base;
            h[i + 1] = h[i] * base + t;
        }

        int ans = 0;
        auto get = [&](int l, int r) {
            return h[r] - h[l - 1] * p[r - l + 1];
        };
        for (int i = 0, j = n - 1; i <= j;) {
            bool ok = false;
            for (int k = 1; i + k - 1 < j - k + 1; ++k) {
                if (get(i + 1, i + k) == get(j - k + 2, j + 1)) {
                    ans += 2;
                    i += k;
                    j -= k;
                    ok = true;
                    break;
                }
            }
            if (!ok) {
                ++ans;
                break;
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func longestDecomposition(text string) int {
	n := len(text)
	if n < 2 {
		return n
	}
	for i := 1; i <= n>>1; i++ {
		if text[:i] == text[n-i:] {
			return 2 + longestDecomposition(text[i:n-i])
		}
	}
	return 1
}
```

```go
func longestDecomposition(text string) (ans int) {
	for i, j := 0, len(text)-1; i <= j; {
		ok := false
		for k := 1; i+k-1 < j-k+1; k++ {
			if text[i:i+k] == text[j-k+1:j+1] {
				ans += 2
				i += k
				j -= k
				ok = true
				break
			}
		}
		if !ok {
			ans++
			break
		}
	}
	return
}
```

```go
func longestDecomposition(text string) (ans int) {
	n := len(text)
	base := 131
	h := make([]int, n+10)
	p := make([]int, n+10)
	p[0] = 1
	for i, c := range text {
		t := int(c-'a') + 1
		p[i+1] = p[i] * base
		h[i+1] = h[i]*base + t
	}
	get := func(l, r int) int {
		return h[r] - h[l-1]*p[r-l+1]
	}

	for i, j := 0, n-1; i <= j; {
		ok := false
		for k := 1; i+k-1 < j-k+1; k++ {
			if get(i+1, i+k) == get(j-k+2, j+1) {
				ans += 2
				i += k
				j -= k
				ok = true
				break
			}
		}
		if !ok {
			ans++
			break
		}
	}
	return
}
```

### **TypeScript**

```ts
function longestDecomposition(text: string): number {
    const n: number = text.length;
    if (n < 2) {
        return n;
    }
    for (let i: number = 1; i <= n >> 1; i++) {
        if (text.slice(0, i) === text.slice(n - i)) {
            return 2 + longestDecomposition(text.slice(i, n - i));
        }
    }
    return 1;
}
```

```ts
function longestDecomposition(text: string): number {
    let ans = 0;
    for (let i = 0, j = text.length - 1; i <= j; ) {
        let ok = false;
        for (let k = 1; i + k - 1 < j - k + 1; ++k) {
            if (text.slice(i, i + k) === text.slice(j - k + 1, j + 1)) {
                ans += 2;
                i += k;
                j -= k;
                ok = true;
                break;
            }
        }
        if (!ok) {
            ++ans;
            break;
        }
    }
    return ans;
}
```

### **...**

```

```

<!-- tabs:end -->
