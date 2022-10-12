# [737. Sentence Similarity II](https://leetcode.com/problems/sentence-similarity-ii)

[中文文档](/solution/0700-0799/0737.Sentence%20Similarity%20II/README.md)

## Description

<p>We can represent a sentence as an array of words, for example, the sentence <code>&quot;I am happy with leetcode&quot;</code> can be represented as <code>arr = [&quot;I&quot;,&quot;am&quot;,happy&quot;,&quot;with&quot;,&quot;leetcode&quot;]</code>.</p>

<p>Given two sentences <code>sentence1</code> and <code>sentence2</code> each represented as a string array and given an array of string pairs <code>similarPairs</code> where <code>similarPairs[i] = [x<sub>i</sub>, y<sub>i</sub>]</code> indicates that the two words <code>x<sub>i</sub></code> and <code>y<sub>i</sub></code> are similar.</p>

<p>Return <code>true</code><em> if <code>sentence1</code> and <code>sentence2</code> are similar, or </em><code>false</code><em> if they are not similar</em>.</p>

<p>Two sentences are similar if:</p>

<ul>
	<li>They have <strong>the same length</strong> (i.e., the same number of words)</li>
	<li><code>sentence1[i]</code> and <code>sentence2[i]</code> are similar.</li>
</ul>

<p>Notice that a word is always similar to itself, also notice that the similarity relation is transitive. For example, if the words <code>a</code> and <code>b</code> are similar, and the words <code>b</code> and <code>c</code> are similar, then&nbsp;<code>a</code> and <code>c</code> are <strong>similar</strong>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> sentence1 = [&quot;great&quot;,&quot;acting&quot;,&quot;skills&quot;], sentence2 = [&quot;fine&quot;,&quot;drama&quot;,&quot;talent&quot;], similarPairs = [[&quot;great&quot;,&quot;good&quot;],[&quot;fine&quot;,&quot;good&quot;],[&quot;drama&quot;,&quot;acting&quot;],[&quot;skills&quot;,&quot;talent&quot;]]
<strong>Output:</strong> true
<strong>Explanation:</strong> The two sentences have the same length and each word i of sentence1 is also similar to the corresponding word in sentence2.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> sentence1 = [&quot;I&quot;,&quot;love&quot;,&quot;leetcode&quot;], sentence2 = [&quot;I&quot;,&quot;love&quot;,&quot;onepiece&quot;], similarPairs = [[&quot;manga&quot;,&quot;onepiece&quot;],[&quot;platform&quot;,&quot;anime&quot;],[&quot;leetcode&quot;,&quot;platform&quot;],[&quot;anime&quot;,&quot;manga&quot;]]
<strong>Output:</strong> true
<strong>Explanation:</strong> &quot;leetcode&quot; --&gt; &quot;platform&quot; --&gt; &quot;anime&quot; --&gt; &quot;manga&quot; --&gt; &quot;onepiece&quot;.
Since &quot;leetcode is similar to &quot;onepiece&quot; and the first two words are the same, the two sentences are similar.</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> sentence1 = [&quot;I&quot;,&quot;love&quot;,&quot;leetcode&quot;], sentence2 = [&quot;I&quot;,&quot;love&quot;,&quot;onepiece&quot;], similarPairs = [[&quot;manga&quot;,&quot;hunterXhunter&quot;],[&quot;platform&quot;,&quot;anime&quot;],[&quot;leetcode&quot;,&quot;platform&quot;],[&quot;anime&quot;,&quot;manga&quot;]]
<strong>Output:</strong> false
<strong>Explanation:</strong> &quot;leetcode&quot; is not similar to &quot;onepiece&quot;.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= sentence1.length, sentence2.length &lt;= 1000</code></li>
	<li><code>1 &lt;= sentence1[i].length, sentence2[i].length &lt;= 20</code></li>
	<li><code>sentence1[i]</code> and <code>sentence2[i]</code> consist of lower-case and upper-case English letters.</li>
	<li><code>0 &lt;= similarPairs.length &lt;= 2000</code></li>
	<li><code>similarPairs[i].length == 2</code></li>
	<li><code>1 &lt;= x<sub>i</sub>.length, y<sub>i</sub>.length &lt;= 20</code></li>
	<li><code>x<sub>i</sub></code> and <code>y<sub>i</sub></code> consist of English letters.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def areSentencesSimilarTwo(
        self, sentence1: List[str], sentence2: List[str], similarPairs: List[List[str]]
    ) -> bool:
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
            if (
                sentence1[i] not in words
                or sentence2[i] not in words
                or find(words[sentence1[i]]) != find(words[sentence2[i]])
            ):
                return False
        return True
```

### **Java**

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
