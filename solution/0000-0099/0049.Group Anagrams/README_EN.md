# [49. Group Anagrams](https://leetcode.com/problems/group-anagrams)

[中文文档](/solution/0000-0099/0049.Group%20Anagrams/README.md)

## Description

<p>Given an array of strings <code>strs</code>, group <strong>the anagrams</strong> together. You can return the answer in <strong>any order</strong>.</p>

<p>An <strong>Anagram</strong> is a word or phrase formed by rearranging the letters of a different word or phrase, typically using all the original letters exactly once.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<pre><strong>Input:</strong> strs = ["eat","tea","tan","ate","nat","bat"]
<strong>Output:</strong> [["bat"],["nat","tan"],["ate","eat","tea"]]
</pre><p><strong>Example 2:</strong></p>
<pre><strong>Input:</strong> strs = [""]
<strong>Output:</strong> [[""]]
</pre><p><strong>Example 3:</strong></p>
<pre><strong>Input:</strong> strs = ["a"]
<strong>Output:</strong> [["a"]]
</pre>
<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= strs.length &lt;= 10<sup>4</sup></code></li>
	<li><code>0 &lt;= strs[i].length &lt;= 100</code></li>
	<li><code>strs[i]</code> consists of lowercase English letters.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def groupAnagrams(self, strs: List[str]) -> List[List[str]]:
        chars = defaultdict(list)
        for s in strs:
            k = ''.join(sorted(list(s)))
            chars[k].append(s)
        return list(chars.values())
```

### **Java**

```java
class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> chars = new HashMap<>();
        for (String s : strs) {
            char[] t = s.toCharArray();
            Arrays.sort(t);
            String k = new String(t);
            chars.computeIfAbsent(k, key -> new ArrayList<>()).add(s);
        }
        return new ArrayList<>(chars.values());
    }
}
```

### **TypeScript**

```ts
function groupAnagrams(strs: string[]): string[][] {
    let map = new Map();
    for (let str of strs) {
        let arr = str.split('');
        arr.sort();
        let key = arr.join('');
        let value = map.get(key) ? map.get(key) : [];
        value.push(str);
        map.set(key, value);
    }
    return Array.from(map.values());
}
```

```ts
function groupAnagrams(strs: string[]): string[][] {
    const map = new Map<string, string[]>();
    for (const str of strs) {
        const k = str.split('').sort().join('');
        map.set(k, (map.get(k) ?? []).concat([str]));
    }
    return [...map.values()];
}
```

### **C++**

```cpp
class Solution {
public:
    vector<vector<string>> groupAnagrams(vector<string>& strs) {
        unordered_map<string, vector<string>> chars;
        for (auto s : strs) {
            string k = s;
            sort(k.begin(), k.end());
            chars[k].emplace_back(s);
        }
        vector<vector<string>> res;
        for (auto it = chars.begin(); it != chars.end(); ++it) {
            res.emplace_back(it->second);
        }
        return res;
    }
};
```

### **Go**

```go
func groupAnagrams(strs []string) [][]string {
	chars := map[string][]string{}
	for _, s := range strs {
		key := []byte(s)
		sort.Slice(key, func(i, j int) bool {
			return key[i] < key[j]
		})
		chars[string(key)] = append(chars[string(key)], s)
	}
	var res [][]string
	for _, v := range chars {
		res = append(res, v)
	}
	return res
}
```

### **Rust**

```rust
use std::collections::HashMap;

impl Solution {
    pub fn group_anagrams(strs: Vec<String>) -> Vec<Vec<String>> {
        let mut map = HashMap::new();
        for s in strs {
            let key = {
                let mut arr = s.chars().collect::<Vec<char>>();
                arr.sort();
                arr.iter().collect::<String>()
            };
            let val = map.entry(key).or_insert(vec![]);
            val.push(s);
        }
        map.into_iter().map(|(_, v)| v).collect()
    }
}
```

### **...**

```

```

<!-- tabs:end -->
