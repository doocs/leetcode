# [10.02. Group Anagrams](https://leetcode-cn.com/problems/group-anagrams-lcci)

[中文文档](/lcci/10.02.Group%20Anagrams/README.md)

## Description

<p>Write a method to sort an array of strings so that all the anagrams are in the same group.</p>

<p><b>Note:&nbsp;</b>This problem is slightly different from the original one the book.</p>

<p><strong>Example:</strong></p>

<pre>

<strong>Input:</strong> <code>[&quot;eat&quot;, &quot;tea&quot;, &quot;tan&quot;, &quot;ate&quot;, &quot;nat&quot;, &quot;bat&quot;]</code>,

<strong>Output:</strong>

[

  [&quot;ate&quot;,&quot;eat&quot;,&quot;tea&quot;],

  [&quot;nat&quot;,&quot;tan&quot;],

  [&quot;bat&quot;]

]</pre>

<p><strong>Notes: </strong></p>

<ul>
	<li>All inputs will be in lowercase.</li>
	<li>The order of your output does not&nbsp;matter.</li>
</ul>

## Solutions

| key     | value                   |
| ------- | ----------------------- |
| `"aet"` | `["eat", "tea", "ate"]` |
| `"ant"` | `["tan", "nat"] `       |
| `"abt"` | `["bat"] `              |

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

### **C++**

```cpp
class Solution {
public:
    vector<vector<string>> groupAnagrams(vector<string> &strs) {
        unordered_map<string, vector<string>> chars;
        for (auto s : strs)
        {
            string k = s;
            sort(k.begin(), k.end());
            chars[k].emplace_back(s);
        }
        vector<vector<string>> res;
        for (auto it = chars.begin(); it != chars.end(); ++it)
        {
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

### **...**

```

```

<!-- tabs:end -->
