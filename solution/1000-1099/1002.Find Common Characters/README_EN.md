# [1002. Find Common Characters](https://leetcode.com/problems/find-common-characters)

[中文文档](/solution/1000-1099/1002.Find%20Common%20Characters/README.md)

## Description

<p>Given a string array <code>words</code>, return <em>an array of all characters that show up in all strings within the </em><code>words</code><em> (including duplicates)</em>. You may return the answer in <strong>any order</strong>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<pre><strong>Input:</strong> words = ["bella","label","roller"]
<strong>Output:</strong> ["e","l","l"]
</pre><p><strong class="example">Example 2:</strong></p>
<pre><strong>Input:</strong> words = ["cool","lock","cook"]
<strong>Output:</strong> ["c","o"]
</pre>
<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= words.length &lt;= 100</code></li>
	<li><code>1 &lt;= words[i].length &lt;= 100</code></li>
	<li><code>words[i]</code> consists of lowercase English letters.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def commonChars(self, words: List[str]) -> List[str]:
        cnt = Counter(words[0])
        for w in words:
            ccnt = Counter(w)
            for c in cnt.keys():
                cnt[c] = min(cnt[c], ccnt[c])
        ans = []
        for c, v in cnt.items():
            ans.extend([c] * v)
        return ans
```

### **Java**

```java
class Solution {
    public List<String> commonChars(String[] words) {
        int[] cnt = new int[26];
        Arrays.fill(cnt, 10000);
        for (String w : words) {
            int[] ccnt = new int[26];
            for (int i = 0; i < w.length(); ++i) {
                ++ccnt[w.charAt(i) - 'a'];
            }
            for (int i = 0; i < 26; ++i) {
                cnt[i] = Math.min(cnt[i], ccnt[i]);
            }
        }
        List<String> ans = new ArrayList<>();
        for (int i = 0; i < 26; ++i) {
            while (cnt[i]-- > 0) {
                ans.add(String.valueOf((char) (i + 'a')));
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
    vector<string> commonChars(vector<string>& words) {
        int cnt[26];
        memset(cnt, 0x3f, sizeof(cnt));
        for (auto& w : words) {
            int ccnt[26]{};
            for (char& c : w) {
                ++ccnt[c - 'a'];
            }
            for (int i = 0; i < 26; ++i) {
                cnt[i] = min(cnt[i], ccnt[i]);
            }
        }
        vector<string> ans;
        for (int i = 0; i < 26; ++i) {
            while (cnt[i]--) {
                ans.emplace_back(1, i + 'a');
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func commonChars(words []string) (ans []string) {
	cnt := [26]int{}
	for i := range cnt {
		cnt[i] = 1 << 30
	}
	for _, w := range words {
		ccnt := [26]int{}
		for _, c := range w {
			ccnt[c-'a']++
		}
		for i, v := range cnt {
			cnt[i] = min(v, ccnt[i])
		}
	}
	for i, v := range cnt {
		for v > 0 {
			ans = append(ans, string(i+'a'))
			v--
		}
	}
	return
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}
```

### **TypeScript**

```ts
function commonChars(words: string[]): string[] {
    const freq: number[] = new Array(26).fill(10000);
    for (const word of words) {
        const t: number[] = new Array(26).fill(0);
        for (const c of word.split('')) {
            ++t[c.charCodeAt(0) - 'a'.charCodeAt(0)];
        }
        for (let i = 0; i < 26; ++i) {
            freq[i] = Math.min(freq[i], t[i]);
        }
    }
    const res: string[] = [];
    for (let i = 0; i < 26; ++i) {
        while (freq[i]-- > 0) {
            res.push(String.fromCharCode(i + 'a'.charCodeAt(0)));
        }
    }
    return res;
}
```

### **...**

```

```

<!-- tabs:end -->
