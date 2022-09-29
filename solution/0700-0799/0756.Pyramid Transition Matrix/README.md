# [756. 金字塔转换矩阵](https://leetcode.cn/problems/pyramid-transition-matrix)

[English Version](/solution/0700-0799/0756.Pyramid%20Transition%20Matrix/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>你正在把积木堆成金字塔。每个块都有一个颜色，用一个字母表示。每一行的块比它下面的行 <strong>少一个块</strong> ，并且居中。</p>

<p>为了使金字塔美观，只有特定的 <strong>三角形图案</strong> 是允许的。一个三角形的图案由&nbsp;<strong>两个块</strong>&nbsp;和叠在上面的 <strong>单个块</strong> 组成。模式是以三个字母字符串的列表形式&nbsp;<code>allowed</code>&nbsp;给出的，其中模式的前两个字符分别表示左右底部块，第三个字符表示顶部块。</p>

<ul>
	<li>例如，<code>"ABC"</code>&nbsp;表示一个三角形图案，其中一个 <code>“C”</code> 块堆叠在一个&nbsp;<code>'A'</code>&nbsp;块(左)和一个&nbsp;<code>'B'</code>&nbsp;块(右)之上。请注意，这与 <code>"BAC"</code>&nbsp;不同，<code>"B"</code>&nbsp;在左下角，<code>"A"</code>&nbsp;在右下角。</li>
</ul>

<p>你从底部的一排积木&nbsp;<code>bottom</code>&nbsp;开始，作为一个单一的字符串，你 <strong>必须</strong> 使用作为金字塔的底部。</p>

<p>在给定&nbsp;<code>bottom</code>&nbsp;和&nbsp;<code>allowed</code>&nbsp;的情况下，如果你能一直构建到金字塔顶部，使金字塔中的 <strong>每个三角形图案</strong> 都是允许的，则返回 <code>true</code> ，否则返回 <code>false</code> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0700-0799/0756.Pyramid%20Transition%20Matrix/images/pyramid1-grid.jpg" style="height: 232px; width: 600px;" /></p>

<pre>
<strong>输入：</strong>bottom = "BCD", allowed = ["BCC","CDE","CEA","FFF"]
<strong>输出：</strong>true
<strong>解释：</strong>允许的三角形模式显示在右边。
从最底层(第3层)开始，我们可以在第2层构建“CE”，然后在第1层构建“E”。
金字塔中有三种三角形图案，分别是“BCC”、“CDE”和“CEA”。都是允许的。
</pre>

<p><strong>示例 2：</strong></p>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0700-0799/0756.Pyramid%20Transition%20Matrix/images/pyramid2-grid.jpg" style="height: 359px; width: 600px;" /></p>

<pre>
<strong>输入：</strong>bottom = "AABA", allowed = ["AAB","AAC","BCD","BBE","DEF"]
<strong>输出：</strong>false
<strong>解释：</strong>允许的三角形模式显示在右边。
从最底层(游戏邦注:即第4个关卡)开始，创造第3个关卡有多种方法，但如果尝试所有可能性，你便会在创造第1个关卡前陷入困境。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= bottom.length &lt;= 6</code></li>
	<li><code>0 &lt;= allowed.length &lt;= 216</code></li>
	<li><code>allowed[i].length == 3</code></li>
	<li>所有输入字符串中的字母来自集合&nbsp;<code>{'A', 'B', 'C', 'D', 'E', 'F', 'G'}</code>。</li>
	<li>&nbsp;<code>allowed</code>&nbsp;中所有值都是 <strong>唯一的</strong></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：记忆化搜索**

定义哈希表 $d$ 存放允许的三角形图案，其中键为两个字符，值为对应的字符列表，表示两个字符可以组成一个三角形图案，三角形图案的顶部为值列表的每一项。

从最底层开始，对于每一层的每两个相邻的字符，如果它们可以组成一个三角形图案，那么就将三角形图案的顶部字符加入到下一层的对应位置的字符列表中，然后对下一层进行递归处理。

时间复杂度 $O(C^N)$。其中 $C$ 是字符集的大小，而 $N$ 是 `bottom` 字符串的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
