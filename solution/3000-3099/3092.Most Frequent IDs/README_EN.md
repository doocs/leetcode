# [3092. Most Frequent IDs](https://leetcode.com/problems/most-frequent-ids)

[中文文档](/solution/3000-3099/3092.Most%20Frequent%20IDs/README.md)

<!-- tags: -->

## Description

<p>The problem involves tracking the frequency of IDs in a collection that changes over time. You have two integer arrays, <code>nums</code> and <code>freq</code>, of equal length <code>n</code>. Each element in <code>nums</code> represents an ID, and the corresponding element in <code>freq</code> indicates how many times that ID should be added to or removed from the collection at each step.</p>

<ul>
	<li><strong>Addition of IDs:</strong> If <code>freq[i]</code> is positive, it means <code>freq[i]</code> IDs with the value <code>nums[i]</code> are added to the collection at step <code>i</code>.</li>
	<li><strong>Removal of IDs:</strong> If <code>freq[i]</code> is negative, it means <code>-freq[i]</code> IDs with the value <code>nums[i]</code> are removed from the collection at step <code>i</code>.</li>
</ul>

<p>Return an array <code>ans</code> of length <code>n</code>, where <code>ans[i]</code> represents the <strong>count</strong> of the <em>most frequent ID</em> in the collection after the <code>i<sup>th</sup></code>&nbsp;step. If the collection is empty at any step, <code>ans[i]</code> should be 0 for that step.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [2,3,2,1], freq = [3,2,-3,1]</span></p>

<p><strong>Output:</strong> <span class="example-io">[3,3,2,2]</span></p>

<p><strong>Explanation:</strong></p>

<p>After step 0, we have 3 IDs with the value of 2. So <code>ans[0] = 3</code>.<br />
After step 1, we have 3 IDs with the value of 2 and 2 IDs with the value of 3. So <code>ans[1] = 3</code>.<br />
After step 2, we have 2 IDs with the value of 3. So <code>ans[2] = 2</code>.<br />
After step 3, we have 2 IDs with the value of 3 and 1 ID with the value of 1. So <code>ans[3] = 2</code>.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [5,5,3], freq = [2,-2,1]</span></p>

<p><strong>Output:</strong> <span class="example-io">[2,0,1]</span></p>

<p><strong>Explanation:</strong></p>

<p>After step 0, we have 2 IDs with the value of 5. So <code>ans[0] = 2</code>.<br />
After step 1, there are no IDs. So <code>ans[1] = 0</code>.<br />
After step 2, we have 1 ID with the value of 3. So <code>ans[2] = 1</code>.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length == freq.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
	<li><code>-10<sup>5</sup> &lt;= freq[i] &lt;= 10<sup>5</sup></code></li>
	<li><code>freq[i] != 0</code></li>
	<li>The input is generated<!-- notionvc: a136b55a-f319-4fa6-9247-11be9f3b1db8 --> such that the occurrences of an ID will not be negative in any step.</li>
</ul>

## Solutions

### Solution 1: Hash Table + Priority Queue (Max Heap)

We use a hash table $cnt$ to record the occurrence times of each ID, a hash table $lazy$ to record the number of times each occurrence needs to be deleted, and a priority queue $pq$ to maintain the maximum occurrence times.

For each operation $(x, f)$, we need to update the occurrence times $cnt[x]$ of $x$, which means the value of $cnt[x]$ in $lazy$ needs to increase by $1$, indicating that the number of times this occurrence needs to be deleted increases by $1$. Then we update the value of $cnt[x]$, adding $f$ to $cnt[x]$. Then we add the updated value of $cnt[x]$ to the priority queue $pq$. Then we check the top element of the priority queue $pq$. If the number of times the corresponding occurrence needs to be deleted in $lazy$ is greater than $0$, we pop the top element. Finally, we judge whether the priority queue is empty. If it is not empty, the top element is the maximum occurrence times, and we add it to the answer array.

The time complexity is $O(n \times \log n)$, and the space complexity is $O(n)$. Where $n$ is the length of the array $nums$.

<!-- tabs:start -->

```python
class Solution:
    def mostFrequentIDs(self, nums: List[int], freq: List[int]) -> List[int]:
        cnt = Counter()
        lazy = Counter()
        ans = []
        pq = []
        for x, f in zip(nums, freq):
            lazy[cnt[x]] += 1
            cnt[x] += f
            heappush(pq, -cnt[x])
            while pq and lazy[-pq[0]] > 0:
                lazy[-pq[0]] -= 1
                heappop(pq)
            ans.append(0 if not pq else -pq[0])
        return ans
```

```java
class Solution {
    public long[] mostFrequentIDs(int[] nums, int[] freq) {
        Map<Integer, Long> cnt = new HashMap<>();
        Map<Long, Integer> lazy = new HashMap<>();
        int n = nums.length;
        long[] ans = new long[n];
        PriorityQueue<Long> pq = new PriorityQueue<>(Collections.reverseOrder());
        for (int i = 0; i < n; ++i) {
            int x = nums[i], f = freq[i];
            lazy.merge(cnt.getOrDefault(x, 0L), 1, Integer::sum);
            cnt.merge(x, (long) f, Long::sum);
            pq.add(cnt.get(x));
            while (!pq.isEmpty() && lazy.getOrDefault(pq.peek(), 0) > 0) {
                lazy.merge(pq.poll(), -1, Integer::sum);
            }
            ans[i] = pq.isEmpty() ? 0 : pq.peek();
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    vector<long long> mostFrequentIDs(vector<int>& nums, vector<int>& freq) {
        unordered_map<int, long long> cnt;
        unordered_map<long long, int> lazy;
        int n = nums.size();
        vector<long long> ans(n);
        priority_queue<long long> pq;

        for (int i = 0; i < n; ++i) {
            int x = nums[i], f = freq[i];
            lazy[cnt[x]]++;
            cnt[x] += f;
            pq.push(cnt[x]);
            while (!pq.empty() && lazy[pq.top()] > 0) {
                lazy[pq.top()]--;
                pq.pop();
            }
            ans[i] = pq.empty() ? 0 : pq.top();
        }

        return ans;
    }
};
```

```go
func mostFrequentIDs(nums []int, freq []int) []int64 {
	n := len(nums)
	cnt := map[int]int{}
	lazy := map[int]int{}
	ans := make([]int64, n)
	pq := hp{}
	heap.Init(&pq)
	for i, x := range nums {
		f := freq[i]
		lazy[cnt[x]]++
		cnt[x] += f
		heap.Push(&pq, cnt[x])
		for pq.Len() > 0 && lazy[pq.IntSlice[0]] > 0 {
			lazy[pq.IntSlice[0]]--
			heap.Pop(&pq)
		}
		if pq.Len() > 0 {
			ans[i] = int64(pq.IntSlice[0])
		}
	}
	return ans
}

type hp struct{ sort.IntSlice }

func (h hp) Less(i, j int) bool { return h.IntSlice[i] > h.IntSlice[j] }
func (h *hp) Push(v any)        { h.IntSlice = append(h.IntSlice, v.(int)) }
func (h *hp) Pop() any {
	a := h.IntSlice
	v := a[len(a)-1]
	h.IntSlice = a[:len(a)-1]
	return v
}
```

<!-- tabs:end -->

<!-- end -->
