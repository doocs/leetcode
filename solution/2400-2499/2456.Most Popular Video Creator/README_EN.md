---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2400-2499/2456.Most%20Popular%20Video%20Creator/README_EN.md
rating: 1548
source: Weekly Contest 317 Q2
tags:
    - Array
    - Hash Table
    - String
    - Sorting
    - Heap (Priority Queue)
---

<!-- problem:start -->

# [2456. Most Popular Video Creator](https://leetcode.com/problems/most-popular-video-creator)

[中文文档](/solution/2400-2499/2456.Most%20Popular%20Video%20Creator/README.md)

## Description

<!-- description:start -->

<p>You are given two string arrays <code>creators</code> and <code>ids</code>, and an integer array <code>views</code>, all of length <code>n</code>. The <code>i<sup>th</sup></code> video on a platform was created by <code>creators[i]</code>, has an id of <code>ids[i]</code>, and has <code>views[i]</code> views.</p>

<p>The <strong>popularity</strong> of a creator is the <strong>sum</strong> of the number of views on <strong>all</strong> of the creator&#39;s videos. Find the creator with the <strong>highest</strong> popularity and the id of their <strong>most</strong> viewed video.</p>

<ul>
	<li>If multiple creators have the highest popularity, find all of them.</li>
	<li>If multiple videos have the highest view count for a creator, find the lexicographically <strong>smallest</strong> id.</li>
</ul>

<p>Note: It is possible for different videos to have the same <code>id</code>, meaning that <code>id</code>s do not uniquely identify a video. For example, two videos with the same ID are considered as distinct videos with their own viewcount.</p>

<p>Return<em> </em>a <strong>2D array</strong> of <strong>strings</strong> <code>answer</code> where <code>answer[i] = [creators<sub>i</sub>, id<sub>i</sub>]</code> means that <code>creators<sub>i</sub></code> has the <strong>highest</strong> popularity and <code>id<sub>i</sub></code> is the <strong>id</strong> of their most <strong>popular</strong> video. The answer can be returned in any order.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">creators = [&quot;alice&quot;,&quot;bob&quot;,&quot;alice&quot;,&quot;chris&quot;], ids = [&quot;one&quot;,&quot;two&quot;,&quot;three&quot;,&quot;four&quot;], views = [5,10,5,4]</span></p>

<p><strong>Output:</strong> <span class="example-io">[[&quot;alice&quot;,&quot;one&quot;],[&quot;bob&quot;,&quot;two&quot;]]</span></p>

<p><strong>Explanation:</strong></p>

<p>The popularity of alice is 5 + 5 = 10.<br />
The popularity of bob is 10.<br />
The popularity of chris is 4.<br />
alice and bob are the most popular creators.<br />
For bob, the video with the highest view count is &quot;two&quot;.<br />
For alice, the videos with the highest view count are &quot;one&quot; and &quot;three&quot;. Since &quot;one&quot; is lexicographically smaller than &quot;three&quot;, it is included in the answer.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">creators = [&quot;alice&quot;,&quot;alice&quot;,&quot;alice&quot;], ids = [&quot;a&quot;,&quot;b&quot;,&quot;c&quot;], views = [1,2,2]</span></p>

<p><strong>Output:</strong> <span class="example-io">[[&quot;alice&quot;,&quot;b&quot;]]</span></p>

<p><strong>Explanation:</strong></p>

<p>The videos with id &quot;b&quot; and &quot;c&quot; have the highest view count.<br />
Since &quot;b&quot; is lexicographically smaller than &quot;c&quot;, it is included in the answer.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == creators.length == ids.length == views.length</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= creators[i].length, ids[i].length &lt;= 5</code></li>
	<li><code>creators[i]</code> and <code>ids[i]</code> consist only of lowercase English letters.</li>
	<li><code>0 &lt;= views[i] &lt;= 10<sup>5</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Hash Table

We traverse the three arrays, use a hash table $cnt$ to count the total play count for each creator, and use a hash table $d$ to record the index of the video with the highest play count for each creator.

