# [804. 唯一摩尔斯密码词](https://leetcode-cn.com/problems/unique-morse-code-words)

[English Version](/solution/0800-0899/0804.Unique%20Morse%20Code%20Words/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>国际摩尔斯密码定义一种标准编码方式，将每个字母对应于一个由一系列点和短线组成的字符串，&nbsp;比如: <code>&quot;a&quot;</code> 对应 <code>&quot;.-&quot;</code>, <code>&quot;b&quot;</code> 对应 <code>&quot;-...&quot;</code>, <code>&quot;c&quot;</code> 对应 <code>&quot;-.-.&quot;</code>, 等等。</p>

<p>为了方便，所有26个英文字母对应摩尔斯密码表如下：</p>

<pre>[&quot;.-&quot;,&quot;-...&quot;,&quot;-.-.&quot;,&quot;-..&quot;,&quot;.&quot;,&quot;..-.&quot;,&quot;--.&quot;,&quot;....&quot;,&quot;..&quot;,&quot;.---&quot;,&quot;-.-&quot;,&quot;.-..&quot;,&quot;--&quot;,&quot;-.&quot;,&quot;---&quot;,&quot;.--.&quot;,&quot;--.-&quot;,&quot;.-.&quot;,&quot;...&quot;,&quot;-&quot;,&quot;..-&quot;,&quot;...-&quot;,&quot;.--&quot;,&quot;-..-&quot;,&quot;-.--&quot;,&quot;--..&quot;]</pre>

<p>给定一个单词列表，每个单词可以写成每个字母对应摩尔斯密码的组合。例如，&quot;cab&quot; 可以写成 &quot;-.-..--...&quot;，(即 &quot;-.-.&quot; + &quot;.-&quot; + &quot;-...&quot; 字符串的结合)。我们将这样一个连接过程称作单词翻译。</p>

<p>返回我们可以获得所有词不同单词翻译的数量。</p>

<pre><strong>例如:</strong>
<strong>输入:</strong> words = [&quot;gin&quot;, &quot;zen&quot;, &quot;gig&quot;, &quot;msg&quot;]
<strong>输出:</strong> 2
<strong>解释: </strong>
各单词翻译如下:
&quot;gin&quot; -&gt; &quot;--...-.&quot;
&quot;zen&quot; -&gt; &quot;--...-.&quot;
&quot;gig&quot; -&gt; &quot;--...--.&quot;
&quot;msg&quot; -&gt; &quot;--...--.&quot;

共有 2 种不同翻译, &quot;--...-.&quot; 和 &quot;--...--.&quot;.
</pre>

<p>&nbsp;</p>

<p><strong>注意:</strong></p>

<ul>
	<li>单词列表<code>words</code>&nbsp;的长度不会超过 <code>100</code>。</li>
	<li>每个单词&nbsp;<code>words[i]</code>的长度范围为&nbsp;<code>[1, 12]</code>。</li>
	<li>每个单词&nbsp;<code>words[i]</code>只包含小写字母。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

哈希表实现。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
