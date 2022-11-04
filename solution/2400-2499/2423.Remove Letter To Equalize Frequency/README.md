# [2423. 删除字符使频率相同](https://leetcode.cn/problems/remove-letter-to-equalize-frequency)

[English Version](/solution/2400-2499/2423.Remove%20Letter%20To%20Equalize%20Frequency/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个下标从 <strong>0</strong>&nbsp;开始的字符串&nbsp;<code>word</code>&nbsp;，字符串只包含小写英文字母。你需要选择 <strong>一个</strong>&nbsp;下标并 <strong>删除</strong>&nbsp;下标处的字符，使得 <code>word</code>&nbsp;中剩余每个字母出现 <strong>频率</strong>&nbsp;相同。</p>

<p>如果删除一个字母后，<code>word</code>&nbsp;中剩余所有字母的出现频率都相同，那么返回 <code>true</code>&nbsp;，否则返回 <code>false</code>&nbsp;。</p>

<p><strong>注意：</strong></p>

<ul>
	<li>字母&nbsp;<code>x</code>&nbsp;的 <strong>频率</strong><strong>&nbsp;</strong>是这个字母在字符串中出现的次数。</li>
	<li>你 <strong>必须</strong>&nbsp;恰好删除一个字母，不能一个字母都不删除。</li>
</ul>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><b>输入：</b>word = "abcc"
<b>输出：</b>true
<b>解释：</b>选择下标 3 并删除该字母，word 变成 "abc" 且每个字母出现频率都为 1 。
</pre>

<p><strong>示例 2：</strong></p>

<pre><b>输入：</b>word = "aazz"
<b>输出：</b>false
<b>解释：</b>我们必须删除一个字母，所以要么 "a" 的频率变为 1 且 "z" 的频率为 2 ，要么两个字母频率反过来。所以不可能让剩余所有字母出现频率相同。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= word.length &lt;= 100</code></li>
	<li><code>word</code>&nbsp;只包含小写英文字母。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：直接模拟**

遍历字符串中的每个字符，删除该字符后，判断剩余字符串中每个字符出现的频率是否相同。如果相同，返回 `true`，否则遍历结束，返回 `false`。

时间复杂度 $O(n^2)$，空间复杂度 $O(n)$。其中 $n$ 为字符串的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def equalFrequency(self, word: str) -> bool:
        for i in range(len(word)):
            cnt = Counter(word[:i] + word[i + 1:])
            if len(set(cnt.values())) == 1:
                return True
        return False
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public boolean equalFrequency(String word) {
        for (int i = 0; i < word.length(); ++i) {
            int[] cnt = new int[26];
            for (int j = 0; j < word.length(); ++j) {
                if (j != i) {
                    ++cnt[word.charAt(j) - 'a'];
                }
            }
            Set<Integer> vis = new HashSet<>();
            for (int v : cnt) {
                if (v > 0) {
                    vis.add(v);
                }
            }
            if (vis.size() == 1) {
                return true;
            }
        }
        return false;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    bool equalFrequency(string word) {
        for (int i = 0; i < word.size(); ++i) {
            int cnt[26] = {0};
            for (int j = 0; j < word.size(); ++j) {
                if (j != i) {
                    ++cnt[word[j] - 'a'];
                }
            }
            unordered_set<int> vis;
            for (int v : cnt) {
                if (v) {
                    vis.insert(v);
                }
            }
            if (vis.size() == 1) {
                return true;
            }
        }
        return false;
    }
};
```

### **Go**

```go
func equalFrequency(word string) bool {
	for i := range word {
		cnt := make([]int, 26)
		for j, c := range word {
			if j != i {
				cnt[c-'a']++
			}
		}
		vis := map[int]bool{}
		for _, v := range cnt {
			if v > 0 {
				vis[v] = true
			}
		}
		if len(vis) == 1 {
			return true
		}
	}
	return false
}
```

### **TypeScript**

```ts
function equalFrequency(word: string): boolean {
    const map = new Map();
    for (const c of word) {
        map.set(c, (map.get(c) ?? 0) + 1);
    }
    const count = new Map();
    for (const v of map.values()) {
        count.set(v, (count.get(v) ?? 0) + 1);
    }
    if (count.size === 1) {
        return map.size == 1 || [...count.keys()][0] === 1;
    }
    if (count.size === 2) {
        return [...count.entries()].some(
            (v, i, arr) =>
                (v[0] === 1 || v[0] - arr[i ^ 1][0] === 1) && v[1] === 1,
        );
    }
    return false;
}
```

### **...**

```

```

<!-- tabs:end -->
