# [1052. Grumpy Bookstore Owner](https://leetcode.com/problems/grumpy-bookstore-owner)

[中文文档](/solution/1000-1099/1052.Grumpy%20Bookstore%20Owner/README.md)

<!-- tags:Array,Sliding Window -->

## Description

<p>There is a bookstore owner that has a store open for <code>n</code> minutes. Every minute, some number of customers enter the store. You are given an integer array <code>customers</code> of length <code>n</code> where <code>customers[i]</code> is the number of the customer that enters the store at the start of the <code>i<sup>th</sup></code> minute and all those customers leave after the end of that minute.</p>

<p>On some minutes, the bookstore owner is grumpy. You are given a binary array grumpy where <code>grumpy[i]</code> is <code>1</code> if the bookstore owner is grumpy during the <code>i<sup>th</sup></code> minute, and is <code>0</code> otherwise.</p>

<p>When the bookstore owner is grumpy, the customers of that minute are not satisfied, otherwise, they are satisfied.</p>

<p>The bookstore owner knows a secret technique to keep themselves not grumpy for <code>minutes</code> consecutive minutes, but can only use it once.</p>

<p>Return <em>the maximum number of customers that can be satisfied throughout the day</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> customers = [1,0,1,2,1,1,7,5], grumpy = [0,1,0,1,0,1,0,1], minutes = 3
<strong>Output:</strong> 16
<strong>Explanation:</strong> The bookstore owner keeps themselves not grumpy for the last 3 minutes. 
The maximum number of customers that can be satisfied = 1 + 1 + 1 + 1 + 7 + 5 = 16.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> customers = [1], grumpy = [0], minutes = 1
<strong>Output:</strong> 1
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == customers.length == grumpy.length</code></li>
	<li><code>1 &lt;= minutes &lt;= n &lt;= 2 * 10<sup>4</sup></code></li>
	<li><code>0 &lt;= customers[i] &lt;= 1000</code></li>
	<li><code>grumpy[i]</code> is either <code>0</code> or <code>1</code>.</li>
</ul>

## Solutions

### Solution 1: Sliding Window

According to the problem description, we only need to count the number of customers when the boss is not angry $tot$, and add the maximum number of customers when the boss is angry within a sliding window of size `minutes` $mx$.

We define a variable $cnt$ to record the number of customers when the boss is angry within the sliding window, the initial value is the number of customers when the boss is angry in the first `minutes`. Then we traverse the array, each time we move the sliding window, we update the value of $cnt$, and at the same time update the value of $mx$.

Finally, return $tot + mx$.

The time complexity is $O(n)$, where $n$ is the length of the array `customers`. The space complexity is $O(1)$.

<!-- tabs:start -->

```python
class Solution:
    def maxSatisfied(
        self, customers: List[int], grumpy: List[int], minutes: int
    ) -> int:
        mx = cnt = sum(c * g for c, g in zip(customers[:minutes], grumpy))
        for i in range(minutes, len(customers)):
            cnt += customers[i] * grumpy[i]
            cnt -= customers[i - minutes] * grumpy[i - minutes]
            mx = max(mx, cnt)
        return sum(c * (g ^ 1) for c, g in zip(customers, grumpy)) + mx
```

```java
class Solution {
    public int maxSatisfied(int[] customers, int[] grumpy, int minutes) {
        int cnt = 0;
        int tot = 0;
        for (int i = 0; i < minutes; ++i) {
            cnt += customers[i] * grumpy[i];
            tot += customers[i] * (grumpy[i] ^ 1);
        }
        int mx = cnt;
        int n = customers.length;
        for (int i = minutes; i < n; ++i) {
            cnt += customers[i] * grumpy[i];
            cnt -= customers[i - minutes] * grumpy[i - minutes];
            mx = Math.max(mx, cnt);
            tot += customers[i] * (grumpy[i] ^ 1);
        }
        return tot + mx;
    }
}
```

```cpp
class Solution {
public:
    int maxSatisfied(vector<int>& customers, vector<int>& grumpy, int minutes) {
        int cnt = 0;
        int tot = 0;
        for (int i = 0; i < minutes; ++i) {
            cnt += customers[i] * grumpy[i];
            tot += customers[i] * (grumpy[i] ^ 1);
        }
        int mx = cnt;
        int n = customers.size();
        for (int i = minutes; i < n; ++i) {
            cnt += customers[i] * grumpy[i];
            cnt -= customers[i - minutes] * grumpy[i - minutes];
            mx = max(mx, cnt);
            tot += customers[i] * (grumpy[i] ^ 1);
        }
        return tot + mx;
    }
};
```

```go
func maxSatisfied(customers []int, grumpy []int, minutes int) int {
	var cnt, tot int
	for i, c := range customers[:minutes] {
		cnt += c * grumpy[i]
		tot += c * (grumpy[i] ^ 1)
	}
	mx := cnt
	for i := minutes; i < len(customers); i++ {
		cnt += customers[i] * grumpy[i]
		cnt -= customers[i-minutes] * grumpy[i-minutes]
		mx = max(mx, cnt)
		tot += customers[i] * (grumpy[i] ^ 1)
	}
	return tot + mx
}
```

```ts
function maxSatisfied(customers: number[], grumpy: number[], minutes: number): number {
    let [cnt, tot] = [0, 0];
    for (let i = 0; i < minutes; ++i) {
        cnt += customers[i] * grumpy[i];
        tot += customers[i] * (grumpy[i] ^ 1);
    }
    let mx = cnt;
    for (let i = minutes; i < customers.length; ++i) {
        cnt += customers[i] * grumpy[i];
        cnt -= customers[i - minutes] * grumpy[i - minutes];
        mx = Math.max(mx, cnt);
        tot += customers[i] * (grumpy[i] ^ 1);
    }
    return tot + mx;
}
```

```rust
impl Solution {
    pub fn max_satisfied(customers: Vec<i32>, grumpy: Vec<i32>, minutes: i32) -> i32 {
        let mut cnt = 0;
        let mut tot = 0;
        let minutes = minutes as usize;
        for i in 0..minutes {
            cnt += customers[i] * grumpy[i];
            tot += customers[i] * (1 - grumpy[i]);
        }
        let mut mx = cnt;
        let n = customers.len();
        for i in minutes..n {
            cnt += customers[i] * grumpy[i];
            cnt -= customers[i - minutes] * grumpy[i - minutes];
            mx = mx.max(cnt);
            tot += customers[i] * (1 - grumpy[i]);
        }
        tot + mx
    }
}
```

<!-- tabs:end -->

<!-- end -->
