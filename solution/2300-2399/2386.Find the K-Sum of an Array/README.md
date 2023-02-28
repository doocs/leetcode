# [2386. 找出数组的第 K 大和](https://leetcode.cn/problems/find-the-k-sum-of-an-array)

[English Version](/solution/2300-2399/2386.Find%20the%20K-Sum%20of%20an%20Array/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个整数数组 <code>nums</code> 和一个 <strong>正</strong> 整数 <code>k</code> 。你可以选择数组的任一 <strong>子序列</strong> 并且对其全部元素求和。</p>

<p>数组的 <strong>第 k 大和</strong> 定义为：可以获得的第 <code>k</code> 个 <strong>最大</strong> 子序列和（子序列和允许出现重复）</p>

<p>返回数组的 <strong>第 k 大和</strong> 。</p>

<p>子序列是一个可以由其他数组删除某些或不删除元素排生而来的数组，且派生过程不改变剩余元素的顺序。</p>

<p><strong>注意：</strong>空子序列的和视作 <code>0</code> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>nums = [2,4,-2], k = 5
<strong>输出：</strong>2
<strong>解释：</strong>所有可能获得的子序列和列出如下，按递减顺序排列：
- 6、4、4、2、<strong><em>2</em></strong>、0、0、-2
数组的第 5 大和是 2 。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>nums = [1,-2,3,4,-10,12], k = 16
<strong>输出：</strong>10
<strong>解释：</strong>数组的第 16 大和是 10 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == nums.length</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>-10<sup>9</sup> &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= k &lt;= min(2000, 2<sup>n</sup>)</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：优先队列（小根堆）**

首先，我们找到最大的子序和 $mx$，即所有正数之和。

可以发现，其他子序列的和，都可以看成在这个最大子序列和之上，减去其他部分子序列之和得到。因此，我们可以将问题转换为求第 $k$ 小的子序列和。

只需要将所有数的绝对值升序排列，之后建立小根堆，存储二元组 $(s, i)$，表示当前和为 $s$，且下一个待选择的数字的下标为 $i$ 的子序列。

每次取出堆顶，并放入两种新情况：一是再选择下一位，二是选择下一位并且不选择本位。

由于数组是从小到大排序，可以证明，这种方式能够不重不漏地按序遍历完所有的子序列和。

时间复杂度 $O(n \times \log n + k \times \log k)$。其中 $n$ 是数组 `nums` 的长度，而 $k$ 是题目中给定的 $k$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def kSum(self, nums: List[int], k: int) -> int:
        mx = 0
        for i, v in enumerate(nums):
            if v > 0:
                mx += v
            else:
                nums[i] = -v
        nums.sort()
        h = [(0, 0)]
        for _ in range(k - 1):
            s, i = heappop(h)
            if i < len(nums):
                heappush(h, (s + nums[i], i + 1))
                if i:
                    heappush(h, (s + nums[i] - nums[i - 1], i + 1))
        return mx - h[0][0]
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public long kSum(int[] nums, int k) {
        long mx = 0;
        int n = nums.length;
        for (int i = 0; i < n; ++i) {
            if (nums[i] > 0) {
                mx += nums[i];
            } else {
                nums[i] *= -1;
            }
        }
        Arrays.sort(nums);
        PriorityQueue<Pair<Long, Integer>> pq
            = new PriorityQueue<>(Comparator.comparing(Pair::getKey));
        pq.offer(new Pair<>(0L, 0));
        while (--k > 0) {
            var p = pq.poll();
            long s = p.getKey();
            int i = p.getValue();
            if (i < n) {
                pq.offer(new Pair<>(s + nums[i], i + 1));
                if (i > 0) {
                    pq.offer(new Pair<>(s + nums[i] - nums[i - 1], i + 1));
                }
            }
        }
        return mx - pq.peek().getKey();
    }
}
```

### **C++**

```cpp
using pli = pair<long long, int>;

class Solution {
public:
    long long kSum(vector<int>& nums, int k) {
        long long mx = 0;
        int n = nums.size();
        for (int i = 0; i < n; ++i) {
            if (nums[i] > 0) {
                mx += nums[i];
            } else {
                nums[i] *= -1;
            }
        }
        sort(nums.begin(), nums.end());
        priority_queue<pli, vector<pli>, greater<pli>> pq;
        pq.push({0, 0});
        while (--k) {
            auto p = pq.top();
            pq.pop();
            long long s = p.first;
            int i = p.second;
            if (i < n) {
                pq.push({s + nums[i], i + 1});
                if (i) {
                    pq.push({s + nums[i] - nums[i - 1], i + 1});
                }
            }
        }
        return mx - pq.top().first;
    }
};
```

### **Go**

```go
func kSum(nums []int, k int) int64 {
	mx := 0
	for i, v := range nums {
		if v > 0 {
			mx += v
		} else {
			nums[i] *= -1
		}
	}
	sort.Ints(nums)
	h := &hp{{0, 0}}
	for k > 1 {
		k--
		p := heap.Pop(h).(pair)
		if p.i < len(nums) {
			heap.Push(h, pair{p.sum + nums[p.i], p.i + 1})
			if p.i > 0 {
				heap.Push(h, pair{p.sum + nums[p.i] - nums[p.i-1], p.i + 1})
			}
		}
	}
	return int64(mx) - int64((*h)[0].sum)
}

type pair struct{ sum, i int }
type hp []pair

func (h hp) Len() int            { return len(h) }
func (h hp) Less(i, j int) bool  { return h[i].sum < h[j].sum }
func (h hp) Swap(i, j int)       { h[i], h[j] = h[j], h[i] }
func (h *hp) Push(v interface{}) { *h = append(*h, v.(pair)) }
func (h *hp) Pop() interface{}   { a := *h; v := a[len(a)-1]; *h = a[:len(a)-1]; return v }
```

### **TypeScript**

```ts

```

### **...**

```


```

<!-- tabs:end -->
