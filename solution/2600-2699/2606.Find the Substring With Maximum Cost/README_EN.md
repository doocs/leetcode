# [2606. Find the Substring With Maximum Cost](https://leetcode.com/problems/find-the-substring-with-maximum-cost)

[中文文档](/solution/2600-2699/2606.Find%20the%20Substring%20With%20Maximum%20Cost/README.md)

## Description

<p>You are given a string <code>s</code>, a string <code>chars</code> of <strong>distinct</strong> characters and an integer array <code>vals</code> of the same length as <code>chars</code>.</p>

<p>The <strong>cost of the substring </strong>is the sum of the values of each character in the substring. The cost of an empty string is considered <code>0</code>.</p>

<p>The <strong>value of the character </strong>is defined in the following way:</p>

<ul>
	<li>If the character is not in the string <code>chars</code>, then its value is its corresponding position <strong>(1-indexed)</strong> in the alphabet.
    <ul>
    	<li>For example, the value of <code>&#39;a&#39;</code> is <code>1</code>, the value of <code>&#39;b&#39;</code> is <code>2</code>, and so on. The value of <code>&#39;z&#39;</code> is <code>26</code>.</li>
    </ul>
    </li>
    <li>Otherwise, assuming <code>i</code> is the index where the character occurs in the string <code>chars</code>, then its value is <code>vals[i]</code>.</li>
</ul>

<p>Return <em>the maximum cost among all substrings of the string</em> <code>s</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;adaa&quot;, chars = &quot;d&quot;, vals = [-1000]
<strong>Output:</strong> 2
<strong>Explanation:</strong> The value of the characters &quot;a&quot; and &quot;d&quot; is 1 and -1000 respectively.
The substring with the maximum cost is &quot;aa&quot; and its cost is 1 + 1 = 2.
It can be proven that 2 is the maximum cost.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;abc&quot;, chars = &quot;abc&quot;, vals = [-1,-1,-1]
<strong>Output:</strong> 0
<strong>Explanation:</strong> The value of the characters &quot;a&quot;, &quot;b&quot; and &quot;c&quot; is -1, -1, and -1 respectively.
The substring with the maximum cost is the empty substring &quot;&quot; and its cost is 0.
It can be proven that 0 is the maximum cost.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s</code> consist of lowercase English letters.</li>
	<li><code>1 &lt;= chars.length &lt;= 26</code></li>
	<li><code>chars</code> consist of <strong>distinct</strong> lowercase English letters.</li>
	<li><code>vals.length == chars.length</code></li>
	<li><code>-1000 &lt;= vals[i] &lt;= 1000</code></li>
</ul>

## Solutions

**Solution 1: Prefix sum + Maintain the minimum prefix sum**

According to the description of the problem, we traverse each character $c$ in the string $s$, obtain its corresponding value $v$, and then update the current prefix sum $tot=tot+v$. Then, the cost of the maximum cost substring ending with $c$ is $tot$ minus the minimum prefix sum $mi$, that is, $tot-mi$. We update the answer $ans=max(ans,tot-mi)$ and maintain the minimum prefix sum $mi=min(mi,tot)$.

After the traversal is over, return the answer $ans$.

The time complexity is $O(n)$, and the space complexity is $O(C)$. Where $n$ is the length of the string $s$; and $C$ is the size of the character set, which is $26$ in this problem.

**Solution 2: Convert to the maximum subarray sum problem**

We can consider the value $v$ of each character $c$ as an integer, so the actual problem is to solve the maximum subarray sum problem.

We use the variable $f$ to maintain the cost of the maximum cost substring ending with the current character $c$. Each time we traverse to a character $c$, we update $f=max(f, 0) + v$. Then we update the answer $ans=max(ans,f)$.

The time complexity is $O(n)$, and the space complexity is $O(C)$. Where $n$ is the length of the string $s$; and $C$ is the size of the character set, which is $26$ in this problem.

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def maximumCostSubstring(self, s: str, chars: str, vals: List[int]) -> int:
        d = {c: v for c, v in zip(chars, vals)}
        ans = tot = mi = 0
        for c in s:
            v = d.get(c, ord(c) - ord('a') + 1)
            tot += v
            ans = max(ans, tot - mi)
            mi = min(mi, tot)
        return ans
```

```python
class Solution:
    def maximumCostSubstring(self, s: str, chars: str, vals: List[int]) -> int:
        d = {c: v for c, v in zip(chars, vals)}
        ans = f = 0
        for c in s:
            v = d.get(c, ord(c) - ord('a') + 1)
            f = max(f, 0) + v
            ans = max(ans, f)
        return ans
