# [804. 唯一摩尔斯密码词](https://leetcode.cn/problems/unique-morse-code-words)

[English Version](/solution/0800-0899/0804.Unique%20Morse%20Code%20Words/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>国际摩尔斯密码定义一种标准编码方式，将每个字母对应于一个由一系列点和短线组成的字符串，&nbsp;比如:</p>

<ul>
	<li><code>'a'</code> 对应 <code>".-"</code> ，</li>
	<li><code>'b'</code> 对应 <code>"-..."</code> ，</li>
	<li><code>'c'</code> 对应 <code>"-.-."</code> ，以此类推。</li>
</ul>

<p>为了方便，所有 <code>26</code> 个英文字母的摩尔斯密码表如下：</p>

<pre>
[".-","-...","-.-.","-..",".","..-.","--.","....","..",".---","-.-",".-..","--","-.","---",".--.","--.-",".-.","...","-","..-","...-",".--","-..-","-.--","--.."]</pre>

<p>给你一个字符串数组 <code>words</code> ，每个单词可以写成每个字母对应摩尔斯密码的组合。</p>

<ul>
	<li>例如，<code>"cab"</code> 可以写成 <code>"-.-..--..."</code> ，(即 <code>"-.-."</code> + <code>".-"</code> + <code>"-..."</code> 字符串的结合)。我们将这样一个连接过程称作 <strong>单词翻译</strong> 。</li>
</ul>

<p>对<strong> </strong><code>words</code> 中所有单词进行单词翻译，返回不同 <strong>单词翻译</strong> 的数量。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入:</strong> words = ["gin", "zen", "gig", "msg"]
<strong>输出:</strong> 2
<strong>解释: </strong>
各单词翻译如下:
"gin" -&gt; "--...-."
"zen" -&gt; "--...-."
"gig" -&gt; "--...--."
"msg" -&gt; "--...--."

共有 2 种不同翻译, "--...-." 和 "--...--.".
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>words = ["a"]
<strong>输出：</strong>1
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= words.length &lt;= 100</code></li>
	<li><code>1 &lt;= words[i].length &lt;= 12</code></li>
	<li><code>words[i]</code> 由小写英文字母组成</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：哈希表**

将 words 所有单词翻译成对应的摩尔斯密码，加入到哈希表中，最后返回哈希表的 size。

时间复杂度 $O(n)$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int uniqueMorseRepresentations(String[] words) {
        String[] codes = new String[] {".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....",
            "..", ".---", "-.-", ".-..", "--", "-.", "---", ".--.", "--.-", ".-.", "...", "-",
            "..-", "...-", ".--", "-..-", "-.--", "--.."};
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
