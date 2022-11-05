# [2451. 差值数组不同的字符串](https://leetcode.cn/problems/odd-string-difference)

[English Version](/solution/2400-2499/2451.Odd%20String%20Difference/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个字符串数组 <code>words</code>&nbsp;，每一个字符串长度都相同，令所有字符串的长度都为 <code>n</code>&nbsp;。</p>

<p>每个字符串&nbsp;<code>words[i]</code>&nbsp;可以被转化为一个长度为&nbsp;<code>n - 1</code>&nbsp;的&nbsp;<strong>差值整数数组</strong>&nbsp;<code>difference[i]</code>&nbsp;，其中对于&nbsp;<code>0 &lt;= j &lt;= n - 2</code>&nbsp;有&nbsp;<code>difference[i][j] = words[i][j+1] - words[i][j]</code>&nbsp;。注意两个字母的差值定义为它们在字母表中&nbsp;<strong>位置</strong>&nbsp;之差，也就是说&nbsp;<code>'a'</code>&nbsp;的位置是&nbsp;<code>0</code>&nbsp;，<code>'b'</code>&nbsp;的位置是&nbsp;<code>1</code>&nbsp;，<code>'z'</code>&nbsp;的位置是&nbsp;<code>25</code>&nbsp;。</p>

<ul>
	<li>比方说，字符串&nbsp;<code>"acb"</code>&nbsp;的差值整数数组是&nbsp;<code>[2 - 0, 1 - 2] = [2, -1]</code>&nbsp;。</li>
</ul>

<p><code>words</code>&nbsp;中所有字符串 <strong>除了一个字符串以外</strong>&nbsp;，其他字符串的差值整数数组都相同。你需要找到那个不同的字符串。</p>

<p>请你返回<em>&nbsp;</em><code>words</code>中&nbsp;<strong>差值整数数组</strong>&nbsp;不同的字符串。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<b>输入：</b>words = ["adc","wzy","abc"]
<b>输出：</b>"abc"
<b>解释：</b>
- "adc" 的差值整数数组是 [3 - 0, 2 - 3] = [3, -1] 。
- "wzy" 的差值整数数组是 [25 - 22, 24 - 25]= [3, -1] 。
- "abc" 的差值整数数组是 [1 - 0, 2 - 1] = [1, 1] 。
不同的数组是 [1, 1]，所以返回对应的字符串，"abc"。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<b>输入：</b>words = ["aaa","bob","ccc","ddd"]
<b>输出：</b>"bob"
<b>解释：</b>除了 "bob" 的差值整数数组是 [13, -13] 以外，其他字符串的差值整数数组都是 [0, 0] 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>3 &lt;= words.length &lt;= 100</code></li>
	<li><code>n == words[i].length</code></li>
	<li><code>2 &lt;= n &lt;= 20</code></li>
	<li><code>words[i]</code>&nbsp;只含有小写英文字母。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：哈希表模拟**

我们用哈希表 `cnt` 存放每个差值数组以及其对应的字符串列表，然后遍历每个字符串列表，找到其中长度为 $1$ 的列表，返回对应的元素即可。

时间复杂度 $O(m\times n)$，其中 $m$ 和 $n$ 分别为字符串数组 `words` 的长度和字符串的平均长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def oddString(self, words: List[str]) -> str:
        cnt = defaultdict(list)
        for w in words:
            d = [str(ord(b) - ord(a)) for a, b in pairwise(w)]
            cnt[','.join(d)].append(w)
        return next(v[0] for v in cnt.values() if len(v) == 1)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public String oddString(String[] words) {
        Map<String, List<String>> cnt = new HashMap<>();
        for (var w : words) {
            List<String> d = new ArrayList<>();
            for (int i = 0; i < w.length() - 1; ++i) {
                d.add(String.valueOf(w.charAt(i + 1) - w.charAt(i)));
            }
            cnt.computeIfAbsent(String.join(",", d), k -> new ArrayList<>()).add(w);
        }
        for (var v : cnt.values()) {
            if (v.size() == 1) {
                return v.get(0);
            }
        }
        return "";
    }
}
```

### **C++**

```cpp
class Solution {
public:
    string oddString(vector<string>& words) {
        unordered_map<string, vector<string>> cnt;
        for (auto& w : words) {
            string d;
            for (int i = 0; i < w.size() - 1; ++i) {
                d += (char) (w[i + 1] - w[i]);
                d += ',';
            }
            cnt[d].emplace_back(w);
        }
        for (auto& [_, v] : cnt) {
            if (v.size() == 1) {
                return v[0];
            }
        }
        return "";
    }
};
```

### **Go**

```go
func oddString(words []string) string {
	cnt := map[string][]string{}
	for _, w := range words {
		d := make([]byte, len(w)-1)
		for i := 0; i < len(w)-1; i++ {
			d[i] = w[i+1] - w[i]
		}
		t := string(d)
		cnt[t] = append(cnt[t], w)
	}
	for _, v := range cnt {
		if len(v) == 1 {
			return v[0]
		}
	}
	return ""
}
```

### **TypeScript**

```ts
function oddString(words: string[]): string {
    const n = words[0].length;
    const map = new Map<string, [boolean, number]>();
    words.forEach((word, i) => {
        const diff: number[] = [];
        for (let j = 1; j < n; j++) {
            diff.push(word[j].charCodeAt(0) - word[j - 1].charCodeAt(0));
        }
        const k = diff.join();
        map.set(k, [!map.has(k), i]);
    });
    for (const [isOnly, i] of map.values()) {
        if (isOnly) {
            return words[i];
        }
    }
    return '';
}
```

### **Rust**

```rust
use std::collections::HashMap;
impl Solution {
    pub fn odd_string(words: Vec<String>) -> String {
        let n = words[0].len();
        let mut map: HashMap<String, (bool, usize)> = HashMap::new();
        for (i, word) in words.iter().enumerate() {
            let mut k = String::new();
            for j in 1..n {
                k.push_str(&(word.as_bytes()[j] - word.as_bytes()[j - 1]).to_string());
                k.push(',');
            }
            let new_is_only = !map.contains_key(&k);
            map.insert(k, (new_is_only, i));
        }
        for (is_only, i) in map.values() {
            if *is_only {
                return words[*i].clone();
            }
        }
        String::new()
    }
}
```

### **...**

```

```

<!-- tabs:end -->
