# [804. Unique Morse Code Words](https://leetcode.com/problems/unique-morse-code-words)

[中文文档](/solution/0800-0899/0804.Unique%20Morse%20Code%20Words/README.md)

## Description

<p>International Morse Code defines a standard encoding where each letter is mapped to a series of dots and dashes, as follows:</p>

<ul>
	<li><code>&#39;a&#39;</code> maps to <code>&quot;.-&quot;</code>,</li>
	<li><code>&#39;b&#39;</code> maps to <code>&quot;-...&quot;</code>,</li>
	<li><code>&#39;c&#39;</code> maps to <code>&quot;-.-.&quot;</code>, and so on.</li>
</ul>

<p>For convenience, the full table for the <code>26</code> letters of the English alphabet is given below:</p>

<pre>
[&quot;.-&quot;,&quot;-...&quot;,&quot;-.-.&quot;,&quot;-..&quot;,&quot;.&quot;,&quot;..-.&quot;,&quot;--.&quot;,&quot;....&quot;,&quot;..&quot;,&quot;.---&quot;,&quot;-.-&quot;,&quot;.-..&quot;,&quot;--&quot;,&quot;-.&quot;,&quot;---&quot;,&quot;.--.&quot;,&quot;--.-&quot;,&quot;.-.&quot;,&quot;...&quot;,&quot;-&quot;,&quot;..-&quot;,&quot;...-&quot;,&quot;.--&quot;,&quot;-..-&quot;,&quot;-.--&quot;,&quot;--..&quot;]</pre>

<p>Given an array of strings <code>words</code> where each word can be written as a concatenation of the Morse code of each letter.</p>

<ul>
	<li>For example, <code>&quot;cab&quot;</code> can be written as <code>&quot;-.-..--...&quot;</code>, which is the concatenation of <code>&quot;-.-.&quot;</code>, <code>&quot;.-&quot;</code>, and <code>&quot;-...&quot;</code>. We will call such a concatenation the <strong>transformation</strong> of a word.</li>
</ul>

<p>Return <em>the number of different <strong>transformations</strong> among all words we have</em>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> words = [&quot;gin&quot;,&quot;zen&quot;,&quot;gig&quot;,&quot;msg&quot;]
<strong>Output:</strong> 2
<strong>Explanation:</strong> The transformation of each word is:
&quot;gin&quot; -&gt; &quot;--...-.&quot;
&quot;zen&quot; -&gt; &quot;--...-.&quot;
&quot;gig&quot; -&gt; &quot;--...--.&quot;
&quot;msg&quot; -&gt; &quot;--...--.&quot;
There are 2 different transformations: &quot;--...-.&quot; and &quot;--...--.&quot;.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> words = [&quot;a&quot;]
<strong>Output:</strong> 1
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= words.length &lt;= 100</code></li>
	<li><code>1 &lt;= words[i].length &lt;= 12</code></li>
	<li><code>words[i]</code> consists of lowercase English letters.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def uniqueMorseRepresentations(self, words: List[str]) -> int:
        codes = [
            ".-",
            "-...",
            "-.-.",
            "-..",
            ".",
            "..-.",
            "--.",
            "....",
            "..",
            ".---",
            "-.-",
            ".-..",
            "--",
            "-.",
            "---",
            ".--.",
            "--.-",
            ".-.",
            "...",
            "-",
            "..-",
            "...-",
            ".--",
            "-..-",
            "-.--",
            "--..",
        ]
        s = {''.join([codes[ord(c) - ord('a')] for c in word]) for word in words}
        return len(s)
```

### **Java**

```java
class Solution {
    public int uniqueMorseRepresentations(String[] words) {
        String[] codes = new String[]{".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---", "-.-", ".-..", "--", "-.", "---", ".--.", "--.-", ".-.", "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--.."};
        Set<String> s = new HashSet<>();
        for (String word : words) {
            StringBuilder t = new StringBuilder();
            for (char c : word.toCharArray()) {
                t.append(codes[c - 'a']);
            }
            s.add(t.toString());
        }
        return s.size();
    }
}
```

### **TypeScript**

```ts
const codes = [
    '.-',
    '-...',
    '-.-.',
    '-..',
    '.',
    '..-.',
    '--.',
    '....',
    '..',
    '.---',
    '-.-',
    '.-..',
    '--',
    '-.',
    '---',
    '.--.',
    '--.-',
    '.-.',
    '...',
    '-',
    '..-',
    '...-',
    '.--',
    '-..-',
    '-.--',
    '--..',
];

function uniqueMorseRepresentations(words: string[]): number {
    return new Set(
        words.map(word => {
            return word
                .split('')
                .map(c => codes[c.charCodeAt(0) - 'a'.charCodeAt(0)])
                .join('');
        }),
    ).size;
}
```

### **Rust**

```rust
use std::collections::HashSet;
impl Solution {
    pub fn unique_morse_representations(words: Vec<String>) -> i32 {
        const codes: [&str; 26] = [
            ".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---", "-.-", ".-..",
            "--", "-.", "---", ".--.", "--.-", ".-.", "...", "-", "..-", "...-", ".--", "-..-",
            "-.--", "--..",
        ];
        words
            .iter()
            .map(|word| {
                word.as_bytes()
                    .iter()
                    .map(|v| codes[(v - b'a') as usize])
                    .collect::<String>()
            })
            .collect::<HashSet<String>>()
            .len() as i32
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int uniqueMorseRepresentations(vector<string>& words) {
        vector<string> codes = {".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---", "-.-", ".-..", "--", "-.",
            "---", ".--.", "--.-", ".-.", "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--.."};
        unordered_set<string> s;
        for (auto& word : words) {
            string t;
            for (char& c : word) t += codes[c - 'a'];
            s.insert(t);
        }
        return s.size();
    }
};
```

### **Go**

```go
func uniqueMorseRepresentations(words []string) int {
	codes := []string{".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---", "-.-", ".-..", "--", "-.",
		"---", ".--.", "--.-", ".-.", "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--.."}
	s := make(map[string]bool)
	for _, word := range words {
		t := &strings.Builder{}
		for _, c := range word {
			t.WriteString(codes[c-'a'])
		}
		s[t.String()] = true
	}
	return len(s)
}
```

### **...**

```

```

<!-- tabs:end -->
