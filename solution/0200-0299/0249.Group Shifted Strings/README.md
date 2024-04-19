# [249. 移位字符串分组 🔒](https://leetcode.cn/problems/group-shifted-strings)

[English Version](/solution/0200-0299/0249.Group%20Shifted%20Strings/README_EN.md)

<!-- tags:数组,哈希表,字符串 -->

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个字符串，对该字符串可以进行 &ldquo;移位&rdquo; 的操作，也就是将字符串中每个字母都变为其在字母表中后续的字母，比如：<code>&quot;abc&quot; -&gt; &quot;bcd&quot;</code>。这样，我们可以持续进行 &ldquo;移位&rdquo; 操作，从而生成如下移位序列：</p>

<pre>&quot;abc&quot; -&gt; &quot;bcd&quot; -&gt; ... -&gt; &quot;xyz&quot;</pre>

<p>给定一个包含仅小写字母字符串的列表，将该列表中所有满足&nbsp;&ldquo;移位&rdquo; 操作规律的组合进行分组并返回。</p>

<p>&nbsp;</p>

<p><strong>示例：</strong></p>

<pre><strong>输入：</strong><code>[&quot;abc&quot;, &quot;bcd&quot;, &quot;acef&quot;, &quot;xyz&quot;, &quot;az&quot;, &quot;ba&quot;, &quot;a&quot;, &quot;z&quot;]</code>
<strong>输出：</strong>
[
  [&quot;abc&quot;,&quot;bcd&quot;,&quot;xyz&quot;],
  [&quot;az&quot;,&quot;ba&quot;],
  [&quot;acef&quot;],
  [&quot;a&quot;,&quot;z&quot;]
]
<strong>解释：</strong>可以认为字母表首尾相接，所以 &#39;z&#39; 的后续为 &#39;a&#39;，所以 [&quot;az&quot;,&quot;ba&quot;] 也满足 &ldquo;移位&rdquo; 操作规律。</pre>

## 解法

### 方法一

<!-- tabs:start -->

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

<!-- tabs:end -->

<!-- end -->
