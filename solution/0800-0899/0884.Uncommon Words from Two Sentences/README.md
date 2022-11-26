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

**方法一：哈希表**

根据题目描述，只要单词出现一次，就符合题目要求。因此，我们用哈希表 $cnt$ 记录所有单词以及出现的次数。

然后遍历哈希表，取出所有出现次数为 $1$ 的字符串即可。

时间复杂度 $O(m + n)$，空间复杂度 $O(m + n)$。其中 $m$ 和 $n$ 分别是字符串 $s1$ 和 $s2$ 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def uncommonFromSentences(self, s1: str, s2: str) -> List[str]:
        cnt = Counter(s1.split()) + Counter(s2.split())
        return [s for s, v in cnt.items() if v == 1]
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public String[] uncommonFromSentences(String s1, String s2) {
        Map<String, Integer> cnt = new HashMap<>();
        for (String s : s1.split(" ")) {
            cnt.put(s, cnt.getOrDefault(s, 0) + 1);
        }
        for (String s : s2.split(" ")) {
            cnt.put(s, cnt.getOrDefault(s, 0) + 1);
        }
        List<String> ans = new ArrayList<>();
        for (var e : cnt.entrySet()) {
            if (e.getValue() == 1) {
                ans.add(e.getKey());
            }
        }
        return ans.toArray(new String[0]);
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<string> uncommonFromSentences(string s1, string s2) {
        unordered_map<string, int> cnt;
        auto add = [&](string& s) {
            stringstream ss(s);
            string w;
            while (ss >> w) ++cnt[move(w)];
        };
        add(s1);
        add(s2);
        vector<string> ans;
        for (auto& [s, v] : cnt) if (v == 1) ans.emplace_back(s);
        return ans;
    }
};
```

### **Go**

```go
func uncommonFromSentences(s1 string, s2 string) (ans []string) {
    cnt := map[string]int{}
    for _, s := range strings.Split(s1, " ") {
        cnt[s]++
    }
    for _, s := range strings.Split(s2, " ") {
        cnt[s]++
    }
    for s, v := range cnt {
        if v == 1 {
            ans = append(ans, s)
        }
    }
    return
}
```

### **TypeScript**

```ts
function uncommonFromSentences(s1: string, s2: string): string[] {
    const cnt: Map<string, number> = new Map();
    for (const s of [...s1.split(' '), ...s2.split(' ')]) {
        cnt.set(s, (cnt.get(s) || 0) + 1);
    }
    const ans: Array<string> = [];
    for (const [s, v] of cnt.entries()) {
        if (v == 1) {
            ans.push(s);
        }
    }
    return ans;
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

### **JavaScript**

```js
/**
 * @param {string} s1
 * @param {string} s2
 * @return {string[]}
 */
var uncommonFromSentences = function (s1, s2) {
    const cnt = new Map();
    for (const s of [...s1.split(' '), ...s2.split(' ')]) {
        cnt.set(s, (cnt.get(s) || 0) + 1);
    }
    const ans = [];
    for (const [s, v] of cnt.entries()) {
        if (v == 1) {
            ans.push(s);
        }
    }
    return ans;
};
```

### **...**

```

```

<!-- tabs:end -->
