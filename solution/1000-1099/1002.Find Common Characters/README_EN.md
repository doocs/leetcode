---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1000-1099/1002.Find%20Common%20Characters/README_EN.md
rating: 1279
source: Weekly Contest 126 Q1
tags:
    - Array
    - Hash Table
    - String
---

<!-- problem:start -->

# [1002. Find Common Characters](https://leetcode.com/problems/find-common-characters)

[中文文档](/solution/1000-1099/1002.Find%20Common%20Characters/README.md)

## Description

<!-- description:start -->

<p>Given a string array <code>words</code>, return <em>an array of all characters that show up in all strings within the </em><code>words</code><em> (including duplicates)</em>. You may return the answer in <strong>any order</strong>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<pre><strong>Input:</strong> words = ["bella","label","roller"]
<strong>Output:</strong> ["e","l","l"]
</pre><p><strong class="example">Example 2:</strong></p>
<pre><strong>Input:</strong> words = ["cool","lock","cook"]
<strong>Output:</strong> ["c","o"]
</pre>
<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= words.length &lt;= 100</code></li>
	<li><code>1 &lt;= words[i].length &lt;= 100</code></li>
	<li><code>words[i]</code> consists of lowercase English letters.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Counting

We use an array $cnt$ of length $26$ to record the minimum number of times each character appears in all strings. Finally, we traverse the $cnt$ array and add characters with a count greater than $0$ to the answer.

The time complexity is $O(n \sum w_i)$, and the space complexity is $O(|\Sigma|)$. Here, $n$ is the length of the string array $words$, $w_i$ is the length of the $i$-th string in the array $words$, and $|\Sigma|$ is the size of the character set, which is $26$ in this problem.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def commonChars(self, words: List[str]) -> List[str]:
        cnt = Counter(words[0])
        for w in words:
            t = Counter(w)
            for c in cnt:
                cnt[c] = min(cnt[c], t[c])
        return list(cnt.elements())
```

#### Java

```java
class Solution {
    public List<String> commonChars(String[] words) {
        int[] cnt = new int[26];
        Arrays.fill(cnt, 20000);
        for (var w : words) {
            int[] t = new int[26];
            for (int i = 0; i < w.length(); ++i) {
                ++t[w.charAt(i) - 'a'];
            }
            for (int i = 0; i < 26; ++i) {
                cnt[i] = Math.min(cnt[i], t[i]);
            }
        }
        List<String> ans = new ArrayList<>();
        for (int i = 0; i < 26; ++i) {
            ans.addAll(Collections.nCopies(cnt[i], String.valueOf((char) ('a' + i))));
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<string> commonChars(vector<string>& words) {
        vector<int> cnt(26, 20000);
        for (const auto& w : words) {
            vector<int> t(26, 0);
            for (char c : w) {
                ++t[c - 'a'];
            }
            for (int i = 0; i < 26; ++i) {
                cnt[i] = min(cnt[i], t[i]);
            }
        }
        vector<string> ans;
        for (int i = 0; i < 26; ++i) {
            for (int j = 0; j < cnt[i]; ++j) {
                ans.push_back(string(1, 'a' + i));
            }
        }
        return ans;
    }
};
```

#### Go

```go
func commonChars(words []string) (ans []string) {
	cnt := make([]int, 26)
	for i := range cnt {
		cnt[i] = 20000
	}
	for _, w := range words {
		t := make([]int, 26)
		for _, c := range w {
			t[c-'a']++
		}
		for i := 0; i < 26; i++ {
			cnt[i] = min(cnt[i], t[i])
		}
	}
	for i := 0; i < 26; i++ {
		for j := 0; j < cnt[i]; j++ {
			ans = append(ans, string('a'+rune(i)))
		}
	}
	return ans
}
```

#### TypeScript

```ts
function commonChars(words: string[]): string[] {
    const cnt = Array(26).fill(20000);
    const aCode = 'a'.charCodeAt(0);
    for (const w of words) {
        const t = Array(26).fill(0);
        for (const c of w) {
            t[c.charCodeAt(0) - aCode]++;
        }
        for (let i = 0; i < 26; i++) {
            cnt[i] = Math.min(cnt[i], t[i]);
        }
    }
    const ans: string[] = [];
    for (let i = 0; i < 26; i++) {
        cnt[i] && ans.push(...String.fromCharCode(i + aCode).repeat(cnt[i]));
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
