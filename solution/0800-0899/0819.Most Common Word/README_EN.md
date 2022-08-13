# [819. Most Common Word](https://leetcode.com/problems/most-common-word)

[中文文档](/solution/0800-0899/0819.Most%20Common%20Word/README.md)

## Description

<p>Given a string <code>paragraph</code> and a string array of the banned words <code>banned</code>, return <em>the most frequent word that is not banned</em>. It is <strong>guaranteed</strong> there is <strong>at least one word</strong> that is not banned, and that the answer is <strong>unique</strong>.</p>

<p>The words in <code>paragraph</code> are <strong>case-insensitive</strong> and the answer should be returned in <strong>lowercase</strong>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> paragraph = &quot;Bob hit a ball, the hit BALL flew far after it was hit.&quot;, banned = [&quot;hit&quot;]
<strong>Output:</strong> &quot;ball&quot;
<strong>Explanation:</strong> 
&quot;hit&quot; occurs 3 times, but it is a banned word.
&quot;ball&quot; occurs twice (and no other word does), so it is the most frequent non-banned word in the paragraph. 
Note that words in the paragraph are not case sensitive,
that punctuation is ignored (even if adjacent to words, such as &quot;ball,&quot;), 
and that &quot;hit&quot; isn&#39;t the answer even though it occurs more because it is banned.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> paragraph = &quot;a.&quot;, banned = []
<strong>Output:</strong> &quot;a&quot;
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= paragraph.length &lt;= 1000</code></li>
	<li>paragraph consists of English letters, space <code>&#39; &#39;</code>, or one of the symbols: <code>&quot;!?&#39;,;.&quot;</code>.</li>
	<li><code>0 &lt;= banned.length &lt;= 100</code></li>
	<li><code>1 &lt;= banned[i].length &lt;= 10</code></li>
	<li><code>banned[i]</code> consists of only lowercase English letters.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def mostCommonWord(self, paragraph: str, banned: List[str]) -> str:
        s = set(banned)
        p = Counter(re.findall('[a-z]+', paragraph.lower()))
        return next(word for word, _ in p.most_common() if word not in s)
```

### **Java**

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

### **...**

```

```

<!-- tabs:end -->
