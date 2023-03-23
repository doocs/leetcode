# [1005. Maximize Sum Of Array After K Negations](https://leetcode.com/problems/maximize-sum-of-array-after-k-negations)

[中文文档](/solution/1000-1099/1005.Maximize%20Sum%20Of%20Array%20After%20K%20Negations/README.md)

## Description

<p>Given an integer array <code>nums</code> and an integer <code>k</code>, modify the array in the following way:</p>

<ul>
	<li>choose an index <code>i</code> and replace <code>nums[i]</code> with <code>-nums[i]</code>.</li>
</ul>

<p>You should apply this process exactly <code>k</code> times. You may choose the same index <code>i</code> multiple times.</p>

<p>Return <em>the largest possible sum of the array after modifying it in this way</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [4,2,3], k = 1
<strong>Output:</strong> 5
<strong>Explanation:</strong> Choose index 1 and nums becomes [4,-2,3].
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [3,-1,0,2], k = 3
<strong>Output:</strong> 6
<strong>Explanation:</strong> Choose indices (1, 2, 2) and nums becomes [3,1,0,2].
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [2,-3,-1,5,-4], k = 2
<strong>Output:</strong> 13
<strong>Explanation:</strong> Choose indices (1, 4) and nums becomes [2,3,-1,5,4].
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>4</sup></code></li>
	<li><code>-100 &lt;= nums[i] &lt;= 100</code></li>
	<li><code>1 &lt;= k &lt;= 10<sup>4</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def largestSumAfterKNegations(self, nums: List[int], k: int) -> int:
        cnt = Counter(nums)
        for x in range(-100, 0):
            if cnt[x]:
                m = min(cnt[x], k)
                cnt[x] -= m
                cnt[-x] += m
                k -= m
                if k == 0:
                    break
        if k & 1 and cnt[0] == 0:
            for x in range(1, 101):
                if cnt[x]:
                    cnt[x] -= 1
                    cnt[-x] += 1
                    break
        return sum(x * v for x, v in cnt.items())
```

### **Java**

```java
class Solution {
    public int largestSumAfterKNegations(int[] nums, int k) {
        Map<Integer, Integer> cnt = new HashMap<>();
        for (int x : nums) {
            cnt.merge(x, 1, Integer::sum);
        }
        for (int x = -100; x < 0 && k > 0; ++x) {
            if (cnt.getOrDefault(x, 0) > 0) {
                int m = Math.min(cnt.get(x), k);
                cnt.merge(x, -m, Integer::sum);
                cnt.merge(-x, m, Integer::sum);
                k -= m;
            }
        }
        if ((k & 1) == 1 && cnt.getOrDefault(0, 0) == 0) {
            for (int x = 1; x <= 100; ++x) {
                if (cnt.getOrDefault(x, 0) > 0) {
                    cnt.merge(x, -1, Integer::sum);
                    cnt.merge(-x, 1, Integer::sum);
                    break;
                }
            }
        }
        int ans = 0;
        for (var e : cnt.entrySet()) {
            ans += e.getKey() * e.getValue();
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int largestSumAfterKNegations(vector<int>& nums, int k) {
        unordered_map<int, int> cnt;
        for (int& x : nums) {
            ++cnt[x];
        }
        for (int x = -100; x < 0 && k > 0; ++x) {
            if (cnt[x]) {
                int m = min(cnt[x], k);
                cnt[x] -= m;
                cnt[-x] += m;
                k -= m;
            }
        }
        if ((k & 1) && !cnt[0]) {
            for (int x = 1; x <= 100; ++x) {
                if (cnt[x]) {
                    --cnt[x];
                    ++cnt[-x];
                    break;
                }
            }
        }
        int ans = 0;
        for (auto& [x, v] : cnt) {
            ans += x * v;
        }
        return ans;
    }
};
```

### **Go**

```go
func largestSumAfterKNegations(nums []int, k int) (ans int) {
	cnt := map[int]int{}
	for _, x := range nums {
		cnt[x]++
	}
	for x := -100; x < 0 && k > 0; x++ {
		if cnt[x] > 0 {
			m := min(k, cnt[x])
			cnt[x] -= m
			cnt[-x] += m
			k -= m
		}
	}
	if k&1 == 1 && cnt[0] == 0 {
		for x := 1; x <= 100; x++ {
			if cnt[x] > 0 {
				cnt[x]--
				cnt[-x]++
				break
			}
		}
	}
	for x, v := range cnt {
		ans += x * v
	}
	return
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}
```

### **TypeScript**

```ts
function largestSumAfterKNegations(nums: number[], k: number): number {
    const cnt: Map<number, number> = new Map();
    for (const x of nums) {
        cnt.set(x, (cnt.get(x) || 0) + 1);
    }
    for (let x = -100; x < 0 && k > 0; ++x) {
        if (cnt.get(x)! > 0) {
            const m = Math.min(cnt.get(x) || 0, k);
            cnt.set(x, (cnt.get(x) || 0) - m);
            cnt.set(-x, (cnt.get(-x) || 0) + m);
            k -= m;
        }
    }
    if ((k & 1) === 1 && (cnt.get(0) || 0) === 0) {
        for (let x = 1; x <= 100; ++x) {
            if (cnt.get(x)! > 0) {
                cnt.set(x, (cnt.get(x) || 0) - 1);
                cnt.set(-x, (cnt.get(-x) || 0) + 1);
                break;
            }
        }
    }
    let ans = 0;
    for (const [key, value] of cnt.entries()) {
        ans += key * value;
    }
    return ans;
}
```

### **...**

```

```

<!-- tabs:end -->
