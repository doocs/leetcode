# [243. Shortest Word Distance](https://leetcode.com/problems/shortest-word-distance)

[中文文档](/solution/0200-0299/0243.Shortest%20Word%20Distance/README.md)

## Description

<p>Given an array of strings <code>wordsDict</code> and two different strings that already exist in the array <code>word1</code> and <code>word2</code>, return <em>the shortest distance between these two words in the list</em>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> wordsDict = [&quot;practice&quot;, &quot;makes&quot;, &quot;perfect&quot;, &quot;coding&quot;, &quot;makes&quot;], word1 = &quot;coding&quot;, word2 = &quot;practice&quot;
<strong>Output:</strong> 3
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> wordsDict = [&quot;practice&quot;, &quot;makes&quot;, &quot;perfect&quot;, &quot;coding&quot;, &quot;makes&quot;], word1 = &quot;makes&quot;, word2 = &quot;coding&quot;
<strong>Output:</strong> 1
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= wordsDict.length &lt;= 3 * 10<sup>4</sup></code></li>
	<li><code>1 &lt;= wordsDict[i].length &lt;= 10</code></li>
	<li><code>wordsDict[i]</code> consists of lowercase English letters.</li>
	<li><code>word1</code> and <code>word2</code> are in <code>wordsDict</code>.</li>
	<li><code>word1 != word2</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def shortestDistance(self, wordsDict: List[str], word1: str, word2: str) -> int:
        i1 = i2 = -1
        shortest_distance = len(wordsDict)
        for i in range(len(wordsDict)):
            if wordsDict[i] == word1:
                i1 = i
            elif wordsDict[i] == word2:
                i2 = i
            if i1 != -1 and i2 != -1:
                shortest_distance = min(shortest_distance, abs(i1 - i2))
        return shortest_distance
```

### **Java**

```java
class Solution {
    public int shortestDistance(String[] wordsDict, String word1, String word2) {
        int i1 = -1, i2 = -1;
        int shortestDistance = wordsDict.length;
        for (int i = 0; i < wordsDict.length; ++i) {
            if (word1.equals(wordsDict[i])) {
                i1 = i;
            } else if (word2.equals(wordsDict[i])) {
                i2 = i;
            }
            if (i1 != -1 && i2 != -1) {
                shortestDistance = Math.min(shortestDistance, Math.abs(i1 - i2));
            }
        }
        return shortestDistance;
    }
}
```

### **TypeScript**

```ts
function integerBreak(n: number): number {
    let dp = new Array(n + 1).fill(1);
    for (let i = 3; i <= n; i++) {
        for (let j = 1; j < i; j++) {
            dp[i] = Math.max(dp[i], j * (i - j), j * dp[i - j]);
        }
    }
    return dp.pop();
}
```

### **...**

```

```

<!-- tabs:end -->
