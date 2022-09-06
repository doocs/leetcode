# [824. 山羊拉丁文](https://leetcode.cn/problems/goat-latin)

[English Version](/solution/0800-0899/0824.Goat%20Latin/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个由若干单词组成的句子&nbsp;<code>sentence</code> ，单词间由空格分隔。每个单词仅由大写和小写英文字母组成。</p>

<p>请你将句子转换为 <em>“</em>山羊拉丁文（<em>Goat Latin</em>）<em>”</em>（一种类似于 猪拉丁文&nbsp;- Pig Latin 的虚构语言）。山羊拉丁文的规则如下：</p>

<ul>
	<li>如果单词以元音开头（<code>'a'</code>, <code>'e'</code>, <code>'i'</code>, <code>'o'</code>, <code>'u'</code>），在单词后添加<code>"ma"</code>。
    <ul>
    	<li>例如，单词 <code>"apple"</code> 变为 <code>"applema"</code> 。</li>
    </ul>
    </li>
    <li>如果单词以辅音字母开头（即，非元音字母），移除第一个字符并将它放到末尾，之后再添加<code>"ma"</code>。
    <ul>
    	<li>例如，单词 <code>"goat"</code> 变为 <code>"oatgma"</code> 。</li>
    </ul>
    </li>
    <li>根据单词在句子中的索引，在单词最后添加与索引相同数量的字母<code>'a'</code>，索引从 <code>1</code> 开始。
    <ul>
    	<li>例如，在第一个单词后添加 <code>"a"</code> ，在第二个单词后添加 <code>"aa"</code> ，以此类推。</li>
    </ul>
    </li>
</ul>

<p>返回将 <code>sentence</code> 转换为山羊拉丁文后的句子。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>sentence = "I speak Goat Latin"
<strong>输出：</strong>"Imaa peaksmaaa oatGmaaaa atinLmaaaaa"
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>sentence = "The quick brown fox jumped over the lazy dog"
<strong>输出：</strong>"heTmaa uickqmaaa rownbmaaaa oxfmaaaaa umpedjmaaaaaa overmaaaaaaa hetmaaaaaaaa azylmaaaaaaaaa ogdmaaaaaaaaaa"
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= sentence.length &lt;= 150</code></li>
	<li><code>sentence</code> 由英文字母和空格组成</li>
	<li><code>sentence</code> 不含前导或尾随空格</li>
	<li><code>sentence</code> 中的所有单词由单个空格分隔</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def toGoatLatin(self, sentence: str) -> str:
        ans = []
        for i, word in enumerate(sentence.split()):
            if word.lower()[0] not in ['a', 'e', 'i', 'o', 'u']:
                word = word[1:] + word[0]
            word += 'ma'
            word += 'a' * (i + 1)
            ans.append(word)
        return ' '.join(ans)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public String toGoatLatin(String sentence) {
        List<String> ans = new ArrayList<>();
        Set<Character> vowels
            = new HashSet<>(Arrays.asList('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'));
        int i = 1;
        for (String word : sentence.split(" ")) {
            StringBuilder t = new StringBuilder();
            if (!vowels.contains(word.charAt(0))) {
                t.append(word.substring(1));
                t.append(word.charAt(0));
            } else {
                t.append(word);
            }
            t.append("ma");
            for (int j = 0; j < i; ++j) {
                t.append("a");
            }
            ++i;
            ans.add(t.toString());
        }
        return String.join(" ", ans);
    }
}
```

### **TypeScript**

```ts
function toGoatLatin(sentence: string): string {
    return sentence
        .split(' ')
        .map((s, i) => {
            let startStr: string;
            if (/[aeiou]/i.test(s[0])) {
                startStr = s;
            } else {
                startStr = s.slice(1) + s[0];
            }
            return `${startStr}ma${'a'.repeat(i + 1)}`;
        })
        .join(' ');
}
```

### **Rust**

```rust
use std::collections::HashSet;
impl Solution {
    pub fn to_goat_latin(sentence: String) -> String {
        let set: HashSet<&char> = ['a', 'e', 'i', 'o', 'u'].into_iter().collect();
        sentence
            .split_whitespace()
            .enumerate()
            .map(|(i, s)| {
                let first = char::from(s.as_bytes()[0]);
                let mut res = if set.contains(&first.to_ascii_lowercase()) {
                    s.to_string()
                } else {
                    s[1..].to_string() + &first.to_string()
                };
                res.push_str("ma");
                res.push_str(&"a".repeat(i + 1));
                res
            })
            .into_iter()
            .collect::<Vec<String>>()
            .join(" ")
    }
}
```

### **...**

```

```

<!-- tabs:end -->
