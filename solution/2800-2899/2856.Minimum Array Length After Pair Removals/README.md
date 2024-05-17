---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2800-2899/2856.Minimum%20Array%20Length%20After%20Pair%20Removals/README.md
rating: 1749
source: 第 113 场双周赛 Q2
tags:
    - 贪心
    - 数组
    - 哈希表
    - 双指针
    - 二分查找
    - 计数
---

<!-- problem:start -->

# [2856. 删除数对后的最小数组长度](https://leetcode.cn/problems/minimum-array-length-after-pair-removals)

[English Version](/solution/2800-2899/2856.Minimum%20Array%20Length%20After%20Pair%20Removals/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个下标从 <strong>0</strong> 开始的 <strong>非递减</strong> 整数数组&nbsp;<code>nums</code>&nbsp;。</p>

<p>你可以执行以下操作任意次：</p>

<ul>
	<li>选择 <strong>两个&nbsp;</strong>下标&nbsp;<code>i</code> 和&nbsp;<code>j</code>&nbsp;，满足&nbsp;<code>i &lt; j</code>&nbsp;且&nbsp;<code>nums[i] &lt; nums[j]</code>&nbsp;。</li>
	<li>将 <code>nums</code>&nbsp;中下标在&nbsp;<code>i</code> 和&nbsp;<code>j</code>&nbsp;处的元素删除。剩余元素按照原来的顺序组成新的数组，下标也重新从 <strong>0</strong>&nbsp;开始编号。</li>
</ul>

<p>请你返回一个整数，表示执行以上操作任意次后（可以执行 <strong>0</strong> 次），<code>nums</code>&nbsp;数组的 <strong>最小</strong>&nbsp;数组长度。</p>

<p>请注意，<code>nums</code> 数组是按&nbsp;<strong>非降序&nbsp;</strong>排序的。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,3,4,9]
<strong>输出：</strong>0
<strong>解释：</strong>一开始，nums = [1, 3, 4, 9] 。
第一次操作，我们选择下标 0 和 1 ，满足 nums[0] &lt; nums[1] &lt;=&gt; 1 &lt; 3 。
删除下标 0 和 1 处的元素，nums 变成 [4, 9] 。
下一次操作，我们选择下标 0 和 1 ，满足 nums[0] &lt; nums[1] &lt;=&gt; 4 &lt; 9 。
删除下标 0 和 1 处的元素，nums 变成空数组 [] 。
所以，可以得到的最小数组长度为 0 。</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [2,3,6,9]
<strong>输出：</strong>0
<strong>解释：</strong>一开始，nums = [2, 3, 6, 9] 。
第一次操作，我们选择下标 0 和 2 ，满足 nums[0] &lt; nums[2] &lt;=&gt; 2 &lt; 6 。
删除下标 0 和 2 处的元素，nums 变成 [3, 9] 。
下一次操作，我们选择下标 0 和 1 ，满足 nums[0] &lt; nums[1] &lt;=&gt; 3 &lt; 9 。
删除下标 0 和 1 处的元素，nums 变成空数组 [] 。
所以，可以得到的最小数组长度为 0 。
</pre>

<p><strong class="example">示例 3：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,1,2]
<strong>输出：</strong>1
<strong>解释：</strong>一开始，nums = [1, 1, 2] 。
第一次操作，我们选择下标 0 和 2 ，满足 nums[0] &lt; nums[2] &lt;=&gt; 1 &lt; 2 。
删除下标 0 和 2 处的元素，nums 变成 [1] 。
无法对数组再执行操作。
所以，可以得到的最小数组长度为 1 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>nums</code>&nbsp;是 <strong>非递减</strong>&nbsp;数组。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：贪心 + 优先队列（大根堆）

