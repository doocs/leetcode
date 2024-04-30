# [1772. 按受欢迎程度排列功能 🔒](https://leetcode.cn/problems/sort-features-by-popularity)

[English Version](/solution/1700-1799/1772.Sort%20Features%20by%20Popularity/README_EN.md)

<!-- tags:数组,哈希表,字符串,排序 -->

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个字符串数组 <code>features</code> ，其中 <code>features[i]</code> 是一个单词，描述你最近参与开发的项目中一个功能的名称。你调查了用户喜欢哪些功能。另给定一个字符串数组 <code>responses</code>，其中 <code>responses[i]</code> 是一个包含以空格分隔的一系列单词的字符串。</p>

<p>你想要按照受欢迎程度排列这些功能。 严格地说，令 <code>appearances(word)</code> 是满足 <code>responses[i]</code> 中包含单词 <code>word</code> 的 <code>i</code> 的个数，则当 <code>appearances(features[x]) > appearances(features[y])</code> 时，第 <code>x</code> 个功能比第 <code>y</code> 个功能更受欢迎。</p>

<p>返回一个数组 <code>sortedFeatures</code> ，包含按受欢迎程度排列的功能名称。当第 <code>x</code>  个功能和第 <code>y</code> 个功能的受欢迎程度相同且 <code>x < y</code> 时，你应当将第 <code>x</code> 个功能放在第 <code>y</code> 个功能之前。</p>

<p> </p>

<p><b>示例 1：</b></p>

<pre>
<strong>输入</strong><b>：</b>features = ["cooler","lock","touch"], responses = ["i like cooler cooler","lock touch cool","locker like touch"]
<strong>输出</strong><b>：</b>["touch","cooler","lock"]
<strong>解释</strong><b>：</b>appearances("cooler") = 1，appearances("lock") = 1，appearances("touch") = 2。由于 "cooler" 和 "lock" 都出现了 1 次，且 "cooler" 在原数组的前面，所以 "cooler" 也应该在结果数组的前面。
</pre>

<p><b>示例 2：</b></p>

<pre>
<strong>输入</strong><b>：</b>features = ["a","aa","b","c"], responses = ["a","a aa","a a a a a","b a"]
<strong>输出</strong><b>：</b>["a","aa","b","c"]
</pre>

<p> </p>

<p><b>提示：</b></p>

<ul>
	<li><code>1 <= features.length <= 10<sup>4</sup></code></li>
	<li><code>1 <= features[i].length <= 10</code></li>
	<li><code>features</code> 不包含重复项。</li>
	<li><code>features[i]</code> 由小写字母构成。</li>
	<li><code>1 <= responses.length <= 10<sup>2</sup></code></li>
	<li><code>1 <= responses[i].length <= 10<sup>3</sup></code></li>
	<li><code>responses[i]</code> 由小写字母和空格组成。</li>
	<li><code>responses[i]</code> 不包含两个连续的空格。</li>
	<li><code>responses[i]</code> 没有前置或后置空格。</li>
</ul>

## 解法

### 方法一：哈希表 + 自定义排序

我们遍历 `responses`，对于 `responses[i]` 中的每个单词，我们用一个哈希表 `vis` 暂存。接下来将 `vis` 中的单词记录到哈希表 `cnt` 中，记录每个单词出现的次数。

接下来，采用自定义排序，将 `features` 中的单词按照出现次数从大到小排序，如果出现次数相同，则按照出现的下标从小到大排序。

时间复杂度 $O(n \times \log n)$，其中 $n$ 为 `features` 的长度。

<!-- tabs:start -->

```python
class Solution:
    def sortFeatures(self, features: List[str], responses: List[str]) -> List[str]:
        cnt = Counter()
        for s in responses:
            for w in set(s.split()):
                cnt[w] += 1
        return sorted(features, key=lambda w: -cnt[w])
```

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

<!-- end -->
