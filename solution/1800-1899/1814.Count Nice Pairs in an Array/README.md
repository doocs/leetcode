# [1814. 统计一个数组中好对子的数目](https://leetcode.cn/problems/count-nice-pairs-in-an-array)

[English Version](/solution/1800-1899/1814.Count%20Nice%20Pairs%20in%20an%20Array/README_EN.md)

<!-- tags:数组,哈希表,数学,计数 -->

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个数组 <code>nums</code> ，数组中只包含非负整数。定义 <code>rev(x)</code> 的值为将整数 <code>x</code> 各个数字位反转得到的结果。比方说 <code>rev(123) = 321</code> ， <code>rev(120) = 21</code> 。我们称满足下面条件的下标对 <code>(i, j)</code> 是 <strong>好的</strong> ：</p>

<ul>
	<li><code>0 &lt;= i &lt; j &lt; nums.length</code></li>
	<li><code>nums[i] + rev(nums[j]) == nums[j] + rev(nums[i])</code></li>
</ul>

<p>请你返回好下标对的数目。由于结果可能会很大，请将结果对 <code>10<sup>9</sup> + 7</code> <b>取余</b> 后返回。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre><b>输入：</b>nums = [42,11,1,97]
<b>输出：</b>2
<b>解释：</b>两个坐标对为：
 - (0,3)：42 + rev(97) = 42 + 79 = 121, 97 + rev(42) = 97 + 24 = 121 。
 - (1,2)：11 + rev(1) = 11 + 1 = 12, 1 + rev(11) = 1 + 11 = 12 。
</pre>

<p><strong>示例 2：</strong></p>

<pre><b>输入：</b>nums = [13,10,35,24,76]
<b>输出：</b>4
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
</ul>

## 解法

### 方法一：式子变换 + 哈希表

对于下标对 $(i, j)$，如果满足条件，那么有 $nums[i] + rev(nums[j]) = nums[j] + rev(nums[i])$，即 $nums[i] - nums[j] = rev(nums[j]) - rev(nums[i])$。

因此，我们可以将 $nums[i] - rev(nums[i])$ 作为哈希表的键，统计每个键出现的次数。最后计算每个键对应的值的组合数，相加得到最终的答案。

注意答案的取模操作。

时间复杂度 $O(n \times \log M)$，其中 $n$ 和 $M$ 分别是数组 `nums` 的长度和数组 `nums` 中的最大值。空间复杂度 $O(n)$。

<!-- tabs:start -->

```python
class Solution:
    def countNicePairs(self, nums: List[int]) -> int:
        def rev(x):
            y = 0
            while x:
                y = y * 10 + x % 10
                x //= 10
            return y

        cnt = Counter(x - rev(x) for x in nums)
        mod = 10**9 + 7
        return sum(v * (v - 1) // 2 for v in cnt.values()) % mod
```

```java
class Solution {
    public int countNicePairs(int[] nums) {
        Map<Integer, Integer> cnt = new HashMap<>();
        for (int x : nums) {
            int y = x - rev(x);
            cnt.merge(y, 1, Integer::sum);
        }
        final int mod = (int) 1e9 + 7;
        long ans = 0;
        for (int v : cnt.values()) {
            ans = (ans + (long) v * (v - 1) / 2) % mod;
        }
        return (int) ans;
    }

    private int rev(int x) {
        int y = 0;
        for (; x > 0; x /= 10) {
            y = y * 10 + x % 10;
        }
        return y;
    }
}
```

```cpp
class Solution {
public:
    int countNicePairs(vector<int>& nums) {
        auto rev = [](int x) {
            int y = 0;
            for (; x > 0; x /= 10) {
                y = y * 10 + x % 10;
            }
            return y;
        };
        unordered_map<int, int> cnt;
        for (int& x : nums) {
            int y = x - rev(x);
            cnt[y]++;
        }
        long long ans = 0;
        const int mod = 1e9 + 7;
        for (auto& [_, v] : cnt) {
            ans = (ans + 1ll * v * (v - 1) / 2) % mod;
        }
        return ans;
    }
};
```

```go
func countNicePairs(nums []int) (ans int) {
	rev := func(x int) (y int) {
		for ; x > 0; x /= 10 {
			y = y*10 + x%10
		}
		return
	}
	cnt := map[int]int{}
	for _, x := range nums {
		y := x - rev(x)
		cnt[y]++
	}
	const mod int = 1e9 + 7
	for _, v := range cnt {
		ans = (ans + v*(v-1)/2) % mod
	}
	return
}
```