我们用一个哈希表 $cnt$ 统计数组 $nums$ 中每个元素的出现次数，然后将 $cnt$ 中的每个值加入一个优先队列（大根堆） $pq$ 中。每次从 $pq$ 中取出两个元素 $x$ 和 $y$，将它们的值减一，如果减一后的值仍大于 $0$，则将减一后的值重新加入 $pq$。每次从 $pq$ 中取出两个元素，表示将数组中的两个数对删除，因此数组的长度减少 $2$。当 $pq$ 的大小小于 $2$ 时，停止删除操作。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(n)$。其中 $n$ 是数组 $nums$ 的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minLengthAfterRemovals(self, nums: List[int]) -> int:
        cnt = Counter(nums)
        pq = [-x for x in cnt.values()]
        heapify(pq)
        ans = len(nums)
        while len(pq) > 1:
            x, y = -heappop(pq), -heappop(pq)
            x -= 1
            y -= 1
            if x > 0:
                heappush(pq, -x)
            if y > 0:
                heappush(pq, -y)
            ans -= 2
        return ans
```

#### Java

```java
class Solution {
    public int minLengthAfterRemovals(List<Integer> nums) {
        Map<Integer, Integer> cnt = new HashMap<>();
        for (int x : nums) {
            cnt.merge(x, 1, Integer::sum);
        }
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
        for (int x : cnt.values()) {
            pq.offer(x);
        }
        int ans = nums.size();
        while (pq.size() > 1) {
            int x = pq.poll();
            int y = pq.poll();
            x--;
            y--;
            if (x > 0) {
                pq.offer(x);
            }
            if (y > 0) {
                pq.offer(y);
            }
            ans -= 2;
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int minLengthAfterRemovals(vector<int>& nums) {
        unordered_map<int, int> cnt;
        for (int x : nums) {
            ++cnt[x];
        }
        priority_queue<int> pq;
        for (auto& [_, v] : cnt) {
            pq.push(v);
        }
        int ans = nums.size();
        while (pq.size() > 1) {
            int x = pq.top();
            pq.pop();
            int y = pq.top();
            pq.pop();
            x--;
            y--;
            if (x > 0) {
                pq.push(x);
            }
            if (y > 0) {
                pq.push(y);
            }
            ans -= 2;
        }
        return ans;
    }
};
```

#### Go

```go
func minLengthAfterRemovals(nums []int) int {
	cnt := map[int]int{}
	for _, x := range nums {
		cnt[x]++
	}
	h := &hp{}
	for _, x := range cnt {
		h.push(x)
	}
	ans := len(nums)
	for h.Len() > 1 {
		x, y := h.pop(), h.pop()
		if x > 1 {
			h.push(x - 1)
		}
		if y > 1 {
			h.push(y - 1)
		}
		ans -= 2
	}
	return ans
}

type hp struct{ sort.IntSlice }

func (h hp) Less(i, j int) bool { return h.IntSlice[i] > h.IntSlice[j] }
func (h *hp) Push(v any)        { h.IntSlice = append(h.IntSlice, v.(int)) }
func (h *hp) Pop() any {
	a := h.IntSlice
	v := a[len(a)-1]
	h.IntSlice = a[:len(a)-1]
	return v
}
func (h *hp) push(v int) { heap.Push(h, v) }
func (h *hp) pop() int   { return heap.Pop(h).(int) }
```

#### TypeScript

```ts
function minLengthAfterRemovals(nums: number[]): number {
    const cnt: Map<number, number> = new Map();
    for (const x of nums) {
        cnt.set(x, (cnt.get(x) ?? 0) + 1);
    }
    const pq = new MaxPriorityQueue();
    for (const [_, v] of cnt) {
        pq.enqueue(v);
    }
    let ans = nums.length;
    while (pq.size() > 1) {
        let x = pq.dequeue().element;
        let y = pq.dequeue().element;
        if (--x > 0) {
            pq.enqueue(x);
        }
        if (--y > 0) {
            pq.enqueue(y);
        }
        ans -= 2;
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
