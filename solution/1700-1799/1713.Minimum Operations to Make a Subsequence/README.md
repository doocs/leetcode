# [1713. 得到子序列的最少操作次数](https://leetcode.cn/problems/minimum-operations-to-make-a-subsequence)

[English Version](/solution/1700-1799/1713.Minimum%20Operations%20to%20Make%20a%20Subsequence/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

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

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：最长递增子序列**

根据题意，`target` 和 `arr` 这两个数组的公共子序列越长，需要添加的元素就越少。因此，最少添加的元素个数等于 `target` 的长度减去 `target` 和 `arr` 的最长公共子序列的长度。

但是，[求最长公共子序列](/solution/1100-1199/1143.Longest%20Common%20Subsequence/README.md)的时间复杂度为 $O(mn)$，无法通过本题，需要转变思路。

我们可以用一个哈希表记录 `target` 数组中每个元素的下标，然后遍历 `arr` 数组，对于 `arr` 数组中的每个元素，如果哈希表中存在该元素，则将该元素的下标加入到一个数组中，这样就得到了一个新的数组 `nums`，该数组是 `arr` 中的元素在 `target` 数组中的下标（去掉了不在 `target` 中的元素），该数组的最长递增子序列的长度就是 `target` 和 `arr` 的最长公共子序列的长度。

因此，问题转化为求 `nums` 数组的最长递增子序列的长度。参考 [300. 最长递增子序列](/solution/0300-0399/0300.Longest%20Increasing%20Subsequence/README.md)。

时间复杂度 $O(n\log n)$，其中 $n$ 为 `arr` 数组的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
    def minOperations(self, target: List[int], arr: List[int]) -> int:
        d = {v: i for i, v in enumerate(target)}
        nums = [d[v] for v in arr if v in d]
        return len(target) - self.lengthOfLIS(nums)

    def lengthOfLIS(self, nums):
        s = sorted(set(nums))
        m = {v: i for i, v in enumerate(s, 1)}
        tree = BinaryIndexedTree(len(m))
        ans = 0
        for v in nums:
            x = m[v]
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
        this.c = new int[n + 1];
    }

    public static int lowbit(int x) {
        return x & -x;
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
}

class Solution {
    public int minOperations(int[] target, int[] arr) {
        Map<Integer, Integer> d = new HashMap<>();
        for (int i = 0; i < target.length; ++i) {
            d.put(target[i], i);
        }
        List<Integer> nums = new ArrayList<>();
        for (int i = 0; i < arr.length; ++i) {
            if (d.containsKey(arr[i])) {
                nums.add(d.get(arr[i]));
            }
        }
        return target.length - lengthOfLIS(nums);
    }

    private int lengthOfLIS(List<Integer> nums) {
        TreeSet<Integer> ts = new TreeSet();
        for (int v : nums) {
            ts.add(v);
        }
        int idx = 1;
        Map<Integer, Integer> d = new HashMap<>();
        for (int v : ts) {
            d.put(v, idx++);
        }
        int ans = 0;
        BinaryIndexedTree tree = new BinaryIndexedTree(nums.size());
        for (int v : nums) {
            int x = d.get(v);
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
    int minOperations(vector<int>& target, vector<int>& arr) {
        unordered_map<int, int> d;
        for (int i = 0; i < target.size(); ++i) d[target[i]] = i;
        vector<int> nums;
        for (int i = 0; i < arr.size(); ++i) {
            if (d.count(arr[i])) {
                nums.push_back(d[arr[i]]);
            }
        }
        return target.size() - lengthOfLIS(nums);
    }

    int lengthOfLIS(vector<int>& nums) {
        set<int> s(nums.begin(), nums.end());
        int idx = 1;
        unordered_map<int, int> d;
        for (int v : s) d[v] = idx++;
        BinaryIndexedTree* tree = new BinaryIndexedTree(d.size());
        int ans = 0;
        for (int v : nums) {
            int x = d[v];
            int t = tree->query(x - 1) + 1;
            ans = max(ans, t);
            tree->update(x, t);
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

func minOperations(target []int, arr []int) int {
	d := map[int]int{}
	for i, v := range target {
		d[v] = i
	}
	nums := []int{}
	for _, v := range arr {
		if i, ok := d[v]; ok {
			nums = append(nums, i)
		}
	}
	return len(target) - lengthOfLIS(nums)
}

func lengthOfLIS(nums []int) int {
	s := map[int]bool{}
	for _, v := range nums {
		s[v] = true
	}
	t := []int{}
	for v := range s {
		t = append(t, v)
	}
	sort.Ints(t)
	d := map[int]int{}
	for i, v := range t {
		d[v] = i + 1
	}
	tree := newBinaryIndexedTree(len(d))
	ans := 0
	for _, v := range nums {
		x := d[v]
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
