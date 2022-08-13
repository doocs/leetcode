# [347. 前 K 个高频元素](https://leetcode.cn/problems/top-k-frequent-elements)

[English Version](/solution/0300-0399/0347.Top%20K%20Frequent%20Elements/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个整数数组 <code>nums</code> 和一个整数 <code>k</code> ，请你返回其中出现频率前 <code>k</code> 高的元素。你可以按 <strong>任意顺序</strong> 返回答案。</p>

<p> </p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入: </strong>nums = [1,1,1,2,2,3], k = 2
<strong>输出: </strong>[1,2]
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入: </strong>nums = [1], k = 1
<strong>输出: </strong>[1]</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= nums.length <= 10<sup>5</sup></code></li>
	<li><code>k</code> 的取值范围是 <code>[1, 数组中不相同的元素的个数]</code></li>
	<li>题目数据保证答案唯一，换句话说，数组中前 <code>k</code> 个高频元素的集合是唯一的</li>
</ul>

<p> </p>

<p><strong>进阶：</strong>你所设计算法的时间复杂度 <strong>必须</strong> 优于 <code>O(n log n)</code> ，其中 <code>n</code><em> </em>是数组大小。</p>

## 解法

<!-- 这里可写通用的实现逻辑 -->

经典 Top K 问题，可以用堆解决

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def topKFrequent(self, nums: List[int], k: int) -> List[int]:
        counter = Counter(nums)
        hp = []
        for num, freq in counter.items():
            if len(hp) == k:
                heappush(hp, (freq, num))
                heappop(hp)
            else:
                heappush(hp, (freq, num))
        return [t[1] for t in hp]
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
class Solution {
public:
    static bool cmp(pair<int, int>& m, pair<int, int>& n) {
        return m.second > n.second;
    }
    vector<int> topKFrequent(vector<int>& nums, int k) {
        unordered_map<int, int> counter;
        for (auto& e : nums) ++counter[e];
        priority_queue<pair<int, int>, vector<pair<int, int>>, decltype(&cmp)> pq(cmp);
        for (auto& [num, freq] : counter) {
            if (pq.size() == k) {
                pq.emplace(num, freq);
                pq.pop();
            } else
                pq.emplace(num, freq);
        }
        vector<int> ans;
        while (!pq.empty()) {
            ans.push_back(pq.top().first);
            pq.pop();
        }
        return ans;
    }
};
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
