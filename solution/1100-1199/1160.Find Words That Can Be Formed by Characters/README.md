# [1160. 拼写单词](https://leetcode.cn/problems/find-words-that-can-be-formed-by-characters)

[English Version](/solution/1100-1199/1160.Find%20Words%20That%20Can%20Be%20Formed%20by%20Characters/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一份『词汇表』（字符串数组）&nbsp;<code>words</code>&nbsp;和一张『字母表』（字符串）&nbsp;<code>chars</code>。</p>

<p>假如你可以用&nbsp;<code>chars</code>&nbsp;中的『字母』（字符）拼写出 <code>words</code>&nbsp;中的某个『单词』（字符串），那么我们就认为你掌握了这个单词。</p>

<p>注意：每次拼写（指拼写词汇表中的一个单词）时，<code>chars</code> 中的每个字母都只能用一次。</p>

<p>返回词汇表&nbsp;<code>words</code>&nbsp;中你掌握的所有单词的 <strong>长度之和</strong>。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>words = [&quot;cat&quot;,&quot;bt&quot;,&quot;hat&quot;,&quot;tree&quot;], chars = &quot;atach&quot;
<strong>输出：</strong>6
<strong>解释： </strong>
可以形成字符串 &quot;cat&quot; 和 &quot;hat&quot;，所以答案是 3 + 3 = 6。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>words = [&quot;hello&quot;,&quot;world&quot;,&quot;leetcode&quot;], chars = &quot;welldonehoneyr&quot;
<strong>输出：</strong>10
<strong>解释：</strong>
可以形成字符串 &quot;hello&quot; 和 &quot;world&quot;，所以答案是 5 + 5 = 10。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ol>
	<li><code>1 &lt;= words.length &lt;= 1000</code></li>
	<li><code>1 &lt;= words[i].length, chars.length&nbsp;&lt;= 100</code></li>
	<li>所有字符串中都仅包含小写英文字母</li>
</ol>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def countCharacters(self, words: List[str], chars: str) -> int:
        counter = Counter(chars)
        ans = 0
        for word in words:
            cnt = Counter(word)
            if all([counter[c] >= v for c, v in cnt.items()]):
                ans += len(word)
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int countCharacters(String[] words, String chars) {
        int[] counter = count(chars);
        int ans = 0;
        for (String word : words) {
            int[] cnt = count(word);
            if (check(counter, cnt)) {
                ans += word.length();
            }
        }
        return ans;
    }

    private int[] count(String s) {
        int[] counter = new int[26];
        for (char c : s.toCharArray()) {
            ++counter[c - 'a'];
        }
        return counter;
    }

    private boolean check(int[] cnt1, int[] cnt2) {
        for (int i = 0; i < 26; ++i) {
            if (cnt1[i] < cnt2[i]) {
                return false;
            }
        }
        return true;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int countCharacters(vector<string>& words, string chars) {
        vector<int> counter = count(chars);
        int ans = 0;
        for (auto& word : words) {
            vector<int> cnt = count(word);
            if (check(counter, cnt)) ans += word.size();
        }
        return ans;
    }

    vector<int> count(string s) {
        vector<int> counter(26);
        for (char c : s) ++counter[c - 'a'];
        return counter;
    }

    bool check(vector<int>& cnt1, vector<int>& cnt2) {
        for (int i = 0; i < 26; ++i)
            if (cnt1[i] < cnt2[i]) return false;
        return true;
    }
};
```

### **Go**

```go
func countCharacters(words []string, chars string) int {
	counter := count(chars)
	ans := 0
	for _, word := range words {
		cnt := count(word)
		if check(counter, cnt) {
			ans += len(word)
		}
	}
	return ans
}

func count(s string) []int {
	counter := make([]int, 26)
	for _, c := range s {
		counter[c-'a']++
	}
	return counter
}

func check(cnt1, cnt2 []int) bool {
	for i := 0; i < 26; i++ {
		if cnt1[i] < cnt2[i] {
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
