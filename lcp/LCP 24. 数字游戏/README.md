---
comments: true
edit_url: https://github.com/doocs/leetcode/edit/main/lcp/LCP%2024.%20%E6%95%B0%E5%AD%97%E6%B8%B8%E6%88%8F/README.md
---

<!-- problem:start -->

# [LCP 24. 数字游戏](https://leetcode.cn/problems/5TxKeK)

## 题目描述

<!-- description:start -->

小扣在秋日市集入口处发现了一个数字游戏。主办方共有 `N` 个计数器，计数器编号为 `0 ~ N-1`。每个计数器上分别显示了一个数字，小扣按计数器编号升序将所显示的数字记于数组 `nums`。每个计数器上有两个按钮，分别可以实现将显示数字加一或减一。小扣每一次操作可以选择一个计数器，按下加一或减一按钮。

主办方请小扣回答出一个长度为 `N` 的数组，第 `i` 个元素(0 <= i < N)表示将 `0~i` 号计数器 **初始** 所示数字操作成满足所有条件 `nums[a]+1 == nums[a+1],(0 <= a < i)` 的最小操作数。回答正确方可进入秋日市集。

由于答案可能很大，请将每个最小操作数对 `1,000,000,007` 取余。

**示例 1：**

> 输入：`nums = [3,4,5,1,6,7]`
>
> 输出：`[0,0,0,5,6,7]`
>
> 解释：
> i = 0，[3] 无需操作
> i = 1，[3,4] 无需操作；
> i = 2，[3,4,5] 无需操作；
> i = 3，将 [3,4,5,1] 操作成 [3,4,5,6], 最少 5 次操作；
> i = 4，将 [3,4,5,1,6] 操作成 [3,4,5,6,7], 最少 6 次操作；
> i = 5，将 [3,4,5,1,6,7] 操作成 [3,4,5,6,7,8]，最少 7 次操作；
> 返回 [0,0,0,5,6,7]。

**示例 2：**

> 输入：`nums = [1,2,3,4,5]`
>
> 输出：`[0,0,0,0,0]`
>
> 解释：对于任意计数器编号 i 都无需操作。

**示例 3：**

> 输入：`nums = [1,1,1,2,3,4]`
>
> 输出：`[0,1,2,3,3,3]`
>
> 解释：
> i = 0，无需操作；
> i = 1，将 [1,1] 操作成 [1,2] 或 [0,1] 最少 1 次操作；
> i = 2，将 [1,1,1] 操作成 [1,2,3] 或 [0,1,2]，最少 2 次操作；
> i = 3，将 [1,1,1,2] 操作成 [1,2,3,4] 或 [0,1,2,3]，最少 3 次操作；
> i = 4，将 [1,1,1,2,3] 操作成 [-1,0,1,2,3]，最少 3 次操作；
> i = 5，将 [1,1,1,2,3,4] 操作成 [-1,0,1,2,3,4]，最少 3 次操作；
> 返回 [0,1,2,3,3,3]。

**提示：**

- `1 <= nums.length <= 10^5`
- `1 <= nums[i] <= 10^3`

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：优先队列（大小根堆）

我们不妨假设最终的数组元素为 $x, x+1, x+2, \cdots, x+n-1$，那么操作次数为 $|nums[0] - x| + |nums[1] - (x+1)| + \cdots + |nums[n-1] - (x+n-1)|$。我们不妨变换一下式子，得到 $|nums[0] - x| + |nums[1] - 1 - x| + \cdots + |nums[n-1] - (n-1) - x|$。

如果我们将 $nums[i] - i$ 作为第 $i$ 个元素 $nums[i]$，那么上述式子就变成了求 $|nums[0] - x| + |nums[1] - x| + \cdots + |nums[n-1] - x|$ 的最小值。这等价于求：把数组 $nums$ 操作成相同数字的最小操作次数，即把数组 $nums$ 操作成中位数的最小操作数。

我们可以用大小根堆，动态维护前缀数组的中位数。

具体地，我们创建大根堆、小根堆，其中：小根堆 $q1$ 存放较大的一半元素，大根堆 $q2$ 存放较小的一半元素。

添加元素时，先放入小根堆，然后将小根堆对顶元素弹出并放入大根堆（使得大根堆个数多 $1$）；若大小根堆元素个数差超过 $1$，则将大根堆元素弹出放入小根堆。过程中，用两个变量 $s1, s2$ 分别记录两个堆的元素和。

