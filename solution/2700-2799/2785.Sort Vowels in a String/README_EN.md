---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2700-2799/2785.Sort%20Vowels%20in%20a%20String/README_EN.md
rating: 1266
source: Biweekly Contest 109 Q2
tags:
    - String
    - Sorting
---

<!-- problem:start -->

# [2785. Sort Vowels in a String](https://leetcode.com/problems/sort-vowels-in-a-string)

[中文文档](/solution/2700-2799/2785.Sort%20Vowels%20in%20a%20String/README.md)

## Description

<!-- description:start -->

<p>Given a <strong>0-indexed</strong> string <code>s</code>, <strong>permute</strong> <code>s</code> to get a new string <code>t</code> such that:</p>

<ul>
	<li>All consonants remain in their original places. More formally, if there is an index <code>i</code> with <code>0 &lt;= i &lt; s.length</code> such that <code>s[i]</code> is a consonant, then <code>t[i] = s[i]</code>.</li>
	<li>The vowels must be sorted in the <strong>nondecreasing</strong> order of their <strong>ASCII</strong> values. More formally, for pairs of indices <code>i</code>, <code>j</code> with <code>0 &lt;= i &lt; j &lt; s.length</code> such that <code>s[i]</code> and <code>s[j]</code> are vowels, then <code>t[i]</code> must not have a higher ASCII value than <code>t[j]</code>.</li>
</ul>

<p>Return <em>the resulting string</em>.</p>

<p>The vowels are <code>&#39;a&#39;</code>, <code>&#39;e&#39;</code>, <code>&#39;i&#39;</code>, <code>&#39;o&#39;</code>, and <code>&#39;u&#39;</code>, and they can appear in lowercase or uppercase. Consonants comprise all letters that are not vowels.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;lEetcOde&quot;
<strong>Output:</strong> &quot;lEOtcede&quot;
<strong>Explanation:</strong> &#39;E&#39;, &#39;O&#39;, and &#39;e&#39; are the vowels in s; &#39;l&#39;, &#39;t&#39;, &#39;c&#39;, and &#39;d&#39; are all consonants. The vowels are sorted according to their ASCII values, and the consonants remain in the same places.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;lYmpH&quot;
<strong>Output:</strong> &quot;lYmpH&quot;
<strong>Explanation:</strong> There are no vowels in s (all characters in s are consonants), so we return &quot;lYmpH&quot;.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s</code> consists only of letters of the&nbsp;English alphabet&nbsp;in <strong>uppercase and lowercase</strong>.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Sorting

First, we store all the vowels in the string into an array or list $vs$, then we sort $vs$.

Next, we traverse the string $s$, keeping the consonants unchanged. If it is a vowel, we replace it in order with the letters in the $vs$ array.

The time complexity is $O(n \times \log n)$, and the space complexity is $O(n)$. Where $n$ is the length of the string $s$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def sortVowels(self, s: str) -> str:
        vs = [c for c in s if c.lower() in "aeiou"]
        vs.sort()
        cs = list(s)
        j = 0
        for i, c in enumerate(cs):
            if c.lower() in "aeiou":
                cs[i] = vs[j]
                j += 1
        return "".join(cs)
```

#### Java

```java
class Solution {
    public String sortVowels(String s) {
        List<Character> vs = new ArrayList<>();
        char[] cs = s.toCharArray();
        for (char c : cs) {
            char d = Character.toLowerCase(c);
            if (d == 'a' || d == 'e' || d == 'i' || d == 'o' || d == 'u') {
                vs.add(c);
            }
        }
        Collections.sort(vs);
        for (int i = 0, j = 0; i < cs.length; ++i) {
            char d = Character.toLowerCase(cs[i]);
            if (d == 'a' || d == 'e' || d == 'i' || d == 'o' || d == 'u') {
                cs[i] = vs.get(j++);
            }
        }
        return String.valueOf(cs);
    }
}
```

#### C++

```cpp
class Solution {
public:
    string sortVowels(string s) {
        string vs;
        for (auto c : s) {
            char d = tolower(c);
            if (d == 'a' || d == 'e' || d == 'i' || d == 'o' || d == 'u') {
                vs.push_back(c);
            }
        }
        sort(vs.begin(), vs.end());
        for (int i = 0, j = 0; i < s.size(); ++i) {
            char d = tolower(s[i]);
            if (d == 'a' || d == 'e' || d == 'i' || d == 'o' || d == 'u') {
                s[i] = vs[j++];
            }
        }
        return s;
    }
};
```

#### Go

```go
func sortVowels(s string) string {
	cs := []byte(s)
	vs := []byte{}
	for _, c := range cs {
		d := c | 32
		if d == 'a' || d == 'e' || d == 'i' || d == 'o' || d == 'u' {
			vs = append(vs, c)
		}
	}
	sort.Slice(vs, func(i, j int) bool { return vs[i] < vs[j] })
	j := 0
	for i, c := range cs {
		d := c | 32
		if d == 'a' || d == 'e' || d == 'i' || d == 'o' || d == 'u' {
			cs[i] = vs[j]
			j++
		}
	}
	return string(cs)
}
```

#### TypeScript

```ts
function sortVowels(s: string): string {
    const vowels = ['a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'];
    const vs = s
        .split('')
        .filter(c => vowels.includes(c))
        .sort();
    const ans: string[] = [];
    let j = 0;
    for (const c of s) {
        ans.push(vowels.includes(c) ? vs[j++] : c);
    }
    return ans.join('');
}
```

#### C#

```cs
public class Solution {
    public string SortVowels(string s) {
        List<char> vs = new List<char>();
        char[] cs = s.ToCharArray();
        foreach (char c in cs) {
            if (IsVowel(c)) {
                vs.Add(c);
            }
        }
        vs.Sort();
        for (int i = 0, j = 0; i < cs.Length; ++i) {
            if (IsVowel(cs[i])) {
                cs[i] = vs[j++];
            }
        }
        return new string(cs);
    }

    public bool IsVowel(char c) {
        c = char.ToLower(c);
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
