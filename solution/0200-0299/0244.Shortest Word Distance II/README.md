# [244. 最短单词距离 II](https://leetcode.cn/problems/shortest-word-distance-ii)

[English Version](/solution/0200-0299/0244.Shortest%20Word%20Distance%20II/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>请设计一个类，使该类的构造函数能够接收一个字符串数组。然后再实现一个方法，该方法能够分别接收两个单词<em>，</em>并返回列表中这两个单词之间的最短距离。</p>

<p>实现&nbsp;<code>WordDistanc</code>&nbsp;类:</p>

<ul>
	<li><code>WordDistance(String[] wordsDict)</code>&nbsp;用字符串数组 <code>wordsDict</code> 初始化对象。</li>
	<li><code>int shortest(String word1, String word2)</code>&nbsp;返回数组 <code>worddict</code> 中 <code>word1</code> 和 <code>word2</code> 之间的最短距离。</li>
</ul>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入:</strong> 
["WordDistance", "shortest", "shortest"]
[[["practice", "makes", "perfect", "coding", "makes"]], ["coding", "practice"], ["makes", "coding"]]
<strong>输出:</strong>
[null, 3, 1]

<b>解释：</b>
WordDistance wordDistance = new WordDistance(["practice", "makes", "perfect", "coding", "makes"]);
wordDistance.shortest("coding", "practice"); // 返回 3
wordDistance.shortest("makes", "coding");    // 返回 1</pre>

<p>&nbsp;</p>

<p><strong>注意:</strong><meta charset="UTF-8" /></p>

<ul>
	<li><code>1 &lt;= wordsDict.length &lt;= 3 * 10<sup>4</sup></code></li>
	<li><code>1 &lt;= wordsDict[i].length &lt;= 10</code></li>
	<li><code>wordsDict[i]</code>&nbsp;由小写英文字母组成</li>
	<li><code>word1</code>&nbsp;和&nbsp;<code>word2</code>&nbsp;在数组&nbsp;<code>wordsDict</code>&nbsp;中</li>
	<li><code>word1 != word2</code></li>
	<li>&nbsp;<code>shortest</code>&nbsp;操作次数不大于&nbsp;<code>5000</code>&nbsp;</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class WordDistance:
    def __init__(self, wordsDict: List[str]):
        self.words = {}
        for i, word in enumerate(wordsDict):
            indexes = self.words.get(word, [])
            indexes.append(i)
            self.words[word] = indexes

    def shortest(self, word1: str, word2: str) -> int:
        idx1, idx2 = self.words[word1], self.words[word2]
        i1 = i2 = 0
        shortest = inf
        while i1 < len(idx1) and i2 < len(idx2):
            shortest = min(shortest, abs(idx1[i1] - idx2[i2]))
            smaller = idx1[i1] < idx2[i2]
            if smaller:
                i1 += 1
            else:
                i2 += 1
        return shortest


# Your WordDistance object will be instantiated and called as such:
# obj = WordDistance(wordsDict)
# param_1 = obj.shortest(word1,word2)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class WordDistance {
    private Map<String, List<Integer>> words;

    public WordDistance(String[] wordsDict) {
        words = new HashMap<>();
        for (int i = 0; i < wordsDict.length; ++i) {
            words.computeIfAbsent(wordsDict[i], k -> new ArrayList<>()).add(i);
        }
    }

    public int shortest(String word1, String word2) {
        List<Integer> idx1 = words.get(word1);
        List<Integer> idx2 = words.get(word2);
        int i1 = 0, i2 = 0, shortest = Integer.MAX_VALUE;
        while (i1 < idx1.size() && i2 < idx2.size()) {
            shortest = Math.min(shortest, Math.abs(idx1.get(i1) - idx2.get(i2)));
            boolean smaller = idx1.get(i1) < idx2.get(i2);
            if (smaller) {
                ++i1;
            } else {
                ++i2;
            }
        }
        return shortest;
    }
}

/**
 * Your WordDistance object will be instantiated and called as such:
 * WordDistance obj = new WordDistance(wordsDict);
 * int param_1 = obj.shortest(word1,word2);
 */
```

### **...**

```

```

<!-- tabs:end -->
