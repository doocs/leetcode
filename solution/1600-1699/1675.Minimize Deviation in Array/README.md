# [1675. 数组的最小偏移量](https://leetcode.cn/problems/minimize-deviation-in-array)

[English Version](/solution/1600-1699/1675.Minimize%20Deviation%20in%20Array/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个由 <code>n</code> 个正整数组成的数组 <code>nums</code> 。</p>

<p>你可以对数组的任意元素执行任意次数的两类操作：</p>

<ul>
	<li>如果元素是<strong> 偶数</strong> ，<strong>除以</strong> <code>2</code>
    <ul>
    	<li>例如，如果数组是 <code>[1,2,3,4]</code> ，那么你可以对最后一个元素执行此操作，使其变成 <code>[1,2,3,<strong>2</strong>]</code></li>
    </ul>
    </li>
    <li>如果元素是 <strong>奇数</strong> ，<strong>乘上</strong> <code>2</code>
    <ul>
    	<li>例如，如果数组是 <code>[1,2,3,4]</code> ，那么你可以对第一个元素执行此操作，使其变成 <code>[<strong>2</strong>,2,3,4]</code></li>
    </ul>
    </li>
</ul>

<p>数组的 <strong>偏移量</strong> 是数组中任意两个元素之间的 <strong>最大差值</strong> 。</p>

<p>返回数组在执行某些操作之后可以拥有的 <strong>最小偏移量</strong> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,2,3,4]
<strong>输出：</strong>1
<strong>解释：</strong>你可以将数组转换为 [1,2,3,<strong>2</strong>]，然后转换成 [<strong>2</strong>,2,3,2]，偏移量是 3 - 2 = 1
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [4,1,5,20,3]
<strong>输出：</strong>3
<strong>解释：</strong>两次操作后，你可以将数组转换为 [4,<strong>2</strong>,5,<strong>5</strong>,3]，偏移量是 5 - 2 = 3
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>nums = [2,10,8]
<strong>输出：</strong>3
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == nums.length</code></li>
	<li><code>2 &lt;= n &lt;= 5 * 10<sup><span style="font-size: 10.8333px;">4</span></sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：贪心 + 优先队列**

直观上，为了得到数组的最小偏移量，我们需要将减小数组的最大值，增大数组的最小值。

由于每次可以执行乘、除两种操作：将奇数乘以 $2$；将偶数除以 $2$，情况较为复杂，我们可以将奇数统一乘以 $2$，转成偶数，这样就等价于只有一种除法操作。除法操作只能减少某个数，而只有减少最大值，结果才可能更优。

因此，我们用优先队列（大根堆）维护数组的最大值，每次取出堆顶元素做除法操作，将新值放入堆中，并且更新最小值以及堆顶元素与最小值的差值的最小值。

当堆顶元素为奇数时，操作停止。

时间复杂度 $O(n\log n \times \log m)$。其中 $n$, $m$ 分别是数组 `nums` 的长度以及数组的最大元素。由于数组中的最大元素除以 $2$ 的操作最多有 $O(\log m)$ 次，因此全部元素除以 $2$ 的操作最多有 $O(n\log m)$ 次。每次弹出、放入堆的操作，时间复杂度为 $O(\log n)$。因此，总的时间复杂度为 $O(n\log n \times \log m)$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def minimumDeviation(self, nums: List[int]) -> int:
        h = []
        mi = inf
        for v in nums:
            if v & 1:
                v <<= 1
            h.append(-v)
            mi = min(mi, v)
        heapify(h)
        ans = -h[0] - mi
        while h[0] % 2 == 0:
            x = heappop(h) // 2
            heappush(h, x)
            mi = min(mi, -x)
            ans = min(ans, -h[0] - mi)
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int minimumDeviation(int[] nums) {
        PriorityQueue<Integer> q = new PriorityQueue<>((a, b) -> b - a);
        int mi = Integer.MAX_VALUE;
        for (int v : nums) {
            if (v % 2 == 1) {
                v <<= 1;
            }
            q.offer(v);
            mi = Math.min(mi, v);
        }
        int ans = q.peek() - mi;
        while (q.peek() % 2 == 0) {
            int x = q.poll() / 2;
            q.offer(x);
            mi = Math.min(mi, x);
            ans = Math.min(ans, q.peek() - mi);
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int minimumDeviation(vector<int>& nums) {
        int mi = INT_MAX;
        priority_queue<int> pq;
        for (int v : nums) {
            if (v & 1) v <<= 1;
            pq.push(v);
            mi = min(mi, v);
        }
        int ans = pq.top() - mi;
        while (pq.top() % 2 == 0) {
            int x = pq.top() >> 1;
            pq.pop();
            pq.push(x);
            mi = min(mi, x);
            ans = min(ans, pq.top() - mi);
        }
        return ans;
    }
};
```

### **Go**

```go
func minimumDeviation(nums []int) int {
	q := hp{}
	mi := math.MaxInt32
	for _, v := range nums {
		if v%2 == 1 {
			v <<= 1
		}
		heap.Push(&q, v)
		mi = min(mi, v)
	}
	ans := q.IntSlice[0] - mi
	for q.IntSlice[0]%2 == 0 {
		x := heap.Pop(&q).(int) >> 1
		heap.Push(&q, x)
		mi = min(mi, x)
		ans = min(ans, q.IntSlice[0]-mi)
	}
	return ans
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}

type hp struct{ sort.IntSlice }

func (h *hp) Push(v interface{}) { h.IntSlice = append(h.IntSlice, v.(int)) }
func (h *hp) Pop() interface{} {
	a := h.IntSlice
	v := a[len(a)-1]
	h.IntSlice = a[:len(a)-1]
	return v
}
func (h *hp) Less(i, j int) bool { return h.IntSlice[i] > h.IntSlice[j] }
```

### **...**

```

```

<!-- tabs:end -->
