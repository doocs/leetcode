# [1002. 查找共用字符](https://leetcode.cn/problems/find-common-characters)

[English Version](/solution/1000-1099/1002.Find%20Common%20Characters/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

给你一个字符串数组 <code>words</code> ，请你找出所有在 <code>words</code> 的每个字符串中都出现的共用字符（ <strong>包括重复字符</strong>），并以数组形式返回。你可以按 <strong>任意顺序</strong> 返回答案。

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>words = ["bella","label","roller"]
<strong>输出：</strong>["e","l","l"]
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>words = ["cool","lock","cook"]
<strong>输出：</strong>["c","o"]
</pre>

<p>&nbsp;</p>

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
    def commonChars(self, words: List[str]) -> List[str]:
        freq = [10000] * 26
        for word in words:
            t = [0] * 26
            for c in word:
                t[ord(c) - ord('a')] += 1
            for i in range(26):
                freq[i] = min(freq[i], t[i])
        res = []
        for i in range(26):
            if freq[i] > 0:
                res.extend([chr(i + ord("a"))] * freq[i])
        return res
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public List<String> commonChars(String[] words) {
        int[] freq = new int[26];
        Arrays.fill(freq, 10000);
        for (String word : words) {
            int[] t = new int[26];
            for (char c : word.toCharArray()) {
                ++t[c - 'a'];
            }
            for (int i = 0; i < 26; ++i) {
                freq[i] = Math.min(freq[i], t[i]);
            }
        }
        List<String> res = new ArrayList<>();
        for (int i = 0; i < 26; ++i) {
            while (freq[i]-- > 0) {
                res.add(String.valueOf((char) (i + 'a')));
            }
        }
        return res;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<string> commonChars(vector<string>& words) {
        vector<int> freq(26, 10000);
        for (auto word : words) {
            vector<int> t(26);
            for (char c : word)
                ++t[c - 'a'];
            for (int i = 0; i < 26; ++i)
                freq[i] = min(freq[i], t[i]);
        }
        vector<string> res;
        for (int i = 0; i < 26; i++) {
            while (freq[i]--)
                res.emplace_back(1, i + 'a');
        }
        return res;
    }
};
```

### **Go**

```go
func commonChars(words []string) []string {
	freq := make([]int, 26)
	for i := 0; i < 26; i++ {
		freq[i] = 10000
	}
	for _, word := range words {
		t := make([]int, 26)
		for _, c := range word {
			t[c-'a']++
		}
		for i := 0; i < 26; i++ {
			freq[i] = min(freq[i], t[i])
		}
	}
	var res []string
	for i := 0; i < 26; i++ {
		for j := 0; j < freq[i]; j++ {
			res = append(res, string('a'+i))
		}
	}
	return res
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}
```

<!-- tabs:end -->
