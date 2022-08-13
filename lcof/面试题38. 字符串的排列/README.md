# [面试题 38. 字符串的排列](https://leetcode.cn/problems/zi-fu-chuan-de-pai-lie-lcof/)

## 题目描述

<!-- 这里写题目描述 -->

<p>输入一个字符串，打印出该字符串中字符的所有排列。</p>

<p>&nbsp;</p>

<p>你可以以任意顺序返回这个字符串数组，但里面不能有重复元素。</p>

<p>&nbsp;</p>

<p><strong>示例:</strong></p>

<pre><strong>输入：</strong>s = &quot;abc&quot;
<strong>输出：[</strong>&quot;abc&quot;,&quot;acb&quot;,&quot;bac&quot;,&quot;bca&quot;,&quot;cab&quot;,&quot;cba&quot;<strong>]</strong>
</pre>

<p>&nbsp;</p>

<p><strong>限制：</strong></p>

<p><code>1 &lt;= s 的长度 &lt;= 8</code></p>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def permutation(self, s: str) -> List[str]:
        def dfs(x):
            if x == len(s) - 1:
                res.append("".join(chars))
                return
            t = set()
            for i in range(x, len(s)):
                if chars[i] in t:
                    continue
                t.add(chars[i])
                chars[i], chars[x] = chars[x], chars[i]
                dfs(x + 1)
                chars[i], chars[x] = chars[x], chars[i]

        chars, res = list(s), []
        dfs(0)
        return res
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    private char[] chars;
    private List<String> res;

    public String[] permutation(String s) {
        chars = s.toCharArray();
        res = new ArrayList<>();
        dfs(0);
        return res.toArray(new String[res.size()]);
    }

    private void dfs(int x) {
        if (x == chars.length - 1) {
            res.add(String.valueOf(chars));
            return;
        }
        Set<Character> set = new HashSet<>();
        for (int i = x; i < chars.length; ++i) {
            if (set.contains(chars[i])) {
                continue;
            }
            set.add(chars[i]);
            swap(i, x);
            dfs(x + 1);
            swap(i, x);
        }
    }

    private void swap(int i, int j) {
        char t = chars[i];
        chars[i] = chars[j];
        chars[j] = t;
    }
}
```

### **JavaScript**

```js
/**
 * @param {string} s
 * @return {string[]}
 */
var permutation = function (s) {
    let len = s.length;
    let res = new Set();
    function dfs(str, isRead) {
        if (str.length === len) {
            res.add(str);
            return;
        }
        for (let i = 0; i < len; i++) {
            if (isRead[i]) continue;
            isRead[i] = 1;
            dfs(str.concat(s[i]), isRead);
            isRead[i] = 0;
        }
    }
    dfs('', {});
    return [...res];
};
```

### **C++**

```cpp
class Solution {
public:
    void func(string str, int index, set<string>& mySet) {
        if (index == str.size()) {
            mySet.insert(str);
        } else {
            for (int i = index; i < str.size(); i++) {
                swap(str[i], str[index]);
                int temp = index + 1;
                func(str, temp, mySet);
                swap(str[i], str[index]);
            }
        }
    }

    vector<string> permutation(string s) {
        set<string> mySet;
        func(s, 0, mySet);
        vector<string> ret;
        for (string x : mySet) {
            ret.push_back(x);
        }
        return ret;
    }
};
```

### **TypeScript**

```ts
function permutation(s: string): string[] {
    const n = s.length;
    const cs = s.split('');
    const set = new Set<string>();
    const dfs = (i: number) => {
        if (i === n) {
            set.add(cs.join(''));
            return;
        }
        dfs(i + 1);
        for (let j = i + 1; j < n; j++) {
            [cs[i], cs[j]] = [cs[j], cs[i]];
            dfs(i + 1);
            [cs[i], cs[j]] = [cs[j], cs[i]];
        }
    };
    dfs(0);
    return [...set];
}
```

### **Rust**

```rust
use std::collections::HashSet;
impl Solution {
    fn dfs(i: usize, cs: &mut Vec<char>, res: &mut Vec<String>) {
        if i == cs.len() {
            res.push(cs.iter().collect());
            return;
        }
        let mut set = HashSet::new();
        for j in i..cs.len() {
            if set.contains(&cs[j]) {
                continue;
            }
            set.insert(cs[j]);
            cs.swap(i, j);
            Self::dfs(i + 1, cs, res);
            cs.swap(i, j);
        }
    }

    pub fn permutation(s: String) -> Vec<String> {
        let mut res = Vec::new();
        Self::dfs(0, &mut s.chars().collect(), &mut res);
        res
    }
}
```

### **C#**

```cs
public class Solution {
    public string[] Permutation(string s) {
        int n = s.Length;
        var data = s.ToCharArray();
        var ans = new List<string>();
        DFS(data, 0, ans);
        return ans.ToArray();
    }

    void DFS(char[] s, int idx, List<string> ans) {
        if (idx == s.Length) {
            ans.Add(new string(s));
            return;
        }
        var set = new HashSet<char>();
        for (int i = idx; i < s.Length; i++) {
            if (set.Add(s[i])) {
                (s[i], s[idx]) = (s[idx], s[i]);
                DFS(s, idx+1, ans);
                (s[i], s[idx]) = (s[idx], s[i]);
            }
        }
    }
}
```

<!-- tabs:end -->
