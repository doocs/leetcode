---
comment: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/lcci/17.11.Find%20Closest/README_EN.md
---

# [17.11. Find Closest](https://leetcode.cn/problems/find-closest-lcci)

[中文文档](/lcci/17.11.Find%20Closest/README.md)

## Description

<p>You have a large text file containing words. Given any two words, find the shortest distance (in terms of number of words) between them in the file. If the operation will be repeated many times for the same file (but different pairs of words), can you optimize your solution?</p>

<p><strong>Example: </strong></p>

<pre>

<strong>Input: </strong>words = [&quot;I&quot;,&quot;am&quot;,&quot;a&quot;,&quot;student&quot;,&quot;from&quot;,&quot;a&quot;,&quot;university&quot;,&quot;in&quot;,&quot;a&quot;,&quot;city&quot;], word1 = &quot;a&quot;, word2 = &quot;student&quot;

<strong>Output: </strong>1</pre>

<p>Note:</p>

<ul>
	<li><code>words.length &lt;= 100000</code></li>
</ul>

## Solutions

### Solution 1: Single Pass

We use two pointers $i$ and $j$ to record the most recent occurrences of the two words $\textit{word1}$ and $\textit{word2}$, respectively. Initially, $i = \infty$ and $j = -\infty$.

Next, we traverse the entire text file. For each word $w$, if $w$ equals $\textit{word1}$, we update $i = k$, where $k$ is the index of the current word; if $w$ equals $\textit{word2}$, we update $j = k$. Then we update the answer $ans = \min(ans, |i - j|)$.

After the traversal, we return the answer $ans$.

The time complexity is $O(n)$, where $n$ is the number of words in the text file. The space complexity is $O(1)$.

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

```swift
class Solution {
    func findClosest(_ words: [String], _ word1: String, _ word2: String) -> Int {
        let inf = Int.max / 2
        var i = inf
        var j = -inf
        var ans = inf

        for (k, word) in words.enumerated() {
            if word == word1 {
                i = k
            } else if word == word2 {
                j = k
            }
            ans = min(ans, abs(i - j))
        }

        return ans
    }
}
```

<!-- tabs:end -->

### Solution 2: Hash Table + Two Pointers

We can use a hash table $d$ to record the positions of each word. Then, for each pair of $\textit{word1}$ and $\textit{word2}$, we can find their shortest distance using the two-pointer method.

We traverse the entire text file. For each word $w$, we add the index of $w$ to $d[w]$.

Next, we find the positions where $\textit{word1}$ and $\textit{word2}$ appear in the text file, represented by $idx1$ and $idx2$ respectively. Then we use two pointers $i$ and $j$ to point to $idx1$ and $idx2$ respectively, with initial values $i = 0$, $j = 0$.

Next, we traverse $idx1$ and $idx2$. Each time we update the answer $ans = \min(ans, |idx1[i] - idx2[j]|)$, then we move the smaller pointer of $i$ and $j$ one step backward.

The time complexity is $O(n)$, and the space complexity is $O(n)$. Where $n$ is the number of words in the text file.

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
            } else {
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
