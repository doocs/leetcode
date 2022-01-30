# [884. 两句话中的不常见单词](https://leetcode-cn.com/problems/uncommon-words-from-two-sentences)

[English Version](/solution/0800-0899/0884.Uncommon%20Words%20from%20Two%20Sentences/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定两个句子&nbsp;<code>A</code>&nbsp;和&nbsp;<code>B</code>&nbsp;。&nbsp;（<em>句子</em>是一串由空格分隔的单词。每个<em>单词</em>仅由小写字母组成。）</p>

<p>如果一个单词在其中一个句子中只出现一次，在另一个句子中却没有出现，那么这个单词就是<em>不常见的</em>。</p>

<p>返回所有不常用单词的列表。</p>

<p>您可以按任何顺序返回列表。</p>

<p>&nbsp;</p>

<ol>
</ol>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>A = &quot;this apple is sweet&quot;, B = &quot;this apple is sour&quot;
<strong>输出：</strong>[&quot;sweet&quot;,&quot;sour&quot;]
</pre>

<p><strong>示例&nbsp;2：</strong></p>

<pre><strong>输入：</strong>A = &quot;apple apple&quot;, B = &quot;banana&quot;
<strong>输出：</strong>[&quot;banana&quot;]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ol>
	<li><code>0 &lt;= A.length &lt;= 200</code></li>
	<li><code>0 &lt;= B.length &lt;= 200</code></li>
	<li><code>A</code> 和&nbsp;<code>B</code>&nbsp;都只包含空格和小写字母。</li>
</ol>

## 解法

<!-- 这里可写通用的实现逻辑 -->

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

### **...**

```

```

<!-- tabs:end -->
