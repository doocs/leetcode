# [737. 句子相似性 II](https://leetcode-cn.com/problems/sentence-similarity-ii)

[English Version](/solution/0700-0799/0737.Sentence%20Similarity%20II/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定两个句子 <code>words1, words2</code> （每个用字符串数组表示），和一个相似单词对的列表&nbsp;<code>pairs</code>&nbsp;，判断是否两个句子是相似的。</p>

<p>例如，当相似单词对是 <code>pairs = [[&quot;great&quot;, &quot;fine&quot;], [&quot;acting&quot;,&quot;drama&quot;], [&quot;skills&quot;,&quot;talent&quot;]]</code>的时候，<code>words1 = [&quot;great&quot;, &quot;acting&quot;, &quot;skills&quot;]</code> 和&nbsp;<code>words2 = [&quot;fine&quot;, &quot;drama&quot;, &quot;talent&quot;]</code> 是相似的。</p>

<p>注意相似关系是 <strong>具有</strong> 传递性的。例如，如果 &quot;great&quot; 和&nbsp;&quot;fine&quot; 是相似的，&quot;fine&quot; 和&nbsp;&quot;good&quot; 是相似的，则 &quot;great&quot; 和 &quot;good&quot; <strong>是相似的</strong>。</p>

<p>而且，相似关系是具有对称性的。例如，&quot;great&quot; 和 &quot;fine&quot; 是相似的相当于&nbsp;&quot;fine&quot; 和&nbsp;&quot;great&quot; 是相似的。</p>

<p>并且，一个单词总是与其自身相似。例如，句子 <code>words1 = [&quot;great&quot;], words2 = [&quot;great&quot;], pairs = []</code> 是相似的，尽管没有输入特定的相似单词对。</p>

<p>最后，句子只会在具有相同单词个数的前提下才会相似。所以一个句子 <code>words1 = [&quot;great&quot;]</code> 永远不可能和句子 <code>words2 = [&quot;doubleplus&quot;,&quot;good&quot;]</code> 相似。</p>

<p><strong>注：</strong></p>

<ul>
	<li><code>words1</code> and <code>words2</code> 的长度不会超过&nbsp;<code>1000</code>。</li>
	<li><code>pairs</code>&nbsp;的长度不会超过&nbsp;<code>2000</code>。</li>
	<li>每个<code>pairs[i]</code>&nbsp;的长度为&nbsp;<code>2</code>。</li>
	<li>每个&nbsp;<code>words[i]</code>&nbsp;和&nbsp;<code>pairs[i][j]</code>&nbsp;的长度范围为&nbsp;<code>[1, 20]</code>。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

并查集。

模板 1——朴素并查集：

```python
# 初始化，p存储每个点的父节点
p = list(range(n))

# 返回x的祖宗节点
def find(x):
    if p[x] != x:
        # 路径压缩
        p[x] = find(p[x])
    return p[x]

# 合并a和b所在的两个集合
p[find(a)] = find(b)
```

模板 2——维护 size 的并查集：

```python
# 初始化，p存储每个点的父节点，size只有当节点是祖宗节点时才有意义，表示祖宗节点所在集合中，点的数量
p = list(range(n))
size = [1] * n

# 返回x的祖宗节点
def find(x):
    if p[x] != x:
        # 路径压缩
        p[x] = find(p[x])
    return p[x]

# 合并a和b所在的两个集合
if find(a) != find(b):
    size[find(b)] += size[find(a)]
    p[find(a)] = find(b)
```

模板 3——维护到祖宗节点距离的并查集：

```python
# 初始化，p存储每个点的父节点，d[x]存储x到p[x]的距离
p = list(range(n))
d = [0] * n

# 返回x的祖宗节点
def find(x):
    if p[x] != x:
        t = find(p[x])
        d[x] += d[p[x]]
        p[x] = t
    return p[x]

# 合并a和b所在的两个集合
p[find(a)] = find(b)
d[find(a)] = distance
```

对于本题，将相似对的所有单词转换为下标，然后套用并查集模板，将相似对合并。

接着遍历 `sentence1`, `sentence2`，若对应的单词相同，直接 continue；若对应的单词不在相似对单词中，或者两单词不在同一个集合中，直接返回 false。否则遍历结束返回 true。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def areSentencesSimilarTwo(self, sentence1: List[str], sentence2: List[str], similarPairs: List[List[str]]) -> bool:
        if len(sentence1) != len(sentence2):
            return False
        n = len(similarPairs)
        p = list(range(n << 1))

        def find(x):
            if p[x] != x:
                p[x] = find(p[x])
            return p[x]

        words = {}
        idx = 0
        for a, b in similarPairs:
            if a not in words:
                words[a] = idx
                idx += 1
            if b not in words:
                words[b] = idx
                idx += 1
            p[find(words[a])] = find(words[b])

        for i in range(len(sentence1)):
            if sentence1[i] == sentence2[i]:
                continue
            if sentence1[i] not in words or sentence2[i] not in words or find(words[sentence1[i]]) != find(words[sentence2[i]]):
                return False
        return True
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    private int[] p;

    public boolean areSentencesSimilarTwo(String[] sentence1, String[] sentence2, List<List<String>> similarPairs) {
        if (sentence1.length != sentence2.length) {
            return false;
        }
        int n = similarPairs.size();
        p = new int[n << 1];
        for (int i = 0; i < p.length; ++i) {
            p[i] = i;
        }
        Map<String, Integer> words = new HashMap<>();
        int idx = 0;
        for (List<String> e : similarPairs) {
            String a = e.get(0), b = e.get(1);
            if (!words.containsKey(a)) {
                words.put(a, idx++);
            }
            if (!words.containsKey(b)) {
                words.put(b, idx++);
            }
            p[find(words.get(a))] = find(words.get(b));
        }
        for (int i = 0; i < sentence1.length; ++i) {
            if (Objects.equals(sentence1[i], sentence2[i])) {
                continue;
            }
            if (!words.containsKey(sentence1[i]) || !words.containsKey(sentence2[i]) || find(words.get(sentence1[i])) != find(words.get(sentence2[i]))) {
                return false;
            }
        }
        return true;
    }

    private int find(int x) {
        if (p[x] != x) {
            p[x] = find(p[x]);
        }
        return p[x];
    }
}
```

### **Go**

```go
var p []int

func areSentencesSimilarTwo(sentence1 []string, sentence2 []string, similarPairs [][]string) bool {
	if len(sentence1) != len(sentence2) {
		return false
	}
	n := len(similarPairs)
	p = make([]int, (n<<1)+10)
	for i := 0; i < len(p); i++ {
		p[i] = i
	}
	words := make(map[string]int)
	idx := 1
	for _, e := range similarPairs {
		a, b := e[0], e[1]
		if words[a] == 0 {
			words[a] = idx
			idx++
		}
		if words[b] == 0 {
			words[b] = idx
			idx++
		}
		p[find(words[a])] = find(words[b])
	}
	for i := 0; i < len(sentence1); i++ {
		if sentence1[i] == sentence2[i] {
			continue
		}
		if words[sentence1[i]] == 0 || words[sentence2[i]] == 0 || find(words[sentence1[i]]) != find(words[sentence2[i]]) {
			return false
		}
	}
	return true
}

func find(x int) int {
	if p[x] != x {
		p[x] = find(p[x])
	}
	return p[x]
}
```

### **...**

```

```

<!-- tabs:end -->
