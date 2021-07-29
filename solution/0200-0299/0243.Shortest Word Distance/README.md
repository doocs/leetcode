# [243. 最短单词距离](https://leetcode-cn.com/problems/shortest-word-distance)

[English Version](/solution/0200-0299/0243.Shortest%20Word%20Distance/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个单词列表和两个单词 <em>word1</em> 和 <em>word2</em>，返回列表中这两个单词之间的最短距离。</p>

<p><strong>示例:</strong><br>
假设 words = <code>[&quot;practice&quot;, &quot;makes&quot;, &quot;perfect&quot;, &quot;coding&quot;, &quot;makes&quot;]</code></p>

<pre><strong>输入:</strong> <em>word1</em> = <code>&ldquo;coding&rdquo;</code>, <em>word2</em> = <code>&ldquo;practice&rdquo;</code>
<strong>输出:</strong> 3
</pre>

<pre><strong>输入:</strong> <em>word1</em> = <code>&quot;makes&quot;</code>, <em>word2</em> = <code>&quot;coding&quot;</code>
<strong>输出:</strong> 1
</pre>

<p><strong>注意:</strong><br>
你可以假设 <em>word1</em> 不等于 <em>word2</em>, 并且 <em>word1</em> 和 <em>word2</em> 都在列表里。</p>

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
};
```

### **...**

```

```

<!-- tabs:end -->
