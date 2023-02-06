# [2559. Count Vowel Strings in Ranges](https://leetcode.com/problems/count-vowel-strings-in-ranges)

[中文文档](/solution/2500-2599/2559.Count%20Vowel%20Strings%20in%20Ranges/README.md)

## Description

<p>You are given a <strong>0-indexed</strong> array of strings <code>words</code> and a 2D array of integers <code>queries</code>.</p>

<p>Each query <code>queries[i] = [l<sub>i</sub>, r<sub>i</sub>]</code> asks us to find the number of strings present in the range <code>l<sub>i</sub></code> to <code>r<sub>i</sub></code> (both <strong>inclusive</strong>) of <code>words</code> that start and end with a vowel.</p>

<p>Return <em>an array </em><code>ans</code><em> of size </em><code>queries.length</code><em>, where </em><code>ans[i]</code><em> is the answer to the </em><code>i</code><sup>th</sup><em> query</em>.</p>

<p><strong>Note</strong> that the vowel letters are <code>&#39;a&#39;</code>, <code>&#39;e&#39;</code>, <code>&#39;i&#39;</code>, <code>&#39;o&#39;</code>, and <code>&#39;u&#39;</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> words = [&quot;aba&quot;,&quot;bcb&quot;,&quot;ece&quot;,&quot;aa&quot;,&quot;e&quot;], queries = [[0,2],[1,4],[1,1]]
<strong>Output:</strong> [2,3,0]
<strong>Explanation:</strong> The strings starting and ending with a vowel are &quot;aba&quot;, &quot;ece&quot;, &quot;aa&quot; and &quot;e&quot;.
The answer to the query [0,2] is 2 (strings &quot;aba&quot; and &quot;ece&quot;).
to query [1,4] is 3 (strings &quot;ece&quot;, &quot;aa&quot;, &quot;e&quot;).
to query [1,1] is 0.
We return [2,3,0].
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> words = [&quot;a&quot;,&quot;e&quot;,&quot;i&quot;], queries = [[0,2],[0,1],[2,2]]
<strong>Output:</strong> [3,2,1]
<strong>Explanation:</strong> Every string satisfies the conditions, so we return [3,2,1].</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= words.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= words[i].length &lt;= 40</code></li>
	<li><code>words[i]</code> consists only of lowercase English letters.</li>
	<li><code>sum(words[i].length) &lt;= 3 * 10<sup>5</sup></code></li>
	<li><code>1 &lt;= queries.length &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= l<sub>i</sub> &lt;= r<sub>i</sub> &lt;&nbsp;words.length</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def vowelStrings(self, words: List[str], queries: List[List[int]]) -> List[int]:
        t = [i for i, w in enumerate(words) if w[0] in "aeiou" and w[-1] in "aeiou"]
        return [bisect_left(t, r + 1) - bisect_left(t, l) for l, r in queries]
```

### **Java**

```java
class Solution {
    public int[] vowelStrings(String[] words, int[][] queries) {
        List<Integer> t = new ArrayList<>();
        Set<Character> vowels = Set.of('a', 'e', 'i', 'o', 'u');
        for (int i = 0; i < words.length; ++i) {
            char a = words[i].charAt(0), b = words[i].charAt(words[i].length() - 1);
            if (vowels.contains(a) && vowels.contains(b)) {
                t.add(i);
            }
        }
        int[] ans = new int[queries.length];
        for (int i = 0; i < ans.length; ++i) {
            ans[i] = search(t, queries[i][1] + 1) - search(t, queries[i][0]);
        }
        return ans;
    }

    private int search(List<Integer> nums, int x) {
        int left = 0, right = nums.size();
        while (left < right) {
            int mid = (left + right) >> 1;
            if (nums.get(mid) >= x) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<int> vowelStrings(vector<string>& words, vector<vector<int>>& queries) {
        vector<int> t;
        unordered_set<char> vowels = {'a', 'e', 'i', 'o', 'u'};
        for (int i = 0; i < words.size(); ++i) {
            if (vowels.count(words[i][0]) && vowels.count(words[i].back())) {
                t.push_back(i);
            }
        }
        vector<int> ans;
        for (auto& q : queries) {
            int x = lower_bound(t.begin(), t.end(), q[1] + 1) - lower_bound(t.begin(), t.end(), q[0]);
            ans.push_back(x);
        }
        return ans;
    }
};
```

### **Go**

```go
func vowelStrings(words []string, queries [][]int) (ans []int) {
	vowels := "aeiou"
	t := []int{}
	for i, w := range words {
		if strings.Contains(vowels, w[:1]) && strings.Contains(vowels, w[len(w)-1:]) {
			t = append(t, i)
		}
	}
	for _, q := range queries {
		i := sort.Search(len(t), func(i int) bool { return t[i] >= q[0] })
		j := sort.Search(len(t), func(i int) bool { return t[i] >= q[1]+1 })
		ans = append(ans, j-i)
	}
	return
}
```

### **...**

```

```

<!-- tabs:end -->
