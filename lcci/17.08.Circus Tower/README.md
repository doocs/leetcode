# [面试题 17.08. 马戏团人塔](https://leetcode.cn/problems/circus-tower-lcci)

[English Version](/lcci/17.08.Circus%20Tower/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>有个马戏团正在设计叠罗汉的表演节目，一个人要站在另一人的肩膀上。出于实际和美观的考虑，在上面的人要比下面的人矮一点且轻一点。已知马戏团每个人的身高和体重，请编写代码计算叠罗汉最多能叠几个人。</p>
<p><strong>示例：</strong></p>
<pre><strong>输入：</strong>height = [65,70,56,75,60,68] weight = [100,150,90,190,95,110]
<strong>输出：</strong>6
<strong>解释：</strong>从上往下数，叠罗汉最多能叠 6 层：(56,90), (60,95), (65,100), (68,110), (70,150), (75,190)</pre>
<p>提示：</p>
<ul>
	<li><code>height.length == weight.length &lt;= 10000</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：排序 + 离散化 + 树状数组**

我们现将所有人按照身高从小到大排序，若身高相同，则按照体重从大到小排序。这样我们可以将问题转换为求体重数组的最长递增子序列的问题。

最长递增子序列的问题可以使用动态规划求解，时间复杂度 $O(n^2)$。但是我们可以使用树状数组来优化求解过程，时间复杂度 $O(n \log n)$。

空间复杂度 $O(n)$。其中 $n$ 为人数。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class BinaryIndexedTree:
    def __init__(self, n):
        self.n = n
        self.c = [0] * (n + 1)

    def update(self, x, delta):
        while x <= self.n:
            self.c[x] = max(self.c[x], delta)
            x += x & -x

    def query(self, x):
        s = 0
        while x:
            s = max(s, self.c[x])
            x -= x & -x
        return s


class Solution:
    def bestSeqAtIndex(self, height: List[int], weight: List[int]) -> int:
        arr = list(zip(height, weight))
        arr.sort(key=lambda x: (x[0], -x[1]))
        alls = sorted({w for _, w in arr})
        m = {w: i for i, w in enumerate(alls, 1)}
        tree = BinaryIndexedTree(len(m))
        ans = 1
        for _, w in arr:
            x = m[w]
            t = tree.query(x - 1) + 1
            ans = max(ans, t)
            tree.update(x, t)
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class BinaryIndexedTree {
    private int n;
    private int[] c;

    public BinaryIndexedTree(int n) {
        this.n = n;
        c = new int[n + 1];
    }

    public void update(int x, int val) {
        while (x <= n) {
            this.c[x] = Math.max(this.c[x], val);
            x += x & -x;
        }
    }

    public int query(int x) {
        int s = 0;
        while (x > 0) {
            s = Math.max(s, this.c[x]);
            x -= x & -x;
        }
        return s;
    }
}

class Solution {
    public int bestSeqAtIndex(int[] height, int[] weight) {
        int n = height.length;
        int[][] arr = new int[n][2];
        for (int i = 0; i < n; ++i) {
            arr[i] = new int[] {height[i], weight[i]};
        }
        Arrays.sort(arr, (a, b) -> a[0] == b[0] ? b[1] - a[1] : a[0] - b[0]);
        Set<Integer> s = new HashSet<>();
        for (int[] e : arr) {
            s.add(e[1]);
        }
        List<Integer> alls = new ArrayList<>(s);
        Collections.sort(alls);
        Map<Integer, Integer> m = new HashMap<>(alls.size());
        for (int i = 0; i < alls.size(); ++i) {
            m.put(alls.get(i), i + 1);
        }
        BinaryIndexedTree tree = new BinaryIndexedTree(alls.size());
        int ans = 1;
        for (int[] e : arr) {
            int x = m.get(e[1]);
            int t = tree.query(x - 1) + 1;
            ans = Math.max(ans, t);
            tree.update(x, t);
        }
        return ans;
    }
}
```

### **C++**

```cpp
class BinaryIndexedTree {
public:
    BinaryIndexedTree(int _n)
        : n(_n)
        , c(_n + 1) {}

    void update(int x, int val) {
        while (x <= n) {
            c[x] = max(c[x], val);
            x += x & -x;
        }
    }

    int query(int x) {
        int s = 0;
        while (x > 0) {
            s = max(s, c[x]);
            x -= x & -x;
        }
        return s;
    }

private:
    int n;
    vector<int> c;
};

class Solution {
public:
    int bestSeqAtIndex(vector<int>& height, vector<int>& weight) {
        int n = height.size();
        vector<pair<int, int>> people;
        for (int i = 0; i < n; ++i) {
            people.emplace_back(height[i], weight[i]);
        }
        sort(people.begin(), people.end(), [](const pair<int, int>& a, const pair<int, int>& b) {
            if (a.first == b.first) {
                return a.second > b.second;
            }
            return a.first < b.first;
        });
        vector<int> alls = weight;
        sort(alls.begin(), alls.end());
        alls.erase(unique(alls.begin(), alls.end()), alls.end());
        BinaryIndexedTree tree(alls.size());
        int ans = 1;
        for (auto& [_, w] : people) {
            int x = lower_bound(alls.begin(), alls.end(), w) - alls.begin() + 1;
            int t = tree.query(x - 1) + 1;
            ans = max(ans, t);
            tree.update(x, t);
        }
        return ans;
    }
};
```

### **Go**

```go
type BinaryIndexedTree struct {
	n int
	c []int
}

func newBinaryIndexedTree(n int) *BinaryIndexedTree {
	c := make([]int, n+1)
	return &BinaryIndexedTree{n, c}
}

func (this *BinaryIndexedTree) update(x, val int) {
	for x <= this.n {
		if this.c[x] < val {
			this.c[x] = val
		}
		x += x & -x
	}
}

func (this *BinaryIndexedTree) query(x int) int {
	s := 0
	for x > 0 {
		if s < this.c[x] {
			s = this.c[x]
		}
		x -= x & -x
	}
	return s
}

func bestSeqAtIndex(height []int, weight []int) int {
	n := len(height)
	people := make([][2]int, n)
	s := map[int]bool{}
	for i := range people {
		people[i] = [2]int{height[i], weight[i]}
		s[weight[i]] = true
	}
	sort.Slice(people, func(i, j int) bool {
		a, b := people[i], people[j]
		return a[0] < b[0] || a[0] == b[0] && a[1] > b[1]
	})
	alls := make([]int, 0, len(s))
	for k := range s {
		alls = append(alls, k)
	}
	sort.Ints(alls)
	tree := newBinaryIndexedTree(len(alls))
	ans := 1
	for _, p := range people {
		x := sort.SearchInts(alls, p[1]) + 1
		t := tree.query(x-1) + 1
		ans = max(ans, t)
		tree.update(x, t)
	}
	return ans
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
```

### **...**

```

```

<!-- tabs:end -->
