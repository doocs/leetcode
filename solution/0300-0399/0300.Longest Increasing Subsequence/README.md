# [300. 最长递增子序列](https://leetcode.cn/problems/longest-increasing-subsequence)

[English Version](/solution/0300-0399/0300.Longest%20Increasing%20Subsequence/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个整数数组 <code>nums</code> ，找到其中最长严格递增子序列的长度。</p>

<p><strong>子序列&nbsp;</strong>是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。例如，<code>[3,6,2,7]</code> 是数组 <code>[0,3,1,6,2,2,7]</code> 的子序列。</p>
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

<!-- 这里可写通用的实现逻辑 -->

**方法一：动态规划**

定义 `dp[i]` 为以 `nums[i]` 结尾的最长子序列的长度，`dp[i]` 初始化为 1(`i∈[0, n)`)。即题目求的是 `dp[i]` （`i ∈[0, n-1]`）的最大值。

状态转移方程为：`dp[i] = max(dp[j]) + 1`，其中 `0≤j<i` 且 `nums[j] < nums[i]`。

时间复杂度：$O(n^{2})$。

**方法二：贪心 + 二分查找**

维护一个数组 `d[i]`，表示长度为 i 的最长上升子序列末尾元素的最小值，初始值 `d[1] = nums[0]`。

直观上，`d[i]` 是单调递增数组。

证明：假设存在 `d[j] ≥ d[i]`，且 `j < i`，我们考虑从长度为 i 的最长上升子序列的末尾删除 `i - j` 个元素，那么这个序列长度变为 j，且第 j 个元素 `d[j]` 必然小于 `d[i]`，由于前面假设 `d[j] ≥ d[i]`，产生了矛盾，因此数组 d 是单调递增数组。

算法思路：

设当前求出的最长上升子序列的长度为 size，初始 `size = 1`，从前往后遍历数组 nums，在遍历到 `nums[i]` 时：

-   若 `nums[i] > d[size]`，则直接将 `nums[i]` 加入到数组 d 的末尾，并且更新 size 自增；
-   否则，在数组 d 中二分查找（前面证明 d 是一个单调递增数组），找到第一个大于等于 `nums[i]` 的位置 idx，更新 `d[idx] = nums[i]`。

最终返回 size。

时间复杂度：$O(nlogn)$。

**方法三：树状数组**

树状数组，也称作“二叉索引树”（Binary Indexed Tree）或 Fenwick 树。 它可以高效地实现如下两个操作：

1. **单点更新** `update(x, delta)`： 把序列 x 位置的数加上一个值 delta；
1. **前缀和查询** `query(x)`：查询序列 `[1,...x]` 区间的区间和，即位置 x 的前缀和。

这两个操作的时间复杂度均为 $O(\log n)$。当数的范围比较大时，需要进行离散化，即先进行去重并排序，然后对每个数字进行编号。

本题我们使用树状数组 `tree[x]` 来维护以 x 结尾的最长上升子序列的长度。

时间复杂度：$O(nlogn)$。

```python
def update(x, val):
    while x <= n:
        c[x] = max(c[x], val)
        x += lowbit(x)


def query(x):
    s = 0
    while x > 0:
        s = max(s, c[x])
        x -= lowbit(x)
    return s
```

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

动态规划：

```python
class Solution:
    def lengthOfLIS(self, nums: List[int]) -> int:
        n = len(nums)
        dp = [1] * n
        for i in range(1, n):
            for j in range(i):
                if nums[j] < nums[i]:
                    dp[i] = max(dp[i], dp[j] + 1)
        return max(dp)
```

贪心 + 二分查找：

```python
class Solution:
    def lengthOfLIS(self, nums: List[int]) -> int:
        d = [nums[0]]
        for x in nums[1:]:
            if x > d[-1]:
                d.append(x)
            else:
                idx = bisect_left(d, x)
                if idx == len(d):
                    idx = 0
                d[idx] = x
        return len(d)
```

树状数组：

```python
class BinaryIndexedTree:
    def __init__(self, n):
        self.n = n
        self.c = [0] * (n + 1)

    @staticmethod
    def lowbit(x):
        return x & -x

    def update(self, x, val):
        while x <= self.n:
            self.c[x] = max(self.c[x], val)
            x += BinaryIndexedTree.lowbit(x)

    def query(self, x):
        s = 0
        while x:
            s = max(s, self.c[x])
            x -= BinaryIndexedTree.lowbit(x)
        return s


class Solution:
    def lengthOfLIS(self, nums: List[int]) -> int:
        s = sorted(set(nums))
        m = {v: i for i, v in enumerate(s, 1)}
        tree = BinaryIndexedTree(len(m))
        ans = 1
        for v in nums:
            x = m[v]
            t = tree.query(x - 1) + 1
            ans = max(ans, t)
            tree.update(x, t)
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

动态规划：

```java
class Solution {
    public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        int res = 1;
        for (int i = 1; i < n; ++i) {
            for (int j = 0; j < i; ++j) {
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            res = Math.max(res, dp[i]);
        }
        return res;
    }
}
```

贪心 + 二分查找：

```java
class Solution {
    public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        int[] d = new int[n + 1];
        d[1] = nums[0];
        int size = 1;
        for (int i = 1; i < n; ++i) {
            if (nums[i] > d[size]) {
                d[++size] = nums[i];
            } else {
                int left = 1, right = size;
                while (left < right) {
                    int mid = (left + right) >> 1;
                    if (d[mid] >= nums[i]) {
                        right = mid;
                    } else {
                        left = mid + 1;
                    }
                }
                int p = d[left] >= nums[i] ? left : 1;
                d[p] = nums[i];
            }
        }
        return size;
    }
}
```

树状数组：

```java
class Solution {
    public int lengthOfLIS(int[] nums) {
        TreeSet<Integer> ts = new TreeSet();
        for (int v : nums) {
            ts.add(v);
        }
        int idx = 1;
        Map<Integer, Integer> m = new HashMap<>();
        for (int v : ts) {
            m.put(v, idx++);
        }
        BinaryIndexedTree tree = new BinaryIndexedTree(m.size());
        int ans = 1;
        for (int v : nums) {
            int x = m.get(v);
            int t = tree.query(x - 1) + 1;
            ans = Math.max(ans, t);
            tree.update(x, t);
        }
        return ans;
    }
}

