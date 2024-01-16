# [2967. Minimum Cost to Make Array Equalindromic](https://leetcode.com/problems/minimum-cost-to-make-array-equalindromic)

[中文文档](/solution/2900-2999/2967.Minimum%20Cost%20to%20Make%20Array%20Equalindromic/README.md)

## Description

<p>You are given a <strong>0-indexed</strong> integer array <code>nums</code> having length <code>n</code>.</p>

<p>You are allowed to perform a special move <strong>any</strong> number of times (<strong>including zero</strong>) on <code>nums</code>. In one <strong>special</strong> <strong>move</strong> you perform the following steps <strong>in order</strong>:</p>

<ul>
	<li>Choose an index <code>i</code> in the range <code>[0, n - 1]</code>, and a <strong>positive</strong> integer <code>x</code>.</li>
	<li>Add <code>|nums[i] - x|</code> to the total cost.</li>
	<li>Change the value of <code>nums[i]</code> to <code>x</code>.</li>
</ul>

<p>A <strong>palindromic number</strong> is a positive integer that remains the same when its digits are reversed. For example, <code>121</code>, <code>2552</code> and <code>65756</code> are palindromic numbers whereas <code>24</code>, <code>46</code>, <code>235</code> are not palindromic numbers.</p>

<p>An array is considered <strong>equalindromic</strong> if all the elements in the array are equal to an integer <code>y</code>, where <code>y</code> is a <strong>palindromic number</strong> less than <code>10<sup>9</sup></code>.</p>

<p>Return <em>an integer denoting the <strong>minimum</strong> possible total cost to make </em><code>nums</code><em> <strong>equalindromic</strong> by performing any number of special moves.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,3,4,5]
<strong>Output:</strong> 6
<strong>Explanation:</strong> We can make the array equalindromic by changing all elements to 3 which is a palindromic number. The cost of changing the array to [3,3,3,3,3] using 4 special moves is given by |1 - 3| + |2 - 3| + |4 - 3| + |5 - 3| = 6.
It can be shown that changing all elements to any palindromic number other than 3 cannot be achieved at a lower cost.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [10,12,13,14,15]
<strong>Output:</strong> 11
<strong>Explanation:</strong> We can make the array equalindromic by changing all elements to 11 which is a palindromic number. The cost of changing the array to [11,11,11,11,11] using 5 special moves is given by |10 - 11| + |12 - 11| + |13 - 11| + |14 - 11| + |15 - 11| = 11.
It can be shown that changing all elements to any palindromic number other than 11 cannot be achieved at a lower cost.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [22,33,22,33,22]
<strong>Output:</strong> 22
<strong>Explanation:</strong> We can make the array equalindromic by changing all elements to 22 which is a palindromic number. The cost of changing the array to [22,22,22,22,22] using 2 special moves is given by |33 - 22| + |33 - 22| = 22.
It can be shown that changing all elements to any palindromic number other than 22 cannot be achieved at a lower cost.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
</ul>

## Solutions

### Solution 1: Preprocessing + Sorting + Binary Search

The range of palindrome numbers in the problem is $[1, 10^9]$. Due to the symmetry of palindrome numbers, we can enumerate in the range of $[1, 10^5]$, then reverse and concatenate them to get all palindrome numbers. Note that if it is an odd-length palindrome number, we need to remove the last digit before reversing. The array of palindrome numbers obtained by preprocessing is denoted as $ps$. We sort the array $ps$.

Next, we sort the array $nums$ and take the median $x$ of $nums$. We only need to find a number in the palindrome array $ps$ that is closest to $x$ through binary search, and then calculate the cost of $nums$ becoming this number to get the answer.

The time complexity is $O(n \times \log n)$, and the space complexity is $O(M)$. Here, $n$ is the length of the array $nums$, and $M$ is the length of the palindrome array $ps$.

<!-- tabs:start -->

```python
ps = []
for i in range(1, 10**5 + 1):
    s = str(i)
    t1 = s[::-1]
    t2 = s[:-1][::-1]
    ps.append(int(s + t1))
    ps.append(int(s + t2))
ps.sort()


class Solution:
    def minimumCost(self, nums: List[int]) -> int:
        def f(x: int) -> int:
            return sum(abs(v - x) for v in nums)

        nums.sort()
        i = bisect_left(ps, nums[len(nums) // 2])
        return min(f(ps[j]) for j in range(i - 1, i + 2) if 0 <= j < len(ps))
```

