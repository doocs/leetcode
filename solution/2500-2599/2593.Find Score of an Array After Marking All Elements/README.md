# [2593. 标记所有元素后数组的分数](https://leetcode.cn/problems/find-score-of-an-array-after-marking-all-elements)

[English Version](/solution/2500-2599/2593.Find%20Score%20of%20an%20Array%20After%20Marking%20All%20Elements/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个数组&nbsp;<code>nums</code>&nbsp;，它包含若干正整数。</p>

<p>一开始分数&nbsp;<code>score = 0</code>&nbsp;，请你按照下面算法求出最后分数：</p>

<ul>
	<li>从数组中选择最小且没有被标记的整数。如果有相等元素，选择下标最小的一个。</li>
	<li>将选中的整数加到&nbsp;<code>score</code>&nbsp;中。</li>
	<li>标记 <strong>被选中元素</strong>，如果有相邻元素，则同时标记&nbsp;<strong>与它相邻的两个元素</strong>&nbsp;。</li>
	<li>重复此过程直到数组中所有元素都被标记。</li>
</ul>

<p>请你返回执行上述算法后最后的分数。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><b>输入：</b>nums = [2,1,3,4,5,2]
<b>输出：</b>7
<b>解释：</b>我们按照如下步骤标记元素：
- 1 是最小未标记元素，所以标记它和相邻两个元素：[<em><strong>2</strong></em>,<em><strong>1</strong></em>,<em><strong>3</strong></em>,4,5,2] 。
- 2 是最小未标记元素，所以标记它和左边相邻元素：[<em><strong>2</strong></em>,<em><strong>1</strong></em>,<em><strong>3</strong></em>,4,<em><strong>5</strong></em>,<em><strong>2</strong></em>] 。
- 4 是仅剩唯一未标记的元素，所以我们标记它：[<em><strong>2</strong></em>,<em><strong>1</strong></em>,<em><strong>3</strong></em>,<em><strong>4</strong></em>,<em><strong>5</strong></em>,<em><strong>2</strong></em>] 。
总得分为 1 + 2 + 4 = 7 。
</pre>

<p><strong>示例 2：</strong></p>

<pre><b>输入：</b>nums = [2,3,5,1,3,2]
<b>输出：</b>5
<b>解释：</b>我们按照如下步骤标记元素：
- 1 是最小未标记元素，所以标记它和相邻两个元素：[2,3,<em><strong>5</strong></em>,<em><strong>1</strong></em>,<em><strong>3</strong></em>,2] 。
- 2 是最小未标记元素，由于有两个 2 ，我们选择最左边的一个 2 ，也就是下标为 0 处的 2 ，以及它右边相邻的元素：[<em><strong>2</strong></em>,<em><strong>3</strong></em>,<em><strong>5</strong></em>,<em><strong>1</strong></em>,<em><strong>3</strong></em>,2] 。
- 2 是仅剩唯一未标记的元素，所以我们标记它：[<em><strong>2</strong></em>,<em><strong>3</strong></em>,<em><strong>5</strong></em>,<em><strong>1</strong></em>,<em><strong>3</strong></em>,<em><strong>2</strong></em>] 。
总得分为 1 + 2 + 2 = 5 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>6</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：优先队列（小根堆）**

我们用一个优先队列维护数组中未被标记的元素，队列中每一项为一个二元组 $(x, i)$，其中 $x$ 和 $i$ 分别表示数组中的元素值和下标，用一个数组 $vis$ 记录数组中的元素是否被标记。

每次从队列中取出最小的元素 $(x, i)$，我们将 $x$ 加入答案，然后标记 $i$ 位置的元素，以及 $i$ 位置的左右相邻元素，即 $i - 1$ 和 $i + 1$ 位置的元素。接下来判断堆顶元素是否被标记，如果被标记则弹出堆顶元素，直到堆顶元素未被标记，或者堆为空。

最后返回答案即可。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(n)$。其中 $n$ 为数组的长度。

**方法二：排序**

我们可以创建一个下标数组 $idx$，其中 $idx[i]=i$，然后我们对数组 $idx$ 按照数组 $nums$ 中的元素值进行排序，如果元素值相同，则按照下标值进行排序。

接下来创建一个长度为 $n+2$ 的数组 $vis$，其中 $vis[i]=false$，表示数组中的元素是否被标记。

我们遍历下标数组 $idx$，对于数组中的每一个下标 $i$，如果 $vis[i + 1]$ 为 $false$，则表示 $i$ 位置的元素未被标记，我们将 $nums[i]$ 加入答案，然后标记 $i$ 位置的元素，以及 $i$ 位置的左右相邻元素，即 $i - 1$ 和 $i + 1$ 位置的元素。继续遍历下标数组 $idx$，直到遍历结束。

最后返回答案即可。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(n)$。其中 $n$ 为数组的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

```python
class Solution:
    def findScore(self, nums: List[int]) -> int:
        n = len(nums)
        vis = [False] * (n + 2)
        idx = sorted(range(n), key=lambda i: (nums[i], i))
        ans = 0
        for i in idx:
            if not vis[i + 1]:
                ans += nums[i]
                vis[i] = vis[i + 2] = True
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

```java
class Solution {
    public long findScore(int[] nums) {
        int n = nums.length;
        boolean[] vis = new boolean[n + 2];
        Integer[] idx = new Integer[n];
        for (int i = 0; i < n; ++i) {
            idx[i] = i;
        }
        Arrays.sort(idx, (i, j) -> nums[i] - nums[j]);
        long ans = 0;
        for (int i : idx) {
            if (!vis[i + 1]) {
                ans += nums[i];
                vis[i] = true;
                vis[i + 2] = true;
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

```cpp
class Solution {
public:
    long long findScore(vector<int>& nums) {
        int n = nums.size();
        vector<int> idx(n);
        iota(idx.begin(), idx.end(), 0);
        sort(idx.begin(), idx.end(), [&](int i, int j) {
            return nums[i] < nums[j] || (nums[i] == nums[j] && i < j);
        });
        long long ans = 0;
        vector<bool> vis(n + 2);
        for (int i : idx) {
            if (!vis[i + 1]) {
                ans += nums[i];
                vis[i] = vis[i + 2] = true;
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

```go
func findScore(nums []int) (ans int64) {
	n := len(nums)
	idx := make([]int, n)
	for i := range idx {
		idx[i] = i
	}
	sort.Slice(idx, func(i, j int) bool {
		i, j = idx[i], idx[j]
		return nums[i] < nums[j] || (nums[i] == nums[j] && i < j)
	})
	vis := make([]bool, n+2)
	for _, i := range idx {
		if !vis[i+1] {
			ans += int64(nums[i])
			vis[i], vis[i+2] = true, true
		}
	}
	return
}
```

### **TypeScript**

```ts
interface pair {
    x: number;
    i: number;
}

function findScore(nums: number[]): number {
    const q = new PriorityQueue({
        compare: (a: pair, b: pair) => (a.x === b.x ? a.i - b.i : a.x - b.x),
    });
    const n = nums.length;
    const vis: boolean[] = new Array(n).fill(false);
    for (let i = 0; i < n; ++i) {
        q.enqueue({ x: nums[i], i });
    }
    let ans = 0;
    while (!q.isEmpty()) {
        const { x, i } = q.dequeue()!;
        if (vis[i]) {
            continue;
        }
        ans += x;
        vis[i] = true;
        if (i - 1 >= 0) {
            vis[i - 1] = true;
        }
        if (i + 1 < n) {
            vis[i + 1] = true;
        }
        while (!q.isEmpty() && vis[q.front()!.i]) {
            q.dequeue();
        }
    }
    return ans;
}
```

```ts
function findScore(nums: number[]): number {
    const n = nums.length;
    const idx: number[] = new Array(n);
    for (let i = 0; i < n; ++i) {
        idx[i] = i;
    }
    idx.sort((i, j) => (nums[i] == nums[j] ? i - j : nums[i] - nums[j]));
    const vis: boolean[] = new Array(n + 2).fill(false);
    let ans = 0;
    for (const i of idx) {
        if (!vis[i + 1]) {
            ans += nums[i];
            vis[i] = true;
            vis[i + 2] = true;
        }
    }
    return ans;
}
```

### **...**

```

```

<!-- tabs:end -->
