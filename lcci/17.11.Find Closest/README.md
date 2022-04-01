# [面试题 17.11. 单词距离](https://leetcode-cn.com/problems/find-closest-lcci)

[English Version](/lcci/17.11.Find%20Closest/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->
<p>有个内含单词的超大文本文件，给定任意两个单词，找出在这个文件中这两个单词的最短距离(相隔单词数)。如果寻找过程在这个文件中会重复多次，而每次寻找的单词不同，你能对此优化吗?</p>

<p><strong>示例：</strong></p>

<pre><strong>输入：</strong>words = [&quot;I&quot;,&quot;am&quot;,&quot;a&quot;,&quot;student&quot;,&quot;from&quot;,&quot;a&quot;,&quot;university&quot;,&quot;in&quot;,&quot;a&quot;,&quot;city&quot;], word1 = &quot;a&quot;, word2 = &quot;student&quot;
<strong>输出：</strong>1</pre>

<p>提示：</p>

<ul>
	<li><code>words.length &lt;= 100000</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def findClosest(self, words: List[str], word1: str, word2: str) -> int:
        idx1, idx2, ans = 10**5, -10**5, 10**5
        for i, word in enumerate(words):
            if word == word1:
                idx1 = i
            elif word == word2:
                idx2 = i
            ans = min(ans, abs(idx1 - idx2))
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int findClosest(String[] words, String word1, String word2) {
        int idx1 = 100000;
        int idx2 = -100000;
        int ans = 100000;
        for (int i = 0; i < words.length; ++i) {
            String word = words[i];
            if (word.equals(word1)) {
                idx1 = i;
            } else if (word.equals(word2)) {
                idx2 = i;
            }
            ans = Math.min(ans, Math.abs(idx1 - idx2));
        }
        return ans;
    }
}
```

### **TypeScript**

```ts
function findClosest(words: string[], word1: string, word2: string): number {
    let index1 = 100000;
    let index2 = -100000;
    let res = 100000;
    const n = words.length;
    for (let i = 0; i < n; i++) {
        const word = words[i];
        if (word === word1) {
            index1 = i;
        } else if (word === word2) {
            index2 = i;
        }
        res = Math.min(res, Math.abs(index1 - index2));
    }
    return res;
}
```

### **C++**

```cpp
class Solution {
public:
    int findClosest(vector<string>& words, string word1, string word2) {
        int idx1 = 1e5, idx2 = -1e5, ans = 1e5;
        for (int i = 0; i < words.size(); ++i)
        {
            string word = words[i];
            if (word == word1) idx1 = i;
            else if (word == word2) idx2 = i;
            ans = min(ans, abs(idx1 - idx2));
        }
        return ans;
    }
};
```

### **Go**

```go
func findClosest(words []string, word1 string, word2 string) int {
	idx1, idx2, ans := 100000, -100000, 100000
	for i, word := range words {
		if word == word1 {
			idx1 = i
		} else if word == word2 {
			idx2 = i
		}
		ans = min(ans, abs(idx1-idx2))
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
