# [804. Unique Morse Code Words](https://leetcode.com/problems/unique-morse-code-words)

[中文文档](/solution/0800-0899/0804.Unique%20Morse%20Code%20Words/README.md)

## Description

<p>International Morse Code defines a standard encoding where each letter is mapped to a series of dots and dashes, as follows: <code>&quot;a&quot;</code> maps to <code>&quot;.-&quot;</code>, <code>&quot;b&quot;</code> maps to <code>&quot;-...&quot;</code>, <code>&quot;c&quot;</code> maps to <code>&quot;-.-.&quot;</code>, and so on.</p>

<p>For convenience, the full table for the 26 letters of the English alphabet is given below:</p>

<pre>
[&quot;.-&quot;,&quot;-...&quot;,&quot;-.-.&quot;,&quot;-..&quot;,&quot;.&quot;,&quot;..-.&quot;,&quot;--.&quot;,&quot;....&quot;,&quot;..&quot;,&quot;.---&quot;,&quot;-.-&quot;,&quot;.-..&quot;,&quot;--&quot;,&quot;-.&quot;,&quot;---&quot;,&quot;.--.&quot;,&quot;--.-&quot;,&quot;.-.&quot;,&quot;...&quot;,&quot;-&quot;,&quot;..-&quot;,&quot;...-&quot;,&quot;.--&quot;,&quot;-..-&quot;,&quot;-.--&quot;,&quot;--..&quot;]</pre>

<p>Now, given a list of words, each word can be written as a concatenation of the Morse code of each letter. For example, &quot;cab&quot; can be written as &quot;-.-..--...&quot;, (which is the concatenation &quot;-.-.&quot; + &quot;.-&quot; + &quot;<code>-...</code>&quot;). We&#39;ll call such a concatenation, the transformation&nbsp;of a word.</p>

<p>Return the number of different transformations among all words we have.</p>

<pre>
<strong>Example:</strong>
<strong>Input:</strong> words = [&quot;gin&quot;, &quot;zen&quot;, &quot;gig&quot;, &quot;msg&quot;]
<strong>Output:</strong> 2
<strong>Explanation: </strong>
The transformation of each word is:
&quot;gin&quot; -&gt; &quot;--...-.&quot;
&quot;zen&quot; -&gt; &quot;--...-.&quot;
&quot;gig&quot; -&gt; &quot;--...--.&quot;
&quot;msg&quot; -&gt; &quot;--...--.&quot;

There are 2 different transformations, &quot;--...-.&quot; and &quot;--...--.&quot;.
</pre>

<p><strong>Note:</strong></p>

<ul>
	<li>The length of <code>words</code> will be at most <code>100</code>.</li>
	<li>Each <code>words[i]</code> will have length in range <code>[1, 12]</code>.</li>
	<li><code>words[i]</code> will only consist of lowercase letters.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def uniqueMorseRepresentations(self, words: List[str]) -> int:
        codes = [".-","-...","-.-.","-..",".","..-.","--.","....","..",".---","-.-",".-..","--","-.","---",".--.","--.-",".-.","...","-","..-","...-",".--","-..-","-.--","--.."]
        s = set()
        for word in words:
            t = []
            for c in word:
                t.append(codes[ord(c) - ord('a')])
            s.add(''.join(t))
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

### **...**

```

```

<!-- tabs:end -->
