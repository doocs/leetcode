# [3072. 将元素分配到两个数组中 II](https://leetcode.cn/problems/distribute-elements-into-two-arrays-ii)

[English Version](/solution/3000-3099/3072.Distribute%20Elements%20Into%20Two%20Arrays%20II/README_EN.md)

<!-- tags:树状数组,线段树,数组,模拟 -->

<!-- difficulty:困难 -->

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个下标从 <strong>1</strong> 开始、长度为 <code>n</code> 的整数数组 <code>nums</code> 。</p>

<p>现定义函数 <code>greaterCount</code> ，使得 <code>greaterCount(arr, val)</code> 返回数组 <code>arr</code> 中<strong> 严格大于</strong> <code>val</code> 的元素数量。</p>

<p>你需要使用 <code>n</code> 次操作，将 <code>nums</code> 的所有元素分配到两个数组 <code>arr1</code> 和 <code>arr2</code> 中。在第一次操作中，将 <code>nums[1]</code> 追加到 <code>arr1</code> 。在第二次操作中，将 <code>nums[2]</code> 追加到 <code>arr2</code> 。之后，在第 <code>i</code> 次操作中：</p>

<ul>
	<li>如果 <code>greaterCount(arr1, nums[i]) &gt; greaterCount(arr2, nums[i])</code> ，将 <code>nums[i]</code> 追加到 <code>arr1</code> 。</li>
	<li>如果 <code>greaterCount(arr1, nums[i]) &lt; greaterCount(arr2, nums[i])</code> ，将 <code>nums[i]</code> 追加到 <code>arr2</code> 。</li>
	<li>如果 <code>greaterCount(arr1, nums[i]) == greaterCount(arr2, nums[i])</code> ，将 <code>nums[i]</code> 追加到元素数量较少的数组中。</li>
	<li>如果仍然相等，那么将 <code>nums[i]</code> 追加到 <code>arr1</code> 。</li>
</ul>

<p>连接数组 <code>arr1</code> 和 <code>arr2</code> 形成数组 <code>result</code> 。例如，如果 <code>arr1 == [1,2,3]</code> 且 <code>arr2 == [4,5,6]</code> ，那么 <code>result = [1,2,3,4,5,6]</code> 。</p>

<p>返回整数数组 <code>result</code> 。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [2,1,3,3]
<strong>输出：</strong>[2,3,1,3]
<strong>解释：</strong>在前两次操作后，arr1 = [2] ，arr2 = [1] 。
在第 3 次操作中，两个数组中大于 3 的元素数量都是零，并且长度相等，因此，将 nums[3] 追加到 arr1 。
在第 4 次操作中，两个数组中大于 3 的元素数量都是零，但 arr2 的长度较小，因此，将 nums[4] 追加到 arr2 。
在 4 次操作后，arr1 = [2,3] ，arr2 = [1,3] 。
因此，连接形成的数组 result 是 [2,3,1,3] 。
</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [5,14,3,1,2]
<strong>输出：</strong>[5,3,1,2,14]
<strong>解释：</strong>在前两次操作后，arr1 = [5] ，arr2 = [14] 。
在第 3 次操作中，两个数组中大于 3 的元素数量都是一，并且长度相等，因此，将 nums[3] 追加到 arr1 。
在第 4 次操作中，arr1 中大于 1 的元素数量大于 arr2 中的数量（2 &gt; 1），因此，将 nums[4] 追加到 arr1 。
在第 5 次操作中，arr1 中大于 2 的元素数量大于 arr2 中的数量（2 &gt; 1），因此，将 nums[5] 追加到 arr1 。
在 5 次操作后，arr1 = [5,3,1,2] ，arr2 = [14] 。
因此，连接形成的数组 result 是 [5,3,1,2,14] 。
</pre>

<p><strong class="example">示例 3：</strong></p>

