# [2910. Minimum Number of Groups to Create a Valid Assignment](https://leetcode.com/problems/minimum-number-of-groups-to-create-a-valid-assignment)

[中文文档](/solution/2900-2999/2910.Minimum%20Number%20of%20Groups%20to%20Create%20a%20Valid%20Assignment/README.md)

## Description

<p>You are given a <strong>0-indexed</strong> integer array <code>nums</code> of length <code>n</code>.</p>

<p>We want to group the indices so for each index <code>i</code> in the range <code>[0, n - 1]</code>, it is assigned to <strong>exactly one</strong> group.</p>

<p>A group<strong> </strong>assignment is <strong>valid</strong> if the following conditions hold:</p>

<ul>
	<li>For every group <code>g</code>, all indices <code>i</code> assigned to group <code>g</code> have the same value in <code>nums</code>.</li>
	<li>For any two groups <code>g<sub>1</sub></code> and <code>g<sub>2</sub></code>, the <strong>difference</strong> between the <strong>number of indices</strong> assigned to <code>g<sub>1</sub></code> and <code>g<sub>2</sub></code> should <strong>not exceed</strong> <code>1</code>.</li>
</ul>

<p>Return <em>an integer denoting </em><em>the <strong>minimum</strong> number of groups needed to create a valid group assignment.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [3,2,3,2,3]
<strong>Output:</strong> 2
<strong>Explanation:</strong> One way the indices can be assigned to 2 groups is as follows, where the values in square brackets are indices:
group 1 -&gt; [0,2,4]
group 2 -&gt; [1,3]
All indices are assigned to one group.
In group 1, nums[0] == nums[2] == nums[4], so all indices have the same value.
In group 2, nums[1] == nums[3], so all indices have the same value.
The number of indices assigned to group 1 is 3, and the number of indices assigned to group 2 is 2.
Their difference doesn&#39;t exceed 1.
It is not possible to use fewer than 2 groups because, in order to use just 1 group, all indices assigned to that group must have the same value.
Hence, the answer is 2.</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [10,10,10,3,1,1]
<strong>Output:</strong> 4
<strong>Explanation:</strong> One way the indices can be assigned to 4 groups is as follows, where the values in square brackets are indices:
group 1 -&gt; [0]
group 2 -&gt; [1,2]
group 3 -&gt; [3]
group 4 -&gt; [4,5]
The group assignment above satisfies both conditions.
It can be shown that it is not possible to create a valid assignment using fewer than 4 groups.
Hence, the answer is 4.</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
</ul>

## Solutions

**Solution 1: Hash Table + Enumeration**

We use a hash table $cnt$ to count the number of occurrences of each number in the array $nums$. Let $k$ be the minimum value of the number of occurrences, and then we can enumerate the size of the groups in the range $[k,..1]$. Since the difference in size between each group is not more than $1$, the group size can be either $k$ or $k+1$.

For the current group size $k$ being enumerated, we traverse each occurrence $v$ in the hash table. If $\lfloor \frac{v}{k} \rfloor < v \bmod k$, it means that we cannot divide the occurrence $v$ into $k$ or $k+1$ groups with the same value, so we can skip this group size $k$ directly. Otherwise, it means that we can form groups, and we only need to form as many groups of size $k+1$ as possible to ensure the minimum number of groups. Therefore, we can divide $v$ numbers into $\lceil \frac{v}{k+1} \rceil$ groups and add them to the current enumerated answer. Since we enumerate $k$ from large to small, as long as we find a valid grouping scheme, it must be optimal.

The time complexity is $O(n)$, and the space complexity is $O(n)$. Here, $n$ is the length of the array $nums$.

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def minGroupsForValidAssignment(self, nums: List[int]) -> int:
        cnt = Counter(nums)
        for k in range(min(cnt.values()), 0, -1):
            ans = 0
            for v in cnt.values():
                if v // k < v % k:
                    ans = 0
                    break
                ans += (v + k) // (k + 1)
            if ans:
                return ans
```

### **Java**

```java
class Solution {
    public int minGroupsForValidAssignment(int[] nums) {
        Map<Integer, Integer> cnt = new HashMap<>();
        for (int x : nums) {
            cnt.merge(x, 1, Integer::sum);
        }
        int k = nums.length;
        for (int v : cnt.values()) {
            k = Math.min(k, v);
        }
        for (;; --k) {
            int ans = 0;
            for (int v : cnt.values()) {
                if (v / k < v % k) {
                    ans = 0;
                    break;
                }
                ans += (v + k) / (k + 1);
            }
            if (ans > 0) {
                return ans;
            }
        }
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int minGroupsForValidAssignment(vector<int>& nums) {
        unordered_map<int, int> cnt;
        for (int x : nums) {
            cnt[x]++;
        }
        int k = 1e9;
        for (auto& [_, v] : cnt) {
            ans = min(ans, v);
        }
        for (;; --k) {
            int ans = 0;
            for (auto& [_, v] : cnt) {
                if (v / k < v % k) {
                    ans = 0;
                    break;
                }
                ans += (v + k) / (k + 1);
            }
            if (ans) {
                return ans;
            }
        }
    }
};
```

### **Go**

```go
func minGroupsForValidAssignment(nums []int) int {
	cnt := map[int]int{}
	for _, x := range nums {
		cnt[x]++
	}
	k := len(nums)
	for _, v := range cnt {
		k = min(k, v)
	}
	for ; ; k-- {
		ans := 0
		for _, v := range cnt {
			if v/k < v%k {
				ans = 0
				break
			}
			ans += (v + k) / (k + 1)
		}
		if ans > 0 {
			return ans
		}
	}
}
```

### **TypeScript**

```ts
function minGroupsForValidAssignment(nums: number[]): number {
    const cnt: Map<number, number> = new Map();
    for (const x of nums) {
        cnt.set(x, (cnt.get(x) || 0) + 1);
    }
    for (let k = Math.min(...cnt.values()); ; --k) {
        let ans = 0;
        for (const [_, v] of cnt) {
            if (((v / k) | 0) < v % k) {
                ans = 0;
                break;
            }
            ans += Math.ceil(v / (k + 1));
        }
        if (ans) {
            return ans;
        }
    }
}
```

### **Rust**

```rust
use std::collections::HashMap;

impl Solution {
    pub fn min_groups_for_valid_assignment(nums: Vec<i32>) -> i32 {
        let mut cnt: HashMap<i32, i32> = HashMap::new();

        for x in nums.iter() {
            let count = cnt.entry(*x).or_insert(0);
            *count += 1;
        }

        let mut k = i32::MAX;

        for &v in cnt.values() {
            k = k.min(v);
        }

        for k in (1..=k).rev() {
            let mut ans = 0;

            for &v in cnt.values() {
                if v / k < v % k {
                    ans = 0;
                    break;
                }

                ans += (v + k) / (k + 1);
            }

            if ans > 0 {
                return ans;
            }
        }

        0
    }
}
```

### **...**

```

```

<!-- tabs:end -->
