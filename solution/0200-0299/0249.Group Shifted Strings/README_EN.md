# [249. Group Shifted Strings](https://leetcode.com/problems/group-shifted-strings)

[中文文档](/solution/0200-0299/0249.Group%20Shifted%20Strings/README.md)

## Description

<p>We can shift a string by shifting each of its letters to its successive letter.</p>

<ul>
	<li>For example, <code>&quot;abc&quot;</code> can be shifted to be <code>&quot;bcd&quot;</code>.</li>
</ul>

<p>We can keep shifting the string to form a sequence.</p>

<ul>
	<li>For example, we can keep shifting <code>&quot;abc&quot;</code> to form the sequence: <code>&quot;abc&quot; -&gt; &quot;bcd&quot; -&gt; ... -&gt; &quot;xyz&quot;</code>.</li>
</ul>

<p>Given an array of strings <code>strings</code>, group all <code>strings[i]</code> that belong to the same shifting sequence. You may return the answer in <strong>any order</strong>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<pre><strong>Input:</strong> strings = ["abc","bcd","acef","xyz","az","ba","a","z"]
<strong>Output:</strong> [["acef"],["a","z"],["abc","bcd","xyz"],["az","ba"]]
</pre><p><strong>Example 2:</strong></p>
<pre><strong>Input:</strong> strings = ["a"]
<strong>Output:</strong> [["a"]]
</pre>
<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= strings.length &lt;= 200</code></li>
	<li><code>1 &lt;= strings[i].length &lt;= 50</code></li>
	<li><code>strings[i]</code> consists of lowercase English letters.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def groupStrings(self, strings: List[str]) -> List[List[str]]:
        mp = defaultdict(list)
        for s in strings:
            t = []
            diff = ord(s[0]) - ord('a')
            for c in s:
                d = ord(c) - diff
                if d < ord('a'):
                    d += 26
                t.append(chr(d))
            k = ''.join(t)
            mp[k].append(s)
        return list(mp.values())
```

### **Java**

```java
class Solution {
    public List<List<String>> groupStrings(String[] strings) {
        Map<String, List<String>> mp = new HashMap<>();
        for (String s : strings) {
            int diff = s.charAt(0) - 'a';
            char[] t = s.toCharArray();
            for (int i = 0; i < t.length; ++i) {
                char d = (char) (t[i] - diff);
                if (d < 'a') {
                    d += 26;
                }
                t[i] = d;
            }
            String key = new String(t);
            mp.computeIfAbsent(key, k -> new ArrayList<>()).add(s);
        }
        return new ArrayList<>(mp.values());
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<vector<string>> groupStrings(vector<string>& strings) {
        unordered_map<string, vector<string>> mp;
        for (auto& s : strings) {
            int diff = s[0] - 'a';
            string t = s;
            for (int i = 0; i < t.size(); ++i) {
                char d = t[i] - diff;
                if (d < 'a') d += 26;
                t[i] = d;
            }
            cout << t << endl;
            mp[t].push_back(s);
        }
        vector<vector<string>> ans;
        for (auto& e : mp)
            ans.push_back(e.second);
        return ans;
    }
};
```

### **Go**

```go
func groupStrings(strings []string) [][]string {
	mp := make(map[string][]string)
	for _, s := range strings {
		k := ""
		for i := range s {
			k += string((s[i]-s[0]+26)%26 + 'a')
		}
		mp[k] = append(mp[k], s)
	}
	var ans [][]string
	for _, v := range mp {
		ans = append(ans, v)
	}
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
