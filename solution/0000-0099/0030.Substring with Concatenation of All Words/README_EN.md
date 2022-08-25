# [30. Substring with Concatenation of All Words](https://leetcode.com/problems/substring-with-concatenation-of-all-words)

[中文文档](/solution/0000-0099/0030.Substring%20with%20Concatenation%20of%20All%20Words/README.md)

## Description

<p>You are given a string <code>s</code> and an array of strings <code>words</code>. All the strings of <code>words</code> are of <strong>the same length</strong>.</p>

<p>A <strong>concatenated substring</strong> in <code>s</code> is a substring that contains all the strings of any permutation of <code>words</code> concatenated.</p>

<ul>
	<li>For example, if <code>words = [&quot;ab&quot;,&quot;cd&quot;,&quot;ef&quot;]</code>, then <code>&quot;abcdef&quot;</code>, <code>&quot;abefcd&quot;</code>, <code>&quot;cdabef&quot;</code>, <code>&quot;cdefab&quot;</code>, <code>&quot;efabcd&quot;</code>, and <code>&quot;efcdab&quot;</code> are all concatenated strings. <code>&quot;acdbef&quot;</code> is not a concatenated substring because it is not the concatenation of any permutation of <code>words</code>.</li>
</ul>

<p>Return <em>the starting indices of all the concatenated substrings in </em><code>s</code>. You can return the answer in <strong>any order</strong>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;barfoothefoobarman&quot;, words = [&quot;foo&quot;,&quot;bar&quot;]
<strong>Output:</strong> [0,9]
<strong>Explanation:</strong> Since words.length == 2 and words[i].length == 3, the concatenated substring has to be of length 6.
The substring starting at 0 is &quot;barfoo&quot;. It is the concatenation of [&quot;bar&quot;,&quot;foo&quot;] which is a permutation of words.
The substring starting at 9 is &quot;foobar&quot;. It is the concatenation of [&quot;foo&quot;,&quot;bar&quot;] which is a permutation of words.
The output order does not matter. Returning [9,0] is fine too.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;wordgoodgoodgoodbestword&quot;, words = [&quot;word&quot;,&quot;good&quot;,&quot;best&quot;,&quot;word&quot;]
<strong>Output:</strong> []
<strong>Explanation:</strong> Since words.length == 4 and words[i].length == 4, the concatenated substring has to be of length 16.
There is no substring of length 16 is s that is equal to the concatenation of any permutation of words.
We return an empty array.
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;barfoofoobarthefoobarman&quot;, words = [&quot;bar&quot;,&quot;foo&quot;,&quot;the&quot;]
<strong>Output:</strong> [6,9,12]
<strong>Explanation:</strong> Since words.length == 3 and words[i].length == 3, the concatenated substring has to be of length 9.
The substring starting at 6 is &quot;foobarthe&quot;. It is the concatenation of [&quot;foo&quot;,&quot;bar&quot;,&quot;the&quot;] which is a permutation of words.
The substring starting at 9 is &quot;barthefoo&quot;. It is the concatenation of [&quot;bar&quot;,&quot;the&quot;,&quot;foo&quot;] which is a permutation of words.
The substring starting at 12 is &quot;thefoobar&quot;. It is the concatenation of [&quot;the&quot;,&quot;foo&quot;,&quot;bar&quot;] which is a permutation of words.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>4</sup></code></li>
	<li><code>1 &lt;= words.length &lt;= 5000</code></li>
	<li><code>1 &lt;= words[i].length &lt;= 30</code></li>
	<li><code>s</code> and <code>words[i]</code> consist of lowercase English letters.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

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