<pre>
<strong>输入：</strong>nums = [3,3,3,3]
<strong>输出：</strong>[3,3,3,3]
<strong>解释：</strong>在 4 次操作后，arr1 = [3,3] ，arr2 = [3,3] 。
因此，连接形成的数组 result 是 [3,3,3,3] 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>3 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
</ul>

## 解法

### 方法一：离散化 + 树状数组

我们可以用两个树状数组 `tree1` 和 `tree2` 分别维护 `arr1` 和 `arr2` 中小于等于某个数的元素个数。每一次，我们在树状数组中查询小于等于当前数的元素个数，那么大于当前数的元素个数就是当前数组的长度减去查询的结果。然后我们就可以根据这个差值来决定将当前数加入到哪个数组中。

由于题目中给出的数的范围很大，所以我们需要对这些数进行离散化。我们可以将这些数排序后去重，然后用二分查找来找到每个数在排序后的数组中的位置。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(n)$。其中 $n$ 是数组 `nums` 的长度。

<!-- tabs:start -->

```python
class BinaryIndexedTree:
    __slots__ = "n", "c"

    def __init__(self, n: int):
        self.n = n
        self.c = [0] * (n + 1)

    def update(self, x: int, delta: int) -> None:
        while x <= self.n:
            self.c[x] += delta
            x += x & -x

    def query(self, x: int) -> int:
        s = 0
        while x:
            s += self.c[x]
            x -= x & -x
        return s


class Solution:
    def resultArray(self, nums: List[int]) -> List[int]:
        st = sorted(set(nums))
        m = len(st)
        tree1 = BinaryIndexedTree(m + 1)
        tree2 = BinaryIndexedTree(m + 1)
        tree1.update(bisect_left(st, nums[0]) + 1, 1)
        tree2.update(bisect_left(st, nums[1]) + 1, 1)
        arr1 = [nums[0]]
        arr2 = [nums[1]]
        for x in nums[2:]:
            i = bisect_left(st, x) + 1
            a = len(arr1) - tree1.query(i)
            b = len(arr2) - tree2.query(i)
            if a > b:
                arr1.append(x)
                tree1.update(i, 1)
            elif a < b:
                arr2.append(x)
                tree2.update(i, 1)
            elif len(arr1) <= len(arr2):
                arr1.append(x)
                tree1.update(i, 1)
            else:
                arr2.append(x)
                tree2.update(i, 1)
        return arr1 + arr2
```

```python
from sortedcontainers import SortedList


class Solution:
    def resultArray(self, nums: List[int]) -> List[int]:
        arr1 = [nums[0]]
        arr2 = [nums[1]]
        sl1 = SortedList(arr1)
        sl2 = SortedList(arr2)
        for x in nums[2:]:
            i = sl1.bisect_right(x)
            j = sl2.bisect_right(x)
            if len(sl1) - i > len(sl2) - j:
                arr1.append(x)
                sl1.add(x)
            elif len(sl1) - i < len(sl2) - j:
                arr2.append(x)
                sl2.add(x)
            elif len(sl1) <= len(sl2):
                arr1.append(x)
                sl1.add(x)
            else:
                arr2.append(x)
                sl2.add(x)
        return arr1 + arr2
```

