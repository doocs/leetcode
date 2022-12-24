# [1170. Compare Strings by Frequency of the Smallest Character](https://leetcode.com/problems/compare-strings-by-frequency-of-the-smallest-character)

[中文文档](/solution/1100-1199/1170.Compare%20Strings%20by%20Frequency%20of%20the%20Smallest%20Character/README.md)

## Description

<p>Let the function <code>f(s)</code> be the <strong>frequency of the lexicographically smallest character</strong> in a non-empty string <code>s</code>. For example, if <code>s = &quot;dcce&quot;</code> then <code>f(s) = 2</code> because the lexicographically smallest character is <code>&#39;c&#39;</code>, which has a frequency of 2.</p>

<p>You are given an array of strings <code>words</code> and another array of query strings <code>queries</code>. For each query <code>queries[i]</code>, count the <strong>number of words</strong> in <code>words</code> such that <code>f(queries[i])</code> &lt; <code>f(W)</code> for each <code>W</code> in <code>words</code>.</p>

<p>Return <em>an integer array </em><code>answer</code><em>, where each </em><code>answer[i]</code><em> is the answer to the </em><code>i<sup>th</sup></code><em> query</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> queries = [&quot;cbd&quot;], words = [&quot;zaaaz&quot;]
<strong>Output:</strong> [1]
<strong>Explanation:</strong> On the first query we have f(&quot;cbd&quot;) = 1, f(&quot;zaaaz&quot;) = 3 so f(&quot;cbd&quot;) &lt; f(&quot;zaaaz&quot;).
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> queries = [&quot;bbb&quot;,&quot;cc&quot;], words = [&quot;a&quot;,&quot;aa&quot;,&quot;aaa&quot;,&quot;aaaa&quot;]
<strong>Output:</strong> [1,2]
<strong>Explanation:</strong> On the first query only f(&quot;bbb&quot;) &lt; f(&quot;aaaa&quot;). On the second query both f(&quot;aaa&quot;) and f(&quot;aaaa&quot;) are both &gt; f(&quot;cc&quot;).
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= queries.length &lt;= 2000</code></li>
	<li><code>1 &lt;= words.length &lt;= 2000</code></li>
	<li><code>1 &lt;= queries[i].length, words[i].length &lt;= 10</code></li>
	<li><code>queries[i][j]</code>, <code>words[i][j]</code> consist of lowercase English letters.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def numSmallerByFrequency(self, queries: List[str], words: List[str]) -> List[int]:
        def f(s):
            cnt = Counter(s)
            for c in ascii_lowercase:
                if cnt[c]:
                    return cnt[c]

        arr = [f(s) for s in words]
        arr.sort()
        n = len(arr)
        return [n - bisect_right(arr, f(q)) for q in queries]
```

### **Java**

```java
class Solution {
    public int[] numSmallerByFrequency(String[] queries, String[] words) {
        int n = words.length;
        int[] arr = new int[n];
        for (int i = 0; i < n; ++i) {
            arr[i] = f(words[i]);
        }
        Arrays.sort(arr);
        int m = queries.length;
        int[] ans = new int[m];
        for (int i = 0; i < m; ++i) {
            int x = f(queries[i]);
            ans[i] = n - search(arr, x);
        }
        return ans;
    }

    private int search(int[] arr, int x) {
        int left = 0, right = arr.length;
        while (left < right) {
            int mid = (left + right) >> 1;
            if (arr[mid] > x) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    private int f(String s) {
        int[] cnt = new int[26];
        for (int i = 0; i < s.length(); ++i) {
            ++cnt[s.charAt(i) - 'a'];
        }
        for (int v : cnt) {
            if (v > 0) {
                return v;
            }
        }
        return 0;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<int> numSmallerByFrequency(vector<string>& queries, vector<string>& words) {
        auto f = [](string& s) {
            int cnt[26] = {0};
            for (char& c : s) {
                cnt[c - 'a']++;
            }
            for (int i = 0; i < 26; ++i) {
                if (cnt[i]) {
                    return cnt[i];
                }
            }
            return 0;
        };
        vector<int> arr;
        for (auto& s : words) {
            arr.emplace_back(f(s));
        }
        sort(arr.begin(), arr.end());
        vector<int> ans;
        for (auto& q : queries) {
            int x = f(q);
            ans.emplace_back(arr.end() - upper_bound(arr.begin(), arr.end(), x));
        }
        return ans;
    }
};
```

### **Go**

```go
func numSmallerByFrequency(queries []string, words []string) (ans []int) {
	f := func(s string) int {
		cnt := [26]int{}
		for _, c := range s {
			cnt[c-'a']++
		}
		for _, v := range cnt {
			if v > 0 {
				return v
			}
		}
		return 0
	}
	arr := []int{}
	for _, s := range words {
		arr = append(arr, f(s))
	}
	sort.Ints(arr)
	n := len(arr)
	for _, q := range queries {
		x := f(q)
		ans = append(ans, n-sort.Search(n, func(i int) bool { return arr[i] > x }))
	}
	return
}
```

### **...**

```

```

<!-- tabs:end -->
