# [851. Loud and Rich](https://leetcode.com/problems/loud-and-rich)

[中文文档](/solution/0800-0899/0851.Loud%20and%20Rich/README.md)

## Description

<p>In a group of N people (labelled <code>0, 1, 2, ..., N-1</code>), each person has different amounts of money, and different levels of quietness.</p>

<p>For convenience, we&#39;ll call the person with label <code>x</code>, simply &quot;person <code>x</code>&quot;.</p>

<p>We&#39;ll say that <code>richer[i] = [x, y]</code> if person <code>x</code>&nbsp;definitely has more money than person&nbsp;<code>y</code>.&nbsp; Note that <code>richer</code>&nbsp;may only be a subset of valid observations.</p>

<p>Also, we&#39;ll say <code>quiet[x] = q</code> if person <font face="monospace">x</font>&nbsp;has quietness <code>q</code>.</p>

<p>Now, return <code>answer</code>, where <code>answer[x] = y</code> if <code>y</code> is the least quiet person (that is, the person <code>y</code> with the smallest value of <code>quiet[y]</code>), among all people&nbsp;who definitely have&nbsp;equal to or more money than person <code>x</code>.</p>

<p>&nbsp;</p>

<div>

<p><strong>Example 1:</strong></p>

<pre>

<strong>Input: </strong>richer = <span id="example-input-1-1">[[1,0],[2,1],[3,1],[3,7],[4,3],[5,3],[6,3]]</span>, quiet = <span id="example-input-1-2">[3,2,5,4,6,1,7,0]</span>

<strong>Output: </strong><span id="example-output-1">[5,5,2,5,4,5,6,7]</span>

<strong>Explanation: </strong>

answer[0] = 5.

Person 5 has more money than 3, which has more money than 1, which has more money than 0.

The only person who is quieter (has lower quiet[x]) is person 7, but

it isn&#39;t clear if they have more money than person 0.



answer[7] = 7.

Among all people that definitely have equal to or more money than person 7

(which could be persons 3, 4, 5, 6, or 7), the person who is the quietest (has lower quiet[x])

is person 7.



The other answers can be filled out with similar reasoning.

</pre>

</div>

<p><strong>Note:</strong></p>

<ol>
	<li><code>1 &lt;= quiet.length = N &lt;= 500</code></li>
	<li><code>0 &lt;= quiet[i] &lt; N</code>, all <code>quiet[i]</code> are different.</li>
	<li><code>0 &lt;= richer.length &lt;= N * (N-1) / 2</code></li>
	<li><code>0 &lt;= richer[i][j] &lt; N</code></li>
	<li><code>richer[i][0] != richer[i][1]</code></li>
	<li><code>richer[i]</code>&#39;s are all different.</li>
	<li>The&nbsp;observations in <code>richer</code> are all logically consistent.</li>
</ol>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def loudAndRich(self, richer: List[List[int]], quiet: List[int]) -> List[int]:
        n = len(quiet)
        g = defaultdict(list)
        for a, b in richer:
            g[b].append(a)
        ans = [-1] * n

        def dfs(i):
            if ans[i] != -1:
                return
            ans[i] = i
            for j in g[i]:
                dfs(j)
                if quiet[ans[j]] < quiet[ans[i]]:
                    ans[i] = ans[j]

        for i in range(n):
            dfs(i)
        return ans
```

### **Java**

```java
class Solution {
    private Map<Integer, List<Integer>> g;
    private int[] quiet;
    private int[] ans;

    public int[] loudAndRich(int[][] richer, int[] quiet) {
        g = new HashMap<>();
        this.quiet = quiet;
        ans = new int[quiet.length];
        Arrays.fill(ans, -1);
        for (int[] r : richer) {
            g.computeIfAbsent(r[1], k -> new ArrayList<>()).add(r[0]);
        }
        for (int i = 0; i < quiet.length; ++i) {
            dfs(i);
        }
        return ans;
    }

    private void dfs(int i) {
        if (ans[i] != -1) {
            return;
        }
        ans[i] = i;
        if (!g.containsKey(i)) {
            return;
        }
        for (int j : g.get(i)) {
            dfs(j);
            if (quiet[ans[j]] < quiet[ans[i]]) {
                ans[i] = ans[j];
            }
        }
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<int> loudAndRich(vector<vector<int>>& richer, vector<int>& quiet) {
        int n = quiet.size();
        vector<vector<int>> g(n);
        for (auto& r : richer) g[r[1]].push_back(r[0]);
        vector<int> ans(n, -1);
        function<void(int)> dfs = [&](int i) {
            if (ans[i] != -1) return;
            ans[i] = i;
            for (int j : g[i])
            {
                dfs(j);
                if (quiet[ans[j]] < quiet[ans[i]]) ans[i] = ans[j];
            }
        };
        for (int i = 0; i < n; ++i)
            dfs(i);
        return ans;
    }
};
```

### **Go**

```go
func loudAndRich(richer [][]int, quiet []int) []int {
    n := len(quiet)
    ans := make([]int, n)
    g := make([][]int, n)
    for i := 0; i < n; i++ {
        ans[i] = -1
        g[i] = make([]int, 0)
    }
    for _, r := range richer {
        g[r[1]] = append(g[r[1]], r[0])
    }

    var dfs func(i int)
    dfs = func(i int) {
        if ans[i] != - 1 {
            return
        }
        ans[i] = i
        for _, j := range g[i] {
            dfs(j)
            if quiet[ans[j]] < quiet[ans[i]] {
                ans[i] = ans[j]
            }
        }
    }

    for i := 0; i < n; i++ {
        dfs(i)
    }
    return ans
}
```

### **...**

```

```

<!-- tabs:end -->
