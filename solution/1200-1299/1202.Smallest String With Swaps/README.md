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

<!-- 这里可写通用的实现逻辑 -->

并查集。对于本题，索引对具备传递性。即如果索引对是 `[0,2]`, `[0, 3]`，那么索引 0、2、3 可以进行任意交换。我们可以利用并查集，遍历每个索引对，将其中的两个索引进行合并，得到并查集，连在一起的索引对应的字符按照字典序排列，这里可以利用优先级队列实现（也可以先用列表存储，再排序）。

最后遍历字符串，找到每个字符的根元素，并替换为字典序中对应的字符。

以下是并查集的几个常用模板。

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

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def smallestStringWithSwaps(self, s: str, pairs: List[List[int]]) -> str:
        def find(x):
            if p[x] != x:
                p[x] = find(p[x])
            return p[x]

        n = len(s)
        p = list(range(n))
        for a, b in pairs:
            p[find(a)] = find(b)
        mp = defaultdict(list)
        for i, c in enumerate(s):
            heappush(mp[find(i)], c)
        return ''.join(heappop(mp[find(i)]) for i in range(n))
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    private int[] p;

    public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
        int n = s.length();
        p = new int[n];
        for (int i = 0; i < n; ++i) {
            p[i] = i;
        }
        for (List<Integer> pair : pairs) {
            p[find(pair.get(0))] = find(pair.get(1));
        }
        Map<Integer, PriorityQueue<Character>> mp = new HashMap<>();
        char[] chars = s.toCharArray();
        for (int i = 0; i < n; ++i) {
            mp.computeIfAbsent(find(i), k -> new PriorityQueue<>()).offer(chars[i]);
        }
        for (int i = 0; i < n; ++i) {
            chars[i] = mp.get(find(i)).poll();
        }
        return new String(chars);
    }

    private int find(int x) {
        if (p[x] != x) {
            p[x] = find(p[x]);
        }
        return p[x];
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<int> p;

    string smallestStringWithSwaps(string s, vector<vector<int>>& pairs) {
        int n = s.length();
        p.resize(n);
        for (int i = 0; i < n; ++i) p[i] = i;
        for (auto& pair : pairs) p[find(pair[0])] = find(pair[1]);
        unordered_map<int, vector<char>> mp;
        for (int i = 0; i < n; ++i) mp[find(i)].push_back(s[i]);
        for (auto& [k, v] : mp) sort(v.rbegin(), v.rend());
        string ans;
        for (int i = 0; i < n; ++i) {
            ans.push_back(mp[find(i)].back());
            mp[find(i)].pop_back();
        }
        return ans;
    }

    int find(int x) {
        if (p[x] != x) p[x] = find(p[x]);
        return p[x];
    }
};
```

### **Go**

```go
func smallestStringWithSwaps(s string, pairs [][]int) string {
	n := len(s)
	p := make([]int, n)
	for i := range p {
		p[i] = i
	}
	var find func(x int) int
	find = func(x int) int {
		if p[x] != x {
			p[x] = find(p[x])
		}
		return p[x]
	}
	for _, pair := range pairs {
		p[find(pair[0])] = find(pair[1])
	}
	mp := make(map[int][]rune)
	for i, c := range s {
		mp[find(i)] = append(mp[find(i)], c)
	}
	for _, v := range mp {
		sort.Slice(v, func(i, j int) bool {
			return v[i] < v[j]
		})
	}
	var ans []rune
	for i := 0; i < n; i++ {
		ans = append(ans, mp[find(i)][0])
		mp[find(i)] = mp[find(i)][1:]
	}
	return string(ans)
}
```

### **...**

```

```

<!-- tabs:end -->
