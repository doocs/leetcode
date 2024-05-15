---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0300-0399/0300.Longest%20Increasing%20Subsequence/README.md
tags:
    - 数组
    - 二分查找
    - 动态规划
---

# [300. 最长递增子序列](https://leetcode.cn/problems/longest-increasing-subsequence)

[English Version](/solution/0300-0399/0300.Longest%20Increasing%20Subsequence/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个整数数组 <code>nums</code> ，找到其中最长严格递增子序列的长度。</p>

<p><strong>子序列&nbsp;</strong>是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。例如，<code>[3,6,2,7]</code> 是数组 <code>[0,3,1,6,2,2,7]</code> 的<span data-keyword="subsequence-array">子序列</span>。</p>
&nbsp;

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [10,9,2,5,3,7,101,18]
<strong>输出：</strong>4
<strong>解释：</strong>最长递增子序列是 [2,3,7,101]，因此长度为 4 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [0,1,0,3,2,3]
<strong>输出：</strong>4
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>nums = [7,7,7,7,7,7,7]
<strong>输出：</strong>1
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 2500</code></li>
	<li><code>-10<sup>4</sup> &lt;= nums[i] &lt;= 10<sup>4</sup></code></li>
</ul>

<p>&nbsp;</p>

<p><b>进阶：</b></p>

<ul>
	<li>你能将算法的时间复杂度降低到&nbsp;<code>O(n log(n))</code> 吗?</li>
</ul>

## 解法

### 方法一：动态规划

我们定义 $f[i]$ 表示以 $nums[i]$ 结尾的最长递增子序列的长度，初始时 $f[i] = 1$，答案为 $f[i]$ 的最大值。

对于 $f[i]$，我们需要枚举 $0 \le j \lt i$，如果 $nums[j] \lt nums[i]$，则 $f[i] = \max(f[i], f[j] + 1)$。

最后的答案即为 $f[i]$ 的最大值。

时间复杂度 $O(n^2)$，空间复杂度 $O(n)$。其中 $n$ 为数组长度。

<!-- tabs:start -->

```python
class Solution:
    def lengthOfLIS(self, nums: List[int]) -> int:
        n = len(nums)
        f = [1] * n
        for i in range(1, n):
            for j in range(i):
                if nums[j] < nums[i]:
                    f[i] = max(f[i], f[j] + 1)
        return max(f)
```

```java
class Solution {
    public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        int[] f = new int[n];
        Arrays.fill(f, 1);
        int ans = 1;
        for (int i = 1; i < n; ++i) {
            for (int j = 0; j < i; ++j) {
                if (nums[j] < nums[i]) {
                    f[i] = Math.max(f[i], f[j] + 1);
                }
            }
            ans = Math.max(ans, f[i]);
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int lengthOfLIS(vector<int>& nums) {
        int n = nums.size();
        vector<int> f(n, 1);
        for (int i = 1; i < n; ++i) {
            for (int j = 0; j < i; ++j) {
                if (nums[j] < nums[i]) {
                    f[i] = max(f[i], f[j] + 1);
                }
            }
        }
        return *max_element(f.begin(), f.end());
    }
};
```

```go
func lengthOfLIS(nums []int) int {
	n := len(nums)
	f := make([]int, n)
	for i := range f {
		f[i] = 1
	}
	ans := 1
	for i := 1; i < n; i++ {
		for j := 0; j < i; j++ {
			if nums[j] < nums[i] {
				f[i] = max(f[i], f[j]+1)
				ans = max(ans, f[i])
			}
		}
	}
	return ans
}
```

```ts
function lengthOfLIS(nums: number[]): number {
    const n = nums.length;
    const f: number[] = new Array(n).fill(1);
    for (let i = 1; i < n; ++i) {
        for (let j = 0; j < i; ++j) {
            if (nums[j] < nums[i]) {
                f[i] = Math.max(f[i], f[j] + 1);
            }
        }
    }
    return Math.max(...f);
}
```

```rust
impl Solution {
    pub fn length_of_lis(nums: Vec<i32>) -> i32 {
        let n = nums.len();
        let mut f = vec![1; n];
        for i in 1..n {
            for j in 0..i {
                if nums[j] < nums[i] {
                    f[i] = f[i].max(f[j] + 1);
                }
            }
        }
        *f.iter().max().unwrap()
    }
}
```

<!-- tabs:end -->

### 方法二：离散化 + 树状数组

我们将数组中的元素离散化，然后使用树状数组维护不大于某个元素的最长递增子序列的长度。

遍历数组中的每个元素 $x$，将其离散化，然后在树状数组中查找不大于 $x-1$ 的最长递增子序列的长度 $t$，则 $x$ 的最长递增子序列的长度为 $t+1$，更新答案，并且更新树状数组中 $x$ 的最长递增子序列的长度。

遍历完数组中的所有元素，即可得到答案。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(n)$。其中 $n$ 为数组长度。

<!-- tabs:start -->

```python
class BinaryIndexedTree:
    def __init__(self, n: int):
        self.n = n
        self.c = [0] * (n + 1)

    def update(self, x: int, v: int):
        while x <= self.n:
            self.c[x] = max(self.c[x], v)
            x += x & -x

    def query(self, x: int) -> int:
        mx = 0
        while x:
            mx = max(mx, self.c[x])
            x -= x & -x
        return mx


class Solution:
    def lengthOfLIS(self, nums: List[int]) -> int:
        s = sorted(set(nums))
        m = len(s)
        tree = BinaryIndexedTree(m)
        for x in nums:
            x = bisect_left(s, x) + 1
            t = tree.query(x - 1) + 1
            tree.update(x, t)
        return tree.query(m)
```

```java
class Solution {
    public int lengthOfLIS(int[] nums) {
        int[] s = nums.clone();
        Arrays.sort(s);
        int m = 0;
        int n = s.length;
        for (int i = 0; i < n; ++i) {
            if (i == 0 || s[i] != s[i - 1]) {
                s[m++] = s[i];
            }
        }
        BinaryIndexedTree tree = new BinaryIndexedTree(m);
        for (int x : nums) {
            x = search(s, x, m);
            int t = tree.query(x - 1) + 1;
            tree.update(x, t);
        }
        return tree.query(m);
    }

    private int search(int[] nums, int x, int r) {
        int l = 0;
        while (l < r) {
            int mid = (l + r) >> 1;
            if (nums[mid] >= x) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l + 1;
    }
}

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
        int mx = 0;
        while (x > 0) {
            mx = Math.max(mx, c[x]);
            x -= x & -x;
        }
        return mx;
    }
}
```

```cpp
class BinaryIndexedTree {
public:
    BinaryIndexedTree(int _n)
        : n(_n)
        , c(_n + 1) {}

    void update(int x, int v) {
        while (x <= n) {
            c[x] = max(c[x], v);
            x += x & -x;
        }
    }

    int query(int x) {
        int mx = 0;
        while (x) {
            mx = max(mx, c[x]);
            x -= x & -x;
        }
        return mx;
    }

private:
    int n;
    vector<int> c;
};

class Solution {
public:
    int lengthOfLIS(vector<int>& nums) {
        vector<int> s = nums;
        sort(s.begin(), s.end());
        s.erase(unique(s.begin(), s.end()), s.end());
        BinaryIndexedTree tree(s.size());
        for (int x : nums) {
            x = lower_bound(s.begin(), s.end(), x) - s.begin() + 1;
            int t = tree.query(x - 1) + 1;
            tree.update(x, t);
        }
        return tree.query(s.size());
    }
};
```

```go
type BinaryIndexedTree struct {
	n int
	c []int
}

func newBinaryIndexedTree(n int) *BinaryIndexedTree {
	return &BinaryIndexedTree{n, make([]int, n+1)}
}

func (bit *BinaryIndexedTree) update(x, v int) {
	for x <= bit.n {
		bit.c[x] = max(bit.c[x], v)
		x += x & -x
	}
}

func (bit *BinaryIndexedTree) query(x int) int {
	mx := 0
	for x > 0 {
		mx = max(mx, bit.c[x])
		x -= x & -x
	}
	return mx
}

func lengthOfLIS(nums []int) int {
	n := len(nums)
	s := make([]int, n)
	copy(s, nums)
	sort.Ints(s)
	m := 0
	for i, x := range s {
		if i == 0 || x != s[i-1] {
			s[m] = x
			m++
		}
	}
	tree := newBinaryIndexedTree(m)
	for _, x := range nums {
		x = sort.SearchInts(s[:m], x) + 1
		t := tree.query(x-1) + 1
		tree.update(x, t)
	}
	return tree.query(m)
}
```

```ts
class BinaryIndexedTree {
    private n: number;
    private c: number[];

    constructor(n: number) {
        this.n = n;
        this.c = new Array(n + 1).fill(0);
    }

    update(x: number, v: number) {
        while (x <= this.n) {
            this.c[x] = Math.max(this.c[x], v);
            x += x & -x;
        }
    }

    query(x: number): number {
        let mx = 0;
        while (x) {
            mx = Math.max(mx, this.c[x]);
            x -= x & -x;
        }
        return mx;
    }
}

function lengthOfLIS(nums: number[]): number {
    const s = [...new Set(nums)].sort((a, b) => a - b);
    const m = s.length;
    const tree = new BinaryIndexedTree(m);
    for (let x of nums) {
        x = search(s, x);
        const t = tree.query(x - 1) + 1;
        tree.update(x, t);
    }
    return tree.query(m);
}

function search(nums: number[], x: number): number {
    let l = 0,
        r = nums.length - 1;
    while (l < r) {
        const mid = (l + r) >> 1;
        if (nums[mid] >= x) {
            r = mid;
        } else {
            l = mid + 1;
        }
    }
    return l + 1;
}
```

<!-- tabs:end -->

<!-- end -->
