# [245. 最短单词距离 III](https://leetcode.cn/problems/shortest-word-distance-iii)

[English Version](/solution/0200-0299/0245.Shortest%20Word%20Distance%20III/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个字符串数组&nbsp;<code>wordsDict</code> 和两个字符串 <code>word1</code> 和 <code>word2</code> ，返回列表中这两个单词之间的最短距离。</p>

<p>注意：<code>word1</code> 和 <code>word2</code>&nbsp;是有可能相同的，并且它们将分别表示为列表中 <strong>两个独立的单词</strong> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>wordsDict = ["practice", "makes", "perfect", "coding", "makes"], word1 = "makes", word2 = "coding"
<strong>输出：</strong>1
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>wordsDict = ["practice", "makes", "perfect", "coding", "makes"], word1 = "makes", word2 = "makes"
<strong>输出：</strong>3
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= wordsDict.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= wordsDict[i].length &lt;= 10</code></li>
	<li><code>wordsDict[i]</code> 由小写英文字母组成</li>
	<li><code>word1</code> 和 <code>word2</code> 都在 <code>wordsDict</code> 中</li>
</ul>

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
