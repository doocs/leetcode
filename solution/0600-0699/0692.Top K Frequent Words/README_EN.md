# [692. Top K Frequent Words](https://leetcode.com/problems/top-k-frequent-words)

[中文文档](/solution/0600-0699/0692.Top%20K%20Frequent%20Words/README.md)

## Description

<p>Given an array of strings <code>words</code> and an integer <code>k</code>, return <em>the </em><code>k</code><em> most frequent strings</em>.</p>

<p>Return the answer <strong>sorted</strong> by <strong>the frequency</strong> from highest to lowest. Sort the words with the same frequency by their <strong>lexicographical order</strong>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> words = [&quot;i&quot;,&quot;love&quot;,&quot;leetcode&quot;,&quot;i&quot;,&quot;love&quot;,&quot;coding&quot;], k = 2
<strong>Output:</strong> [&quot;i&quot;,&quot;love&quot;]
<strong>Explanation:</strong> &quot;i&quot; and &quot;love&quot; are the two most frequent words.
Note that &quot;i&quot; comes before &quot;love&quot; due to a lower alphabetical order.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> words = [&quot;the&quot;,&quot;day&quot;,&quot;is&quot;,&quot;sunny&quot;,&quot;the&quot;,&quot;the&quot;,&quot;the&quot;,&quot;sunny&quot;,&quot;is&quot;,&quot;is&quot;], k = 4
<strong>Output:</strong> [&quot;the&quot;,&quot;is&quot;,&quot;sunny&quot;,&quot;day&quot;]
<strong>Explanation:</strong> &quot;the&quot;, &quot;is&quot;, &quot;sunny&quot; and &quot;day&quot; are the four most frequent words, with the number of occurrence being 4, 3, 2 and 1 respectively.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= words.length &lt;= 500</code></li>
	<li><code>1 &lt;= words[i].length &lt;= 10</code></li>
	<li><code>words[i]</code> consists of lowercase English letters.</li>
	<li><code>k</code> is in the range <code>[1, The number of <strong>unique</strong> words[i]]</code></li>
</ul>

<p>&nbsp;</p>
<p><strong>Follow-up:</strong> Could you solve it in <code>O(n log(k))</code> time and <code>O(n)</code> extra space?</p>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def topKFrequent(self, words: List[str], k: int) -> List[str]:
        cnt = Counter(words)
        return sorted(cnt, key=lambda x: (-cnt[x], x))[:k]
```

### **Java**

```java
class Solution {
    public List<String> topKFrequent(String[] words, int k) {
        Map<String, Integer> cnt = new HashMap<>();
        for (String v : words) {
            cnt.put(v, cnt.getOrDefault(v, 0) + 1);
        }
        PriorityQueue<String> q = new PriorityQueue<>((a, b) -> {
            int d = cnt.get(a) - cnt.get(b);
            return d == 0 ? b.compareTo(a) : d;
        });
        for (String v : cnt.keySet()) {
            q.offer(v);
            if (q.size() > k) {
                q.poll();
            }
        }
        LinkedList<String> ans = new LinkedList<>();
        while (!q.isEmpty()) {
            ans.addFirst(q.poll());
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<string> topKFrequent(vector<string>& words, int k) {
        unordered_map<string, int> cnt;
        for (auto& v : words) ++cnt[v];
        vector<string> ans;
        for (auto& [key, _] : cnt) ans.emplace_back(key);
        sort(ans.begin(), ans.end(), [&](const string& a, const string& b) -> bool {
            return cnt[a] == cnt[b] ? a < b : cnt[a] > cnt[b];
        });
        ans.erase(ans.begin() + k, ans.end());
        return ans;
    }
};
```

### **Go**

```go
func topKFrequent(words []string, k int) []string {
	cnt := map[string]int{}
	for _, v := range words {
		cnt[v]++
	}
	ans := []string{}
	for v := range cnt {
		ans = append(ans, v)
	}
	sort.Slice(ans, func(i, j int) bool {
		a, b := ans[i], ans[j]
		return cnt[a] > cnt[b] || cnt[a] == cnt[b] && a < b
	})
	return ans[:k]
}
```

### **...**

```

```

<!-- tabs:end -->
