# [30. Substring with Concatenation of All Words](https://leetcode.com/problems/substring-with-concatenation-of-all-words)

[中文文档](/solution/0000-0099/0030.Substring%20with%20Concatenation%20of%20All%20Words/README.md)

<!-- tags:Hash Table,String,Sliding Window -->

<!-- difficulty:Hard -->

## Description

<p>You are given a string <code>s</code> and an array of strings <code>words</code>. All the strings of <code>words</code> are of <strong>the same length</strong>.</p>

<p>A <strong>concatenated string</strong> is a string that exactly contains all the strings of any permutation of <code>words</code> concatenated.</p>

<ul>
	<li>For example, if <code>words = [&quot;ab&quot;,&quot;cd&quot;,&quot;ef&quot;]</code>, then <code>&quot;abcdef&quot;</code>, <code>&quot;abefcd&quot;</code>, <code>&quot;cdabef&quot;</code>, <code>&quot;cdefab&quot;</code>, <code>&quot;efabcd&quot;</code>, and <code>&quot;efcdab&quot;</code> are all concatenated strings. <code>&quot;acdbef&quot;</code> is not a concatenated string because it is not the concatenation of any permutation of <code>words</code>.</li>
</ul>

<p>Return an array of <em>the starting indices</em> of all the concatenated substrings in <code>s</code>. You can return the answer in <strong>any order</strong>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;barfoothefoobarman&quot;, words = [&quot;foo&quot;,&quot;bar&quot;]</span></p>

<p><strong>Output:</strong> <span class="example-io">[0,9]</span></p>

<p><strong>Explanation:</strong></p>

<p>The substring starting at 0 is <code>&quot;barfoo&quot;</code>. It is the concatenation of <code>[&quot;bar&quot;,&quot;foo&quot;]</code> which is a permutation of <code>words</code>.<br />
The substring starting at 9 is <code>&quot;foobar&quot;</code>. It is the concatenation of <code>[&quot;foo&quot;,&quot;bar&quot;]</code> which is a permutation of <code>words</code>.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;wordgoodgoodgoodbestword&quot;, words = [&quot;word&quot;,&quot;good&quot;,&quot;best&quot;,&quot;word&quot;]</span></p>

<p><strong>Output:</strong> <span class="example-io">[]</span></p>

<p><strong>Explanation:</strong></p>

<p>There is no concatenated substring.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;barfoofoobarthefoobarman&quot;, words = [&quot;bar&quot;,&quot;foo&quot;,&quot;the&quot;]</span></p>

<p><strong>Output:</strong> <span class="example-io">[6,9,12]</span></p>

<p><strong>Explanation:</strong></p>

<p>The substring starting at 6 is <code>&quot;foobarthe&quot;</code>. It is the concatenation of <code>[&quot;foo&quot;,&quot;bar&quot;,&quot;the&quot;]</code>.<br />
The substring starting at 9 is <code>&quot;barthefoo&quot;</code>. It is the concatenation of <code>[&quot;bar&quot;,&quot;the&quot;,&quot;foo&quot;]</code>.<br />
The substring starting at 12 is <code>&quot;thefoobar&quot;</code>. It is the concatenation of <code>[&quot;the&quot;,&quot;foo&quot;,&quot;bar&quot;]</code>.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>4</sup></code></li>
	<li><code>1 &lt;= words.length &lt;= 5000</code></li>
	<li><code>1 &lt;= words[i].length &lt;= 30</code></li>
	<li><code>s</code> and <code>words[i]</code> consist of lowercase English letters.</li>
</ul>

## Solutions

### Solution 1: Hash Table + Sliding Window

We use a hash table $cnt$ to count the number of times each word appears in $words$, and use a hash table $cnt1$ to count the number of times each word appears in the current sliding window. We denote the length of the string $s$ as $m$, the number of words in the string array $words$ as $n$, and the length of each word as $k$.

We can enumerate the starting point $i$ of the sliding window, where $0 \lt i < k$. For each starting point, we maintain a sliding window with the left boundary as $l$, the right boundary as $r$, and the number of words in the sliding window as $t$. Additionally, we use a hash table $cnt1$ to count the number of times each word appears in the sliding window.

Each time, we extract the string $s[r:r+k]$. If $s[r:r+k]$ is not in the hash table $cnt$, it means that the words in the current sliding window are not valid. We update the left boundary $l$ to $r$, clear the hash table $cnt1$, and reset the word count $t$ to 0. If $s[r:r+k]$ is in the hash table $cnt$, it means that the words in the current sliding window are valid. We increase the word count $t$ by 1, and increase the count of $s[r:r+k]$ in the hash table $cnt1$ by 1. If $cnt1[s[r:r+k]]$ is greater than $cnt[s[r:r+k]]$, it means that $s[r:r+k]$ appears too many times in the current sliding window. We need to move the left boundary $l$ to the right until $cnt1[s[r:r+k]] = cnt[s[r:r+k]]$. If $t = n$, it means that the words in the current sliding window are exactly valid, and we add the left boundary $l$ to the answer array.

The time complexity is $O(m \times k)$, and the space complexity is $O(n \times k)$. Here, $m$ and $n$ are the lengths of the string $s$ and the string array $words$ respectively, and $k$ is the length of the words in the string array $words$.

<!-- tabs:start -->

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
                w = s[r : r + k]
                r += k
                if w not in cnt:
                    l = r
                    cnt1.clear()
                    t = 0
                    continue
                cnt1[w] += 1
                t += 1
                while cnt1[w] > cnt[w]:
                    remove = s[l : l + k]
                    l += k
                    cnt1[remove] -= 1
                    t -= 1
                if t == n:
                    ans.append(l)
        return ans
```

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

```php
class Solution {
    /**
     * @param String $s
     * @param String[] $words
     * @return Integer[]
     */
    function findSubstring($s, $words) {
        $cnt = [];
        foreach ($words as $w) {
            if (!isset($cnt[$w])) {
                $cnt[$w] = 1;
            } else {
                $cnt[$w]++;
            }
        }
        $m = strlen($s);
        $n = count($words);
        $k = strlen($words[0]);
        $ans = [];
        for ($i = 0; $i < $k; ++$i) {
            $cnt1 = [];
            $l = $i;
            $r = $i;
            $t = 0;
            while ($r + $k <= $m) {
                $w = substr($s, $r, $k);
                $r += $k;
                if (!array_key_exists($w, $cnt)) {
                    $cnt1 = [];
                    $l = $r;
                    $t = 0;
                    continue;
                }
                if (!isset($cnt1[$w])) {
                    $cnt1[$w] = 1;
                } else {
                    $cnt1[$w]++;
                }
                ++$t;
                while ($cnt1[$w] > $cnt[$w]) {
                    $remove = substr($s, $l, $k);
                    $l += $k;
                    $cnt1[$remove]--;
                    $t--;
                }
                if ($t == $n) {
                    $ans[] = $l;
                }
            }
        }
        return $ans;
    }
}
```

<!-- tabs:end -->

### Solution 2

<!-- tabs:start -->

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

<!-- tabs:end -->

<!-- end -->
