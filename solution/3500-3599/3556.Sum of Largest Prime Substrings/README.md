---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3500-3599/3556.Sum%20of%20Largest%20Prime%20Substrings/README.md
rating: 1439
source: 第 157 场双周赛 Q1
tags:
    - 哈希表
    - 数学
    - 字符串
    - 数论
    - 排序
---

<!-- problem:start -->

# [3556. 最大质数子字符串之和](https://leetcode.cn/problems/sum-of-largest-prime-substrings)

[English Version](/solution/3500-3599/3556.Sum%20of%20Largest%20Prime%20Substrings/README_EN.md)

## 题目描述

<!-- description:start -->

<p data-end="157" data-start="30">给定一个字符串 <code>s</code>，找出可以由其&nbsp;<strong>子字符串&nbsp;</strong>组成的&nbsp;<strong>3个最大的不同质数&nbsp;</strong>的和。</p>

<p data-end="269" data-start="166">返回这些质数的&nbsp;<strong>总和&nbsp;</strong>，如果少于 3 个不同的质数，则返回&nbsp;<strong>所有&nbsp;</strong>不同质数的和。</p>

<p data-end="269" data-start="166">质数是大于 1 且只有两个因数的自然数：1和它本身。</p>

<p data-end="269" data-start="166"><strong>子字符串&nbsp;</strong>是字符串中的一个连续字符序列。&nbsp;</p>

<p data-end="370" data-is-last-node="" data-is-only-node="" data-start="271"><strong data-end="280" data-start="271">注意：</strong>每个质数即使出现在&nbsp;<strong>多个&nbsp;</strong>子字符串中，也只能计算&nbsp;<strong>一次&nbsp;</strong>。此外，将子字符串转换为整数时，忽略任何前导零。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">s = "12234"</span></p>

<p><strong>输出：</strong> <span class="example-io">1469</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li data-end="136" data-start="16">由 <code>"12234"</code> 的子字符串形成的不同质数为 2 ，3 ，23 ，223 和 1223。</li>
	<li data-end="226" data-start="137">最大的 3 个质数是 1223、223 和 23。它们的和是 1469。</li>
</ul>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">s = "111"</span></p>

<p><strong>输出：</strong> <span class="example-io">11</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li data-end="339" data-start="244">由 <code>"111"</code> 的子字符串形成的不同质数是 11。</li>
	<li data-end="412" data-is-last-node="" data-start="340">由于只有一个质数，所以结果是 11。</li>
</ul>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li data-end="39" data-start="18"><code>1 &lt;= s.length &lt;= 10</code></li>
	<li data-end="68" data-is-last-node="" data-start="40"><code>s</code> 仅由数字组成。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：枚举 + 哈希表

我们可以枚举所有的子字符串，然后判断它们是否是质数。由于题目要求我们返回最大的 3 个不同质数的和，因此我们可以使用一个哈希表来存储所有的质数。

在遍历完所有的子字符串后，我们将哈希表中的质数按从小到大的顺序排序，然后取出最大的 3 个质数进行求和。

如果哈希表中质数的数量小于 3，则返回所有质数的和。

时间复杂度 $O(n^2 \times \sqrt{M})$，空间复杂度 $O(n^2)$，其中 $n$ 为字符串的长度，而 $M$ 为字符串中最大的子字符串的值。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def sumOfLargestPrimes(self, s: str) -> int:
        def is_prime(x: int) -> bool:
            if x < 2:
                return False
            return all(x % i for i in range(2, int(sqrt(x)) + 1))

        st = set()
        n = len(s)
        for i in range(n):
            x = 0
            for j in range(i, n):
                x = x * 10 + int(s[j])
                if is_prime(x):
                    st.add(x)
        return sum(sorted(st)[-3:])
```

#### Java

```java
class Solution {
    public long sumOfLargestPrimes(String s) {
        Set<Long> st = new HashSet<>();
        int n = s.length();

        for (int i = 0; i < n; i++) {
            long x = 0;
            for (int j = i; j < n; j++) {
                x = x * 10 + (s.charAt(j) - '0');
                if (is_prime(x)) {
                    st.add(x);
                }
            }
        }

        List<Long> sorted = new ArrayList<>(st);
        Collections.sort(sorted);

        long ans = 0;
        int start = Math.max(0, sorted.size() - 3);
        for (int idx = start; idx < sorted.size(); idx++) {
            ans += sorted.get(idx);
        }
        return ans;
    }

    private boolean is_prime(long x) {
        if (x < 2) return false;
        for (long i = 2; i * i <= x; i++) {
            if (x % i == 0) return false;
        }
        return true;
    }
}
```

#### C++

```cpp
class Solution {
public:
    long long sumOfLargestPrimes(string s) {
        unordered_set<long long> st;
        int n = s.size();

        for (int i = 0; i < n; ++i) {
            long long x = 0;
            for (int j = i; j < n; ++j) {
                x = x * 10 + (s[j] - '0');
                if (is_prime(x)) {
                    st.insert(x);
                }
            }
        }

        vector<long long> sorted(st.begin(), st.end());
        sort(sorted.begin(), sorted.end());

        long long ans = 0;
        int cnt = 0;
        for (int i = (int) sorted.size() - 1; i >= 0 && cnt < 3; --i, ++cnt) {
            ans += sorted[i];
        }
        return ans;
    }

private:
    bool is_prime(long long x) {
        if (x < 2) return false;
        for (long long i = 2; i * i <= x; ++i) {
            if (x % i == 0) return false;
        }
        return true;
    }
};
```

#### Go

```go
func sumOfLargestPrimes(s string) (ans int64) {
	st := make(map[int64]struct{})
	n := len(s)

	for i := 0; i < n; i++ {
		var x int64 = 0
		for j := i; j < n; j++ {
			x = x*10 + int64(s[j]-'0')
			if isPrime(x) {
				st[x] = struct{}{}
			}
		}
	}

	nums := make([]int64, 0, len(st))
	for num := range st {
		nums = append(nums, num)
	}
	sort.Slice(nums, func(i, j int) bool { return nums[i] < nums[j] })
	for i := len(nums) - 1; i >= 0 && len(nums)-i <= 3; i-- {
		ans += nums[i]
	}
	return
}

func isPrime(x int64) bool {
	if x < 2 {
		return false
	}
	sqrtX := int64(math.Sqrt(float64(x)))
	for i := int64(2); i <= sqrtX; i++ {
		if x%i == 0 {
			return false
		}
	}
	return true
}
```

#### TypeScript

```ts
function sumOfLargestPrimes(s: string): number {
    const st = new Set<number>();
    const n = s.length;

    for (let i = 0; i < n; i++) {
        let x = 0;
        for (let j = i; j < n; j++) {
            x = x * 10 + Number(s[j]);
            if (isPrime(x)) {
                st.add(x);
            }
        }
    }

    const sorted = Array.from(st).sort((a, b) => a - b);
    const topThree = sorted.slice(-3);
    return topThree.reduce((sum, val) => sum + val, 0);
}

function isPrime(x: number): boolean {
    if (x < 2) return false;
    for (let i = 2; i * i <= x; i++) {
        if (x % i === 0) return false;
    }
    return true;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
