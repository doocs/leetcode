# [49. 字母异位词分组](https://leetcode.cn/problems/group-anagrams)

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

**方法一：哈希表**

1. 遍历字符串，对每个字符串按照**字符字典序**排序，得到一个新的字符串。
2. 以新字符串为 `key`，`[str]` 为 `value`，存入哈希表当中（`HashMap<String, List<String>>`）。
3. 后续遍历得到相同 `key` 时，将其加入到对应的 `value` 当中即可。

以 `strs = ["eat", "tea", "tan", "ate", "nat", "bat"]` 为例，遍历结束时，哈希表的状况：

| key     | value                   |
| ------- | ----------------------- |
| `"aet"` | `["eat", "tea", "ate"]` |
| `"ant"` | `["tan", "nat"] `       |
| `"abt"` | `["bat"] `              |

最后返回哈希表的 `value` 列表即可。

时间复杂度 $O(n\times k\times \log k)$。其中 $n$ 和 $k$ 分别是字符串数组的长度和字符串的最大长度。

**方法二：计数**

我们也可以将方法一中的排序部分改为计数，也就是说，将每个字符串 $s$ 中的字符以及出现的次数作为 `key`，将字符串 $s$ 作为 `value` 存入哈希表当中。

时间复杂度 $O(n\times (k + C))$。其中 $n$ 和 $k$ 分别是字符串数组的长度和字符串的最大长度，而 $C$ 是字符集的大小，本题中 $C = 26$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def groupAnagrams(self, strs: List[str]) -> List[List[str]]:
        d = defaultdict(list)
        for s in strs:
            k = ''.join(sorted(s))
            d[k].append(s)
        return list(d.values())
```

```python
class Solution:
    def groupAnagrams(self, strs: List[str]) -> List[List[str]]:
        d = defaultdict(list)
        for s in strs:
            cnt = [0] * 26
            for c in s:
                cnt[ord(c) - ord('a')] += 1
            d[tuple(cnt)].append(s)
        return list(d.values())
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> d = new HashMap<>();
        for (String s : strs) {
            char[] t = s.toCharArray();
            Arrays.sort(t);
            String k = String.valueOf(t);
            d.computeIfAbsent(k, key -> new ArrayList<>()).add(s);
        }
        return new ArrayList<>(d.values());
    }
}
```

```java
class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> d = new HashMap<>();
        for (String s : strs) {
            int[] cnt = new int[26];
            for (int i = 0; i < s.length(); ++i) {
                ++cnt[s.charAt(i) - 'a'];
            }
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 26; ++i) {
                if (cnt[i] > 0) {
                    sb.append((char) ('a' + i)).append(cnt[i]);
                }
            }
            String k = sb.toString();
            d.computeIfAbsent(k, key -> new ArrayList<>()).add(s);
        }
        return new ArrayList<>(d.values());
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<vector<string>> groupAnagrams(vector<string>& strs) {
        unordered_map<string, vector<string>> d;
        for (auto& s : strs) {
            string k = s;
            sort(k.begin(), k.end());
            d[k].emplace_back(s);
        }
        vector<vector<string>> ans;
        for (auto& [_, v] : d) ans.emplace_back(v);
        return ans;
    }
};
```

```cpp
class Solution {
public:
    vector<vector<string>> groupAnagrams(vector<string>& strs) {
        unordered_map<string, vector<string>> d;
        for (auto& s : strs) {
            int cnt[26] = {0};
            for (auto& c : s) ++cnt[c - 'a'];
            string k;
            for (int i = 0; i < 26; ++i) {
                if (cnt[i]) {
                    k += 'a' + i;
                    k += to_string(cnt[i]);
                }
            }
            d[k].emplace_back(s);
        }
        vector<vector<string>> ans;
        for (auto& [_, v] : d) ans.emplace_back(v);
        return ans;
    }
};
```

### **Go**

```go
func groupAnagrams(strs []string) (ans [][]string) {
	d := map[string][]string{}
	for _, s := range strs {
		t := []byte(s)
		sort.Slice(t, func(i, j int) bool { return t[i] < t[j] })
		k := string(t)
		d[k] = append(d[k], s)
	}
	for _, v := range d {
		ans = append(ans, v)
	}
	return
}
```

```go
func groupAnagrams(strs []string) (ans [][]string) {
	d := map[[26]int][]string{}
	for _, s := range strs {
		cnt := [26]int{}
		for _, c := range s {
			cnt[c-'a']++
		}
		d[cnt] = append(d[cnt], s)
	}
	for _, v := range d {
		ans = append(ans, v)
	}
	return
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
