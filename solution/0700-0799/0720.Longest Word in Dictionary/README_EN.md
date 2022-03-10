# [720. Longest Word in Dictionary](https://leetcode.com/problems/longest-word-in-dictionary)

[中文文档](/solution/0700-0799/0720.Longest%20Word%20in%20Dictionary/README.md)

## Description

<p>Given an array of strings <code>words</code> representing an English Dictionary, return <em>the longest word in</em> <code>words</code> <em>that can be built one character at a time by other words in</em> <code>words</code>.</p>

<p>If there is more than one possible answer, return the longest word with the smallest lexicographical order. If there is no answer, return the empty string.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> words = [&quot;w&quot;,&quot;wo&quot;,&quot;wor&quot;,&quot;worl&quot;,&quot;world&quot;]
<strong>Output:</strong> &quot;world&quot;
<strong>Explanation:</strong> The word &quot;world&quot; can be built one character at a time by &quot;w&quot;, &quot;wo&quot;, &quot;wor&quot;, and &quot;worl&quot;.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> words = [&quot;a&quot;,&quot;banana&quot;,&quot;app&quot;,&quot;appl&quot;,&quot;ap&quot;,&quot;apply&quot;,&quot;apple&quot;]
<strong>Output:</strong> &quot;apple&quot;
<strong>Explanation:</strong> Both &quot;apply&quot; and &quot;apple&quot; can be built from other words in the dictionary. However, &quot;apple&quot; is lexicographically smaller than &quot;apply&quot;.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= words.length &lt;= 1000</code></li>
	<li><code>1 &lt;= words[i].length &lt;= 30</code></li>
	<li><code>words[i]</code> consists of lowercase English letters.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python

```

### **Java**

```java

```

### **TypeScript**

```ts
function longestWord(words: string[]): string {
    words.sort((a, b) => {
        const n = a.length;
        const m = b.length;
        if (n === m) {
            return a < b ? -1 : 1;
        }
        return m - n;
    });
    for (const word of words) {
        let isPass = true;
        for (let i = 1; i <= word.length; i++) {
            if (!words.includes(word.slice(0, i))) {
                isPass = false;
                break;
            }
        }
        if (isPass) {
            return word;
        }
    }
    return '';
}
```

### **Rust**

```rust
impl Solution {
    pub fn longest_word(mut words: Vec<String>) -> String {
        words.sort_unstable_by(|a, b| (b.len(), a).cmp(&(a.len(), b)));
        for word in words.iter() {
            let mut is_pass = true;
            for i in 1..=word.len() {
                if !words.contains(&word[..i].to_string()) {
                    is_pass = false;
                    break;
                }
            }
            if is_pass {
                return word.clone();
            }
        }
        String::new()
    }
}
```

### **...**

```

```

<!-- tabs:end -->