取中位数时，若大根堆元素较多，取大根堆堆顶，否则取两堆顶元素和的平均值。求出中位数 $x$ 后，当前最小操作次数为 $s1 - x \times |q1| + x \times |q2| - s2$。注意，我们需要对最小操作次数取模。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(n)$。其中 $n$ 为数组长度。

<!-- tabs:start -->

#### Python3

```python
class MedianFinder:
    def __init__(self):
        self.q1 = []
        self.q2 = []
        self.s1 = 0
        self.s2 = 0

    def addNum(self, num: int) -> None:
        heappush(self.q1, num)
        self.s1 += num
        num = heappop(self.q1)
        heappush(self.q2, -num)
        self.s1 -= num
        self.s2 += num
        if len(self.q2) - len(self.q1) > 1:
            num = -heappop(self.q2)
            heappush(self.q1, num)
            self.s1 += num
            self.s2 -= num

    def findMedian(self) -> int:
        if len(self.q2) > len(self.q1):
            return -self.q2[0]
        return (self.q1[0] - self.q2[0]) // 2

    def cal(self) -> int:
        x = self.findMedian()
        return (self.s1 - x * len(self.q1) + x * len(self.q2) - self.s2) % (10**9 + 7)


class Solution:
    def numsGame(self, nums: List[int]) -> List[int]:
        n = len(nums)
        ans = [0] * n
        finder = MedianFinder()
        for i, x in enumerate(nums):
            finder.addNum(x - i)
            ans[i] = finder.cal()
        return ans
```

#### Java

```java
class MedianFinder {
    private PriorityQueue<Integer> q1 = new PriorityQueue<>();
    private PriorityQueue<Integer> q2 = new PriorityQueue<>(Collections.reverseOrder());
    private final int mod = (int) 1e9 + 7;
    private long s1;
    private long s2;

    public MedianFinder() {
    }

    public void addNum(int num) {
        q1.offer(num);
        s1 += num;
        num = q1.poll();
        q2.offer(num);
        s1 -= num;
        s2 += num;
        if (q2.size() - q1.size() > 1) {
            num = q2.poll();
            q1.offer(num);
            s1 += num;
            s2 -= num;
        }
    }

    public long findMedian() {
        if (q2.size() > q1.size()) {
            return q2.peek();
        }
        return (q1.peek() + q2.peek()) / 2;
    }

    public int cal() {
        long x = findMedian();
        return (int) ((s1 - x * q1.size() + x * q2.size() - s2) % mod);
    }
}

class Solution {
    public int[] numsGame(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n];
        MedianFinder finder = new MedianFinder();
        for (int i = 0; i < n; ++i) {
            finder.addNum(nums[i] - i);
            ans[i] = finder.cal();
        }
        return ans;
    }
}
```

#### C++

```cpp
class MedianFinder {
public:
    MedianFinder() {
    }

    void addNum(int num) {
        q1.push(num);
        s1 += num;
        num = q1.top();
        q2.push(num);
        q1.pop();
        s2 += num;
        s1 -= num;
        if (q2.size() - q1.size() > 1) {
            num = q2.top();
            q1.push(num);
            q2.pop();
            s1 += num;
            s2 -= num;
        }
    }

    int findMedian() {
        if (q2.size() > q1.size()) {
            return q2.top();
        }
        return (q1.top() + q2.top()) / 2;
    }

    int cal() {
        long long x = findMedian();
        return (s1 - x * q1.size() + x * q2.size() - s2) % mod;
    }

private:
    priority_queue<int, vector<int>, greater<int>> q1;
    priority_queue<int> q2;
    long long s1 = 0;
    long long s2 = 0;
    const int mod = 1e9 + 7;
};

class Solution {
public:
    vector<int> numsGame(vector<int>& nums) {
        int n = nums.size();
        vector<int> ans(n);
        MedianFinder finder;
        for (int i = 0; i < n; ++i) {
            finder.addNum(nums[i] - i);
            ans[i] = finder.cal();
        }
        return ans;
    }
};
```

#### Go

