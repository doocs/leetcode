# [850. 矩形面积 II](https://leetcode.cn/problems/rectangle-area-ii)

[English Version](/solution/0800-0899/0850.Rectangle%20Area%20II/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>我们给出了一个（轴对齐的）二维矩形列表&nbsp;<code>rectangles</code>&nbsp;。 对于&nbsp;<code>rectangle[i] = [x1, y1, x2, y2]</code>，其中（x1，y1）是矩形&nbsp;<code>i</code>&nbsp;左下角的坐标，<meta charset="UTF-8" />&nbsp;<code>(x<sub>i1</sub>, y<sub>i1</sub>)</code>&nbsp;是该矩形 <strong>左下角</strong> 的坐标，<meta charset="UTF-8" />&nbsp;<code>(x<sub>i2</sub>, y<sub>i2</sub>)</code>&nbsp;是该矩形&nbsp;<strong>右上角</strong> 的坐标。</p>

<p>计算平面中所有&nbsp;<code>rectangles</code>&nbsp;所覆盖的 <strong>总面积 </strong>。任何被两个或多个矩形覆盖的区域应只计算 <strong>一次</strong> 。</p>

<p>返回<em> <strong>总面积</strong> </em>。因为答案可能太大，返回<meta charset="UTF-8" />&nbsp;<code>10<sup>9</sup>&nbsp;+ 7</code> 的&nbsp;<strong>模</strong>&nbsp;。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0800-0899/0850.Rectangle%20Area%20II/images/rectangle_area_ii_pic.png" style="height: 300px; width: 400px;" /></p>

<pre>
<strong>输入：</strong>rectangles = [[0,0,2,2],[1,0,2,3],[1,0,3,1]]
<strong>输出：</strong>6
<strong>解释：</strong>如图所示，三个矩形覆盖了总面积为6的区域。
从(1,1)到(2,2)，绿色矩形和红色矩形重叠。
从(1,0)到(2,3)，三个矩形都重叠。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>rectangles = [[0,0,1000000000,1000000000]]
<strong>输出：</strong>49
<strong>解释：</strong>答案是 10<sup>18</sup> 对 (10<sup>9</sup> + 7) 取模的结果， 即 49 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= rectangles.length &lt;= 200</code></li>
	<li><code>rectanges[i].length = 4</code><meta charset="UTF-8" /></li>
	<li><code>0 &lt;= x<sub>i1</sub>, y<sub>i1</sub>, x<sub>i2</sub>, y<sub>i2</sub>&nbsp;&lt;= 10<sup>9</sup></code></li>
	<li>矩形叠加覆盖后的总面积不会超越&nbsp;<code>2^63 - 1</code>&nbsp;，这意味着可以用一个&nbsp;64 位有符号整数来保存面积结果。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：离散化 + 线段树 + 扫描线**

线段树将整个区间分割为多个不连续的子区间，子区间的数量不超过 `log(width)`。更新某个元素的值，只需要更新 `log(width)` 个区间，并且这些区间都包含在一个包含该元素的大区间内。区间修改时，需要使用**懒标记**保证效率。

-   线段树的每个节点代表一个区间；
-   线段树具有唯一的根节点，代表的区间是整个统计范围，如 `[1, N]`；
-   线段树的每个叶子节点代表一个长度为 1 的元区间 `[x, x]`；
-   对于每个内部节点 `[l, r]`，它的左儿子是 `[l, mid]`，右儿子是 `[mid + 1, r]`, 其中 `mid = ⌊(l + r) / 2⌋` (即向下取整)。

对于本题，线段树节点维护的信息有：

1. 区间被覆盖的次数 cnt；
1. 区间被覆盖的长度 len。

另外，由于本题利用了扫描线本身的特性，因此，区间修改时，不需要懒标记，也无须进行 pushdown 操作。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Node:
    def __init__(self):
        self.l = 0
        self.r = 0
        self.cnt = 0
        self.length = 0


class SegmentTree:
    def __init__(self, nums):
        n = len(nums) - 1
        self.nums = nums
        self.tr = [Node() for _ in range(n << 2)]
        self.build(1, 0, n - 1)

    def build(self, u, l, r):
        self.tr[u].l, self.tr[u].r = l, r
        if l != r:
            mid = (l + r) >> 1
            self.build(u << 1, l, mid)
            self.build(u << 1 | 1, mid + 1, r)

    def modify(self, u, l, r, k):
        if self.tr[u].l >= l and self.tr[u].r <= r:
            self.tr[u].cnt += k
        else:
            mid = (self.tr[u].l + self.tr[u].r) >> 1
            if l <= mid:
                self.modify(u << 1, l, r, k)
            if r > mid:
                self.modify(u << 1 | 1, l, r, k)
        self.pushup(u)

    def pushup(self, u):
        if self.tr[u].cnt:
            self.tr[u].length = self.nums[self.tr[u].r + 1] - self.nums[self.tr[u].l]
        elif self.tr[u].l == self.tr[u].r:
            self.tr[u].length = 0
        else:
            self.tr[u].length = self.tr[u << 1].length + self.tr[u << 1 | 1].length

    @property
    def length(self):
        return self.tr[1].length


class Solution:
    def rectangleArea(self, rectangles: List[List[int]]) -> int:
        segs = []
        alls = set()
        for x1, y1, x2, y2 in rectangles:
            segs.append((x1, y1, y2, 1))
            segs.append((x2, y1, y2, -1))
            alls.add(y1)
            alls.add(y2)
        alls = sorted(alls)
        m = {v: i for i, v in enumerate(alls)}
        tree = SegmentTree(alls)
        segs.sort()
        ans = 0
        for i, (x, y1, y2, k) in enumerate(segs):
            if i > 0:
                ans += tree.length * (x - segs[i - 1][0])
            tree.modify(1, m[y1], m[y2] - 1, k)
        ans %= int(1e9 + 7)
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Node {
    int l;
    int r;
    int cnt;
    int len;
}

class SegmentTree {
    private Node[] tr;
    private int[] nums;

    public SegmentTree(int[] nums) {
        int n = nums.length - 1;
        this.nums = nums;
        tr = new Node[n << 2];
        for (int i = 0; i < tr.length; ++i) {
            tr[i] = new Node();
        }
        build(1, 0, n - 1);
    }

    public void build(int u, int l, int r) {
        tr[u].l = l;
        tr[u].r = r;
        if (l != r) {
            int mid = (l + r) >> 1;
            build(u << 1, l, mid);
            build(u << 1 | 1, mid + 1, r);
        }
    }

    public void modify(int u, int l, int r, int k) {
        if (tr[u].l >= l && tr[u].r <= r) {
            tr[u].cnt += k;
        } else {
            int mid = (tr[u].l + tr[u].r) >> 1;
            if (l <= mid) {
                modify(u << 1, l, r, k);
            }
            if (r > mid) {
                modify(u << 1 | 1, l, r, k);
            }
        }
        pushup(u);
    }

    public int query() {
        return tr[1].len;
    }

    public void pushup(int u) {
        if (tr[u].cnt > 0) {
            tr[u].len = nums[tr[u].r + 1] - nums[tr[u].l];
        } else if (tr[u].l == tr[u].r) {
            tr[u].len = 0;
        } else {
            tr[u].len = tr[u << 1].len + tr[u << 1 | 1].len;
        }
    }
}

class Solution {
    private static final int MOD = (int) 1e9 + 7;

    public int rectangleArea(int[][] rectangles) {
        int n = rectangles.length;
        int[][] segs = new int[n << 1][4];
        int idx = 0;
        TreeSet<Integer> ts = new TreeSet<>();
        for (int[] rect : rectangles) {
            int x1 = rect[0], y1 = rect[1], x2 = rect[2], y2 = rect[3];
            segs[idx++] = new int[]{x1, y1, y2, 1};
            segs[idx++] = new int[]{x2, y1, y2, -1};
            ts.add(y1);
            ts.add(y2);
        }
        Map<Integer, Integer> m = new HashMap<>();
        int[] nums = new int[ts.size()];
        idx = 0;
        for (int v : ts) {
            nums[idx] = v;
            m.put(v, idx++);
        }
        Arrays.sort(segs, Comparator.comparingInt(a -> a[0]));
        SegmentTree tree = new SegmentTree(nums);
        long ans = 0;
        for (int i = 0; i < segs.length; ++i) {
            int x = segs[i][0], y1 = segs[i][1], y2 = segs[i][2], k = segs[i][3];
            if (i > 0) {
                ans += (long) tree.query() * (x - segs[i - 1][0]);
            }
            tree.modify(1, m.get(y1), m.get(y2) - 1, k);
        }
        ans %= MOD;
        return (int) ans;
    }
}
```

### **C++**

```cpp
class Node {
public:
    int l, r, cnt, len;
};

class SegmentTree {
private:
    vector<Node*> tr;
    vector<int> nums;

public:
    SegmentTree(vector<int>& nums) {
        int n = nums.size() - 1;
        this->nums = nums;
        tr.resize(n << 2);
        for (int i = 0; i < tr.size(); ++i) tr[i] = new Node();
        build(1, 0, n - 1);
    }

    void build(int u, int l, int r) {
        tr[u]->l = l;
        tr[u]->r = r;
        if (l != r) {
            int mid = (l + r) >> 1;
            build(u << 1, l, mid);
            build(u << 1 | 1, mid + 1, r);
        }
    }

    void modify(int u, int l, int r, int k) {
        if (tr[u]->l >= l && tr[u]->r <= r)
            tr[u]->cnt += k;
        else {
            int mid = (tr[u]->l + tr[u]->r) >> 1;
            if (l <= mid) modify(u << 1, l, r, k);
            if (r > mid) modify(u << 1 | 1, l, r, k);
        }
        pushup(u);
    }

    int query() {
        return tr[1]->len;
    }

    void pushup(int u) {
        if (tr[u]->cnt)
            tr[u]->len = nums[tr[u]->r + 1] - nums[tr[u]->l];
        else if (tr[u]->l == tr[u]->r)
            tr[u]->len = 0;
        else
            tr[u]->len = tr[u << 1]->len + tr[u << 1 | 1]->len;
    }
};

class Solution {
public:
    int rectangleArea(vector<vector<int>>& rectangles) {
        int n = rectangles.size();
        vector<vector<int>> segs;
        set<int> ts;
        int mod = 1e9 + 7;
        for (auto& rect : rectangles) {
            int x1 = rect[0], y1 = rect[1], x2 = rect[2], y2 = rect[3];
            segs.push_back({x1, y1, y2, 1});
            segs.push_back({x2, y1, y2, -1});
            ts.insert(y1);
            ts.insert(y2);
        }
        unordered_map<int, int> m;
        int idx = 0;
        for (int v : ts) m[v] = idx++;
        sort(segs.begin(), segs.end());
        vector<int> nums(ts.begin(), ts.end());
        SegmentTree* tree = new SegmentTree(nums);
        long long ans = 0;
        for (int i = 0; i < segs.size(); ++i) {
            int x = segs[i][0], y1 = segs[i][1], y2 = segs[i][2], k = segs[i][3];
            if (i > 0) ans += (long long)tree->query() * (x - segs[i - 1][0]);
            tree->modify(1, m[y1], m[y2] - 1, k);
        }
        ans %= mod;
        return (int)ans;
    }
};
```

### **...**

```

```

<!-- tabs:end -->
