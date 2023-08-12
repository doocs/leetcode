# [471. 编码最短长度的字符串](https://leetcode.cn/problems/encode-string-with-shortest-length)

[English Version](/solution/0400-0499/0471.Encode%20String%20with%20Shortest%20Length/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个 <strong>非空</strong> 字符串，将其编码为具有最短长度的字符串。</p>

<p>编码规则是：<code>k[encoded_string]</code>，其中在方括号 <code>encoded_string</code><em> </em>中的内容重复 <code>k</code> 次。</p>

<p><strong>注：</strong></p>

<ul>
	<li><em>k</em> 为正整数</li>
	<li>如果编码的过程不能使字符串缩短，则不要对其进行编码。如果有多种编码方式，返回 <strong>任意一种</strong> 即可</li>
</ul>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>s = "aaa"
<strong>输出：</strong>"aaa"
<strong>解释：</strong>无法将其编码为更短的字符串，因此不进行编码。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>s = "aaaaa"
<strong>输出：</strong>"5[a]"
<strong>解释：</strong>"5[a]" 比 "aaaaa" 短 1 个字符。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>s = "aaaaaaaaaa"
<strong>输出：</strong>"10[a]"
<strong>解释：</strong>"a9[a]" 或 "9[a]a" 都是合法的编码，和 "10[a]" 一样长度都为 5。
</pre>

<p><strong>示例 4：</strong></p>

<pre>
<strong>输入：</strong>s = "aabcaabcd"
<strong>输出：</strong>"2[aabc]d"
<strong>解释：</strong>"aabc" 出现两次，因此一种答案可以是 "2[aabc]d"。
</pre>

<p><strong>示例 5：</strong></p>

<pre>
<strong>输入：</strong>s = "abbbabbbcabbbabbbc"
<strong>输出：</strong>"2[2[abbb]c]"
<strong>解释：</strong>"abbbabbbc" 出现两次，但是 "abbbabbbc" 可以编码为 "2[abbb]c"，因此一种答案可以是 "2[2[abbb]c]"。
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= s.length <= 150</code></li>
	<li><code>s</code> 由小写英文字母组成</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：动态规划（区间 DP）**

在这道题中，我们需要判断一个字符串是否能够进行压缩，也即是说，一个字符串是否能通过其子串重复多次构成。我们可以利用第 $459$ 题的方法来判断，定义一个方法 $g(i, j)$，表示将字符串 $s[i...j]$ 进行压缩后得到的字符串。

接下来，我们用动态规划的方法，将字符串 $s$ 编码成一个最短长度的字符串。

我们定义 $f[i][j]$ 表示将字符串 $s[i..j]$ 编码后的最短字符串。如果直接将 $s[i..j]$ 进行压缩编码，那么 $f[i][j] = g(i, j)$，如果我们将其分成两个子串进行编码，那么 $f[i][j]$ 的值为 $f[i][k] + f[k + 1][j]$ 的最小值，其中 $i \le k < j$。取两种情况下长度较小的字符串即可。

在枚举 $i$ 和 $j$ 时，我们可以从大到小枚举 $i$，然后从小到大枚举 $j$，这样我们在计算 $f[i][j]$ 时，$f[i][k]$ 和 $f[k + 1][j]$ 的值都已经被计算过了。

时间复杂度 $O(n^3)$，空间复杂度 $O(n^2)$。其中 $n$ 是字符串 $s$ 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

### **C++**

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

### **Go**

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

### **TypeScript**

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

### **...**

```

```

<!-- tabs:end -->