```go
func numsGame(nums []int) []int {
	n := len(nums)
	ans := make([]int, n)
	finder := newMedianFinder()
	for i, x := range nums {
		finder.AddNum(x - i)
		ans[i] = finder.Cal()
	}
	return ans
}

type MedianFinder struct {
	q1 hp
	q2 hp
	s1 int
	s2 int
}

func newMedianFinder() *MedianFinder {
	return &MedianFinder{hp{}, hp{}, 0, 0}
}

func (this *MedianFinder) AddNum(num int) {
	heap.Push(&this.q1, num)
	this.s1 += num
	num = heap.Pop(&this.q1).(int)
	heap.Push(&this.q2, -num)
	this.s1 -= num
	this.s2 += num
	if this.q2.Len()-this.q1.Len() > 1 {
		num = -heap.Pop(&this.q2).(int)
		heap.Push(&this.q1, num)
		this.s1 += num
		this.s2 -= num
	}
}

func (this *MedianFinder) FindMedian() int {
	if this.q2.Len() > this.q1.Len() {
		return -this.q2.IntSlice[0]
	}
	return (this.q1.IntSlice[0] - this.q2.IntSlice[0]) / 2
}

func (this *MedianFinder) Cal() int {
	x := this.FindMedian()
	return (this.s1 - x*this.q1.Len() + x*this.q2.Len() - this.s2) % 1000000007
}

type hp struct{ sort.IntSlice }

func (h hp) Less(i, j int) bool { return h.IntSlice[i] < h.IntSlice[j] }
func (h *hp) Push(v any)        { h.IntSlice = append(h.IntSlice, v.(int)) }
func (h *hp) Pop() any {
	a := h.IntSlice
	v := a[len(a)-1]
	h.IntSlice = a[:len(a)-1]
	return v
}
```

#### Swift

```swift
class MedianFinder {
    private var lowerHeap: [Int] = []
    private var upperHeap: [Int] = []
    private let mod = 1_000_000_007
    private var sumLower = 0
    private var sumUpper = 0

    init() {}

    func addNum(_ num: Int) {

        upperHeap.append(num)
        sumUpper += num
        upperHeap.sort()


        if let minUpper = upperHeap.first {
            upperHeap.removeFirst()
            lowerHeap.append(minUpper)
            sumUpper -= minUpper
            sumLower += minUpper
            lowerHeap.sort(by: >)
        }


        if lowerHeap.count > upperHeap.count + 1 {
            if let maxLower = lowerHeap.first {
                lowerHeap.removeFirst()
                upperHeap.append(maxLower)
                sumLower -= maxLower
                sumUpper += maxLower
                upperHeap.sort()
            }
        }
    }

    func findMedian() -> Int {
        if lowerHeap.count > upperHeap.count {
            return lowerHeap.first ?? 0
        } else if let minUpper = upperHeap.first, let maxLower = lowerHeap.first {
            return (minUpper + maxLower) / 2
        }
        return 0
    }

    func cal() -> Int {
        let median = findMedian()
        var result = (sumUpper - median * upperHeap.count) + (median * lowerHeap.count - sumLower)
        result %= mod
        if result < 0 {
            result += mod
        }
        return Int(result)
    }
}

class Solution {
    func numsGame(_ nums: [Int]) -> [Int] {
        let n = nums.count
        var result = [Int]()
        let finder = MedianFinder()

        for i in 0..<n {
            finder.addNum(nums[i] - i)
            result.append(finder.cal())
        }

        return result
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法二：有序集合

与方法一类似，我们可以用两个有序集合 $\textit{l}$ 和 $\textit{r}$ 分别维护前缀数组较小的一半元素和较大的一半元素。

具体地，我们创建两个有序集合 $\textit{l}$ 和 $\textit{r}$，其中：$\textit{l}$ 存放较小的一半元素，$\textit{r}$ 存放较大的一半元素。同时，我们维护两个变量 $\textit{s}$ 和 $\textit{t}$ 分别记录 $\textit{l}$ 和 $\textit{r}$ 的元素和。

添加元素时，先放入 $\textit{r}$，然后将 $\textit{r}$ 的最小元素弹出并放入 $\textit{l}$（使得 $\textit{l}$ 的元素个数多 $1$）；若 $\textit{l}$ 元素个数与 $\textit{r}$ 元素个数差超过 $1$，则将 $\textit{l}$ 的最大元素弹出放入 $\textit{r}$。过程中，用两个变量 $\textit{s}$ 和 $\textit{t}$ 分别记录两个集合的元素和。那么，此时的中位数为 $\textit{l}$ 的最大元素，最小操作次数为中位数乘以 $\textit{l}$ 的元素个数减去 $\textit{s}$ 再加上 $\textit{t}$ 减去中位数乘以 $\textit{r}$ 的元素个数。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(n)$。其中 $n$ 为数组长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def numsGame(self, nums: List[int]) -> List[int]:
        l = SortedList()
        r = SortedList()
        s = t = 0
        mod = 10**9 + 7
        ans = []
        for i, x in enumerate(nums):
            x -= i
            r.add(x)
            t += x
            x = r.pop(0)
            t -= x
            l.add(x)
            s += x
            while len(l) - len(r) > 1:
                x = l.pop()
                s -= x
                r.add(x)
                t += x
            v = len(l) * l[-1] - s + t - len(r) * l[-1]
            ans.append(v % mod)
        return ans
```

