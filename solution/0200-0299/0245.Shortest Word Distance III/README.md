---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0200-0299/0245.Shortest%20Word%20Distance%20III/README.md
tags:
    - 数组
    - 字符串
---

<!-- problem:start -->

# [245. 最短单词距离 III 🔒](https://leetcode.cn/problems/shortest-word-distance-iii)

[English Version](/solution/0200-0299/0245.Shortest%20Word%20Distance%20III/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定一个字符串数组&nbsp;<code>wordsDict</code> 和两个字符串 <code>word1</code> 和 <code>word2</code> ，返回这两个单词在列表中出现的最短距离。</p>

<p>注意：<code>word1</code> 和 <code>word2</code>&nbsp;是有可能相同的，并且它们将分别表示为列表中 <strong>两个独立的单词</strong> 。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<strong>输入：</strong>wordsDict = ["practice", "makes", "perfect", "coding", "makes"], word1 = "makes", word2 = "coding"
<strong>输出：</strong>1
</pre>

<p><strong class="example">示例 2：</strong></p>

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

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：分情况讨论

<!-- thinking:start -->

> **思考**
>
> $word1$ 可以等于 $word2$，此时最短距离是同一单词相邻两次出现的间距；否则仍是两个词最近出现之差。
>
> 分情况维护最近下标：相同则只记上一次出现，不同则分别更新。

<!-- thinking:end -->

我们首先判断 $\textit{word1}$ 和 $\textit{word2}$ 是否相等：

- 如果相等，遍历数组 $\textit{wordsDict}$，找到两个 $\textit{word1}$ 的下标 $i$ 和 $j$，求 $i-j$ 的最小值。
- 如果不相等，遍历数组 $\textit{wordsDict}$，找到 $\textit{word1}$ 和 $\textit{word2}$ 的下标 $i$ 和 $j$，求 $i-j$ 的最小值。

时间复杂度 $O(n)$，其中 $n$ 为数组 $\textit{wordsDict}$ 的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def shortestWordDistance(self, wordsDict: List[str], word1: str, word2: str) -> int:
        ans = len(wordsDict)
        if word1 == word2:
            j = -1
            for i, w in enumerate(wordsDict):
                if w == word1:
                    if j != -1:
                        ans = min(ans, i - j)
                    j = i
        else:
            i = j = -1
            for k, w in enumerate(wordsDict):
                if w == word1:
                    i = k
                if w == word2:
                    j = k
                if i != -1 and j != -1:
                    ans = min(ans, abs(i - j))
        return ans
```

#### Java

```java
class Solution {
    public int shortestWordDistance(String[] wordsDict, String word1, String word2) {
        int ans = wordsDict.length;
        if (word1.equals(word2)) {
            for (int i = 0, j = -1; i < wordsDict.length; ++i) {
                if (wordsDict[i].equals(word1)) {
                    if (j != -1) {
                        ans = Math.min(ans, i - j);
                    }
                    j = i;
                }
            }
        } else {
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
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int shortestWordDistance(vector<string>& wordsDict, string word1, string word2) {
        int n = wordsDict.size();
        int ans = n;
        if (word1 == word2) {
            for (int i = 0, j = -1; i < n; ++i) {
                if (wordsDict[i] == word1) {
                    if (j != -1) {
                        ans = min(ans, i - j);
                    }
                    j = i;
                }
            }
        } else {
            for (int k = 0, i = -1, j = -1; k < n; ++k) {
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
        }
        return ans;
    }
};
```

#### Go

```go
func shortestWordDistance(wordsDict []string, word1 string, word2 string) int {
	ans := len(wordsDict)
	if word1 == word2 {
		j := -1
		for i, w := range wordsDict {
			if w == word1 {
				if j != -1 {
					ans = min(ans, i-j)
				}
				j = i
			}
		}
	} else {
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

#### TypeScript

```ts
function shortestWordDistance(wordsDict: string[], word1: string, word2: string): number {
    let ans = wordsDict.length;
    if (word1 === word2) {
        let j = -1;
        for (let i = 0; i < wordsDict.length; i++) {
            if (wordsDict[i] === word1) {
                if (j !== -1) {
                    ans = Math.min(ans, i - j);
                }
                j = i;
            }
        }
    } else {
        let i = -1,
            j = -1;
        for (let k = 0; k < wordsDict.length; k++) {
            if (wordsDict[k] === word1) {
                i = k;
            }
            if (wordsDict[k] === word2) {
                j = k;
            }
            if (i !== -1 && j !== -1) {
                ans = Math.min(ans, Math.abs(i - j));
            }
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
