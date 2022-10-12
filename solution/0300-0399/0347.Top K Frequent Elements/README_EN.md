# [347. Top K Frequent Elements](https://leetcode.com/problems/top-k-frequent-elements)

[中文文档](/solution/0300-0399/0347.Top%20K%20Frequent%20Elements/README.md)

## Description

<p>Given an integer array <code>nums</code> and an integer <code>k</code>, return <em>the</em> <code>k</code> <em>most frequent elements</em>. You may return the answer in <strong>any order</strong>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<pre><strong>Input:</strong> nums = [1,1,1,2,2,3], k = 2
<strong>Output:</strong> [1,2]
</pre><p><strong class="example">Example 2:</strong></p>
<pre><strong>Input:</strong> nums = [1], k = 1
<strong>Output:</strong> [1]
</pre>
<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>-10<sup>4</sup> &lt;= nums[i] &lt;= 10<sup>4</sup></code></li>
	<li><code>k</code> is in the range <code>[1, the number of unique elements in the array]</code>.</li>
	<li>It is <strong>guaranteed</strong> that the answer is <strong>unique</strong>.</li>
</ul>

<p>&nbsp;</p>
<p><strong>Follow up:</strong> Your algorithm&#39;s time complexity must be better than <code>O(n log n)</code>, where n is the array&#39;s size.</p>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def topKFrequent(self, nums: List[int], k: int) -> List[int]:
        cnt = Counter(nums)
        return [v[0] for v in cnt.most_common(k)]
```

```python
class Solution:
    def topKFrequent(self, nums: List[int], k: int) -> List[int]:
        cnt = Counter(nums)
        hp = []
        for num, freq in cnt.items():
            heappush(hp, (freq, num))
            if len(hp) > k:
                heappop(hp)
        return [v[1] for v in hp]
```

### **Java**

```java
class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Long> frequency = Arrays.stream(nums).boxed().collect(
            Collectors.groupingBy(Function.identity(), Collectors.counting()));
        Queue<Map.Entry<Integer, Long>> queue = new PriorityQueue<>(Map.Entry.comparingByValue());
        for (var entry : frequency.entrySet()) {
            queue.offer(entry);
            if (queue.size() > k) {
                queue.poll();
            }
        }
        return queue.stream().mapToInt(Map.Entry::getKey).toArray();
    }
}
```

```java
class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> cnt = new HashMap<>();
        for (int v : nums) {
            cnt.put(v, cnt.getOrDefault(v, 0) + 1);
        }
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        for (var e : cnt.entrySet()) {
            pq.offer(new int[] {e.getKey(), e.getValue()});
            if (pq.size() > k) {
                pq.poll();
            }
        }
        int[] ans = new int[k];
        for (int i = 0; i < k; ++i) {
            ans[i] = pq.poll()[0];
        }
        return ans;
    }
}
```

### **TypeScript**

```ts
function topKFrequent(nums: number[], k: number): number[] {
    let hashMap = new Map();
    for (let num of nums) {
        hashMap.set(num, (hashMap.get(num) || 0) + 1);
    }
    let list = [...hashMap];
    list.sort((a, b) => b[1] - a[1]);
    let ans = [];
    for (let i = 0; i < k; i++) {
        ans.push(list[i][0]);
    }
    return ans;
}
```

```ts
function topKFrequent(nums: number[], k: number): number[] {
    const map = new Map<number, number>();
    let maxCount = 0;
    for (const num of nums) {
        map.set(num, (map.get(num) ?? 0) + 1);
        maxCount = Math.max(maxCount, map.get(num));
    }

    const res = [];
    while (k > 0) {
        for (const key of map.keys()) {
            if (map.get(key) === maxCount) {
                res.push(key);
                k--;
            }
        }
        maxCount--;
    }
    return res;
}
```

### **C++**

```cpp
using pii = pair<int, int>;

class Solution {
public:
    vector<int> topKFrequent(vector<int>& nums, int k) {
        unordered_map<int, int> cnt;
        for (int v : nums) ++cnt[v];
        priority_queue<pii, vector<pii>, greater<pii>> pq;
        for (auto& [num, freq] : cnt) {
            pq.push({freq, num});
            if (pq.size() > k) {
                pq.pop();
            }
        }
        vector<int> ans(k);
        for (int i = 0; i < k; ++i) {
            ans[i] = pq.top().second;
            pq.pop();
        }
        return ans;
    }
};
```

### **Go**

```go
func topKFrequent(nums []int, k int) []int {
	cnt := map[int]int{}
	for _, v := range nums {
		cnt[v]++
	}
	h := hp{}
	for v, freq := range cnt {
		heap.Push(&h, pair{v, freq})
		if len(h) > k {
			heap.Pop(&h)
		}
	}
	ans := make([]int, k)
	for i := range ans {
		ans[i] = heap.Pop(&h).(pair).v
	}
	return ans
}

type pair struct{ v, cnt int }
type hp []pair

func (h hp) Len() int            { return len(h) }
func (h hp) Less(i, j int) bool  { return h[i].cnt < h[j].cnt }
func (h hp) Swap(i, j int)       { h[i], h[j] = h[j], h[i] }
func (h *hp) Push(v interface{}) { *h = append(*h, v.(pair)) }
func (h *hp) Pop() interface{}   { a := *h; v := a[len(a)-1]; *h = a[:len(a)-1]; return v }
```

### **Rust**

```rust
use std::collections::HashMap;
impl Solution {
    pub fn top_k_frequent(nums: Vec<i32>, k: i32) -> Vec<i32> {
        let mut map = HashMap::new();
        let mut max_count = 0;
        for &num in nums.iter() {
            let val = map.get(&num).unwrap_or(&0) + 1;
            map.insert(num, val);
            max_count = max_count.max(val);
        }
        let mut k = k as usize;
        let mut res = vec![0; k];
        while k > 0 {
            let mut next = 0;
            for key in map.keys() {
                let val = map[key];
                if val == max_count {
                    res[k - 1] = *key;
                    k -= 1;
                } else if val < max_count {
                    next = next.max(val);
                }
            }
            max_count = next;
        }
        res
    }
}
```

### **...**

```

```

<!-- tabs:end -->
