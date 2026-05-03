---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3900-3999/3913.Sort%20Vowels%20by%20Frequency/README_EN.md
rating: 1524
source: Weekly Contest 499 Q2
---

<!-- problem:start -->

# [3913. Sort Vowels by Frequency](https://leetcode.com/problems/sort-vowels-by-frequency)

[中文文档](/solution/3900-3999/3913.Sort%20Vowels%20by%20Frequency/README.md)

## Description

<!-- description:start -->

<p data-end="271" data-start="95">You are given a string <code>s</code> consisting of lowercase English characters.</p>

<p>Rearrange only the <strong>vowels</strong> in the string so that they appear in <strong>non-increasing</strong> order of their frequency.</p>

<p>If multiple vowels have the same <strong>frequency</strong>, order them by the position of their <strong>first occurrence</strong> in <code>s</code>.</p>

<p>Return the modified string.</p>

<p>Vowels are <code>&#39;a&#39;</code>, <code>&#39;e&#39;</code>, <code>&#39;i&#39;</code>, <code>&#39;o&#39;</code>, and <code>&#39;u&#39;</code>.</p>

<p>The <strong>frequency</strong> of a letter is the number of times it occurs in the string.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;leetcode&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">&quot;leetcedo&quot;</span></p>

<p><strong>Explanation:</strong>​​​​​​​</p>

<ul>
	<li>Vowels in the string are <code>[&#39;e&#39;, &#39;e&#39;, &#39;o&#39;, &#39;e&#39;]</code> with frequencies: <code>e = 3</code>, <code>o = 1</code>.</li>
	<li>Sorting in non-increasing order of frequency and placing them back into the vowel positions results in <code>&quot;leetcedo&quot;</code>.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;aeiaaioooa&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">&quot;aaaaoooiie&quot;</span></p>

<p><strong>Explanation:</strong>​​​​​​​</p>

<ul>
	<li>Vowels in the string are <code>[&#39;a&#39;, &#39;e&#39;, &#39;i&#39;, &#39;a&#39;, &#39;a&#39;, &#39;i&#39;, &#39;o&#39;, &#39;o&#39;, &#39;o&#39;, &#39;a&#39;]</code> with frequencies: <code>a = 4</code>, <code>o = 3</code>, <code>i = 2</code>, <code>e = 1</code>.</li>
	<li>Sorting them in non-increasing order of frequency and placing them back into the vowel positions results in <code>&quot;aaaaoooiie&quot;</code>.</li>
</ul>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;baeiou&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">&quot;baeiou&quot;</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Each vowel appears exactly once, so all have the same frequency.</li>
	<li>Thus, they retain their relative order based on first occurrence, and the string remains unchanged.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s</code> consists of lowercase English letters</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Counting + Custom Sorting

We can use a hash table $\textit{cnt}$ to record the frequency of each vowel. We also need a list $\textit{vowels}$ to store the vowels that appear in the string, ordered by their first occurrence.

We then sort the $\textit{vowels}$ list with a custom comparator: vowels are sorted in non-increasing order of their frequency.

Finally, we traverse the string, replacing each vowel with the corresponding letter from the $\textit{vowels}$ list, and update the frequency in the hash table. When the frequency of a vowel becomes 0, we move the pointer in the $\textit{vowels}$ list forward by one.

The time complexity is $O(n + |\Sigma| \log |\Sigma|)$ and the space complexity is $O(n + |\Sigma|)$, where $n$ is the length of the string and $\Sigma$ is the set of vowels that appear in the string.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def sortVowels(self, s: str) -> str:
        st = set("aeiou")
        vowels = []
        cnt = Counter()
        for c in s:
            if c not in st:
                continue
            if c not in cnt:
                vowels.append(c)
            cnt[c] += 1
        vowels.sort(key=lambda c: -cnt[c])
        ans = list(s)
        i = 0
        for k, c in enumerate(s):
            if c not in st:
                continue
            ans[k] = c = vowels[i]
            cnt[c] -= 1
            if cnt[c] == 0:
                i += 1
        return "".join(ans)
```

#### Java

```java
class Solution {
    public String sortVowels(String s) {
        Set<Character> st = Set.of('a', 'e', 'i', 'o', 'u');
        List<Character> vowels = new ArrayList<>();
        Map<Character, Integer> cnt = new HashMap<>();
        for (char c : s.toCharArray()) {
            if (!st.contains(c)) {
                continue;
            }
            if (!cnt.containsKey(c)) {
                vowels.add(c);
            }
            cnt.merge(c, 1, Integer::sum);
        }
        vowels.sort((a, b) -> cnt.get(b) - cnt.get(a));
        char[] ans = s.toCharArray();
        int i = 0;
        for (int k = 0; k < s.length(); k++) {
            char c = s.charAt(k);
            if (!st.contains(c)) {
                continue;
            }
            ans[k] = c = vowels.get(i);
            cnt.merge(c, -1, Integer::sum);
            if (cnt.get(c) == 0) {
                i++;
            }
        }
        return new String(ans);
    }
}
```

#### C++

```cpp
class Solution {
public:
    string sortVowels(string s) {
        unordered_set<char> st = {'a', 'e', 'i', 'o', 'u'};
        vector<char> vowels;
        unordered_map<char, int> cnt;
        for (char c : s) {
            if (!st.count(c)) {
                continue;
            }
            if (!cnt.count(c)) {
                vowels.push_back(c);
            }
            cnt[c]++;
        }
        sort(vowels.begin(), vowels.end(), [&](char a, char b) {
            return cnt[a] > cnt[b];
        });
        string ans = s;
        int i = 0;
        for (int k = 0; k < s.size(); k++) {
            if (!st.count(s[k])) {
                continue;
            }
            char c = vowels[i];
            ans[k] = c;
            if (--cnt[c] == 0) {
                i++;
            }
        }
        return ans;
    }
};
```

#### Go

```go
func sortVowels(s string) string {
	st := map[rune]bool{'a': true, 'e': true, 'i': true, 'o': true, 'u': true}
	var vowels []rune
	cnt := make(map[rune]int)
	for _, c := range s {
		if !st[c] {
			continue
		}
		if _, ok := cnt[c]; !ok {
			vowels = append(vowels, c)
		}
		cnt[c]++
	}
	sort.Slice(vowels, func(i, j int) bool {
		return cnt[vowels[i]] > cnt[vowels[j]]
	})
	ans := []rune(s)
	i := 0
	for k, c := range s {
		if !st[c] {
			continue
		}
		char := vowels[i]
		ans[k] = char
		cnt[char]--
		if cnt[char] == 0 {
			i++
		}
	}
	return string(ans)
}
```

#### TypeScript

```ts
function sortVowels(s: string): string {
    const st = new Set('aeiou');
    const vowels: string[] = [];
    const cnt: Map<string, number> = new Map();
    for (const c of s) {
        if (!st.has(c)) {
            continue;
        }
        if (!cnt.has(c)) {
            vowels.push(c);
        }
        cnt.set(c, (cnt.get(c) || 0) + 1);
    }
    vowels.sort((a, b) => (cnt.get(b) || 0) - (cnt.get(a) || 0));
    const ans = s.split('');
    let i = 0;
    for (let k = 0; k < s.length; k++) {
        let c = s[k];
        if (!st.has(c)) {
            continue;
        }
        c = vowels[i];
        ans[k] = c;
        cnt.set(c, (cnt.get(c) || 0) - 1);
        if (cnt.get(c) === 0) {
            i++;
        }
    }
    return ans.join('');
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
