# [451. 根据字符出现频率排序](https://leetcode-cn.com/problems/sort-characters-by-frequency)

[English Version](/solution/0400-0499/0451.Sort%20Characters%20By%20Frequency/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个字符串，请将字符串里的字符按照出现的频率降序排列。</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入:</strong>
&quot;tree&quot;

<strong>输出:</strong>
&quot;eert&quot;

<strong>解释:
</strong>&#39;e&#39;出现两次，&#39;r&#39;和&#39;t&#39;都只出现一次。
因此&#39;e&#39;必须出现在&#39;r&#39;和&#39;t&#39;之前。此外，&quot;eetr&quot;也是一个有效的答案。
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入:</strong>
&quot;cccaaa&quot;

<strong>输出:</strong>
&quot;cccaaa&quot;

<strong>解释:
</strong>&#39;c&#39;和&#39;a&#39;都出现三次。此外，&quot;aaaccc&quot;也是有效的答案。
注意&quot;cacaca&quot;是不正确的，因为相同的字母必须放在一起。
</pre>

<p><strong>示例 3:</strong></p>

<pre>
<strong>输入:</strong>
&quot;Aabb&quot;

<strong>输出:</strong>
&quot;bbAa&quot;

<strong>解释:
</strong>此外，&quot;bbaA&quot;也是一个有效的答案，但&quot;Aabb&quot;是不正确的。
注意&#39;A&#39;和&#39;a&#39;被认为是两种不同的字符。
</pre>

## 解法

<!-- 这里可写通用的实现逻辑 -->

“计数器 + 桶”实现。其中，计数器统计字符串中每个字符出现的次数。而对于桶，第 i 个位置存放出现次数为 i 的所有字符。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def frequencySort(self, s: str) -> str:
        counter = collections.Counter(s)
        buckets = collections.defaultdict(list)
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

### **...**

```

```

<!-- tabs:end -->
