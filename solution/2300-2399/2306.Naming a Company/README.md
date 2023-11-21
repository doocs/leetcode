# [2306. 公司命名](https://leetcode.cn/problems/naming-a-company)

[English Version](/solution/2300-2399/2306.Naming%20a%20Company/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个字符串数组 <code>ideas</code> 表示在公司命名过程中使用的名字列表。公司命名流程如下：</p>

<ol>
	<li>从 <code>ideas</code> 中选择 2 个 <strong>不同</strong> 名字，称为 <code>idea<sub>A</sub></code> 和 <code>idea<sub>B</sub></code> 。</li>
	<li>交换 <code>idea<sub>A</sub></code> 和 <code>idea<sub>B</sub></code> 的首字母。</li>
	<li>如果得到的两个新名字 <strong>都</strong> 不在 <code>ideas</code> 中，那么 <code>idea<sub>A</sub> idea<sub>B</sub></code>（<strong>串联</strong> <code>idea<sub>A</sub></code> 和 <code>idea<sub>B</sub></code> ，中间用一个空格分隔）是一个有效的公司名字。</li>
	<li>否则，不是一个有效的名字。</li>
</ol>

<p>返回 <strong>不同</strong> 且有效的公司名字的数目。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>ideas = ["coffee","donuts","time","toffee"]
<strong>输出：</strong>6
<strong>解释：</strong>下面列出一些有效的选择方案：
- ("coffee", "donuts")：对应的公司名字是 "doffee conuts" 。
- ("donuts", "coffee")：对应的公司名字是 "conuts doffee" 。
- ("donuts", "time")：对应的公司名字是 "tonuts dime" 。
- ("donuts", "toffee")：对应的公司名字是 "tonuts doffee" 。
- ("time", "donuts")：对应的公司名字是 "dime tonuts" 。
- ("toffee", "donuts")：对应的公司名字是 "doffee tonuts" 。
因此，总共有 6 个不同的公司名字。

下面列出一些无效的选择方案：
- ("coffee", "time")：在原数组中存在交换后形成的名字 "toffee" 。
- ("time", "toffee")：在原数组中存在交换后形成的两个名字。
- ("coffee", "toffee")：在原数组中存在交换后形成的两个名字。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>ideas = ["lack","back"]
<strong>输出：</strong>0
<strong>解释：</strong>不存在有效的选择方案。因此，返回 0 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= ideas.length &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>1 &lt;= ideas[i].length &lt;= 10</code></li>
	<li><code>ideas[i]</code> 由小写英文字母组成</li>
	<li><code>ideas</code> 中的所有字符串 <strong>互不相同</strong></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：枚举计数**

我们定义 $f[i][j]$ 表示 $ideas$ 中以第 $i$ 个字母开头，替换为第 $j$ 个字母后，不在 $ideas$ 中的字符串的个数。初始时 $f[i][j] = 0$。另外，用一个哈希表 $s$ 记录 $ideas$ 中的字符串，方便我们开快速判断某个字符串是否在 $ideas$ 中。

接下来，我们遍历 $ideas$ 中字符串，对于当前遍历到的字符串 $v$，我们枚举替换后的第一个字母 $j$，如果 $v$ 替换后的字符串不在 $ideas$ 中，那么我们就更新 $f[i][j] = f[i][j] + 1$。

最后，我们再次遍历 $ideas$ 中字符串，对于当前遍历到的字符串 $v$，我们枚举替换后的第一个字母 $j$，如果 $v$ 替换后的字符串不在 $ideas$ 中，那么我们就更新答案 $ans = ans + f[j][i]$。

最终答案即为 $ans$。

时间复杂度 $O(n \times m \times |\Sigma|)$，空间复杂度 $O(|\Sigma|^2)$。其中 $n$ 和 $m$ 分别是 $ideas$ 中字符串的个数和字符串的最大长度，而 $|\Sigma|$ 是字符串中出现的字符集，本题中 $|\Sigma| \leq 26$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

### **C++**

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

### **Go**

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

### **TypeScript**

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

### **...**

```

```

<!-- tabs:end -->
