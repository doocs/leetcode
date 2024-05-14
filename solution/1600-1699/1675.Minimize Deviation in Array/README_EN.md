# [1675. Minimize Deviation in Array](https://leetcode.com/problems/minimize-deviation-in-array)

[中文文档](/solution/1600-1699/1675.Minimize%20Deviation%20in%20Array/README.md)

<!-- tags:Greedy,Array,Ordered Set,Heap (Priority Queue) -->

<!-- difficulty:Hard -->

## Description

<p>You are given an array <code>nums</code> of <code>n</code> positive integers.</p>

<p>You can perform two types of operations on any element of the array any number of times:</p>

<ul>
	<li>If the element is <strong>even</strong>, <strong>divide</strong> it by <code>2</code>.

    <ul>
    	<li>For example, if the array is <code>[1,2,3,4]</code>, then you can do this operation on the last element, and the array will be <code>[1,2,3,<u>2</u>].</code></li>
    </ul>
    </li>
    <li>If the element is <strong>odd</strong>, <strong>multiply</strong> it by <code>2</code>.
    <ul>
    	<li>For example, if the array is <code>[1,2,3,4]</code>, then you can do this operation on the first element, and the array will be <code>[<u>2</u>,2,3,4].</code></li>
    </ul>
    </li>

</ul>

<p>The <strong>deviation</strong> of the array is the <strong>maximum difference</strong> between any two elements in the array.</p>

<p>Return <em>the <strong>minimum deviation</strong> the array can have after performing some number of operations.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,3,4]
<strong>Output:</strong> 1
<strong>Explanation:</strong> You can transform the array to [1,2,3,<u>2</u>], then to [<u>2</u>,2,3,2], then the deviation will be 3 - 2 = 1.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [4,1,5,20,3]
<strong>Output:</strong> 3
<strong>Explanation:</strong> You can transform the array after two operations to [4,<u>2</u>,5,<u>5</u>,3], then the deviation will be 5 - 2 = 3.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [2,10,8]
<strong>Output:</strong> 3
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == nums.length</code></li>
	<li><code>2 &lt;= n &lt;= 5 * 10<sup><span style="font-size: 10.8333px;">4</span></sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
</ul>

## Solutions

### Solution 1: Greedy + Priority Queue

Intuitively, to get the minimum offset of the array, we need to decrease the maximum value of the array and increase the minimum value of the array.

Since there are two operations that can be performed each time: multiply an odd number by $2$; divide an even number by $2$, the situation is more complex. We can multiply all odd numbers by $2$ to convert them into even numbers, which is equivalent to having only one division operation. The division operation can only reduce a certain number, and only by reducing the maximum value can the result be more optimal.

Therefore, we use a priority queue (max heap) to maintain the maximum value of the array. Each time we take out the top element of the heap for division operation, put the new value into the heap, and update the minimum value and the minimum value of the difference between the top element of the heap and the minimum value.

When the top element of the heap is an odd number, the operation stops.

The time complexity is $O(n\log n \times \log m)$. Where $n$ and $m$ are the length of the array `nums` and the maximum element of the array, respectively. Since the maximum element in the array is divided by $2$ at most $O(\log m)$ times, all elements are divided by $2$ at most $O(n\log m)$ times. Each time the heap is popped and put into operation, the time complexity is $O(\log n)$. Therefore, the total time complexity is $O(n\log n \times \log m)$.

<!-- tabs:start -->

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

type hp struct{ sort.IntSlice }

func (h *hp) Push(v any) { h.IntSlice = append(h.IntSlice, v.(int)) }
func (h *hp) Pop() any {
	a := h.IntSlice
	v := a[len(a)-1]
	h.IntSlice = a[:len(a)-1]
	return v
}
func (h *hp) Less(i, j int) bool { return h.IntSlice[i] > h.IntSlice[j] }
```

<!-- tabs:end -->

<!-- end -->
