# [面试题 08.07. 无重复字符串的排列组合](https://leetcode.cn/problems/permutation-i-lcci)

[English Version](/lcci/08.07.Permutation%20I/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->
<p>无重复字符串的排列组合。编写一种方法，计算某字符串的所有排列组合，字符串每个字符均不相同。</p>

<p> <strong>示例1:</strong></p>

<pre>
<strong> 输入</strong>：S = "qwe"
<strong> 输出</strong>：["qwe", "qew", "wqe", "weq", "ewq", "eqw"]
</pre>

<p> <strong>示例2:</strong></p>

<pre>
<strong> 输入</strong>：S = "ab"
<strong> 输出</strong>：["ab", "ba"]
</pre>

<p> <strong>提示:</strong></p>

<ol>
<li>字符都是英文字母。</li>
<li>字符串长度在[1, 9]之间。</li>
</ol>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：回溯**

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
