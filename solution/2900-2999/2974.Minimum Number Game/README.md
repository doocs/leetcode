# [2974. 最小数字游戏](https://leetcode.cn/problems/minimum-number-game)

[English Version](/solution/2900-2999/2974.Minimum%20Number%20Game/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>你有一个下标从 <strong>0</strong> 开始、长度为 <strong>偶数</strong> 的整数数组 <code>nums</code> ，同时还有一个空数组 <code>arr</code> 。Alice 和 Bob 决定玩一个游戏，游戏中每一轮 Alice 和 Bob 都会各自执行一次操作。游戏规则如下：</p>

<ul>
	<li>每一轮，Alice 先从 <code>nums</code> 中移除一个<strong> 最小</strong> 元素，然后 Bob 执行同样的操作。</li>
	<li>接着，Bob 会将移除的元素添加到数组 <code>arr</code> 中，然后 Alice 也执行同样的操作。</li>
	<li>游戏持续进行，直到 <code>nums</code> 变为空。</li>
</ul>

<p>返回结果数组 <code>arr</code> 。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [5,4,2,3]
<strong>输出：</strong>[3,2,5,4]
<strong>解释：</strong>第一轮，Alice 先移除 2 ，然后 Bob 移除 3 。然后 Bob 先将 3 添加到 arr 中，接着 Alice 再将 2 添加到 arr 中。于是 arr = [3,2] 。
第二轮开始时，nums = [5,4] 。Alice 先移除 4 ，然后 Bob 移除 5 。接着他们都将元素添加到 arr 中，arr 变为 [3,2,5,4] 。
</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [2,5]
<strong>输出：</strong>[5,2]
<strong>解释：</strong>第一轮，Alice 先移除 2 ，然后 Bob 移除 5 。然后 Bob 先将 5 添加到 arr 中，接着 Alice 再将 2 添加到 arr 中。于是 arr = [5,2] 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 100</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 100</code></li>
	<li><code>nums.length % 2 == 0</code></li>
</ul>

## 解法

### 方法一：模拟 + 优先队列（小根堆）

我们可以将数组 $nums$ 中的元素依次放入一个小根堆中，每次从小根堆中取出两个元素 $a$ 和 $b$，然后依次将 $b$ 和 $a$ 放入答案数组中，直到小根堆为空。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(n)$。其中 $n$ 为数组 $nums$ 的长度。

<!-- tabs:start -->

```python
class Solution:
    def numberGame(self, nums: List[int]) -> List[int]:
        heapify(nums)
        ans = []
        while nums:
            a, b = heappop(nums), heappop(nums)
            ans.append(b)
            ans.append(a)
        return ans
```

```java
class Solution {
    public int[] numberGame(int[] nums) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int x : nums) {
            pq.offer(x);
        }
        int[] ans = new int[nums.length];
        int i = 0;
        while (!pq.isEmpty()) {
            int a = pq.poll();
            ans[i++] = pq.poll();
            ans[i++] = a;
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    vector<int> numberGame(vector<int>& nums) {
        priority_queue<int, vector<int>, greater<int>> pq;
        for (int x : nums) {
            pq.push(x);
        }
        vector<int> ans;
        while (pq.size()) {
            int a = pq.top();
            pq.pop();
            int b = pq.top();
            pq.pop();
            ans.push_back(b);
            ans.push_back(a);
        }
        return ans;
    }
};
```

```go
func numberGame(nums []int) (ans []int) {
	pq := &hp{nums}
	heap.Init(pq)
	for pq.Len() > 0 {
		a := heap.Pop(pq).(int)
		b := heap.Pop(pq).(int)
		ans = append(ans, b)
		ans = append(ans, a)
	}
	return
}

type hp struct{ sort.IntSlice }

func (h *hp) Less(i, j int) bool { return h.IntSlice[i] < h.IntSlice[j] }
func (h *hp) Pop() interface{} {
	old := h.IntSlice
	n := len(old)
	x := old[n-1]
	h.IntSlice = old[0 : n-1]
	return x
}
func (h *hp) Push(x interface{}) {
	h.IntSlice = append(h.IntSlice, x.(int))
}
```

```ts
function numberGame(nums: number[]): number[] {
    const pq = new MinPriorityQueue();
    for (const x of nums) {
        pq.enqueue(x);
    }
    const ans: number[] = [];
    while (pq.size()) {
        const a = pq.dequeue().element;
        const b = pq.dequeue().element;
        ans.push(b, a);
    }
    return ans;
}
```

```rust
use std::collections::BinaryHeap;
use std::cmp::Reverse;

impl Solution {
    pub fn number_game(nums: Vec<i32>) -> Vec<i32> {
        let mut pq = BinaryHeap::new();

        for &x in &nums {
            pq.push(Reverse(x));
        }

        let mut ans = Vec::new();

        while let Some(Reverse(a)) = pq.pop() {
            if let Some(Reverse(b)) = pq.pop() {
                ans.push(b);
                ans.push(a);
            }
        }

        ans
    }
}
```

<!-- tabs:end -->

### 方法二：排序 + 交换

我们可以将数组 $nums$ 排序，然后依次将相邻的两个元素交换位置，即可得到答案数组。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(\log n)$。其中 $n$ 为数组 $nums$ 的长度。

<!-- tabs:start -->

```python
class Solution:
    def numberGame(self, nums: List[int]) -> List[int]:
        nums.sort()
        for i in range(0, len(nums), 2):
            nums[i], nums[i + 1] = nums[i + 1], nums[i]
        return nums
```

```java
class Solution {
    public int[] numberGame(int[] nums) {
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i += 2) {
            int t = nums[i];
            nums[i] = nums[i + 1];
            nums[i + 1] = t;
        }
        return nums;
    }
}
```

```cpp
class Solution {
public:
    vector<int> numberGame(vector<int>& nums) {
        sort(nums.begin(), nums.end());
        int n = nums.size();
        for (int i = 0; i < n; i += 2) {
            swap(nums[i], nums[i + 1]);
        }
        return nums;
    }
};
```

```go
func numberGame(nums []int) []int {
	sort.Ints(nums)
	for i := 0; i < len(nums); i += 2 {
		nums[i], nums[i+1] = nums[i+1], nums[i]
	}
	return nums
}
```

```ts
function numberGame(nums: number[]): number[] {
    nums.sort((a, b) => a - b);
    for (let i = 0; i < nums.length; i += 2) {
        [nums[i], nums[i + 1]] = [nums[i + 1], nums[i]];
    }
    return nums;
}
```

```rust
impl Solution {
    pub fn number_game(nums: Vec<i32>) -> Vec<i32> {
        let mut nums = nums;
        nums.sort_unstable();
        for i in (0..nums.len()).step_by(2) {
            nums.swap(i, i + 1);
        }
        nums
    }
}
```

<!-- tabs:end -->

<!-- end -->
