# [953. 验证外星语词典](https://leetcode.cn/problems/verifying-an-alien-dictionary)

[English Version](/solution/0900-0999/0953.Verifying%20an%20Alien%20Dictionary/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>某种外星语也使用英文小写字母，但可能顺序 <code>order</code> 不同。字母表的顺序（<code>order</code>）是一些小写字母的排列。</p>

<p>给定一组用外星语书写的单词 <code>words</code>，以及其字母表的顺序 <code>order</code>，只有当给定的单词在这种外星语中按字典序排列时，返回 <code>true</code>；否则，返回 <code>false</code>。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>words = ["hello","leetcode"], order = "hlabcdefgijkmnopqrstuvwxyz"
<strong>输出：</strong>true
<strong>解释：</strong>在该语言的字母表中，'h' 位于 'l' 之前，所以单词序列是按字典序排列的。</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>words = ["word","world","row"], order = "worldabcefghijkmnpqstuvxyz"
<strong>输出：</strong>false
<strong>解释：</strong>在该语言的字母表中，'d' 位于 'l' 之后，那么 words[0] > words[1]，因此单词序列不是按字典序排列的。</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>words = ["apple","app"], order = "abcdefghijklmnopqrstuvwxyz"
<strong>输出：</strong>false
<strong>解释：</strong>当前三个字符 "app" 匹配时，第二个字符串相对短一些，然后根据词典编纂规则 "apple" > "app"，因为 'l' > '∅'，其中 '∅' 是空白字符，定义为比任何其他字符都小（<a href="https://baike.baidu.com/item/%E5%AD%97%E5%85%B8%E5%BA%8F" target="_blank">更多信息</a>）。
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= words.length <= 100</code></li>
	<li><code>1 <= words[i].length <= 20</code></li>
	<li><code>order.length == 26</code></li>
	<li>在 <code>words[i]</code> 和 <code>order</code> 中的所有字符都是英文小写字母。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

用数组或哈希表存放字母顺序。依次遍历单词列表，检测相邻两单词是否满足字典序。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def isAlienSorted(self, words: List[str], order: str) -> bool:
        m = {c: i for i, c in enumerate(order)}
        for i in range(20):
            prev = -1
            valid = True
            for x in words:
                curr = -1 if i >= len(x) else m[x[i]]
                if prev > curr:
                    return False
                if prev == curr:
                    valid = False
                prev = curr
            if valid:
                return True
        return True
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public boolean isAlienSorted(String[] words, String order) {
        int[] m = new int[26];
        for (int i = 0; i < 26; ++i) {
            m[order.charAt(i) - 'a'] = i;
        }
        for (int i = 0; i < 20; ++i) {
            int prev = -1;
            boolean valid = true;
            for (String x : words) {
                int curr = i >= x.length() ? -1 : m[x.charAt(i) - 'a'];
                if (prev > curr) {
                    return false;
                }
                if (prev == curr) {
                    valid = false;
                }
                prev = curr;
            }
            if (valid) {
                break;
            }
        }
        return true;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    bool isAlienSorted(vector<string>& words, string order) {
        vector<int> m(26);
        for (int i = 0; i < 26; ++i) m[order[i] - 'a'] = i;
        for (int i = 0; i < 20; ++i) {
            int prev = -1;
            bool valid = true;
            for (auto& x : words) {
                int curr = i >= x.size() ? -1 : m[x[i] - 'a'];
                if (prev > curr) return false;
                if (prev == curr) valid = false;
                prev = curr;
            }
            if (valid) break;
        }
        return true;
    }
};
```

### **Go**

```go
func isAlienSorted(words []string, order string) bool {
	m := make([]int, 26)
	for i, c := range order {
		m[c-'a'] = i
	}
	for i := 0; i < 20; i++ {
		prev := -1
		valid := true
		for _, x := range words {
			curr := -1
			if i < len(x) {
				curr = m[x[i]-'a']
			}
			if prev > curr {
				return false
			}
			if prev == curr {
				valid = false
			}
			prev = curr
		}
		if valid {
			break
		}
	}
	return true
}
```

### **TypeScript**

```ts
function isAlienSorted(words: string[], order: string): boolean {
    const map = new Map();
    for (const c of order) {
        map.set(c, map.size);
    }
    const n = words.length;
    for (let i = 1; i < n; i++) {
        const s1 = words[i - 1];
        const s2 = words[i];
        const m = Math.min(s1.length, s2.length);
        let isEqual = false;
        for (let j = 0; j < m; j++) {
            if (map.get(s1[j]) > map.get(s2[j])) {
                return false;
            }
            if (map.get(s1[j]) < map.get(s2[j])) {
                isEqual = true;
                break;
            }
        }
        if (!isEqual && s1.length > s2.length) {
            return false;
        }
    }
    return true;
}
```

### **Rust**

```rust
use std::collections::HashMap;
impl Solution {
    pub fn is_alien_sorted(words: Vec<String>, order: String) -> bool {
        let n = words.len();
        let mut map = HashMap::new();
        order.as_bytes().iter().enumerate().for_each(|(i, &v)| {
            map.insert(v, i);
        });
        for i in 1..n {
            let s1 = words[i - 1].as_bytes();
            let s2 = words[i].as_bytes();
            let mut is_equal = true;
            for i in 0..s1.len().min(s2.len()) {
                if map.get(&s1[i]) > map.get(&s2[i]) {
                    return false;
                }
                if map.get(&s1[i]) < map.get(&s2[i]) {
                    is_equal = false;
                    break;
                }
            }
            if is_equal && s1.len() > s2.len() {
                return false;
            }
        }
        true
    }
}
```

### **C**

```c
#define min(a, b) (((a) < (b)) ? (a) : (b))

bool isAlienSorted(char **words, int wordsSize, char *order) {
    int map[26] = {0};
    for (int i = 0; i < 26; i++) {
        map[order[i] - 'a'] = i;
    }
    for (int i = 1; i < wordsSize; i++) {
        char *s1 = words[i - 1];
        char *s2 = words[i];
        int n = strlen(s1);
        int m = strlen(s2);
        int len = min(n, m);
        int isEqual = 1;
        for (int j = 0; j < len; j++) {
            if (map[s1[j] - 'a'] > map[s2[j] - 'a']) {
                return 0;
            }
            if (map[s1[j] - 'a'] < map[s2[j] - 'a']) {
                isEqual = 0;
                break;
            }
        }
        if (isEqual && n > m) {
            return 0;
        }
    }
    return 1;
}
```

### **...**

```

```

<!-- tabs:end -->
