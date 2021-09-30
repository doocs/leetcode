# [1002. 查找常用字符](https://leetcode-cn.com/problems/find-common-characters)

[English Version](/solution/1000-1099/1002.Find%20Common%20Characters/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定仅有小写字母组成的字符串数组 <code>A</code>，返回列表中的每个字符串中都显示的全部字符（<strong>包括重复字符</strong>）组成的列表。例如，如果一个字符在每个字符串中出现 3 次，但不是 4 次，则需要在最终答案中包含该字符 3 次。</p>

<p>你可以按任意顺序返回答案。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>[&quot;bella&quot;,&quot;label&quot;,&quot;roller&quot;]
<strong>输出：</strong>[&quot;e&quot;,&quot;l&quot;,&quot;l&quot;]
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>[&quot;cool&quot;,&quot;lock&quot;,&quot;cook&quot;]
<strong>输出：</strong>[&quot;c&quot;,&quot;o&quot;]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ol>
	<li><code>1 &lt;= A.length &lt;= 100</code></li>
	<li><code>1 &lt;= A[i].length &lt;= 100</code></li>
	<li><code>A[i][j]</code> 是小写字母</li>
</ol>

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
        for (auto word : words)
        {
            vector<int> t(26);
            for (char c : word)
                ++t[c - 'a'];
            for (int i = 0; i < 26; ++i)
                freq[i] = min(freq[i], t[i]);
        }
        vector<string> res;
        for (int i = 0; i < 26; i++)
        {
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
