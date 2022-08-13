# [850. Rectangle Area II](https://leetcode.com/problems/rectangle-area-ii)

[中文文档](/solution/0800-0899/0850.Rectangle%20Area%20II/README.md)

## Description

<p>You are given a 2D array of axis-aligned <code>rectangles</code>. Each <code>rectangle[i] = [x<sub>i1</sub>, y<sub>i1</sub>, x<sub>i2</sub>, y<sub>i2</sub>]</code> denotes the <code>i<sup>th</sup></code> rectangle where <code>(x<sub>i1</sub>, y<sub>i1</sub>)</code> are the coordinates of the <strong>bottom-left corner</strong>, and <code>(x<sub>i2</sub>, y<sub>i2</sub>)</code> are the coordinates of the <strong>top-right corner</strong>.</p>

<p>Calculate the <strong>total area</strong> covered by all <code>rectangles</code> in the plane. Any area covered by two or more rectangles should only be counted <strong>once</strong>.</p>

<p>Return <em>the <strong>total area</strong></em>. Since the answer may be too large, return it <strong>modulo</strong> <code>10<sup>9</sup> + 7</code>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0800-0899/0850.Rectangle%20Area%20II/images/rectangle_area_ii_pic.png" style="width: 600px; height: 450px;" />
<pre>
<strong>Input:</strong> rectangles = [[0,0,2,2],[1,0,2,3],[1,0,3,1]]
<strong>Output:</strong> 6
<strong>Explanation:</strong> A total area of 6 is covered by all three rectangles, as illustrated in the picture.
From (1,1) to (2,2), the green and red rectangles overlap.
From (1,0) to (2,3), all three rectangles overlap.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> rectangles = [[0,0,1000000000,1000000000]]
<strong>Output:</strong> 49
<strong>Explanation:</strong> The answer is 10<sup>18</sup> modulo (10<sup>9</sup> + 7), which is 49.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= rectangles.length &lt;= 200</code></li>
	<li><code>rectanges[i].length == 4</code></li>
	<li><code>0 &lt;= x<sub>i1</sub>, y<sub>i1</sub>, x<sub>i2</sub>, y<sub>i2</sub> &lt;= 10<sup>9</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

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
