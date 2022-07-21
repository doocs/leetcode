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

单调队列。

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

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def maxSlidingWindow(self, nums: List[int], k: int) -> List[int]:
        q, res = deque(), []
        for i, num in enumerate(nums):
            if q and i - k + 1 > q[0]:
                q.popleft()
            while q and nums[q[-1]] <= num:
                q.pop()
            q.append(i)
            if i >= k - 1:
                res.append(nums[q[0]])
        return res

```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int index = 0, n = nums.length;
        if (k == 0 || n == 0) {
            return new int[0];
        }
        int[] res = new int[n - k + 1];
        LinkedList<Integer> q = new LinkedList<>();
        for (int i = 0; i < n; ++i) {
            while (!q.isEmpty() && nums[q.peekLast()] <= nums[i]) {
                q.pollLast();
            }
            q.addLast(i);
            if (q.peekFirst() == i - k) {
                q.pollFirst();
            }
            if (i >= k - 1) {
                res[index++] = nums[q.peekFirst()];
            }
        }
        return res;
    }
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
    if (!nums.length || !k) return [];
    if (k === 1) return nums;
    let res = [];
    let tmpMax = -Infinity;
    let len = nums.length;
    let window = [];
    for (let i = 0; i < k; i++) {
        tmpMax = Math.max(nums[i], tmpMax);
        window.push(nums[i]);
    }
    res.push(tmpMax);
    for (let i = k; i < len; i++) {
        let a = window.shift();
        window.push(nums[i]);
        if (nums[i] > tmpMax) {
            tmpMax = nums[i];
        } else if (tmpMax === a) {
            tmpMax = Math.max(...window);
        }
        res.push(tmpMax);
    }
    return res;
};
```

### **C++**

```cpp
class Solution {
public:
    vector<int> maxSlidingWindow(vector<int>& nums, int k) {
        vector<int> ans;
        deque<int> window;
        int n = nums.size();
        for (int i = 0; i < n; ++i) {
            while (!window.empty() && nums[window.back()] <= nums[i]) {
                window.pop_back();
            }
            window.push_back(i);
            if (window.front() == i - k) {
                window.pop_front();
            }
            if (i >= k - 1) {
                ans.push_back(nums[window.front()]);
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func maxSlidingWindow(nums []int, k int) []int {
	ans := make([]int, 0, len(nums)-k+1)
	window := make([]int, 0)
	for i, num := range nums {
		for len(window) != 0 && nums[window[len(window)-1]] <= num {
			window = window[:len(window)-1]
		}
		window = append(window, i)
		if window[0] == i-k {
			window = window[1:]
		}
		if i >= k-1 {
			ans = append(ans, nums[window[0]])
		}
	}
	return ans
}
```

### **TypeScript**

```ts
function maxSlidingWindow(nums: number[], k: number): number[] {
    const n = nums.length;
    const res = [];
    if (n === 0 || k === 0) {
        return res;
    }
    const queue = [];
    for (let i = 0; i < k; i++) {
        while (queue.length !== 0 && queue[queue.length - 1] < nums[i]) {
            queue.pop();
        }
        queue.push(nums[i]);
    }
    res.push(queue[0]);
    for (let i = k; i < n; i++) {
        if (queue[0] === nums[i - k]) {
            queue.shift();
        }
        while (queue.length !== 0 && queue[queue.length - 1] < nums[i]) {
            queue.pop();
        }
        queue.push(nums[i]);
        res.push(queue[0]);
    }
    return res;
}
```

### **Rust**

```rust
use std::collections::VecDeque;
impl Solution {
    pub fn max_sliding_window(nums: Vec<i32>, k: i32) -> Vec<i32> {
        let k = k as usize;
        let n = nums.len();
        if n == 0 || k == 0 {
            return Vec::new();
        }
        let mut res = vec![0; n - k + 1];
        let mut queue = VecDeque::new();
        for i in 0..k {
            while !queue.is_empty() && *queue.back().unwrap() < nums[i] {
                queue.pop_back();
            }
            queue.push_back(nums[i]);
        }
        res[0] = queue[0];
        for i in k..n {
            if nums[i - k] == queue[0] {
                queue.pop_front();
            }
            while !queue.is_empty() && *queue.back().unwrap() < nums[i] {
                queue.pop_back();
            }
            queue.push_back(nums[i]);
            res[i - k + 1] = queue[0];
        }
        res
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
