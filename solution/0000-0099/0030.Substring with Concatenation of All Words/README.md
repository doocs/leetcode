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

我们用哈希表 $cnt$ 统计 $words$ 中每个单词出现的次数，用哈希表 $cnt1$ 统计当前滑动窗口中每个单词出现的次数。我们记字符串 $s$ 的长度为 $m$，字符串数组 $words$ 中单词的数量为 $n$，每个单词的长度为 $k$。

我们可以枚举滑动窗口的起点 $i$，其中 $0 \lt i \lt k$。对于每个起点，我们维护一个滑动窗口，左边界为 $l$，右边界为 $r$，滑动窗口中的单词个数为 $t$，另外用一个哈希表 $cnt1$ 统计滑动窗口中每个单词出现的次数。

每一次，我们提取字符串 $s[r:r+k]$，如果 $s[r:r+k]$ 不在哈希表 $cnt$ 中，说明当前滑动窗口中的单词不合法，我们将左边界 $l$ 更新为 $r$，同时将哈希表 $cnt1$ 清空，单词个数 $t$ 重置为 0。如果 $s[r:r+k]$ 在哈希表 $cnt$ 中，说明当前滑动窗口中的单词合法，我们将单词个数 $t$ 加 1，将哈希表 $cnt1$ 中 $s[r:r+k]$ 的次数加 1。如果 $cnt1[s[r:r+k]]$ 大于 $cnt[s[r:r+k]]$，说明当前滑动窗口中 $s[r:r+k]$ 出现的次数过多，我们需要将左边界 $l$ 右移，直到 $cnt1[s[r:r+k]] = cnt[s[r:r+k]]$。如果 $t = n$，说明当前滑动窗口中的单词正好合法，我们将左边界 $l$ 加入答案数组。

