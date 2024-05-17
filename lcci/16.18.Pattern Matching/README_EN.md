---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/lcci/16.18.Pattern%20Matching/README_EN.md
---

<!-- problem:start -->

# [16.18. Pattern Matching](https://leetcode.cn/problems/pattern-matching-lcci)

[中文文档](/lcci/16.18.Pattern%20Matching/README.md)

## Description

<!-- description:start -->

<p>You are given two strings, pattern and value. The pattern string consists of just the letters a and b, describing a pattern within a string. For example, the string catcatgocatgo matches the pattern aabab (where cat is a and go is b). It also matches patterns like a, ab, and b. Write a method to determine if value matches pattern. a and b cannot be the same string.</p>
<p><strong>Example 1: </strong></p>
<pre>

<strong>Input: </strong> pattern = &quot;abba&quot;, value = &quot;dogcatcatdog&quot;

<strong>Output: </strong> true

</pre>
<p><strong>Example 2: </strong></p>
<pre>

<strong>Input: </strong> pattern = &quot;abba&quot;, value = &quot;dogcatcatfish&quot;

<strong>Output: </strong> false

</pre>
<p><strong>Example 3: </strong></p>
<pre>

<strong>Input: </strong> pattern = &quot;aaaa&quot;, value = &quot;dogcatcatdog&quot;

<strong>Output: </strong> false

</pre>
<p><strong>Example 4: </strong></p>
<pre>

<strong>Input: </strong> pattern = &quot;abba&quot;, value = &quot;dogdogdogdog&quot;

<strong>Output: </strong> true

<strong>Explanation: </strong> &quot;a&quot;=&quot;dogdog&quot;,b=&quot;&quot;，vice versa.

</pre>
<p><strong>Note: </strong></p>
<ul>
	<li><code>0 &lt;= len(pattern) &lt;= 1000</code></li>
	<li><code>0 &lt;= len(value) &lt;= 1000</code></li>
	<li><code>pattern</code>&nbsp;only contains&nbsp;<code>&quot;a&quot;</code>&nbsp;and&nbsp;<code>&quot;b&quot;</code>,&nbsp;<code>value</code> only contains lowercase letters.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Enumeration

We first count the number of characters `'a'` and `'b'` in the pattern string $pattern$, denoted as $cnt[0]$ and $cnt[1]$, respectively. Let the length of the string $value$ be $n$.

If $cnt[0]=0$, it means that the pattern string only contains the character `'b'`. We need to check whether $n$ is a multiple of $cnt[1]$, and whether $value$ can be divided into $cnt[1]$ substrings of length $n/cnt[1]$, and all these substrings are the same. If not, return $false$ directly.

If $cnt[1]=0$, it means that the pattern string only contains the character `'a'`. We need to check whether $n$ is a multiple of $cnt[0]$, and whether $value$ can be divided into $cnt[0]$ substrings of length $n/cnt[0]$, and all these substrings are the same. If not, return $false$ directly.

Next, we denote the length of the string matched by the character `'a'` as $la$, and the length of the string matched by the character `'b'` as $lb$. Then we have $la \times cnt[0] + lb \times cnt[1] = n$. If we enumerate $la$, we can determine the value of $lb$. Therefore, we can enumerate $la$ and check whether there exists an integer $lb$ that satisfies the above equation.

