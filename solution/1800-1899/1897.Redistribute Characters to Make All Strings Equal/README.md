# [1897. 重新分配字符使所有字符串都相等](https://leetcode.cn/problems/redistribute-characters-to-make-all-strings-equal)

[English Version](/solution/1800-1899/1897.Redistribute%20Characters%20to%20Make%20All%20Strings%20Equal/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个字符串数组 <code>words</code>（下标 <strong>从 0 开始</strong> 计数）。</p>

<p>在一步操作中，需先选出两个 <strong>不同</strong> 下标 <code>i</code> 和 <code>j</code>，其中 <code>words[i]</code> 是一个非空字符串，接着将 <code>words[i]</code> 中的 <strong>任一</strong> 字符移动到 <code>words[j]</code> 中的 <strong>任一</strong> 位置上。</p>

<p>如果执行任意步操作可以使 <code>words</code> 中的每个字符串都相等，返回 <code>true</code><em> </em>；否则，返回<em> </em><code>false</code> 。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>words = ["abc","aabc","bc"]
<strong>输出：</strong>true
<strong>解释：</strong>将 <code>words[1] 中的第一个</code> 'a' 移动到<code> words[2] 的最前面。
使 </code><code>words[1]</code> = "abc" 且 words[2] = "abc" 。
所有字符串都等于 "abc" ，所以返回 <code>true</code> 。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>words = ["ab","a"]
<strong>输出：</strong>false
<strong>解释：</strong>执行操作无法使所有字符串都相等。
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= words.length &lt;= 100</code></li>
	<li><code>1 &lt;= words[i].length &lt;= 100</code></li>
	<li><code>words[i]</code> 由小写英文字母组成</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def makeEqual(self, words: List[str]) -> bool:
        counter = Counter()
        for word in words:
            for c in word:
                counter[c] += 1
        n = len(words)
        return all(count % n == 0 for count in counter.values())
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public boolean makeEqual(String[] words) {
        int[] counter = new int[26];
        for (String word : words) {
            for (char c : word.toCharArray()) {
                ++counter[c - 'a'];
            }
        }
        int n = words.length;
        for (int i = 0; i < 26; ++i) {
            if (counter[i] % n != 0) {
                return false;
            }
        }
        return true;
    }
}
```

### **TypeScript**

```ts
function makeEqual(words: string[]): boolean {
    let n = words.length;
    let letters = new Array(26).fill(0);
    for (let word of words) {
        for (let i = 0; i < word.length; ++i) {
            ++letters[word.charCodeAt(i) - 97];
        }
    }

    for (let i = 0; i < letters.length; ++i) {
        if (letters[i] % n != 0) {
            return false;
        }
    }
    return true;
}
```

### **C++**

```cpp
class Solution {
public:
    bool makeEqual(vector<string>& words) {
        vector<int> counter(26, 0);
        for (string word : words) {
            for (char c : word) {
                ++counter[c - 'a'];
            }
        }
        int n = words.size();
        for (int count : counter) {
            if (count % n != 0) return false;
        }
        return true;
    }
};
```

### **Go**

```go
func makeEqual(words []string) bool {
	counter := [26]int{}
	for _, word := range words {
		for _, c := range word {
			counter[c-'a']++
		}
	}
	n := len(words)
	for _, count := range counter {
		if count%n != 0 {
			return false
		}
	}
	return true
}
```

### **...**

```

```

<!-- tabs:end -->
