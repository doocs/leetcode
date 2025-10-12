---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2200-2299/2273.Find%20Resultant%20Array%20After%20Removing%20Anagrams/README_EN.md
rating: 1294
source: Weekly Contest 293 Q1
tags:
    - Array
    - Hash Table
    - String
    - Sorting
---

<!-- problem:start -->

# [2273. Find Resultant Array After Removing Anagrams](https://leetcode.com/problems/find-resultant-array-after-removing-anagrams)

[中文文档](/solution/2200-2299/2273.Find%20Resultant%20Array%20After%20Removing%20Anagrams/README.md)

## Description

<!-- description:start -->

<p>You are given a <strong>0-indexed</strong> string array <code>words</code>, where <code>words[i]</code> consists of lowercase English letters.</p>

<p>In one operation, select any index <code>i</code> such that <code>0 &lt; i &lt; words.length</code> and <code>words[i - 1]</code> and <code>words[i]</code> are <strong>anagrams</strong>, and <strong>delete</strong> <code>words[i]</code> from <code>words</code>. Keep performing this operation as long as you can select an index that satisfies the conditions.</p>

<p>Return <code>words</code> <em>after performing all operations</em>. It can be shown that selecting the indices for each operation in <strong>any</strong> arbitrary order will lead to the same result.</p>

<p>An <strong>Anagram</strong> is a word or phrase formed by rearranging the letters of a different word or phrase using all the original letters exactly once. For example, <code>&quot;dacb&quot;</code> is an anagram of <code>&quot;abdc&quot;</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> words = [&quot;abba&quot;,&quot;baba&quot;,&quot;bbaa&quot;,&quot;cd&quot;,&quot;cd&quot;]
<strong>Output:</strong> [&quot;abba&quot;,&quot;cd&quot;]
<strong>Explanation:</strong>
One of the ways we can obtain the resultant array is by using the following operations:
- Since words[2] = &quot;bbaa&quot; and words[1] = &quot;baba&quot; are anagrams, we choose index 2 and delete words[2].
  Now words = [&quot;abba&quot;,&quot;baba&quot;,&quot;cd&quot;,&quot;cd&quot;].
- Since words[1] = &quot;baba&quot; and words[0] = &quot;abba&quot; are anagrams, we choose index 1 and delete words[1].
  Now words = [&quot;abba&quot;,&quot;cd&quot;,&quot;cd&quot;].
- Since words[2] = &quot;cd&quot; and words[1] = &quot;cd&quot; are anagrams, we choose index 2 and delete words[2].
  Now words = [&quot;abba&quot;,&quot;cd&quot;].
We can no longer perform any operations, so [&quot;abba&quot;,&quot;cd&quot;] is the final answer.</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> words = [&quot;a&quot;,&quot;b&quot;,&quot;c&quot;,&quot;d&quot;,&quot;e&quot;]
<strong>Output:</strong> [&quot;a&quot;,&quot;b&quot;,&quot;c&quot;,&quot;d&quot;,&quot;e&quot;]
<strong>Explanation:</strong>
No two adjacent strings in words are anagrams of each other, so no operations are performed.</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= words.length &lt;= 100</code></li>
	<li><code>1 &lt;= words[i].length &lt;= 10</code></li>
	<li><code>words[i]</code> consists of lowercase English letters.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Simulation

We first add $\textit{words}[0]$ to the answer array, then traverse from $\textit{words}[1]$. If $\textit{words}[i - 1]$ and $\textit{words}[i]$ are not anagrams, we add $\textit{words}[i]$ to the answer array.

The problem is converted to determining whether two strings are anagrams. We define a helper function $\textit{check}(s, t)$ to achieve this. If $s$ and $t$ are not anagrams, we return $\text{true}$; otherwise, we return $\text{false}$.

In the function $\textit{check}(s, t)$, we first check if the lengths of $s$ and $t$ are equal. If they are not, we return $\text{true}$. Otherwise, we use an array $\textit{cnt}$ of length $26$ to count the occurrences of each character in $s$, then traverse each character in $t$ and decrement $\textit{cnt}[c]$ by $1$. If $\textit{cnt}[c]$ is less than $0$, we return $\text{true}$. If we traverse all characters in $t$ without issues, it means $s$ and $t$ are anagrams, and we return $\text{false}$.

