# [2047. Number of Valid Words in a Sentence](https://leetcode.com/problems/number-of-valid-words-in-a-sentence)

[中文文档](/solution/2000-2099/2047.Number%20of%20Valid%20Words%20in%20a%20Sentence/README.md)

## Description

<p>A sentence consists of lowercase letters (<code>&#39;a&#39;</code> to <code>&#39;z&#39;</code>), digits (<code>&#39;0&#39;</code> to <code>&#39;9&#39;</code>), hyphens (<code>&#39;-&#39;</code>), punctuation marks (<code>&#39;!&#39;</code>, <code>&#39;.&#39;</code>, and <code>&#39;,&#39;</code>), and spaces (<code>&#39; &#39;</code>) only. Each sentence can be broken down into <strong>one or more tokens</strong> separated by one or more spaces <code>&#39; &#39;</code>.</p>

<p>A token is a valid word if <strong>all three</strong> of the following are true:</p>

<ul>
	<li>It only contains lowercase letters, hyphens, and/or punctuation (<strong>no</strong> digits).</li>
	<li>There is <strong>at most one</strong> hyphen <code>&#39;-&#39;</code>. If present, it <strong>must</strong> be surrounded by lowercase characters (<code>&quot;a-b&quot;</code> is valid, but <code>&quot;-ab&quot;</code> and <code>&quot;ab-&quot;</code> are not valid).</li>
	<li>There is <strong>at most one</strong> punctuation mark. If present, it <strong>must</strong> be at the <strong>end</strong> of the token (<code>&quot;ab,&quot;</code>, <code>&quot;cd!&quot;</code>, and <code>&quot;.&quot;</code> are valid, but <code>&quot;a!b&quot;</code> and <code>&quot;c.,&quot;</code> are not valid).</li>
</ul>

<p>Examples of valid words include <code>&quot;a-b.&quot;</code>, <code>&quot;afad&quot;</code>, <code>&quot;ba-c&quot;</code>, <code>&quot;a!&quot;</code>, and <code>&quot;!&quot;</code>.</p>

<p>Given a string <code>sentence</code>, return <em>the <strong>number</strong> of valid words in </em><code>sentence</code>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> sentence = &quot;<u>cat</u> <u>and</u>  <u>dog</u>&quot;
<strong>Output:</strong> 3
<strong>Explanation:</strong> The valid words in the sentence are &quot;cat&quot;, &quot;and&quot;, and &quot;dog&quot;.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> sentence = &quot;!this  1-s b8d!&quot;
<strong>Output:</strong> 0
<strong>Explanation:</strong> There are no valid words in the sentence.
&quot;!this&quot; is invalid because it starts with a punctuation mark.
&quot;1-s&quot; and &quot;b8d&quot; are invalid because they contain digits.
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> sentence = &quot;<u>alice</u> <u>and</u>  <u>bob</u> <u>are</u> <u>playing</u> stone-game10&quot;
<strong>Output:</strong> 5
<strong>Explanation:</strong> The valid words in the sentence are &quot;alice&quot;, &quot;and&quot;, &quot;bob&quot;, &quot;are&quot;, and &quot;playing&quot;.
&quot;stone-game10&quot; is invalid because it contains digits.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= sentence.length &lt;= 1000</code></li>
	<li><code>sentence</code> only contains lowercase English letters, digits, <code>&#39; &#39;</code>, <code>&#39;-&#39;</code>, <code>&#39;!&#39;</code>, <code>&#39;.&#39;</code>, and <code>&#39;,&#39;</code>.</li>
	<li>There will be at least&nbsp;<code>1</code> token.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def countValidWords(self, sentence: str) -> int:
        def check(token):
            hyphen = False
            for i, c in enumerate(token):
                if c.isdigit() or (c in '!.,' and i < len(token) - 1):
                    return False
                if c == '-':
                    if (
                        hyphen
                        or i == 0
                        or i == len(token) - 1
                        or not token[i - 1].islower()
                        or not token[i + 1].islower()
                    ):
                        return False
                    hyphen = True
            return True

        return sum(check(token) for token in sentence.split())
```

### **Java**

```java
class Solution {
    public int countValidWords(String sentence) {
        int ans = 0;
        for (String token : sentence.split(" ")) {
            if (check(token)) {
                ++ans;
            }
        }
        return ans;
    }

    private boolean check(String token) {
        int n = token.length();
        if (n == 0) {
            return false;
        }
        boolean hyphen = false;
        for (int i = 0; i < n; ++i) {
            char c = token.charAt(i);
            if (Character.isDigit(c) || (i < n - 1 && (c == '!' || c == '.' || c == ','))) {
                return false;
            }
            if (c == '-') {
                if (hyphen || i == 0 || i == n - 1 || !Character.isLetter(token.charAt(i - 1)) || !Character.isLetter(token.charAt(i + 1))) {
                    return false;
                }
                hyphen = true;
            }
        }
        return true;
    }
}
```

### **TypeScript**

```ts
function countValidWords(sentence: string): number {
    let words = sentence.trim().split(/\s+/);
    let ans = 0;
    for (let word of words) {
        if (isValied(word)) {
            ans++;
        }
    }
    return ans;
}

function isValied(str: string): boolean {
    let n = str.length;
    let hasLine = false;
    for (let i = 0; i < n; i++) {
        const char = str.charAt(i);
        if (/^[0-9]$/.test(char)) {
            return false;
        }
        if (char == '-') {
            if (hasLine) return false;
            else {
                hasLine = true;
            }
            let pre = str.charAt(i - 1),
                post = str.charAt(i + 1);
            if (!/^[a-z]$/g.test(pre) || !/^[a-z]$/g.test(post)) {
                return false;
            }
        }
        if (/^[\!\.\,\s]$/.test(char) && i != n - 1) {
            return false;
        }
    }
    return true;
}
```

### **...**

```

```

<!-- tabs:end -->
