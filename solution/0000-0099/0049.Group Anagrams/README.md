# [49. 字母异位词分组](https://leetcode-cn.com/problems/group-anagrams)

[English Version](/solution/0000-0099/0049.Group%20Anagrams/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个字符串数组，请你将 <strong>字母异位词</strong> 组合在一起。可以按任意顺序返回结果列表。</p>

<p><strong>字母异位词</strong> 是由重新排列源单词的字母得到的一个新单词，所有源单词中的字母通常恰好只用一次。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入:</strong> strs = <code>["eat", "tea", "tan", "ate", "nat", "bat"]</code>
<strong>输出: </strong>[["bat"],["nat","tan"],["ate","eat","tea"]]</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入:</strong> strs = <code>[""]</code>
<strong>输出: </strong>[[""]]
</pre>

<p><strong>示例 3:</strong></p>

<pre>
<strong>输入:</strong> strs = <code>["a"]</code>
<strong>输出: </strong>[["a"]]</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= strs.length &lt;= 10<sup>4</sup></code></li>
	<li><code>0 &lt;= strs[i].length &lt;= 100</code></li>
	<li><code>strs[i]</code>&nbsp;仅包含小写字母</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

遍历字符串，将每个字符串按照字符字典序排序后得到一个新的字符串，将相同的新字符串放在哈希表的同一个 key 对应 value 列表中。

| key     | value                   |
| ------- | ----------------------- |
| `"aet"` | `["eat", "tea", "ate"]` |
| `"ant"` | `["tan", "nat"] `       |
| `"abt"` | `["bat"] `              |

最后返回哈希表的 value 列表即可。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
