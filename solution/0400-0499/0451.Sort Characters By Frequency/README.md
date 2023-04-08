# [451. 根据字符出现频率排序](https://leetcode.cn/problems/sort-characters-by-frequency)

[English Version](/solution/0400-0499/0451.Sort%20Characters%20By%20Frequency/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个字符串 <code>s</code> ，根据字符出现的 <strong>频率</strong> 对其进行 <strong>降序排序</strong> 。一个字符出现的 <strong>频率</strong> 是它出现在字符串中的次数。</p>

<p>返回 <em>已排序的字符串&nbsp;</em>。如果有多个答案，返回其中任何一个。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入: </strong>s = "tree"
<strong>输出: </strong>"eert"
<strong>解释: </strong>'e'出现两次，'r'和't'都只出现一次。
因此'e'必须出现在'r'和't'之前。此外，"eetr"也是一个有效的答案。
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入: </strong>s = "cccaaa"
<strong>输出: </strong>"cccaaa"
<strong>解释: </strong>'c'和'a'都出现三次。此外，"aaaccc"也是有效的答案。
注意"cacaca"是不正确的，因为相同的字母必须放在一起。
</pre>

<p><strong>示例 3:</strong></p>

<pre>
<strong>输入: </strong>s = "Aabb"
<strong>输出: </strong>"bbAa"
<strong>解释: </strong>此外，"bbaA"也是一个有效的答案，但"Aabb"是不正确的。
注意'A'和'a'被认为是两种不同的字符。
</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 5 * 10<sup>5</sup></code></li>
	<li><code>s</code>&nbsp;由大小写英文字母和数字组成</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：哈希表 + 排序**

我们用哈希表 $cnt$ 统计字符串 $s$ 中每个字符出现的次数，然后将 $cnt$ 中的键值对按照出现次数降序排序，最后按照排序后的顺序拼接字符串即可。

时间复杂度 $O(n + k \times \log k)$，空间复杂度 $O(n + k)$，其中 $n$ 为字符串 $s$ 的长度，而 $k$ 为不同字符的个数。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def frequencySort(self, s: str) -> str:
        cnt = Counter(s)
        return ''.join(c * v for c, v in sorted(cnt.items(), key=lambda x: -x[1]))
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public String frequencySort(String s) {
        Map<Character, Integer> cnt = new HashMap<>(52);
        for (int i = 0; i < s.length(); ++i) {
            cnt.merge(s.charAt(i), 1, Integer::sum);
        }
        List<Character> cs = new ArrayList<>(cnt.keySet());
        cs.sort((a, b) -> cnt.get(b) - cnt.get(a));
        StringBuilder ans = new StringBuilder();
        for (char c : cs) {
            for (int v = cnt.get(c); v > 0; --v) {
                ans.append(c);
            }
        }
        return ans.toString();
    }
}
```

### **C++**

```cpp
class Solution {
public:
    string frequencySort(string s) {
        unordered_map<char, int> cnt;
        for (char& c : s) {
            ++cnt[c];
        }
        vector<char> cs;
        for (auto& [c, _] : cnt) {
            cs.push_back(c);
        }
        sort(cs.begin(), cs.end(), [&](char& a, char& b) {
            return cnt[a] > cnt[b];
        });
        string ans;
        for (char& c : cs) {
            ans += string(cnt[c], c);
        }
        return ans;
    }
};
```

### **Go**

```go
func frequencySort(s string) string {
	cnt := map[byte]int{}
	for i := range s {
		cnt[s[i]]++
	}
	cs := make([]byte, 0, len(s))
	for c := range cnt {
		cs = append(cs, c)
	}
	sort.Slice(cs, func(i, j int) bool { return cnt[cs[i]] > cnt[cs[j]] })
	ans := make([]byte, 0, len(s))
	for _, c := range cs {
		ans = append(ans, bytes.Repeat([]byte{c}, cnt[c])...)
	}
	return string(ans)
}
```

### **TypeScript**

```ts
function frequencySort(s: string): string {
    const cnt: Map<string, number> = new Map();
    for (const c of s) {
        cnt.set(c, (cnt.get(c) || 0) + 1);
    }
    const cs = Array.from(cnt.keys()).sort((a, b) => cnt.get(b)! - cnt.get(a)!);
    const ans: string[] = [];
    for (const c of cs) {
        ans.push(c.repeat(cnt.get(c)!));
    }
    return ans.join('');
}
```

### **Rust**

```rust
use std::collections::HashMap;
impl Solution {
    pub fn frequency_sort(s: String) -> String {
        let mut cnt = HashMap::new();
        for c in s.chars() {
            cnt.insert(c, cnt.get(&c).unwrap_or(&0) + 1);
        }
        let mut cs = cnt.into_iter().collect::<Vec<(char, i32)>>();
        cs.sort_unstable_by(|(_, a), (_, b)| b.cmp(&a));
        cs.into_iter()
            .map(|(c, v)| vec![c; v as usize].into_iter().collect::<String>())
            .collect()
    }
}
```

### **...**

```

```

<!-- tabs:end -->
