# [245. 最短单词距离 III](https://leetcode-cn.com/problems/shortest-word-distance-iii)

[English Version](/solution/0200-0299/0245.Shortest%20Word%20Distance%20III/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个单词列表和两个单词 <em>word1</em> 和 <em>word2</em>，返回列表中这两个单词之间的最短距离。</p>

<p><em>word1</em> 和 <em>word2</em>&nbsp;是有可能相同的，并且它们将分别表示为列表中两个独立的单词。</p>

<p><strong>示例:</strong><br>
假设 words = <code>[&quot;practice&quot;, &quot;makes&quot;, &quot;perfect&quot;, &quot;coding&quot;, &quot;makes&quot;]</code>.</p>

<pre><strong>输入:</strong> <em>word1</em> = <code>&ldquo;makes&rdquo;</code>, <em>word2</em> = <code>&ldquo;coding&rdquo;</code>
<strong>输出:</strong> 1
</pre>

<pre><strong>输入:</strong> <em>word1</em> = <code>&quot;makes&quot;</code>, <em>word2</em> = <code>&quot;makes&quot;</code>
<strong>输出:</strong> 3
</pre>

<p><strong>注意:</strong><br>
你可以假设 <em>word1</em> 和 <em>word2</em> 都在列表里。</p>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def shortestWordDistance(self, wordsDict: List[str], word1: str, word2: str) -> int:
        i1 = i2 = -1
        shortest_distance = len(wordsDict)
        same = word1 == word2
        for i in range(len(wordsDict)):
            if same:
                if word1 == wordsDict[i]:
                    if i1 != -1:
                        shortest_distance = min(shortest_distance, i - i1)
                    i1 = i
            else:
                if word1 == wordsDict[i]:
                    i1 = i
                if word2 == wordsDict[i]:
                    i2 = i
                if i1 != -1 and i2 != -1:
                    shortest_distance = min(shortest_distance, abs(i1 - i2))
        return shortest_distance
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int shortestWordDistance(String[] wordsDict, String word1, String word2) {
        int i1 = -1, i2 = -1;
        int shortestDistance = wordsDict.length;
        boolean same = word1.equals(word2);
        for (int i = 0; i < wordsDict.length; ++i) {
            if (same) {
                if (word1.equals(wordsDict[i])) {
                    if (i1 != -1) {
                        shortestDistance = Math.min(shortestDistance, i - i1);
                    }
                    i1 = i;
                }
            } else {
                if (word1.equals(wordsDict[i])) {
                    i1 = i;
                }
                if (word2.equals(wordsDict[i])) {
                    i2 = i;
                }
                if (i1 != -1 && i2 != -1) {
                    shortestDistance = Math.min(shortestDistance, Math.abs(i1 - i2));
                }
            }
        }
        return shortestDistance;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
