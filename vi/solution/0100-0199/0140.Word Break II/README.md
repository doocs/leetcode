---
comments: true
difficulty: Hard
tags:
    - Trie
    - Memoization
    - Array
    - Hash Table
    - String
    - Dynamic Programming
    - Backtracking
---

<!-- problem:start -->

# [140. Word Break II](https://leetcode.com/problems/word-break-ii)

[中文文档](/solution/0100-0199/0140.Word%20Break%20II/README.md)

## Mô tả

<!-- description:start -->

<p>Cho một chuỗi <code>s</code> và một từ điển các chuỗi <code>wordDict</code>, hãy thêm dấu cách vào <code>s</code> để tạo thành một câu trong đó mỗi từ là một từ hợp lệ trong từ điển. Trả về tất cả các câu có thể có theo <strong>bất kỳ thứ tự nào</strong>.</p>

<p><strong>Lưu ý</strong> rằng cùng một từ trong từ điển có thể được sử dụng lại nhiều lần trong quá trình phân tách.</p>

<p>&nbsp;</p>
<p><strong class="example">Ví dụ 1:</strong></p>

<pre>
<strong>Đầu vào:</strong> s = &quot;catsanddog&quot;, wordDict = [&quot;cat&quot;,&quot;cats&quot;,&quot;and&quot;,&quot;sand&quot;,&quot;dog&quot;]
<strong>Đầu ra:</strong> [&quot;cats and dog&quot;,&quot;cat sand dog&quot;]
</pre>

<p><strong class="example">Ví dụ 2:</strong></p>

<pre>
<strong>Đầu vào:</strong> s = &quot;pineapplepenapple&quot;, wordDict = [&quot;apple&quot;,&quot;pen&quot;,&quot;applepen&quot;,&quot;pine&quot;,&quot;pineapple&quot;]
<strong>Đầu ra:</strong> [&quot;pine apple pen apple&quot;,&quot;pineapple pen apple&quot;,&quot;pine applepen apple&quot;]
<strong>Giải thích:</strong> Lưu ý rằng bạn được phép sử dụng lại một từ trong từ điển.
</pre>

<p><strong class="example">Ví dụ 3:</strong></p>

<pre>
<strong>Đầu vào:</strong> s = &quot;catsandog&quot;, wordDict = [&quot;cats&quot;,&quot;dog&quot;,&quot;sand&quot;,&quot;and&quot;,&quot;cat&quot;]
<strong>Đầu ra:</strong> []
</pre>

<p>&nbsp;</p>
<p><strong>Ràng buộc:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 20</code></li>
	<li><code>1 &lt;= wordDict.length &lt;= 1000</code></li>
	<li><code>1 &lt;= wordDict[i].length &lt;= 10</code></li>
	<li><code>s</code> và <code>wordDict[i]</code> chỉ gồm các chữ cái tiếng Anh viết thường.</li>
	<li>Tất cả các chuỗi trong <code>wordDict</code> đều <strong>khác nhau</strong>.</li>
	<li>Dữ liệu đầu vào được tạo sao cho độ dài của đáp án không vượt quá&nbsp;10<sup>5</sup>.</li>
</ul>

<!-- description:end -->

## Lời giải

<!-- solution:start -->

### Lời giải 1

<!-- thinking:start -->

> **Tư duy**
>
> Chúng ta phải liệt kê mọi câu, không chỉ quyết định xem có khả thi hay không. $|s|\le 20$, nên số cách phân hoạch có thể xử lý được. Sau khi chèn các từ vào một trie, thử mọi từ tiếp theo kết thúc ở hậu tố hiện tại; khi tìm thấy, đệ quy trên phần còn lại và nối các phần lại. Trie kiểm tra các tiền tố nhanh chóng.

<!-- thinking:end -->

<!-- tabs:start -->

#### Python3

```python
class Trie:
    def __init__(self):
        self.children = [None] * 26
        self.is_end = False

    def insert(self, word):
        node = self
        for c in word:
            idx = ord(c) - ord('a')
            if node.children[idx] is None:
                node.children[idx] = Trie()
            node = node.children[idx]
        node.is_end = True

    def search(self, word):
        node = self
        for c in word:
            idx = ord(c) - ord('a')
            if node.children[idx] is None:
                return False
            node = node.children[idx]
        return node.is_end


class Solution:
    def wordBreak(self, s: str, wordDict: List[str]) -> List[str]:
        def dfs(s):
            if not s:
                return [[]]
            res = []
            for i in range(1, len(s) + 1):
                if trie.search(s[:i]):
                    for v in dfs(s[i:]):
                        res.append([s[:i]] + v)
            return res

        trie = Trie()
        for w in wordDict:
            trie.insert(w)
        ans = dfs(s)
        return [' '.join(v) for v in ans]
```

#### Java

