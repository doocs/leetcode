# [面试题 51. 数组中的逆序对](https://leetcode.cn/problems/shu-zu-zhong-de-ni-xu-dui-lcof/)

## 题目描述

<!-- 这里写题目描述 -->

<p>在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。输入一个数组，求出这个数组中的逆序对的总数。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre><strong>输入</strong>: [7,5,6,4]
<strong>输出</strong>: 5</pre>

<p>&nbsp;</p>

<p><strong>限制：</strong></p>

<p><code>0 &lt;= 数组长度 &lt;= 50000</code></p>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：归并排序**

归并排序的过程中，如果左边的数大于右边的数，则右边的数与左边的数之后的数都构成逆序对。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(n)$。其中 $n$ 为数组长度。

**方法二：树状数组**

树状数组，也称作“二叉索引树”（Binary Indexed Tree）或 Fenwick 树。 它可以高效地实现如下两个操作：

1. **单点更新** `update(x, delta)`： 把序列 x 位置的数加上一个值 delta；
1. **前缀和查询** `query(x)`：查询序列 `[1,...x]` 区间的区间和，即位置 x 的前缀和。

这两个操作的时间复杂度均为 $O(\log n)$。

树状数组最基本的功能就是求比某点 x 小的点的个数（这里的比较是抽象的概念，可以是数的大小、坐标的大小、质量的大小等等）。

比如给定数组 `a[5] = {2, 5, 3, 4, 1}`，求 `b[i] = 位置 i 左边小于等于 a[i] 的数的个数`。对于此例，`b[5] = {0, 1, 1, 2, 0}`。

解决方案是直接遍历数组，每个位置先求出 `query(a[i])`，然后再修改树状数组 `update(a[i], 1)` 即可。当数的范围比较大时，需要进行离散化，即先进行去重并排序，然后对每个数字进行编号。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def reversePairs(self, nums: List[int]) -> int:
        def merge_sort(l, r):
            if l >= r:
                return 0
            mid = (l + r) >> 1
            ans = merge_sort(l, mid) + merge_sort(mid + 1, r)
            t = []
            i, j = l, mid + 1
            while i <= mid and j <= r:
                if nums[i] <= nums[j]:
                    t.append(nums[i])
                    i += 1
                else:
                    ans += mid - i + 1
                    t.append(nums[j])
                    j += 1
            t.extend(nums[i : mid + 1])
            t.extend(nums[j : r + 1])
            nums[l : r + 1] = t
            return ans

        return merge_sort(0, len(nums) - 1)
```

```python
class BinaryIndexedTree:
    def __init__(self, n):
        self.n = n
        self.c = [0] * (n + 1)

    def update(self, x, delta):
        while x <= self.n:
            self.c[x] += delta
            x += x & -x

    def query(self, x):
        s = 0
        while x:
            s += self.c[x]
            x -= x & -x
        return s

class Solution:
    def reversePairs(self, nums: List[int]) -> int:
        alls = sorted(set(nums))
        m = len(alls)
        tree = BinaryIndexedTree(m)
        ans = 0
        for v in nums[::-1]:
            x = bisect_left(alls, v) + 1
            ans += tree.query(x - 1)
            tree.update(x, 1)
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    private int[] nums;
    private int[] t;

    public int reversePairs(int[] nums) {
        this.nums = nums;
        int n = nums.length;
        this.t = new int[n];
        return mergeSort(0, n - 1);
    }

    private int mergeSort(int l, int r) {
        if (l >= r) {
            return 0;
        }
        int mid = (l + r) >> 1;
        int ans = mergeSort(l, mid) + mergeSort(mid + 1, r);
        int i = l, j = mid + 1, k = 0;
        while (i <= mid && j <= r) {
            if (nums[i] <= nums[j]) {
                t[k++] = nums[i++];
            } else {
                ans += mid - i + 1;
                t[k++] = nums[j++];
            }
        }
        while (i <= mid) {
            t[k++] = nums[i++];
        }
        while (j <= r) {
            t[k++] = nums[j++];
        }
        for (i = l; i <= r; ++i) {
            nums[i] = t[i - l];
        }
        return ans;
    }
}
```

```java
class Solution {
    public int reversePairs(int[] nums) {
        Set<Integer> s = new TreeSet<>();
        for (int v : nums) {
            s.add(v);
        }
        Map<Integer, Integer> alls = new HashMap<>();
        int i = 1;
        for (int v : s) {
            alls.put(v, i++);
        }
        BinaryIndexedTree tree = new BinaryIndexedTree(s.size());
        int ans = 0;
        for (i = nums.length - 1; i >= 0; --i) {
            int x = alls.get(nums[i]);
            ans += tree.query(x - 1);
            tree.update(x, 1);
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
            x += x & -x;
        }
    }

