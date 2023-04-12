# [1395. 统计作战单位数](https://leetcode.cn/problems/count-number-of-teams)

[English Version](/solution/1300-1399/1395.Count%20Number%20of%20Teams/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p> <code>n</code> 名士兵站成一排。每个士兵都有一个 <strong>独一无二</strong> 的评分 <code>rating</code> 。</p>

<p>每 <strong>3</strong> 个士兵可以组成一个作战单位，分组规则如下：</p>

<ul>
	<li>从队伍中选出下标分别为 <code>i</code>、<code>j</code>、<code>k</code> 的 3 名士兵，他们的评分分别为 <code>rating[i]</code>、<code>rating[j]</code>、<code>rating[k]</code></li>
	<li>作战单位需满足： <code>rating[i] < rating[j] < rating[k]</code> 或者 <code>rating[i] > rating[j] > rating[k]</code> ，其中  <code>0 <= i < j < k < n</code></li>
</ul>

<p>请你返回按上述条件可以组建的作战单位数量。每个士兵都可以是多个作战单位的一部分。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>rating = [2,5,3,4,1]
<strong>输出：</strong>3
<strong>解释：</strong>我们可以组建三个作战单位 (2,3,4)、(5,4,1)、(5,3,1) 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>rating = [2,1,3]
<strong>输出：</strong>0
<strong>解释：</strong>根据题目条件，我们无法组建作战单位。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>rating = [1,2,3,4]
<strong>输出：</strong>4
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == rating.length</code></li>
	<li><code>3 <= n <= 1000</code></li>
	<li><code>1 <= rating[i] <= 10^5</code></li>
	<li><code>rating</code> 中的元素都是唯一的</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：枚举中间元素**

我们可以枚举数组 $rating$ 中每个元素 $rating[i]$ 作为中间元素，然后统计左边比它小的元素的个数 $l$，右边比它大的元素的个数 $r$，那么以该元素为中间元素的作战单位的个数为 $l \times r + (i - l) \times (n - i - 1 - r)$，累加到答案中即可。

时间复杂度 $O(n^2)$，空间复杂度 $O(1)$。其中 $n$ 为数组 $rating$ 的长度。

**方法二：树状数组**

我们可以用两个树状数组分别维护数组 $rating$ 中每个元素的左边比它小的元素的个数 $l$，右边比它大的元素的个数 $r$，然后统计以该元素为中间元素的作战单位的个数为 $l \times r + (i - l) \times (n - i - 1 - r)$，累加到答案中即可。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(n)$。其中 $n$ 为数组 $rating$ 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def numTeams(self, rating: List[int]) -> int:
        ans, n = 0, len(rating)
        for i, b in enumerate(rating):
            l = sum(a < b for a in rating[:i])
            r = sum(c > b for c in rating[i + 1:])
            ans += l * r
            ans += (i - l) * (n - i - 1 - r)
        return ans
```

```python
class BinaryIndexedTree:
    def __init__(self, n: int):
        self.n = n
        self.c = [0] * (n + 1)

    def update(self, x: int, v: int):
        while x <= self.n:
            self.c[x] += v
            x += x & -x

    def query(self, x: int) -> int:
        s = 0
        while x:
            s += self.c[x]
            x -= x & -x
        return s


class Solution:
    def numTeams(self, rating: List[int]) -> int:
        nums = sorted(set(rating))
        m = len(nums)
        tree1 = BinaryIndexedTree(m)
        tree2 = BinaryIndexedTree(m)
        for v in rating:
            x = bisect_left(nums, v) + 1
            tree2.update(x, 1)
        n = len(rating)
        ans = 0
        for i, v in enumerate(rating):
            x = bisect_left(nums, v) + 1
            tree1.update(x, 1)
            tree2.update(x, -1)
            l = tree1.query(x - 1)
            r = n - i - 1 - tree2.query(x)
            ans += l * r
            ans += (i - l) * (n - i - 1 - r)
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int numTeams(int[] rating) {
        int n = rating.length;
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            int l = 0, r = 0;
            for (int j = 0; j < i; ++j) {
                if (rating[j] < rating[i]) {
                    ++l;
                }
            }
            for (int j = i + 1; j < n; ++j) {
                if (rating[j] > rating[i]) {
                    ++r;
                }
            }
            ans += l * r;
            ans += (i - l) * (n - i - 1 - r);
        }
        return ans;
    }
}
```

```java
class BinaryIndexedTree {
    private int n;
    private int[] c;

    public BinaryIndexedTree(int n) {
        this.n = n;
        this.c = new int[n + 1];
    }

