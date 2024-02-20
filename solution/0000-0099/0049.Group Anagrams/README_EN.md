# [49. Group Anagrams](https://leetcode.com/problems/group-anagrams)

[中文文档](/solution/0000-0099/0049.Group%20Anagrams/README.md)

<!-- tags:Array,Hash Table,String,Sorting -->

## Description

<p>Given an array of strings <code>strs</code>, group <strong>the anagrams</strong> together. You can return the answer in <strong>any order</strong>.</p>

<p>An <strong>Anagram</strong> is a word or phrase formed by rearranging the letters of a different word or phrase, typically using all the original letters exactly once.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<pre><strong>Input:</strong> strs = ["eat","tea","tan","ate","nat","bat"]
<strong>Output:</strong> [["bat"],["nat","tan"],["ate","eat","tea"]]
</pre><p><strong class="example">Example 2:</strong></p>
<pre><strong>Input:</strong> strs = [""]
<strong>Output:</strong> [[""]]
</pre><p><strong class="example">Example 3:</strong></p>
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

### Solution 1: Hash Table

1. Traverse the string array, sort each string in **character dictionary order** to get a new string.
2. Use the new string as `key` and `[str]` as `value`, and store them in the hash table (`HashMap<String, List<String>>`).
3. When encountering the same `key` during subsequent traversal, add it to the corresponding `value`.

Take `strs = ["eat", "tea", "tan", "ate", "nat", "bat"]` as an example. At the end of the traversal, the state of the hash table is:

| key     | value                   |
| ------- | ----------------------- |
| `"aet"` | `["eat", "tea", "ate"]` |
| `"ant"` | `["tan", "nat"] `       |
| `"abt"` | `["bat"] `              |

Finally, return the `value` list of the hash table.

The time complexity is $O(n\times k\times \log k)$, where $n$ and $k$ are the lengths of the string array and the maximum length of the string, respectively.

<!-- tabs:start -->

```python
class Solution:
    def groupAnagrams(self, strs: List[str]) -> List[List[str]]:
        d = defaultdict(list)
        for s in strs:
            k = ''.join(sorted(s))
            d[k].append(s)
        return list(d.values())
```

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

```ts
function groupAnagrams(strs: string[]): string[][] {
    const d: Map<string, string[]> = new Map();
    for (const s of strs) {
        const k = s.split('').sort().join('');
        if (!d.has(k)) {
            d.set(k, []);
        }
        d.get(k)!.push(s);
    }
    return Array.from(d.values());
}
```

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
        map.into_iter()
            .map(|(_, v)| v)
            .collect()
    }
}
```

```cs
using System.Collections.Generic;

public class Comparer : IEqualityComparer<string>
{
    public bool Equals(string left, string right)
    {
        if (left.Length != right.Length) return false;

        var leftCount = new int[26];
        foreach (var ch in left)
        {
            ++leftCount[ch - 'a'];
        }

        var rightCount = new int[26];
        foreach (var ch in right)
        {
            var index = ch - 'a';
            if (++rightCount[index] > leftCount[index]) return false;
        }

        return true;
    }

    public int GetHashCode(string obj)
    {
        var hashCode = 0;
        for (int i = 0; i < obj.Length; ++i)
        {
            hashCode ^= 1 << (obj[i] - 'a');
        }
        return hashCode;
    }
}

public class Solution {
    public IList<IList<string>> GroupAnagrams(string[] strs) {
        var dict = new Dictionary<string, List<string>>(new Comparer());
        foreach (var str in strs)
        {
            List<string> list;
            if (!dict.TryGetValue(str, out list))
            {
                list = new List<string>();
                dict.Add(str, list);
            }
            list.Add(str);
        }
        foreach (var list in dict.Values)
        {
            list.Sort();
        }
        return new List<IList<string>>(dict.Values);
    }
}
```

<!-- tabs:end -->

### Solution 2: Counting

We can also change the sorting part in Solution 1 to counting, that is, use the characters in each string $s$ and their occurrence times as `key`, and use the string $s$ as `value` to store in the hash table.

The time complexity is $O(n\times (k + C))$, where $n$ and $k$ are the lengths of the string array and the maximum length of the string, respectively, and $C$ is the size of the character set. In this problem, $C = 26$.

<!-- tabs:start -->

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

<!-- tabs:end -->

<!-- end -->
