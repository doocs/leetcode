---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0100-0199/0187.Repeated%20DNA%20Sequences/README_EN.md
tags:
    - Bit Manipulation
    - Hash Table
    - String
    - Sliding Window
    - Hash Function
    - Rolling Hash
---

<!-- problem:start -->

# [187. Repeated DNA Sequences](https://leetcode.com/problems/repeated-dna-sequences)

[中文文档](/solution/0100-0199/0187.Repeated%20DNA%20Sequences/README.md)

## Description

<p>The <strong>DNA sequence</strong> is composed of a series of nucleotides abbreviated as <code>&#39;A&#39;</code>, <code>&#39;C&#39;</code>, <code>&#39;G&#39;</code>, and <code>&#39;T&#39;</code>.</p>

<ul>
	<li>For example, <code>&quot;ACGAATTCCG&quot;</code> is a <strong>DNA sequence</strong>.</li>
</ul>

<p>When studying <strong>DNA</strong>, it is useful to identify repeated sequences within the DNA.</p>

<p>Given a string <code>s</code> that represents a <strong>DNA sequence</strong>, return all the <strong><code>10</code>-letter-long</strong> sequences (substrings) that occur more than once in a DNA molecule. You may return the answer in <strong>any order</strong>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<pre><strong>Input:</strong> s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"
<strong>Output:</strong> ["AAAAACCCCC","CCCCCAAAAA"]
</pre><p><strong class="example">Example 2:</strong></p>
<pre><strong>Input:</strong> s = "AAAAAAAAAAAAA"
<strong>Output:</strong> ["AAAAAAAAAA"]
</pre>
<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s[i]</code> is either <code>&#39;A&#39;</code>, <code>&#39;C&#39;</code>, <code>&#39;G&#39;</code>, or <code>&#39;T&#39;</code>.</li>
</ul>

## Solutions

<!-- solution:start -->

### Solution 1: Hash Table

We define a hash table $cnt$ to store the occurrence count of all substrings of length $10$.

We iterate through all substrings of length $10$ in the string $s$. For the current substring $t$, we update its count in the hash table. If the count of $t$ is $2$, we add it to the answer.

After the iteration, we return the answer array.

The time complexity is $O(n \times 10)$, and the space complexity is $O(n \times 10)$. Here, $n$ is the length of the string $s$.

<!-- tabs:start -->

```python
class Solution:
    def findRepeatedDnaSequences(self, s: str) -> List[str]:
        cnt = Counter()
        ans = []
        for i in range(len(s) - 10 + 1):
            t = s[i : i + 10]
            cnt[t] += 1
            if cnt[t] == 2:
                ans.append(t)
        return ans
```

```java
class Solution {
    public List<String> findRepeatedDnaSequences(String s) {
        Map<String, Integer> cnt = new HashMap<>();
        List<String> ans = new ArrayList<>();
        for (int i = 0; i < s.length() - 10 + 1; ++i) {
            String t = s.substring(i, i + 10);
            if (cnt.merge(t, 1, Integer::sum) == 2) {
                ans.add(t);
            }
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    vector<string> findRepeatedDnaSequences(string s) {
        unordered_map<string, int> cnt;
        vector<string> ans;
        for (int i = 0, n = s.size() - 10 + 1; i < n; ++i) {
            auto t = s.substr(i, 10);
            if (++cnt[t] == 2) {
                ans.emplace_back(t);
            }
        }
        return ans;
    }
};
```

```go
func findRepeatedDnaSequences(s string) (ans []string) {
	cnt := map[string]int{}
	for i := 0; i < len(s)-10+1; i++ {
		t := s[i : i+10]
		cnt[t]++
		if cnt[t] == 2 {
			ans = append(ans, t)
		}
	}
	return
}
```

```ts
function findRepeatedDnaSequences(s: string): string[] {
    const n = s.length;
    const cnt: Map<string, number> = new Map();
    const ans: string[] = [];
    for (let i = 0; i <= n - 10; ++i) {
        const t = s.slice(i, i + 10);
        cnt.set(t, (cnt.get(t) ?? 0) + 1);
        if (cnt.get(t) === 2) {
            ans.push(t);
        }
    }
    return ans;
}
```

```rust
use std::collections::HashMap;

impl Solution {
    pub fn find_repeated_dna_sequences(s: String) -> Vec<String> {
        if s.len() < 10 {
            return vec![];
        }
        let mut cnt = HashMap::new();
        let mut ans = Vec::new();
        for i in 0..s.len() - 9 {
            let t = &s[i..i + 10];
            let count = cnt.entry(t).or_insert(0);
            *count += 1;
            if *count == 2 {
                ans.push(t.to_string());
            }
        }
        ans
    }
}
```

```js
/**
 * @param {string} s
 * @return {string[]}
 */
var findRepeatedDnaSequences = function (s) {
    const cnt = new Map();
    const ans = [];
    for (let i = 0; i < s.length - 10 + 1; ++i) {
        const t = s.slice(i, i + 10);
        cnt.set(t, (cnt.get(t) || 0) + 1);
        if (cnt.get(t) === 2) {
            ans.push(t);
        }
    }
    return ans;
};
```

```cs
public class Solution {
    public IList<string> FindRepeatedDnaSequences(string s) {
        var cnt = new Dictionary<string, int>();
        var ans = new List<string>();
        for (int i = 0; i < s.Length - 10 + 1; ++i) {
            var t = s.Substring(i, 10);
            if (!cnt.ContainsKey(t)) {
                cnt[t] = 0;
            }
            if (++cnt[t] == 2) {
                ans.Add(t);
            }
        }
        return ans;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Solution 2: Rabin-Karp String Matching Algorithm

This method essentially combines sliding window and hash. Similar to 0028. Find the Index of the First Occurrence in a String, this problem can use a hash function to reduce the time complexity of counting subsequences to $O(1)$.

The time complexity is $O(n)$, and the space complexity is $O(n)$. Here, $n$ is the length of the string $s$.

<!-- tabs:start -->

```go
func findRepeatedDnaSequences(s string) []string {
	hashCode := map[byte]int{'A': 0, 'C': 1, 'G': 2, 'T': 3}
	ans, cnt, left, right := []string{}, map[int]int{}, 0, 0

	sha, multi := 0, int(math.Pow(4, 9))
	for ; right < len(s); right++ {
		sha = sha*4 + hashCode[s[right]]
		if right-left+1 < 10 {
			continue
		}
		cnt[sha]++
		if cnt[sha] == 2 {
			ans = append(ans, s[left:right+1])
		}
		sha, left = sha-multi*hashCode[s[left]], left+1
	}
	return ans
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
