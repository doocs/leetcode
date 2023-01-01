# [2208. Minimum Operations to Halve Array Sum](https://leetcode.com/problems/minimum-operations-to-halve-array-sum)

[中文文档](/solution/2200-2299/2208.Minimum%20Operations%20to%20Halve%20Array%20Sum/README.md)

## Description

<p>You are given an array <code>nums</code> of positive integers. In one operation, you can choose <strong>any</strong> number from <code>nums</code> and reduce it to <strong>exactly</strong> half the number. (Note that you may choose this reduced number in future operations.)</p>

<p>Return<em> the <strong>minimum</strong> number of operations to reduce the sum of </em><code>nums</code><em> by <strong>at least</strong> half.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [5,19,8,1]
<strong>Output:</strong> 3
<strong>Explanation:</strong> The initial sum of nums is equal to 5 + 19 + 8 + 1 = 33.
The following is one of the ways to reduce the sum by at least half:
Pick the number 19 and reduce it to 9.5.
Pick the number 9.5 and reduce it to 4.75.
Pick the number 8 and reduce it to 4.
The final array is [5, 4.75, 4, 1] with a total sum of 5 + 4.75 + 4 + 1 = 14.75. 
The sum of nums has been reduced by 33 - 14.75 = 18.25, which is at least half of the initial sum, 18.25 &gt;= 33/2 = 16.5.
Overall, 3 operations were used so we return 3.
It can be shown that we cannot reduce the sum by at least half in less than 3 operations.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [3,8,20]
<strong>Output:</strong> 3
<strong>Explanation:</strong> The initial sum of nums is equal to 3 + 8 + 20 = 31.
The following is one of the ways to reduce the sum by at least half:
Pick the number 20 and reduce it to 10.
Pick the number 10 and reduce it to 5.
Pick the number 3 and reduce it to 1.5.
The final array is [1.5, 8, 5] with a total sum of 1.5 + 8 + 5 = 14.5. 
The sum of nums has been reduced by 31 - 14.5 = 16.5, which is at least half of the initial sum, 16.5 &gt;= 31/2 = 15.5.
Overall, 3 operations were used so we return 3.
It can be shown that we cannot reduce the sum by at least half in less than 3 operations.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>7</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def halveArray(self, nums: List[int]) -> int:
        s = sum(nums) / 2
        h = []
        for v in nums:
            heappush(h, -v)
        ans = 0
        while s > 0:
            t = -heappop(h) / 2
            s -= t
            heappush(h, -t)
            ans += 1
        return ans
```

### **Java**

```java
class Solution {
    public int halveArray(int[] nums) {
        long s = 0;
        PriorityQueue<Double> q = new PriorityQueue<>(Collections.reverseOrder());
        for (int v : nums) {
            q.offer(v * 1.0);
            s += v;
        }
        double d = s / 2.0;
        int ans = 0;
        while (d > 0) {
            double t = q.poll();
            d -= t / 2.0;
            q.offer(t / 2.0);
            ++ans;
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int halveArray(vector<int>& nums) {
        priority_queue<double> q;
        long long s = 0;
        for (int& v : nums) {
            s += v;
            q.push(v);
        }
        double d = s / 2.0;
        int ans = 0;
        while (d > 0) {
            double t = q.top() / 2;
            q.pop();
            d -= t;
            q.push(t);
            ++ans;
        }
        return ans;
    }
};
```

### **Go**

```go
func halveArray(nums []int) (ans int) {
    half := 0
    for i := range nums {
        nums[i] <<= 20
        half += nums[i]
    }
    h := hp{nums}
    heap.Init(&h)
    for half >>= 1; half > 0; ans++ {
        half -= h.IntSlice[0] >> 1
        h.IntSlice[0] >>= 1
        heap.Fix(&h, 0)
    }
    return
}

type hp struct{ sort.IntSlice }

func (h hp) Less(i, j int) bool { return h.IntSlice[i] > h.IntSlice[j] }
func (hp) Push(interface{})     {}
func (hp) Pop() (_ interface{}) { return }
```

### **TypeScript**

```ts

```

### **...**

```

```

<!-- tabs:end -->
