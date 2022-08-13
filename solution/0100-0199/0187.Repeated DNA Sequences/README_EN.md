# [187. Repeated DNA Sequences](https://leetcode.com/problems/repeated-dna-sequences)

[中文文档](/solution/0100-0199/0187.Repeated%20DNA%20Sequences/README.md)

## Description

<p>The <strong>DNA sequence</strong> is composed of a series of nucleotides abbreviated as <code>&#39;A&#39;</code>, <code>&#39;C&#39;</code>, <code>&#39;G&#39;</code>, and <code>&#39;T&#39;</code>.</p>

<ul>
	<li>For example, <code>&quot;ACGAATTCCG&quot;</code> is a <strong>DNA sequence</strong>.</li>
</ul>

<p>When studying <strong>DNA</strong>, it is useful to identify repeated sequences within the DNA.</p>

<p>Given a string <code>s</code> that represents a <strong>DNA sequence</strong>, return all the <strong><code>10</code>-letter-long</strong> sequences (substrings) that occur more than once in a DNA molecule. You may return the answer in <strong>any order</strong>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<pre><strong>Input:</strong> s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"
<strong>Output:</strong> ["AAAAACCCCC","CCCCCAAAAA"]
</pre><p><strong>Example 2:</strong></p>
<pre><strong>Input:</strong> s = "AAAAAAAAAAAAA"
<strong>Output:</strong> ["AAAAAAAAAA"]
</pre>
<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s[i]</code> is either <code>&#39;A&#39;</code>, <code>&#39;C&#39;</code>, <code>&#39;G&#39;</code>, or <code>&#39;T&#39;</code>.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def findRepeatedDnaSequences(self, s: str) -> List[str]:
        n = len(s) - 10
        cnt = Counter()
        ans = []
        for i in range(n + 1):
            sub = s[i : i + 10]
            cnt[sub] += 1
            if cnt[sub] == 2:
                ans.append(sub)
        return ans
```

### **Java**

```java
class Solution {
    public List<String> findRepeatedDnaSequences(String s) {
        int n = s.length() - 10;
        Map<String, Integer> cnt = new HashMap<>();
        List<String> ans = new ArrayList<>();
        for (int i = 0; i <= n; ++i) {
            String sub = s.substring(i, i + 10);
            cnt.put(sub, cnt.getOrDefault(sub, 0) + 1);
            if (cnt.get(sub) == 2) {
                ans.add(sub);
            }
        }
        return ans;
    }
}
```

### **JavaScript**

```js
/**
 * @param {string} s
 * @return {string[]}
 */
var findRepeatedDnaSequences = function (s) {
    const n = s.length - 10;
    let cnt = new Map();
    let ans = [];
    for (let i = 0; i <= n; ++i) {
        let sub = s.slice(i, i + 10);
        cnt[sub] = (cnt[sub] || 0) + 1;
        if (cnt[sub] == 2) {
            ans.push(sub);
        }
    }
    return ans;
};
```

### **Go**

```go
func findRepeatedDnaSequences(s string) []string {
	cnt := make(map[string]int)
	n := len(s) - 10
	ans := make([]string, 0)
	for i := 0; i <= n; i++ {
		sub := s[i : i+10]
		cnt[sub]++
		if cnt[sub] == 2 {
			ans = append(ans, sub)
		}
	}
	return ans
}
```

### **C++**

```cpp
class Solution {
public:
    vector<string> findRepeatedDnaSequences(string s) {
        map<string, int> cnt;
        int n = s.size() - 10;
        vector<string> ans;
        for (int i = 0; i <= n; ++i) {
            string sub = s.substr(i, 10);
            if (++cnt[sub] == 2) {
                ans.push_back(sub);
            }
        }
        return ans;
    }
};
```

### **C#**

```cs
using System.Collections.Generic;

public class Solution {
    public IList<string> FindRepeatedDnaSequences(string s) {
        var once = new HashSet<int>();
        var moreThanOnce = new HashSet<int>();
        int bits = 0;
        for (var i = 0; i < s.Length; ++i)
        {
            bits <<= 2;
            switch (s[i])
            {
                case 'A':
                    break;
                case 'C':
                    bits |= 1;
                    break;
                case 'G':
                    bits |= 2;
                    break;
                case 'T':
                    bits |= 3;
                    break;
            }
            if (i >= 10)
            {
                bits &= 0xFFFFF;
            }
            if (i >= 9 && !once.Add(bits))
            {
                moreThanOnce.Add(bits);
            }
        }

        var results = new List<string>();
        foreach (var item in moreThanOnce)
        {
            var itemCopy = item;
            var charArray = new char[10];
            for (var i = 9; i >= 0; --i)
            {
                switch (itemCopy & 3)
                {
                    case 0:
                        charArray[i] = 'A';
                        break;
                    case 1:
                        charArray[i] = 'C';
                        break;
                    case 2:
                        charArray[i] = 'G';
                        break;
                    case 3:
                        charArray[i] = 'T';
                        break;
                }
                itemCopy >>= 2;
            }
            results.Add(new string(charArray));
        }
        return results;
    }
}
```

### **TypeScript**

```ts
function findRepeatedDnaSequences(s: string): string[] {
    const n = s.length;
    const map = new Map<string, boolean>();
    const res = [];
    for (let i = 0; i <= n - 10; i++) {
        const key = s.slice(i, i + 10);
        if (map.has(key) && map.get(key)) {
            res.push(key);
        }
        map.set(key, !map.has(key));
    }
    return res;
}
```

### **Rust**

```rust
use std::collections::HashMap;

impl Solution {
    pub fn find_repeated_dna_sequences(s: String) -> Vec<String> {
        let n = s.len();
        let mut res = vec![];
        if n < 10 {
            return res;
        }
        let mut map = HashMap::new();
        for i in 0..=n - 10 {
            let key = &s[i..i + 10];
            if map.contains_key(&key) && *map.get(&key).unwrap() {
                res.push(key.to_string());
            }
            map.insert(key, !map.contains_key(&key));
        }
        res
    }
}
```

### **...**

```

```

<!-- tabs:end -->
