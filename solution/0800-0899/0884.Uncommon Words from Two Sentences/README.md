# [884. 两句话中的不常见单词](https://leetcode.cn/problems/uncommon-words-from-two-sentences)

[English Version](/solution/0800-0899/0884.Uncommon%20Words%20from%20Two%20Sentences/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p><strong>句子</strong> 是一串由空格分隔的单词。每个 <strong>单词</strong><em> </em>仅由小写字母组成。</p>

<p>如果某个单词在其中一个句子中恰好出现一次，在另一个句子中却 <strong>没有出现</strong> ，那么这个单词就是 <strong>不常见的</strong><em> </em>。</p>

<p>给你两个 <strong>句子</strong> <code>s1</code> 和 <code>s2</code> ，返回所有 <strong>不常用单词</strong> 的列表。返回列表中单词可以按 <strong>任意顺序</strong> 组织。</p>

<p>&nbsp;</p>

<ol>
</ol>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>s1 = "this apple is sweet", s2 = "this apple is sour"
<strong>输出：</strong>["sweet","sour"]
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>s1 = "apple apple", s2 = "banana"
<strong>输出：</strong>["banana"]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s1.length, s2.length &lt;= 200</code></li>
	<li><code>s1</code> 和 <code>s2</code> 由小写英文字母和空格组成</li>
	<li><code>s1</code> 和 <code>s2</code> 都不含前导或尾随空格</li>
	<li><code>s1</code> 和 <code>s2</code> 中的所有单词间均由单个空格分隔</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

使用哈希表 `HashMap<String, Integer>` 记录字符串出现的次数，完成所有记录后遍历哈希表，找到出现次数为 1 的 `key`，存入数组当中并返回。

由于只关注出现 1 次的字符串，可以将 `value` 类型定为 `Boolean`。首次记录为 `true`，后续再出现则改为 `false`。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def uncommonFromSentences(self, s1: str, s2: str) -> List[str]:
        c = Counter(s1.split()) + Counter(s2.split())
        return [w for w, n in c.items() if n == 1]
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {

    public String[] uncommonFromSentences(String s1, String s2) {
        Map<String, Integer> counter = new HashMap<>();
        add(s1, counter);
        add(s2, counter);
        List<String> ans = new ArrayList<>();
        for (Map.Entry<String, Integer> e : counter.entrySet()) {
            if (e.getValue() == 1) {
                ans.add(e.getKey());
            }
        }
        return ans.toArray(new String[0]);
    }

    private void add(String s, Map<String, Integer> counter) {
        for (String w : s.split(" ")) {
            counter.put(w, counter.getOrDefault(w, 0) + 1);
        }
    }
}

```

### **TypeScript**

```ts
function uncommonFromSentences(s1: string, s2: string): string[] {
    let hashMap: Map<string, number> = new Map();
    for (let str of [...s1.split(' '), ...s2.split(' ')]) {
        hashMap.set(str, (hashMap.get(str) || 0) + 1);
    }
    let ans: Array<string> = [];
    for (let [key, count] of hashMap.entries()) {
        if (count == 1) {
            ans.push(key);
        }
    }
    return ans;
}
```

### **C++**

```cpp
class Solution {
public:
    vector<string> uncommonFromSentences(string s1, string s2) {
        unordered_map<string, int> counter;

        auto add = [&](const string& s) {
            stringstream ss(s);
            string word;
            while (ss >> word) ++counter[move(word)];
        };

        add(s1);
        add(s2);
        vector<string> ans;
        for (auto& [word, n] : counter)
            if (n == 1)
                ans.push_back(word);
        return ans;
    }
};
```

### **Go**

```go
func uncommonFromSentences(s1 string, s2 string) []string {
	counter := make(map[string]int)
	add := func(s string) {
		for _, w := range strings.Split(s, " ") {
			counter[w]++
		}
	}
	add(s1)
	add(s2)
	var ans []string
	for word, n := range counter {
		if n == 1 {
			ans = append(ans, word)
		}
	}
	return ans
}
```

### **Rust**

```rust
use std::collections::HashMap;

impl Solution {
    pub fn uncommon_from_sentences(s1: String, s2: String) -> Vec<String> {
        let mut map = HashMap::new();
        for s in s1.split(' ') {
            map.insert(s, !map.contains_key(s));
        }
        for s in s2.split(' ') {
            map.insert(s, !map.contains_key(s));
        }
        let mut res = Vec::new();
        for (k, v) in map {
            if v {
                res.push(String::from(k))
            }
        }
        res
    }
}
```

### **...**

```

```

<!-- tabs:end -->
