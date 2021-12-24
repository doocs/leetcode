# [347. Top K Frequent Elements](https://leetcode.com/problems/top-k-frequent-elements)

[中文文档](/solution/0300-0399/0347.Top%20K%20Frequent%20Elements/README.md)

## Description

<p>Given an integer array <code>nums</code> and an integer <code>k</code>, return <em>the</em> <code>k</code> <em>most frequent elements</em>. You may return the answer in <strong>any order</strong>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<pre><strong>Input:</strong> nums = [1,1,1,2,2,3], k = 2
<strong>Output:</strong> [1,2]
</pre><p><strong>Example 2:</strong></p>
<pre><strong>Input:</strong> nums = [1], k = 1
<strong>Output:</strong> [1]
</pre>
<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
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
        counter = Counter(nums)
        hp = []
        for num, freq in counter.items():
            if len(hp) == k:
                heapq.heappush(hp, (freq, num))
                heapq.heappop(hp)
            else:
                heapq.heappush(hp, (freq, num))
        return [t[1] for t in hp]
```

### **Java**

```java
class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Long> frequency = Arrays.stream(nums).boxed()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        Queue<Map.Entry<Integer, Long>> queue = new PriorityQueue<>(Map.Entry.comparingByValue());
        for (Map.Entry<Integer, Long> entry : frequency.entrySet()) {
            long count = entry.getValue();
            if (queue.size() == k) {
                if (count > queue.peek().getValue()) {
                    queue.poll();
                    queue.offer(entry);
                }
            } else {
                queue.offer(entry);
            }
        }

        return queue.stream().mapToInt(Map.Entry::getKey).toArray();
    }
}
```

```java
class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> counter = new HashMap<>();
        for (int num : nums) {
            counter.put(num, counter.getOrDefault(num, 0) + 1);
        }
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        counter.forEach((num, freq) -> {
            if (pq.size() == k) {
                pq.offer(new int[]{num, freq});
                pq.poll();
            } else {
                pq.offer(new int[]{num, freq});
            }
        });
        return pq.stream().mapToInt(e -> e[0]).toArray();
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

### **C++**

```cpp
class Solution {
public:
    static bool cmp(pair<int, int>& m, pair<int, int>& n) {
        return m.second > n.second;
    }
    vector<int> topKFrequent(vector<int>& nums, int k) {
        unordered_map<int, int> counter;
        for (auto& e : nums) ++counter[e];
        priority_queue<pair<int, int>, vector<pair<int, int>>, decltype(&cmp)> pq(cmp);
        for (auto& [num, freq] : counter)
        {
            if (pq.size() == k)
            {
                pq.emplace(num, freq);
                pq.pop();
            }
            else pq.emplace(num, freq);
        }
        vector<int> ans;
        while (!pq.empty())
        {
            ans.push_back(pq.top().first);
            pq.pop();
        }
        return ans;
    }
};
```

### **...**

```

```

<!-- tabs:end -->