Then, we traverse the hash table $cnt$ to find the maximum play count $mx$; then we traverse the hash table $cnt$ again to find the creators with a play count of $mx$, and add them to the answer array.

The time complexity is $O(n)$, and the space complexity is $O(n)$. Here, $n$ is the number of videos.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def mostPopularCreator(
        self, creators: List[str], ids: List[str], views: List[int]
    ) -> List[List[str]]:
        cnt = defaultdict(int)
        d = defaultdict(int)
        for k, (c, i, v) in enumerate(zip(creators, ids, views)):
            cnt[c] += v
            if c not in d or views[d[c]] < v or (views[d[c]] == v and ids[d[c]] > i):
                d[c] = k
        mx = max(cnt.values())
        return [[c, ids[d[c]]] for c, x in cnt.items() if x == mx]
```

#### Java

```java
class Solution {
    public List<List<String>> mostPopularCreator(String[] creators, String[] ids, int[] views) {
        int n = ids.length;
        Map<String, Long> cnt = new HashMap<>(n);
        Map<String, Integer> d = new HashMap<>(n);
        for (int k = 0; k < n; ++k) {
            String c = creators[k], i = ids[k];
            long v = views[k];
            cnt.merge(c, v, Long::sum);
            if (!d.containsKey(c) || views[d.get(c)] < v
                || (views[d.get(c)] == v && ids[d.get(c)].compareTo(i) > 0)) {
                d.put(c, k);
            }
        }
        long mx = 0;
        for (long x : cnt.values()) {
            mx = Math.max(mx, x);
        }
        List<List<String>> ans = new ArrayList<>();
        for (var e : cnt.entrySet()) {
            if (e.getValue() == mx) {
                String c = e.getKey();
                ans.add(List.of(c, ids[d.get(c)]));
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
    vector<vector<string>> mostPopularCreator(vector<string>& creators, vector<string>& ids, vector<int>& views) {
        unordered_map<string, long long> cnt;
        unordered_map<string, int> d;
        int n = ids.size();
        for (int k = 0; k < n; ++k) {
            auto c = creators[k], id = ids[k];
            int v = views[k];
            cnt[c] += v;
            if (!d.count(c) || views[d[c]] < v || (views[d[c]] == v && ids[d[c]] > id)) {
                d[c] = k;
            }
        }
        long long mx = 0;
        for (auto& [_, x] : cnt) {
            mx = max(mx, x);
        }
        vector<vector<string>> ans;
        for (auto& [c, x] : cnt) {
            if (x == mx) {
                ans.push_back({c, ids[d[c]]});
            }
        }
        return ans;
    }
};
```

#### Go

```go
func mostPopularCreator(creators []string, ids []string, views []int) (ans [][]string) {
	cnt := map[string]int{}
	d := map[string]int{}
	for k, c := range creators {
		i, v := ids[k], views[k]
		cnt[c] += v
		if j, ok := d[c]; !ok || views[j] < v || (views[j] == v && ids[j] > i) {
			d[c] = k
		}
	}
	mx := 0
	for _, x := range cnt {
		if mx < x {
			mx = x
		}
	}
	for c, x := range cnt {
		if x == mx {
			ans = append(ans, []string{c, ids[d[c]]})
		}
	}
	return
}
```

#### TypeScript

```ts
function mostPopularCreator(creators: string[], ids: string[], views: number[]): string[][] {
    const cnt: Map<string, number> = new Map();
    const d: Map<string, number> = new Map();
    const n = ids.length;
    for (let k = 0; k < n; ++k) {
        const [c, i, v] = [creators[k], ids[k], views[k]];
        cnt.set(c, (cnt.get(c) ?? 0) + v);
        if (!d.has(c) || views[d.get(c)!] < v || (views[d.get(c)!] === v && ids[d.get(c)!] > i)) {
            d.set(c, k);
        }
    }
    const mx = Math.max(...cnt.values());
    const ans: string[][] = [];
    for (const [c, x] of cnt) {
        if (x === mx) {
            ans.push([c, ids[d.get(c)!]]);
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
