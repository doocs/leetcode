---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2300-2399/2306.Naming%20a%20Company/README_EN.md
rating: 2305
source: Weekly Contest 297 Q4
tags:
    - Bit Manipulation
    - Array
    - Hash Table
    - String
    - Enumeration
---

# [2306. Naming a Company](https://leetcode.com/problems/naming-a-company)

[中文文档](/solution/2300-2399/2306.Naming%20a%20Company/README.md)

## Description

<p>You are given an array of strings <code>ideas</code> that represents a list of names to be used in the process of naming a company. The process of naming a company is as follows:</p>

<ol>
	<li>Choose 2 <strong>distinct</strong> names from <code>ideas</code>, call them <code>idea<sub>A</sub></code> and <code>idea<sub>B</sub></code>.</li>
	<li>Swap the first letters of <code>idea<sub>A</sub></code> and <code>idea<sub>B</sub></code> with each other.</li>
	<li>If <strong>both</strong> of the new names are not found in the original <code>ideas</code>, then the name <code>idea<sub>A</sub> idea<sub>B</sub></code> (the <strong>concatenation</strong> of <code>idea<sub>A</sub></code> and <code>idea<sub>B</sub></code>, separated by a space) is a valid company name.</li>
	<li>Otherwise, it is not a valid name.</li>
</ol>

<p>Return <em>the number of <strong>distinct</strong> valid names for the company</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> ideas = [&quot;coffee&quot;,&quot;donuts&quot;,&quot;time&quot;,&quot;toffee&quot;]
<strong>Output:</strong> 6
<strong>Explanation:</strong> The following selections are valid:
- (&quot;coffee&quot;, &quot;donuts&quot;): The company name created is &quot;doffee conuts&quot;.
- (&quot;donuts&quot;, &quot;coffee&quot;): The company name created is &quot;conuts doffee&quot;.
- (&quot;donuts&quot;, &quot;time&quot;): The company name created is &quot;tonuts dime&quot;.
- (&quot;donuts&quot;, &quot;toffee&quot;): The company name created is &quot;tonuts doffee&quot;.
- (&quot;time&quot;, &quot;donuts&quot;): The company name created is &quot;dime tonuts&quot;.
- (&quot;toffee&quot;, &quot;donuts&quot;): The company name created is &quot;doffee tonuts&quot;.
Therefore, there are a total of 6 distinct company names.

The following are some examples of invalid selections:
- (&quot;coffee&quot;, &quot;time&quot;): The name &quot;toffee&quot; formed after swapping already exists in the original array.
- (&quot;time&quot;, &quot;toffee&quot;): Both names are still the same after swapping and exist in the original array.
- (&quot;coffee&quot;, &quot;toffee&quot;): Both names formed after swapping already exist in the original array.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> ideas = [&quot;lack&quot;,&quot;back&quot;]
<strong>Output:</strong> 0
<strong>Explanation:</strong> There are no valid selections. Therefore, 0 is returned.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= ideas.length &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>1 &lt;= ideas[i].length &lt;= 10</code></li>
	<li><code>ideas[i]</code> consists of lowercase English letters.</li>
	<li>All the strings in <code>ideas</code> are <strong>unique</strong>.</li>
</ul>

## Solutions

### Solution 1: Enumeration Counting

We define $f[i][j]$ to represent the number of strings in $ideas$ that start with the $i$th letter and are not in $ideas$ after being replaced with the $j$th letter. Initially, $f[i][j] = 0$. Additionally, we use a hash table $s$ to record the strings in $ideas$, which allows us to quickly determine whether a string is in $ideas$.

Next, we traverse the strings in $ideas$. For the current string $v$, we enumerate the first letter $j$ after replacement. If the string after $v$ is replaced is not in $ideas$, then we update $f[i][j] = f[i][j] + 1$.

Finally, we traverse the strings in $ideas$ again. For the current string $v$, we enumerate the first letter $j$ after replacement. If the string after $v$ is replaced is not in $ideas$, then we update the answer $ans = ans + f[j][i]$.

The final answer is $ans$.

