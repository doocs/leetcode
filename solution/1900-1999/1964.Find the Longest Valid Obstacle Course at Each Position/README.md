---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1900-1999/1964.Find%20the%20Longest%20Valid%20Obstacle%20Course%20at%20Each%20Position/README.md
rating: 1933
source: 第 253 场周赛 Q4
tags:
    - 树状数组
    - 数组
    - 二分查找
---

<!-- problem:start -->

# [1964. 找出到每个位置为止最长的有效障碍赛跑路线](https://leetcode.cn/problems/find-the-longest-valid-obstacle-course-at-each-position)

[English Version](/solution/1900-1999/1964.Find%20the%20Longest%20Valid%20Obstacle%20Course%20at%20Each%20Position/README_EN.md)

## 题目描述

<!-- description:start -->

<p>你打算构建一些障碍赛跑路线。给你一个 <strong>下标从 0 开始</strong> 的整数数组 <code>obstacles</code> ，数组长度为 <code>n</code> ，其中 <code>obstacles[i]</code> 表示第 <code>i</code> 个障碍的高度。</p>

<p>对于每个介于 <code>0</code> 和 <code>n - 1</code> 之间（包含 <code>0</code> 和 <code>n - 1</code>）的下标&nbsp; <code>i</code> ，在满足下述条件的前提下，请你找出&nbsp;<code>obstacles</code> 能构成的最长障碍路线的长度：</p>

<ul>
	<li>你可以选择下标介于 <code>0</code> 到 <code>i</code> 之间（包含 <code>0</code> 和 <code>i</code>）的任意个障碍。</li>
	<li>在这条路线中，必须包含第 <code>i</code> 个障碍。</li>
	<li>你必须按障碍在&nbsp;<code>obstacles</code>&nbsp;中的<strong> </strong><strong>出现顺序</strong> 布置这些障碍。</li>
	<li>除第一个障碍外，路线中每个障碍的高度都必须和前一个障碍 <strong>相同</strong> 或者 <strong>更高</strong> 。</li>
</ul>

<p>返回长度为 <code>n</code> 的答案数组 <code>ans</code> ，其中 <code>ans[i]</code> 是上面所述的下标 <code>i</code> 对应的最长障碍赛跑路线的长度。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>obstacles = [1,2,3,2]
<strong>输出：</strong>[1,2,3,3]
<strong>解释：</strong>每个位置的最长有效障碍路线是：
- i = 0: [<em><strong>1</strong></em>], [1] 长度为 1
- i = 1: [<em><strong>1</strong></em>,<em><strong>2</strong></em>], [1,2] 长度为 2
- i = 2: [<em><strong>1</strong></em>,<em><strong>2</strong></em>,<em><strong>3</strong></em>], [1,2,3] 长度为 3
- i = 3: [<em><strong>1</strong></em>,<em><strong>2</strong></em>,3,<em><strong>2</strong></em>], [1,2,2] 长度为 3
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>obstacles = [2,2,1]
<strong>输出：</strong>[1,2,1]
<strong>解释：</strong>每个位置的最长有效障碍路线是：
- i = 0: [<em><strong>2</strong></em>], [2] 长度为 1
- i = 1: [<em><strong>2</strong></em>,<em><strong>2</strong></em>], [2,2] 长度为 2
- i = 2: [2,2,<em><strong>1</strong></em>], [1] 长度为 1
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>obstacles = [3,1,5,6,4,2]
<strong>输出：</strong>[1,1,2,3,2,2]
<strong>解释：</strong>每个位置的最长有效障碍路线是：
- i = 0: [<em><strong>3</strong></em>], [3] 长度为 1
- i = 1: [3,<em><strong>1</strong></em>], [1] 长度为 1
- i = 2: [<em><strong>3</strong></em>,1,<em><strong>5</strong></em>], [3,5] 长度为 2, [1,5] 也是有效的障碍赛跑路线
- i = 3: [<em><strong>3</strong></em>,1,<em><strong>5</strong></em>,<em><strong>6</strong></em>], [3,5,6] 长度为 3, [1,5,6] 也是有效的障碍赛跑路线
- i = 4: [<em><strong>3</strong></em>,1,5,6,<em><strong>4</strong></em>], [3,4] 长度为 2, [1,4] 也是有效的障碍赛跑路线
- i = 5: [3,<em><strong>1</strong></em>,5,6,4,<em><strong>2</strong></em>], [1,2] 长度为 2
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == obstacles.length</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= obstacles[i] &lt;= 10<sup>7</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：树状数组

我们可以用树状数组维护一个最长递增子序列的长度数组。

然后对于每个障碍，我们在树状数组中查询小于等于当前障碍的最长递增子序列的长度，假设为 $l$，那么当前障碍的最长递增子序列的长度为 $l+1$，我们将 $l+1$ 添加到答案数组中，并将 $l+1$ 更新到树状数组。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(n)$。其中 $n$ 为障碍的数量。

<!-- tabs:start -->

#### Python3

