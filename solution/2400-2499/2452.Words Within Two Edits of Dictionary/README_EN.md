# [2452. Words Within Two Edits of Dictionary](https://leetcode.com/problems/words-within-two-edits-of-dictionary)

[中文文档](/solution/2400-2499/2452.Words%20Within%20Two%20Edits%20of%20Dictionary/README.md)

## Description

<p>You are given two string arrays, <code>queries</code> and <code>dictionary</code>. All words in each array comprise of lowercase English letters and have the same length.</p>

<p>In one <strong>edit</strong> you can take a word from <code>queries</code>, and change any letter in it to any other letter. Find all words from <code>queries</code> that, after a <strong>maximum</strong> of two edits, equal some word from <code>dictionary</code>.</p>

<p>Return<em> a list of all words from </em><code>queries</code><em>, </em><em>that match with some word from </em><code>dictionary</code><em> after a maximum of <strong>two edits</strong></em>. Return the words in the <strong>same order</strong> they appear in <code>queries</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> queries = [&quot;word&quot;,&quot;note&quot;,&quot;ants&quot;,&quot;wood&quot;], dictionary = [&quot;wood&quot;,&quot;joke&quot;,&quot;moat&quot;]
<strong>Output:</strong> [&quot;word&quot;,&quot;note&quot;,&quot;wood&quot;]
<strong>Explanation:</strong>
- Changing the &#39;r&#39; in &quot;word&quot; to &#39;o&#39; allows it to equal the dictionary word &quot;wood&quot;.
- Changing the &#39;n&#39; to &#39;j&#39; and the &#39;t&#39; to &#39;k&#39; in &quot;note&quot; changes it to &quot;joke&quot;.
- It would take more than 2 edits for &quot;ants&quot; to equal a dictionary word.
- &quot;wood&quot; can remain unchanged (0 edits) and match the corresponding dictionary word.
Thus, we return [&quot;word&quot;,&quot;note&quot;,&quot;wood&quot;].
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> queries = [&quot;yes&quot;], dictionary = [&quot;not&quot;]
<strong>Output:</strong> []
<strong>Explanation:</strong>
Applying any two edits to &quot;yes&quot; cannot make it equal to &quot;not&quot;. Thus, we return an empty array.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= queries.length, dictionary.length &lt;= 100</code></li>
	<li><code>n == queries[i].length == dictionary[j].length</code></li>
	<li><code>1 &lt;= n &lt;= 100</code></li>
	<li>All <code>queries[i]</code> and <code>dictionary[j]</code> are composed of lowercase English letters.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def twoEditWords(self, queries: List[str], dictionary: List[str]) -> List[str]:
        ans = []
        for s in queries:
            for t in dictionary:
                if sum(a != b for a, b in zip(s, t)) < 3:
                    ans.append(s)
                    break
        return ans
```

### **Java**

```java
class Solution {
    public List<String> twoEditWords(String[] queries, String[] dictionary) {
        List<String> ans = new ArrayList<>();
        int n = queries[0].length();
        for (var s : queries) {
            for (var t : dictionary) {
                int cnt = 0;
                for (int i = 0; i < n; ++i) {
                    if (s.charAt(i) != t.charAt(i)) {
                        ++cnt;
                    }
                }
                if (cnt < 3) {
                    ans.add(s);
                    break;
                }
            }
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<string> twoEditWords(vector<string>& queries, vector<string>& dictionary) {
        vector<string> ans;
        for (auto& s : queries) {
            for (auto& t : dictionary) {
                int cnt = 0;
                for (int i = 0; i < s.size(); ++i) cnt += s[i] != t[i];
                if (cnt < 3) {
                    ans.emplace_back(s);
                    break;
                }
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func twoEditWords(queries []string, dictionary []string) (ans []string) {
	for _, s := range queries {
		for _, t := range dictionary {
			cnt := 0
			for i := range s {
				if s[i] != t[i] {
					cnt++
				}
			}
			if cnt < 3 {
				ans = append(ans, s)
				break
			}
		}
	}
	return
}
```

### **TypeScript**

```ts
function twoEditWords(queries: string[], dictionary: string[]): string[] {
    const n = queries[0].length;
    return queries.filter(querie => {
        for (const s of dictionary) {
            let diff = 0;
            for (let i = 0; i < n; i++) {
                if (querie[i] !== s[i] && ++diff > 2) {
                    break;
                }
            }
            if (diff <= 2) {
                return true;
            }
        }
        return false;
    });
}
```

### **Rust**

```rust
impl Solution {
    pub fn two_edit_words(queries: Vec<String>, dictionary: Vec<String>) -> Vec<String> {
        let n = queries[0].len();
        queries
            .into_iter()
            .filter(|querie| {
                for s in dictionary.iter() {
                    let mut diff = 0;
                    for i in 0..n {
                        if querie.as_bytes()[i] != s.as_bytes()[i] {
                            diff += 1;
                        }
                    }
                    if diff <= 2 {
                        return true;
                    }
                }
                false
            })
            .collect()
    }
}
```

### **...**

```

```

<!-- tabs:end -->
