---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3500-3599/3527.Find%20the%20Most%20Common%20Response/README.md
tags:
    - 数组
    - 哈希表
    - 字符串
    - 计数
---

<!-- problem:start -->

# [3527. 找到最常见的回答](https://leetcode.cn/problems/find-the-most-common-response)

[English Version](/solution/3500-3599/3527.Find%20the%20Most%20Common%20Response/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个二维字符串数组 <code>responses</code>，其中每个 <code>responses[i]</code> 是一个字符串数组，表示第 <code>i</code>&nbsp;天调查的回答结果。</p>

<p>请返回在对每个 <code>responses[i]</code> 中的回答&nbsp;<strong>去重</strong> 后，所有天数中&nbsp;<strong>最常见&nbsp;</strong>的回答。如果有多个回答出现频率相同，则返回&nbsp;<strong><span data-keyword="lexicographically-smaller-string">字典序最小</span>&nbsp;</strong>的那个回答。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">responses = [["good","ok","good","ok"],["ok","bad","good","ok","ok"],["good"],["bad"]]</span></p>

<p><strong>输出：</strong> <span class="example-io">"good"</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li>每个列表去重后，得到&nbsp;<code>responses = [["good", "ok"], ["ok", "bad", "good"], ["good"], ["bad"]]</code>。</li>
	<li><code>"good"</code> 出现了 3 次，<code>"ok"</code> 出现了 2 次，<code>"bad"</code> 也出现了 2 次。</li>
	<li>返回 <code>"good"</code>，因为它出现的频率最高。</li>
</ul>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">responses = [["good","ok","good"],["ok","bad"],["bad","notsure"],["great","good"]]</span></p>

<p><strong>输出：</strong> <span class="example-io">"bad"</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li>每个列表去重后，<code>responses = [["good", "ok"], ["ok", "bad"], ["bad", "notsure"], ["great", "good"]]</code>。</li>
	<li><code>"bad"</code>、<code>"good"</code> 和 <code>"ok"</code> 都出现了 2 次。</li>
	<li>返回 <code>"bad"</code>，因为它在这些最高频率的词中字典序最小。</li>
</ul>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= responses.length &lt;= 1000</code></li>
	<li><code>1 &lt;= responses[i].length &lt;= 1000</code></li>
	<li><code>1 &lt;= responses[i][j].length &lt;= 10</code></li>
	<li><code>responses[i][j]</code> 仅由小写英文字母组成</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：哈希表

我们可以用一个哈希表 $\textit{cnt}$ 来统计每个回答的出现次数。对于每一天的回答，我们先去重，然后将每个回答加入哈希表中，更新其出现次数。

最后，我们遍历哈希表，找到出现次数最多的回答。如果有多个回答出现次数相同，则返回字典序最小的那个回答。

时间复杂度 $O(L)$，空间复杂度 $O(L)$。其中 $L$ 是所有回答的总长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def findCommonResponse(self, responses: List[List[str]]) -> str:
        cnt = Counter()
        for ws in responses:
            for w in set(ws):
                cnt[w] += 1
        ans = responses[0][0]
        for w, x in cnt.items():
            if cnt[ans] < x or (cnt[ans] == x and w < ans):
                ans = w
        return ans
```

#### Java

```java
class Solution {
    public String findCommonResponse(List<List<String>> responses) {
        Map<String, Integer> cnt = new HashMap<>();
        for (var ws : responses) {
            Set<String> s = new HashSet<>();
            for (var w : ws) {
                if (s.add(w)) {
                    cnt.merge(w, 1, Integer::sum);
                }
            }
        }
        String ans = responses.get(0).get(0);
        for (var e : cnt.entrySet()) {
            String w = e.getKey();
            int v = e.getValue();
            if (cnt.get(ans) < v || (cnt.get(ans) == v && w.compareTo(ans) < 0)) {
                ans = w;
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
    string findCommonResponse(vector<vector<string>>& responses) {
        unordered_map<string, int> cnt;
        for (const auto& ws : responses) {
            unordered_set<string> s;
            for (const auto& w : ws) {
                if (s.insert(w).second) {
                    ++cnt[w];
                }
            }
        }
        string ans = responses[0][0];
        for (const auto& e : cnt) {
            const string& w = e.first;
            int v = e.second;
            if (cnt[ans] < v || (cnt[ans] == v && w < ans)) {
                ans = w;
            }
        }
        return ans;
    }
};
```

#### Go

```go
func findCommonResponse(responses [][]string) string {
	cnt := map[string]int{}
	for _, ws := range responses {
		s := map[string]struct{}{}
		for _, w := range ws {
			if _, ok := s[w]; !ok {
				s[w] = struct{}{}
				cnt[w]++
			}
		}
	}
	ans := responses[0][0]
	for w, v := range cnt {
		if cnt[ans] < v || (cnt[ans] == v && w < ans) {
			ans = w
		}
	}
	return ans
}
```

#### TypeScript

```ts
function findCommonResponse(responses: string[][]): string {
    const cnt = new Map<string, number>();
    for (const ws of responses) {
        const s = new Set<string>();
        for (const w of ws) {
            if (!s.has(w)) {
                s.add(w);
                cnt.set(w, (cnt.get(w) ?? 0) + 1);
            }
        }
    }
    let ans = responses[0][0];
    for (const [w, v] of cnt) {
        const best = cnt.get(ans)!;
        if (best < v || (best === v && w < ans)) {
            ans = w;
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
