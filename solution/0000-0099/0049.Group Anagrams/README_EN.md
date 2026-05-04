---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0000-0099/0049.Group%20Anagrams/README_EN.md
tags:
    - Array
    - Hash Table
    - String
    - Sorting
---

<!-- problem:start -->

# [49. Group Anagrams](https://leetcode.com/problems/group-anagrams)

[中文文档](/solution/0000-0099/0049.Group%20Anagrams/README.md)

## Description

<!-- description:start -->

<p>Given an array of strings <code>strs</code>, group the <span data-keyword="anagram">anagrams</span> together. You can return the answer in <strong>any order</strong>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">strs = [&quot;eat&quot;,&quot;tea&quot;,&quot;tan&quot;,&quot;ate&quot;,&quot;nat&quot;,&quot;bat&quot;]</span></p>

<p><strong>Output:</strong> <span class="example-io">[[&quot;bat&quot;],[&quot;nat&quot;,&quot;tan&quot;],[&quot;ate&quot;,&quot;eat&quot;,&quot;tea&quot;]]</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>There is no string in strs that can be rearranged to form <code>&quot;bat&quot;</code>.</li>
	<li>The strings <code>&quot;nat&quot;</code> and <code>&quot;tan&quot;</code> are anagrams as they can be rearranged to form each other.</li>
	<li>The strings <code>&quot;ate&quot;</code>, <code>&quot;eat&quot;</code>, and <code>&quot;tea&quot;</code> are anagrams as they can be rearranged to form each other.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">strs = [&quot;&quot;]</span></p>

<p><strong>Output:</strong> <span class="example-io">[[&quot;&quot;]]</span></p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">strs = [&quot;a&quot;]</span></p>

<p><strong>Output:</strong> <span class="example-io">[[&quot;a&quot;]]</span></p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= strs.length &lt;= 10<sup>4</sup></code></li>
	<li><code>0 &lt;= strs[i].length &lt;= 100</code></li>
	<li><code>strs[i]</code> consists of lowercase English letters.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

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

#### Python3

```python
class Solution:
    def groupAnagrams(self, strs: List[str]) -> List[List[str]]:
        d = defaultdict(list)
        for s in strs:
            k = ''.join(sorted(s))
            d[k].append(s)
        return list(d.values())
```

#### Java

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

#### C++

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

#### Go

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

#### TypeScript

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

#### Rust

```rust
use std::collections::HashMap;

impl Solution {
    pub fn group_anagrams(strs: Vec<String>) -> Vec<Vec<String>> {
        let mut d = HashMap::new();
        for s in strs {
            let mut t: Vec<char> = s.chars().collect();
            t.sort_unstable();
            let k: String = t.into_iter().collect();
            d.entry(k).or_insert_with(Vec::new).push(s);
        }
        d.into_values().collect()
    }
}
```

#### C#

```cs
public class Solution {
    public IList<IList<string>> GroupAnagrams(string[] strs) {
        var d = new Dictionary<string, List<string>>();
        foreach (string s in strs) {
            char[] t = s.ToCharArray();
            Array.Sort(t);
            string k = new string(t);
            if (!d.ContainsKey(k)) {
                d[k] = new List<string>();
            }
            d[k].Add(s);
        }
        return new List<IList<string>>(d.Values);
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Solution 2: Counting

We can also change the sorting part in Solution 1 to counting, that is, use the characters in each string $s$ and their occurrence times as `key`, and use the string $s$ as `value` to store in the hash table.

The time complexity is $O(n\times (k + C))$, where $n$ and $k$ are the lengths of the string array and the maximum length of the string, respectively, and $C$ is the size of the character set. In this problem, $C = 26$.

<!-- tabs:start -->

#### Python3

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

#### Java

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

#### C++

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

#### Go

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

#### TypeScript

```ts
function groupAnagrams(strs: string[]): string[][] {
    const d = new Map<string, string[]>();
    for (const s of strs) {
        const cnt = new Array(26).fill(0);
        for (const c of s) {
            cnt[c.charCodeAt(0) - 'a'.charCodeAt(0)]++;
        }
        const key = cnt.join(',');
        if (!d.has(key)) {
            d.set(key, []);
        }
        d.get(key)!.push(s);
    }
    return Array.from(d.values());
}
```

#### Rust

```rust
use std::collections::HashMap;

impl Solution {
    pub fn group_anagrams(strs: Vec<String>) -> Vec<Vec<String>> {
        let mut d = HashMap::new();
        for s in strs {
            let mut cnt = [0; 26];
            for c in s.chars() {
                cnt[(c as usize) - ('a' as usize)] += 1;
            }
            d.entry(cnt).or_insert_with(Vec::new).push(s);
        }
        d.into_values().collect()
    }
}
```

#### C#

```cs
public class Solution {
    public IList<IList<string>> GroupAnagrams(string[] strs) {
        var d = new Dictionary<string, List<string>>();
        foreach (string s in strs) {
            int[] cnt = new int[26];
            foreach (char c in s) {
                cnt[c - 'a']++;
            }
            string key = string.Join(",", cnt);
            if (!d.ContainsKey(key)) {
                d[key] = new List<string>();
            }
            d[key].Add(s);
        }
        return new List<IList<string>>(d.Values);
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