时间复杂度 $O(m \times k)$，空间复杂度 $O(n \times k)$。其中 $m$ 和 $n$ 分别是字符串 $s$ 和字符串数组 $words$ 的长度，而 $k$ 是字符串数组 $words$ 中单词的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def findSubstring(self, s: str, words: List[str]) -> List[int]:
        cnt = Counter(words)
        m, n = len(s), len(words)
        k = len(words[0])
        ans = []
        for i in range(k):
            cnt1 = Counter()
            l = r = i
            t = 0
            while r + k <= m:
                w = s[r: r + k]
                r += k
                if w not in cnt:
                    l = r
                    cnt1.clear()
                    t = 0
                    continue
                cnt1[w] += 1
                t += 1
                while cnt1[w] > cnt[w]:
                    remove = s[l: l + k]
                    l += k
                    cnt1[remove] -= 1
                    t -= 1
                if t == n:
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
            cnt.merge(w, 1, Integer::sum);
        }
        int m = s.length(), n = words.length;
        int k = words[0].length();
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < k; ++i) {
            Map<String, Integer> cnt1 = new HashMap<>();
            int l = i, r = i;
            int t = 0;
            while (r + k <= m) {
                String w = s.substring(r, r + k);
                r += k;
                if (!cnt.containsKey(w)) {
                    cnt1.clear();
                    l = r;
                    t = 0;
                    continue;
                }
                cnt1.merge(w, 1, Integer::sum);
                ++t;
                while (cnt1.get(w) > cnt.get(w)) {
                    String remove = s.substring(l, l + k);
                    l += k;
                    cnt1.merge(remove, -1, Integer::sum);
                    --t;
                }
                if (t == n) {
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
        for (auto& w : words) {
            ++cnt[w];
        }
        int m = s.size(), n = words.size(), k = words[0].size();
        vector<int> ans;
        for (int i = 0; i < k; ++i) {
            unordered_map<string, int> cnt1;
            int l = i, r = i;
            int t = 0;
            while (r + k <= m) {
                string w = s.substr(r, k);
                r += k;
                if (!cnt.count(w)) {
                    cnt1.clear();
                    l = r;
                    t = 0;
                    continue;
                }
                ++cnt1[w];
                ++t;
                while (cnt1[w] > cnt[w]) {
                    string remove = s.substr(l, k);
                    l += k;
                    --cnt1[remove];
                    --t;
                }
                if (t == n) {
                    ans.push_back(l);
                }
            }
        }
        return ans;
    }
};
```

```cpp
class Solution {
public:
    vector<int> findSubstring(string s, vector<string>& words) {
        unordered_map<string, int> d;
        for (auto& w : words) ++d[w];
        vector<int> ans;
        int n = s.size(), m = words.size(), k = words[0].size();
        for (int i = 0; i < k; ++i) {
            int cnt = 0;
            unordered_map<string, int> t;
            for (int j = i; j <= n; j += k) {
                if (j - i >= m * k) {
                    auto s1 = s.substr(j - m * k, k);
                    --t[s1];
                    cnt -= d[s1] > t[s1];
                }
                auto s2 = s.substr(j, k);
                ++t[s2];
                cnt += d[s2] >= t[s2];
                if (cnt == m) ans.emplace_back(j - (m - 1) * k);
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func findSubstring(s string, words []string) (ans []int) {
	cnt := map[string]int{}
	for _, w := range words {
		cnt[w]++
	}
	m, n, k := len(s), len(words), len(words[0])
	for i := 0; i < k; i++ {
		cnt1 := map[string]int{}
		l, r, t := i, i, 0
		for r+k <= m {
			w := s[r : r+k]
			r += k
			if _, ok := cnt[w]; !ok {
				l, t = r, 0
				cnt1 = map[string]int{}
				continue
			}
			cnt1[w]++
			t++
			for cnt1[w] > cnt[w] {
				cnt1[s[l:l+k]]--
				l += k
				t--
			}
			if t == n {
				ans = append(ans, l)
			}
		}
	}
	return
}
```

### **C#**

```cs
public class Solution {
    public IList<int> FindSubstring(string s, string[] words) {
        var cnt = new Dictionary<string, int>();
        foreach (var w in words) {
            if (!cnt.ContainsKey(w)) {
                cnt[w] = 0;
            }
            ++cnt[w];
        }
        int m = s.Length, n = words.Length, k = words[0].Length;
        var ans = new List<int>();
        for (int i = 0; i < k; ++i) {
            var cnt1 = new Dictionary<string, int>();
            int l = i, r = i, t = 0;
            while (r + k <= m) {
                var w = s.Substring(r, k);
                r += k;
                if (!cnt.ContainsKey(w)) {
                    cnt1.Clear();
                    t = 0;
                    l = r;
                    continue;
                }
                if (!cnt1.ContainsKey(w)) {
                    cnt1[w] = 0;
                }
                ++cnt1[w];
                ++t;
                while (cnt1[w] > cnt[w]) {
                    --cnt1[s.Substring(l, k)];
                    l += k;
                    --t;
                }
                if (t == n) {
                    ans.Add(l);
                }
            }
        }
        return ans;
    }
}
```

### **TypeScript**

```ts
function findSubstring(s: string, words: string[]): number[] {
    const cnt: Map<string, number> = new Map();
    for (const w of words) {
        cnt.set(w, (cnt.get(w) || 0) + 1);
    }
    const m = s.length;
    const n = words.length;
    const k = words[0].length;
    const ans: number[] = [];
    for (let i = 0; i < k; ++i) {
        const cnt1: Map<string, number> = new Map();
        let l = i;
        let r = i;
        let t = 0;
        while (r + k <= m) {
            const w = s.slice(r, r + k);
            r += k;
            if (!cnt.has(w)) {
                cnt1.clear();
                l = r;
                t = 0;
                continue;
            }
            cnt1.set(w, (cnt1.get(w) || 0) + 1);
            ++t;
            while (cnt1.get(w)! - cnt.get(w)! > 0) {
                const remove = s.slice(l, l + k);
                cnt1.set(remove, cnt1.get(remove)! - 1);
                l += k;
                --t;
            }
            if (t === n) {
                ans.push(l);
            }
        }
    }
    return ans;
}
```

### **...**

```

```

<!-- tabs:end -->
