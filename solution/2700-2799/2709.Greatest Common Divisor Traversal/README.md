# [2709. 最大公约数遍历](https://leetcode.cn/problems/greatest-common-divisor-traversal)

[English Version](/solution/2700-2799/2709.Greatest%20Common%20Divisor%20Traversal/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个下标从 <strong>0</strong>&nbsp;开始的整数数组&nbsp;<code>nums</code>&nbsp;，你可以在一些下标之间遍历。对于两个下标&nbsp;<code>i</code>&nbsp;和&nbsp;<code>j</code>（<code>i != j</code>），当且仅当&nbsp;<code>gcd(nums[i], nums[j]) &gt; 1</code>&nbsp;时，我们可以在两个下标之间通行，其中&nbsp;<code>gcd</code>&nbsp;是两个数的 <strong>最大公约数</strong>&nbsp;。</p>

<p>你需要判断 <code>nums</code>&nbsp;数组中&nbsp;<strong>任意&nbsp;</strong>两个满足 <code>i &lt; j</code>&nbsp;的下标&nbsp;<code>i</code>&nbsp;和&nbsp;<code>j</code> ，是否存在若干次通行可以从 <code>i</code>&nbsp;遍历到 <code>j</code>&nbsp;。</p>

<p>如果任意满足条件的下标对都可以遍历，那么返回 <code>true</code>&nbsp;，否则返回 <code>false</code>&nbsp;。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<b>输入：</b>nums = [2,3,6]
<b>输出：</b>true
<b>解释：</b>这个例子中，总共有 3 个下标对：(0, 1) ，(0, 2) 和 (1, 2) 。
从下标 0 到下标 1 ，我们可以遍历 0 -&gt; 2 -&gt; 1 ，我们可以从下标 0 到 2 是因为 gcd(nums[0], nums[2]) = gcd(2, 6) = 2 &gt; 1 ，从下标 2 到 1 是因为 gcd(nums[2], nums[1]) = gcd(6, 3) = 3 &gt; 1 。
从下标 0 到下标 2 ，我们可以直接遍历，因为 gcd(nums[0], nums[2]) = gcd(2, 6) = 2 &gt; 1 。同理，我们也可以从下标 1 到 2 因为 gcd(nums[1], nums[2]) = gcd(3, 6) = 3 &gt; 1 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<b>输入：</b>nums = [3,9,5]
<b>输出：</b>false
<b>解释：</b>我们没法从下标 0 到 2 ，所以返回 false 。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<b>输入：</b>nums = [4,3,12,8]
<b>输出：</b>true
<b>解释：</b>总共有 6 个下标对：(0, 1) ，(0, 2) ，(0, 3) ，(1, 2) ，(1, 3) 和 (2, 3) 。所有下标对之间都存在可行的遍历，所以返回 true 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class UnionFind:
    def __init__(self, n):
        self.p = list(range(n))
        self.size = [1] * n

    def find(self, x):
        if self.p[x] != x:
            self.p[x] = self.find(self.p[x])
        return self.p[x]

    def union(self, a, b):
        pa, pb = self.find(a), self.find(b)
        if pa == pb:
            return False
        if self.size[pa] > self.size[pb]:
            self.p[pb] = pa
            self.size[pa] += self.size[pb]
        else:
            self.p[pa] = pb
            self.size[pb] += self.size[pa]
        return True


mx = 100010
p = defaultdict(list)
for x in range(1, mx + 1):
    v = x
    i = 2
    while i <= v // i:
        if v % i == 0:
            p[x].append(i)
            while v % i == 0:
                v //= i
        i += 1
    if v > 1:
        p[x].append(v)


class Solution:
    def canTraverseAllPairs(self, nums: List[int]) -> bool:
        n = len(nums)
        m = max(nums)
        uf = UnionFind(n + m + 1)
        for i, x in enumerate(nums):
            for j in p[x]:
                uf.union(i, j + n)
        return len(set(uf.find(i) for i in range(n))) == 1
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class UnionFind {
    private int[] p;
    private int[] size;

    public UnionFind(int n) {
        p = new int[n];
        size = new int[n];
        for (int i = 0; i < n; ++i) {
            p[i] = i;
            size[i] = 1;
        }
    }

    public int find(int x) {
        if (p[x] != x) {
            p[x] = find(p[x]);
        }
        return p[x];
    }

    public boolean union(int a, int b) {
        int pa = find(a), pb = find(b);
        if (pa == pb) {
            return false;
        }
        if (size[pa] > size[pb]) {
            p[pb] = pa;
            size[pa] += size[pb];
        } else {
            p[pa] = pb;
            size[pb] += size[pa];
        }
        return true;
    }
}

class Solution {
    private static final int MX = 100010;
    private static final List<Integer>[] P = new List[MX];

    static {
        Arrays.setAll(P, k -> new ArrayList<>());
        for (int x = 1; x < MX; ++x) {
            int v = x;
            int i = 2;
            while (i <= v / i) {
                if (v % i == 0) {
                    P[x].add(i);
                    while (v % i == 0) {
                        v /= i;
                    }
                }
                ++i;
            }
            if (v > 1) {
                P[x].add(v);
            }
        }
    }

    public boolean canTraverseAllPairs(int[] nums) {
        int m = Arrays.stream(nums).max().getAsInt();
        int n = nums.length;
        UnionFind uf = new UnionFind(n + m + 1);
        for (int i = 0; i < n; ++i) {
            for (int j : P[nums[i]]) {
                uf.union(i, j + n);
            }
        }
        Set<Integer> s = new HashSet<>();
        for (int i = 0; i < n; ++i) {
            s.add(uf.find(i));
        }
        return s.size() == 1;
    }
}
```

### **C++**

```cpp
int MX = 100010;
vector<int> P[100010];

int init = []() {
    for (int x = 1; x < MX; ++x) {
        int v = x;
        int i = 2;
        while (i <= v / i) {
            if (v % i == 0) {
                P[x].push_back(i);
                while (v % i == 0) {
                    v /= i;
                }
            }
            ++i;
        }
        if (v > 1) {
            P[x].push_back(v);
        }
    }
    return 0;
}();

class UnionFind {
public:
    UnionFind(int n) {
        p = vector<int>(n);
        size = vector<int>(n, 1);
        iota(p.begin(), p.end(), 0);
    }

    bool unite(int a, int b) {
        int pa = find(a), pb = find(b);
        if (pa == pb) {
            return false;
        }
        if (size[pa] > size[pb]) {
            p[pb] = pa;
            size[pa] += size[pb];
        } else {
            p[pa] = pb;
            size[pb] += size[pa];
        }
        return true;
    }

    int find(int x) {
        if (p[x] != x) {
            p[x] = find(p[x]);
        }
        return p[x];
    }

private:
    vector<int> p, size;
};

class Solution {
public:
    bool canTraverseAllPairs(vector<int>& nums) {
        int m = *max_element(nums.begin(), nums.end());
        int n = nums.size();
        UnionFind uf(m + n + 1);
        for (int i = 0; i < n; ++i) {
            for (int j : P[nums[i]]) {
                uf.unite(i, j + n);
            }
        }
        unordered_set<int> s;
        for (int i = 0; i < n; ++i) {
            s.insert(uf.find(i));
        }
        return s.size() == 1;
    }
};
```

### **Go**

```go
const mx = 100010

var p = make([][]int, mx)

func init() {
	for x := 1; x < mx; x++ {
		v := x
		i := 2
		for i <= v/i {
			if v%i == 0 {
				p[x] = append(p[x], i)
				for v%i == 0 {
					v /= i
				}
			}
			i++
		}
		if v > 1 {
			p[x] = append(p[x], v)
		}
	}
}

type unionFind struct {
	p, size []int
}

func newUnionFind(n int) *unionFind {
	p := make([]int, n)
	size := make([]int, n)
	for i := range p {
		p[i] = i
		size[i] = 1
	}
	return &unionFind{p, size}
}

func (uf *unionFind) find(x int) int {
	if uf.p[x] != x {
		uf.p[x] = uf.find(uf.p[x])
	}
	return uf.p[x]
}

func (uf *unionFind) union(a, b int) bool {
	pa, pb := uf.find(a), uf.find(b)
	if pa == pb {
		return false
	}
	if uf.size[pa] > uf.size[pb] {
		uf.p[pb] = pa
		uf.size[pa] += uf.size[pb]
	} else {
		uf.p[pa] = pb
		uf.size[pb] += uf.size[pa]
	}
	return true
}

func canTraverseAllPairs(nums []int) bool {
	m := slices.Max(nums)
	n := len(nums)
	uf := newUnionFind(m + n + 1)
	for i, x := range nums {
		for _, j := range p[x] {
			uf.union(i, j+n)
		}
	}
	s := map[int]bool{}
	for i := 0; i < n; i++ {
		s[uf.find(i)] = true
	}
	return len(s) == 1
}
```

### **...**

```

```

<!-- tabs:end -->
