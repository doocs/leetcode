# [1361. 验证二叉树](https://leetcode.cn/problems/validate-binary-tree-nodes)

[English Version](/solution/1300-1399/1361.Validate%20Binary%20Tree%20Nodes/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>二叉树上有 <code>n</code>&nbsp;个节点，按从&nbsp;<code>0</code>&nbsp;到 <code>n - 1</code>&nbsp;编号，其中节点&nbsp;<code>i</code>&nbsp;的两个子节点分别是&nbsp;<code>leftChild[i]</code>&nbsp;和&nbsp;<code>rightChild[i]</code>。</p>

<p>只有 <strong>所有</strong> 节点能够形成且 <strong>只</strong> 形成 <strong>一颗</strong>&nbsp;有效的二叉树时，返回&nbsp;<code>true</code>；否则返回 <code>false</code>。</p>

<p>如果节点&nbsp;<code>i</code>&nbsp;没有左子节点，那么&nbsp;<code>leftChild[i]</code>&nbsp;就等于&nbsp;<code>-1</code>。右子节点也符合该规则。</p>

<p>注意：节点没有值，本问题中仅仅使用节点编号。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1300-1399/1361.Validate%20Binary%20Tree%20Nodes/images/1503_ex1.png" style="height: 287px; width: 195px;"></strong></p>

<pre><strong>输入：</strong>n = 4, leftChild = [1,-1,3,-1], rightChild = [2,-1,-1,-1]
<strong>输出：</strong>true
</pre>

<p><strong>示例 2：</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1300-1399/1361.Validate%20Binary%20Tree%20Nodes/images/1503_ex2.png" style="height: 272px; width: 183px;"></strong></p>

<pre><strong>输入：</strong>n = 4, leftChild = [1,-1,3,-1], rightChild = [2,3,-1,-1]
<strong>输出：</strong>false
</pre>

<p><strong>示例 3：</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1300-1399/1361.Validate%20Binary%20Tree%20Nodes/images/1503_ex3.png" style="height: 174px; width: 82px;"></strong></p>

<pre><strong>输入：</strong>n = 2, leftChild = [1,0], rightChild = [-1,-1]
<strong>输出：</strong>false
</pre>

<p><strong>示例 4：</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1300-1399/1361.Validate%20Binary%20Tree%20Nodes/images/1503_ex4.png" style="height: 191px; width: 470px;"></strong></p>

<pre><strong>输入：</strong>n = 6, leftChild = [1,-1,-1,4,-1,-1], rightChild = [2,-1,-1,5,-1,-1]
<strong>输出：</strong>false
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10^4</code></li>
	<li><code>leftChild.length == rightChild.length == n</code></li>
	<li><code>-1 &lt;= leftChild[i], rightChild[i] &lt;= n - 1</code></li>
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

对于本题，遍历每个节点 i 以及对应的左右孩子 l, r，用 vis 数组记录节点是否有父节点：

-   若孩子节点已存在父节点，说明有多个父亲，不满足条件，直接返回 false。
-   若孩子节点与父节点已经处于同一个连通分量，说明会形成环，不满足条件，直接返回 false。
-   否则，进行合并，并将节点 i 分别与 l, r 进行合并。

遍历结束，判断连通分量个数是否为 1，若是返回 true，否则返回 false。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def validateBinaryTreeNodes(self, n: int, leftChild: List[int], rightChild: List[int]) -> bool:
        p = list(range(n))
        vis = [False] * n

        def find(x):
            if p[x] != x:
                p[x] = find(p[x])
            return p[x]

        for i in range(n):
            l, r = leftChild[i], rightChild[i]
            if l != -1:
                if vis[l] or find(i) == find(l):
                    return False
                p[find(i)] = find(l)
                vis[l] = True
                n -= 1
            if r != -1:
                if vis[r] or find(i) == find(r):
                    return False
                p[find(i)] = find(r)
                vis[r] = True
                n -= 1
        return n == 1
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    private int[] p;

    public boolean validateBinaryTreeNodes(int n, int[] leftChild, int[] rightChild) {
        p = new int[n];
        boolean[] vis = new boolean[n];
        for (int i = 0; i < n; ++i) {
            p[i] = i;
        }
        for (int i = 0, t = n; i < t; ++i) {
            int l = leftChild[i], r = rightChild[i];
            if (l != -1) {
                if (vis[l] || find(i) == find(l)) {
                    return false;
                }
                vis[l] = true;
                p[find(i)] = find(l);
                --n;
            }
            if (r != -1) {
                if (vis[r] || find(i) == find(r)) {
                    return false;
                }
                vis[r] = true;
                p[find(i)] = find(r);
                --n;
            }
        }
        return n == 1;
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

    bool validateBinaryTreeNodes(int n, vector<int>& leftChild, vector<int>& rightChild) {
        p.resize(n);
        for (int i = 0; i < n; ++i) p[i] = i;
        vector<bool> vis(n, false);
        for (int i = 0, t = n; i < t; ++i) {
            int l = leftChild[i], r = rightChild[i];
            if (l != -1) {
                if (vis[l] || find(i) == find(l)) return false;
                vis[l] = true;
                p[find(i)] = find(l);
                --n;
            }
            if (r != -1) {
                if (vis[r] || find(i) == find(r)) return false;
                vis[r] = true;
                p[find(i)] = find(r);
                --n;
            }
        }
        return n == 1;
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

func validateBinaryTreeNodes(n int, leftChild []int, rightChild []int) bool {
	p = make([]int, n)
	for i := 0; i < n; i++ {
		p[i] = i
	}
	vis := make([]bool, n)
	for i, t := 0, n; i < t; i++ {
		l, r := leftChild[i], rightChild[i]
		if l != -1 {
			if vis[l] || find(i) == find(l) {
				return false
			}
			vis[l] = true
			p[find(i)] = find(l)
			n--
		}
		if r != -1 {
			if vis[r] || find(i) == find(r) {
				return false
			}
			vis[r] = true
			p[find(i)] = find(r)
			n--
		}
	}
	return n == 1
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