```

### **Java**

```java
class Solution {
    public int maximumCostSubstring(String s, String chars, int[] vals) {
        int[] d = new int[26];
        for (int i = 0; i < d.length; ++i) {
            d[i] = i + 1;
        }
        int m = chars.length();
        for (int i = 0; i < m; ++i) {
            d[chars.charAt(i) - 'a'] = vals[i];
        }
        int ans = 0, tot = 0, mi = 0;
        int n = s.length();
        for (int i = 0; i < n; ++i) {
            int v = d[s.charAt(i) - 'a'];
            tot += v;
            ans = Math.max(ans, tot - mi);
            mi = Math.min(mi, tot);
        }
        return ans;
    }
}
```

```java
class Solution {
    public int maximumCostSubstring(String s, String chars, int[] vals) {
        int[] d = new int[26];
        for (int i = 0; i < d.length; ++i) {
            d[i] = i + 1;
        }
        int m = chars.length();
        for (int i = 0; i < m; ++i) {
            d[chars.charAt(i) - 'a'] = vals[i];
        }
        int ans = 0, f = 0;
        int n = s.length();
        for (int i = 0; i < n; ++i) {
            int v = d[s.charAt(i) - 'a'];
            f = Math.max(f, 0) + v;
            ans = Math.max(ans, f);
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int maximumCostSubstring(string s, string chars, vector<int>& vals) {
        vector<int> d(26, -1);
        int m = chars.size();
        for (int i = 0; i < m; ++i) {
            d[chars[i] - 'a'] = i;
        }
        int ans = 0, tot = 0, mi = 0;
        for (char& c : s) {
            int j = c - 'a';
            int v = d[j] == -1 ? j + 1 : vals[d[j]];
            tot += v;
            ans = max(ans, tot - mi);
            mi = min(mi, tot);
        }
        return ans;
    }
};
```

```cpp
class Solution {
public:
    int maximumCostSubstring(string s, string chars, vector<int>& vals) {
        vector<int> d(26);
        iota(d.begin(), d.end(), 1);
        int m = chars.size();
        for (int i = 0; i < m; ++i) {
            d[chars[i] - 'a'] = vals[i];
        }
        int ans = 0, tot = 0, mi = 0;
        for (char& c : s) {
            int v = d[c - 'a'];
            tot += v;
            ans = max(ans, tot - mi);
            mi = min(mi, tot);
        }
        return ans;
    }
};
```

### **Go**

```go
func maximumCostSubstring(s string, chars string, vals []int) (ans int) {
	d := [26]int{}
	for i := range d {
		d[i] = i + 1
	}
	for i, c := range chars {
		d[c-'a'] = vals[i]
	}
	tot, mi := 0, 0
	for _, c := range s {
		v := d[c-'a']
		tot += v
		ans = max(ans, tot-mi)
		mi = min(mi, tot)
	}
	return
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}
```

```go
func maximumCostSubstring(s string, chars string, vals []int) (ans int) {
	d := [26]int{}
	for i := range d {
		d[i] = i + 1
	}
	for i, c := range chars {
		d[c-'a'] = vals[i]
	}
	f := 0
	for _, c := range s {
		v := d[c-'a']
		f = max(f, 0) + v
		ans = max(ans, f)
	}
	return
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}
```

### **TypeScript**

```ts
function maximumCostSubstring(
    s: string,
    chars: string,
    vals: number[],
): number {
    const d: number[] = Array.from({ length: 26 }, (_, i) => i + 1);
    for (let i = 0; i < chars.length; ++i) {
        d[chars.charCodeAt(i) - 97] = vals[i];
    }
    let ans = 0;
    let tot = 0;
    let mi = 0;
    for (const c of s) {
        tot += d[c.charCodeAt(0) - 97];
        ans = Math.max(ans, tot - mi);
        mi = Math.min(mi, tot);
    }
    return ans;
}
```

```ts
function maximumCostSubstring(
    s: string,
    chars: string,
    vals: number[],
): number {
    const d: number[] = Array.from({ length: 26 }, (_, i) => i + 1);
    for (let i = 0; i < chars.length; ++i) {
        d[chars.charCodeAt(i) - 97] = vals[i];
    }
    let ans = 0;
    let f = 0;
    for (const c of s) {
        f = Math.max(f, 0) + d[c.charCodeAt(0) - 97];
        ans = Math.max(ans, f);
    }
    return ans;
}
```

### **...**

```

```

<!-- tabs:end -->
