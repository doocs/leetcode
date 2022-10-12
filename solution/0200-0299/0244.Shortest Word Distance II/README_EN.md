# [244. Shortest Word Distance II](https://leetcode.com/problems/shortest-word-distance-ii)

[中文文档](/solution/0200-0299/0244.Shortest%20Word%20Distance%20II/README.md)

## Description

<p>Design a data structure that will be initialized with a string array, and then it should answer queries of the shortest distance between two different strings from the array.</p>

<p>Implement the <code>WordDistance</code> class:</p>

<ul>
	<li><code>WordDistance(String[] wordsDict)</code> initializes the object with the strings array <code>wordsDict</code>.</li>
	<li><code>int shortest(String word1, String word2)</code> returns the shortest distance between <code>word1</code> and <code>word2</code> in the array <code>wordsDict</code>.</li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input</strong>
[&quot;WordDistance&quot;, &quot;shortest&quot;, &quot;shortest&quot;]
[[[&quot;practice&quot;, &quot;makes&quot;, &quot;perfect&quot;, &quot;coding&quot;, &quot;makes&quot;]], [&quot;coding&quot;, &quot;practice&quot;], [&quot;makes&quot;, &quot;coding&quot;]]
<strong>Output</strong>
[null, 3, 1]

<strong>Explanation</strong>
WordDistance wordDistance = new WordDistance([&quot;practice&quot;, &quot;makes&quot;, &quot;perfect&quot;, &quot;coding&quot;, &quot;makes&quot;]);
wordDistance.shortest(&quot;coding&quot;, &quot;practice&quot;); // return 3
wordDistance.shortest(&quot;makes&quot;, &quot;coding&quot;);    // return 1
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= wordsDict.length &lt;= 3 * 10<sup>4</sup></code></li>
	<li><code>1 &lt;= wordsDict[i].length &lt;= 10</code></li>
	<li><code>wordsDict[i]</code> consists of lowercase English letters.</li>
	<li><code>word1</code> and <code>word2</code> are in <code>wordsDict</code>.</li>
	<li><code>word1 != word2</code></li>
	<li>At most <code>5000</code> calls will be made to <code>shortest</code>.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class WordDistance:
    def __init__(self, wordsDict: List[str]):
        self.d = defaultdict(list)
        for i, w in enumerate(wordsDict):
            self.d[w].append(i)

    def shortest(self, word1: str, word2: str) -> int:
        a, b = self.d[word1], self.d[word2]
        ans = inf
        i = j = 0
        while i < len(a) and j < len(b):
            ans = min(ans, abs(a[i] - b[j]))
            if a[i] <= b[j]:
                i += 1
            else:
                j += 1
        return ans


# Your WordDistance object will be instantiated and called as such:
# obj = WordDistance(wordsDict)
# param_1 = obj.shortest(word1,word2)
```

### **Java**

```java
class WordDistance {
    private Map<String, List<Integer>> d = new HashMap<>();

    public WordDistance(String[] wordsDict) {
        for (int i = 0; i < wordsDict.length; ++i) {
            d.computeIfAbsent(wordsDict[i], k -> new ArrayList<>()).add(i);
        }
    }

    public int shortest(String word1, String word2) {
        List<Integer> a = d.get(word1), b = d.get(word2);
        int ans = 0x3f3f3f3f;
        int i = 0, j = 0;
        while (i < a.size() && j < b.size()) {
            ans = Math.min(ans, Math.abs(a.get(i) - b.get(j)));
            if (a.get(i) <= b.get(j)) {
                ++i;
            } else {
                ++j;
            }
        }
        return ans;
    }
}

/**
 * Your WordDistance object will be instantiated and called as such:
 * WordDistance obj = new WordDistance(wordsDict);
 * int param_1 = obj.shortest(word1,word2);
 */
```

### **C++**

```cpp
class WordDistance {
public:
    WordDistance(vector<string>& wordsDict) {
        for (int i = 0; i < wordsDict.size(); ++i) {
            d[wordsDict[i]].push_back(i);
        }
    }

    int shortest(string word1, string word2) {
        auto a = d[word1], b = d[word2];
        int i = 0, j = 0;
        int ans = INT_MAX;
        while (i < a.size() && j < b.size()) {
            ans = min(ans, abs(a[i] - b[j]));
            if (a[i] <= b[j]) {
                ++i;
            } else {
                ++j;
            }
        }
        return ans;
    }
private:
    unordered_map<string, vector<int>> d;
};

/**
 * Your WordDistance object will be instantiated and called as such:
 * WordDistance* obj = new WordDistance(wordsDict);
 * int param_1 = obj->shortest(word1,word2);
 */
```

### **Go**

```go
type WordDistance struct {
	d map[string][]int
}

func Constructor(wordsDict []string) WordDistance {
	d := map[string][]int{}
	for i, w := range wordsDict {
		d[w] = append(d[w], i)
	}
	return WordDistance{d}
}

func (this *WordDistance) Shortest(word1 string, word2 string) int {
	a, b := this.d[word1], this.d[word2]
	ans := 0x3f3f3f3f
	i, j := 0, 0
	for i < len(a) && j < len(b) {
		ans = min(ans, abs(a[i]-b[j]))
		if a[i] <= b[j] {
			i++
		} else {
			j++
		}
	}
	return ans
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}

/**
 * Your WordDistance object will be instantiated and called as such:
 * obj := Constructor(wordsDict);
 * param_1 := obj.Shortest(word1,word2);
 */
```

### **...**

```

```

<!-- tabs:end -->
