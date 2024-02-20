# [471. Encode String with Shortest Length](https://leetcode.com/problems/encode-string-with-shortest-length)

[中文文档](/solution/0400-0499/0471.Encode%20String%20with%20Shortest%20Length/README.md)

<!-- tags:String,Dynamic Programming -->

## Description

<p>Given a string <code>s</code>, encode the string such that its encoded length is the shortest.</p>

<p>The encoding rule is: <code>k[encoded_string]</code>, where the <code>encoded_string</code> inside the square brackets is being repeated exactly <code>k</code> times. <code>k</code> should be a positive integer.</p>

<p>If an encoding process does not make the string shorter, then do not encode it. If there are several solutions, return <strong>any of them</strong>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;aaa&quot;
<strong>Output:</strong> &quot;aaa&quot;
<strong>Explanation:</strong> There is no way to encode it such that it is shorter than the input string, so we do not encode it.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;aaaaa&quot;
<strong>Output:</strong> &quot;5[a]&quot;
<strong>Explanation:</strong> &quot;5[a]&quot; is shorter than &quot;aaaaa&quot; by 1 character.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;aaaaaaaaaa&quot;
<strong>Output:</strong> &quot;10[a]&quot;
<strong>Explanation:</strong> &quot;a9[a]&quot; or &quot;9[a]a&quot; are also valid solutions, both of them have the same length = 5, which is the same as &quot;10[a]&quot;.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 150</code></li>
	<li><code>s</code> consists of only lowercase English letters.</li>
</ul>

## Solutions

### Solution 1

<!-- tabs:start -->

```python
class Solution:
    def encode(self, s: str) -> str:
        def g(i: int, j: int) -> str:
            t = s[i : j + 1]
            if len(t) < 5:
                return t
            k = (t + t).index(t, 1)
            if k < len(t):
                cnt = len(t) // k
                return f"{cnt}[{f[i][i + k - 1]}]"
            return t

        n = len(s)
        f = [[None] * n for _ in range(n)]
        for i in range(n - 1, -1, -1):
            for j in range(i, n):
                f[i][j] = g(i, j)
                if j - i + 1 > 4:
                    for k in range(i, j):
                        t = f[i][k] + f[k + 1][j]
                        if len(f[i][j]) > len(t):
                            f[i][j] = t
        return f[0][-1]
```

```java
class Solution {
    private String s;
    private String[][] f;

    public String encode(String s) {
        this.s = s;
        int n = s.length();
        f = new String[n][n];
        for (int i = n - 1; i >= 0; --i) {
            for (int j = i; j < n; ++j) {
                f[i][j] = g(i, j);
                if (j - i + 1 > 4) {
                    for (int k = i; k < j; ++k) {
                        String t = f[i][k] + f[k + 1][j];
                        if (f[i][j].length() > t.length()) {
                            f[i][j] = t;
                        }
                    }
                }
            }
        }
        return f[0][n - 1];
    }

    private String g(int i, int j) {
        String t = s.substring(i, j + 1);
        if (t.length() < 5) {
            return t;
        }
        int k = (t + t).indexOf(t, 1);
        if (k < t.length()) {
            int cnt = t.length() / k;
            return String.format("%d[%s]", cnt, f[i][i + k - 1]);
        }
        return t;
    }
}
```

```cpp
class Solution {
public:
    string encode(string s) {
        int n = s.size();
        vector<vector<string>> f(n, vector<string>(n));

        auto g = [&](int i, int j) {
            string t = s.substr(i, j - i + 1);
            if (t.size() < 5) {
                return t;
            }
            int k = (t + t).find(t, 1);
            if (k < t.size()) {
                int cnt = t.size() / k;
                return to_string(cnt) + "[" + f[i][i + k - 1] + "]";
            }
            return t;
        };

        for (int i = n - 1; ~i; --i) {
            for (int j = i; j < n; ++j) {
                f[i][j] = g(i, j);
                if (j - i + 1 > 4) {
                    for (int k = i; k < j; ++k) {
                        string t = f[i][k] + f[k + 1][j];
                        if (t.size() < f[i][j].size()) {
                            f[i][j] = t;
                        }
                    }
                }
            }
        }
        return f[0][n - 1];
    }
};
```

```go
func encode(s string) string {
	n := len(s)
	f := make([][]string, n)
	for i := range f {
		f[i] = make([]string, n)
	}
	g := func(i, j int) string {
		t := s[i : j+1]
		if len(t) < 5 {
			return t
		}
		k := strings.Index((t + t)[1:], t) + 1
		if k < len(t) {
			cnt := len(t) / k
			return strconv.Itoa(cnt) + "[" + f[i][i+k-1] + "]"
		}
		return t
	}
	for i := n - 1; i >= 0; i-- {
		for j := i; j < n; j++ {
			f[i][j] = g(i, j)
			if j-i+1 > 4 {
				for k := i; k < j; k++ {
					t := f[i][k] + f[k+1][j]
					if len(t) < len(f[i][j]) {
						f[i][j] = t
					}
				}
			}
		}
	}
	return f[0][n-1]
}
```

```ts
function encode(s: string): string {
    const n = s.length;
    const f: string[][] = new Array(n).fill(0).map(() => new Array(n).fill(''));
    const g = (i: number, j: number): string => {
        const t = s.slice(i, j + 1);
        if (t.length < 5) {
            return t;
        }
        const k = t.repeat(2).indexOf(t, 1);
        if (k < t.length) {
            const cnt = Math.floor(t.length / k);
            return cnt + '[' + f[i][i + k - 1] + ']';
        }
        return t;
    };
    for (let i = n - 1; i >= 0; --i) {
        for (let j = i; j < n; ++j) {
            f[i][j] = g(i, j);
            if (j - i + 1 > 4) {
                for (let k = i; k < j; ++k) {
                    const t = f[i][k] + f[k + 1][j];
                    if (t.length < f[i][j].length) {
                        f[i][j] = t;
                    }
                }
            }
        }
    }
    return f[0][n - 1];
}
```

<!-- tabs:end -->

<!-- end -->
