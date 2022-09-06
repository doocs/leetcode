# [2170. 使数组变成交替数组的最少操作数](https://leetcode.cn/problems/minimum-operations-to-make-the-array-alternating)

[English Version](/solution/2100-2199/2170.Minimum%20Operations%20to%20Make%20the%20Array%20Alternating/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个下标从 <strong>0</strong> 开始的数组 <code>nums</code> ，该数组由 <code>n</code> 个正整数组成。</p>

<p>如果满足下述条件，则数组 <code>nums</code> 是一个 <strong>交替数组</strong> ：</p>

<ul>
	<li><code>nums[i - 2] == nums[i]</code> ，其中 <code>2 &lt;= i &lt;= n - 1</code> 。</li>
	<li><code>nums[i - 1] != nums[i]</code> ，其中 <code>1 &lt;= i &lt;= n - 1</code> 。</li>
</ul>

<p>在一步 <strong>操作</strong> 中，你可以选择下标 <code>i</code> 并将 <code>nums[i]</code> <strong>更改</strong> 为 <strong>任一</strong> 正整数。</p>

<p>返回使数组变成交替数组的 <strong>最少操作数</strong> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [3,1,3,2,4,3]
<strong>输出：</strong>3
<strong>解释：</strong>
使数组变成交替数组的方法之一是将该数组转换为 [3,1,3,<em><strong>1</strong></em>,<em><strong>3</strong></em>,<em><strong>1</strong></em>] 。
在这种情况下，操作数为 3 。
可以证明，操作数少于 3 的情况下，无法使数组变成交替数组。</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,2,2,2,2]
<strong>输出：</strong>2
<strong>解释：</strong>
使数组变成交替数组的方法之一是将该数组转换为 [1,2,<em><strong>1</strong></em>,2,<em><strong>1</strong></em>].
在这种情况下，操作数为 2 。
注意，数组不能转换成 [<em><strong>2</strong></em>,2,2,2,2] 。因为在这种情况下，nums[0] == nums[1]，不满足交替数组的条件。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def minimumOperations(self, nums: List[int]) -> int:
        def get(i):
            c = Counter(nums[i::2]).most_common(2)
            if not c:
                return [(0, 0), (0, 0)]
            if len(c) == 1:
                return [c[0], (0, 0)]
            return c

        n = len(nums)
        return min(n - (n1 + n2) for a, n1 in get(0) for b, n2 in get(1) if a != b)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    private int[] nums;
    private int n;

    public int minimumOperations(int[] nums) {
        this.nums = nums;
        n = nums.length;
        int ans = Integer.MAX_VALUE;
        for (int[] e1 : get(0)) {
            for (int[] e2 : get(1)) {
                if (e1[0] != e2[0]) {
                    ans = Math.min(ans, n - (e1[1] + e2[1]));
                }
            }
        }
        return ans;
    }

    private int[][] get(int i) {
        Map<Integer, Integer> freq = new HashMap<>();
        for (; i < n; i += 2) {
            freq.put(nums[i], freq.getOrDefault(nums[i], 0) + 1);
        }
        int a = 0;
        int n1 = 0;
        int b = 0;
        int n2 = 0;
        for (Map.Entry<Integer, Integer> e : freq.entrySet()) {
            int k = e.getKey();
            int v = e.getValue();
            if (v > n1) {
                b = a;
                n2 = n1;
                a = k;
                n1 = v;
            } else if (v > n2) {
                b = k;
                n2 = v;
            }
        }
        return new int[][] {{a, n1}, {b, n2}};
    }
}
```

### **TypeScript**

```ts
function minimumOperations(nums: number[]): number {
    const n = nums.length,
        m = 10 ** 5;
    let odd = new Array(m).fill(0);
    let even = new Array(m).fill(0);
    for (let i = 0; i < n; i++) {
        let cur = nums[i];
        if (i & 1) {
            odd[cur] = (odd[cur] || 0) + 1;
        } else {
            even[cur] = (even[cur] || 0) + 1;
        }
    }
    let i1 = odd.indexOf(Math.max(...odd));
    let i2 = even.indexOf(Math.max(...even));
    if (i1 != i2) {
        return n - odd[i1] - even[i2];
    } else {
        let l1 = odd[i1],
            l2 = even[i2];
        (odd[i1] = 0), (even[i2] = 0);
        let j1 = odd.indexOf(Math.max(...odd));
        let j2 = even.indexOf(Math.max(...even));
        return n - Math.max(l1 + even[j2], l2 + odd[j1]);
    }
}
```

### **C++**

```cpp
typedef pair<int, int> PII;

class Solution {
public:
    int minimumOperations(vector<int>& nums) {
        int ans = INT_MAX;
        int n = nums.size();
        for (auto& [a, n1] : get(0, nums))
            for (auto& [b, n2] : get(1, nums))
                if (a != b)
                    ans = min(ans, n - (n1 + n2));
        return ans;
    }

    vector<PII> get(int i, vector<int>& nums) {
        unordered_map<int, int> freq;
        for (; i < nums.size(); i += 2) ++freq[nums[i]];
        int a = 0, n1 = 0, b = 0, n2 = 0;
        for (auto& [k, v] : freq) {
            if (v > n1) {
                b = a;
                n2 = n1;
                a = k;
                n1 = v;
            } else if (v > n2) {
                b = k;
                n2 = v;
            }
        }
        return {{a, n1}, {b, n2}};
    }
};
```

### **Go**

```go
func minimumOperations(nums []int) int {
	n := len(nums)
	get := func(i int) [][]int {
		freq := make(map[int]int)
		for ; i < n; i += 2 {
			freq[nums[i]]++
		}
		a, n1, b, n2 := 0, 0, 0, 0
		for k, v := range freq {
			if v > n1 {
				b, n2, a, n1 = a, n1, k, v
			} else if v > n2 {
				b, n2 = k, v
			}
		}
		return [][]int{{a, n1}, {b, n2}}
	}
	ans := 100000
	for _, e1 := range get(0) {
		for _, e2 := range get(1) {
			if e1[0] != e2[0] && ans > (n-(e1[1]+e2[1])) {
				ans = n - (e1[1] + e2[1])
			}
		}
	}
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
