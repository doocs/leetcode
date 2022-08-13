# [2233. Maximum Product After K Increments](https://leetcode.com/problems/maximum-product-after-k-increments)

[中文文档](/solution/2200-2299/2233.Maximum%20Product%20After%20K%20Increments/README.md)

## Description

<p>You are given an array of non-negative integers <code>nums</code> and an integer <code>k</code>. In one operation, you may choose <strong>any</strong> element from <code>nums</code> and <strong>increment</strong> it by <code>1</code>.</p>

<p>Return<em> the <strong>maximum</strong> <strong>product</strong> of </em><code>nums</code><em> after <strong>at most</strong> </em><code>k</code><em> operations. </em>Since the answer may be very large, return it <b>modulo</b> <code>10<sup>9</sup> + 7</code>. Note that you should maximize the product before taking the modulo.&nbsp;</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [0,4], k = 5
<strong>Output:</strong> 20
<strong>Explanation:</strong> Increment the first number 5 times.
Now nums = [5, 4], with a product of 5 * 4 = 20.
It can be shown that 20 is maximum product possible, so we return 20.
Note that there may be other ways to increment nums to have the maximum product.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [6,3,3,2], k = 2
<strong>Output:</strong> 216
<strong>Explanation:</strong> Increment the second number 1 time and increment the fourth number 1 time.
Now nums = [6, 4, 3, 3], with a product of 6 * 4 * 3 * 3 = 216.
It can be shown that 216 is maximum product possible, so we return 216.
Note that there may be other ways to increment nums to have the maximum product.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length, k &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= nums[i] &lt;= 10<sup>6</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def maximumProduct(self, nums: List[int], k: int) -> int:
        heapify(nums)
        for _ in range(k):
            heappush(nums, heappop(nums) + 1)
        ans = 1
        mod = 10**9 + 7
        for v in nums:
            ans = (ans * v) % mod
        return ans
```

### **Java**

```java
class Solution {
    private static final int MOD = (int) 1e9 + 7;

    public int maximumProduct(int[] nums, int k) {
        PriorityQueue<Integer> q = new PriorityQueue<>();
        for (int v : nums) {
            q.offer(v);
        }
        while (k-- > 0) {
            q.offer(q.poll() + 1);
        }
        long ans = 1;
        while (!q.isEmpty()) {
            ans = (ans * q.poll()) % MOD;
        }
        return (int) ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int maximumProduct(vector<int>& nums, int k) {
        int mod = 1e9 + 7;
        make_heap(nums.begin(), nums.end(), greater<int>());
        while (k--) {
            pop_heap(nums.begin(), nums.end(), greater<int>());
            ++nums.back();
            push_heap(nums.begin(), nums.end(), greater<int>());
        }
        long long ans = 1;
        for (int v : nums) ans = (ans * v) % mod;
        return ans;
    }
};
```

### **Go**

```go
func maximumProduct(nums []int, k int) int {
	h := hp{nums}
	for heap.Init(&h); k > 0; k-- {
		h.IntSlice[0]++
		heap.Fix(&h, 0)
	}
	ans := 1
	for _, v := range nums {
		ans = (ans * v) % (1e9 + 7)
	}
	return ans
}

type hp struct{ sort.IntSlice }

func (hp) Push(interface{})     {}
func (hp) Pop() (_ interface{}) { return }
```

### **JavaScript**

```js
/**
 * @param {number[]} nums
 * @param {number} k
 * @return {number}
 */
var maximumProduct = function (nums, k) {
    const n = nums.length;
    let pq = new MinPriorityQueue();
    for (let i = 0; i < n; i++) {
        pq.enqueue(nums[i]);
    }
    for (let i = 0; i < k; i++) {
        pq.enqueue(pq.dequeue().element + 1);
    }
    let ans = 1;
    const limit = 10 ** 9 + 7;
    for (let i = 0; i < n; i++) {
        ans = (ans * pq.dequeue().element) % limit;
    }
    return ans;
};
```

### **...**

```

```

<!-- tabs:end -->
