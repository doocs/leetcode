# [350. Intersection of Two Arrays II](https://leetcode.com/problems/intersection-of-two-arrays-ii)

[中文文档](/solution/0300-0399/0350.Intersection%20of%20Two%20Arrays%20II/README.md)

## Description

<p>Given two integer arrays <code>nums1</code> and <code>nums2</code>, return <em>an array of their intersection</em>. Each element in the result must appear as many times as it shows in both arrays and you may return the result in <strong>any order</strong>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums1 = [1,2,2,1], nums2 = [2,2]
<strong>Output:</strong> [2,2]
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums1 = [4,9,5], nums2 = [9,4,9,8,4]
<strong>Output:</strong> [4,9]
<strong>Explanation:</strong> [9,4] is also accepted.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums1.length, nums2.length &lt;= 1000</code></li>
	<li><code>0 &lt;= nums1[i], nums2[i] &lt;= 1000</code></li>
</ul>

<p>&nbsp;</p>
<p><strong>Follow up:</strong></p>

<ul>
	<li>What if the given array is already sorted? How would you optimize your algorithm?</li>
	<li>What if <code>nums1</code>&#39;s size is small compared to <code>nums2</code>&#39;s size? Which algorithm is better?</li>
	<li>What if elements of <code>nums2</code> are stored on disk, and the memory is limited such that you cannot load all elements into the memory at once?</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def intersect(self, nums1: List[int], nums2: List[int]) -> List[int]:
        counter = Counter(nums1)
        res = []
        for num in nums2:
            if counter[num] > 0:
                res.append(num)
                counter[num] -= 1
        return res
```

### **Java**

```java
class Solution {
    public int[] intersect(int[] nums1, int[] nums2) {
        Map<Integer, Integer> counter = new HashMap<>();
        for (int num : nums1) {
            counter.put(num, counter.getOrDefault(num, 0) + 1);
        }
        List<Integer> t = new ArrayList<>();
        for (int num : nums2) {
            if (counter.getOrDefault(num, 0) > 0) {
                t.add(num);
                counter.put(num, counter.get(num) - 1);
            }
        }
        int[] res = new int[t.size()];
        for (int i = 0; i < res.length; ++i) {
            res[i] = t.get(i);
        }
        return res;
    }
}
```

### **JavaScript**

```js
/**
 * @param {number[]} nums1
 * @param {number[]} nums2
 * @return {number[]}
 */
var intersect = function (nums1, nums2) {
    const counter = {};
    for (const num of nums1) {
        counter[num] = (counter[num] || 0) + 1;
    }
    let res = [];
    for (const num of nums2) {
        if (counter[num] > 0) {
            res.push(num);
            counter[num] -= 1;
        }
    }
    return res;
};
```

### **C++**

```cpp
class Solution {
public:
    vector<int> intersect(vector<int>& nums1, vector<int>& nums2) {
        unordered_map<int, int> counter;
        for (int num : nums1) ++counter[num];
        vector<int> res;
        for (int num : nums2) {
            if (counter[num] > 0) {
                --counter[num];
                res.push_back(num);
            }
        }
        return res;
    }
};
```

### **Go**

```go
func intersect(nums1 []int, nums2 []int) []int {
	counter := make(map[int]int)
	for _, num := range nums1 {
		counter[num]++
	}
	var res []int
	for _, num := range nums2 {
		if counter[num] > 0 {
			counter[num]--
			res = append(res, num)
		}
	}
	return res
}
```

### **TypeScript**

```ts
function intersect(nums1: number[], nums2: number[]): number[] {
    const map = new Map<number, number>();
    for (const num of nums1) {
        map.set(num, (map.get(num) ?? 0) + 1);
    }

    const res = [];
    for (const num of nums2) {
        if (map.has(num) && map.get(num) !== 0) {
            res.push(num);
            map.set(num, map.get(num) - 1);
        }
    }
    return res;
}
```

### **Rust**

```rust
use std::collections::HashMap;
impl Solution {
    pub fn intersect(nums1: Vec<i32>, nums2: Vec<i32>) -> Vec<i32> {
        let mut map = HashMap::new();
        for num in nums1.iter() {
            *map.entry(num).or_insert(0) += 1;
        }

        let mut res = vec![];
        for num in nums2.iter() {
            if map.contains_key(num) && map.get(num).unwrap() != &0 {
                map.insert(num, map.get(&num).unwrap() - 1);
                res.push(*num);
            }
        }
        res
    }
}
```

### **PHP**

```php
class Solution {
    /**
     * @param Integer[] $nums1
     * @param Integer[] $nums2
     * @return Integer[]
     */
    function intersect($nums1, $nums2) {
        $rs = [];
        for ($i = 0; $i < count($nums1); $i++) {
            $hashtable[$nums1[$i]] += 1;
        }
        for ($j = 0; $j < count($nums2); $j++) {
            if (isset($hashtable[$nums2[$j]]) && $hashtable[$nums2[$j]] > 0) {
                array_push($rs, $nums2[$j]);
                $hashtable[$nums2[$j]] -= 1;
            }
        }
        return $rs;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