#### Java

```java
class Solution {
    public int[] numsGame(int[] nums) {
        TreeMap<Integer, Integer> l = new TreeMap<>();
        TreeMap<Integer, Integer> r = new TreeMap<>();
        int n = nums.length;
        int[] ans = new int[n];
        final int mod = (int) 1e9 + 7;
        long s = 0, t = 0;
        int lSize = 0, rSize = 0;
        for (int i = 0; i < n; ++i) {
            int x = nums[i] - i;
            r.merge(x, 1, Integer::sum);
            t += x;
            x = r.firstKey();
            if (r.merge(x, -1, Integer::sum) == 0) {
                r.remove(x);
            }
            t -= x;
            l.merge(x, 1, Integer::sum);
            s += x;
            ++lSize;
            while (lSize - rSize > 1) {
                x = l.lastKey();
                if (l.merge(x, -1, Integer::sum) == 0) {
                    l.remove(x);
                }
                s -= x;
                --lSize;
                r.merge(x, 1, Integer::sum);
                t += x;
                ++rSize;
            }
            long mid = l.lastKey();
            ans[i] = (int) ((mid * lSize - s + t - mid * rSize) % mod);
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<int> numsGame(vector<int>& nums) {
        multiset<int> l, r;
        int n = nums.size();
        vector<int> ans(n);
        const int mod = 1e9 + 7;
        long long s = 0, t = 0;
        for (int i = 0; i < n; ++i) {
            int x = nums[i] - i;
            r.insert(x);
            t += x;
            x = *r.begin();
            r.erase(r.begin());
            t -= x;
            l.insert(x);
            s += x;
            while (l.size() - r.size() > 1) {
                x = *l.rbegin();
                l.erase(l.find(x));
                s -= x;
                r.insert(x);
                t += x;
            }
            long long mid = *l.rbegin();
            ans[i] = (mid * l.size() - s + t - mid * r.size()) % mod;
        }
        return ans;
    }
};
```

#### Go

```go
func numsGame(nums []int) (ans []int) {
	l := redblacktree.New[int, int]()
	r := redblacktree.New[int, int]()
	merge := func(st *redblacktree.Tree[int, int], x, v int) {
		c, _ := st.Get(x)
		if c+v == 0 {
			st.Remove(x)
		} else {
			st.Put(x, c+v)
		}
	}
	s, t := 0, 0
	lSize, rSize := 0, 0
	const mod int = 1e9 + 7
	for i, x := range nums {
		x -= i
		merge(r, x, 1)
		t += x
		x = r.Left().Key
		merge(r, x, -1)
		t -= x
		merge(l, x, 1)
		s += x
		lSize++
		for lSize-rSize > 1 {
			x = l.Right().Key
			merge(l, x, -1)
			s -= x
			lSize--
			merge(r, x, 1)
			t += x
			rSize++
		}
		mid := l.Right().Key
		v := (mid*lSize%mod - s + t - mid*rSize%mod) % mod
		ans = append(ans, v)
	}
	return
}
```

#### TypeScript

