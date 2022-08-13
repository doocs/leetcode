# [08.07. Permutation I](https://leetcode.cn/problems/permutation-i-lcci)

[中文文档](/lcci/08.07.Permutation%20I/README.md)

## Description

<p>Write a method to compute all permutations of a string of unique characters.</p>

<p><strong>Example1:</strong></p>

<pre>

<strong> Input</strong>: S = &quot;qwe&quot;

<strong> Output</strong>: [&quot;qwe&quot;, &quot;qew&quot;, &quot;wqe&quot;, &quot;weq&quot;, &quot;ewq&quot;, &quot;eqw&quot;]

</pre>

<p><strong>Example2:</strong></p>

<pre>

<strong> Input</strong>: S = &quot;ab&quot;

<strong> Output</strong>: [&quot;ab&quot;, &quot;ba&quot;]

</pre>

<p><strong>Note:</strong></p>

<ol>
	<li>All charaters are English letters.</li>
	<li><code>1 &lt;= S.length &lt;= 9</code></li>
</ol>

## Solutions

Backtracking

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def permutation(self, S: str) -> List[str]:
        def dfs(u, t):
            if u == n:
                ans.append(''.join(t))
                return
            for i in range(n):
                if vis[i]:
                    continue
                vis[i] = True
                t.append(S[i])
                dfs(u + 1, t)
                t.pop()
                vis[i] = False

        n = len(S)
        vis = [False] * n
        ans = []
        dfs(0, [])
        return ans
```

### **Java**

```java
class Solution {
    public String[] permutation(String S) {
        Set<Character> vis = new HashSet<>();
        List<String> ans = new ArrayList<>();
        StringBuilder t = new StringBuilder();
        dfs(0, S, t, ans, vis);
        return ans.toArray(new String[0]);
    }

    private void dfs(int u, String S, StringBuilder t, List<String> ans, Set<Character> vis) {
        if (u == S.length()) {
            ans.add(t.toString());
            return;
        }
        for (char c : S.toCharArray()) {
            if (vis.contains(c)) {
                continue;
            }
            vis.add(c);
            t.append(c);
            dfs(u + 1, S, t, ans, vis);
            t.deleteCharAt(t.length() - 1);
            vis.remove(c);
        }
    }
}
```

### **JavaSript**

```js
/**
 * @param {string} S
 * @return {string[]}
 */
var permutation = function (S) {
    let res = [];
    let arr = [...S];
    let prev = [];
    let record = new Array(S.length).fill(false);
    dfs(arr, 0, prev, record, res);
    return res;
};

function dfs(arr, depth, prev, record, res) {
    if (depth == arr.length) {
        res.push(prev.join(''));
        return;
    }
    for (let i = 0; i < arr.length; i++) {
        if (record[i]) {
            continue;
        }
        prev.push(arr[i]);
        record[i] = true;
        dfs(arr, depth + 1, prev, record, res);
        prev.pop();
        record[i] = false;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<string> permutation(string S) {
        unordered_set<char> vis;
        vector<string> ans;
        string t = "";
        dfs(0, S, t, ans, vis);
        return ans;
    }

    void dfs(int u, string& S, string& t, vector<string>& ans, unordered_set<char>& vis) {
        if (u == S.size()) {
            ans.push_back(t);
            return;
        }
        for (char& c : S) {
            if (vis.count(c)) continue;
            vis.insert(c);
            t.push_back(c);
            dfs(u + 1, S, t, ans, vis);
            vis.erase(c);
            t.pop_back();
        }
    }
};
```

### **Go**

```go
func permutation(S string) []string {
	vis := make(map[byte]bool)
	var ans []string
	var t []byte
	var dfs func(u int, t []byte)
	dfs = func(u int, t []byte) {
		if u == len(S) {
			ans = append(ans, string(t))
			return
		}
		for i := range S {
			if vis[S[i]] {
				continue
			}
			vis[S[i]] = true
			t = append(t, S[i])
			dfs(u+1, t)
			vis[S[i]] = false
			t = t[:len(t)-1]
		}
	}
	dfs(0, t)
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
