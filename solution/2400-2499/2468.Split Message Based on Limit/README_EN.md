---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2400-2499/2468.Split%20Message%20Based%20on%20Limit/README_EN.md
rating: 2381
source: Biweekly Contest 91 Q4
tags:
    - String
    - Binary Search
    - Enumeration
---

<!-- problem:start -->

# [2468. Split Message Based on Limit](https://leetcode.com/problems/split-message-based-on-limit)

[中文文档](/solution/2400-2499/2468.Split%20Message%20Based%20on%20Limit/README.md)

## Description

<!-- description:start -->

<p>You are given a string, <code>message</code>, and a positive integer, <code>limit</code>.</p>

<p>You must <strong>split</strong> <code>message</code> into one or more <strong>parts</strong> based on <code>limit</code>. Each resulting part should have the suffix <code>&quot;&lt;a/b&gt;&quot;</code>, where <code>&quot;b&quot;</code> is to be <strong>replaced</strong> with the total number of parts and <code>&quot;a&quot;</code> is to be <strong>replaced</strong> with the index of the part, starting from <code>1</code> and going up to <code>b</code>. Additionally, the length of each resulting part (including its suffix) should be <strong>equal</strong> to <code>limit</code>, except for the last part whose length can be <strong>at most</strong> <code>limit</code>.</p>

<p>The resulting parts should be formed such that when their suffixes are removed and they are all concatenated <strong>in order</strong>, they should be equal to <code>message</code>. Also, the result should contain as few parts as possible.</p>

<p>Return<em> the parts </em><code>message</code><em> would be split into as an array of strings</em>. If it is impossible to split <code>message</code> as required, return<em> an empty array</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> message = &quot;this is really a very awesome message&quot;, limit = 9
<strong>Output:</strong> [&quot;thi&lt;1/14&gt;&quot;,&quot;s i&lt;2/14&gt;&quot;,&quot;s r&lt;3/14&gt;&quot;,&quot;eal&lt;4/14&gt;&quot;,&quot;ly &lt;5/14&gt;&quot;,&quot;a v&lt;6/14&gt;&quot;,&quot;ery&lt;7/14&gt;&quot;,&quot; aw&lt;8/14&gt;&quot;,&quot;eso&lt;9/14&gt;&quot;,&quot;me&lt;10/14&gt;&quot;,&quot; m&lt;11/14&gt;&quot;,&quot;es&lt;12/14&gt;&quot;,&quot;sa&lt;13/14&gt;&quot;,&quot;ge&lt;14/14&gt;&quot;]
<strong>Explanation:</strong>
The first 9 parts take 3 characters each from the beginning of message.
The next 5 parts take 2 characters each to finish splitting message. 
In this example, each part, including the last, has length 9. 
It can be shown it is not possible to split message into less than 14 parts.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> message = &quot;short message&quot;, limit = 15
<strong>Output:</strong> [&quot;short mess&lt;1/2&gt;&quot;,&quot;age&lt;2/2&gt;&quot;]
<strong>Explanation:</strong>
Under the given constraints, the string can be split into two parts: 
- The first part comprises of the first 10 characters, and has a length 15.
- The next part comprises of the last 3 characters, and has a length 8.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= message.length &lt;= 10<sup>4</sup></code></li>
	<li><code>message</code> consists only of lowercase English letters and <code>&#39; &#39;</code>.</li>
	<li><code>1 &lt;= limit &lt;= 10<sup>4</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Enumerate the Number of Segments + Simulation

We denote the length of the string `message` as $n$, and the number of segments as $k$.

According to the problem, if $k > n$, it means that we can divide the string into more than $n$ segments. Since the length of the string is only $n$, dividing it into more than $n$ segments will inevitably lead to some segments with a length of $0$, which can be deleted. Therefore, we only need to limit the range of $k$ to $[1,.. n]$.

We enumerate the number of segments $k$ from small to large. Let the length of $a$ segments in all segments be $sa$, the length of $b$ segments in all segments be $sb$, and the length of all symbols (including angle brackets and slashes) in all segments be $sc$.

Then the value of $sa$ is ${\textstyle \sum_{j=1}^{k}} len(s_j)$, which can be directly obtained through the prefix sum; the value of $sb$ is $len(str(k)) \times k$; and the value of $sc$ is $3 \times k$.

Therefore, the number of characters that can be filled in all segments is $limit\times k - (sa + sb + sc)$. If this value is greater than or equal to $n$, it means that the string can be divided into $k$ segments, and we can directly construct the answer and return it.

The time complexity is $O(n\times \log n)$, where $n$ is the length of the string `message`. Ignoring the space consumption of the answer, the space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def splitMessage(self, message: str, limit: int) -> List[str]:
        n = len(message)
        sa = 0
        for k in range(1, n + 1):
            sa += len(str(k))
            sb = len(str(k)) * k
            sc = 3 * k
            if limit * k - (sa + sb + sc) >= n:
                ans = []
                i = 0
                for j in range(1, k + 1):
                    tail = f'<{j}/{k}>'
                    t = message[i : i + limit - len(tail)] + tail
                    ans.append(t)
                    i += limit - len(tail)
                return ans
        return []
```

#### Java

```java
class Solution {
    public String[] splitMessage(String message, int limit) {
        int n = message.length();
        int sa = 0;
        String[] ans = new String[0];
        for (int k = 1; k <= n; ++k) {
            int lk = (k + "").length();
            sa += lk;
            int sb = lk * k;
            int sc = 3 * k;
            if (limit * k - (sa + sb + sc) >= n) {
                int i = 0;
                ans = new String[k];
                for (int j = 1; j <= k; ++j) {
                    String tail = String.format("<%d/%d>", j, k);
                    String t = message.substring(i, Math.min(n, i + limit - tail.length())) + tail;
                    ans[j - 1] = t;
                    i += limit - tail.length();
                }
                break;
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
    vector<string> splitMessage(string message, int limit) {
        int n = message.size();
        int sa = 0;
        vector<string> ans;
        for (int k = 1; k <= n; ++k) {
            int lk = to_string(k).size();
            sa += lk;
            int sb = lk * k;
            int sc = 3 * k;
            if (k * limit - (sa + sb + sc) >= n) {
                int i = 0;
                for (int j = 1; j <= k; ++j) {
                    string tail = "<" + to_string(j) + "/" + to_string(k) + ">";
                    string t = message.substr(i, limit - tail.size()) + tail;
                    ans.emplace_back(t);
                    i += limit - tail.size();
                }
                break;
            }
        }
        return ans;
    }
};
```

#### Go

```go
func splitMessage(message string, limit int) (ans []string) {
	n := len(message)
	sa := 0
	for k := 1; k <= n; k++ {
		lk := len(strconv.Itoa(k))
		sa += lk
		sb := lk * k
		sc := 3 * k
		if limit*k-(sa+sb+sc) >= n {
			i := 0
			for j := 1; j <= k; j++ {
				tail := "<" + strconv.Itoa(j) + "/" + strconv.Itoa(k) + ">"
				t := message[i:min(i+limit-len(tail), n)] + tail
				ans = append(ans, t)
				i += limit - len(tail)
			}
			break
		}
	}
	return
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
