# [剑指 Offer II 061. 和最小的 k 个数对](https://leetcode.cn/problems/qn8gGX)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定两个以升序排列的整数数组 <code>nums1</code> 和<strong> </strong><code>nums2</code><strong>&nbsp;</strong>,&nbsp;以及一个整数 <code>k</code><strong>&nbsp;</strong>。</p>

<p>定义一对值&nbsp;<code>(u,v)</code>，其中第一个元素来自&nbsp;<code>nums1</code>，第二个元素来自 <code>nums2</code><strong>&nbsp;</strong>。</p>

<p>请找到和最小的 <code>k</code>&nbsp;个数对&nbsp;<code>(u<sub>1</sub>,v<sub>1</sub>)</code>, <code>&nbsp;(u<sub>2</sub>,v<sub>2</sub>)</code> &nbsp;... &nbsp;<code>(u<sub>k</sub>,v<sub>k</sub>)</code>&nbsp;。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入:</strong> nums1 = [1,7,11], nums2 = [2,4,6], k = 3
<strong>输出:</strong> [1,2],[1,4],[1,6]
<strong>解释: </strong>返回序列中的前 3 对数：
    [1,2],[1,4],[1,6],[7,2],[7,4],[11,2],[7,6],[11,4],[11,6]
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入: </strong>nums1 = [1,1,2], nums2 = [1,2,3], k = 2
<strong>输出: </strong>[1,1],[1,1]
<strong>解释: </strong>返回序列中的前 2 对数：
&nbsp;    [1,1],[1,1],[1,2],[2,1],[1,2],[2,2],[1,3],[1,3],[2,3]
</pre>

<p><strong>示例 3:</strong></p>

<pre>
<strong>输入: </strong>nums1 = [1,2], nums2 = [3], k = 3
<strong>输出:</strong> [1,3],[2,3]
<strong>解释: </strong>也可能序列中所有的数对都被返回:[1,3],[2,3]
</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>1 &lt;= nums1.length, nums2.length &lt;= 10<sup>4</sup></code></li>
	<li><code>-10<sup>9</sup> &lt;= nums1[i], nums2[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>nums1</code>, <code>nums2</code> 均为升序排列</li>
	<li><code>1 &lt;= k &lt;= 1000</code></li>
</ul>

<p>&nbsp;</p>

<p><meta charset="UTF-8" />注意：本题与主站 373&nbsp;题相同：<a href="https://leetcode.cn/problems/find-k-pairs-with-smallest-sums/">https://leetcode.cn/problems/find-k-pairs-with-smallest-sums/</a></p>

## 解法

<!-- 这里可写通用的实现逻辑 -->

大顶堆

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def kSmallestPairs(
        self, nums1: List[int], nums2: List[int], k: int
    ) -> List[List[int]]:
        hp = []
        for x in nums1[:k]:
            for y in nums2[:k]:
                heappush(hp, (-(x + y), [x, y]))
                if len(hp) > k:
                    heappop(hp)
        return [p for _, p in hp]
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        Queue<List<Integer>> pq = new PriorityQueue<>((p1, p2) -> {
            return p2.get(0) + p2.get(1) - (p1.get(0) + p1.get(1));
        });
        for (int i = 0; i < nums1.length && i < k; i++) {
            for (int j = 0; j < nums2.length && j < k; j++) {
                pq.offer(List.of(nums1[i], nums2[j]));
                if (pq.size() > k) {
                    pq.poll();
                }
            }
        }
        return new ArrayList<>(pq);
    }
}
```

### **Go**

```go
type pairHeap [][]int

func (a pairHeap) Len() int            { return len(a) }
func (a pairHeap) Swap(i, j int)       { a[i], a[j] = a[j], a[i] }
func (a pairHeap) Less(i, j int) bool  { return a[i][0]+a[i][1] > a[j][0]+a[j][1] }
func (a *pairHeap) Push(x interface{}) { *a = append(*a, x.([]int)) }
func (a *pairHeap) Pop() interface{}   { l := len(*a); tmp := (*a)[l-1]; *a = (*a)[:l-1]; return tmp }

func kSmallestPairs(nums1 []int, nums2 []int, k int) [][]int {
	var hp pairHeap
	for _, x := range nums1[:min(k, len(nums1))] {
		for _, y := range nums2[:min(k, len(nums2))] {
			heap.Push(&hp, []int{x, y})
			if len(hp) > k {
				heap.Pop(&hp)
			}
		}
	}
	return hp
}

func min(x, y int) int {
	if x < y {
		return x
	}
	return y
}
```

### **C++**

```cpp
class Solution {
public:
    vector<vector<int>> kSmallestPairs(vector<int>& nums1, vector<int>& nums2, int k) {
        using pii = pair<int, int>;
        auto cmp = [](pii p1, pii p2) { return p1.first + p1.second < p2.first + p2.second; };
        priority_queue<pii, vector<pii>, decltype(cmp)> pq(cmp);
        for (int i = 0; i < nums1.size() && i < k; ++i) {
            for (int j = 0; j < nums2.size() && j < k; ++j) {
                pq.push({nums1[i], nums2[j]});
                if (pq.size() > k) pq.pop();
            }
        }
        vector<vector<int>> ans;
        while (!pq.empty()) {
            pii p = pq.top();
            pq.pop();
            ans.push_back({p.first, p.second});
        }
        return ans;
    }
};
```

### **...**

```

```

<!-- tabs:end -->
