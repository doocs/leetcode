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

-   `1 <= nums.length <= 10^5`
-   `1 <= nums[i] <= 10^3`

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

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
