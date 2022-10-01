# [244. 最短单词距离 II](https://leetcode.cn/problems/shortest-word-distance-ii)

[English Version](/solution/0200-0299/0244.Shortest%20Word%20Distance%20II/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>请设计一个类，使该类的构造函数能够接收一个字符串数组。然后再实现一个方法，该方法能够分别接收两个单词<em>，</em>并返回列表中这两个单词之间的最短距离。</p>

<p>实现&nbsp;<code>WordDistanc</code>&nbsp;类:</p>

<ul>
	<li><code>WordDistance(String[] wordsDict)</code>&nbsp;用字符串数组 <code>wordsDict</code> 初始化对象。</li>
	<li><code>int shortest(String word1, String word2)</code>&nbsp;返回数组 <code>worddict</code> 中 <code>word1</code> 和 <code>word2</code> 之间的最短距离。</li>
</ul>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入:</strong> 
["WordDistance", "shortest", "shortest"]
[[["practice", "makes", "perfect", "coding", "makes"]], ["coding", "practice"], ["makes", "coding"]]
<strong>输出:</strong>
[null, 3, 1]

<b>解释：</b>
WordDistance wordDistance = new WordDistance(["practice", "makes", "perfect", "coding", "makes"]);
wordDistance.shortest("coding", "practice"); // 返回 3
wordDistance.shortest("makes", "coding");    // 返回 1</pre>

<p>&nbsp;</p>

<p><strong>注意:</strong><meta charset="UTF-8" /></p>

<ul>
	<li><code>1 &lt;= wordsDict.length &lt;= 3 * 10<sup>4</sup></code></li>
	<li><code>1 &lt;= wordsDict[i].length &lt;= 10</code></li>
	<li><code>wordsDict[i]</code>&nbsp;由小写英文字母组成</li>
	<li><code>word1</code>&nbsp;和&nbsp;<code>word2</code>&nbsp;在数组&nbsp;<code>wordsDict</code>&nbsp;中</li>
	<li><code>word1 != word2</code></li>
	<li>&nbsp;<code>shortest</code>&nbsp;操作次数不大于&nbsp;<code>5000</code>&nbsp;</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：哈希表 + 双指针**

我们用哈希表 $d$ 存储每个单词在数组中出现的所有下标，然后用双指针 $i$ 和 $j$ 分别指向两个单词在数组中出现的下标列表 $a$ 和 $b$，每次更新下标差值的最小值，然后移动下标较小的指针，直到其中一个指针遍历完下标列表。

初始化的时间复杂度为 $O(n)$，其中 $n$ 为数组的长度。每次调用 `shortest` 方法的时间复杂度为 $O(m + n)$，其中 $m$ 为两个单词在数组中出现的下标列表的长度之和。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
