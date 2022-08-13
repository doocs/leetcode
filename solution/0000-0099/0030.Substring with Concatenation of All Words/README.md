# [30. 串联所有单词的子串](https://leetcode.cn/problems/substring-with-concatenation-of-all-words)

[English Version](/solution/0000-0099/0030.Substring%20with%20Concatenation%20of%20All%20Words/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个字符串 <code>s</code><strong> </strong>和一些 <strong>长度相同</strong> 的单词 <code>words</code><strong> 。</strong>找出 <code>s</code><strong> </strong>中恰好可以由 <code>words</code><strong> </strong>中所有单词串联形成的子串的起始位置。</p>

<p>注意子串要与 <code>words</code><strong> </strong>中的单词完全匹配，<strong>中间不能有其他字符 </strong>，但不需要考虑 <code>words</code><strong> </strong>中单词串联的顺序。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>s = "barfoothefoobarman", words = ["foo","bar"]
<strong>输出：</strong><code>[0,9]</code>
<strong>解释：</strong>
从索引 0 和 9 开始的子串分别是 "barfoo" 和 "foobar" 。
输出的顺序不重要, [9,0] 也是有效答案。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>s = "wordgoodgoodgoodbestword", words = ["word","good","best","word"]
<code><strong>输出：</strong>[]</code>
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>s = "barfoofoobarthefoobarman", words = ["bar","foo","the"]
<strong>输出：</strong>[6,9,12]
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= s.length <= 10<sup>4</sup></code></li>
	<li><code>s</code> 由小写英文字母组成</li>
	<li><code>1 <= words.length <= 5000</code></li>
	<li><code>1 <= words[i].length <= 30</code></li>
	<li><code>words[i]</code> 由小写英文字母组成</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：哈希表 + 滑动窗口**

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def findSubstring(self, s: str, words: List[str]) -> List[int]:
        cnt = Counter(words)
        sublen = len(words[0])
        n, m = len(s), len(words)
        ans = []
        for i in range(sublen):
            cnt1 = Counter()
            l = r = i
            t = 0
            while r + sublen <= n:
                w = s[r : r + sublen]
                r += sublen
                if w not in cnt:
                    l = r
                    cnt1.clear()
                    t = 0
                    continue
                cnt1[w] += 1
                t += 1
                while cnt1[w] > cnt[w]:
                    remove = s[l : l + sublen]
                    l += sublen
                    cnt1[remove] -= 1
                    t -= 1
                if m == t:
                    ans.append(l)
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public List<Integer> findSubstring(String s, String[] words) {
        Map<String, Integer> cnt = new HashMap<>();
        for (String w : words) {
            cnt.put(w, cnt.getOrDefault(w, 0) + 1);
        }
        int subLen = words[0].length();
        int n = s.length(), m = words.length;
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < subLen; ++i) {
            Map<String, Integer> cnt1 = new HashMap<>();
            int l = i, r = i;
            int t = 0;
            while (r + subLen <= n) {
                String w = s.substring(r, r + subLen);
                r += subLen;
                if (!cnt.containsKey(w)) {
                    l = r;
                    cnt1.clear();
                    t = 0;
                    continue;
                }
                cnt1.put(w, cnt1.getOrDefault(w, 0) + 1);
                ++t;
                while (cnt1.get(w) > cnt.get(w)) {
                    String remove = s.substring(l, l + subLen);
                    l += subLen;
                    cnt1.put(remove, cnt1.get(remove) - 1);
                    --t;
                }
                if (m == t) {
                    ans.add(l);
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
    vector<int> findSubstring(string s, vector<string>& words) {
        unordered_map<string, int> cnt;
        for (auto& w : words) cnt[w]++;
        int subLen = words[0].size();
        int n = s.size(), m = words.size();
        vector<int> ans;
        for (int i = 0; i < subLen; ++i) {
            unordered_map<string, int> cnt1;
            int l = i, r = i;
            int t = 0;
            while (r + subLen <= n) {
                string w = s.substr(r, subLen);
                r += subLen;
                if (!cnt.count(w)) {
                    l = r;
                    t = 0;
                    cnt1.clear();
                    continue;
                }
                cnt1[w]++;
                t++;
                while (cnt1[w] > cnt[w]) {
                    string remove = s.substr(l, subLen);
                    l += subLen;
                    cnt1[remove]--;
                    --t;
                }
                if (t == m) ans.push_back(l);
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func findSubstring(s string, words []string) []int {
	cnt := map[string]int{}
	for _, w := range words {
		cnt[w]++
	}
	subLen := len(words[0])
	n, m := len(s), len(words)
	var ans []int
	for i := 0; i < subLen; i++ {
		cnt1 := map[string]int{}
		l, r := i, i
		t := 0
		for r+subLen <= n {
			w := s[r : r+subLen]
			r += subLen
			if _, ok := cnt[w]; !ok {
				l = r
				t = 0
				cnt1 = map[string]int{}
				continue
			}
			cnt1[w]++
			t++
			for cnt1[w] > cnt[w] {
				remove := s[l : l+subLen]
				l += subLen
				cnt1[remove]--
				t--
			}
			if t == m {
				ans = append(ans, l)
			}
		}
	}
	return ans
}
```

### **C#**

```cs
public class Solution {
    public IList<int> FindSubstring(string s, string[] words) {
        var wordsDict = new Dictionary<string, int>();
        foreach (var word in words)
        {
            if (!wordsDict.ContainsKey(word))
            {
                wordsDict.Add(word, 1);
            }
            else
            {
                ++wordsDict[word];
            }
        }

        var wordOfS = new string[s.Length];
        var wordLength = words[0].Length;
        var wordCount = words.Length;
        for (var i = 0; i <= s.Length - wordLength; ++i)
        {
            var substring = s.Substring(i, wordLength);
            if (wordsDict.ContainsKey(substring))
            {
                wordOfS[i] = substring;
            }
        }

        var result = new List<int>();
        for (var i = 0; i <= s.Length - wordLength * wordCount; ++i)
        {
            var tempDict = new Dictionary<string, int>(wordsDict);
            var tempCount = 0;
            for (var j = i; j <= i + wordLength * (wordCount - 1); j += wordLength)
            {
                if (wordOfS[j] != null && tempDict[wordOfS[j]] > 0)
                {
                    --tempDict[wordOfS[j]];
                    ++tempCount;
                }
                else
                {
                    break;
                }
            }
            if (tempCount == wordCount)
            {
                result.Add(i);
            }
        }

        return result;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
