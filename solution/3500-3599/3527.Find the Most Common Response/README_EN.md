---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3500-3599/3527.Find%20the%20Most%20Common%20Response/README_EN.md
rating: 1282
source: Biweekly Contest 155 Q1
tags:
    - Array
    - Hash Table
    - String
    - Counting
---

<!-- problem:start -->

# [3527. Find the Most Common Response](https://leetcode.com/problems/find-the-most-common-response)

[中文文档](/solution/3500-3599/3527.Find%20the%20Most%20Common%20Response/README.md)

## Description

<!-- description:start -->

<p>You are given a 2D string array <code>responses</code> where each <code>responses[i]</code> is an array of strings representing survey responses from the <code>i<sup>th</sup></code> day.</p>

<p>Return the <strong>most common</strong> response across all days after removing <strong>duplicate</strong> responses within each <code>responses[i]</code>. If there is a tie, return the <em><span data-keyword="lexicographically-smaller-string">lexicographically smallest</span></em> response.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">responses = [[&quot;good&quot;,&quot;ok&quot;,&quot;good&quot;,&quot;ok&quot;],[&quot;ok&quot;,&quot;bad&quot;,&quot;good&quot;,&quot;ok&quot;,&quot;ok&quot;],[&quot;good&quot;],[&quot;bad&quot;]]</span></p>

<p><strong>Output:</strong> <span class="example-io">&quot;good&quot;</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>After removing duplicates within each list, <code>responses = [[&quot;good&quot;, &quot;ok&quot;], [&quot;ok&quot;, &quot;bad&quot;, &quot;good&quot;], [&quot;good&quot;], [&quot;bad&quot;]]</code>.</li>
	<li><code>&quot;good&quot;</code> appears 3 times, <code>&quot;ok&quot;</code> appears 2 times, and <code>&quot;bad&quot;</code> appears 2 times.</li>
	<li>Return <code>&quot;good&quot;</code> because it has the highest frequency.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">responses = [[&quot;good&quot;,&quot;ok&quot;,&quot;good&quot;],[&quot;ok&quot;,&quot;bad&quot;],[&quot;bad&quot;,&quot;notsure&quot;],[&quot;great&quot;,&quot;good&quot;]]</span></p>

<p><strong>Output:</strong> <span class="example-io">&quot;bad&quot;</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>After removing duplicates within each list we have <code>responses = [[&quot;good&quot;, &quot;ok&quot;], [&quot;ok&quot;, &quot;bad&quot;], [&quot;bad&quot;, &quot;notsure&quot;], [&quot;great&quot;, &quot;good&quot;]]</code>.</li>
	<li><code>&quot;bad&quot;</code>, <code>&quot;good&quot;</code>, and <code>&quot;ok&quot;</code> each occur 2 times.</li>
	<li>The output is <code>&quot;bad&quot;</code> because it is the lexicographically smallest amongst the words with the highest frequency.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= responses.length &lt;= 1000</code></li>
	<li><code>1 &lt;= responses[i].length &lt;= 1000</code></li>
	<li><code>1 &lt;= responses[i][j].length &lt;= 10</code></li>
	<li><code>responses[i][j]</code> consists of only lowercase English letters</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Hash Table

We can use a hash table $\textit{cnt}$ to count the occurrences of each response. For the responses of each day, we first remove duplicates, then add each response to the hash table and update its count.

Finally, we iterate through the hash table to find the response with the highest count. If there are multiple responses with the same count, we return the lexicographically smallest one.

The complexity is $O(L)$, and the space complexity is $O(L)$, where $L$ is the total length of all responses.

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
