# [面试题 59 - I. 滑动窗口的最大值](https://leetcode.cn/problems/hua-dong-chuang-kou-de-zui-da-zhi-lcof/)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个数组 <code>nums</code> 和滑动窗口的大小 <code>k</code>，请找出所有滑动窗口里的最大值。</p>

<p><strong>示例:</strong></p>

<pre><strong>输入:</strong> <em>nums</em> = <code>[1,3,-1,-3,5,3,6,7]</code>, 和 <em>k</em> = 3
<strong>输出: </strong><code>[3,3,5,5,6,7] 
<strong>解释: 
</strong></code>
  滑动窗口的位置                最大值
---------------               -----
[1  3  -1] -3  5  3  6  7       3
 1 [3  -1  -3] 5  3  6  7       3
 1  3 [-1  -3  5] 3  6  7       5
 1  3  -1 [-3  5  3] 6  7       5
 1  3  -1  -3 [5  3  6] 7       6
 1  3  -1  -3  5 [3  6  7]      7</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<p>你可以假设 <em>k </em>总是有效的，在输入数组不为空的情况下，1 &le; k &le;&nbsp;输入数组的大小。</p>

<p>注意：本题与主站 239 题相同：<a href="https://leetcode.cn/problems/sliding-window-maximum/">https://leetcode.cn/problems/sliding-window-maximum/</a></p>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：单调队列**

单调队列常见模型：找出滑动窗口中的最大值/最小值。模板：

```python
q = deque()
for i in range(n):
    # 判断队头是否滑出窗口
    while q and checkout_out(q[0]):
        q.popleft()
    while q and check(q[-1]):
        q.pop()
    q.append(i)
```

时间复杂度 $O(n)$，空间复杂度 $O(k)$。其中 $n$ 为数组长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def maxSlidingWindow(self, nums: List[int], k: int) -> List[int]:
        q = deque()
        ans = []
        for i, x in enumerate(nums):
            if q and i - q[0] + 1 > k:
                q.popleft()
            while q and nums[q[-1]] <= x:
                q.pop()
            q.append(i)
            if i >= k - 1:
                ans.append(nums[q[0]])
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        int[] ans = new int[n - k + 1];
        Deque<Integer> q = new ArrayDeque<>();
        for (int i = 0; i < n; ++i) {
            if (!q.isEmpty() && i - q.peek() + 1 > k) {
                q.poll();
            }
            while (!q.isEmpty() && nums[q.peekLast()] <= nums[i]) {
                q.pollLast();
            }
            q.offer(i);
            if (i >= k - 1) {
                ans[i - k + 1] = nums[q.peek()];
            }
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<int> maxSlidingWindow(vector<int>& nums, int k) {
        vector<int> ans;
        deque<int> q;
        int n = nums.size();
        for (int i = 0; i < n; ++i) {
            if (!q.empty() && i - q.front() + 1 > k) {
                q.pop_front();
            }
            while (!q.empty() && nums[q.back()] <= nums[i]) {
                q.pop_back();
            }
            q.push_back(i);
            if (i >= k - 1) {
                ans.push_back(nums[q.front()]);
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func maxSlidingWindow(nums []int, k int) (ans []int) {
	q := []int{}
	for i, x := range nums {
		for len(q) > 0 && i-q[0]+1 > k {
			q = q[1:]
		}
		for len(q) > 0 && nums[q[len(q)-1]] <= x {
			q = q[:len(q)-1]
		}
		q = append(q, i)
		if i >= k-1 {
			ans = append(ans, nums[q[0]])
		}
	}
	return
}
```

### **JavaScript**

```js
/**
 * @param {number[]} nums
 * @param {number} k
 * @return {number[]}
 */
var maxSlidingWindow = function (nums, k) {
    const q = [];
    const n = nums.length;
    const ans = [];
    for (let i = 0; i < n; ++i) {
        while (q.length && i - q[0] + 1 > k) {
            q.shift();
        }
        while (q.length && nums[q[q.length - 1]] <= nums[i]) {
            q.pop();
        }
        q.push(i);
        if (i >= k - 1) {
            ans.push(nums[q[0]]);
        }
    }
    return ans;
};
```

### **TypeScript**

```ts
function maxSlidingWindow(nums: number[], k: number): number[] {
    const q: number[] = [];
    const n = nums.length;
    const ans: number[] = [];
    for (let i = 0; i < n; ++i) {
        while (q.length && i - q[0] + 1 > k) {
            q.shift();
        }
        while (q.length && nums[q[q.length - 1]] <= nums[i]) {
            q.pop();
        }
        q.push(i);
        if (i >= k - 1) {
            ans.push(nums[q[0]]);
        }
    }
    return ans;
}
```

### **Rust**

```rust
use std::collections::VecDeque;
impl Solution {
    pub fn max_sliding_window(nums: Vec<i32>, k: i32) -> Vec<i32> {
        let k = k as usize;
        let n = nums.len();
        let mut ans = vec![0; n - k + 1];
        let mut q = VecDeque::new();
        for i in 0..n {
            while !q.is_empty() && i - q[0] + 1 > k {
                q.pop_front();
            }
            while !q.is_empty() && nums[*q.back().unwrap()] <= nums[i] {
                q.pop_back();
            }
            q.push_back(i);
            if i >= k - 1 {
                ans[i - k + 1] = nums[q[0]]
            }
        }
        ans
    }
}
```

### **C#**

```cs
public class Solution {
    public int[] MaxSlidingWindow(int[] nums, int k) {
        if (nums.Length == 0) {
            return new int[]{};
        }
        int[] array = new int[nums.Length - (k - 1)];
        Queue<int> queue = new Queue<int>();
        int index = 0;
        for (int i = 0; i < nums.Length; i++) {
            queue.Enqueue(nums[i]);
            if (queue.Count == k) {
                array[index] = queue.Max();
                queue.Dequeue();
                index++;
            }
        }
        return array;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
