# [1630. 等差子数组](https://leetcode.cn/problems/arithmetic-subarrays)

[English Version](/solution/1600-1699/1630.Arithmetic%20Subarrays/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>如果一个数列由至少两个元素组成，且每两个连续元素之间的差值都相同，那么这个序列就是 <strong>等差数列</strong> 。更正式地，数列 <code>s</code> 是等差数列，只需要满足：对于每个有效的 <code>i</code> ， <code>s[i+1] - s[i] == s[1] - s[0]</code> 都成立。</p>

<p>例如，下面这些都是 <strong>等差数列</strong> ：</p>

<pre>1, 3, 5, 7, 9
7, 7, 7, 7
3, -1, -5, -9</pre>

<p>下面的数列 <strong>不是等差数列</strong> ：</p>

<pre>1, 1, 2, 5, 7</pre>

<p>给你一个由 <code>n</code> 个整数组成的数组 <code>nums</code>，和两个由 <code>m</code> 个整数组成的数组 <code>l</code> 和 <code>r</code>，后两个数组表示 <code>m</code> 组范围查询，其中第 <code>i</code> 个查询对应范围 <code>[l[i], r[i]]</code> 。所有数组的下标都是 <strong>从 0 开始</strong> 的。</p>

<p>返回<em> </em><code>boolean</code> 元素构成的答案列表 <code>answer</code> 。如果子数组 <code>nums[l[i]], nums[l[i]+1], ... , nums[r[i]]</code> 可以 <strong>重新排列</strong> 形成 <strong>等差数列</strong> ，<code>answer[i]</code> 的值就是 <code>true</code>；否则<code>answer[i]</code> 的值就是 <code>false</code> 。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>nums = <code>[4,6,5,9,3,7]</code>, l = <code>[0,0,2]</code>, r = <code>[2,3,5]</code>
<strong>输出：</strong><code>[true,false,true]</code>
<strong>解释：</strong>
第 0 个查询，对应子数组 [4,6,5] 。可以重新排列为等差数列 [6,5,4] 。
第 1 个查询，对应子数组 [4,6,5,9] 。无法重新排列形成等差数列。
第 2 个查询，对应子数组 <code>[5,9,3,7] 。</code>可以重新排列为等差数列 <code>[3,5,7,9] 。</code></pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>nums = [-12,-9,-3,-12,-6,15,20,-25,-20,-15,-10], l = [0,1,6,4,8,7], r = [4,4,9,7,9,10]
<strong>输出：</strong>[false,true,false,false,true,true]
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == nums.length</code></li>
	<li><code>m == l.length</code></li>
	<li><code>m == r.length</code></li>
	<li><code>2 &lt;= n &lt;= 500</code></li>
	<li><code>1 &lt;= m &lt;= 500</code></li>
	<li><code>0 &lt;= l[i] &lt; r[i] &lt; n</code></li>
	<li><code>-10<sup>5</sup> &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：数学 + 模拟**

我们设计一个函数 $check(nums, l, r)$，用于判断子数组 $nums[l], nums[l+1], \dots, nums[r]$ 是否可以重新排列形成等差数列。

函数 $check(nums, l, r)$ 的实现逻辑如下：

-   首先，我们计算子数组的长度 $n = r - l + 1$，并将子数组中的元素放入集合 $s$ 中，方便后续的查找；
-   然后，我们获取子数组中的最小值 $a_1$ 和最大值 $a_n$，如果 $a_n - a_1$ 不能被 $n - 1$ 整除，那么子数组不可能形成等差数列，直接返回 $false$；否则，我们计算等差数列的公差 $d = \frac{a_n - a_1}{n - 1}$；
-   接下来从 $a_1$ 开始，依次计算等差数列中第 $i$ 项元素，如果第 $i$ 项元素 $a_1 + (i - 1) \times d$ 不在集合 $s$ 中，那么子数组不可能形成等差数列，直接返回 $false$；否则，当我们遍历完所有的元素，说明子数组可以重新排列形成等差数列，返回 $true$。

在主函数中，我们遍历所有的查询，对于每个查询 $l[i]$ 和 $r[i]$，我们调用函数 $check(nums, l[i], r[i])$ 判断子数组是否可以重新排列形成等差数列，将结果存入答案数组中。

时间复杂度 $O(n \times m)$，空间复杂度 $O(n)$。其中 $n$ 和 $m$ 分别为数组 $nums$ 的长度以及查询的组数。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def checkArithmeticSubarrays(self, nums: List[int], l: List[int], r: List[int]) -> List[bool]:
        def check(nums, l, r):
            n = r - l + 1
            s = set(nums[l: l + n])
            a1, an = min(nums[l: l + n]), max(nums[l: l + n])
            d, mod = divmod(an - a1, n - 1)
            return mod == 0 and all((a1 + (i - 1) * d) in s for i in range(1, n))

        return [check(nums, left, right) for left, right in zip(l, r)]
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public List<Boolean> checkArithmeticSubarrays(int[] nums, int[] l, int[] r) {
        List<Boolean> ans = new ArrayList<>();
        for (int i = 0; i < l.length; ++i) {
            ans.add(check(nums, l[i], r[i]));
        }
        return ans;
    }

    private boolean check(int[] nums, int l, int r) {
        Set<Integer> s = new HashSet<>();
        int n = r - l + 1;
        int a1 = 1 << 30, an = -a1;
        for (int i = l; i <= r; ++i) {
            s.add(nums[i]);
            a1 = Math.min(a1, nums[i]);
            an = Math.max(an, nums[i]);
        }
        if ((an - a1) % (n - 1) != 0) {
            return false;
        }
        int d = (an - a1) / (n - 1);
        for (int i = 1; i < n; ++i) {
            if (!s.contains(a1 + (i - 1) * d)) {
                return false;
            }
        }
        return true;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<bool> checkArithmeticSubarrays(vector<int>& nums, vector<int>& l, vector<int>& r) {
        vector<bool> ans;
        auto check = [](vector<int>& nums, int l, int r) {
            unordered_set<int> s;
            int n = r - l + 1;
            int a1 = 1 << 30, an = -a1;
            for (int i = l; i <= r; ++i) {
                s.insert(nums[i]);
                a1 = min(a1, nums[i]);
                an = max(an, nums[i]);
            }
            if ((an - a1) % (n - 1)) {
                return false;
            }
            int d = (an - a1) / (n - 1);
            for (int i = 1; i < n; ++i) {
                if (!s.count(a1 + (i - 1) * d)) {
                    return false;
                }
            }
            return true;
        };
        for (int i = 0; i < l.size(); ++i) {
            ans.push_back(check(nums, l[i], r[i]));
        }
        return ans;
    }
};
```

### **Go**

```go
func checkArithmeticSubarrays(nums []int, l []int, r []int) (ans []bool) {
	check := func(nums []int, l, r int) bool {
		s := map[int]struct{}{}
		n := r - l + 1
		a1, an := 1<<30, -(1 << 30)
		for _, x := range nums[l : r+1] {
			s[x] = struct{}{}
			if a1 > x {
				a1 = x
			}
			if an < x {
				an = x
			}
		}
		if (an-a1)%(n-1) != 0 {
			return false
		}
		d := (an - a1) / (n - 1)
		for i := 1; i < n; i++ {
			if _, ok := s[a1+(i-1)*d]; !ok {
				return false
			}
		}
		return true
	}
	for i := range l {
		ans = append(ans, check(nums, l[i], r[i]))
	}
	return
}
```

### **TypeScript**

```ts
function checkArithmeticSubarrays(
    nums: number[],
    l: number[],
    r: number[],
): boolean[] {
    const check = (nums: number[], l: number, r: number): boolean => {
        const s = new Set<number>();
        const n = r - l + 1;
        let a1 = 1 << 30;
        let an = -a1;
        for (let i = l; i <= r; ++i) {
            s.add(nums[i]);
            a1 = Math.min(a1, nums[i]);
            an = Math.max(an, nums[i]);
        }
        if ((an - a1) % (n - 1) !== 0) {
            return false;
        }
        const d = Math.floor((an - a1) / (n - 1));
        for (let i = 1; i < n; ++i) {
            if (!s.has(a1 + (i - 1) * d)) {
                return false;
            }
        }
        return true;
    };
    const ans: boolean[] = [];
    for (let i = 0; i < l.length; ++i) {
        ans.push(check(nums, l[i], r[i]));
    }
    return ans;
}
```

### **Rust**

```rust
impl Solution {
    pub fn check_arithmetic_subarrays(nums: Vec<i32>, l: Vec<i32>, r: Vec<i32>) -> Vec<bool> {
        let m = l.len();
        let mut res = vec![true; m];
        for i in 0..m {
            let mut arr = nums[l[i] as usize..=r[i] as usize].to_vec();
            arr.sort();
            for j in 2..arr.len() {
                if arr[j - 2] - arr[j - 1] != arr[j - 1] - arr[j] {
                    res[i] = false;
                    break;
                }
            }
        }
        res
    }
}
```

### **...**

```

```

<!-- tabs:end -->
