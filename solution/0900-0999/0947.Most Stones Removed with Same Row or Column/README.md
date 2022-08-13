# [947. 移除最多的同行或同列石头](https://leetcode.cn/problems/most-stones-removed-with-same-row-or-column)

[English Version](/solution/0900-0999/0947.Most%20Stones%20Removed%20with%20Same%20Row%20or%20Column/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p><code>n</code> 块石头放置在二维平面中的一些整数坐标点上。每个坐标点上最多只能有一块石头。</p>

<p>如果一块石头的 <strong>同行或者同列</strong> 上有其他石头存在，那么就可以移除这块石头。</p>

<p>给你一个长度为 <code>n</code> 的数组 <code>stones</code> ，其中 <code>stones[i] = [x<sub>i</sub>, y<sub>i</sub>]</code> 表示第 <code>i</code> 块石头的位置，返回 <strong>可以移除的石子</strong> 的最大数量。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>stones = [[0,0],[0,1],[1,0],[1,2],[2,1],[2,2]]
<strong>输出：</strong>5
<strong>解释：</strong>一种移除 5 块石头的方法如下所示：
1. 移除石头 [2,2] ，因为它和 [2,1] 同行。
2. 移除石头 [2,1] ，因为它和 [0,1] 同列。
3. 移除石头 [1,2] ，因为它和 [1,0] 同行。
4. 移除石头 [1,0] ，因为它和 [0,0] 同列。
5. 移除石头 [0,1] ，因为它和 [0,0] 同行。
石头 [0,0] 不能移除，因为它没有与另一块石头同行/列。</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>stones = [[0,0],[0,2],[1,1],[2,0],[2,2]]
<strong>输出：</strong>3
<strong>解释：</strong>一种移除 3 块石头的方法如下所示：
1. 移除石头 [2,2] ，因为它和 [2,0] 同行。
2. 移除石头 [2,0] ，因为它和 [0,0] 同列。
3. 移除石头 [0,2] ，因为它和 [0,0] 同行。
石头 [0,0] 和 [1,1] 不能移除，因为它们没有与另一块石头同行/列。</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>stones = [[0,0]]
<strong>输出：</strong>0
<strong>解释：</strong>[0,0] 是平面上唯一一块石头，所以不可以移除它。</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= stones.length <= 1000</code></li>
	<li><code>0 <= x<sub>i</sub>, y<sub>i</sub> <= 10<sup>4</sup></code></li>
	<li>不会有两块石头放在同一个坐标点上</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

并查集。对于本题，`可以移除的石头数量 = 石头总数 - 连通分量个数`。遍历 `stones` 数组，将每个 `stone` 的横、纵坐标进行合并。需要注意的是，横、纵坐标数值可能相同，我们需要处理成不同的值，结合横、纵坐标的范围，我们只需要在其中一个坐标值加上 10010 即可。

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
    def removeStones(self, stones: List[List[int]]) -> int:
        def find(x):
            if p[x] != x:
                p[x] = find(p[x])
            return p[x]

        n = 10010
        p = list(range(n << 1))
        for x, y in stones:
            p[find(x)] = find(y + n)

        s = {find(x) for x, _ in stones}
        return len(stones) - len(s)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    private int[] p;

    public int removeStones(int[][] stones) {
        int n = 10010;
        p = new int[n << 1];
        for (int i = 0; i < p.length; ++i) {
            p[i] = i;
        }
        for (int[] stone : stones) {
            p[find(stone[0])] = find(stone[1] + n);
        }
        Set<Integer> s = new HashSet<>();
        for (int[] stone : stones) {
            s.add(find(stone[0]));
        }
        return stones.length - s.size();
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

    int removeStones(vector<vector<int>>& stones) {
        int n = 10010;
        p.resize(n << 1);
        for (int i = 0; i < p.size(); ++i) p[i] = i;
        for (auto& stone : stones) p[find(stone[0])] = find(stone[1] + n);
        unordered_set<int> s;
        for (auto& stone : stones) s.insert(find(stone[0]));
        return stones.size() - s.size();
    }

    int find(int x) {
        if (p[x] != x) p[x] = find(p[x]);
        return p[x];
    }
};
```

### **Go**

```go
func removeStones(stones [][]int) int {
	n := 10010
	p := make([]int, n<<1)
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
	for _, stone := range stones {
		p[find(stone[0])] = find(stone[1] + n)
	}
	s := make(map[int]bool)
	for _, stone := range stones {
		s[find(stone[0])] = true
	}
	return len(stones) - len(s)
}
```

### **...**

```

```

<!-- tabs:end -->
