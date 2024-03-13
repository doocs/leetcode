# [3076. Shortest Uncommon Substring in an Array](https://leetcode.com/problems/shortest-uncommon-substring-in-an-array)

[中文文档](/solution/3000-3099/3076.Shortest%20Uncommon%20Substring%20in%20an%20Array/README.md)

<!-- tags:Trie,Array,Hash Table,String -->

## Description

<p>You are given an array <code>arr</code> of size <code>n</code> consisting of <strong>non-empty</strong> strings.</p>

<p>Find a string array <code>answer</code> of size <code>n</code> such that:</p>

<ul>
	<li><code>answer[i]</code> is the <strong>shortest</strong> <span data-keyword="substring">substring</span> of <code>arr[i]</code> that does <strong>not</strong> occur as a substring in any other string in <code>arr</code>. If multiple such substrings exist, <code>answer[i]</code> should be the <span data-keyword="lexicographically-smaller-string">lexicographically smallest</span>. And if no such substring exists, <code>answer[i]</code> should be an empty string.</li>
</ul>

<p>Return <em>the array </em><code>answer</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> arr = [&quot;cab&quot;,&quot;ad&quot;,&quot;bad&quot;,&quot;c&quot;]
<strong>Output:</strong> [&quot;ab&quot;,&quot;&quot;,&quot;ba&quot;,&quot;&quot;]
<strong>Explanation:</strong> We have the following:
- For the string &quot;cab&quot;, the shortest substring that does not occur in any other string is either &quot;ca&quot; or &quot;ab&quot;, we choose the lexicographically smaller substring, which is &quot;ab&quot;.
- For the string &quot;ad&quot;, there is no substring that does not occur in any other string.
- For the string &quot;bad&quot;, the shortest substring that does not occur in any other string is &quot;ba&quot;.
- For the string &quot;c&quot;, there is no substring that does not occur in any other string.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> arr = [&quot;abc&quot;,&quot;bcd&quot;,&quot;abcd&quot;]
<strong>Output:</strong> [&quot;&quot;,&quot;&quot;,&quot;abcd&quot;]
<strong>Explanation:</strong> We have the following:
- For the string &quot;abc&quot;, there is no substring that does not occur in any other string.
- For the string &quot;bcd&quot;, there is no substring that does not occur in any other string.
- For the string &quot;abcd&quot;, the shortest substring that does not occur in any other string is &quot;abcd&quot;.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == arr.length</code></li>
	<li><code>2 &lt;= n &lt;= 100</code></li>
	<li><code>1 &lt;= arr[i].length &lt;= 20</code></li>
	<li><code>arr[i]</code> consists only of lowercase English letters.</li>
</ul>

## Solutions

### Solution 1: Enumeration

Given the small data scale, we can directly enumerate all substrings of each string and then determine whether it is a substring of other strings.

Specifically, we first enumerate each string `arr[i]`, then enumerate the length $j$ of each substring from small to large, and then enumerate the starting position $l$ of each substring. We can get the current substring as `sub = arr[i][l:l+j]`. Then we determine whether `sub` is a substring of other strings. If it is, we skip the current substring; otherwise, we update the answer.

The time complexity is $O(n^2 \times m^4)$, and the space complexity is $O(m)$. Where $n$ is the length of the string array `arr`, and $m$ is the maximum length of the string. In this problem, $m \le 20$.

<!-- tabs:start -->

```python
class Solution:
    def shortestSubstrings(self, arr: List[str]) -> List[str]:
        ans = [""] * len(arr)
        for i, s in enumerate(arr):
            m = len(s)
            for j in range(1, m + 1):
                for l in range(m - j + 1):
                    sub = s[l : l + j]
                    if not ans[i] or ans[i] > sub:
                        if all(k == i or sub not in t for k, t in enumerate(arr)):
                            ans[i] = sub
                if ans[i]:
                    break
        return ans
```

```java
class Solution {
    public String[] shortestSubstrings(String[] arr) {
        int n = arr.length;
        String[] ans = new String[n];
        Arrays.fill(ans, "");
        for (int i = 0; i < n; ++i) {
            int m = arr[i].length();
            for (int j = 1; j <= m && ans[i].isEmpty(); ++j) {
                for (int l = 0; l <= m - j; ++l) {
                    String sub = arr[i].substring(l, l + j);
                    if (ans[i].isEmpty() || sub.compareTo(ans[i]) < 0) {
                        boolean ok = true;
                        for (int k = 0; k < n && ok; ++k) {
                            if (k != i && arr[k].contains(sub)) {
                                ok = false;
                            }
                        }
                        if (ok) {
                            ans[i] = sub;
                        }
                    }
                }
            }
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    vector<string> shortestSubstrings(vector<string>& arr) {
        int n = arr.size();
        vector<string> ans(n);
        for (int i = 0; i < n; ++i) {
            int m = arr[i].size();
            for (int j = 1; j <= m && ans[i].empty(); ++j) {
                for (int l = 0; l <= m - j; ++l) {
                    string sub = arr[i].substr(l, j);
                    if (ans[i].empty() || sub < ans[i]) {
                        bool ok = true;
                        for (int k = 0; k < n && ok; ++k) {
                            if (k != i && arr[k].find(sub) != string::npos) {
                                ok = false;
                            }
                        }
                        if (ok) {
                            ans[i] = sub;
                        }
                    }
                }
            }
        }
        return ans;
    }
};
```

```go
func shortestSubstrings(arr []string) []string {
	ans := make([]string, len(arr))
	for i, s := range arr {
		m := len(s)
		for j := 1; j <= m && len(ans[i]) == 0; j++ {
			for l := 0; l <= m-j; l++ {
				sub := s[l : l+j]
				if len(ans[i]) == 0 || ans[i] > sub {
					ok := true
					for k, t := range arr {
						if k != i && strings.Contains(t, sub) {
							ok = false
							break
						}
					}
					if ok {
						ans[i] = sub
					}
				}
			}
		}
	}
	return ans
}
```

```ts
function shortestSubstrings(arr: string[]): string[] {
    const n: number = arr.length;
    const ans: string[] = Array(n).fill('');
    for (let i = 0; i < n; ++i) {
        const m: number = arr[i].length;
        for (let j = 1; j <= m && ans[i] === ''; ++j) {
            for (let l = 0; l <= m - j; ++l) {
                const sub: string = arr[i].slice(l, l + j);
                if (ans[i] === '' || sub.localeCompare(ans[i]) < 0) {
                    let ok: boolean = true;
                    for (let k = 0; k < n && ok; ++k) {
                        if (k !== i && arr[k].includes(sub)) {
                            ok = false;
                        }
                    }
                    if (ok) {
                        ans[i] = sub;
                    }
                }
            }
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- end -->
