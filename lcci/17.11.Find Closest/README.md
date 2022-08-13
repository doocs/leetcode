# [面试题 17.11. 单词距离](https://leetcode.cn/problems/find-closest-lcci)

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
        i, j, ans = 1e5, -1e5, 1e5
        for k, word in enumerate(words):
            if word == word1:
                i = k
            elif word == word2:
                j = k
            ans = min(ans, abs(i - j))
        return ans
```

```python
class Solution:
    def findClosest(self, words: List[str], word1: str, word2: str) -> int:
        d = defaultdict(list)
        for i, w in enumerate(words):
            d[w].append(i)
        ans = 1e5
        idx1, idx2 = d[word1], d[word2]
        i, j, m, n = 0, 0, len(idx1), len(idx2)
        while i < m and j < n:
            ans = min(ans, abs(idx1[i] - idx2[j]))
            if idx1[i] < idx2[j]:
                i += 1
            else:
                j += 1
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int findClosest(String[] words, String word1, String word2) {
        int i = 100000, j = -100000, ans = 100000;
        for (int k = 0; k < words.length; ++k) {
            String word = words[k];
            if (word.equals(word1)) {
                i = k;
            } else if (word.equals(word2)) {
                j = k;
            }
            ans = Math.min(ans, Math.abs(i - j));
        }
        return ans;
    }
}
```

```java
class Solution {
    public int findClosest(String[] words, String word1, String word2) {
        Map<String, List<Integer>> d = new HashMap<>();
        for (int i = 0; i < words.length; ++i) {
            d.computeIfAbsent(words[i], k -> new ArrayList<>()).add(i);
        }
        List<Integer> idx1 = d.get(word1), idx2 = d.get(word2);
        int i = 0, j = 0, m = idx1.size(), n = idx2.size();
        int ans = 100000;
        while (i < m && j < n) {
            int t = Math.abs(idx1.get(i) - idx2.get(j));
            ans = Math.min(ans, t);
            if (idx1.get(i) < idx2.get(j)) {
                ++i;
            } else {
                ++j;
            }
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
        int i = 1e5, j = -1e5, ans = 1e5;
        for (int k = 0; k < words.size(); ++k) {
            string word = words[k];
            if (word == word1)
                i = k;
            else if (word == word2)
                j = k;
            ans = min(ans, abs(i - j));
        }
        return ans;
    }
};
```

```cpp
class Solution {
public:
    int findClosest(vector<string>& words, string word1, string word2) {
        unordered_map<string, vector<int>> d;
        for (int i = 0; i < words.size(); ++i) d[words[i]].push_back(i);
        vector<int> idx1 = d[word1], idx2 = d[word2];
        int i = 0, j = 0, m = idx1.size(), n = idx2.size();
        int ans = 1e5;
        while (i < m && j < n)
        {
            int t = abs(idx1[i] - idx2[j]);
            ans = min(ans, t);
            if (idx1[i] < idx2[j]) ++i;
            else ++j;
        }
        return ans;
    }
};
```

### **Go**

```go
func findClosest(words []string, word1 string, word2 string) int {
	i, j, ans := 100000, -100000, 100000
	for k, word := range words {
		if word == word1 {
			i = k
		} else if word == word2 {
			j = k
		}
		ans = min(ans, abs(i-j))
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

```go
func findClosest(words []string, word1 string, word2 string) int {
    d := map[string][]int{}
    for i, w := range words {
        d[w] = append(d[w], i)
    }
    idx1, idx2 := d[word1], d[word2]
    i, j, m, n := 0, 0, len(idx1), len(idx2)
    ans := 100000
    for i < m && j < n {
        t := abs(idx1[i] - idx2[j])
        if t < ans {
            ans = t
        }
        if idx1[i] < idx2[j] {
            i++
        } else {
            j++
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

### **Rust**

```rust
impl Solution {
    pub fn find_closest(words: Vec<String>, word1: String, word2: String) -> i32 {
        let mut res = i32::MAX;
        let mut index1 = -1;
        let mut index2 = -1;
        for (i, word) in words.iter().enumerate() {
            let i = i as i32;
            if word.eq(&word1) {
                index1 = i;
            } else if word.eq(&word2) {
                index2 = i;
            }
            if index1 != -1 && index2 != -1 {
                res = res.min((index1 - index2).abs());
            }
        }
        res
    }
}
```

### **...**

```

```

<!-- tabs:end -->
