# [2653. 滑动子数组的美丽值](https://leetcode.cn/problems/sliding-subarray-beauty)

[English Version](/solution/2600-2699/2653.Sliding%20Subarray%20Beauty/README_EN.md)

<!-- tags:数组,哈希表,滑动窗口 -->

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个长度为 <code>n</code>&nbsp;的整数数组&nbsp;<code>nums</code>&nbsp;，请你求出每个长度为&nbsp;<code>k</code>&nbsp;的子数组的 <b>美丽值</b>&nbsp;。</p>

<p>一个子数组的 <strong>美丽值</strong>&nbsp;定义为：如果子数组中第 <code>x</code>&nbsp;<strong>小整数</strong>&nbsp;是 <strong>负数</strong>&nbsp;，那么美丽值为第 <code>x</code>&nbsp;小的数，否则美丽值为 <code>0</code>&nbsp;。</p>

<p>请你返回一个包含<em>&nbsp;</em><code>n - k + 1</code>&nbsp;个整数的数组，<strong>依次</strong>&nbsp;表示数组中从第一个下标开始，每个长度为&nbsp;<code>k</code>&nbsp;的子数组的<strong>&nbsp;美丽值</strong>&nbsp;。</p>

<ul>
	<li>
	<p>子数组指的是数组中一段连续 <strong>非空</strong>&nbsp;的元素序列。</p>
	</li>
</ul>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><b>输入：</b>nums = [1,-1,-3,-2,3], k = 3, x = 2
<b>输出：</b>[-1,-2,-2]
<b>解释：</b>总共有 3 个 k = 3 的子数组。
第一个子数组是 <code>[1, -1, -3]</code> ，第二小的数是负数 -1 。
第二个子数组是 <code>[-1, -3, -2]</code> ，第二小的数是负数 -2 。
第三个子数组是 <code>[-3, -2, 3]&nbsp;，第二小的数是负数 -2 。</code></pre>

<p><strong>示例 2：</strong></p>

<pre><b>输入：</b>nums = [-1,-2,-3,-4,-5], k = 2, x = 2
<b>输出：</b>[-1,-2,-3,-4]
<b>解释：</b>总共有 4 个 k = 2 的子数组。
<code>[-1, -2] 中第二小的数是负数 -1 。</code>
<code>[-2, -3] 中第二小的数是负数 -2 。</code>
<code>[-3, -4] 中第二小的数是负数 -3 。</code>
<code>[-4, -5] 中第二小的数是负数 -4 。</code></pre>

<p><strong>示例 3：</strong></p>

<pre><b>输入：</b>nums = [-3,1,2,-3,0,-3], k = 2, x = 1
<b>输出：</b>[-3,0,-3,-3,-3]
<b>解释：</b>总共有 5 个 k = 2 的子数组。
<code>[-3, 1] 中最小的数是负数 -3 。</code>
<code>[1, 2] 中最小的数不是负数，所以美丽值为 0 。</code>
<code>[2, -3] 中最小的数是负数 -3 。</code>
<code>[-3, 0] 中最小的数是负数 -3 。</code>
<code>[0, -3] 中最小的数是负数 -3 。</code></pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == nums.length&nbsp;</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= k &lt;= n</code></li>
	<li><code>1 &lt;= x &lt;= k&nbsp;</code></li>
	<li><code>-50&nbsp;&lt;= nums[i] &lt;= 50&nbsp;</code></li>
</ul>

## 解法

### 方法一：滑动窗口

我们注意到，数组 $nums$ 中元素的范围为 $[-50,50]$，因此，我们可以用一个数组长度为 $101$ 的数组 $cnt$ 统计 $[-50,50]$ 中每个数出现的次数。由于负数的存在，我们可以将每个数加上 $50$，使得每个数都变成非负数，这样就可以用数组 $cnt$ 统计每个数出现的次数了。

接下来，我们遍历数组 $nums$，维护一个长度为 $k$ 的滑动窗口，窗口中所有元素出现的次记数录在数组 $cnt$ 中，然后我们遍历数组 $cnt$，找到第 $x$ 小的负数，即为当前滑动窗口的美丽值。如果不存在第 $x$ 小的负数，那么美丽值为 $0$。

