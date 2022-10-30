# [784. Letter Case Permutation](https://leetcode.com/problems/letter-case-permutation)

[中文文档](/solution/0700-0799/0784.Letter%20Case%20Permutation/README.md)

## Description

<p>Given a string <code>s</code>, you&nbsp;can transform every letter individually to be lowercase or uppercase to create another string.</p>

<p>Return <em>a list of all possible strings we could create</em>. Return the output in <strong>any order</strong>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;a1b2&quot;
<strong>Output:</strong> [&quot;a1b2&quot;,&quot;a1B2&quot;,&quot;A1b2&quot;,&quot;A1B2&quot;]
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;3z4&quot;
<strong>Output:</strong> [&quot;3z4&quot;,&quot;3Z4&quot;]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 12</code></li>
	<li><code>s</code> consists of lowercase English letters, uppercase English letters, and digits.</li>
</ul>

## Solutions

DFS.

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def letterCasePermutation(self, s: str) -> List[str]:
        def dfs(i):
            if i >= len(s):
                ans.append(''.join(t))
                return
            dfs(i + 1)
            if t[i].isalpha():
                t[i] = chr(ord(t[i]) ^ 32)
                dfs(i + 1)

        t = list(s)
        ans = []
        dfs(0)
        return ans
```

```python
class Solution:
    def letterCasePermutation(self, s: str) -> List[str]:
        ans = []
        n = sum(c.isalpha() for c in s)
        for i in range(1 << n):
            j, t = 0, []
            for c in s:
                if c.isalpha():
                    c = c.lower() if (i >> j) & 1 else c.upper()
                    j += 1
                t.append(c)
            ans.append(''.join(t))
        return ans
```

### **Java**

```java
class Solution {
    private List<String> ans = new ArrayList<>();
    private char[] t;

    public List<String> letterCasePermutation(String s) {
        t = s.toCharArray();
        dfs(0);
        return ans;
    }

    private void dfs(int i) {
        if (i >= t.length) {
            ans.add(String.valueOf(t));
            return;
        }
        dfs(i + 1);
        if (t[i] >= 'A') {
            t[i] ^= 32;
            dfs(i + 1);
        }
    }
}
```

```java
class Solution {
    public List<String> letterCasePermutation(String s) {
        int n = 0;
        for (int i = 0; i < s.length(); ++i) {
            if (s.charAt(i) >= 'A') {
                ++n;
            }
        }
        List<String> ans = new ArrayList<>();
        for (int i = 0; i < 1 << n; ++i) {
            int j = 0;
            StringBuilder t = new StringBuilder();
            for (int k = 0; k < s.length(); ++k) {
                char x = s.charAt(k);
                if (x >= 'A') {
                    x = ((i >> j) & 1) == 1 ? Character.toLowerCase(x) : Character.toUpperCase(x);
                    ++j;
                }
                t.append(x);
            }
            ans.add(t.toString());
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<string> letterCasePermutation(string s) {
        vector<string> ans;
        function<void(int)> dfs = [&](int i) {
            if (i >= s.size()) {
                ans.emplace_back(s);
                return;
            }
            dfs(i + 1);
            if (s[i] >= 'A') {
                s[i] ^= 32;
                dfs(i + 1);
            }
        };
        dfs(0);
        return ans;
    }
};
```

```cpp
class Solution {
public:
    vector<string> letterCasePermutation(string s) {
        int n = 0;
        for (char c : s) if (isalpha(c)) ++n;
        vector<string> ans;
        for (int i = 0; i < 1 << n; ++i) {
            int j = 0;
            string t;
            for (char c : s) {
                if (isalpha(c)) {
                    c = (i >> j & 1) ? tolower(c) : toupper(c);
                    ++j;
                }
                t += c;
            }
            ans.emplace_back(t);
        }
        return ans;
    }
};
```

### **Go**

```go
func letterCasePermutation(s string) (ans []string) {
	t := []byte(s)
	var dfs func(int)
	dfs = func(i int) {
		if i >= len(t) {
			ans = append(ans, string(t))
			return
		}
		dfs(i + 1)
		if t[i] >= 'A' {
			t[i] ^= 32
			dfs(i + 1)
		}
	}

	dfs(0)
	return ans
}
```

```go
func letterCasePermutation(s string) (ans []string) {
	n := 0
	for _, c := range s {
		if c >= 'A' {
			n++
		}
	}
	for i := 0; i < 1<<n; i++ {
		j := 0
		t := []rune{}
		for _, c := range s {
			if c >= 'A' {
				if ((i >> j) & 1) == 1 {
					c = unicode.ToLower(c)
				} else {
					c = unicode.ToUpper(c)
				}
				j++
			}
			t = append(t, c)
		}
		ans = append(ans, string(t))
	}
	return ans
}
```

### **TypeScript**

```ts
function letterCasePermutation(s: string): string[] {
    const n = s.length;
    const cs = [...s];
    const res = [];
    const dfs = (i: number) => {
        if (i === n) {
            res.push(cs.join(''));
            return;
        }
        dfs(i + 1);
        if (cs[i] >= 'A') {
            cs[i] = String.fromCharCode(cs[i].charCodeAt(0) ^ 32);
            dfs(i + 1);
        }
    };
    dfs(0);
    return res;
}
```

### **Rust**

```rust
impl Solution {
    fn dfs(i: usize, cs: &mut Vec<char>, res: &mut Vec<String>) {
        if i == cs.len() {
            res.push(cs.iter().collect());
            return;
        }
        Self::dfs(i + 1, cs, res);
        if cs[i] >= 'A' {
            cs[i] = char::from((cs[i] as u8) ^ 32);
            Self::dfs(i + 1, cs, res);
        }
    }

    pub fn letter_case_permutation(s: String) -> Vec<String> {
        let mut res = Vec::new();
        let mut cs = s.chars().collect::<Vec<char>>();
        Self::dfs(0, &mut cs, &mut res);
        res
    }
}
```

### **...**

```

```

<!-- tabs:end -->
