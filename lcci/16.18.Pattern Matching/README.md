# [面试题 16.18. 模式匹配](https://leetcode.cn/problems/pattern-matching-lcci)

[English Version](/lcci/16.18.Pattern%20Matching/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>你有两个字符串，即<code>pattern</code>和<code>value</code>。 <code>pattern</code>字符串由字母<code>"a"</code>和<code>"b"</code>组成，用于描述字符串中的模式。例如，字符串<code>"catcatgocatgo"</code>匹配模式<code>"aabab"</code>（其中<code>"cat"</code>是<code>"a"</code>，<code>"go"</code>是<code>"b"</code>），该字符串也匹配像<code>"a"</code>、<code>"ab"</code>和<code>"b"</code>这样的模式。但需注意<code>"a"</code>和<code>"b"</code>不能同时表示相同的字符串。编写一个方法判断<code>value</code>字符串是否匹配<code>pattern</code>字符串。</p>
<p><strong>示例 1：</strong></p>
<pre><strong>输入：</strong> pattern = "abba", value = "dogcatcatdog"
<strong>输出：</strong> true
</pre>
<p><strong>示例 2：</strong></p>
<pre><strong>输入：</strong> pattern = "abba", value = "dogcatcatfish"
<strong>输出：</strong> false
</pre>
<p><strong>示例 3：</strong></p>
<pre><strong>输入：</strong> pattern = "aaaa", value = "dogcatcatdog"
<strong>输出：</strong> false
</pre>
<p><strong>示例 4：</strong></p>
<pre><strong>输入：</strong> pattern = "abba", value = "dogdogdogdog"
<strong>输出：</strong> true
<strong>解释：</strong> "a"="dogdog",b=""，反之也符合规则
</pre>
<p><strong>提示：</strong></p>
<ul>
<li><code>0 <= len(pattern) <= 1000</code></li>
<li><code>0 <= len(value) <= 1000</code></li>
<li>你可以假设<code>pattern</code>只包含字母<code>"a"</code>和<code>"b"</code>，<code>value</code>仅包含小写字母。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：枚举**

我们先统计出模式串 $pattern$ 中 `'a'` 和 `'b'` 的个数，分别为 $cnt[0]$ 和 $cnt[1]$。记字符串 $value$ 的长度为 $n$。

如果 $cnt[0]=0$，说明模式串中只包含字符 `'b'`，那么我们需要判断 $n$ 是否是 $cnt[1]$ 的倍数，以及 $value$ 是否可以分割成 $cnt[1]$ 个长度为 $n/cnt[1]$ 的子串，且这些子串都相同。如果不满足，直接返回 $false$ 即可。

如果 $cnt[1]=0$，说明模式串中只包含字符 `'a'`，那么我们需要判断 $n$ 是否是 $cnt[0]$ 的倍数，以及 $value$ 是否可以分割成 $cnt[0]$ 个长度为 $n/cnt[0]$ 的子串，且这些子串都相同。如果不满足，直接返回 $false$ 即可。

接下来，我们记字符 `'a'` 所匹配的字符串的长度 $la$，字符 `'b'` 所匹配的字符串的长度 $lb$。那么有 $la \times cnt[0] + lb \times cnt[1] = n$。如果我们枚举 $la$，就可以知道 $lb$ 的值了。因此我们可以枚举 $la$，只需要判断是否存在一个整数 $lb$，满足上述等式即可。

时间复杂度 $O(n^2)$，空间复杂度 $O(n)$。其中 $n$ 为字符串 $value$ 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

### **C++**

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

### **Go**

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

### **TypeScript**

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

### **...**

```

```

<!-- tabs:end -->