The time complexity is $O(n^2)$, and the space complexity is $O(n)$. Here, $n$ is the length of the string $value$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def patternMatching(self, pattern: str, value: str) -> bool:
        def check(la: int, lb: int) -> bool:
            i = 0
            a, b = "", ""
            for c in pattern:
                if c == "a":
                    if a and value[i : i + la] != a:
                        return False
                    a = value[i : i + la]
                    i += la
                else:
                    if b and value[i : i + lb] != b:
                        return False
                    b = value[i : i + lb]
                    i += lb
            return a != b

        n = len(value)
        cnt = Counter(pattern)
        if cnt["a"] == 0:
            return n % cnt["b"] == 0 and value[: n // cnt["b"]] * cnt["b"] == value
        if cnt["b"] == 0:
            return n % cnt["a"] == 0 and value[: n // cnt["a"]] * cnt["a"] == value

        for la in range(n + 1):
            if la * cnt["a"] > n:
                break
            lb, mod = divmod(n - la * cnt["a"], cnt["b"])
            if mod == 0 and check(la, lb):
                return True
        return False
```

#### Java

```java
class Solution {
    private String pattern;
    private String value;

    public boolean patternMatching(String pattern, String value) {
        this.pattern = pattern;
        this.value = value;
        int[] cnt = new int[2];
        for (char c : pattern.toCharArray()) {
            ++cnt[c - 'a'];
        }
        int n = value.length();
        if (cnt[0] == 0) {
            return n % cnt[1] == 0 && value.substring(0, n / cnt[1]).repeat(cnt[1]).equals(value);
        }
        if (cnt[1] == 0) {
            return n % cnt[0] == 0 && value.substring(0, n / cnt[0]).repeat(cnt[0]).equals(value);
        }
        for (int la = 0; la <= n; ++la) {
            if (la * cnt[0] > n) {
                break;
            }
            if ((n - la * cnt[0]) % cnt[1] == 0) {
                int lb = (n - la * cnt[0]) / cnt[1];
                if (check(la, lb)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean check(int la, int lb) {
        int i = 0;
        String a = null, b = null;
        for (char c : pattern.toCharArray()) {
            if (c == 'a') {
                if (a != null && !a.equals(value.substring(i, i + la))) {
                    return false;
                }
                a = value.substring(i, i + la);
                i += la;
            } else {
                if (b != null && !b.equals(value.substring(i, i + lb))) {
                    return false;
                }
                b = value.substring(i, i + lb);
                i += lb;
            }
        }
        return !a.equals(b);
    }
}
```

#### C++

```cpp
class Solution {
public:
    bool patternMatching(string pattern, string value) {
        int n = value.size();
        int cnt[2]{};
        for (char c : pattern) {
            cnt[c - 'a']++;
        }
        if (cnt[0] == 0) {
            return n % cnt[1] == 0 && repeat(value.substr(0, n / cnt[1]), cnt[1]) == value;
        }
        if (cnt[1] == 0) {
            return n % cnt[0] == 0 && repeat(value.substr(0, n / cnt[0]), cnt[0]) == value;
        }
        auto check = [&](int la, int lb) {
            int i = 0;
            string a, b;
            for (char c : pattern) {
                if (c == 'a') {
                    if (!a.empty() && a != value.substr(i, la)) {
                        return false;
                    }
                    a = value.substr(i, la);
                    i += la;
                } else {
                    if (!b.empty() && b != value.substr(i, lb)) {
                        return false;
                    }
                    b = value.substr(i, lb);
                    i += lb;
                }
            }
            return a != b;
        };
        for (int la = 0; la <= n; ++la) {
            if (la * cnt[0] > n) {
                break;
            }
            if ((n - la * cnt[0]) % cnt[1] == 0) {
                int lb = (n - la * cnt[0]) / cnt[1];
                if (check(la, lb)) {
                    return true;
                }
            }
        }
        return false;
    }

    string repeat(string s, int n) {
        string ans;
        while (n--) {
            ans += s;
        }
        return ans;
    }
};
```

#### Go

```go
func patternMatching(pattern string, value string) bool {
	cnt := [2]int{}
	for _, c := range pattern {
		cnt[c-'a']++
	}
	n := len(value)
	if cnt[0] == 0 {
		return n%cnt[1] == 0 && strings.Repeat(value[:n/cnt[1]], cnt[1]) == value
	}
	if cnt[1] == 0 {
		return n%cnt[0] == 0 && strings.Repeat(value[:n/cnt[0]], cnt[0]) == value
	}
	check := func(la, lb int) bool {
		i := 0
		a, b := "", ""
		for _, c := range pattern {
			if c == 'a' {
				if a != "" && value[i:i+la] != a {
					return false
				}
				a = value[i : i+la]
				i += la
			} else {
				if b != "" && value[i:i+lb] != b {
					return false
				}
				b = value[i : i+lb]
				i += lb
			}
		}
		return a != b
	}
	for la := 0; la <= n; la++ {
		if la*cnt[0] > n {
			break
		}
		if (n-la*cnt[0])%cnt[1] == 0 {
			lb := (n - la*cnt[0]) / cnt[1]
			if check(la, lb) {
				return true
			}
		}
	}
	return false
}
```

#### TypeScript

```ts
function patternMatching(pattern: string, value: string): boolean {
    const cnt: number[] = [0, 0];
    for (const c of pattern) {
        cnt[c === 'a' ? 0 : 1]++;
    }
    const n = value.length;
    if (cnt[0] === 0) {
        return n % cnt[1] === 0 && value.slice(0, (n / cnt[1]) | 0).repeat(cnt[1]) === value;
    }
    if (cnt[1] === 0) {
        return n % cnt[0] === 0 && value.slice(0, (n / cnt[0]) | 0).repeat(cnt[0]) === value;
    }
    const check = (la: number, lb: number) => {
        let i = 0;
        let a = '';
        let b = '';
        for (const c of pattern) {
            if (c === 'a') {
                if (a && a !== value.slice(i, i + la)) {
                    return false;
                }
                a = value.slice(i, (i += la));
            } else {
                if (b && b !== value.slice(i, i + lb)) {
                    return false;
                }
                b = value.slice(i, (i += lb));
            }
        }
        return a !== b;
    };
    for (let la = 0; la <= n; ++la) {
        if (la * cnt[0] > n) {
            break;
        }
        if ((n - la * cnt[0]) % cnt[1] === 0) {
            const lb = ((n - la * cnt[0]) / cnt[1]) | 0;
            if (check(la, lb)) {
                return true;
            }
        }
    }
    return false;
}
```

#### Swift

```swift
class Solution {
    private var pattern: String = ""
    private var value: String = ""

    func patternMatching(_ pattern: String, _ value: String) -> Bool {
        self.pattern = pattern
        self.value = value
        var cnt = [Int](repeating: 0, count: 2)
        for c in pattern {
            cnt[Int(c.asciiValue! - Character("a").asciiValue!)] += 1
        }
        let n = value.count
        if cnt[0] == 0 {
            return n % cnt[1] == 0 && String(repeating: String(value.prefix(n / cnt[1])), count: cnt[1]) == value
        }
        if cnt[1] == 0 {
            return n % cnt[0] == 0 && String(repeating: String(value.prefix(n / cnt[0])), count: cnt[0]) == value
        }
        for la in 0...n {
            if la * cnt[0] > n {
                break
            }
            if (n - la * cnt[0]) % cnt[1] == 0 {
                let lb = (n - la * cnt[0]) / cnt[1]
                if check(la, lb) {
                    return true
                }
            }
        }
        return false
    }

    private func check(_ la: Int, _ lb: Int) -> Bool {
        var a: String? = nil
        var b: String? = nil
        var index = value.startIndex

        for c in pattern {
            if c == "a" {
                let end = value.index(index, offsetBy: la)
                if let knownA = a {
                    if knownA != value[index..<end] {
                        return false
                    }
                } else {
                    a = String(value[index..<end])
                }
                index = end
            } else {
                let end = value.index(index, offsetBy: lb)
                if let knownB = b {
                    if knownB != value[index..<end] {
                        return false
                    }
                } else {
                    b = String(value[index..<end])
                }
                index = end
            }
        }
        return a != b
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
