# [245. Shortest Word Distance III](https://leetcode.com/problems/shortest-word-distance-iii)

[中文文档](/solution/0200-0299/0245.Shortest%20Word%20Distance%20III/README.md)

## Description

<p>Given an array of strings <code>wordsDict</code> and two strings that already exist in the array <code>word1</code> and <code>word2</code>, return <em>the shortest distance between these two words in the list</em>.</p>

<p><strong>Note</strong> that <code>word1</code> and <code>word2</code> may be the same. It is guaranteed that they represent <strong>two individual words</strong> in the list.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<pre><strong>Input:</strong> wordsDict = ["practice", "makes", "perfect", "coding", "makes"], word1 = "makes", word2 = "coding"
<strong>Output:</strong> 1
</pre><p><strong>Example 2:</strong></p>
<pre><strong>Input:</strong> wordsDict = ["practice", "makes", "perfect", "coding", "makes"], word1 = "makes", word2 = "makes"
<strong>Output:</strong> 3
</pre>
<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= wordsDict.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= wordsDict[i].length &lt;= 10</code></li>
	<li><code>wordsDict[i]</code> consists of lowercase English letters.</li>
	<li><code>word1</code> and <code>word2</code> are in <code>wordsDict</code>.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

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