class BinaryIndexedTree {
    private int n;
    private int[] c;

    public BinaryIndexedTree(int n) {
        this.n = n;
        c = new int[n + 1];
    }

    public void update(int x, int val) {
        while (x <= n) {
            c[x] = Math.max(c[x], val);
            x += lowbit(x);
        }
    }

    public int query(int x) {
        int s = 0;
        while (x > 0) {
            s = Math.max(s, c[x]);
            x -= lowbit(x);
        }
        return s;
    }

    public static int lowbit(int x) {
        return x & -x;
    }
}
```

### **TypeScript**

动态规划：

```ts
function lengthOfLIS(nums: number[]): number {
    let n = nums.length;
    let dp = new Array(n).fill(1);
    for (let i = 0; i < n; i++) {
        for (let j = 0; j < i; j++) {
            if (nums[j] < nums[i]) {
                dp[i] = Math.max(dp[i], dp[j] + 1);
            }
        }
    }
    return Math.max(...dp);
}
```

贪心 + 二分查找：

```ts
function lengthOfLIS(nums: number[]): number {
    const n = nums.length;
    let d = new Array(n + 1);
    d[1] = nums[0];
    let size = 1;
    for (let i = 1; i < n; ++i) {
        if (nums[i] > d[size]) {
            d[++size] = nums[i];
        } else {
            let left = 1,
                right = size;
            while (left < right) {
                const mid = (left + right) >> 1;
                if (d[mid] >= nums[i]) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }
            const p = d[left] >= nums[i] ? left : 1;
            d[p] = nums[i];
        }
    }
    return size;
}
```

### **C++**

动态规划：

```cpp
class Solution {
public:
    int lengthOfLIS(vector<int>& nums) {
        int n = nums.size();
        vector<int> dp(n, 1);
        for (int i = 1; i < n; ++i) {
            for (int j = 0; j < i; ++j) {
                if (nums[j] < nums[i]) dp[i] = max(dp[i], dp[j] + 1);
            }
        }
        return *max_element(dp.begin(), dp.end());
    }
};
```

贪心 + 二分查找：

```cpp
class Solution {
public:
    int lengthOfLIS(vector<int>& nums) {
        int n = nums.size();
        vector<int> d {nums[0]};
        for (int i = 1; i < n; ++i) {
            if (nums[i] > d[d.size() - 1])
                d.push_back(nums[i]);
            else {
                int idx = lower_bound(d.begin(), d.end(), nums[i]) - d.begin();
                if (idx == d.size()) idx = 0;
                d[idx] = nums[i];
            }
        }
        return d.size();
    }
};
```

树状数组：

```cpp
class BinaryIndexedTree {
public:
    int n;
    vector<int> c;

