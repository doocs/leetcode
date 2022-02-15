# [884. Uncommon Words from Two Sentences](https://leetcode.com/problems/uncommon-words-from-two-sentences)

[中文文档](/solution/0800-0899/0884.Uncommon%20Words%20from%20Two%20Sentences/README.md)

## Description

<p>We are given two sentences <code>A</code> and <code>B</code>.&nbsp; (A <em>sentence</em>&nbsp;is a string of space separated words.&nbsp; Each <em>word</em> consists only of lowercase letters.)</p>

<p>A word is <em>uncommon</em>&nbsp;if it appears exactly once in one of the sentences, and does not appear in the other sentence.</p>

<p>Return a list of all uncommon words.&nbsp;</p>

<p>You may return the list in any order.</p>

<p>&nbsp;</p>

<ol>

</ol>

<div>

<p><strong>Example 1:</strong></p>

<pre>

<strong>Input: </strong>A = <span id="example-input-1-1">&quot;this apple is sweet&quot;</span>, B = <span id="example-input-1-2">&quot;this apple is sour&quot;</span>

<strong>Output: </strong><span id="example-output-1">[&quot;sweet&quot;,&quot;sour&quot;]</span>

</pre>

<div>

<p><strong>Example 2:</strong></p>

<pre>

<strong>Input: </strong>A = <span id="example-input-2-1">&quot;apple apple&quot;</span>, B = <span id="example-input-2-2">&quot;banana&quot;</span>

<strong>Output: </strong><span id="example-output-2">[&quot;banana&quot;]</span>

</pre>

<p>&nbsp;</p>

<p><strong>Note:</strong></p>

<ul>
    <li><code>1 &lt;= s1.length, s2.length &lt;= 200</code></li>
    <li><code>s1</code> and <code>s2</code> consist of lowercase English letters and spaces.</li>
    <li><code>s1</code> and <code>s2</code> do not have leading or trailing spaces.</li>
    <li>All the words in <code>s1</code> and <code>s2</code> are separated by a single space.</li>
</ul>

</div>

</div>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def uncommonFromSentences(self, s1: str, s2: str) -> List[str]:
        c = Counter(s1.split()) + Counter(s2.split())
        return [w for w, n in c.items() if n == 1]
```

### **Java**

```java
class Solution {

    public String[] uncommonFromSentences(String s1, String s2) {
        Map<String, Integer> counter = new HashMap<>();
        add(s1, counter);
        add(s2, counter);
        List<String> ans = new ArrayList<>();
        for (Map.Entry<String, Integer> e : counter.entrySet()) {
            if (e.getValue() == 1) {
                ans.add(e.getKey());
            }
        }
        return ans.toArray(new String[0]);
    }

    private void add(String s, Map<String, Integer> counter) {
        for (String w : s.split(" ")) {
            counter.put(w, counter.getOrDefault(w, 0) + 1);
        }
    }
}

```

### **TypeScript**

```ts
function uncommonFromSentences(s1: string, s2: string): string[] {
    let hashMap: Map<string, number> = new Map();
    for (let str of [...s1.split(' '), ...s2.split(' ')]) {
        hashMap.set(str, (hashMap.get(str) || 0) + 1);
    }
    let ans: Array<string> = [];
    for (let [key, count] of hashMap.entries()) {
        if (count == 1) {
            ans.push(key);
        }
    }
    return ans;
}
```

### **C++**

```cpp
class Solution {
public:
    vector<string> uncommonFromSentences(string s1, string s2) {
        unordered_map<string, int> counter;

        auto add = [&](const string& s) {
            stringstream ss(s);
            string word;
            while (ss >> word) ++counter[move(word)];
        };

        add(s1);
        add(s2);
        vector<string> ans;
        for (auto& [word, n] : counter)
            if (n == 1)
                ans.push_back(word);
        return ans;
    }
};
```

### **Go**

```go
func uncommonFromSentences(s1 string, s2 string) []string {
	counter := make(map[string]int)
	add := func(s string) {
		for _, w := range strings.Split(s, " ") {
			counter[w]++
		}
	}
	add(s1)
	add(s2)
	var ans []string
	for word, n := range counter {
		if n == 1 {
			ans = append(ans, word)
		}
	}
	return ans
}
```

### **Rust**

```rust
use std::collections::HashMap;

impl Solution {
    pub fn uncommon_from_sentences(s1: String, s2: String) -> Vec<String> {
        let mut map = HashMap::new();
        for s in s1.split(' ') {
            map.insert(s, !map.contains_key(s));
        }
        for s in s2.split(' ') {
            map.insert(s, !map.contains_key(s));
        }
        let mut res = Vec::new();
        for (k, v) in map {
            if v {
                res.push(String::from(k))
            }
        }
        res
    }
}
```

### **...**

```

```

<!-- tabs:end -->
