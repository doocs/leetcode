# [737. Sentence Similarity II](https://leetcode.com/problems/sentence-similarity-ii)

[中文文档](/solution/0700-0799/0737.Sentence%20Similarity%20II/README.md)

## Description

<p>Given two sentences <code>words1, words2</code> (each represented as an array of strings), and a list of similar word pairs <code>pairs</code>, determine if two sentences are similar.</p>

<p>For example, <code>words1 = [&quot;great&quot;, &quot;acting&quot;, &quot;skills&quot;]</code> and <code>words2 = [&quot;fine&quot;, &quot;drama&quot;, &quot;talent&quot;]</code> are similar, if the similar word pairs are <code>pairs = [[&quot;great&quot;, &quot;good&quot;], [&quot;fine&quot;, &quot;good&quot;], [&quot;acting&quot;,&quot;drama&quot;], [&quot;skills&quot;,&quot;talent&quot;]]</code>.</p>

<p>Note that the similarity relation <b>is</b> transitive. For example, if &quot;great&quot; and &quot;good&quot; are similar, and &quot;fine&quot; and &quot;good&quot; are similar, then &quot;great&quot; and &quot;fine&quot; <b>are similar</b>.</p>

<p>Similarity is also symmetric. For example, &quot;great&quot; and &quot;fine&quot; being similar is the same as &quot;fine&quot; and &quot;great&quot; being similar.</p>

<p>Also, a word is always similar with itself. For example, the sentences <code>words1 = [&quot;great&quot;], words2 = [&quot;great&quot;], pairs = []</code> are similar, even though there are no specified similar word pairs.</p>

<p>Finally, sentences can only be similar if they have the same number of words. So a sentence like <code>words1 = [&quot;great&quot;]</code> can never be similar to <code>words2 = [&quot;doubleplus&quot;,&quot;good&quot;]</code>.</p>

<p><b>Note:</b></p>

<ul>
	<li>The length of <code>words1</code> and <code>words2</code> will not exceed <code>1000</code>.</li>
	<li>The length of <code>pairs</code> will not exceed <code>2000</code>.</li>
	<li>The length of each <code>pairs[i]</code> will be <code>2</code>.</li>
	<li>The length of each <code>words[i]</code> and <code>pairs[i][j]</code> will be in the range <code>[1, 20]</code>.</li>
</ul>

<p>&nbsp;</p>

## Solutions

<!-- tabs:start -->

### **Python3**

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
