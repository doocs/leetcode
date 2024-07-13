---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1700-1799/1713.Minimum%20Operations%20to%20Make%20a%20Subsequence/README.md
rating: 2350
source: 第 222 场周赛 Q4
tags:
    - 贪心
    - 数组
    - 哈希表
    - 二分查找
---

<!-- problem:start -->

# [1713. 得到子序列的最少操作次数](https://leetcode.cn/problems/minimum-operations-to-make-a-subsequence)

[English Version](/solution/1700-1799/1713.Minimum%20Operations%20to%20Make%20a%20Subsequence/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个数组 <code>target</code> ，包含若干 <strong>互不相同</strong> 的整数，以及另一个整数数组 <code>arr</code> ，<code>arr</code> <strong>可能</strong> 包含重复元素。</p>

<p>每一次操作中，你可以在 <code>arr</code> 的任意位置插入任一整数。比方说，如果 <code>arr = [1,4,1,2]</code> ，那么你可以在中间添加 <code>3</code> 得到 <code>[1,4,<strong>3</strong>,1,2]</code> 。你可以在数组最开始或最后面添加整数。</p>

<p>请你返回 <strong>最少</strong> 操作次数，使得<em> </em><code>target</code><em> </em>成为 <code>arr</code> 的一个子序列。</p>

<p>一个数组的 <strong>子序列</strong> 指的是删除原数组的某些元素（可能一个元素都不删除），同时不改变其余元素的相对顺序得到的数组。比方说，<code>[2,7,4]</code> 是 <code>[4,<strong>2</strong>,3,<strong>7</strong>,2,1,<strong>4</strong>]</code> 的子序列（加粗元素），但 <code>[2,4,2]</code> 不是子序列。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre><b>输入：</b>target = [5,1,3], <code>arr</code> = [9,4,2,3,4]
<b>输出：</b>2
<b>解释：</b>你可以添加 5 和 1 ，使得 arr 变为 [<strong>5</strong>,9,4,<strong>1</strong>,2,3,4] ，target 为 arr 的子序列。
</pre>

<p><strong>示例 2：</strong></p>

<pre><b>输入：</b>target = [6,4,8,1,3,2], <code>arr</code> = [4,7,6,2,3,8,6,1]
<b>输出：</b>3
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= target.length, arr.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= target[i], arr[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>target</code> 不包含任何重复元素。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：最长递增子序列 + 树状数组

根据题意，`target` 和 `arr` 这两个数组的公共子序列越长，需要添加的元素就越少。因此，最少添加的元素个数等于 `target` 的长度减去 `target` 和 `arr` 的最长公共子序列的长度。

但是，[求最长公共子序列](https://github.com/doocs/leetcode/blob/main/solution/1100-1199/1143.Longest%20Common%20Subsequence/README.md)的时间复杂度为 $O(m \times n)$，无法通过本题，需要转变思路。

我们可以用一个哈希表记录 `target` 数组中每个元素的下标，然后遍历 `arr` 数组，对于 `arr` 数组中的每个元素，如果哈希表中存在该元素，则将该元素的下标加入到一个数组中，这样就得到了一个新的数组 `nums`，该数组是 `arr` 中的元素在 `target` 数组中的下标（去掉了不在 `target` 中的元素），该数组的最长递增子序列的长度就是 `target` 和 `arr` 的最长公共子序列的长度。

因此，问题转化为求 `nums` 数组的最长递增子序列的长度。参考 [300. 最长递增子序列](https://github.com/doocs/leetcode/blob/main/solution/0300-0399/0300.Longest%20Increasing%20Subsequence/README.md)。

时间复杂度 $O(n \times \log m)$，空间复杂度 $O(m)$。其中 $m$ 和 $n$ 分别为 `target` 和 `arr` 的长度。

<!-- tabs:start -->

#### Python3

```python
class BinaryIndexedTree:
    __slots__ = "n", "c"

    def __init__(self, n: int):
        self.n = n
        self.c = [0] * (n + 1)

    def update(self, x: int, v: int):
        while x <= self.n:
            self.c[x] = max(self.c[x], v)
            x += x & -x

    def query(self, x: int) -> int:
        res = 0
        while x:
            res = max(res, self.c[x])
            x -= x & -x
        return res


class Solution:
    def minOperations(self, target: List[int], arr: List[int]) -> int:
        d = {x: i for i, x in enumerate(target, 1)}
        nums = [d[x] for x in arr if x in d]
        m = len(target)
        tree = BinaryIndexedTree(m)
        ans = 0
        for x in nums:
            v = tree.query(x - 1) + 1
            ans = max(ans, v)
            tree.update(x, v)
        return len(target) - ans
```

#### Java

```java
class BinaryIndexedTree {
    private int n;
    private int[] c;

    public BinaryIndexedTree(int n) {
        this.n = n;
        this.c = new int[n + 1];
    }

    public void update(int x, int v) {
        for (; x <= n; x += x & -x) {
            c[x] = Math.max(c[x], v);
        }
    }

    public int query(int x) {
        int ans = 0;
        for (; x > 0; x -= x & -x) {
            ans = Math.max(ans, c[x]);
        }
        return ans;
    }
}

class Solution {
    public int minOperations(int[] target, int[] arr) {
        int m = target.length;
        Map<Integer, Integer> d = new HashMap<>(m);
        for (int i = 0; i < m; i++) {
            d.put(target[i], i + 1);
        }
        List<Integer> nums = new ArrayList<>();
        for (int x : arr) {
            if (d.containsKey(x)) {
                nums.add(d.get(x));
            }
        }
        BinaryIndexedTree tree = new BinaryIndexedTree(m);
        int ans = 0;
        for (int x : nums) {
            int v = tree.query(x - 1) + 1;
            ans = Math.max(ans, v);
            tree.update(x, v);
        }
        return m - ans;
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
    BinaryIndexedTree(int n)
        : n(n)
        , c(n + 1) {}

    void update(int x, int v) {
        for (; x <= n; x += x & -x) {
            c[x] = max(c[x], v);
        }
    }

    int query(int x) {
        int ans = 0;
        for (; x > 0; x -= x & -x) {
            ans = max(ans, c[x]);
        }
        return ans;
    }
};

class Solution {
public:
    int minOperations(vector<int>& target, vector<int>& arr) {
        int m = target.size();
        unordered_map<int, int> d;
        for (int i = 0; i < m; ++i) {
            d[target[i]] = i + 1;
        }
        vector<int> nums;
        for (int x : arr) {
            if (d.contains(x)) {
                nums.push_back(d[x]);
            }
        }
        BinaryIndexedTree tree(m);
        int ans = 0;
        for (int x : nums) {
            int v = tree.query(x - 1) + 1;
            ans = max(ans, v);
            tree.update(x, v);
        }
        return m - ans;
    }
};
```

#### Go

```go
type BinaryIndexedTree struct {
	n int
	c []int
}

func NewBinaryIndexedTree(n int) BinaryIndexedTree {
	return BinaryIndexedTree{n: n, c: make([]int, n+1)}
}

func (bit *BinaryIndexedTree) Update(x, v int) {
	for ; x <= bit.n; x += x & -x {
		if v > bit.c[x] {
			bit.c[x] = v
		}
	}
}

func (bit *BinaryIndexedTree) Query(x int) int {
	ans := 0
	for ; x > 0; x -= x & -x {
		if bit.c[x] > ans {
			ans = bit.c[x]
		}
	}
	return ans
}

func minOperations(target []int, arr []int) int {
	m := len(target)
	d := make(map[int]int)
	for i, x := range target {
		d[x] = i + 1
	}
	var nums []int
	for _, x := range arr {
		if pos, exists := d[x]; exists {
			nums = append(nums, pos)
		}
	}
	tree := NewBinaryIndexedTree(m)
	ans := 0
	for _, x := range nums {
		v := tree.Query(x-1) + 1
		if v > ans {
			ans = v
		}
		tree.Update(x, v)
	}
	return m - ans
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
        for (; x <= this.n; x += x & -x) {
            this.c[x] = Math.max(this.c[x], v);
        }
    }

    query(x: number): number {
        let ans = 0;
        for (; x > 0; x -= x & -x) {
            ans = Math.max(ans, this.c[x]);
        }
        return ans;
    }
}

function minOperations(target: number[], arr: number[]): number {
    const m = target.length;
    const d: Map<number, number> = new Map();
    target.forEach((x, i) => d.set(x, i + 1));
    const nums: number[] = [];
    arr.forEach(x => {
        if (d.has(x)) {
            nums.push(d.get(x)!);
        }
    });
    const tree = new BinaryIndexedTree(m);
    let ans = 0;
    nums.forEach(x => {
        const v = tree.query(x - 1) + 1;
        ans = Math.max(ans, v);
        tree.update(x, v);
    });
    return m - ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
