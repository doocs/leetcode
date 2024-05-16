---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2600-2699/2653.Sliding%20Subarray%20Beauty/README_EN.md
rating: 1785
source: Weekly Contest 342 Q3
tags:
    - Array
    - Hash Table
    - Sliding Window
---

<!-- problem:start -->

# [2653. Sliding Subarray Beauty](https://leetcode.com/problems/sliding-subarray-beauty)

[中文文档](/solution/2600-2699/2653.Sliding%20Subarray%20Beauty/README.md)

## Description

<p>Given an integer array <code>nums</code> containing <code>n</code> integers, find the <strong>beauty</strong> of each subarray of size <code>k</code>.</p>

<p>The <strong>beauty</strong> of a subarray is the <code>x<sup>th</sup></code><strong> smallest integer </strong>in the subarray if it is <strong>negative</strong>, or <code>0</code> if there are fewer than <code>x</code> negative integers.</p>

<p>Return <em>an integer array containing </em><code>n - k + 1</code> <em>integers, which denote the </em><strong>beauty</strong><em> of the subarrays <strong>in order</strong> from the first index in the array.</em></p>

<ul>
	<li>
	<p>A subarray is a contiguous <strong>non-empty</strong> sequence of elements within an array.</p>
	</li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,-1,-3,-2,3], k = 3, x = 2
<strong>Output:</strong> [-1,-2,-2]
<strong>Explanation:</strong> There are 3 subarrays with size k = 3. 
The first subarray is <code>[1, -1, -3]</code> and the 2<sup>nd</sup> smallest negative integer is -1.&nbsp;
The second subarray is <code>[-1, -3, -2]</code> and the 2<sup>nd</sup> smallest negative integer is -2.&nbsp;
The third subarray is <code>[-3, -2, 3]&nbsp;</code>and the 2<sup>nd</sup> smallest negative integer is -2.</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [-1,-2,-3,-4,-5], k = 2, x = 2
<strong>Output:</strong> [-1,-2,-3,-4]
<strong>Explanation:</strong> There are 4 subarrays with size k = 2.
For <code>[-1, -2]</code>, the 2<sup>nd</sup> smallest negative integer is -1.
For <code>[-2, -3]</code>, the 2<sup>nd</sup> smallest negative integer is -2.
For <code>[-3, -4]</code>, the 2<sup>nd</sup> smallest negative integer is -3.
For <code>[-4, -5]</code>, the 2<sup>nd</sup> smallest negative integer is -4.&nbsp;</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [-3,1,2,-3,0,-3], k = 2, x = 1
<strong>Output:</strong> [-3,0,-3,-3,-3]
<strong>Explanation:</strong> There are 5 subarrays with size k = 2<strong>.</strong>
For <code>[-3, 1]</code>, the 1<sup>st</sup> smallest negative integer is -3.
For <code>[1, 2]</code>, there is no negative integer so the beauty is 0.
For <code>[2, -3]</code>, the 1<sup>st</sup> smallest negative integer is -3.
For <code>[-3, 0]</code>, the 1<sup>st</sup> smallest negative integer is -3.
For <code>[0, -3]</code>, the 1<sup>st</sup> smallest negative integer is -3.</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == nums.length&nbsp;</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= k &lt;= n</code></li>
	<li><code>1 &lt;= x &lt;= k&nbsp;</code></li>
	<li><code>-50&nbsp;&lt;= nums[i] &lt;= 50&nbsp;</code></li>
</ul>

## Solutions

<!-- solution:start -->

### Solution 1: Sliding Window

We notice that the range of elements in the array $nums$ is $[-50,50]$. Therefore, we can use an array of length $101$, denoted as $cnt$, to count the occurrences of each number in $[-50,50]$. Due to the presence of negative numbers, we can add $50$ to each number to make them all non-negative, so we can use the array $cnt$ to count the occurrences of each number.

Next, we traverse the array $nums$, maintaining a sliding window of length $k$. The occurrence times of all elements in the window are recorded in the array $cnt$. Then we traverse the array $cnt$ to find the $x$-th smallest number, which is the beauty value of the current sliding window. If there is no $x$-th smallest number, then the beauty value is $0$.

The time complexity is $O(n \times 50)$, and the space complexity is $O(100)$. Where $n$ is the length of the array $nums$.

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

<!-- solution:end -->

<!-- solution:start -->

### Solution 2: Double Priority Queue (Min-Max Heap) + Delayed Deletion

We can use two priority queues (min-max heap) to maintain the elements in the current window, one priority queue stores the smaller $x$ elements in the current window, and the other priority queue stores the larger $k - x$ elements in the current window. We also need a delayed deletion dictionary `delayed` to record whether the elements in the current window need to be deleted.

We design a class `MedianFinder` to maintain the elements in the current window. This class includes the following methods:

-   `add_num(num)`: Add `num` to the current window.
-   `find()`: Return the beauty value of the current window.
-   `remove_num(num)`: Remove `num` from the current window.
-   `prune(pq)`: If the top element of the heap is in the delayed deletion dictionary `delayed`, pop it from the top of the heap and subtract one from its delayed deletion count. If the delayed deletion count of the element is zero, delete it from the delayed deletion dictionary.
-   `rebalance()`: Balance the size of the two priority queues.

In the `add_num(num)` method, we first consider adding `num` to the smaller queue. If the count is greater than $x$ or `num` is less than or equal to the top element of the smaller queue, add `num` to the smaller queue; otherwise, add `num` to the larger queue. Then we call the `rebalance()` method to ensure that the number of elements in the smaller queue does not exceed $x$.

In the `remove_num(num)` method, we increase the delayed deletion count of `num` by one. Then we compare `num` with the top element of the smaller queue. If `num` is less than or equal to the top element of the smaller queue, update the size of the smaller queue and call the `prune()` method to ensure that the top element of the smaller queue is not in the delayed deletion dictionary. Otherwise, we update the size of the larger queue and call the `prune()` method to ensure that the top element of the larger queue is not in the delayed deletion dictionary.

In the `find()` method, if the size of the smaller queue is equal to $x$, return the top element of the smaller queue, otherwise return $0$.

In the `prune(pq)` method, if the top element of the heap is in the delayed deletion dictionary, pop it from the top of the heap and subtract one from its delayed deletion count. If the delayed deletion count of the element is zero, delete it from the delayed deletion dictionary.

In the `rebalance()` method, if the size of the smaller queue is greater than $x$, add the top element of the smaller queue to the larger queue and call the `prune()` method to ensure that the top element of the smaller queue is not in the delayed deletion dictionary. If the size of the smaller queue is less than $x$ and the size of the larger queue is greater than $0$, add the top element of the larger queue to the smaller queue and call the `prune()` method to ensure that the top element of the larger queue is not in the delayed deletion dictionary.

The time complexity is $O(n \times \log n)$, and the space complexity is $O(n)$. Where $n$ is the length of the array `nums`.

Similar problems:

-   [480. Sliding Window Median](https://github.com/doocs/leetcode/blob/main/solution/0400-0499/0480.Sliding%20Window%20Median/README.md)

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

<!-- solution:end -->

<!-- problem:end -->
