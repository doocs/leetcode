# [剑指 Offer II 006. 排序数组中两个数字之和](https://leetcode.cn/problems/kLl5u1)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个已按照<strong><em> </em>升序排列&nbsp; </strong>的整数数组&nbsp;<code>numbers</code> ，请你从数组中找出两个数满足相加之和等于目标数&nbsp;<code>target</code> 。</p>

<p>函数应该以长度为 <code>2</code> 的整数数组的形式返回这两个数的下标值<em>。</em><code>numbers</code> 的下标 <strong>从 0&nbsp;开始计数</strong> ，所以答案数组应当满足 <code>0&nbsp;&lt;= answer[0] &lt; answer[1] &lt;&nbsp;numbers.length</code>&nbsp;。</p>

<p>假设数组中存在且只存在一对符合条件的数字，同时一个数字不能使用两次。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>numbers = [1,2,4,6,10], target = 8
<strong>输出：</strong>[1,3]
<strong>解释：</strong>2 与 6 之和等于目标数 8 。因此 index1 = 1, index2 = 3 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>numbers = [2,3,4], target = 6
<strong>输出：</strong>[0,2]
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>numbers = [-1,0], target = -1
<strong>输出：</strong>[0,1]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= numbers.length &lt;= 3 * 10<sup>4</sup></code></li>
	<li><code>-1000 &lt;= numbers[i] &lt;= 1000</code></li>
	<li><code>numbers</code> 按 <strong>递增顺序</strong> 排列</li>
	<li><code>-1000 &lt;= target &lt;= 1000</code></li>
	<li>仅存在一个有效答案</li>
</ul>

<p>&nbsp;</p>

<p>注意：本题与主站 167 题相似（下标起点不同）：<a href="https://leetcode.cn/problems/two-sum-ii-input-array-is-sorted/">https://leetcode.cn/problems/two-sum-ii-input-array-is-sorted/</a></p>

## 解法

<!-- 这里可写通用的实现逻辑 -->

双指针

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def twoSum(self, numbers: List[int], target: int) -> List[int]:
        i, j = 0, len(numbers) - 1
        while True:
            if numbers[i] + numbers[j] < target:
                i += 1
            elif numbers[i] + numbers[j] > target:
                j -= 1
            else:
                return [i, j]
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int[] twoSum(int[] numbers, int target) {
        int i = 0, j = numbers.length - 1;
        for (;;) {
            if (numbers[i] + numbers[j] < target) {
                i++;
            } else if (numbers[i] + numbers[j] > target) {
                j--;
            } else {
                return new int[] {i, j};
            }
        }
    }
}
```

### **Go**

```go
func twoSum(numbers []int, target int) []int {
	for i, j := 0, len(numbers)-1; ; {
		if numbers[i]+numbers[j] < target {
			i++
		} else if numbers[i]+numbers[j] > target {
			j--
		} else {
			return []int{i, j}
		}
	}
}
```

### **C++**

```cpp
class Solution {
public:
    vector<int> twoSum(vector<int>& numbers, int target) {
        int i = 0;
        int j = numbers.size() - 1;
        vector<int> res;

        while (i < j) {
            int sum = numbers[i] + numbers[j];
            if (sum < target) {
                i++;
            } else if (sum > target) {
                j--;
            } else {
                res.push_back(i);
                res.push_back(j);
                break;
            }
        }

        return res;
    }
};
```

### **Rust**

```rust
use std::cmp::Ordering;

impl Solution {
    pub fn two_sum(numbers: Vec<i32>, target: i32) -> Vec<i32> {
        let n = numbers.len();
        let mut l = 0;
        let mut r = n - 1;
        loop {
            match target.cmp(&(numbers[l] + numbers[r])) {
                Ordering::Less => r -= 1,
                Ordering::Greater => l += 1,
                Ordering::Equal => break,
            }
        }
        vec![l as i32, r as i32]
    }
}
```

```rust
use std::cmp::Ordering;

impl Solution {
    pub fn two_sum(numbers: Vec<i32>, target: i32) -> Vec<i32> {
        let n = numbers.len();
        for i in 0..n - 1 {
            let num = target - numbers[i];
            let mut l = i + 1;
            let mut r = n - 1;
            while l <= r {
                let mid = l + (r - l) / 2;
                match num.cmp(&numbers[mid]) {
                    Ordering::Less => r = mid - 1,
                    Ordering::Greater => l = mid + 1,
                    Ordering::Equal => return vec![i as i32, mid as i32],
                }
            }
        }
        vec![-1, -1]
    }
}
```

### **...**

```

```

<!-- tabs:end -->