The time complexity is $O(n \times m \times |\Sigma|)$, and the space complexity is $O(|\Sigma|^2)$. Here, $n$ and $m$ are the number of strings in $ideas$ and the maximum length of the strings, respectively, and $|\Sigma|$ is the character set that appears in the string. In this problem, $|\Sigma| \leq 26$.

<!-- tabs:start -->

```python
class Solution:
    def distinctNames(self, ideas: List[str]) -> int:
        s = set(ideas)
        f = [[0] * 26 for _ in range(26)]
        for v in ideas:
            i = ord(v[0]) - ord('a')
            t = list(v)
            for j in range(26):
                t[0] = chr(ord('a') + j)
                if ''.join(t) not in s:
                    f[i][j] += 1
        ans = 0
        for v in ideas:
            i = ord(v[0]) - ord('a')
            t = list(v)
            for j in range(26):
                t[0] = chr(ord('a') + j)
                if ''.join(t) not in s:
                    ans += f[j][i]
        return ans
```

```java
class Solution {
    public long distinctNames(String[] ideas) {
        Set<String> s = new HashSet<>();
        for (String v : ideas) {
            s.add(v);
        }
        int[][] f = new int[26][26];
        for (String v : ideas) {
            char[] t = v.toCharArray();
            int i = t[0] - 'a';
            for (int j = 0; j < 26; ++j) {
                t[0] = (char) (j + 'a');
                if (!s.contains(String.valueOf(t))) {
                    ++f[i][j];
                }
            }
        }
        long ans = 0;
        for (String v : ideas) {
            char[] t = v.toCharArray();
            int i = t[0] - 'a';
            for (int j = 0; j < 26; ++j) {
                t[0] = (char) (j + 'a');
                if (!s.contains(String.valueOf(t))) {
                    ans += f[j][i];
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
    long long distinctNames(vector<string>& ideas) {
        unordered_set<string> s(ideas.begin(), ideas.end());
        int f[26][26]{};
        for (auto v : ideas) {
            int i = v[0] - 'a';
            for (int j = 0; j < 26; ++j) {
                v[0] = j + 'a';
                if (!s.count(v)) {
                    ++f[i][j];
                }
            }
        }
        long long ans = 0;
        for (auto& v : ideas) {
            int i = v[0] - 'a';
            for (int j = 0; j < 26; ++j) {
                v[0] = j + 'a';
                if (!s.count(v)) {
                    ans += f[j][i];
                }
            }
        }
        return ans;
    }
};
```

```go
func distinctNames(ideas []string) (ans int64) {
	s := map[string]bool{}
	for _, v := range ideas {
		s[v] = true
	}
	f := [26][26]int{}
	for _, v := range ideas {
		i := int(v[0] - 'a')
		t := []byte(v)
		for j := 0; j < 26; j++ {
			t[0] = 'a' + byte(j)
			if !s[string(t)] {
				f[i][j]++
			}
		}
	}

	for _, v := range ideas {
		i := int(v[0] - 'a')
		t := []byte(v)
		for j := 0; j < 26; j++ {
			t[0] = 'a' + byte(j)
			if !s[string(t)] {
				ans += int64(f[j][i])
			}
		}
	}
	return
}
```

```ts
function distinctNames(ideas: string[]): number {
    const s = new Set(ideas);
    const f: number[][] = Array(26)
        .fill(0)
        .map(() => Array(26).fill(0));
    for (const v of s) {
        const i = v.charCodeAt(0) - 'a'.charCodeAt(0);
        const t = [...v];
        for (let j = 0; j < 26; ++j) {
            t[0] = String.fromCharCode('a'.charCodeAt(0) + j);
            if (!s.has(t.join(''))) {
                f[i][j]++;
            }
        }
    }
    let ans = 0;
    for (const v of s) {
        const i = v.charCodeAt(0) - 'a'.charCodeAt(0);
        const t = [...v];
        for (let j = 0; j < 26; ++j) {
            t[0] = String.fromCharCode('a'.charCodeAt(0) + j);
            if (!s.has(t.join(''))) {
                ans += f[j][i];
            }
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- end -->