```java
class Trie {
    Trie[] children = new Trie[26];
    boolean isEnd;

    void insert(String word) {
        Trie node = this;
        for (char c : word.toCharArray()) {
            c -= 'a';
            if (node.children[c] == null) {
                node.children[c] = new Trie();
            }
            node = node.children[c];
        }
        node.isEnd = true;
    }

    boolean search(String word) {
        Trie node = this;
        for (char c : word.toCharArray()) {
            c -= 'a';
            if (node.children[c] == null) {
                return false;
            }
            node = node.children[c];
        }
        return node.isEnd;
    }
}

class Solution {
    private Trie trie = new Trie();

    public List<String> wordBreak(String s, List<String> wordDict) {
        for (String w : wordDict) {
            trie.insert(w);
        }
        List<List<String>> res = dfs(s);
        return res.stream().map(e -> String.join(" ", e)).collect(Collectors.toList());
    }

    private List<List<String>> dfs(String s) {
        List<List<String>> res = new ArrayList<>();
        if ("".equals(s)) {
            res.add(new ArrayList<>());
            return res;
        }
        for (int i = 1; i <= s.length(); ++i) {
            if (trie.search(s.substring(0, i))) {
                for (List<String> v : dfs(s.substring(i))) {
                    v.add(0, s.substring(0, i));
                    res.add(v);
                }
            }
        }
        return res;
    }
}
```

#### Go

```go
type Trie struct {
	children [26]*Trie
	isEnd    bool
}

func newTrie() *Trie {
	return &Trie{}
}
func (this *Trie) insert(word string) {
	node := this
	for _, c := range word {
		c -= 'a'
		if node.children[c] == nil {
			node.children[c] = newTrie()
		}
		node = node.children[c]
	}
	node.isEnd = true
}
func (this *Trie) search(word string) bool {
	node := this
	for _, c := range word {
		c -= 'a'
		if node.children[c] == nil {
			return false
		}
		node = node.children[c]
	}
	return node.isEnd
}

func wordBreak(s string, wordDict []string) []string {
	trie := newTrie()
	for _, w := range wordDict {
		trie.insert(w)
	}
	var dfs func(string) [][]string
	dfs = func(s string) [][]string {
		res := [][]string{}
		if len(s) == 0 {
			res = append(res, []string{})
			return res
		}
		for i := 1; i <= len(s); i++ {
			if trie.search(s[:i]) {
				for _, v := range dfs(s[i:]) {
					v = append([]string{s[:i]}, v...)
					res = append(res, v)
				}
			}
		}
		return res
	}
	res := dfs(s)
	ans := []string{}
	for _, v := range res {
		ans = append(ans, strings.Join(v, " "))
	}
	return ans
}
```

#### C#

```cs
class Node {
    public int Index1 { get; set; }
    public int Index2 { get; set; }
}

public class Solution {
    public IList<string> WordBreak(string s, IList<string> wordDict) {
        var paths = new List<Tuple<int, string>>[s.Length + 1];
        paths[s.Length] = new List<Tuple<int, string>> { Tuple.Create(-1, (string)null) };
        var wordDictGroup = wordDict.GroupBy(word => word.Length);
        for (var i = s.Length - 1; i >= 0; --i) {
            paths[i] = new List<Tuple<int, string>>();
            foreach (var wordGroup in wordDictGroup) {
                var wordLength = wordGroup.Key;
                if (i + wordLength <= s.Length && paths[i + wordLength].Count > 0) {
                    foreach (var word in wordGroup) {
                        if (s.Substring(i, wordLength) == word) {
                            paths[i].Add(Tuple.Create(i + wordLength, word));
                        }
                    }
                }
            }
        }

        return GenerateResults(paths);
    }

    private IList<string> GenerateResults(List<Tuple<int, string>>[] paths) {
        var results = new List<string>();
        var sb = new StringBuilder();
        var stack = new Stack<Node>();
        stack.Push(new Node());
        while (stack.Count > 0) {
            var node = stack.Peek();
            if (node.Index1 == paths.Length - 1 || node.Index2 == paths[node.Index1].Count) {
                if (node.Index1 == paths.Length - 1) {
                    results.Add(sb.ToString());
                }
                stack.Pop();
                if (stack.Count > 0) {
                    var parent = stack.Peek();
                    var length = paths[parent.Index1][parent.Index2 - 1].Item2.Length;
                    if (length < sb.Length) ++length;
                    sb.Remove(sb.Length - length, length);
                }
            }
            else {
                var newNode = new Node { Index1 = paths[node.Index1][node.Index2].Item1, Index2 = 0 };
                if (sb.Length != 0) {
                    sb.Append(' ');
                }
                sb.Append(paths[node.Index1][node.Index2].Item2);
                stack.Push(newNode);
                ++node.Index2;
            }
        }
        return results;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