```ts
function numsGame(nums: number[]): number[] {
    const l = new TreapMultiSet<number>((a, b) => a - b);
    const r = new TreapMultiSet<number>((a, b) => a - b);
    let [s, t] = [0, 0];
    const ans: number[] = [];
    const mod = 1e9 + 7;
    for (let i = 0; i < nums.length; ++i) {
        let x = nums[i] - i;
        r.add(x);
        t += x;
        x = r.shift()!;
        t -= x;
        l.add(x);
        s += x;
        while (l.size - r.size > 1) {
            x = l.pop()!;
            s -= x;
            r.add(x);
            t += x;
        }
        const mid = l.last()!;
        ans.push((((mid * l.size) % mod) - s + t - ((mid * r.size) % mod) + mod) % mod);
    }
    return ans;
}

type CompareFunction<T, R extends 'number' | 'boolean'> = (
    a: T,
    b: T,
) => R extends 'number' ? number : boolean;

interface ITreapMultiSet<T> extends Iterable<T> {
    add: (...value: T[]) => this;
    has: (value: T) => boolean;
    delete: (value: T) => void;

    bisectLeft: (value: T) => number;
    bisectRight: (value: T) => number;

    indexOf: (value: T) => number;
    lastIndexOf: (value: T) => number;

    at: (index: number) => T | undefined;
    first: () => T | undefined;
    last: () => T | undefined;

    lower: (value: T) => T | undefined;
    higher: (value: T) => T | undefined;
    floor: (value: T) => T | undefined;
    ceil: (value: T) => T | undefined;

    shift: () => T | undefined;
    pop: (index?: number) => T | undefined;

    count: (value: T) => number;

    keys: () => IterableIterator<T>;
    values: () => IterableIterator<T>;
    rvalues: () => IterableIterator<T>;
    entries: () => IterableIterator<[number, T]>;

    readonly size: number;
}

class TreapNode<T = number> {
    value: T;
    count: number;
    size: number;
    priority: number;
    left: TreapNode<T> | null;
    right: TreapNode<T> | null;

    constructor(value: T) {
        this.value = value;
        this.count = 1;
        this.size = 1;
        this.priority = Math.random();
        this.left = null;
        this.right = null;
    }

    static getSize(node: TreapNode<any> | null): number {
        return node?.size ?? 0;
    }

    static getFac(node: TreapNode<any> | null): number {
        return node?.priority ?? 0;
    }

    pushUp(): void {
        let tmp = this.count;
        tmp += TreapNode.getSize(this.left);
        tmp += TreapNode.getSize(this.right);
        this.size = tmp;
    }

    rotateRight(): TreapNode<T> {
        // eslint-disable-next-line @typescript-eslint/no-this-alias
        let node: TreapNode<T> = this;
        const left = node.left;
        node.left = left?.right ?? null;
        left && (left.right = node);
        left && (node = left);
        node.right?.pushUp();
        node.pushUp();
        return node;
    }

    rotateLeft(): TreapNode<T> {
        // eslint-disable-next-line @typescript-eslint/no-this-alias
        let node: TreapNode<T> = this;
        const right = node.right;
        node.right = right?.left ?? null;
        right && (right.left = node);
        right && (node = right);
        node.left?.pushUp();
        node.pushUp();
        return node;
    }
}

class TreapMultiSet<T = number> implements ITreapMultiSet<T> {
    private readonly root: TreapNode<T>;
    private readonly compareFn: CompareFunction<T, 'number'>;
    private readonly leftBound: T;
    private readonly rightBound: T;

    constructor(compareFn?: CompareFunction<T, 'number'>);
    constructor(compareFn: CompareFunction<T, 'number'>, leftBound: T, rightBound: T);
    constructor(
        compareFn: CompareFunction<T, any> = (a: any, b: any) => a - b,
        leftBound: any = -Infinity,
        rightBound: any = Infinity,
    ) {
        this.root = new TreapNode<T>(rightBound);
        this.root.priority = Infinity;
        this.root.left = new TreapNode<T>(leftBound);
        this.root.left.priority = -Infinity;
        this.root.pushUp();

        this.leftBound = leftBound;
        this.rightBound = rightBound;
        this.compareFn = compareFn;
    }

    get size(): number {
        return this.root.size - 2;
    }

    get height(): number {
        const getHeight = (node: TreapNode<T> | null): number => {
            if (node == null) return 0;
            return 1 + Math.max(getHeight(node.left), getHeight(node.right));
        };

        return getHeight(this.root);
    }

    /**
     *
     * @complexity `O(logn)`
     * @description Returns true if value is a member.
     */
    has(value: T): boolean {
        const compare = this.compareFn;
        const dfs = (node: TreapNode<T> | null, value: T): boolean => {
            if (node == null) return false;
            if (compare(node.value, value) === 0) return true;
            if (compare(node.value, value) < 0) return dfs(node.right, value);
            return dfs(node.left, value);
        };

        return dfs(this.root, value);
    }

    /**
     *
     * @complexity `O(logn)`
     * @description Add value to sorted set.
     */
    add(...values: T[]): this {
        const compare = this.compareFn;
        const dfs = (
            node: TreapNode<T> | null,
            value: T,
            parent: TreapNode<T>,
            direction: 'left' | 'right',
        ): void => {
            if (node == null) return;
            if (compare(node.value, value) === 0) {
                node.count++;
                node.pushUp();
            } else if (compare(node.value, value) > 0) {
                if (node.left) {
                    dfs(node.left, value, node, 'left');
                } else {
                    node.left = new TreapNode(value);
                    node.pushUp();
                }

                if (TreapNode.getFac(node.left) > node.priority) {
                    parent[direction] = node.rotateRight();
                }
            } else if (compare(node.value, value) < 0) {
                if (node.right) {
                    dfs(node.right, value, node, 'right');
                } else {
                    node.right = new TreapNode(value);
                    node.pushUp();
                }

                if (TreapNode.getFac(node.right) > node.priority) {
                    parent[direction] = node.rotateLeft();
                }
            }
            parent.pushUp();
        };

        values.forEach(value => dfs(this.root.left, value, this.root, 'left'));
        return this;
    }

    /**
     *
     * @complexity `O(logn)`
     * @description Remove value from sorted set if it is a member.
     * If value is not a member, do nothing.
     */
    delete(value: T): void {
        const compare = this.compareFn;
        const dfs = (
            node: TreapNode<T> | null,
            value: T,
            parent: TreapNode<T>,
            direction: 'left' | 'right',
        ): void => {
            if (node == null) return;

            if (compare(node.value, value) === 0) {
                if (node.count > 1) {
                    node.count--;
                    node?.pushUp();
                } else if (node.left == null && node.right == null) {
                    parent[direction] = null;
                } else {
                    // 旋到根节点
                    if (
                        node.right == null ||
                        TreapNode.getFac(node.left) > TreapNode.getFac(node.right)
                    ) {
                        parent[direction] = node.rotateRight();
                        dfs(parent[direction]?.right ?? null, value, parent[direction]!, 'right');
                    } else {
                        parent[direction] = node.rotateLeft();
                        dfs(parent[direction]?.left ?? null, value, parent[direction]!, 'left');
                    }
                }
            } else if (compare(node.value, value) > 0) {
                dfs(node.left, value, node, 'left');
            } else if (compare(node.value, value) < 0) {
                dfs(node.right, value, node, 'right');
            }

            parent?.pushUp();
        };

        dfs(this.root.left, value, this.root, 'left');
    }

    /**
     *
     * @complexity `O(logn)`
     * @description Returns an index to insert value in the sorted set.
     * If the value is already present, the insertion point will be before (to the left of) any existing values.
     */
    bisectLeft(value: T): number {
        const compare = this.compareFn;
        const dfs = (node: TreapNode<T> | null, value: T): number => {
            if (node == null) return 0;

            if (compare(node.value, value) === 0) {
                return TreapNode.getSize(node.left);
            } else if (compare(node.value, value) > 0) {
                return dfs(node.left, value);
            } else if (compare(node.value, value) < 0) {
                return dfs(node.right, value) + TreapNode.getSize(node.left) + node.count;
            }

            return 0;
        };

        return dfs(this.root, value) - 1;
    }

    /**
     *
     * @complexity `O(logn)`
     * @description Returns an index to insert value in the sorted set.
     * If the value is already present, the insertion point will be before (to the right of) any existing values.
     */
    bisectRight(value: T): number {
        const compare = this.compareFn;
        const dfs = (node: TreapNode<T> | null, value: T): number => {
            if (node == null) return 0;

            if (compare(node.value, value) === 0) {
                return TreapNode.getSize(node.left) + node.count;
            } else if (compare(node.value, value) > 0) {
                return dfs(node.left, value);
            } else if (compare(node.value, value) < 0) {
                return dfs(node.right, value) + TreapNode.getSize(node.left) + node.count;
            }

            return 0;
        };
        return dfs(this.root, value) - 1;
    }

    /**
     *
     * @complexity `O(logn)`
     * @description Returns the index of the first occurrence of a value in the set, or -1 if it is not present.
     */
    indexOf(value: T): number {
        const compare = this.compareFn;
        let isExist = false;

        const dfs = (node: TreapNode<T> | null, value: T): number => {
            if (node == null) return 0;

            if (compare(node.value, value) === 0) {
                isExist = true;
                return TreapNode.getSize(node.left);
            } else if (compare(node.value, value) > 0) {
                return dfs(node.left, value);
            } else if (compare(node.value, value) < 0) {
                return dfs(node.right, value) + TreapNode.getSize(node.left) + node.count;
            }

            return 0;
        };
        const res = dfs(this.root, value) - 1;
        return isExist ? res : -1;
    }

    /**
     *
     * @complexity `O(logn)`
     * @description Returns the index of the last occurrence of a value in the set, or -1 if it is not present.
     */
    lastIndexOf(value: T): number {
        const compare = this.compareFn;
        let isExist = false;

        const dfs = (node: TreapNode<T> | null, value: T): number => {
            if (node == null) return 0;

            if (compare(node.value, value) === 0) {
                isExist = true;
                return TreapNode.getSize(node.left) + node.count - 1;
            } else if (compare(node.value, value) > 0) {
                return dfs(node.left, value);
            } else if (compare(node.value, value) < 0) {
                return dfs(node.right, value) + TreapNode.getSize(node.left) + node.count;
            }

            return 0;
        };

        const res = dfs(this.root, value) - 1;
        return isExist ? res : -1;
    }

    /**
     *
     * @complexity `O(logn)`
     * @description Returns the item located at the specified index.
     * @param index The zero-based index of the desired code unit. A negative index will count back from the last item.
     */
    at(index: number): T | undefined {
        if (index < 0) index += this.size;
        if (index < 0 || index >= this.size) return undefined;

        const dfs = (node: TreapNode<T> | null, rank: number): T | undefined => {
            if (node == null) return undefined;

            if (TreapNode.getSize(node.left) >= rank) {
                return dfs(node.left, rank);
            } else if (TreapNode.getSize(node.left) + node.count >= rank) {
                return node.value;
            } else {
                return dfs(node.right, rank - TreapNode.getSize(node.left) - node.count);
            }
        };

        const res = dfs(this.root, index + 2);
        return ([this.leftBound, this.rightBound] as any[]).includes(res) ? undefined : res;
    }

    /**
     *
     * @complexity `O(logn)`
     * @description Find and return the element less than `val`, return `undefined` if no such element found.
     */
    lower(value: T): T | undefined {
        const compare = this.compareFn;
        const dfs = (node: TreapNode<T> | null, value: T): T | undefined => {
            if (node == null) return undefined;
            if (compare(node.value, value) >= 0) return dfs(node.left, value);

            const tmp = dfs(node.right, value);
            if (tmp == null || compare(node.value, tmp) > 0) {
                return node.value;
            } else {
                return tmp;
            }
        };

        const res = dfs(this.root, value) as any;
        return res === this.leftBound ? undefined : res;
    }

    /**
     *
     * @complexity `O(logn)`
     * @description Find and return the element greater than `val`, return `undefined` if no such element found.
     */
    higher(value: T): T | undefined {
        const compare = this.compareFn;
        const dfs = (node: TreapNode<T> | null, value: T): T | undefined => {
            if (node == null) return undefined;
            if (compare(node.value, value) <= 0) return dfs(node.right, value);

            const tmp = dfs(node.left, value);

            if (tmp == null || compare(node.value, tmp) < 0) {
                return node.value;
            } else {
                return tmp;
            }
        };

        const res = dfs(this.root, value) as any;
        return res === this.rightBound ? undefined : res;
    }

    /**
     *
     * @complexity `O(logn)`
     * @description Find and return the element less than or equal to `val`, return `undefined` if no such element found.
     */
    floor(value: T): T | undefined {
        const compare = this.compareFn;
        const dfs = (node: TreapNode<T> | null, value: T): T | undefined => {
            if (node == null) return undefined;
            if (compare(node.value, value) === 0) return node.value;
            if (compare(node.value, value) >= 0) return dfs(node.left, value);

            const tmp = dfs(node.right, value);
            if (tmp == null || compare(node.value, tmp) > 0) {
                return node.value;
            } else {
                return tmp;
            }
        };

        const res = dfs(this.root, value) as any;
        return res === this.leftBound ? undefined : res;
    }

    /**
     *
     * @complexity `O(logn)`
     * @description Find and return the element greater than or equal to `val`, return `undefined` if no such element found.
     */
    ceil(value: T): T | undefined {
        const compare = this.compareFn;
        const dfs = (node: TreapNode<T> | null, value: T): T | undefined => {
            if (node == null) return undefined;
            if (compare(node.value, value) === 0) return node.value;
            if (compare(node.value, value) <= 0) return dfs(node.right, value);

            const tmp = dfs(node.left, value);

            if (tmp == null || compare(node.value, tmp) < 0) {
                return node.value;
            } else {
                return tmp;
            }
        };

        const res = dfs(this.root, value) as any;
        return res === this.rightBound ? undefined : res;
    }

    /**
     * @complexity `O(logn)`
     * @description
     * Returns the last element from set.
     * If the set is empty, undefined is returned.
     */
    first(): T | undefined {
        const iter = this.inOrder();
        iter.next();
        const res = iter.next().value;
        return res === this.rightBound ? undefined : res;
    }

    /**
     * @complexity `O(logn)`
     * @description
     * Returns the last element from set.
     * If the set is empty, undefined is returned .
     */
    last(): T | undefined {
        const iter = this.reverseInOrder();
        iter.next();
        const res = iter.next().value;
        return res === this.leftBound ? undefined : res;
    }

    /**
     * @complexity `O(logn)`
     * @description
     * Removes the first element from an set and returns it.
     * If the set is empty, undefined is returned and the set is not modified.
     */
    shift(): T | undefined {
        const first = this.first();
        if (first === undefined) return undefined;
        this.delete(first);
        return first;
    }

    /**
     * @complexity `O(logn)`
     * @description
     * Removes the last element from an set and returns it.
     * If the set is empty, undefined is returned and the set is not modified.
     */
    pop(index?: number): T | undefined {
        if (index == null) {
            const last = this.last();
            if (last === undefined) return undefined;
            this.delete(last);
            return last;
        }

        const toDelete = this.at(index);
        if (toDelete == null) return;
        this.delete(toDelete);
        return toDelete;
    }

    /**
     *
     * @complexity `O(logn)`
     * @description
     * Returns number of occurrences of value in the sorted set.
     */
    count(value: T): number {
        const compare = this.compareFn;
        const dfs = (node: TreapNode<T> | null, value: T): number => {
            if (node == null) return 0;
            if (compare(node.value, value) === 0) return node.count;
            if (compare(node.value, value) < 0) return dfs(node.right, value);
            return dfs(node.left, value);
        };

        return dfs(this.root, value);
    }

    *[Symbol.iterator](): Generator<T, any, any> {
        yield* this.values();
    }

    /**
     * @description
     * Returns an iterable of keys in the set.
     */
    *keys(): Generator<T, any, any> {
        yield* this.values();
    }

    /**
     * @description
     * Returns an iterable of values in the set.
     */
    *values(): Generator<T, any, any> {
        const iter = this.inOrder();
        iter.next();
        const steps = this.size;
        for (let _ = 0; _ < steps; _++) {
            yield iter.next().value;
        }
    }

    /**
     * @description
     * Returns a generator for reversed order traversing the set.
     */
    *rvalues(): Generator<T, any, any> {
        const iter = this.reverseInOrder();
        iter.next();
        const steps = this.size;
        for (let _ = 0; _ < steps; _++) {
            yield iter.next().value;
        }
    }

    /**
     * @description
     * Returns an iterable of key, value pairs for every entry in the set.
     */
    *entries(): IterableIterator<[number, T]> {
        const iter = this.inOrder();
        iter.next();
        const steps = this.size;
        for (let i = 0; i < steps; i++) {
            yield [i, iter.next().value];
        }
    }

    private *inOrder(root: TreapNode<T> | null = this.root): Generator<T, any, any> {
        if (root == null) return;
        yield* this.inOrder(root.left);
        const count = root.count;
        for (let _ = 0; _ < count; _++) {
            yield root.value;
        }
        yield* this.inOrder(root.right);
    }

    private *reverseInOrder(root: TreapNode<T> | null = this.root): Generator<T, any, any> {
        if (root == null) return;
        yield* this.reverseInOrder(root.right);
        const count = root.count;
        for (let _ = 0; _ < count; _++) {
            yield root.value;
        }
        yield* this.reverseInOrder(root.left);
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