```python
class BinaryIndexedTree:
    __slots__ = ["n", "c"]

    def __init__(self, n: int):
        self.n = n
        self.c = [0] * (n + 1)

    def update(self, x: int, v: int):
        while x <= self.n:
            self.c[x] = max(self.c[x], v)
            x += x & -x

    def query(self, x: int) -> int:
        s = 0
        while x:
            s = max(s, self.c[x])
            x -= x & -x
        return s


class Solution:
    def longestObstacleCourseAtEachPosition(self, obstacles: List[int]) -> List[int]:
        nums = sorted(set(obstacles))
        n = len(nums)
        tree = BinaryIndexedTree(n)
        ans = []
        for x in obstacles:
            i = bisect_left(nums, x) + 1
            ans.append(tree.query(i) + 1)
            tree.update(i, ans[-1])
        return ans
```

#### Java

```java
class BinaryIndexedTree {
    private int n;
    private int[] c;

    public BinaryIndexedTree(int n) {
        this.n = n;
        c = new int[n + 1];
    }

    public void update(int x, int v) {
        while (x <= n) {
            c[x] = Math.max(c[x], v);
            x += x & -x;
        }
    }

    public int query(int x) {
        int s = 0;
        while (x > 0) {
            s = Math.max(s, c[x]);
            x -= x & -x;
        }
        return s;
    }
}

class Solution {
    public int[] longestObstacleCourseAtEachPosition(int[] obstacles) {
        int[] nums = obstacles.clone();
        Arrays.sort(nums);
        int n = nums.length;
        int[] ans = new int[n];
        BinaryIndexedTree tree = new BinaryIndexedTree(n);
        for (int k = 0; k < n; ++k) {
            int x = obstacles[k];
            int i = Arrays.binarySearch(nums, x) + 1;
            ans[k] = tree.query(i) + 1;
            tree.update(i, ans[k]);
        }
        return ans;
    }
}
```

#### C++

```cpp
class BinaryIndexedTree {
private:
    int n;
    vector<int> c;

public:
    BinaryIndexedTree(int n) {
        this->n = n;
        c = vector<int>(n + 1);
    }

    void update(int x, int v) {
        while (x <= n) {
            c[x] = max(c[x], v);
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
};

class Solution {
public:
    vector<int> longestObstacleCourseAtEachPosition(vector<int>& obstacles) {
        vector<int> nums = obstacles;
        sort(nums.begin(), nums.end());
        int n = nums.size();
        vector<int> ans(n);
        BinaryIndexedTree tree(n);
        for (int k = 0; k < n; ++k) {
            int x = obstacles[k];
            auto it = lower_bound(nums.begin(), nums.end(), x);
            int i = distance(nums.begin(), it) + 1;
            ans[k] = tree.query(i) + 1;
            tree.update(i, ans[k]);
        }
        return ans;
    }
};
```

#### Go

```go
type BinaryIndexedTree struct {
	n int
	c []int
}

func NewBinaryIndexedTree(n int) *BinaryIndexedTree {
	return &BinaryIndexedTree{n, make([]int, n+1)}
}

func (bit *BinaryIndexedTree) update(x, v int) {
	for x <= bit.n {
		bit.c[x] = max(bit.c[x], v)
		x += x & -x
	}
}

func (bit *BinaryIndexedTree) query(x int) (s int) {
	for x > 0 {
		s = max(s, bit.c[x])
		x -= x & -x
	}
	return
}

func longestObstacleCourseAtEachPosition(obstacles []int) (ans []int) {
	nums := slices.Clone(obstacles)
	sort.Ints(nums)
	n := len(nums)
	tree := NewBinaryIndexedTree(n)
	for k, x := range obstacles {
		i := sort.SearchInts(nums, x) + 1
		ans = append(ans, tree.query(i)+1)
		tree.update(i, ans[k])
	}
	return
}
```

#### TypeScript

```ts
class BinaryIndexedTree {
    private n: number;
    private c: number[];

    constructor(n: number) {
        this.n = n;
        this.c = Array(n + 1).fill(0);
    }

    update(x: number, v: number): void {
        while (x <= this.n) {
            this.c[x] = Math.max(this.c[x], v);
            x += x & -x;
        }
    }

    query(x: number): number {
        let s = 0;
        while (x > 0) {
            s = Math.max(s, this.c[x]);
            x -= x & -x;
        }
        return s;
    }
}

function longestObstacleCourseAtEachPosition(obstacles: number[]): number[] {
    const nums: number[] = [...obstacles];
    nums.sort((a, b) => a - b);
    const n: number = nums.length;
    const ans: number[] = [];
    const tree: BinaryIndexedTree = new BinaryIndexedTree(n);
    const search = (x: number): number => {
        let [l, r] = [0, n];
        while (l < r) {
            const mid = (l + r) >> 1;
            if (nums[mid] >= x) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    };
    for (let k = 0; k < n; ++k) {
        const i: number = search(obstacles[k]) + 1;
        ans[k] = tree.query(i) + 1;
        tree.update(i, ans[k]);
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