```java
public class Solution {
    private static long[] ps;
    private int[] nums;

    static {
        ps = new long[2 * (int) 1e5];
        for (int i = 1; i <= 1e5; i++) {
            String s = Integer.toString(i);
            String t1 = new StringBuilder(s).reverse().toString();
            String t2 = new StringBuilder(s.substring(0, s.length() - 1)).reverse().toString();
            ps[2 * i - 2] = Long.parseLong(s + t1);
            ps[2 * i - 1] = Long.parseLong(s + t2);
        }
        Arrays.sort(ps);
    }

    public long minimumCost(int[] nums) {
        this.nums = nums;
        Arrays.sort(nums);
        int i = Arrays.binarySearch(ps, nums[nums.length / 2]);
        i = i < 0 ? -i - 1 : i;
        long ans = 1L << 60;
        for (int j = i - 1; j <= i + 1; j++) {
            if (0 <= j && j < ps.length) {
                ans = Math.min(ans, f(ps[j]));
            }
        }
        return ans;
    }

    private long f(long x) {
        long ans = 0;
        for (int v : nums) {
            ans += Math.abs(v - x);
        }
        return ans;
    }
}
```

```cpp
using ll = long long;

ll ps[2 * 100000];

int init = [] {
    for (int i = 1; i <= 100000; i++) {
        string s = to_string(i);
        string t1 = s;
        reverse(t1.begin(), t1.end());
        string t2 = s.substr(0, s.length() - 1);
        reverse(t2.begin(), t2.end());
        ps[2 * i - 2] = stoll(s + t1);
        ps[2 * i - 1] = stoll(s + t2);
    }
    sort(ps, ps + 2 * 100000);
    return 0;
}();

class Solution {
public:
    long long minimumCost(vector<int>& nums) {
        sort(nums.begin(), nums.end());
        int i = lower_bound(ps, ps + 2 * 100000, nums[nums.size() / 2]) - ps;
        auto f = [&](ll x) {
            ll ans = 0;
            for (int& v : nums) {
                ans += abs(v - x);
            }
            return ans;
        };
        ll ans = LLONG_MAX;
        for (int j = i - 1; j <= i + 1; j++) {
            if (0 <= j && j < 2 * 100000) {
                ans = min(ans, f(ps[j]));
            }
        }
        return ans;
    }
};
```

```go
var ps [2 * 100000]int64

func init() {
	for i := 1; i <= 100000; i++ {
		s := strconv.Itoa(i)
		t1 := reverseString(s)
		t2 := reverseString(s[:len(s)-1])
		ps[2*i-2], _ = strconv.ParseInt(s+t1, 10, 64)
		ps[2*i-1], _ = strconv.ParseInt(s+t2, 10, 64)
	}
	sort.Slice(ps[:], func(i, j int) bool {
		return ps[i] < ps[j]
	})
}

func reverseString(s string) string {
	cs := []rune(s)
	for i, j := 0, len(cs)-1; i < j; i, j = i+1, j-1 {
		cs[i], cs[j] = cs[j], cs[i]
	}
	return string(cs)
}

func minimumCost(nums []int) int64 {
	sort.Ints(nums)
	i := sort.Search(len(ps), func(i int) bool {
		return ps[i] >= int64(nums[len(nums)/2])
	})

	f := func(x int64) int64 {
		var ans int64
		for _, v := range nums {
			ans += int64(abs(int(x - int64(v))))
		}
		return ans
	}

	ans := int64(math.MaxInt64)
	for j := i - 1; j <= i+1; j++ {
		if 0 <= j && j < len(ps) {
			ans = min(ans, f(ps[j]))
		}
	}
	return ans
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}
```

```ts
const ps = Array(2e5).fill(0);

const init = (() => {
    for (let i = 1; i <= 1e5; ++i) {
        const s: string = i.toString();
        const t1: string = s.split('').reverse().join('');
        const t2: string = s.slice(0, -1).split('').reverse().join('');
        ps[2 * i - 2] = parseInt(s + t1, 10);
        ps[2 * i - 1] = parseInt(s + t2, 10);
    }
    ps.sort((a, b) => a - b);
})();

function minimumCost(nums: number[]): number {
    const search = (x: number): number => {
        let [l, r] = [0, ps.length];
        while (l < r) {
            const mid = (l + r) >> 1;
            if (ps[mid] >= x) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    };
    const f = (x: number): number => {
        return nums.reduce((acc, v) => acc + Math.abs(v - x), 0);
    };

    nums.sort((a, b) => a - b);
    const i: number = search(nums[nums.length >> 1]);
    let ans: number = Number.MAX_SAFE_INTEGER;
    for (let j = i - 1; j <= i + 1; j++) {
        if (j >= 0 && j < ps.length) {
            ans = Math.min(ans, f(ps[j]));
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- end -->
