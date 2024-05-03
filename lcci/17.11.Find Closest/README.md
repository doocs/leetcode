# [面试题 17.11. 单词距离](https://leetcode.cn/problems/find-closest-lcci)

[English Version](/lcci/17.11.Find%20Closest/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->
<p>有个内含单词的超大文本文件，给定任意两个单词，找出在这个文件中这两个单词的最短距离(相隔单词数)。如果寻找过程在这个文件中会重复多次，而每次寻找的单词不同，你能对此优化吗?</p>

<p><strong>示例：</strong></p>

<pre><strong>输入：</strong>words = [&quot;I&quot;,&quot;am&quot;,&quot;a&quot;,&quot;student&quot;,&quot;from&quot;,&quot;a&quot;,&quot;university&quot;,&quot;in&quot;,&quot;a&quot;,&quot;city&quot;], word1 = &quot;a&quot;, word2 = &quot;student&quot;
<strong>输出：</strong>1</pre>

<p>提示：</p>

<ul>
	<li><code>words.length &lt;= 100000</code></li>
</ul>

## 解法

### 方法一：一次遍历

我们用两个指针 $i$ 和 $j$ 分别记录两个单词 $\textit{word1}$ 和 $\textit{word2}$ 最近出现的位置，初始时 $i = \infty$, $j = -\infty$。

接下来我们遍历整个文本文件，对于每个单词 $w$，如果 $w$ 等于 $\textit{word1}$，我们更新 $i = k$，其中 $k$ 是当前单词的下标；如果 $w$ 等于 $\textit{word2}$，我们更新 $j = k$。然后我们更新答案 $ans = \min(ans, |i - j|)$。

遍历结束后，我们返回答案 $ans$。

时间复杂度 $O(n)$，其中 $n$ 是文本文件中的单词数。空间复杂度 $O(1)$。

<!-- tabs:start -->

```python
class Solution:
    def findClosest(self, words: List[str], word1: str, word2: str) -> int:
        i, j = inf, -inf
        ans = inf
        for k, w in enumerate(words):
            if w == word1:
                i = k
            elif w == word2:
                j = k
            ans = min(ans, abs(i - j))
        return ans
```

```java
class Solution {
    public int findClosest(String[] words, String word1, String word2) {
        final int inf = 1 << 29;
        int i = inf, j = -inf, ans = inf;
        for (int k = 0; k < words.length; ++k) {
            if (words[k].equals(word1)) {
                i = k;
            } else if (words[k].equals(word2)) {
                j = k;
            }
            ans = Math.min(ans, Math.abs(i - j));
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int findClosest(vector<string>& words, string word1, string word2) {
        const int inf = 1 << 29;
        int i = inf, j = -inf;
        int ans = inf;
        for (int k = 0; k < words.size(); ++k) {
            if (words[k] == word1) {
                i = k;
            } else if (words[k] == word2) {
                j = k;
            }
            ans = min(ans, abs(i - j));
        }
        return ans;
    }
};
```

```go
func findClosest(words []string, word1 string, word2 string) int {
	const inf int = 1 << 29
	i, j, ans := inf, -inf, inf
	for k, w := range words {
		if w == word1 {
			i = k
		} else if w == word2 {
			j = k
		}
		ans = min(ans, max(i-j, j-i))
	}
	return ans
}
```

```ts
function findClosest(words: string[], word1: string, word2: string): number {
    let [i, j, ans] = [Infinity, -Infinity, Infinity];
    for (let k = 0; k < words.length; ++k) {
        if (words[k] === word1) {
            i = k;
        } else if (words[k] === word2) {
            j = k;
        }
        ans = Math.min(ans, Math.abs(i - j));
    }
    return ans;
}
```

```rust
impl Solution {
    pub fn find_closest(words: Vec<String>, word1: String, word2: String) -> i32 {
        let mut ans = i32::MAX;
        let mut i = -1;
        let mut j = -1;
        for (k, w) in words.iter().enumerate() {
            let k = k as i32;
            if w.eq(&word1) {
                i = k;
            } else if w.eq(&word2) {
                j = k;
            }
            if i != -1 && j != -1 {
                ans = ans.min((i - j).abs());
            }
        }
        ans
    }
}
```

<!-- tabs:end -->

### 方法二：哈希表 + 双指针

我们可以用哈希表 $d$ 记录每个单词出现的位置，然后对于每一对 $\textit{word1}$ 和 $\textit{word2}$，我们可以通过双指针的方法找到它们的最短距离。

我们遍历整个文本文件，对于每个单词 $w$，我们将 $w$ 的下标加入到 $d[w]$ 中。

接下来我们找到 $\textit{word1}$ 和 $\textit{word2}$ 在文本文件中出现的位置，分别用 $idx1$ 和 $idx2$ 表示。然后我们用两个指针 $i$ 和 $j$ 分别指向 $idx1$ 和 $idx2$，初始时 $i = 0$, $j = 0$。

接下来我们遍历 $idx1$ 和 $idx2$，每次我们更新答案 $ans = \min(ans, |idx1[i] - idx2[j]|)$，然后我们将 $i$ 和 $j$ 中较小的那个指针向后移动一位。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 是文本文件中的单词数。

<!-- tabs:start -->

```python
class Solution:
    def findClosest(self, words: List[str], word1: str, word2: str) -> int:
        d = defaultdict(list)
        for i, w in enumerate(words):
            d[w].append(i)
        ans = inf
        idx1, idx2 = d[word1], d[word2]
        i, j, m, n = 0, 0, len(idx1), len(idx2)
        while i < m and j < n:
            ans = min(ans, abs(idx1[i] - idx2[j]))
            if idx1[i] < idx2[j]:
                i += 1
            else:
                j += 1
        return ans
```

```java
class Solution {
    public int findClosest(String[] words, String word1, String word2) {
        Map<String, List<Integer>> d = new HashMap<>();
        for (int i = 0; i < words.length; ++i) {
            d.computeIfAbsent(words[i], k -> new ArrayList<>()).add(i);
        }
        List<Integer> idx1 = d.get(word1), idx2 = d.get(word2);
        int i = 0, j = 0, m = idx1.size(), n = idx2.size();
        int ans = 1 << 29;
        while (i < m && j < n) {
            int t = Math.abs(idx1.get(i) - idx2.get(j));
            ans = Math.min(ans, t);
            if (idx1.get(i) < idx2.get(j)) {
                ++i;
            } else {
                ++j;
            }
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int findClosest(vector<string>& words, string word1, string word2) {
        unordered_map<string, vector<int>> d;
        for (int i = 0; i < words.size(); ++i) {
            d[words[i]].push_back(i);
        }
        vector<int> idx1 = d[word1], idx2 = d[word2];
        int i = 0, j = 0, m = idx1.size(), n = idx2.size();
        int ans = 1e5;
        while (i < m && j < n) {
            int t = abs(idx1[i] - idx2[j]);
            ans = min(ans, t);
            if (idx1[i] < idx2[j]) {
                ++i;
            }
            else {
                ++j;
            }
        }
        return ans;
    }
};
```

```go
func findClosest(words []string, word1 string, word2 string) int {
	d := map[string][]int{}
	for i, w := range words {
		d[w] = append(d[w], i)
	}
	idx1, idx2 := d[word1], d[word2]
	i, j, m, n := 0, 0, len(idx1), len(idx2)
	ans := 1 << 30
	for i < m && j < n {
		t := max(idx1[i]-idx2[j], idx2[j]-idx1[i])
		if t < ans {
			ans = t
		}
		if idx1[i] < idx2[j] {
			i++
		} else {
			j++
		}
	}
	return ans
}
```

```ts
function findClosest(words: string[], word1: string, word2: string): number {
    const d: Map<string, number[]> = new Map();
    for (let i = 0; i < words.length; ++i) {
        if (!d.has(words[i])) {
            d.set(words[i], []);
        }
        d.get(words[i])!.push(i);
    }
    let [i, j] = [0, 0];
    let ans = Infinity;
    while (i < d.get(word1)!.length && j < d.get(word2)!.length) {
        ans = Math.min(ans, Math.abs(d.get(word1)![i] - d.get(word2)![j]));
        if (d.get(word1)![i] < d.get(word2)![j]) {
            ++i;
        } else {
            ++j;
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- end -->
