# [2157. 字符串分组](https://leetcode.cn/problems/groups-of-strings)

[English Version](/solution/2100-2199/2157.Groups%20of%20Strings/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个下标从&nbsp;<strong>0&nbsp;</strong>开始的字符串数组&nbsp;<code>words</code>&nbsp;。每个字符串都只包含 <strong>小写英文字母</strong>&nbsp;。<code>words</code>&nbsp;中任意一个子串中，每个字母都至多只出现一次。</p>

<p>如果通过以下操作之一，我们可以从 <code>s1</code>&nbsp;的字母集合得到 <code>s2</code>&nbsp;的字母集合，那么我们称这两个字符串为 <strong>关联的</strong>&nbsp;：</p>

<ul>
	<li>往&nbsp;<code>s1</code>&nbsp;的字母集合中添加一个字母。</li>
	<li>从&nbsp;<code>s1</code>&nbsp;的字母集合中删去一个字母。</li>
	<li>将 <code>s1</code>&nbsp;中的一个字母替换成另外任意一个字母（也可以替换为这个字母本身）。</li>
</ul>

<p>数组&nbsp;<code>words</code>&nbsp;可以分为一个或者多个无交集的 <strong>组</strong>&nbsp;。如果一个字符串与另一个字符串关联，那么它们应当属于同一个组。</p>

<p>注意，你需要确保分好组后，一个组内的任一字符串与其他组的字符串都不关联。可以证明在这个条件下，分组方案是唯一的。</p>

<p>请你返回一个长度为 <code>2</code>&nbsp;的数组&nbsp;<code>ans</code>&nbsp;：</p>

<ul>
	<li><code>ans[0]</code>&nbsp;是&nbsp;<code>words</code>&nbsp;分组后的&nbsp;<strong>总组数</strong>&nbsp;。</li>
	<li><code>ans[1]</code>&nbsp;是字符串数目最多的组所包含的字符串数目。</li>
</ul>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<b>输入：</b>words = ["a","b","ab","cde"]
<b>输出：</b>[2,3]
<b>解释：</b>
- words[0] 可以得到 words[1] （将 'a' 替换为 'b'）和 words[2] （添加 'b'）。所以 words[0] 与 words[1] 和 words[2] 关联。
- words[1] 可以得到 words[0] （将 'b' 替换为 'a'）和 words[2] （添加 'a'）。所以 words[1] 与 words[0] 和 words[2] 关联。
- words[2] 可以得到 words[0] （删去 'b'）和 words[1] （删去 'a'）。所以 words[2] 与 words[0] 和 words[1] 关联。
- words[3] 与 words 中其他字符串都不关联。
所以，words 可以分成 2 个组 ["a","b","ab"] 和 ["cde"] 。最大的组大小为 3 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<b>输入：</b>words = ["a","ab","abc"]
<b>输出：</b>[1,3]
<strong>解释：</strong>
- words[0] 与 words[1] 关联。
- words[1] 与 words[0] 和 words[2] 关联。
- words[2] 与 words[1] 关联。
由于所有字符串与其他字符串都关联，所以它们全部在同一个组内。
所以最大的组大小为 3 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= words.length &lt;= 2 * 10<sup>4</sup></code></li>
	<li><code>1 &lt;= words[i].length &lt;= 26</code></li>
	<li><code>words[i]</code>&nbsp;只包含小写英文字母。</li>
	<li><code>words[i]</code> 中每个字母最多只出现一次。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：状态压缩（位运算） + 并查集**

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def groupStrings(self, words: List[str]) -> List[int]:
        def find(x):
            if p[x] != x:
                p[x] = find(p[x])
            return p[x]

        def union(a, b):
            nonlocal mx, n
            if b not in p:
                return
            pa, pb = find(a), find(b)
            if pa == pb:
                return
            p[pa] = pb
            size[pb] += size[pa]
            mx = max(mx, size[pb])
            n -= 1

        p = {}
        size = Counter()
        n = len(words)
        mx = 0
        for word in words:
            x = 0
            for c in word:
                x |= 1 << (ord(c) - ord('a'))
            p[x] = x
            size[x] += 1
            mx = max(mx, size[x])
            if size[x] > 1:
                n -= 1
        for x in p.keys():
            for i in range(26):
                union(x, x ^ (1 << i))
                if (x >> i) & 1:
                    for j in range(26):
                        if ((x >> j) & 1) == 0:
                            union(x, x ^ (1 << i) | (1 << j))
        return [n, mx]
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    private Map<Integer, Integer> p;
    private Map<Integer, Integer> size;
    private int mx;
    private int n;

    public int[] groupStrings(String[] words) {
        p = new HashMap<>();
        size = new HashMap<>();
        n = words.length;
        mx = 0;
        for (String word : words) {
            int x = 0;
            for (char c : word.toCharArray()) {
                x |= 1 << (c - 'a');
            }
            p.put(x, x);
            size.put(x, size.getOrDefault(x, 0) + 1);
            mx = Math.max(mx, size.get(x));
            if (size.get(x) > 1) {
                --n;
            }
        }
        for (int x : p.keySet()) {
            for (int i = 0; i < 26; ++i) {
                union(x, x ^ (1 << i));
                if (((x >> i) & 1) != 0) {
                    for (int j = 0; j < 26; ++j) {
                        if (((x >> j) & 1) == 0) {
                            union(x, x ^ (1 << i) | (1 << j));
                        }
                    }
                }
            }
        }
        return new int[] {n, mx};
    }

    private int find(int x) {
        if (p.get(x) != x) {
            p.put(x, find(p.get(x)));
        }
        return p.get(x);
    }

    private void union(int a, int b) {
        if (!p.containsKey(b)) {
            return;
        }
        int pa = find(a), pb = find(b);
        if (pa == pb) {
            return;
        }
        p.put(pa, pb);
        size.put(pb, size.get(pb) + size.get(pa));
        mx = Math.max(mx, size.get(pb));
        --n;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int mx, n;

    vector<int> groupStrings(vector<string>& words) {
        unordered_map<int, int> p;
        unordered_map<int, int> size;
        mx = 0;
        n = words.size();
        for (auto& word : words) {
            int x = 0;
            for (auto& c : word) x |= 1 << (c - 'a');
            p[x] = x;
            ++size[x];
            mx = max(mx, size[x]);
            if (size[x] > 1) --n;
        }
        for (auto& [x, _] : p) {
            for (int i = 0; i < 26; ++i) {
                unite(x, x ^ (1 << i), p, size);
                if ((x >> i) & 1) {
                    for (int j = 0; j < 26; ++j) {
                        if (((x >> j) & 1) == 0) unite(x, x ^ (1 << i) | (1 << j), p, size);
                    }
                }
            }
        }
        return {n, mx};
    }

    int find(int x, unordered_map<int, int>& p) {
        if (p[x] != x) p[x] = find(p[x], p);
        return p[x];
    }

    void unite(int a, int b, unordered_map<int, int>& p, unordered_map<int, int>& size) {
        if (!p.count(b)) return;
        int pa = find(a, p), pb = find(b, p);
        if (pa == pb) return;
        p[pa] = pb;
        size[pb] += size[pa];
        mx = max(mx, size[pb]);
        --n;
    }
};
```

### **Go**

```go
func groupStrings(words []string) []int {
	p := map[int]int{}
	size := map[int]int{}
	mx, n := 0, len(words)
	var find func(int) int
	find = func(x int) int {
		if p[x] != x {
			p[x] = find(p[x])
		}
		return p[x]
	}
	union := func(a, b int) {
		if _, ok := p[b]; !ok {
			return
		}
		pa, pb := find(a), find(b)
		if pa == pb {
			return
		}
		p[pa] = pb
		size[pb] += size[pa]
		mx = max(mx, size[pb])
		n--
	}

	for _, word := range words {
		x := 0
		for _, c := range word {
			x |= 1 << (c - 'a')
		}
		p[x] = x
		size[x]++
		mx = max(mx, size[x])
		if size[x] > 1 {
			n--
		}
	}
	for x := range p {
		for i := 0; i < 26; i++ {
			union(x, x^(1<<i))
			if ((x >> i) & 1) != 0 {
				for j := 0; j < 26; j++ {
					if ((x >> j) & 1) == 0 {
						union(x, x^(1<<i)|(1<<j))
					}
				}
			}
		}
	}
	return []int{n, mx}
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
```

### **TypeScript**

```ts

```

### **...**

```

```

<!-- tabs:end -->
