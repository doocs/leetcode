---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2800-2899/2828.Check%20if%20a%20String%20Is%20an%20Acronym%20of%20Words/README_EN.md
rating: 1151
source: Weekly Contest 359 Q1
tags:
    - Array
    - String
---

<!-- problem:start -->

# [2828. Check if a String Is an Acronym of Words](https://leetcode.com/problems/check-if-a-string-is-an-acronym-of-words)

[中文文档](/solution/2800-2899/2828.Check%20if%20a%20String%20Is%20an%20Acronym%20of%20Words/README.md)

## Description

<!-- description:start -->

<p>Given an array of strings <code>words</code> and a string <code>s</code>, determine if <code>s</code> is an <strong>acronym</strong> of words.</p>

<p>The string <code>s</code> is considered an acronym of <code>words</code> if it can be formed by concatenating the <strong>first</strong> character of each string in <code>words</code> <strong>in order</strong>. For example, <code>&quot;ab&quot;</code> can be formed from <code>[&quot;apple&quot;, &quot;banana&quot;]</code>, but it can&#39;t be formed from <code>[&quot;bear&quot;, &quot;aardvark&quot;]</code>.</p>

<p>Return <code>true</code><em> if </em><code>s</code><em> is an acronym of </em><code>words</code><em>, and </em><code>false</code><em> otherwise. </em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> words = [&quot;alice&quot;,&quot;bob&quot;,&quot;charlie&quot;], s = &quot;abc&quot;
<strong>Output:</strong> true
<strong>Explanation:</strong> The first character in the words &quot;alice&quot;, &quot;bob&quot;, and &quot;charlie&quot; are &#39;a&#39;, &#39;b&#39;, and &#39;c&#39;, respectively. Hence, s = &quot;abc&quot; is the acronym. 
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> words = [&quot;an&quot;,&quot;apple&quot;], s = &quot;a&quot;
<strong>Output:</strong> false
<strong>Explanation:</strong> The first character in the words &quot;an&quot; and &quot;apple&quot; are &#39;a&#39; and &#39;a&#39;, respectively. 
The acronym formed by concatenating these characters is &quot;aa&quot;. 
Hence, s = &quot;a&quot; is not the acronym.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> words = [&quot;never&quot;,&quot;gonna&quot;,&quot;give&quot;,&quot;up&quot;,&quot;on&quot;,&quot;you&quot;], s = &quot;ngguoy&quot;
<strong>Output:</strong> true
<strong>Explanation: </strong>By concatenating the first character of the words in the array, we get the string &quot;ngguoy&quot;. 
Hence, s = &quot;ngguoy&quot; is the acronym.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= words.length &lt;= 100</code></li>
	<li><code>1 &lt;= words[i].length &lt;= 10</code></li>
	<li><code>1 &lt;= s.length &lt;= 100</code></li>
	<li><code>words[i]</code> and <code>s</code> consist of lowercase English letters.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Simulation

We can iterate over each string in the array $words$, concatenate their first letters to form a new string $t$, and then check if $t$ is equal to $s$.

The time complexity is $O(n)$, and the space complexity is $O(n)$. Here, $n$ is the length of the array $words$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def isAcronym(self, words: List[str], s: str) -> bool:
        return "".join(w[0] for w in words) == s
```

#### Java

```java
class Solution {
    public boolean isAcronym(List<String> words, String s) {
        StringBuilder t = new StringBuilder();
        for (var w : words) {
            t.append(w.charAt(0));
        }
        return t.toString().equals(s);
    }
}
```

#### C++

```cpp
class Solution {
public:
    bool isAcronym(vector<string>& words, string s) {
        string t;
        for (auto& w : words) {
            t += w[0];
        }
        return t == s;
    }
};
```

#### Go

```go
func isAcronym(words []string, s string) bool {
	t := []byte{}
	for _, w := range words {
		t = append(t, w[0])
	}
	return string(t) == s
}
```

#### TypeScript

```ts
function isAcronym(words: string[], s: string): boolean {
    return words.map(w => w[0]).join('') === s;
}
```

#### Rust

```rust
impl Solution {
    pub fn is_acronym(words: Vec<String>, s: String) -> bool {
        words
            .iter()
            .map(|w| w.chars().next().unwrap_or_default())
            .collect::<String>()
            == s
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Solution 2: Simulation (Space Optimization)

First, we check if the number of strings in $words$ is equal to the length of $s$. If not, $s$ is definitely not an acronym of the first letters of $words$, and we directly return $false$.

Then, we iterate over each character in $s$, checking if it is equal to the first letter of the corresponding string in $words$. If not, $s$ is definitely not an acronym of the first letters of $words$, and we directly return $false$.

After the iteration, if we haven't returned $false$, then $s$ is an acronym of the first letters of $words$, and we return $true$.

The time complexity is $O(n)$, where $n$ is the length of the array $words$. The space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def isAcronym(self, words: List[str], s: str) -> bool:
        return len(words) == len(s) and all(w[0] == c for w, c in zip(words, s))
```

#### Java

```java
class Solution {
    public boolean isAcronym(List<String> words, String s) {
        if (words.size() != s.length()) {
            return false;
        }
        for (int i = 0; i < s.length(); ++i) {
            if (words.get(i).charAt(0) != s.charAt(i)) {
                return false;
            }
        }
        return true;
    }
}
```

#### C++

```cpp
class Solution {
public:
    bool isAcronym(vector<string>& words, string s) {
        if (words.size() != s.size()) {
            return false;
        }
        for (int i = 0; i < s.size(); ++i) {
            if (words[i][0] != s[i]) {
                return false;
            }
        }
        return true;
    }
};
```

#### Go

```go
func isAcronym(words []string, s string) bool {
	if len(words) != len(s) {
		return false
	}
	for i := range s {
		if words[i][0] != s[i] {
			return false
		}
	}
	return true
}
```

#### TypeScript

```ts
function isAcronym(words: string[], s: string): boolean {
    if (words.length !== s.length) {
        return false;
    }
    for (let i = 0; i < words.length; i++) {
        if (words[i][0] !== s[i]) {
            return false;
        }
    }
    return true;
}
```

#### Rust

```rust
impl Solution {
    pub fn is_acronym(words: Vec<String>, s: String) -> bool {
        if words.len() != s.len() {
            return false;
        }
        for (i, w) in words.iter().enumerate() {
            if w.chars().next().unwrap_or_default() != s.chars().nth(i).unwrap_or_default() {
                return false;
            }
        }
        true
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
