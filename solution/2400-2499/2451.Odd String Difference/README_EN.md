# [2451. Odd String Difference](https://leetcode.com/problems/odd-string-difference)

[中文文档](/solution/2400-2499/2451.Odd%20String%20Difference/README.md)

## Description

<p>You are given an array of equal-length strings <code>words</code>. Assume that the length of each string is <code>n</code>.</p>

<p>Each string <code>words[i]</code> can be converted into a <strong>difference integer array</strong> <code>difference[i]</code> of length <code>n - 1</code> where <code>difference[i][j] = words[i][j+1] - words[i][j]</code> where <code>0 &lt;= j &lt;= n - 2</code>. Note that the difference between two letters is the difference between their <strong>positions</strong> in the alphabet i.e.&nbsp;the position of <code>&#39;a&#39;</code> is <code>0</code>, <code>&#39;b&#39;</code> is <code>1</code>, and <code>&#39;z&#39;</code> is <code>25</code>.</p>

<ul>
	<li>For example, for the string <code>&quot;acb&quot;</code>, the difference integer array is <code>[2 - 0, 1 - 2] = [2, -1]</code>.</li>
</ul>

<p>All the strings in words have the same difference integer array, <strong>except one</strong>. You should find that string.</p>

<p>Return<em> the string in </em><code>words</code><em> that has different <strong>difference integer array</strong>.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> words = [&quot;adc&quot;,&quot;wzy&quot;,&quot;abc&quot;]
<strong>Output:</strong> &quot;abc&quot;
<strong>Explanation:</strong> 
- The difference integer array of &quot;adc&quot; is [3 - 0, 2 - 3] = [3, -1].
- The difference integer array of &quot;wzy&quot; is [25 - 22, 24 - 25]= [3, -1].
- The difference integer array of &quot;abc&quot; is [1 - 0, 2 - 1] = [1, 1]. 
The odd array out is [1, 1], so we return the corresponding string, &quot;abc&quot;.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> words = [&quot;aaa&quot;,&quot;bob&quot;,&quot;ccc&quot;,&quot;ddd&quot;]
<strong>Output:</strong> &quot;bob&quot;
<strong>Explanation:</strong> All the integer arrays are [0, 0] except for &quot;bob&quot;, which corresponds to [13, -13].
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>3 &lt;= words.length &lt;= 100</code></li>
	<li><code>n == words[i].length</code></li>
	<li><code>2 &lt;= n &lt;= 20</code></li>
	<li><code>words[i]</code> consists of lowercase English letters.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def oddString(self, words: List[str]) -> str:
        cnt = defaultdict(list)
        for w in words:
            d = [str(ord(b) - ord(a)) for a, b in pairwise(w)]
            cnt[','.join(d)].append(w)
        return next(v[0] for v in cnt.values() if len(v) == 1)
```

### **Java**

```java
class Solution {
    public String oddString(String[] words) {
        Map<String, List<String>> cnt = new HashMap<>();
        for (var w : words) {
            List<String> d = new ArrayList<>();
            for (int i = 0; i < w.length() - 1; ++i) {
                d.add(String.valueOf(w.charAt(i + 1) - w.charAt(i)));
            }
            cnt.computeIfAbsent(String.join(",", d), k -> new ArrayList<>()).add(w);
        }
        for (var v : cnt.values()) {
            if (v.size() == 1) {
                return v.get(0);
            }
        }
        return "";
    }
}
```

### **C++**

```cpp
class Solution {
public:
    string oddString(vector<string>& words) {
        unordered_map<string, vector<string>> cnt;
        for (auto& w : words) {
            string d;
            for (int i = 0; i < w.size() - 1; ++i) {
                d += (char) (w[i + 1] - w[i]);
                d += ',';
            }
            cnt[d].emplace_back(w);
        }
        for (auto& [_, v] : cnt) {
            if (v.size() == 1) {
                return v[0];
            }
        }
        return "";
    }
};
```

### **Go**

```go
func oddString(words []string) string {
	cnt := map[string][]string{}
	for _, w := range words {
		d := make([]byte, len(w)-1)
		for i := 0; i < len(w)-1; i++ {
			d[i] = w[i+1] - w[i]
		}
		t := string(d)
		cnt[t] = append(cnt[t], w)
	}
	for _, v := range cnt {
		if len(v) == 1 {
			return v[0]
		}
	}
	return ""
}
```

### **TypeScript**

```ts
function oddString(words: string[]): string {
    const n = words[0].length;
    const map = new Map<string, [boolean, number]>();
    words.forEach((word, i) => {
        const diff: number[] = [];
        for (let j = 1; j < n; j++) {
            diff.push(word[j].charCodeAt(0) - word[j - 1].charCodeAt(0));
        }
        const k = diff.join();
        map.set(k, [!map.has(k), i]);
    });
    for (const [isOnly, i] of map.values()) {
        if (isOnly) {
            return words[i];
        }
    }
    return '';
}
```

### **Rust**

```rust
use std::collections::HashMap;
impl Solution {
    pub fn odd_string(words: Vec<String>) -> String {
        let n = words[0].len();
        let mut map: HashMap<String, (bool, usize)> = HashMap::new();
        for (i, word) in words.iter().enumerate() {
            let mut k = String::new();
            for j in 1..n {
                k.push_str(&(word.as_bytes()[j] - word.as_bytes()[j - 1]).to_string());
                k.push(',');
            }
            let new_is_only = !map.contains_key(&k);
            map.insert(k, (new_is_only, i));
        }
        for (is_only, i) in map.values() {
            if *is_only {
                return words[*i].clone();
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
