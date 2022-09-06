# [1722. 执行交换操作后的最小汉明距离](https://leetcode.cn/problems/minimize-hamming-distance-after-swap-operations)

[English Version](/solution/1700-1799/1722.Minimize%20Hamming%20Distance%20After%20Swap%20Operations/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你两个整数数组 <code>source</code> 和 <code>target</code> ，长度都是 <code>n</code> 。还有一个数组 <code>allowedSwaps</code> ，其中每个 <code>allowedSwaps[i] = [a<sub>i</sub>, b<sub>i</sub>]</code> 表示你可以交换数组 <code>source</code> 中下标为 <code>a<sub>i</sub></code> 和 <code>b<sub>i</sub></code>（<strong>下标从 0 开始</strong>）的两个元素。注意，你可以按 <strong>任意</strong> 顺序 <strong>多次</strong> 交换一对特定下标指向的元素。</p>

<p>相同长度的两个数组 <code>source</code> 和 <code>target</code> 间的 <strong>汉明距离</strong> 是元素不同的下标数量。形式上，其值等于满足 <code>source[i] != target[i]</code> （<strong>下标从 0 开始</strong>）的下标 <code>i</code>（<code>0 &lt;= i &lt;= n-1</code>）的数量。</p>

<p>在对数组 <code>source</code> 执行 <strong>任意</strong> 数量的交换操作后，返回 <code>source</code> 和 <code>target</code> 间的 <strong>最小汉明距离</strong> 。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>source = [1,2,3,4], target = [2,1,4,5], allowedSwaps = [[0,1],[2,3]]
<strong>输出：</strong>1
<strong>解释：</strong>source 可以按下述方式转换：
- 交换下标 0 和 1 指向的元素：source = [<strong>2</strong>,<strong>1</strong>,3,4]
- 交换下标 2 和 3 指向的元素：source = [2,1,<strong>4</strong>,<strong>3</strong>]
source 和 target 间的汉明距离是 1 ，二者有 1 处元素不同，在下标 3 。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>source = [1,2,3,4], target = [1,3,2,4], allowedSwaps = []
<strong>输出：</strong>2
<strong>解释：</strong>不能对 source 执行交换操作。
source 和 target 间的汉明距离是 2 ，二者有 2 处元素不同，在下标 1 和下标 2 。</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>source = [5,1,2,4,3], target = [1,5,4,2,3], allowedSwaps = [[0,4],[4,2],[1,3],[1,4]]
<strong>输出：</strong>0
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == source.length == target.length</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= source[i], target[i] &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= allowedSwaps.length &lt;= 10<sup>5</sup></code></li>
	<li><code>allowedSwaps[i].length == 2</code></li>
	<li><code>0 &lt;= a<sub>i</sub>, b<sub>i</sub> &lt;= n - 1</code></li>
	<li><code>a<sub>i</sub> != b<sub>i</sub></code></li>
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

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def minimumHammingDistance(self, source: List[int], target: List[int], allowedSwaps: List[List[int]]) -> int:
        n = len(source)
        p = list(range(n))

        def find(x):
            if p[x] != x:
                p[x] = find(p[x])
            return p[x]

        for i, j in allowedSwaps:
            p[find(i)] = find(j)

        mp = defaultdict(Counter)
        for i in range(n):
            mp[find(i)][source[i]] += 1
        res = 0
        for i in range(n):
            if mp[find(i)][target[i]] > 0:
                mp[find(i)][target[i]] -= 1
            else:
                res += 1
        return res
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    private int[] p;

    public int minimumHammingDistance(int[] source, int[] target, int[][] allowedSwaps) {
        int n = source.length;
        p = new int[n];
        for (int i = 0; i < n; ++i) {
            p[i] = i;
        }
        for (int[] e : allowedSwaps) {
            p[find(e[0])] = find(e[1]);
        }
        Map<Integer, Map<Integer, Integer>> mp = new HashMap<>();
        for (int i = 0; i < n; ++i) {
            int root = find(i);
            mp.computeIfAbsent(root, k -> new HashMap<>())
                .put(source[i], mp.get(root).getOrDefault(source[i], 0) + 1);
        }
        int res = 0;
        for (int i = 0; i < n; ++i) {
            int root = find(i);
            if (mp.get(root).getOrDefault(target[i], 0) > 0) {
                mp.get(root).put(target[i], mp.get(root).get(target[i]) - 1);
            } else {
                ++res;
            }
        }
        return res;
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

    int minimumHammingDistance(vector<int>& source, vector<int>& target, vector<vector<int>>& allowedSwaps) {
        int n = source.size();
        p.resize(n);
        for (int i = 0; i < n; ++i) p[i] = i;
        for (auto e : allowedSwaps) p[find(e[0])] = find(e[1]);
        unordered_map<int, unordered_map<int, int>> mp;
        for (int i = 0; i < n; ++i) ++mp[find(i)][source[i]];
        int res = 0;
        for (int i = 0; i < n; ++i) {
            if (mp[find(i)][target[i]] > 0)
                --mp[find(i)][target[i]];
            else
                ++res;
        }
        return res;
    }

    int find(int x) {
        if (p[x] != x) p[x] = find(p[x]);
        return p[x];
    }
};
```

### **Go**

```go
var p []int

func minimumHammingDistance(source []int, target []int, allowedSwaps [][]int) int {
	n := len(source)
	p = make([]int, n)
	for i := 0; i < n; i++ {
		p[i] = i
	}
	for _, e := range allowedSwaps {
		p[find(e[0])] = find(e[1])
	}
	mp := make(map[int]map[int]int)
	for i := 0; i < n; i++ {
		if mp[find(i)] == nil {
			mp[find(i)] = make(map[int]int)
		}
		mp[find(i)][source[i]]++
	}
	res := 0
	for i := 0; i < n; i++ {
		if mp[find(i)][target[i]] > 0 {
			mp[find(i)][target[i]]--
		} else {
			res++
		}
	}
	return res
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
