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

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def checkArithmeticSubarrays(
        self, nums: List[int], l: List[int], r: List[int]
    ) -> List[bool]:
        def check(nums, l, r):
            if r - l < 2:
                return True
            s = set(nums[l : r + 1])
            mx = max(nums[l : r + 1])
            mi = min(nums[l : r + 1])
            if (mx - mi) % (r - l) != 0:
                return False
            delta = (mx - mi) / (r - l)
            for i in range(1, r - l + 1):
                if (mi + delta * i) not in s:
                    return False
            return True

        return [check(nums, l[i], r[i]) for i in range(len(l))]
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public List<Boolean> checkArithmeticSubarrays(int[] nums, int[] l, int[] r) {
        List<Boolean> res = new ArrayList<>();
        for (int i = 0; i < l.length; ++i) {
            res.add(check(nums, l[i], r[i]));
        }
        return res;
    }

    private boolean check(int[] nums, int l, int r) {
        if (r - l < 2) {
            return true;
        }
        Set<Integer> s = new HashSet<>();
        int mx = Integer.MIN_VALUE;
        int mi = Integer.MAX_VALUE;
        for (int i = l; i <= r; ++i) {
            s.add(nums[i]);
            mx = Math.max(mx, nums[i]);
            mi = Math.min(mi, nums[i]);
        }
        if ((mx - mi) % (r - l) != 0) {
            return false;
        }
        int delta = (mx - mi) / (r - l);
        for (int i = 1; i <= r - l; ++i) {
            if (!s.contains(mi + delta * i)) {
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
        vector<bool> res;
        for (int i = 0; i < l.size(); ++i) {
            res.push_back(check(nums, l[i], r[i]));
        }
        return res;
    }

    bool check(vector<int>& nums, int l, int r) {
        if (r - l < 2) return true;
        unordered_set<int> s;
        int mx = -100010;
        int mi = 100010;
        for (int i = l; i <= r; ++i) {
            s.insert(nums[i]);
            mx = max(mx, nums[i]);
            mi = min(mi, nums[i]);
        }
        if ((mx - mi) % (r - l) != 0) return false;
        int delta = (mx - mi) / (r - l);
        for (int i = 1; i <= r - l; ++i) {
            if (!s.count(mi + delta * i)) return false;
        }
        return true;
    }
};
```

### **Go**

```go
func checkArithmeticSubarrays(nums []int, l []int, r []int) []bool {
	n := len(l)
	var res []bool
	for i := 0; i < n; i++ {
		res = append(res, check(nums, l[i], r[i]))
	}
	return res
}

func check(nums []int, l, r int) bool {
	if r-l < 2 {
		return true
	}
	s := make(map[int]bool)
	mx, mi := -100010, 100010
	for i := l; i <= r; i++ {
		s[nums[i]] = true
		mx = max(mx, nums[i])
		mi = min(mi, nums[i])
	}
	if (mx-mi)%(r-l) != 0 {
		return false
	}
	delta := (mx - mi) / (r - l)
	for i := 1; i <= r-l; i++ {
		if !s[mi+delta*i] {
			return false
		}
	}
	return true
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
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
function checkArithmeticSubarrays(
    nums: number[],
    l: number[],
    r: number[],
): boolean[] {
    const m = l.length;
    const res = new Array(m).fill(true);
    for (let i = 0; i < m; i++) {
        const arr = nums.slice(l[i], r[i] + 1).sort((a, b) => b - a);
        for (let j = 2; j < arr.length; j++) {
            if (arr[j - 2] - arr[j - 1] !== arr[j - 1] - arr[j]) {
                res[i] = false;
                break;
            }
        }
    }
    return res;
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
