# [765. 情侣牵手](https://leetcode-cn.com/problems/couples-holding-hands)

[English Version](/solution/0700-0799/0765.Couples%20Holding%20Hands/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>N 对情侣坐在连续排列的 2N 个座位上，想要牵到对方的手。 计算最少交换座位的次数，以便每对情侣可以并肩坐在一起。 <em>一</em>次交换可选择任意两人，让他们站起来交换座位。</p>

<p>人和座位用&nbsp;<code>0</code>&nbsp;到&nbsp;<code>2N-1</code>&nbsp;的整数表示，情侣们按顺序编号，第一对是&nbsp;<code>(0, 1)</code>，第二对是&nbsp;<code>(2, 3)</code>，以此类推，最后一对是&nbsp;<code>(2N-2, 2N-1)</code>。</p>

<p>这些情侣的初始座位&nbsp;&nbsp;<code>row[i]</code>&nbsp;是由最初始坐在第 i 个座位上的人决定的。</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入:</strong> row = [0, 2, 1, 3]
<strong>输出:</strong> 1
<strong>解释:</strong> 我们只需要交换row[1]和row[2]的位置即可。
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入:</strong> row = [3, 2, 0, 1]
<strong>输出:</strong> 0
<strong>解释:</strong> 无需交换座位，所有的情侣都已经可以手牵手了。
</pre>

<p><strong>说明:</strong></p>

<ol>
	<li><code>len(row)</code> 是偶数且数值在&nbsp;<code>[4, 60]</code>范围内。</li>
	<li>可以保证<code>row</code> 是序列&nbsp;<code>0...len(row)-1</code>&nbsp;的一个全排列。</li>
</ol>

## 解法

<!-- 这里可写通用的实现逻辑 -->

坐错位置的情况与最少需要交换次数：

- 1 对情侣、2 个座位，不需要交换。
- 2 对情侣、4 个座位，交换 1 次。
- 3 对情侣、6 个座位。首先交换 1 次使得其中 1 对情侣坐在一起，剩下 2 对情侣、4 个座位。即需要交换 2 次。

以此类推，得到 `f(n)=n-1`。即：n 对情侣相互坐错位置，最少需要交换 `n-1` 次。

把相互坐错位置的情侣放在一组（同个集合），组内有 n 对情侣就需要 `n-1` 次交换。将 n 对情侣分为 K 组：N1,N2...Nk，有 N1+N2+...+Nk=n。需要交换的次数分别为：N1-1、N2-1、...、Nk-1，则总的最少交换次数为 N1-1+N2-1+...+Nk-1=N1+N2+...+Nk-k=n-k。问题转换为：n 对情侣，根据相互坐错位置的条件分组，共有多少个分组。并查集实现。

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
    def minSwapsCouples(self, row: List[int]) -> int:
        n = len(row) >> 1
        p = list(range(n))

        def find(x):
            if p[x] != x:
                p[x] = find(p[x])
            return p[x]

        for i in range(0, len(row), 2):
            a, b = row[i] >> 1, row[i + 1] >> 1
            p[find(a)] = find(b)

        cnt = 0
        for i in range(n):
            if i == find(i):
                cnt += 1
        return n - cnt
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    private int[] p;

    public int minSwapsCouples(int[] row) {
        int n = row.length >> 1;
        p = new int[n];
        for (int i = 0; i < n; ++i) {
            p[i] = i;
        }
        for (int i = 0; i < row.length; i += 2) {
            int a = row[i] >> 1, b = row[i + 1] >> 1;
            p[find(a)] = find(b);
        }
        int cnt = 0;
        for (int i = 0; i < n; ++i) {
            if (i == find(i)) {
                ++cnt;
            }
        }
        return n - cnt;
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

    int minSwapsCouples(vector<int> &row) {
        int n = row.size() >> 1;
        p.resize(n);
        for (int i = 0; i < n; ++i)
        {
            p[i] = i;
        }
        for (int i = 0; i < row.size(); i += 2)
        {
            int a = row[i] >> 1, b = row[i + 1] >> 1;
            p[find(a)] = find(b);
        }
        int cnt = 0;
        for (int i = 0; i < n; ++i)
        {
            if (i == find(i))
                ++cnt;
        }
        return n - cnt;
    }

    int find(int x) {
        if (p[x] != x)
        {
            p[x] = find(p[x]);
        }
        return p[x];
    }
};
```

### **Go**

```go
var p []int

func minSwapsCouples(row []int) int {
	n := len(row) >> 1
	p = make([]int, n)
	for i := 0; i < n; i++ {
		p[i] = i
	}
	for i := 0; i < len(row); i += 2 {
		a, b := row[i]>>1, row[i+1]>>1
		p[find(a)] = find(b)
	}
	cnt := 0
	for i := 0; i < n; i++ {
		if i == find(i) {
			cnt++
		}
	}
	return n - cnt
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
