---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3900-3999/3907.Count%20Smaller%20Elements%20With%20Opposite%20Parity/README.md
---

<!-- problem:start -->

# [3907. 统计具有相反奇偶性的较小元素 🔒](https://leetcode.cn/problems/count-smaller-elements-with-opposite-parity)

[English Version](/solution/3900-3999/3907.Count%20Smaller%20Elements%20With%20Opposite%20Parity/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定一个长度为 <code>n</code> 的整数数组&nbsp;<code>nums</code>。</p>

<p>下标 <code>i</code> 的得分定义为满足以下条件的索引 <code>j</code> 的数量：</p>

<ul>
	<li><code>i &lt; j &lt; n</code></li>
	<li><code>nums[j] &lt; nums[i]</code></li>
	<li><code>nums[i]</code> 和&nbsp;<code>nums[j]</code>&nbsp;有不同的奇偶性（一个是偶数，另一个是奇数）。</li>
</ul>

<p>返回一个长度为 <code>n</code> 的整数数组&nbsp;<code>answer</code>，其中&nbsp;<code>answer[i]</code>&nbsp;是下标&nbsp;<code>i</code>&nbsp;的得分。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>nums = [5,2,4,1,3]</span></p>

<p><span class="example-io"><b>输出：</b>[2,1,2,0,0]</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li>对于&nbsp;<code>i = 0</code>，元素&nbsp;<code>nums[1] = 2</code> 和&nbsp;<code>nums[2] = 4</code>&nbsp;更小且奇偶性不同。</li>
	<li>对于 <code>i = 1</code>，元素 <code>nums[3] = 1</code>&nbsp;更小且奇偶性不同。</li>
	<li>对于 <code>i = 2</code>，元素 <code>nums[3] = 1</code> 和&nbsp;<code>nums[4] = 3</code> 更小且奇偶性不同。</li>
	<li>剩余下标无有效元素。</li>
</ul>

<p>因此，<code>answer = [2, 1, 2, 0, 0]</code>。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>nums = [4,4,1]</span></p>

<p><span class="example-io"><b>输出：</b>[1,1,0]</span></p>

<p><strong>解释：</strong></p>

<p>对于&nbsp;<code>i = 0</code> 和&nbsp;<code>i = 1</code>，元素&nbsp;<code>nums[2] = 1</code>&nbsp;更小且奇偶性不同。因此，<code>answer = [1, 1, 0]</code>。</p>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>nums = [7]</span></p>

<p><span class="example-io"><b>输出：</b>[0]</span></p>

<p><strong>解释：</strong></p>

<p>没有元素在下标 0 右侧，因此其得分为 0。因此，<code>answer = [0]</code>。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：有序列表或树状数组

我们可以使用两个有序列表（或树状数组）分别维护偶数和奇数的元素。对于每个元素，我们查询另一个列表中比它小的元素的数量，并将当前元素添加到对应的列表中。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(n)$。其中 $n$ 是数组的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def countSmallerOppositeParity(self, nums: list[int]) -> list[int]:
        n = len(nums)
        ans = [0] * n
        sl = [SortedList(), SortedList()]
        for i in range(n - 1, -1, -1):
            ans[i] = sl[nums[i] & 1 ^ 1].bisect_left(nums[i])
            sl[nums[i] & 1].add(nums[i])
        return ans
```

#### Java

```java
class BinaryIndexedTree {
    int n;
    int[] c;

    BinaryIndexedTree(int n) {
        this.n = n;
        this.c = new int[n + 1];
    }

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
}

class Solution {
    public int[] countSmallerOppositeParity(int[] nums) {
        int n = nums.length;
        int[] sorted = nums.clone();
        Arrays.sort(sorted);
        int m = 0;
        for (int i = 0; i < n; i++) {
            if (i == 0 || sorted[i] != sorted[i - 1]) {
                sorted[m++] = sorted[i];
            }
        }
        BinaryIndexedTree[] bit = {new BinaryIndexedTree(m), new BinaryIndexedTree(m)};
        int[] ans = new int[n];
        for (int i = n - 1; i >= 0; --i) {
            int x = Arrays.binarySearch(sorted, 0, m, nums[i]) + 1;
            ans[i] = bit[nums[i] & 1 ^ 1].query(x - 1);
            bit[nums[i] & 1].update(x, 1);
        }
        return ans;
    }
}
```

#### C++

```cpp
struct BIT {
    int n;
    vector<int> c;
    BIT(int n)
        : n(n)
        , c(n + 1, 0) {}
    void update(int x, int delta) {
        for (; x <= n; x += x & -x) c[x] += delta;
    }
    int query(int x) {
        int s = 0;
        for (; x > 0; x -= x & -x) s += c[x];
        return s;
    }
};

class Solution {
public:
    vector<int> countSmallerOppositeParity(vector<int>& nums) {
        int n = nums.size();
        vector<int> sorted = nums;
        sort(sorted.begin(), sorted.end());
        sorted.erase(unique(sorted.begin(), sorted.end()), sorted.end());

        int m = sorted.size();
        BIT* bits[2] = {new BIT(m), new BIT(m)};
        vector<int> ans(n);

        for (int i = n - 1; i >= 0; --i) {
            int x = lower_bound(sorted.begin(), sorted.end(), nums[i]) - sorted.begin() + 1;
            ans[i] = bits[nums[i] & 1 ^ 1]->query(x - 1);
            bits[nums[i] & 1]->update(x, 1);
        }
        return ans;
    }
};
```

#### Go

```go
type BIT struct {
	n int
	c []int
}

func newBIT(n int) *BIT {
	return &BIT{n: n, c: make([]int, n+1)}
}

func (b *BIT) update(x, delta int) {
	for ; x <= b.n; x += x & -x {
		b.c[x] += delta
	}
}

func (b *BIT) query(x int) int {
	s := 0
	for ; x > 0; x -= x & -x {
		s += b.c[x]
	}
	return s
}

func countSmallerOppositeParity(nums []int) []int {
	n := len(nums)
	sorted := make([]int, n)
	copy(sorted, nums)
	sort.Ints(sorted)

	m := 0
	if n > 0 {
		m = 1
		for i := 1; i < n; i++ {
			if sorted[i] != sorted[i-1] {
				sorted[m] = sorted[i]
				m++
			}
		}
		sorted = sorted[:m]
	}

	bits := []*BIT{newBIT(m), newBIT(m)}
	ans := make([]int, n)

	for i := n - 1; i >= 0; i-- {
		x := sort.SearchInts(sorted, nums[i]) + 1
		ans[i] = bits[nums[i]&1^1].query(x - 1)
		bits[nums[i]&1].update(x, 1)
	}
	return ans
}
```

#### TypeScript

```ts
class BIT {
    private c: Int32Array;
    constructor(private n: number) {
        this.c = new Int32Array(n + 1);
    }
    update(x: number, delta: number) {
        for (; x <= this.n; x += x & -x) this.c[x] += delta;
    }
    query(x: number): number {
        let s = 0;
        for (; x > 0; x -= x & -x) s += this.c[x];
        return s;
    }
}

function countSmallerOppositeParity(nums: number[]): number[] {
    const n = nums.length;
    const sorted = _.sortedUniq(_.sortBy(nums));
    const m = sorted.length;

    const bits = [new BIT(m), new BIT(m)];
    const ans = new Array(n);

    for (let i = n - 1; i >= 0; i--) {
        const rank = _.sortedIndex(sorted, nums[i]) + 1;
        ans[i] = bits[(nums[i] & 1) ^ 1].query(rank - 1);
        bits[nums[i] & 1].update(rank, 1);
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
