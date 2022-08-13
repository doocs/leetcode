# [1005. K 次取反后最大化的数组和](https://leetcode.cn/problems/maximize-sum-of-array-after-k-negations)

[English Version](/solution/1000-1099/1005.Maximize%20Sum%20Of%20Array%20After%20K%20Negations/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个整数数组 <code>nums</code> 和一个整数 <code>k</code> ，按以下方法修改该数组：</p>

<ul>
	<li>选择某个下标 <code>i</code>&nbsp;并将 <code>nums[i]</code> 替换为 <code>-nums[i]</code> 。</li>
</ul>

<p>重复这个过程恰好 <code>k</code> 次。可以多次选择同一个下标 <code>i</code> 。</p>

<p>以这种方式修改数组后，返回数组 <strong>可能的最大和</strong> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [4,2,3], k = 1
<strong>输出：</strong>5
<strong>解释：</strong>选择下标 1 ，nums 变为 [4,-2,3] 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [3,-1,0,2], k = 3
<strong>输出：</strong>6
<strong>解释：</strong>选择下标 (1, 2, 2) ，nums 变为 [3,1,0,2] 。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>nums = [2,-3,-1,5,-4], k = 2
<strong>输出：</strong>13
<strong>解释：</strong>选择下标 (1, 4) ，nums 变为 [2,3,-1,5,4] 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>4</sup></code></li>
	<li><code>-100 &lt;= nums[i] &lt;= 100</code></li>
	<li><code>1 &lt;= k &lt;= 10<sup>4</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def largestSumAfterKNegations(self, nums: List[int], k: int) -> int:
        counter = Counter(nums)
        ans = sum(nums)
        for i in range(-100, 0):
            if counter[i]:
                ops = min(counter[i], k)
                ans -= i * ops * 2
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

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
        for (int num : nums) ++counter[num];
        int ans = accumulate(nums.begin(), nums.end(), 0);
        for (int i = -100; i < 0; ++i) {
            if (counter[i]) {
                int ops = min(counter[i], k);
                ans -= (i * ops * 2);
                counter[i] -= ops;
                counter[-i] += ops;
                k -= ops;
                if (k == 0) break;
            }
        }
        if (k > 0 && k % 2 == 1 && !counter[0]) {
            for (int i = 1; i < 101; ++i) {
                if (counter[i]) {
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
