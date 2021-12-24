# [剑指 Offer II 065. 最短的单词编码](https://leetcode-cn.com/problems/iSwD2y)

## 题目描述

<!-- 这里写题目描述 -->

<p>单词数组&nbsp;<code>words</code> 的 <strong>有效编码</strong> 由任意助记字符串 <code>s</code> 和下标数组 <code>indices</code> 组成，且满足：</p>

<ul>
	<li><code>words.length == indices.length</code></li>
	<li>助记字符串 <code>s</code> 以 <code>&#39;#&#39;</code> 字符结尾</li>
	<li>对于每个下标 <code>indices[i]</code> ，<code>s</code> 的一个从 <code>indices[i]</code> 开始、到下一个 <code>&#39;#&#39;</code> 字符结束（但不包括 <code>&#39;#&#39;</code>）的 <strong>子字符串</strong> 恰好与 <code>words[i]</code> 相等</li>
</ul>

<p>给定一个单词数组&nbsp;<code>words</code> ，返回成功对 <code>words</code> 进行编码的最小助记字符串 <code>s</code> 的长度 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>words = [&quot;time&quot;, &quot;me&quot;, &quot;bell&quot;]
<strong>输出：</strong>10
<strong>解释：</strong>一组有效编码为 s = <code>&quot;time#bell#&quot; 和 indices = [0, 2, 5</code>] 。
words[0] = &quot;time&quot; ，s 开始于 indices[0] = 0 到下一个 &#39;#&#39; 结束的子字符串，如加粗部分所示 &quot;<strong>time</strong>#bell#&quot;
words[1] = &quot;me&quot; ，s 开始于 indices[1] = 2 到下一个 &#39;#&#39; 结束的子字符串，如加粗部分所示 &quot;ti<strong>me</strong>#bell#&quot;
words[2] = &quot;bell&quot; ，s 开始于 indices[2] = 5 到下一个 &#39;#&#39; 结束的子字符串，如加粗部分所示 &quot;time#<strong>bell</strong>#&quot;
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>words = [&quot;t&quot;]
<strong>输出：</strong>2
<strong>解释：</strong>一组有效编码为 s = &quot;t#&quot; 和 indices = [0] 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= words.length &lt;= 2000</code></li>
	<li><code>1 &lt;= words[i].length &lt;= 7</code></li>
	<li><code>words[i]</code> 仅由小写字母组成</li>
</ul>

<p>&nbsp;</p>

<p><meta charset="UTF-8" />注意：本题与主站 820&nbsp;题相同：&nbsp;<a href="https://leetcode-cn.com/problems/short-encoding-of-words/">https://leetcode-cn.com/problems/short-encoding-of-words/</a></p>

## 解法

<!-- 这里可写通用的实现逻辑 -->

题目大意：充分利用重叠的后缀，使有效编码尽可能短

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Trie:
    def __init__(self) -> None:
        self.children = [None] * 26


class Solution:
    def minimumLengthEncoding(self, words: List[str]) -> int:
        root = Trie()
        for w in words:
            cur = root
            for i in range(len(w) - 1, -1, -1):
                idx = ord(w[i]) - ord('a')
                if cur.children[idx] == None:
                    cur.children[idx] = Trie()
                cur = cur.children[idx]
        return self.dfs(root, 1)

    def dfs(self, cur: Trie, l: int) -> int:
        isLeaf, ans = True, 0
        for i in range(26):
            if cur.children[i] != None:
                isLeaf = False
                ans += self.dfs(cur.children[i], l + 1)
        if isLeaf:
            ans += l
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Trie {
    Trie[] children = new Trie[26];
}

class Solution {
    public int minimumLengthEncoding(String[] words) {
        Trie root = new Trie();
        for (String w : words) {
            Trie cur = root;
            for (int i = w.length() - 1; i >= 0; i--) {
                int idx = w.charAt(i) - 'a';
                if (cur.children[idx] == null) {
                    cur.children[idx] = new Trie();
                }
                cur = cur.children[idx];
            }
        }
        return dfs(root, 1);
    }

    private int dfs(Trie cur, int l) {
        boolean isLeaf = true;
        int ans = 0;
        for (int i = 0; i < 26; i++) {
            if (cur.children[i] != null) {
                isLeaf = false;
                ans += dfs(cur.children[i], l + 1);
            }
        }
        if (isLeaf) {
            ans += l;
        }
        return ans;
    }
}
```

### **Go**

```go
type trie struct {
	children [26]*trie
}

func minimumLengthEncoding(words []string) int {
	root := new(trie)
	for _, w := range words {
		cur := root
		for i := len(w) - 1; i >= 0; i-- {
			if cur.children[w[i]-'a'] == nil {
				cur.children[w[i]-'a'] = new(trie)
			}
			cur = cur.children[w[i]-'a']
		}
	}
	return dfs(root, 1)
}

func dfs(cur *trie, l int) int {
	isLeaf, ans := true, 0
	for i := 0; i < 26; i++ {
		if cur.children[i] != nil {
			isLeaf = false
			ans += dfs(cur.children[i], l+1)
		}
	}
	if isLeaf {
		ans += l
	}
	return ans
}
```

### **C++**

```cpp
struct Trie {
    Trie* children[26] = { nullptr };
};

class Solution {
public:
    int minimumLengthEncoding(vector<string>& words) {
        auto root = new Trie();
        for (auto& w : words) {
            auto cur = root;
            for (int i = w.size() - 1; i >= 0; --i) {
                if (cur->children[w[i] - 'a'] == nullptr) {
                    cur->children[w[i] - 'a'] = new Trie();
                }
                cur = cur->children[w[i] - 'a'];
            }
        }
        return dfs(root, 1);
    }

private:
    int dfs(Trie* cur, int l) {
        bool isLeaf = true;
        int ans = 0;
        for (int i = 0; i < 26; ++i) {
            if (cur->children[i] != nullptr) {
                isLeaf = false;
                ans += dfs(cur->children[i], l + 1);
            }
        }
        if (isLeaf) {
            ans += l;
        }
        return ans;
    }
};
```

### **...**

```

```

<!-- tabs:end -->
