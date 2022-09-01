# [30. 串联所有单词的子串](https://leetcode.cn/problems/substring-with-concatenation-of-all-words)

[English Version](/solution/0000-0099/0030.Substring%20with%20Concatenation%20of%20All%20Words/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个字符串&nbsp;<code>s</code><strong>&nbsp;</strong>和一个字符串数组&nbsp;<code>words</code><strong>。</strong>&nbsp;<code>words</code>&nbsp;中所有字符串 <strong>长度相同</strong>。</p>

<p>&nbsp;<code>s</code><strong>&nbsp;</strong>中的 <strong>串联子串</strong> 是指一个包含&nbsp;&nbsp;<code>words</code>&nbsp;中所有字符串以任意顺序排列连接起来的子串。</p>

<ul>
	<li>例如，如果&nbsp;<code>words = ["ab","cd","ef"]</code>， 那么&nbsp;<code>"abcdef"</code>，&nbsp;<code>"abefcd"</code>，<code>"cdabef"</code>，&nbsp;<code>"cdefab"</code>，<code>"efabcd"</code>， 和&nbsp;<code>"efcdab"</code> 都是串联子串。&nbsp;<code>"acdbef"</code> 不是串联子串，因为他不是任何&nbsp;<code>words</code>&nbsp;排列的连接。</li>
</ul>

<p>返回所有串联字串在&nbsp;<code>s</code><strong>&nbsp;</strong>中的开始索引。你可以以 <strong>任意顺序</strong> 返回答案。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>s = "barfoothefoobarman", words = ["foo","bar"]
<strong>输出：</strong><code>[0,9]</code>
<strong>解释：</strong>因为 words.length == 2 同时 words[i].length == 3，连接的子字符串的长度必须为 6。
子串 "barfoo" 开始位置是 0。它是 words 中以 ["bar","foo"] 顺序排列的连接。
子串 "foobar" 开始位置是 9。它是 words 中以 ["foo","bar"] 顺序排列的连接。
输出顺序无关紧要。返回 [9,0] 也是可以的。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>s = "wordgoodgoodgoodbestword", words = ["word","good","best","word"]
<code><strong>输出：</strong>[]</code>
<strong>解释：</strong>因为<strong> </strong>words.length == 4 并且 words[i].length == 4，所以串联子串的长度必须为 16。
s 中没有子串长度为 16 并且等于 words 的任何顺序排列的连接。
所以我们返回一个空数组。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>s = "barfoofoobarthefoobarman", words = ["bar","foo","the"]
<strong>输出：</strong>[6,9,12]
<strong>解释：</strong>因为 words.length == 3 并且 words[i].length == 3，所以串联子串的长度必须为 9。
子串 "foobarthe" 开始位置是 6。它是 words 中以 ["foo","bar","the"] 顺序排列的连接。
子串 "barthefoo" 开始位置是 9。它是 words 中以 ["bar","the","foo"] 顺序排列的连接。
子串 "thefoobar" 开始位置是 12。它是 words 中以 ["the","foo","bar"] 顺序排列的连接。</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>4</sup></code></li>
	<li><code>1 &lt;= words.length &lt;= 5000</code></li>
	<li><code>1 &lt;= words[i].length &lt;= 30</code></li>
	<li><code>words[i]</code>&nbsp;和&nbsp;<code>s</code> 由小写英文字母组成</li>
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
