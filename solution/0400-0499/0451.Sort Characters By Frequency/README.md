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

“计数器 + 桶”实现。其中，计数器统计字符串中每个字符出现的次数。而对于桶，第 i 个位置存放出现次数为 i 的所有字符。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def frequencySort(self, s: str) -> str:
        counter = Counter(s)
        buckets = defaultdict(list)
        for c, freq in counter.items():
            buckets[freq].append(c)
        res = []
        for i in range(len(s), -1, -1):
            if buckets[i]:
                for c in buckets[i]:
                    res.append(c * i)
        return ''.join(res)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public String frequencySort(String s) {
        Map<Character, Integer> counter = new HashMap<>();
        for (char c : s.toCharArray()) {
            counter.put(c, counter.getOrDefault(c, 0) + 1);
        }
        List<Character>[] buckets = new List[s.length() + 1];
        for (Map.Entry<Character, Integer> entry : counter.entrySet()) {
            char c = entry.getKey();
            int freq = entry.getValue();
            if (buckets[freq] == null) {
                buckets[freq] = new ArrayList<>();
            }
            buckets[freq].add(c);
        }
        StringBuilder sb = new StringBuilder();
        for (int i = s.length(); i >= 0; --i) {
            if (buckets[i] != null) {
                for (char c : buckets[i]) {
                    for (int j = 0; j < i; ++j) {
                        sb.append(c);
                    }
                }
            }
        }
        return sb.toString();
    }
}
```

### **Go**

用结构体排序进行模拟

```go
type pair struct {
	b   byte
	cnt int
}

func frequencySort(s string) string {
	freq := make(map[byte]int)
	for _, r := range s {
		freq[byte(r)]++
	}
	a := make([]pair, 0)
	for k, v := range freq {
		a = append(a, pair{b: k, cnt: v})
	}
	sort.Slice(a, func(i, j int) bool { return a[i].cnt > a[j].cnt })
	var sb strings.Builder
	for _, p := range a {
		sb.Write(bytes.Repeat([]byte{p.b}, p.cnt))
	}
	return sb.String()
}
```

### **TypeScript**

```ts
function frequencySort(s: string): string {
    const map = new Map<string, number>();
    for (const c of s) {
        map.set(c, (map.get(c) ?? 0) + 1);
    }
    return [...map.entries()]
        .sort((a, b) => b[1] - a[1])
        .map(([k, v]) => k.padStart(v, k))
        .join('');
}
```

### **Rust**

```rust
use std::collections::HashMap;
impl Solution {
    pub fn frequency_sort(s: String) -> String {
        let mut map = HashMap::new();
        for c in s.chars() {
            map.insert(c, map.get(&c).unwrap_or(&0) + 1);
        }
        let mut arr = map.into_iter().collect::<Vec<(char, i32)>>();
        arr.sort_unstable_by(|(_, a), (_, b)| b.cmp(&a));
        arr.into_iter()
            .map(|(c, v)| vec![c; v as usize].into_iter().collect::<String>())
            .collect()
    }
}
```

### **...**

```

```

<!-- tabs:end -->
