# [524. Longest Word in Dictionary through Deleting](https://leetcode.com/problems/longest-word-in-dictionary-through-deleting)

[中文文档](/solution/0500-0599/0524.Longest%20Word%20in%20Dictionary%20through%20Deleting/README.md)

## Description

<p>Given a string <code>s</code> and a string array <code>dictionary</code>, return <em>the longest string in the dictionary that can be formed by deleting some of the given string characters</em>. If there is more than one possible result, return the longest word with the smallest lexicographical order. If there is no possible result, return the empty string.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;abpcplea&quot;, dictionary = [&quot;ale&quot;,&quot;apple&quot;,&quot;monkey&quot;,&quot;plea&quot;]
<strong>Output:</strong> &quot;apple&quot;
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;abpcplea&quot;, dictionary = [&quot;a&quot;,&quot;b&quot;,&quot;c&quot;]
<strong>Output:</strong> &quot;a&quot;
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 1000</code></li>
	<li><code>1 &lt;= dictionary.length &lt;= 1000</code></li>
	<li><code>1 &lt;= dictionary[i].length &lt;= 1000</code></li>
	<li><code>s</code> and <code>dictionary[i]</code> consist of lowercase English letters.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def findLongestWord(self, s: str, dictionary: List[str]) -> str:
        def check(a, b):
            m, n = len(a), len(b)
            i = j = 0
            while i < m and j < n:
                if a[i] == b[j]:
                    j += 1
                i += 1
            return j == n

        ans = ''
        for a in dictionary:
            if check(s, a) and (len(ans) < len(a) or (len(ans) == len(a) and ans > a)):
                ans = a
        return ans
```

### **Java**

```java
class Solution {
    public String findLongestWord(String s, List<String> dictionary) {
        String ans = "";
        for (String a : dictionary) {
            if (check(s, a)
                && (ans.length() < a.length()
                    || (ans.length() == a.length() && a.compareTo(ans) < 0))) {
                ans = a;
            }
        }
        return ans;
    }

    private boolean check(String a, String b) {
        int m = a.length(), n = b.length();
        int i = 0, j = 0;
        while (i < m && j < n) {
            if (a.charAt(i) == b.charAt(j)) {
                ++j;
            }
            ++i;
        }
        return j == n;
    }
}
```

### **TypeScript**

```ts
function findLongestWord(s: string, dictionary: string[]): string {
    dictionary.sort((a, b) => {
        if (a.length === b.length) {
            return b < a ? 1 : -1;
        }
        return b.length - a.length;
    });
    const n = s.length;
    for (const target of dictionary) {
        const m = target.length;
        if (m > n) {
            continue;
        }
        let i = 0;
        let j = 0;
        while (i < n && j < m) {
            if (s[i] === target[j]) {
                j++;
            }
            i++;
        }
        if (j === m) {
            return target;
        }
    }
    return '';
}
```

### **Rust**

```rust
impl Solution {
    pub fn find_longest_word(s: String, mut dictionary: Vec<String>) -> String {
        dictionary.sort_unstable_by(|a, b| (b.len(), a).cmp(&(a.len(), b)));
        for target in dictionary {
            let target: Vec<char> = target.chars().collect();
            let n = target.len();
            let mut i = 0;
            for c in s.chars() {
                if i == n {
                    break;
                }
                if c == target[i] {
                    i += 1;
                }
            }
            if i == n {
                return target.iter().collect();
            }
        }
        String::new()
    }
}
```

### **C++**

```cpp
class Solution {
public:
    string findLongestWord(string s, vector<string>& dictionary) {
        string ans = "";
        for (string& a : dictionary)
            if (check(s, a) && (ans.size() < a.size() || (ans.size() == a.size() && a < ans)))
                ans = a;
        return ans;
    }

    bool check(string& a, string& b) {
        int m = a.size(), n = b.size();
        int i = 0, j = 0;
        while (i < m && j < n) {
            if (a[i] == b[j]) ++j;
            ++i;
        }
        return j == n;
    }
};
```

### **Go**

```go
func findLongestWord(s string, dictionary []string) string {
	ans := ""
	check := func(a, b string) bool {
		m, n := len(a), len(b)
		i, j := 0, 0
		for i < m && j < n {
			if a[i] == b[j] {
				j++
			}
			i++
		}
		return j == n
	}
	for _, a := range dictionary {
		if check(s, a) && (len(ans) < len(a) || (len(ans) == len(a) && a < ans)) {
			ans = a
		}
	}
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