    public int query(int x) {
        int s = 0;
        while (x > 0) {
            s += c[x];
            x -= x & -x;
        }
        return s;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int reversePairs(vector<int>& nums) {
        int n = nums.size();
        if (n == 0) {
            return 0;
        }
        int t[n];
        function<int(int, int)> mergeSort = [&](int l, int r) -> int {
            if (l >= r) {
                return 0;
            }
            int mid = (l + r) >> 1;
            int ans = mergeSort(l, mid) + mergeSort(mid + 1, r);
            int i = l, j = mid + 1, k = 0;
            while (i <= mid && j <= r) {
                if (nums[i] <= nums[j]) {
                    t[k++] = nums[i++];
                } else {
                    ans += mid - i + 1;
                    t[k++] = nums[j++];
                }
            }
            while (i <= mid) {
                t[k++] = nums[i++];
            }
            while (j <= r) {
                t[k++] = nums[j++];
            }
            for (i = l; i <= r; ++i) {
                nums[i] = t[i - l];
            }
            return ans;
        };
        return mergeSort(0, n - 1);
    }
};
```

```cpp
class BinaryIndexedTree {
public:
    int n;
    vector<int> c;

    BinaryIndexedTree(int _n): n(_n), c(_n + 1){}

    void update(int x, int delta) {
        while (x <= n) {
            c[x] += delta;
            x += x & -x;
        }
    }

