# [819. 最常见的单词](https://leetcode.cn/problems/most-common-word)

[English Version](/solution/0800-0899/0819.Most%20Common%20Word/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个段落 (paragraph) 和一个禁用单词列表 (banned)。返回出现次数最多，同时不在禁用列表中的单词。</p>

<p>题目保证至少有一个词不在禁用列表中，而且答案唯一。</p>

<p>禁用列表中的单词用小写字母表示，不含标点符号。段落中的单词不区分大小写。答案都是小写字母。</p>

<p>&nbsp;</p>

<p><strong>示例：</strong></p>

<pre><strong>输入:</strong> 
paragraph = &quot;Bob hit a ball, the hit BALL flew far after it was hit.&quot;
banned = [&quot;hit&quot;]
<strong>输出:</strong> &quot;ball&quot;
<strong>解释:</strong> 
&quot;hit&quot; 出现了3次，但它是一个禁用的单词。
&quot;ball&quot; 出现了2次 (同时没有其他单词出现2次)，所以它是段落里出现次数最多的，且不在禁用列表中的单词。 
注意，所有这些单词在段落里不区分大小写，标点符号需要忽略（即使是紧挨着单词也忽略， 比如 &quot;ball,&quot;）， 
&quot;hit&quot;不是最终的答案，虽然它出现次数更多，但它在禁用单词列表中。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= 段落长度 &lt;= 1000</code></li>
	<li><code>0 &lt;= 禁用单词个数 &lt;= 100</code></li>
	<li><code>1 &lt;= 禁用单词长度 &lt;= 10</code></li>
	<li>答案是唯一的, 且都是小写字母&nbsp;(即使在 <code>paragraph</code> 里是大写的，即使是一些特定的名词，答案都是小写的。)</li>
	<li><code>paragraph</code>&nbsp;只包含字母、空格和下列标点符号<code>!?&#39;,;.</code></li>
	<li>不存在没有连字符或者带有连字符的单词。</li>
	<li>单词里只包含字母，不会出现省略号或者其他标点符号。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：正则匹配/双指针 + 哈希表**

正则匹配（或双指针）找出所有单词，用哈希表统计每个单词出现的频率，找到出现未在 banned 中出现且频率最大的单词。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def mostCommonWord(self, paragraph: str, banned: List[str]) -> str:
        s = set(banned)
        p = Counter(re.findall('[a-z]+', paragraph.lower()))
        return next(word for word, _ in p.most_common() if word not in s)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Solution {
    private static Pattern pattern = Pattern.compile("[a-z]+");

    public String mostCommonWord(String paragraph, String[] banned) {
        Set<String> bannedWords = new HashSet<>();
        for (String word : banned) {
            bannedWords.add(word);
        }
        Map<String, Integer> counter = new HashMap<>();
        Matcher matcher = pattern.matcher(paragraph.toLowerCase());
        while (matcher.find()) {
            String word = matcher.group();
            if (bannedWords.contains(word)) {
                continue;
            }
            counter.put(word, counter.getOrDefault(word, 0) + 1);
        }
        int max = Integer.MIN_VALUE;
        String ans = null;
        for (Map.Entry<String, Integer> entry : counter.entrySet()) {
            if (entry.getValue() > max) {
                max = entry.getValue();
                ans = entry.getKey();
            }
        }
        return ans;
    }
}
```

### **TypeScript**

```ts
function mostCommonWord(paragraph: string, banned: string[]): string {
    const s = paragraph.toLocaleLowerCase();
    const map = new Map<string, number>();
    const set = new Set<string>(banned);
    for (const word of s.split(/[^A-z]/)) {
        if (word === '' || set.has(word)) {
            continue;
        }
        map.set(word, (map.get(word) ?? 0) + 1);
    }
    return [...map.entries()].reduce(
        (r, v) => (v[1] > r[1] ? v : r),
        ['', 0],
    )[0];
}
```

### **Rust**

```rust
use std::collections::{HashMap, HashSet};
impl Solution {
    pub fn most_common_word(mut paragraph: String, banned: Vec<String>) -> String {
        paragraph.make_ascii_lowercase();
        let banned: HashSet<&str> = banned.iter().map(String::as_str).collect();
        let mut map = HashMap::new();
        for word in paragraph.split(|c| !matches!(c, 'a'..='z')) {
            if word.is_empty() || banned.contains(word) {
                continue;
            }
            let val = map.get(&word).unwrap_or(&0) + 1;
            map.insert(word, val);
        }
        map.into_iter()
            .max_by_key(|&(_, v)| v)
            .unwrap()
            .0
            .to_string()
    }
}
```

### **C++**

```cpp
class Solution {
public:
    string mostCommonWord(string paragraph, vector<string>& banned) {
        unordered_set<string> s(banned.begin(), banned.end());
        unordered_map<string, int> counter;
        string ans;
        for (int i = 0, mx = 0, n = paragraph.size(); i < n;) {
            if (!isalpha(paragraph[i]) && (++i > 0)) continue;
            int j = i;
            string word;
            while (j < n && isalpha(paragraph[j])) {
                word.push_back(tolower(paragraph[j]));
                ++j;
            }
            i = j + 1;
            if (s.count(word)) continue;
            ++counter[word];
            if (counter[word] > mx) {
                ans = word;
                mx = counter[word];
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func mostCommonWord(paragraph string, banned []string) string {
	s := make(map[string]bool)
	for _, w := range banned {
		s[w] = true
	}
	counter := make(map[string]int)
	var ans string
	for i, mx, n := 0, 0, len(paragraph); i < n; {
		if !unicode.IsLetter(rune(paragraph[i])) {
			i++
			continue
		}
		j := i
		var word []byte
		for j < n && unicode.IsLetter(rune(paragraph[j])) {
			word = append(word, byte(unicode.ToLower(rune(paragraph[j]))))
			j++
		}
		i = j + 1
		t := string(word)
		if s[t] {
			continue
		}
		counter[t]++
		if counter[t] > mx {
			ans = t
			mx = counter[t]
		}
	}
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
