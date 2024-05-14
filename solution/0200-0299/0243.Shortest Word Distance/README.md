---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0200-0299/0243.Shortest%20Word%20Distance/README.md
tags:
    - 数组
    - 字符串
---

# [243. 最短单词距离 🔒](https://leetcode.cn/problems/shortest-word-distance)

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

### 方法一：双指针

遍历数组 `wordsDict`，找到 `word1` 和 `word2` 的下标 $i$ 和 $j$，求 $i-j$ 的最小值。

时间复杂度 $O(n)$，空间复杂度 $O(1)$。其中 $n$ 为数组 `wordsDict` 的长度。

<!-- tabs:start -->

```python
class Solution:
    def shortestDistance(self, wordsDict: List[str], word1: str, word2: str) -> int:
        i = j = -1
        ans = inf
        for k, w in enumerate(wordsDict):
            if w == word1:
                i = k
            if w == word2:
                j = k
            if i != -1 and j != -1:
                ans = min(ans, abs(i - j))
        return ans
```

```java
class Solution {
    public int shortestDistance(String[] wordsDict, String word1, String word2) {
        int ans = 0x3f3f3f3f;
        for (int k = 0, i = -1, j = -1; k < wordsDict.length; ++k) {
            if (wordsDict[k].equals(word1)) {
                i = k;
            }
            if (wordsDict[k].equals(word2)) {
                j = k;
            }
            if (i != -1 && j != -1) {
                ans = Math.min(ans, Math.abs(i - j));
            }
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int shortestDistance(vector<string>& wordsDict, string word1, string word2) {
        int ans = INT_MAX;
        for (int k = 0, i = -1, j = -1; k < wordsDict.size(); ++k) {
            if (wordsDict[k] == word1) {
                i = k;
            }
            if (wordsDict[k] == word2) {
                j = k;
            }
            if (i != -1 && j != -1) {
                ans = min(ans, abs(i - j));
            }
        }
        return ans;
    }
};
```

```go
func shortestDistance(wordsDict []string, word1 string, word2 string) int {
	ans := 0x3f3f3f3f
	i, j := -1, -1
	for k, w := range wordsDict {
		if w == word1 {
			i = k
		}
		if w == word2 {
			j = k
		}
		if i != -1 && j != -1 {
			ans = min(ans, abs(i-j))
		}
	}
	return ans
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}
```

<!-- tabs:end -->

<!-- end -->