时间复杂度 $O(n \times 50)$，空间复杂度 $O(100)$。其中 $n$ 为数组 $nums$ 的长度。

<!-- tabs:start -->

```python
class Solution:
    def getSubarrayBeauty(self, nums: List[int], k: int, x: int) -> List[int]:
        def f(x: int) -> int:
            s = 0
            for i in range(50):
                s += cnt[i]
                if s >= x:
                    return i - 50
            return 0

        cnt = [0] * 101
        for v in nums[:k]:
            cnt[v + 50] += 1
        ans = [f(x)]
        for i in range(k, len(nums)):
            cnt[nums[i] + 50] += 1
            cnt[nums[i - k] + 50] -= 1
            ans.append(f(x))
        return ans
```

```python
from sortedcontainers import SortedList


class Solution:
    def getSubarrayBeauty(self, nums: List[int], k: int, x: int) -> List[int]:
        sl = SortedList(nums[:k])
        ans = [sl[x - 1] if sl[x - 1] < 0 else 0]
        for i in range(k, len(nums)):
            sl.remove(nums[i - k])
            sl.add(nums[i])
            ans.append(sl[x - 1] if sl[x - 1] < 0 else 0)
        return ans
```

```java
class Solution {
    public int[] getSubarrayBeauty(int[] nums, int k, int x) {
        int n = nums.length;
        int[] cnt = new int[101];
        for (int i = 0; i < k; ++i) {
            ++cnt[nums[i] + 50];
        }
        int[] ans = new int[n - k + 1];
        ans[0] = f(cnt, x);
        for (int i = k, j = 1; i < n; ++i) {
            ++cnt[nums[i] + 50];
            --cnt[nums[i - k] + 50];
            ans[j++] = f(cnt, x);
        }
        return ans;
    }

    private int f(int[] cnt, int x) {
        int s = 0;
        for (int i = 0; i < 50; ++i) {
            s += cnt[i];
            if (s >= x) {
                return i - 50;
            }
        }
        return 0;
    }
}
```

```cpp
class Solution {
public:
    vector<int> getSubarrayBeauty(vector<int>& nums, int k, int x) {
        int n = nums.size();
        int cnt[101]{};
        for (int i = 0; i < k; ++i) {
            ++cnt[nums[i] + 50];
        }
        vector<int> ans(n - k + 1);
        auto f = [&](int x) {
            int s = 0;
            for (int i = 0; i < 50; ++i) {
                s += cnt[i];
                if (s >= x) {
                    return i - 50;
                }
            }
            return 0;
        };
        ans[0] = f(x);
        for (int i = k, j = 1; i < n; ++i) {
            ++cnt[nums[i] + 50];
            --cnt[nums[i - k] + 50];
            ans[j++] = f(x);
        }
        return ans;
    }
};
```

```go
func getSubarrayBeauty(nums []int, k int, x int) []int {
	n := len(nums)
	cnt := [101]int{}
	for _, x := range nums[:k] {
		cnt[x+50]++
	}
	ans := make([]int, n-k+1)
	f := func(x int) int {
		s := 0
		for i := 0; i < 50; i++ {
			s += cnt[i]
			if s >= x {
				return i - 50
			}
		}
		return 0
	}
	ans[0] = f(x)
	for i, j := k, 1; i < n; i, j = i+1, j+1 {
		cnt[nums[i]+50]++
		cnt[nums[i-k]+50]--
		ans[j] = f(x)
	}
	return ans
}
```

```ts
function getSubarrayBeauty(nums: number[], k: number, x: number): number[] {
    const n = nums.length;
    const cnt: number[] = new Array(101).fill(0);
    for (let i = 0; i < k; ++i) {
        ++cnt[nums[i] + 50];
    }
    const ans: number[] = new Array(n - k + 1);
    const f = (x: number): number => {
        let s = 0;
        for (let i = 0; i < 50; ++i) {
            s += cnt[i];
            if (s >= x) {
                return i - 50;
            }
        }
        return 0;
    };
    ans[0] = f(x);
    for (let i = k, j = 1; i < n; ++i, ++j) {
        cnt[nums[i] + 50]++;
        cnt[nums[i - k] + 50]--;
        ans[j] = f(x);
    }
    return ans;
}
```