```java
class BinaryIndexedTree {
    private int n;
    private int[] c;

    public BinaryIndexedTree(int n) {
        this.n = n;
        this.c = new int[n + 1];
    }

    public void update(int x, int delta) {
        for (; x <= n; x += x & -x) {
            c[x] += delta;
        }
    }

    public int query(int x) {
        int s = 0;
        for (; x > 0; x -= x & -x) {
            s += c[x];
        }
        return s;
    }
}

class Solution {
    public int[] resultArray(int[] nums) {
        int[] st = nums.clone();
        Arrays.sort(st);
        int n = st.length;
        BinaryIndexedTree tree1 = new BinaryIndexedTree(n + 1);
        BinaryIndexedTree tree2 = new BinaryIndexedTree(n + 1);
        tree1.update(Arrays.binarySearch(st, nums[0]) + 1, 1);
        tree2.update(Arrays.binarySearch(st, nums[1]) + 1, 1);
        int[] arr1 = new int[n];
        int[] arr2 = new int[n];
        arr1[0] = nums[0];
        arr2[0] = nums[1];
        int i = 1, j = 1;
        for (int k = 2; k < n; ++k) {
            int x = Arrays.binarySearch(st, nums[k]) + 1;
            int a = i - tree1.query(x);
            int b = j - tree2.query(x);
            if (a > b) {
                arr1[i++] = nums[k];
                tree1.update(x, 1);
            } else if (a < b) {
                arr2[j++] = nums[k];
                tree2.update(x, 1);
            } else if (i <= j) {
                arr1[i++] = nums[k];
                tree1.update(x, 1);
            } else {
                arr2[j++] = nums[k];
                tree2.update(x, 1);
            }
        }
        for (int k = 0; k < j; ++k) {
            arr1[i++] = arr2[k];
        }
        return arr1;
    }
}
```

```cpp
class BinaryIndexedTree {
private:
    int n;
    vector<int> c;

public:
    BinaryIndexedTree(int n)
        : n(n)
        , c(n + 1) {}

    void update(int x, int delta) {
        for (; x <= n; x += x & -x) {
            c[x] += delta;
        }
    }

    int query(int x) {
        int s = 0;
        for (; x > 0; x -= x & -x) {
            s += c[x];
        }
        return s;
    }
};

class Solution {
public:
    vector<int> resultArray(vector<int>& nums) {
        vector<int> st = nums;
        sort(st.begin(), st.end());
        int n = st.size();
        BinaryIndexedTree tree1(n + 1);
        BinaryIndexedTree tree2(n + 1);
        tree1.update(distance(st.begin(), lower_bound(st.begin(), st.end(), nums[0])) + 1, 1);
        tree2.update(distance(st.begin(), lower_bound(st.begin(), st.end(), nums[1])) + 1, 1);
        vector<int> arr1 = {nums[0]};
        vector<int> arr2 = {nums[1]};
        for (int k = 2; k < n; ++k) {
            int x = distance(st.begin(), lower_bound(st.begin(), st.end(), nums[k])) + 1;
            int a = arr1.size() - tree1.query(x);
            int b = arr2.size() - tree2.query(x);
            if (a > b) {
                arr1.push_back(nums[k]);
                tree1.update(x, 1);
            } else if (a < b) {
                arr2.push_back(nums[k]);
                tree2.update(x, 1);
            } else if (arr1.size() <= arr2.size()) {
                arr1.push_back(nums[k]);
                tree1.update(x, 1);
            } else {
                arr2.push_back(nums[k]);
                tree2.update(x, 1);
            }
        }
        arr1.insert(arr1.end(), arr2.begin(), arr2.end());
        return arr1;
    }
};
```

```go
type BinaryIndexedTree struct {
	n int
	c []int
}

func NewBinaryIndexedTree(n int) *BinaryIndexedTree {
	return &BinaryIndexedTree{n: n, c: make([]int, n+1)}
}

func (bit *BinaryIndexedTree) update(x, delta int) {
	for ; x <= bit.n; x += x & -x {
		bit.c[x] += delta
	}
}

func (bit *BinaryIndexedTree) query(x int) int {
	s := 0
	for ; x > 0; x -= x & -x {
		s += bit.c[x]
	}
	return s
}

func resultArray(nums []int) []int {
	st := make([]int, len(nums))
	copy(st, nums)
	sort.Ints(st)
	n := len(st)
	tree1 := NewBinaryIndexedTree(n + 1)
	tree2 := NewBinaryIndexedTree(n + 1)
	tree1.update(sort.SearchInts(st, nums[0])+1, 1)
	tree2.update(sort.SearchInts(st, nums[1])+1, 1)
	arr1 := []int{nums[0]}
	arr2 := []int{nums[1]}
	for _, x := range nums[2:] {
		i := sort.SearchInts(st, x) + 1
		a := len(arr1) - tree1.query(i)
		b := len(arr2) - tree2.query(i)
		if a > b {
			arr1 = append(arr1, x)
			tree1.update(i, 1)
		} else if a < b {
			arr2 = append(arr2, x)
			tree2.update(i, 1)
		} else if len(arr1) <= len(arr2) {
			arr1 = append(arr1, x)
			tree1.update(i, 1)
		} else {
			arr2 = append(arr2, x)
			tree2.update(i, 1)
		}
	}
	arr1 = append(arr1, arr2...)
	return arr1
}
```

