# [1005. Maximize Sum Of Array After K Negations](https://leetcode.com/problems/maximize-sum-of-array-after-k-negations)

[中文文档](/solution/1000-1099/1005.Maximize%20Sum%20Of%20Array%20After%20K%20Negations/README.md)

## Description

<p>Given an array <code>A</code> of integers, we <strong>must</strong>&nbsp;modify the array in the following way: we choose an <code>i</code>&nbsp;and replace&nbsp;<code>A[i]</code> with <code>-A[i]</code>, and we repeat this process <code>K</code> times in total.&nbsp; (We may choose the same index <code>i</code> multiple times.)</p>

<p>Return the largest possible sum of the array after modifying it in this way.</p>

<p>&nbsp;</p>

<p><strong>Example 1:</strong></p>

<pre>

<strong>Input: </strong>A = <span id="example-input-1-1">[4,2,3]</span>, K = <span id="example-input-1-2">1</span>

<strong>Output: </strong><span id="example-output-1">5

<strong>Explanation: </strong>Choose indices (1,) and A becomes [4,-2,3].</span>

</pre>

<div>

<p><strong>Example 2:</strong></p>

<pre>

<strong>Input: </strong>A = <span id="example-input-2-1">[3,-1,0,2]</span>, K = <span id="example-input-2-2">3</span>

<strong>Output: </strong>6

<span id="example-output-1"><strong>Explanation: </strong>Choose indices (1, 2, 2) and A becomes [3,1,0,2].</span>

</pre>

<div>

<p><strong>Example 3:</strong></p>

<pre>

<strong>Input: </strong>A = <span id="example-input-3-1">[2,-3,-1,5,-4]</span>, K = <span id="example-input-3-2">2</span>

<strong>Output: </strong><span id="example-output-3">13

</span><span id="example-output-1"><strong>Explanation: </strong>Choose indices (1, 4) and A becomes [2,3,-1,5,4].</span>

</pre>

</div>

</div>

<p>&nbsp;</p>

<p><strong>Note:</strong></p>

<ol>
	<li><code>1 &lt;= A.length &lt;= 10000</code></li>
	<li><code>1 &lt;= K &lt;= 10000</code></li>
	<li><code>-100 &lt;= A[i] &lt;= 100</code></li>
</ol>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def largestSumAfterKNegations(self, nums: List[int], k: int) -> int:
        counter = Counter(nums)
        ans = sum(nums)
        for i in range(-100, 0):
            if counter[i]:
                ops = min(counter[i], k)
                ans -= (i * ops * 2)
                counter[i] -= ops
                counter[-i] += ops
                k -= ops
                if k == 0:
                    break
        if k > 0 and k % 2 == 1 and not counter[0]:
            for i in range(1, 101):
                if counter[i]:
                    ans -= 2 * i
                    break
        return ans
```

### **Java**

```java
class Solution {
    public int largestSumAfterKNegations(int[] nums, int k) {
        int ans = 0;
        Map<Integer, Integer> counter = new HashMap<>();
        for (int num : nums) {
            ans += num;
            counter.put(num, counter.getOrDefault(num, 0) + 1);
        }
        for (int i = -100; i < 0; ++i) {
            if (counter.getOrDefault(i, 0) > 0) {
                int ops = Math.min(counter.get(i), k);
                ans -= (i * ops * 2);
                counter.put(i, counter.getOrDefault(i, 0) - ops);
                counter.put(-i, counter.getOrDefault(-i, 0) + ops);
                k -= ops;
                if (k == 0) {
                    break;
                }
            }
        }
        if (k > 0 && (k % 2) == 1 && counter.get(0) == null) {
            for (int i = 1; i < 101; ++i) {
                if (counter.getOrDefault(i, 0) > 0) {
                    ans -= 2 * i;
                    break;
                }
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
    int largestSumAfterKNegations(vector<int>& nums, int k) {
        unordered_map<int, int> counter;
        for (int num: nums) ++counter[num];
        int ans = accumulate(nums.begin(), nums.end(), 0);
        for (int i = -100; i < 0; ++i)
        {
            if (counter[i])
            {
                int ops = min(counter[i], k);
                ans -= (i * ops * 2);
                counter[i] -= ops;
                counter[-i] += ops;
                k -= ops;
                if (k == 0) break;
            }
        }
        if (k > 0 && k % 2 == 1 && !counter[0])
        {
            for (int i = 1; i < 101; ++i)
            {
                if (counter[i])
                {
                    ans -= 2 * i;
                    break;
                }
            }
        }
        return ans;
    }
};
```

### **Go**

```cpp
func largestSumAfterKNegations(nums []int, k int) int {
	ans := 0
	counter := make(map[int]int)
	for _, num := range nums {
		ans += num
		counter[num]++
	}
	for i := -100; i < 0; i++ {
		if counter[i] > 0 {
			ops := min(counter[i], k)
			ans -= (i * ops * 2)
			counter[i] -= ops
			counter[-i] += ops
			k -= ops
			if k == 0 {
				break
			}
		}
	}
	if k > 0 && k%2 == 1 && counter[0] == 0 {
		for i := 1; i < 101; i++ {
			if counter[i] > 0 {
				ans -= 2 * i
				break
			}
		}
	}
	return ans
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}
```

### **...**

```

```

<!-- tabs:end -->
