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

**方法一：回溯 + 哈希表**

我们设计一个函数 $dfs(i)$，表示当前排列到了第 $i$ 个位置，我们需要在第 $i$ 个位置上填入一个字符，这个字符可以从 $s[i..n-1]$ 中任意选择。

函数 $dfs(i)$ 的执行过程如下：

-   如果 $i = n-1$，说明当前排列已经填满，将当前排列加入答案，返回。
-   否则，我们需要在 $s[i..n-1]$ 中选择一个字符填入第 $i$ 个位置，我们可以使用哈希表记录哪些字符已经被填过，从而避免重复填入相同的字符。
-   在 $s[i..n-1]$ 中选择一个字符填入第 $i$ 个位置，然后递归执行函数 $dfs(i+1)$，即填入第 $i+1$ 个位置。
-   回溯，撤销选择，即将第 $i$ 个位置的字符填回原位。

我们在主函数中调用函数 $dfs(0)$，即从第 0 个位置开始填入字符。最后返回答案数组即可。

时间复杂度 $O(n! \times n)$，空间复杂度 $O(n)$。其中 $n$ 是字符串 $s$ 的长度。需要进行 $n!$ 次排列，每次排列需要 $O(n)$ 的时间复制字符串。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def permutation(self, s: str) -> List[str]:
        def dfs(i):
            if i == len(s) - 1:
                ans.append(''.join(cs))
                return
            vis = set()
            for j in range(i, len(s)):
                if cs[j] not in vis:
                    vis.add(cs[j])
                    cs[i], cs[j] = cs[j], cs[i]
                    dfs(i + 1)
                    cs[i], cs[j] = cs[j], cs[i]

        ans = []
        cs = list(s)
        dfs(0)
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    private List<String> ans = new ArrayList<>();
    private char[] cs;

    public String[] permutation(String s) {
        cs = s.toCharArray();
        dfs(0);
        return ans.toArray(new String[ans.size()]);
    }

    private void dfs(int i) {
        if (i == cs.length - 1) {
            ans.add(String.valueOf(cs));
            return;
        }
        Set<Character> vis = new HashSet<>();
        for (int j = i; j < cs.length; ++j) {
            if (vis.add(cs[j])) {
                swap(i, j);
                dfs(i + 1);
                swap(i, j);
            }
        }
    }

    private void swap(int i, int j) {
        char t = cs[i];
        cs[i] = cs[j];
        cs[j] = t;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<string> permutation(string s) {
        vector<string> ans;
        function<void(int)> dfs = [&](int i) {
            if (i == s.size() - 1) {
                ans.push_back(s);
                return;
            }
            unordered_set<char> vis;
            for (int j = i; j < s.size(); ++j) {
                if (!vis.count(s[j])) {
                    vis.insert(s[j]);
                    swap(s[i], s[j]);
                    dfs(i + 1);
                    swap(s[i], s[j]);
                }
            }
        };
        dfs(0);
        return ans;
    }
};
```

### **Go**

```go
func permutation(s string) (ans []string) {
	cs := []byte(s)
	var dfs func(int)
	dfs = func(i int) {
		if i == len(s)-1 {
			ans = append(ans, string(cs))
			return
		}
		vis := map[byte]bool{}
		for j := i; j < len(s); j++ {
			if !vis[cs[j]] {
				vis[cs[j]] = true
				cs[i], cs[j] = cs[j], cs[i]
				dfs(i + 1)
				cs[i], cs[j] = cs[j], cs[i]
			}
		}
	}
	dfs(0)
	return
}
```

### **JavaScript**

```js
/**
 * @param {string} s
 * @return {string[]}
 */
var permutation = function (s) {
    const cs = s.split('');
    const ans = [];
    const n = s.length;
    const dfs = i => {
        if (i == n - 1) {
            ans.push(cs.join(''));
            return;
        }
        const vis = new Set();
        for (let j = i; j < n; ++j) {
            if (!vis.has(cs[j])) {
                vis.add(cs[j]);
                [cs[i], cs[j]] = [cs[j], cs[i]];
                dfs(i + 1);
                [cs[i], cs[j]] = [cs[j], cs[i]];
            }
        }
    };
    dfs(0);
    return ans;
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
    private char[] cs;
    private List<string> ans = new List<string>();

    public string[] Permutation(string s) {
        cs = s.ToCharArray();
        dfs(0);
        return ans.ToArray();
    }

    private void dfs(int i) {
        if (i == cs.Length - 1) {
            ans.Add(new string(cs));
            return;
        }
        var vis = new HashSet<char>();
        for (int j = i; j < cs.Length; ++j) {
            if (vis.Add(cs[j])) {
                (cs[i], cs[j]) = (cs[j], cs[i]);
                dfs(i + 1);
                (cs[i], cs[j]) = (cs[j], cs[i]);
            }
        }
    }
}
```

<!-- tabs:end -->
