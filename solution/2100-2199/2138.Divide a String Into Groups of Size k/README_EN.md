---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2100-2199/2138.Divide%20a%20String%20Into%20Groups%20of%20Size%20k/README_EN.md
rating: 1273
source: Weekly Contest 276 Q1
tags:
    - String
    - Simulation
---

<!-- problem:start -->

# [2138. Divide a String Into Groups of Size k](https://leetcode.com/problems/divide-a-string-into-groups-of-size-k)

[中文文档](/solution/2100-2199/2138.Divide%20a%20String%20Into%20Groups%20of%20Size%20k/README.md)

## Description

<!-- description:start -->

<p>A string <code>s</code> can be partitioned into groups of size <code>k</code> using the following procedure:</p>

<ul>
	<li>The first group consists of the first <code>k</code> characters of the string, the second group consists of the next <code>k</code> characters of the string, and so on. Each character can be a part of <strong>exactly one</strong> group.</li>
	<li>For the last group, if the string <strong>does not</strong> have <code>k</code> characters remaining, a character <code>fill</code> is used to complete the group.</li>
</ul>

<p>Note that the partition is done so that after removing the <code>fill</code> character from the last group (if it exists) and concatenating all the groups in order, the resultant string should be <code>s</code>.</p>

<p>Given the string <code>s</code>, the size of each group <code>k</code> and the character <code>fill</code>, return <em>a string array denoting the <strong>composition of every group</strong> </em><code>s</code><em> has been divided into, using the above procedure</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;abcdefghi&quot;, k = 3, fill = &quot;x&quot;
<strong>Output:</strong> [&quot;abc&quot;,&quot;def&quot;,&quot;ghi&quot;]
<strong>Explanation:</strong>
The first 3 characters &quot;abc&quot; form the first group.
The next 3 characters &quot;def&quot; form the second group.
The last 3 characters &quot;ghi&quot; form the third group.
Since all groups can be completely filled by characters from the string, we do not need to use fill.
Thus, the groups formed are &quot;abc&quot;, &quot;def&quot;, and &quot;ghi&quot;.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;abcdefghij&quot;, k = 3, fill = &quot;x&quot;
<strong>Output:</strong> [&quot;abc&quot;,&quot;def&quot;,&quot;ghi&quot;,&quot;jxx&quot;]
<strong>Explanation:</strong>
Similar to the previous example, we are forming the first three groups &quot;abc&quot;, &quot;def&quot;, and &quot;ghi&quot;.
For the last group, we can only use the character &#39;j&#39; from the string. To complete this group, we add &#39;x&#39; twice.
Thus, the 4 groups formed are &quot;abc&quot;, &quot;def&quot;, &quot;ghi&quot;, and &quot;jxx&quot;.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 100</code></li>
	<li><code>s</code> consists of lowercase English letters only.</li>
	<li><code>1 &lt;= k &lt;= 100</code></li>
	<li><code>fill</code> is a lowercase English letter.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Simulation

We can directly simulate the process described in the problem statement, dividing the string $s$ into groups of length $k$. For the last group, if it contains fewer than $k$ characters, we use the character $\textit{fill}$ to pad it.

The time complexity is $O(n)$, and the space complexity is $O(n)$, where $n$ is the length of the string $s$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def divideString(self, s: str, k: int, fill: str) -> List[str]:
        return [s[i : i + k].ljust(k, fill) for i in range(0, len(s), k)]
```

#### Java

```java
class Solution {
    public String[] divideString(String s, int k, char fill) {
        int n = s.length();
        String[] ans = new String[(n + k - 1) / k];
        if (n % k != 0) {
            s += String.valueOf(fill).repeat(k - n % k);
        }
        for (int i = 0; i < ans.length; ++i) {
            ans[i] = s.substring(i * k, (i + 1) * k);
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<string> divideString(string s, int k, char fill) {
        int n = s.size();
        if (n % k) {
            s += string(k - n % k, fill);
        }
        vector<string> ans;
        for (int i = 0; i < s.size() / k; ++i) {
            ans.push_back(s.substr(i * k, k));
        }
        return ans;
    }
};
```

#### Go

```go
func divideString(s string, k int, fill byte) (ans []string) {
	n := len(s)
	if n%k != 0 {
		s += strings.Repeat(string(fill), k-n%k)
	}
	for i := 0; i < len(s)/k; i++ {
		ans = append(ans, s[i*k:(i+1)*k])
	}
	return
}
```

#### TypeScript

```ts
function divideString(s: string, k: number, fill: string): string[] {
    const ans: string[] = [];
    for (let i = 0; i < s.length; i += k) {
        ans.push(s.slice(i, i + k).padEnd(k, fill));
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