<!-- tabs:end -->

### 方法二：双优先队列（大小根堆） + 延迟删除

我们可以使用两个优先队列（大小根堆）维护当前窗口中的元素，其中一个优先队列存储当前窗口中较小的 $x$ 个元素，另一个优先队列存储当前窗口中较大的 $k - x$ 个元素。我们还需要一个延迟删除字典 `delayed`，用于记录当前窗口中的元素是否需要删除。

我们设计一个类 `MedianFinder`，用于维护当前窗口中的元素。该类包含以下方法：

-   `add_num(num)`：将 `num` 加入当前窗口中。
-   `find()`：返回当前窗口的美丽值。
-   `remove_num(num)`：将 `num` 从当前窗口中移除。
-   `prune(pq)`：如果堆顶元素在延迟删除字典 `delayed` 中，则将其从堆顶弹出，并从该元素的延迟删除次数中减一。如果该元素的延迟删除次数为零，则将其从延迟删除字典中删除。
-   `rebalance()`：平衡两个优先队列的大小。

在 `add_num(num)` 方法中，我们先考虑将 `num` 加入较小的队列中，如果数量大于 $x$ 或者 `num` 小于等于较小的队列的堆顶元素，则将 `num` 加入较小的队列中；否则，将 `num` 加入较大的队列中。然后我们调用 `rebalance()` 方法，使得较小的队列中的元素数量不超过 $x$。

在 `remove_num(num)` 方法中，我们将 `num` 的延迟删除次数加一。然后我们将 `num` 与较小的队列的堆顶元素进行比较，如果 `num` 小于等于较小的队列的堆顶元素，则更新较小的队列的大小，并且调用 `prune()` 方法，使得较小的队列的堆顶元素不在延迟删除字典中。否则，我们更新较大的队列的大小，并且调用 `prune()` 方法，使得较大的队列的堆顶元素不在延迟删除字典中。

在 `find()` 方法中，如果较小的队列的大小等于 $x$，则返回较小的队列的堆顶元素，否则返回 $0$。

在 `prune(pq)` 方法中，如果堆顶元素在延迟删除字典中，则将其从堆顶弹出，并从该元素的延迟删除次数中减一。如果该元素的延迟删除次数为零，则将其从延迟删除字典中删除。

在 `rebalance()` 方法中，如果较小的队列的大小大于 $x$，则将较小的队列的堆顶元素加入较大的队列中，并调用 `prune()` 方法，使得较小的队列的堆顶元素不在延迟删除字典中。如果较小的队列的大小小于 $x$ 且较大的队列的大小大于 $0$，则将较大的队列的堆顶元素加入较小的队列中，并调用 `prune()` 方法，使得较大的队列的堆顶元素不在延迟删除字典中。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(n)$。其中 $n$ 为数组 `nums` 的长度。

相似题目：

