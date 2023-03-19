# [2593. Find Score of an Array After Marking All Elements](https://leetcode.com/problems/find-score-of-an-array-after-marking-all-elements)

[中文文档](/solution/2500-2599/2593.Find%20Score%20of%20an%20Array%20After%20Marking%20All%20Elements/README.md)

## Description

<p>You are given an array <code>nums</code> consisting of positive integers.</p>

<p>Starting with <code>score = 0</code>, apply the following algorithm:</p>

<ul>
	<li>Choose the smallest integer of the array that is not marked. If there is a tie, choose the one with the smallest index.</li>
	<li>Add the value of the chosen integer to <code>score</code>.</li>
	<li>Mark <strong>the chosen element and its two adjacent elements if they exist</strong>.</li>
	<li>Repeat until all the array elements are marked.</li>
</ul>

<p>Return <em>the score you get after applying the above algorithm</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [2,1,3,4,5,2]
<strong>Output:</strong> 7
<strong>Explanation:</strong> We mark the elements as follows:
- 1 is the smallest unmarked element, so we mark it and its two adjacent elements: [<u>2</u>,<u>1</u>,<u>3</u>,4,5,2].
- 2 is the smallest unmarked element, so we mark it and its left adjacent element: [<u>2</u>,<u>1</u>,<u>3</u>,4,<u>5</u>,<u>2</u>].
- 4 is the only remaining unmarked element, so we mark it: [<u>2</u>,<u>1</u>,<u>3</u>,<u>4</u>,<u>5</u>,<u>2</u>].
Our score is 1 + 2 + 4 = 7.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [2,3,5,1,3,2]
<strong>Output:</strong> 5
<strong>Explanation:</strong> We mark the elements as follows:
- 1 is the smallest unmarked element, so we mark it and its two adjacent elements: [2,3,<u>5</u>,<u>1</u>,<u>3</u>,2].
- 2 is the smallest unmarked element, since there are two of them, we choose the left-most one, so we mark the one at index 0 and its right adjacent element: [<u>2</u>,<u>3</u>,<u>5</u>,<u>1</u>,<u>3</u>,2].
- 2 is the only remaining unmarked element, so we mark it: [<u>2</u>,<u>3</u>,<u>5</u>,<u>1</u>,<u>3</u>,<u>2</u>].
Our score is 1 + 2 + 2 = 5.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>6</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def findScore(self, nums: List[int]) -> int:
        n = len(nums)
        vis = [False] * n
        q = [(x, i) for i, x in enumerate(nums)]
        heapify(q)
        ans = 0
        while q:
            x, i = heappop(q)
            ans += x
            vis[i] = True
            for j in (i - 1, i + 1):
                if 0 <= j < n:
                    vis[j] = True
            while q and vis[q[0][1]]:
                heappop(q)
        return ans
```

### **Java**

```java
class Solution {
    public long findScore(int[] nums) {
        int n = nums.length;
        boolean[] vis = new boolean[n];
        PriorityQueue<int[]> q = new PriorityQueue<>((a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
        for (int i = 0; i < n; ++i) {
            q.offer(new int[] {nums[i], i});
        }
        long ans = 0;
        while (!q.isEmpty()) {
            var p = q.poll();
            ans += p[0];
            vis[p[1]] = true;
            for (int j : List.of(p[1] - 1, p[1] + 1)) {
                if (j >= 0 && j < n) {
                    vis[j] = true;
                }
            }
            while (!q.isEmpty() && vis[q.peek()[1]]) {
                q.poll();
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
    long long findScore(vector<int>& nums) {
        int n = nums.size();
        vector<bool> vis(n);
        using pii = pair<int, int>;
        priority_queue<pii, vector<pii>, greater<pii>> q;
        for (int i = 0; i < n; ++i) {
            q.emplace(nums[i], i);
        }
        long long ans = 0;
        while (!q.empty()) {
            auto [x, i] = q.top();
            q.pop();
            ans += x;
            vis[i] = true;
            if (i + 1 < n) {
                vis[i + 1] = true;
            }
            if (i - 1 >= 0) {
                vis[i - 1] = true;
            }
            while (!q.empty() && vis[q.top().second]) {
                q.pop();
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func findScore(nums []int) (ans int64) {
	h := hp{}
	for i, x := range nums {
		heap.Push(&h, pair{x, i})
	}
	n := len(nums)
	vis := make([]bool, n)
	for len(h) > 0 {
		p := heap.Pop(&h).(pair)
		x, i := p.x, p.i
		ans += int64(x)
		vis[i] = true
		for _, j := range []int{i - 1, i + 1} {
			if j >= 0 && j < n {
				vis[j] = true
			}
		}
		for len(h) > 0 && vis[h[0].i] {
			heap.Pop(&h)
		}
	}
	return
}

type pair struct{ x, i int }
type hp []pair

func (h hp) Len() int            { return len(h) }
func (h hp) Less(i, j int) bool  { return h[i].x < h[j].x || (h[i].x == h[j].x && h[i].i < h[j].i) }
func (h hp) Swap(i, j int)       { h[i], h[j] = h[j], h[i] }
func (h *hp) Push(v interface{}) { *h = append(*h, v.(pair)) }
func (h *hp) Pop() interface{}   { a := *h; v := a[len(a)-1]; *h = a[:len(a)-1]; return v }
```

### **...**

```

```

<!-- tabs:end -->