```ts
function countNicePairs(nums: number[]): number {
    const rev = (x: number): number => {
        let y = 0;
        while (x) {
            y = y * 10 + (x % 10);
            x = Math.floor(x / 10);
        }
        return y;
    };
    const mod = 10 ** 9 + 7;
    const cnt = new Map<number, number>();
    let ans = 0;
    for (const x of nums) {
        const y = x - rev(x);
        ans = (ans + (cnt.get(y) ?? 0)) % mod;
        cnt.set(y, (cnt.get(y) ?? 0) + 1);
    }
    return ans;
}
```

```js
/**
 * @param {number[]} nums
 * @return {number}
 */
var countNicePairs = function (nums) {
    const rev = x => {
        let y = 0;
        for (; x > 0; x = Math.floor(x / 10)) {
            y = y * 10 + (x % 10);
        }
        return y;
    };
    const cnt = new Map();
    for (const x of nums) {
        const y = x - rev(x);
        cnt.set(y, (cnt.get(y) | 0) + 1);
    }
    let ans = 0;
    const mod = 1e9 + 7;
    for (const [_, v] of cnt) {
        ans = (ans + Math.floor((v * (v - 1)) / 2)) % mod;
    }
    return ans;
};
```

```cs
public class Solution {
    public int CountNicePairs(int[] nums) {
        Dictionary<int, int> cnt = new Dictionary<int, int>();
        foreach (int x in nums) {
            int y = x - Rev(x);
            cnt[y] = cnt.GetValueOrDefault(y, 0) + 1;
        }
        int mod = (int)1e9 + 7;
        long ans = 0;
        foreach (int v in cnt.Values) {
            ans = (ans + (long)v * (v - 1) / 2) % mod;
        }
        return (int)ans;
    }

    private int Rev(int x) {
        int y = 0;
        while (x > 0) {
            y = y * 10 + x % 10;
            x /= 10;
        }
        return y;
    }
}
```

<!-- tabs:end -->

### 方法二

<!-- tabs:start -->

```python
class Solution:
    def countNicePairs(self, nums: List[int]) -> int:
        def rev(x):
            y = 0
            while x:
                y = y * 10 + x % 10
                x //= 10
            return y

        ans = 0
        mod = 10**9 + 7
        cnt = Counter()
        for x in nums:
            y = x - rev(x)
            ans += cnt[y]
            cnt[y] += 1
        return ans % mod
```

```java
class Solution {
    public int countNicePairs(int[] nums) {
        Map<Integer, Integer> cnt = new HashMap<>();
        final int mod = (int) 1e9 + 7;
        int ans = 0;
        for (int x : nums) {
            int y = x - rev(x);
            ans = (ans + cnt.getOrDefault(y, 0)) % mod;
            cnt.merge(y, 1, Integer::sum);
        }
        return ans;
    }

    private int rev(int x) {
        int y = 0;
        for (; x > 0; x /= 10) {
            y = y * 10 + x % 10;
        }
        return y;
    }
}
```

```cpp
class Solution {
public:
    int countNicePairs(vector<int>& nums) {
        auto rev = [](int x) {
            int y = 0;
            for (; x > 0; x /= 10) {
                y = y * 10 + x % 10;
            }
            return y;
        };
        unordered_map<int, int> cnt;
        int ans = 0;
        const int mod = 1e9 + 7;
        for (int& x : nums) {
            int y = x - rev(x);
            ans = (ans + cnt[y]++) % mod;
        }
        return ans;
    }
};
```

```go
func countNicePairs(nums []int) (ans int) {
	rev := func(x int) (y int) {
		for ; x > 0; x /= 10 {
			y = y*10 + x%10
		}
		return
	}
	cnt := map[int]int{}
	const mod int = 1e9 + 7
	for _, x := range nums {
		y := x - rev(x)
		ans = (ans + cnt[y]) % mod
		cnt[y]++
	}
	return
}
```

```js
/**
 * @param {number[]} nums
 * @return {number}
 */
var countNicePairs = function (nums) {
    const rev = x => {
        let y = 0;
        for (; x > 0; x = Math.floor(x / 10)) {
            y = y * 10 + (x % 10);
        }
        return y;
    };
    let ans = 0;
    const mod = 1e9 + 7;
    const cnt = new Map();
    for (const x of nums) {
        const y = x - rev(x);
        const v = cnt.get(y) | 0;
        ans = (ans + v) % mod;
        cnt.set(y, v + 1);
    }
    return ans;
};
```

<!-- tabs:end -->

<!-- end -->