```ts
class BinaryIndexedTree {
    private n: number;
    private c: number[];

    constructor(n: number) {
        this.n = n;
        this.c = Array(n + 1).fill(0);
    }

    update(x: number, delta: number): void {
        for (; x <= this.n; x += x & -x) {
            this.c[x] += delta;
        }
    }

    query(x: number): number {
        let s = 0;
        for (; x > 0; x -= x & -x) {
            s += this.c[x];
        }
        return s;
    }
}

function resultArray(nums: number[]): number[] {
    const st: number[] = nums.slice().sort((a, b) => a - b);
    const n: number = st.length;
    const search = (x: number): number => {
        let [l, r] = [0, n];
        while (l < r) {
            const mid = (l + r) >> 1;
            if (st[mid] >= x) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    };
    const tree1: BinaryIndexedTree = new BinaryIndexedTree(n + 1);
    const tree2: BinaryIndexedTree = new BinaryIndexedTree(n + 1);
    tree1.update(search(nums[0]) + 1, 1);
    tree2.update(search(nums[1]) + 1, 1);
    const arr1: number[] = [nums[0]];
    const arr2: number[] = [nums[1]];
    for (const x of nums.slice(2)) {
        const i: number = search(x) + 1;
        const a: number = arr1.length - tree1.query(i);
        const b: number = arr2.length - tree2.query(i);
        if (a > b) {
            arr1.push(x);
            tree1.update(i, 1);
        } else if (a < b) {
            arr2.push(x);
            tree2.update(i, 1);
        } else if (arr1.length <= arr2.length) {
            arr1.push(x);
            tree1.update(i, 1);
        } else {
            arr2.push(x);
            tree2.update(i, 1);
        }
    }
    return arr1.concat(arr2);
}
```

```php
class Solution {
    /**
     * @param String $s
     * @param String[] $words
     * @return Integer[]
     */
    function findSubstring($s, $words) {
        $cnt = [];
        foreach ($words as $w) {
            if (!isset($cnt[$w])) {
                $cnt[$w] = 1;
            } else {
                $cnt[$w]++;
            }
        }
        $m = strlen($s);
        $n = count($words);
        $k = strlen($words[0]);
        $ans = [];
        for ($i = 0; $i < $k; ++$i) {
            $cnt1 = [];
            $l = $i;
            $r = $i;
            $t = 0;
            while ($r + $k <= $m) {
                $w = substr($s, $r, $k);
                $r += $k;
                if (!array_key_exists($w, $cnt)) {
                    $cnt1 = [];
                    $l = $r;
                    $t = 0;
                    continue;
                }
                if (!isset($cnt1[$w])) {
                    $cnt1[$w] = 1;
                } else {
                    $cnt1[$w]++;
                }
                ++$t;
                while ($cnt1[$w] > $cnt[$w]) {
                    $remove = substr($s, $l, $k);
                    $l += $k;
                    $cnt1[$remove]--;
                    $t--;
                }
                if ($t == $n) {
                    $ans[] = $l;
                }
            }
        }
        return $ans;
    }
}
```

<!-- tabs:end -->

<!-- end -->
