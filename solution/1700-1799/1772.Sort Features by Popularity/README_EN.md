---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1700-1799/1772.Sort%20Features%20by%20Popularity/README_EN.md
tags:
    - Array
    - Hash Table
    - String
    - Sorting
---

<!-- problem:start -->

# [1772. Sort Features by Popularity 🔒](https://leetcode.com/problems/sort-features-by-popularity)

[中文文档](/solution/1700-1799/1772.Sort%20Features%20by%20Popularity/README.md)

## Description

<!-- description:start -->

<p>You are given a string array <code>features</code> where <code>features[i]</code> is a single word that represents the name of a feature of the latest product you are working on. You have made a survey where users have reported which features they like. You are given a string array <code>responses</code>, where each <code>responses[i]</code> is a string containing space-separated words.</p>

<p>The <strong>popularity</strong> of a feature is the number of <code>responses[i]</code> that contain the feature. You want to sort the features in non-increasing order by their popularity. If two features have the same popularity, order them by their original index in <code>features</code>. Notice that one response could contain the same feature multiple times; this feature is only counted once in its popularity.</p>

<p>Return <em>the features in sorted order.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> features = [&quot;cooler&quot;,&quot;lock&quot;,&quot;touch&quot;], responses = [&quot;i like cooler cooler&quot;,&quot;lock touch cool&quot;,&quot;locker like touch&quot;]
<strong>Output:</strong> [&quot;touch&quot;,&quot;cooler&quot;,&quot;lock&quot;]
<strong>Explanation:</strong> appearances(&quot;cooler&quot;) = 1, appearances(&quot;lock&quot;) = 1, appearances(&quot;touch&quot;) = 2. Since &quot;cooler&quot; and &quot;lock&quot; both had 1 appearance, &quot;cooler&quot; comes first because &quot;cooler&quot; came first in the features array.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> features = [&quot;a&quot;,&quot;aa&quot;,&quot;b&quot;,&quot;c&quot;], responses = [&quot;a&quot;,&quot;a aa&quot;,&quot;a a a a a&quot;,&quot;b a&quot;]
<strong>Output:</strong> [&quot;a&quot;,&quot;aa&quot;,&quot;b&quot;,&quot;c&quot;]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= features.length &lt;= 10<sup>4</sup></code></li>
	<li><code>1 &lt;= features[i].length &lt;= 10</code></li>
	<li><code>features</code> contains no duplicates.</li>
	<li><code>features[i]</code> consists of lowercase letters.</li>
	<li><code>1 &lt;= responses.length &lt;= 10<sup>2</sup></code></li>
	<li><code>1 &lt;= responses[i].length &lt;= 10<sup>3</sup></code></li>
	<li><code>responses[i]</code> consists of lowercase letters and spaces.</li>
	<li><code>responses[i]</code> contains no two consecutive spaces.</li>
	<li><code>responses[i]</code> has no leading or trailing spaces.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Hash Table + Custom Sorting

We traverse `responses`, and for each word in `responses[i]`, we temporarily store it in a hash table `vis`. Next, we record the words in `vis` into the hash table `cnt`, recording the number of times each word appears.

Next, we use custom sorting to sort the words in `features` in descending order of occurrence. If the number of occurrences is the same, we sort them in ascending order of the index where they appear.

The time complexity is $O(n \times \log n)$, where $n$ is the length of `features`.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def sortFeatures(self, features: List[str], responses: List[str]) -> List[str]:
        cnt = Counter()
        for s in responses:
            for w in set(s.split()):
                cnt[w] += 1
        return sorted(features, key=lambda w: -cnt[w])
```

#### Java

```java
class Solution {
    public String[] sortFeatures(String[] features, String[] responses) {
        Map<String, Integer> cnt = new HashMap<>();
        for (String s : responses) {
            Set<String> vis = new HashSet<>();
            for (String w : s.split(" ")) {
                if (vis.add(w)) {
                    cnt.merge(w, 1, Integer::sum);
                }
            }
        }
        int n = features.length;
        Integer[] idx = new Integer[n];
        for (int i = 0; i < n; i++) {
            idx[i] = i;
        }
        Arrays.sort(idx, (i, j) -> {
            int x = cnt.getOrDefault(features[i], 0);
            int y = cnt.getOrDefault(features[j], 0);
            return x == y ? i - j : y - x;
        });
        String[] ans = new String[n];
        for (int i = 0; i < n; i++) {
            ans[i] = features[idx[i]];
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<string> sortFeatures(vector<string>& features, vector<string>& responses) {
        unordered_map<string, int> cnt;
        for (auto& s : responses) {
            istringstream iss(s);
            string w;
            unordered_set<string> st;
            while (iss >> w) {
                st.insert(w);
            }
            for (auto& w : st) {
                ++cnt[w];
            }
        }
        int n = features.size();
        vector<int> idx(n);
        iota(idx.begin(), idx.end(), 0);
        sort(idx.begin(), idx.end(), [&](int i, int j) {
            int x = cnt[features[i]], y = cnt[features[j]];
            return x == y ? i < j : x > y;
        });
        vector<string> ans(n);
        for (int i = 0; i < n; ++i) {
            ans[i] = features[idx[i]];
        }
        return ans;
    }
};
```

#### Go

```go
func sortFeatures(features []string, responses []string) []string {
	cnt := map[string]int{}
	for _, s := range responses {
		vis := map[string]bool{}
		for _, w := range strings.Split(s, " ") {
			if !vis[w] {
				cnt[w]++
				vis[w] = true
			}
		}
	}
	sort.SliceStable(features, func(i, j int) bool { return cnt[features[i]] > cnt[features[j]] })
	return features
}
```

#### TypeScript

```ts
function sortFeatures(features: string[], responses: string[]): string[] {
    const cnt: Map<string, number> = new Map();
    for (const s of responses) {
        const vis: Set<string> = new Set();
        for (const w of s.split(' ')) {
            if (vis.has(w)) {
                continue;
            }
            vis.add(w);
            cnt.set(w, (cnt.get(w) || 0) + 1);
        }
    }
    const n = features.length;
    const idx: number[] = Array.from({ length: n }, (_, i) => i);
    idx.sort((i, j) => {
        const x = cnt.get(features[i]) || 0;
        const y = cnt.get(features[j]) || 0;
        return x === y ? i - j : y - x;
    });
    return idx.map(i => features[i]);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
