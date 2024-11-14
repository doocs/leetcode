---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0600-0699/0692.Top%20K%20Frequent%20Words/README.md
tags:
    - 字典树
    - 哈希表
    - 字符串
    - 桶排序
    - 计数
    - 排序
    - 堆（优先队列）
---

<!-- problem:start -->

# [692. 前K个高频单词](https://leetcode.cn/problems/top-k-frequent-words)

[English Version](/solution/0600-0699/0692.Top%20K%20Frequent%20Words/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定一个单词列表&nbsp;<code>words</code>&nbsp;和一个整数 <code>k</code> ，返回前&nbsp;<code>k</code><em>&nbsp;</em>个出现次数最多的单词。</p>

<p>返回的答案应该按单词出现频率由高到低排序。如果不同的单词有相同出现频率， <strong>按字典顺序</strong> 排序。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入:</strong> words = ["i", "love", "leetcode", "i", "love", "coding"], k = 2
<strong>输出:</strong> ["i", "love"]
<strong>解析:</strong> "i" 和 "love" 为出现次数最多的两个单词，均为2次。
    注意，按字母顺序 "i" 在 "love" 之前。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入:</strong> ["the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"], k = 4
<strong>输出:</strong> ["the", "is", "sunny", "day"]
<strong>解析:</strong> "the", "is", "sunny" 和 "day" 是出现次数最多的四个单词，
    出现次数依次为 4, 3, 2 和 1 次。
</pre>

<p>&nbsp;</p>

<p><strong>注意：</strong></p>

<ul>
	<li><code>1 &lt;= words.length &lt;= 500</code></li>
	<li><code>1 &lt;= words[i] &lt;= 10</code></li>
	<li><code>words[i]</code>&nbsp;由小写英文字母组成。</li>
	<li><code>k</code> 的取值范围是&nbsp;<code>[1, <strong>不同</strong> words[i] 的数量]</code></li>
</ul>

<p>&nbsp;</p>

<p><strong>进阶：</strong>尝试以&nbsp;<code>O(n log k)</code> 时间复杂度和&nbsp;<code>O(n)</code> 空间复杂度解决。</p>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：哈希表 + 排序

我们可以用一个哈希表 $\textit{cnt}$ 记录每一个单词出现的次数，然后对哈希表中的键值对按照值进行排序，如果值相同，按照键进行排序。

最后取出前 $k$ 个键即可。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(n)$。其中 $n$ 为单词的个数。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def topKFrequent(self, words: List[str], k: int) -> List[str]:
        cnt = Counter(words)
        return sorted(cnt, key=lambda x: (-cnt[x], x))[:k]
```

#### Java

```java
class Solution {
    public List<String> topKFrequent(String[] words, int k) {
        Map<String, Integer> cnt = new HashMap<>();
        for (String w : words) {
            cnt.merge(w, 1, Integer::sum);
        }
        Arrays.sort(words, (a, b) -> {
            int c1 = cnt.get(a), c2 = cnt.get(b);
            return c1 == c2 ? a.compareTo(b) : c2 - c1;
        });
        List<String> ans = new ArrayList<>();
        for (int i = 0; i < words.length && ans.size() < k; ++i) {
            if (i == 0 || !words[i].equals(words[i - 1])) {
                ans.add(words[i]);
            }
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<string> topKFrequent(vector<string>& words, int k) {
        unordered_map<string, int> cnt;
        for (const auto& w : words) {
            ++cnt[w];
        }
        vector<string> ans;
        for (const auto& [w, _] : cnt) {
            ans.push_back(w);
        }
        ranges::sort(ans, [&](const string& a, const string& b) {
            return cnt[a] > cnt[b] || (cnt[a] == cnt[b] && a < b);
        });
        ans.resize(k);
        return ans;
    }
};
```

#### Go

```go
func topKFrequent(words []string, k int) (ans []string) {
	cnt := map[string]int{}
	for _, w := range words {
		cnt[w]++
	}
	for w := range cnt {
		ans = append(ans, w)
	}
	sort.Slice(ans, func(i, j int) bool { a, b := ans[i], ans[j]; return cnt[a] > cnt[b] || cnt[a] == cnt[b] && a < b })
	return ans[:k]
}
```

#### TypeScript

```ts
function topKFrequent(words: string[], k: number): string[] {
    const cnt: Map<string, number> = new Map();
    for (const w of words) {
        cnt.set(w, (cnt.get(w) || 0) + 1);
    }
    const ans: string[] = Array.from(cnt.keys());
    ans.sort((a, b) => {
        return cnt.get(a) === cnt.get(b) ? a.localeCompare(b) : cnt.get(b)! - cnt.get(a)!;
    });
    return ans.slice(0, k);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
