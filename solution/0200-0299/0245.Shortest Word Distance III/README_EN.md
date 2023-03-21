# [245. Shortest Word Distance III](https://leetcode.com/problems/shortest-word-distance-iii)

[中文文档](/solution/0200-0299/0245.Shortest%20Word%20Distance%20III/README.md)

## Description

<p>Given an array of strings <code>wordsDict</code> and two strings that already exist in the array <code>word1</code> and <code>word2</code>, return <em>the shortest distance between the occurrence of these two words in the list</em>.</p>

<p><strong>Note</strong> that <code>word1</code> and <code>word2</code> may be the same. It is guaranteed that they represent <strong>two individual words</strong> in the list.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<pre><strong>Input:</strong> wordsDict = ["practice", "makes", "perfect", "coding", "makes"], word1 = "makes", word2 = "coding"
<strong>Output:</strong> 1
</pre><p><strong class="example">Example 2:</strong></p>
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

### **Java**

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

### **C++**

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

### **Go**

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

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}
```

### **...**

```

```

<!-- tabs:end -->
