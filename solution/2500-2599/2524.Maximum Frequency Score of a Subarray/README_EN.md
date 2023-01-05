# [2524. Maximum Frequency Score of a Subarray](https://leetcode.com/problems/maximum-frequency-score-of-a-subarray)

[中文文档](/solution/2500-2599/2524.Maximum%20Frequency%20Score%20of%20a%20Subarray/README.md)

## Description

<p>You are given an integer array <code>nums</code> and a <strong>positive</strong> integer <code>k</code>.</p>

<p>The <strong>frequency score</strong> of an array is the sum of the <strong>distinct</strong> values in the array raised to the power of their <strong>frequencies</strong>, taking the sum <strong>modulo</strong> <code>10<sup>9</sup> + 7</code>.</p>

<ul>
	<li>For example, the frequency score of the array <code>[5,4,5,7,4,4]</code> is <code>(4<sup>3</sup> + 5<sup>2</sup> + 7<sup>1</sup>) modulo (10<sup>9</sup> + 7) = 96</code>.</li>
</ul>

<p>Return <em>the <strong>maximum</strong> frequency score of a <strong>subarray</strong> of size </em><code>k</code><em> in </em><code>nums</code>. You should maximize the value under the modulo and not the actual value.</p>

<p>A <strong>subarray</strong> is a contiguous part of an array.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,1,1,2,1,2], k = 3
<strong>Output:</strong> 5
<strong>Explanation:</strong> The subarray [2,1,2] has a frequency score equal to 5. It can be shown that it is the maximum frequency score we can have.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,1,1,1,1,1], k = 4
<strong>Output:</strong> 1
<strong>Explanation:</strong> All the subarrays of length 4 have a frequency score equal to 1.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= k &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>6</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def maxFrequencyScore(self, nums: List[int], k: int) -> int:
        mod = 10**9 + 7
        cnt = Counter(nums[:k])
        ans = cur = sum(pow(k, v, mod) for k, v in cnt.items()) % mod
        i = k
        while i < len(nums):
            a, b = nums[i - k], nums[i]
            if a != b:
                cur += (b - 1) * pow(b, cnt[b], mod) if cnt[b] else b
                cur -= (a - 1) * pow(a, cnt[a] - 1, mod) if cnt[a] > 1 else a
                cur %= mod
                cnt[b] += 1
                cnt[a] -= 1
                ans = max(ans, cur)
            i += 1
        return ans
```

### **Java**

```java
class Solution {
    public int maxFrequencyScore(int[] nums, int k) {
        final int mod = (int) 1e9 + 7;
        Map<Integer, Integer> cnt = new HashMap<>();
        for (int i = 0; i < k; ++i) {
            cnt.put(nums[i], cnt.getOrDefault(nums[i], 0) + 1);
        }
        long cur = 0;
        for (var e : cnt.entrySet()) {
            cur = (cur + qmi(e.getKey(), e.getValue(), mod)) % mod;
        }
        long ans = cur;
        for (int i = k; i < nums.length; ++i) {
            int a = nums[i - k];
            int b = nums[i];
            if (a != b) {
                if (cnt.getOrDefault(b, 0) > 0) {
                    cur += (b - 1) * qmi(b, cnt.get(b), mod) % mod;
                } else {
                    cur += b;
                }
                if (cnt.getOrDefault(a, 0) > 1) {
                    cur -= (a - 1) * qmi(a, cnt.get(a) - 1, mod) % mod;
                } else {
                    cur -= a;
                }
                cur = (cur + mod) % mod;
                cnt.put(b, cnt.getOrDefault(b, 0) + 1);
                cnt.put(a, cnt.getOrDefault(a, 0) - 1);
                ans = Math.max(ans, cur);
            }
        }
        return (int) ans;
    }

    long qmi(long a, long k, long p) {
        long res = 1;
        while (k != 0) {
            if ((k & 1) == 1) {
                res = res * a % p;
            }
            k >>= 1;
            a = a * a % p;
        }
        return res;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int maxFrequencyScore(vector<int>& nums, int k) {
        unordered_map<int, int> cnt;
        for (int i = 0; i < k; ++i) {
            cnt[nums[i]]++;
        }
        long cur = 0;
        const int mod = 1e9 + 7;
        for (auto& [k, v] : cnt) {
            cur = (cur + qmi(k, v, mod)) % mod;
        }
        long ans = cur;
        for (int i = k; i < nums.size(); ++i) {
            int a = nums[i - k], b = nums[i];
            if (a != b) {
                cur += cnt[b] ? (b - 1) * qmi(b, cnt[b], mod) % mod : b;
                cur -= cnt[a] > 1 ? (a - 1) * qmi(a, cnt[a] - 1, mod) % mod : a;
                cur = (cur + mod) % mod;
                ans = max(ans, cur);
                cnt[b]++;
                cnt[a]--;
            }
        }
        return ans;
    }

    long qmi(long a, long k, long p) {
        long res = 1;
        while (k != 0) {
            if ((k & 1) == 1) {
                res = res * a % p;
            }
            k >>= 1;
            a = a * a % p;
        }
        return res;
    }
};
```

### **Go**

```go
func maxFrequencyScore(nums []int, k int) int {
	cnt := map[int]int{}
	for _, v := range nums[:k] {
		cnt[v]++
	}
	cur := 0
	const mod int = 1e9 + 7
	for k, v := range cnt {
		cur = (cur + qmi(k, v, mod)) % mod
	}
	ans := cur
	for i := k; i < len(nums); i++ {
		a, b := nums[i-k], nums[i]
		if a != b {
			if cnt[b] > 0 {
				cur += (b - 1) * qmi(b, cnt[b], mod) % mod
			} else {
				cur += b
			}
			if cnt[a] > 1 {
				cur -= (a - 1) * qmi(a, cnt[a]-1, mod) % mod
			} else {
				cur -= a
			}
			cur = (cur + mod) % mod
			ans = max(ans, cur)
			cnt[b]++
			cnt[a]--
		}
	}
	return ans
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}

func qmi(a, k, p int) int {
	res := 1
	for k != 0 {
		if k&1 == 1 {
			res = res * a % p
		}
		k >>= 1
		a = a * a % p
	}
	return res
}
```

### **...**

```

```

<!-- tabs:end -->
