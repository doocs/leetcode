# [187. 重复的 DNA 序列](https://leetcode.cn/problems/repeated-dna-sequences)

[English Version](/solution/0100-0199/0187.Repeated%20DNA%20Sequences/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p><strong>DNA序列</strong>&nbsp;由一系列核苷酸组成，缩写为<meta charset="UTF-8" />&nbsp;<code>'A'</code>,&nbsp;<code>'C'</code>,&nbsp;<code>'G'</code>&nbsp;和<meta charset="UTF-8" />&nbsp;<code>'T'</code>.。</p>

<ul>
	<li>例如，<meta charset="UTF-8" /><code>"ACGAATTCCG"</code>&nbsp;是一个 <strong>DNA序列</strong> 。</li>
</ul>

<p>在研究 <strong>DNA</strong> 时，识别 DNA 中的重复序列非常有用。</p>

<p>给定一个表示 <strong>DNA序列</strong> 的字符串 <code>s</code> ，返回所有在 DNA 分子中出现不止一次的&nbsp;<strong>长度为&nbsp;<code>10</code></strong>&nbsp;的序列(子字符串)。你可以按 <strong>任意顺序</strong> 返回答案。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"
<strong>输出：</strong>["AAAAACCCCC","CCCCCAAAAA"]
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>s = "AAAAAAAAAAAAA"
<strong>输出：</strong>["AAAAAAAAAA"]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>0 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s[i]</code><code>==</code><code>'A'</code>、<code>'C'</code>、<code>'G'</code>&nbsp;or&nbsp;<code>'T'</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**朴素解法：**

使用哈希表，记录所有连续长度为 10 的子字符串出现次数（字符串为 Key，次数为 Value），当出现一次以上时，将其加入返回列表当中。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def findRepeatedDnaSequences(self, s: str) -> List[str]:
        n = len(s) - 10
        cnt = Counter()
        ans = []
        for i in range(n + 1):
            sub = s[i : i + 10]
            cnt[sub] += 1
            if cnt[sub] == 2:
                ans.append(sub)
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public List<String> findRepeatedDnaSequences(String s) {
        int n = s.length() - 10;
        Map<String, Integer> cnt = new HashMap<>();
        List<String> ans = new ArrayList<>();
        for (int i = 0; i <= n; ++i) {
            String sub = s.substring(i, i + 10);
            cnt.put(sub, cnt.getOrDefault(sub, 0) + 1);
            if (cnt.get(sub) == 2) {
                ans.add(sub);
            }
        }
        return ans;
    }
}
```

### **JavaScript**

```js
/**
 * @param {string} s
 * @return {string[]}
 */
var findRepeatedDnaSequences = function (s) {
    const n = s.length - 10;
    let cnt = new Map();
    let ans = [];
    for (let i = 0; i <= n; ++i) {
        let sub = s.slice(i, i + 10);
        cnt[sub] = (cnt[sub] || 0) + 1;
        if (cnt[sub] == 2) {
            ans.push(sub);
        }
    }
    return ans;
};
```

### **Go**

```go
func findRepeatedDnaSequences(s string) []string {
	cnt := make(map[string]int)
	n := len(s) - 10
	ans := make([]string, 0)
	for i := 0; i <= n; i++ {
		sub := s[i : i+10]
		cnt[sub]++
		if cnt[sub] == 2 {
			ans = append(ans, sub)
		}
	}
	return ans
}
```

### **C++**

```cpp
class Solution {
public:
    vector<string> findRepeatedDnaSequences(string s) {
        map<string, int> cnt;
        int n = s.size() - 10;
        vector<string> ans;
        for (int i = 0; i <= n; ++i) {
            string sub = s.substr(i, 10);
            if (++cnt[sub] == 2) {
                ans.push_back(sub);
            }
        }
        return ans;
    }
};
```

### **C#**

```cs
using System.Collections.Generic;

public class Solution {
    public IList<string> FindRepeatedDnaSequences(string s) {
        var once = new HashSet<int>();
        var moreThanOnce = new HashSet<int>();
        int bits = 0;
        for (var i = 0; i < s.Length; ++i)
        {
            bits <<= 2;
            switch (s[i])
            {
                case 'A':
                    break;
                case 'C':
                    bits |= 1;
                    break;
                case 'G':
                    bits |= 2;
                    break;
                case 'T':
                    bits |= 3;
                    break;
            }
            if (i >= 10)
            {
                bits &= 0xFFFFF;
            }
            if (i >= 9 && !once.Add(bits))
            {
                moreThanOnce.Add(bits);
            }
        }

        var results = new List<string>();
        foreach (var item in moreThanOnce)
        {
            var itemCopy = item;
            var charArray = new char[10];
            for (var i = 9; i >= 0; --i)
            {
                switch (itemCopy & 3)
                {
                    case 0:
                        charArray[i] = 'A';
                        break;
                    case 1:
                        charArray[i] = 'C';
                        break;
                    case 2:
                        charArray[i] = 'G';
                        break;
                    case 3:
                        charArray[i] = 'T';
                        break;
                }
                itemCopy >>= 2;
            }
            results.Add(new string(charArray));
        }
        return results;
    }
}
```

### **TypeScript**

```ts
function findRepeatedDnaSequences(s: string): string[] {
    const n = s.length;
    const map = new Map<string, boolean>();
    const res = [];
    for (let i = 0; i <= n - 10; i++) {
        const key = s.slice(i, i + 10);
        if (map.has(key) && map.get(key)) {
            res.push(key);
        }
        map.set(key, !map.has(key));
    }
    return res;
}
```

### **Rust**

```rust
use std::collections::HashMap;

impl Solution {
    pub fn find_repeated_dna_sequences(s: String) -> Vec<String> {
        let n = s.len();
        let mut res = vec![];
        if n < 10 {
            return res;
        }
        let mut map = HashMap::new();
        for i in 0..=n - 10 {
            let key = &s[i..i + 10];
            if map.contains_key(&key) && *map.get(&key).unwrap() {
                res.push(key.to_string());
            }
            map.insert(key, !map.contains_key(&key));
        }
        res
    }
}
```

### **...**

```

```

<!-- tabs:end -->