    public void update(int x, int v) {
        while (x <= n) {
            c[x] += v;
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

class Solution {
    public int numTeams(int[] rating) {
        int n = rating.length;
        int[] nums = rating.clone();
        Arrays.sort(nums);
        int m = 0;
        for (int i = 0; i < n; ++i) {
            if (i == 0 || nums[i] != nums[i - 1]) {
                nums[m++] = nums[i];
            }
        }
        BinaryIndexedTree tree1 = new BinaryIndexedTree(m);
        BinaryIndexedTree tree2 = new BinaryIndexedTree(m);
        for (int v : rating) {
            int x = search(nums, v);
            tree2.update(x, 1);
        }

        int ans = 0;
        for (int i = 0; i < n; ++i) {
            int x = search(nums, rating[i]);
            tree1.update(x, 1);
            tree2.update(x, -1);
            int l = tree1.query(x - 1);
            int r = n - i - 1 - tree2.query(x);
            ans += l * r;
            ans += (i - l) * (n - i - 1 - r);
        }
        return ans;
    }

    private int search(int[] nums, int x) {
        int l = 0, r = nums.length;
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
```

### **C++**

```cpp
class Solution {
public:
    int numTeams(vector<int>& rating) {
        int n = rating.size();
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            int l = 0, r = 0;
            for (int j = 0; j < i; ++j) {
                if (rating[j] < rating[i]) {
                    ++l;
                }
            }
            for (int j = i + 1; j < n; ++j) {
                if (rating[j] > rating[i]) {
                    ++r;
                }
            }
            ans += l * r;
            ans += (i - l) * (n - i - 1 - r);
        }
        return ans;
    }
};
```

```cpp
class BinaryIndexedTree {
public:
    BinaryIndexedTree(int _n)
        : n(_n)
        , c(_n + 1) {}

    void update(int x, int delta) {
        while (x <= n) {
            c[x] += delta;
            x += x & -x;
        }
    }

    int query(int x) {
        int s = 0;
        while (x) {
            s += c[x];
            x -= x & -x;
        }
        return s;
    }

private:
    int n;
    vector<int> c;
};

class Solution {
public:
    int numTeams(vector<int>& rating) {
        vector<int> nums = rating;
        sort(nums.begin(), nums.end());
        nums.erase(unique(nums.begin(), nums.end()), nums.end());
        int m = nums.size();
        BinaryIndexedTree tree1(m);
        BinaryIndexedTree tree2(m);
        for (int& v : rating) {
            int x = lower_bound(nums.begin(), nums.end(), v) - nums.begin() + 1;
            tree2.update(x, 1);
        }
        int ans = 0;
        int n = rating.size();
        for (int i = 0; i < n; ++i) {
            int x = lower_bound(nums.begin(), nums.end(), rating[i]) - nums.begin() + 1;
            tree1.update(x, 1);
            tree2.update(x, -1);
            int l = tree1.query(x - 1);
            int r = n - i - 1 - tree2.query(x);
            ans += l * r;
            ans += (i - l) * (n - i - 1 - r);
        }
        return ans;
    }
};
```

### **Go**

```go
func numTeams(rating []int) (ans int) {
	n := len(rating)
	for i, b := range rating {
		l, r := 0, 0
		for _, a := range rating[:i] {
			if a < b {
				l++
			}
		}
		for _, c := range rating[i+1:] {
			if c < b {
				r++
			}
		}
		ans += l * r
		ans += (i - l) * (n - i - 1 - r)
	}
	return
}
```

```go
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

func numTeams(rating []int) (ans int) {
	nums := make([]int, len(rating))
	copy(nums, rating)
	sort.Ints(nums)
	m := 0
	for i, x := range nums {
		if i == 0 || x != nums[i-1] {
			nums[m] = x
			m++
		}
	}
	nums = nums[:m]
	tree1 := newBinaryIndexedTree(m)
	tree2 := newBinaryIndexedTree(m)
	for _, x := range rating {
		tree2.update(sort.SearchInts(nums, x)+1, 1)
	}
	n := len(rating)
	for i, v := range rating {
		x := sort.SearchInts(nums, v) + 1
		tree1.update(x, 1)
		tree2.update(x, -1)
		l := tree1.query(x - 1)
		r := n - i - 1 - tree2.query(x)
		ans += l * r
		ans += (i - l) * (n - i - 1 - r)
	}
	return
}
```

### **TypeScript**

```ts
function numTeams(rating: number[]): number {
    let ans = 0;
    const n = rating.length;
    for (let i = 0; i < n; ++i) {
        let l = 0;
        let r = 0;
        for (let j = 0; j < i; ++j) {
            if (rating[j] < rating[i]) {
                ++l;
            }
        }
        for (let j = i + 1; j < n; ++j) {
            if (rating[j] > rating[i]) {
                ++r;
            }
        }
        ans += l * r;
        ans += (i - l) * (n - i - 1 - r);
    }
    return ans;
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

    public update(x: number, v: number): void {
        while (x <= this.n) {
            this.c[x] += v;
            x += x & -x;
        }
    }

    public query(x: number): number {
        let s = 0;
        while (x > 0) {
            s += this.c[x];
            x -= x & -x;
        }
        return s;
    }
}

function numTeams(rating: number[]): number {
    let nums = [...rating];
    nums.sort((a, b) => a - b);
    const n = rating.length;
    let m = 0;
    for (let i = 0; i < n; ++i) {
        if (i === 0 || nums[i] !== nums[i - 1]) {
            nums[m++] = nums[i];
        }
    }
    nums = nums.slice(0, m);
    const search = (x: number): number => {
        let l = 0;
        let r = m;
        while (l < r) {
            const mid = (l + r) >> 1;
            if (nums[mid] >= x) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l + 1;
    };
    let ans = 0;
    const tree1 = new BinaryIndexedTree(m);
    const tree2 = new BinaryIndexedTree(m);
    for (const x of rating) {
        tree2.update(search(x), 1);
    }
    for (let i = 0; i < n; ++i) {
        const x = search(rating[i]);
        tree1.update(x, 1);
        tree2.update(x, -1);
        const l = tree1.query(x - 1);
        const r = n - i - 1 - tree2.query(x);
        ans += l * r;
        ans += (i - l) * (n - i - 1 - r);
    }
    return ans;
}
```

### **...**

```

```

<!-- tabs:end -->
