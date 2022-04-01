# [17.11. Find Closest](https://leetcode-cn.com/problems/find-closest-lcci)

[中文文档](/lcci/17.11.Find%20Closest/README.md)

## Description

<p>You have a large text file containing words. Given any two words, find the shortest distance (in terms of number of words) between them in the file. If the operation will be repeated many times for the same file (but different pairs of words), can you optimize your solution?</p>

<p><strong>Example: </strong></p>

<pre>

<strong>Input: </strong>words = [&quot;I&quot;,&quot;am&quot;,&quot;a&quot;,&quot;student&quot;,&quot;from&quot;,&quot;a&quot;,&quot;university&quot;,&quot;in&quot;,&quot;a&quot;,&quot;city&quot;], word1 = &quot;a&quot;, word2 = &quot;student&quot;

<strong>Output: </strong>1</pre>

<p>Note:</p>

<ul>
	<li><code>words.length &lt;= 100000</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

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
