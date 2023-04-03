# [2601. 质数减法运算](https://leetcode.cn/problems/prime-subtraction-operation)

[English Version](/solution/2600-2699/2601.Prime%20Subtraction%20Operation/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个下标从 <strong>0</strong> 开始的整数数组 <code>nums</code> ，数组长度为 <code>n</code> 。</p>

<p>你可以执行无限次下述运算：</p>

<ul>
	<li>选择一个之前未选过的下标 <code>i</code> ，并选择一个 <strong>严格小于</strong> <code>nums[i]</code> 的质数 <code>p</code> ，从 <code>nums[i]</code> 中减去 <code>p</code> 。</li>
</ul>

<p>如果你能通过上述运算使得 <code>nums</code> 成为严格递增数组，则返回 <code>true</code> ；否则返回 <code>false</code> 。</p>

<p><strong>严格递增数组</strong> 中的每个元素都严格大于其前面的元素。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [4,9,6,10]
<strong>输出：</strong>true
<strong>解释：</strong>
在第一次运算中：选择 i = 0 和 p = 3 ，然后从 nums[0] 减去 3 ，nums 变为 [1,9,6,10] 。
在第二次运算中：选择 i = 1 和 p = 7 ，然后从 nums[1] 减去 7 ，nums 变为 [1,2,6,10] 。
第二次运算后，nums 按严格递增顺序排序，因此答案为 true 。</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [6,8,11,12]
<strong>输出：</strong>true
<strong>解释：</strong>nums 从一开始就按严格递增顺序排序，因此不需要执行任何运算。</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>nums = [5,8,3]
<strong>输出：</strong>false
<strong>解释：</strong>可以证明，执行运算无法使 nums 按严格递增顺序排序，因此答案是 false 。</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 1000</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 1000</code></li>
	<li><code>nums.length == n</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：预处理质数 + 二分查找**

我们先预处理得到 $1000$ 以内的所有质数，记录在数组 $p$ 中。

对于数组 $nums$ 中的每个元素 $nums[i]$，我们需要找到一个质数 $p[j]$，使得 p[j] \gt nums[i] - nums[i + 1]，并且 $p[j]$ 尽可能小。如果找不到这样的质数，说明无法通过减法运算使得 $nums$ 严格递增，返回 `false`。如果找到了这样的质数，我们就将 $nums[i]$ 减去 $p[j]$，并继续处理下一个元素。

如果 $nums$ 中的所有元素都处理完了，说明可以通过减法运算使得 $nums$ 严格递增，返回 `true`。

时间复杂度 $O(n \log n)$，空间复杂度 $O(n)$。其中 $n$ 为数组 $nums$ 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def primeSubOperation(self, nums: List[int]) -> bool:
        p = []
        for i in range(2, max(nums)):
            for j in p:
                if i % j == 0:
                    break
            else:
                p.append(i)

        n = len(nums)
        for i in range(n - 2, -1, -1):
            if nums[i] < nums[i + 1]:
                continue
            j = bisect_right(p, nums[i] - nums[i + 1])
            if j == len(p) or p[j] >= nums[i]:
                return False
            nums[i] -= p[j]
        return True
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public boolean primeSubOperation(int[] nums) {
        List<Integer> p = new ArrayList<>();
        for (int i = 2; i <= 1000; ++i) {
            boolean ok = true;
            for (int j : p) {
                if (i % j == 0) {
                    ok = false;
                    break;
                }
            }
            if (ok) {
                p.add(i);
            }
        }
        int n = nums.length;
        for (int i = n - 2; i >= 0; --i) {
            if (nums[i] < nums[i + 1]) {
                continue;
            }
            int j = search(p, nums[i] - nums[i + 1]);
            if (j == p.size() || p.get(j) >= nums[i]) {
                return false;
            }
            nums[i] -= p.get(j);
        }
        return true;
    }

    private int search(List<Integer> nums, int x) {
        int l = 0, r = nums.size();
        while (l < r) {
            int mid = (l + r) >> 1;
            if (nums.get(mid) > x) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    bool primeSubOperation(vector<int>& nums) {
        vector<int> p;
        for (int i = 2; i <= 1000; ++i) {
            bool ok = true;
            for (int j : p) {
                if (i % j == 0) {
                    ok = false;
                    break;
                }
            }
            if (ok) {
                p.push_back(i);
            }
        }
        int n = nums.size();
        for (int i = n - 2; i >= 0; --i) {
            if (nums[i] < nums[i + 1]) {
                continue;
            }
            int j = upper_bound(p.begin(), p.end(), nums[i] - nums[i + 1]) - p.begin();
            if (j == p.size() || p[j] >= nums[i]) {
                return false;
            }
            nums[i] -= p[j];
        }
        return true;
    }
};
```

### **Go**

```go
func primeSubOperation(nums []int) bool {
	p := []int{}
	for i := 2; i <= 1000; i++ {
		ok := true
		for _, j := range p {
			if i%j == 0 {
				ok = false
				break
			}
		}
		if ok {
			p = append(p, i)
		}
	}
	for i := len(nums) - 2; i >= 0; i-- {
		if nums[i] < nums[i+1] {
			continue
		}
		j := sort.SearchInts(p, nums[i]-nums[i+1]+1)
		if j == len(p) || p[j] >= nums[i] {
			return false
		}
		nums[i] -= p[j]
	}
	return true
}
```

### **TypeScript**

```ts
function primeSubOperation(nums: number[]): boolean {
    const p: number[] = [];
    for (let i = 2; i <= 1000; ++i) {
        let ok = true;
        for (const j of p) {
            if (i % j === 0) {
                ok = false;
                break;
            }
        }
        if (ok) {
            p.push(i);
        }
    }
    const search = (x: number): number => {
        let l = 0;
        let r = p.length;
        while (l < r) {
            const mid = (l + r) >> 1;
            if (p[mid] > x) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    };
    const n = nums.length;
    for (let i = n - 2; i >= 0; --i) {
        if (nums[i] < nums[i + 1]) {
            continue;
        }
        const j = search(nums[i] - nums[i + 1]);
        if (j === p.length || p[j] >= nums[i]) {
            return false;
        }
        nums[i] -= p[j];
    }
    return true;
}
```

### **...**

```

```

<!-- tabs:end -->