The time complexity is $O(L)$, and the space complexity is $O(|\Sigma|)$. Here, $L$ is the length of the array $\textit{words}$, and $\Sigma$ is the character set, which is lowercase English letters, so $|\Sigma| = 26$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def removeAnagrams(self, words: List[str]) -> List[str]:
        def check(s: str, t: str) -> bool:
            if len(s) != len(t):
                return True
            cnt = Counter(s)
            for c in t:
                cnt[c] -= 1
                if cnt[c] < 0:
                    return True
            return False

        return [words[0]] + [t for s, t in pairwise(words) if check(s, t)]
```

#### Java

```java
class Solution {
    public List<String> removeAnagrams(String[] words) {
        List<String> ans = new ArrayList<>();
        ans.add(words[0]);
        for (int i = 1; i < words.length; ++i) {
            if (check(words[i - 1], words[i])) {
                ans.add(words[i]);
            }
        }
        return ans;
    }

    private boolean check(String s, String t) {
        if (s.length() != t.length()) {
            return true;
        }
        int[] cnt = new int[26];
        for (int i = 0; i < s.length(); ++i) {
            ++cnt[s.charAt(i) - 'a'];
        }
        for (int i = 0; i < t.length(); ++i) {
            if (--cnt[t.charAt(i) - 'a'] < 0) {
                return true;
            }
        }
        return false;
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<string> removeAnagrams(vector<string>& words) {
        auto check = [](string& s, string& t) -> bool {
            if (s.size() != t.size()) {
                return true;
            }
            int cnt[26]{};
            for (char& c : s) {
                ++cnt[c - 'a'];
            }
            for (char& c : t) {
                if (--cnt[c - 'a'] < 0) {
                    return true;
                }
            }
            return false;
        };

        vector<string> ans = {words[0]};
        for (int i = 1; i < words.size(); ++i) {
            if (check(words[i - 1], words[i])) {
                ans.emplace_back(words[i]);
            }
        }
        return ans;
    }
};
```

#### Go

```go
func removeAnagrams(words []string) []string {
	ans := []string{words[0]}
	check := func(s, t string) bool {
		if len(s) != len(t) {
			return true
		}
		cnt := [26]int{}
		for _, c := range s {
			cnt[c-'a']++
		}
		for _, c := range t {
			cnt[c-'a']--
			if cnt[c-'a'] < 0 {
				return true
			}
		}
		return false
	}
	for i, t := range words[1:] {
		if check(words[i], t) {
			ans = append(ans, t)
		}
	}
	return ans
}
```

#### TypeScript

```ts
function removeAnagrams(words: string[]): string[] {
    const ans: string[] = [words[0]];
    const check = (s: string, t: string): boolean => {
        if (s.length !== t.length) {
            return true;
        }
        const cnt: number[] = Array(26).fill(0);
        for (const c of s) {
            ++cnt[c.charCodeAt(0) - 97];
        }
        for (const c of t) {
            if (--cnt[c.charCodeAt(0) - 97] < 0) {
                return true;
            }
        }
        return false;
    };
    for (let i = 1; i < words.length; ++i) {
        if (check(words[i - 1], words[i])) {
            ans.push(words[i]);
        }
    }
    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn remove_anagrams(words: Vec<String>) -> Vec<String> {
        fn check(s: &str, t: &str) -> bool {
            if s.len() != t.len() {
                return true;
            }
            let mut cnt = [0; 26];
            for c in s.bytes() {
                cnt[(c - b'a') as usize] += 1;
            }
            for c in t.bytes() {
                let idx = (c - b'a') as usize;
                cnt[idx] -= 1;
                if cnt[idx] < 0 {
                    return true;
                }
            }
            false
        }

        let mut ans = vec![words[0].clone()];
        for i in 1..words.len() {
            if check(&words[i - 1], &words[i]) {
                ans.push(words[i].clone());
            }
        }
        ans
    }
}
```

#### JavaScript

```js
/**
 * @param {string[]} words
 * @return {string[]}
 */
var removeAnagrams = function (words) {
    const ans = [words[0]];
    const check = (s, t) => {
        if (s.length !== t.length) {
            return true;
        }
        const cnt = Array(26).fill(0);
        for (const c of s) {
            ++cnt[c.charCodeAt() - 97];
        }
        for (const c of t) {
            if (--cnt[c.charCodeAt() - 97] < 0) {
                return true;
            }
        }
        return false;
    };
    for (let i = 1; i < words.length; ++i) {
        if (check(words[i - 1], words[i])) {
            ans.push(words[i]);
        }
    }
    return ans;
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
