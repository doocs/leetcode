# [14. Longest Common Prefix](https://leetcode.com/problems/longest-common-prefix)

[中文文档](/solution/0000-0099/0014.Longest%20Common%20Prefix/README.md)

## Description

<p>Write a function to find the longest common prefix string amongst an array of strings.</p>

<p>If there is no common prefix, return an empty string <code>&quot;&quot;</code>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> strs = [&quot;flower&quot;,&quot;flow&quot;,&quot;flight&quot;]
<strong>Output:</strong> &quot;fl&quot;
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> strs = [&quot;dog&quot;,&quot;racecar&quot;,&quot;car&quot;]
<strong>Output:</strong> &quot;&quot;
<strong>Explanation:</strong> There is no common prefix among the input strings.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= strs.length &lt;= 200</code></li>
	<li><code>0 &lt;= strs[i].length &lt;= 200</code></li>
	<li><code>strs[i]</code> consists of only lowercase English letters.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def longestCommonPrefix(self, strs: List[str]) -> str:
        n = len(strs)
        if n == 0:
            return ''
        for i in range(len(strs[0])):
            for j in range(1, n):
                if len(strs[j]) <= i or strs[j][i] != strs[0][i]:
                    return strs[0][:i]
        return strs[0]
```

### **Java**

```java
class Solution {
    public String longestCommonPrefix(String[] strs) {
        int n;
        if ((n = strs.length) == 0) return "";
        for (int i = 0; i < strs[0].length(); ++i) {
            for (int j = 1; j < n; ++j) {
                if (strs[j].length() <= i || strs[j].charAt(i) != strs[0].charAt(i)) {
                    return strs[0].substring(0, i);
                }
            }
        }
        return strs[0];
    }
}
```

### **C++**

```cpp
class Solution {
public:
    string longestCommonPrefix(vector<string>& strs) {
        int n;
        if ((n = strs.size()) == 0) return "";
        for (int i = 0; i < strs[0].size(); ++i) {
            for (int j = 1; j < n; ++j) {
                if (strs[j].size() <= i || strs[j][i] != strs[0][i]) {
                    return strs[0].substr(0, i);
                }
            }
        }
        return strs[0];
    }
};
```

### **Go**

```go
func longestCommonPrefix(strs []string) string {
	if len(strs) == 0 {
		return ""
	}

	var b strings.Builder
	m, n := len(strs[0]), len(strs)

LOOP:
	for i := 0; i < m; i++ {
		for j := 1; j < n; j++ {
			if i >= len(strs[j]) || strs[0][i] != strs[j][i] {
				break LOOP
			}
		}
		b.WriteByte(strs[0][i])
	}

	return b.String()
}
```

### **C#**

```cs
using System.Text;
using System.Linq;

public class Solution {
    public string LongestCommonPrefix(string[] strs) {
        if (strs.Length == 0) return string.Empty;
        var sb = new StringBuilder();
        for (var i = 0; i < strs[0].Length; ++i)
        {
            var ch = strs[0][i];
            if (strs.All(str => str.Length > i && str[i] == ch))
            {
                sb.Append(ch);
            }
            else
            {
                break;
            }
        }
        return sb.ToString();
    }
}
```

### **Ruby**

```rb
# @param {String[]} strs
# @return {String}
def longest_common_prefix(strs)
  return '' if strs.nil? || strs.length.zero?

  return strs[0] if strs.length == 1

  idx = 0
  while idx < strs[0].length
    cur_char = strs[0][idx]

    str_idx = 1
    while str_idx < strs.length
      return idx > 0 ? strs[0][0..idx-1] : '' if strs[str_idx].length <= idx

      return '' if strs[str_idx][idx] != cur_char && idx.zero?
      return strs[0][0..idx - 1] if strs[str_idx][idx] != cur_char
      str_idx += 1
    end

    idx += 1
  end

  idx > 0 ? strs[0][0..idx] : ''
end
```

### **JavaScript**

```js
const longestCommonPrefix = function (strs) {
    if (strs.length === 0) return '';
    for (let j = 0; j < strs[0].length; j++) {
        for (let i = 0; i < strs.length; i++) {
            if (strs[0][j] !== strs[i][j]) {
                return strs[0].substring(0, j);
            }
        }
    }
    return strs[0];
};
```

### **TypeScript**

```ts
function longestCommonPrefix(strs: string[]): string {
    const len = strs.reduce((r, s) => Math.min(r, s.length), Infinity);
    for (let i = len; i > 0; i--) {
        const target = strs[0].slice(0, i);
        if (strs.every(s => s.slice(0, i) === target)) {
            return target;
        }
    }
    return '';
}
```

### **Rust**

```rust
impl Solution {
    pub fn longest_common_prefix(strs: Vec<String>) -> String {
        let mut len = strs.iter().map(|s| s.len()).min().unwrap();
        for i in (1..=len).rev() {
            let mut is_equal = true;
            let target = strs[0][0..i].to_string();
            if strs.iter().all(|s| target == s[0..i]) {
                return target;
            }
        }
        String::new()
    }
}
```

### **...**

```

```

<!-- tabs:end -->
