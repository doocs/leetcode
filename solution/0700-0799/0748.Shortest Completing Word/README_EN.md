---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0700-0799/0748.Shortest%20Completing%20Word/README_EN.md
tags:
    - Array
    - Hash Table
    - String
---

<!-- problem:start -->

# [748. Shortest Completing Word](https://leetcode.com/problems/shortest-completing-word)

[中文文档](/solution/0700-0799/0748.Shortest%20Completing%20Word/README.md)

## Description

<!-- description:start -->

<p>Given a string <code>licensePlate</code> and an array of strings <code>words</code>, find the <strong>shortest completing</strong> word in <code>words</code>.</p>

<p>A <strong>completing</strong> word is a word that <strong>contains all the letters</strong> in <code>licensePlate</code>. <strong>Ignore numbers and spaces</strong> in <code>licensePlate</code>, and treat letters as <strong>case insensitive</strong>. If a letter appears more than once in <code>licensePlate</code>, then it must appear in the word the same number of times or more.</p>

<p>For example, if <code>licensePlate</code><code> = &quot;aBc 12c&quot;</code>, then it contains letters <code>&#39;a&#39;</code>, <code>&#39;b&#39;</code> (ignoring case), and <code>&#39;c&#39;</code> twice. Possible <strong>completing</strong> words are <code>&quot;abccdef&quot;</code>, <code>&quot;caaacab&quot;</code>, and <code>&quot;cbca&quot;</code>.</p>

<p>Return <em>the shortest <strong>completing</strong> word in </em><code>words</code><em>.</em> It is guaranteed an answer exists. If there are multiple shortest <strong>completing</strong> words, return the <strong>first</strong> one that occurs in <code>words</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> licensePlate = &quot;1s3 PSt&quot;, words = [&quot;step&quot;,&quot;steps&quot;,&quot;stripe&quot;,&quot;stepple&quot;]
<strong>Output:</strong> &quot;steps&quot;
<strong>Explanation:</strong> licensePlate contains letters &#39;s&#39;, &#39;p&#39;, &#39;s&#39; (ignoring case), and &#39;t&#39;.
&quot;step&quot; contains &#39;t&#39; and &#39;p&#39;, but only contains 1 &#39;s&#39;.
&quot;steps&quot; contains &#39;t&#39;, &#39;p&#39;, and both &#39;s&#39; characters.
&quot;stripe&quot; is missing an &#39;s&#39;.
&quot;stepple&quot; is missing an &#39;s&#39;.
Since &quot;steps&quot; is the only word containing all the letters, that is the answer.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> licensePlate = &quot;1s3 456&quot;, words = [&quot;looks&quot;,&quot;pest&quot;,&quot;stew&quot;,&quot;show&quot;]
<strong>Output:</strong> &quot;pest&quot;
<strong>Explanation:</strong> licensePlate only contains the letter &#39;s&#39;. All the words contain &#39;s&#39;, but among these &quot;pest&quot;, &quot;stew&quot;, and &quot;show&quot; are shortest. The answer is &quot;pest&quot; because it is the word that appears earliest of the 3.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= licensePlate.length &lt;= 7</code></li>
	<li><code>licensePlate</code> contains digits, letters (uppercase or lowercase), or space <code>&#39; &#39;</code>.</li>
	<li><code>1 &lt;= words.length &lt;= 1000</code></li>
	<li><code>1 &lt;= words[i].length &lt;= 15</code></li>
	<li><code>words[i]</code> consists of lower case English letters.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Counting

First, we use a hash table or an array $cnt$ of length $26$ to count the frequency of each letter in the string `licensePlate`. Note that we convert all letters to lowercase for counting.

Then, we traverse each word $w$ in the array `words`. If the length of the word $w$ is longer than the length of the answer $ans$, we directly skip this word. Otherwise, we use another hash table or an array $t$ of length $26$ to count the frequency of each letter in the word $w$. If for any letter, the frequency of this letter in $t$ is less than the frequency of this letter in $cnt$, we can also directly skip this word. Otherwise, we have found a word that meets the conditions, and we update the answer $ans$ to the current word $w$.

