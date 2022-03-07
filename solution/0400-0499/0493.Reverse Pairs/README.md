# [493. 翻转对](https://leetcode-cn.com/problems/reverse-pairs)

[English Version](/solution/0400-0499/0493.Reverse%20Pairs/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个数组&nbsp;<code>nums</code>&nbsp;，如果&nbsp;<code>i &lt; j</code>&nbsp;且&nbsp;<code>nums[i] &gt; 2*nums[j]</code>&nbsp;我们就将&nbsp;<code>(i, j)</code>&nbsp;称作一个<strong><em>重要翻转对</em></strong>。</p>

<p>你需要返回给定数组中的重要翻转对的数量。</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入</strong>: [1,3,2,3,1]
<strong>输出</strong>: 2
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入</strong>: [2,4,3,5,1]
<strong>输出</strong>: 3
</pre>

<p><strong>注意:</strong></p>

<ol>
	<li>给定数组的长度不会超过<code>50000</code>。</li>
	<li>输入数组中的所有数字都在32位整数的表示范围内。</li>
</ol>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：归并排序**

**方法二：树状数组**

树状数组，也称作“二叉索引树”（Binary Indexed Tree）或 Fenwick 树。 它可以高效地实现如下两个操作：

1. **单点更新** `update(x, delta)`： 把序列 x 位置的数加上一个值 delta；
1. **前缀和查询** `query(x)`：查询序列 `[1,...x]` 区间的区间和，即位置 x 的前缀和。

这两个操作的时间复杂度均为 `O(log n)`。

树状数组最基本的功能就是求比某点 x 小的点的个数（这里的比较是抽象的概念，可以是数的大小、坐标的大小、质量的大小等等）。

比如给定数组 `a[5] = {2, 5, 3, 4, 1}`，求 `b[i] = 位置 i 左边小于等于 a[i] 的数的个数`。对于此例，`b[5] = {0, 1, 1, 2, 0}`。

解决方案是直接遍历数组，每个位置先求出 `query(a[i])`，然后再修改树状数组 `update(a[i], 1)` 即可。当数的范围比较大时，需要进行离散化，即先进行去重并排序，然后对每个数字进行编号。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

归并排序：

```python
class Solution:
    def reversePairs(self, nums: List[int]) -> int:
        def merge_sort(nums, left, right):
            if left >= right:
                return 0
            mid = (left + right) >> 1
            res = merge_sort(nums, left, mid) + \
                merge_sort(nums, mid + 1, right)
            i, j = left, mid + 1
            while i <= mid and j <= right:
                if nums[i] <= 2 * nums[j]:
                    i += 1
                else:
                    res += (mid - i + 1)
                    j += 1
            tmp = []
            i, j = left, mid + 1
            while i <= mid and j <= right:
                if nums[i] <= nums[j]:
                    tmp.append(nums[i])
                    i += 1
                else:
                    tmp.append(nums[j])
                    j += 1
            while i <= mid:
                tmp.append(nums[i])
                i += 1
            while j <= right:
                tmp.append(nums[j])
                j += 1
            for i in range(left, right + 1):
                nums[i] = tmp[i - left]
            return res

        return merge_sort(nums, 0, len(nums) - 1)
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

    def update(self, x, delta):
        while x <= self.n:
            self.c[x] += delta
            x += BinaryIndexedTree.lowbit(x)

    def query(self, x):
        s = 0
        while x > 0:
            s += self.c[x]
            x -= BinaryIndexedTree.lowbit(x)
        return s


class Solution:
    def reversePairs(self, nums: List[int]) -> int:
        s = set()
        for num in nums:
            s.add(num)
            s.add(num * 2)
        alls = sorted(s)
        m = {v: i for i, v in enumerate(alls, 1)}
        ans = 0
        tree = BinaryIndexedTree(len(m))
        for num in nums[::-1]:
            ans += tree.query(m[num] - 1)
            tree.update(m[num * 2], 1)
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

归并排序：

```java
class Solution {
    private static int[] tmp = new int[50010];

    public int reversePairs(int[] nums) {
        return mergeSort(nums, 0, nums.length - 1);
    }

    private int mergeSort(int[] nums, int left, int right) {
        if (left >= right) {
            return 0;
        }
        int mid = (left + right) >> 1;
        int res = mergeSort(nums, left, mid) + mergeSort(nums, mid + 1, right);
        int i = left, j = mid + 1, k = 0;
        while (i <= mid && j <= right) {
            if ((long) nums[i] <= (long) 2 * nums[j]) {
                ++i;
            } else {
                res += (mid - i + 1);
                ++j;
            }
        }
        i = left;
        j = mid + 1;
        while (i <= mid && j <= right) {
            if (nums[i] <= nums[j]) {
                tmp[k++] = nums[i++];
            } else {
                tmp[k++] = nums[j++];
            }
        }
        while (i <= mid) {
            tmp[k++] = nums[i++];
        }
        while (j <= right) {
            tmp[k++] = nums[j++];
        }
        for (i = left; i <= right; ++i) {
            nums[i] = tmp[i - left];
        }
        return res;
    }
}
```

树状数组：

```java
class Solution {
    public int reversePairs(int[] nums) {
        TreeSet<Long> ts = new TreeSet<>();
        for (int num : nums) {
            ts.add((long) num);
            ts.add((long) num * 2);
        }
        Map<Long, Integer> m = new HashMap<>();
        int idx = 0;
        for (long num : ts) {
            m.put(num, ++idx);
        }
        BinaryIndexedTree tree = new BinaryIndexedTree(m.size());
        int ans = 0;
        for (int i = nums.length - 1; i >= 0; --i) {
            int x = m.get((long) nums[i]);
            ans += tree.query(x - 1);
            tree.update(m.get((long) nums[i] * 2), 1);
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

    public void update(int x, int delta) {
        while (x <= n) {
            c[x] += delta;
            x += lowbit(x);
        }
    }

    public int query(int x) {
        int s = 0;
        while (x > 0) {
            s += c[x];
            x -= lowbit(x);
        }
        return s;
    }

    public static int lowbit(int x) {
        return x & -x;
    }
}
```

### **C++**

归并排序：

```cpp
class Solution {
public:
    int reversePairs(vector<int>& nums) {
        int n = nums.size();
        vector<int> temp(n);
        return mergeSort(nums, temp, 0, n - 1);
    }

private:
    int mergeSort(vector<int>& nums, vector<int>& temp, int l, int r) {
        if (l >= r) {
            return 0;
        }
        int m = l + r >> 1;
        int count = mergeSort(nums, temp, l, m) + mergeSort(nums, temp, m + 1, r);
        int i = l, j = m + 1, k = l;
        while (i <= m && j <= r) {
            if ((long long) nums[i] <= (long long) 2 * nums[j]) {
                ++i;
            } else {
                count += (m - i + 1);
                ++j;
            }
        }
        i = l;
        j = m + 1;
        while (i <= m || j <= r) {
            if (i > m) {
                temp[k++] = nums[j++];
            } else if (j > r || nums[i] <= nums[j]) {
                temp[k++] = nums[i++];
            } else {
                temp[k++] = nums[j++];
            }
        }
        copy(temp.begin() + l, temp.begin() + r + 1, nums.begin() + l);
        return count;
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

    void update(int x, int delta) {
        while (x <= n)
        {
            c[x] += delta;
            x += lowbit(x);
        }
    }

    int query(int x) {
        int s = 0;
        while (x > 0)
        {
            s += c[x];
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
    int reversePairs(vector<int>& nums) {
        set<long long> s;
        for (int num : nums)
        {
            s.insert(num);
            s.insert(num * 2ll);
        }
        unordered_map<long long, int> m;
        int idx = 0;
        for (long long num : s) m[num] = ++idx;
        BinaryIndexedTree* tree = new BinaryIndexedTree(m.size());
        int ans = 0;
        for (int i = nums.size() - 1; i >= 0; --i)
        {
            ans += tree->query(m[nums[i]] - 1);
            tree->update(m[nums[i] * 2ll], 1);
        }
        return ans;
    }
};
```

### **Go**

归并排序：

```go
func reversePairs(nums []int) int {
	return mergeSort(nums, 0, len(nums)-1)
}

func mergeSort(nums []int, left, right int) int {
	if left >= right {
		return 0
	}
	mid := (left + right) >> 1
	res := mergeSort(nums, left, mid) + mergeSort(nums, mid+1, right)
	i, j := left, mid+1
	for i <= mid && j <= right {
		if nums[i] <= 2*nums[j] {
			i++
		} else {
			res += (mid - i + 1)
			j++
		}
	}
	i, j = left, mid+1
	var tmp []int
	for i <= mid && j <= right {
		if nums[i] <= nums[j] {
			tmp = append(tmp, nums[i])
			i++
		} else {
			tmp = append(tmp, nums[j])
			j++
		}
	}
	for i <= mid {
		tmp = append(tmp, nums[i])
		i++
	}
	for j <= right {
		tmp = append(tmp, nums[j])
		j++
	}
	for i = left; i <= right; i++ {
		nums[i] = tmp[i-left]
	}
	return res
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

func (this *BinaryIndexedTree) update(x, delta int) {
	for x <= this.n {
		this.c[x] += delta
		x += this.lowbit(x)
	}
}

func (this *BinaryIndexedTree) query(x int) int {
	s := 0
	for x > 0 {
		s += this.c[x]
		x -= this.lowbit(x)
	}
	return s
}

func reversePairs(nums []int) int {
	s := make(map[int]bool)
	for _, num := range nums {
		s[num] = true
		s[num*2] = true
	}
	var alls []int
	for num := range s {
		alls = append(alls, num)
	}
	sort.Ints(alls)
	m := make(map[int]int)
	for i, num := range alls {
		m[num] = i + 1
	}
	tree := newBinaryIndexedTree(len(m))
	ans := 0
	for i := len(nums) - 1; i >= 0; i-- {
		ans += tree.query(m[nums[i]] - 1)
		tree.update(m[nums[i]*2], 1)
	}
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
