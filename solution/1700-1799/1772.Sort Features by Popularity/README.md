# [1772. 按受欢迎程度排列功能](https://leetcode.cn/problems/sort-features-by-popularity)

[English Version](/solution/1700-1799/1772.Sort%20Features%20by%20Popularity/README_EN.md)

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

<!-- 这里可写通用的实现逻辑 -->

**方法一：哈希表 + 自定义排序**

我们遍历 `responses`，对于 `responses[i]` 中的每个单词，我们用一个哈希表 `ws` 暂存。接下来将 `ws` 中的单词记录到哈希表 `cnt` 中，记录每个单词出现的次数。

接下来，采用自定义排序，将 `features` 中的单词按照出现次数从大到小排序，如果出现次数相同，则按照出现的下标从小到大排序。

时间复杂度 $O(n \times \log n)$，其中 $n$ 为 `features` 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def sortFeatures(self, features: List[str], responses: List[str]) -> List[str]:
        cnt = Counter()
        for r in responses:
            ws = set(r.split())
            for s in ws:
                cnt[s] += 1
        return sorted(features, key=lambda x: -cnt[x])
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public String[] sortFeatures(String[] features, String[] responses) {
        Map<String, Integer> cnt = new HashMap<>();
        for (String r : responses) {
            Set<String> ws = new HashSet<>();
            for (String w : r.split(" ")) {
                ws.add(w);
            }
            for (String w : ws) {
                cnt.put(w, cnt.getOrDefault(w, 0) + 1);
            }
        }
        int n = features.length;
        Integer[] idx = new Integer[n];
        for (int i = 0; i < n; ++i) {
            idx[i] = i;
        }
        Arrays.sort(idx, (i, j) -> {
            int d = cnt.getOrDefault(features[j], 0) - cnt.getOrDefault(features[i], 0);
            return d == 0 ? i - j : d;
        });
        String[] ans = new String[n];
        for (int i = 0; i < n; ++i) {
            ans[i] = features[idx[i]];
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<string> sortFeatures(vector<string>& features, vector<string>& responses) {
        unordered_map<string, int> cnt;
        for (auto& r : responses) {
            stringstream ss(r);
            string t;
            unordered_set<string> ws;
            while (ss >> t) {
                ws.insert(t);
            }
            for (auto& w : ws) {
                cnt[w]++;
            }
        }
        int n = features.size();
        vector<int> idx(n);
        iota(idx.begin(), idx.end(), 0);
        sort(idx.begin(), idx.end(), [&](int i, int j) -> bool {
            int d = cnt[features[i]] - cnt[features[j]];
            return d > 0 || (d == 0 && i < j);
        });
        vector<string> ans(n);
        for (int i = 0; i < n; ++i) {
            ans[i] = features[idx[i]];
        }
        return ans;
    }
};
```

### **Go**

```go
func sortFeatures(features []string, responses []string) []string {
	cnt := map[string]int{}
	for _, r := range responses {
		ws := map[string]bool{}
		for _, s := range strings.Split(r, " ") {
			ws[s] = true
		}
		for w := range ws {
			cnt[w]++
		}
	}
	n := len(features)
	idx := make([]int, n)
	for i := range idx {
		idx[i] = i
	}
	sort.Slice(idx, func(i, j int) bool {
		d := cnt[features[idx[i]]] - cnt[features[idx[j]]]
		return d > 0 || (d == 0 && idx[i] < idx[j])
	})
	ans := make([]string, n)
	for i := range ans {
		ans[i] = features[idx[i]]
	}
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