The time complexity is $O(n \times |\Sigma|)$, and the space complexity is $O(|\Sigma|)$. Here, $n$ is the length of the array `words`, and $\Sigma$ is the character set. In this case, the character set is all lowercase letters, so $|\Sigma| = 26$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def shortestCompletingWord(self, licensePlate: str, words: List[str]) -> str:
        cnt = Counter(c.lower() for c in licensePlate if c.isalpha())
        ans = None
        for w in words:
            if ans and len(w) >= len(ans):
                continue
            t = Counter(w)
            if all(v <= t[c] for c, v in cnt.items()):
                ans = w
        return ans
```

#### Java

```java
class Solution {
    public String shortestCompletingWord(String licensePlate, String[] words) {
        int[] cnt = new int[26];
        for (int i = 0; i < licensePlate.length(); ++i) {
            char c = licensePlate.charAt(i);
            if (Character.isLetter(c)) {
                cnt[Character.toLowerCase(c) - 'a']++;
            }
        }
        String ans = "";
        for (String w : words) {
            if (!ans.isEmpty() && w.length() >= ans.length()) {
                continue;
            }
            int[] t = new int[26];
            for (int i = 0; i < w.length(); ++i) {
                t[w.charAt(i) - 'a']++;
            }
            boolean ok = true;
            for (int i = 0; i < 26; ++i) {
                if (t[i] < cnt[i]) {
                    ok = false;
                    break;
                }
            }
            if (ok) {
                ans = w;
            }
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    string shortestCompletingWord(string licensePlate, vector<string>& words) {
        int cnt[26]{};
        for (char& c : licensePlate) {
            if (isalpha(c)) {
                ++cnt[tolower(c) - 'a'];
            }
        }
        string ans;
        for (auto& w : words) {
            if (ans.size() && ans.size() <= w.size()) {
                continue;
            }
            int t[26]{};
            for (char& c : w) {
                ++t[c - 'a'];
            }
            bool ok = true;
            for (int i = 0; i < 26; ++i) {
                if (cnt[i] > t[i]) {
                    ok = false;
                    break;
                }
            }
            if (ok) {
                ans = w;
            }
        }
        return ans;
    }
};
```

#### Go

```go
func shortestCompletingWord(licensePlate string, words []string) (ans string) {
	cnt := [26]int{}
	for _, c := range licensePlate {
		if unicode.IsLetter(c) {
			cnt[unicode.ToLower(c)-'a']++
		}
	}
	for _, w := range words {
		if len(ans) > 0 && len(ans) <= len(w) {
			continue
		}
		t := [26]int{}
		for _, c := range w {
			t[c-'a']++
		}
		ok := true
		for i, v := range cnt {
			if t[i] < v {
				ok = false
				break
			}
		}
		if ok {
			ans = w
		}
	}
	return
}
```

#### TypeScript

```ts
function shortestCompletingWord(licensePlate: string, words: string[]): string {
    const cnt: number[] = Array(26).fill(0);
    for (const c of licensePlate) {
        const i = c.toLowerCase().charCodeAt(0) - 97;
        if (0 <= i && i < 26) {
            ++cnt[i];
        }
    }
    let ans = '';
    for (const w of words) {
        if (ans.length && ans.length <= w.length) {
            continue;
        }
        const t = Array(26).fill(0);
        for (const c of w) {
            ++t[c.charCodeAt(0) - 97];
        }
        let ok = true;
        for (let i = 0; i < 26; ++i) {
            if (t[i] < cnt[i]) {
                ok = false;
                break;
            }
        }
        if (ok) {
            ans = w;
        }
    }
    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn shortest_completing_word(license_plate: String, words: Vec<String>) -> String {
        let mut cnt = vec![0; 26];
        for c in license_plate.chars() {
            if c.is_ascii_alphabetic() {
                cnt[((c.to_ascii_lowercase() as u8) - b'a') as usize] += 1;
            }
        }
        let mut ans = String::new();
        for w in words {
            if !ans.is_empty() && w.len() >= ans.len() {
                continue;
            }
            let mut t = vec![0; 26];
            for c in w.chars() {
                t[((c as u8) - b'a') as usize] += 1;
            }
            let mut ok = true;
            for i in 0..26 {
                if t[i] < cnt[i] {
                    ok = false;
                    break;
                }
            }
            if ok {
                ans = w;
            }
        }
        ans
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
