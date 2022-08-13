# [2198. Number of Single Divisor Triplets](https://leetcode.cn/problems/number-of-single-divisor-triplets)

[English Version](/solution/2100-2199/2198.Number%20of%20Single%20Divisor%20Triplets/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>You are given a <strong>0-indexed</strong> array of positive integers <code>nums</code>. A triplet of three <strong>distinct</strong> indices <code>(i, j, k)</code> is called a <strong>single divisor triplet</strong> of <code>nums</code> if <code>nums[i] + nums[j] + nums[k]</code> is divisible by <strong>exactly one</strong> of <code>nums[i]</code>, <code>nums[j]</code>, or <code>nums[k]</code>.</p>
Return <em>the number of <strong>single divisor triplets</strong> of </em><code>nums</code><em>.</em>
<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [4,6,7,3,2]
<strong>Output:</strong> 12
<strong>Explanation:
</strong>The triplets (0, 3, 4), (0, 4, 3), (3, 0, 4), (3, 4, 0), (4, 0, 3), and (4, 3, 0) have the values of [4, 3, 2] (or a permutation of [4, 3, 2]).
4 + 3 + 2 = 9 which is only divisible by 3, so all such triplets are single divisor triplets.
The triplets (0, 2, 3), (0, 3, 2), (2, 0, 3), (2, 3, 0), (3, 0, 2), and (3, 2, 0) have the values of [4, 7, 3] (or a permutation of [4, 7, 3]).
4 + 7 + 3 = 14 which is only divisible by 7, so all such triplets are single divisor triplets.
There are 12 single divisor triplets in total.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,2]
<strong>Output:</strong> 6
<strong>Explanation:</strong>
The triplets (0, 1, 2), (0, 2, 1), (1, 0, 2), (1, 2, 0), (2, 0, 1), and (2, 1, 0) have the values of [1, 2, 2] (or a permutation of [1, 2, 2]).
1 + 2 + 2 = 5 which is only divisible by 1, so all such triplets are single divisor triplets.
There are 6 single divisor triplets in total.
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,1,1]
<strong>Output:</strong> 0
<strong>Explanation:</strong>
There are no single divisor triplets.
Note that (0, 1, 2) is not a single divisor triplet because nums[0] + nums[1] + nums[2] = 3 and 3 is divisible by nums[0], nums[1], and nums[2].
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>3 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 100</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def singleDivisorTriplet(self, nums: List[int]) -> int:
        def check(a, b, c):
            s = a + b + c
            return sum(s % x == 0 for x in [a, b, c]) == 1

        counter = Counter(nums)
        ans = 0
        for a, cnt1 in counter.items():
            for b, cnt2 in counter.items():
                for c, cnt3 in counter.items():
                    if check(a, b, c):
                        if a == b:
                            ans += cnt1 * (cnt1 - 1) * cnt3
                        elif a == c:
                            ans += cnt1 * (cnt1 - 1) * cnt2
                        elif b == c:
                            ans += cnt1 * cnt2 * (cnt2 - 1)
                        else:
                            ans += cnt1 * cnt2 * cnt3
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public long singleDivisorTriplet(int[] nums) {
        int[] counter = new int[101];
        for (int x : nums) {
            ++counter[x];
        }
        long ans = 0;
        for (int i = 1; i <= 100; ++i) {
            for (int j = 1; j <= 100; ++j) {
                for (int k = 1; k <= 100; ++k) {
                    int cnt1 = counter[i], cnt2 = counter[j], cnt3 = counter[k];
                    int s = i + j + k;
                    int cnt = 0;
                    if (s % i == 0) {
                        ++cnt;
                    }
                    if (s % j == 0) {
                        ++cnt;
                    }
                    if (s % k == 0) {
                        ++cnt;
                    }
                    if (cnt != 1) {
                        continue;
                    }
                    if (i == j) {
                        ans += (long) cnt1 * (cnt1 - 1) * cnt3;
                    } else if (i == k) {
                        ans += (long) cnt1 * (cnt1 - 1) * cnt2;
                    } else if (j == k) {
                        ans += (long) cnt1 * cnt2 * (cnt2 - 1);
                    } else {
                        ans += (long) cnt1 * cnt2 * cnt3;
                    }
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
    long long singleDivisorTriplet(vector<int>& nums) {
        vector<int> counter(101);
        for (int& x : nums) ++counter[x];
        long long ans = 0;
        for (int i = 1; i <= 100; ++i) {
            for (int j = 1; j <= 100; ++j) {
                for (int k = 1; k <= 100; ++k) {
                    int cnt1 = counter[i], cnt2 = counter[j], cnt3 = counter[k];
                    int s = i + j + k;
                    int cnt = (s % i == 0) + (s % j == 0) + (s % k == 0);
                    if (cnt != 1) continue;
                    if (i == j)
                        ans += 1ll * cnt1 * (cnt1 - 1) * cnt3;
                    else if (i == k)
                        ans += 1ll * cnt1 * (cnt1 - 1) * cnt2;
                    else if (j == k)
                        ans += 1ll * cnt1 * cnt2 * (cnt2 - 1);
                    else
                        ans += 1ll * cnt1 * cnt2 * cnt3;
                }
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func singleDivisorTriplet(nums []int) int64 {
	counter := make([]int, 101)
	for _, x := range nums {
		counter[x]++
	}
	var ans int64
	check := func(a, b, c int) bool {
		s := a + b + c
		cnt := 0
		if s%a == 0 {
			cnt++
		}
		if s%b == 0 {
			cnt++
		}
		if s%c == 0 {
			cnt++
		}
		return cnt == 1
	}
	for i := 1; i <= 100; i++ {
		for j := 1; j <= 100; j++ {
			for k := 1; k <= 100; k++ {
				if check(i, j, k) {
					cnt1, cnt2, cnt3 := counter[i], counter[j], counter[k]
					if i == j {
						ans += int64(cnt1 * (cnt1 - 1) * cnt3)
					} else if i == k {
						ans += int64(cnt1 * (cnt1 - 1) * cnt2)
					} else if j == k {
						ans += int64(cnt1 * cnt2 * (cnt2 - 1))
					} else {
						ans += int64(cnt1 * cnt2 * cnt3)
					}
				}
			}
		}
	}
	return ans
}
```

### **TypeScript**

```ts

```

### **...**

```

```

<!-- tabs:end -->
