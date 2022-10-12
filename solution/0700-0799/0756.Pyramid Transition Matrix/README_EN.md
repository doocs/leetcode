# [756. Pyramid Transition Matrix](https://leetcode.com/problems/pyramid-transition-matrix)

[中文文档](/solution/0700-0799/0756.Pyramid%20Transition%20Matrix/README.md)

## Description

<p>You are stacking blocks to form a pyramid. Each block has a color, which is represented by a single letter. Each row of blocks contains <strong>one less block</strong> than the row beneath it and is centered on top.</p>

<p>To make the pyramid aesthetically pleasing, there are only specific <strong>triangular patterns</strong> that are allowed. A triangular pattern consists of a <strong>single block</strong> stacked on top of <strong>two blocks</strong>. The patterns are given&nbsp;as a list of&nbsp;three-letter strings <code>allowed</code>, where the first two characters of a pattern represent the left and right bottom blocks respectively, and the third character is the top block.</p>

<ul>
	<li>For example, <code>&quot;ABC&quot;</code> represents a triangular pattern with a <code>&#39;C&#39;</code> block stacked on top of an <code>&#39;A&#39;</code> (left) and <code>&#39;B&#39;</code> (right) block. Note that this is different from <code>&quot;BAC&quot;</code> where <code>&#39;B&#39;</code> is on the left bottom and <code>&#39;A&#39;</code> is on the right bottom.</li>
</ul>

<p>You start with a bottom row of blocks <code>bottom</code>, given as a single string, that you <strong>must</strong> use as the base of the pyramid.</p>

<p>Given <code>bottom</code> and <code>allowed</code>, return <code>true</code><em> if you can build the pyramid all the way to the top such that <strong>every triangular pattern</strong> in the pyramid is in </em><code>allowed</code><em>, or </em><code>false</code><em> otherwise</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0700-0799/0756.Pyramid%20Transition%20Matrix/images/pyramid1-grid.jpg" style="width: 600px; height: 232px;" />
<pre>
<strong>Input:</strong> bottom = &quot;BCD&quot;, allowed = [&quot;BCC&quot;,&quot;CDE&quot;,&quot;CEA&quot;,&quot;FFF&quot;]
<strong>Output:</strong> true
<strong>Explanation:</strong> The allowed triangular patterns are shown on the right.
Starting from the bottom (level 3), we can build &quot;CE&quot; on level 2 and then build &quot;A&quot; on level 1.
There are three triangular patterns in the pyramid, which are &quot;BCC&quot;, &quot;CDE&quot;, and &quot;CEA&quot;. All are allowed.
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0700-0799/0756.Pyramid%20Transition%20Matrix/images/pyramid2-grid.jpg" style="width: 600px; height: 359px;" />
<pre>
<strong>Input:</strong> bottom = &quot;AAAA&quot;, allowed = [&quot;AAB&quot;,&quot;AAC&quot;,&quot;BCD&quot;,&quot;BBE&quot;,&quot;DEF&quot;]
<strong>Output:</strong> false
<strong>Explanation:</strong> The allowed triangular patterns are shown on the right.
Starting from the bottom (level 4), there are multiple ways to build level 3, but trying all the possibilites, you will get always stuck before building level 1.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= bottom.length &lt;= 6</code></li>
	<li><code>0 &lt;= allowed.length &lt;= 216</code></li>
	<li><code>allowed[i].length == 3</code></li>
	<li>The letters in all input strings are from the set <code>{&#39;A&#39;, &#39;B&#39;, &#39;C&#39;, &#39;D&#39;, &#39;E&#39;, &#39;F&#39;}</code>.</li>
	<li>All the values of <code>allowed</code> are <strong>unique</strong>.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def pyramidTransition(self, bottom: str, allowed: List[str]) -> bool:
        @cache
        def dfs(s):
            if len(s) == 1:
                return True
            t = []
            for a, b in pairwise(s):
                cs = d[a, b]
                if not cs:
                    return False
                t.append(cs)
            return any(dfs(''.join(nxt)) for nxt in product(*t))

        d = defaultdict(list)
        for a, b, c in allowed:
            d[a, b].append(c)
        return dfs(bottom)
```

### **Java**

```java
class Solution {
    private int[][] f = new int[7][7];
    private Map<String, Boolean> dp = new HashMap<>();

    public boolean pyramidTransition(String bottom, List<String> allowed) {
        for (String s : allowed) {
            int a = s.charAt(0) - 'A', b = s.charAt(1) - 'A';
            f[a][b] |= 1 << (s.charAt(2) - 'A');
        }
        return dfs(bottom, new StringBuilder());
    }

    boolean dfs(String s, StringBuilder t) {
        if (s.length() == 1) {
            return true;
        }
        if (t.length() + 1 == s.length()) {
            return dfs(t.toString(), new StringBuilder());
        }
        String k = s + "." + t.toString();
        if (dp.containsKey(k)) {
            return dp.get(k);
        }
        int a = s.charAt(t.length()) - 'A', b = s.charAt(t.length() + 1) - 'A';
        int cs = f[a][b];
        for (int i = 0; i < 7; ++i) {
            if (((cs >> i) & 1) == 1) {
                t.append((char) ('A' + i));
                if (dfs(s, t)) {
                    dp.put(k, true);
                    return true;
                }
                t.deleteCharAt(t.length() - 1);
            }
        }
        dp.put(k, false);
        return false;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int f[7][7];
    unordered_map<string, bool> dp;

    bool pyramidTransition(string bottom, vector<string>& allowed) {
        memset(f, 0, sizeof f);
        for (auto& s : allowed) {
            int a = s[0] - 'A', b = s[1] - 'A';
            f[a][b] |= 1 << (s[2] - 'A');
        }
        return dfs(bottom, "");
    }

    bool dfs(string& s, string t) {
        if (s.size() == 1) {
            return true;
        }
        if (t.size() + 1 == s.size()) {
            return dfs(t, "");
        }
        string k = s + "." + t;
        if (dp.count(k)) {
            return dp[k];
        }
        int a = s[t.size()] - 'A', b = s[t.size() + 1] - 'A';
        int cs = f[a][b];
        for (int i = 0; i < 7; ++i) {
            if ((cs >> i) & 1) {
                if (dfs(s, t + (char) (i + 'A'))) {
                    dp[k] = true;
                    return true;
                }
            }
        }
        dp[k] = false;
        return false;
    }
};
```

### **Go**

```go
func pyramidTransition(bottom string, allowed []string) bool {
	f := make([][]int, 7)
	for i := range f {
		f[i] = make([]int, 7)
	}
	for _, s := range allowed {
		a, b := s[0]-'A', s[1]-'A'
		f[a][b] |= 1 << (s[2] - 'A')
	}
	dp := map[string]bool{}
	var dfs func(s string, t []byte) bool
	dfs = func(s string, t []byte) bool {
		if len(s) == 1 {
			return true
		}
		if len(t)+1 == len(s) {
			return dfs(string(t), []byte{})
		}
		k := s + "." + string(t)
		if v, ok := dp[k]; ok {
			return v
		}
		a, b := s[len(t)]-'A', s[len(t)+1]-'A'
		cs := f[a][b]
		for i := 0; i < 7; i++ {
			if ((cs >> i) & 1) == 1 {
				t = append(t, byte('A'+i))
				if dfs(s, t) {
					dp[k] = true
					return true
				}
				t = t[:len(t)-1]
			}
		}
		dp[k] = false
		return false
	}
	return dfs(bottom, []byte{})
}
```

### **...**

```

```

<!-- tabs:end -->