-   [480. 滑动窗口中位数](https://github.com/doocs/leetcode/blob/main/solution/0400-0499/0480.Sliding%20Window%20Median/README.md)

<!-- tabs:start -->

```python
class MedianFinder:
    def __init__(self, x: int):
        self.x = x
        self.small = []
        self.large = []
        self.delayed = defaultdict(int)
        self.small_size = 0
        self.large_size = 0

    def add_num(self, num: int):
        if self.small_size < self.x or num <= -self.small[0]:
            heappush(self.small, -num)
            self.small_size += 1
        else:
            heappush(self.large, num)
            self.large_size += 1
        self.rebalance()

    def find(self) -> float:
        return -self.small[0] if self.small_size == self.x else 0

    def remove_num(self, num: int):
        self.delayed[num] += 1
        if num <= -self.small[0]:
            self.small_size -= 1
            if num == -self.small[0]:
                self.prune(self.small)
        else:
            self.large_size -= 1
            if num == self.large[0]:
                self.prune(self.large)
        self.rebalance()

    def prune(self, pq: List[int]):
        sign = -1 if pq is self.small else 1
        while pq and sign * pq[0] in self.delayed:
            self.delayed[sign * pq[0]] -= 1
            if self.delayed[sign * pq[0]] == 0:
                self.delayed.pop(sign * pq[0])
            heappop(pq)

    def rebalance(self):
        if self.small_size > self.x:
            heappush(self.large, -heappop(self.small))
            self.small_size -= 1
            self.large_size += 1
            self.prune(self.small)
        elif self.small_size < self.x and self.large_size > 0:
            heappush(self.small, -heappop(self.large))
            self.large_size -= 1
            self.small_size += 1
            self.prune(self.large)


class Solution:
    def getSubarrayBeauty(self, nums: List[int], k: int, x: int) -> List[int]:
        finder = MedianFinder(x)
        for i in range(k):
            if nums[i] < 0:
                finder.add_num(nums[i])
        ans = [finder.find()]
        for i in range(k, len(nums)):
            if nums[i] < 0:
                finder.add_num(nums[i])
            if nums[i - k] < 0:
                finder.remove_num(nums[i - k])
            ans.append(finder.find())
        return ans
```

```java
class MedianFinder {
    private PriorityQueue<Integer> small = new PriorityQueue<>(Comparator.reverseOrder());
    private PriorityQueue<Integer> large = new PriorityQueue<>();
    private Map<Integer, Integer> delayed = new HashMap<>();
    private int smallSize;
    private int largeSize;
    private int x;

    public MedianFinder(int x) {
        this.x = x;
    }

    public void addNum(int num) {
        if (smallSize < x || num <= small.peek()) {
            small.offer(num);
            ++smallSize;
        } else {
            large.offer(num);
            ++largeSize;
        }
        rebalance();
    }

    public int find() {
        return smallSize == x ? small.peek() : 0;
    }

    public void removeNum(int num) {
        delayed.merge(num, 1, Integer::sum);
        if (num <= small.peek()) {
            --smallSize;
            if (num == small.peek()) {
                prune(small);
            }
        } else {
            --largeSize;
            if (num == large.peek()) {
                prune(large);
            }
        }
        rebalance();
    }

    private void prune(PriorityQueue<Integer> pq) {
        while (!pq.isEmpty() && delayed.containsKey(pq.peek())) {
            if (delayed.merge(pq.peek(), -1, Integer::sum) == 0) {
                delayed.remove(pq.peek());
            }
            pq.poll();
        }
    }

    private void rebalance() {
        if (smallSize > x) {
            large.offer(small.poll());
            --smallSize;
            ++largeSize;
            prune(small);
        } else if (smallSize < x && largeSize > 0) {
            small.offer(large.poll());
            --largeSize;
            ++smallSize;
            prune(large);
        }
    }
}

class Solution {
    public int[] getSubarrayBeauty(int[] nums, int k, int x) {
        MedianFinder finder = new MedianFinder(x);
        for (int i = 0; i < k; ++i) {
            if (nums[i] < 0) {
                finder.addNum(nums[i]);
            }
        }
        int n = nums.length;
        int[] ans = new int[n - k + 1];
        ans[0] = finder.find();
        for (int i = k; i < n; ++i) {
            if (nums[i] < 0) {
                finder.addNum(nums[i]);
            }
            if (nums[i - k] < 0) {
                finder.removeNum(nums[i - k]);
            }
            ans[i - k + 1] = finder.find();
        }
        return ans;
    }
}
```

```cpp
class MedianFinder {
public:
    MedianFinder(int x) {
        this->x = x;
    }

    void addNum(int num) {
        if (smallSize < x || num <= small.top()) {
            small.push(num);
            ++smallSize;
        } else {
            large.push(num);
            ++largeSize;
        }
        reblance();
    }

    void removeNum(int num) {
        ++delayed[num];
        if (num <= small.top()) {
            --smallSize;
            if (num == small.top()) {
                prune(small);
            }
        } else {
            --largeSize;
            if (num == large.top()) {
                prune(large);
            }
        }
        reblance();
    }

    int find() {
        return smallSize == x ? small.top() : 0;
    }

private:
    priority_queue<int> small;
    priority_queue<int, vector<int>, greater<int>> large;
    unordered_map<int, int> delayed;
    int smallSize = 0;
    int largeSize = 0;
    int x;

    template <typename T>
    void prune(T& pq) {
        while (!pq.empty() && delayed[pq.top()]) {
            if (--delayed[pq.top()] == 0) {
                delayed.erase(pq.top());
            }
            pq.pop();
        }
    }

    void reblance() {
        if (smallSize > x) {
            large.push(small.top());
            small.pop();
            --smallSize;
            ++largeSize;
            prune(small);
        } else if (smallSize < x && largeSize > 0) {
            small.push(large.top());
            large.pop();
            ++smallSize;
            --largeSize;
            prune(large);
        }
    }
};


class Solution {
public:
    vector<int> getSubarrayBeauty(vector<int>& nums, int k, int x) {
        MedianFinder finder(x);
        for (int i = 0; i < k; ++i) {
            if (nums[i] < 0) {
                finder.addNum(nums[i]);
            }
        }
        int n = nums.size();
        vector<int> ans;
        ans.push_back(finder.find());
        for (int i = k; i < n; ++i) {
            if (nums[i] < 0) {
                finder.addNum(nums[i]);
            }
            if (nums[i - k] < 0) {
                finder.removeNum(nums[i - k]);
            }
            ans.push_back(finder.find());
        }
        return ans;
    }
};
```

```go
type MedianFinder struct {
	small                hp
	large                hp
	delayed              map[int]int
	smallSize, largeSize int
	x                    int
}

func Constructor(x int) MedianFinder {
	return MedianFinder{hp{}, hp{}, map[int]int{}, 0, 0, x}
}

func (this *MedianFinder) AddNum(num int) {
	if this.smallSize < this.x || num <= -this.small.IntSlice[0] {
		heap.Push(&this.small, -num)
		this.smallSize++
	} else {
		heap.Push(&this.large, num)
		this.largeSize++
	}
	this.rebalance()
}

func (this *MedianFinder) Find() int {
	if this.smallSize == this.x {
		return -this.small.IntSlice[0]
	}
	return 0
}

func (this *MedianFinder) RemoveNum(num int) {
	this.delayed[num]++
	if num <= -this.small.IntSlice[0] {
		this.smallSize--
		if num == -this.small.IntSlice[0] {
			this.prune(&this.small)
		}
	} else {
		this.largeSize--
		if num == this.large.IntSlice[0] {
			this.prune(&this.large)
		}
	}
	this.rebalance()
}

func (this *MedianFinder) prune(pq *hp) {
	sign := 1
	if pq == &this.small {
		sign = -1
	}
	for pq.Len() > 0 && this.delayed[sign*pq.IntSlice[0]] > 0 {
		this.delayed[sign*pq.IntSlice[0]]--
		if this.delayed[sign*pq.IntSlice[0]] == 0 {
			delete(this.delayed, sign*pq.IntSlice[0])
		}
		heap.Pop(pq)
	}
}

func (this *MedianFinder) rebalance() {
	if this.smallSize > this.x {
		heap.Push(&this.large, -heap.Pop(&this.small).(int))
		this.smallSize--
		this.largeSize++
		this.prune(&this.small)
	} else if this.smallSize < this.x && this.largeSize > 0 {
		heap.Push(&this.small, -heap.Pop(&this.large).(int))
		this.smallSize++
		this.largeSize--
		this.prune(&this.large)
	}
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

func getSubarrayBeauty(nums []int, k int, x int) []int {
	finder := Constructor(x)
	for _, num := range nums[:k] {
		if num < 0 {
			finder.AddNum(num)
		}
	}
	ans := []int{finder.Find()}
	for i := k; i < len(nums); i++ {
		if nums[i] < 0 {
			finder.AddNum(nums[i])
		}
		if nums[i-k] < 0 {
			finder.RemoveNum(nums[i-k])
		}
		ans = append(ans, finder.Find())
	}
	return ans
}
```

<!-- tabs:end -->

<!-- end -->