    BinaryIndexedTree(int _n): n(_n), c(_n + 1){}

    void update(int x, int val) {
        while (x <= n)
        {
            c[x] = max(c[x], val);
            x += lowbit(x);
        }
    }

    int query(int x) {
        int s = 0;
        while (x > 0)
        {
            s = max(s, c[x]);
            x -= lowbit(x);
        }
        return s;
    }

    int lowbit(int x) {
        return x & -x;
    }
};

class Solution {
public:
    int lengthOfLIS(vector<int>& nums) {
        set<int> s(nums.begin(), nums.end());
        int idx = 1;
        unordered_map<int, int> m;
        for (int v : s) m[v] = idx++;
        BinaryIndexedTree* tree = new BinaryIndexedTree(m.size());
        int ans = 1;
        for (int v : nums)
        {
            int x = m[v];
            int t = tree->query(x - 1) + 1;
            ans = max(ans, t);
            tree->update(x, t);
        }
        return ans;
    }
};
```

### **Go**

动态规划：

```go
func lengthOfLIS(nums []int) int {
	n := len(nums)
	dp := make([]int, n)
	dp[0] = 1
	res := 1
	for i := 1; i < n; i++ {
		dp[i] = 1
		for j := 0; j < i; j++ {
			if nums[j] < nums[i] {
				dp[i] = max(dp[i], dp[j]+1)
			}
		}
		res = max(res, dp[i])
	}
	return res
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
```

贪心 + 二分查找：

```go
func lengthOfLIS(nums []int) int {
	d := make([]int, len(nums)+1)
	d[1] = nums[0]
	size := 1
	for _, x := range nums[1:] {
		if x > d[size] {
			size++
			d[size] = x
		} else {
			left, right := 1, size
			for left < right {
				mid := (left + right) >> 1
				if d[mid] >= x {
					right = mid
				} else {
					left = mid + 1
				}
			}
			if d[left] < x {
				left = 1
			}
			d[left] = x
		}
	}
	return size
}
```

树状数组：

```go
type BinaryIndexedTree struct {
	n int
	c []int
}

func newBinaryIndexedTree(n int) *BinaryIndexedTree {
	c := make([]int, n+1)
	return &BinaryIndexedTree{n, c}
}

func (this *BinaryIndexedTree) lowbit(x int) int {
	return x & -x
}

func (this *BinaryIndexedTree) update(x, val int) {
	for x <= this.n {
		if this.c[x] < val {
			this.c[x] = val
		}
		x += this.lowbit(x)
	}
}

func (this *BinaryIndexedTree) query(x int) int {
	s := 0
	for x > 0 {
		if s < this.c[x] {
			s = this.c[x]
		}
		x -= this.lowbit(x)
	}
	return s
}

func lengthOfLIS(nums []int) int {
	s := make(map[int]bool)
	for _, v := range nums {
		s[v] = true
	}
	var t []int
	for v, _ := range s {
		t = append(t, v)
	}
	sort.Ints(t)
	m := make(map[int]int)
	for i, v := range t {
		m[v] = i + 1
	}
	ans := 1
	tree := newBinaryIndexedTree(len(m))
	for _, v := range nums {
		x := m[v]
		t := tree.query(x-1) + 1
		if ans < t {
			ans = t
		}
		tree.update(x, t)
	}
	return ans
}
```

### **Rust**

```rust
impl Solution {
    pub fn length_of_lis(nums: Vec<i32>) -> i32 {
        let n = nums.len();
        let mut dp = vec![1; n];
        for i in 1..n {
            for j in 0..i {
                if nums[i] > nums[j] {
                    dp[i] = dp[i].max(dp[j] + 1);
                }
            }
        }
        *dp.iter().max().unwrap()
    }
}
```

### **...**

```

```

<!-- tabs:end -->
