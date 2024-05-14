---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1200-1299/1202.Smallest%20String%20With%20Swaps/README.md
rating: 1855
tags:
    - 深度优先搜索
    - 广度优先搜索
    - 并查集
    - 数组
    - 哈希表
    - 字符串
    - 排序
---

# [1202. 交换字符串中的元素](https://leetcode.cn/problems/smallest-string-with-swaps)

[English Version](/solution/1200-1299/1202.Smallest%20String%20With%20Swaps/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个字符串&nbsp;<code>s</code>，以及该字符串中的一些「索引对」数组&nbsp;<code>pairs</code>，其中&nbsp;<code>pairs[i] =&nbsp;[a, b]</code>&nbsp;表示字符串中的两个索引（编号从 0 开始）。</p>

<p>你可以 <strong>任意多次交换</strong> 在&nbsp;<code>pairs</code>&nbsp;中任意一对索引处的字符。</p>

<p>返回在经过若干次交换后，<code>s</code>&nbsp;可以变成的按字典序最小的字符串。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre><strong>输入：</strong>s = &quot;dcab&quot;, pairs = [[0,3],[1,2]]
<strong>输出：</strong>&quot;bacd&quot;
<strong>解释：</strong> 
交换 s[0] 和 s[3], s = &quot;bcad&quot;
交换 s[1] 和 s[2], s = &quot;bacd&quot;
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>s = &quot;dcab&quot;, pairs = [[0,3],[1,2],[0,2]]
<strong>输出：</strong>&quot;abcd&quot;
<strong>解释：</strong>
交换 s[0] 和 s[3], s = &quot;bcad&quot;
交换 s[0] 和 s[2], s = &quot;acbd&quot;
交换 s[1] 和 s[2], s = &quot;abcd&quot;</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>s = &quot;cba&quot;, pairs = [[0,1],[1,2]]
<strong>输出：</strong>&quot;abc&quot;
<strong>解释：</strong>
交换 s[0] 和 s[1], s = &quot;bca&quot;
交换 s[1] 和 s[2], s = &quot;bac&quot;
交换 s[0] 和 s[1], s = &quot;abc&quot;
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10^5</code></li>
	<li><code>0 &lt;= pairs.length &lt;= 10^5</code></li>
	<li><code>0 &lt;= pairs[i][0], pairs[i][1] &lt;&nbsp;s.length</code></li>
	<li><code>s</code>&nbsp;中只含有小写英文字母</li>
</ul>

## 解法

### 方法一：并查集

我们注意到，索引对具有传递性，即如果 $a$ 与 $b$ 可交换，而 $b$ 与 $c$ 可交换，那么 $a$ 与 $c$ 也可交换。因此，我们可以考虑使用并查集维护这些索引对的连通性，将属于同一个连通分量的字符按照字典序排序。

最后，遍历字符串，对于当前位置的字符，我们将其替换为该连通分量中最小的字符，然后从该连通分量中取出该字符，继续遍历字符串即可。

时间复杂度 $O(n \times \log n + m \times \alpha(m))$，空间复杂度 $O(n)$。其中 $n$ 和 $m$ 分别为字符串的长度和索引对的数量，而 $\alpha$ 为阿克曼函数的反函数。

<!-- tabs:start -->

```python
class Solution:
    def smallestStringWithSwaps(self, s: str, pairs: List[List[int]]) -> str:
        def find(x: int) -> int:
            if p[x] != x:
                p[x] = find(p[x])
            return p[x]

        n = len(s)
        p = list(range(n))
        for a, b in pairs:
            p[find(a)] = find(b)
        d = defaultdict(list)
        for i, c in enumerate(s):
            d[find(i)].append(c)
        for i in d.keys():
            d[i].sort(reverse=True)
        return "".join(d[find(i)].pop() for i in range(n))
```

```java
class Solution {
    private int[] p;

    public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
        int n = s.length();
        p = new int[n];
        List<Character>[] d = new List[n];
        for (int i = 0; i < n; ++i) {
            p[i] = i;
            d[i] = new ArrayList<>();
        }
        for (var pair : pairs) {
            int a = pair.get(0), b = pair.get(1);
            p[find(a)] = find(b);
        }
        char[] cs = s.toCharArray();
        for (int i = 0; i < n; ++i) {
            d[find(i)].add(cs[i]);
        }
        for (var e : d) {
            e.sort((a, b) -> b - a);
        }
        for (int i = 0; i < n; ++i) {
            var e = d[find(i)];
            cs[i] = e.remove(e.size() - 1);
        }
        return String.valueOf(cs);
    }

    private int find(int x) {
        if (p[x] != x) {
            p[x] = find(p[x]);
        }
        return p[x];
    }
}
```

```cpp
class Solution {
public:
    string smallestStringWithSwaps(string s, vector<vector<int>>& pairs) {
        int n = s.size();
        int p[n];
        iota(p, p + n, 0);
        vector<char> d[n];
        function<int(int)> find = [&](int x) -> int {
            if (p[x] != x) {
                p[x] = find(p[x]);
            }
            return p[x];
        };
        for (auto e : pairs) {
            int a = e[0], b = e[1];
            p[find(a)] = find(b);
        }
        for (int i = 0; i < n; ++i) {
            d[find(i)].push_back(s[i]);
        }
        for (auto& e : d) {
            sort(e.rbegin(), e.rend());
        }
        for (int i = 0; i < n; ++i) {
            auto& e = d[find(i)];
            s[i] = e.back();
            e.pop_back();
        }
        return s;
    }
};
```

```go
func smallestStringWithSwaps(s string, pairs [][]int) string {
	n := len(s)
	p := make([]int, n)
	d := make([][]byte, n)
	for i := range p {
		p[i] = i
	}
	var find func(int) int
	find = func(x int) int {
		if p[x] != x {
			p[x] = find(p[x])
		}
		return p[x]
	}
	for _, pair := range pairs {
		a, b := pair[0], pair[1]
		p[find(a)] = find(b)
	}
	cs := []byte(s)
	for i, c := range cs {
		j := find(i)
		d[j] = append(d[j], c)
	}
	for i := range d {
		sort.Slice(d[i], func(a, b int) bool { return d[i][a] > d[i][b] })
	}
	for i := range cs {
		j := find(i)
		cs[i] = d[j][len(d[j])-1]
		d[j] = d[j][:len(d[j])-1]
	}
	return string(cs)
}
```

```ts
function smallestStringWithSwaps(s: string, pairs: number[][]): string {
    const n = s.length;
    const p = new Array(n).fill(0).map((_, i) => i);
    const find = (x: number): number => {
        if (p[x] !== x) {
            p[x] = find(p[x]);
        }
        return p[x];
    };
    const d: string[][] = new Array(n).fill(0).map(() => []);
    for (const [a, b] of pairs) {
        p[find(a)] = find(b);
    }
    for (let i = 0; i < n; ++i) {
        d[find(i)].push(s[i]);
    }
    for (const e of d) {
        e.sort((a, b) => b.charCodeAt(0) - a.charCodeAt(0));
    }
    const ans: string[] = [];
    for (let i = 0; i < n; ++i) {
        ans.push(d[find(i)].pop()!);
    }
    return ans.join('');
}
```

```rust
impl Solution {
    #[allow(dead_code)]
    pub fn smallest_string_with_swaps(s: String, pairs: Vec<Vec<i32>>) -> String {
        let n = s.as_bytes().len();
        let s = s.as_bytes();
        let mut disjoint_set: Vec<usize> = vec![0; n];
        let mut str_vec: Vec<Vec<u8>> = vec![Vec::new(); n];
        let mut ret_str = String::new();

        // Initialize the disjoint set
        for i in 0..n {
            disjoint_set[i] = i;
        }

        // Union the pairs
        for pair in pairs {
            Self::union(pair[0] as usize, pair[1] as usize, &mut disjoint_set);
        }

        // Initialize the return vector
        for (i, c) in s.iter().enumerate() {
            let p_c = Self::find(i, &mut disjoint_set);
            str_vec[p_c].push(*c);
        }

        // Sort the return vector in reverse order
        for cur_vec in &mut str_vec {
            cur_vec.sort();
            cur_vec.reverse();
        }

        // Construct the return string
        for i in 0..n {
            let index = Self::find(i, &mut disjoint_set);
            ret_str.push(str_vec[index].last().unwrap().clone() as char);
            str_vec[index].pop();
        }

        ret_str
    }

    #[allow(dead_code)]
    fn find(x: usize, d_set: &mut Vec<usize>) -> usize {
        if d_set[x] != x {
            d_set[x] = Self::find(d_set[x], d_set);
        }
        d_set[x]
    }

    #[allow(dead_code)]
    fn union(x: usize, y: usize, d_set: &mut Vec<usize>) {
        let p_x = Self::find(x, d_set);
        let p_y = Self::find(y, d_set);
        d_set[p_x] = p_y;
    }
}
```

<!-- tabs:end -->

<!-- end -->
