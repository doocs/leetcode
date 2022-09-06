# [737. 句子相似性 II](https://leetcode.cn/problems/sentence-similarity-ii)

[English Version](/solution/0700-0799/0737.Sentence%20Similarity%20II/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>我们可以将一个句子表示为一个单词数组，例如，句子 <code>I am happy with leetcode"</code>可以表示为&nbsp;<code>arr = ["I","am",happy","with","leetcode"]</code></p>

<p>给定两个句子 <code>sentence1</code> 和 <code>sentence2</code> 分别表示为一个字符串数组，并给定一个字符串对 <code>similarPairs</code> ，其中&nbsp;<code>similarPairs[i] = [x<sub>i</sub>, y<sub>i</sub>]</code>&nbsp;表示两个单词&nbsp;<code>x<sub>i</sub></code>&nbsp;和&nbsp;<code>y<sub>i</sub></code>&nbsp;是相似的。</p>

<p>如果 <code>sentence1</code> 和 <code>sentence2</code> 相似则返回 <code>true</code> ，如果不相似则返回 <code>false</code> 。</p>

<p>两个句子是相似的，如果:</p>

<ul>
	<li>它们具有 <strong>相同的长度</strong> (即相同的字数)</li>
	<li><code>sentence1[i]</code>&nbsp;和&nbsp;<code>sentence2[i]</code>&nbsp;是相似的</li>
</ul>

<p>请注意，一个词总是与它自己相似，也请注意，相似关系是可传递的。例如，如果单词 <code>a</code> 和 <code>b</code> 是相似的，单词&nbsp;<code>b</code> 和 <code>c</code> 也是相似的，那么 <code>a</code> 和 <code>c</code> 也是 <strong>相似</strong> 的。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入:</strong> sentence1 = ["great","acting","skills"], sentence2 = ["fine","drama","talent"], similarPairs = [["great","good"],["fine","good"],["drama","acting"],["skills","talent"]]
<strong>输出:</strong> true
<strong>解释:</strong> 这两个句子长度相同，每个单词都相似。
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入:</strong> sentence1 = ["I","love","leetcode"], sentence2 = ["I","love","onepiece"], similarPairs = [["manga","onepiece"],["platform","anime"],["leetcode","platform"],["anime","manga"]]
<strong>输出:</strong> true
<strong>解释:</strong> "leetcode" --&gt; "platform" --&gt; "anime" --&gt; "manga" --&gt; "onepiece".
因为“leetcode”和“onepiece”相似，而且前两个单词是相同的，所以这两句话是相似的。</pre>

<p><strong>示例 3:</strong></p>

<pre>
<strong>输入:</strong> sentence1 = ["I","love","leetcode"], sentence2 = ["I","love","onepiece"], similarPairs = [["manga","hunterXhunter"],["platform","anime"],["leetcode","platform"],["anime","manga"]]
<strong>输出:</strong> false
<strong>解释: </strong>“leetcode”和“onepiece”不相似。
</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>1 &lt;= sentence1.length, sentence2.length &lt;= 1000</code></li>
	<li><code>1 &lt;= sentence1[i].length, sentence2[i].length &lt;= 20</code></li>
	<li><code>sentence1[i]</code>&nbsp;和&nbsp;<code>sentence2[i]</code>&nbsp;只包含大小写英文字母</li>
	<li><code>0 &lt;= similarPairs.length &lt;= 2000</code></li>
	<li><code>similarPairs[i].length == 2</code></li>
	<li><code>1 &lt;= x<sub>i</sub>.length, y<sub>i</sub>.length &lt;= 20</code></li>
	<li><code>x<sub>i</sub></code>&nbsp;和&nbsp;<code>y<sub>i</sub></code>&nbsp;只含英文字母</li>
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

    public boolean areSentencesSimilarTwo(
        String[] sentence1, String[] sentence2, List<List<String>> similarPairs) {
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
            if (!words.containsKey(sentence1[i]) || !words.containsKey(sentence2[i])
                || find(words.get(sentence1[i])) != find(words.get(sentence2[i]))) {
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
