# [3072. Distribute Elements Into Two Arrays II](https://leetcode.com/problems/distribute-elements-into-two-arrays-ii)

[中文文档](/solution/3000-3099/3072.Distribute%20Elements%20Into%20Two%20Arrays%20II/README.md)

<!-- tags: -->

## Description

<p>You are given a <strong>1-indexed</strong> array of integers <code>nums</code> of length <code>n</code>.</p>

<p>We define a function <code>greaterCount</code> such that <code>greaterCount(arr, val)</code> returns the number of elements in <code>arr</code> that are <strong>strictly greater</strong> than <code>val</code>.</p>

<p>You need to distribute all the elements of <code>nums</code> between two arrays <code>arr1</code> and <code>arr2</code> using <code>n</code> operations. In the first operation, append <code>nums[1]</code> to <code>arr1</code>. In the second operation, append <code>nums[2]</code> to <code>arr2</code>. Afterwards, in the <code>i<sup>th</sup></code> operation:</p>

<ul>
	<li>If <code>greaterCount(arr1, nums[i]) &gt; greaterCount(arr2, nums[i])</code>, append <code>nums[i]</code> to <code>arr1</code>.</li>
	<li>If <code>greaterCount(arr1, nums[i]) &lt; greaterCount(arr2, nums[i])</code>, append <code>nums[i]</code> to <code>arr2</code>.</li>
	<li>If <code>greaterCount(arr1, nums[i]) == greaterCount(arr2, nums[i])</code>, append <code>nums[i]</code> to the array with a <strong>lesser</strong> number of elements.</li>
	<li>If there is still a tie, append <code>nums[i]</code> to <code>arr1</code>.</li>
</ul>

<p>The array <code>result</code> is formed by concatenating the arrays <code>arr1</code> and <code>arr2</code>. For example, if <code>arr1 == [1,2,3]</code> and <code>arr2 == [4,5,6]</code>, then <code>result = [1,2,3,4,5,6]</code>.</p>

<p>Return <em>the integer array</em> <code>result</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [2,1,3,3]
<strong>Output:</strong> [2,3,1,3]
<strong>Explanation:</strong> After the first 2 operations, arr1 = [2] and arr2 = [1].
In the 3<sup>rd</sup> operation, the number of elements greater than 3 is zero in both arrays. Also, the lengths are equal, hence, append nums[3] to arr1.
In the 4<sup>th</sup> operation, the number of elements greater than 3 is zero in both arrays. As the length of arr2 is lesser, hence, append nums[4] to arr2.
After 4 operations, arr1 = [2,3] and arr2 = [1,3].
Hence, the array result formed by concatenation is [2,3,1,3].
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [5,14,3,1,2]
<strong>Output:</strong> [5,3,1,2,14]
<strong>Explanation:</strong> After the first 2 operations, arr1 = [5] and arr2 = [14].
In the 3<sup>rd</sup> operation, the number of elements greater than 3 is one in both arrays. Also, the lengths are equal, hence, append nums[3] to arr1.
In the 4<sup>th</sup> operation, the number of elements greater than 1 is greater in arr1 than arr2 (2 &gt; 1). Hence, append nums[4] to arr1.
In the 5<sup>th</sup> operation, the number of elements greater than 2 is greater in arr1 than arr2 (2 &gt; 1). Hence, append nums[5] to arr1.
After 5 operations, arr1 = [5,3,1,2] and arr2 = [14].
Hence, the array result formed by concatenation is [5,3,1,2,14].
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [3,3,3,3]
<strong>Output:</strong> [3,3,3,3]
<strong>Explanation:</strong> At the end of 4 operations, arr1 = [3,3] and arr2 = [3,3].
Hence, the array result formed by concatenation is [3,3,3,3].
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>3 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
</ul>

## Solutions

### Solution 1: Discretization + Binary Indexed Tree

We can use two binary indexed trees `tree1` and `tree2` to maintain the number of elements in `arr1` and `arr2` that are less than or equal to a certain number. Each time, we query the number of elements that are less than or equal to the current number in the binary indexed tree, then the number of elements that are greater than the current number is the length of the current array minus the query result. Then we can decide which array to add the current number to based on this difference.

Since the range of numbers given in the problem is very large, we need to discretize these numbers. We can sort these numbers and remove duplicates, then use binary search to find the position of each number in the sorted array.

The time complexity is $O(n \times \log n)$, and the space complexity is $O(n)$, where $n$ is the length of the array `nums`.

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

<!-- tabs:end -->

<!-- end -->
