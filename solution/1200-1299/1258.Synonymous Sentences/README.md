# [1258. 近义词句子](https://leetcode.cn/problems/synonymous-sentences)

[English Version](/solution/1200-1299/1258.Synonymous%20Sentences/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个近义词表&nbsp;<code>synonyms</code> 和一个句子&nbsp;<code>text</code>&nbsp;，&nbsp;<code>synonyms</code> 表中是一些近义词对 ，你可以将句子&nbsp;<code>text</code> 中每个单词用它的近义词来替换。</p>

<p>请你找出所有用近义词替换后的句子，按&nbsp;<strong>字典序排序</strong>&nbsp;后返回。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：
</strong>synonyms = [[&quot;happy&quot;,&quot;joy&quot;],[&quot;sad&quot;,&quot;sorrow&quot;],[&quot;joy&quot;,&quot;cheerful&quot;]],
text = &quot;I am happy today but was sad yesterday&quot;
<strong>输出：
</strong>[&quot;I am cheerful today but was sad yesterday&quot;,
&quot;I am cheerful today but was sorrow yesterday&quot;,
&quot;I am happy today but was sad yesterday&quot;,
&quot;I am happy today but was sorrow yesterday&quot;,
&quot;I am joy today but was sad yesterday&quot;,
&quot;I am joy today but was sorrow yesterday&quot;]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>0 &lt;=&nbsp;synonyms.length &lt;= 10</code></li>
	<li><code>synonyms[i].length == 2</code></li>
	<li><code>synonyms[0] != synonyms[1]</code></li>
	<li>所有单词仅包含英文字母，且长度最多为&nbsp;<code>10</code> 。</li>
	<li><code>text</code>&nbsp;最多包含&nbsp;<code>10</code> 个单词，且单词间用单个空格分隔开。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

并查集。

先找出单词的相似单词集合，构成对应的并查集。

然后循环相似单词集合，进行句子拼接。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def generateSentences(self, synonyms: List[List[str]], text: str) -> List[str]:
        p = {}

        def find(x):
            if p[x] != x:
                p[x] = find(p[x])
            return p[x]

        for a, b in synonyms:
            p[a] = a
            p[b] = b
        for a, b in synonyms:
            p[find(a)] = find(b)

        s = defaultdict(set)
        for a, b in synonyms:
            s[find(a)].add(a)
            s[find(b)].add(b)
        res = []
        for word in text.split(' '):
            if word not in p:
                if not res:
                    res.append([word])
                else:
                    for a in res:
                        a.append(word)
            else:
                words = sorted(s[find(word)])
                if not res:
                    for b in words:
                        res.append([b])
                else:
                    res = [a + [b] for a in res for b in words]
        return [' '.join(sentence) for sentence in res]
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    private Map<String, String> p;

    public List<String> generateSentences(List<List<String>> synonyms, String text) {
        p = new HashMap<>();
        for (List<String> item : synonyms) {
            p.put(item.get(0), item.get(0));
            p.put(item.get(1), item.get(1));
        }
        for (List<String> item : synonyms) {
            p.put(find(item.get(0)), find(item.get(1)));
        }
        Map<String, Set<String>> s = new HashMap<>();
        for (List<String> item : synonyms) {
            String a = find(item.get(0)), b = find(item.get(1));
            s.computeIfAbsent(a, k -> new HashSet<>()).add(item.get(0));
            s.computeIfAbsent(b, k -> new HashSet<>()).add(item.get(1));
        }
        List<List<String>> all = new ArrayList<>();
        for (String word : text.split(" ")) {
            if (!p.containsKey(word)) {
                if (all.isEmpty()) {
                    List<String> t = new ArrayList<>();
                    t.add(word);
                    all.add(t);
                } else {
                    for (List<String> a : all) {
                        a.add(word);
                    }
                }
            } else {
                Set<String> words = s.get(find(word));
                if (all.isEmpty()) {
                    for (String b : words) {
                        List<String> t = new ArrayList<>();
                        t.add(b);
                        all.add(t);
                    }
                } else {
                    List<List<String>> t = new ArrayList<>();
                    for (List<String> a : all) {
                        for (String b : words) {
                            List<String> c = new ArrayList<>(a);
                            c.add(b);
                            t.add(c);
                        }
                    }
                    all = t;
                }
            }
        }
        List<String> res = new ArrayList<>();
        for (List<String> item : all) {
            res.add(String.join(" ", item));
        }
        Collections.sort(res);
        return res;
    }

    private String find(String x) {
        if (!Objects.equals(p.get(x), x)) {
            p.put(x, find(p.get(x)));
        }
        return p.get(x);
    }
}
```

### **...**

```

```

<!-- tabs:end -->
