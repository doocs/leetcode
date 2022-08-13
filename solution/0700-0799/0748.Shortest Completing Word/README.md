# [748. 最短补全词](https://leetcode.cn/problems/shortest-completing-word)

[English Version](/solution/0700-0799/0748.Shortest%20Completing%20Word/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个字符串 <code>licensePlate</code> 和一个字符串数组 <code>words</code> ，请你找出&nbsp;<code>words</code> 中的 <strong>最短补全词</strong> 。</p>

<p><strong>补全词 </strong>是一个包含 <code>licensePlate</code> 中所有字母的单词。<strong>忽略</strong>&nbsp;<code>licensePlate</code> 中的 <strong>数字和空格 </strong>。<strong>不区分大小写</strong>。如果某个字母在 <code>licensePlate</code> 中出现不止一次，那么该字母在补全词中的出现次数应当一致或者更多。</p>

<p>例如：<code>licensePlate</code><code> = "aBc 12c"</code>，那么它的补全词应当包含字母 <code>'a'</code>、<code>'b'</code> （忽略大写）和两个 <code>'c'</code> 。可能的 <strong>补全词</strong> 有 <code>"abccdef"</code>、<code>"caaacab"</code> 以及 <code>"cbca"</code> 。</p>

<p>请返回 <code>words</code> 中的 <strong>最短补全词</strong> 。题目数据保证一定存在一个最短补全词。当有多个单词都符合最短补全词的匹配条件时取 <code>words</code> 中 <strong>第一个</strong> 出现的那个。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>licensePlate = "1s3 PSt", words = ["step", "steps", "stripe", "stepple"]
<strong>输出：</strong>"steps"
<strong>解释：</strong>最短补全词应该包括 "s"、"p"、"s"（忽略大小写） 以及 "t"。
"step" 包含 "t"、"p"，但只包含一个 "s"，所以它不符合条件。
"steps" 包含 "t"、"p" 和两个 "s"。
"stripe" 缺一个 "s"。
"stepple" 缺一个 "s"。
因此，"steps" 是唯一一个包含所有字母的单词，也是本例的答案。</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>licensePlate = "1s3 456", words = ["looks", "pest", "stew", "show"]
<strong>输出：</strong>"pest"
<strong>解释：</strong>licensePlate 只包含字母 "s" 。所有的单词都包含字母 "s" ，其中 "pest"、"stew"、和 "show" 三者最短。答案是 "pest" ，因为它是三个单词中在 words 里最靠前的那个。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= licensePlate.length &lt;= 7</code></li>
	<li><code>licensePlate</code> 由数字、大小写字母或空格 <code>' '</code> 组成</li>
	<li><code>1 &lt;= words.length &lt;= 1000</code></li>
	<li><code>1 &lt;= words[i].length &lt;= 15</code></li>
	<li><code>words[i]</code> 由小写英文字母组成</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def shortestCompletingWord(self, licensePlate: str, words: List[str]) -> str:
        def count(word):
            counter = [0] * 26
            for c in word:
                counter[ord(c) - ord('a')] += 1
            return counter

        def check(counter1, counter2):
            for i in range(26):
                if counter1[i] > counter2[i]:
                    return False
            return True

        counter = count(c.lower() for c in licensePlate if c.isalpha())
        ans, n = None, 16
        for word in words:
            if n <= len(word):
                continue
            t = count(word)
            if check(counter, t):
                n = len(word)
                ans = word
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public String shortestCompletingWord(String licensePlate, String[] words) {
        int[] counter = count(licensePlate.toLowerCase());
        String ans = null;
        int n = 16;
        for (String word : words) {
            if (n <= word.length()) {
                continue;
            }
            int[] t = count(word);
            if (check(counter, t)) {
                n = word.length();
                ans = word;
            }
        }
        return ans;
    }

    private int[] count(String word) {
        int[] counter = new int[26];
        for (char c : word.toCharArray()) {
            if (Character.isLetter(c)) {
                ++counter[c - 'a'];
            }

        }
        return counter;
    }

    private boolean check(int[] counter1, int[] counter2) {
        for (int i = 0; i < 26; ++i) {
            if (counter1[i] > counter2[i]) {
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
    string shortestCompletingWord(string licensePlate, vector<string>& words) {
        vector<int> counter = count(licensePlate);
        int n = 16;
        string ans;
        for (auto& word : words) {
            if (n <= word.size()) continue;
            vector<int> t = count(word);
            if (check(counter, t)) {
                n = word.size();
                ans = word;
            }
        }
        return ans;
    }

    vector<int> count(string& word) {
        vector<int> counter(26);
        for (char& c : word)
            if (isalpha(c))
                ++counter[tolower(c) - 'a'];
        return counter;
    }

    bool check(vector<int>& counter1, vector<int>& counter2) {
        for (int i = 0; i < 26; ++i)
            if (counter1[i] > counter2[i])
                return false;
        return true;
    }
};
```

### **Go**

```go
func shortestCompletingWord(licensePlate string, words []string) string {
	count := func(word string) []int {
		counter := make([]int, 26)
		for _, c := range word {
			if unicode.IsLetter(c) {
				counter[c-'a']++
			}
		}
		return counter
	}

	check := func(cnt1, cnt2 []int) bool {
		for i := 0; i < 26; i++ {
			if cnt1[i] > cnt2[i] {
				return false
			}
		}
		return true
	}

	counter := count(strings.ToLower(licensePlate))
	var ans string
	n := 16
	for _, word := range words {
		if n <= len(word) {
			continue
		}
		t := count(word)
		if check(counter, t) {
			n = len(word)
			ans = word
		}
	}
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