    int query(int x) {
        int s = 0;
        while (x > 0) {
            s += c[x];
            x -= x & -x;
        }
        return s;
    }
};


class Solution {
public:
    int reversePairs(vector<int>& nums) {
        vector<int> alls = nums;
        sort(alls.begin(), alls.end());
        alls.erase(unique(alls.begin(), alls.end()), alls.end());
        BinaryIndexedTree tree(alls.size());
        int ans = 0;
        for (int i = nums.size() - 1; ~i; --i) {
            int x = lower_bound(alls.begin(), alls.end(), nums[i]) - alls.begin() + 1;
            ans += tree.query(x - 1);
            tree.update(x, 1);
        }
        return ans;
    }
};
```

### **Go**

```go
func reversePairs(nums []int) int {
	n := len(nums)
	t := make([]int, n)
	var mergeSort func(l, r int) int
	mergeSort = func(l, r int) int {
		if l >= r {
			return 0
		}
		mid := (l + r) >> 1
		ans := mergeSort(l, mid) + mergeSort(mid+1, r)
		i, j, k := l, mid+1, 0
		for i <= mid && j <= r {
			if nums[i] <= nums[j] {
				t[k] = nums[i]
				k, i = k+1, i+1
			} else {
				ans += mid - i + 1
				t[k] = nums[j]
				k, j = k+1, j+1
			}
		}
		for ; i <= mid; i, k = i+1, k+1 {
			t[k] = nums[i]
		}
		for ; j <= r; j, k = j+1, k+1 {
			t[k] = nums[j]
		}
		for i = l; i <= r; i++ {
			nums[i] = t[i-l]
		}
		return ans
	}
	return mergeSort(0, n-1)
}
```

```go
func reversePairs(nums []int) (ans int) {
	s := map[int]bool{}
	for _, v := range nums {
		s[v] = true
	}
	alls := []int{}
	for v := range s {
		alls = append(alls, v)
	}
	sort.Ints(alls)
	tree := newBinaryIndexedTree(len(alls))
	for i := len(nums) - 1; i >= 0; i-- {
		x := sort.SearchInts(alls, nums[i]) + 1
		ans += tree.query(x - 1)
		tree.update(x, 1)
	}
	return
}

type BinaryIndexedTree struct {
	n int
	c []int
}

func newBinaryIndexedTree(n int) *BinaryIndexedTree {
	c := make([]int, n+1)
	return &BinaryIndexedTree{n, c}
}

func (this *BinaryIndexedTree) update(x, delta int) {
	for x <= this.n {
		this.c[x] += delta
		x += x & -x
	}
}

func (this *BinaryIndexedTree) query(x int) int {
	s := 0
	for x > 0 {
		s += this.c[x]
		x -= x & -x
	}
	return s
}
```

### **JavaScript**

```js
/**
 * @param {number[]} nums
 * @return {number}
 */
var reversePairs = function (nums) {
    const mergeSort = (l, r) => {
        if (l >= r) {
            return 0;
        }
        const mid = (l + r) >> 1;
        let ans = mergeSort(l, mid) + mergeSort(mid + 1, r);
        let i = l;
        let j = mid + 1;
        let t = [];
        while (i <= mid && j <= r) {
            if (nums[i] <= nums[j]) {
                t.push(nums[i++]);
            } else {
                ans += mid - i + 1;
                t.push(nums[j++]);
            }
        }
        while (i <= mid) {
            t.push(nums[i++]);
        }
        while (j <= r) {
            t.push(nums[j++]);
        }
        for (i = l; i <= r; ++i) {
            nums[i] = t[i - l];
        }
        return ans;
    };
    return mergeSort(0, nums.length - 1);
};
```

### **TypeScript**

```ts
function reversePairs(nums: number[]): number {
    const mergeSort = (l: number, r: number): number => {
        if (l >= r) {
            return 0;
        }
        const mid = (l + r) >> 1;
        let ans = mergeSort(l, mid) + mergeSort(mid + 1, r);
        let i = l;
        let j = mid + 1;
        const t: number[] = [];
        while (i <= mid && j <= r) {
            if (nums[i] <= nums[j]) {
                t.push(nums[i++]);
            } else {
                ans += mid - i + 1;
                t.push(nums[j++]);
            }
        }
        while (i <= mid) {
            t.push(nums[i++]);
        }
        while (j <= r) {
            t.push(nums[j++]);
        }
        for (i = l; i <= r; ++i) {
            nums[i] = t[i - l];
        }
        return ans;
    };
    return mergeSort(0, nums.length - 1);
}
```

### **C#**

```cs
public class Solution {
    private int[] nums;
    private int[] t;

    public int ReversePairs(int[] nums) {
        this.nums = nums;
        int n = nums.Length;
        this.t = new int[n];
        return mergeSort(0, n - 1);
    }

    private int mergeSort(int l, int r) {
        if (l >= r) {
            return 0;
        }
        int mid = (l + r) >> 1;
        int ans = mergeSort(l, mid) + mergeSort(mid + 1, r);
        int i = l, j = mid + 1, k = 0;
        while (i <= mid && j <= r) {
            if (nums[i] <= nums[j]) {
                t[k++] = nums[i++];
            } else {
                ans += mid - i + 1;
                t[k++] = nums[j++];
            }
        }
        while (i <= mid) {
            t[k++] = nums[i++];
        }
        while (j <= r) {
            t[k++] = nums[j++];
        }
        for (i = l; i <= r; ++i) {
            nums[i] = t[i - l];
        }
        return ans;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
