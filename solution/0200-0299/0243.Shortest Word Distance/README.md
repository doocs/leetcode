# [243. 最短单词距离](https://leetcode.cn/problems/shortest-word-distance)

[English Version](/solution/0200-0299/0243.Shortest%20Word%20Distance/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个字符串数组&nbsp;<code>wordDict</code>&nbsp;和两个已经存在于该数组中的不同的字符串&nbsp;<code>word1</code> 和 <code>word2</code> 。返回列表中这两个单词之间的最短距离。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入:</strong> wordsDict = ["practice", "makes", "perfect", "coding", "makes"], word1 = "coding", word2 = "practice"
<strong>输出:</strong> 3
</pre>

<p><strong>示例&nbsp;2:</strong></p>

<pre>
<strong>输入:</strong> wordsDict = ["practice", "makes", "perfect", "coding", "makes"], word1 = "makes", word2 = "coding"
<strong>输出:</strong> 1</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong><meta charset="UTF-8" /></p>

<ul>
	<li><code>1 &lt;= wordsDict.length &lt;= 3 * 10<sup>4</sup></code></li>
	<li><code>1 &lt;= wordsDict[i].length &lt;= 10</code></li>
	<li><code>wordsDict[i]</code>&nbsp;由小写英文字母组成</li>
	<li><code>word1</code>&nbsp;和&nbsp;<code>word2</code>&nbsp;在&nbsp;<code>wordsDict</code> 中</li>
	<li><code>word1 != word2</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

用两个指针 `i1`, `i2` 保存 `word1` 和 `word2` 最近出现的位置，然后每次计算距离 `Math.abs(i1 - i2)` 是否比此前的记录更小，是则更新最短距离。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
